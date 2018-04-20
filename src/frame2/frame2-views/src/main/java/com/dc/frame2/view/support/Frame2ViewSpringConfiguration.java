package com.dc.frame2.view.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/17.
 */
public class Frame2ViewSpringConfiguration  {
    @Autowired(required = false)
    private Frame2ViewConfiguration frame2ViewConfiguration=new Frame2ViewConfiguration();
    
    private freemarker.template.Configuration configuration;
    
    @Bean
    public Frame2ServletContextResolver frame2ServletContextResolver(){
        if (configuration==null){
            configuration=frame2ViewConfiguration.buildConfiguration();
        }
        return new FreemarkerServletContextResolver().setConfiguration(configuration);
    }
    
    @Bean
    public Frame2ViewHandler frame2ViewHandler(Frame2ServletContextResolver frame2ServletContextResolver){
        if (configuration==null){
            configuration=frame2ViewConfiguration.buildConfiguration();
        }
        return new Frame2ViewHandler()
                       .setFrame2ServletContextResolver(frame2ServletContextResolver)
                       .setConfiguration(configuration)
                       .setFrame2ViewConfiguration(frame2ViewConfiguration);
    }
    
    
    @Configuration
    public class Frame2WebMvcConfig extends WebMvcConfigurerAdapter{
        @Autowired
        private Frame2ViewHandler frame2ViewHandler;
        @Override
        public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
            returnValueHandlers.add(frame2ViewHandler);
        }
    }
    

}
