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
package com.github.haixing_hu.criteria.parser.sql;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import com.github.haixing_hu.criteria.BinaryCriterion;
import com.github.haixing_hu.criteria.BinaryOperator;
import com.github.haixing_hu.criteria.CollectionCriterion;
import com.github.haixing_hu.criteria.CollectionOperator;
import com.github.haixing_hu.criteria.CombinedCriterion;
import com.github.haixing_hu.criteria.Criterion;
import com.github.haixing_hu.criteria.LikeCriterion;
import com.github.haixing_hu.criteria.LogicOperator;
import com.github.haixing_hu.criteria.MatchMode;
import com.github.haixing_hu.criteria.UnaryCriterion;
import com.github.haixing_hu.criteria.UnaryOperator;
import com.github.haixing_hu.criteria.ValueCriterion;
import com.github.haixing_hu.criteria.parser.sql.CriterionParser.BinaryCriterionContext;
import com.github.haixing_hu.criteria.parser.sql.CriterionParser.CollectionCriterionContext;
import com.github.haixing_hu.criteria.parser.sql.CriterionParser.CombinedCriterionContext;
import com.github.haixing_hu.criteria.parser.sql.CriterionParser.CriterionContext;
import com.github.haixing_hu.criteria.parser.sql.CriterionParser.LikeCriterionContext;
import com.github.haixing_hu.criteria.parser.sql.CriterionParser.PropertyContext;
import com.github.haixing_hu.criteria.parser.sql.CriterionParser.UnaryCriterionContext;
import com.github.haixing_hu.criteria.parser.sql.CriterionParser.ValueContext;
import com.github.haixing_hu.criteria.parser.sql.CriterionParser.ValueCriterionContext;

import static com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter.*;

/**
 * A visitor used to parse
 *
 * @author Haixing Hu
 */
public class CriterionParsingVisitor extends CriterionBaseVisitor<Criterion> {

  @Override
  public Criterion visitLikeCriterion(LikeCriterionContext ctx) {
    final String property = ctx.property().getText();
    String pattern = parseStringValue(ctx.pattern().getText());
    pattern = pattern.replace(ESCAPED_WILDCARD, WILDCARD_STRING);
    final int n = pattern.length();
    final MatchMode mode;
    if ((n >= 2)
        && (pattern.charAt(0) == WILDCARD)
        && (pattern.charAt(n - 1) == WILDCARD)) {
      mode = MatchMode.ANYWHERE;
      pattern = pattern.substring(1, n - 1);
    } else if ((n >= 1) && (pattern.charAt(0) == WILDCARD)) {
      mode = MatchMode.END;
      pattern = pattern.substring(1);
    } else if ((n >= 1) && (pattern.charAt(n - 1) == WILDCARD)) {
      mode = MatchMode.START;
      pattern = pattern.substring(0, n - 1);
    } else {
      mode = MatchMode.ANYWHERE;
    }
    return new LikeCriterion(property, pattern, mode);
  }

  @Override
  public Criterion visitBinaryCriterion(BinaryCriterionContext ctx) {
    final List<PropertyContext> propertyCtxes = ctx.property();
    final String leftProperty = propertyCtxes.get(0).getText();
    final BinaryOperator operator = parseBinaryOperator(ctx.operator);
    final String rightProperty = propertyCtxes.get(1).getText();
    return new BinaryCriterion(leftProperty, operator, rightProperty);
  }

  @Override
  public Criterion visitValueCriterion(ValueCriterionContext ctx) {
    final String property = ctx.property().getText();
    final BinaryOperator operator = parseBinaryOperator(ctx.operator);
    final Object value = parseValue(ctx.value());
    return new ValueCriterion(property, operator, value);
  }

  @Override
  public Criterion visitCollectionCriterion(CollectionCriterionContext ctx) {
    final String property = ctx.property().getText();
    final CollectionOperator operator = parseCollectionOperator(ctx.operator);
    final List<ValueContext> valueContexts = ctx.value();
    final List<Object> values = new ArrayList<Object>();
    for (final ValueContext context : valueContexts) {
      values.add(parseValue(context));
    }
    return new CollectionCriterion(property, operator, values.toArray(new Object[0]));
  }


  @Override
  public Criterion visitUnaryCriterion(UnaryCriterionContext ctx) {
    final String property = ctx.property().getText();
    final UnaryOperator operator = parseUnaryOperator(ctx.operator);
    return new UnaryCriterion(property, operator);
  }

  @Override
  public Criterion visitCombinedCriterion(CombinedCriterionContext ctx) {
    final List<CriterionContext> criteriaContexts = ctx.criterion();
    final LogicOperator operator = parseLogicOperator(ctx.operator);
    final List<Criterion> criteria = new ArrayList<Criterion>();
    for (final CriterionContext context : criteriaContexts) {
      criteria.add(visit(context));
    }
    return new CombinedCriterion(operator, criteria.toArray(new Criterion[0]));
  }

  private UnaryOperator parseUnaryOperator(Token token) {
    switch (token.getType()) {
      case CriterionParser.IS_NULL:
        return UnaryOperator.NULL;
      case CriterionParser.IS_NOT_NULL:
        return UnaryOperator.NOT_NULL;
      default:
        throw new IllegalArgumentException(
            "Invalid token for unary operator: " + token.getText());
    }
  }

  private BinaryOperator parseBinaryOperator(Token token) {
    switch (token.getType()) {
      case CriterionParser.EQUAL:
        return BinaryOperator.EQUAL;
      case CriterionParser.NOT_EQUAL:
        return BinaryOperator.NOT_EQUAL;
      case CriterionParser.LESS:
        return BinaryOperator.LESS;
      case CriterionParser.LESS_EQUAL:
        return BinaryOperator.LESS_EQUAL;
      case CriterionParser.GREATER:
        return BinaryOperator.GREATER;
      case CriterionParser.GREATER_EQUAL:
        return BinaryOperator.GREATER_EQUAL;
      default:
        throw new IllegalArgumentException(
            "Invalid token for binary operator: " + token.getText());
    }
  }

  private CollectionOperator parseCollectionOperator(Token token) {
    switch (token.getType()) {
      case CriterionParser.IN:
        return CollectionOperator.IN;
      case CriterionParser.NOT_IN:
        return CollectionOperator.NOT_IN;
      default:
        throw new IllegalArgumentException(
            "Invalid token for collection operator: " + token.getText());
    }
  }

  private LogicOperator parseLogicOperator(Token token) {
    switch (token.getType()) {
      case CriterionParser.AND:
        return LogicOperator.AND;
      case CriterionParser.OR:
        return LogicOperator.OR;
      default:
        throw new IllegalArgumentException(
            "Invalid token for logic operator: " + token.getText());
    }
  }

  private Object parseValue(ValueContext ctx) {
    final String valueString = ctx.getText();
    switch (ctx.type.getType()) {
      case CriterionParser.INTEGER_NUMBER:
        return Integer.valueOf(valueString);
      case CriterionParser.HEX_NUMBER:
        return Integer.valueOf(valueString, 16);
      case CriterionParser.REAL_NUMBER:
        return Double.valueOf(valueString);
      case CriterionParser.BOOLEAN:
        return Boolean.valueOf(valueString);
      case CriterionParser.PLACEHOLDER:
        return null;
      case CriterionParser.STRING:
        return parseStringValue(valueString);
      default:
        throw new IllegalArgumentException(
            "Invalid token type for value: " + ctx.getText());
    }
  }

  private String parseStringValue(String str) {
    final int n = str.length();
    if (n < 2) {
      throw new IllegalArgumentException(
          "Invalid string value representation: " + str);
    }
    if ((str.charAt(0) != QUOTE) || (str.charAt(n-1) != QUOTE)) {
      throw new IllegalArgumentException(
          "Invalid quoted string value: " + str);
    }
    //  strip quotes
    String result = str.substring(1, n - 1);
    //  replace escaped quotes
    result = result.replace(ESCAPED_BACKSLASH, BACKSLASH_STRING);
    result = result.replace(ESCAPED_QUOTE, QUOTE_STRING);
    return result;
  }
}
