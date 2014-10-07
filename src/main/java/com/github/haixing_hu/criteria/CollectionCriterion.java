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
 * A {@link CollectionCriterion} represents a criterion which consists
 * of a binary relation between a property value and a collection of values.
 *
 *
 * @author Haixing Hu
 */
public class CollectionCriterion extends Criterion {

  private String property;
  private CollectionOperator operator;
  private Object[] values;

  /**
   * Constructs a {@link CollectionCriterion}.
   *
   * @param property
   *          the name of a property.
   * @param operator
   *          a collection operator.
   * @param values
   *          the collection of values acts as the other operand of the operator.
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
   * Gets the collection operator involved in this criterion.
   *
   * @return the collection operator involved in this criterion.
   */
  public CollectionOperator getOperator() {
    return operator;
  }

  /**
   * Sets the collection operator involved in this criterion.
   *
   * @param operator
   *          the new collection operator involved in this criterion.
   * @throws NullPointerException
   *           if {@code operator} is {@code null}.
   */
  public void setOperator(final CollectionOperator operator) {
    this.operator = requireNonNull("operator", operator);
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

  /**
   * Sets the collection of values as the other operand of the binary operator,
   * which could be {@code null}.
   *
   * @param values
   *          the new collection of values as the other operand of the binary
   *          operator, which could be {@code null}.
   * @throws NullPointerException
   *           if {@code values} is {@code null}.
   * @throws IllegalArgumentException
   *           if {@code values} is empty.
   */
  public void setValues(@Nullable final Object ... values) {
    this.values = requireNonEmpty("value", values);
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