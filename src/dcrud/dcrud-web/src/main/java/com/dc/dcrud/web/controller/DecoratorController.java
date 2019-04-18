package com.dc.dcrud.web.controller;

import com.dc.dcrud.service.rbac.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DecoratorController {
    
    @RequestMapping("/decorator")
    public Object decorator(){
        return new ModelAndView("common/decorator");
    }
    
}
