package com.dc.dcrud.web.view.support.viewpojo.datatable;

import java.lang.annotation.*;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/1.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DataTableConfig {
    String id() default "";
    
    int pageSize() default 10;
    
    int[] pageSizes() default {10, 50, 100};
    
    TableColumnConfig[] columns();
    
    TableOptionButtonConfig[] buttons() default {};
    
    String optionButtonColumnName() default "crud.query.table.option.head";
    
    String optionButtonColumnWidth() default "";
}
