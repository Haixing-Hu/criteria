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
 * The enumeration of operators for relations between an element and a collection.
 *
 * @author Haixing Hu
 */
public enum CollectionOperator {

  IN("IN"),

  NOT_IN("NOT IN");

  private String symbol;

  private CollectionOperator(final String symbol) {
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
   * Gets the {@link CollectionOperator} corresponds to the specified symbol.
   * <p>
   * Note that the string comparison is case-insensitive.
   *
   * @param symbol
   *          a specified symbol.
   * @return the {@link CollectionOperator} corresponds to the specified symbol.
   * @throws IllegalArgumentException
   *           if the symbol does not corresponds to any collection operator.
   */
  public static CollectionOperator forSymbol(String symbol) {
    symbol = symbol.toUpperCase();
    if (symbol.equals(IN.symbol())) {
      return IN;
    } else if (symbol.equals(NOT_IN.symbol())) {
      return NOT_IN;
    }
    throw new IllegalArgumentException(
        "Invalid symbol for collection operator: " + symbol);
  }
}
