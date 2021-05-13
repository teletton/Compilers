package prev.phase.mmix;

import java.io.*;
import java.util.*;
import prev.phase.*;
import prev.data.asm.*;
import prev.data.mem.*;
import prev.data.typ.*;
import prev.data.lin.*;
import prev.common.report.*;
import prev.phase.lexan.*;
import prev.phase.synan.*;
import prev.phase.abstr.*;
import prev.phase.asmgen.*;
import prev.phase.seman.*;
import prev.phase.memory.*;
import prev.phase.imcgen.*;
import prev.phase.imclin.*;
import prev.phase.livean.*;
import prev.phase.regall.*;

public class Mmix extends Phase {
    public int numregs = 8;
    public static PrintWriter file;
    public HashMap<MemTemp, Integer> regs;

    public Mmix() {
        super("mmixa");

        try {
            file = new PrintWriter("code.mms", "UTF-8");
        } catch (Exception e) {
            System.out.println("PrintWriter err");
        }
    }

    public void finish() {
        System.out.println("VLEZE");
        String f = "%-16s\t%s\t%s\n";
        file.printf(f, "", "LOC", "#0");
        file.printf(f, "", "GREG", "0");
        file.printf(f, "", "GREG", "0");
        file.printf(f, "", "GREG", "0");
        file.printf("\n");

        file.printf(f, "", "LOC", "#10000000");
        for (int i = 0; i < ImcLin.dataChunks().size(); i++) {
            file.printf(f, "", "GREG", "@");

            if (ImcLin.dataChunks().get(i).init == null) { /// global variable
                for (int j = 0; j < ImcLin.dataChunks().get(i).size / 8; j++) {
                    if (j == 0) {
                        file.printf(f, ImcLin.dataChunks().get(i).label.name, "OCTA", "0");
                    } else {
                        file.printf(f, "", "OCTA", "0");
                    }
                }
            } else { /// string constant
                for (int j = 0; j < ImcLin.dataChunks().get(i).init.length(); j++) {
                    System.out.println(ImcLin.dataChunks().get(i).init.charAt(j));
                }
                for (int j = 1; j < ImcLin.dataChunks().get(i).init.length() - 1; j++) {
                    if (j == 1) {
                        file.printf(f, ImcLin.dataChunks().get(i).label.name, "BYTE",
                                Integer.toString((int) ImcLin.dataChunks().get(i).init.charAt(j)));
                    } else {
                        file.printf(f, "", "BYTE", Integer.toString((int) ImcLin.dataChunks().get(i).init.charAt(j)));
                    }
                }

                file.printf(f, "", "BYTE", "0");
            }
        }
        file.printf("\n");
        // initoutreg
        file.printf(f, "", "LOC", "#20000000");
        file.printf(f, "", "GREG", "@");
        file.printf(f, "OutData", "BYTE", "0");
        file.printf("\n");
        // Std
        file.printf(f, "", "LOC", "#30000000");
        // PutChar
        file.printf(f, "", "GREG", "@");
        file.printf(f, "_putChar", "LDO", "$0,$254,8");
        file.printf(f, "", "LDA", "$1,OutData");
        file.printf(f, "", "OR", "$255,$1,0");
        file.printf(f, "", "STB", "$0,$1,0");
        file.printf(f, "", "ADD", "$1,$1,1");
        file.printf(f, "", "SETL", "$0,0");
        file.printf(f, "", "STB", "$0,$1,0");
        file.printf(f, "", "TRAP", "0,Fputs,StdOut");
        file.printf(f, "", "POP", "8" + ",0");
        file.printf("\n");
        // PutString
        /*
         * file.printf(f, "", "GREG", "@"); file.printf(f, "_putString", "LDO",
         * "$0,$254,8"); file.printf(f, "", "OR", "$255,$0,0"); file.printf(f, "",
         * "TRAP", "0,Fputs,StdOut"); file.printf(f, "", "POP", "8" + ",0");
         * file.printf("\n");
         */
        // generatecode
        for (int i = 0; i < AsmGen.codes.size(); i++) {
            Code code = AsmGen.codes.get(i);
            // if (code.frame.label.name.equals("_putString"))
            // continue;
            // Prologue
            file.printf(f, "", "GREG", "@");
            file.printf(f, code.frame.label.name, "SETL", "$0," + Math.abs(0x000000000000FFFFL & code.frame.locsSize));
            file.printf(f, "", "INCML", "$0," + Math.abs(0x00000000FFFF0000L & code.frame.locsSize));
            file.printf(f, "", "INCMH", "$0," + Math.abs(0x0000FFFF00000000L & code.frame.locsSize));
            file.printf(f, "", "INCH", "$0," + Math.abs(0xFFFF000000000000L & code.frame.locsSize));
            file.printf(f, "", "ADD", "$0,$0,8");
            file.printf(f, "", "SUB", "$0,$254,$0"); /// $0 <- SP - locsSize - 8;
            file.printf(f, "", "STO", "$253,$0,0"); /// store FP.

            file.printf(f, "", "SUB", "$0,$0,8");
            file.printf(f, "", "GET", "$1,rJ");
            file.printf(f, "", "STO", "$1,$0,0"); /// store RA.

            file.printf(f, "", "OR", "$253,$254,0"); /// FP <- SP

            file.printf(f, "", "SETL", "$0," + Math.abs(0x000000000000FFFFL & code.frame.size));
            file.printf(f, "", "INCML", "$0," + Math.abs(0x00000000FFFF0000L & code.frame.size));
            file.printf(f, "", "INCMH", "$0," + Math.abs(0x0000FFFF00000000L & code.frame.size));
            file.printf(f, "", "INCH", "$0," + Math.abs(0xFFFF000000000000L & code.frame.size));
            file.printf(f, "", "SUB", "$254,$254,$0"); /// SP <- SP - frame size.

            file.printf(f, "", "JMP", code.entryLabel.name);
            file.printf(f, "", "GREG", "@");
            // Body
            for (int j = 0; j < code.instrs.size(); j++) {
                if (code.instrs.get(j) instanceof AsmLABEL) {
                    file.printf(f, ((AsmLABEL) code.instrs.get(j)).toString(), "OR", "$0,$0,0");
                } else {
                    file.printf(f, "", ((AsmOPER) code.instrs.get(j)).toString(regs).split(" ")[0],
                            ((AsmOPER) code.instrs.get(j)).toString(regs).split(" ")[1]);
                }
            }
            // Epilogue
            file.printf(f, "", "GREG", "@");
            file.printf(f, code.exitLabel.name, "OR", "$0,$" + regs.get(code.frame.RV) + ",0"); /// $0 <- RV

            file.printf(f, "", "OR", "$1,$253,0"); /// $1 <- FP

            file.printf(f, "", "STO", "$0,$1,0"); /// Store RV.

            file.printf(f, "", "OR", "$254,$253,0"); /// SP <- FP

            file.printf(f, "", "SETL", "$0," + Math.abs(0x000000000000FFFFL & code.frame.locsSize));
            file.printf(f, "", "INCML", "$0," + Math.abs(0x00000000FFFF0000L & code.frame.locsSize));
            file.printf(f, "", "INCMH", "$0," + Math.abs(0x0000FFFF00000000L & code.frame.locsSize));
            file.printf(f, "", "INCH", "$0," + Math.abs(0xFFFF000000000000L & code.frame.locsSize));
            file.printf(f, "", "ADD", "$0,$0,8");
            file.printf(f, "", "SUB", "$0,$253,$0");
            file.printf(f, "", "LDO", "$253,$0,0"); /// FP <- oldFP

            file.printf(f, "", "SUB", "$0,$0,8");
            file.printf(f, "", "LDO", "$0,$0,0"); /// $0 <- RA

            file.printf(f, "", "PUT", "rJ,$0"); /// rJ <- $0 (RA)

            file.printf(f, "", "POP", "8" + ",0");
        }
        file.printf(f, "", "GREG", "@");
        file.printf(f, "Main", "SETH", "$254,#3000"); /// SP
        file.printf(f, "", "SETH", "$253,#3000"); /// FP
        file.printf(f, "", "SETH", "$252,#2000"); /// HP
        file.printf(f, "", "PUSHJ", "$" + "8" + ",_main");
        file.printf(f, "", "TRAP", "0,Halt,0");
        file.printf("\n");
        file.close();
    }

}
