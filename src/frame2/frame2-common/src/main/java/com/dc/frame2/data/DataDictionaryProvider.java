package com.dc.frame2.data;

import java.util.Collection;
import java.util.Objects;

/**
 * <p>Descriptions...
 * Note:
 * @author Diamon.Cheng
 * @date 2018/5/17.
 */
public interface DataDictionaryProvider extends DataFieldExtractor {
    Collection<DataDictionaryEntry> listEntries();
    
    @Override
    default String extract(Object data, String field, Object fieldValue) {
        for (DataDictionaryEntry dataDictionaryEntry : listEntries()) {
            if (Objects.equals(dataDictionaryEntry.getValue(), fieldValue)) {
                return dataDictionaryEntry.getText();
            }
        }
        return null;
    }
}
