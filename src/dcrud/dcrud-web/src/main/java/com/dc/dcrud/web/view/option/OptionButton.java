package com.dc.dcrud.web.view.option;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/18.
 */
public class OptionButton implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/common/crud/option/button.html.ftl";
    
    public enum Type {
        /***/
        DEFAULT("layui-btn"), PRIMARY("layui-btn layui-btn-primary"), DANGER("layui-btn layui-btn-danger");
        /**
         * 就是按钮标签上的类
         */
        private String cls;
        
        Type(String cls) {
            this.cls = cls;
        }
        
        String getCls() {
            return cls;
        }
    }
    
    private String name = "";
    private String title = "";
    private String id;
    private Type type = Type.DEFAULT;
    
    //TODO permission
    
    public String getName() {
        return name;
    }
    
    public OptionButton setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getTitle() {
        return title;
    }
    
    public OptionButton setTitle(String title) {
        this.title = title;
        return this;
    }
    
    public String getId() {
        return id;
    }
    
    public OptionButton setId(String id) {
        this.id = id;
        return this;
    }
    
    public Type getType() {
        return type;
    }
    
    public OptionButton setType(Type type) {
        this.type = type;
        return this;
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder
                       .dataMap()
                       .put("button", MapBuilder.dataMap()
                                              .put("id", id)
                                              .put("title", title)
                                              .put("name", name)
                                              .put("cls", type.getCls())
                                              .build())
                       .build();
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
