package prev.phase.memory;

import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.visitor.*;
import prev.data.typ.*;
import prev.data.mem.*;
import prev.phase.seman.*;
import java.util.*;

/**
 * Computing memory layout: frames and accesses.
 */
public class MemEvaluator extends AstFullVisitor<Object, Object> {
	public int depth = 0;
	public int size = 0;
	public long locsSize = 0;
	public long par = 0;
	public long argsSize = new SemPtr(new SemVoid()).size();
	public static Stack<Integer> stek = new Stack<Integer>();
    // GENERAL PURPOSE

	@Override
	public Object visit(AstTrees<? extends AstTree> trees, Object arg) {
		for (AstTree t : trees) {
			t.accept(this, arg);
		}
		return null;
	}

	public static int lvl = 0;
	public static Stack<Integer> st = new Stack<Integer>();
	public static Stack<Integer> ls = new Stack<Integer>();
	// DECLARATIONS

	@Override
	public Object visit(AstCompDecl compDecl, Object arg) {
		if (compDecl.type != null)
			compDecl.type.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
		par=0;
		depth++;
		st.push(0);
		funDecl.pars.accept(this, arg);
		funDecl.type.accept(this, arg);
		ls.push(0);
		if (funDecl.expr != null)
		funDecl.expr.accept(this, arg);
		
		MemLabel ml;
		if (depth == 1) ml = new MemLabel(funDecl.name);
		else ml = new MemLabel();
		MemFrame mf = new MemFrame(ml, depth, ls.peek(), st.peek());
		Memory.frames.put(funDecl, mf);
		ls.pop();
		st.pop();
		depth--;
		return null;
	}

	@Override
	public Object visit(AstParDecl parDecl, Object arg) {
		//System.out.println("Vleze");
		SemType st = parDecl.type.accept(new TypeResolver(), arg);
		//System.out.println(st);
		par += st.size();
		MemRelAccess mr = new MemRelAccess(st.size(), par, depth);
		Memory.accesses.put(parDecl, mr);
		return null;
	}

	@Override
	public Object visit(AstTypeDecl typeDecl, Object arg) {
		if (typeDecl.type != null)
			typeDecl.type.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Object arg) {		
		SemType st = varDecl.type.accept(new TypeResolver(), arg);
		if (depth == 0) {
			MemLabel ml = new MemLabel(varDecl.name); 
			MemAbsAccess maa = new MemAbsAccess(st.size(), ml);
			Memory.accesses.put(varDecl, maa);
		} else {
		int br = ls.peek();
		ls.pop();
		int br1 = (int)st.size();
		ls.push(br + br1);
		MemRelAccess mra = new MemRelAccess(st.size(), -ls.peek(), depth);
		Memory.accesses.put(varDecl, mra);
		}
		return null;
	}

	// EXPRESSIONS

	@Override
	public Object visit(AstArrExpr arrExpr, Object arg) {
		if (arrExpr.arr != null)
			arrExpr.arr.accept(this, arg);
		if (arrExpr.idx != null)
			arrExpr.idx.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstAtomExpr atomExpr, Object arg) {
		switch (atomExpr.type) {
			case STRING:
				MemAbsAccess maa = new MemAbsAccess(8, new MemLabel());
				Memory.strings.put(atomExpr, maa);
				break;
		
			default:
				break;
		}
		return null;
	}

	@Override
	public Object visit(AstBinExpr binExpr, Object arg) {
		if (binExpr.fstExpr != null)
			binExpr.fstExpr.accept(this, arg);
		if (binExpr.sndExpr != null)
			binExpr.sndExpr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstCallExpr callExpr, Object arg) {
		int stevec = 0;
		if (callExpr.args != null) {
			for (AstExpr ae : callExpr.args) {
				stevec += (SemAn.ofType.get(ae)).size();
			}
			callExpr.args.accept(this, arg);
		}
		int br1 = st.peek();
		st.pop();
		int aa = (Integer)stevec;
		int bb = (int)argsSize;
		br1 = Math.max(br1, aa + bb);
		st.push(br1);
		return null;
	}

	@Override
	public Object visit(AstCastExpr castExpr, Object arg) {
		if (castExpr.expr != null)
			castExpr.expr.accept(this, arg);
		if (castExpr.type != null)
			castExpr.type.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Object arg) {
		return null;
	}

	@Override
	public Object visit(AstPfxExpr pfxExpr, Object arg) {
		if (pfxExpr.expr != null)
			pfxExpr.expr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstRecExpr recExpr, Object arg) {
		if (recExpr.rec != null)
			recExpr.rec.accept(this, arg);
		if (recExpr.comp != null)
			recExpr.comp.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstSfxExpr sfxExpr, Object arg) {
		if (sfxExpr.expr != null)
			sfxExpr.expr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstStmtExpr stmtExpr, Object arg) {
		if (stmtExpr.stmts != null)
			stmtExpr.stmts.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstWhereExpr whereExpr, Object arg) {
		if (whereExpr.expr != null)
			whereExpr.expr.accept(this, arg);
		if (whereExpr.decls != null)
			whereExpr.decls.accept(this, arg);
		
		
		return null;
	}

	// STATEMENTS

	@Override
	public Object visit(AstAssignStmt assignStmt, Object arg) {
		if (assignStmt.dst != null)
			assignStmt.dst.accept(this, arg);
		if (assignStmt.src != null)
			assignStmt.src.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstExprStmt exprStmt, Object arg) {
		if (exprStmt.expr != null)
			exprStmt.expr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstIfStmt ifStmt, Object arg) {
		if (ifStmt.cond != null)
			ifStmt.cond.accept(this, arg);
		if (ifStmt.thenStmt != null)
			ifStmt.thenStmt.accept(this, arg);
		if (ifStmt.elseStmt != null)
			ifStmt.elseStmt.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstWhileStmt whileStmt, Object arg) {
		if (whileStmt.cond != null)
			whileStmt.cond.accept(this, arg);
		if (whileStmt.bodyStmt != null)
			whileStmt.bodyStmt.accept(this, arg);
		return null;
	}

	// TYPES

	@Override
	public Object visit(AstArrType arrType, Object arg) {
		if (arrType.elemType != null)
			arrType.elemType.accept(this, arg);
		if (arrType.numElems != null)
			arrType.numElems.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstAtomType atomType, Object arg) {
		return null;
	}

	@Override
	public Object visit(AstNameType nameType, Object arg) {
		return null;
	}

	@Override
	public Object visit(AstPtrType ptrType, Object arg) {
		if (ptrType.baseType != null)
			ptrType.baseType.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstRecType recType, Object arg) {
		if (recType.comps != null)
			recType.comps.accept(this, arg);
		return null;
	}
}
