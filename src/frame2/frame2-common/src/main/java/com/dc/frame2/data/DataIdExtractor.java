package com.dc.frame2.data;

import com.dc.frame2.util.SpringContextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/17.
 */
public interface DataIdExtractor<T> {
    Map<String, String> extract(T data);
    
    @SuppressWarnings("unchecked")
    static <T2> Map<String, String> extractId(T2 data) {
        Objects.requireNonNull(data);
        IdExtractor idExtractor = data.getClass().getAnnotation(IdExtractor.class);
        // found custom extractor
        if (idExtractor != null) {
            Class<? extends DataIdExtractor> dataIdExtractorClass = idExtractor.value();
            DataIdExtractor dataIdExtractor;
            try {
                dataIdExtractor = SpringContextUtils.getBean(dataIdExtractorClass);
                if (dataIdExtractor == null) {
                    dataIdExtractor = dataIdExtractorClass.newInstance();
                }
                return dataIdExtractor.extract(data);
            } catch (Exception e) {
                throw new IllegalStateException("Extract data id class failed. extractor: " + idExtractor, e);
            }
        }
        
        //not found custom extractor
        Map<String, String> result = new HashMap<>(3);
        org.springframework.util.ReflectionUtils.doWithFields(
                data.getClass(),
                field -> result.put(field.getName(), DataFieldExtractor.extractText(data, field.getName())),
                field -> field.getAnnotation(javax.persistence.Id.class) != null
        );
        return result;
    }
}
