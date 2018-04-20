package com.dc.frame2.view.support;

import freemarker.template.SimpleHash;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Defferson.Cheng
 * @date 2018/4/20.
 */
public interface Frame2ServletContextResolver {
    SimpleHash buildContextModule(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response);
}
