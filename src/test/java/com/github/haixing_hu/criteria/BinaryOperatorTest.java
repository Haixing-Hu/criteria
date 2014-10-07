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
 * Unit test for the {@link BinaryOperator} class.
 *
 * @author Haixing Hu
 */
public class BinaryOperatorTest {

  @Test
  public void testForSymbol() {

    assertEquals(BinaryOperator.EQUAL, BinaryOperator.forSymbol("="));
    assertEquals(BinaryOperator.LESS, BinaryOperator.forSymbol("<"));
    assertEquals(BinaryOperator.LESS_EQUAL, BinaryOperator.forSymbol("<="));
    assertEquals(BinaryOperator.GREATER, BinaryOperator.forSymbol(">"));
    assertEquals(BinaryOperator.GREATER_EQUAL, BinaryOperator.forSymbol(">="));
    assertEquals(BinaryOperator.NOT_EQUAL, BinaryOperator.forSymbol("!="));

    try {
      BinaryOperator.forSymbol(null);
      fail("should throw NullPointerException");
    } catch (final NullPointerException e) {
      //  pass
    }

    try {
      BinaryOperator.forSymbol("");
      fail("should throw IllegalArgumentException");
    } catch (final IllegalArgumentException e) {
      //  pass
    }

    try {
      BinaryOperator.forSymbol(">==");
      fail("should throw IllegalArgumentException");
    } catch (final IllegalArgumentException e) {
      //  pass
    }

    try {
      BinaryOperator.forSymbol("@");
      fail("should throw IllegalArgumentException");
    } catch (final IllegalArgumentException e) {
      //  pass
    }

    try {
      BinaryOperator.forSymbol("<<");
      fail("should throw IllegalArgumentException");
    } catch (final IllegalArgumentException e) {
      //  pass
    }
  }
}
