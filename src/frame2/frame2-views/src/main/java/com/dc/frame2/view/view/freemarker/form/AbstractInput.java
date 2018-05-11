package com.dc.frame2.view.view.freemarker.form;

import com.dc.frame2.util.MapBuilder;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/5/11.
 */
public class AbstractInput<T extends AbstractInput> {
    private String id;
    private String name;
    private String title;
    private Map<String, String> attrs = MapBuilder.<String, String>hashMap().build();
    private Set<String> classes = new HashSet<>(3);
    
    public String getId() {
        return id;
    }
    
    public T setId(String id) {
        this.id = id;
        return (T) this;
    }
    
    public String getName() {
        return name;
    }
    
    public T setName(String name) {
        this.name = name;
        return (T) this;
    }
    
    public String getTitle() {
        return title;
    }
    
    public T setTitle(String title) {
        this.title = title;
        return (T) this;
    }
    
    
    public T addAttr(String key, String value) {
        this.attrs.put(key, value);
        return (T) this;
    }
    
    public T removeAttr(String key) {
        this.attrs.remove(key);
        return (T) this;
    }
    
    public T addCls(String cls) {
        this.classes.add(cls);
        return (T) this;
    }
    
    public T removeCls(String cls) {
        this.classes.remove(cls);
        return (T) this;
    }
    
    public Map<String, String> getAttrs() {
        return attrs;
    }
    
    public Set<String> getClasses() {
        return classes;
    }
    
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap()
                       .put("id", id)
                       .put("name", name)
                       .put("title", title)
                       .put("attrs", attrs)
                       .put("classes", classes)
                       .build();
    }
    
}
