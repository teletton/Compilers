package prev.phase.seman;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.*;

/**
 * Name resolver.
 * 
 * Name resolver connects each node of a abstract syntax tree where a name is
 * used with the node where it is declared. The only exceptions are a record
 * field names which are connected with its declarations by type resolver. The
 * results of the name resolver are stored in
 * {@link prev.phase.seman.SemAn#declaredAt}.
 */


public class NameResolver extends AstFullVisitor<Object, Object> {

	private final SymbTable symbTable = new SymbTable();
	@Override
	public Object visit(AstTrees<? extends AstTree> trees, Object arg) {
		if ((int)arg == 0) symbTable.newScope();
		
			for (AstTree t : trees) {
				//System.out.println("id = "+t.id()+" class = "+t.getClass()+" arg = "+arg);
				if ((t instanceof AstFunDecl) || (t instanceof AstVarDecl) || (t instanceof AstTypeDecl)) {
					t.accept(this, -1);
				}
					
			}
		
		for (AstTree t : trees) {
			//System.out.println("id = "+t.id()+" class = "+t.getClass()+" arg = "+arg);
			t.accept(this, (int)arg+1);
				
		}
		if ((int)arg == 0) symbTable.oldScope();
		return null;
	}

	// DECLARATIONS

	@Override
	public Object visit(AstCompDecl compDecl, Object arg) {
		//System.out.println("compdeclid = "+compDecl.id()+" arg = "+arg+" name = "+compDecl.name);
		if ((int)arg == -1) return null;
		if (compDecl.type != null) {

			compDecl.type.accept(this, arg);
			try {
				symbTable.ins(compDecl.name, compDecl);
			} catch (Exception e) {
				throw new Report.Error("SEMANTIC ERROR at: "+compDecl.location()+ " Function " + compDecl.name + " is already defined");
			}

		}
		return null;
	}

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
		//System.out.println("fundeclid = "+funDecl.id()+" arg = "+arg);
		if ((int)arg == -1) {
		try {
			symbTable.ins(funDecl.name, funDecl);
		} catch (Exception e) {
			throw new Report.Error("SEMANTIC ERROR at: "+funDecl.location()+ " Function " + funDecl.name + " is already defined");
		}
		return null;
	}
		symbTable.newScope();
		if (funDecl.pars != null)
			funDecl.pars.accept(this, arg);
		if (funDecl.type != null)
			funDecl.type.accept(this, arg);

		if (funDecl.expr != null) {

			funDecl.expr.accept(this, arg);

		}
		symbTable.oldScope();
		return null;
	}

	@Override
	public Object visit(AstParDecl parDecl, Object arg) {
		//System.out.println("pardeclid = "+parDecl.id()+" arg = "+arg);
		if ((int)arg==-1) return null;
		try {
			symbTable.ins(parDecl.name, parDecl);
		} catch (Exception e) {
			throw new Report.Error("SEMANTIC ERROR at: "+parDecl.location()+ " Parameter "+parDecl.name + " is already defined");
		}
		if (parDecl.type != null)
			parDecl.type.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstTypeDecl typeDecl, Object arg) {
		//System.out.println("typedeclid = "+typeDecl.id()+" arg = "+arg);
		if ((int)arg == -1) {
			try {
				symbTable.ins(typeDecl.name, typeDecl);
			} catch (Exception e) {
				throw new Report.Error("SEMANTIC ERROR at: "+typeDecl.location()+ "Type " + typeDecl.name + " is already defined");
			}
			return null;
		}
		if (typeDecl.type != null)
			typeDecl.type.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Object arg) {
		//System.out.println("vardeclid = "+varDecl.id()+" arg = "+arg);
		if ((int)arg == -1) {
			try {
				symbTable.ins(varDecl.name, varDecl);
			} catch (Exception e) {
				throw new Report.Error("SEMANTIC ERROR at: "+varDecl.location()+ "Variable " + varDecl.name + " is already defined");
			}
			return null;
		}
		if (varDecl.type != null)
			varDecl.type.accept(this, arg);
		return null;
	}

	// EXPRESSIONS

	@Override
	public Object visit(AstArrExpr arrExpr, Object arg) {
		//System.out.println("arexprid = "+arrExpr.id()+" arg = "+arg);
		if (arrExpr.arr != null)
			arrExpr.arr.accept(this, arg);
		if (arrExpr.idx != null)
			arrExpr.idx.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstAtomExpr atomExpr, Object arg) {
		//System.out.println("atomexprid = "+atomExpr.id()+" arg = "+arg);
		return null;
	}

	@Override
	public Object visit(AstBinExpr binExpr, Object arg) {
		//System.out.println("binexprid = "+binExpr.id()+" arg = "+arg);
		if (binExpr.fstExpr != null)
			binExpr.fstExpr.accept(this, arg);
		if (binExpr.sndExpr != null)
			binExpr.sndExpr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstCallExpr callExpr, Object arg) {
		//System.out.println("callexprid = "+callExpr.id()+" arg = "+arg);

		try {
			symbTable.fnd(callExpr.name);
			SemAn.declaredAt.put(callExpr, symbTable.fnd(callExpr.name));
		} catch (Exception e) {
			throw new Report.Error("SEMANTIC ERROR at: "+callExpr.location()+ "Function " + callExpr.name + " has not been defined");
		}

		callExpr.args.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstCastExpr castExpr, Object arg) {
		//System.out.println("castexprid = "+castExpr.id()+" arg = "+arg);
		if (castExpr.expr != null)
			castExpr.expr.accept(this, arg);
		if (castExpr.type != null)
			castExpr.type.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Object arg) {
		//System.out.println("nameexprid = "+nameExpr.id()+" arg = "+arg+" name = "+nameExpr.name);

		try {
			symbTable.fnd(nameExpr.name);
			SemAn.declaredAt.put(nameExpr, symbTable.fnd(nameExpr.name));
		} catch (Exception e) {
			throw new Report.Error("SEMANTIC ERROR at: "+nameExpr.id() + " " + nameExpr.location()+ " "+nameExpr.name + " has not been defined");
		}

		return null;
	}

	@Override
	public Object visit(AstPfxExpr pfxExpr, Object arg) {
		//System.out.println("pfxexprid = "+pfxExpr.id()+" arg = "+arg);
		if (pfxExpr.expr != null)
			pfxExpr.expr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstRecExpr recExpr, Object arg) {
		//System.out.println("recexprid = "+recExpr.id()+" arg = "+arg);
		if (recExpr.rec != null)
			recExpr.rec.accept(this, arg);
		if (recExpr.comp != null)
			recExpr.comp.accept(this, arg);
			try {
				AstDecl ad = symbTable.fnd(recExpr.comp.name);
				SemAn.declaredAt.put(recExpr.comp, ad);
			} catch(Exception e) {
				System.out.println("GRESKA GOLEMA");
			}
		return null;
	}

	@Override
	public Object visit(AstSfxExpr sfxExpr, Object arg) {
		//System.out.println("sfxexprid = "+sfxExpr.id()+" arg = "+arg);
		if (sfxExpr.expr != null)
			sfxExpr.expr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstStmtExpr stmtExpr, Object arg) {
		//System.out.println("stmtexprid = "+stmtExpr.id()+" arg = "+arg);
		if (stmtExpr.stmts != null)
			stmtExpr.stmts.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstWhereExpr whereExpr, Object arg) {
		//System.out.println("whereexprid = "+whereExpr.id()+" arg = "+arg);
		symbTable.newScope();
		if (whereExpr.decls != null) {
			whereExpr.decls.accept(this, arg);
		}
		if (whereExpr.expr != null)
			whereExpr.expr.accept(this, arg);

		symbTable.oldScope();
		return null;
	}

	// STATEMENTS

	@Override
	public Object visit(AstAssignStmt assignStmt, Object arg) {
		//System.out.println("assignstmtid = "+assignStmt.id()+" arg = "+arg);
		if (assignStmt.dst != null)
			assignStmt.dst.accept(this, arg);
		if (assignStmt.src != null)
			assignStmt.src.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstExprStmt exprStmt, Object arg) {
		//System.out.println("exprstmtid = "+exprStmt.id()+" arg = "+arg);
		if (exprStmt.expr != null)
			exprStmt.expr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstIfStmt ifStmt, Object arg) {
		//System.out.println("ifstmtid = "+ifStmt.id()+" arg = "+arg);
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
		//System.out.println("whilestmtid = "+whileStmt.id()+" arg = "+arg);
		if (whileStmt.cond != null)
			whileStmt.cond.accept(this, arg);
		if (whileStmt.bodyStmt != null)
			whileStmt.bodyStmt.accept(this, arg);
		return null;
	}

	// TYPES

	@Override
	public Object visit(AstArrType arrType, Object arg) {
		//System.out.println("arrtypeid = "+arrType.id()+" arg = "+arg);
		if (arrType.elemType != null)
			arrType.elemType.accept(this, arg);
		if (arrType.numElems != null)
			arrType.numElems.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstAtomType atomType, Object arg) {
		//System.out.println("atomtypeid = "+atomType.id()+" arg = "+arg);
		return null;
	}

	@Override
	public Object visit(AstNameType nameType, Object arg) {
		//System.out.println("nametypeid = "+nameType.id()+" arg = "+arg);
		try {
			AstDecl sym = symbTable.fnd(nameType.name);
			if (!(sym instanceof AstTypeDecl))
				throw new Report.Error(nameType.location(), "Symbol"+nameType.name+"is not a type.");
			SemAn.declaredAt.put(nameType, sym);
		} catch (Exception e) {
			throw new Report.Error(nameType.location(), "Usage of undeclared type "+nameType.name);
		}
		return null;
	}

	@Override
	public Object visit(AstPtrType ptrType, Object arg) {
		//System.out.println("ptrtypeid = "+ptrType.id()+" arg = "+arg);
		if (ptrType.baseType != null)
			ptrType.baseType.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstRecType recType, Object arg) {
		//System.out.println("rectypeid = "+recType.id()+" arg = "+arg);
		

			recType.comps.accept(this, arg);
			
		return null;
	}

}

