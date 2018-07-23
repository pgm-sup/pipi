package com.wyc.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wyc.utils.HttpInvoker;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getPipiJobTaskList(HttpServletRequest request, HttpServletResponse response) {
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		
		ByteArrayOutputStream out1 = (ByteArrayOutputStream)(HttpInvoker.doGet("http://192.168.1.39:8080/" + uri));
		
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
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void getPipiJobTaskAdd(HttpServletRequest request, HttpServletResponse response) {
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		
		ByteArrayOutputStream out1 = (ByteArrayOutputStream)(HttpInvoker.doGet("http://192.168.1.39:8080/" + uri));
		
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
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void getPipiJobTaskAddForm(HttpServletRequest request, HttpServletResponse response) {
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		
		ByteArrayOutputStream out1 = (ByteArrayOutputStream)(HttpInvoker.doGet("http://192.168.1.39:8080/" + uri));
		
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
