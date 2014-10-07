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
 * A {@link LikeCriterion} represents a criterion consists of a matching
 * relation between a property name and a string.
 *
 * @author Haixing Hu
 */
public class LikeCriterion extends Criterion {

  private String property;
  private String value;
  private MatchMode matchMode;

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
  public final String getProperty() {
    return property;
  }

  /**
   * Sets the name of the property.
   *
   * @param property
   *    the name of the new property.
   */
  public void setProperty(String property) {
    this.property = requireNonEmpty("property", property);
  }

  /**
   * Gets the value to be matched with the property value.
   *
   * @return the value to be matched with the property value.
   */
  public final String getValue() {
    return value;
  }

  /**
   * Sets the value to be matched with the property value.
   *
   * @param value
   *          the new value to be matched with the property value.
   */
  public final void setValue(String value) {
    this.value = requireNonEmpty("value", value);
  }

  /**
   * Gets the matching mode.
   *
   * @return the matching mode.
   */
  public final MatchMode getMatchMode() {
    return matchMode;
  }

  /**
   * Sets the matching mode.
   *
   * @param matchMode
   *          the new matching mode.
   */
  public final void setMatchMode(MatchMode matchMode) {
    this.matchMode = requireNonNull("matchMode", matchMode);
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