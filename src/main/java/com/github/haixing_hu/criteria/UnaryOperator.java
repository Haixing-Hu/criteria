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
 * The enumeration of operators for unary relations.
 *
 * @author Haixing Hu
 */
public enum UnaryOperator {

  NULL("IS NULL"),

  NOT_NULL("IS NOT NULL");

//  EMPTY,

//  NOT_EMPTY,

  private String symbol;

  private UnaryOperator(final String symbol) {
    this.symbol = symbol;
  }

  /**
   * Gets the symbol for the operator.
   *
   * @return the symbol for the operator.
   */
  public String symbol() {
    return symbol;
  }

  /**
   * Gets the {@link UnaryOperator} corresponds to the specified symbol.
   * <p>
   * Note that the string comparison is case-insensitive.
   *
   * @param symbol
   *          a specified symbol.
   * @return the {@link UnaryOperator} corresponds to the specified symbol.
   * @throws IllegalArgumentException
   *           if the symbol does not corresponds to any unary operator.
   */
  public static UnaryOperator forSymbol(String symbol) {
    symbol = symbol.toUpperCase();
    if (symbol.equals(NULL.symbol())) {
      return NULL;
    } else if (symbol.equals(NOT_NULL.symbol())) {
      return NOT_NULL;
    }
    throw new IllegalArgumentException(
        "Invalid symbol for unary operator: " + symbol);
  }
}
