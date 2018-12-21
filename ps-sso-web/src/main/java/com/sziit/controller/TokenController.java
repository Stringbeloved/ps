package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sziit.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TokenController {

	@Reference
	private TokenService tokenService;
	
	@RequestMapping(value="/user/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token,String callback){
		String result = tokenService.getUserByToken(token);
		//响应结果之前判断是否为jsonp请求
		if(StringUtils.isNotBlank(callback)){
			/*
			 * spring4.1以后支持
			 */
			MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return result;
	}
}
