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
import com.github.haixing_hu.text.ParseException;
import com.github.haixing_hu.text.Parser;


/**
 * A parser used to parse a criterion from its SQL representation.
 * <p>
 * <b>NOTE:</b> This parser can only recognize the string representation of
 * criteria formatted by the {@link SqlCriterionFormatter} class.
 *
 * @author Haixing Hu
 */
public class SqlCriterionParser implements Parser<String, Criterion> {

  @Override
  public Criterion parse(String input) throws ParseException {
    final ANTLRInputStream is = new ANTLRInputStream(input);
    final ThrowExceptionErrorListener listener = new ThrowExceptionErrorListener();
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
        throw new ParseException(input, 0, "Failed to parse the SQL representation.");
      }
      return result;
    } catch (final AntlrParseException e) {
      throw new ParseException(e.getInput(), e.getPosition(), e.getMessage());
    }
  }

}
