package com.dc.dcrud.service.rbac;

import com.dc.dcrud.dao.ResourceDao;
import com.dc.dcrud.dao.RoleDao;
import com.dc.dcrud.domain.RoleEntity;
import com.dc.dcrud.searcher.RoleSearcher;
import com.dc.dcrud.service.util.OptimisticLockCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/21.
 */
@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceDao resourceDao;
    
    public void searchPage(RoleSearcher searcher) {
        roleDao.searchPage(searcher);
    }
    
    public RoleEntity get(Long id) {
        return roleDao.getOne(id);
    }
    
    public void saveOrUpdate(RoleEntity roleEntity) {
        if (roleEntity.getId() == null) {
            //insert
            if (roleEntity.getResources() != null) {
                roleEntity.setResources(
                        roleEntity.getResources().stream()
                                .map(r -> resourceDao.findOne(r.getId()))
                                .collect(Collectors.toSet())
                );
            }
            roleDao.save(roleEntity);
        } else {
            //update
            RoleEntity roleEntity1 = roleDao.findOne(roleEntity.getId());
            OptimisticLockCheckUtil.checkOptimisticLock(roleEntity1, roleEntity);
            roleEntity1.setName(roleEntity.getName())
                    .setCode(roleEntity.getCode())
                    .setDescription(roleEntity.getDescription());
            if (roleEntity.getResources() != null) {
                roleEntity1.setResources(
                        roleEntity.getResources().stream()
                                .map(r -> resourceDao.findOne(r.getId()))
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
