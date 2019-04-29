package com.dc.dcrud.service.rbac;

import com.dc.dcrud.dao.ResourceDao;
import com.dc.dcrud.model.domain.ResourceEntity;
import com.dc.dcrud.api.pojo.Menu;
import com.dc.dcrud.model.searcher.ResourceSearcher;
import com.dc.frame2.util.OptimisticLockCheckUtil;
import com.dc.dcrud.web.view.query.TreeSelectInputNode;
import com.dc.dcrud.web.view.support.viewpojo.inputview.TreeNodeProvider;
import com.dc.frame2.core.exception.TranslatableException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/5.
 */
@Service("resourceService")
@Transactional(rollbackFor = Exception.class)
public class ResourceService implements TreeNodeProvider {
    @Autowired
    private ResourceDao resourceDao;
    
    public List<Menu> loadUserMenu() {
        List<ResourceEntity> list = resourceDao.findRootMenu();
        return traverse(list);
    }
    
    private List<Menu> traverse(List<ResourceEntity> resourceEntities) {
        // hibernate 只会返回空集合所以不需要判空
        return resourceEntities.stream()
                       .filter(resourceEntity -> SecurityUtils.getSubject().isPermitted(resourceEntity.getCode()))
                       .map(resourceEntity ->
                                    new Menu()
                                            .setName(chooseFieldByLocal(resourceEntity))
                                            .setCode(resourceEntity.getCode())
                                            .setIcon(resourceEntity.getIconClass())
                                            .setUri(resourceEntity.getRequestURI())
                                            .setChildren(traverse(resourceEntity.getChildren()))
                       ).collect(Collectors.toList());
        
    }
    
    private List<TreeSelectInputNode.TreeNode> traverse2(List<ResourceEntity> operationEntities) {
        // hibernate 只会返回空集合所以不需要判空
        return operationEntities.stream()
                       .map(resourceEntity ->
                                    new TreeSelectInputNode.TreeNode()
                                            .setNodeText(chooseFieldByLocal(resourceEntity))
                                            .setNodeTitle(resourceEntity.getRequestURI())
                                            .setNodeValue(resourceEntity.getId().toString())
                                            .setNodeIcon(resourceEntity.getIconClass())
                                            .setChildren(traverse2(resourceEntity.getChildren()))
                       ).collect(Collectors.toList());
        
    }
    
    @Override
    public List<TreeSelectInputNode.TreeNode> listRootTreeNode() {
        List<ResourceEntity> list = resourceDao.findAllByParentIsNullOrderBySortOrder();
        return traverse2(list);
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
        return resourceDao.findById(id).orElse(null);
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
