package com.dc.dcrud.web.view.data;

import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/9/3.
 */
public interface TableOptionButtonFilter {
    TableOptionButtonFilter DEFAULT_INSTANCE = (list, data, index) -> true;
    boolean test(List<?> dataList, Object data, int index);
}
