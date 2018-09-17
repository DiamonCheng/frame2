package com.dc.frame2.data;

import com.dc.frame2.ReflectionUtils;
import com.dc.frame2.util.SpringContextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * to extract a field value to string
 * <p>Use @Extractor on field to customer extract
 *
 * @author Diamon.Cheng
 * @date 2018/6/5.
 */
public interface DataFieldExtractor<T, TT> {
    
    /**
     * given entity data and field name, to transfer field value to a string value
     */
    @SuppressWarnings("unchecked")
    static String extractString(Object data, String field) {
        if (data == null) {
            return null;
        }
        Object value = null;
        try {
            value = ReflectionUtils.getValueByField(data, field);
        } catch (Exception e) {
            throw new IllegalStateException("Try extract data field failed. Maybe this field not exists in entity. data:" + data + ", field:" + field, e);
        }
        if (value == null) {
            return null;
        }
        Extractor extractor = ReflectionUtils.getFieldAnnotation(data.getClass(), field, Extractor.class);
        if (extractor != null) {
            Class<? extends DataFieldExtractor> dataFiledExtractorClass = extractor.value();
            DataFieldExtractor dataFiledExtractor;
            IllegalStateException exception = new IllegalStateException("Try extract data field failed. data:" + data + ", field:" + field);
            try {
                try {
                    dataFiledExtractor = SpringContextUtils.getBean(dataFiledExtractorClass);
                } catch (Exception e) {
                    exception.addSuppressed(e);
                    dataFiledExtractor = dataFiledExtractorClass.newInstance();
                }
                return dataFiledExtractor.extract(data, field, value);
            } catch (Exception e) {
                exception.addSuppressed(e);
                throw exception;
            }
        }
        if (value instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
        }
        return value.toString();
    }
    
    String extract(T data, String field, TT fieldValue);
}