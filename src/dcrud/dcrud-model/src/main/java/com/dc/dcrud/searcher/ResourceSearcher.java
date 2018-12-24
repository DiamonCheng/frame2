package com.dc.dcrud.searcher;

import com.dc.dcrud.domain.ResourceEntity;
import com.dc.dcrud.web.view.support.viewpojo.datatable.DataTableConfig;
import com.dc.dcrud.web.view.support.viewpojo.datatable.TableColumnConfig;
import com.dc.dcrud.web.view.support.viewpojo.datatable.TableOptionButtonConfig;
import com.dc.dcrud.web.view.support.viewpojo.inputview.ConditionViewTitle;
import com.dc.dcrud.web.view.support.viewpojo.inputview.SelectInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.TextInput;
import com.dc.dcrud.web.view.support.viewpojo.optionbutton.OptionButton;
import com.dc.dcrud.web.view.support.viewpojo.optionbutton.OptionButtons;
import com.dc.frame2.core.dao.condition.*;
import com.dc.frame2.core.dto.PageSearcher;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/24.
 */
@OptionButtons({
        @OptionButton(name = "crud.query.option.add", href = "add"),
})
@DataTableConfig(
        columns = {
                @TableColumnConfig(
                        path = "code", headName = "com.dc.dcrud.domain.ResourceEntity.code"
                ),
                @TableColumnConfig(
                        path = "nameZh", headName = "com.dc.dcrud.domain.ResourceEntity.nameZh"
                ),
                @TableColumnConfig(
                        path = "nameEn", headName = "com.dc.dcrud.domain.ResourceEntity.nameEn"
                ),
                @TableColumnConfig(
                        path = "type", headName = "com.dc.dcrud.domain.ResourceEntity.type"
                ),
                @TableColumnConfig(
                        path = "requestURI", headName = "com.dc.dcrud.domain.ResourceEntity.requestURI"
                ),
                @TableColumnConfig(
                        path = "createDateTime", headName = "com.dc.dcrud.domain.createDateTime"
                ),
                @TableColumnConfig(
                        path = "updateDateTime", headName = "com.dc.dcrud.domain.updateDateTime"
                ),
        }, buttons = {
        @TableOptionButtonConfig(name = "crud.query.table.option.update", href = "edit"),
        @TableOptionButtonConfig(name = "crud.query.table.option.delete", href = "delete",
                ajax = true,
                ajaxConfirm = true)
})
@ConditionViewTitle("crud.resourceEntity.query.condition.title")
public class ResourceSearcher extends PageSearcher<ResourceEntity> {
    public static class NameGroup implements ConditionsGroup {
        @Condition(operator = CompareOperator.DUP_LIKE)
        private String nameZh;
        @Condition(operator = CompareOperator.DUP_LIKE, preContact = PreContact.OR)
        private String nameEn;
        
        public String getNameZh() {
            return nameZh;
        }
        
        public NameGroup setNameZh(String nameZh) {
            this.nameZh = nameZh;
            return this;
        }
        
        public String getNameEn() {
            return nameEn;
        }
        
        public NameGroup setNameEn(String nameEn) {
            this.nameEn = nameEn;
            return this;
        }
        
        /**
         * toString
         */
        @Override
        public String toString() {
            return "NameGroup{" +
                           "nameZh='" + nameZh + '\'' +
                           ", nameEn='" + nameEn + '\'' +
                           '}';
        }
    }
    
    @TextInput
    @Condition(operator = CompareOperator.DUP_LIKE)
    private String code;
    @TextInput
    @NotCondition
    private String name;
    
    private NameGroup nameGroup = new NameGroup();
    
    @Condition(joinType = JoinType.LEFT, value = "parent.id")
    @SelectInput(placeHolder = "crud.query.condition.select.option.all",
            optionProvider = "HqlOptionProvider",
            optionProviderKey = "select nameZh as text,nameZh as text_zh,nameEn as text_en,id as value from ResourceEntity res where res.children is not empty")
    private Long parentId;
    
    @Condition
    private String type;
    
    public String getCode() {
        return code;
    }
    
    public ResourceSearcher setCode(String code) {
        this.code = code;
        return this;
    }
    
    public String getName() {
        return name;
    }
    
    public ResourceSearcher setName(String name) {
        this.nameGroup.setNameEn(name);
        this.nameGroup.setNameZh(name);
        this.name = name;
        return this;
    }
    
    public Long getParentId() {
        return parentId;
    }
    
    public ResourceSearcher setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }
    
    public String getType() {
        return type;
    }
    
    public ResourceSearcher setType(String type) {
        this.type = type;
        return this;
    }
    
    
    public NameGroup getNameGroup() {
        return nameGroup;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "ResourceSearcher{" +
                       "code='" + code + '\'' +
                       ", name='" + name + '\'' +
                       ", nameGroup=" + nameGroup +
                       ", parentId=" + parentId +
                       ", type='" + type + '\'' +
                       '}';
    }
}

