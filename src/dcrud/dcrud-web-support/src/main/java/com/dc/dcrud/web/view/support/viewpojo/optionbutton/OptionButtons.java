package com.dc.dcrud.web.view.support.viewpojo.optionbutton;

import java.lang.annotation.*;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/10/30.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OptionButtons {
    OptionButton[] value();
}
