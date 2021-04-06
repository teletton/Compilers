package prev.phase.seman;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.*;
import prev.data.typ.*;



/**
 * Address resolver.
 * 
 * The address resolver finds out which expressions denote lvalues and leaves
 * the information in {@link SemAn#isAddr}.
 */
public class AddrResolver extends AstFullVisitor<Boolean, Integer> {
    @Override
    public Boolean visit(AstTrees<? extends AstTree> trees, Integer arg) {
      //  System.out.println("id = "+trees.id()+" class = "+trees.getClass()+" arg = "+arg);

        for (AstTree t : trees) {
           // System.out.println("id = "+t.id()+" class = "+t.getClass()+" arg = "+arg);

            t.accept(this, (int)arg);
        }
        return null;
    }

    // DECLARATIONS

    @Override
    public Boolean visit(AstCompDecl compDecl, Integer arg) {
      //  System.out.println("compdeclid = "+compDecl.id()+" arg = "+arg);
        if (compDecl.type != null) {
            compDecl.type.accept(this, arg);
        }
        return null;
    }

    @Override
    public Boolean visit(AstFunDecl funDecl, Integer arg) {
      //  System.out.println("fundeclid = "+funDecl.id()+" arg = "+arg);

        if (funDecl.pars != null)
            funDecl.pars.accept(this, arg);
        if (funDecl.type != null)
            funDecl.type.accept(this, arg);

        if (funDecl.expr != null) {

            funDecl.expr.accept(this, arg);

        }

        return null;
    }

    @Override
    public Boolean visit(AstParDecl parDecl, Integer arg) {
       // System.out.println("pardeclid = "+parDecl.id()+" arg = "+arg);
        if (parDecl.type != null)
            parDecl.type.accept(this, arg);
        return null;
    }

    @Override
    public Boolean visit(AstTypeDecl typeDecl, Integer arg) {
       // System.out.println("typedeclid = "+typeDecl.id()+" arg = "+arg);
        if (typeDecl.type != null)
            typeDecl.type.accept(this, arg);
        return null;
    }

    @Override
    public Boolean visit(AstVarDecl varDecl, Integer arg) {
       // System.out.println("vardeclid = "+varDecl.id()+" arg = "+arg);
        if (varDecl.type != null)
            varDecl.type.accept(this, arg);
        
        return true;
    }

    // EXPRESSIONS

    @Override
    public Boolean visit(AstArrExpr arrExpr, Integer arg) {
        //System.out.println("arexprid = "+arrExpr.id()+" arg = "+arg);
        boolean bt = false;
        if (arg == 256)
            bt = arrExpr.arr.accept(this, arg);
        else
            arrExpr.arr.accept(this, arg);
        arrExpr.idx.accept(this, arg);
        
          if (bt) {
            SemAn.isAddr.put(arrExpr, true);
            return true;
        }
        
        return null;
    }

    @Override
    public Boolean visit(AstAtomExpr atomExpr, Integer arg) {
      //  System.out.println("atomexprid = "+atomExpr.id()+" arg = "+arg);
        return null;
    }

    @Override
    public Boolean visit(AstBinExpr binExpr, Integer arg) {
       // System.out.println("binexprid = "+binExpr.id()+" arg = "+arg);
        if (binExpr.fstExpr != null)
            binExpr.fstExpr.accept(this, arg);
        if (binExpr.sndExpr != null)
            binExpr.sndExpr.accept(this, arg);
        return null;
    }

    @Override
    public Boolean visit(AstCallExpr callExpr, Integer arg) {
       // System.out.println("callexprid = "+callExpr.id()+" arg = "+arg);
        callExpr.args.accept(this, arg);
        return null;
    }

    @Override
    public Boolean visit(AstCastExpr castExpr, Integer arg) {
       // System.out.println("castexprid = "+castExpr.id()+" arg = "+arg);
        if (castExpr.expr != null)
            castExpr.expr.accept(this, arg);
        if (castExpr.type != null)
            castExpr.type.accept(this, arg);
        return null;
    }

    @Override
    public Boolean visit(AstNameExpr nameExpr, Integer arg) {
      //  System.out.println("nameexprid = "+nameExpr.id()+" arg = "+arg);
        AstDecl ad = SemAn.declaredAt.get(nameExpr);
        if ((int)arg == 256) {
            SemAn.isAddr.put(nameExpr, true);
            return true;
        }
        return null;
    }

    @Override
    public Boolean visit(AstPfxExpr pfxExpr, Integer arg) {
      //  System.out.println("pfxexprid = "+pfxExpr.id()+" arg = "+arg);
        if (pfxExpr.expr != null)
            pfxExpr.expr.accept(this, arg);
        return null;
    }

    @Override
    public Boolean visit(AstRecExpr recExpr, Integer arg) {
      //  System.out.println("recexprid = "+recExpr.id()+" arg = "+arg);
        SemAn.isAddr.put(recExpr.rec, true);
        if (recExpr.rec != null)
            recExpr.rec.accept(this, arg);
        if (recExpr.comp != null)
            recExpr.comp.accept(this, arg);
        return null;
    }

    @Override
    public Boolean visit(AstSfxExpr sfxExpr, Integer arg) {
      //  System.out.println("sfxexprid = "+sfxExpr.id()+" arg = "+arg);
        
        sfxExpr.expr.accept(this, arg);
        if (SemAn.ofType.get(sfxExpr.expr) instanceof SemPtr) {
            SemAn.isAddr.put(sfxExpr, true);
            return true;
        }
        return null;
    }

    @Override
    public Boolean visit(AstStmtExpr stmtExpr, Integer arg) {
     //  System.out.println("stmtexprid = "+stmtExpr.id()+" arg = "+arg);
        if (stmtExpr.stmts != null)
            stmtExpr.stmts.accept(this, arg);
        return null;
    }

    @Override
    public Boolean visit(AstWhereExpr whereExpr, Integer arg) {
      //  System.out.println("whereexprid = "+whereExpr.id()+" arg = "+arg);
        if (whereExpr.decls != null) {
            whereExpr.decls.accept(this, arg);
        }
        if (whereExpr.expr != null)
            whereExpr.expr.accept(this, arg);
        return null;
    }

    // STATEMENTS

    @Override
    public Boolean visit(AstAssignStmt assignStmt, Integer arg) {
      //  System.out.println("assignstmtid = "+assignStmt.id()+" arg = "+arg);
        if (assignStmt.dst != null)
            assignStmt.dst.accept(this, 256);
        if (assignStmt.src != null)
            assignStmt.src.accept(this, arg);
        return null;
    }


    @Override
    public Boolean visit(AstExprStmt exprStmt, Integer arg) {
       // System.out.println("exprstmtid = "+exprStmt.id()+" arg = "+arg);
        if (exprStmt.expr != null)
            exprStmt.expr.accept(this, arg);
        return null;
    }

    @Override
    public Boolean visit(AstIfStmt ifStmt, Integer arg) {
      //  System.out.println("ifstmtid = "+ifStmt.id()+" arg = "+arg);
        if (ifStmt.cond != null)
            ifStmt.cond.accept(this, arg);
        if (ifStmt.thenStmt != null)
            ifStmt.thenStmt.accept(this, arg);
        if (ifStmt.elseStmt != null)
            ifStmt.elseStmt.accept(this, arg);
        return null;
    }

    @Override
    public Boolean visit(AstWhileStmt whileStmt, Integer arg) {
      //  System.out.println("whilestmtid = "+whileStmt.id()+" arg = "+arg);
        if (whileStmt.cond != null)
            whileStmt.cond.accept(this, arg);
        if (whileStmt.bodyStmt != null)
            whileStmt.bodyStmt.accept(this, arg);
        return null;
    }

    // TYPES

    @Override
    public Boolean visit(AstArrType arrType, Integer arg) {
       // System.out.println("arrtypeid = "+arrType.id()+" arg = "+arg);
        if (arrType.elemType != null)
            arrType.elemType.accept(this, arg);
        if (arrType.numElems != null)
            arrType.numElems.accept(this, arg);
        return null;
    }

    @Override
    public Boolean visit(AstAtomType atomType, Integer arg) {
      //  System.out.println("atomtypeid = "+atomType.id()+" arg = "+arg);
        return null;
    }

    @Override
    public Boolean visit(AstNameType nameType, Integer arg) {
       // System.out.println("nametypeid = "+nameType.id()+" arg = "+arg);
        return null;
    }

    @Override
    public Boolean visit(AstPtrType ptrType, Integer arg) {
        //System.out.println("ptrtypeid = "+ptrType.id()+" arg = "+arg);
        if (ptrType.baseType != null)
            ptrType.baseType.accept(this, arg);
        return null;
    }

    @Override
    public Boolean visit(AstRecType recType, Integer arg) {
        //System.out.println("rectypeid = "+recType.id()+" arg = "+arg);
        if (recType.comps != null)
            recType.comps.accept(this, arg);
        return null;
    }

}
