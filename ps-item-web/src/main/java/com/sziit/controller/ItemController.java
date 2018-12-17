package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sziit.pojo.Item;
import com.sziit.pojo.ItemDesc;
import com.sziit.service.ItemDescService;
import com.sziit.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.controller
 *  @文件名:   ItemController
 *  @创建者:   daizengyi9.0
 *  @创建时间:  2018/12/17 21:41
 *  @描述：    TODO
 */
@Controller
public class ItemController {

    @Reference
    private ItemService itemService;

    @Reference
    private ItemDescService itemDescService;


    @RequestMapping("/item/{id}.html")
    public String item(@PathVariable long id , Model model){

        //1. 获取商品数据
        Item item = itemService.findItemById(id);

        //2. 查询商品的描述信息
        ItemDesc itemDesc = itemDescService.findDescById(id);

        //3. 存储商品的数据
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);

        return "introduction";
    }
}
