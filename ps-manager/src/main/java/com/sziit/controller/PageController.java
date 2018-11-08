package com.sziit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  ps-parent 
 *  @包名：    com.sziit.controller
 *  @文件名:   PageController
 *  @创建者:   dzy
 *  @创建时间:  2018/10/15 10:43
 *  @描述：    TODO
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String index(){
        //System.out.println("跳转首页");
        return "index";
    }


//    @RequestMapping("/{page}")
//    public String showPage(String page){
//        return page;
//    }

    @RequestMapping("/rest/page/{pageName}")
    public String page(@PathVariable String pageName){

        //System.out.println("pageName=" + pageName);

        return pageName;
    }

}
