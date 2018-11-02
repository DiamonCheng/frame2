package com.dc.dcrud.web.view.support;

import com.dc.dcrud.web.view.data.*;
import com.dc.dcrud.web.view.option.OperationPermissionCheck;
import com.dc.dcrud.web.view.option.OptionButton;
import com.dc.dcrud.web.view.support.viewpojo.datatable.DataTableConfig;
import com.dc.frame2.core.dto.PageSearcher;
import com.dc.frame2.data.DataIdExtractor;
import com.dc.frame2.util.SpringContextUtils;
import com.dc.frame2.view.Frame2View;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/1.
 */
public class QueryPageViewDataTableFactory implements QueryViewFactory {
    private DataTableConfig dataTableConfig = null;
    
    public QueryPageViewDataTableFactory configure(PageSearcher searcher) {
        Assert.notNull(searcher, "Searcher cannot be null.");
        Class<?> configPojoClass = searcher.getClass();
        dataTableConfig = configPojoClass.getAnnotation(DataTableConfig.class);
        Assert.notNull(searcher, "Cannot generate data table configuration with no DataTableConfig annotation.");
        return this;
    }
    
    @Override
    public Frame2View createPageQueryView(PageSearcher searcher) {
        if (dataTableConfig == null) {
            configure(searcher);
        }
        DefaultDataTableView dataTableView = new DefaultDataTableView()
                                                     .setPageParameters(searcher.getPageNo(), searcher.getPageSize(), searcher.getTotalCount())
                                                     .setData(searcher.getResultList());
        Arrays.stream(dataTableConfig.columns()).forEach(tableColumnConfig -> {
            DefaultTableHeadView defaultTableHeadView = new DefaultTableHeadView();
            defaultTableHeadView.setFieldName(tableColumnConfig.path());
            defaultTableHeadView.setName(tableColumnConfig.headName());
            defaultTableHeadView.setWidth(StringUtils.isEmpty(tableColumnConfig.width()) ? null : tableColumnConfig.width());
            defaultTableHeadView.setTextAlign(tableColumnConfig.align().toString());
            defaultTableHeadView.setSortable(tableColumnConfig.sortable());
            defaultTableHeadView.setSortFieldName(tableColumnConfig.path());
            /*sort /sort order*/
            int sortIndex = 0;
            Sort sortList = searcher.getSort();
            if (sortList != null) {
                for (Sort.Order order : sortList) {
                    sortIndex++;
                    if (order.getProperty().equalsIgnoreCase(tableColumnConfig.path())) {
                        defaultTableHeadView.setSort(order.getDirection().toString().toUpperCase());
                        defaultTableHeadView.setSortOrder(sortIndex);
                        break;
                    }
                }
            }
            dataTableView.addTableHeadView(defaultTableHeadView);
        });
        if (dataTableConfig.buttons().length > 0) {
            OptionTableHeadView optionTableHeadView = new OptionTableHeadView();
            dataTableView.addTableHeadView(optionTableHeadView);
            optionTableHeadView.setName(dataTableConfig.optionButtonColumnName());
            if (!StringUtils.isEmpty(dataTableConfig.optionButtonColumnWidth())) {
                optionTableHeadView.setWidth(dataTableConfig.optionButtonColumnWidth());
            }
            Arrays.stream(dataTableConfig.buttons()).forEach(buttonConfig -> {
                TableOptionButton tableOptionButton = new TableOptionButton();
                
                tableOptionButton.setName(buttonConfig.name());
                tableOptionButton.setTitle(StringUtils.isEmpty(buttonConfig.title()) ? buttonConfig.name() : buttonConfig.title());
                if (!StringUtils.isEmpty(buttonConfig.id())) {
                    tableOptionButton.setId(buttonConfig.id());
                }
                Arrays.stream(buttonConfig.classes()).forEach(tableOptionButton::addCls);
                tableOptionButton.setHref(buttonConfig.href());
                tableOptionButton.setType(buttonConfig.type());
                if (!OperationPermissionCheck.class.equals(buttonConfig.permissionCheckClass())) {
                    tableOptionButton.setPermissionCheck(SpringContextUtils.tryGetInstanceByClass(buttonConfig.permissionCheckClass()));
                }
                if (!TableOptionButtonFilter.class.equals(buttonConfig.filter())) {
                    tableOptionButton.setTableOptionButtonFilter(SpringContextUtils.tryGetInstanceByClass(buttonConfig.filter()));
                }
                tableOptionButton.setTableOptionButtonRowDataConfigurator((button, dataList, data, index) -> {
                    String href = button.getHref();
                    if (href != null && !href.startsWith(OptionButton.DEFAULT_HREF)) {
                        boolean flag = href.contains("?");
                        Map<String, String> ids = DataIdExtractor.extractId(data);
                        StringBuilder paramStr = new StringBuilder();
                        int index1 = 0;
                        for (Map.Entry<String, String> entry : ids.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            if (index1++ == 0 && !flag) {
                                paramStr.append("?");
                            } else {
                                paramStr.append("&");
                            }
                            paramStr.append(key).append("=").append(value);
                        }
                        button.setHref(href + paramStr);
                    }
                });
                optionTableHeadView.addButton(tableOptionButton);
            });
        }
        
        return dataTableView;
    }
}
