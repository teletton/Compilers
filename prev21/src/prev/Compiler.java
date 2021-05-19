package prev;

import java.io.PrintWriter;
import java.util.*;

import org.antlr.v4.runtime.*;

import prev.common.report.*;
import prev.phase.lexan.*;
import prev.phase.synan.*;
import prev.phase.abstr.*;
import prev.phase.seman.*;
import prev.phase.memory.*;
import prev.phase.imcgen.*;
import prev.phase.imclin.*;
import prev.phase.asmgen.*;
import prev.phase.livean.*;
import prev.phase.regall.*;
import prev.data.mem.*;
import prev.data.asm.*;

/**
 * The compiler.
 */
public class Compiler {

	// COMMAND LINE ARGUMENTS

	/** All valid phases of the compiler. */
	private static final String phases = "lexan|synan|abstr|seman|memory|imcgen|imclin|asmgen|livean|regall|none";

	/** Values of command line arguments. */
	private static HashMap<String, String> cmdLine = new HashMap<String, String>();
	public static int numofregs = 8;

	public int funnum() {
		return this.numofregs;
	}

	/**
	 * Returns the value of a command line argument.
	 * 
	 * @param cmdLineArgName The name of the command line argument.
	 * @return The value of the specified command line argument or {@code null} if
	 *         the specified command line argument has not been used.
	 */
	public static String cmdLineArgValue(String cmdLineArgName) {
		return cmdLine.get(cmdLineArgName);
	}

	// THE COMPILER'S STARTUP METHOD

	/**
	 * The compiler's startup method.
	 * 
	 * @param args Command line arguments (see {@link prev.Compiler}).
	 */
	public static void main(String[] args) {
		try {
			Report.info("This is PREV'21 compiler:");

			// Scan the command line.
			for (int argc = 0; argc < args.length; argc++) {
				if (args[argc].startsWith("--")) {
					// Command-line switch.
					if (args[argc].matches("--num-regs=.*")) {
						String num = "";
						for (int i = 11; i < args[argc].length(); i++) {
							num += args[argc].charAt(i);
						}
						numofregs = Integer.parseInt(num);
					}
					if (args[argc].matches("--src-file-name=.*")) {
						if (cmdLine.get("--src-file-name") == null) {
							cmdLine.put("--src-file-name", args[argc]);
							continue;
						}
					}
					if (args[argc].matches("--dst-file-name=.*")) {
						if (cmdLine.get("--dst-file-name") == null) {
							cmdLine.put("--dst-file-name", args[argc]);
							continue;
						}
					}
					if (args[argc].matches("--target-phase=(" + phases + "|all)")) {
						if (cmdLine.get("--target-phase") == null) {
							cmdLine.put("--target-phase", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					if (args[argc].matches("--logged-phase=(" + phases + "|all)")) {
						if (cmdLine.get("--logged-phase") == null) {
							cmdLine.put("--logged-phase", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					if (args[argc].matches("--xml=.*")) {
						if (cmdLine.get("--xml") == null) {
							cmdLine.put("--xml", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					if (args[argc].matches("--xsl=.*")) {
						if (cmdLine.get("--xsl") == null) {
							cmdLine.put("--xsl", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					Report.warning("Command line argument '" + args[argc] + "' ignored.");
				} else {
					// Source file name.
					if (cmdLine.get("--src-file-name") == null) {
						cmdLine.put("--src-file-name", args[argc]);
					} else {
						Report.warning("Source file '" + args[argc] + "' ignored.");
					}
				}
			}
			if (cmdLine.get("--src-file-name") == null) {
				throw new Report.Error("Source file not specified.");
			}
			if (cmdLine.get("--dst-file-name") == null) {
				cmdLine.put("--dst-file-name", cmdLine.get("--src-file-name").replaceFirst("\\.[^./]*$", "") + ".mms");
			}
			if ((cmdLine.get("--target-phase") == null) || (cmdLine.get("--target-phase").equals("all"))) {
				cmdLine.put("--target-phase", phases.replaceFirst("^.*\\|", ""));
			}

			// Compilation process carried out phase by phase.
			while (true) {

				// Lexical analysis.
				if (Compiler.cmdLineArgValue("--target-phase").equals("lexan"))
					try (LexAn lexan = new LexAn()) {
						while (lexan.lexer.nextToken().getType() != Token.EOF) {
						}
						break;
					}

				// Syntax analysis.
				try (LexAn lexan = new LexAn(); SynAn synan = new SynAn(lexan)) {
					SynAn.tree = synan.parser.source();
					synan.log(SynAn.tree);
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("synan"))
					break;

				// Abstract syntax tree construction.
				try (Abstr abstr = new Abstr()) {
					Abstr.tree = SynAn.tree.ast;
					AbsLogger logger = new AbsLogger(abstr.logger);
					Abstr.tree.accept(logger, "Decls");
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("abstr"))
					break;

				// Semantic analysis.
				try (SemAn seman = new SemAn()) {
					Abstr.tree.accept(new NameResolver(), 0);
					Abstr.tree.accept(new TypeResolver(), 0);
					Abstr.tree.accept(new AddrResolver(), 0);
					AbsLogger logger = new AbsLogger(seman.logger);
					logger.addSubvisitor(new SemLogger(seman.logger));
					Abstr.tree.accept(logger, "Decls");
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("seman"))
					break;

				// Memory layout.
				try (Memory memory = new Memory()) {
					Abstr.tree.accept(new MemEvaluator(), 0);
					AbsLogger logger = new AbsLogger(memory.logger);
					logger.addSubvisitor(new SemLogger(memory.logger));
					logger.addSubvisitor(new MemLogger(memory.logger));
					Abstr.tree.accept(logger, "Decls");
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("memory"))
					break;

				// Intermediate code generation.
				try (ImcGen imcgen = new ImcGen()) {
					Stack<MemFrame> ss = new Stack<MemFrame>();
					Abstr.tree.accept(new CodeGenerator(), ss);
					AbsLogger logger = new AbsLogger(imcgen.logger);
					logger.addSubvisitor(new SemLogger(imcgen.logger));
					logger.addSubvisitor(new MemLogger(imcgen.logger));
					logger.addSubvisitor(new ImcLogger(imcgen.logger));
					Abstr.tree.accept(logger, "Decls");
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("imcgen"))
					break;

				// Linearization of intermediate code.
				try (ImcLin imclin = new ImcLin()) {
					Abstr.tree.accept(new ChunkGenerator(), 0);
					imclin.log();

					// Interpreter interpreter = new Interpreter(ImcLin.dataChunks(),
					// ImcLin.codeChunks());
					// System.out.println("EXIT CODE: " + interpreter.run("_main"));
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("imclin"))
					break;

				// Machine code generation.
				try (AsmGen asmgen = new AsmGen()) {
					asmgen.numm = numofregs;
					asmgen.genAsmCodes();
					asmgen.log();
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("asmgen"))
					break;

				// Liveness analysis.
				try (LiveAn livean = new LiveAn()) {
					livean.analysis();
					livean.log();
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("livean"))
					break;

				// Register allocation.
				HashMap<MemTemp, Integer> t2r;
				try (RegAll regall = new RegAll()) {
					regall.numreg = numofregs;
					regall.allocate();
					t2r = regall.tempToReg;
					regall.log();
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("regall"))
					break;

				// Convert to mmix
				/*
				 * try (Mmix mmixa = new Mmix()) { mmixa.regs = hm; mmixa.finish(); }
				 */
				String numregss = Integer.toString(numofregs);
				PrintWriter mmix;
				HashMap<String, Integer> hsml = new HashMap<String, Integer>();
				for (int i = 0; i < AsmGen.codes.size(); i++) {
					hsml.put(AsmGen.codes.get(i).frame.label.name, 1);
				}
				try {
					mmix = new PrintWriter("prev21.mms", "UTF-8");
				} catch (Exception e) {
					throw new Report.Error("GRESHKA");
				}
				mmix.printf("%-16s\t%s\t%s\n", "", "LOC", "#0");
				mmix.printf("%-16s\t%s\t%s\n", "", "GREG", "0");
				mmix.printf("%-16s\t%s\t%s\n", "", "GREG", "0");
				mmix.printf("%-16s\t%s\t%s\n", "", "GREG", "0");
				mmix.printf("\n");
				mmix.printf("%-16s\t%s\t%s\n", "", "LOC", "#10000000");
				for (int i = 0; i < ImcLin.dataChunks().size(); i++) {
					mmix.printf("%-16s\t%s\t%s\n", "", "GREG", "@");
					if (ImcLin.dataChunks().get(i).init == null) {
						int len = (int) (ImcLin.dataChunks().get(i).size) / 8;
						for (int j = 0; j < len; j++) {
							if (j < 1) {
								String s = ImcLin.dataChunks().get(i).label.name;
								mmix.printf("%-16s\t%s\t%s\n", s, "OCTA", "0");
							} else {
								mmix.printf("%-16s\t%s\t%s\n", "", "OCTA", "0");
							}
						}
					} else {
						int len = ImcLin.dataChunks().get(i).init.length() - 1;
						for (int j = 1; j < len; j++) {
							String ch = Integer.toString((int) ImcLin.dataChunks().get(i).init.charAt(j));
							String s = ImcLin.dataChunks().get(i).label.name;
							if (j < 2) {
								mmix.printf("%-16s\t%s\t%s\n", s, "OCTA", ch);
							} else {
								mmix.printf("%-16s\t%s\t%s\n", "", "OCTA", ch);
							}
						}
						mmix.printf("%-16s\t%s\t%s\n", "", "OCTA", "0");
					}
				}
				mmix.printf("\n");
				// initoutreg
				mmix.printf("%-16s\t%s\t%s\n", "", "LOC", "#20000000");
				mmix.printf("%-16s\t%s\t%s\n", "", "GREG", "@");
				mmix.printf("%-16s\t%s\t%s\n", "OutData", "BYTE", "0");
				mmix.printf("\n");
				// PutChar
				mmix.printf("%-16s\t%s\t%s\n", "", "LOC", "#30000000");
				mmix.printf("%-16s\t%s\t%s\n", "", "GREG", "@");
				mmix.printf("%-16s\t%s\t%s\n", "_putChar", "LDO", "$0,$253,8");
				mmix.printf("%-16s\t%s\t%s\n", "", "LDA", "$1,OutData");
				mmix.printf("%-16s\t%s\t%s\n", "", "OR", "$255,$1,0");
				mmix.printf("%-16s\t%s\t%s\n", "", "STB", "$0,$1,0");
				mmix.printf("%-16s\t%s\t%s\n", "", "ADD", "$1,$1,1");
				mmix.printf("%-16s\t%s\t%s\n", "", "SETL", "$0,0");
				mmix.printf("%-16s\t%s\t%s\n", "", "STB", "$0,$1,0");
				mmix.printf("%-16s\t%s\t%s\n", "", "TRAP", "0,Fputs,StdOut");
				mmix.printf("%-16s\t%s\t%s\n", "", "POP", numregss + ",0");
				mmix.printf("\n");
				if (!hsml.containsKey("_exit")) {
					mmix.printf("%-16s\t%s\t%s\n", "_exit", "TRAP", "0,Halt,0");
					mmix.printf("\n");
				}
				if (!hsml.containsKey("_getChar")) {
					mmix.printf("%-16s\t%s\t%s\n", "_getChar", "TRAP", "0,Halt,0");
					mmix.printf("\n");
				}
				for (int i = 0; i < AsmGen.codes.size(); i++) {
					Code code = AsmGen.codes.get(i);
					// if (code.frame.label.name.equals("_putString"))
					// continue;
					// Prologue
					long lsize = code.frame.locsSize;
					String s = code.frame.label.name;
					long size = code.frame.size;
					String ent = code.entryLabel.name;
					String ext = code.exitLabel.name;
					mmix.printf("%-16s\t%s\t%s\n", "", "GREG", "@");
					mmix.printf("%-16s\t%s\t%s\n", s, "SETH", "$0," + Math.abs(0xFFFF000000000000L & lsize));
					mmix.printf("%-16s\t%s\t%s\n", "", "INCMH", "$0," + Math.abs(0x0000FFFF00000000L & lsize));
					mmix.printf("%-16s\t%s\t%s\n", "", "INCML", "$0," + Math.abs(0x00000000FFFF0000L & lsize));
					mmix.printf("%-16s\t%s\t%s\n", "", "INCL", "$0," + Math.abs(0x000000000000FFFFL & lsize));
					mmix.printf("%-16s\t%s\t%s\n", "", "ADD", "$0,$0,8");
					mmix.printf("%-16s\t%s\t%s\n", "", "SUB", "$0,$253,$0");
					mmix.printf("%-16s\t%s\t%s\n", "", "STO", "$254,$0,0");
					mmix.printf("%-16s\t%s\t%s\n", "", "SUB", "$0,$0,8");
					mmix.printf("%-16s\t%s\t%s\n", "", "GET", "$1,rJ");
					mmix.printf("%-16s\t%s\t%s\n", "", "STO", "$1,$0,0");
					mmix.printf("%-16s\t%s\t%s\n", "", "OR", "$254,$253,0");
					mmix.printf("%-16s\t%s\t%s\n", "", "SETH", "$0," + Math.abs(0xFFFF000000000000L & size));
					mmix.printf("%-16s\t%s\t%s\n", "", "INCMH", "$0," + Math.abs(0x0000FFFF00000000L & size));
					mmix.printf("%-16s\t%s\t%s\n", "", "INCML", "$0," + Math.abs(0x00000000FFFF0000L & size));
					mmix.printf("%-16s\t%s\t%s\n", "", "INCL", "$0," + Math.abs(0x000000000000FFFFL & size));
					mmix.printf("%-16s\t%s\t%s\n", "", "SUB", "$253,$253,$0");
					mmix.printf("%-16s\t%s\t%s\n", "", "JMP", ent);
					mmix.printf("%-16s\t%s\t%s\n", "", "GREG", "@");

					for (int j = 0; j < code.instrs.size(); j++) {
						if (!(code.instrs.get(j) instanceof AsmLABEL)) {
							// System.out.println(((AsmOPER) code.instrs.get(j)).);
							String[] ss = ((AsmOPER) code.instrs.get(j)).toString(t2r).split(" ");
							mmix.printf("%-16s\t%s\t%s\n", "", ss[0], ss[1]);
						} else {
							mmix.printf("%-16s\t%s\t%s\n", code.instrs.get(j), "ADD", "$0,$0,0");
						}
					}

					mmix.printf("%-16s\t%s\t%s\n", "", "GREG", "@");
					mmix.printf("%-16s\t%s\t%s\n", ext, "OR", "$0,$" + t2r.get(code.frame.RV) + ",0");
					// mmix.printf("%-16s\t%s\t%s\n", "", "OR", "$1,$254,0");
					mmix.printf("%-16s\t%s\t%s\n", "", "STO", "$0,$254,0");
					mmix.printf("%-16s\t%s\t%s\n", "", "OR", "$253,$254,0");
					mmix.printf("%-16s\t%s\t%s\n", "", "SETH", "$0," + Math.abs(0xFFFF000000000000L & lsize));
					mmix.printf("%-16s\t%s\t%s\n", "", "INCMH", "$0," + Math.abs(0x0000FFFF00000000L & lsize));
					mmix.printf("%-16s\t%s\t%s\n", "", "INCML", "$0," + Math.abs(0x00000000FFFF0000L & lsize));
					mmix.printf("%-16s\t%s\t%s\n", "", "INCL", "$0," + Math.abs(0x000000000000FFFFL & lsize));
					mmix.printf("%-16s\t%s\t%s\n", "", "ADD", "$0,$0,8");
					mmix.printf("%-16s\t%s\t%s\n", "", "SUB", "$0,$254,$0");
					mmix.printf("%-16s\t%s\t%s\n", "", "LDO", "$254,$0,0");
					mmix.printf("%-16s\t%s\t%s\n", "", "SUB", "$0,$0,8");
					mmix.printf("%-16s\t%s\t%s\n", "", "LDO", "$0,$0,0");
					mmix.printf("%-16s\t%s\t%s\n", "", "PUT", "rJ,$0");
					mmix.printf("%-16s\t%s\t%s\n", "", "POP", numregss + ",0");
				}
				mmix.printf("%-16s\t%s\t%s\n", "", "GREG", "@");
				mmix.printf("%-16s\t%s\t%s\n", "Main", "SETH", "$253,#3000");
				mmix.printf("%-16s\t%s\t%s\n", "", "SETH", "$254,#3000");
				mmix.printf("%-16s\t%s\t%s\n", "", "PUSHJ", "$" + numregss + ",_main");
				mmix.printf("%-16s\t%s\t%s\n", "", "TRAP", "0,Halt,0");
				mmix.printf("\n");
				mmix.close();
				break;

			}

			Report.info("Done.");
		} catch (Report.Error __) {
			System.exit(1);
		}
	}

}
