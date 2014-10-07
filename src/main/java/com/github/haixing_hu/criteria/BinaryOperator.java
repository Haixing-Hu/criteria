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
