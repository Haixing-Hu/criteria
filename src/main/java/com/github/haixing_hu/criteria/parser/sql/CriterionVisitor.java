// Generated from com/github/haixing_hu/criteria/parser/sql/Criterion.g4 by ANTLR 4.3
package com.github.haixing_hu.criteria.parser.sql;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CriterionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CriterionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CriterionParser#likeCriterion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLikeCriterion(@NotNull CriterionParser.LikeCriterionContext ctx);

	/**
	 * Visit a parse tree produced by {@link CriterionParser#criterion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCriterion(@NotNull CriterionParser.CriterionContext ctx);

	/**
	 * Visit a parse tree produced by {@link CriterionParser#binaryCriterion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryCriterion(@NotNull CriterionParser.BinaryCriterionContext ctx);

	/**
	 * Visit a parse tree produced by {@link CriterionParser#valueCriterion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueCriterion(@NotNull CriterionParser.ValueCriterionContext ctx);

	/**
	 * Visit a parse tree produced by {@link CriterionParser#collectionCriterion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollectionCriterion(@NotNull CriterionParser.CollectionCriterionContext ctx);

	/**
	 * Visit a parse tree produced by {@link CriterionParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(@NotNull CriterionParser.LineContext ctx);

	/**
	 * Visit a parse tree produced by {@link CriterionParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(@NotNull CriterionParser.PropertyContext ctx);

	/**
	 * Visit a parse tree produced by {@link CriterionParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(@NotNull CriterionParser.PatternContext ctx);

	/**
	 * Visit a parse tree produced by {@link CriterionParser#unaryCriterion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryCriterion(@NotNull CriterionParser.UnaryCriterionContext ctx);

	/**
	 * Visit a parse tree produced by {@link CriterionParser#combinedCriterion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCombinedCriterion(@NotNull CriterionParser.CombinedCriterionContext ctx);

	/**
	 * Visit a parse tree produced by {@link CriterionParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull CriterionParser.ValueContext ctx);
}