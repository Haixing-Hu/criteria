/*
 * Copyright (C) 2014 Haixing Hu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

grammar Criterion;

/**
 * If your top-level rule does not end with an explicit EOF, then ANTLR is not 
 * required to parse to the end of the input sequence. Rather than throw an 
 * exception, it simply parsed the valid portion of the sequence you gave it.
 */
line: criterion EOF;
 
criterion:
	unaryCriterion
  | binaryCriterion
  | valueCriterion
  | collectionCriterion
  | likeCriterion
  | combinedCriterion
;

unaryCriterion:
	property operator=(IS_NULL | IS_NOT_NULL)
;

binaryCriterion:
	property operator=(EQUAL | NOT_EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL) property
;

valueCriterion:
	property operator=(EQUAL | NOT_EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL) value
;

collectionCriterion:
	property operator=(IN | NOT_IN) '(' value (',' value)* ')'
;

likeCriterion:
	property LIKE pattern
;

combinedCriterion :
	'(' criterion ')' operator=AND '(' criterion ')' (AND '(' criterion ')')*
  | '(' criterion ')' operator=OR '(' criterion ')' (OR '(' criterion ')')*
;

property:
	ID
;

value:
	type=(INTEGER_NUMBER
  		| HEX_NUMBER
  		| REAL_NUMBER
  		| BOOLEAN
  		| STRING
  		| PLACEHOLDER)
;

pattern:
	STRING
;

fragment A:('a'|'A');
fragment B:('b'|'B');
fragment C:('c'|'C');
fragment D:('d'|'D');
fragment E:('e'|'E');
fragment F:('f'|'F');
fragment G:('g'|'G');
fragment H:('h'|'H');
fragment I:('i'|'I');
fragment J:('j'|'J');
fragment K:('k'|'K');
fragment L:('l'|'L');
fragment M:('m'|'M');
fragment N:('n'|'N');
fragment O:('o'|'O');
fragment P:('p'|'P');
fragment Q:('q'|'Q');
fragment R:('r'|'R');
fragment S:('s'|'S');
fragment T:('t'|'T');
fragment U:('u'|'U');
fragment V:('v'|'V');
fragment W:('w'|'W');
fragment X:('x'|'X');
fragment Y:('y'|'Y');
fragment Z:('z'|'Z');

IS: I S;
NOT: N O T;
NULL: N U L L;

IS_NULL: IS (SPACE)+ NULL;
IS_NOT_NULL: IS (SPACE)+ NOT (SPACE)+ NULL;

EQUAL: '=';
NOT_EQUAL: '!=';
GREATER: '>';
GREATER_EQUAL: '>=';
LESS: '<';
LESS_EQUAL: '<=';

IN:	I N;
NOT_IN: NOT (SPACE)+ IN;

AND: A N D;
OR: O R;

LIKE: L I K E;

fragment SINGLE_QUOTE_ESCAPE: '\\\\' | '\\\'';
fragment DOUBLE_QUOTE_ESCAPE: '\\\\' | '\\"';
fragment NON_LINE_BREAK: ~('\n' | '\r');
STRING:
	'\'' ( SINGLE_QUOTE_ESCAPE | NON_LINE_BREAK )*? '\''
;

BOOLEAN:
	T R U E
  | F A L S E
;

PLACEHOLDER:
	'?'
;

fragment LETTER: [a-zA-Z_];
fragment DIGIT: [0-9];
ID: LETTER (LETTER | DIGIT)*;

fragment SIGN: ('+' | '-');
INTEGER_NUMBER:
	SIGN? DIGIT+
;

fragment HEX_LETTER: [a-fA-F];
HEX_NUMBER:
	'0x' (HEX_LETTER | DIGIT)+
;

fragment EXP: ('E'|'e');
REAL_NUMBER:
	SIGN? DIGIT+ '.' DIGIT* (EXP SIGN? DIGIT+)?
  | SIGN? '.' DIGIT+  (EXP SIGN? DIGIT+)?
  | SIGN? DIGIT+ EXP SIGN? DIGIT+
;

SPACE: [ \t] -> skip;

WHITE_SPACE:
	[ \r\t\n]+ -> skip
;
