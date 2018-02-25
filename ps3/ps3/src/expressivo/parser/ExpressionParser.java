// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, NUMBER=5, VARIABLE=6, SPACES=7;
  public static final int
    RULE_expr = 0, RULE_term = 1, RULE_factor = 2;
  public static final String[] ruleNames = {
    "expr", "term", "factor"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'+'", "'*'", "'('", "')'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, "NUMBER", "VARIABLE", "SPACES"
  };
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
  public String getGrammarFileName() { return "Expression.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public ATN getATN() { return _ATN; }


      // This method makes the lexer or parser stop running if it encounters
      // invalid input and throw a ParseCancellationException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol,
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }

  public ExpressionParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class ExprContext extends ParserRuleContext {
    public TermContext term() {
      return getRuleContext(TermContext.class,0);
    }
    public ExprContext expr() {
      return getRuleContext(ExprContext.class,0);
    }
    public ExprContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_expr; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterExpr(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitExpr(this);
    }
  }

  public final ExprContext expr() throws RecognitionException {
    return expr(0);
  }

  private ExprContext expr(int _p) throws RecognitionException {
    ParserRuleContext _parentctx = _ctx;
    int _parentState = getState();
    ExprContext _localctx = new ExprContext(_ctx, _parentState);
    ExprContext _prevctx = _localctx;
    int _startState = 0;
    enterRecursionRule(_localctx, 0, RULE_expr, _p);
    try {
      int _alt;
      enterOuterAlt(_localctx, 1);
      {
      {
      setState(7);
      term(0);
      }
      _ctx.stop = _input.LT(-1);
      setState(14);
      _errHandler.sync(this);
      _alt = getInterpreter().adaptivePredict(_input,0,_ctx);
      while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
        if ( _alt==1 ) {
          if ( _parseListeners!=null ) triggerExitRuleEvent();
          _prevctx = _localctx;
          {
          {
          _localctx = new ExprContext(_parentctx, _parentState);
          pushNewRecursionContext(_localctx, _startState, RULE_expr);
          setState(9);
          if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
          setState(10);
          match(T__0);
          setState(11);
          term(0);
          }
          } 
        }
        setState(16);
        _errHandler.sync(this);
        _alt = getInterpreter().adaptivePredict(_input,0,_ctx);
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

  public static class TermContext extends ParserRuleContext {
    public FactorContext factor() {
      return getRuleContext(FactorContext.class,0);
    }
    public TermContext term() {
      return getRuleContext(TermContext.class,0);
    }
    public TermContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_term; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterTerm(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitTerm(this);
    }
  }

  public final TermContext term() throws RecognitionException {
    return term(0);
  }

  private TermContext term(int _p) throws RecognitionException {
    ParserRuleContext _parentctx = _ctx;
    int _parentState = getState();
    TermContext _localctx = new TermContext(_ctx, _parentState);
    TermContext _prevctx = _localctx;
    int _startState = 2;
    enterRecursionRule(_localctx, 2, RULE_term, _p);
    try {
      int _alt;
      enterOuterAlt(_localctx, 1);
      {
      {
      setState(18);
      factor();
      }
      _ctx.stop = _input.LT(-1);
      setState(25);
      _errHandler.sync(this);
      _alt = getInterpreter().adaptivePredict(_input,1,_ctx);
      while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
        if ( _alt==1 ) {
          if ( _parseListeners!=null ) triggerExitRuleEvent();
          _prevctx = _localctx;
          {
          {
          _localctx = new TermContext(_parentctx, _parentState);
          pushNewRecursionContext(_localctx, _startState, RULE_term);
          setState(20);
          if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
          setState(21);
          match(T__1);
          setState(22);
          factor();
          }
          } 
        }
        setState(27);
        _errHandler.sync(this);
        _alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

  public static class FactorContext extends ParserRuleContext {
    public TerminalNode NUMBER() { return getToken(ExpressionParser.NUMBER, 0); }
    public TerminalNode VARIABLE() { return getToken(ExpressionParser.VARIABLE, 0); }
    public ExprContext expr() {
      return getRuleContext(ExprContext.class,0);
    }
    public FactorContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_factor; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterFactor(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitFactor(this);
    }
  }

  public final FactorContext factor() throws RecognitionException {
    FactorContext _localctx = new FactorContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_factor);
    try {
      setState(34);
      switch (_input.LA(1)) {
      case NUMBER:
        enterOuterAlt(_localctx, 1);
        {
        setState(28);
        match(NUMBER);
        }
        break;
      case VARIABLE:
        enterOuterAlt(_localctx, 2);
        {
        setState(29);
        match(VARIABLE);
        }
        break;
      case T__2:
        enterOuterAlt(_localctx, 3);
        {
        setState(30);
        match(T__2);
        setState(31);
        expr(0);
        setState(32);
        match(T__3);
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

  public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
    switch (ruleIndex) {
    case 0:
      return expr_sempred((ExprContext)_localctx, predIndex);
    case 1:
      return term_sempred((TermContext)_localctx, predIndex);
    }
    return true;
  }
  private boolean expr_sempred(ExprContext _localctx, int predIndex) {
    switch (predIndex) {
    case 0:
      return precpred(_ctx, 2);
    }
    return true;
  }
  private boolean term_sempred(TermContext _localctx, int predIndex) {
    switch (predIndex) {
    case 1:
      return precpred(_ctx, 2);
    }
    return true;
  }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\t\'\4\2\t\2\4\3"+
      "\t\3\4\4\t\4\3\2\3\2\3\2\3\2\3\2\3\2\7\2\17\n\2\f\2\16\2\22\13\2\3"+
      "\3\3\3\3\3\3\3\3\3\3\3\7\3\32\n\3\f\3\16\3\35\13\3\3\4\3\4\3\4\3\4"+
      "\3\4\3\4\5\4%\n\4\3\4\2\4\2\4\5\2\4\6\2\2\'\2\b\3\2\2\2\4\23\3\2\2"+
      "\2\6$\3\2\2\2\b\t\b\2\1\2\t\n\5\4\3\2\n\20\3\2\2\2\13\f\f\4\2\2\f"+
      "\r\7\3\2\2\r\17\5\4\3\2\16\13\3\2\2\2\17\22\3\2\2\2\20\16\3\2\2\2"+
      "\20\21\3\2\2\2\21\3\3\2\2\2\22\20\3\2\2\2\23\24\b\3\1\2\24\25\5\6"+
      "\4\2\25\33\3\2\2\2\26\27\f\4\2\2\27\30\7\4\2\2\30\32\5\6\4\2\31\26"+
      "\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\5\3\2\2\2\35"+
      "\33\3\2\2\2\36%\7\7\2\2\37%\7\b\2\2 !\7\5\2\2!\"\5\2\2\2\"#\7\6\2"+
      "\2#%\3\2\2\2$\36\3\2\2\2$\37\3\2\2\2$ \3\2\2\2%\7\3\2\2\2\5\20\33"+
      "$";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}