package com.wyc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.wyc.entity.Role;
import com.wyc.entity.User;
import com.wyc.log.SystemLog;
import com.wyc.service.RoleService;
import com.wyc.service.UserService;

/**
 * 用户控制器
 * @author haima
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
//	@Autowired
//	private PasswordService passwordService;
//	
	@RequestMapping("/login")
	@SystemLog(module = "用户管理", methods = "用户登录")
	public ModelAndView login(HttpServletRequest req){
		String error=null;
		String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
//		System.out.println("1111");
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "账号错误!";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "密码错误!";
        } else if(exceptionClassName != null) {
            error = "登录异常" + exceptionClassName;
        }
        ModelAndView mav=new ModelAndView("login");
        mav.addObject("error", error);
		return mav;
	}
	
	@RequiresPermissions("user:list")
	@RequestMapping("/list")
	@SystemLog(module = "用户管理", methods = "查看用户列表")
	public ModelAndView showUserList(){
		List<User> list=userService.getAllUsers();
		ModelAndView mav=new ModelAndView("user-list");
		mav.addObject("users", list);
		return mav;
	}
	
	@RequiresPermissions("user:add")
	@RequestMapping("/add")
	@ResponseBody
	@SystemLog(module = "用户管理", methods = "添加用户")
	public Object addUser(User user,Long...roleIds){
		String password = user.getPassword();
		if(validPwd(password)) {
			userService.addUser(user, roleIds);
			return user;
		}
		return "密码不符合规范";
	}
	
	@RequiresPermissions("user:showroles")
	@RequestMapping("/showroles")
	@ResponseBody
	@SystemLog(module = "用户管理", methods = "查看用户所有角色")
	public List<Role> shwoRoles(String userName){
		return roleService.getRolesByUserName(userName);
	}

	@RequiresPermissions("role:list")
	@RequestMapping("/listRoles")
	@ResponseBody
	@SystemLog(module = "用户管理", methods = "查看所有角色")
	public List<Role> getRoles(){
		return roleService.getAllRoles();
	}
	
	@RequiresPermissions("user:delete")
	@RequestMapping("/delete")
	@ResponseBody
	@SystemLog(module = "用户管理", methods = "删除用户账号")
	public void deleteUser(Long userId){
		userService.deleteUser(userId);
	}
	
	@RequiresPermissions("user:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	@SystemLog(module = "用户管理", methods = "批量删除用户账号")
	public void deleteMoreUsers(Long...userIds){
		userService.deleteMoreUsers(userIds);
	}
	
	@RequiresPermissions("user:corelationrole")
	@RequestMapping("/corelationRole")
	@ResponseBody
	@SystemLog(module = "用户管理", methods = "更新用户角色")
	public void corelationRole(Long userId,Long...roleIds){
		userService.updateUserRoles(userId, roleIds);
	}
	

/**
	 * 校验密码
	 * 1、长度不小于6位
	 * 2、必须以字母开头
	 * 3、必须包含特殊字符
	 * 4、必须包含数字
	 * @param pwd
	 * @return
	 */
	public static boolean validPwd(String pwd){
		if(StringUtils.isNullOrEmpty(pwd)){
			return false;
		}
		if(pwd.length() < 6){
			return false;
		}
		if(pwd.matches("^[a-zA-z](.*)") && pwd.matches("(.*)[-`=\\\\\\[\\];',./~!@#$%^&*()_+|{}:\"<>?]+(.*)") && pwd.matches("(.*)\\d+(.*)")){
			return true;
		}
		return false;
	}
}
