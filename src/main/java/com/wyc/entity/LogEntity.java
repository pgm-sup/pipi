package com.wyc.entity;

import java.io.Serializable;

public class LogEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// �û�
	private String username;
	// ִ��ģ��
	private String module;
	// ִ�з�������
	private String method;
	// ��Ӧʱ��
	private String response_data;
	// IP
	private String ip;
	// ִ��ʱ��
	private String data;
	// ִ������
	private String comment;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getResponse_data() {
		return response_data;
	}
	public void setResponse_data(String response_daata) {
		this.response_data = response_daata;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
