package com.wyc.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wyc.entity.Permission;
import com.wyc.log.SystemLog;
import com.wyc.service.PermissionService;


/**
 * Ȩ�޿�����
 * @author haima
 *
 */
@Controller
@RequestMapping("/perm")
public class PermissionController {
	@Autowired
	private PermissionService permissionService;
	
	@RequiresPermissions("perm:list")
	@RequestMapping("/list")
	@SystemLog(module = "Ȩ�޹���", methods = "�鿴Ȩ���б�")
	public ModelAndView showRoleList(){
		List<Permission> list=permissionService.getAllPermissions();
		
		ModelAndView mav=new ModelAndView("permission-list");
		mav.addObject("perms", list);
		
		return mav;
	}
	
	@RequiresPermissions("perm:add")
	@RequestMapping("/add")
	@ResponseBody
	@SystemLog(module = "Ȩ�޹���", methods = "���Ȩ��")
	public Permission addPermission(Permission permission){
		
		permissionService.addPermission(permission);
		return permission;
	}
	
	@RequiresPermissions("perm:delete")
	@RequestMapping("/delete")
	@ResponseBody
	@SystemLog(module = "Ȩ�޹���", methods = "ɾ��Ȩ��")
	public void deletePermission(Long permId){
		permissionService.deletePermission(permId);
	}
	
	@RequiresPermissions("perm:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	@SystemLog(module = "Ȩ�޹���", methods = "����ɾ��Ȩ��")
	public void deleteMorePerms(Long...permIds){
		permissionService.deleteMorePermissions(permIds);
	}
	
	@RequiresPermissions("perm:update")
	@RequestMapping("/getperm")
	@ResponseBody
	@SystemLog(module = "Ȩ�޹���", methods = "�鿴Ȩ��")
	public Permission getPermById(Long permId){
		return permissionService.findById(permId);
	}
	
	@RequiresPermissions("perm:update")
	@RequestMapping("/update")
	@ResponseBody
	@SystemLog(module = "Ȩ�޹���", methods = "����Ȩ��")
	public void updatePermission(Permission permission){
		permissionService.updatePermission(permission);
	}
}
