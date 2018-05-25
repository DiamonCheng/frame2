package com.dc.dcrud.web.view.data;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

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
    
    //TODO
    private List<TableHeadView> head;
    private List<?> data;
    private List<List<DataCellView>> dataViews;
    
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
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap()
                       .put(
                               "dataTable",
                               MapBuilder.dataMap()
                                       .put("id", id)
                                       .put("pageable", pageable)
                                       .put("pageNo", pageNo)
                                       .put("pageSize", pageSize)
                                       .put("totalCount", totalCount)
                                       .build()
                       )
                       .build();
    }
}
