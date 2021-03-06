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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.github.haixing_hu.lang.Argument.requireLengthAtLeast;
import static com.github.haixing_hu.lang.Argument.requireNonNull;
import static com.github.haixing_hu.lang.Argument.requireSizeAtLeast;

/**
 * A {@link CombinedCriterion} represents a criterion consists of logic
 * combination of sub-criteria.
 *
 * @author Haixing Hu
 */
@Immutable
public final class CombinedCriterion extends Criterion {

  private final LogicOperator operator;
  private final Criterion[] criteria;

  /**
   * Constructs a {@link CombinedCriterion}.
   *
   * @param operator
   *          the logic operator.
   * @param criteria
   *          an array of sub-criteria involved in the logic combination.
   * @throws NullPointerException
   *           if {@code operator} or {@code criteria} is {@code null}.
   * @throws IllegalArgumentException
   *           if {@code criteria} has less than 2 elements.
   */
  public CombinedCriterion(final LogicOperator operator,
      final Criterion ... criteria) {
    super(CriterionType.COMBINED);
    this.operator = requireNonNull("operator", operator);
    this.criteria = requireLengthAtLeast("criteria", criteria, 2);
  }

  /**
   * Constructs a {@link CombinedCriterion}.
   *
   * @param operator
   *          the logic operator.
   * @param criteria
   *          an array of sub-criteria involved in the logic combination.
   * @throws NullPointerException
   *           if {@code operator} or {@code criteria} is {@code null}.
   * @throws IllegalArgumentException
   *           if {@code criteria} has less than 2 elements.
   */
  public CombinedCriterion(final LogicOperator operator,
      final Collection<Criterion> criteria) {
    super(CriterionType.COMBINED);
    this.operator = requireNonNull("operator", operator);
    requireSizeAtLeast("criteria", criteria, 2);
    this.criteria = criteria.toArray(new Criterion[0]);
  }

  /**
   * Gets the logic operator involved in this criterion.
   *
   * @return the logic operator involved in this criterion.
   */
  public LogicOperator getOperator() {
    return operator;
  }

  /**
   * Gets the sub-criteria.
   *
   * @return
   *    the sub-criteria.
   */
  public Criterion[] getCriteria() {
    return criteria.clone();
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(final Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @Override
  public CombinedCriterion clone() {
    return new CombinedCriterion(operator, criteria);
  }
}
