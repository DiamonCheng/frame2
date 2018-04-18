package com.dc.dcrud.web.controller;

import com.dc.dcrud.dao.UserDao;
import com.dc.dcrud.domain.UserEntity;
import com.dc.dcrud.searcher.UserSearcher;
import com.dc.frame2.core.dto.AjaxResult;
import com.dc.frame2.view.engine.freemarker.FreeMarkerConfigurationManager;
import com.dc.frame2.view.view.freemarker.SimpleContentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
                               .setUsername("admin_" + new Random().nextInt(2333))
                               .setPassword("2333")
        );
        return new AjaxResult();
    }
    
    @RequestMapping("/user/search")
    @ResponseBody
    public Object searchUser(UserSearcher searcher){
        return userDao.searchPage(searcher);
    }
    
    @RequestMapping({"/index2"})
    public Object index2() {
        return "index2";
    }
    
    
    @Autowired
    private FreeMarkerConfigurationManager freeMarkerConfigurationManager;
    
    @RequestMapping("/testF2")
    public Object frame2(){
        return new SimpleContentView().setConfiguration(freeMarkerConfigurationManager.getConfiguration()).setName("DCCCCCCCCCCCCCCCCCCCC");
    }
}
