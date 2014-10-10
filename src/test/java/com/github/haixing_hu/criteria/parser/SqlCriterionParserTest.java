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
package com.github.haixing_hu.criteria.parser;

import org.junit.Test;

import com.github.haixing_hu.criteria.BinaryCriterion;
import com.github.haixing_hu.criteria.BinaryOperator;
import com.github.haixing_hu.criteria.CollectionCriterion;
import com.github.haixing_hu.criteria.CollectionOperator;
import com.github.haixing_hu.criteria.CombinedCriterion;
import com.github.haixing_hu.criteria.Criterion;
import com.github.haixing_hu.criteria.LikeCriterion;
import com.github.haixing_hu.criteria.LogicOperator;
import com.github.haixing_hu.criteria.MatchMode;
import com.github.haixing_hu.criteria.UnaryCriterion;
import com.github.haixing_hu.criteria.UnaryOperator;
import com.github.haixing_hu.criteria.ValueCriterion;
import com.github.haixing_hu.text.ParsingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Unit test for the {@link SqlCriterionParser} class.
 *
 * @author Haixing Hu
 */
public final class SqlCriterionParserTest {

    @Test
    public void testParseUnaryCriterion() throws ParsingException {
	final SqlCriterionParser parser = new SqlCriterionParser();

	final UnaryCriterion c1 = new UnaryCriterion("field1",
		UnaryOperator.NULL);
	assertEquals(c1, parser.parse("field1 IS NULL"));

	final UnaryCriterion c2 = new UnaryCriterion("_field2",
		UnaryOperator.NOT_NULL);
	assertEquals(c2, parser.parse("_field2 IS NOT NULL"));

	final UnaryCriterion c3 = new UnaryCriterion("field1",
		UnaryOperator.NULL);
	assertEquals(c3, parser.parse("field1 is     Null  "));

	final UnaryCriterion c4 = new UnaryCriterion("_field2",
		UnaryOperator.NOT_NULL);
	assertEquals(c4, parser.parse("_field2 is \tNoT          NULL"));
    }

    @Test
    public void testParseBinaryCriterion() throws ParsingException {
	final SqlCriterionParser parser = new SqlCriterionParser();

	final BinaryCriterion c1 = new BinaryCriterion("field1",
		BinaryOperator.EQUAL, "field2");
	assertEquals(c1, parser.parse("field1    = field2"));

	final BinaryCriterion c2 = new BinaryCriterion("field1",
		BinaryOperator.GREATER, "field2");
	assertEquals(c2, parser.parse("field1 > field2"));

	final BinaryCriterion c3 = new BinaryCriterion("field1",
		BinaryOperator.GREATER_EQUAL, "field2");
	assertEquals(c3, parser.parse("field1 >= field2"));

	final BinaryCriterion c4 = new BinaryCriterion("field1",
		BinaryOperator.LESS, "field2");
	assertEquals(c4, parser.parse("field1 < field2"));

	final BinaryCriterion c5 = new BinaryCriterion("field1",
		BinaryOperator.LESS_EQUAL, "field2");
	assertEquals(c5, parser.parse("field1 <=\tfield2"));

	final BinaryCriterion c6 = new BinaryCriterion("field1",
		BinaryOperator.NOT_EQUAL, "field2");
	assertEquals(c6, parser.parse("field1    != field2"));
    }

    @Test
    public void testParseValueCriterion() throws ParsingException {
	final SqlCriterionParser parser = new SqlCriterionParser();

	final ValueCriterion c1 = new ValueCriterion("field1",
		BinaryOperator.EQUAL, Integer.valueOf(-123));
	assertEquals(c1, parser.parse("field1 = -123"));

	final ValueCriterion c2 = new ValueCriterion("field1",
		BinaryOperator.GREATER, Double.valueOf(3.14));
	assertEquals(c2, parser.parse("field1 > 3.14"));

	final ValueCriterion c3 = new ValueCriterion("field1",
		BinaryOperator.GREATER_EQUAL, String.valueOf('c'));
	assertEquals(c3, parser.parse("field1 >= 'c'"));

	final ValueCriterion c4 = new ValueCriterion("field1",
		BinaryOperator.LESS, Double.valueOf(.10e-3));
	assertEquals(c4, parser.parse("field1 < .10e-3"));

	final ValueCriterion c5 = new ValueCriterion("field1",
		BinaryOperator.LESS_EQUAL, "abc");
	assertEquals(c5, parser.parse("field1 <=     'abc'"));

	final ValueCriterion c6 = new ValueCriterion("field1",
		BinaryOperator.NOT_EQUAL, Boolean.valueOf(true));
	assertEquals(c6, parser.parse("field1\t!= true"));

	final ValueCriterion c7 = new ValueCriterion("field1",
		BinaryOperator.NOT_EQUAL, null);
	assertEquals(c7, parser.parse("field1\t!= ?"));
    }

    @Test
    public void testParseCollectionCriterion() throws ParsingException {
	final SqlCriterionParser parser = new SqlCriterionParser();

	final CollectionCriterion c1 = new CollectionCriterion("field1",
		CollectionOperator.IN, -123, 456);
	assertEquals(c1, parser.parse("field1 in (-123, 456)"));

	final CollectionCriterion c2 = new CollectionCriterion("field1",
		CollectionOperator.NOT_IN, 3.14);
	assertEquals(c2, parser.parse("field1 not     \tIn ( 3.14  )"));

	final CollectionCriterion c3 = new CollectionCriterion("field1",
		CollectionOperator.IN, "abc", "'def");
	assertEquals(c3, parser.parse("field1 in ('abc' , '\\'def'  )"));

	final CollectionCriterion c4 = new CollectionCriterion("field1",
		CollectionOperator.NOT_IN, .10e-3, "x\\x'yy");
	assertEquals(c4,
		parser.parse("field1 not In ( .10e-3 , 'x\\\\x\\'yy')"));

	final CollectionCriterion c5 = new CollectionCriterion("field1",
		CollectionOperator.IN, "abc", null, 123);
	assertEquals(c5, parser.parse("field1 in ('abc', ?, 123)"));

	final CollectionCriterion c6 = new CollectionCriterion("field1",
		CollectionOperator.NOT_IN, true, false);
	assertEquals(c6, parser.parse("field1 NOT in ( true, false)"));
    }

    @Test
    public void testParseLikeCriterion() throws ParsingException {
	final SqlCriterionParser parser = new SqlCriterionParser();

	final LikeCriterion c1 = new LikeCriterion("field1", "abc");
	assertEquals(c1, parser.parse("field1 LIKE '%abc%'"));

	final LikeCriterion c2 = new LikeCriterion("field1", "de%f",
		MatchMode.START);
	assertEquals(c2, parser.parse("field1 LIKE 'de\\%f%'"));

	final LikeCriterion c3 = new LikeCriterion("field1", "a%c",
		MatchMode.END);
	assertEquals(c3, parser.parse("field1 LIKE '%a\\%c'"));

	final LikeCriterion c4 = new LikeCriterion("field1", "xyz",
		MatchMode.ANYWHERE);
	assertEquals(c4, parser.parse("field1 LIKE '%xyz%'"));

	final LikeCriterion c5 = new LikeCriterion("field1", "x'y\\z%",
		MatchMode.ANYWHERE);
	assertEquals(c5, parser.parse("field1 LIKE '%x\\\'y\\\\z\\%%'"));
    }

    @Test
    public void testParseCombinedCriterion() throws ParsingException {
	final SqlCriterionParser parser = new SqlCriterionParser();

	final CombinedCriterion c1 = new CombinedCriterion(LogicOperator.AND,
		new ValueCriterion("field1", BinaryOperator.GREATER, 123),
		new ValueCriterion("field1", BinaryOperator.LESS, 456));
	assertEquals(c1, parser.parse("(field1 > 123) AND (field1 < 456)"));

	final CombinedCriterion c2 = new CombinedCriterion(
		LogicOperator.OR,
		new CombinedCriterion(LogicOperator.AND, new ValueCriterion(
			"field1", BinaryOperator.GREATER, 123),
			new ValueCriterion("field1", BinaryOperator.LESS, 456)),
		new BinaryCriterion("field1", BinaryOperator.EQUAL, "field2"),
		new CollectionCriterion("field3", CollectionOperator.IN, 1, 2,
			3));
	assertEquals(c2, parser.parse("((field1 > 123) AND (field1 < 456)) OR "
		+ "(field1 = field2) OR " + "(field3 IN (1, 2, 3))"));
    }

    @Test
    public void testParsingErrors() {
	final SqlCriterionParser parser = new SqlCriterionParser();

	try {
	    parser.parse("hello world");
	    fail("should throw");
	} catch (final ParsingException e) {
	    // pass
	}

	try {
	    parser.parse("1field = 123");
	    fail("should throw");
	} catch (final ParsingException e) {
	    // pass
	}

	try {
	    parser.parse("field1 = 123 xx");
	    fail("should throw");
	} catch (final ParsingException e) {
	    // pass
	}

	try {
	    final Criterion criterion = parser.parse("field1 ! = 'abc'");
	    System.err.println("criterion = " + criterion);
	    fail("should throw");
	} catch (final ParsingException e) {
	    // pass
	}

	try {
	    parser.parse("field1 = \"xyz\"");
	    fail("should throw");
	} catch (final ParsingException e) {
	    // pass
	}

	try {
	    parser.parse("(filed1 = 1) and (field2 > 3) or (field3 < 4)");
	    fail("should throw");
	} catch (final ParsingException e) {
	    // pass
	}
    }
}
