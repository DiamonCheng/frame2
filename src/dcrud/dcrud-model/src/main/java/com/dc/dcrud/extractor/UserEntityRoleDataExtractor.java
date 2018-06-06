package com.dc.dcrud.extractor;

import com.dc.dcrud.domain.RoleEntity;
import com.dc.dcrud.domain.UserEntity;
import com.dc.frame2.data.DataFieldExtractor;

import java.util.Set;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/6/6.
 */
public class UserEntityRoleDataExtractor implements DataFieldExtractor<UserEntity, Set<RoleEntity>> {
    @Override
    public String extract(UserEntity data, String field, Set<RoleEntity> fieldValue) {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        for (RoleEntity roleEntity : fieldValue) {
            if (index++ > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(roleEntity.getName());
        }
        return stringBuilder.toString();
    }
}
