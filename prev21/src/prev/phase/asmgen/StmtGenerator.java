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

	public Vector<AsmInstr> visit(ImcCALL call, Object visArg) {
		return null;
	}

	public Vector<AsmInstr> visit(ImcCJUMP cjump, Object visArg) {
		return null;
	}

	public Vector<AsmInstr> visit(ImcJUMP jump, Object visArg) {
		return null;
	}

	public Vector<AsmInstr> visit(ImcLABEL label, Object visArg) {
		return null;
	}

	public Vector<AsmInstr> visit(ImcMOVE move, Object visArg) {
		return null;
	}

	public Vector<AsmInstr> visit(ImcSTMTS stmts, Object visArg) {
		return null;
	}
}
