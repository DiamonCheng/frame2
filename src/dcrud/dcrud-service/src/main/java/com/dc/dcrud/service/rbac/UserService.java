package com.dc.dcrud.service.rbac;

import com.dc.dcrud.dao.UserDao;
import com.dc.dcrud.domain.UserEntity;
import com.dc.dcrud.searcher.UserSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public UserEntity getUserByUsername(String username) {
        return userDao.getUserEntityByUsername(username);
    }
    
    public void searchPage(UserSearcher searcher) {
        userDao.searchPage(searcher);
    }
    
    public UserEntity get(Long id) {
        return userDao.getOne(id);
    }
}
