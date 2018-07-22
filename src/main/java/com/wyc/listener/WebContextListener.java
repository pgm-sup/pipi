package com.wyc.listener;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

 /**
  * 系统自启动服务
  * @author haima
  *
  */
@Service
public class WebContextListener implements InitializingBean, ServletContextAware{
 
	
	/**
	 * 登录pipiJob
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
		try {
			System.out.println("启动便执行代码");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	 
}

