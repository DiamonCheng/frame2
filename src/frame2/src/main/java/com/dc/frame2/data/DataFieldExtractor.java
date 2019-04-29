package com.dc.frame2.data;

import com.dc.frame2.util.ReflectionUtils;
import com.dc.frame2.util.SpringContextUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * to extractText a field value to string
 * <p>Use @Extractor on field to customer extractText
 *
 * @author Diamon.Cheng
 * @date 2018/6/5.
 */
public interface DataFieldExtractor<T, TT> {
    
    /**
     * given entity data and field name, to transfer field value to a readable string text
     */
    @SuppressWarnings("unchecked")
    static String extractText(Object data, String field) {
        if (data == null) {
            return null;
        }
        Object value = null;
        try {
            value = ReflectionUtils.getValueByField(data, field);
        } catch (Exception e) {
            throw new IllegalStateException("Try extractText data field failed. Maybe this field not exists in entity. data:" + data + ", field:" + field, e);
        }
        if (value == null) {
            return null;
        }
        Extractor extractor = ReflectionUtils.getFieldAnnotation(data.getClass(), field, Extractor.class);
        if (extractor != null) {
            Class<? extends DataFieldExtractor> dataFiledExtractorClass = extractor.value();
            DataFieldExtractor dataFiledExtractor;
            IllegalStateException exception = new IllegalStateException("Try extractText data field failed. data:" + data + ", field:" + field);
            try {
                try {
                    dataFiledExtractor = SpringContextUtils.getBean(dataFiledExtractorClass);
                } catch (Exception e) {
                    exception.addSuppressed(e);
                    dataFiledExtractor = dataFiledExtractorClass.newInstance();
                }
                return dataFiledExtractor.extractText(data, field, value);
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
    
    /**
     * given entity data and field name, to transfer field value to a string value
     */
    @SuppressWarnings("unchecked")
    static String extractValue(Object data, String field) {
        if (data == null) {
            return null;
        }
        Object value = null;
        try {
            value = ReflectionUtils.getValueByField(data, field);
        } catch (Exception e) {
            throw new IllegalStateException("Try extractValue data field failed. Maybe this field not exists in entity. data:" + data + ", field:" + field, e);
        }
        if (value == null) {
            return null;
        }
        Extractor extractor = ReflectionUtils.getFieldAnnotation(data.getClass(), field, Extractor.class);
        if (extractor != null) {
            Class<? extends DataFieldExtractor> dataFiledExtractorClass = extractor.value();
            DataFieldExtractor dataFiledExtractor;
            IllegalStateException exception = new IllegalStateException("Try extractValue data field failed. data:" + data + ", field:" + field);
            try {
                try {
                    dataFiledExtractor = SpringContextUtils.getBean(dataFiledExtractorClass);
                } catch (Exception e) {
                    exception.addSuppressed(e);
                    dataFiledExtractor = dataFiledExtractorClass.newInstance();
                }
                return dataFiledExtractor.extractValue(data, field, value);
            } catch (Exception e) {
                exception.addSuppressed(e);
                throw exception;
            }
        }
        if (value instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
        }
        if (value.getClass().isArray()) {
            return Arrays.stream((Object[]) value).map(Object::toString).reduce("", (a, b) -> StringUtils.isEmpty(a) ? b : a + "," + b);
        }
        return value.toString();
    }
    
    String extractText(T data, String field, TT fieldValue);
    
    String extractValue(T data, String field, TT fieldValue);
}
