package com.dc.dcrud.provider;

import com.dc.dcrud.api.model.UserRO;
import com.dc.dcrud.api.provider.UserServiceProvider;
import com.dc.dcrud.model.domain.UserEntity;
import com.dc.dcrud.service.rbac.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2019/4/16.
 */
@RestController
public class UserServiceProviderImpl implements UserServiceProvider {
    @Autowired
    private UserService userService;
    
    @Override
    public UserRO getUserById(long id){
        UserEntity userEntity=userService.get(id);
        if (userEntity!=null){
            UserRO userRO=new UserRO();
            BeanUtils.copyProperties(userEntity,userRO);
            return userRO;
        }
        return null;
    }
    
    @Override
    public List<UserRO> listUser(){
       return userService.list().stream().map(userEntity -> {
           UserRO userRO=new UserRO();
           BeanUtils.copyProperties(userEntity,userRO);
           return userRO;
       }).collect(Collectors.toList());
    }
}
