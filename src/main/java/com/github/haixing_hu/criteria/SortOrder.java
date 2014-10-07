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

/**
 * The enumeration of orders of sorting.
 *
 * @author Haixing Hu
 */
public enum SortOrder {

  /**
   * Enumeration value indicating the items are sorted in increasing order.
   * For example, the set <code>1, 4, 0</code> sorted in
   * <code>ASC</code> order is <code>0, 1, 4</code>.
   */
  ASC("ASC"),

  /**
   * Enumeration value indicating the items are sorted in decreasing order.
   * For example, the set <code>1, 4, 0</code> sorted in
   * <code>DESC</code> order is <code>4, 1, 0</code>.
   */
  DESC("DESC");

  private String symbol;

  private SortOrder(final String symbol) {
    this.symbol = symbol;
  }

  public String symbol() {
    return symbol;
  }
}
