package com.wyc.shiro.realm;

import java.util.Collection;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.wyc.entity.User;
import com.wyc.service.UserService;

public class UserRealm extends AuthorizingRealm{
	
    @Autowired
    private DefaultWebSecurityManager securityManager;

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName=(String)principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findPermissionsByUserName(userName));
		authorizationInfo.setStringPermissions(userService.findPermissionsByUserName(userName));
		
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName=(String)token.getPrincipal();
        Session sessionLocal = SecurityUtils.getSubject().getSession();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager
                .getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO()
                .getActiveSessions();// 获取当前已登录的用户session列表
        for (Session session : sessions) {
            // 实现单点登录
            if (userName
                    .equals(String.valueOf(session
                            .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
            	sessionManager.getSessionDAO().delete(session);
            }
        }
		User user=userService.getUserByUserName(userName);
		if(user != null){
			SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
					user.getUserName(),
					user.getPassword(),
					ByteSource.Util.bytes(user.getUserName()+user.getPasswordSalt()),
					getName());
			return authenticationInfo;
			
		}
		throw new UnknownAccountException();
	}

}
