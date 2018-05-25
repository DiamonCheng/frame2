package com.dc.dcrud.web.view.data;

import com.dc.frame2.view.view.freemarker.FreemarkerView;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/25.
 */
public interface TableHeadView extends FreemarkerView {
    DataCellView resolveDataCellView(Object data, TableHeadView tableHeadView);
//    resolveData
//    resolveDataStyle
    
}
