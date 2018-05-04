package com.dc.dcrud.service.shiro;

import com.dc.dcrud.domain.RoleEntity;
import com.dc.dcrud.domain.UserEntity;
import com.dc.dcrud.pojo.User;
import com.dc.dcrud.service.rbac.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/21.
 */
public class SecurityRealm extends AuthorizingRealm {
    
    @Autowired
    private UserService userService;
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserEntity userEntity = userService.getUserByUsername(username);
        Collection<RoleEntity> roles = userEntity.getRoles();
        HashSet<String> roleCodes = new HashSet<>(roles.size());
        Set<String> permissions = new HashSet<>();
        roles.forEach(roleEntity -> {
            roleCodes.add(roleEntity.getCode());
            roleEntity.getPermissions().forEach(operationEntity -> permissions.add(operationEntity.getCode()));
        });
        authorizationInfo.setRoles(roleCodes);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        UserEntity userEntity = userService.getUserByUsername(username);
        if (userEntity == null) {
            return null;
        }
        User user = new User().setUsername(username).setNickName(userEntity.getNickName());
        SecurityUtils.getSubject().getSession().setAttribute(User.USER_KEY, user);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userEntity.getUsername(), userEntity.getPassword(), "**");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userEntity.getUsername()));
        return authenticationInfo;
    }
}
