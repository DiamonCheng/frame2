package com.dc.dcrud.web.controller;

import com.dc.dcrud.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/18.
 */
@Controller
public class LoginController {
    
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public Object login(){
        return "common/login";
    }
    
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Object login(UserEntity userEntity){
        return "redirect:index";
    }
}
