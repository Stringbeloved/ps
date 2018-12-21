package com.sziit.service.impl;

import com.google.gson.Gson;
import com.sziit.mapper.UserMapper;
import com.sziit.pojo.User;
import com.sziit.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.service.impl
 *  @文件名:   LoginServiceImpl
 *  @创建者:   daizengyi9.0
 *  @创建时间:  2018/12/19 15:16
 *  @描述：    TODO
 */
@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String userLogin(User user) {
        //对密码进行MD5加密
        String newPassword= DigestUtils.md5DigestAsHex( user.getPassword().getBytes());
        user.setPassword(newPassword);

        //根据账号和密码查询用户
        user = userMapper.selectOne(user);

        String key = null;

        //判定登录是否成功，如果成功，那么需要保存到redis
        if(user !=null ){
            String json = new Gson().toJson(user);
            key= "SESSION:"+ UUID.randomUUID().toString();
            //把用户数据保存到redis里面去。
            redisTemplate.opsForValue().set(key,json);
        }
        return key;
    }
}
