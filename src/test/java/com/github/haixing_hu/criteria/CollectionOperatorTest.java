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
