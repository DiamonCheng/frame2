package com.dc.frame2.data;

import java.util.Collection;
import java.util.Locale;

/**
 * <p>Descriptions...
 * Note:
 * @author Diamon.Cheng
 * @date 2018/5/17.
 */
public interface DataDictionaryProvider {
    Collection<DataDictionaryEntry> listEntries(Locale locale, String key);
}
