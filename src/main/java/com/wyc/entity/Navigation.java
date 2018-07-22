package com.wyc.entity;

import java.util.List;

/**
 * µ¼º½½Úµã
 * @author haima
 *
 */
public class Navigation {
	private String navigationName;
	private List<Permission> childNavigations;
	
	public String getNavigationName() {
		return navigationName;
	}
	public void setNavigationName(String navigationName) {
		this.navigationName = navigationName;
	}
	public List<Permission> getChildNavigations() {
		return childNavigations;
	}
	public void setChildNavigations(List<Permission> childNavigations) {
		this.childNavigations = childNavigations;
	}
}
