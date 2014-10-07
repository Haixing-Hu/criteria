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

import static com.github.haixing_hu.lang.Argument.requireLengthAtLeast;
import static com.github.haixing_hu.lang.Argument.requireNonNull;

/**
 * A {@link CombinedCriterion} represents a criterion consists of logic
 * combination of sub-criteria.
 *
 * @author Haixing Hu
 */
public class CombinedCriterion extends Criterion {

  private LogicOperator operator;
  private Criterion[] criteria;

  /**
   * Constructs a {@link CombinedCriterion}.
   *
   * @param operator
   *          the logic operator.
   * @param criteria
   *          sub-criteria involved in the logic combination.
   * @throws NullPointerException
   *           if {@code operator} or {@code criteria} is {@code null}.
   * @throws IllegalArgumentException
   *           if {@code criteria} has less than 2 elements.
   */
  public CombinedCriterion(final LogicOperator operator, final Criterion ... criteria) {
    super(CriterionType.COMBINED);
    this.operator = requireNonNull("operator", operator);
    this.criteria = requireLengthAtLeast("criteria", criteria, 2);
  }


  /**
   * Gets the logic operator involved in this criterion.
   *
   * @return the logic operator involved in this criterion.
   */
  public final LogicOperator getOperator() {
    return operator;
  }

  /**
   * Sets the logic operator involved in this criterion.
   *
   * @param operator
   *          the new logic operator involved in this criterion.
   * @throws NullPointerException
   *           if {@code operator} is {@code null}.
   */
  public final void setOperator(final LogicOperator operator) {
    this.operator = requireNonNull("operator", operator);
  }

  /**
   * Gets the sub-criteria.
   *
   * @return
   *    the sub-criteria.
   */
  public final Criterion[] getCriteria() {
    return criteria;
  }

  /**
   * Sets the sub-criteria.
   *
   * @param criteria
   *          the new sub-criteria.
   * @throws NullPointerException
   *           if {@code criteria} is {@code null}.
   * @throws IllegalArgumentException
   *           if {@code criteria} has less than 2 elements.
   */
  public final void setCriteria(Criterion[] criteria) {
    this.criteria = requireLengthAtLeast("criteria", criteria, 2);
  }

  @Override
  public int hashCode() {
    final int multiplier = 33331;
    int code = 31;
    code = Hash.combine(code, multiplier, operator);
    code = Hash.combine(code, multiplier, criteria);
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
    final CombinedCriterion other = (CombinedCriterion) obj;
    return (operator == other.operator)
        && Equality.equals(criteria, other.criteria);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
          .append("operator", operator)
          .append("criteria", criteria)
          .toString();
  }


  @Override
  public CombinedCriterion clone() {
    return new CombinedCriterion(operator, criteria);
  }
}