package com.dc.frame2.view.support;

import freemarker.template.Configuration;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/17.
 */
public class Frame2ViewConfiguration {
    private static final String DEFAULT_TEMPLATE_BASE_PACKAGE_PATH="/templates";
    private static final String DEFAULT_CHARSET="UTF-8";
    private static final String DATE_FORMAT="yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT="yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_CONTENT_TYPE = "text/html";
    private String charset=DEFAULT_CHARSET;
    private String dateFormat=DATE_FORMAT;
    private String dateTimeFormat=DATE_TIME_FORMAT;
    private String contentType = DEFAULT_CONTENT_TYPE;
    
    private boolean debug;
    
    public Configuration buildConfiguration() {
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_0);
        configuration.setClassForTemplateLoading(this.getClass(),DEFAULT_TEMPLATE_BASE_PACKAGE_PATH);
        configuration.setDefaultEncoding(charset);
        configuration.setDateFormat(dateFormat);
        configuration.setDateTimeFormat(dateTimeFormat);
        configuration.setClassicCompatible(true);
        if (debug) {
            configuration.setTemplateUpdateDelayMilliseconds(0);
        }
        configuration.addAutoImport("spring","/spring.ftl");
        return configuration;
    }
    

    
    public Frame2ViewConfiguration setCharset(String charset) {
        this.charset = charset;
        return this;
    }
    
    public Frame2ViewConfiguration setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }
    
    public Frame2ViewConfiguration setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
        return this;
    }
    
    public String getCharset() {
        return charset;
    }
    
    public String getContentType() {
        return contentType;
    }
    
    public Frame2ViewConfiguration setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    public Frame2ViewConfiguration setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }
}
