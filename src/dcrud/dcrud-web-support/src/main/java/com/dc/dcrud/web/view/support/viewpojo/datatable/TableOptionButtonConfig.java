package com.dc.dcrud.web.view.support.viewpojo.datatable;

import com.dc.dcrud.web.view.data.TableOptionButtonFilter;
import com.dc.dcrud.web.view.option.OperationPermissionCheck;

import java.lang.annotation.*;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/1.
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TableOptionButtonConfig {
    /**
     * if define this as true, this button will create a ajax request using href and data-id as param, using POST method when click
     *
     * @return false default
     */
    boolean ajax() default false;
    
    /**
     * if define this and ajax as true, it will pop confirm window before create ajax request.
     *
     * @return true or false
     */
    boolean ajaxConfirm() default false;
    
    String name();
    
    String title() default "";
    
    String id() default "";
    
    String[] classes() default {};
    
    String href() default "javascript:";
    
    Class<? extends OperationPermissionCheck> permissionCheckClass() default OperationPermissionCheck.class;
    
    com.dc.dcrud.web.view.option.OptionButton.Type type() default com.dc.dcrud.web.view.option.OptionButton.Type.DEFAULT;
    
    Class<? extends TableOptionButtonFilter> filter() default TableOptionButtonFilter.class;
}
