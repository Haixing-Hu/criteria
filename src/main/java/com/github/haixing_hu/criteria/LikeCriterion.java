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
 * A {@link LikeCriterion} represents a criterion consists of a matching
 * relation between a property name and a string.
 *
 * @author Haixing Hu
 */
@Immutable
public final class LikeCriterion extends Criterion {

  private final String property;
  private final String value;
  private final MatchMode matchMode;

  /**
   * Constructs a {@link LikeCriterion}.
   *
   * @param property
   *          the name of a property.
   * @param value
   *          the value of a substring.
   */
  public LikeCriterion(final String property, final String value) {
    this(property, value, MatchMode.ANYWHERE);
  }

  /**
   * Constructs a {@link LikeCriterion}.
   *
   * @param property
   *          the name of a property.
   * @param value
   *          the value of a substring.
   * @param matchMode
   *          the matching mode.
   */
  public LikeCriterion(final String property, final String value,
      final MatchMode matchMode) {
    super(CriterionType.LIKE);
    this.property = requireNonEmpty("property", property);
    this.value = requireNonEmpty("value", value);
    this.matchMode = requireNonNull("matchMode", matchMode);
  }

  /**
   * Gets the name of the property.
   *
   * @return
   *    the name of the property.
   */
  public String getProperty() {
    return property;
  }

  /**
   * Gets the value to be matched with the property value.
   *
   * @return the value to be matched with the property value.
   */
  public String getValue() {
    return value;
  }

  /**
   * Gets the matching mode.
   *
   * @return the matching mode.
   */
  public MatchMode getMatchMode() {
    return matchMode;
  }

  @Override
  public int hashCode() {
    final int multiplier = 33331;
    int code = 31;
    code = Hash.combine(code, multiplier, property);
    code = Hash.combine(code, multiplier, value);
    code = Hash.combine(code, multiplier, matchMode);
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
    final LikeCriterion other = (LikeCriterion) obj;
    return (matchMode == other.matchMode)
        && Equality.equals(property, other.property)
        && Equality.equals(value, other.value);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
          .append("property", property)
          .append("value", value)
          .append("matchMode", matchMode)
          .toString();
  }

  @Override
  public LikeCriterion clone() {
    return new LikeCriterion(property, value, matchMode);
  }

}
