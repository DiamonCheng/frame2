package com.dc.frame2.view.view.freemarker.page;

/**
 * Base html static resource template structure.
 * <p>Used to add custom resource in PageView
 *
 * @author DC
 * @date 2018/5/6.
 */
public interface Resource {
    /**
     * render a html element for this resource
     *
     * @return String as a rendered html element, such as <br/>
     * <code> &lt;script src="/resource/js/common.js"&gt;&lt;/script&gt;</code>
     */
    String resolveHtmlElement();
}
