package com.dc.dcrud.web.view.support.viewpojo.inputview;

import java.lang.annotation.*;

/**
 * <p>Mark a field in ConditionsGroup display as a Text Input View
 *
 * @author Diamon.Cheng
 * @date 2018/9/18.
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@InputViewConfigurator(NumberInputGenerator.class)
public @interface NumberInput {
    /**
     * Not required. define the name attribute of this HTML element.
     * In Spring MVC the input name will auto reflect to the parameter.
     *
     * @return a string
     */
    String name() default "";
    
    /**
     * define placeholder of this input element.
     *
     * @return a string, the string will be translated when use key in i18n/message*
     */
    String placeHolder() default "";
    
    /**
     * define DOM id of this input element
     *
     * @return a string
     */
    String id() default "";
    
    /**
     * Not required. Default is a full field path such as com.dc.dcrud.searcher.UserSearcher.nickName
     * define label text of this condition.
     *
     * @return a string, the string will be translated when use key in i18n/message*
     */
    String label() default "";
    
    /**
     * Not required. using validator.js . Rules are in validator.list.js
     *
     * @return string array
     */
    String[] validators() default {};
}
