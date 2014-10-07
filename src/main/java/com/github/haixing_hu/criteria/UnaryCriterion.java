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

import static com.github.haixing_hu.lang.Argument.requireNonNull;

/**
 * A {@link UnaryCriterion} represents a criterion consists of a unary
 * relation of a property.
 *
 * @author Haixing Hu
 */
public class UnaryCriterion extends Criterion {

  private final UnaryOperator operator;
  private String property;

  /**
   * Constructs a {@link UnaryCriterion}.
   *
   * @param property
   *          the name of property involved in the new criterion.
   * @param operator
   *          the unary operator.
   */
  public UnaryCriterion(final String property,
      final UnaryOperator operator) {
    super(CriterionType.UNARY);
    this.operator = requireNonNull("operator", operator);
    this.property = requireNonNull("property", property);
  }

  /**
   * Gets the name of the property involved in this criterion.
   *
   * @return the name of the property involved in this criterion.
   */
  public final String getProperty() {
    return property;
  }

  /**
   * Sets the name of the property involved in this criterion.
   *
   * @param proeprty
   *    the name of the new property involved in this criterion.
   */
  public final void setProperty(String property) {
    this.property = requireNonNull("property", property);
  }

  /**
   * Gets the unary operator involved in this criterion.
   *
   * @return the unary operator involved in this criterion.
   */
  public final UnaryOperator getOperator() {
    return operator;
  }

  /**
   * Sets the unary operator involved in this criterion.
   *
   * @param operator
   *          the new unary operator involved in this criterion.
   */
  public final void setOperator(UnaryOperator operator) {
    property = requireNonNull("property", property);
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