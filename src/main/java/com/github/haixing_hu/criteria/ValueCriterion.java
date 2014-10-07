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

import javax.annotation.Nullable;

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
public class ValueCriterion extends Criterion {

  private String property;
  private BinaryOperator operator;
  private Object value;

  /**
   * Constructs a {@link ValueCriterion}.
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
   * Sets the name of the property involved in this criterion.
   *
   * @param property
   *          the name of the new property involved in this criterion.
   * @throws NullPointerException
   *           if {@code property} is {@code null}.
   */
  public void setProperty(final String property) {
    this.property = requireNonEmpty("property", property);
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
   * Sets the binary operator involved in this criterion.
   *
   * @param operator
   *          the new binary operator involved in this criterion.
   * @throws NullPointerException
   *           if {@code operator} is {@code null}.
   */
  public void setOperator(final BinaryOperator operator) {
    this.operator = requireNonNull("operator", operator);
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

  /**
   * Sets the value as the other operand of the binary operator, which could be
   * {@code null}.
   *
   * @param value
   *          the new value as the other operand of the binary operator, which
   *          could be {@code null}.
   */
  public void setValue(@Nullable final Object value) {
    this.value = value;
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
