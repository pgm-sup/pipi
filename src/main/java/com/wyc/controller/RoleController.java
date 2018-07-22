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

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	
	@RequiresPermissions("role:list")
	@RequestMapping("/list")
	@SystemLog(module = "��ɫ����", methods = "�鿴��ɫ�б�")
	public ModelAndView showRoleList(){
		List<Role> list=roleService.getAllRoles();
		
		ModelAndView mav=new ModelAndView("role-list");
		mav.addObject("roles", list);
		return mav;
	}
	
	@RequiresPermissions("role:showperms")
	@RequestMapping("/listperms")
	@ResponseBody
	@SystemLog(module = "��ɫ����", methods = "�鿴��ǰ��ɫ������Ȩ��")
	public List<Permission> getPerms(){
		return permissionService.getAllPermissions();
	}
	
	@RequiresPermissions("role:add")
	@RequestMapping("/add")
	@ResponseBody
	@SystemLog(module = "��ɫ����", methods = "��ӽ�ɫ")
	public Role addRole(Role role,Long...permIds){
		roleService.addRole(role, permIds);
		return role;
	}
	
	@RequiresPermissions("role:delete")
	@RequestMapping("/delete")
	@ResponseBody
	@SystemLog(module = "��ɫ����", methods = "ɾ����ɫ")
	public void deleteRole(Long roleId){
		roleService.deleteRole(roleId);
	}
	
	@RequiresPermissions("role:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	@SystemLog(module = "��ɫ����", methods = "����ɾ����ɫ")
	public void deleteMoreRoles(Long...roleIds){
		roleService.deleteMoreRoles(roleIds);
	}
	
	@RequiresPermissions("role:showperms")
	@RequestMapping("/showroleperms")
	@ResponseBody
	@SystemLog(module = "��ɫ����", methods = "�鿴��ɫ����Ȩ��")
	public List<Permission> showRolePerms(Long roleId){
		return permissionService.getPermissionsByRoleId(roleId);
	}
	
	@RequiresPermissions("role:findinfo")
	@RequestMapping("/getrole")
	@ResponseBody
	@SystemLog(module = "��ɫ����", methods = "�鿴��ɫ")
	public Role getRoleById(Long roleId){
		return roleService.getRoleById(roleId);
	}
	
	@RequiresPermissions("role:corelationperm")
	@RequestMapping("/updaterole")
	@ResponseBody()
	@SystemLog(module = "��ɫ����", methods = "���½�ɫ��Ȩ��")
	public void updateRole(Role role,Long...permIds){
		roleService.updateRole(role,permIds);
	}
}
