package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.sziit.pojo.Item;
import com.sziit.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    //因为页面在定义的时候， 文档在编写的时候，有意的迎合了 一种风格 RestFul 设计风格
    /*

        javaweb 里面有增删改查， 实际上页面和controller对接，也就是crud操作。
        平常我们接触的都是get &  post操作。实际上http的常用的请求方式有好几种，
        其中有4种正好吻合了增删改查。

        GET ---> 查询
        POST ---> 添加
        DELETE ---> 删除
        PUT ----> 更新
     */

    //添加商品  --- POST
    @RequestMapping(value = "/rest/item" , method = RequestMethod.POST)
    public String add(Item item , String desc){
        itemService.add(item ,desc);
        return "success";
    }


    //查询商品列表  -- GET
    //@GetMapping
    @RequestMapping(value = "/rest/item" , method = RequestMethod.GET)
    public Map<String  , Object> list(int page , int rows){
        PageInfo<Item> pageInfo = itemService.list(page, rows);
        //就差把数据返回给页面了。 easyui 的数据网格显示数据，有固定的格式要求。
        //必须有两个属性，total  & rows  : total 表示总记录数， rows : 表示
        //这一页的集合数据 list集合
        Map<String  , Object> map = new HashMap<String ,Object>();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return map;

    }




    //宜立方版
//    @RequestMapping("/item/{itemId}")
//    public Item getItemById(@PathVariable Long itemId){
//        Item item=itemService.getItemById(itemId);
//        return item;
//    }
//
//    @RequestMapping("/item/list")
//    public EasyUIDataGridResult getItemList(Integer page, Integer rows){
//        EasyUIDataGridResult result=itemService.getItemList(page, rows);
//        return result;
//    }
//
//    @RequestMapping(value="/item/save")
//    public E3Result addItem(Item item, String desc){
//        E3Result result = itemService.addItem(item, desc);
//        return result;
//    }
}

