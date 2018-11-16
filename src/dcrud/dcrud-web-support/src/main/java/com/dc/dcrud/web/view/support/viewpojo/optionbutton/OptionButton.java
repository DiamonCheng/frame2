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
    String name();
    
    String title() default "";
    
    String id() default "";
    
    String[] classes() default {};
    
    String href() default "javascript:";
    
    Class<? extends OperationPermissionCheck> permissionCheckClass() default OperationPermissionCheck.class;
    
    //todo permission check key
    
    com.dc.dcrud.web.view.option.OptionButton.Type type() default com.dc.dcrud.web.view.option.OptionButton.Type.DEFAULT;
    
}
