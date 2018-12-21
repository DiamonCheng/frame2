package com.dc.dcrud.searcher;

import com.dc.dcrud.domain.RoleEntity;
import com.dc.dcrud.web.view.support.viewpojo.datatable.DataTableConfig;
import com.dc.dcrud.web.view.support.viewpojo.datatable.TableColumnConfig;
import com.dc.dcrud.web.view.support.viewpojo.datatable.TableOptionButtonConfig;
import com.dc.dcrud.web.view.support.viewpojo.inputview.ConditionViewTitle;
import com.dc.dcrud.web.view.support.viewpojo.inputview.TextInput;
import com.dc.dcrud.web.view.support.viewpojo.optionbutton.OptionButton;
import com.dc.dcrud.web.view.support.viewpojo.optionbutton.OptionButtons;
import com.dc.frame2.core.dao.condition.CompareOperator;
import com.dc.frame2.core.dao.condition.Condition;
import com.dc.frame2.core.dto.PageSearcher;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/21.
 */
@OptionButtons({
        @OptionButton(name = "crud.query.option.add", href = "add"),
})
@DataTableConfig(
        columns = {
                @TableColumnConfig(
                        path = "name", headName = "com.dc.dcrud.domain.RoleEntity.name"
                ),
                @TableColumnConfig(
                        path = "code", headName = "com.dc.dcrud.domain.RoleEntity.code"
                ),
                @TableColumnConfig(
                        path = "description", headName = "com.dc.dcrud.domain.RoleEntity.description",
                        sortable = false
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
@ConditionViewTitle("crud.userEntity.query.condition.title")
public class RoleSearcher extends PageSearcher<RoleEntity> {
    @TextInput
    @Condition(operator = CompareOperator.DUP_LIKE)
    private String name;
    @TextInput
    @Condition(operator = CompareOperator.DUP_LIKE)
    private String code;
    @TextInput
    @Condition(operator = CompareOperator.DUP_LIKE)
    private String description;
    
    public String getName() {
        return name;
    }
    
    public RoleSearcher setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getCode() {
        return code;
    }
    
    public RoleSearcher setCode(String code) {
        this.code = code;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public RoleSearcher setDescription(String description) {
        this.description = description;
        return this;
    }
}
