package com.dc.frame2.view.support;

import com.dc.frame2.view.engine.freemarker.FreemarkerViewRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/17.
 */
public class Frame2ViewSpringConfiguration  implements WebMvcConfigurer{
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
    public FreemarkerViewRender freemarkerViewRender(Frame2ServletContextResolver frame2ServletContextResolver) {
        if (configuration == null) {
            configuration = frame2ViewConfiguration.buildConfiguration();
        }
        return new FreemarkerViewRender().setConfiguration(configuration)
                       .setFrame2ServletContextResolver(frame2ServletContextResolver)
                       .setFrame2ViewConfiguration(frame2ViewConfiguration);
    }
    
    @Bean
    public Frame2ViewHandler frame2ViewHandler(FreemarkerViewRender freemarkerViewRender) {
        return new Frame2ViewHandler()
                       .setFrame2ViewRender(
                               new Frame2ViewRenderHub().addFrame2ViewRender(freemarkerViewRender)
                       ).setFrame2ViewConfiguration(frame2ViewConfiguration);
    }
    
    

}
