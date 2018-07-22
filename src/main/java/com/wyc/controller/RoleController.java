package com.wyc.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wyc.entity.Permission;
import com.wyc.entity.Role;
import com.wyc.log.SystemLog;
import com.wyc.service.PermissionService;
import com.wyc.service.RoleService;


/**
 * 角色控制器
 * @author haima
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	
	@RequiresPermissions("role:list")
	@RequestMapping("/list")
	@SystemLog(module = "角色管理", methods = "查看角色列表")
	public ModelAndView showRoleList(){
		List<Role> list=roleService.getAllRoles();
		
		ModelAndView mav=new ModelAndView("role-list");
		mav.addObject("roles", list);
		return mav;
	}
	
	@RequiresPermissions("role:showperms")
	@RequestMapping("/listperms")
	@ResponseBody
	@SystemLog(module = "角色管理", methods = "查看当前角色的所有权限")
	public List<Permission> getPerms(){
		return permissionService.getAllPermissions();
	}
	
	@RequiresPermissions("role:add")
	@RequestMapping("/add")
	@ResponseBody
	@SystemLog(module = "角色管理", methods = "添加角色")
	public Role addRole(Role role,Long...permIds){
		roleService.addRole(role, permIds);
		return role;
	}
	
	@RequiresPermissions("role:delete")
	@RequestMapping("/delete")
	@ResponseBody
	@SystemLog(module = "角色管理", methods = "删除角色")
	public void deleteRole(Long roleId){
		roleService.deleteRole(roleId);
	}
	
	@RequiresPermissions("role:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	@SystemLog(module = "角色管理", methods = "批量删除角色")
	public void deleteMoreRoles(Long...roleIds){
		roleService.deleteMoreRoles(roleIds);
	}
	
	@RequiresPermissions("role:showperms")
	@RequestMapping("/showroleperms")
	@ResponseBody
	@SystemLog(module = "角色管理", methods = "查看角色所有权限")
	public List<Permission> showRolePerms(Long roleId){
		return permissionService.getPermissionsByRoleId(roleId);
	}
	
	@RequiresPermissions("role:findinfo")
	@RequestMapping("/getrole")
	@ResponseBody
	@SystemLog(module = "角色管理", methods = "查看角色")
	public Role getRoleById(Long roleId){
		return roleService.getRoleById(roleId);
	}
	
	@RequiresPermissions("role:corelationperm")
	@RequestMapping("/updaterole")
	@ResponseBody()
	@SystemLog(module = "角色管理", methods = "更新角色的权限")
	public void updateRole(Role role,Long...permIds){
		roleService.updateRole(role,permIds);
	}
}
