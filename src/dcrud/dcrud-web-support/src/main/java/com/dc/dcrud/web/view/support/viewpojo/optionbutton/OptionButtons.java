package com.dc.dcrud.web.view.support.viewpojo.optionbutton;

import java.lang.annotation.*;

/**
 * <p>Annotated on XxSearcher to define Option Button in a page query view
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
