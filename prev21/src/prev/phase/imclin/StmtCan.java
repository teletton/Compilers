package prev.phase.imclin;

import java.util.*;

import prev.common.report.Report;
import prev.data.imc.visitor.ImcVisitor;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.mem.*;

public class StmtCan implements ImcVisitor<Vector<ImcStmt>, Object> {

    public static Stack<ImcExpr> stexpr = new Stack<ImcExpr>();
    
    @Override
    public Vector<ImcStmt> visit(ImcSTMTS stmts, Object visArg) {
        //System.out.println("IMCSTMTS");
        Vector<ImcStmt> stmt = new Vector<ImcStmt>();

        for (ImcStmt cStmt : stmts.stmts) {
            Vector<ImcStmt> nStmt = cStmt.accept(this, visArg);
            
            stmt.addAll(nStmt);
        }



        return stmt;
    }

    @Override
    public Vector<ImcStmt> visit(ImcBINOP binOp, Object visArg) {
        //System.out.println("IMCBINOP");
        Vector<ImcStmt> prvStmt = binOp.fstExpr.accept(this, visArg);
        MemTemp prv = new MemTemp();
        ImcExpr prvexpr = stexpr.pop();
 
        Vector<ImcStmt> vtorStmt = binOp.sndExpr.accept(this, visArg);
        MemTemp vtor = new MemTemp();
        ImcTEMP pr = new ImcTEMP(prv);
        ImcTEMP vt = new ImcTEMP(vtor);
        ImcExpr vtorexpr = stexpr.pop();

        Vector<ImcStmt> stmt = new Vector<ImcStmt>();
        stmt.addAll(prvStmt);
        stmt.add(new ImcMOVE(pr, prvexpr));
        stmt.addAll(vtorStmt);
        stmt.add(new ImcMOVE(vt, vtorexpr));
        stexpr.push(new ImcBINOP(binOp.oper, pr, vt));
  
        return stmt;
    }

    @Override
    public Vector<ImcStmt> visit(ImcCALL call, Object visArg) {
        //System.out.println("IMCCALL");
        Vector<ImcExpr> args = new Vector<ImcExpr>();
        Vector<ImcStmt> stmt = new Vector<ImcStmt>();
        for (int i = 0; i < call.args.size(); i++) {
            Vector<ImcStmt> aStmt = new Vector<ImcStmt>();
            if (i == 0) {
                MemTemp temp = new MemTemp();
                ImcTEMP tt = new ImcTEMP(temp);
                aStmt.add(new ImcMOVE(tt, call.args.get(i)));
                args.add(tt);
                stmt.addAll(aStmt);
                continue;
            }
            ImcExpr expr = call.args.get(i);
            Vector<ImcStmt> argStmt = expr.accept(this, visArg);
            MemTemp argv = new MemTemp();
            ImcTEMP argvt = new ImcTEMP(argv);
            ImcExpr argExpr = stexpr.pop();

            aStmt.addAll(argStmt);
            aStmt.add(new ImcMOVE(argvt, argExpr));
            args.add(argvt);
            stmt.addAll(aStmt);
        }
        stexpr.push(new ImcCALL(call.label, call.offs, args));

        return stmt;
    }

    @Override
    public Vector<ImcStmt> visit(ImcCJUMP cjump, Object visArg) {
        //System.out.println("IMCCJUMP");
        Vector<ImcStmt> cStmt = cjump.cond.accept(this, visArg);
        MemTemp temp = new MemTemp();
        ImcTEMP tt = new ImcTEMP(temp);
        ImcExpr cExpr = stexpr.pop();

        Vector<ImcStmt> stmt = new Vector<ImcStmt>();
        stmt.addAll(cStmt);
        stmt.add(new ImcMOVE(tt, cExpr));
        MemLabel fLabel = new MemLabel();
        stmt.add(new ImcCJUMP(tt, cjump.posLabel, fLabel));
        stmt.add(new ImcLABEL(fLabel));
        stmt.add(new ImcJUMP(cjump.negLabel));

        return stmt;
    }

    @Override
    public Vector<ImcStmt> visit(ImcCONST constant, Object visArg) {
        //System.out.println("IMCCONST");
        Vector<ImcStmt> vist = new Vector<ImcStmt>();
        stexpr.push(constant);

        return vist;
    }

    @Override
    public Vector<ImcStmt> visit(ImcESTMT eStmt, Object visArg) {
        //System.out.println("IMCESTMT");
        Vector<ImcStmt> exStmt = eStmt.expr.accept(this, visArg);
        ImcExpr exExpr = stexpr.pop();


        Vector<ImcStmt> stmt = new Vector<ImcStmt>();
        stmt.addAll(exStmt);
        stmt.add(new ImcMOVE(new ImcTEMP(new MemTemp()), exExpr));

        return stmt;
    }

    @Override
    public Vector<ImcStmt> visit(ImcJUMP jump, Object visArg) {
        //System.out.println("IMCJUMP");
        Vector<ImcStmt> stmt = new Vector<ImcStmt>();
        stmt.add(jump);

        return stmt;
    }

    public Vector<ImcStmt> visit(ImcLABEL label, Object visArg) {
        //System.out.println("IMCLABEL");
        Vector<ImcStmt> stmt = new Vector<ImcStmt>();
        stmt.add(label);

        return stmt;
    }

    @Override
    public Vector<ImcStmt> visit(ImcMEM mem, Object visArg) {
        //System.out.println("IMCMEM");
        Vector<ImcStmt> addrStmt = mem.addr.accept(this, visArg);
        MemTemp temp = new MemTemp();
        ImcTEMP tt = new ImcTEMP(temp);
        ImcExpr addrExpr = stexpr.pop();

        Vector<ImcStmt> stmt = new Vector<ImcStmt>();
        stmt.addAll(addrStmt);
        stmt.add(new ImcMOVE(tt, addrExpr));
        stexpr.push(new ImcMEM(tt));

        return stmt;
    }

    @Override
    public Vector<ImcStmt> visit(ImcMOVE move, Object visArg) {
        //System.out.println("IMCMOVE" + move.);
        if (move.dst instanceof ImcMEM) {

            Vector<ImcStmt> dstStmt = move.dst.accept(this, visArg);
            ImcExpr dstExpr = stexpr.pop();


            Vector<ImcStmt> srcStmt = move.src.accept(this, visArg);
            MemTemp src = new MemTemp();
            ImcExpr srcExpr = stexpr.pop();


            Vector<ImcStmt> stmt = new Vector<ImcStmt>();
            stmt.addAll(dstStmt);
            stmt.addAll(srcStmt);
            ImcTEMP sr = new ImcTEMP(src);
            stmt.add(new ImcMOVE(sr, srcExpr));
            stmt.add(new ImcMOVE(dstExpr, sr));

            return stmt;
        }

        Vector<ImcStmt> dstStmt = move.dst.accept(this, visArg);
        MemTemp dst = new MemTemp();
        ImcExpr dstExpr = stexpr.pop();


        Vector<ImcStmt> srcStmt = move.src.accept(this, visArg);
        MemTemp src = new MemTemp();
        ImcExpr srcExpr = stexpr.pop();

        Vector<ImcStmt> stmt = new Vector<ImcStmt>();
        stmt.addAll(dstStmt);
        stmt.add(new ImcMOVE(new ImcTEMP(dst), dstExpr));
        stmt.addAll(srcStmt);
        ImcTEMP sr = new ImcTEMP(src);
        stmt.add(new ImcMOVE(sr, srcExpr));
        ImcTEMP ds = new ImcTEMP(dst);
        System.out.println(ds);
        stmt.add(new ImcMOVE(ds, sr));

        return stmt;
    }

    @Override
    public Vector<ImcStmt> visit(ImcNAME name, Object visArg) {
        //System.out.println("IMCNAME");
        Vector<ImcStmt> vist = new Vector<ImcStmt>();
        stexpr.push(name);

        return vist;
    }

    @Override
    public Vector<ImcStmt> visit(ImcSEXPR sExpr, Object visArg) {
        //System.out.println("IMCSEXPR");
        Vector<ImcStmt> sStmt = sExpr.stmt.accept(this, visArg);

        Vector<ImcStmt> sexprStmt = sExpr.expr.accept(this, visArg);
        MemTemp temp = new MemTemp();
        ImcTEMP tt = new ImcTEMP(temp);
        ImcExpr sexprExpr = stexpr.pop();

        Vector<ImcStmt> stmt = new Vector<ImcStmt>();
        stmt.addAll(sStmt);
        stmt.addAll(sexprStmt);
        stmt.add(new ImcMOVE(tt, sexprExpr));
        stexpr.push(tt);

        return stmt;
    }

    @Override
    public Vector<ImcStmt> visit(ImcTEMP temp, Object visArg) {
        //System.out.println("IMCTEMP");

        Vector<ImcStmt> vist = new Vector<ImcStmt>();
        stexpr.push(temp);
 
        return vist;
    }

    @Override
    public Vector<ImcStmt> visit(ImcUNOP unOp, Object visArg) {
        //System.out.println("IMCUNOP");
        Vector<ImcStmt> subStmt = unOp.subExpr.accept(this, visArg);
        MemTemp temp = new MemTemp();
        ImcTEMP tt = new ImcTEMP(temp);
        ImcExpr subExpr = stexpr.pop();

        Vector<ImcStmt> stmt = new Vector<ImcStmt>();
        stmt.addAll(subStmt);
        stmt.add(new ImcMOVE(tt, subExpr));
        stexpr.push(new ImcUNOP(unOp.oper, tt));


        return stmt;
    }
}
