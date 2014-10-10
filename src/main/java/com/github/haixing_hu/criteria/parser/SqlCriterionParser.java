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
package com.github.haixing_hu.criteria.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.github.haixing_hu.antlr.AntlrParseException;
import com.github.haixing_hu.antlr.ThrowExceptionErrorListener;
import com.github.haixing_hu.criteria.Criterion;
import com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter;
import com.github.haixing_hu.criteria.parser.sql.CriterionLexer;
import com.github.haixing_hu.criteria.parser.sql.CriterionParser;
import com.github.haixing_hu.criteria.parser.sql.CriterionParsingVisitor;
import com.github.haixing_hu.text.Parser;
import com.github.haixing_hu.text.ParsingException;


/**
 * A parser used to parse a criterion from its SQL representation.
 * <p>
 * <b>NOTE:</b> This parser can only recognize the string representation of
 * criteria formatted by the {@link SqlCriterionFormatter} class.
 * <p>
 * <b>NOTE:</b> In order to distinguish between character literal and string
 * literal, we make the following conventions:
 * <ul>
 * <li>the character literals are quoted by single quotation marks, and</li>
 * <li>the string literals are quoted by double quotation marks.</li>
 * </ul>
 *
 * @author Haixing Hu
 */
public final class SqlCriterionParser implements Parser<String, Criterion> {

  @Override
  public Criterion parse(final String input) throws ParsingException {
    final ANTLRInputStream is = new ANTLRInputStream(input);
    final ThrowExceptionErrorListener listener =
        new ThrowExceptionErrorListener();
    final CriterionLexer lexer = new CriterionLexer(is);
    lexer.removeErrorListeners();
    lexer.addErrorListener(listener);
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final CriterionParser parser = new CriterionParser(tokens);
    parser.removeErrorListeners();
    parser.addErrorListener(listener);
    try {
      final ParseTree tree = parser.line().criterion();
      final CriterionParsingVisitor visitor = new CriterionParsingVisitor();
      final Criterion result = visitor.visit(tree);
      if (result == null) {
        throw new ParsingException(input, 0,
            "Failed to parse the SQL representation.");
      }
      return result;
    } catch (final AntlrParseException e) {
      throw new ParsingException(e.getInput(), e.getPosition(), e.getMessage());
    }
  }

}
