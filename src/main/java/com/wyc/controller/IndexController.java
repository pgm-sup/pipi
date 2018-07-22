package com.wyc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Ê×Ò³
 * @author haima
 *
 */
@Controller
public class IndexController {
	
	@RequestMapping(value={"/","/index"})
	public ModelAndView index(){
		ModelAndView mov=new ModelAndView("index");
		
		return mov;
	}
}
