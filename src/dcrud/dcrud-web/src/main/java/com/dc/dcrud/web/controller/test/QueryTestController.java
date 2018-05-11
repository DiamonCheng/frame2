package com.dc.dcrud.web.controller.test;

import com.dc.dcrud.web.view.DefaultQueryPanelView;
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
        return new DefaultQueryPanelView();
    }
}
