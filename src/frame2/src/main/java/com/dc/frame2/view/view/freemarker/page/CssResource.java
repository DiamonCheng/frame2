package com.dc.frame2.view.view.freemarker.page;

/**
 * <p>Css resource element used to add custom css resource in PageView
 *
 * @author DC
 * @date 2018/5/6.
 */
public class CssResource implements Resource {
    private static final String ELEMENT = "<link rel=\"stylesheet\" type=\"text/css\" href=\"%s\"/>";
    private String path;
    
    /**
     * set the html load path
     *
     * @param path url relative or absolute
     * @return this
     */
    public CssResource setPath(String path) {
        this.path = path;
        return this;
    }
    
    @Override
    public String resolveHtmlElement() {
        if (path != null) {
            return String.format(ELEMENT, path);
        } else {
            return "";
        }
    }
}
