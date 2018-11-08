package com.sziit.service;

import com.github.pagehelper.PageInfo;
import com.sziit.pojo.Item;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.service
 *  @文件名:   ItemService
 *  @创建者:   dzy
 *  @创建时间:  2018/10/8 9:08
 *  @描述：    商品接口
 */
public interface ItemService {


	/**
	 * 添加商品
	 * @param item 商品基本信息
	 * @param desc 商品的描述
	 * @return
	 */
	int add(Item item ,String desc);

	//既然是分页查询，返回list集合是不够的，因为list集合仅仅只能体现这一页的数据集合
	//还有些东西没法体现：
	//List<Item> list(int page , int rows);

	PageInfo<Item> list(int page , int rows);

	Item findItemById(long id);

	int deleteItem(long id);

	int updateItem(Item item);

	//宜立方版
	//Item getItemById(long itemId);
	//EasyUIDataGridResult getItemList(int page, int rows);
	//E3Result addItem(Item item, String desc);
	//ItemDesc getItemDescById(long itemId);
}

