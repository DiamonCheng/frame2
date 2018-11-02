package com.dc.dcrud.web.view.data;

import com.dc.dcrud.web.view.option.OptionButton;

import java.util.HashMap;
import java.util.HashSet;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/3.
 */
public class TableOptionButton extends OptionButton implements Cloneable {
    private TableOptionButtonFilter tableOptionButtonFilter = TableOptionButtonFilter.DEFAULT_INSTANCE;
    private TableOptionButtonRowDataConfigurator tableOptionButtonRowDataConfigurator = TableOptionButtonRowDataConfigurator.DEFAULT_INSTANCE;
    public TableOptionButton setTableOptionButtonFilter(TableOptionButtonFilter tableOptionButtonFilter) {
        this.tableOptionButtonFilter = tableOptionButtonFilter;
        return this;
    }
    
    public TableOptionButtonFilter getTableOptionButtonFilter() {
        return tableOptionButtonFilter;
    }
    
    public TableOptionButtonRowDataConfigurator getTableOptionButtonRowDataConfigurator() {
        return tableOptionButtonRowDataConfigurator;
    }
    
    public TableOptionButton setTableOptionButtonRowDataConfigurator(TableOptionButtonRowDataConfigurator tableOptionButtonRowDataConfigurator) {
        this.tableOptionButtonRowDataConfigurator = tableOptionButtonRowDataConfigurator;
        return this;
    }
    
    public TableOptionButton() {
        addCls("layui-btn-xs");
        setType(Type.PRIMARY);
    }
    
    @Override
    protected TableOptionButton clone() throws CloneNotSupportedException {
        TableOptionButton tableOptionButton = (TableOptionButton) super.clone();
        tableOptionButton.setAttrs(new HashMap<>(getAttrs()));
        tableOptionButton.setClasses(new HashSet<>(getClasses()));
        return tableOptionButton;
    }
}
