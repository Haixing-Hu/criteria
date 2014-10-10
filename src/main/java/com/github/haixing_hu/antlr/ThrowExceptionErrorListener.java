/******************************************************************************
 *
 * Copyright (c) 2014  Haixing Hu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/
package com.github.haixing_hu.antlr;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.ParseCancellationException;

/**
 * An error listener which throws a {@link ParseCancellationException} when any
 * error occurs.
 *
 * @author Haixing Hu
 */
public class ThrowExceptionErrorListener extends BaseErrorListener {

  @Override
  public final void syntaxError(final Recognizer<?, ?> recognizer,
      final Object offendingSymbol, final int line,
      final int charPositionInLine, final String msg,
      final RecognitionException e) {
    String input;
    if (recognizer instanceof Lexer) {
      final CharStream cs = ((Lexer) recognizer).getInputStream();
      input = cs.getText(new Interval(0, cs.size()));
    } else if (recognizer instanceof Parser) {
      final TokenStream tokens = ((Parser) recognizer).getInputStream();
      if (tokens != null) {
        input = tokens.getText();
      } else {
        input = "<unknown input>";
      }
    } else {
      input = "<unknown input>";
    }
    throw new AntlrParseException(input, line, charPositionInLine, msg);
  }
}
