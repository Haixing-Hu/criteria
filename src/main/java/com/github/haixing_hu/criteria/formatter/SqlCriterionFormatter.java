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
package com.github.haixing_hu.criteria.formatter;

import javax.annotation.Nullable;

import com.github.haixing_hu.criteria.BinaryCriterion;
import com.github.haixing_hu.criteria.CollectionCriterion;
import com.github.haixing_hu.criteria.CombinedCriterion;
import com.github.haixing_hu.criteria.Criterion;
import com.github.haixing_hu.criteria.LikeCriterion;
import com.github.haixing_hu.criteria.LogicOperator;
import com.github.haixing_hu.criteria.MatchMode;
import com.github.haixing_hu.criteria.UnaryCriterion;
import com.github.haixing_hu.criteria.ValueCriterion;
import com.github.haixing_hu.text.Formatter;

/**
 * A formatter used to format a {@link Criterion} to a SQL statement.
 *
 * @author Haixing Hu
 */
public class SqlCriterionFormatter implements Formatter<Criterion, String> {

  public static final char BACKSLASH = '\\';

  public static final char QUOTE = '\'';

  public static final char WILDCARD = '%';

  public static final char PLACEHOLDER = '?';

  public static final String BACKSLASH_STRING = "\\";

  public static final String QUOTE_STRING = "'";

  public static final String WILDCARD_STRING = "%";

  public static final String PLACEHOLDER_STRING = "?";

  public static final String ESCAPED_BACKSLASH = "\\\\";

  public static final String ESCAPED_QUOTE = "\\'";

  public static final String ESCAPED_WILDCARD = "\\%";

  public static final String LIKE = "LIKE";

  @Override
  public String format(Criterion criterion) {
    switch (criterion.getType()) {
      case UNARY:
        return formatUnaryCriterion((UnaryCriterion) criterion);
      case BINARY:
        return formatBinaryCriterion((BinaryCriterion) criterion);
      case VALUE:
        return formatValueCriterion((ValueCriterion) criterion);
      case COLLECTION:
        return formatCollectionCriterion((CollectionCriterion) criterion);
      case LIKE:
        return formatLikeCriterion((LikeCriterion) criterion);
      case COMBINED:
        return formatCombinedCriterion((CombinedCriterion) criterion);
      default:
        return null;
    }
  }

  private String formatUnaryCriterion(UnaryCriterion criterion) {
    final StringBuilder builder = new StringBuilder();
    builder.append(criterion.getProperty())
           .append(' ')
           .append(criterion.getOperator().symbol());
    return builder.toString();
  }

  private String formatBinaryCriterion(BinaryCriterion criterion) {
    final StringBuilder sql = new StringBuilder();
    sql.append(criterion.getLeftProperty())
       .append(' ')
       .append(criterion.getOperator().symbol())
       .append(' ')
       .append(criterion.getRightProperty());
    return sql.toString();
  }

  private String formatValueCriterion(ValueCriterion criterion) {
    final StringBuilder builder = new StringBuilder();
    final Object value = criterion.getValue();
    builder.append(criterion.getProperty())
           .append(' ')
           .append(criterion.getOperator().symbol())
           .append(' ');
    appendValue(value, builder);
    return builder.toString();
  }

  private String formatCollectionCriterion(CollectionCriterion criterion) {
    final StringBuilder builder = new StringBuilder();
    builder.append(criterion.getProperty())
           .append(' ')
           .append(criterion.getOperator().symbol())
           .append(' ')
           .append('(');
    final Object[] values = criterion.getValues();
    for (int i = 0; i < values.length; ++i) {
      if (i > 0) {
        builder.append(',').append(' ');
      }
      appendValue(values[i], builder);
    }
    builder.append(')');
    return builder.toString();
  }

  private String formatLikeCriterion(LikeCriterion criterion) {
    final StringBuilder builder = new StringBuilder();
    builder.append(criterion.getProperty())
           .append(' ');
    appendLikePattern(criterion.getValue(), criterion.getMatchMode(), builder);
    return builder.toString();
  }

  private String formatCombinedCriterion(CombinedCriterion criterion) {
    boolean first = true;
    final StringBuilder builder = new StringBuilder();
    final LogicOperator operator = criterion.getOperator();
    final Criterion[] criteria = criterion.getCriteria();
    for (final Criterion c : criteria) {
      if (first) {
        builder.append('(')
               .append(format(c))
               .append(')');
        first = false;
      } else {
        builder.append(' ')
               .append(operator.symbol())
               .append(' ')
               .append('(')
               .append(format(c))
               .append(')');
      }
    }
    return builder.toString();
  }

  private void appendValue(@Nullable Object value, StringBuilder builder) {
    if (value == null) {
      builder.append(PLACEHOLDER);
    } else if (value instanceof Character) {
      appendCharLiteral((Character) value, builder);
    } else if (value instanceof String) {
      appendStringLiteral((String) value, builder);
    } else {
      builder.append(value);
    }
  }

  private void appendCharLiteral(Character value, StringBuilder builder) {
    if (value == QUOTE) {
      builder.append(QUOTE)
             .append(ESCAPED_QUOTE)
             .append(QUOTE);
    } else {
      builder.append(QUOTE)
             .append(value)
             .append(QUOTE);
    }
  }

  private void appendStringLiteral(String value, StringBuilder builder) {
    String result = value.replace(BACKSLASH_STRING, ESCAPED_BACKSLASH);
    result = result.replace(QUOTE_STRING, ESCAPED_QUOTE);
    builder.append(QUOTE)
           .append(result)
           .append(QUOTE);
  }

  private void appendLikePattern(String value, MatchMode mode, StringBuilder builder) {
    value = value.replace(BACKSLASH_STRING, ESCAPED_BACKSLASH);
    value = value.replace(QUOTE_STRING, ESCAPED_QUOTE);
    value = value.replace(WILDCARD_STRING, ESCAPED_WILDCARD);
    switch (mode) {
      case START:
        builder.append(LIKE)
               .append(' ')
               .append(QUOTE)
               .append(value)
               .append(WILDCARD)
               .append(QUOTE);
        break;
      case END:
        builder.append(LIKE)
               .append(' ')
               .append(QUOTE)
               .append(WILDCARD)
               .append(value)
               .append(QUOTE);
        break;
      case ANYWHERE:
      default:
        builder.append(LIKE)
               .append(' ')
               .append(QUOTE)
               .append(WILDCARD)
               .append(value)
               .append(WILDCARD)
               .append(QUOTE);
        break;
    }
  }
}
