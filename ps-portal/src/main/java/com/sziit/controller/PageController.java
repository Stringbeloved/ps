package com.sziit.controller;

import com.sziit.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.controller
 *  @文件名:   PageController
 *  @创建者:   dzy
 *  @创建时间:  2018/11/7 17:05
 *  @描述：    TODO
 */
@Controller
public class PageController {

    //@Reference
    private ContentService contentService;

    @RequestMapping("/")
    public String index(Model model){

        //System.out.println("要跳转首页了~！");
        long categoryId = 89;
        //String json = contentService.getContentByCategoryId(categoryId);
        //model.addAttribute("data",json);
        return "index";
    }

    @RequestMapping("/{page}")
    public String showPage(String page){
        return page;
    }
}
