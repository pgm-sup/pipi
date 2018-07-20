package com.wyc.service;

import java.util.List;

import com.wyc.entity.Permission;

public interface PermissionService {
	Long addPermission(Permission permission);
	void deletePermission(Long permissionId);
	void deleteMorePermissions(Long...permIds);
	Permission findById(Long permId);
	List<Permission> getPermissionsByRoleId(Long roleId);
	List<Permission> getAllPermissions();
	void updatePermission(Permission permission);
}
