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
 * The enumeration of operators for binary relations.
 *
 * @author Haixing Hu
 */
public enum BinaryOperator {

  EQUAL("="),

  NOT_EQUAL("!="),

  LESS("<"),

  GREATER(">"),

  LESS_EQUAL("<="),

  GREATER_EQUAL(">=");

  private String symbol;

  private BinaryOperator(final String symbol) {
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
   * Gets the {@link BinaryOperator} corresponds to the specified symbol.
   * <p>
   * Note that the string comparison is case-insensitive.
   *
   * @param symbol
   *          a specified symbol.
   * @return the {@link BinaryOperator} corresponds to the specified symbol.
   * @throws IllegalArgumentException
   *           if the symbol does not corresponds to any binary operator.
   */
  public static BinaryOperator forSymbol(String symbol) {
    switch (symbol.length()) {
      case 1:
        switch (symbol.charAt(0)) {
          case '=':
            return EQUAL;
          case '<':
            return LESS;
          case '>':
            return GREATER;
          default:
            break;
        }
        break;
      case 2:
        if (symbol.charAt(1) == '=') {
          switch (symbol.charAt(0)) {
            case '<':
              return LESS_EQUAL;
            case '>':
              return GREATER_EQUAL;
            case '!':
              return NOT_EQUAL;
            default:
              break;
          }
        }
        break;
      default:
        break;
    }
    throw new IllegalArgumentException(
        "Invalid symbol for binary operator: " + symbol);
  }
}
