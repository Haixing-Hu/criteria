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
 * Unit test for the {@link UnaryOperator} class.
 *
 * @author Haixing Hu
 */
public class UnaryOperatorTest {

  @Test
  public void testForSymbol() {

    assertEquals(UnaryOperator.NULL, UnaryOperator.forSymbol("IS NULL"));
    assertEquals(UnaryOperator.NOT_NULL, UnaryOperator.forSymbol("IS NOT NULL"));
    assertEquals(UnaryOperator.NULL, UnaryOperator.forSymbol("is null"));
    assertEquals(UnaryOperator.NOT_NULL, UnaryOperator.forSymbol("Is Not Null"));
    assertEquals(UnaryOperator.NULL, UnaryOperator.forSymbol("IS NuLL"));
    assertEquals(UnaryOperator.NOT_NULL, UnaryOperator.forSymbol("is not null"));

    try {
      UnaryOperator.forSymbol(null);
      fail("should throw NullPointerException");
    } catch (final NullPointerException e) {
      //  pass
    }

    try {
      UnaryOperator.forSymbol("");
      fail("should throw IllegalArgumentException");
    } catch (final IllegalArgumentException e) {
      //  pass
    }

    try {
      UnaryOperator.forSymbol("NULL");
      fail("should throw IllegalArgumentException");
    } catch (final IllegalArgumentException e) {
      //  pass
    }

    //  FIXME: shall we need to treat successive spaces as one?
    try {
      UnaryOperator.forSymbol("IS   NULL");
      fail("should throw IllegalArgumentException");
    } catch (final IllegalArgumentException e) {
      //  pass
    }

    try {
      UnaryOperator.forSymbol("NOT NULL");
      fail("should throw IllegalArgumentException");
    } catch (final IllegalArgumentException e) {
      //  pass
    }
  }
}
