parser grammar PrevParser;

@header {
	package prev.phase.synan;

	import java.util.*;

	import prev.common.report.*;
	import prev.phase.lexan.*;
	import prev.data.ast.tree.decl.*;
    import prev.data.ast.tree.type.*;
    import prev.data.ast.tree.expr.*;
    import prev.data.ast.tree.stmt.*;
    import prev.data.ast.tree.*;
    import prev.phase.lexan.PrevLexer;
    import prev.data.ast.visitor.*;
	
}

options{
    tokenVocab = PrevLexer;
}


source
    returns[AstTrees<AstDecl> ast]
    : {Vector<AstDecl> decls = new Vector<AstDecl>();} (decl {decls.add($decl.ast);})+ {$ast = new AstTrees<AstDecl>(decls);}
    ;

decl
    returns[AstDecl ast]
    :  	TYP IDENTIFIER IS type {$ast = new AstTypeDecl(new Location($start.getLine(), $start.getCharPositionInLine(), ($type.ast.location()).end1(), ($type.ast.location()).end2()), $IDENTIFIER.text, $type.ast);}
    |   VAR IDENTIFIER COLON type {$ast = new AstVarDecl(new Location($start.getLine(), $start.getCharPositionInLine(), ($type.ast.location()).end1(), ($type.ast.location()).end2()), $IDENTIFIER.text, $type.ast);}
    |   {Vector<AstParDecl> par = new Vector<AstParDecl>();} FUN funName = IDENTIFIER LPAREN ( IDENTIFIER COLON type1 = type {par.add(new AstParDecl(new Location($start.getLine(), $start.getCharPositionInLine(), ($type1.ast.location()).end1(), ($type1.ast.location()).end2()), $IDENTIFIER.text, $type1.ast));} (COMMA IDENTIFIER COLON type2 = type {par.add(new AstParDecl(new Location($start.getLine(), $start.getCharPositionInLine(), ($type2.ast.location()).end1(), ($type2.ast.location()).end2()), $IDENTIFIER.text, $type2.ast));})*)? RPAREN COLON funType = type {$ast = new AstFunDecl(new Location($start.getLine(), $start.getCharPositionInLine(), ($funType.ast.location()).end1(), ($funType.ast.location()).end2()), $funName.text, new AstTrees<AstParDecl>(par) , $funType.ast, null);}
    |   {Vector<AstParDecl> par = new Vector<AstParDecl>();} FUN funName = IDENTIFIER LPAREN ( IDENTIFIER COLON type1 = type {par.add(new AstParDecl(new Location($start.getLine(), $start.getCharPositionInLine(), ($type1.ast.location()).end1(), ($type1.ast.location()).end2()), $IDENTIFIER.text, $type1.ast));} (COMMA IDENTIFIER COLON type2 = type {par.add(new AstParDecl(new Location($start.getLine(), $start.getCharPositionInLine(), ($type2.ast.location()).end1(), ($type2.ast.location()).end2()), $IDENTIFIER.text, $type2.ast));})*)? RPAREN COLON funType = type IS expr {$ast = new AstFunDecl(new Location($start.getLine(), $start.getCharPositionInLine(), ($expr.ast.location()).end1(), ($expr.ast.location()).end2()), $funName.text, new AstTrees<AstParDecl>(par) , $funType.ast, $expr.ast);}


    ;

type
    returns[AstType ast]
     : IDENTIFIER {$ast = new AstNameType(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), $IDENTIFIER.text);}
     | LBRACKET exprtip2 = expr RBRACKET type1 = type {$ast = new AstArrType(new Location($start.getLine(), $start.getCharPositionInLine(), ($type1.ast.location()).end1(), ($type1.ast.location()).end2()), $type1.ast, $exprtip2.ast);}
     | PTR type1 = type {$ast = new AstPtrType(new Location($start.getLine(), $start.getCharPositionInLine(), ($type1.ast.location()).end1(), ($type1.ast.location()).end2()), $type1.ast);}
     | {Vector<AstCompDecl> compdec = new Vector<AstCompDecl>();} LBRACE IDENTIFIER COLON type1 = type {compdec.add(new AstCompDecl(new Location($start.getLine(), $start.getCharPositionInLine(), ($type1.ast.location()).end1(), ($type1.ast.location()).end2()), $IDENTIFIER.text, $type1.ast));} ( COMMA IDENTIFIER COLON type2 = type {compdec.add(new AstCompDecl(new Location($start.getLine(), $start.getCharPositionInLine(), ($type2.ast.location()).end1(), ($type2.ast.location()).end2()), $IDENTIFIER.text, $type2.ast));} )* RBRACE {$ast = new AstRecType(new Location($start.getLine(), $start.getCharPositionInLine(), $RBRACE.getLine(), $RBRACE.getCharPositionInLine() + $RBRACE.getText().length() - 1), new AstTrees<AstCompDecl>(compdec));}
     | LPAREN type1 = type RPAREN {$ast = $type1.ast;}
     | CHAR {$ast = new AstAtomType(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomType.Type.CHAR);}
     | INT {$ast = new AstAtomType(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomType.Type.INT);}
     | VOID {$ast = new AstAtomType(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomType.Type.VOID);}
     | BOOL {$ast = new AstAtomType(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomType.Type.BOOL);}
     ;

expr
    returns[AstExpr ast]:
    {Vector<AstStmt> stm = new Vector<AstStmt>();}LBRACE stm1 = stmt{stm.add($stm1.ast);} SEMIC (stm2 = stmt {stm.add($stm2.ast);} SEMIC)* RBRACE{$ast = new AstStmtExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $RBRACE.getLine(), $RBRACE.getCharPositionInLine() + $RBRACE.getText().length() - 1), new AstTrees<AstStmt>(stm));}
    | {Vector<AstExpr> exp = new Vector<AstExpr>();} IDENTIFIER LPAREN (exprpoc = expr {exp.add($exprpoc.ast);} (COMMA exprost = expr {exp.add($exprost.ast);})*)? RPAREN {$ast = new AstCallExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $RPAREN.getLine(), $RPAREN.getCharPositionInLine() + $RPAREN.getText().length() - 1), $IDENTIFIER.text, new AstTrees<AstExpr>(exp));}
    | NEW exprnew = expr {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprnew.ast.location()).end1(), ($exprnew.ast.location()).end2()), AstPfxExpr.Oper.NEW, $exprnew.ast);}
    | DEL exprdel = expr {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprdel.ast.location()).end1(), ($exprdel.ast.location()).end2()), AstPfxExpr.Oper.DEL, $exprdel.ast);}
    | LPAREN exprlp = expr RPAREN {$ast = $exprlp.ast;}
    | LPAREN exprcast = expr COLON type RPAREN {$ast = new AstCastExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $RPAREN.getCharPositionInLine() + $RPAREN.getText().length() - 1), $exprcast.ast, $type.ast);}
    | exprlb = expr LBRACKET exprrb = expr RBRACKET {$ast = new AstArrExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $RBRACKET.getCharPositionInLine() + $RBRACKET.getText().length() - 1), $exprlb.ast, $exprrb.ast);}
    | exprptr = expr PTR {$ast = new AstSfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $PTR.getCharPositionInLine() + $PTR.getText().length() - 1), AstSfxExpr.Oper.PTR, $exprptr.ast);}
    | exprdot = expr DOT IDENTIFIER {$ast = new AstRecExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprdot.ast.location()).end1(), ($exprdot.ast.location()).end2()), $exprdot.ast, new AstNameExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $IDENTIFIER.getLine(), $IDENTIFIER.getCharPositionInLine() + $IDENTIFIER.getText().length() - 1), $IDENTIFIER.text));}
    | NOT exprnot = expr {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprnot.ast.location()).end1(), ($exprnot.ast.location()).end2()), AstPfxExpr.Oper.NOT, $exprnot.ast);}
    | ADD exprad = expr {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprad.ast.location()).end1(), ($exprad.ast.location()).end2()), AstPfxExpr.Oper.ADD, $exprad.ast);}
    | SUB exprsub = expr {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprsub.ast.location()).end1(), ($exprsub.ast.location()).end2()), AstPfxExpr.Oper.SUB, $exprsub.ast);}
    | PTR exprpttr = expr {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprpttr.ast.location()).end1(), ($exprpttr.ast.location()).end2()), AstPfxExpr.Oper.PTR, $exprpttr.ast);}
    | exprmull = expr MUL exprmulr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprmulr.ast.location()).end1(), ($exprmulr.ast.location()).end2()), AstBinExpr.Oper.MUL, $exprmull.ast, $exprmulr.ast);}
    | exprdivl = expr DIV exprdivr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprdivr.ast.location()).end1(), ($exprdivr.ast.location()).end2()), AstBinExpr.Oper.DIV, $exprdivl.ast, $exprdivr.ast);}
    | exprmodl = expr MOD exprmodr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprmodr.ast.location()).end1(), ($exprmodr.ast.location()).end2()), AstBinExpr.Oper.MOD, $exprmodl.ast, $exprmodr.ast);}
    | expraddl = expr ADD expraddr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($expraddr.ast.location()).end1(), ($expraddr.ast.location()).end2()), AstBinExpr.Oper.ADD, $expraddl.ast, $expraddr.ast);}
    | exprsubl = expr SUB exprsubr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprsubr.ast.location()).end1(), ($exprsubr.ast.location()).end2()), AstBinExpr.Oper.SUB, $exprsubl.ast, $exprsubr.ast);}
    | exprequl = expr EQU exprequr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprequr.ast.location()).end1(), ($exprequr.ast.location()).end2()), AstBinExpr.Oper.EQU, $exprequl.ast, $exprequr.ast);}
    | exprneql = expr NEQ exprneqr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprneqr.ast.location()).end1(), ($exprneqr.ast.location()).end2()), AstBinExpr.Oper.NEQ, $exprneql.ast, $exprneqr.ast);}
    | exprlthl = expr LTH exprlthr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprlthr.ast.location()).end1(), ($exprlthr.ast.location()).end2()), AstBinExpr.Oper.LTH, $exprlthl.ast, $exprlthr.ast);}
    | exprgthl = expr GTH exprgthr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprgthr.ast.location()).end1(), ($exprgthr.ast.location()).end2()), AstBinExpr.Oper.GTH, $exprgthl.ast, $exprgthr.ast);}
    | exprleql = expr LEQ exprleqr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprleqr.ast.location()).end1(), ($exprleqr.ast.location()).end2()), AstBinExpr.Oper.LEQ, $exprleql.ast, $exprleqr.ast);}
    | exprgeql = expr GEQ exprgeqr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprgeqr.ast.location()).end1(), ($exprgeqr.ast.location()).end2()), AstBinExpr.Oper.GEQ, $exprgeql.ast, $exprgeqr.ast);}
    | exprandl = expr AND exprandr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprandr.ast.location()).end1(), ($exprandr.ast.location()).end2()), AstBinExpr.Oper.AND, $exprandl.ast, $exprandr.ast);}
    | exprorl = expr OR exprorr = expr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprorr.ast.location()).end1(), ($exprorr.ast.location()).end2()), AstBinExpr.Oper.OR, $exprorl.ast, $exprorr.ast);}
    | {Vector<AstDecl> decls = new Vector<AstDecl>();} whileexpr = noviexpr WHERE LBRACE (wdecl = decl{decls.add($wdecl.ast);})+ RBRACE {$ast = new AstWhereExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $RBRACE.getLine(), $RBRACE.getCharPositionInLine() + $RBRACE.getText().length() - 1), $whileexpr.ast, new AstTrees<AstDecl>(decls));}
    | INTCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.INT, $INTCONST.text);}
    | BOOLCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.BOOL, $BOOLCONST.text);}
    | STRINGCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.STRING, $STRINGCONST.text);}
    | CHARCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.CHAR, $CHARCONST.text);}
    | POINTERCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.POINTER, $POINTERCONST.text);}
    | VOIDCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.VOID, $VOIDCONST.text);}
    | IDENTIFIER {$ast = new AstNameExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1),$IDENTIFIER.text);}
    ;

stmt
    returns[AstStmt ast]:
    exprstmt = expr {$ast = new AstExprStmt(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprstmt.ast.location()).end1(), ($exprstmt.ast.location()).end2()), $exprstmt.ast);}
    | exprassto = expr IS exprass = expr {$ast = new AstAssignStmt(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprass.ast.location()).end1(), ($exprass.ast.location()).end2()), $exprassto.ast, $exprass.ast);}
    | IF ifexpr = expr THEN thenstmt = stmt ELSE elsestmt = stmt {$ast = new AstIfStmt(new Location($start.getLine(), $start.getCharPositionInLine(), ($elsestmt.ast.location()).end1(), ($elsestmt.ast.location()).end2()), $ifexpr.ast, $thenstmt.ast, $elsestmt.ast);}
    | WHILE whileexpr = expr DO dostmt = stmt {$ast = new AstWhileStmt(new Location($start.getLine(), $start.getCharPositionInLine(), ($dostmt.ast.location()).end1(), ($dostmt.ast.location()).end2()), $whileexpr.ast, $dostmt.ast);}
    ;

noviexpr
    returns[AstExpr ast]:
    {Vector<AstStmt> stm = new Vector<AstStmt>();}LBRACE stm1 = stmt{stm.add($stm1.ast);} SEMIC (stm2 = stmt {stm.add($stm2.ast);} SEMIC)* RBRACE{$ast = new AstStmtExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $RBRACE.getLine(), $RBRACE.getCharPositionInLine() + $RBRACE.getText().length() - 1), new AstTrees<AstStmt>(stm));}
    | IDENTIFIER {$ast = new AstNameExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1),$IDENTIFIER.text);}
    | {Vector<AstExpr> exp = new Vector<AstExpr>();} IDENTIFIER LPAREN (exprpoc = noviexpr {exp.add($exprpoc.ast);} (COMMA exprost = noviexpr {exp.add($exprost.ast);})*)? RPAREN {$ast = new AstCallExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $RPAREN.getLine(), $RPAREN.getCharPositionInLine() + $RPAREN.getText().length() - 1), $IDENTIFIER.text, new AstTrees<AstExpr>(exp));}
    | NEW exprnew = noviexpr {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprnew.ast.location()).end1(), ($exprnew.ast.location()).end2()), AstPfxExpr.Oper.NEW, $exprnew.ast);}
    | DEL exprdel = noviexpr {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprdel.ast.location()).end1(), ($exprdel.ast.location()).end2()), AstPfxExpr.Oper.DEL, $exprdel.ast);}
    | LPAREN exprlp = noviexpr RPAREN {$ast = $exprlp.ast;}
    | LPAREN exprcast = noviexpr COLON type RPAREN {$ast = new AstCastExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $RPAREN.getCharPositionInLine() + $RPAREN.getText().length() - 1), $exprcast.ast, $type.ast);}
    | exprlb = noviexpr LBRACKET exprrb = noviexpr RBRACKET {$ast = new AstArrExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $RBRACKET.getCharPositionInLine() + $RBRACKET.getText().length() - 1), $exprlb.ast, $exprrb.ast);}
    | exprptr = noviexpr PTR {$ast = new AstSfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $PTR.getCharPositionInLine() + $PTR.getText().length() - 1), AstSfxExpr.Oper.PTR, $exprptr.ast);}
    | exprdot = noviexpr DOT IDENTIFIER {$ast = new AstRecExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprdot.ast.location()).end1(), ($exprdot.ast.location()).end2()), $exprdot.ast, new AstNameExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $IDENTIFIER.getLine(), $IDENTIFIER.getCharPositionInLine() + $IDENTIFIER.getText().length() - 1), $IDENTIFIER.text));}
    | NOT exprnot = noviexpr {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprnot.ast.location()).end1(), ($exprnot.ast.location()).end2()), AstPfxExpr.Oper.NOT, $exprnot.ast);}
    | ADD exprad = noviexpr {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprad.ast.location()).end1(), ($exprad.ast.location()).end2()), AstPfxExpr.Oper.ADD, $exprad.ast);}
    | SUB exprsub = noviexpr {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprsub.ast.location()).end1(), ($exprsub.ast.location()).end2()), AstPfxExpr.Oper.SUB, $exprsub.ast);}
    | PTR exprpttr = noviexpr {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprpttr.ast.location()).end1(), ($exprpttr.ast.location()).end2()), AstPfxExpr.Oper.PTR, $exprpttr.ast);}
    | exprmull = noviexpr MUL exprmulr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprmulr.ast.location()).end1(), ($exprmulr.ast.location()).end2()), AstBinExpr.Oper.MUL, $exprmull.ast, $exprmulr.ast);}
    | exprdivl = noviexpr DIV exprdivr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprdivr.ast.location()).end1(), ($exprdivr.ast.location()).end2()), AstBinExpr.Oper.DIV, $exprdivl.ast, $exprdivr.ast);}
    | exprmodl = noviexpr MOD exprmodr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprmodr.ast.location()).end1(), ($exprmodr.ast.location()).end2()), AstBinExpr.Oper.MOD, $exprmodl.ast, $exprmodr.ast);}
    | expraddl = noviexpr ADD expraddr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($expraddr.ast.location()).end1(), ($expraddr.ast.location()).end2()), AstBinExpr.Oper.ADD, $expraddl.ast, $expraddr.ast);}
    | exprsubl = noviexpr SUB exprsubr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprsubr.ast.location()).end1(), ($exprsubr.ast.location()).end2()), AstBinExpr.Oper.SUB, $exprsubl.ast, $exprsubr.ast);}
    | exprequl = noviexpr EQU exprequr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprequr.ast.location()).end1(), ($exprequr.ast.location()).end2()), AstBinExpr.Oper.EQU, $exprequl.ast, $exprequr.ast);}
    | exprneql = noviexpr NEQ exprneqr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprneqr.ast.location()).end1(), ($exprneqr.ast.location()).end2()), AstBinExpr.Oper.NEQ, $exprneql.ast, $exprneqr.ast);}
    | exprlthl = noviexpr LTH exprlthr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprlthr.ast.location()).end1(), ($exprlthr.ast.location()).end2()), AstBinExpr.Oper.LTH, $exprlthl.ast, $exprlthr.ast);}
    | exprgthl = noviexpr GTH exprgthr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprgthr.ast.location()).end1(), ($exprgthr.ast.location()).end2()), AstBinExpr.Oper.GTH, $exprgthl.ast, $exprgthr.ast);}
    | exprleql = noviexpr LEQ exprleqr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprleqr.ast.location()).end1(), ($exprleqr.ast.location()).end2()), AstBinExpr.Oper.LEQ, $exprleql.ast, $exprleqr.ast);}
    | exprgeql = noviexpr GEQ exprgeqr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprgeqr.ast.location()).end1(), ($exprgeqr.ast.location()).end2()), AstBinExpr.Oper.GEQ, $exprgeql.ast, $exprgeqr.ast);}
    | exprandl = noviexpr AND exprandr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprandr.ast.location()).end1(), ($exprandr.ast.location()).end2()), AstBinExpr.Oper.AND, $exprandl.ast, $exprandr.ast);}
    | exprorl = noviexpr OR exprorr = noviexpr {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprorr.ast.location()).end1(), ($exprorr.ast.location()).end2()), AstBinExpr.Oper.OR, $exprorl.ast, $exprorr.ast);}
    | {Vector<AstDecl> decls = new Vector<AstDecl>();} whileexpr = noviexpr2 WHERE LBRACE (wdecl = decl{decls.add($wdecl.ast);})+ RBRACE {$ast = new AstWhereExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $RBRACE.getLine(), $RBRACE.getCharPositionInLine() + $RBRACE.getText().length() - 1), $whileexpr.ast, new AstTrees<AstDecl>(decls));}
    | INTCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.INT, $INTCONST.text);}
    | BOOLCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.BOOL, $BOOLCONST.text);}
    | STRINGCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.STRING, $STRINGCONST.text);}
    | CHARCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.CHAR, $CHARCONST.text);}
    | POINTERCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.POINTER, $POINTERCONST.text);}
    | VOIDCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.VOID, $VOIDCONST.text);}
    ;

    noviexpr2
        returns[AstExpr ast]:
        {Vector<AstStmt> stm = new Vector<AstStmt>();}LBRACE stm1 = stmt{stm.add($stm1.ast);} SEMIC (stm2 = stmt {stm.add($stm2.ast);} SEMIC)* RBRACE{$ast = new AstStmtExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $RBRACE.getLine(), $RBRACE.getCharPositionInLine() + $RBRACE.getText().length() - 1), new AstTrees<AstStmt>(stm));}
        | IDENTIFIER {$ast = new AstNameExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1),$IDENTIFIER.text);}
        | {Vector<AstExpr> exp = new Vector<AstExpr>();} IDENTIFIER LPAREN (exprpoc = noviexpr2 {exp.add($exprpoc.ast);} (COMMA exprost = noviexpr2 {exp.add($exprost.ast);})*)? RPAREN {$ast = new AstCallExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $RPAREN.getLine(), $RPAREN.getCharPositionInLine() + $RPAREN.getText().length() - 1), $IDENTIFIER.text, new AstTrees<AstExpr>(exp));}
        | NEW exprnew = noviexpr2 {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprnew.ast.location()).end1(), ($exprnew.ast.location()).end2()), AstPfxExpr.Oper.NEW, $exprnew.ast);}
        | DEL exprdel = noviexpr2 {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprdel.ast.location()).end1(), ($exprdel.ast.location()).end2()), AstPfxExpr.Oper.DEL, $exprdel.ast);}
        | LPAREN exprlp = noviexpr2 RPAREN {$ast = $exprlp.ast;}
        | LPAREN exprcast = noviexpr2 COLON type RPAREN {$ast = new AstCastExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $RPAREN.getCharPositionInLine() + $RPAREN.getText().length() - 1), $exprcast.ast, $type.ast);}
        | exprlb = noviexpr2 LBRACKET exprrb = noviexpr2 RBRACKET {$ast = new AstArrExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $RBRACKET.getCharPositionInLine() + $RBRACKET.getText().length() - 1), $exprlb.ast, $exprrb.ast);}
        | exprptr = noviexpr2 PTR {$ast = new AstSfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $PTR.getCharPositionInLine() + $PTR.getText().length() - 1), AstSfxExpr.Oper.PTR, $exprptr.ast);}
        | exprdot = noviexpr2 DOT IDENTIFIER {$ast = new AstRecExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprdot.ast.location()).end1(), ($exprdot.ast.location()).end2()), $exprdot.ast, new AstNameExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $IDENTIFIER.getLine(), $IDENTIFIER.getCharPositionInLine() + $IDENTIFIER.getText().length() - 1), $IDENTIFIER.text));}
        | NOT exprnot = noviexpr2 {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprnot.ast.location()).end1(), ($exprnot.ast.location()).end2()), AstPfxExpr.Oper.NOT, $exprnot.ast);}
        | ADD exprad = noviexpr2 {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprad.ast.location()).end1(), ($exprad.ast.location()).end2()), AstPfxExpr.Oper.ADD, $exprad.ast);}
        | SUB exprsub = noviexpr2 {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprsub.ast.location()).end1(), ($exprsub.ast.location()).end2()), AstPfxExpr.Oper.SUB, $exprsub.ast);}
        | PTR exprpttr = noviexpr2 {$ast = new AstPfxExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprpttr.ast.location()).end1(), ($exprpttr.ast.location()).end2()), AstPfxExpr.Oper.PTR, $exprpttr.ast);}
        | exprmull = noviexpr2 MUL exprmulr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprmulr.ast.location()).end1(), ($exprmulr.ast.location()).end2()), AstBinExpr.Oper.MUL, $exprmull.ast, $exprmulr.ast);}
        | exprdivl = noviexpr2 DIV exprdivr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprdivr.ast.location()).end1(), ($exprdivr.ast.location()).end2()), AstBinExpr.Oper.DIV, $exprdivl.ast, $exprdivr.ast);}
        | exprmodl = noviexpr2 MOD exprmodr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprmodr.ast.location()).end1(), ($exprmodr.ast.location()).end2()), AstBinExpr.Oper.MOD, $exprmodl.ast, $exprmodr.ast);}
        | expraddl = noviexpr2 ADD expraddr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($expraddr.ast.location()).end1(), ($expraddr.ast.location()).end2()), AstBinExpr.Oper.ADD, $expraddl.ast, $expraddr.ast);}
        | exprsubl = noviexpr2 SUB exprsubr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprsubr.ast.location()).end1(), ($exprsubr.ast.location()).end2()), AstBinExpr.Oper.SUB, $exprsubl.ast, $exprsubr.ast);}
        | exprequl = noviexpr2 EQU exprequr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprequr.ast.location()).end1(), ($exprequr.ast.location()).end2()), AstBinExpr.Oper.EQU, $exprequl.ast, $exprequr.ast);}
        | exprneql = noviexpr2 NEQ exprneqr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprneqr.ast.location()).end1(), ($exprneqr.ast.location()).end2()), AstBinExpr.Oper.NEQ, $exprneql.ast, $exprneqr.ast);}
        | exprlthl = noviexpr2 LTH exprlthr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprlthr.ast.location()).end1(), ($exprlthr.ast.location()).end2()), AstBinExpr.Oper.LTH, $exprlthl.ast, $exprlthr.ast);}
        | exprgthl = noviexpr2 GTH exprgthr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprgthr.ast.location()).end1(), ($exprgthr.ast.location()).end2()), AstBinExpr.Oper.GTH, $exprgthl.ast, $exprgthr.ast);}
        | exprleql = noviexpr2 LEQ exprleqr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprleqr.ast.location()).end1(), ($exprleqr.ast.location()).end2()), AstBinExpr.Oper.LEQ, $exprleql.ast, $exprleqr.ast);}
        | exprgeql = noviexpr2 GEQ exprgeqr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprgeqr.ast.location()).end1(), ($exprgeqr.ast.location()).end2()), AstBinExpr.Oper.GEQ, $exprgeql.ast, $exprgeqr.ast);}
        | exprandl = noviexpr2 AND exprandr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprandr.ast.location()).end1(), ($exprandr.ast.location()).end2()), AstBinExpr.Oper.AND, $exprandl.ast, $exprandr.ast);}
        | exprorl = noviexpr2 OR exprorr = noviexpr2 {$ast = new AstBinExpr(new Location($start.getLine(), $start.getCharPositionInLine(), ($exprorr.ast.location()).end1(), ($exprorr.ast.location()).end2()), AstBinExpr.Oper.OR, $exprorl.ast, $exprorr.ast);}
        | INTCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.INT, $INTCONST.text);}
        | BOOLCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.BOOL, $BOOLCONST.text);}
        | STRINGCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.STRING, $STRINGCONST.text);}
        | CHARCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.CHAR, $CHARCONST.text);}
        | POINTERCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.POINTER, $POINTERCONST.text);}
        | VOIDCONST {$ast = new AstAtomExpr(new Location($start.getLine(), $start.getCharPositionInLine(), $start.getLine(), $start.getCharPositionInLine() + $start.getText().length() - 1), AstAtomExpr.Type.VOID, $VOIDCONST.text);}
        ;


