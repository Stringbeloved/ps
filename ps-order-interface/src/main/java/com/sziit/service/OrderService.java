package com.sziit.service;

import com.github.pagehelper.PageInfo;
import com.sziit.pojo.Order;
import com.sziit.pojo.OrderInfo;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.service
 *  @文件名:   OrderService
 *  @创建者:   daizengyi9.0
 *  @创建时间:  2018/12/21 14:36
 *  @描述：    TODO
 */
public interface OrderService {

    String createOrder(OrderInfo orderInfo);

    PageInfo<Order> getOrderList(int page, int rows);
}
