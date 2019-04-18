package com.dc.dcrud.web.controller.rbac;

import com.dc.dcrud.model.domain.RoleEntity;
import com.dc.dcrud.model.domain.UserEntity;
import com.dc.dcrud.model.searcher.UserSearcher;
import com.dc.dcrud.service.rbac.UserService;
import com.dc.dcrud.web.view.support.EditViewFactory;
import com.dc.dcrud.web.view.support.QueryPageViewFactory;
import com.dc.dcrud.web.vo.rbac.UserEntityEditVO;
import com.dc.frame2.core.domain.BaseConfigEntity;
import com.dc.frame2.core.dto.AjaxResult;
import com.dc.frame2.view.view.freemarker.form.FormView;
import com.dc.frame2.view.view.freemarker.page.PageView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.stream.Collectors;

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
    private EditViewFactory addViewFactory = new EditViewFactory();
    
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
        return addViewFactory.generateAddView(new UserEntity());
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public Object edit(Long id) {
        UserEntity userEntity = userService.get(id);
        UserEntityEditVO userEntityVO = new UserEntityEditVO();
        BeanUtils.copyProperties(userEntity, userEntityVO);
        userEntityVO.setRoles(userEntity.getRoles().stream().map(BaseConfigEntity::getId).toArray(Long[]::new));
        return editViewFactory.generateEditView(userEntityVO);
    }
    
    @RequestMapping(value = {"/add", "/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public Object save(UserEntityEditVO editVO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(editVO, userEntity);
        if (editVO.getRoles() != null) {
            userEntity.setRoles(Arrays.stream(editVO.getRoles()).map(s -> new RoleEntity() {{
                setId(s);
            }}).collect(Collectors.toSet()));
        }
        userService.saveOrUpdate(userEntity);
        return new AjaxResult();
    }
    
    @RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.DELETE})
    @ResponseBody
    public Object delete(UserEntity userEntity) {
        userService.delete(userEntity);
        return new AjaxResult();
    }
    
    @RequestMapping(value = "/resetPassword", method = {RequestMethod.POST})
    @ResponseBody
    public Object resetPassword(UserEntity userEntity) {
        userService.resetPassword(userEntity);
        return new AjaxResult();
    }
    
}
