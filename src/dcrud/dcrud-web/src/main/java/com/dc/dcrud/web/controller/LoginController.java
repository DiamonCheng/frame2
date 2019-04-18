package com.dc.dcrud.web.controller;

import com.dc.dcrud.model.domain.UserEntity;
import com.dc.frame2.util.web.MessageResolver;
import com.dc.frame2.util.web.WebContextBinder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/18.
 */
@Controller
public class LoginController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private MessageResolver messageResolver;
    
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public Object login(){
        return "common/login";
    }
    
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Object login(UserEntity userEntity, boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUsername(), userEntity.getPassword());
        token.setRememberMe(rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(WebContextBinder.getRequest());
            if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
                return "redirect:" + savedRequest.getRequestUrl();
            } else {
                return "redirect:/index";
            }
        } catch (AuthenticationException e) {
            LOGGER.error(String.format("User %s login failed.", userEntity.getUsername()), e);
            return new ModelAndView("common/login").addObject("username", userEntity.getUsername()).addObject("failedMessage", messageResolver.getMessage("login.failed.message"));
        }
    }
}
