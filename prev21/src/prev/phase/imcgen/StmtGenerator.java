package prev.phase.imcgen;

import java.util.*;

import prev.data.ast.tree.stmt.*;
import prev.data.ast.tree.*;
import prev.data.ast.visitor.*;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;

public class StmtGenerator implements AstVisitor<ImcStmt, Stack<MemFrame>> {
	@Override
	public ImcStmt visit(AstAssignStmt assignStmt, Stack<MemFrame> arg) {
        //System.out.println("STMTassignstmt");
		ImcExpr ie1 = assignStmt.dst.accept(new ExprGenerator(), arg);
		ImcExpr ie2 = assignStmt.src.accept(new ExprGenerator(), arg);
		ImcMOVE is = new ImcMOVE(ie1, ie2);
		ImcGen.stmtImc.put(assignStmt, is);
		return is;
	}

	@Override
	public ImcStmt visit(AstExprStmt exprStmt, Stack<MemFrame> arg) {
        //System.out.println("STMTexprstmt");
		ImcESTMT ie = new ImcESTMT(exprStmt.expr.accept(new ExprGenerator(), arg));
		ImcGen.stmtImc.put(exprStmt, ie);
		return ie;
	}

	@Override
	public ImcStmt visit(AstIfStmt ifStmt, Stack<MemFrame> arg) {
        //System.out.println("STMTifstmt");
		Vector<ImcStmt> vis = new Vector<ImcStmt>();
		ImcExpr ie = ifStmt.cond.accept(new ExprGenerator(), arg);
		ImcStmt ist = ifStmt.thenStmt.accept(this, arg);
		if (ifStmt.elseStmt != null) {
			ImcStmt ie1 = ifStmt.elseStmt.accept(this, arg);
			MemLabel l1 = new MemLabel();
			MemLabel l2 = new MemLabel();
			MemLabel l3 = new MemLabel();
			vis.add(new ImcCJUMP(ie, l1, l2));
			vis.add(new ImcLABEL(l1));
			vis.add(ist);
			vis.add(new ImcJUMP(l3));
			vis.add(new ImcLABEL(l2));
			vis.add(ie1);
			vis.add(new ImcLABEL(l3));

		}
		else {
			MemLabel zac = new MemLabel();
			MemLabel kon = new MemLabel();

			vis.add(new ImcCJUMP(ie, zac, kon));
			vis.add(new ImcLABEL(zac));
			vis.add(ist);
			vis.add(new ImcLABEL(kon));
		}
		ImcSTMTS is = new ImcSTMTS(vis);
		ImcGen.stmtImc.put(ifStmt, is);
		return is;
	}

	@Override
	public ImcStmt visit(AstWhileStmt whileStmt, Stack<MemFrame> arg) {
        //System.out.println("STMTwhilestmt");
		Vector<ImcStmt> vis = new Vector<ImcStmt>();
		ImcExpr ie = whileStmt.cond.accept(new ExprGenerator(), arg);
		ImcStmt is = whileStmt.bodyStmt.accept(this, arg);
		MemLabel l1 = new MemLabel();
		MemLabel l2 = new MemLabel();
		MemLabel l3 = new MemLabel();
		vis.add(new ImcLABEL(l1));
		vis.add(new ImcCJUMP(ie, l2, l3));
		vis.add(new ImcLABEL(l2));
		vis.add(new ImcJUMP(l1));
		vis.add(new ImcLABEL(l3));
		ImcSTMTS iss = new ImcSTMTS(vis);
		ImcGen.stmtImc.put(whileStmt, iss);
		return iss;
	}
}
