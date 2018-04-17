package com.dc.frame2.view.engine.freemarker;

import freemarker.template.Configuration;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/17.
 */
public class FreeMarkerConfigurationManager {
    private static final String DEFAULT_TEMPLATE_BASE_PACKAGE_PATH="/templates";
    private static final String DEFAULT_CHARSET="UTF-8";
    private Configuration configuration;
    
    public FreeMarkerConfigurationManager(){
        this.configuration=new Configuration(Configuration.VERSION_2_3_0);
        configuration.setClassForTemplateLoading(this.getClass(),DEFAULT_TEMPLATE_BASE_PACKAGE_PATH);
        configuration.setDefaultEncoding(DEFAULT_CHARSET);
    }
    
    public FreeMarkerConfigurationManager(Configuration configuration){
        this.configuration=configuration;
    }
    
    public Configuration getConfiguration() {
        return configuration;
    }
    
    public FreeMarkerConfigurationManager setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }
}
