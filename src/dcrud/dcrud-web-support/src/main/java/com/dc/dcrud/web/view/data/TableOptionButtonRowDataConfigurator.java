package com.dc.dcrud.web.view.data;

import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/18.
 */
public interface TableOptionButtonRowDataConfigurator {
    TableOptionButtonRowDataConfigurator DEFAULT_INSTANCE = (button, dataList, data, index) -> {
    };
    
    void configure(TableOptionButton button, List<?> dataList, Object data, int index);
}
