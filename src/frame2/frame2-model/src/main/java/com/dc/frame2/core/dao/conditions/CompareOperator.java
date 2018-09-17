package com.dc.frame2.core.dao.conditions;

/**
 * <p>define compare type with this enum.
 *
 * @author DC
 * @date 2018/4/14.
 */
public enum CompareOperator {
    /**
     * use "=" compare this condition
     */
    EQ,
    /**
     * use "like" compare this condition, will not contact "%" character
     */
    LIKE,
    /**
     * use "like" compare this condition, will contact "%" after.  result "name%" , will escape % char
     */
    PRE_LIKE,
    /**
     * use "like" compare this condition, will contact "%" before.  result "%name", will escape % char
     */
    POST_LIKE,
    /**
     * use "like" compare this condition, will contact "%" before and after.  result "%name%", will escape % char
     */
    DUP_LIKE,
    /**
     * use "&gt;" compare this condition.
     */
    GT,
    /**
     * use "&lt;" compare this condition
     */
    LT,
    /**
     * use "&gt;=" compare this condition.
     */
    GTEQ,
    /**
     * use "&lt;=" compare this condition.
     */
    LTEQ,
    /**
     * use "!=" compare this condition.
     */
    NOT_EQ,
    /**
     * use "in" compare this condition, a array type or collection type value can be resolved, else a single(string) value will be resolve as xxx in ('string value')
     */
    IN,
    /**
     * use "not in" compare this condition, a array type or collection type value can be resolved, else a single(string) value will be resolve as xxx not in ('string value')
     */
    NOT_IN
}
