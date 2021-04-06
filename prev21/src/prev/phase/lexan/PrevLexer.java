// Generated from lexan/PrevLexer.g4 by ANTLR 4.9.1

	package prev.phase.lexan;
	import prev.common.report.*;
	import prev.data.sym.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrevLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"VOID", "BOOLCONST", "STRINGCONST", "CHARCONST", "WHITESPACE", "COMMENT", 
			"RETURN", "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", "LBRACE", "RBRACE", 
			"DOT", "COMMA", "SEMIC", "COLON", "AND", "NOT", "OR", "EQU", "NEQ", "LTH", 
			"GTH", "LEQ", "GEQ", "ADD", "SUB", "MUL", "DIV", "MOD", "PTR", "IS", 
			"CHAR", "INT", "BOOL", "DEL", "DO", "ELSE", "FUN", "IF", "NEW", "THEN", 
			"TYP", "VAR", "WHERE", "WHILE", "VOIDCONST", "POINTERCONST", "IDENTIFIER", 
			"INTCONST", "UNKNOWN"
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
		public Token nextToken() {

			return (Token) super.nextToken();
		}


	public PrevLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PrevLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 51:
			UNKNOWN_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void UNKNOWN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			if(true) {throw new Report.Error("LEXICAL ANALYSIS ERROR! Check this at location:" + this.getLine() + ":" +  this.getCharPositionInLine());}

			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\66\u0159\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\5\3z\n\3\3\4\3\4\3\4\3\4\7\4\u0080\n\4\f\4\16\4\u0083\13\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\5\5\u008b\n\5\3\5\3\5\3\6\6\6\u0090\n\6\r\6\16\6\u0091"+
		"\3\6\3\6\3\7\3\7\7\7\u0098\n\7\f\7\16\7\u009b\13\7\3\7\5\7\u009e\n\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3"+
		" \3!\3!\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&"+
		"\3\'\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3+\3+\3+\3+\3,\3,\3,"+
		"\3,\3,\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3"+
		"\60\3\60\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\63\5\63\u0129"+
		"\n\63\3\63\7\63\u012c\n\63\f\63\16\63\u012f\13\63\3\63\3\63\3\63\3\63"+
		"\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\5\63\u0144\n\63\3\63\5\63\u0147\n\63\3\63\3\63\3\63\6\63\u014c\n"+
		"\63\r\63\16\63\u014d\5\63\u0150\n\63\3\64\6\64\u0153\n\64\r\64\16\64\u0154"+
		"\3\65\3\65\3\65\2\2\66\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63"+
		"e\64g\65i\66\3\2\16\3\2$$\4\2\"#%\u0080\3\2))\4\2\"(*\u0080\5\2\13\f\17"+
		"\17\"\"\3\2%%\3\3\f\f\5\2C\\aac|\6\2\62;C\\aac|\4\2C\\c|\3\2aa\3\2\62"+
		";\2\u0175\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2"+
		"\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\3k\3\2\2\2"+
		"\5y\3\2\2\2\7{\3\2\2\2\t\u0086\3\2\2\2\13\u008f\3\2\2\2\r\u0095\3\2\2"+
		"\2\17\u00a1\3\2\2\2\21\u00a8\3\2\2\2\23\u00aa\3\2\2\2\25\u00ac\3\2\2\2"+
		"\27\u00ae\3\2\2\2\31\u00b0\3\2\2\2\33\u00b2\3\2\2\2\35\u00b4\3\2\2\2\37"+
		"\u00b6\3\2\2\2!\u00b8\3\2\2\2#\u00ba\3\2\2\2%\u00bc\3\2\2\2\'\u00be\3"+
		"\2\2\2)\u00c0\3\2\2\2+\u00c2\3\2\2\2-\u00c5\3\2\2\2/\u00c8\3\2\2\2\61"+
		"\u00ca\3\2\2\2\63\u00cc\3\2\2\2\65\u00cf\3\2\2\2\67\u00d2\3\2\2\29\u00d4"+
		"\3\2\2\2;\u00d6\3\2\2\2=\u00d8\3\2\2\2?\u00da\3\2\2\2A\u00dc\3\2\2\2C"+
		"\u00de\3\2\2\2E\u00e0\3\2\2\2G\u00e5\3\2\2\2I\u00e9\3\2\2\2K\u00ee\3\2"+
		"\2\2M\u00f2\3\2\2\2O\u00f5\3\2\2\2Q\u00fa\3\2\2\2S\u00fe\3\2\2\2U\u0101"+
		"\3\2\2\2W\u0105\3\2\2\2Y\u010a\3\2\2\2[\u010e\3\2\2\2]\u0112\3\2\2\2_"+
		"\u0118\3\2\2\2a\u011e\3\2\2\2c\u0123\3\2\2\2e\u014f\3\2\2\2g\u0152\3\2"+
		"\2\2i\u0156\3\2\2\2kl\7x\2\2lm\7q\2\2mn\7k\2\2no\7f\2\2o\4\3\2\2\2pq\7"+
		"v\2\2qr\7t\2\2rs\7w\2\2sz\7g\2\2tu\7h\2\2uv\7c\2\2vw\7n\2\2wx\7u\2\2x"+
		"z\7g\2\2yp\3\2\2\2yt\3\2\2\2z\6\3\2\2\2{\u0081\t\2\2\2|\u0080\t\3\2\2"+
		"}~\7^\2\2~\u0080\7$\2\2\177|\3\2\2\2\177}\3\2\2\2\u0080\u0083\3\2\2\2"+
		"\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083\u0081"+
		"\3\2\2\2\u0084\u0085\t\2\2\2\u0085\b\3\2\2\2\u0086\u008a\t\4\2\2\u0087"+
		"\u0088\7^\2\2\u0088\u008b\7)\2\2\u0089\u008b\t\5\2\2\u008a\u0087\3\2\2"+
		"\2\u008a\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\t\4\2\2\u008d\n"+
		"\3\2\2\2\u008e\u0090\t\6\2\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\b\6"+
		"\2\2\u0094\f\3\2\2\2\u0095\u0099\t\7\2\2\u0096\u0098\4\"\u0080\2\u0097"+
		"\u0096\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2"+
		"\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u009e\t\b\2\2\u009d"+
		"\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\b\7\2\2\u00a0\16\3\2\2"+
		"\2\u00a1\u00a2\7t\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5"+
		"\7w\2\2\u00a5\u00a6\7t\2\2\u00a6\u00a7\7p\2\2\u00a7\20\3\2\2\2\u00a8\u00a9"+
		"\7*\2\2\u00a9\22\3\2\2\2\u00aa\u00ab\7+\2\2\u00ab\24\3\2\2\2\u00ac\u00ad"+
		"\7]\2\2\u00ad\26\3\2\2\2\u00ae\u00af\7_\2\2\u00af\30\3\2\2\2\u00b0\u00b1"+
		"\7}\2\2\u00b1\32\3\2\2\2\u00b2\u00b3\7\177\2\2\u00b3\34\3\2\2\2\u00b4"+
		"\u00b5\7\60\2\2\u00b5\36\3\2\2\2\u00b6\u00b7\7.\2\2\u00b7 \3\2\2\2\u00b8"+
		"\u00b9\7=\2\2\u00b9\"\3\2\2\2\u00ba\u00bb\7<\2\2\u00bb$\3\2\2\2\u00bc"+
		"\u00bd\7(\2\2\u00bd&\3\2\2\2\u00be\u00bf\7#\2\2\u00bf(\3\2\2\2\u00c0\u00c1"+
		"\7~\2\2\u00c1*\3\2\2\2\u00c2\u00c3\7?\2\2\u00c3\u00c4\7?\2\2\u00c4,\3"+
		"\2\2\2\u00c5\u00c6\7#\2\2\u00c6\u00c7\7?\2\2\u00c7.\3\2\2\2\u00c8\u00c9"+
		"\7>\2\2\u00c9\60\3\2\2\2\u00ca\u00cb\7@\2\2\u00cb\62\3\2\2\2\u00cc\u00cd"+
		"\7>\2\2\u00cd\u00ce\7?\2\2\u00ce\64\3\2\2\2\u00cf\u00d0\7@\2\2\u00d0\u00d1"+
		"\7?\2\2\u00d1\66\3\2\2\2\u00d2\u00d3\7-\2\2\u00d38\3\2\2\2\u00d4\u00d5"+
		"\7/\2\2\u00d5:\3\2\2\2\u00d6\u00d7\7,\2\2\u00d7<\3\2\2\2\u00d8\u00d9\7"+
		"\61\2\2\u00d9>\3\2\2\2\u00da\u00db\7\'\2\2\u00db@\3\2\2\2\u00dc\u00dd"+
		"\7`\2\2\u00ddB\3\2\2\2\u00de\u00df\7?\2\2\u00dfD\3\2\2\2\u00e0\u00e1\7"+
		"e\2\2\u00e1\u00e2\7j\2\2\u00e2\u00e3\7c\2\2\u00e3\u00e4\7t\2\2\u00e4F"+
		"\3\2\2\2\u00e5\u00e6\7k\2\2\u00e6\u00e7\7p\2\2\u00e7\u00e8\7v\2\2\u00e8"+
		"H\3\2\2\2\u00e9\u00ea\7d\2\2\u00ea\u00eb\7q\2\2\u00eb\u00ec\7q\2\2\u00ec"+
		"\u00ed\7n\2\2\u00edJ\3\2\2\2\u00ee\u00ef\7f\2\2\u00ef\u00f0\7g\2\2\u00f0"+
		"\u00f1\7n\2\2\u00f1L\3\2\2\2\u00f2\u00f3\7f\2\2\u00f3\u00f4\7q\2\2\u00f4"+
		"N\3\2\2\2\u00f5\u00f6\7g\2\2\u00f6\u00f7\7n\2\2\u00f7\u00f8\7u\2\2\u00f8"+
		"\u00f9\7g\2\2\u00f9P\3\2\2\2\u00fa\u00fb\7h\2\2\u00fb\u00fc\7w\2\2\u00fc"+
		"\u00fd\7p\2\2\u00fdR\3\2\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100\7h\2\2\u0100"+
		"T\3\2\2\2\u0101\u0102\7p\2\2\u0102\u0103\7g\2\2\u0103\u0104\7y\2\2\u0104"+
		"V\3\2\2\2\u0105\u0106\7v\2\2\u0106\u0107\7j\2\2\u0107\u0108\7g\2\2\u0108"+
		"\u0109\7p\2\2\u0109X\3\2\2\2\u010a\u010b\7v\2\2\u010b\u010c\7{\2\2\u010c"+
		"\u010d\7r\2\2\u010dZ\3\2\2\2\u010e\u010f\7x\2\2\u010f\u0110\7c\2\2\u0110"+
		"\u0111\7t\2\2\u0111\\\3\2\2\2\u0112\u0113\7y\2\2\u0113\u0114\7j\2\2\u0114"+
		"\u0115\7g\2\2\u0115\u0116\7t\2\2\u0116\u0117\7g\2\2\u0117^\3\2\2\2\u0118"+
		"\u0119\7y\2\2\u0119\u011a\7j\2\2\u011a\u011b\7k\2\2\u011b\u011c\7n\2\2"+
		"\u011c\u011d\7g\2\2\u011d`\3\2\2\2\u011e\u011f\7p\2\2\u011f\u0120\7q\2"+
		"\2\u0120\u0121\7p\2\2\u0121\u0122\7g\2\2\u0122b\3\2\2\2\u0123\u0124\7"+
		"p\2\2\u0124\u0125\7k\2\2\u0125\u0126\7n\2\2\u0126d\3\2\2\2\u0127\u0129"+
		"\t\t\2\2\u0128\u0127\3\2\2\2\u0129\u012d\3\2\2\2\u012a\u012c\t\n\2\2\u012b"+
		"\u012a\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2"+
		"\2\2\u012e\u0150\3\2\2\2\u012f\u012d\3\2\2\2\u0130\u0144\5G$\2\u0131\u0144"+
		"\5]/\2\u0132\u0144\5_\60\2\u0133\u0144\5Y-\2\u0134\u0144\5W,\2\u0135\u0144"+
		"\5Q)\2\u0136\u0144\5O(\2\u0137\u0144\5M\'\2\u0138\u0144\5\3\2\2\u0139"+
		"\u0144\5[.\2\u013a\u0144\5U+\2\u013b\u0144\5E#\2\u013c\u0144\5I%\2\u013d"+
		"\u0144\5K&\2\u013e\u0144\5S*\2\u013f\u0144\5\5\3\2\u0140\u0144\5a\61\2"+
		"\u0141\u0144\5c\62\2\u0142\u0144\5\17\b\2\u0143\u0130\3\2\2\2\u0143\u0131"+
		"\3\2\2\2\u0143\u0132\3\2\2\2\u0143\u0133\3\2\2\2\u0143\u0134\3\2\2\2\u0143"+
		"\u0135\3\2\2\2\u0143\u0136\3\2\2\2\u0143\u0137\3\2\2\2\u0143\u0138\3\2"+
		"\2\2\u0143\u0139\3\2\2\2\u0143\u013a\3\2\2\2\u0143\u013b\3\2\2\2\u0143"+
		"\u013c\3\2\2\2\u0143\u013d\3\2\2\2\u0143\u013e\3\2\2\2\u0143\u013f\3\2"+
		"\2\2\u0143\u0140\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0142\3\2\2\2\u0144"+
		"\u0146\3\2\2\2\u0145\u0147\t\t\2\2\u0146\u0145\3\2\2\2\u0147\u014b\3\2"+
		"\2\2\u0148\u014c\t\13\2\2\u0149\u014a\t\f\2\2\u014a\u014c\t\r\2\2\u014b"+
		"\u0148\3\2\2\2\u014b\u0149\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014b\3\2"+
		"\2\2\u014d\u014e\3\2\2\2\u014e\u0150\3\2\2\2\u014f\u0128\3\2\2\2\u014f"+
		"\u0143\3\2\2\2\u0150f\3\2\2\2\u0151\u0153\t\r\2\2\u0152\u0151\3\2\2\2"+
		"\u0153\u0154\3\2\2\2\u0154\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155h\3"+
		"\2\2\2\u0156\u0157\13\2\2\2\u0157\u0158\b\65\3\2\u0158j\3\2\2\2\23\2y"+
		"\177\u0081\u008a\u0091\u0099\u009d\u0128\u012b\u012d\u0143\u0146\u014b"+
		"\u014d\u014f\u0154\4\b\2\2\3\65\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}