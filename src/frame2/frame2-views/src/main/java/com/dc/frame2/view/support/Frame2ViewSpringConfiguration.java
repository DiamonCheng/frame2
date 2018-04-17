package com.dc.frame2.view.support;

import com.dc.frame2.view.engine.freemarker.FreeMarkerConfigurationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Collections;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/17.
 */
@Configuration
public class Frame2ViewSpringConfiguration {
    @Bean
    public FreeMarkerConfigurationManager freeMarkerConfigurationManager(){
        return new FreeMarkerConfigurationManager();
    }
    @Bean
    public RequestMappingHandlerAdapter addHandlers(RequestMappingHandlerAdapter requestMappingHandlerAdapter){
        requestMappingHandlerAdapter.setCustomReturnValueHandlers(Collections.singletonList(new Frame2ViewHandler()));
        return requestMappingHandlerAdapter;
    }
}
