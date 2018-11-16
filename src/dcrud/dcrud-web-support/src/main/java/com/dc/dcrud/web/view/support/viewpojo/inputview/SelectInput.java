package com.dc.dcrud.web.view.support.viewpojo.inputview;

import java.lang.annotation.*;

/**
 * <p>Mark a field in ConditionsGroup display as a Select Input View
 *
 * @author Diamon.Cheng
 * @date 2018/9/18.
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@InputViewConfigurator(SelectInputGenerator.class)
public @interface SelectInput {
    /**
     * Not required. define the name attribute of this HTML element.
     * In Spring MVC the input name will auto reflect to the parameter.
     * <p>
     * when a field is a entity or list&lt;entity&gt; field, this field need to set as xxx.id
     *
     * @return a string
     */
    String name() default "";
    
    /**
     * define placeholder of this input element.
     * when init and empty selected, show a selection with this placeholder.
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
    
    /**
     * define option provider class. it's suggested that use a spring bean class
     *
     * @return Bean id or class path of Class<? extends OptionProvider> , this class need to be a spring bean or can initialize with no arguments.
     */
    String optionProvider();
    
    /**
     * define option provider key when invoke optionProvider.optionList
     *
     * @return a string
     */
    String optionProviderKey() default "";
}
