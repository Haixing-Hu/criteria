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
import java.util.Collection;

import javax.annotation.concurrent.Immutable;

import com.github.haixing_hu.lang.Equality;
import com.github.haixing_hu.lang.Hash;
import com.github.haixing_hu.text.tostring.ToStringBuilder;

import static com.github.haixing_hu.lang.Argument.requireNonEmpty;
import static com.github.haixing_hu.lang.Argument.requireNonNull;

/**
 * A {@link CollectionCriterion} represents a criterion which consists
 * of a binary relation between a property value and a collection of values.
 *
 *
 * @author Haixing Hu
 */
@Immutable
public final class CollectionCriterion extends Criterion {

  private final String property;
  private final CollectionOperator operator;
  private final Object[] values;

  /**
   * Constructs a {@link CollectionCriterion}.
   *
   * @param property
   *          the name of a property.
   * @param operator
   *          a collection operator.
   * @param values
   *          an array of values acts as the other operand of the operator.
   * @throws NullPointerException
   *           if {@code property} or {@code operator} or {@code values} is
   *           {@code null}.
   * @throws IllegalArgumentException
   *           if {@code values} is empty.
   */
  public CollectionCriterion(final String property,
      final CollectionOperator operator, final Object... values) {
    super(CriterionType.COLLECTION);
    this.property = requireNonEmpty("property", property);
    this.operator = requireNonNull("operator", operator);
    this.values = requireNonEmpty("value", values);
  }

  /**
   * Constructs a {@link CollectionCriterion}.
   *
   * @param property
   *          the name of a property.
   * @param operator
   *          a collection operator.
   * @param values
   *          a collection of values acts as the other operand of the operator.
   * @throws NullPointerException
   *           if {@code property} or {@code operator} or {@code values} is
   *           {@code null}.
   * @throws IllegalArgumentException
   *           if {@code values} is empty.
   */
  public CollectionCriterion(final String property,
      final CollectionOperator operator, final Collection<?> values) {
    super(CriterionType.COLLECTION);
    this.property = requireNonEmpty("property", property);
    this.operator = requireNonNull("operator", operator);
    this.values = values.toArray();
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
   * Gets the collection operator involved in this criterion.
   *
   * @return the collection operator involved in this criterion.
   */
  public CollectionOperator getOperator() {
    return operator;
  }

  /**
   * Gets the collection of values as the other operand of the binary operator,
   * which could be {@code null}.
   *
   * @return the collection of values as the other operand of the binary
   *         operator, which could be {@code null}.
   */
  public Object[] getValues() {
    return values;
  }

  @Override
  public int hashCode() {
    final int multiplier = 33331;
    int code = 31;
    code = Hash.combine(code, multiplier, property);
    code = Hash.combine(code, multiplier, operator);
    code = Hash.combine(code, multiplier, values);
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
    final CollectionCriterion other = (CollectionCriterion) obj;
    return (operator == other.operator)
        && Equality.equals(property, other.property)
        && Equality.equals(values, other.values);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
          .append("property", property)
          .append("operator", operator)
          .append("values", values)
          .toString();
  }

  @Override
  public CollectionCriterion clone() {
    return new CollectionCriterion(property, operator, values);
  }

}