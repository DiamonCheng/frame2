package com.dc.dcrud.web.view.support.viewpojo.inputview;

import com.dc.frame2.view.view.freemarker.form.Option;

import java.util.List;
import java.util.Locale;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/15.
 */
public interface OptionProvider {
    List<Option> listOptions(String optionKey, Locale locale);
}
