package com.dc.dcrud.extractor;

import com.dc.dcrud.domain.ResourceEntity;
import com.dc.dcrud.domain.RoleEntity;
import com.dc.frame2.core.domain.BaseConfigEntity;
import com.dc.frame2.data.DataFieldExtractor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/25.
 */
@Service
public class RoleEntityDataExtractor implements DataFieldExtractor<RoleEntity, Set<ResourceEntity>> {
    @Override
    public String extractText(RoleEntity data, String field, Set<ResourceEntity> fieldValue) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String extractValue(RoleEntity data, String field, Set<ResourceEntity> fieldValue) {
        if (fieldValue == null) {
            return null;
        } else {
            return fieldValue.stream()
                           .map(BaseConfigEntity::getId)
                           .map(Object::toString)
                           .reduce(
                                   "",
                                   (str, id) ->
                                           StringUtils.isEmpty(str) ? id : str + "," + id
                           );
        }
    }
}
