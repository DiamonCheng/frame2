package com.dc.dcrud.service.rbac;

import com.dc.dcrud.dao.ResourceDao;
import com.dc.dcrud.domain.ResourceEntity;
import com.dc.dcrud.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/5.
 */
@Service
public class MenuService {
    @Autowired
    private ResourceDao resourceDao;
    
    public List<Menu> loadUserMenu() {
        List<ResourceEntity> list = resourceDao.findRootMenu();
        return traverse(list);
    }
    
    private List<Menu> traverse(List<ResourceEntity> operationEntities) {
        //TODO 1 translate code to name; 2 filter menu by role operation
        return operationEntities.stream()
                       .map(resourceEntity ->
                                    new Menu()
                                            .setName(resourceEntity.getCode())
                                            .setCode(resourceEntity.getCode())
                                            .setIcon(resourceEntity.getIconClass())
                                            .setUri(resourceEntity.getRequestURI())
                                            .setChildren(traverse(resourceEntity.getChildren()))
                       ).collect(Collectors.toList());
        
    }
}
