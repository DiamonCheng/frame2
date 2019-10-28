package com.dc.dcrud.model.domain;

import com.dc.dcrud.extractor.ResourceEntityDataExtractor;
import com.dc.dcrud.web.view.support.viewpojo.edit.EditPanelConfig;
import com.dc.dcrud.web.view.support.viewpojo.inputview.NumberInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.SelectInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.TextInput;
import com.dc.frame2.core.domain.BaseConfigEntity;
import com.dc.frame2.data.Extractor;
import com.dc.frame2.dict.TranslatableEnum;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

/**
 * @author DC
 */
@Table(name = "sys_resource", uniqueConstraints = @UniqueConstraint(name = "sys_resource_code_unique", columnNames = "code"))
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EditPanelConfig(
        addTitle = "crud.resourceEntity.edit.add.title"
)
public class ResourceEntity extends BaseConfigEntity {
    /**
     * Code 字段枚举，表明资源类型
     */
    public enum Type implements TranslatableEnum {
    
        /*菜单类型*/ MENU("menu");
        
        Type(String code) {
            this.code = code;
        }
        
        private String code;
        
        @Override
        public String getCode() {
            return "com.dc.dcrud.domain.ResourceEntity.type." + code;
        }
        
        @Override
        public String getValue() {
            return code;
        }
    }
    private static final long serialVersionUID = 2283028973673917967L;
    @Column(nullable = false)
    @TextInput(validators = "required")
    private String code;
    @TextInput(validators = "required")
    private String nameZh;
    @TextInput(validators = "required")
    private String nameEn;
    @TextInput()
    private String requestURI;
    @Extractor(ResourceEntityDataExtractor.class)
    @SelectInput(optionProvider = "EnumOptionProvider", optionProviderKey = "com.dc.dcrud.domain.ResourceEntity$Type")
    private String type;
    @NumberInput
    private Integer sortOrder;
    @TextInput
    private String iconClass;
    @SelectInput(placeHolder = "crud.edit.field.select.option.toCheck",
            optionProvider = "HqlOptionProvider",
            optionProviderKey = "select nameZh as text,nameZh as text_zh,nameEn as text_en,id as value from ResourceEntity res")
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_resource_self"))
    private ResourceEntity parent;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_resource_self"))
    @OrderBy("sortOrder")
    private List<ResourceEntity> children;
    
    public String getCode() {
        
        return code;
    }
    
    public ResourceEntity setCode(String code) {
        this.code = code;
        return this;
    }
    
    public String getRequestURI() {
        return requestURI;
    }
    
    public ResourceEntity setRequestURI(String requestURI) {
        this.requestURI = requestURI;
        return this;
    }
    
    public String getType() {
        
        return type;
    }
    
    public ResourceEntity setType(String type) {
        this.type = type;
        return this;
    }
    
    public ResourceEntity getParent() {
        return parent;
    }
    
    public ResourceEntity setParent(ResourceEntity parent) {
        this.parent = parent;
        return this;
    }
    
    public List<ResourceEntity> getChildren() {
        return children;
    }
    
    public ResourceEntity setChildren(List<ResourceEntity> children) {
        this.children = children;
        return this;
    }
    
    public Integer getSortOrder() {
        return sortOrder;
    }
    
    public ResourceEntity setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }
    
    public String getIconClass() {
        return iconClass;
    }
    
    public ResourceEntity setIconClass(String iconClass) {
        this.iconClass = iconClass;
        return this;
    }
    
    public String getNameZh() {
        return nameZh;
    }
    
    public ResourceEntity setNameZh(String nameZh) {
        this.nameZh = nameZh;
        return this;
    }
    
    public String getNameEn() {
        return nameEn;
    }
    
    public ResourceEntity setNameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "ResourceEntity{" +
                       "code='" + code + '\'' +
                       ", nameZh='" + nameZh + '\'' +
                       ", nameEn='" + nameEn + '\'' +
                       ", requestURI='" + requestURI + '\'' +
                       ", type='" + type + '\'' +
                       ", sortOrder=" + sortOrder +
                       ", iconClass='" + iconClass + '\'' +
                       ", parent=" + parent +
                       ", children=" + children.size() +
                       '}';
    }
}
