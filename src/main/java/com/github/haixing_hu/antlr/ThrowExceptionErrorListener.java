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
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
      int line, int charPositionInLine, String msg, RecognitionException e) {
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
