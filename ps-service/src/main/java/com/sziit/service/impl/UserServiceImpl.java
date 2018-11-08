package com.sziit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sziit.service.UserService;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.impl
 *  @文件名:   UserServiceImpl
 *  @创建者:   dzy
 *  @创建时间:  2018/9/10 16:05
 *  @描述：    TODO
 *
 *
 *  com
 *     itheima
 *         mapper
 *              ItemMapper
 *         service
 *             impl
 *                 UserServiceImpl --> yinru  ItemMapper
 *
 *
 *             @SpringBootApplication
 *             ServiceApp
 *
 *             会检查者这个serviceapp所在包以及它的这个包的子包 有没有哪一个类接口被打上了注解
 *             @Service @Mapper 如果有，就让spring管理起来。
 *
 *
 *
 */
public class UserServiceImpl implements UserService {

    @Override
    public void save() {
        //System.out.println("save()...");
    }
}
