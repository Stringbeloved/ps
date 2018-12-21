package com.sziit.service;

import com.sziit.pojo.ItemCat;

import java.util.List;


public interface ItemCatService {

	//宜立方版
	//List<EasyUITreeNode> getItemCatList(long parentId);

	List<ItemCat> getCategoryByParentId(int parentId);
	
}
