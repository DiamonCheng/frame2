package com.dc.dcrud.web.view.data;

import com.dc.dcrud.web.view.option.OptionButton;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/3.
 */
public class TableOptionButton extends OptionButton {
    private TableOptionButtonFilter tableOptionButtonFilter = (list, data, index) -> true;
    
    public TableOptionButton setTableOptionButtonFilter(TableOptionButtonFilter tableOptionButtonFilter) {
        this.tableOptionButtonFilter = tableOptionButtonFilter;
        return this;
    }
    
    public TableOptionButtonFilter getTableOptionButtonFilter() {
        return tableOptionButtonFilter;
    }
    
    {
        addCls("layui-btn-xs");
        setType(Type.PRIMARY);
    }
}
