package com.dc.dcrud.web.controller.user;

import com.dc.dcrud.searcher.UserSearcher;
import com.dc.dcrud.service.rbac.UserService;
import com.dc.dcrud.web.view.support.QueryPageViewFactory;
import com.dc.frame2.view.view.freemarker.form.FormView;
import com.dc.frame2.view.view.freemarker.page.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/17.
 */
@Controller
@RequestMapping("/dcrud/user")
public class UserController {
    
    private QueryPageViewFactory queryPageViewFactory = new QueryPageViewFactory();
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/")
    public Object searchPage(UserSearcher searcher) {
        userService.searchPage(searcher);
        PageView pageView = (PageView) queryPageViewFactory.createPageQueryView(searcher);
        FormView formView = (FormView) pageView.getComponents().get(0);
        return pageView;
    }


}
