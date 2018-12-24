package com.dc.dcrud.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/5.
 */
public class Menu implements Serializable {
    private String name;
    private String code;
    private String uri;
    private String icon;
    private List<Menu> children;
    
    public String getName() {
        return name;
    }
    
    public Menu setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getCode() {
        return code;
    }
    
    public Menu setCode(String code) {
        this.code = code;
        return this;
    }
    
    public List<Menu> getChildren() {
        return children;
    }
    
    public Menu setChildren(List<Menu> children) {
        this.children = children;
        return this;
    }
    
    public String getUri() {
        return uri;
    }
    
    public Menu setUri(String uri) {
        this.uri = uri;
        return this;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public Menu setIcon(String icon) {
        this.icon = icon;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "Menu{" +
                       "name='" + name + '\'' +
                       ", code='" + code + '\'' +
                       ", uri='" + uri + '\'' +
                       ", icon='" + icon + '\'' +
                       ", children=" + children +
                       '}';
    }
}
