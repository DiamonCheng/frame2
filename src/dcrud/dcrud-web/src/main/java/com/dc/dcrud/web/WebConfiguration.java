package com.dc.dcrud.web;

import com.dc.dcrud.web.interceptor.DcrudInterceptor;
import com.dc.frame2.util.SpringContextUtils;
import com.dc.frame2.util.web.MessageResolver;
import com.dc.frame2.util.web.WebContextBinder;
import com.dc.frame2.view.support.Frame2ViewConfiguration;
import com.dc.frame2.view.support.Frame2ViewSpringConfiguration;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
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
@Import({Frame2ViewSpringConfiguration.class})
public class WebConfiguration extends WebMvcConfigurerAdapter {
    private static final String[] MESSAGE_SOURCE_LOCATIONS = {
            "classpath:/i18n/messages",
            "classpath:/i18n/messages_page",
            "classpath:/i18n/messages_base",
            "classpath:/i18n/messages_page_base"
    };
    
    @Value("${debug:false}")
    private boolean debug;
    
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource=new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
        reloadableResourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
        reloadableResourceBundleMessageSource.setBasenames(MESSAGE_SOURCE_LOCATIONS);
        if (debug) {
            reloadableResourceBundleMessageSource.setCacheMillis(0);
        }
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
    public SpringContextUtils springContextUtil() {
        return new SpringContextUtils();
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
    
    @Bean
    public DcrudInterceptor dcrudInterceptor() {
        return new DcrudInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webContextBinder());
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(dcrudInterceptor());
    }
    
    @Bean
    public FilterRegistrationBean getConfigurableSiteMeshFilter(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new ConfigurableSiteMeshFilter(){
            @Override
            protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
                builder.addDecoratorPath("/**", "/decorator")
                        .addExcludedPath("/resources**")
                        .addExcludedPath("**ajax**")
                        .addExcludedPath("/login");
            }
        });
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
    
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
            container.addErrorPages(new ErrorPage(Throwable.class, "/error/500"));
        };
    }
    
    @Bean
    public Frame2ViewConfiguration frame2ViewConfiguration(@Value("${debug:false}") boolean debug) {
        return new Frame2ViewConfiguration().setDebug(debug);
    }
}
