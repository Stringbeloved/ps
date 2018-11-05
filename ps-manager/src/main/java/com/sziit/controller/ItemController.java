package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sziit.pojo.EasyUIDataGridResult;
import com.sziit.pojo.Item;
import com.sziit.service.ItemService;
import com.sziit.utils.E3Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *  @项目名：  ps-parent 
 *  @包名：    com.sziit.controller
 *  @文件名:   ItemController
 *  @创建者:   dzy
 *  @创建时间:  2018/10/15 21:39
 *  @描述：    TODO
 */
@RestController
public class ItemController {

    @Reference
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public Item getItemById(@PathVariable Long itemId){
        Item item=itemService.getItemById(itemId);
        return item;
    }

    @RequestMapping("/item/list")
    public EasyUIDataGridResult getItemList(Integer page, Integer rows){
        EasyUIDataGridResult result=itemService.getItemList(page, rows);
        return result;
    }

    @RequestMapping(value="/item/save")
    public E3Result addItem(Item item, String desc){
        E3Result result = itemService.addItem(item, desc);
        return result;
    }
}

