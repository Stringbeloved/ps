package com.sziit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.sziit.mapper.OrderItemMapper;
import com.sziit.mapper.OrderMapper;
import com.sziit.mapper.OrderShippingMapper;
import com.sziit.pojo.Order;
import com.sziit.pojo.OrderInfo;
import com.sziit.pojo.OrderItem;
import com.sziit.pojo.OrderShipping;
import com.sziit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.service.impl
 *  @文件名:   OrderServiceImpl
 *  @创建者:   daizengyi9.0
 *  @创建时间:  2018/12/21 15:28
 *  @描述：    TODO
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderShippingMapper orderShippingMapper;

    @Override
    public String createOrder(OrderInfo orderInfo) {
        String time = new Date().getTime() + "";
        String number = Math.random() * 10 + "";
        String orderId = number.substring(2, 7) + time.substring(7);
        // 插入订单表
        orderInfo.setOrderId(orderId);
        // 1未付款2已付款3未发货4已发货5交易成功6交易关闭
        orderInfo.setStatus(1);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderMapper.insert(orderInfo);
        // 插入明细表
        List<OrderItem> orderItems = orderInfo.getOrderItems();
        if (orderItems == null || orderItems.size() == 0) {
            return null;
        }
        for (OrderItem tbOrderItem : orderItems) {
            // 生成明细ID
            // String odId =
            // jedisClient.incr(ORDER_DETAIL_ID_GEN_KEY).toString();
            time = new Date().getTime() + "";
            number = Math.random() + "";
            String odId = time.substring(6) + number.substring(2, 7);
            tbOrderItem.setId(odId);
            tbOrderItem.setOrderId(orderId);
            orderItemMapper.insert(tbOrderItem);
        }
        // 插入物流表
        OrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        orderShippingMapper.insert(orderShipping);
        return orderId;
    }

    @Override
    public PageInfo<Order> getOrderList(int page, int rows) {
        return null;
    }
}
