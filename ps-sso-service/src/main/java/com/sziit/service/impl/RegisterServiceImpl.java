package com.sziit.service.impl;

import com.sziit.mapper.UserMapper;
import com.sziit.pojo.User;
import com.sziit.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;


@Service
public class RegisterServiceImpl implements RegisterService {


	@Autowired
	private UserMapper userMapper;

	@Override
	public Boolean checkData(String param, int type) {
		User user = new User();

		switch (type) {
			case 1://校验用户名
				user.setUsername(param);
				break;
			case 2://校验电话
				user.setPhone(param);
				break;
			case 3://校验邮箱
				user.setEmail(param);
				break;
			default: //默认是校验用户名
				user.setUsername(param);
				break;
		}
		user = userMapper.selectOne(user);
		//返回true 表示可以使用。 返回false：表示不能使用
		//return user !=null ? false : true ;
		return user == null;
	}

	@Override
	public int register(User user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		String newPassword= DigestUtils.md5DigestAsHex( user.getPassword().getBytes());
		user.setPassword(newPassword);
		return userMapper.insert(user);
	}
}
