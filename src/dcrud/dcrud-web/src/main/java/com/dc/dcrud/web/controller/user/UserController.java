package com.dc.dcrud.web.controller.user;

import com.dc.dcrud.domain.UserEntity;
import com.dc.dcrud.searcher.UserSearcher;
import com.dc.dcrud.service.rbac.UserService;
import com.dc.dcrud.web.view.support.EditViewFactory;
import com.dc.dcrud.web.view.support.QueryPageViewFactory;
import com.dc.frame2.core.dto.AjaxResult;
import com.dc.frame2.view.view.freemarker.form.FormView;
import com.dc.frame2.view.view.freemarker.page.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    private EditViewFactory editViewFactory = new EditViewFactory();
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/")
    public Object searchPage(UserSearcher searcher) {
        userService.searchPage(searcher);
        PageView pageView = (PageView) queryPageViewFactory.createPageQueryView(searcher);
        FormView formView = (FormView) pageView.getComponents().get(0);
        return pageView;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Object add() {
        return editViewFactory.generateAddView(new UserEntity());
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public Object edit(Long id) {
        UserEntity userEntity = userService.get(id);
        
        return editViewFactory.generateEditView(userEntity);
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(UserEntity userEntity) {
        throw new IllegalStateException(" Unknow exception occurred");
        //return new AjaxResult();
    }
    
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(long id) {
        return new AjaxResult();
    }

}
