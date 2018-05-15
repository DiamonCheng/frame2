package com.dc.dcrud.web.controller;

import com.dc.frame2.core.dto.AjaxResult;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/15.
 */
@Controller
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {
    private static final String ERROR_PATH = "error";
    private static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName()
                                                          + ".ERROR";
    private static final String JAVAX_EX_KEY = "javax.servlet.error.exception";
    
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
    
    @RequestMapping("/404")
    public Object error404(HttpServletRequest request) {
        return "/error/404";
    }
    
    @RequestMapping("/403")
    public Object error403(HttpServletRequest request) {
        return "/error/403";
    }
    
    @RequestMapping(value = "/500", produces = "text/html")
    public ModelAndView error500(HttpServletRequest request,
                                 HttpServletResponse response) {
        Throwable ex = getError(request);
        String exStr = "";
        if (ex != null) {
            exStr = printExString(getError(request)).replace("\n", "<br/>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        }
        return new ModelAndView("error/500").addObject("ex", exStr);
    }
    
    @RequestMapping("/500")
    @ResponseBody
    public Object error500(HttpServletRequest request) {
        AjaxResult result = new AjaxResult().setSuccess(false);
        Throwable ex = getError(request);
        if (ex != null) {
            result.setData(printExString(ex));
        }
        return result;
    }
    
    protected boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }
    
    public Throwable getError(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Throwable exception = getAttribute(requestAttributes, ERROR_ATTRIBUTE);
        if (exception == null) {
            exception = getAttribute(requestAttributes, JAVAX_EX_KEY);
        }
        return exception;
    }
    
    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
    
    private static String printExString(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
