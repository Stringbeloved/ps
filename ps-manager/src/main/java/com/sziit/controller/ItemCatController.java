package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sziit.pojo.ItemCat;
import com.sziit.service.ItemCatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.controller
 *  @文件名:   ItemCatController
 *  @创建者:   dzy
 *  @创建时间:  2018/11/8 18:50
 *  @描述：    TODO
 */
@Controller
public class ItemCatController {

	@Reference
	private ItemCatService itemCatService;

	@RequestMapping("/rest/item/cat")
	@ResponseBody
	public List<ItemCat> getCategoryByParentId(@RequestParam(defaultValue = "0") int id ){

		//默认先获取所有的一级分类
		// int parentId = 0 ;

		List<ItemCat> list = itemCatService.getCategoryByParentId(id);


		return list;
	}

	//宜立方版
//	@RequestMapping("/item/cat/list")
//	@ResponseBody
//	public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id",defaultValue="0")long parentId){
//		List<EasyUITreeNode> list = itemCatService.getItemCatList(parentId);
//		return list;
//	}
	
}
