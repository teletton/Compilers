// Generated from /home/iletavcioski/Documents/prev21/src/prev/phase/synan/PrevParser.g4 by ANTLR 4.8

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
	

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrevParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VOID=1, BOOLCONST=2, STRINGCONST=3, CHARCONST=4, WHITESPACE=5, COMMENT=6, 
		RETURN=7, LPAREN=8, RPAREN=9, LBRACKET=10, RBRACKET=11, LBRACE=12, RBRACE=13, 
		DOT=14, COMMA=15, SEMIC=16, COLON=17, AND=18, NOT=19, OR=20, EQU=21, NEQ=22, 
		LTH=23, GTH=24, LEQ=25, GEQ=26, ADD=27, SUB=28, MUL=29, DIV=30, MOD=31, 
		PTR=32, IS=33, CHAR=34, INT=35, BOOL=36, DEL=37, DO=38, ELSE=39, FUN=40, 
		IF=41, NEW=42, THEN=43, TYP=44, VAR=45, WHERE=46, WHILE=47, VOIDCONST=48, 
		POINTERCONST=49, IDENTIFIER=50, INTCONST=51, UNKNOWN=52;
	public static final int
		RULE_source = 0, RULE_decl = 1, RULE_type = 2, RULE_expr = 3, RULE_stmt = 4, 
		RULE_noviexpr = 5, RULE_noviexpr2 = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "decl", "type", "expr", "stmt", "noviexpr", "noviexpr2"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'void'", null, null, null, null, null, "'return'", "'('", "')'", 
			"'['", "']'", "'{'", "'}'", "'.'", "','", "';'", "':'", "'&'", "'!'", 
			"'|'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'+'", "'-'", "'*'", 
			"'/'", "'%'", "'^'", "'='", "'char'", "'int'", "'bool'", "'del'", "'do'", 
			"'else'", "'fun'", "'if'", "'new'", "'then'", "'typ'", "'var'", "'where'", 
			"'while'", "'none'", "'nil'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "VOID", "BOOLCONST", "STRINGCONST", "CHARCONST", "WHITESPACE", 
			"COMMENT", "RETURN", "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", "LBRACE", 
			"RBRACE", "DOT", "COMMA", "SEMIC", "COLON", "AND", "NOT", "OR", "EQU", 
			"NEQ", "LTH", "GTH", "LEQ", "GEQ", "ADD", "SUB", "MUL", "DIV", "MOD", 
			"PTR", "IS", "CHAR", "INT", "BOOL", "DEL", "DO", "ELSE", "FUN", "IF", 
			"NEW", "THEN", "TYP", "VAR", "WHERE", "WHILE", "VOIDCONST", "POINTERCONST", 
			"IDENTIFIER", "INTCONST", "UNKNOWN"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PrevParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PrevParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SourceContext extends ParserRuleContext {
		public AstTrees<AstDecl> ast;
		public DeclContext decl;
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_source);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			Vector<AstDecl> decls = new Vector<AstDecl>();
			setState(18); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(15);
				((SourceContext)_localctx).decl = decl();
				decls.add(((SourceContext)_localctx).decl.ast);
				}
				}
				setState(20); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUN) | (1L << TYP) | (1L << VAR))) != 0) );
			((SourceContext)_localctx).ast =  new AstTrees<AstDecl>(decls);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public AstDecl ast;
		public Token IDENTIFIER;
		public TypeContext type;
		public Token funName;
		public TypeContext type1;
		public TypeContext type2;
		public TypeContext funType;
		public ExprContext expr;
		public TerminalNode TYP() { return getToken(PrevParser.TYP, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public TerminalNode IS() { return getToken(PrevParser.IS, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode VAR() { return getToken(PrevParser.VAR, 0); }
		public List<TerminalNode> COLON() { return getTokens(PrevParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(PrevParser.COLON, i);
		}
		public TerminalNode FUN() { return getToken(PrevParser.FUN, 0); }
		public TerminalNode LPAREN() { return getToken(PrevParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PrevParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				match(TYP);
				setState(25);
				((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(26);
				match(IS);
				setState(27);
				((DeclContext)_localctx).type = type();
				((DeclContext)_localctx).ast =  new AstTypeDecl(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((DeclContext)_localctx).type.ast.location()).end1(), (((DeclContext)_localctx).type.ast.location()).end2()), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(30);
				match(VAR);
				setState(31);
				((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(32);
				match(COLON);
				setState(33);
				((DeclContext)_localctx).type = type();
				((DeclContext)_localctx).ast =  new AstVarDecl(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((DeclContext)_localctx).type.ast.location()).end1(), (((DeclContext)_localctx).type.ast.location()).end2()), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type.ast);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				Vector<AstParDecl> par = new Vector<AstParDecl>();
				setState(37);
				match(FUN);
				setState(38);
				((DeclContext)_localctx).funName = match(IDENTIFIER);
				setState(39);
				match(LPAREN);
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(40);
					((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					setState(41);
					match(COLON);
					setState(42);
					((DeclContext)_localctx).type1 = type();
					par.add(new AstParDecl(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((DeclContext)_localctx).type1.ast.location()).end1(), (((DeclContext)_localctx).type1.ast.location()).end2()), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type1.ast));
					setState(52);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(44);
						match(COMMA);
						setState(45);
						((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						setState(46);
						match(COLON);
						setState(47);
						((DeclContext)_localctx).type2 = type();
						par.add(new AstParDecl(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((DeclContext)_localctx).type2.ast.location()).end1(), (((DeclContext)_localctx).type2.ast.location()).end2()), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type2.ast));
						}
						}
						setState(54);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(57);
				match(RPAREN);
				setState(58);
				match(COLON);
				setState(59);
				((DeclContext)_localctx).funType = type();
				((DeclContext)_localctx).ast =  new AstFunDecl(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((DeclContext)_localctx).funType.ast.location()).end1(), (((DeclContext)_localctx).funType.ast.location()).end2()), (((DeclContext)_localctx).funName!=null?((DeclContext)_localctx).funName.getText():null), new AstTrees<AstParDecl>(par) , ((DeclContext)_localctx).funType.ast, null);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				Vector<AstParDecl> par = new Vector<AstParDecl>();
				setState(63);
				match(FUN);
				setState(64);
				((DeclContext)_localctx).funName = match(IDENTIFIER);
				setState(65);
				match(LPAREN);
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(66);
					((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					setState(67);
					match(COLON);
					setState(68);
					((DeclContext)_localctx).type1 = type();
					par.add(new AstParDecl(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((DeclContext)_localctx).type1.ast.location()).end1(), (((DeclContext)_localctx).type1.ast.location()).end2()), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type1.ast));
					setState(78);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(70);
						match(COMMA);
						setState(71);
						((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						setState(72);
						match(COLON);
						setState(73);
						((DeclContext)_localctx).type2 = type();
						par.add(new AstParDecl(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((DeclContext)_localctx).type2.ast.location()).end1(), (((DeclContext)_localctx).type2.ast.location()).end2()), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type2.ast));
						}
						}
						setState(80);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(83);
				match(RPAREN);
				setState(84);
				match(COLON);
				setState(85);
				((DeclContext)_localctx).funType = type();
				setState(86);
				match(IS);
				setState(87);
				((DeclContext)_localctx).expr = expr(0);
				((DeclContext)_localctx).ast =  new AstFunDecl(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((DeclContext)_localctx).expr.ast.location()).end1(), (((DeclContext)_localctx).expr.ast.location()).end2()), (((DeclContext)_localctx).funName!=null?((DeclContext)_localctx).funName.getText():null), new AstTrees<AstParDecl>(par) , ((DeclContext)_localctx).funType.ast, ((DeclContext)_localctx).expr.ast);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public AstType ast;
		public Token IDENTIFIER;
		public ExprContext exprtip2;
		public TypeContext type1;
		public TypeContext type2;
		public Token RBRACE;
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public TerminalNode LBRACKET() { return getToken(PrevParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(PrevParser.RBRACKET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode PTR() { return getToken(PrevParser.PTR, 0); }
		public TerminalNode LBRACE() { return getToken(PrevParser.LBRACE, 0); }
		public List<TerminalNode> COLON() { return getTokens(PrevParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(PrevParser.COLON, i);
		}
		public TerminalNode RBRACE() { return getToken(PrevParser.RBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public TerminalNode LPAREN() { return getToken(PrevParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PrevParser.RPAREN, 0); }
		public TerminalNode CHAR() { return getToken(PrevParser.CHAR, 0); }
		public TerminalNode INT() { return getToken(PrevParser.INT, 0); }
		public TerminalNode VOID() { return getToken(PrevParser.VOID, 0); }
		public TerminalNode BOOL() { return getToken(PrevParser.BOOL, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		int _la;
		try {
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				((TypeContext)_localctx).ast =  new AstNameType(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getText():null));
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				match(LBRACKET);
				setState(95);
				((TypeContext)_localctx).exprtip2 = expr(0);
				setState(96);
				match(RBRACKET);
				setState(97);
				((TypeContext)_localctx).type1 = type();
				((TypeContext)_localctx).ast =  new AstArrType(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((TypeContext)_localctx).type1.ast.location()).end1(), (((TypeContext)_localctx).type1.ast.location()).end2()), ((TypeContext)_localctx).type1.ast, ((TypeContext)_localctx).exprtip2.ast);
				}
				break;
			case PTR:
				enterOuterAlt(_localctx, 3);
				{
				setState(100);
				match(PTR);
				setState(101);
				((TypeContext)_localctx).type1 = type();
				((TypeContext)_localctx).ast =  new AstPtrType(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((TypeContext)_localctx).type1.ast.location()).end1(), (((TypeContext)_localctx).type1.ast.location()).end2()), ((TypeContext)_localctx).type1.ast);
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 4);
				{
				Vector<AstCompDecl> compdec = new Vector<AstCompDecl>();
				setState(105);
				match(LBRACE);
				setState(106);
				((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(107);
				match(COLON);
				setState(108);
				((TypeContext)_localctx).type1 = type();
				compdec.add(new AstCompDecl(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((TypeContext)_localctx).type1.ast.location()).end1(), (((TypeContext)_localctx).type1.ast.location()).end2()), (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getText():null), ((TypeContext)_localctx).type1.ast));
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(110);
					match(COMMA);
					setState(111);
					((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					setState(112);
					match(COLON);
					setState(113);
					((TypeContext)_localctx).type2 = type();
					compdec.add(new AstCompDecl(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((TypeContext)_localctx).type2.ast.location()).end1(), (((TypeContext)_localctx).type2.ast.location()).end2()), (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getText():null), ((TypeContext)_localctx).type2.ast));
					}
					}
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(121);
				((TypeContext)_localctx).RBRACE = match(RBRACE);
				((TypeContext)_localctx).ast =  new AstRecType(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), ((TypeContext)_localctx).RBRACE.getLine(), ((TypeContext)_localctx).RBRACE.getCharPositionInLine() + ((TypeContext)_localctx).RBRACE.getText().length() - 1), new AstTrees<AstCompDecl>(compdec));
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 5);
				{
				setState(124);
				match(LPAREN);
				setState(125);
				((TypeContext)_localctx).type1 = type();
				setState(126);
				match(RPAREN);
				((TypeContext)_localctx).ast =  ((TypeContext)_localctx).type1.ast;
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 6);
				{
				setState(129);
				match(CHAR);
				((TypeContext)_localctx).ast =  new AstAtomType(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomType.Type.CHAR);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 7);
				{
				setState(131);
				match(INT);
				((TypeContext)_localctx).ast =  new AstAtomType(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomType.Type.INT);
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 8);
				{
				setState(133);
				match(VOID);
				((TypeContext)_localctx).ast =  new AstAtomType(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomType.Type.VOID);
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 9);
				{
				setState(135);
				match(BOOL);
				((TypeContext)_localctx).ast =  new AstAtomType(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomType.Type.BOOL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public AstExpr ast;
		public ExprContext exprlb;
		public ExprContext exprptr;
		public ExprContext exprdot;
		public ExprContext exprmull;
		public ExprContext exprdivl;
		public ExprContext exprmodl;
		public ExprContext expraddl;
		public ExprContext exprsubl;
		public ExprContext exprequl;
		public ExprContext exprneql;
		public ExprContext exprlthl;
		public ExprContext exprgthl;
		public ExprContext exprleql;
		public ExprContext exprgeql;
		public ExprContext exprandl;
		public ExprContext exprorl;
		public StmtContext stm1;
		public StmtContext stm2;
		public Token RBRACE;
		public Token IDENTIFIER;
		public ExprContext exprpoc;
		public ExprContext exprost;
		public Token RPAREN;
		public ExprContext exprnew;
		public ExprContext exprdel;
		public ExprContext exprlp;
		public ExprContext exprcast;
		public TypeContext type;
		public ExprContext exprnot;
		public ExprContext exprad;
		public ExprContext exprsub;
		public Token PTR;
		public ExprContext exprpttr;
		public NoviexprContext whileexpr;
		public DeclContext wdecl;
		public Token INTCONST;
		public Token BOOLCONST;
		public Token STRINGCONST;
		public Token CHARCONST;
		public Token POINTERCONST;
		public Token VOIDCONST;
		public ExprContext exprmulr;
		public ExprContext exprdivr;
		public ExprContext exprmodr;
		public ExprContext expraddr;
		public ExprContext exprsubr;
		public ExprContext exprequr;
		public ExprContext exprneqr;
		public ExprContext exprlthr;
		public ExprContext exprgthr;
		public ExprContext exprleqr;
		public ExprContext exprgeqr;
		public ExprContext exprandr;
		public ExprContext exprorr;
		public ExprContext exprrb;
		public Token RBRACKET;
		public TerminalNode LBRACE() { return getToken(PrevParser.LBRACE, 0); }
		public List<TerminalNode> SEMIC() { return getTokens(PrevParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(PrevParser.SEMIC, i);
		}
		public TerminalNode RBRACE() { return getToken(PrevParser.RBRACE, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(PrevParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PrevParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public TerminalNode NEW() { return getToken(PrevParser.NEW, 0); }
		public TerminalNode DEL() { return getToken(PrevParser.DEL, 0); }
		public TerminalNode COLON() { return getToken(PrevParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NOT() { return getToken(PrevParser.NOT, 0); }
		public TerminalNode ADD() { return getToken(PrevParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(PrevParser.SUB, 0); }
		public TerminalNode PTR() { return getToken(PrevParser.PTR, 0); }
		public TerminalNode WHERE() { return getToken(PrevParser.WHERE, 0); }
		public NoviexprContext noviexpr() {
			return getRuleContext(NoviexprContext.class,0);
		}
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public TerminalNode INTCONST() { return getToken(PrevParser.INTCONST, 0); }
		public TerminalNode BOOLCONST() { return getToken(PrevParser.BOOLCONST, 0); }
		public TerminalNode STRINGCONST() { return getToken(PrevParser.STRINGCONST, 0); }
		public TerminalNode CHARCONST() { return getToken(PrevParser.CHARCONST, 0); }
		public TerminalNode POINTERCONST() { return getToken(PrevParser.POINTERCONST, 0); }
		public TerminalNode VOIDCONST() { return getToken(PrevParser.VOIDCONST, 0); }
		public TerminalNode MUL() { return getToken(PrevParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(PrevParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(PrevParser.MOD, 0); }
		public TerminalNode EQU() { return getToken(PrevParser.EQU, 0); }
		public TerminalNode NEQ() { return getToken(PrevParser.NEQ, 0); }
		public TerminalNode LTH() { return getToken(PrevParser.LTH, 0); }
		public TerminalNode GTH() { return getToken(PrevParser.GTH, 0); }
		public TerminalNode LEQ() { return getToken(PrevParser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(PrevParser.GEQ, 0); }
		public TerminalNode AND() { return getToken(PrevParser.AND, 0); }
		public TerminalNode OR() { return getToken(PrevParser.OR, 0); }
		public TerminalNode LBRACKET() { return getToken(PrevParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(PrevParser.RBRACKET, 0); }
		public TerminalNode DOT() { return getToken(PrevParser.DOT, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				Vector<AstStmt> stm = new Vector<AstStmt>();
				setState(141);
				match(LBRACE);
				setState(142);
				((ExprContext)_localctx).stm1 = stmt();
				stm.add(((ExprContext)_localctx).stm1.ast);
				setState(144);
				match(SEMIC);
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLCONST) | (1L << STRINGCONST) | (1L << CHARCONST) | (1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << ADD) | (1L << SUB) | (1L << PTR) | (1L << DEL) | (1L << IF) | (1L << NEW) | (1L << WHILE) | (1L << VOIDCONST) | (1L << POINTERCONST) | (1L << IDENTIFIER) | (1L << INTCONST))) != 0)) {
					{
					{
					setState(145);
					((ExprContext)_localctx).stm2 = stmt();
					stm.add(((ExprContext)_localctx).stm2.ast);
					setState(147);
					match(SEMIC);
					}
					}
					setState(153);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(154);
				((ExprContext)_localctx).RBRACE = match(RBRACE);
				((ExprContext)_localctx).ast =  new AstStmtExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), ((ExprContext)_localctx).RBRACE.getLine(), ((ExprContext)_localctx).RBRACE.getCharPositionInLine() + ((ExprContext)_localctx).RBRACE.getText().length() - 1), new AstTrees<AstStmt>(stm));
				}
				break;
			case 2:
				{
				Vector<AstExpr> exp = new Vector<AstExpr>();
				setState(158);
				((ExprContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(159);
				match(LPAREN);
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLCONST) | (1L << STRINGCONST) | (1L << CHARCONST) | (1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << ADD) | (1L << SUB) | (1L << PTR) | (1L << DEL) | (1L << NEW) | (1L << VOIDCONST) | (1L << POINTERCONST) | (1L << IDENTIFIER) | (1L << INTCONST))) != 0)) {
					{
					setState(160);
					((ExprContext)_localctx).exprpoc = expr(0);
					exp.add(((ExprContext)_localctx).exprpoc.ast);
					setState(168);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(162);
						match(COMMA);
						setState(163);
						((ExprContext)_localctx).exprost = expr(0);
						exp.add(((ExprContext)_localctx).exprost.ast);
						}
						}
						setState(170);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(173);
				((ExprContext)_localctx).RPAREN = match(RPAREN);
				((ExprContext)_localctx).ast =  new AstCallExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), ((ExprContext)_localctx).RPAREN.getLine(), ((ExprContext)_localctx).RPAREN.getCharPositionInLine() + ((ExprContext)_localctx).RPAREN.getText().length() - 1), (((ExprContext)_localctx).IDENTIFIER!=null?((ExprContext)_localctx).IDENTIFIER.getText():null), new AstTrees<AstExpr>(exp));
				}
				break;
			case 3:
				{
				setState(175);
				match(NEW);
				setState(176);
				((ExprContext)_localctx).exprnew = expr(32);
				((ExprContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprnew.ast.location()).end1(), (((ExprContext)_localctx).exprnew.ast.location()).end2()), AstPfxExpr.Oper.NEW, ((ExprContext)_localctx).exprnew.ast);
				}
				break;
			case 4:
				{
				setState(179);
				match(DEL);
				setState(180);
				((ExprContext)_localctx).exprdel = expr(31);
				((ExprContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprdel.ast.location()).end1(), (((ExprContext)_localctx).exprdel.ast.location()).end2()), AstPfxExpr.Oper.DEL, ((ExprContext)_localctx).exprdel.ast);
				}
				break;
			case 5:
				{
				setState(183);
				match(LPAREN);
				setState(184);
				((ExprContext)_localctx).exprlp = expr(0);
				setState(185);
				((ExprContext)_localctx).RPAREN = match(RPAREN);
				((ExprContext)_localctx).ast =  ((ExprContext)_localctx).exprlp.ast;
				}
				break;
			case 6:
				{
				setState(188);
				match(LPAREN);
				setState(189);
				((ExprContext)_localctx).exprcast = expr(0);
				setState(190);
				match(COLON);
				setState(191);
				((ExprContext)_localctx).type = type();
				setState(192);
				((ExprContext)_localctx).RPAREN = match(RPAREN);
				((ExprContext)_localctx).ast =  new AstCastExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), ((ExprContext)_localctx).RPAREN.getCharPositionInLine() + ((ExprContext)_localctx).RPAREN.getText().length() - 1), ((ExprContext)_localctx).exprcast.ast, ((ExprContext)_localctx).type.ast);
				}
				break;
			case 7:
				{
				setState(195);
				match(NOT);
				setState(196);
				((ExprContext)_localctx).exprnot = expr(25);
				((ExprContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprnot.ast.location()).end1(), (((ExprContext)_localctx).exprnot.ast.location()).end2()), AstPfxExpr.Oper.NOT, ((ExprContext)_localctx).exprnot.ast);
				}
				break;
			case 8:
				{
				setState(199);
				match(ADD);
				setState(200);
				((ExprContext)_localctx).exprad = expr(24);
				((ExprContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprad.ast.location()).end1(), (((ExprContext)_localctx).exprad.ast.location()).end2()), AstPfxExpr.Oper.ADD, ((ExprContext)_localctx).exprad.ast);
				}
				break;
			case 9:
				{
				setState(203);
				match(SUB);
				setState(204);
				((ExprContext)_localctx).exprsub = expr(23);
				((ExprContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprsub.ast.location()).end1(), (((ExprContext)_localctx).exprsub.ast.location()).end2()), AstPfxExpr.Oper.SUB, ((ExprContext)_localctx).exprsub.ast);
				}
				break;
			case 10:
				{
				setState(207);
				((ExprContext)_localctx).PTR = match(PTR);
				setState(208);
				((ExprContext)_localctx).exprpttr = expr(22);
				((ExprContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprpttr.ast.location()).end1(), (((ExprContext)_localctx).exprpttr.ast.location()).end2()), AstPfxExpr.Oper.PTR, ((ExprContext)_localctx).exprpttr.ast);
				}
				break;
			case 11:
				{
				Vector<AstDecl> decls = new Vector<AstDecl>();
				setState(212);
				((ExprContext)_localctx).whileexpr = noviexpr(0);
				setState(213);
				match(WHERE);
				setState(214);
				match(LBRACE);
				setState(218); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(215);
					((ExprContext)_localctx).wdecl = decl();
					decls.add(((ExprContext)_localctx).wdecl.ast);
					}
					}
					setState(220); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUN) | (1L << TYP) | (1L << VAR))) != 0) );
				setState(222);
				((ExprContext)_localctx).RBRACE = match(RBRACE);
				((ExprContext)_localctx).ast =  new AstWhereExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), ((ExprContext)_localctx).RBRACE.getLine(), ((ExprContext)_localctx).RBRACE.getCharPositionInLine() + ((ExprContext)_localctx).RBRACE.getText().length() - 1), ((ExprContext)_localctx).whileexpr.ast, new AstTrees<AstDecl>(decls));
				}
				break;
			case 12:
				{
				setState(225);
				((ExprContext)_localctx).INTCONST = match(INTCONST);
				((ExprContext)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.INT, (((ExprContext)_localctx).INTCONST!=null?((ExprContext)_localctx).INTCONST.getText():null));
				}
				break;
			case 13:
				{
				setState(227);
				((ExprContext)_localctx).BOOLCONST = match(BOOLCONST);
				((ExprContext)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.BOOL, (((ExprContext)_localctx).BOOLCONST!=null?((ExprContext)_localctx).BOOLCONST.getText():null));
				}
				break;
			case 14:
				{
				setState(229);
				((ExprContext)_localctx).STRINGCONST = match(STRINGCONST);
				((ExprContext)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.STRING, (((ExprContext)_localctx).STRINGCONST!=null?((ExprContext)_localctx).STRINGCONST.getText():null));
				}
				break;
			case 15:
				{
				setState(231);
				((ExprContext)_localctx).CHARCONST = match(CHARCONST);
				((ExprContext)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.CHAR, (((ExprContext)_localctx).CHARCONST!=null?((ExprContext)_localctx).CHARCONST.getText():null));
				}
				break;
			case 16:
				{
				setState(233);
				((ExprContext)_localctx).POINTERCONST = match(POINTERCONST);
				((ExprContext)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.POINTER, (((ExprContext)_localctx).POINTERCONST!=null?((ExprContext)_localctx).POINTERCONST.getText():null));
				}
				break;
			case 17:
				{
				setState(235);
				((ExprContext)_localctx).VOIDCONST = match(VOIDCONST);
				((ExprContext)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.VOID, (((ExprContext)_localctx).VOIDCONST!=null?((ExprContext)_localctx).VOIDCONST.getText():null));
				}
				break;
			case 18:
				{
				setState(237);
				((ExprContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				((ExprContext)_localctx).ast =  new AstNameExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1),(((ExprContext)_localctx).IDENTIFIER!=null?((ExprContext)_localctx).IDENTIFIER.getText():null));
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(321);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(319);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprmull = _prevctx;
						_localctx.exprmull = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(241);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(242);
						match(MUL);
						setState(243);
						((ExprContext)_localctx).exprmulr = expr(22);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprmulr.ast.location()).end1(), (((ExprContext)_localctx).exprmulr.ast.location()).end2()), AstBinExpr.Oper.MUL, ((ExprContext)_localctx).exprmull.ast, ((ExprContext)_localctx).exprmulr.ast);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprdivl = _prevctx;
						_localctx.exprdivl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(246);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(247);
						match(DIV);
						setState(248);
						((ExprContext)_localctx).exprdivr = expr(21);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprdivr.ast.location()).end1(), (((ExprContext)_localctx).exprdivr.ast.location()).end2()), AstBinExpr.Oper.DIV, ((ExprContext)_localctx).exprdivl.ast, ((ExprContext)_localctx).exprdivr.ast);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprmodl = _prevctx;
						_localctx.exprmodl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(251);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(252);
						match(MOD);
						setState(253);
						((ExprContext)_localctx).exprmodr = expr(20);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprmodr.ast.location()).end1(), (((ExprContext)_localctx).exprmodr.ast.location()).end2()), AstBinExpr.Oper.MOD, ((ExprContext)_localctx).exprmodl.ast, ((ExprContext)_localctx).exprmodr.ast);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.expraddl = _prevctx;
						_localctx.expraddl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(256);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(257);
						match(ADD);
						setState(258);
						((ExprContext)_localctx).expraddr = expr(19);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).expraddr.ast.location()).end1(), (((ExprContext)_localctx).expraddr.ast.location()).end2()), AstBinExpr.Oper.ADD, ((ExprContext)_localctx).expraddl.ast, ((ExprContext)_localctx).expraddr.ast);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprsubl = _prevctx;
						_localctx.exprsubl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(261);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(262);
						match(SUB);
						setState(263);
						((ExprContext)_localctx).exprsubr = expr(18);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprsubr.ast.location()).end1(), (((ExprContext)_localctx).exprsubr.ast.location()).end2()), AstBinExpr.Oper.SUB, ((ExprContext)_localctx).exprsubl.ast, ((ExprContext)_localctx).exprsubr.ast);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprequl = _prevctx;
						_localctx.exprequl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(266);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(267);
						match(EQU);
						setState(268);
						((ExprContext)_localctx).exprequr = expr(17);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprequr.ast.location()).end1(), (((ExprContext)_localctx).exprequr.ast.location()).end2()), AstBinExpr.Oper.EQU, ((ExprContext)_localctx).exprequl.ast, ((ExprContext)_localctx).exprequr.ast);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprneql = _prevctx;
						_localctx.exprneql = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(271);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(272);
						match(NEQ);
						setState(273);
						((ExprContext)_localctx).exprneqr = expr(16);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprneqr.ast.location()).end1(), (((ExprContext)_localctx).exprneqr.ast.location()).end2()), AstBinExpr.Oper.NEQ, ((ExprContext)_localctx).exprneql.ast, ((ExprContext)_localctx).exprneqr.ast);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprlthl = _prevctx;
						_localctx.exprlthl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(276);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(277);
						match(LTH);
						setState(278);
						((ExprContext)_localctx).exprlthr = expr(15);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprlthr.ast.location()).end1(), (((ExprContext)_localctx).exprlthr.ast.location()).end2()), AstBinExpr.Oper.LTH, ((ExprContext)_localctx).exprlthl.ast, ((ExprContext)_localctx).exprlthr.ast);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprgthl = _prevctx;
						_localctx.exprgthl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(281);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(282);
						match(GTH);
						setState(283);
						((ExprContext)_localctx).exprgthr = expr(14);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprgthr.ast.location()).end1(), (((ExprContext)_localctx).exprgthr.ast.location()).end2()), AstBinExpr.Oper.GTH, ((ExprContext)_localctx).exprgthl.ast, ((ExprContext)_localctx).exprgthr.ast);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprleql = _prevctx;
						_localctx.exprleql = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(286);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(287);
						match(LEQ);
						setState(288);
						((ExprContext)_localctx).exprleqr = expr(13);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprleqr.ast.location()).end1(), (((ExprContext)_localctx).exprleqr.ast.location()).end2()), AstBinExpr.Oper.LEQ, ((ExprContext)_localctx).exprleql.ast, ((ExprContext)_localctx).exprleqr.ast);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprgeql = _prevctx;
						_localctx.exprgeql = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(291);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(292);
						match(GEQ);
						setState(293);
						((ExprContext)_localctx).exprgeqr = expr(12);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprgeqr.ast.location()).end1(), (((ExprContext)_localctx).exprgeqr.ast.location()).end2()), AstBinExpr.Oper.GEQ, ((ExprContext)_localctx).exprgeql.ast, ((ExprContext)_localctx).exprgeqr.ast);
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprandl = _prevctx;
						_localctx.exprandl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(296);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(297);
						match(AND);
						setState(298);
						((ExprContext)_localctx).exprandr = expr(11);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprandr.ast.location()).end1(), (((ExprContext)_localctx).exprandr.ast.location()).end2()), AstBinExpr.Oper.AND, ((ExprContext)_localctx).exprandl.ast, ((ExprContext)_localctx).exprandr.ast);
						}
						break;
					case 13:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprorl = _prevctx;
						_localctx.exprorl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(301);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(302);
						match(OR);
						setState(303);
						((ExprContext)_localctx).exprorr = expr(10);
						((ExprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprorr.ast.location()).end1(), (((ExprContext)_localctx).exprorr.ast.location()).end2()), AstBinExpr.Oper.OR, ((ExprContext)_localctx).exprorl.ast, ((ExprContext)_localctx).exprorr.ast);
						}
						break;
					case 14:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprlb = _prevctx;
						_localctx.exprlb = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(306);
						if (!(precpred(_ctx, 28))) throw new FailedPredicateException(this, "precpred(_ctx, 28)");
						setState(307);
						match(LBRACKET);
						setState(308);
						((ExprContext)_localctx).exprrb = expr(0);
						setState(309);
						((ExprContext)_localctx).RBRACKET = match(RBRACKET);
						((ExprContext)_localctx).ast =  new AstArrExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), ((ExprContext)_localctx).RBRACKET.getCharPositionInLine() + ((ExprContext)_localctx).RBRACKET.getText().length() - 1), ((ExprContext)_localctx).exprlb.ast, ((ExprContext)_localctx).exprrb.ast);
						}
						break;
					case 15:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprptr = _prevctx;
						_localctx.exprptr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(312);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(313);
						((ExprContext)_localctx).PTR = match(PTR);
						((ExprContext)_localctx).ast =  new AstSfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), ((ExprContext)_localctx).PTR.getCharPositionInLine() + ((ExprContext)_localctx).PTR.getText().length() - 1), AstSfxExpr.Oper.PTR, ((ExprContext)_localctx).exprptr.ast);
						}
						break;
					case 16:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprdot = _prevctx;
						_localctx.exprdot = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(315);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(316);
						match(DOT);
						setState(317);
						((ExprContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						((ExprContext)_localctx).ast =  new AstRecExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((ExprContext)_localctx).exprdot.ast.location()).end1(), (((ExprContext)_localctx).exprdot.ast.location()).end2()), ((ExprContext)_localctx).exprdot.ast, new AstNameExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), ((ExprContext)_localctx).IDENTIFIER.getLine(), ((ExprContext)_localctx).IDENTIFIER.getCharPositionInLine() + ((ExprContext)_localctx).IDENTIFIER.getText().length() - 1), (((ExprContext)_localctx).IDENTIFIER!=null?((ExprContext)_localctx).IDENTIFIER.getText():null)));
						}
						break;
					}
					} 
				}
				setState(323);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public AstStmt ast;
		public ExprContext exprstmt;
		public ExprContext exprassto;
		public ExprContext exprass;
		public ExprContext ifexpr;
		public StmtContext thenstmt;
		public StmtContext elsestmt;
		public ExprContext whileexpr;
		public StmtContext dostmt;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode IS() { return getToken(PrevParser.IS, 0); }
		public TerminalNode IF() { return getToken(PrevParser.IF, 0); }
		public TerminalNode THEN() { return getToken(PrevParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(PrevParser.ELSE, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode WHILE() { return getToken(PrevParser.WHILE, 0); }
		public TerminalNode DO() { return getToken(PrevParser.DO, 0); }
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_stmt);
		try {
			setState(346);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(324);
				((StmtContext)_localctx).exprstmt = expr(0);
				((StmtContext)_localctx).ast =  new AstExprStmt(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((StmtContext)_localctx).exprstmt.ast.location()).end1(), (((StmtContext)_localctx).exprstmt.ast.location()).end2()), ((StmtContext)_localctx).exprstmt.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(327);
				((StmtContext)_localctx).exprassto = expr(0);
				setState(328);
				match(IS);
				setState(329);
				((StmtContext)_localctx).exprass = expr(0);
				((StmtContext)_localctx).ast =  new AstAssignStmt(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((StmtContext)_localctx).exprass.ast.location()).end1(), (((StmtContext)_localctx).exprass.ast.location()).end2()), ((StmtContext)_localctx).exprassto.ast, ((StmtContext)_localctx).exprass.ast);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(332);
				match(IF);
				setState(333);
				((StmtContext)_localctx).ifexpr = expr(0);
				setState(334);
				match(THEN);
				setState(335);
				((StmtContext)_localctx).thenstmt = stmt();
				setState(336);
				match(ELSE);
				setState(337);
				((StmtContext)_localctx).elsestmt = stmt();
				((StmtContext)_localctx).ast =  new AstIfStmt(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((StmtContext)_localctx).elsestmt.ast.location()).end1(), (((StmtContext)_localctx).elsestmt.ast.location()).end2()), ((StmtContext)_localctx).ifexpr.ast, ((StmtContext)_localctx).thenstmt.ast, ((StmtContext)_localctx).elsestmt.ast);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(340);
				match(WHILE);
				setState(341);
				((StmtContext)_localctx).whileexpr = expr(0);
				setState(342);
				match(DO);
				setState(343);
				((StmtContext)_localctx).dostmt = stmt();
				((StmtContext)_localctx).ast =  new AstWhileStmt(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((StmtContext)_localctx).dostmt.ast.location()).end1(), (((StmtContext)_localctx).dostmt.ast.location()).end2()), ((StmtContext)_localctx).whileexpr.ast, ((StmtContext)_localctx).dostmt.ast);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NoviexprContext extends ParserRuleContext {
		public AstExpr ast;
		public NoviexprContext exprlb;
		public NoviexprContext exprptr;
		public NoviexprContext exprdot;
		public NoviexprContext exprmull;
		public NoviexprContext exprdivl;
		public NoviexprContext exprmodl;
		public NoviexprContext expraddl;
		public NoviexprContext exprsubl;
		public NoviexprContext exprequl;
		public NoviexprContext exprneql;
		public NoviexprContext exprlthl;
		public NoviexprContext exprgthl;
		public NoviexprContext exprleql;
		public NoviexprContext exprgeql;
		public NoviexprContext exprandl;
		public NoviexprContext exprorl;
		public StmtContext stm1;
		public StmtContext stm2;
		public Token RBRACE;
		public Token IDENTIFIER;
		public NoviexprContext exprpoc;
		public NoviexprContext exprost;
		public Token RPAREN;
		public NoviexprContext exprnew;
		public NoviexprContext exprdel;
		public NoviexprContext exprlp;
		public NoviexprContext exprcast;
		public TypeContext type;
		public NoviexprContext exprnot;
		public NoviexprContext exprad;
		public NoviexprContext exprsub;
		public Token PTR;
		public NoviexprContext exprpttr;
		public Noviexpr2Context whileexpr;
		public DeclContext wdecl;
		public Token INTCONST;
		public Token BOOLCONST;
		public Token STRINGCONST;
		public Token CHARCONST;
		public Token POINTERCONST;
		public Token VOIDCONST;
		public NoviexprContext exprmulr;
		public NoviexprContext exprdivr;
		public NoviexprContext exprmodr;
		public NoviexprContext expraddr;
		public NoviexprContext exprsubr;
		public NoviexprContext exprequr;
		public NoviexprContext exprneqr;
		public NoviexprContext exprlthr;
		public NoviexprContext exprgthr;
		public NoviexprContext exprleqr;
		public NoviexprContext exprgeqr;
		public NoviexprContext exprandr;
		public NoviexprContext exprorr;
		public NoviexprContext exprrb;
		public Token RBRACKET;
		public TerminalNode LBRACE() { return getToken(PrevParser.LBRACE, 0); }
		public List<TerminalNode> SEMIC() { return getTokens(PrevParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(PrevParser.SEMIC, i);
		}
		public TerminalNode RBRACE() { return getToken(PrevParser.RBRACE, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(PrevParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PrevParser.RPAREN, 0); }
		public List<NoviexprContext> noviexpr() {
			return getRuleContexts(NoviexprContext.class);
		}
		public NoviexprContext noviexpr(int i) {
			return getRuleContext(NoviexprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public TerminalNode NEW() { return getToken(PrevParser.NEW, 0); }
		public TerminalNode DEL() { return getToken(PrevParser.DEL, 0); }
		public TerminalNode COLON() { return getToken(PrevParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NOT() { return getToken(PrevParser.NOT, 0); }
		public TerminalNode ADD() { return getToken(PrevParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(PrevParser.SUB, 0); }
		public TerminalNode PTR() { return getToken(PrevParser.PTR, 0); }
		public TerminalNode WHERE() { return getToken(PrevParser.WHERE, 0); }
		public Noviexpr2Context noviexpr2() {
			return getRuleContext(Noviexpr2Context.class,0);
		}
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public TerminalNode INTCONST() { return getToken(PrevParser.INTCONST, 0); }
		public TerminalNode BOOLCONST() { return getToken(PrevParser.BOOLCONST, 0); }
		public TerminalNode STRINGCONST() { return getToken(PrevParser.STRINGCONST, 0); }
		public TerminalNode CHARCONST() { return getToken(PrevParser.CHARCONST, 0); }
		public TerminalNode POINTERCONST() { return getToken(PrevParser.POINTERCONST, 0); }
		public TerminalNode VOIDCONST() { return getToken(PrevParser.VOIDCONST, 0); }
		public TerminalNode MUL() { return getToken(PrevParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(PrevParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(PrevParser.MOD, 0); }
		public TerminalNode EQU() { return getToken(PrevParser.EQU, 0); }
		public TerminalNode NEQ() { return getToken(PrevParser.NEQ, 0); }
		public TerminalNode LTH() { return getToken(PrevParser.LTH, 0); }
		public TerminalNode GTH() { return getToken(PrevParser.GTH, 0); }
		public TerminalNode LEQ() { return getToken(PrevParser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(PrevParser.GEQ, 0); }
		public TerminalNode AND() { return getToken(PrevParser.AND, 0); }
		public TerminalNode OR() { return getToken(PrevParser.OR, 0); }
		public TerminalNode LBRACKET() { return getToken(PrevParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(PrevParser.RBRACKET, 0); }
		public TerminalNode DOT() { return getToken(PrevParser.DOT, 0); }
		public NoviexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noviexpr; }
	}

	public final NoviexprContext noviexpr() throws RecognitionException {
		return noviexpr(0);
	}

	private NoviexprContext noviexpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		NoviexprContext _localctx = new NoviexprContext(_ctx, _parentState);
		NoviexprContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_noviexpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(448);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				Vector<AstStmt> stm = new Vector<AstStmt>();
				setState(350);
				match(LBRACE);
				setState(351);
				((NoviexprContext)_localctx).stm1 = stmt();
				stm.add(((NoviexprContext)_localctx).stm1.ast);
				setState(353);
				match(SEMIC);
				setState(360);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLCONST) | (1L << STRINGCONST) | (1L << CHARCONST) | (1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << ADD) | (1L << SUB) | (1L << PTR) | (1L << DEL) | (1L << IF) | (1L << NEW) | (1L << WHILE) | (1L << VOIDCONST) | (1L << POINTERCONST) | (1L << IDENTIFIER) | (1L << INTCONST))) != 0)) {
					{
					{
					setState(354);
					((NoviexprContext)_localctx).stm2 = stmt();
					stm.add(((NoviexprContext)_localctx).stm2.ast);
					setState(356);
					match(SEMIC);
					}
					}
					setState(362);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(363);
				((NoviexprContext)_localctx).RBRACE = match(RBRACE);
				((NoviexprContext)_localctx).ast =  new AstStmtExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), ((NoviexprContext)_localctx).RBRACE.getLine(), ((NoviexprContext)_localctx).RBRACE.getCharPositionInLine() + ((NoviexprContext)_localctx).RBRACE.getText().length() - 1), new AstTrees<AstStmt>(stm));
				}
				break;
			case 2:
				{
				setState(366);
				((NoviexprContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				((NoviexprContext)_localctx).ast =  new AstNameExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1),(((NoviexprContext)_localctx).IDENTIFIER!=null?((NoviexprContext)_localctx).IDENTIFIER.getText():null));
				}
				break;
			case 3:
				{
				Vector<AstExpr> exp = new Vector<AstExpr>();
				setState(369);
				((NoviexprContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(370);
				match(LPAREN);
				setState(382);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLCONST) | (1L << STRINGCONST) | (1L << CHARCONST) | (1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << ADD) | (1L << SUB) | (1L << PTR) | (1L << DEL) | (1L << NEW) | (1L << VOIDCONST) | (1L << POINTERCONST) | (1L << IDENTIFIER) | (1L << INTCONST))) != 0)) {
					{
					setState(371);
					((NoviexprContext)_localctx).exprpoc = noviexpr(0);
					exp.add(((NoviexprContext)_localctx).exprpoc.ast);
					setState(379);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(373);
						match(COMMA);
						setState(374);
						((NoviexprContext)_localctx).exprost = noviexpr(0);
						exp.add(((NoviexprContext)_localctx).exprost.ast);
						}
						}
						setState(381);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(384);
				((NoviexprContext)_localctx).RPAREN = match(RPAREN);
				((NoviexprContext)_localctx).ast =  new AstCallExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), ((NoviexprContext)_localctx).RPAREN.getLine(), ((NoviexprContext)_localctx).RPAREN.getCharPositionInLine() + ((NoviexprContext)_localctx).RPAREN.getText().length() - 1), (((NoviexprContext)_localctx).IDENTIFIER!=null?((NoviexprContext)_localctx).IDENTIFIER.getText():null), new AstTrees<AstExpr>(exp));
				}
				break;
			case 4:
				{
				setState(386);
				match(NEW);
				setState(387);
				((NoviexprContext)_localctx).exprnew = noviexpr(31);
				((NoviexprContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprnew.ast.location()).end1(), (((NoviexprContext)_localctx).exprnew.ast.location()).end2()), AstPfxExpr.Oper.NEW, ((NoviexprContext)_localctx).exprnew.ast);
				}
				break;
			case 5:
				{
				setState(390);
				match(DEL);
				setState(391);
				((NoviexprContext)_localctx).exprdel = noviexpr(30);
				((NoviexprContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprdel.ast.location()).end1(), (((NoviexprContext)_localctx).exprdel.ast.location()).end2()), AstPfxExpr.Oper.DEL, ((NoviexprContext)_localctx).exprdel.ast);
				}
				break;
			case 6:
				{
				setState(394);
				match(LPAREN);
				setState(395);
				((NoviexprContext)_localctx).exprlp = noviexpr(0);
				setState(396);
				((NoviexprContext)_localctx).RPAREN = match(RPAREN);
				((NoviexprContext)_localctx).ast =  ((NoviexprContext)_localctx).exprlp.ast;
				}
				break;
			case 7:
				{
				setState(399);
				match(LPAREN);
				setState(400);
				((NoviexprContext)_localctx).exprcast = noviexpr(0);
				setState(401);
				match(COLON);
				setState(402);
				((NoviexprContext)_localctx).type = type();
				setState(403);
				((NoviexprContext)_localctx).RPAREN = match(RPAREN);
				((NoviexprContext)_localctx).ast =  new AstCastExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), ((NoviexprContext)_localctx).RPAREN.getCharPositionInLine() + ((NoviexprContext)_localctx).RPAREN.getText().length() - 1), ((NoviexprContext)_localctx).exprcast.ast, ((NoviexprContext)_localctx).type.ast);
				}
				break;
			case 8:
				{
				setState(406);
				match(NOT);
				setState(407);
				((NoviexprContext)_localctx).exprnot = noviexpr(24);
				((NoviexprContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprnot.ast.location()).end1(), (((NoviexprContext)_localctx).exprnot.ast.location()).end2()), AstPfxExpr.Oper.NOT, ((NoviexprContext)_localctx).exprnot.ast);
				}
				break;
			case 9:
				{
				setState(410);
				match(ADD);
				setState(411);
				((NoviexprContext)_localctx).exprad = noviexpr(23);
				((NoviexprContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprad.ast.location()).end1(), (((NoviexprContext)_localctx).exprad.ast.location()).end2()), AstPfxExpr.Oper.ADD, ((NoviexprContext)_localctx).exprad.ast);
				}
				break;
			case 10:
				{
				setState(414);
				match(SUB);
				setState(415);
				((NoviexprContext)_localctx).exprsub = noviexpr(22);
				((NoviexprContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprsub.ast.location()).end1(), (((NoviexprContext)_localctx).exprsub.ast.location()).end2()), AstPfxExpr.Oper.SUB, ((NoviexprContext)_localctx).exprsub.ast);
				}
				break;
			case 11:
				{
				setState(418);
				((NoviexprContext)_localctx).PTR = match(PTR);
				setState(419);
				((NoviexprContext)_localctx).exprpttr = noviexpr(21);
				((NoviexprContext)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprpttr.ast.location()).end1(), (((NoviexprContext)_localctx).exprpttr.ast.location()).end2()), AstPfxExpr.Oper.PTR, ((NoviexprContext)_localctx).exprpttr.ast);
				}
				break;
			case 12:
				{
				Vector<AstDecl> decls = new Vector<AstDecl>();
				setState(423);
				((NoviexprContext)_localctx).whileexpr = noviexpr2(0);
				setState(424);
				match(WHERE);
				setState(425);
				match(LBRACE);
				setState(429); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(426);
					((NoviexprContext)_localctx).wdecl = decl();
					decls.add(((NoviexprContext)_localctx).wdecl.ast);
					}
					}
					setState(431); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUN) | (1L << TYP) | (1L << VAR))) != 0) );
				setState(433);
				((NoviexprContext)_localctx).RBRACE = match(RBRACE);
				((NoviexprContext)_localctx).ast =  new AstWhereExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), ((NoviexprContext)_localctx).RBRACE.getLine(), ((NoviexprContext)_localctx).RBRACE.getCharPositionInLine() + ((NoviexprContext)_localctx).RBRACE.getText().length() - 1), ((NoviexprContext)_localctx).whileexpr.ast, new AstTrees<AstDecl>(decls));
				}
				break;
			case 13:
				{
				setState(436);
				((NoviexprContext)_localctx).INTCONST = match(INTCONST);
				((NoviexprContext)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.INT, (((NoviexprContext)_localctx).INTCONST!=null?((NoviexprContext)_localctx).INTCONST.getText():null));
				}
				break;
			case 14:
				{
				setState(438);
				((NoviexprContext)_localctx).BOOLCONST = match(BOOLCONST);
				((NoviexprContext)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.BOOL, (((NoviexprContext)_localctx).BOOLCONST!=null?((NoviexprContext)_localctx).BOOLCONST.getText():null));
				}
				break;
			case 15:
				{
				setState(440);
				((NoviexprContext)_localctx).STRINGCONST = match(STRINGCONST);
				((NoviexprContext)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.STRING, (((NoviexprContext)_localctx).STRINGCONST!=null?((NoviexprContext)_localctx).STRINGCONST.getText():null));
				}
				break;
			case 16:
				{
				setState(442);
				((NoviexprContext)_localctx).CHARCONST = match(CHARCONST);
				((NoviexprContext)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.CHAR, (((NoviexprContext)_localctx).CHARCONST!=null?((NoviexprContext)_localctx).CHARCONST.getText():null));
				}
				break;
			case 17:
				{
				setState(444);
				((NoviexprContext)_localctx).POINTERCONST = match(POINTERCONST);
				((NoviexprContext)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.POINTER, (((NoviexprContext)_localctx).POINTERCONST!=null?((NoviexprContext)_localctx).POINTERCONST.getText():null));
				}
				break;
			case 18:
				{
				setState(446);
				((NoviexprContext)_localctx).VOIDCONST = match(VOIDCONST);
				((NoviexprContext)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.VOID, (((NoviexprContext)_localctx).VOIDCONST!=null?((NoviexprContext)_localctx).VOIDCONST.getText():null));
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(530);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(528);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprmull = _prevctx;
						_localctx.exprmull = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(450);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(451);
						match(MUL);
						setState(452);
						((NoviexprContext)_localctx).exprmulr = noviexpr(21);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprmulr.ast.location()).end1(), (((NoviexprContext)_localctx).exprmulr.ast.location()).end2()), AstBinExpr.Oper.MUL, ((NoviexprContext)_localctx).exprmull.ast, ((NoviexprContext)_localctx).exprmulr.ast);
						}
						break;
					case 2:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprdivl = _prevctx;
						_localctx.exprdivl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(455);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(456);
						match(DIV);
						setState(457);
						((NoviexprContext)_localctx).exprdivr = noviexpr(20);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprdivr.ast.location()).end1(), (((NoviexprContext)_localctx).exprdivr.ast.location()).end2()), AstBinExpr.Oper.DIV, ((NoviexprContext)_localctx).exprdivl.ast, ((NoviexprContext)_localctx).exprdivr.ast);
						}
						break;
					case 3:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprmodl = _prevctx;
						_localctx.exprmodl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(460);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(461);
						match(MOD);
						setState(462);
						((NoviexprContext)_localctx).exprmodr = noviexpr(19);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprmodr.ast.location()).end1(), (((NoviexprContext)_localctx).exprmodr.ast.location()).end2()), AstBinExpr.Oper.MOD, ((NoviexprContext)_localctx).exprmodl.ast, ((NoviexprContext)_localctx).exprmodr.ast);
						}
						break;
					case 4:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.expraddl = _prevctx;
						_localctx.expraddl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(465);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(466);
						match(ADD);
						setState(467);
						((NoviexprContext)_localctx).expraddr = noviexpr(18);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).expraddr.ast.location()).end1(), (((NoviexprContext)_localctx).expraddr.ast.location()).end2()), AstBinExpr.Oper.ADD, ((NoviexprContext)_localctx).expraddl.ast, ((NoviexprContext)_localctx).expraddr.ast);
						}
						break;
					case 5:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprsubl = _prevctx;
						_localctx.exprsubl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(470);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(471);
						match(SUB);
						setState(472);
						((NoviexprContext)_localctx).exprsubr = noviexpr(17);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprsubr.ast.location()).end1(), (((NoviexprContext)_localctx).exprsubr.ast.location()).end2()), AstBinExpr.Oper.SUB, ((NoviexprContext)_localctx).exprsubl.ast, ((NoviexprContext)_localctx).exprsubr.ast);
						}
						break;
					case 6:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprequl = _prevctx;
						_localctx.exprequl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(475);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(476);
						match(EQU);
						setState(477);
						((NoviexprContext)_localctx).exprequr = noviexpr(16);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprequr.ast.location()).end1(), (((NoviexprContext)_localctx).exprequr.ast.location()).end2()), AstBinExpr.Oper.EQU, ((NoviexprContext)_localctx).exprequl.ast, ((NoviexprContext)_localctx).exprequr.ast);
						}
						break;
					case 7:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprneql = _prevctx;
						_localctx.exprneql = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(480);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(481);
						match(NEQ);
						setState(482);
						((NoviexprContext)_localctx).exprneqr = noviexpr(15);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprneqr.ast.location()).end1(), (((NoviexprContext)_localctx).exprneqr.ast.location()).end2()), AstBinExpr.Oper.NEQ, ((NoviexprContext)_localctx).exprneql.ast, ((NoviexprContext)_localctx).exprneqr.ast);
						}
						break;
					case 8:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprlthl = _prevctx;
						_localctx.exprlthl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(485);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(486);
						match(LTH);
						setState(487);
						((NoviexprContext)_localctx).exprlthr = noviexpr(14);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprlthr.ast.location()).end1(), (((NoviexprContext)_localctx).exprlthr.ast.location()).end2()), AstBinExpr.Oper.LTH, ((NoviexprContext)_localctx).exprlthl.ast, ((NoviexprContext)_localctx).exprlthr.ast);
						}
						break;
					case 9:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprgthl = _prevctx;
						_localctx.exprgthl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(490);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(491);
						match(GTH);
						setState(492);
						((NoviexprContext)_localctx).exprgthr = noviexpr(13);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprgthr.ast.location()).end1(), (((NoviexprContext)_localctx).exprgthr.ast.location()).end2()), AstBinExpr.Oper.GTH, ((NoviexprContext)_localctx).exprgthl.ast, ((NoviexprContext)_localctx).exprgthr.ast);
						}
						break;
					case 10:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprleql = _prevctx;
						_localctx.exprleql = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(495);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(496);
						match(LEQ);
						setState(497);
						((NoviexprContext)_localctx).exprleqr = noviexpr(12);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprleqr.ast.location()).end1(), (((NoviexprContext)_localctx).exprleqr.ast.location()).end2()), AstBinExpr.Oper.LEQ, ((NoviexprContext)_localctx).exprleql.ast, ((NoviexprContext)_localctx).exprleqr.ast);
						}
						break;
					case 11:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprgeql = _prevctx;
						_localctx.exprgeql = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(500);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(501);
						match(GEQ);
						setState(502);
						((NoviexprContext)_localctx).exprgeqr = noviexpr(11);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprgeqr.ast.location()).end1(), (((NoviexprContext)_localctx).exprgeqr.ast.location()).end2()), AstBinExpr.Oper.GEQ, ((NoviexprContext)_localctx).exprgeql.ast, ((NoviexprContext)_localctx).exprgeqr.ast);
						}
						break;
					case 12:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprandl = _prevctx;
						_localctx.exprandl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(505);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(506);
						match(AND);
						setState(507);
						((NoviexprContext)_localctx).exprandr = noviexpr(10);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprandr.ast.location()).end1(), (((NoviexprContext)_localctx).exprandr.ast.location()).end2()), AstBinExpr.Oper.AND, ((NoviexprContext)_localctx).exprandl.ast, ((NoviexprContext)_localctx).exprandr.ast);
						}
						break;
					case 13:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprorl = _prevctx;
						_localctx.exprorl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(510);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(511);
						match(OR);
						setState(512);
						((NoviexprContext)_localctx).exprorr = noviexpr(9);
						((NoviexprContext)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprorr.ast.location()).end1(), (((NoviexprContext)_localctx).exprorr.ast.location()).end2()), AstBinExpr.Oper.OR, ((NoviexprContext)_localctx).exprorl.ast, ((NoviexprContext)_localctx).exprorr.ast);
						}
						break;
					case 14:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprlb = _prevctx;
						_localctx.exprlb = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(515);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(516);
						match(LBRACKET);
						setState(517);
						((NoviexprContext)_localctx).exprrb = noviexpr(0);
						setState(518);
						((NoviexprContext)_localctx).RBRACKET = match(RBRACKET);
						((NoviexprContext)_localctx).ast =  new AstArrExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), ((NoviexprContext)_localctx).RBRACKET.getCharPositionInLine() + ((NoviexprContext)_localctx).RBRACKET.getText().length() - 1), ((NoviexprContext)_localctx).exprlb.ast, ((NoviexprContext)_localctx).exprrb.ast);
						}
						break;
					case 15:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprptr = _prevctx;
						_localctx.exprptr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(521);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(522);
						((NoviexprContext)_localctx).PTR = match(PTR);
						((NoviexprContext)_localctx).ast =  new AstSfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), ((NoviexprContext)_localctx).PTR.getCharPositionInLine() + ((NoviexprContext)_localctx).PTR.getText().length() - 1), AstSfxExpr.Oper.PTR, ((NoviexprContext)_localctx).exprptr.ast);
						}
						break;
					case 16:
						{
						_localctx = new NoviexprContext(_parentctx, _parentState);
						_localctx.exprdot = _prevctx;
						_localctx.exprdot = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr);
						setState(524);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(525);
						match(DOT);
						setState(526);
						((NoviexprContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						((NoviexprContext)_localctx).ast =  new AstRecExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((NoviexprContext)_localctx).exprdot.ast.location()).end1(), (((NoviexprContext)_localctx).exprdot.ast.location()).end2()), ((NoviexprContext)_localctx).exprdot.ast, new AstNameExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), ((NoviexprContext)_localctx).IDENTIFIER.getLine(), ((NoviexprContext)_localctx).IDENTIFIER.getCharPositionInLine() + ((NoviexprContext)_localctx).IDENTIFIER.getText().length() - 1), (((NoviexprContext)_localctx).IDENTIFIER!=null?((NoviexprContext)_localctx).IDENTIFIER.getText():null)));
						}
						break;
					}
					} 
				}
				setState(532);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Noviexpr2Context extends ParserRuleContext {
		public AstExpr ast;
		public Noviexpr2Context exprlb;
		public Noviexpr2Context exprptr;
		public Noviexpr2Context exprdot;
		public Noviexpr2Context exprmull;
		public Noviexpr2Context exprdivl;
		public Noviexpr2Context exprmodl;
		public Noviexpr2Context expraddl;
		public Noviexpr2Context exprsubl;
		public Noviexpr2Context exprequl;
		public Noviexpr2Context exprneql;
		public Noviexpr2Context exprlthl;
		public Noviexpr2Context exprgthl;
		public Noviexpr2Context exprleql;
		public Noviexpr2Context exprgeql;
		public Noviexpr2Context exprandl;
		public Noviexpr2Context exprorl;
		public StmtContext stm1;
		public StmtContext stm2;
		public Token RBRACE;
		public Token IDENTIFIER;
		public Noviexpr2Context exprpoc;
		public Noviexpr2Context exprost;
		public Token RPAREN;
		public Noviexpr2Context exprnew;
		public Noviexpr2Context exprdel;
		public Noviexpr2Context exprlp;
		public Noviexpr2Context exprcast;
		public TypeContext type;
		public Noviexpr2Context exprnot;
		public Noviexpr2Context exprad;
		public Noviexpr2Context exprsub;
		public Token PTR;
		public Noviexpr2Context exprpttr;
		public Token INTCONST;
		public Token BOOLCONST;
		public Token STRINGCONST;
		public Token CHARCONST;
		public Token POINTERCONST;
		public Token VOIDCONST;
		public Noviexpr2Context exprmulr;
		public Noviexpr2Context exprdivr;
		public Noviexpr2Context exprmodr;
		public Noviexpr2Context expraddr;
		public Noviexpr2Context exprsubr;
		public Noviexpr2Context exprequr;
		public Noviexpr2Context exprneqr;
		public Noviexpr2Context exprlthr;
		public Noviexpr2Context exprgthr;
		public Noviexpr2Context exprleqr;
		public Noviexpr2Context exprgeqr;
		public Noviexpr2Context exprandr;
		public Noviexpr2Context exprorr;
		public Noviexpr2Context exprrb;
		public Token RBRACKET;
		public TerminalNode LBRACE() { return getToken(PrevParser.LBRACE, 0); }
		public List<TerminalNode> SEMIC() { return getTokens(PrevParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(PrevParser.SEMIC, i);
		}
		public TerminalNode RBRACE() { return getToken(PrevParser.RBRACE, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(PrevParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PrevParser.RPAREN, 0); }
		public List<Noviexpr2Context> noviexpr2() {
			return getRuleContexts(Noviexpr2Context.class);
		}
		public Noviexpr2Context noviexpr2(int i) {
			return getRuleContext(Noviexpr2Context.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public TerminalNode NEW() { return getToken(PrevParser.NEW, 0); }
		public TerminalNode DEL() { return getToken(PrevParser.DEL, 0); }
		public TerminalNode COLON() { return getToken(PrevParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NOT() { return getToken(PrevParser.NOT, 0); }
		public TerminalNode ADD() { return getToken(PrevParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(PrevParser.SUB, 0); }
		public TerminalNode PTR() { return getToken(PrevParser.PTR, 0); }
		public TerminalNode INTCONST() { return getToken(PrevParser.INTCONST, 0); }
		public TerminalNode BOOLCONST() { return getToken(PrevParser.BOOLCONST, 0); }
		public TerminalNode STRINGCONST() { return getToken(PrevParser.STRINGCONST, 0); }
		public TerminalNode CHARCONST() { return getToken(PrevParser.CHARCONST, 0); }
		public TerminalNode POINTERCONST() { return getToken(PrevParser.POINTERCONST, 0); }
		public TerminalNode VOIDCONST() { return getToken(PrevParser.VOIDCONST, 0); }
		public TerminalNode MUL() { return getToken(PrevParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(PrevParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(PrevParser.MOD, 0); }
		public TerminalNode EQU() { return getToken(PrevParser.EQU, 0); }
		public TerminalNode NEQ() { return getToken(PrevParser.NEQ, 0); }
		public TerminalNode LTH() { return getToken(PrevParser.LTH, 0); }
		public TerminalNode GTH() { return getToken(PrevParser.GTH, 0); }
		public TerminalNode LEQ() { return getToken(PrevParser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(PrevParser.GEQ, 0); }
		public TerminalNode AND() { return getToken(PrevParser.AND, 0); }
		public TerminalNode OR() { return getToken(PrevParser.OR, 0); }
		public TerminalNode LBRACKET() { return getToken(PrevParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(PrevParser.RBRACKET, 0); }
		public TerminalNode DOT() { return getToken(PrevParser.DOT, 0); }
		public Noviexpr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noviexpr2; }
	}

	public final Noviexpr2Context noviexpr2() throws RecognitionException {
		return noviexpr2(0);
	}

	private Noviexpr2Context noviexpr2(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Noviexpr2Context _localctx = new Noviexpr2Context(_ctx, _parentState);
		Noviexpr2Context _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_noviexpr2, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(619);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				Vector<AstStmt> stm = new Vector<AstStmt>();
				setState(535);
				match(LBRACE);
				setState(536);
				((Noviexpr2Context)_localctx).stm1 = stmt();
				stm.add(((Noviexpr2Context)_localctx).stm1.ast);
				setState(538);
				match(SEMIC);
				setState(545);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLCONST) | (1L << STRINGCONST) | (1L << CHARCONST) | (1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << ADD) | (1L << SUB) | (1L << PTR) | (1L << DEL) | (1L << IF) | (1L << NEW) | (1L << WHILE) | (1L << VOIDCONST) | (1L << POINTERCONST) | (1L << IDENTIFIER) | (1L << INTCONST))) != 0)) {
					{
					{
					setState(539);
					((Noviexpr2Context)_localctx).stm2 = stmt();
					stm.add(((Noviexpr2Context)_localctx).stm2.ast);
					setState(541);
					match(SEMIC);
					}
					}
					setState(547);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(548);
				((Noviexpr2Context)_localctx).RBRACE = match(RBRACE);
				((Noviexpr2Context)_localctx).ast =  new AstStmtExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), ((Noviexpr2Context)_localctx).RBRACE.getLine(), ((Noviexpr2Context)_localctx).RBRACE.getCharPositionInLine() + ((Noviexpr2Context)_localctx).RBRACE.getText().length() - 1), new AstTrees<AstStmt>(stm));
				}
				break;
			case 2:
				{
				setState(551);
				((Noviexpr2Context)_localctx).IDENTIFIER = match(IDENTIFIER);
				((Noviexpr2Context)_localctx).ast =  new AstNameExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1),(((Noviexpr2Context)_localctx).IDENTIFIER!=null?((Noviexpr2Context)_localctx).IDENTIFIER.getText():null));
				}
				break;
			case 3:
				{
				Vector<AstExpr> exp = new Vector<AstExpr>();
				setState(554);
				((Noviexpr2Context)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(555);
				match(LPAREN);
				setState(567);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLCONST) | (1L << STRINGCONST) | (1L << CHARCONST) | (1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << ADD) | (1L << SUB) | (1L << PTR) | (1L << DEL) | (1L << NEW) | (1L << VOIDCONST) | (1L << POINTERCONST) | (1L << IDENTIFIER) | (1L << INTCONST))) != 0)) {
					{
					setState(556);
					((Noviexpr2Context)_localctx).exprpoc = noviexpr2(0);
					exp.add(((Noviexpr2Context)_localctx).exprpoc.ast);
					setState(564);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(558);
						match(COMMA);
						setState(559);
						((Noviexpr2Context)_localctx).exprost = noviexpr2(0);
						exp.add(((Noviexpr2Context)_localctx).exprost.ast);
						}
						}
						setState(566);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(569);
				((Noviexpr2Context)_localctx).RPAREN = match(RPAREN);
				((Noviexpr2Context)_localctx).ast =  new AstCallExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), ((Noviexpr2Context)_localctx).RPAREN.getLine(), ((Noviexpr2Context)_localctx).RPAREN.getCharPositionInLine() + ((Noviexpr2Context)_localctx).RPAREN.getText().length() - 1), (((Noviexpr2Context)_localctx).IDENTIFIER!=null?((Noviexpr2Context)_localctx).IDENTIFIER.getText():null), new AstTrees<AstExpr>(exp));
				}
				break;
			case 4:
				{
				setState(571);
				match(NEW);
				setState(572);
				((Noviexpr2Context)_localctx).exprnew = noviexpr2(30);
				((Noviexpr2Context)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprnew.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprnew.ast.location()).end2()), AstPfxExpr.Oper.NEW, ((Noviexpr2Context)_localctx).exprnew.ast);
				}
				break;
			case 5:
				{
				setState(575);
				match(DEL);
				setState(576);
				((Noviexpr2Context)_localctx).exprdel = noviexpr2(29);
				((Noviexpr2Context)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprdel.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprdel.ast.location()).end2()), AstPfxExpr.Oper.DEL, ((Noviexpr2Context)_localctx).exprdel.ast);
				}
				break;
			case 6:
				{
				setState(579);
				match(LPAREN);
				setState(580);
				((Noviexpr2Context)_localctx).exprlp = noviexpr2(0);
				setState(581);
				((Noviexpr2Context)_localctx).RPAREN = match(RPAREN);
				((Noviexpr2Context)_localctx).ast =  ((Noviexpr2Context)_localctx).exprlp.ast;
				}
				break;
			case 7:
				{
				setState(584);
				match(LPAREN);
				setState(585);
				((Noviexpr2Context)_localctx).exprcast = noviexpr2(0);
				setState(586);
				match(COLON);
				setState(587);
				((Noviexpr2Context)_localctx).type = type();
				setState(588);
				((Noviexpr2Context)_localctx).RPAREN = match(RPAREN);
				((Noviexpr2Context)_localctx).ast =  new AstCastExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), ((Noviexpr2Context)_localctx).RPAREN.getCharPositionInLine() + ((Noviexpr2Context)_localctx).RPAREN.getText().length() - 1), ((Noviexpr2Context)_localctx).exprcast.ast, ((Noviexpr2Context)_localctx).type.ast);
				}
				break;
			case 8:
				{
				setState(591);
				match(NOT);
				setState(592);
				((Noviexpr2Context)_localctx).exprnot = noviexpr2(23);
				((Noviexpr2Context)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprnot.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprnot.ast.location()).end2()), AstPfxExpr.Oper.NOT, ((Noviexpr2Context)_localctx).exprnot.ast);
				}
				break;
			case 9:
				{
				setState(595);
				match(ADD);
				setState(596);
				((Noviexpr2Context)_localctx).exprad = noviexpr2(22);
				((Noviexpr2Context)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprad.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprad.ast.location()).end2()), AstPfxExpr.Oper.ADD, ((Noviexpr2Context)_localctx).exprad.ast);
				}
				break;
			case 10:
				{
				setState(599);
				match(SUB);
				setState(600);
				((Noviexpr2Context)_localctx).exprsub = noviexpr2(21);
				((Noviexpr2Context)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprsub.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprsub.ast.location()).end2()), AstPfxExpr.Oper.SUB, ((Noviexpr2Context)_localctx).exprsub.ast);
				}
				break;
			case 11:
				{
				setState(603);
				((Noviexpr2Context)_localctx).PTR = match(PTR);
				setState(604);
				((Noviexpr2Context)_localctx).exprpttr = noviexpr2(20);
				((Noviexpr2Context)_localctx).ast =  new AstPfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprpttr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprpttr.ast.location()).end2()), AstPfxExpr.Oper.PTR, ((Noviexpr2Context)_localctx).exprpttr.ast);
				}
				break;
			case 12:
				{
				setState(607);
				((Noviexpr2Context)_localctx).INTCONST = match(INTCONST);
				((Noviexpr2Context)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.INT, (((Noviexpr2Context)_localctx).INTCONST!=null?((Noviexpr2Context)_localctx).INTCONST.getText():null));
				}
				break;
			case 13:
				{
				setState(609);
				((Noviexpr2Context)_localctx).BOOLCONST = match(BOOLCONST);
				((Noviexpr2Context)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.BOOL, (((Noviexpr2Context)_localctx).BOOLCONST!=null?((Noviexpr2Context)_localctx).BOOLCONST.getText():null));
				}
				break;
			case 14:
				{
				setState(611);
				((Noviexpr2Context)_localctx).STRINGCONST = match(STRINGCONST);
				((Noviexpr2Context)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.STRING, (((Noviexpr2Context)_localctx).STRINGCONST!=null?((Noviexpr2Context)_localctx).STRINGCONST.getText():null));
				}
				break;
			case 15:
				{
				setState(613);
				((Noviexpr2Context)_localctx).CHARCONST = match(CHARCONST);
				((Noviexpr2Context)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.CHAR, (((Noviexpr2Context)_localctx).CHARCONST!=null?((Noviexpr2Context)_localctx).CHARCONST.getText():null));
				}
				break;
			case 16:
				{
				setState(615);
				((Noviexpr2Context)_localctx).POINTERCONST = match(POINTERCONST);
				((Noviexpr2Context)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.POINTER, (((Noviexpr2Context)_localctx).POINTERCONST!=null?((Noviexpr2Context)_localctx).POINTERCONST.getText():null));
				}
				break;
			case 17:
				{
				setState(617);
				((Noviexpr2Context)_localctx).VOIDCONST = match(VOIDCONST);
				((Noviexpr2Context)_localctx).ast =  new AstAtomExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), _localctx.start.getCharPositionInLine() + _localctx.start.getText().length() - 1), AstAtomExpr.Type.VOID, (((Noviexpr2Context)_localctx).VOIDCONST!=null?((Noviexpr2Context)_localctx).VOIDCONST.getText():null));
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(701);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(699);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprmull = _prevctx;
						_localctx.exprmull = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(621);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(622);
						match(MUL);
						setState(623);
						((Noviexpr2Context)_localctx).exprmulr = noviexpr2(20);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprmulr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprmulr.ast.location()).end2()), AstBinExpr.Oper.MUL, ((Noviexpr2Context)_localctx).exprmull.ast, ((Noviexpr2Context)_localctx).exprmulr.ast);
						}
						break;
					case 2:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprdivl = _prevctx;
						_localctx.exprdivl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(626);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(627);
						match(DIV);
						setState(628);
						((Noviexpr2Context)_localctx).exprdivr = noviexpr2(19);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprdivr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprdivr.ast.location()).end2()), AstBinExpr.Oper.DIV, ((Noviexpr2Context)_localctx).exprdivl.ast, ((Noviexpr2Context)_localctx).exprdivr.ast);
						}
						break;
					case 3:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprmodl = _prevctx;
						_localctx.exprmodl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(631);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(632);
						match(MOD);
						setState(633);
						((Noviexpr2Context)_localctx).exprmodr = noviexpr2(18);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprmodr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprmodr.ast.location()).end2()), AstBinExpr.Oper.MOD, ((Noviexpr2Context)_localctx).exprmodl.ast, ((Noviexpr2Context)_localctx).exprmodr.ast);
						}
						break;
					case 4:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.expraddl = _prevctx;
						_localctx.expraddl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(636);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(637);
						match(ADD);
						setState(638);
						((Noviexpr2Context)_localctx).expraddr = noviexpr2(17);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).expraddr.ast.location()).end1(), (((Noviexpr2Context)_localctx).expraddr.ast.location()).end2()), AstBinExpr.Oper.ADD, ((Noviexpr2Context)_localctx).expraddl.ast, ((Noviexpr2Context)_localctx).expraddr.ast);
						}
						break;
					case 5:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprsubl = _prevctx;
						_localctx.exprsubl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(641);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(642);
						match(SUB);
						setState(643);
						((Noviexpr2Context)_localctx).exprsubr = noviexpr2(16);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprsubr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprsubr.ast.location()).end2()), AstBinExpr.Oper.SUB, ((Noviexpr2Context)_localctx).exprsubl.ast, ((Noviexpr2Context)_localctx).exprsubr.ast);
						}
						break;
					case 6:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprequl = _prevctx;
						_localctx.exprequl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(646);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(647);
						match(EQU);
						setState(648);
						((Noviexpr2Context)_localctx).exprequr = noviexpr2(15);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprequr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprequr.ast.location()).end2()), AstBinExpr.Oper.EQU, ((Noviexpr2Context)_localctx).exprequl.ast, ((Noviexpr2Context)_localctx).exprequr.ast);
						}
						break;
					case 7:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprneql = _prevctx;
						_localctx.exprneql = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(651);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(652);
						match(NEQ);
						setState(653);
						((Noviexpr2Context)_localctx).exprneqr = noviexpr2(14);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprneqr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprneqr.ast.location()).end2()), AstBinExpr.Oper.NEQ, ((Noviexpr2Context)_localctx).exprneql.ast, ((Noviexpr2Context)_localctx).exprneqr.ast);
						}
						break;
					case 8:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprlthl = _prevctx;
						_localctx.exprlthl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(656);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(657);
						match(LTH);
						setState(658);
						((Noviexpr2Context)_localctx).exprlthr = noviexpr2(13);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprlthr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprlthr.ast.location()).end2()), AstBinExpr.Oper.LTH, ((Noviexpr2Context)_localctx).exprlthl.ast, ((Noviexpr2Context)_localctx).exprlthr.ast);
						}
						break;
					case 9:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprgthl = _prevctx;
						_localctx.exprgthl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(661);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(662);
						match(GTH);
						setState(663);
						((Noviexpr2Context)_localctx).exprgthr = noviexpr2(12);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprgthr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprgthr.ast.location()).end2()), AstBinExpr.Oper.GTH, ((Noviexpr2Context)_localctx).exprgthl.ast, ((Noviexpr2Context)_localctx).exprgthr.ast);
						}
						break;
					case 10:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprleql = _prevctx;
						_localctx.exprleql = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(666);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(667);
						match(LEQ);
						setState(668);
						((Noviexpr2Context)_localctx).exprleqr = noviexpr2(11);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprleqr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprleqr.ast.location()).end2()), AstBinExpr.Oper.LEQ, ((Noviexpr2Context)_localctx).exprleql.ast, ((Noviexpr2Context)_localctx).exprleqr.ast);
						}
						break;
					case 11:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprgeql = _prevctx;
						_localctx.exprgeql = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(671);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(672);
						match(GEQ);
						setState(673);
						((Noviexpr2Context)_localctx).exprgeqr = noviexpr2(10);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprgeqr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprgeqr.ast.location()).end2()), AstBinExpr.Oper.GEQ, ((Noviexpr2Context)_localctx).exprgeql.ast, ((Noviexpr2Context)_localctx).exprgeqr.ast);
						}
						break;
					case 12:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprandl = _prevctx;
						_localctx.exprandl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(676);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(677);
						match(AND);
						setState(678);
						((Noviexpr2Context)_localctx).exprandr = noviexpr2(9);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprandr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprandr.ast.location()).end2()), AstBinExpr.Oper.AND, ((Noviexpr2Context)_localctx).exprandl.ast, ((Noviexpr2Context)_localctx).exprandr.ast);
						}
						break;
					case 13:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprorl = _prevctx;
						_localctx.exprorl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(681);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(682);
						match(OR);
						setState(683);
						((Noviexpr2Context)_localctx).exprorr = noviexpr2(8);
						((Noviexpr2Context)_localctx).ast =  new AstBinExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprorr.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprorr.ast.location()).end2()), AstBinExpr.Oper.OR, ((Noviexpr2Context)_localctx).exprorl.ast, ((Noviexpr2Context)_localctx).exprorr.ast);
						}
						break;
					case 14:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprlb = _prevctx;
						_localctx.exprlb = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(686);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(687);
						match(LBRACKET);
						setState(688);
						((Noviexpr2Context)_localctx).exprrb = noviexpr2(0);
						setState(689);
						((Noviexpr2Context)_localctx).RBRACKET = match(RBRACKET);
						((Noviexpr2Context)_localctx).ast =  new AstArrExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), ((Noviexpr2Context)_localctx).RBRACKET.getCharPositionInLine() + ((Noviexpr2Context)_localctx).RBRACKET.getText().length() - 1), ((Noviexpr2Context)_localctx).exprlb.ast, ((Noviexpr2Context)_localctx).exprrb.ast);
						}
						break;
					case 15:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprptr = _prevctx;
						_localctx.exprptr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(692);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(693);
						((Noviexpr2Context)_localctx).PTR = match(PTR);
						((Noviexpr2Context)_localctx).ast =  new AstSfxExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), _localctx.start.getLine(), ((Noviexpr2Context)_localctx).PTR.getCharPositionInLine() + ((Noviexpr2Context)_localctx).PTR.getText().length() - 1), AstSfxExpr.Oper.PTR, ((Noviexpr2Context)_localctx).exprptr.ast);
						}
						break;
					case 16:
						{
						_localctx = new Noviexpr2Context(_parentctx, _parentState);
						_localctx.exprdot = _prevctx;
						_localctx.exprdot = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_noviexpr2);
						setState(695);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(696);
						match(DOT);
						setState(697);
						((Noviexpr2Context)_localctx).IDENTIFIER = match(IDENTIFIER);
						((Noviexpr2Context)_localctx).ast =  new AstRecExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), (((Noviexpr2Context)_localctx).exprdot.ast.location()).end1(), (((Noviexpr2Context)_localctx).exprdot.ast.location()).end2()), ((Noviexpr2Context)_localctx).exprdot.ast, new AstNameExpr(new Location(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), ((Noviexpr2Context)_localctx).IDENTIFIER.getLine(), ((Noviexpr2Context)_localctx).IDENTIFIER.getCharPositionInLine() + ((Noviexpr2Context)_localctx).IDENTIFIER.getText().length() - 1), (((Noviexpr2Context)_localctx).IDENTIFIER!=null?((Noviexpr2Context)_localctx).IDENTIFIER.getText():null)));
						}
						break;
					}
					} 
				}
				setState(703);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 5:
			return noviexpr_sempred((NoviexprContext)_localctx, predIndex);
		case 6:
			return noviexpr2_sempred((Noviexpr2Context)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 21);
		case 1:
			return precpred(_ctx, 20);
		case 2:
			return precpred(_ctx, 19);
		case 3:
			return precpred(_ctx, 18);
		case 4:
			return precpred(_ctx, 17);
		case 5:
			return precpred(_ctx, 16);
		case 6:
			return precpred(_ctx, 15);
		case 7:
			return precpred(_ctx, 14);
		case 8:
			return precpred(_ctx, 13);
		case 9:
			return precpred(_ctx, 12);
		case 10:
			return precpred(_ctx, 11);
		case 11:
			return precpred(_ctx, 10);
		case 12:
			return precpred(_ctx, 9);
		case 13:
			return precpred(_ctx, 28);
		case 14:
			return precpred(_ctx, 27);
		case 15:
			return precpred(_ctx, 26);
		}
		return true;
	}
	private boolean noviexpr_sempred(NoviexprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 16:
			return precpred(_ctx, 20);
		case 17:
			return precpred(_ctx, 19);
		case 18:
			return precpred(_ctx, 18);
		case 19:
			return precpred(_ctx, 17);
		case 20:
			return precpred(_ctx, 16);
		case 21:
			return precpred(_ctx, 15);
		case 22:
			return precpred(_ctx, 14);
		case 23:
			return precpred(_ctx, 13);
		case 24:
			return precpred(_ctx, 12);
		case 25:
			return precpred(_ctx, 11);
		case 26:
			return precpred(_ctx, 10);
		case 27:
			return precpred(_ctx, 9);
		case 28:
			return precpred(_ctx, 8);
		case 29:
			return precpred(_ctx, 27);
		case 30:
			return precpred(_ctx, 26);
		case 31:
			return precpred(_ctx, 25);
		}
		return true;
	}
	private boolean noviexpr2_sempred(Noviexpr2Context _localctx, int predIndex) {
		switch (predIndex) {
		case 32:
			return precpred(_ctx, 19);
		case 33:
			return precpred(_ctx, 18);
		case 34:
			return precpred(_ctx, 17);
		case 35:
			return precpred(_ctx, 16);
		case 36:
			return precpred(_ctx, 15);
		case 37:
			return precpred(_ctx, 14);
		case 38:
			return precpred(_ctx, 13);
		case 39:
			return precpred(_ctx, 12);
		case 40:
			return precpred(_ctx, 11);
		case 41:
			return precpred(_ctx, 10);
		case 42:
			return precpred(_ctx, 9);
		case 43:
			return precpred(_ctx, 8);
		case 44:
			return precpred(_ctx, 7);
		case 45:
			return precpred(_ctx, 26);
		case 46:
			return precpred(_ctx, 25);
		case 47:
			return precpred(_ctx, 24);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\66\u02c3\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\6\2\25"+
		"\n\2\r\2\16\2\26\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\65\n\3"+
		"\f\3\16\38\13\3\5\3:\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3O\n\3\f\3\16\3R\13\3\5\3T\n\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\5\3]\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4w\n\4\f"+
		"\4\16\4z\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\5\4\u008c\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0098"+
		"\n\5\f\5\16\5\u009b\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\7\5\u00a9\n\5\f\5\16\5\u00ac\13\5\5\5\u00ae\n\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\6\5\u00dd\n\5\r\5\16\5\u00de\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00f2\n\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0142\n\5\f\5\16\5\u0145\13\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\5\6\u015d\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u0169"+
		"\n\7\f\7\16\7\u016c\13\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\7\7\u017c\n\7\f\7\16\7\u017f\13\7\5\7\u0181\n\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\6\7\u01b0\n\7\r\7\16\7\u01b1\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u01c3\n\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u0213\n\7\f\7\16\7\u0216\13\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0222\n\b\f\b\16\b\u0225\13\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0235\n\b\f\b\16"+
		"\b\u0238\13\b\5\b\u023a\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\5\b\u026e\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u02be"+
		"\n\b\f\b\16\b\u02c1\13\b\3\b\2\5\b\f\16\t\2\4\6\b\n\f\16\2\2\2\u033c\2"+
		"\20\3\2\2\2\4\\\3\2\2\2\6\u008b\3\2\2\2\b\u00f1\3\2\2\2\n\u015c\3\2\2"+
		"\2\f\u01c2\3\2\2\2\16\u026d\3\2\2\2\20\24\b\2\1\2\21\22\5\4\3\2\22\23"+
		"\b\2\1\2\23\25\3\2\2\2\24\21\3\2\2\2\25\26\3\2\2\2\26\24\3\2\2\2\26\27"+
		"\3\2\2\2\27\30\3\2\2\2\30\31\b\2\1\2\31\3\3\2\2\2\32\33\7.\2\2\33\34\7"+
		"\64\2\2\34\35\7#\2\2\35\36\5\6\4\2\36\37\b\3\1\2\37]\3\2\2\2 !\7/\2\2"+
		"!\"\7\64\2\2\"#\7\23\2\2#$\5\6\4\2$%\b\3\1\2%]\3\2\2\2&\'\b\3\1\2\'(\7"+
		"*\2\2()\7\64\2\2)9\7\n\2\2*+\7\64\2\2+,\7\23\2\2,-\5\6\4\2-\66\b\3\1\2"+
		"./\7\21\2\2/\60\7\64\2\2\60\61\7\23\2\2\61\62\5\6\4\2\62\63\b\3\1\2\63"+
		"\65\3\2\2\2\64.\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67:\3"+
		"\2\2\28\66\3\2\2\29*\3\2\2\29:\3\2\2\2:;\3\2\2\2;<\7\13\2\2<=\7\23\2\2"+
		"=>\5\6\4\2>?\b\3\1\2?]\3\2\2\2@A\b\3\1\2AB\7*\2\2BC\7\64\2\2CS\7\n\2\2"+
		"DE\7\64\2\2EF\7\23\2\2FG\5\6\4\2GP\b\3\1\2HI\7\21\2\2IJ\7\64\2\2JK\7\23"+
		"\2\2KL\5\6\4\2LM\b\3\1\2MO\3\2\2\2NH\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2"+
		"\2\2QT\3\2\2\2RP\3\2\2\2SD\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\7\13\2\2VW\7"+
		"\23\2\2WX\5\6\4\2XY\7#\2\2YZ\5\b\5\2Z[\b\3\1\2[]\3\2\2\2\\\32\3\2\2\2"+
		"\\ \3\2\2\2\\&\3\2\2\2\\@\3\2\2\2]\5\3\2\2\2^_\7\64\2\2_\u008c\b\4\1\2"+
		"`a\7\f\2\2ab\5\b\5\2bc\7\r\2\2cd\5\6\4\2de\b\4\1\2e\u008c\3\2\2\2fg\7"+
		"\"\2\2gh\5\6\4\2hi\b\4\1\2i\u008c\3\2\2\2jk\b\4\1\2kl\7\16\2\2lm\7\64"+
		"\2\2mn\7\23\2\2no\5\6\4\2ox\b\4\1\2pq\7\21\2\2qr\7\64\2\2rs\7\23\2\2s"+
		"t\5\6\4\2tu\b\4\1\2uw\3\2\2\2vp\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2"+
		"y{\3\2\2\2zx\3\2\2\2{|\7\17\2\2|}\b\4\1\2}\u008c\3\2\2\2~\177\7\n\2\2"+
		"\177\u0080\5\6\4\2\u0080\u0081\7\13\2\2\u0081\u0082\b\4\1\2\u0082\u008c"+
		"\3\2\2\2\u0083\u0084\7$\2\2\u0084\u008c\b\4\1\2\u0085\u0086\7%\2\2\u0086"+
		"\u008c\b\4\1\2\u0087\u0088\7\3\2\2\u0088\u008c\b\4\1\2\u0089\u008a\7&"+
		"\2\2\u008a\u008c\b\4\1\2\u008b^\3\2\2\2\u008b`\3\2\2\2\u008bf\3\2\2\2"+
		"\u008bj\3\2\2\2\u008b~\3\2\2\2\u008b\u0083\3\2\2\2\u008b\u0085\3\2\2\2"+
		"\u008b\u0087\3\2\2\2\u008b\u0089\3\2\2\2\u008c\7\3\2\2\2\u008d\u008e\b"+
		"\5\1\2\u008e\u008f\b\5\1\2\u008f\u0090\7\16\2\2\u0090\u0091\5\n\6\2\u0091"+
		"\u0092\b\5\1\2\u0092\u0099\7\22\2\2\u0093\u0094\5\n\6\2\u0094\u0095\b"+
		"\5\1\2\u0095\u0096\7\22\2\2\u0096\u0098\3\2\2\2\u0097\u0093\3\2\2\2\u0098"+
		"\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009c\3\2"+
		"\2\2\u009b\u0099\3\2\2\2\u009c\u009d\7\17\2\2\u009d\u009e\b\5\1\2\u009e"+
		"\u00f2\3\2\2\2\u009f\u00a0\b\5\1\2\u00a0\u00a1\7\64\2\2\u00a1\u00ad\7"+
		"\n\2\2\u00a2\u00a3\5\b\5\2\u00a3\u00aa\b\5\1\2\u00a4\u00a5\7\21\2\2\u00a5"+
		"\u00a6\5\b\5\2\u00a6\u00a7\b\5\1\2\u00a7\u00a9\3\2\2\2\u00a8\u00a4\3\2"+
		"\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab"+
		"\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00a2\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\7\13\2\2\u00b0\u00f2\b\5\1\2\u00b1"+
		"\u00b2\7,\2\2\u00b2\u00b3\5\b\5\"\u00b3\u00b4\b\5\1\2\u00b4\u00f2\3\2"+
		"\2\2\u00b5\u00b6\7\'\2\2\u00b6\u00b7\5\b\5!\u00b7\u00b8\b\5\1\2\u00b8"+
		"\u00f2\3\2\2\2\u00b9\u00ba\7\n\2\2\u00ba\u00bb\5\b\5\2\u00bb\u00bc\7\13"+
		"\2\2\u00bc\u00bd\b\5\1\2\u00bd\u00f2\3\2\2\2\u00be\u00bf\7\n\2\2\u00bf"+
		"\u00c0\5\b\5\2\u00c0\u00c1\7\23\2\2\u00c1\u00c2\5\6\4\2\u00c2\u00c3\7"+
		"\13\2\2\u00c3\u00c4\b\5\1\2\u00c4\u00f2\3\2\2\2\u00c5\u00c6\7\25\2\2\u00c6"+
		"\u00c7\5\b\5\33\u00c7\u00c8\b\5\1\2\u00c8\u00f2\3\2\2\2\u00c9\u00ca\7"+
		"\35\2\2\u00ca\u00cb\5\b\5\32\u00cb\u00cc\b\5\1\2\u00cc\u00f2\3\2\2\2\u00cd"+
		"\u00ce\7\36\2\2\u00ce\u00cf\5\b\5\31\u00cf\u00d0\b\5\1\2\u00d0\u00f2\3"+
		"\2\2\2\u00d1\u00d2\7\"\2\2\u00d2\u00d3\5\b\5\30\u00d3\u00d4\b\5\1\2\u00d4"+
		"\u00f2\3\2\2\2\u00d5\u00d6\b\5\1\2\u00d6\u00d7\5\f\7\2\u00d7\u00d8\7\60"+
		"\2\2\u00d8\u00dc\7\16\2\2\u00d9\u00da\5\4\3\2\u00da\u00db\b\5\1\2\u00db"+
		"\u00dd\3\2\2\2\u00dc\u00d9\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00dc\3\2"+
		"\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1\7\17\2\2\u00e1"+
		"\u00e2\b\5\1\2\u00e2\u00f2\3\2\2\2\u00e3\u00e4\7\65\2\2\u00e4\u00f2\b"+
		"\5\1\2\u00e5\u00e6\7\4\2\2\u00e6\u00f2\b\5\1\2\u00e7\u00e8\7\5\2\2\u00e8"+
		"\u00f2\b\5\1\2\u00e9\u00ea\7\6\2\2\u00ea\u00f2\b\5\1\2\u00eb\u00ec\7\63"+
		"\2\2\u00ec\u00f2\b\5\1\2\u00ed\u00ee\7\62\2\2\u00ee\u00f2\b\5\1\2\u00ef"+
		"\u00f0\7\64\2\2\u00f0\u00f2\b\5\1\2\u00f1\u008d\3\2\2\2\u00f1\u009f\3"+
		"\2\2\2\u00f1\u00b1\3\2\2\2\u00f1\u00b5\3\2\2\2\u00f1\u00b9\3\2\2\2\u00f1"+
		"\u00be\3\2\2\2\u00f1\u00c5\3\2\2\2\u00f1\u00c9\3\2\2\2\u00f1\u00cd\3\2"+
		"\2\2\u00f1\u00d1\3\2\2\2\u00f1\u00d5\3\2\2\2\u00f1\u00e3\3\2\2\2\u00f1"+
		"\u00e5\3\2\2\2\u00f1\u00e7\3\2\2\2\u00f1\u00e9\3\2\2\2\u00f1\u00eb\3\2"+
		"\2\2\u00f1\u00ed\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u0143\3\2\2\2\u00f3"+
		"\u00f4\f\27\2\2\u00f4\u00f5\7\37\2\2\u00f5\u00f6\5\b\5\30\u00f6\u00f7"+
		"\b\5\1\2\u00f7\u0142\3\2\2\2\u00f8\u00f9\f\26\2\2\u00f9\u00fa\7 \2\2\u00fa"+
		"\u00fb\5\b\5\27\u00fb\u00fc\b\5\1\2\u00fc\u0142\3\2\2\2\u00fd\u00fe\f"+
		"\25\2\2\u00fe\u00ff\7!\2\2\u00ff\u0100\5\b\5\26\u0100\u0101\b\5\1\2\u0101"+
		"\u0142\3\2\2\2\u0102\u0103\f\24\2\2\u0103\u0104\7\35\2\2\u0104\u0105\5"+
		"\b\5\25\u0105\u0106\b\5\1\2\u0106\u0142\3\2\2\2\u0107\u0108\f\23\2\2\u0108"+
		"\u0109\7\36\2\2\u0109\u010a\5\b\5\24\u010a\u010b\b\5\1\2\u010b\u0142\3"+
		"\2\2\2\u010c\u010d\f\22\2\2\u010d\u010e\7\27\2\2\u010e\u010f\5\b\5\23"+
		"\u010f\u0110\b\5\1\2\u0110\u0142\3\2\2\2\u0111\u0112\f\21\2\2\u0112\u0113"+
		"\7\30\2\2\u0113\u0114\5\b\5\22\u0114\u0115\b\5\1\2\u0115\u0142\3\2\2\2"+
		"\u0116\u0117\f\20\2\2\u0117\u0118\7\31\2\2\u0118\u0119\5\b\5\21\u0119"+
		"\u011a\b\5\1\2\u011a\u0142\3\2\2\2\u011b\u011c\f\17\2\2\u011c\u011d\7"+
		"\32\2\2\u011d\u011e\5\b\5\20\u011e\u011f\b\5\1\2\u011f\u0142\3\2\2\2\u0120"+
		"\u0121\f\16\2\2\u0121\u0122\7\33\2\2\u0122\u0123\5\b\5\17\u0123\u0124"+
		"\b\5\1\2\u0124\u0142\3\2\2\2\u0125\u0126\f\r\2\2\u0126\u0127\7\34\2\2"+
		"\u0127\u0128\5\b\5\16\u0128\u0129\b\5\1\2\u0129\u0142\3\2\2\2\u012a\u012b"+
		"\f\f\2\2\u012b\u012c\7\24\2\2\u012c\u012d\5\b\5\r\u012d\u012e\b\5\1\2"+
		"\u012e\u0142\3\2\2\2\u012f\u0130\f\13\2\2\u0130\u0131\7\26\2\2\u0131\u0132"+
		"\5\b\5\f\u0132\u0133\b\5\1\2\u0133\u0142\3\2\2\2\u0134\u0135\f\36\2\2"+
		"\u0135\u0136\7\f\2\2\u0136\u0137\5\b\5\2\u0137\u0138\7\r\2\2\u0138\u0139"+
		"\b\5\1\2\u0139\u0142\3\2\2\2\u013a\u013b\f\35\2\2\u013b\u013c\7\"\2\2"+
		"\u013c\u0142\b\5\1\2\u013d\u013e\f\34\2\2\u013e\u013f\7\20\2\2\u013f\u0140"+
		"\7\64\2\2\u0140\u0142\b\5\1\2\u0141\u00f3\3\2\2\2\u0141\u00f8\3\2\2\2"+
		"\u0141\u00fd\3\2\2\2\u0141\u0102\3\2\2\2\u0141\u0107\3\2\2\2\u0141\u010c"+
		"\3\2\2\2\u0141\u0111\3\2\2\2\u0141\u0116\3\2\2\2\u0141\u011b\3\2\2\2\u0141"+
		"\u0120\3\2\2\2\u0141\u0125\3\2\2\2\u0141\u012a\3\2\2\2\u0141\u012f\3\2"+
		"\2\2\u0141\u0134\3\2\2\2\u0141\u013a\3\2\2\2\u0141\u013d\3\2\2\2\u0142"+
		"\u0145\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144\t\3\2\2\2"+
		"\u0145\u0143\3\2\2\2\u0146\u0147\5\b\5\2\u0147\u0148\b\6\1\2\u0148\u015d"+
		"\3\2\2\2\u0149\u014a\5\b\5\2\u014a\u014b\7#\2\2\u014b\u014c\5\b\5\2\u014c"+
		"\u014d\b\6\1\2\u014d\u015d\3\2\2\2\u014e\u014f\7+\2\2\u014f\u0150\5\b"+
		"\5\2\u0150\u0151\7-\2\2\u0151\u0152\5\n\6\2\u0152\u0153\7)\2\2\u0153\u0154"+
		"\5\n\6\2\u0154\u0155\b\6\1\2\u0155\u015d\3\2\2\2\u0156\u0157\7\61\2\2"+
		"\u0157\u0158\5\b\5\2\u0158\u0159\7(\2\2\u0159\u015a\5\n\6\2\u015a\u015b"+
		"\b\6\1\2\u015b\u015d\3\2\2\2\u015c\u0146\3\2\2\2\u015c\u0149\3\2\2\2\u015c"+
		"\u014e\3\2\2\2\u015c\u0156\3\2\2\2\u015d\13\3\2\2\2\u015e\u015f\b\7\1"+
		"\2\u015f\u0160\b\7\1\2\u0160\u0161\7\16\2\2\u0161\u0162\5\n\6\2\u0162"+
		"\u0163\b\7\1\2\u0163\u016a\7\22\2\2\u0164\u0165\5\n\6\2\u0165\u0166\b"+
		"\7\1\2\u0166\u0167\7\22\2\2\u0167\u0169\3\2\2\2\u0168\u0164\3\2\2\2\u0169"+
		"\u016c\3\2\2\2\u016a\u0168\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016d\3\2"+
		"\2\2\u016c\u016a\3\2\2\2\u016d\u016e\7\17\2\2\u016e\u016f\b\7\1\2\u016f"+
		"\u01c3\3\2\2\2\u0170\u0171\7\64\2\2\u0171\u01c3\b\7\1\2\u0172\u0173\b"+
		"\7\1\2\u0173\u0174\7\64\2\2\u0174\u0180\7\n\2\2\u0175\u0176\5\f\7\2\u0176"+
		"\u017d\b\7\1\2\u0177\u0178\7\21\2\2\u0178\u0179\5\f\7\2\u0179\u017a\b"+
		"\7\1\2\u017a\u017c\3\2\2\2\u017b\u0177\3\2\2\2\u017c\u017f\3\2\2\2\u017d"+
		"\u017b\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u0181\3\2\2\2\u017f\u017d\3\2"+
		"\2\2\u0180\u0175\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0182\3\2\2\2\u0182"+
		"\u0183\7\13\2\2\u0183\u01c3\b\7\1\2\u0184\u0185\7,\2\2\u0185\u0186\5\f"+
		"\7!\u0186\u0187\b\7\1\2\u0187\u01c3\3\2\2\2\u0188\u0189\7\'\2\2\u0189"+
		"\u018a\5\f\7 \u018a\u018b\b\7\1\2\u018b\u01c3\3\2\2\2\u018c\u018d\7\n"+
		"\2\2\u018d\u018e\5\f\7\2\u018e\u018f\7\13\2\2\u018f\u0190\b\7\1\2\u0190"+
		"\u01c3\3\2\2\2\u0191\u0192\7\n\2\2\u0192\u0193\5\f\7\2\u0193\u0194\7\23"+
		"\2\2\u0194\u0195\5\6\4\2\u0195\u0196\7\13\2\2\u0196\u0197\b\7\1\2\u0197"+
		"\u01c3\3\2\2\2\u0198\u0199\7\25\2\2\u0199\u019a\5\f\7\32\u019a\u019b\b"+
		"\7\1\2\u019b\u01c3\3\2\2\2\u019c\u019d\7\35\2\2\u019d\u019e\5\f\7\31\u019e"+
		"\u019f\b\7\1\2\u019f\u01c3\3\2\2\2\u01a0\u01a1\7\36\2\2\u01a1\u01a2\5"+
		"\f\7\30\u01a2\u01a3\b\7\1\2\u01a3\u01c3\3\2\2\2\u01a4\u01a5\7\"\2\2\u01a5"+
		"\u01a6\5\f\7\27\u01a6\u01a7\b\7\1\2\u01a7\u01c3\3\2\2\2\u01a8\u01a9\b"+
		"\7\1\2\u01a9\u01aa\5\16\b\2\u01aa\u01ab\7\60\2\2\u01ab\u01af\7\16\2\2"+
		"\u01ac\u01ad\5\4\3\2\u01ad\u01ae\b\7\1\2\u01ae\u01b0\3\2\2\2\u01af\u01ac"+
		"\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1\u01af\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2"+
		"\u01b3\3\2\2\2\u01b3\u01b4\7\17\2\2\u01b4\u01b5\b\7\1\2\u01b5\u01c3\3"+
		"\2\2\2\u01b6\u01b7\7\65\2\2\u01b7\u01c3\b\7\1\2\u01b8\u01b9\7\4\2\2\u01b9"+
		"\u01c3\b\7\1\2\u01ba\u01bb\7\5\2\2\u01bb\u01c3\b\7\1\2\u01bc\u01bd\7\6"+
		"\2\2\u01bd\u01c3\b\7\1\2\u01be\u01bf\7\63\2\2\u01bf\u01c3\b\7\1\2\u01c0"+
		"\u01c1\7\62\2\2\u01c1\u01c3\b\7\1\2\u01c2\u015e\3\2\2\2\u01c2\u0170\3"+
		"\2\2\2\u01c2\u0172\3\2\2\2\u01c2\u0184\3\2\2\2\u01c2\u0188\3\2\2\2\u01c2"+
		"\u018c\3\2\2\2\u01c2\u0191\3\2\2\2\u01c2\u0198\3\2\2\2\u01c2\u019c\3\2"+
		"\2\2\u01c2\u01a0\3\2\2\2\u01c2\u01a4\3\2\2\2\u01c2\u01a8\3\2\2\2\u01c2"+
		"\u01b6\3\2\2\2\u01c2\u01b8\3\2\2\2\u01c2\u01ba\3\2\2\2\u01c2\u01bc\3\2"+
		"\2\2\u01c2\u01be\3\2\2\2\u01c2\u01c0\3\2\2\2\u01c3\u0214\3\2\2\2\u01c4"+
		"\u01c5\f\26\2\2\u01c5\u01c6\7\37\2\2\u01c6\u01c7\5\f\7\27\u01c7\u01c8"+
		"\b\7\1\2\u01c8\u0213\3\2\2\2\u01c9\u01ca\f\25\2\2\u01ca\u01cb\7 \2\2\u01cb"+
		"\u01cc\5\f\7\26\u01cc\u01cd\b\7\1\2\u01cd\u0213\3\2\2\2\u01ce\u01cf\f"+
		"\24\2\2\u01cf\u01d0\7!\2\2\u01d0\u01d1\5\f\7\25\u01d1\u01d2\b\7\1\2\u01d2"+
		"\u0213\3\2\2\2\u01d3\u01d4\f\23\2\2\u01d4\u01d5\7\35\2\2\u01d5\u01d6\5"+
		"\f\7\24\u01d6\u01d7\b\7\1\2\u01d7\u0213\3\2\2\2\u01d8\u01d9\f\22\2\2\u01d9"+
		"\u01da\7\36\2\2\u01da\u01db\5\f\7\23\u01db\u01dc\b\7\1\2\u01dc\u0213\3"+
		"\2\2\2\u01dd\u01de\f\21\2\2\u01de\u01df\7\27\2\2\u01df\u01e0\5\f\7\22"+
		"\u01e0\u01e1\b\7\1\2\u01e1\u0213\3\2\2\2\u01e2\u01e3\f\20\2\2\u01e3\u01e4"+
		"\7\30\2\2\u01e4\u01e5\5\f\7\21\u01e5\u01e6\b\7\1\2\u01e6\u0213\3\2\2\2"+
		"\u01e7\u01e8\f\17\2\2\u01e8\u01e9\7\31\2\2\u01e9\u01ea\5\f\7\20\u01ea"+
		"\u01eb\b\7\1\2\u01eb\u0213\3\2\2\2\u01ec\u01ed\f\16\2\2\u01ed\u01ee\7"+
		"\32\2\2\u01ee\u01ef\5\f\7\17\u01ef\u01f0\b\7\1\2\u01f0\u0213\3\2\2\2\u01f1"+
		"\u01f2\f\r\2\2\u01f2\u01f3\7\33\2\2\u01f3\u01f4\5\f\7\16\u01f4\u01f5\b"+
		"\7\1\2\u01f5\u0213\3\2\2\2\u01f6\u01f7\f\f\2\2\u01f7\u01f8\7\34\2\2\u01f8"+
		"\u01f9\5\f\7\r\u01f9\u01fa\b\7\1\2\u01fa\u0213\3\2\2\2\u01fb\u01fc\f\13"+
		"\2\2\u01fc\u01fd\7\24\2\2\u01fd\u01fe\5\f\7\f\u01fe\u01ff\b\7\1\2\u01ff"+
		"\u0213\3\2\2\2\u0200\u0201\f\n\2\2\u0201\u0202\7\26\2\2\u0202\u0203\5"+
		"\f\7\13\u0203\u0204\b\7\1\2\u0204\u0213\3\2\2\2\u0205\u0206\f\35\2\2\u0206"+
		"\u0207\7\f\2\2\u0207\u0208\5\f\7\2\u0208\u0209\7\r\2\2\u0209\u020a\b\7"+
		"\1\2\u020a\u0213\3\2\2\2\u020b\u020c\f\34\2\2\u020c\u020d\7\"\2\2\u020d"+
		"\u0213\b\7\1\2\u020e\u020f\f\33\2\2\u020f\u0210\7\20\2\2\u0210\u0211\7"+
		"\64\2\2\u0211\u0213\b\7\1\2\u0212\u01c4\3\2\2\2\u0212\u01c9\3\2\2\2\u0212"+
		"\u01ce\3\2\2\2\u0212\u01d3\3\2\2\2\u0212\u01d8\3\2\2\2\u0212\u01dd\3\2"+
		"\2\2\u0212\u01e2\3\2\2\2\u0212\u01e7\3\2\2\2\u0212\u01ec\3\2\2\2\u0212"+
		"\u01f1\3\2\2\2\u0212\u01f6\3\2\2\2\u0212\u01fb\3\2\2\2\u0212\u0200\3\2"+
		"\2\2\u0212\u0205\3\2\2\2\u0212\u020b\3\2\2\2\u0212\u020e\3\2\2\2\u0213"+
		"\u0216\3\2\2\2\u0214\u0212\3\2\2\2\u0214\u0215\3\2\2\2\u0215\r\3\2\2\2"+
		"\u0216\u0214\3\2\2\2\u0217\u0218\b\b\1\2\u0218\u0219\b\b\1\2\u0219\u021a"+
		"\7\16\2\2\u021a\u021b\5\n\6\2\u021b\u021c\b\b\1\2\u021c\u0223\7\22\2\2"+
		"\u021d\u021e\5\n\6\2\u021e\u021f\b\b\1\2\u021f\u0220\7\22\2\2\u0220\u0222"+
		"\3\2\2\2\u0221\u021d\3\2\2\2\u0222\u0225\3\2\2\2\u0223\u0221\3\2\2\2\u0223"+
		"\u0224\3\2\2\2\u0224\u0226\3\2\2\2\u0225\u0223\3\2\2\2\u0226\u0227\7\17"+
		"\2\2\u0227\u0228\b\b\1\2\u0228\u026e\3\2\2\2\u0229\u022a\7\64\2\2\u022a"+
		"\u026e\b\b\1\2\u022b\u022c\b\b\1\2\u022c\u022d\7\64\2\2\u022d\u0239\7"+
		"\n\2\2\u022e\u022f\5\16\b\2\u022f\u0236\b\b\1\2\u0230\u0231\7\21\2\2\u0231"+
		"\u0232\5\16\b\2\u0232\u0233\b\b\1\2\u0233\u0235\3\2\2\2\u0234\u0230\3"+
		"\2\2\2\u0235\u0238\3\2\2\2\u0236\u0234\3\2\2\2\u0236\u0237\3\2\2\2\u0237"+
		"\u023a\3\2\2\2\u0238\u0236\3\2\2\2\u0239\u022e\3\2\2\2\u0239\u023a\3\2"+
		"\2\2\u023a\u023b\3\2\2\2\u023b\u023c\7\13\2\2\u023c\u026e\b\b\1\2\u023d"+
		"\u023e\7,\2\2\u023e\u023f\5\16\b \u023f\u0240\b\b\1\2\u0240\u026e\3\2"+
		"\2\2\u0241\u0242\7\'\2\2\u0242\u0243\5\16\b\37\u0243\u0244\b\b\1\2\u0244"+
		"\u026e\3\2\2\2\u0245\u0246\7\n\2\2\u0246\u0247\5\16\b\2\u0247\u0248\7"+
		"\13\2\2\u0248\u0249\b\b\1\2\u0249\u026e\3\2\2\2\u024a\u024b\7\n\2\2\u024b"+
		"\u024c\5\16\b\2\u024c\u024d\7\23\2\2\u024d\u024e\5\6\4\2\u024e\u024f\7"+
		"\13\2\2\u024f\u0250\b\b\1\2\u0250\u026e\3\2\2\2\u0251\u0252\7\25\2\2\u0252"+
		"\u0253\5\16\b\31\u0253\u0254\b\b\1\2\u0254\u026e\3\2\2\2\u0255\u0256\7"+
		"\35\2\2\u0256\u0257\5\16\b\30\u0257\u0258\b\b\1\2\u0258\u026e\3\2\2\2"+
		"\u0259\u025a\7\36\2\2\u025a\u025b\5\16\b\27\u025b\u025c\b\b\1\2\u025c"+
		"\u026e\3\2\2\2\u025d\u025e\7\"\2\2\u025e\u025f\5\16\b\26\u025f\u0260\b"+
		"\b\1\2\u0260\u026e\3\2\2\2\u0261\u0262\7\65\2\2\u0262\u026e\b\b\1\2\u0263"+
		"\u0264\7\4\2\2\u0264\u026e\b\b\1\2\u0265\u0266\7\5\2\2\u0266\u026e\b\b"+
		"\1\2\u0267\u0268\7\6\2\2\u0268\u026e\b\b\1\2\u0269\u026a\7\63\2\2\u026a"+
		"\u026e\b\b\1\2\u026b\u026c\7\62\2\2\u026c\u026e\b\b\1\2\u026d\u0217\3"+
		"\2\2\2\u026d\u0229\3\2\2\2\u026d\u022b\3\2\2\2\u026d\u023d\3\2\2\2\u026d"+
		"\u0241\3\2\2\2\u026d\u0245\3\2\2\2\u026d\u024a\3\2\2\2\u026d\u0251\3\2"+
		"\2\2\u026d\u0255\3\2\2\2\u026d\u0259\3\2\2\2\u026d\u025d\3\2\2\2\u026d"+
		"\u0261\3\2\2\2\u026d\u0263\3\2\2\2\u026d\u0265\3\2\2\2\u026d\u0267\3\2"+
		"\2\2\u026d\u0269\3\2\2\2\u026d\u026b\3\2\2\2\u026e\u02bf\3\2\2\2\u026f"+
		"\u0270\f\25\2\2\u0270\u0271\7\37\2\2\u0271\u0272\5\16\b\26\u0272\u0273"+
		"\b\b\1\2\u0273\u02be\3\2\2\2\u0274\u0275\f\24\2\2\u0275\u0276\7 \2\2\u0276"+
		"\u0277\5\16\b\25\u0277\u0278\b\b\1\2\u0278\u02be\3\2\2\2\u0279\u027a\f"+
		"\23\2\2\u027a\u027b\7!\2\2\u027b\u027c\5\16\b\24\u027c\u027d\b\b\1\2\u027d"+
		"\u02be\3\2\2\2\u027e\u027f\f\22\2\2\u027f\u0280\7\35\2\2\u0280\u0281\5"+
		"\16\b\23\u0281\u0282\b\b\1\2\u0282\u02be\3\2\2\2\u0283\u0284\f\21\2\2"+
		"\u0284\u0285\7\36\2\2\u0285\u0286\5\16\b\22\u0286\u0287\b\b\1\2\u0287"+
		"\u02be\3\2\2\2\u0288\u0289\f\20\2\2\u0289\u028a\7\27\2\2\u028a\u028b\5"+
		"\16\b\21\u028b\u028c\b\b\1\2\u028c\u02be\3\2\2\2\u028d\u028e\f\17\2\2"+
		"\u028e\u028f\7\30\2\2\u028f\u0290\5\16\b\20\u0290\u0291\b\b\1\2\u0291"+
		"\u02be\3\2\2\2\u0292\u0293\f\16\2\2\u0293\u0294\7\31\2\2\u0294\u0295\5"+
		"\16\b\17\u0295\u0296\b\b\1\2\u0296\u02be\3\2\2\2\u0297\u0298\f\r\2\2\u0298"+
		"\u0299\7\32\2\2\u0299\u029a\5\16\b\16\u029a\u029b\b\b\1\2\u029b\u02be"+
		"\3\2\2\2\u029c\u029d\f\f\2\2\u029d\u029e\7\33\2\2\u029e\u029f\5\16\b\r"+
		"\u029f\u02a0\b\b\1\2\u02a0\u02be\3\2\2\2\u02a1\u02a2\f\13\2\2\u02a2\u02a3"+
		"\7\34\2\2\u02a3\u02a4\5\16\b\f\u02a4\u02a5\b\b\1\2\u02a5\u02be\3\2\2\2"+
		"\u02a6\u02a7\f\n\2\2\u02a7\u02a8\7\24\2\2\u02a8\u02a9\5\16\b\13\u02a9"+
		"\u02aa\b\b\1\2\u02aa\u02be\3\2\2\2\u02ab\u02ac\f\t\2\2\u02ac\u02ad\7\26"+
		"\2\2\u02ad\u02ae\5\16\b\n\u02ae\u02af\b\b\1\2\u02af\u02be\3\2\2\2\u02b0"+
		"\u02b1\f\34\2\2\u02b1\u02b2\7\f\2\2\u02b2\u02b3\5\16\b\2\u02b3\u02b4\7"+
		"\r\2\2\u02b4\u02b5\b\b\1\2\u02b5\u02be\3\2\2\2\u02b6\u02b7\f\33\2\2\u02b7"+
		"\u02b8\7\"\2\2\u02b8\u02be\b\b\1\2\u02b9\u02ba\f\32\2\2\u02ba\u02bb\7"+
		"\20\2\2\u02bb\u02bc\7\64\2\2\u02bc\u02be\b\b\1\2\u02bd\u026f\3\2\2\2\u02bd"+
		"\u0274\3\2\2\2\u02bd\u0279\3\2\2\2\u02bd\u027e\3\2\2\2\u02bd\u0283\3\2"+
		"\2\2\u02bd\u0288\3\2\2\2\u02bd\u028d\3\2\2\2\u02bd\u0292\3\2\2\2\u02bd"+
		"\u0297\3\2\2\2\u02bd\u029c\3\2\2\2\u02bd\u02a1\3\2\2\2\u02bd\u02a6\3\2"+
		"\2\2\u02bd\u02ab\3\2\2\2\u02bd\u02b0\3\2\2\2\u02bd\u02b6\3\2\2\2\u02bd"+
		"\u02b9\3\2\2\2\u02be\u02c1\3\2\2\2\u02bf\u02bd\3\2\2\2\u02bf\u02c0\3\2"+
		"\2\2\u02c0\17\3\2\2\2\u02c1\u02bf\3\2\2\2\37\26\669PS\\x\u008b\u0099\u00aa"+
		"\u00ad\u00de\u00f1\u0141\u0143\u015c\u016a\u017d\u0180\u01b1\u01c2\u0212"+
		"\u0214\u0223\u0236\u0239\u026d\u02bd\u02bf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}