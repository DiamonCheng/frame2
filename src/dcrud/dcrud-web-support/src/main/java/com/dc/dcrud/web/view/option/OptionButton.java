package com.dc.dcrud.web.view.option;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/18.
 */
public class OptionButton implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/common/crud/option/button.html.ftl";
    public static final String DEFAULT_HREF = "javascript:";
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
    private Map<String, String> attrs = MapBuilder.<String, String>hashMap().put("href", DEFAULT_HREF).build();
    private Set<String> classes = new HashSet<>(3);
    private OperationPermissionCheck permissionCheck = (b) -> true;
    
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
    
    public OptionButton addAttr(String key, String value) {
        this.attrs.put(key, value);
        return this;
    }
    
    public OptionButton removeAttr(String key) {
        this.attrs.remove(key);
        return this;
    }
    
    public OptionButton addCls(String cls) {
        this.classes.add(cls);
        return this;
    }
    
    public OptionButton removeCls(String cls) {
        this.classes.remove(cls);
        return this;
    }
    
    public OptionButton setHref(String href) {
        this.addAttr("href", href);
        return this;
    }
    
    public String getHref() {
        return this.attrs.get("href");
    }
    
    public OptionButton setPermissionCheck(OperationPermissionCheck permissionCheck) {
        this.permissionCheck = Objects.requireNonNull(permissionCheck);
        return this;
    }
    
    public OperationPermissionCheck getPermissionCheck() {
        return permissionCheck;
    }
    
    public Map<String, String> getAttrs() {
        return attrs;
    }
    
    public OptionButton setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
        return this;
    }
    
    public Set<String> getClasses() {
        return classes;
    }
    
    public OptionButton setClasses(Set<String> classes) {
        this.classes = classes;
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
                                              .put("classes", classes)
                                              .put("attrs", attrs)
                                              .put("permissionCheck", permissionCheck.test(this))
                                              .build())
                       .build();
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
