package prev.phase.asmgen;

import java.util.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.imc.visitor.*;
import prev.data.mem.*;
import prev.data.asm.*;
import prev.common.report.*;

/**
 * Machine code generator for statements.
 */
public class StmtGenerator implements ImcVisitor<Vector<AsmInstr>, Object> {

	public Vector<AsmInstr> visit(ImcCJUMP cjump, Object visArg) {
		//Vector<AsmInstr> vis = new Vector<AsmInstr>();
		ExprGenerator eg = new ExprGenerator();
		MemTemp s0 = cjump.cond.accept(eg, null);
		Vector<AsmInstr> cvis = eg.sis.pop();
		Vector<MemLabel> js = new Vector<MemLabel>();
		js.add(cjump.negLabel);
		js.add(cjump.posLabel);
		Vector<MemTemp> u = new Vector<MemTemp>();
		u.add(s0);
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		ins.addAll(cvis);
		AsmOPER ao = new AsmOPER("BNZ `s0," + cjump.posLabel.name, u, null, js);
		ins.add(ao);
		return ins;
	}

	public Vector<AsmInstr> visit(ImcJUMP jump, Object visArg) {
		Vector<MemLabel> js = new Vector<MemLabel>();
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		js.add(jump.label);
		AsmOPER ao = new AsmOPER("JMP " + jump.label.name, null, null, js);
		ins.add(ao);
		return ins;
	}

	public Vector<AsmInstr> visit(ImcLABEL label, Object visArg) {
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		AsmLABEL al = new AsmLABEL(label.label);
		ins.add(al);
		return ins;
	}

	public Vector<AsmInstr> visit(ImcMOVE move, Object visArg) {
		return null;
	}
}
