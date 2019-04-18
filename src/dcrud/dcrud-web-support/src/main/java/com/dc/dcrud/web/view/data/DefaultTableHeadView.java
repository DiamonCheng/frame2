package com.dc.dcrud.web.view.data;

import com.dc.frame2.util.ReflectionUtils;
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
    private String sortFieldName;
    private Boolean sortable = false;
    private String sort;
    private Integer sortOrder;
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
                        .put("sort", sort)
                        .put("sortOrder", sortOrder)
                        .put("sortFieldName", sortFieldName)
                        .put("sortable", sortable)
                        .build()
        ).build();
    }
    
    public DefaultTableHeadView setName(String name) {
        this.name = name;
        return this;
    }
    
    public DefaultTableHeadView setFieldName(String fieldName) {
        this.fieldName = fieldName;
        setSortFieldName(fieldName);
        return this;
    }
    
    public DefaultTableHeadView setSort(String sort) {
        this.sort = sort == null ? null : sort.toLowerCase();
        return this;
    }
    
    public DefaultTableHeadView setSortOrder(Integer sortOrder) {
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
    
    public DefaultTableHeadView setSortFieldName(String sortFieldName) {
        this.sortFieldName = sortFieldName;
        return this;
    }
    
    public DefaultTableHeadView setSortable(Boolean sortable) {
        this.sortable = sortable;
        return this;
    }
    
    @Override
    public DataCellView resolveDataCellView(List<?> dataList, Object data, int index) {
        String content = extractText(data,fieldName);
        return new DataCellView() {
            @Override
            public Object getCellData() {
                return data;
            }
            
            @Override
            public String getTemplateName() {
                return "/common/crud/data/table.cell.html.ftl";
            }
            
            @Override
            public Map<String, Object> getParam() {
                return MapBuilder.dataMap().put(
                        "cell",
                        MapBuilder.dataMap()
                                .put("align", textAlign)
                                .put("content", content)
                                .build()
                ).build();
            }
        };
    }
    private String extractText(Object data,String fieldName){
        String[] fields=fieldName.split("\\.");
        for(int i=0;i<fields.length-1 && data!=null;i++){
            data=ReflectionUtils.getValueByField(data,fields[i]);
        }
        return DataFieldExtractor.extractText(data, fields[fields.length-1]);
    }
}
