// Generated from com/github/haixing_hu/criteria/parser/sql/Criterion.g4 by ANTLR 4.3
package com.github.haixing_hu.criteria.parser.sql;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CriterionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__2=1, T__1=2, T__0=3, IS=4, NOT=5, NULL=6, IS_NULL=7, IS_NOT_NULL=8, 
		EQUAL=9, NOT_EQUAL=10, GREATER=11, GREATER_EQUAL=12, LESS=13, LESS_EQUAL=14, 
		IN=15, NOT_IN=16, AND=17, OR=18, LIKE=19, STRING=20, BOOLEAN=21, PLACEHOLDER=22, 
		ID=23, INTEGER_NUMBER=24, HEX_NUMBER=25, REAL_NUMBER=26, SPACE=27, WHITE_SPACE=28;
	public static final String[] tokenNames = {
		"<INVALID>", "'('", "')'", "','", "IS", "NOT", "NULL", "IS_NULL", "IS_NOT_NULL", 
		"'='", "'!='", "'>'", "'>='", "'<'", "'<='", "IN", "NOT_IN", "AND", "OR", 
		"LIKE", "STRING", "BOOLEAN", "'?'", "ID", "INTEGER_NUMBER", "HEX_NUMBER", 
		"REAL_NUMBER", "SPACE", "WHITE_SPACE"
	};
	public static final int
		RULE_line = 0, RULE_criterion = 1, RULE_unaryCriterion = 2, RULE_binaryCriterion = 3, 
		RULE_valueCriterion = 4, RULE_collectionCriterion = 5, RULE_likeCriterion = 6, 
		RULE_combinedCriterion = 7, RULE_property = 8, RULE_value = 9, RULE_pattern = 10;
	public static final String[] ruleNames = {
		"line", "criterion", "unaryCriterion", "binaryCriterion", "valueCriterion", 
		"collectionCriterion", "likeCriterion", "combinedCriterion", "property", 
		"value", "pattern"
	};

	@Override
	public String getGrammarFileName() { return "Criterion.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CriterionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class LineContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CriterionParser.EOF, 0); }
		public CriterionContext criterion() {
			return getRuleContext(CriterionContext.class,0);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).exitLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CriterionVisitor ) return ((CriterionVisitor<? extends T>)visitor).visitLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_line);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22); criterion();
			setState(23); match(EOF);
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

	public static class CriterionContext extends ParserRuleContext {
		public CollectionCriterionContext collectionCriterion() {
			return getRuleContext(CollectionCriterionContext.class,0);
		}
		public LikeCriterionContext likeCriterion() {
			return getRuleContext(LikeCriterionContext.class,0);
		}
		public UnaryCriterionContext unaryCriterion() {
			return getRuleContext(UnaryCriterionContext.class,0);
		}
		public ValueCriterionContext valueCriterion() {
			return getRuleContext(ValueCriterionContext.class,0);
		}
		public CombinedCriterionContext combinedCriterion() {
			return getRuleContext(CombinedCriterionContext.class,0);
		}
		public BinaryCriterionContext binaryCriterion() {
			return getRuleContext(BinaryCriterionContext.class,0);
		}
		public CriterionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_criterion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).enterCriterion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).exitCriterion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CriterionVisitor ) return ((CriterionVisitor<? extends T>)visitor).visitCriterion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CriterionContext criterion() throws RecognitionException {
		CriterionContext _localctx = new CriterionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_criterion);
		try {
			setState(31);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(25); unaryCriterion();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(26); binaryCriterion();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(27); valueCriterion();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(28); collectionCriterion();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(29); likeCriterion();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(30); combinedCriterion();
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

	public static class UnaryCriterionContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode IS_NOT_NULL() { return getToken(CriterionParser.IS_NOT_NULL, 0); }
		public TerminalNode IS_NULL() { return getToken(CriterionParser.IS_NULL, 0); }
		public PropertyContext property() {
			return getRuleContext(PropertyContext.class,0);
		}
		public UnaryCriterionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryCriterion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).enterUnaryCriterion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).exitUnaryCriterion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CriterionVisitor ) return ((CriterionVisitor<? extends T>)visitor).visitUnaryCriterion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryCriterionContext unaryCriterion() throws RecognitionException {
		UnaryCriterionContext _localctx = new UnaryCriterionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_unaryCriterion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); property();
			setState(34);
			((UnaryCriterionContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==IS_NULL || _la==IS_NOT_NULL) ) {
				((UnaryCriterionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
			}
			consume();
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

	public static class BinaryCriterionContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode LESS() { return getToken(CriterionParser.LESS, 0); }
		public TerminalNode EQUAL() { return getToken(CriterionParser.EQUAL, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(CriterionParser.LESS_EQUAL, 0); }
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public TerminalNode NOT_EQUAL() { return getToken(CriterionParser.NOT_EQUAL, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(CriterionParser.GREATER_EQUAL, 0); }
		public TerminalNode GREATER() { return getToken(CriterionParser.GREATER, 0); }
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public BinaryCriterionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryCriterion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).enterBinaryCriterion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).exitBinaryCriterion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CriterionVisitor ) return ((CriterionVisitor<? extends T>)visitor).visitBinaryCriterion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryCriterionContext binaryCriterion() throws RecognitionException {
		BinaryCriterionContext _localctx = new BinaryCriterionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_binaryCriterion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); property();
			setState(37);
			((BinaryCriterionContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUAL) | (1L << NOT_EQUAL) | (1L << GREATER) | (1L << GREATER_EQUAL) | (1L << LESS) | (1L << LESS_EQUAL))) != 0)) ) {
				((BinaryCriterionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
			}
			consume();
			setState(38); property();
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

	public static class ValueCriterionContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode LESS() { return getToken(CriterionParser.LESS, 0); }
		public TerminalNode EQUAL() { return getToken(CriterionParser.EQUAL, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(CriterionParser.LESS_EQUAL, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode NOT_EQUAL() { return getToken(CriterionParser.NOT_EQUAL, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(CriterionParser.GREATER_EQUAL, 0); }
		public TerminalNode GREATER() { return getToken(CriterionParser.GREATER, 0); }
		public PropertyContext property() {
			return getRuleContext(PropertyContext.class,0);
		}
		public ValueCriterionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueCriterion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).enterValueCriterion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).exitValueCriterion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CriterionVisitor ) return ((CriterionVisitor<? extends T>)visitor).visitValueCriterion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueCriterionContext valueCriterion() throws RecognitionException {
		ValueCriterionContext _localctx = new ValueCriterionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_valueCriterion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40); property();
			setState(41);
			((ValueCriterionContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUAL) | (1L << NOT_EQUAL) | (1L << GREATER) | (1L << GREATER_EQUAL) | (1L << LESS) | (1L << LESS_EQUAL))) != 0)) ) {
				((ValueCriterionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
			}
			consume();
			setState(42); value();
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

	public static class CollectionCriterionContext extends ParserRuleContext {
		public Token operator;
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode NOT_IN() { return getToken(CriterionParser.NOT_IN, 0); }
		public TerminalNode IN() { return getToken(CriterionParser.IN, 0); }
		public PropertyContext property() {
			return getRuleContext(PropertyContext.class,0);
		}
		public CollectionCriterionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collectionCriterion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).enterCollectionCriterion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).exitCollectionCriterion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CriterionVisitor ) return ((CriterionVisitor<? extends T>)visitor).visitCollectionCriterion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CollectionCriterionContext collectionCriterion() throws RecognitionException {
		CollectionCriterionContext _localctx = new CollectionCriterionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_collectionCriterion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44); property();
			setState(45);
			((CollectionCriterionContext)_localctx).operator = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==IN || _la==NOT_IN) ) {
				((CollectionCriterionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
			}
			consume();
			setState(46); match(T__2);
			setState(47); value();
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(48); match(T__0);
				setState(49); value();
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55); match(T__1);
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

	public static class LikeCriterionContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public TerminalNode LIKE() { return getToken(CriterionParser.LIKE, 0); }
		public PropertyContext property() {
			return getRuleContext(PropertyContext.class,0);
		}
		public LikeCriterionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_likeCriterion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).enterLikeCriterion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).exitLikeCriterion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CriterionVisitor ) return ((CriterionVisitor<? extends T>)visitor).visitLikeCriterion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LikeCriterionContext likeCriterion() throws RecognitionException {
		LikeCriterionContext _localctx = new LikeCriterionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_likeCriterion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57); property();
			setState(58); match(LIKE);
			setState(59); pattern();
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

	public static class CombinedCriterionContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode AND(int i) {
			return getToken(CriterionParser.AND, i);
		}
		public List<TerminalNode> AND() { return getTokens(CriterionParser.AND); }
		public List<TerminalNode> OR() { return getTokens(CriterionParser.OR); }
		public List<CriterionContext> criterion() {
			return getRuleContexts(CriterionContext.class);
		}
		public TerminalNode OR(int i) {
			return getToken(CriterionParser.OR, i);
		}
		public CriterionContext criterion(int i) {
			return getRuleContext(CriterionContext.class,i);
		}
		public CombinedCriterionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_combinedCriterion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).enterCombinedCriterion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).exitCombinedCriterion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CriterionVisitor ) return ((CriterionVisitor<? extends T>)visitor).visitCombinedCriterion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CombinedCriterionContext combinedCriterion() throws RecognitionException {
		CombinedCriterionContext _localctx = new CombinedCriterionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_combinedCriterion);
		int _la;
		try {
			setState(95);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61); match(T__2);
				setState(62); criterion();
				setState(63); match(T__1);
				setState(64); ((CombinedCriterionContext)_localctx).operator = match(AND);
				setState(65); match(T__2);
				setState(66); criterion();
				setState(67); match(T__1);
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AND) {
					{
					{
					setState(68); match(AND);
					setState(69); match(T__2);
					setState(70); criterion();
					setState(71); match(T__1);
					}
					}
					setState(77);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78); match(T__2);
				setState(79); criterion();
				setState(80); match(T__1);
				setState(81); ((CombinedCriterionContext)_localctx).operator = match(OR);
				setState(82); match(T__2);
				setState(83); criterion();
				setState(84); match(T__1);
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OR) {
					{
					{
					setState(85); match(OR);
					setState(86); match(T__2);
					setState(87); criterion();
					setState(88); match(T__1);
					}
					}
					setState(94);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class PropertyContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CriterionParser.ID, 0); }
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CriterionVisitor ) return ((CriterionVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97); match(ID);
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

	public static class ValueContext extends ParserRuleContext {
		public Token type;
		public TerminalNode HEX_NUMBER() { return getToken(CriterionParser.HEX_NUMBER, 0); }
		public TerminalNode REAL_NUMBER() { return getToken(CriterionParser.REAL_NUMBER, 0); }
		public TerminalNode STRING() { return getToken(CriterionParser.STRING, 0); }
		public TerminalNode BOOLEAN() { return getToken(CriterionParser.BOOLEAN, 0); }
		public TerminalNode PLACEHOLDER() { return getToken(CriterionParser.PLACEHOLDER, 0); }
		public TerminalNode INTEGER_NUMBER() { return getToken(CriterionParser.INTEGER_NUMBER, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CriterionVisitor ) return ((CriterionVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			((ValueContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << BOOLEAN) | (1L << PLACEHOLDER) | (1L << INTEGER_NUMBER) | (1L << HEX_NUMBER) | (1L << REAL_NUMBER))) != 0)) ) {
				((ValueContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			consume();
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

	public static class PatternContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CriterionParser.STRING, 0); }
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CriterionListener ) ((CriterionListener)listener).exitPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CriterionVisitor ) return ((CriterionVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); match(STRING);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\36j\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3\"\n\3\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7\65\n\7\f\7\16\7"+
		"8\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\7\tL\n\t\f\t\16\tO\13\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\7\t]\n\t\f\t\16\t`\13\t\5\tb\n\t\3\n\3\n\3\13\3\13\3\f\3\f"+
		"\3\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26\2\6\3\2\t\n\3\2\13\20\3\2\21\22"+
		"\4\2\26\30\32\34g\2\30\3\2\2\2\4!\3\2\2\2\6#\3\2\2\2\b&\3\2\2\2\n*\3\2"+
		"\2\2\f.\3\2\2\2\16;\3\2\2\2\20a\3\2\2\2\22c\3\2\2\2\24e\3\2\2\2\26g\3"+
		"\2\2\2\30\31\5\4\3\2\31\32\7\2\2\3\32\3\3\2\2\2\33\"\5\6\4\2\34\"\5\b"+
		"\5\2\35\"\5\n\6\2\36\"\5\f\7\2\37\"\5\16\b\2 \"\5\20\t\2!\33\3\2\2\2!"+
		"\34\3\2\2\2!\35\3\2\2\2!\36\3\2\2\2!\37\3\2\2\2! \3\2\2\2\"\5\3\2\2\2"+
		"#$\5\22\n\2$%\t\2\2\2%\7\3\2\2\2&\'\5\22\n\2\'(\t\3\2\2()\5\22\n\2)\t"+
		"\3\2\2\2*+\5\22\n\2+,\t\3\2\2,-\5\24\13\2-\13\3\2\2\2./\5\22\n\2/\60\t"+
		"\4\2\2\60\61\7\3\2\2\61\66\5\24\13\2\62\63\7\5\2\2\63\65\5\24\13\2\64"+
		"\62\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28\66\3"+
		"\2\2\29:\7\4\2\2:\r\3\2\2\2;<\5\22\n\2<=\7\25\2\2=>\5\26\f\2>\17\3\2\2"+
		"\2?@\7\3\2\2@A\5\4\3\2AB\7\4\2\2BC\7\23\2\2CD\7\3\2\2DE\5\4\3\2EM\7\4"+
		"\2\2FG\7\23\2\2GH\7\3\2\2HI\5\4\3\2IJ\7\4\2\2JL\3\2\2\2KF\3\2\2\2LO\3"+
		"\2\2\2MK\3\2\2\2MN\3\2\2\2Nb\3\2\2\2OM\3\2\2\2PQ\7\3\2\2QR\5\4\3\2RS\7"+
		"\4\2\2ST\7\24\2\2TU\7\3\2\2UV\5\4\3\2V^\7\4\2\2WX\7\24\2\2XY\7\3\2\2Y"+
		"Z\5\4\3\2Z[\7\4\2\2[]\3\2\2\2\\W\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2"+
		"\2_b\3\2\2\2`^\3\2\2\2a?\3\2\2\2aP\3\2\2\2b\21\3\2\2\2cd\7\31\2\2d\23"+
		"\3\2\2\2ef\t\5\2\2f\25\3\2\2\2gh\7\26\2\2h\27\3\2\2\2\7!\66M^a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}