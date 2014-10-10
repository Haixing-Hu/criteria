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

import static com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter.BACKSLASH_STRING;
import static com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter.DOUBLE_QUOTE;
import static com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter.DOUBLE_QUOTE_STRING;
import static com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter.ESCAPED_BACKSLASH;
import static com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter.ESCAPED_DOUBLE_QUOTE;
import static com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter.ESCAPED_SINGLE_QUOTE;
import static com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter.ESCAPED_WILDCARD;
import static com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter.SINGLE_QUOTE;
import static com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter.SINGLE_QUOTE_STRING;
import static com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter.WILDCARD;
import static com.github.haixing_hu.criteria.formatter.SqlCriterionFormatter.WILDCARD_STRING;

/**
 * A visitor used to parse.
 *
 * @author Haixing Hu
 */
public final class CriterionParsingVisitor extends
    CriterionBaseVisitor<Criterion> {

  @Override
  public Criterion visitLikeCriterion(final LikeCriterionContext ctx) {
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
  public Criterion visitBinaryCriterion(final BinaryCriterionContext ctx) {
    final List<PropertyContext> propertyCtxes = ctx.property();
    final String leftProperty = propertyCtxes.get(0).getText();
    final BinaryOperator operator = parseBinaryOperator(ctx.operator);
    final String rightProperty = propertyCtxes.get(1).getText();
    return new BinaryCriterion(leftProperty, operator, rightProperty);
  }

  @Override
  public Criterion visitValueCriterion(final ValueCriterionContext ctx) {
    final String property = ctx.property().getText();
    final BinaryOperator operator = parseBinaryOperator(ctx.operator);
    final Object value = parseValue(ctx.value());
    return new ValueCriterion(property, operator, value);
  }

  @Override
  public Criterion visitCollectionCriterion(
      final CollectionCriterionContext ctx) {
    final String property = ctx.property().getText();
    final CollectionOperator operator = parseCollectionOperator(ctx.operator);
    final List<ValueContext> valueContexts = ctx.value();
    final List<Object> values = new ArrayList<Object>();
    for (final ValueContext context : valueContexts) {
      values.add(parseValue(context));
    }
    return new CollectionCriterion(property, operator,
        values.toArray(new Object[0]));
  }

  @Override
  public Criterion visitUnaryCriterion(final UnaryCriterionContext ctx) {
    final String property = ctx.property().getText();
    final UnaryOperator operator = parseUnaryOperator(ctx.operator);
    return new UnaryCriterion(property, operator);
  }

  @Override
  public Criterion visitCombinedCriterion(final CombinedCriterionContext ctx) {
    final List<CriterionContext> criteriaContexts = ctx.criterion();
    final LogicOperator operator = parseLogicOperator(ctx.operator);
    final List<Criterion> criteria = new ArrayList<Criterion>();
    for (final CriterionContext context : criteriaContexts) {
      criteria.add(visit(context));
    }
    return new CombinedCriterion(operator, criteria.toArray(new Criterion[0]));
  }

  private UnaryOperator parseUnaryOperator(final Token token) {
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

  private BinaryOperator parseBinaryOperator(final Token token) {
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

  private CollectionOperator parseCollectionOperator(final Token token) {
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

  private LogicOperator parseLogicOperator(final Token token) {
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

  private Object parseValue(final ValueContext ctx) {
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
      case CriterionParser.CHARACTER:
        return parseCharacterValue(valueString);
      case CriterionParser.STRING:
        return parseStringValue(valueString);
      default:
        throw new IllegalArgumentException(
            "Invalid token type for value: " + ctx.getText());
    }
  }

  private Character parseCharacterValue(final String str) {
    final int n = str.length();
    if (n < 2) {
      throw new IllegalArgumentException(
          "Invalid string value representation: " + str);
    }
    if ((str.charAt(0) != SINGLE_QUOTE) || (str.charAt(n - 1) != SINGLE_QUOTE)) {
      throw new IllegalArgumentException(
          "Invalid quoted character value: " + str);
    }
    //  strip quotes
    String result = str.substring(1, n - 1);
    //  replace escaped quotes
    // result = result.replace(ESCAPED_BACKSLASH, BACKSLASH_STRING);
    result = result.replace(ESCAPED_SINGLE_QUOTE, SINGLE_QUOTE_STRING);
    if (result.length() != 1) {
      throw new IllegalArgumentException(
          "Invalid quoted character value: " + str);
    }
    return Character.valueOf(result.charAt(0));
  }

  private String parseStringValue(final String str) {
    final int n = str.length();
    if (n < 2) {
      throw new IllegalArgumentException(
          "Invalid string value representation: " + str);
    }
    if ((str.charAt(0) != DOUBLE_QUOTE) || (str.charAt(n - 1) != DOUBLE_QUOTE)) {
      throw new IllegalArgumentException(
          "Invalid quoted string value: " + str);
    }
    //  strip quotes
    String result = str.substring(1, n - 1);
    //  replace escaped quotes
    result = result.replace(ESCAPED_BACKSLASH, BACKSLASH_STRING);
    result = result.replace(ESCAPED_DOUBLE_QUOTE, DOUBLE_QUOTE_STRING);
    return result;
  }
}
