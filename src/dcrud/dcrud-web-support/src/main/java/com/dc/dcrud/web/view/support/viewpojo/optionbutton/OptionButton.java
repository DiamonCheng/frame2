package com.dc.dcrud.web.view.support.viewpojo.optionbutton;

import com.dc.dcrud.web.view.option.OperationPermissionCheck;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/10/30.
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OptionButton {
    /**
     * @return Text description of button
     */
    String name();
    
    /**
     * @return title text when mouse hover on this button, default same as name
     */
    String title() default "";
    
    /**
     * @return DOM id, for add event listen in additional javascript file
     */
    String id() default "";
    
    /**
     * @return DOM class
     */
    String[] classes() default {};
    
    /**
     * @return link to another page, default no op
     */
    String href() default "javascript:";
    
    /**
     * @return call back called when render page, decide whether show this button
     */
    Class<? extends OperationPermissionCheck> permissionCheckClass() default OperationPermissionCheck.class;
    
    //todo permission check key
    
    /**
     * @return Button type with style
     */
    com.dc.dcrud.web.view.option.OptionButton.Type type() default com.dc.dcrud.web.view.option.OptionButton.Type.DEFAULT;
    
}
