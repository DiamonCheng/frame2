package com.dc.frame2.view.view.freemarker.page;


import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Frame2 default HTML view fragment.
 *
 * @author DC
 * @date 2018/5/6.
 */
public class PageView implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/page/page.html.ftl";
    private String title = "";
    private List<Resource> headResources = new ArrayList<>();
    private List<JsResource> bottomJsResources = new ArrayList<>();
    private List<Frame2View> components = new ArrayList<>();
    
    /**
     * set page title, set message code to support i18n
     *
     * @param title title string, set message code to support i18n
     * @return this
     */
    public PageView setTitle(String title) {
        this.title = title;
        return this;
    }
    
    /**
     * add a resource element in head tag <br/>
     * ordered
     *
     * @param resource Resource implementation
     * @return this
     */
    public PageView addHeadResource(Resource resource) {
        this.headResources.add(resource);
        return this;
    }
    
    /**
     * add a javascript resource element in bottom of this page
     *
     * @param jsResource JsResource
     * @return this
     */
    public PageView addBottomJsResource(JsResource jsResource) {
        this.bottomJsResources.add(jsResource);
        return this;
    }
    
    /**
     * add a Frame2View component to render. <br/>
     * ordered.
     *
     * @param frame2View Frame2View
     * @return this
     */
    public PageView addComponent(Frame2View frame2View) {
        this.components.add(frame2View);
        return this;
    }
    
    /**
     * get Components to modify
     *
     * @return frame2view components
     */
    public List<Frame2View> getComponents() {
        return components;
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.<String, Object>hashMap()
                       .put("title", title)
                       .put("headResources", headResources.stream().map(Resource::resolveHtmlElement).collect(Collectors.toList()))
                       .put("bottomJsResources", bottomJsResources.stream().map(JsResource::resolveHtmlElement).collect(Collectors.toList()))
                       .put("components", components)
                       .build();
    }
}
