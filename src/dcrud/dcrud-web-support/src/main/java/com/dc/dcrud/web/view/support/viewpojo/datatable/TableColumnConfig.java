package com.dc.dcrud.web.view.support.viewpojo.datatable;

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
public @interface TableColumnConfig {
    String path();
    
    String headName();
    
    boolean sortable() default true;
    
    String width() default "";
    
    Align align() default Align.left;
    
    enum Align {
        /**/
        left, right, center
    }
}
