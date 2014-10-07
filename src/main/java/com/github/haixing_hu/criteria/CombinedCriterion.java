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