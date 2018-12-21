package com.sziit.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sziit.pojo.Item;
import com.sziit.pojo.User;
import com.sziit.service.CartService;
import com.sziit.service.TokenService;
import com.sziit.utils.CookieUtils;
import com.sziit.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderInterceptor implements HandlerInterceptor {

    private final String SSO_URL="http://localhost:8088";

    @Reference
    private TokenService tokenService;

    @Reference
    private CartService cartService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        //token不存在
        if (StringUtils.isBlank(token)) {
            response.sendRedirect(SSO_URL+"/page/login?redirect="+request.getRequestURL());
            return false;
        }
        //token过期
        String userJson = tokenService.getUserByToken(token);
        if(userJson.isEmpty()){
            response.sendRedirect(SSO_URL+"/page/login?redirect="+request.getRequestURL());
            return false;
        }
        //登陆状态
        //User user= JsonUtils.jsonToPojo(userJson,User.class);
        User user= new Gson().fromJson(userJson,User.class);
        request.setAttribute("user", user);
        String jsonCartList = CookieUtils.getCookieValue(request, "cart",true);
        if(StringUtils.isNotBlank(jsonCartList)){
            //合并购物车
            cartService.mergeCart(user.getId(), JsonUtils.jsonToList(jsonCartList, Item.class));
        }
        return true;
    }

}
