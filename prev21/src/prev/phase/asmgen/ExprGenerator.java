package prev.phase.asmgen;

import java.text.BreakIterator;
import java.util.*;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.visitor.*;
import prev.data.asm.*;

/**
 * Machine code generator for expressions.
 */
public class ExprGenerator implements ImcVisitor<MemTemp, Vector<AsmInstr>> {

	Stack<Vector<AsmInstr> > sis;
	@Override
    public MemTemp visit(ImcBINOP binOp, Vector<AsmInstr> visArg) {
		MemTemp s0 = binOp.fstExpr.accept(this, visArg);
		Vector<AsmInstr> bvis = sis.pop();
		MemTemp s1 = binOp.sndExpr.accept(this, visArg);
		Vector<AsmInstr> svis = sis.pop();
		Vector<MemTemp> u = new Vector<MemTemp>();
		Vector<MemTemp> d = new Vector<MemTemp>();
		MemTemp d0 = new MemTemp();
		u.add(s0);
		u.add(s1);
		d.add(d0);
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		ins.addAll(bvis);
		ins.addAll(svis);
		switch (binOp.oper) {
			case OR:
				AsmOPER ao1 = new AsmOPER("OR `d0,`s0,`s1", u, d, null);
				ins.add(ao1);
				break;
			case AND:
				AsmOPER ao2 = new AsmOPER("AND `d0,`s0,`s1", u, d, null);
				ins.add(ao2);
				break;
			case ADD:
				AsmOPER ao3 = new AsmOPER("ADD `d0,`s0,`s1", u, d, null);
				ins.add(ao3);
				break;
			case SUB:
				AsmOPER ao4 = new AsmOPER("SUB `d0,`s0,`s1", u, d, null);
				ins.add(ao4);
				break;
			case MUL:
				AsmOPER ao5 = new AsmOPER("MUL `d0,`s0,`s1", u, d, null);
				ins.add(ao5);
				break;
			case DIV:
				AsmOPER ao6 = new AsmOPER("DIV `d0,`s0,`s1", u, d, null);
				ins.add(ao6);
				break;

			case MOD:
				AsmOPER ao71 = new AsmOPER("DIV `d0,`s0,`s1", u, d, null);
				ins.add(ao71);
				AsmOPER ao72 = new AsmOPER("GET `d0,rR", null, d, null);
				ins.add(ao72);
				break;
			case LTH:
				AsmOPER ao81 = new AsmOPER("CMP `d0,`s0,`s1", u, d, null);
				ins.add(ao81);
				AsmOPER ao82 = new AsmOPER("ZSN `d0,`s0,1", d, d, null);
				ins.add(ao82);
				break;
			case GTH:
				AsmOPER ao91 = new AsmOPER("CMP `d0,`s0,`s1", u, d, null);
				ins.add(ao91);
				AsmOPER ao92 = new AsmOPER("ZSP `d0,`s0,1", d, d, null);
				ins.add(ao92);
				break;
			case LEQ:
				AsmOPER ao101 = new AsmOPER("CMP `d0,`s0,`s1", u, d, null);
				ins.add(ao101);
				AsmOPER ao102 = new AsmOPER("ZSNP `d0,`s0,1", d, d, null);
				ins.add(ao102);
				break;
			case GEQ:
				AsmOPER ao111 = new AsmOPER("CMP `d0,`s0,`s1", u, d, null);
				ins.add(ao111);
				AsmOPER ao112 = new AsmOPER("ZSNN `d0,`s0,1", d, d, null);
				ins.add(ao112);
				break;
			case NEQ:
				AsmOPER ao121 = new AsmOPER("CMP `d0,`s0,`s1", u, d, null);
				ins.add(ao121);
				AsmOPER ao122 = new AsmOPER("ZSNZ `d0,`s0,1", d, d, null);
				ins.add(ao122);
				break;
			case EQU:
				AsmOPER ao131 = new AsmOPER("CMP `d0,`s0,`s1", u, d, null);
				ins.add(ao131);
				AsmOPER ao132 = new AsmOPER("ZSZ `d0,`s0,1", d, d, null);
				ins.add(ao132);
				break;
		
			default:
				break;
		}
		sis.push(ins);
		return d0;
	}

	@Override
	public MemTemp visit(ImcCALL call, Vector<AsmInstr> visArg) {
		Vector<MemLabel> js = new Vector<MemLabel>();
		js.add(call.label);
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		long off = 0;
		for (ImcExpr ie : call.args) {
			MemTemp s0 = ie.accept(this, visArg);
			Vector<AsmInstr> vis = sis.pop();
			ins.addAll(vis);
			Vector<MemTemp> u = new Vector<MemTemp>();
			u.add(s0);
			AsmOPER ao = new AsmOPER("STO `s0,$253," + off, u, null, null);
			ins.add(ao);
			off += 8;
		} 
		AsmOPER ao = new AsmOPER("PUSHJ $8," + call.label.name, null, null, js);
		ins.add(ao);
		MemTemp d0 = new MemTemp();
		Vector<MemTemp> d = new Vector<MemTemp>();
		d.add(d0);
		AsmOPER ao1 = new AsmOPER("LDO `d0,$253,0", null, d, null);
		ins.add(ao1);
		sis.push(ins);
		return d0;
	}

	@Override
	public MemTemp visit(ImcCONST constant, Vector<AsmInstr> visArg) {
		MemTemp d0 = new MemTemp();
		Vector<MemTemp> d = new Vector<MemTemp>();
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		AsmOPER ao1 = new AsmOPER("SETH `d0," + (0xFFFF000000000000L & Math.abs(constant.value)), null, d, null);
		ins.add(ao1);
		AsmOPER ao2 = new AsmOPER("INCMH `d0," + (0x0000FFFF00000000L & Math.abs(constant.value)), d, d, null);
		ins.add(ao2);
		AsmOPER ao3 = new AsmOPER("INCML `d0," + (0x00000000FFFF0000L & Math.abs(constant.value)), d, d, null);
		ins.add(ao3);
		AsmOPER ao4 = new AsmOPER("INCL `d0," + (0x000000000000FFFFL & Math.abs(constant.value)), d, d, null);
		ins.add(ao4);
		if (constant.value < 0) {
			AsmOPER ao5 = new AsmOPER("NEG `d0,0,`s0", d, d, null);
			ins.add(ao5);
		}
		sis.push(ins);
		return d0;
	}

	@Override
	public MemTemp visit(ImcMEM mem, Vector<AsmInstr> visArg) {
		MemTemp s0 = mem.addr.accept(this, visArg);
		Vector<AsmInstr> vis = sis.pop();
		MemTemp d0 = new MemTemp();
		Vector<MemTemp> u = new Vector<MemTemp>();
		Vector<MemTemp> d = new Vector<MemTemp>();
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		u.add(s0);
		d.add(d0);
		ins.addAll(vis);
		AsmOPER ao = new AsmOPER("LDO `d0,`s0,0", u, d, null);
		ins.add(ao);
		sis.add(ins);
		return d0;
	}

	@Override
	public MemTemp visit(ImcNAME name, Vector<AsmInstr> visArg) {
		Vector<MemTemp> d = new Vector<MemTemp>();
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		MemTemp d0 = new MemTemp();
		d.add(d0);
		AsmOPER ao = new AsmOPER("LDA `d0," + name.label.name, null, d, null);
		ins.add(ao);
		sis.push(ins);
		return d0;
	}

	@Override
	public MemTemp visit(ImcTEMP temp, Vector<AsmInstr> visArg) {
		System.out.println("TEMP");
		sis.push(new Vector<AsmInstr>());
		return temp.temp;
	}

	@Override
	public MemTemp visit(ImcUNOP unOp, Vector<AsmInstr> visArg) {
		MemTemp s0 = unOp.subExpr.accept(this, visArg);
		Vector<AsmInstr> svis = sis.pop();
		Vector<MemTemp> u = new Vector<MemTemp>();
		u.add(s0);
		MemTemp d0 = new MemTemp();
		Vector<MemTemp> d = new Vector<MemTemp>();
		d.add(d0);
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		ins.addAll(svis);
		switch (unOp.oper) {
			case NOT: 
				AsmOPER ao1 = new AsmOPER("NEG `d0,1,`s0", u, d, null);
				ins.add(ao1);
			break;
			case NEG: 
				AsmOPER ao2 = new AsmOPER("NEG `d0,0,`s0", u, d, null);
				ins.add(ao2);
			break;
			default:
			break;
		}
		sis.push(ins);
		return d0;
	}
}
