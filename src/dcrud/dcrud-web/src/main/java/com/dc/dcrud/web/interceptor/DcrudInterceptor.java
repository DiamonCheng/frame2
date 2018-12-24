package com.dc.dcrud.web.interceptor;


import com.dc.dcrud.dao.UserDao;
import com.dc.dcrud.domain.UserEntity;
import com.dc.dcrud.pojo.User;
import com.dc.dcrud.service.shiro.SecurityRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/5/6.
 */
public class DcrudInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DcrudInterceptor.class);
    @Autowired
    private UserDao userDao;
    

    /**
     * 如果记住用户了 但是session里面的用户超时了，就重新查询用户信息
     */
    private void checkSessionUser() {
        Session session = null;
        PrincipalCollection principalCollection = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            session = subject.getSession();
            principalCollection = subject.getPrincipals();
            if (principalCollection == null) {
                return;
            }
        } catch (Exception e) {
            return;
        }
        Collection realm = principalCollection.fromRealm(SecurityRealm.REALM_NAME);
        if (!realm.isEmpty()) {
            User user = (User) session.getAttribute(User.USER_KEY);
            if (user == null) {
                try {
                    String userName = (String) realm.iterator().next();
                    UserEntity userEntity = userDao.getUserEntityByUsername(userName);
                    user = new User().setUsername(userName).setNickName(userEntity.getNickName()).setId(userEntity.getId());
                    session.setAttribute(User.USER_KEY, user);
                } catch (Exception e) {
                    LOGGER.warn("Failed to refresh user state, it may cause other exception.", e);
                }
            }
        }
        
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().contains("decorator")) {
            LOGGER.info("access url:{}, handler:{}", request.getRequestURI(), handler);
        }
        checkSessionUser();
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            LOGGER.error("ERROR caught in interceptor", ex);
        }
    }
    
}
