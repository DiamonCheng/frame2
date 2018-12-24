package com.dc.dcrud.service.rbac;

import com.dc.dcrud.dao.ResourceDao;
import com.dc.dcrud.domain.ResourceEntity;
import com.dc.dcrud.pojo.Menu;
import com.dc.dcrud.searcher.ResourceSearcher;
import com.dc.dcrud.service.util.OptimisticLockCheckUtil;
import com.dc.frame2.core.exception.TranslatableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/5.
 */
@Service
public class ResourceService {
    @Autowired
    private ResourceDao resourceDao;
    
    public List<Menu> loadUserMenu() {
        List<ResourceEntity> list = resourceDao.findRootMenu();
        return traverse(list);
    }
    
    private List<Menu> traverse(List<ResourceEntity> operationEntities) {
        //TODO 2 filter menu by role operation
        return operationEntities.stream()
                       .map(resourceEntity ->
                                    new Menu()
                                            .setName(chooseFieldByLocal(resourceEntity))
                                            .setCode(resourceEntity.getCode())
                                            .setIcon(resourceEntity.getIconClass())
                                            .setUri(resourceEntity.getRequestURI())
                                            .setChildren(traverse(resourceEntity.getChildren()))
                       ).collect(Collectors.toList());
        
    }
    
    private static String chooseFieldByLocal(ResourceEntity resourceEntity) {
        Locale locale = LocaleContextHolder.getLocale();
        if ("en".equalsIgnoreCase(locale.getLanguage())) {
            return resourceEntity.getNameEn();
        }
        return resourceEntity.getNameZh();
    }
    
    public void searchPage(ResourceSearcher searcher) {
        resourceDao.searchPage(searcher);
    }
    
    public ResourceEntity get(long id) {
        return resourceDao.findOne(id);
    }
    
    public void delete(ResourceEntity resourceEntity) {
        ResourceEntity resourceEntity1 = get(resourceEntity.getId());
        OptimisticLockCheckUtil.checkOptimisticLock(resourceEntity1, resourceEntity);
        if (resourceEntity1.getChildren() == null || resourceEntity1.getChildren().isEmpty()) {
            throw TranslatableException.builder()
                          .type("BUSINESS")
                          .system("DCRUD")
                          .functionName("RESOURCE-DELETE")
                          .sceneName("CHILDREN-NOT-EMPTY")
                          .message("For resource entity :" + resourceEntity1)
                          .build();
        }
        resourceEntity1.setParent(null);
        resourceDao.save(resourceEntity1);
        resourceDao.delete(resourceEntity1);
    }
    
    public void saveOrUpdate(ResourceEntity resourceEntity) {
        ResourceEntity resourceEntity1;
        if (resourceEntity.getId() != null) {
            resourceEntity1 = get(resourceEntity.getId());
            resourceEntity1.setIconClass(resourceEntity.getIconClass())
                    .setNameEn(resourceEntity.getNameEn())
                    .setNameZh(resourceEntity.getNameZh())
                    .setRequestURI(resourceEntity.getRequestURI())
                    .setSortOrder(resourceEntity.getSortOrder())
                    .setType(resourceEntity.getType());
        } else {
            resourceEntity1 = resourceEntity;
        }
        if (resourceEntity.getParent() != null) {
            resourceEntity1.setParent(get(resourceEntity.getParent().getId()));
        } else {
            resourceEntity1.setParent(null);
        }
        resourceDao.save(resourceEntity1);
    }
}
