package com.dc.frame2.util.web;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Define in a web context, bind  request response session to thread local, then use static method to obtain the objects in current thread.
 *
 * @author DC
 * @date 2018/4/21.
 */
public class WebContextBinder implements HandlerInterceptor {
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = ThreadLocal.withInitial(() -> new HashMap<>(3));
    private static final String REQUEST_KEY = "REQUEST_KEY";
    private static final String RESPONSE_KEY = "RESPONSE_KEY";
    private static final String SESSION_KEY = "SESSION_KEY";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        THREAD_LOCAL.get().put(REQUEST_KEY, request);
        THREAD_LOCAL.get().put(RESPONSE_KEY, response);
        THREAD_LOCAL.get().put(SESSION_KEY, request.getSession());
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
    
    /**
     * get request in current thread
     *
     * @return request in current thread
     */
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) THREAD_LOCAL.get().get(REQUEST_KEY);
    }
    
    /**
     * get response in current thread
     *
     * @return response in current thread
     */
    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) THREAD_LOCAL.get().get(RESPONSE_KEY);
    }
    
    /**
     * get session in current thread
     *
     * @return session in current thread
     */
    public static HttpSession getSession() {
        return (HttpSession) THREAD_LOCAL.get().get(SESSION_KEY);
    }
}
