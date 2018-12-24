package com.dc.dcrud.extractor;

import com.dc.frame2.data.DataFieldExtractor;
import com.dc.frame2.util.web.MessageResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/24.
 */
@Service
public class ResourceEntityDataExtractor implements DataFieldExtractor<Object, String> {
    @Autowired
    private MessageResolver messageResolver;
    
    @Override
    public String extractText(Object data, String field, String fieldValue) {
        if ("type".equals(field)) {
            return fieldValue == null ? null : messageResolver.getMessage("com.dc.dcrud.domain.ResourceEntity.type." + fieldValue);
        } else {
            return fieldValue;
        }
    }
    
    @Override
    public String extractValue(Object data, String field, String fieldValue) {
        return fieldValue;
    }
}
