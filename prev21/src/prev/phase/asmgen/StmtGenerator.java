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
	@Override
	public Vector<AsmInstr> visit(ImcCJUMP cjump, Object visArg) {
		Vector<AsmInstr> vis = new Vector<AsmInstr>();
		ExprGenerator eg = new ExprGenerator();
		eg.numofregss = (int) visArg;
		MemTemp s0 = cjump.cond.accept(eg, vis);
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

	@Override
	public Vector<AsmInstr> visit(ImcJUMP jump, Object visArg) {
		Vector<MemLabel> js = new Vector<MemLabel>();
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		js.add(jump.label);
		AsmOPER ao = new AsmOPER("JMP " + jump.label.name, null, null, js);
		ins.add(ao);
		return ins;
	}

	@Override
	public Vector<AsmInstr> visit(ImcLABEL label, Object visArg) {
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		AsmLABEL al = new AsmLABEL(label.label);
		ins.add(al);
		return ins;
	}

	@Override
	public Vector<AsmInstr> visit(ImcMOVE move, Object visArg) {
		Vector<AsmInstr> inn = new Vector<AsmInstr>();
		ExprGenerator eg = new ExprGenerator();
		eg.numofregss = (int) visArg;
		if (move.dst instanceof ImcMEM) {
			inn.add(new AsmOPER("", null, null, null));
		}
		MemTemp d0 = move.dst.accept(eg, inn);
		inn.clear();
		inn = new Vector<AsmInstr>();
		Vector<AsmInstr> vis1 = eg.sis.pop();
		ExprGenerator eg1 = new ExprGenerator();
		eg1.numofregss = (int) visArg;
		MemTemp s0 = move.src.accept(eg1, inn);
		Vector<AsmInstr> vis2 = eg1.sis.pop();
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		ins.addAll(vis1);
		ins.addAll(vis2);
		if (move.dst instanceof ImcMEM) {
			Vector<MemTemp> u = new Vector<MemTemp>();
			u.add(s0);
			u.add(d0);
			AsmOPER ao = new AsmOPER("STO `s0,`s1,0", u, null, null);
			ins.add(ao);
		} else {
			Vector<MemTemp> u = new Vector<MemTemp>();
			u.add(s0);
			Vector<MemTemp> d = new Vector<MemTemp>();
			d.add(d0);
			AsmOPER ao = new AsmOPER("OR `d0,`s0,0", u, d, null);
			ins.add(ao);
		}
		return ins;
	}
}
