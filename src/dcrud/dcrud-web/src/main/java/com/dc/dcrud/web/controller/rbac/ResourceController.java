package com.dc.dcrud.web.controller.rbac;

import com.dc.dcrud.model.domain.ResourceEntity;
import com.dc.dcrud.model.searcher.ResourceSearcher;
import com.dc.dcrud.service.rbac.ResourceService;
import com.dc.dcrud.web.view.support.EditViewFactory;
import com.dc.dcrud.web.view.support.QueryPageViewFactory;
import com.dc.dcrud.web.vo.rbac.ResourceEntityEditVO;
import com.dc.frame2.core.dto.AjaxResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/24.
 */
@Controller
@RequestMapping("/dcrud/resource")
public class ResourceController {
    private QueryPageViewFactory queryPageViewFactory = new QueryPageViewFactory();
    
    private EditViewFactory editViewFactory = new EditViewFactory();
    private EditViewFactory addViewFactory = new EditViewFactory();
    
    @Autowired
    private ResourceService resourceService;
    
    @RequestMapping("/")
    public Object searchPage(ResourceSearcher searcher) {
        resourceService.searchPage(searcher);
        return queryPageViewFactory.createPageQueryView(searcher);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Object add() {
        return addViewFactory.generateAddView(new ResourceEntity());
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public Object edit(long id) {
        ResourceEntity resourceEntity = resourceService.get(id);
        ResourceEntityEditVO resourceEntityEditVO = new ResourceEntityEditVO();
        BeanUtils.copyProperties(resourceEntity, resourceEntityEditVO);
        resourceEntityEditVO.setParent(resourceEntity.getParent() == null ? null : resourceEntity.getParent().getId());
        return editViewFactory.generateEditView(resourceEntityEditVO);
    }
    
    @RequestMapping(value = {"/add", "/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public Object save(ResourceEntityEditVO editVO) {
        ResourceEntity resourceEntity = new ResourceEntity();
        BeanUtils.copyProperties(editVO, resourceEntity);
        if (editVO.getParent() != null) {
            resourceEntity.setParent(new ResourceEntity() {{
                setId(editVO.getParent());
            }});
        }
        resourceService.saveOrUpdate(resourceEntity);
        return new AjaxResult();
    }
    
    @RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.DELETE})
    @ResponseBody
    public Object delete(ResourceEntity resourceEntity) {
        resourceService.delete(resourceEntity);
        return new AjaxResult();
    }
}
