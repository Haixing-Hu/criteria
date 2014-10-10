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

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Sort} represents a sorting rule.
 *
 * @author Haixing Hu
 */
public class Sort extends ArrayList<SortOrder> {

  private static final long serialVersionUID = -7323846213838886382L;

  /**
   * Constructs an empty sorting rule.
   */
  public Sort() {
    super();
  }

  /**
   * Constructs a sorting rule with the specified sorting orders.
   *
   * @param orders
   *          an array of sorting orders.
   */
  public Sort(final SortOrder... orders) {
    super(orders.length);
    for (final SortOrder order : orders) {
      this.add(order);
    }
  }

  /**
   * Constructs a sorting rule with the specified sorting orders.
   *
   * @param orders
   *          a list of sorting orders.
   */
  public Sort(List<SortOrder> orders) {
    super(orders.size());
    this.addAll(orders);
  }

  /**
   * Constructs a sorting rule to sort with the specified properties in the
   * default direction and with the default null handling strategy.
   *
   * @param properties
   *          an array of properties used as sorting orders.
   */
  public Sort(String... properties) {
    this(SortOrder.DEFAULT_DIRECTION, SortOrder.DEFAULT_NULL_HANDLING_STRATEGY,
        properties);
  }

  /**
   * Constructs a sorting rule to sort with the specified properties in the
   * specified direction and with the default null handling strategy.
   *
   * @param direction
   *          a sorting direction.
   * @param properties
   *          an array of properties used as sorting orders.
   */
  public Sort(SortDirection direction, String... properties) {
    this(direction, SortOrder.DEFAULT_NULL_HANDLING_STRATEGY, properties);
  }

  /**
   * Constructs a sorting rule to sort with the specified properties in the
   * specified direction and with the default null handling strategy.
   *
   * @param direction
   *          a sorting direction.
   * @param properties
   *          a list of properties used as sorting orders.
   */
  public Sort(SortDirection direction, List<String> properties) {
    this(direction, SortOrder.DEFAULT_NULL_HANDLING_STRATEGY, properties);
  }

  /**
   * Constructs a sorting rule to sort with the specified properties in the
   * specified direction and with the specified null handling strategy.
   *
   * @param direction
   *          a sorting direction.
   * @param nullHandlingStrategy
   *          a null handling strategy.
   * @param properties
   *          an array of properties used as sorting orders.
   */
    public Sort(SortDirection direction,
        NullHandlingStrategy nullHandlingStrategy, String... properties) {
    super(properties.length);
    for (final String property : properties) {
      final SortOrder order = new SortOrder(property, direction, nullHandlingStrategy);
      this.add(order);
    }
  }

  /**
   * Constructs a sorting rule to sort with the specified properties in the
   * specified direction and with the specified null handling strategy.
   *
   * @param direction
   *          a sorting direction.
   * @param nullHandlingStrategy
   *          a null handling strategy.
   * @param properties
   *          a list of properties used as sorting orders.
   */
  public Sort(SortDirection direction,
      NullHandlingStrategy nullHandlingStrategy, List<String> properties) {
    super(properties.size());
    for (final String property : properties) {
      final SortOrder order = new SortOrder(property, direction, nullHandlingStrategy);
      this.add(order);
    }
  }
}
