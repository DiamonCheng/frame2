package com.dc.frame2.view.engine.freemarker;

import com.dc.frame2.util.MapBuilder;
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
    private static final String DATE_FORMAT="yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT="yyyy-MM-dd HH:mm:ss";
    private static FreeMarkerConfigurationManager instance;
    private Configuration configuration;
    
    public FreeMarkerConfigurationManager(){
        this.configuration=new Configuration(Configuration.VERSION_2_3_0);
        configuration.setClassForTemplateLoading(this.getClass(),DEFAULT_TEMPLATE_BASE_PACKAGE_PATH);
        configuration.setDefaultEncoding(DEFAULT_CHARSET);
        configuration.setDateFormat(DATE_FORMAT);
        configuration.setDateTimeFormat(DATE_TIME_FORMAT);
//        configuration.set
        if (instance!=null){
            throw new IllegalStateException("Do not init FreeMarkerConfigurationManager more than once");
        }
        instance=this;
    }
    
    public FreeMarkerConfigurationManager(Configuration configuration){
        this.configuration=configuration;
        configuration.setClassForTemplateLoading(this.getClass(),DEFAULT_TEMPLATE_BASE_PACKAGE_PATH);
        configuration.setDefaultEncoding(DEFAULT_CHARSET);
        if (instance!=null){
            throw new IllegalStateException("Do not init FreeMarkerConfigurationManager more than once");
        }
        instance=this;
    }
    
    public Configuration getConfiguration() {
        return configuration;
    }
    
    public FreeMarkerConfigurationManager setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }
    
    public static FreeMarkerConfigurationManager getInstance() {
        return instance==null?new FreeMarkerConfigurationManager():instance;
    }
}
