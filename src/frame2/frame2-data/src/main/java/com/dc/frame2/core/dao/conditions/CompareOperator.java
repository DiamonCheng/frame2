package com.dc.frame2.core.dao.conditions;

/**
 * <p>define compare type with this enum.
 *
 * @author DC
 * @date 2018/4/14.
 */
public enum CompareType {
    /**
     * use "=" compare this condition
     */
    EQ,
    /**
     * use like compare this condition, will not contact "%" character
     */
    LIKE,
    
    PRE_LIKE,
    POST_LIKE,
    BOTH_LIKE,
    GT,
    LT,
    GTEQ,
    LTEQ
}
