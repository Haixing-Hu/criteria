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
import com.github.haixing_hu.lang.CharUtils;
import com.github.haixing_hu.lang.StringUtils;
import com.github.haixing_hu.text.Formatter;

/**
 * A formatter used to format a {@link Criterion} to a SQL statement.
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
public final class SqlCriterionFormatter implements
    Formatter<Criterion, String> {

  public static final char ESCAPE = '\\';

  public static final char CHAR_QUOTE = '\'';

  public static final char STRING_QUOTE = '"';

  public static final char WILDCARD = '%';

  public static final char PLACEHOLDER = '?';

  public static final String LIKE = "LIKE";

  @Override
  public String format(final Criterion criterion) {
    switch (criterion.getType()) {
      case UNARY:
        return formatUnary((UnaryCriterion) criterion);
      case BINARY:
        return formatBinary((BinaryCriterion) criterion);
      case VALUE:
        return formatValue((ValueCriterion) criterion);
      case COLLECTION:
        return formatCollection((CollectionCriterion) criterion);
      case LIKE:
        return formatLike((LikeCriterion) criterion);
      case COMBINED:
        return formatCombined((CombinedCriterion) criterion);
      default:
        return null;
    }
  }

  private String formatUnary(final UnaryCriterion criterion) {
    final StringBuilder builder = new StringBuilder();
    builder.append(criterion.getProperty()).append(' ')
    .append(criterion.getOperator().symbol());
    return builder.toString();
  }

  private String formatBinary(final BinaryCriterion criterion) {
    final StringBuilder sql = new StringBuilder();
    sql.append(criterion.getLeftProperty()).append(' ')
    .append(criterion.getOperator().symbol()).append(' ')
    .append(criterion.getRightProperty());
    return sql.toString();
  }

  private String formatValue(final ValueCriterion criterion) {
    final StringBuilder builder = new StringBuilder();
    final Object value = criterion.getValue();
    builder.append(criterion.getProperty()).append(' ')
    .append(criterion.getOperator().symbol()).append(' ');
    appendValue(value, builder);
    return builder.toString();
  }

  private String formatCollection(final CollectionCriterion criterion) {
    final StringBuilder builder = new StringBuilder();
    builder.append(criterion.getProperty()).append(' ')
    .append(criterion.getOperator().symbol()).append(' ').append('(');
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

  private String formatLike(final LikeCriterion criterion) {
    final StringBuilder builder = new StringBuilder();
    builder.append(criterion.getProperty()).append(' ');
    appendLikePattern(criterion.getValue(), criterion.getMatchMode(), builder);
    return builder.toString();
  }

  private String formatCombined(final CombinedCriterion criterion) {
    boolean first = true;
    final StringBuilder builder = new StringBuilder();
    final LogicOperator operator = criterion.getOperator();
    final Criterion[] criteria = criterion.getCriteria();
    for (final Criterion c : criteria) {
      if (first) {
        builder.append('(').append(format(c)).append(')');
        first = false;
      } else {
        builder.append(' ').append(operator.symbol()).append(' ').append('(')
        .append(format(c)).append(')');
      }
    }
    return builder.toString();
  }

  private void appendValue(@Nullable final Object value,
      final StringBuilder builder) {
    if (value == null) {
      builder.append(PLACEHOLDER);
    } else if (value instanceof Character) {
      final char ch = ((Character) value).charValue();
      CharUtils.quote(ch, ESCAPE, CHAR_QUOTE, CHAR_QUOTE, builder);
    } else if (value instanceof String) {
      final String str = (String) value;
      StringUtils.quote(str, ESCAPE, STRING_QUOTE, STRING_QUOTE, builder);
    } else {
      builder.append(value);
    }
  }

  private void appendLikePattern(final String value, final MatchMode mode,
      final StringBuilder builder) {
    final String str = StringUtils.escape(value, ESCAPE, STRING_QUOTE,
        STRING_QUOTE, WILDCARD);
    switch (mode) {
      case START:
        builder.append(LIKE)
               .append(' ')
               .append(STRING_QUOTE)
               .append(str)
               .append(WILDCARD)
               .append(STRING_QUOTE);
        break;
      case END:
        builder.append(LIKE)
               .append(' ')
               .append(STRING_QUOTE)
               .append(WILDCARD)
               .append(str)
               .append(STRING_QUOTE);
        break;
      case ANYWHERE:
      default:
        builder.append(LIKE)
               .append(' ')
               .append(STRING_QUOTE)
               .append(WILDCARD)
               .append(str)
               .append(WILDCARD)
               .append(STRING_QUOTE);
        break;
    }
  }
}
