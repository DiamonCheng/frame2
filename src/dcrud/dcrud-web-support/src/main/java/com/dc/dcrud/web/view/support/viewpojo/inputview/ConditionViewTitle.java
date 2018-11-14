package com.dc.dcrud.web.view.support.viewpojo.inputview;

import java.lang.annotation.*;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/2.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ConditionViewTitle {
    String value();
}
