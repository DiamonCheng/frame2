package com.dc.dcrud.web.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/16.
 */
public class AjaxFormAuthenticationFilter extends FormAuthenticationFilter {
    private static final String XHR = "XMLHttpRequest";
    
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (this.isLoginRequest(request, response)) {
            if (this.isLoginSubmission(request, response)) {
                return this.executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            if (isAjax(request)) {
                ((HttpServletResponse) response).addHeader("SESSION_STATUS", "TIME_OUT");
                ((HttpServletResponse) response).setStatus(403);
                response.getWriter().append("{\"success\":false,\"message\":\"SESSION TIME OUT\"}").flush();
            } else {
                this.saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }
    
    
    public static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if (XHR.equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
}
