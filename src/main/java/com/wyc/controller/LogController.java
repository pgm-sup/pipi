package com.wyc.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wyc.entity.LogEntity;
import com.wyc.log.SystemLog;
import com.wyc.service.LogService;

/**
 * ��ƿ�����
 * @author haima
 *
 */
@Controller
@RequestMapping("/audit")
public class LogController {
	
	@Autowired
	private LogService logService;

	@RequiresPermissions("audit:list")
	@RequestMapping("/list")
	@SystemLog(module = "��ƹ���", methods = "�鿴����б�")
	public ModelAndView showAuditList(){
		List<LogEntity> list=logService.getAllLogs();
		ModelAndView mav=new ModelAndView("audit-list");
		mav.addObject("logs", list);
		return mav;
	}
}
