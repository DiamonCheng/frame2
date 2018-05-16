package com.dc.dcrud.web.controller.test;

import com.dc.dcrud.web.view.DefaultDataTableView;
import com.dc.dcrud.web.view.DefaultOptionButtonView;
import com.dc.dcrud.web.view.query.QueryPanelView;
import com.dc.frame2.view.view.freemarker.form.FormView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/11.
 */
@Controller
@RequestMapping("/query")
public class QueryTestController {
    
    @RequestMapping("/")
    public Object page() {
        return new FormView().addContent(new QueryPanelView()).addContent(new DefaultOptionButtonView()).addContent(new DefaultDataTableView());
    }
}
