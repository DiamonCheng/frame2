package com.dc.dcrud.web.interceptor;


import com.dc.dcrud.dao.UserDao;
import com.dc.dcrud.domain.UserEntity;
import com.dc.dcrud.pojo.User;
import com.dc.dcrud.service.shiro.SecurityRealm;
import com.dc.frame2.core.dto.AjaxResult;
import com.dc.frame2.core.exception.TranslatableException;
import com.dc.frame2.util.web.MessageResolver;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    
    @Autowired
    private MessageResolver messageResolver;
    
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
                    user = new User().setUsername(userName).setNickName(userEntity.getNickName());
                    session.setAttribute(User.USER_KEY, user);
                } catch (Exception e) {
                    LOGGER.warn("Failed to refresh user state, it may cause other exception.", e);
                }
            }
        }
        
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        checkSessionUser();
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (!request.getRequestURI().contains("decorator")) {
            LOGGER.info("access url:{}, handler:{}", request.getRequestURI(), handler);
        }
        if (ex != null) {
            LOGGER.error("拦截器捕获到异常。", ex);
            if (isAjaxRequest(request)) {
                AjaxResult res = new AjaxResult();
                res.setSuccess(false);
                String message = null;
                if (ex instanceof TranslatableException) {
                    message = messageResolver.getMessage(((TranslatableException) ex).getCode());
                } else if (ex instanceof NullPointerException) {
                    message = "Null Point Exception!";
                }
                if (StringUtils.isEmpty(message)) {
                    message = ex.getLocalizedMessage();
                }
                res.setMessage(message);
                res.setData(ex);
                response.setCharacterEncoding("UTF-8");
                response.setStatus(500);
                response.setHeader("AJAX_ERROR", "1");
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                response.getWriter().print(res);
                response.getWriter().flush();
                response.getWriter().close();
            } else {
                throw ex;
            }
        }
    }
    
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals("XMLHttpRequest");
    }
}
