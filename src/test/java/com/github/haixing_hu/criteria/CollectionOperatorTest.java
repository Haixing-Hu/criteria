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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Unit test for the {@link CollectionOperator} class.
 *
 * @author Haixing Hu
 */
public class CollectionOperatorTest {

  @Test
  public void testForSymbol() {

    assertEquals(CollectionOperator.IN, CollectionOperator.forSymbol("IN"));
    assertEquals(CollectionOperator.NOT_IN, CollectionOperator.forSymbol("NOT IN"));
    assertEquals(CollectionOperator.IN, CollectionOperator.forSymbol("in"));
    assertEquals(CollectionOperator.NOT_IN, CollectionOperator.forSymbol("not in"));
    assertEquals(CollectionOperator.IN, CollectionOperator.forSymbol("iN"));
    assertEquals(CollectionOperator.NOT_IN, CollectionOperator.forSymbol("Not In"));

    try {
      CollectionOperator.forSymbol(null);
      fail("should throw NullPointerException");
    } catch (final NullPointerException e) {
      //  pass
    }

    try {
      CollectionOperator.forSymbol("");
      fail("should throw IllegalArgumentException");
    } catch (final IllegalArgumentException e) {
      //  pass
    }

    try {
      CollectionOperator.forSymbol("abc");
      fail("should throw IllegalArgumentException");
    } catch (final IllegalArgumentException e) {
      //  pass
    }

    //  FIXME: shall we need to treat successive spaces as one?
    try {
      CollectionOperator.forSymbol("NOT   IN");
      fail("should throw IllegalArgumentException");
    } catch (final IllegalArgumentException e) {
      //  pass
    }

    try {
      CollectionOperator.forSymbol("IN IN");
      fail("should throw IllegalArgumentException");
    } catch (final IllegalArgumentException e) {
      //  pass
    }
  }
}
