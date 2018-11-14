package com.dc.frame2.core.dao.condition;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 * <p> Define a custom field resolver to define field condition in query
 * <p>T field type</p>
 * <p>TT entity type</p>
 *
 * @author Diamon.Cheng
 * @date 2018/9/17.
 * @see Condition
 */
public interface CustomSearcherFieldResolver<T, TT> {
    /**
     * process your custom condition field.
     *
     * @param root    javax.persistence.criteria.Root A root type in the from clause. Query roots always reference entities.
     * @param path    javax.persistence.criteria.Path Represents a simple or compound attribute path from a bound type or collection, and is a "primitive" expression.
     * @param query   javax.persistence.criteria.CriteriaQuery The CriteriaQuery interface defines functionality that is specific to top-level queries.
     * @param builder javax.persistence.criteria.CriteriaBuilder Used to construct criteria queries, compound selections, expressions, predicates, orderings.
     *                Note that Predicate is used instead of Expression<Boolean> in this API in order to work around the fact that Java generics are not compatible with varags.
     * @param data    the searcher
     * @param value   the field value
     * @return a Predicate, if null will not add to conditions.
     */
    javax.persistence.criteria.Predicate resolve(Root<?> root, Path<T> path, CriteriaQuery<?> query, CriteriaBuilder builder, TT data, T value);
}
