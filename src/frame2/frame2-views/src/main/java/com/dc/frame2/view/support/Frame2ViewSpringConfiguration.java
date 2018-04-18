package com.dc.frame2.view.support;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.engine.freemarker.FreeMarkerConfigurationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/17.
 */
@Configuration
@EnableWebMvc
public class Frame2ViewSpringConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public FreeMarkerConfigurationManager freeMarkerConfigurationManager(){
        FreeMarkerConfigurationManager freeMarkerConfigurationManager=new FreeMarkerConfigurationManager();
        freeMarkerConfigurationManager.getConfiguration().addAutoImport("spring","/spring.ftl");
        return freeMarkerConfigurationManager;
    }
    @Bean
    public Frame2ViewHandler frame2ViewHandler(){
        return new Frame2ViewHandler();
    }
    
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(frame2ViewHandler());
    }

}
