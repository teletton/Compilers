package prev.phase.imcgen;

import java.util.*;

import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.visitor.*;
import prev.data.mem.*;
import prev.phase.memory.*;

public class CodeGenerator extends AstNullVisitor<Object, Stack<MemFrame>> {
    // GENERAL PURPOSE

	@Override
	public Object visit(AstTrees<? extends AstTree> trees, Stack<MemFrame> arg) {
        //System.out.println("CODEtrees");
        for (AstTree t : trees) {
            t.accept(this, arg);
        }
		return null;
	}

	// DECLARATIONS

	@Override
	public Object visit(AstCompDecl compDecl, Stack<MemFrame> arg) {
        //System.out.println("CODEcompdecl");
		return null;
	}

	@Override
	public Object visit(AstFunDecl funDecl, Stack<MemFrame> arg) {
        //System.out.println("CODEfundecl = " + funDecl.name);
        if (funDecl.expr != null) {
			MemFrame mf = Memory.frames.get(funDecl);
			arg.add(mf);
            funDecl.expr.accept(new ExprGenerator(), arg);
			arg.pop();
        }
		return null;
	}

	@Override
	public Object visit(AstParDecl parDecl, Stack<MemFrame> arg) {
        //System.out.println("CODEpardecl");
		return null;
	}

	@Override
	public Object visit(AstTypeDecl typeDecl, Stack<MemFrame> arg) {
       // System.out.println("CODEtypedecl");
		return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Stack<MemFrame> arg) {
        //System.out.println("CODEvardecl");
		return null;
	}

}
