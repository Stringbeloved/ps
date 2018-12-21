package com.sziit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sziit.pojo.User;
import com.sziit.service.LoginService;
import com.sziit.utils.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {

	@Reference
	private LoginService loginService;

	
	@RequestMapping("/page/login")
	public String showLogin(String redirect,Model model){
		model.addAttribute("redirect",redirect);
		return "login";
	}
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(User user, HttpServletRequest request, HttpServletResponse response){
		String key=loginService.userLogin(user);
		if(key!=null) {
			String token = key.toString();
			CookieUtils.setCookie(request, response, "token", token);
		}
		return key;
	}
}
