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
//			String result = HttpUtils.doPost(url, params)("http://127.0.0.1:8081/login", "username=admin&password=123456");

//			System.out.println(result);
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

