package com.dc.dcrud.web;

import com.dc.dcrud.web.interceptor.DcrudInterceptor;
import com.dc.frame2.dict.HqlOptionProvider;
import com.dc.frame2.util.SpringContextUtils;
import com.dc.frame2.util.web.MessageResolver;
import com.dc.frame2.util.web.WebContextBinder;
import com.dc.frame2.view.support.Frame2ViewConfiguration;
import com.dc.frame2.view.support.Frame2ViewSpringConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.Filter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/18.
 */
@Configuration
@Import({Frame2ViewSpringConfiguration.class})
public class WebConfiguration implements WebMvcConfigurer {
    
    @Value("${spring.messages.basename}")
    private String[] messageSourceBaseNames;
    
    @Value("${debug:false}")
    private boolean debug;
    
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource=new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
        reloadableResourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
        reloadableResourceBundleMessageSource.setBasenames(messageSourceBaseNames);
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
    
    @Bean
    public OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor() {
        return new OpenEntityManagerInViewInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(openEntityManagerInViewInterceptor());
        registry.addInterceptor(webContextBinder());
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(dcrudInterceptor()).excludePathPatterns("/error/*");
    }
    
    @Bean
    public FilterRegistrationBean getConfigurableSiteMeshFilter(){
        FilterRegistrationBean<Filter> registrationBean=new FilterRegistrationBean<>();
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
        registrationBean.setOrder(1000);
        return registrationBean;
    }
    
    @Bean
    public FilterRegistrationBean getShiroFilter(ShiroFilterFactoryBean shiroFilterFactoryBean) throws Exception {
        FilterRegistrationBean<Filter> registrationBean=new FilterRegistrationBean<>();
        registrationBean.setFilter((Filter) shiroFilterFactoryBean.getObject());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(10);
        return registrationBean;
    }
    
    @Bean
    public ErrorPageRegistrar containerCustomizer() {
        return registry -> {
                    registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
                    registry.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"));
                    registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
                    registry.addErrorPages(new ErrorPage(Throwable.class, "/error/500"));
        };
    }
    
    @Bean
    public Frame2ViewConfiguration frame2ViewConfiguration(@Value("${debug:false}") boolean debug) {
        return new Frame2ViewConfiguration().setDebug(debug);
    }
    
    @Bean
    public WebBindingInitializer configurableWebBindingInitializer() {
        return new ConfigurableWebBindingInitializer() {
            @Override
            public void initBinder(WebDataBinder binder) {
                super.initBinder(binder);
                bindingDateEditor(binder);
                bindingArrayEditor(binder);
            }
        };
    }
    
    private void bindingDateEditor(WebDataBinder binder) {
        CustomDateEditor dataEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"), true) {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    super.setAsText(text);
                } catch (IllegalArgumentException e) {
                    DateFormat[] dateFormats = new DateFormat[]{
                            new SimpleDateFormat("yyyy-MM-dd"),
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"),
                            new SimpleDateFormat("yyyy/MM/dd"),
                            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),
                            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS"),
                    };
                    for (DateFormat df : dateFormats) {
                        try {
                            setValue(df.parse(text));
                            return;
                        } catch (Exception e2) {
                            e.addSuppressed(e2);
                        }
                    }
                    try {
                        setValue(new Date(Long.parseLong(text)));
                        return;
                    } catch (Exception e2) {
                        e.addSuppressed(e2);
                    }
                    
                    throw e;
                }
            }
        };
        binder.registerCustomEditor(Date.class, dataEditor);
    }
    
    private void bindingArrayEditor(WebDataBinder binder) {
        StringArrayPropertyEditor sae = new StringArrayPropertyEditor();
        binder.registerCustomEditor(long[].class, sae);
        binder.registerCustomEditor(int[].class, sae);
        binder.registerCustomEditor(short[].class, sae);
        binder.registerCustomEditor(Long[].class, sae);
        binder.registerCustomEditor(Integer[].class, sae);
        binder.registerCustomEditor(Short[].class, sae);
    }
    
    @Bean(name = "HqlOptionProvider")
    public HqlOptionProvider hqlOptionProvider(){
        return new HqlOptionProvider();
    }
}
