package com.sziit.service;

import com.sziit.pojo.EasyUITreeNode;

import java.util.List;


public interface ItemCatService {

	List<EasyUITreeNode> getItemCatList(long parentId);
	
}
