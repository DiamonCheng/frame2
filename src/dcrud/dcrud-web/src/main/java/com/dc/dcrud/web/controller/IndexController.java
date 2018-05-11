package com.dc.dcrud.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/10.
 */
@Controller
public class IndexController {
    
    @RequestMapping({"/", "/index"})
    public Object index() {
        return "index";
    }
    
    @RequestMapping("/decorator")
    public Object decorator(){
        return new ModelAndView("/common/decorator");
    }
    
}
