package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sziit.pojo.Item;
import com.sziit.pojo.OrderInfo;
import com.sziit.pojo.User;
import com.sziit.service.CartService;
import com.sziit.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.controller
 *  @文件名:   OrderController
 *  @创建者:   daizengyi9.0
 *  @创建时间:  2018/12/21 15:34
 *  @描述：    TODO
 */
@Controller
public class OrderController {

    @Reference
    private CartService cartService;

    @Reference
    private OrderService orderService;


    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        List<Item> cartList = cartService.getCartList(user.getId());
        if (cartList.isEmpty()){
            return "redirect:http:localhost:8082";
        }
        request.setAttribute("cartList", cartList);
        return "order-cart";
    }

    @RequestMapping(value="/order/create",method= RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo, HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getAttribute("user");
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUsername());
        String result = orderService.createOrder(orderInfo);
        if (result==null) {
            return "redirect:http://localhost:8082";
        }
        else {
            cartService.clearCartItem(user.getId());
        }
        request.setAttribute("orderId", result);
        request.setAttribute("payment", orderInfo.getPayment());
        return "success";
    }
}
