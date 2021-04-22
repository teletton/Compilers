package prev.phase.imclin;

import java.util.*;

import prev.data.ast.tree.AstTree;
import prev.data.ast.tree.AstTrees;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.AstAssignStmt;
import prev.data.ast.tree.stmt.AstExprStmt;
import prev.data.ast.tree.stmt.AstIfStmt;
import prev.data.ast.tree.stmt.AstWhileStmt;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.*;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.lin.*;
import prev.data.typ.SemChar;
import prev.phase.imcgen.*;
import prev.phase.memory.*;

public class ChunkGenerator extends AstFullVisitor<Object, Object> {
    // GENERAL PURPOSE

    @Override
    public Object visit(AstTrees<? extends AstTree> trees, Object arg) {
        for (AstTree t : trees)
            if (t != null)
                t.accept(this, arg);
        return null;
    }

    // DECLARATIONS

    @Override
    public Object visit(AstCompDecl compDecl, Object arg) {
        if (compDecl.type != null)
            compDecl.type.accept(this, arg);
        return null;
    }

    @Override
    public Object visit(AstFunDecl funDecl, Object arg) {
        //System.out.println("funDecl name = " + funDecl.name);
        if (funDecl.pars != null)
            funDecl.pars.accept(this, arg);
        if (funDecl.type != null)
            funDecl.type.accept(this, arg);
        if (funDecl.expr != null) {
            funDecl.expr.accept(this, arg);
            ImcExpr ie = ImcGen.exprImc.get(funDecl.expr);
            //System.out.println(ie.getClass());
            ie.accept(new StmtCan(), 0);
            Vector<ImcStmt> vis = StmtCan.svec.peek();
            StmtCan.svec.pop();
            MemFrame mf = Memory.frames.get(funDecl);
            MemLabel entry = new MemLabel();
            MemLabel exit = new MemLabel();
            Vector<ImcStmt> vis1 = new Vector<ImcStmt>();
            vis1.add(new ImcLABEL(entry));
            vis1.addAll(vis);
            vis1.add(new ImcMOVE(new ImcTEMP(mf.RV), StmtCan.stexpr.peek()));
            StmtCan.stexpr.pop();
            vis1.add(new ImcJUMP(exit));
            ImcLin.addCodeChunk(new LinCodeChunk(mf, vis1, entry, exit));
        }
        return null;
    }

    @Override
    public Object visit(AstParDecl parDecl, Object arg) {
        if (parDecl.type != null)
            parDecl.type.accept(this, arg);
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
        if (varDecl.type != null) {
            varDecl.type.accept(this, arg);
            MemAccess maa = Memory.accesses.get(varDecl);
            if (maa instanceof MemAbsAccess) {
                LinDataChunk ldc = new LinDataChunk((MemAbsAccess) maa);
                ImcLin.addDataChunk(ldc);
            }
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
        if (atomExpr.type == AstAtomExpr.Type.STRING) {
            String init = atomExpr.value;
            ImcNAME imc = (ImcNAME) ImcGen.exprImc.get(atomExpr);
            ImcLin.addDataChunk(new LinDataChunk(new MemAbsAccess((init.length() + 1) * (new SemChar()).size(), imc.label, init)));
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
        if (callExpr.args != null)
            callExpr.args.accept(this, arg);
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
