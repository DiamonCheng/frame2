package com.dc.frame2.util.web;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Configure it in spring and inject it the use；
 * Only usable in a web context; means a request should bind to this thread.
 * Must define and configure the WebContextBinder.
 *
 *
 * @author DC
 * @date 2018/4/21.
 */
public class MessageResolver {
    
    private MessageSource messageSource;
    
    /**
     * get translated message by code. Use local in current request.
     *
     * @param code message code in message*.properties
     * @return translated message
     */
    public String getMessage(String code) {
        return messageSource.getMessage(code, new Object[0], LocaleContextHolder.getLocale());
    }
    
    /**
     * get translated message by code. Use local in current request.
     *
     * @param code message code in message*.properties
     * @param args the arguments will be filled to "{}" in translated message.
     * @return translated message
     */
    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
    
    public MessageResolver setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
        return this;
    }
}
