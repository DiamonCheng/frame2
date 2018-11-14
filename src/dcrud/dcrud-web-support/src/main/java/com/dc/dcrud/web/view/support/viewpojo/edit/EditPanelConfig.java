package com.dc.dcrud.web.view.support.viewpojo.edit;

import java.lang.annotation.*;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/12.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EditPanelConfig {
    String editTitle() default "crud.query.table.option.update";
    
    String addTitle() default "crud.query.option.add";
    
    String editSubmitPath() default "save";
    
    String addSubmitPath() default "save";
    
    String backHref() default "./";
}
