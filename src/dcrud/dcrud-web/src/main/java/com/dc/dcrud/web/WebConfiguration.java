package com.dc.dcrud.web;

import com.dc.frame2.util.web.MessageResolver;
import com.dc.frame2.util.web.WebContextBinder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/18.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    private static final String[] MESSAGE_SOURCE_LOCATIONS={"classpath:/i18n/messages_page"};
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource=new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
        reloadableResourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
        reloadableResourceBundleMessageSource.setBasenames(MESSAGE_SOURCE_LOCATIONS);
        return reloadableResourceBundleMessageSource;
    }
    
    @Bean("localeResolver")
    public LocaleResolver localeResolverBean() {
        SessionLocaleResolver sessionLocaleResolver=new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.CHINA);
        return sessionLocaleResolver;
    }
    
    
    @Bean
    public WebContextBinder webContextBinder() {
        return new WebContextBinder();
    }
    
    @Bean
    public MessageResolver messageResolver(MessageSource messageSource) {
        return new MessageResolver().setMessageSource(messageSource);
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setIgnoreInvalidLocale(true);
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webContextBinder());
        registry.addInterceptor(localeChangeInterceptor());
    }
    
}
