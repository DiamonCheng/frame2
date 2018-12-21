package com.dc.dcrud.web.view.support.viewpojo.datatable;

import java.lang.annotation.*;

/**
 * <p>Annotated on XxxSearcher to define table config such as columns in a page query view.
 *
 * @author Diamon.Cheng
 * @date 2018/11/1.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DataTableConfig {
    /**
     * @return DOM id
     */
    String id() default "";
    
    /**
     * @return candidate page size selection
     */
    int[] pageSizes() default {10, 50, 100};
    
    /**
     * @return column definition
     */
    TableColumnConfig[] columns();
    
    /**
     * @return column button definition
     */
    TableOptionButtonConfig[] buttons() default {};
    
    /**
     * @return column name key of option button column
     */
    String optionButtonColumnName() default "crud.query.table.option.head";
    
    /**
     * @return define width of option column. should append unit such as 'px'
     */
    String optionButtonColumnWidth() default "";
}
