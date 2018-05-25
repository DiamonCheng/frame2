package com.dc.dcrud.web.controller.test;

import com.dc.dcrud.dao.UserDao;
import com.dc.dcrud.domain.UserEntity;
import com.dc.dcrud.searcher.UserSearcher;
import com.dc.frame2.core.dto.AjaxResult;
import com.dc.frame2.view.view.freemarker.ListContentView;
import com.dc.frame2.view.view.freemarker.SimpleContentView;
import com.dc.frame2.view.view.freemarker.SimpleContentView2;
import com.dc.frame2.view.view.freemarker.form.FormView;
import com.dc.frame2.view.view.freemarker.page.CssResource;
import com.dc.frame2.view.view.freemarker.page.JsResource;
import com.dc.frame2.view.view.freemarker.page.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/5/6.
 */
@Controller
public class TestController {
    @Autowired
    private UserDao userDao;
    
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
    public Object searchUser(UserSearcher searcher) {
        userDao.searchPage(searcher);
        return searcher;
    }
    
    @RequestMapping({"/index2"})
    public Object index2() {
        return "index2";
    }
    
    @RequestMapping("/testF2")
    public Object frame2() {
        return new SimpleContentView2().setSimpleContentView(
                new ListContentView().addView(new SimpleContentView().setName("DCCCCCCCCC<><><><M><><><CCCCCCCCCCC"))
                        .addView(new SimpleContentView().setName("~~~~ List 2 content ~~~~~~"))
                        .addView(new SimpleContentView().setName("~~~~ List 3 测试中文 ~~~~~~"))
                        .addView(new SimpleContentView().setName("~~~~ List 4 测试中文2~~~~~~"))
        );
    }
    
    @RequestMapping("/testPageView")
    public Object pageView() {
        return new PageView()
                       .setTitle("login.page.bottomBtn3")
                       .addBottomJsResource(new JsResource().setPath("/resources/js/common/common.js"))
                       .addHeadResource(new CssResource().setPath("/resources/css/common/app.css"))
                       .addComponent(new ListContentView().addView(new SimpleContentView().setName("DCCCCCCCCC<><><><M><><><CCCCCCCCCCC"))
                                             .addView(new SimpleContentView().setName("~~~~ List 2 content ~~~~~~"))
                                             .addView(new SimpleContentView().setName("~~~~ List 3 测试中文 ~~~~~~"))
                                             .addView(new SimpleContentView().setName("~~~~ List 4 测试中文2~~~~~~")))
                       .addComponent(new SimpleContentView2().setSimpleContentView(
                               new ListContentView().addView(new SimpleContentView().setName("DCCCCCCCCC<><><><M><><><CCCCCCCCCCC"))
                                       .addView(new SimpleContentView().setName("~~~~ List 2 content ~~~~~~"))
                                       .addView(new SimpleContentView().setName("~~~~ List 3 测试中文 ~~~~~~"))
                                       .addView(new SimpleContentView().setName("~~~~ List 4 测试中文2~~~~~~"))
                       ));
    }
    
    @RequestMapping("/testFormView")
    public Object testFormView() {
        return new PageView()
                       .setTitle("login.page.bottomBtn3")
                       .addBottomJsResource(new JsResource().setPath("/resources/js/common/common.js"))
                       .addHeadResource(new CssResource().setPath("/resources/css/common/app.css"))
                       .addComponent(new FormView().setAction("").setMethod("GET").setId("pageForm")
                                             .addContent(new SimpleContentView().setName("DCCCCCCCCC<><><><M><><><CCCCCCCCCCC"))
                                             .addContent(new SimpleContentView().setName("~~~~ List 2 content ~~~~~~"))
                                             .addContent(new SimpleContentView().setName("~~~~ List 3 测试中文 ~~~~~~"))
                                             .addContent(new SimpleContentView().setName("~~~~ List 4 测试中文2~~~~~~")))
                       .addComponent(new SimpleContentView2().setSimpleContentView(
                               new ListContentView().addView(new SimpleContentView().setName("DCCCCCCCCC<><><><M><><><CCCCCCCCCCC"))
                                       .addView(new SimpleContentView().setName("~~~~ List 2 content ~~~~~~"))
                                       .addView(new SimpleContentView().setName("~~~~ List 3 测试中文 ~~~~~~"))
                                       .addView(new SimpleContentView().setName("~~~~ List 4 测试中文2~~~~~~"))
                       ));
    }
}
