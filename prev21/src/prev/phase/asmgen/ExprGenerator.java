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
    public MemTemp visit(ImcBINOP binOp, Vector<AsmInstr> visArg) {
		MemTemp s0 = binOp.fstExpr.accept(this, visArg);
		MemTemp s1 = binOp.sndExpr.accept(this, visArg);
		Vector<MemTemp> u = new Vector<MemTemp>();
		Vector<MemTemp> d = new Vector<MemTemp>();
		MemTemp d0 = new MemTemp();
		u.add(s0);
		u.add(s1);
		d.add(d0);
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		switch (binOp.oper) {
			case OR:
				AsmOPER ao1 = new AsmOPER("OR `d0, `s0, `s1", u, d, null);
				ins.add(ao1);
				break;
			case AND:
				AsmOPER ao2 = new AsmOPER("AND `d0, `s0, `s1", u, d, null);
				ins.add(ao2);
				break;
			case ADD:
				AsmOPER ao3 = new AsmOPER("ADD `d0, `s0, `s1", u, d, null);
				ins.add(ao3);
				break;
			case SUB:
				AsmOPER ao4 = new AsmOPER("SUB `d0, `s0, `s1", u, d, null);
				ins.add(ao4);
				break;
			case MUL:
				AsmOPER ao5 = new AsmOPER("MUL `d0, `s0, `s1", u, d, null);
				ins.add(ao5);
				break;
			case DIV:
				AsmOPER ao6 = new AsmOPER("DIV `d0, `s0, `s1", u, d, null);
				ins.add(ao6);
				break;

			case MOD:
				AsmOPER ao71 = new AsmOPER("DIV `d0, `s0, `s1", u, d, null);
				ins.add(ao71);
				AsmOPER ao72 = new AsmOPER("GET `d0, rR", null, d, null);
				ins.add(ao72);
				break;
			case LTH:
				AsmOPER ao81 = new AsmOPER("CMP `d0, `s0, `s1", u, d, null);
				ins.add(ao81);
				AsmOPER ao82 = new AsmOPER("ZSN `d0, `s0, 1", d, d, null);
				ins.add(ao82);
				break;
			case GTH:
				AsmOPER ao91 = new AsmOPER("CMP `d0, `s0, `s1", u, d, null);
				ins.add(ao91);
				AsmOPER ao92 = new AsmOPER("ZSP `d0, `s0, 1", d, d, null);
				ins.add(ao92);
				break;
			case LEQ:
				AsmOPER ao101 = new AsmOPER("CMP `d0, `s0, `s1", u, d, null);
				ins.add(ao101);
				AsmOPER ao102 = new AsmOPER("ZSNP `d0, `s0, 1", d, d, null);
				ins.add(ao102);
				break;
			case GEQ:
				AsmOPER ao111 = new AsmOPER("CMP `d0, `s0, `s1", u, d, null);
				ins.add(ao111);
				AsmOPER ao112 = new AsmOPER("ZSNN `d0, `s0, 1", d, d, null);
				ins.add(ao112);
				break;
			case NEQ:
				AsmOPER ao121 = new AsmOPER("CMP `d0, `s0, `s1", u, d, null);
				ins.add(ao121);
				AsmOPER ao122 = new AsmOPER("ZSNZ `d0, `s0, 1", d, d, null);
				ins.add(ao122);
				break;
			case EQU:
				AsmOPER ao131 = new AsmOPER("CMP `d0, `s0, `s1", u, d, null);
				ins.add(ao131);
				AsmOPER ao132 = new AsmOPER("ZSZ `d0, `s0, 1", d, d, null);
				ins.add(ao132);
				break;
		
			default:
				break;
		}
		return d0;
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
		AsmOPER ao = new AsmOPER("LDA `d0," + name.label.name, null, d, null);
		ins.add(ao);
		return d0;
	}

	public MemTemp visit(ImcTEMP temp, Vector<AsmInstr> visArg) {
		return temp.temp;
	}

	public MemTemp visit(ImcUNOP unOp, Vector<AsmInstr> visArg) {
		MemTemp s0 = unOp.subExpr.accept(this, visArg);
		Vector<MemTemp> u = new Vector<AsmInstr>();
		u.add(s0);
		MemTemp d0 = new MemTemp();
		Vector<MemTemp> d = new Vector<MemTemp>():
		d.add(d0);
		Vector<AsmInstr> ins = new Vector<AsmInstr>();
		switch (unOp.oper) {
			case NOT: 
				AsmOPER ao1 = new AsmOPER("NEG `d0, 1, `s0", u, d, null);
				ins.add(ao1);
			break;
			case NEG: 
				AsmOPER ao2 = new AsmOPER("NEG `d0, 0, `s0", u, d, null);
				ins.add(ao2);
			break;
			default:
			break;
		}
		return d0;
	}
}
