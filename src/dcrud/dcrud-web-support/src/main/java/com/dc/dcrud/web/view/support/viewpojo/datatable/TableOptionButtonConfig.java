package com.dc.dcrud.web.view.support.viewpojo.datatable;

import com.dc.dcrud.web.view.data.TableOptionButtonFilter;
import com.dc.dcrud.web.view.option.OperationPermissionCheck;

import java.lang.annotation.*;

/**
 * <p>Annotation in DataTableConfig to define row option buttons
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
    
    /**
     * @return text description of this button
     */
    String name();
    
    /**
     * @return title text when mouse hover on this button, default same as name
     */
    String title() default "";
    
    /**
     * @return DOM id
     */
    String id() default "";
    
    /**
     * @return DOM class
     */
    String[] classes() default {};
    
    /**
     * @return link to another page, will append data id and version parameter automatically
     */
    String href() default "javascript:";
    
    /**
     * @return call back called when render this table, decide whether show this button
     */
    Class<? extends OperationPermissionCheck> permissionCheckClass() default OperationPermissionCheck.class;
    
    /**
     * @return Button type with style
     */
    com.dc.dcrud.web.view.option.OptionButton.Type type() default com.dc.dcrud.web.view.option.OptionButton.Type.DEFAULT;
    
    /**
     * @return call back called when render row, decide whether show this button, by row data.
     */
    Class<? extends TableOptionButtonFilter> filter() default TableOptionButtonFilter.class;
}
