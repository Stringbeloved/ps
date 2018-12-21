package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sziit.pojo.Item;
import com.sziit.pojo.User;
import com.sziit.service.CartService;
import com.sziit.service.ItemService;
import com.sziit.utils.CookieUtils;
import com.sziit.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.controller
 *  @文件名:   CartController
 *  @创建者:   daizengyi9.0
 *  @创建时间:  2018/12/19 22:53
 *  @描述：    TODO
 */
@Controller
public class CartController {

    @Reference
    private CartService cartService;

    @Reference
    private ItemService itemService;

    private final Integer COOKIE_CART_EXPIRE=432000;

    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
                          HttpServletRequest request, HttpServletResponse response) {
        // 判断用户是否登录
        // 如果使用是登录状态，把购物车写入redis
        User user = (User) request.getAttribute("user");
        if (user != null) {
            cartService.addCart(user.getId(), itemId, num);
            return "cartSuccess";
        }

        // 如果未登录状态，把购物车写进cookie
        List<Item> cartList = getCartListFromCookie(request);
        boolean flag = false;
        for (Item tbItem : cartList) {
            if (tbItem.getId() == itemId.longValue()) {
                flag = true;
                tbItem.setNum(tbItem.getNum() + num);
                break;
            }
        }
        if (!flag) {
            Item tbItem = itemService.findItemById(itemId);
            tbItem.setNum(num);
            String image = tbItem.getImage();
            if (StringUtils.isNotBlank(image)) {
                tbItem.setImage(image.split(",")[0]);
            }
            cartList.add(tbItem);
        }
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRE, true);
        return "cartSuccess";
    }

    @RequestMapping("/cart/cart")
    public String showCartList(HttpServletRequest request, HttpServletResponse response) {
        // 从cookie中取购物车列表
        List<Item> cartList = getCartListFromCookie(request);
        User user = (User) request.getAttribute("user");
        // 登录状态
        if (user != null) {
            // 从cookie中取购物车列表
            // 如果不为空，把cookie中的购物车商品和服务端的商品合并
            cartService.mergeCart(user.getId(), cartList);
            // 把cookie中的购物车删除
            CookieUtils.deleteCookie(request, response, "cart");
            // 从服务端取购物车列表
            cartList = cartService.getCartList(user.getId());
        }
        // 未登录状态
        request.setAttribute("cartList", cartList);
        return "shopcart";
    }

    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public int updateCartNum(@PathVariable Long itemId, @PathVariable Integer num, HttpServletRequest request,
                                  HttpServletResponse response) {
        // 判断用户是否为登录状态
        User user = (User) request.getAttribute("user");
        if (user != null) {
            cartService.updateCartNum(user.getId(), itemId, num);
            return 200;
        }
        List<Item> cartList = getCartListFromCookie(request);
        for (Item tbItem : cartList) {
            if (tbItem.getId() == itemId.longValue()) {
                tbItem.setNum(num);
                break;
            }
        }
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRE, true);
        return 200;
    }

    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("------------------"+itemId);
        // 判断用户是否为登录状态
        User user = (User) request.getAttribute("user");
        if (user != null) {
            cartService.deleteCartItem(user.getId(), itemId);
            return "redirect:/cart/cart.html";
        }
        List<Item> cartList = getCartListFromCookie(request);
        for (Item tbItem : cartList) {
            if (tbItem.getId() == itemId.longValue()) {
                cartList.remove(tbItem);
                break;
            }
        }
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRE, true);
        return "redirect:/cart/cart.html";
    }

    private List<Item> getCartListFromCookie(HttpServletRequest request) {
        String json = CookieUtils.getCookieValue(request, "cart", true);
        if (StringUtils.isBlank(json)) {
            return new ArrayList<>();
        }
        List<Item> list = JsonUtils.jsonToList(json, Item.class);
        return list;
    }

}
