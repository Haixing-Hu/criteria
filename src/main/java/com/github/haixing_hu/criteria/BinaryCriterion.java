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

import static com.github.haixing_hu.lang.Argument.requireNonEmpty;
import static com.github.haixing_hu.lang.Argument.requireNonNull;

/**
 * A {@link BinaryCriterion} represents a criterion which consists of a
 * binary relation between two property values.
 *
 * @author Haixing Hu
 */
@Immutable
public final class BinaryCriterion extends Criterion {

  private final String leftProperty;
  private final BinaryOperator operator;
  private final String rightProperty;

  /**
   * Constructs a {@link ValueCriterion}.
   *
   * @param leftProperty
   *          the name of the property whose value acts as the left operand.
   * @param operator
   *          a binary operator.
   * @param rightProperty
   *          the name of the property whose value acts as the right operand.
   * @throws NullPointerException
   *           if {@code leftProperty} or {@code operator} or
   *           {@code rightProperty} is {@code null}.
   * @throws IllegalArgumentException
   *           if {@code leftProperty} or {@code rightProperty} is empty.
   */
  public BinaryCriterion(final String leftProperty,
      final BinaryOperator operator, final String rightProperty) {
    super(CriterionType.BINARY);
    this.leftProperty = requireNonEmpty("leftProperty", leftProperty);
    this.operator = requireNonNull("operator", operator);
    this.rightProperty = requireNonEmpty("rightProperty", rightProperty);
  }

  /**
   * Gets the name of the property whose value acts as the left operand.
   *
   * @return the name of the property whose value acts as the left operand.
   */
  public String getLeftProperty() {
    return leftProperty;
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
   * Gets the name of the property whose value acts as the right operand.
   *
   * @return the name of the property whose value acts as the right operand.
   */
  public String getRightProperty() {
    return rightProperty;
  }

  @Override
  public int hashCode() {
    final int multiplier = 33331;
    int code = 31;
    code = Hash.combine(code, multiplier, leftProperty);
    code = Hash.combine(code, multiplier, operator);
    code = Hash.combine(code, multiplier, rightProperty);
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
    final BinaryCriterion other = (BinaryCriterion) obj;
    return (operator == other.operator)
        && Equality.equals(leftProperty, other.leftProperty)
        && Equality.equals(rightProperty, other.rightProperty);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
          .append("leftProperty", leftProperty)
          .append("operator", operator)
          .append("rightProperty", rightProperty)
          .toString();
  }

  @Override
  public BinaryCriterion clone() {
    return new BinaryCriterion(leftProperty, operator, rightProperty);
  }
}
