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
package com.github.haixing_hu.criteria;
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
public class BinaryCriterion extends Criterion {

  private String leftProperty;
  private BinaryOperator operator;
  private String rightProperty;

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
  public final String getLeftProperty() {
    return leftProperty;
  }

  /**
   * Gets the name of the property whose value acts as the left operand.
   *
   * @param leftProperty
   *          the name of the new property whose value acts as the left operand.
   * @throws NullPointerException
   *           if {@code leftProperty}is {@code null}.
   * @throws IllegalArgumentException
   *           if {@code leftProperty} is empty.
   */
  public final void setLeftProperty(String leftProperty) {
    this.leftProperty = requireNonEmpty("leftProperty", leftProperty);
  }

  /**
   * Gets the binary operator involved in this criterion.
   *
   * @return the binary operator involved in this criterion.
   */
  public final BinaryOperator getOperator() {
    return operator;
  }

  /**
   * Sets the binary operator involved in this criterion.
   *
   * @param operator
   *          the new binary operator involved in this criterion.
   * @throws NullPointerException
   *          if {@code operator} is {@code null}.
   */
  public final void setOperator(final BinaryOperator operator) {
    this.operator = requireNonNull("operator", operator);
  }

  /**
   * Gets the name of the property whose value acts as the right operand.
   *
   * @return the name of the property whose value acts as the right operand.
   */
  public final String getRightProperty() {
    return rightProperty;
  }

  /**
   * Gets the name of the property whose value acts as the right operand.
   *
   * @param rightProperty
   *          the name of the new property whose value acts as the right
   *          operand.
   * @throws NullPointerException
   *           if {@code rightProperty} is {@code null}.
   * @throws IllegalArgumentException
   *           if {@code rightProperty} is empty.
   */
  public final void setRightProperty(String leftProperty) {
    rightProperty = requireNonEmpty("rightProperty", rightProperty);
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
