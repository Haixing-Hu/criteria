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
package com.github.haixing_hu.criteria;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.github.haixing_hu.lang.Equality;
import com.github.haixing_hu.lang.Hash;
import com.github.haixing_hu.text.tostring.ToStringBuilder;

import static com.github.haixing_hu.lang.Argument.requireNonEmpty;
import static com.github.haixing_hu.lang.Argument.requireNonNull;

/**
 * A {@link ValueCriterion} represents a criterion which consists of a binary
 * relation between a property value and a value.
 * <p>
 * The value operand of a {@link ValueCriterion} could be {@code null}. In this
 * case, the SQL statement corresponds to the {@link ValueCriterion} will be a
 * prepared SQL statement, i.e., the value in the SQL statement is replaced with
 * a question mark '?'.
 *
 * @author Haixing Hu
 */
@Immutable
public final class ValueCriterion extends Criterion {

  private final String property;
  private final BinaryOperator operator;
  private final Object value;

  /**
   * Constructs a {@link ValueCriterion} with {@code null} property value.
   *
   * @param property
   *          the name of a property.
   * @param operator
   *          a binary operator.
   * @throws NullPointerException
   *           if {@code property} or {@code operator} is {@code null}.
   */
  public ValueCriterion(final String property,
      final BinaryOperator operator) {
    this(property, operator, null);
  }

  /**
   * Constructs a {@link ValueCriterion}.
   *
   * @param property
   *          the name of a property.
   * @param operator
   *          a binary operator.
   * @param value
   *          the value as the other operand of the binary operator, which could
   *          be {@code null}.
   * @throws NullPointerException
   *           if {@code property} or {@code operator} is {@code null}.
   */
  public ValueCriterion(final String property,
      final BinaryOperator operator, @Nullable final Object value) {
    super(CriterionType.VALUE);
    this.property = requireNonEmpty("property", property);
    this.operator = requireNonNull("operator", operator);
    this.value = value;
  }

  /**
   * Gets the name of the property involved in this criterion.
   *
   * @return the name of the property involved in this criterion.
   */
  public String getProperty() {
    return property;
  }

  /**
   * Gets the binary operator involved in this criterion.
   *
   * @return the binary operator involved in this criterion.
   */
  public BinaryOperator getOperator() {
    return operator;
  }

  /**
   * Gets the value as the other operand of the binary operator, which could be
   * {@code null}.
   *
   * @return the value as the other operand of the binary operator, which could
   *         be {@code null}.
   */
  public Object getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    final int multiplier = 33331;
    int code = 31;
    code = Hash.combine(code, multiplier, property);
    code = Hash.combine(code, multiplier, operator);
    code = Hash.combine(code, multiplier, value);
    return code;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ValueCriterion other = (ValueCriterion) obj;
    return (operator == other.operator)
        && Equality.equals(property, other.property)
        && Equality.equals(value, other.value);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
          .append("property", property)
          .append("operator", operator)
          .append("value", value)
          .append("value.class", value.getClass())
          .toString();
  }

  @Override
  public ValueCriterion clone() {
    return new ValueCriterion(property, operator, value);
  }
}
