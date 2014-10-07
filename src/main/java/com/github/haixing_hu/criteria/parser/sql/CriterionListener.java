// Generated from com/github/haixing_hu/criteria/parser/sql/Criterion.g4 by ANTLR 4.3
package com.github.haixing_hu.criteria.parser.sql;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CriterionParser}.
 */
public interface CriterionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CriterionParser#likeCriterion}.
	 * @param ctx the parse tree
	 */
	void enterLikeCriterion(@NotNull CriterionParser.LikeCriterionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CriterionParser#likeCriterion}.
	 * @param ctx the parse tree
	 */
	void exitLikeCriterion(@NotNull CriterionParser.LikeCriterionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CriterionParser#criterion}.
	 * @param ctx the parse tree
	 */
	void enterCriterion(@NotNull CriterionParser.CriterionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CriterionParser#criterion}.
	 * @param ctx the parse tree
	 */
	void exitCriterion(@NotNull CriterionParser.CriterionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CriterionParser#binaryCriterion}.
	 * @param ctx the parse tree
	 */
	void enterBinaryCriterion(@NotNull CriterionParser.BinaryCriterionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CriterionParser#binaryCriterion}.
	 * @param ctx the parse tree
	 */
	void exitBinaryCriterion(@NotNull CriterionParser.BinaryCriterionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CriterionParser#valueCriterion}.
	 * @param ctx the parse tree
	 */
	void enterValueCriterion(@NotNull CriterionParser.ValueCriterionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CriterionParser#valueCriterion}.
	 * @param ctx the parse tree
	 */
	void exitValueCriterion(@NotNull CriterionParser.ValueCriterionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CriterionParser#collectionCriterion}.
	 * @param ctx the parse tree
	 */
	void enterCollectionCriterion(@NotNull CriterionParser.CollectionCriterionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CriterionParser#collectionCriterion}.
	 * @param ctx the parse tree
	 */
	void exitCollectionCriterion(@NotNull CriterionParser.CollectionCriterionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CriterionParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(@NotNull CriterionParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link CriterionParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(@NotNull CriterionParser.LineContext ctx);

	/**
	 * Enter a parse tree produced by {@link CriterionParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(@NotNull CriterionParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CriterionParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(@NotNull CriterionParser.PropertyContext ctx);

	/**
	 * Enter a parse tree produced by {@link CriterionParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(@NotNull CriterionParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CriterionParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(@NotNull CriterionParser.PatternContext ctx);

	/**
	 * Enter a parse tree produced by {@link CriterionParser#unaryCriterion}.
	 * @param ctx the parse tree
	 */
	void enterUnaryCriterion(@NotNull CriterionParser.UnaryCriterionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CriterionParser#unaryCriterion}.
	 * @param ctx the parse tree
	 */
	void exitUnaryCriterion(@NotNull CriterionParser.UnaryCriterionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CriterionParser#combinedCriterion}.
	 * @param ctx the parse tree
	 */
	void enterCombinedCriterion(@NotNull CriterionParser.CombinedCriterionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CriterionParser#combinedCriterion}.
	 * @param ctx the parse tree
	 */
	void exitCombinedCriterion(@NotNull CriterionParser.CombinedCriterionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CriterionParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull CriterionParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CriterionParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull CriterionParser.ValueContext ctx);
}