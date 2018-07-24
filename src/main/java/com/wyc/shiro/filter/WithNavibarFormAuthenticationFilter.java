package com.wyc.shiro.filter;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.wyc.entity.Navigation;
import com.wyc.service.UserService;
import com.wyc.utils.HttpInvoker;

public class WithNavibarFormAuthenticationFilter extends FormAuthenticationFilter {

	@Autowired
	private UserService userService;
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletRequest httpReq=(HttpServletRequest)request;
		System.out.println(SecurityUtils.getSubject().hasRole(""));		
		System.out.println(httpReq.getRequestURI());
		System.out.println(httpReq.getSession().getAttribute("user"));
		System.out.println(SecurityUtils.getSubject().hasRole("operatAdmin"));
		String userName=(String)SecurityUtils.getSubject().getPrincipal();
		List<Navigation> navigationBar=userService.getNavigationBar(userName);
		httpReq.getSession().setAttribute("navibar", navigationBar);
		return super.onLoginSuccess(token, subject, request, response);
	}
}
