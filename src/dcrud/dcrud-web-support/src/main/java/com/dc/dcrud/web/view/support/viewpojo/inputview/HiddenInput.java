package com.dc.dcrud.web.view.support.viewpojo.inputview;

import java.lang.annotation.*;

/**
 * <p>Mark a field in ConditionsGroup display as a Readonly Text Input View
 *
 * @author Diamon.Cheng
 * @date 2018/9/18.
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@InputViewConfigurator(HiddenInputGenerator.class)
public @interface HiddenInput {
    /**
     * define DOM id of this input element
     *
     * @return a string
     */
    String id() default "";
    
    String name() default "";
    
}
