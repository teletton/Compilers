package prev.phase.livean;

import prev.data.mem.*;
import prev.data.asm.*;
import prev.phase.*;
import prev.phase.asmgen.*;
import java.util.*;

/**
 * Liveness analysis.
 */
public class LiveAn extends Phase {

	public LiveAn() {
		super("livean");
	}

	public void analysis() {
		for (Code code : AsmGen.codes) {
			boolean cc = true;
			while (cc) {
				int brojac = 0;
				for (int i = 0; i < code.instrs.size(); i++) {
					AsmInstr ins = code.instrs.get(i);
					int in1 = ins.in().size();
					int out1 = ins.out().size();
					HashSet<MemTemp> out2 = ins.out();
					ins.addInTemps(new HashSet<MemTemp>(ins.uses()));
					for (MemTemp d : ins.defs()) {
						out2.remove(d);
					}
					ins.addInTemps(out2);
					if (i + 1 < code.instrs.size()) {
						ins.addOutTemp(code.instrs.get(i + 1).in());
					}
					if ((in1 !=  ins.in().size()) || (out1 != ins.out().size())) {
						brojac++;
					} 
					if (brojac > 0) {
						cc = true;
					} else {
						cc = false;
					}
					System.out.println(i + "  " + in1 + " " + ins.in().size() + "  " + out1 + " " + ins.out().size());
				}
			}
		}
	}
	
	public void log() {
		if (logger == null)
			return;
		for (Code code : AsmGen.codes) {
			logger.begElement("code");
			logger.addAttribute("entrylabel", code.entryLabel.name);
			logger.addAttribute("exitlabel", code.exitLabel.name);
			logger.addAttribute("tempsize", Long.toString(code.tempSize));
			code.frame.log(logger);
			logger.begElement("instructions");
			for (AsmInstr instr : code.instrs) {
				logger.begElement("instruction");
				logger.addAttribute("code", instr.toString());
				logger.begElement("temps");
				logger.addAttribute("name", "use");
				for (MemTemp temp : instr.uses()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "def");
				for (MemTemp temp : instr.defs()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "in");
				for (MemTemp temp : instr.in()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "out");
				for (MemTemp temp : instr.out()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.endElement();
			}
			logger.endElement();
			logger.endElement();
		}
	}

}
