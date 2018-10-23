package com.dc.dcrud.web.view.support;

import com.dc.frame2.core.dto.PageSearcher;
import com.dc.frame2.view.Frame2View;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/5/6.
 */
public interface QueryViewFactory {
    Frame2View createPageQueryView(PageSearcher searcher);
}
