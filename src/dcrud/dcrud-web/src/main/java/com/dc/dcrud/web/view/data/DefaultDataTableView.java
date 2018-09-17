package com.dc.dcrud.web.view.data;

import com.dc.frame2.data.DataIdExtractor;
import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/5/6.
 */
public class DefaultDataTableView implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/common/crud/data/table.html.ftl";
    private boolean pageable = false;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalCount;
    
    private String id;
    
    private List<TableHeadView> tableHeadViews = new ArrayList<>(3);
    private List<?> data;
    
    public DefaultDataTableView setPageParameters(int pageNo, int pageSize, long totalCount) {
        this.pageable = true;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        return this;
    }
    
    public String getId() {
        return id;
    }
    
    public DefaultDataTableView setId(String id) {
        this.id = id;
        return this;
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    public DefaultDataTableView setData(List<?> data) {
        this.data = data;
        return this;
    }
    
    public List<?> getData() {
        return data;
    }
    
    public List<TableHeadView> getTableHeadViews() {
        return tableHeadViews;
    }
    
    public DefaultDataTableView addTableHeadView(TableHeadView tableHeadView) {
        tableHeadViews.add(tableHeadView);
        return this;
    }
    
    @Override
    public Map<String, Object> getParam() {
        List<Map<String, Object>> tableRows = new ArrayList<>(data.size());
        for (Object d : data) {
            Map<String, Object> rowData = new HashMap<>(3);
            tableRows.add(rowData);
            List<DataCellView> tableColumn = new ArrayList<>(tableHeadViews.size());
            rowData.put("dataCells", tableColumn);
            int index = 0;
            for (TableHeadView tableHeadView : tableHeadViews) {
                tableColumn.add(tableHeadView.resolveDataCellView(data, d, index++));
            }
            Map<String, String> dataIds = DataIdExtractor.extractId(d);
            rowData.put("dataIds", dataIds);
        }
        return MapBuilder.dataMap()
                       .put(
                               "dataTable",
                               MapBuilder.dataMap()
                                       .put("id", id)
                                       .put("pageable", pageable)
                                       .put("pageNo", pageNo)
                                       .put("pageSize", pageSize)
                                       .put("totalCount", totalCount)
                                       .put("tableRows", tableRows)
                                       .put("tableHeadViews", tableHeadViews)
                                       .build()
                       )
                       .build();
    }
}
