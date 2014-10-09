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

import javax.annotation.concurrent.Immutable;

import com.github.haixing_hu.lang.Equality;
import com.github.haixing_hu.lang.Hash;
import com.github.haixing_hu.text.tostring.ToStringBuilder;

import static com.github.haixing_hu.lang.Argument.requireNonNull;

/**
 * A {@link UnaryCriterion} represents a criterion consists of a unary
 * relation of a property.
 *
 * @author Haixing Hu
 */
@Immutable
public final class UnaryCriterion extends Criterion {

  private final UnaryOperator operator;
  private final String property;

  /**
   * Constructs a {@link UnaryCriterion}.
   *
   * @param property
   *          the name of property involved in the new criterion.
   * @param operator
   *          the unary operator.
   */
  public UnaryCriterion(final String property, final UnaryOperator operator) {
    super(CriterionType.UNARY);
    this.operator = requireNonNull("operator", operator);
    this.property = requireNonNull("property", property);
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
   * Gets the unary operator involved in this criterion.
   *
   * @return the unary operator involved in this criterion.
   */
  public UnaryOperator getOperator() {
    return operator;
  }

  @Override
  public int hashCode() {
    final int multiplier = 33331;
    int code = 31;
    code = Hash.combine(code, multiplier, property);
    code = Hash.combine(code, multiplier, operator);
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
    final UnaryCriterion other = (UnaryCriterion) obj;
    return (operator == other.operator)
        && Equality.equals(property, other.property);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
              .append("property", property)
              .append("operator", operator)
              .toString();
  }

  @Override
  public UnaryCriterion clone() {
    return new UnaryCriterion(property, operator);
  }
}