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
@InputViewConfigurator(TreeSelectInputGenerator.class)
public @interface TreeSelectInput {
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
     * define option provider class. it's suggested that use a spring bean class
     *
     * @return Bean id or class path of Class&lt;? extends TreeNodeProvider&gt; , this class need to be a spring bean or can initialize with no arguments.
     */
    String optionProvider();
    
}
