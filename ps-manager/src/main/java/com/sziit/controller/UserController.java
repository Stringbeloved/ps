package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sziit.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *  @项目名：  ps-parent 
 *  @包名：    com.sziit.controller
 *  @文件名:   UserController
 *  @创建者:   dzy
 *  @创建时间:  2018/10/15 9:26
 *  @描述：    TODO
 */
//@RestController
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("save")
    public String  save(){
        //System.out.println("调用了UserController的save方法~！·");
        userService.save();
        return "save scuccess~!~!";
    }



}
