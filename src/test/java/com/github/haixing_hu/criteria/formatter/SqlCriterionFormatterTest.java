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
package com.github.haixing_hu.criteria.formatter;

import org.junit.Test;

import com.github.haixing_hu.criteria.BinaryCriterion;
import com.github.haixing_hu.criteria.BinaryOperator;
import com.github.haixing_hu.criteria.CollectionCriterion;
import com.github.haixing_hu.criteria.CollectionOperator;
import com.github.haixing_hu.criteria.CombinedCriterion;
import com.github.haixing_hu.criteria.LikeCriterion;
import com.github.haixing_hu.criteria.LogicOperator;
import com.github.haixing_hu.criteria.MatchMode;
import com.github.haixing_hu.criteria.UnaryCriterion;
import com.github.haixing_hu.criteria.UnaryOperator;
import com.github.haixing_hu.criteria.ValueCriterion;

import static org.junit.Assert.assertEquals;

/**
 * Unit test of the {@link SqlCriterionFormatter}.
 *
 * @author Haixing Hu
 */
public class SqlCriterionFormatterTest {

  @Test
  public void testFormatUnitaryCriterion() {
    final SqlCriterionFormatter formatter = new SqlCriterionFormatter();

    final UnaryCriterion c1 = new UnaryCriterion("field1", UnaryOperator.NULL);
    assertEquals("field1 IS NULL", formatter.format(c1));

    final UnaryCriterion c2 = new UnaryCriterion("field2", UnaryOperator.NOT_NULL);
    assertEquals("field2 IS NOT NULL", formatter.format(c2));
  }

  @Test
  public void testFormatBinaryCriterion() {
    final SqlCriterionFormatter formatter = new SqlCriterionFormatter();

    final BinaryCriterion c1 = new BinaryCriterion("field1", BinaryOperator.EQUAL, "field2");
    assertEquals("field1 = field2", formatter.format(c1));

    final BinaryCriterion c2 = new BinaryCriterion("field1", BinaryOperator.GREATER, "field2");
    assertEquals("field1 > field2", formatter.format(c2));

    final BinaryCriterion c3 = new BinaryCriterion("field1", BinaryOperator.GREATER_EQUAL, "field2");
    assertEquals("field1 >= field2", formatter.format(c3));

    final BinaryCriterion c4 = new BinaryCriterion("field1", BinaryOperator.LESS, "field2");
    assertEquals("field1 < field2", formatter.format(c4));

    final BinaryCriterion c5 = new BinaryCriterion("field1", BinaryOperator.LESS_EQUAL, "field2");
    assertEquals("field1 <= field2", formatter.format(c5));

    final BinaryCriterion c6 = new BinaryCriterion("field1", BinaryOperator.NOT_EQUAL, "field2");
    assertEquals("field1 != field2", formatter.format(c6));
  }

  @Test
  public void testFormatValueCriterion() {
    final SqlCriterionFormatter formatter = new SqlCriterionFormatter();

    final ValueCriterion c1 = new ValueCriterion("field1", BinaryOperator.EQUAL, true);
    assertEquals("field1 = true", formatter.format(c1));

    final ValueCriterion c2 = new ValueCriterion("field1", BinaryOperator.GREATER, 3.14);
    assertEquals("field1 > 3.14", formatter.format(c2));

    final ValueCriterion c3 = new ValueCriterion("field1", BinaryOperator.GREATER_EQUAL, null);
    assertEquals("field1 >= ?", formatter.format(c3));

    final ValueCriterion c4 = new ValueCriterion("field1", BinaryOperator.LESS, -128);
    assertEquals("field1 < -128", formatter.format(c4));

    final ValueCriterion c5 = new ValueCriterion("field1", BinaryOperator.LESS_EQUAL, "\\a'b%c");
    assertEquals("field1 <= '\\\\a\\\'b%c'", formatter.format(c5));

    final ValueCriterion c6 = new ValueCriterion("field1", BinaryOperator.NOT_EQUAL, 'd');
    assertEquals("field1 != 'd'", formatter.format(c6));
  }

  @Test
  public void testFormatCollectionCriterion() {
    final SqlCriterionFormatter formatter = new SqlCriterionFormatter();

    final CollectionCriterion c1 = new CollectionCriterion("field1",
        CollectionOperator.IN, 1, 2, 3);
    assertEquals("field1 IN (1, 2, 3)", formatter.format(c1));

    final CollectionCriterion c2 = new CollectionCriterion("field1",
        CollectionOperator.NOT_IN, 'a', "xxb", 'c');
    assertEquals("field1 NOT IN ('a', 'xxb', 'c')", formatter.format(c2));

    final CollectionCriterion c3 = new CollectionCriterion("field1",
        CollectionOperator.NOT_IN, 'a', null, 123);
    assertEquals("field1 NOT IN ('a', ?, 123)", formatter.format(c3));
  }

  @Test
  public void testFormatLikeCriterion() {
    final SqlCriterionFormatter formatter = new SqlCriterionFormatter();

    final LikeCriterion c1 = new LikeCriterion("field1", "abc");
    assertEquals("field1 LIKE '%abc%'", formatter.format(c1));

    final LikeCriterion c2 = new LikeCriterion("field1", "de%f", MatchMode.START);
    assertEquals("field1 LIKE 'de\\%f%'", formatter.format(c2));

    final LikeCriterion c3 = new LikeCriterion("field1", "a%c", MatchMode.END);
    assertEquals("field1 LIKE '%a\\%c'", formatter.format(c3));

    final LikeCriterion c4 = new LikeCriterion("field1", "xyz", MatchMode.ANYWHERE);
    assertEquals("field1 LIKE '%xyz%'", formatter.format(c4));

    final LikeCriterion c5 = new LikeCriterion("field1", "x'y\\z%", MatchMode.ANYWHERE);
    assertEquals("field1 LIKE '%x\\\'y\\\\z\\%%'", formatter.format(c5));
  }

  @Test
  public void testFormatCombinedCriterion() {
    final SqlCriterionFormatter formatter = new SqlCriterionFormatter();

    final CombinedCriterion c1 = new CombinedCriterion(LogicOperator.AND,
        new ValueCriterion("field1", BinaryOperator.GREATER, 123),
        new ValueCriterion("field1", BinaryOperator.LESS, 456));
    assertEquals("(field1 > 123) AND (field1 < 456)", formatter.format(c1));

    final CombinedCriterion c2 = new CombinedCriterion(LogicOperator.OR,
        new CombinedCriterion(LogicOperator.AND,
            new ValueCriterion("field1", BinaryOperator.GREATER, 123),
            new ValueCriterion("field1", BinaryOperator.LESS, 456)),
       new BinaryCriterion("field1", BinaryOperator.EQUAL, "field2"),
       new CollectionCriterion("field3", CollectionOperator.IN, 1, 2, 3));
    assertEquals("((field1 > 123) AND (field1 < 456)) OR "
        + "(field1 = field2) OR "
        + "(field3 IN (1, 2, 3))", formatter.format(c2));
  }


}
