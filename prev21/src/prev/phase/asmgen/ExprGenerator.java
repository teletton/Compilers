package prev.phase.asmgen;

import java.util.*;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.visitor.*;
import prev.data.asm.*;

/**
 * Machine code generator for expressions.
 */
public class ExprGenerator implements ImcVisitor<MemTemp, Vector<AsmInstr>> {
    public MemTemp visit(ImcBINOP binOp, Vector<AsmInstr> visArg) {
		return null;
	}

	public MemTemp visit(ImcCALL call, Vector<AsmInstr> visArg) {
		return null;
	}

	public MemTemp visit(ImcCONST constant, Vector<AsmInstr> visArg) {
		return null;
	}

	public MemTemp visit(ImcMEM mem, Vector<AsmInstr> visArg) {
		return null;
	}

	public MemTemp visit(ImcNAME name, Vector<AsmInstr> visArg) {
		Vector<MemTemp> d = new Vector<MemTemp>();
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		MemTemp d0 = new MemTemp();
		d.add(d0);
		AsmOPER ao = new AsmOPER("LDA `d0," + name.label.name, null, defs, null);
		ins.add(ao);
		return d0;
	}

	public MemTemp visit(ImcTEMP temp, Vector<AsmInstr> visArg) {
		return temp.temp;
	}

	public MemTemp visit(ImcUNOP unOp, Vector<AsmInstr> visArg) {
		return null;
	}
}
