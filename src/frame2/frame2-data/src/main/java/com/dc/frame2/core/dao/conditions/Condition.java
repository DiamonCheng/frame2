package com.dc.frame2.core.dao.conditions;


import java.lang.annotation.*;

/**
 * specify this field condition.
 * @author DC
 * @date 2018/4/14.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Condition{
    /**
     * <p>specified this condition to compare witch field.
     * <p>use dot(.) to split join field</p>
     * for default use '$' to split join field.
     *
     * for the following equals code <br/>
     * <code>
     *     private String parent$name;
     * </code>
     * <br/>-------------<br/>
     * <code>
     *     \@Condition("parent.name") <br/>
     *     private String parentName;
     * </code>
     */
    String value() default "";
    
    /**
     * specified compare operator use this attr
     * @return default eq
     */
    CompareOperator operator() default CompareOperator.EQ;
    /**
     * specified this condition order use this attr, for general condition, each condition will sort by define order. but it may different in different runtime jvm.
     * @return default 0
     */
    int order() default 0;
    
    /**
     * use witch to contact this condition, default and.
     * @return default AND
     */
    PreContact preContact() default PreContact.AND;
    
    /**
     * use witch join type to join foreign entity. just work for join field.
     * @return default NONE.
     */
    JoinType joinType() default JoinType.NONE;
    
    /**
     * define if this value will be a condition when this value is null
     * @return default false
     */
    boolean nullResolve() default false;
}
