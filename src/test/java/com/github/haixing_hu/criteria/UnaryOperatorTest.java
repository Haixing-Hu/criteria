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
