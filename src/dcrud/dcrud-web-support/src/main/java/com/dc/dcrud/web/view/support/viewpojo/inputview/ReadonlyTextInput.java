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
@InputViewConfigurator(ReadonlyTextInputGenerator.class)
public @interface ReadonlyTextInput {
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
    
}
