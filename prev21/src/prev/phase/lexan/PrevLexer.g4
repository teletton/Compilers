lexer grammar PrevLexer;

@header {
	package prev.phase.lexan;
	import prev.common.report.*;
	import prev.data.sym.*;
}

@members {
    @Override
	public Token nextToken() {

		return (Token) super.nextToken();
	}
}

VOID : 'void' ;
BOOLCONST : 'true' | 'false' ;
STRINGCONST : ["](('\u0020'..'\u0021') | ('\u0023'..'\u007E') | ('\u005C''\u0022'))*["] ;
CHARCONST : ['](('\u005C''\u0027') | ('\u0020'..'\u0026') | ('\u0028'..'\u007E') )['] ;
WHITESPACE : (' ' | '\t' | '\n' | '\r')+ -> skip ;
COMMENT : [#]('\u0020'..'\u007E')*('\n' | EOF) -> skip;
RETURN : 'return';
LPAREN : '(' ;
RPAREN : ')' ;
LBRACKET : '[' ;
RBRACKET : ']' ;
LBRACE  : '{' ;
RBRACE : '}' ;
DOT : '.' ;
COMMA : ',' ;
SEMIC : ';' ;
COLON : ':' ;
AND : '&' ;
NOT : '!' ;
OR : '|' ;
EQU : '==' ;
NEQ : '!=' ;
LTH : '<' ;
GTH : '>' ;
LEQ : '<=' ;
GEQ : '>=' ;
ADD : '+' ;
SUB : '-' ;
MUL : '*' ;
DIV : '/' ;
MOD : '%' ;
PTR : '^' ;
IS : '=' ;
CHAR : 'char' ;
INT : 'int' ;
BOOL : 'bool' ;
DEL : 'del' ;
DO : 'do' ;
ELSE : 'else' ;
FUN : 'fun' ;
IF : 'if' ;
NEW : 'new' ;
THEN : 'then' ;
TYP : 'typ' ;
VAR : 'var' ;
WHERE : 'where';
WHILE : 'while';
VOIDCONST : 'none';



POINTERCONST : 'nil' ;
IDENTIFIER :  ( ([A-Z]|[a-z]|[_])([A-Z]|[a-z]|[_]|[0-9])* | ((INT | WHERE | WHILE | TYP | THEN | FUN | ELSE | DO | VOID | VAR | NEW | CHAR | BOOL | DEL | IF | BOOLCONST | VOIDCONST | POINTERCONST | RETURN) ([A-Z]|[a-z]|[_])([A-Z]|[a-z]|[_][0-9])+) ) ;
INTCONST : [0-9]+ ;
UNKNOWN : .{
if(true) {throw new Report.Error("LEXICAL ANALYSIS ERROR! Check this at location:" + this.getLine() + ":" +  this.getCharPositionInLine());}
};

