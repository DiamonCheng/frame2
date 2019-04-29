package com.dc.frame2.data;

import java.lang.annotation.*;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/6/5.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface IdExtractor {
    Class<? extends DataIdExtractor> value();
}
