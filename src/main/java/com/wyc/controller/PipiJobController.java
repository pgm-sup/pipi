package com.wyc.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyc.log.SystemLog;
import com.wyc.utils.HttpInvoker;

@Controller
@RequestMapping("/pipiJob")
public class PipiJobController {
	
	
	@RequestMapping(value = "/index")
	@RequiresPermissions("pipiJob:index")
	@SystemLog(module = "pipiJob首页访问", methods = "进入pipiJob首页")
	public void getPipiJonIndex(HttpServletResponse response) {
		
		
		ByteArrayOutputStream out1 = (ByteArrayOutputStream)(HttpInvoker.doGet("http://100.124.10.6:10725/"));
		
		response.setContentType("text/html;charset=utf-8");
		try {
			OutputStream out = response.getOutputStream();
			out.write(out1.toByteArray());
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
