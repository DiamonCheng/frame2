package com.dc.dcrud.service.rbac;

import com.dc.dcrud.dao.RoleDao;
import com.dc.dcrud.dao.UserDao;
import com.dc.dcrud.domain.UserEntity;
import com.dc.dcrud.searcher.UserSearcher;
import com.dc.dcrud.service.util.OptimisticLockCheckUtil;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/21.
 */
@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RoleDao roleDao;
    
    private String defaultPasswd = "233333";
    
    public UserEntity getUserByUsername(String username) {
        return userDao.getUserEntityByUsername(username);
    }
    
    public void searchPage(UserSearcher searcher) {
        userDao.searchPage(searcher);
    }
    
    public UserEntity get(Long id) {
        return userDao.getOne(id);
    }
    
    public void saveOrUpdate(UserEntity userEntity) {
        if (userEntity.getId() == null) {
            //insert
            userEntity.setPassword(new Sha1Hash(defaultPasswd, userEntity.getUsername()).toHex());
            userEntity.setRoles(userEntity.getRoles().stream().map(r -> roleDao.getOne(r.getId())).collect(Collectors.toSet()));
            userDao.save(userEntity);
        } else {
            //update
            UserEntity userEntity1 = userDao.findOne(userEntity.getId());
            OptimisticLockCheckUtil.checkOptimisticLock(userEntity1, userEntity);
            userEntity1
                    .setNickName(userEntity.getNickName())
                    .setRoles(userEntity.getRoles().stream().map(r -> roleDao.getOne(r.getId())).collect(Collectors.toSet()))
            ;
            userDao.save(userEntity1);
        }
    }
    
    public void delete(UserEntity userEntity) {
        UserEntity userEntity1 = get(userEntity.getId());
        OptimisticLockCheckUtil.checkOptimisticLock(userEntity1, userEntity);
        userEntity1.getRoles().clear();
        userDao.save(userEntity1);
        userDao.delete(userEntity1);
    }
}
