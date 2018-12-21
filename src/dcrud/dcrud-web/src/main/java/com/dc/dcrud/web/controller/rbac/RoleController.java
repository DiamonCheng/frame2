package com.dc.dcrud.web.controller.rbac;

import com.dc.dcrud.domain.ResourceEntity;
import com.dc.dcrud.domain.RoleEntity;
import com.dc.dcrud.searcher.RoleSearcher;
import com.dc.dcrud.service.rbac.RoleService;
import com.dc.dcrud.web.view.support.EditViewFactory;
import com.dc.dcrud.web.view.support.QueryPageViewFactory;
import com.dc.dcrud.web.vo.rbac.RoleEntityEditVO;
import com.dc.frame2.core.domain.BaseConfigEntity;
import com.dc.frame2.core.dto.AjaxResult;
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
 * @date 2018/12/21.
 */
@Controller
@RequestMapping("/dcrud/role")
public class RoleController {
    private QueryPageViewFactory queryPageViewFactory = new QueryPageViewFactory();
    
    private EditViewFactory editViewFactory = new EditViewFactory();
    private EditViewFactory addViewFactory = new EditViewFactory();
    
    @Autowired
    private RoleService roleService;
    
    @RequestMapping("/")
    public Object searchPage(RoleSearcher searcher) {
        roleService.searchPage(searcher);
        return queryPageViewFactory.createPageQueryView(searcher);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Object add() {
        return addViewFactory.generateAddView(new RoleEntity());
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public Object edit(long id) {
        RoleEntity roleEntity = roleService.get(id);
        RoleEntityEditVO roleEntityEditVO = new RoleEntityEditVO();
        BeanUtils.copyProperties(roleEntity, roleEntityEditVO);
        roleEntityEditVO.setResources(roleEntity.getResources().stream().map(BaseConfigEntity::getId).toArray(Long[]::new));
        return editViewFactory.generateEditView(roleEntityEditVO);
    }
    
    @RequestMapping(value = {"/add", "/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public Object save(RoleEntityEditVO editVO) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(editVO, roleEntity);
        if (editVO.getResources() != null) {
            roleEntity.setResources(Arrays.stream(editVO.getResources()).map(s -> new ResourceEntity() {{
                setId(s);
            }}).collect(Collectors.toSet()));
        }
        roleService.saveOrUpdate(roleEntity);
        return new AjaxResult();
    }
    
    @RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.DELETE})
    @ResponseBody
    public Object delete(RoleEntity roleEntity) {
        roleService.delete(roleEntity);
        return new AjaxResult();
    }
    
}
