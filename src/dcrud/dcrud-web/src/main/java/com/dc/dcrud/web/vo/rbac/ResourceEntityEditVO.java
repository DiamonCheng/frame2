package com.dc.dcrud.web.vo.rbac;

import com.dc.dcrud.extractor.ResourceEntityDataExtractor;
import com.dc.dcrud.web.view.support.viewpojo.edit.EditPanelConfig;
import com.dc.dcrud.web.view.support.viewpojo.inputview.*;
import com.dc.frame2.data.Extractor;

import java.util.Date;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/24.
 */
@EditPanelConfig(
        addTitle = "crud.resourceEntity.edit.modify.title"
)
public class ResourceEntityEditVO {
    @HiddenInput
    private Long id;
    @HiddenInput
    private Integer version;
    @ReadonlyTextInput(label = "com.dc.dcrud.domain.ResourceEntity.code")
    private String code;
    @TextInput(label = "com.dc.dcrud.domain.ResourceEntity.nameZh", validators = "required")
    private String nameZh;
    @TextInput(label = "com.dc.dcrud.domain.ResourceEntity.nameEn", validators = "required")
    private String nameEn;
    @TextInput(label = "com.dc.dcrud.domain.ResourceEntity.requestURI")
    private String requestURI;
    @Extractor(ResourceEntityDataExtractor.class)
    @SelectInput(label = "com.dc.dcrud.domain.ResourceEntity.type",
            optionProvider = "EnumOptionProvider",
            optionProviderKey = "com.dc.dcrud.domain.ResourceEntity$Type")
    private String type;
    @NumberInput(label = "com.dc.dcrud.domain.ResourceEntity.sortOrder")
    private Integer sortOrder;
    @TextInput(label = "com.dc.dcrud.domain.ResourceEntity.iconClass")
    private String iconClass;
    @SelectInput(
            label = "com.dc.dcrud.domain.ResourceEntity.parent",
            placeHolder = "crud.edit.field.select.option.toCheck",
            optionProvider = "HqlOptionProvider",
            optionProviderKey = "select nameZh as text,nameZh as text_zh,nameEn as text_en,id as value from ResourceEntity res")
    private Long parent;
    @ReadonlyTextInput(label = "com.dc.dcrud.domain.createDateTime")
    private Date createDateTime;
    
    @ReadonlyTextInput(label = "com.dc.dcrud.domain.updateDateTime")
    private Date updateDateTime;
    
    public String getCode() {
        return code;
    }
    
    public ResourceEntityEditVO setCode(String code) {
        this.code = code;
        return this;
    }
    
    public String getNameZh() {
        return nameZh;
    }
    
    public ResourceEntityEditVO setNameZh(String nameZh) {
        this.nameZh = nameZh;
        return this;
    }
    
    public String getNameEn() {
        return nameEn;
    }
    
    public ResourceEntityEditVO setNameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }
    
    public String getRequestURI() {
        return requestURI;
    }
    
    public ResourceEntityEditVO setRequestURI(String requestURI) {
        this.requestURI = requestURI;
        return this;
    }
    
    public String getType() {
        return type;
    }
    
    public ResourceEntityEditVO setType(String type) {
        this.type = type;
        return this;
    }
    
    public Integer getSortOrder() {
        return sortOrder;
    }
    
    public ResourceEntityEditVO setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }
    
    public String getIconClass() {
        return iconClass;
    }
    
    public ResourceEntityEditVO setIconClass(String iconClass) {
        this.iconClass = iconClass;
        return this;
    }
    
    public Long getParent() {
        return parent;
    }
    
    public ResourceEntityEditVO setParent(Long parent) {
        this.parent = parent;
        return this;
    }
    
    public Date getCreateDateTime() {
        return createDateTime;
    }
    
    public ResourceEntityEditVO setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }
    
    public Date getUpdateDateTime() {
        return updateDateTime;
    }
    
    public ResourceEntityEditVO setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }
    
    public Long getId() {
        return id;
    }
    
    public ResourceEntityEditVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Integer getVersion() {
        return version;
    }
    
    public ResourceEntityEditVO setVersion(Integer version) {
        this.version = version;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "ResourceEntityEditVO{" +
                       "id=" + id +
                       ", version=" + version +
                       ", code='" + code + '\'' +
                       ", nameZh='" + nameZh + '\'' +
                       ", nameEn='" + nameEn + '\'' +
                       ", requestURI='" + requestURI + '\'' +
                       ", type='" + type + '\'' +
                       ", sortOrder=" + sortOrder +
                       ", iconClass='" + iconClass + '\'' +
                       ", parent=" + parent +
                       ", createDateTime=" + createDateTime +
                       ", updateDateTime=" + updateDateTime +
                       '}';
    }
}
