package com.dc.dcrud.web.view.data;

import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.Collection;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/25.
 */
public interface DataCellView extends FreemarkerView {
    Collection<String> getCellStyleClass();
}
