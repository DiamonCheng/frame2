package com.dc.dcrud.web.interceptor;


import com.dc.dcrud.domain.UserEntity;
import com.dc.dcrud.pojo.Menu;
import com.dc.dcrud.pojo.User;
import com.dc.dcrud.service.rbac.ResourceService;
import com.dc.dcrud.service.rbac.UserService;
import com.dc.dcrud.service.shiro.SecurityRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/5/6.
 */
public class DcrudInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DcrudInterceptor.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

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
                    UserEntity userEntity = userService.getUserByUsername(userName);
                    user = new User().setUsername(userName).setNickName(userEntity.getNickName()).setId(userEntity.getId());
                    session.setAttribute(User.USER_KEY, user);
                } catch (Exception e) {
                    LOGGER.warn("Failed to refresh user state, it may cause other exception.", e);
                }
            }
            List<?> menuList = (List<?>) session.getAttribute(Menu.MENU_KEY);
            Object langMenu = session.getAttribute(Menu.MENU_LANG_KEY);
            Object langPage = LocaleContextHolder.getLocale().getLanguage();
            if (menuList == null || !Objects.equals(langMenu, langPage)) {
                menuList = resourceService.loadUserMenu();
                session.setAttribute(Menu.MENU_KEY, menuList);
                session.setAttribute(Menu.MENU_LANG_KEY, langPage);
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
