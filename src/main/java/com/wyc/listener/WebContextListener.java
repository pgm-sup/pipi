package com.wyc.listener;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

 /**
  * ϵͳ����������
  * @author haima
  *
  */
@Service
public class WebContextListener implements InitializingBean, ServletContextAware{
 
	
	/**
	 * ��¼pipiJob
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
		try {
			System.out.println("������ִ�д���");
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

