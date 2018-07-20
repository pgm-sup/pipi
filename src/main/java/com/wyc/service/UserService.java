package com.wyc.service;

import java.util.List;
import java.util.Set;

import com.wyc.entity.Navigation;
import com.wyc.entity.User;

public interface UserService {
	void addUser(User user,Long...roleIds);//����û�
	void deleteUser(Long userId);//ɾ���û�
	void deleteMoreUsers(Long...userIds);//����ɾ���û�
	User getUserByUserName(String userName);//�����û�����ȡ�û�
	List<User> getAllUsers();//��ȡ�����û�
	
	void updateUserRoles(Long userId,Long...roleIds);//����û���ɫ����
	
	Set<String> findRolesByUserName(String userName);//�����û�����ȡ�û����н�ɫ
	Set<String> findPermissionsByUserName(String userName);//�����û�����ȡ�û�����Ȩ��
	
	List<Navigation> getNavigationBar(String userName);//��ȡ����������
}
