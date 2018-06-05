package com.dc.dcrud.web.view.data;

import com.dc.frame2.data.DataFieldExtractor;
import com.dc.frame2.util.MapBuilder;

import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/6/5.
 */
public class DefaultTableHeadView implements TableHeadView {
    public static final String TEXT_ALIGN_LEFT = "left";
    public static final String TEXT_ALIGN_RIGHT = "right";
    private String name;
    private String fieldName;
    private String sort;
    private String sortOrder;
    private String width;
    private String textAlign;
    
    @Override
    public String getTemplateName() {
        return "/common/crud/data/table.head.html.ftl";
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap().put(
                "head",
                MapBuilder.dataMap()
                        .put("name", name)
                        .put("width", width)
                        .put("textAlign", textAlign)
                        .put("sort", sort)
                        .put("sortOrder", sortOrder)
                        .build()
        ).build();
    }
    
    public DefaultTableHeadView setName(String name) {
        this.name = name;
        return this;
    }
    
    public DefaultTableHeadView setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }
    
    public DefaultTableHeadView setSort(String sort) {
        this.sort = sort;
        return this;
    }
    
    public DefaultTableHeadView setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }
    
    public DefaultTableHeadView setWidth(String width) {
        this.width = width;
        return this;
    }
    
    public DefaultTableHeadView setTextAlign(String textAlign) {
        this.textAlign = textAlign;
        return this;
    }
    
    @Override
    public DataCellView resolveDataCellView(List<?> dataList, Object data, int index) {
        String content = DataFieldExtractor.extractString(data, fieldName);
        return new DataCellView() {
            @Override
            public String getTemplateName() {
                return "/common/crud/data/table.cell.html.ftl";
            }
            
            @Override
            public Map<String, Object> getParam() {
                return MapBuilder.dataMap().put(
                        "cell",
                        MapBuilder.dataMap()
                                .put("content", content)
                                .build()
                ).build();
            }
        };
    }
}
