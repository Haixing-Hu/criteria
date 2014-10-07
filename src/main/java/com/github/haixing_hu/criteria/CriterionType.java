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
 * The enumeration of types of criteria.
 *
 * @author Haixing Hu
 */
public enum CriterionType {

  /**
   * Represents an unary criterion.
   */
  UNARY,

  /**
   * Represents a binary criterion.
   */
  BINARY,

  /**
   * Represents a value related criterion.
   */
  VALUE,

  /**
   * Represents a collection related criterion.
   */
  COLLECTION,

  /**
   * Represents a criterion related to string matching, i.e., the "like"
   * statement in SQL.
   */
  LIKE,

  /**
   * Represents a criterion consists of logic combination of sub-criteria.
   */
  COMBINED,
}