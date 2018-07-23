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
 * 权限控制器
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
	@SystemLog(module = "权限管理", methods = "查看权限列表")
	public ModelAndView showRoleList(){
		List<Permission> list=permissionService.getAllPermissions();
		
		ModelAndView mav=new ModelAndView("permission-list");
		mav.addObject("perms", list);
		
		return mav;
	}
	
	@RequiresPermissions("perm:add")
	@RequestMapping("/add")
	@ResponseBody
	@SystemLog(module = "权限管理", methods = "添加权限")
	public Permission addPermission(Permission permission){
		
		permissionService.addPermission(permission);
		return permission;
	}
	
	@RequiresPermissions("perm:delete")
	@RequestMapping("/delete")
	@ResponseBody
	@SystemLog(module = "权限管理", methods = "删除权限")
	public void deletePermission(Long permId){
		permissionService.deletePermission(permId);
	}
	
	@RequiresPermissions("perm:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	@SystemLog(module = "权限管理", methods = "批量删除权限")
	public void deleteMorePerms(Long...permIds){
		permissionService.deleteMorePermissions(permIds);
	}
	
	@RequiresPermissions("perm:update")
	@RequestMapping("/getperm")
	@ResponseBody
	@SystemLog(module = "权限管理", methods = "查看权限")
	public Permission getPermById(Long permId){
		return permissionService.findById(permId);
	}
	
	@RequiresPermissions("perm:update")
	@RequestMapping("/update")
	@ResponseBody
	@SystemLog(module = "权限管理", methods = "更新权限")
	public void updatePermission(Permission permission){
		permissionService.updatePermission(permission);
	}
}
