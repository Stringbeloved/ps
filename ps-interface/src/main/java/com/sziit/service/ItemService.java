package com.sziit.service;

import com.sziit.pojo.EasyUIDataGridResult;
import com.sziit.pojo.Item;
import com.sziit.utils.E3Result;


public interface ItemService {
	Item getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
	E3Result addItem(Item item, String desc);
	//ItemDesc getItemDescById(long itemId);
}

