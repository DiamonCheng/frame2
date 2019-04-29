package com.dc.dcrud.service.rbac;

import com.dc.dcrud.dao.RoleDao;
import com.dc.dcrud.dao.UserDao;
import com.dc.dcrud.model.domain.UserEntity;
import com.dc.dcrud.model.searcher.UserSearcher;
import com.dc.frame2.util.OptimisticLockCheckUtil;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/21.
 */
@Service
@Transactional(rollbackFor = Exception.class)
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
        return userDao.findById(id).orElse(null);
    }
    
    public void saveOrUpdate(UserEntity userEntity) {
        if (userEntity.getId() == null) {
            //insert
            userEntity.setPassword(new Sha1Hash(defaultPasswd, userEntity.getUsername()).toHex());
            userEntity.setRoles(userEntity.getRoles().stream().map(r -> roleDao.findById(r.getId()).orElse(null)).collect(Collectors.toSet()));
            userDao.save(userEntity);
        } else {
            //update
            UserEntity userEntity1 = userDao.findById(userEntity.getId()).orElse(null);
            OptimisticLockCheckUtil.checkOptimisticLock(userEntity1, userEntity);
            userEntity1
                    .setNickName(userEntity.getNickName())
                    .setRoles(userEntity.getRoles().stream().map(r -> roleDao.findById(r.getId()).orElse(null)).collect(Collectors.toSet()))
            ;
            userDao.save(userEntity1);
        }
    }
    
    public void resetPassword(UserEntity userEntity) {
        //update
        UserEntity userEntity1 = userDao.findById(userEntity.getId()).orElse(null);
        OptimisticLockCheckUtil.checkOptimisticLock(userEntity1, userEntity);
        userEntity.setPassword(new Sha1Hash(defaultPasswd, userEntity.getUsername()).toHex());
        userDao.save(userEntity1);
    }
    public void delete(UserEntity userEntity) {
        UserEntity userEntity1 = get(userEntity.getId());
        OptimisticLockCheckUtil.checkOptimisticLock(userEntity1, userEntity);
        userEntity1.getRoles().clear();
        userDao.save(userEntity1);
        userDao.delete(userEntity1);
    }
    
    public List<UserEntity> list(){
        return userDao.findAll();
    }
}
