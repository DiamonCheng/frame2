package com.dc.frame2.web.controller;

import com.dc.frame2.busi.dao.UserDao;
import com.dc.frame2.busi.domain.UserEntity;
import com.dc.frame2.busi.dto.UserSearcher;
import com.dc.frame2.core.dto.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/10.
 */
@Controller
public class IndexController {
    
    @Autowired
    private UserDao userDao;
    
    
    @RequestMapping({"/", "/index"})
    public Object index() {
        return "index";
    }
    
    @RequestMapping("/user/insert")
    @ResponseBody
    public Object insertUser() {
        userDao.save(new UserEntity()
                               .setNickName("沈程辉_" + new Random().nextInt(2333))
                               .setUserName("admin_" + new Random().nextInt(2333))
                               .setUserPassword("2333")
        );
        return new AjaxResult();
    }
    
    @RequestMapping("/user/search")
    @ResponseBody
    public Object searchUser(UserSearcher searcher){
        return userDao.searchPage(searcher);
    }
}
