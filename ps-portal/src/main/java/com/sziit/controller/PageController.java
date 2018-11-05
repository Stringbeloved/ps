package com.sziit.controller;

import com.sziit.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    //@Reference
    private ContentService contentService;

    @RequestMapping("/")
    public String index(Model model){

        System.out.println("要跳转首页了~！");
        long categoryId = 89;
        //String json = contentService.getContentByCategoryId(categoryId);
        //model.addAttribute("data",json);
        return "index";
    }
}
