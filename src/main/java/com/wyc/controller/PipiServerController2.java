package com.wyc.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpClient;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wyc.log.SystemLog;
import com.wyc.utils.HttpInvoker;

@Controller
@RequestMapping("/server")
public class PipiServerController2 {

	@RequestMapping(value = "/*")
	@RequiresPermissions("server:*")
	@SystemLog(module = "pipiJob服务器管理", methods = "操作pipiJob服务器")
	public void getPipiJobTaskList(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		Map<String, String[]> parameterMap = request.getParameterMap();
		System.out.println(request.getMethod());
		String prestr = "";
		System.out.println(request.getQueryString());
		ByteArrayOutputStream out1 = null;
		if ("GET".equals(request.getMethod())) {
			List<String> keys = new ArrayList<String>(parameterMap.keySet());
			Collections.sort(keys);
			for (int i = 0; i < keys.size(); i++) {
				String key = keys.get(i);
				// @TODO
				String values[] = parameterMap.get(key);
				String value = "";
				try {
					if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
						for (int j = 0; j < values.length; j++) {
							if (j != values.length - 1) {
								prestr = prestr + key + "=" + URLEncoder.encode(values[j], "UTF-8") + "&";
							} else {
								prestr = prestr + key + "=" + URLEncoder.encode(values[j], "UTF-8");
							}
						}
					} else {
						for (int j = 0; j < values.length; j++) {
							prestr = prestr + key + "=" + URLEncoder.encode(values[j], "UTF-8") + "&";
						}
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			System.out.println("http://192.168.1.104:8080" + uri + "?" + prestr);
			out1 = (ByteArrayOutputStream) (HttpInvoker.doGet("http://192.168.1.104:8080" + uri + "?" + prestr));
		} else {
			
			out1 = (ByteArrayOutputStream) (HttpInvoker.doPostWithUrlParams("http://192.168.1.104:8080" + uri,
					parameterMap));
			System.out.println(1111);
		}

		response.setContentType("text/html;charset=utf-8");
		System.out.println("http://192.168.1.104:8080" + uri + "?" + prestr);
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
