package com.dc.dcrud.web.shiro;

import com.dc.dcrud.service.shiro.SecurityRealm;
import com.dc.frame2.util.MapBuilder;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/***
 *
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/resources/**", "anon");
        filterChainDefinitionMap.put("/actuator/health", "anon");
        filterChainDefinitionMap.put("/service/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        //or authc
        filterChainDefinitionMap.put("/**", "user");
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/templates/error/403.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setFilters(
                MapBuilder.<String, Filter>hashMap()
                        .put("authc", new AjaxFormAuthenticationFilter())
                        .put("user", new AjaxFormAuthenticationFilter())
                        .build()
        );
        return shiroFilterFactoryBean;
    }
    
    @Bean
    public AuthorizingRealm shiroRealm() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("sha1");
        SecurityRealm securityRealm = new SecurityRealm();
        securityRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return securityRealm;
    }
    
    @Bean
    public SessionDAO sessionDAO() {
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        enterpriseCacheSessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
        enterpriseCacheSessionDAO.setActiveSessionsCacheName("FRAME2-SESSION-CACHE");
        return enterpriseCacheSessionDAO;
    }
    
    public Cookie cookie(String name) {
        Cookie cookie = new SimpleCookie();
        cookie.setName("FRAME2-COOKIE-" + name);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600 * 24 * 10);
        return cookie;
    }
    
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(cookie("SESSION"));
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setGlobalSessionTimeout(1000 * 3600);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
    
    
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setSessionManager(sessionManager());
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(cookie("REMEMBER_ME"));
        String cipherKey = "HoTP07fJPKIRLOWoVXmv+Q==";
        rememberMeManager.setCipherKey(Base64.decode(cipherKey));
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }
}