package com.sziit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sziit.pojo.User;
import com.sziit.service.TokenService;
import com.sziit.utils.CookieUtils;
import com.sziit.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 用户登录处理拦截器
 * @author sziit
 *
 */
@Component
public class CartInterceptor implements HandlerInterceptor {

	@Reference
	private TokenService tokenService;
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object hanlder, Exception exception)
			throws Exception {
		//完成处理返回modelandview之后
		//可以处理异常
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		//handler执行之后，返回modelandview之前
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//handler执行之前，然会true放行，false拦截
		String token = CookieUtils.getCookieValue(request, "token");
		if(StringUtils.isBlank(token)){
			return true;
		}
		String userJson= tokenService.getUserByToken(token);
		if(userJson.isEmpty()){
			return true;
		}
		User user= JsonUtils.jsonToPojo(userJson,User.class);
		request.setAttribute("user", user);
		return true;
	}

}
