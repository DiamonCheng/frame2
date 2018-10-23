package com.dc.dcrud.web.view.support.viewpojo;

import java.lang.annotation.*;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/18.
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ViewConfigurator {
    Class<? extends ViewGenerator> value();
}
