package com.dc.dcrud.web.view.data;

import com.dc.frame2.util.MapBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/8/30.
 */
public class OptionTableHeadView implements TableHeadView {
    private static final String TEMPLATE_NAME = "/common/crud/data/table.head.option.html.ftl";
    private static final String CELL_TEMPLATE_NAME = "/common/crud/data/table.cell.option.html.ftl";
    private String name;
    private String width;
    private List<TableOptionButton> buttons = new ArrayList<>();
    
    @Override
    public DataCellView resolveDataCellView(List<?> dataList, Object data, int index) {
        return new DataCellView() {
            @Override
            public Object getCellData() {
                return data;
            }
            
            @Override
            public String getTemplateName() {
                return CELL_TEMPLATE_NAME;
            }
            
            @Override
            public Map<String, Object> getParam() {
                List<TableOptionButton> buttons1 = buttons.stream()
                                                           .filter(button -> button.getPermissionCheck().test(button)
                                                                                     && button.getTableOptionButtonFilter().test(dataList, data, index))
                                                           .map(button -> {
                                                               try {
                                                                   return button.clone();
                                                               } catch (CloneNotSupportedException e) {
                                                                   throw new IllegalStateException(e);
                                                               }
                                                           }).collect(Collectors.toList());
                buttons1.forEach(
                        button -> button.getTableOptionButtonRowDataConfigurator()
                                          .configure(button, dataList, data, index)
                );
                return MapBuilder.dataMap().put("buttons", buttons1).build();
            }
        };
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap()
                       .put("tableHead", MapBuilder.dataMap().put("name", name).put("width", width).build())
                       .build();
    }
    
    public OptionTableHeadView setName(String name) {
        this.name = name;
        return this;
    }
    
    public OptionTableHeadView setWidth(String width) {
        this.width = width;
        return this;
    }
    
    public OptionTableHeadView addButton(TableOptionButton optionButton) {
        buttons.add(optionButton);
        return this;
    }
    
    public List<TableOptionButton> getButtons() {
        return buttons;
    }
}
