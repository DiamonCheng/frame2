package com.dc.frame2.core.dao.conditions;

import java.lang.annotation.*;

/**
 * <p> Use this annotation to mark a filed as not a condition. (default is a eq field named condition)
 *
 * @author DC
 * @date 2018/4/14.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface NotCondition {
}
