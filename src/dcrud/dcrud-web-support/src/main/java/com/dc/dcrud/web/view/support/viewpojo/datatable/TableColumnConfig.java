package com.dc.dcrud.web.view.support.viewpojo.datatable;

import java.lang.annotation.*;

/**
 * <p>Annotation in DataTableConfig to define each table column
 *
 * @author Diamon.Cheng
 * @date 2018/11/1.
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TableColumnConfig {
    /**
     * @return property name of JavaBean, use dot(.) to split sub JavaBean
     */
    String path();
    
    /**
     * @return table head name
     */
    String headName();
    
    /**
     * @return if enable sort button
     */
    boolean sortable() default true;
    
    /**
     * @return define width of this column. should append unit such as 'px'
     */
    String width() default "";
    
    /**
     * @return define text align of this column.
     */
    Align align() default Align.left;
    
    enum Align {
        /**/
        left, right, center
    }
}
