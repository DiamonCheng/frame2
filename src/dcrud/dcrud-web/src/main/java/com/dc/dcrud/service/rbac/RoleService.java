package com.dc.dcrud.service.rbac;

import com.dc.dcrud.dao.ResourceDao;
import com.dc.dcrud.dao.RoleDao;
import com.dc.dcrud.model.domain.RoleEntity;
import com.dc.dcrud.model.searcher.RoleSearcher;
import com.dc.frame2.util.OptimisticLockCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/21.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceDao resourceDao;
    
    public void searchPage(RoleSearcher searcher) {
        roleDao.searchPage(searcher);
    }
    
    public RoleEntity get(Long id) {
        return roleDao.findById(id).orElse(null);
    }
    
    public void saveOrUpdate(RoleEntity roleEntity) {
        if (roleEntity.getId() == null) {
            //insert
            if (roleEntity.getResources() != null) {
                roleEntity.setResources(
                        roleEntity.getResources().stream()
                                .map(r -> resourceDao.findById(r.getId()).orElse(null))
                                .collect(Collectors.toSet())
                );
            }
            roleDao.save(roleEntity);
        } else {
            //update
            RoleEntity roleEntity1 = roleDao.findById(roleEntity.getId()).orElse(null);
            OptimisticLockCheckUtil.checkOptimisticLock(roleEntity1, roleEntity);
            roleEntity1.setName(roleEntity.getName())
                    .setCode(roleEntity.getCode())
                    .setDescription(roleEntity.getDescription());
            if (roleEntity.getResources() != null) {
                roleEntity1.setResources(
                        roleEntity.getResources().stream()
                                .map(r -> resourceDao.findById(r.getId()).orElse(null))
                                .collect(Collectors.toSet())
                );
            }
            roleDao.save(roleEntity1);
        }
    }
    
    public void delete(RoleEntity roleEntity) {
        //todo check user list
        RoleEntity roleEntity1 = get(roleEntity.getId());
        OptimisticLockCheckUtil.checkOptimisticLock(roleEntity1, roleEntity);
        roleEntity1.getResources().clear();
        roleDao.save(roleEntity1);
        roleDao.delete(roleEntity1);
    }
    
    
}
