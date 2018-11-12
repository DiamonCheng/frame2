package com.dc.dcrud.web.view.support;

import com.dc.dcrud.web.view.edit.EditPanelView;
import com.dc.dcrud.web.view.support.viewpojo.edit.EditPanelConfig;
import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.view.freemarker.form.FormView;
import com.dc.frame2.view.view.freemarker.page.JsResource;
import com.dc.frame2.view.view.freemarker.page.PageView;
import com.dc.frame2.view.view.freemarker.page.Resource;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/12.
 */
public class EditViewFactory extends InputPanelViewSupport {
    private List<Resource> headResources = new LinkedList<>();
    
    private List<JsResource> buttonJsResources = new LinkedList<>();
    
    private String editTitle = "crud.query.table.option.update";
    private String addTitle = "crud.query.option.add";
    private String editSubmitPath = "save";
    private String addSubmitPath = "save";
    
    public EditViewFactory config(Object configPojo) {
        Assert.notNull(configPojo, "ConfigPojo cannot be null.");
        configBaseClass = EditPanelConfig.class;
        EditPanelConfig editPanelConfig = configPojo.getClass().getAnnotation(EditPanelConfig.class);
        if (editPanelConfig != null) {
            editTitle = editPanelConfig.editTitle();
            addTitle = editPanelConfig.addTitle();
            editSubmitPath = editPanelConfig.editSubmitPath();
            addSubmitPath = editPanelConfig.addSubmitPath();
        }
        fieldViewConfigurations = new ArrayList<>(10);
        resolveConditionGroups(configPojo.getClass(), Collections.emptyList(), configPojo.getClass());
        return this;
    }
    
    public Frame2View generateEditView(Object configPojo) {
        if (fieldViewConfigurations == null) {
            config(configPojo);
        }
        return genEditView(editTitle, editSubmitPath, configPojo);
    }
    
    public Frame2View generateAddView(Object configPojo) {
        if (fieldViewConfigurations == null) {
            config(configPojo);
        }
        return genEditView(addTitle, addSubmitPath, configPojo);
    }
    
    private Frame2View genEditView(String title, String submitPath, Object configPojo) {
        EditPanelView editPanelView = new EditPanelView();
        editPanelView.setTitle(title);
        genInputViews(configPojo, editPanelView::addCondition);
        FormView formView = new FormView();
        formView.addContent(editPanelView);
        formView.setAction(submitPath);
        formView.setMethod("POST");
        formView.setId("pageForm");
        PageView pageView = new PageView();
        pageView.addComponent(formView);
        headResources.forEach(pageView::addHeadResource);
        buttonJsResources.forEach(pageView::addBottomJsResource);
        return pageView;
    }
    
    
    public List<JsResource> getButtonJsResources() {
        return buttonJsResources;
    }
    
    public List<Resource> getHeadResources() {
        return headResources;
    }
    
    public EditViewFactory addButtonJsResources(JsResource jsResource) {
        buttonJsResources.add(jsResource);
        return this;
    }
    
    public EditViewFactory addHeadResources(Resource resource) {
        headResources.add(resource);
        return this;
    }
    
    public String getEditTitle() {
        return editTitle;
    }
    
    public EditViewFactory setEditTitle(String editTitle) {
        this.editTitle = editTitle;
        return this;
    }
    
    public String getAddTitle() {
        return addTitle;
    }
    
    public EditViewFactory setAddTitle(String addTitle) {
        this.addTitle = addTitle;
        return this;
    }
    
    public String getEditSubmitPath() {
        return editSubmitPath;
    }
    
    public EditViewFactory setEditSubmitPath(String editSubmitPath) {
        this.editSubmitPath = editSubmitPath;
        return this;
    }
    
    public String getAddSubmitPath() {
        return addSubmitPath;
    }
    
    public EditViewFactory setAddSubmitPath(String addSubmitPath) {
        this.addSubmitPath = addSubmitPath;
        return this;
    }
}
