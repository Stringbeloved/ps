package com.sziit.service;

import com.sziit.pojo.Item;

import java.util.List;


public interface CartService {

	int addCart(long userId, long itemId, int num);
	int mergeCart(long userId, List<Item> itemList);
	List<Item> getCartList(long userId);
	int updateCartNum(long userId, long itemId, int num);
	int deleteCartItem(long userId, long itemId);
	int clearCartItem(long userId);
}
