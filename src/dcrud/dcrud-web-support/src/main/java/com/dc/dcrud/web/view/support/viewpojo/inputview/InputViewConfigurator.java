package com.dc.dcrud.web.view.support.viewpojo.inputview;

import com.dc.dcrud.web.view.support.viewpojo.ViewGenerator;

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
public @interface InputViewConfigurator {
    Class<? extends ViewGenerator> value();
}
