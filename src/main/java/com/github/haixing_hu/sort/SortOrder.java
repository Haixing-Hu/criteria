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

package com.github.haixing_hu.sort;

import javax.annotation.concurrent.Immutable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import static com.github.haixing_hu.lang.Argument.requireNonNull;

/**
 * A {@link SortOrder} specifies how to sort a property.
 *
 * @author Haixing Hu
 */
@Immutable
public final class SortOrder {

  /**
   * The default sorting direction.
   */
  public static final SortDirection DEFAULT_DIRECTION = SortDirection.ASC;

  /**
   * The default null handling strategy.
   */
  public static final NullHandlingStrategy DEFAULT_NULL_HANDLING_STRATEGY =
      NullHandlingStrategy.NATIVE;

  private final String property;
  private final SortDirection direction;
  private final NullHandlingStrategy nullHandlingStrategy;

  /**
   * Constructs a {@link SortOrder}.
   * <p>
   * The new {@link SortOrder} will have the specified property name, the
   * ascending sorting direction, and the native null handling strategy.
   *
   * @param property
   *          the name of the property to be sorted, which cannot be
   *          {@code null}.
   */
  public SortOrder(final String property) {
    this.property = requireNonNull("property", property);
    direction = DEFAULT_DIRECTION;
    nullHandlingStrategy = DEFAULT_NULL_HANDLING_STRATEGY;
  }

  /**
   * Constructs a {@link SortOrder}.
   * <p>
   * The new {@link SortOrder} will have the specified property name, the
   * specified sorting direction, and the native null handling strategy.
   *
   * @param property
   *          the name of the property to be sorted, which cannot be
   *          {@code null}.
   * @param direction
   *          the sorting direction, which cannot be {@code null}.
   */
  public SortOrder(final String property, final SortDirection direction) {
    this.property = requireNonNull("property", property);
    this.direction = requireNonNull("direction", direction);
    nullHandlingStrategy = DEFAULT_NULL_HANDLING_STRATEGY;
  }

  /**
   * Constructs a {@link SortOrder}.
   * <p>
   * The new {@link SortOrder} will have the specified property name, the
   * specified sorting direction, and the specified null handling strategy.
   *
   * @param property
   *          the name of the property to be sorted, which cannot be
   *          {@code null}.
   * @param direction
   *          the sorting direction, which cannot be {@code null}.
   * @param nullHandlingStrategy
   *          the {@code null} values handling strategy, which cannot be
   *          {@code null}.
   */
  public SortOrder(final String property, final SortDirection direction,
      final NullHandlingStrategy nullHandlingStrategy) {
    this.property = requireNonNull("property", property);
    this.direction = requireNonNull("direction", direction);
    this.nullHandlingStrategy = requireNonNull("nullHandlingStrategy",
        nullHandlingStrategy);
  }

  /**
   * Gets the name of the property to be sorted.
   *
   * @return the name of the property to be sorted, which will never be
   *         {@code null}.
   */
  public String getProperty() {
    return property;
  }

  /**
   * Gets the sorting direction.
   *
   * @return the sorting direction, which will never be {@code null}.
   */
  public SortDirection getDirection() {
    return direction;
  }

  /**
   * Gets the null handling strategy.
   *
   * @return the nullHandlingStrategy, which will never be {@code null}.
   */
  public NullHandlingStrategy getNullHandlingStrategy() {
    return nullHandlingStrategy;
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
}
