package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sziit.pojo.User;
import com.sziit.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

	@Reference
	private RegisterService registerService;
	
	
	@RequestMapping("/page/register")
	public String showRegister(){
		return "register";
	}
	
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public Boolean checkData(@PathVariable String param,@PathVariable Integer type){
		Boolean result = registerService.checkData(param, type);
		return result;
	}
	
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	@ResponseBody
	public int register(User user){
		int result = registerService.register(user);
		return result;
	}
	
}
