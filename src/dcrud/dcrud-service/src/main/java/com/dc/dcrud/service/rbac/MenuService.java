package com.dc.dcrud.service.rbac;

import com.dc.dcrud.dao.OperationDao;
import com.dc.dcrud.domain.OperationEntity;
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
    private OperationDao operationDao;
    
    public List<Menu> loadUserMenu() {
        List<OperationEntity> list = operationDao.findRootMenu();
        return traverse(list);
    }
    
    private List<Menu> traverse(List<OperationEntity> operationEntities) {
        //TODO 1 translate code to name; 2 filter menu by role operation
        return operationEntities.stream()
                       .map(operationEntity ->
                                    new Menu()
                                            .setName(operationEntity.getCode())
                                            .setCode(operationEntity.getCode())
                                            .setIcon(operationEntity.getIconClass())
                                            .setUri(operationEntity.getRequestURI())
                                            .setChildren(traverse(operationEntity.getChildren()))
                       ).collect(Collectors.toList());
        
    }
}
