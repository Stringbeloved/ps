package com.sziit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sziit.mapper.ItemMapper;
import com.sziit.pojo.Item;
import com.sziit.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.service.impl
 *  @文件名:   CartServiceImpl
 *  @创建者:   daizengyi9.0
 *  @创建时间:  2018/12/19 22:03
 *  @描述：    TODO
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public int addCart(long userId, long itemId, int num) {
        Boolean hexists=redisTemplate.opsForHash().hasKey("CART_PRE:"+userId,String.valueOf(itemId));
        if(hexists){
            Object obj = redisTemplate.opsForHash().get("CART_PRE:" + userId, String.valueOf(itemId));
            System.out.println("obj:"+obj);
            Item item=(Item) obj;
            item.setNum(item.getNum()+num);
            System.out.println("item:"+item);
            redisTemplate.opsForHash().put("CART_PRE:"+userId,itemId+"", new Gson().toJson(item));
            return 0;
        }
        Item item = itemMapper.selectByPrimaryKey(itemId);
        item.setNum(num);
        String image = item.getImage();
        if(StringUtils.isNotBlank(image)){
            item.setImage(image.split(",")[0]);
        }
        redisTemplate.opsForHash().put("CART_PRE:"+userId,itemId+"", new Gson().toJson(item));
        return 0;
    }

    @Override
    public int mergeCart(long userId, List<Item> itemList) {
        for (Item tbItem : itemList) {
            addCart(userId, tbItem.getId(), tbItem.getNum());
        }
        return 0;
    }

    /*@Override
    public int addCart(long userId, long itemId, int num) {
        //查要添加的商品数据
        Item item = itemService.findItemById(itemId);

        //查redis，遍历购物车，判断是否有重复数据，进而修改数量
        List<Cart> cartList=queryCartByUserId(userId);

        //遍历购物车
        Cart c=null;
        for (Cart cart:cartList){
            if (itemId==cart.getItemId()) {
                c = cart;
                break;
            }
        }
        if (c!=null){
            //有重复的商品
            c.setNum(c.getNum()+num);
        }else {
            Cart cart=new Cart();
            cart.setItemId(itemId);
            cart.setNum(num);
            cart.setItemTitle(item.getTitle());
            cart.setItemPrice(item.getPrice());
            cart.setItemImage(item.getImages()[0]);
            cart.setCreate(new Date());;
            cart.setUpdate(new Date());
            cartList.add(cart);
        }

        //存redis
        String json=new Gson().toJson(cartList);
        redisTemplate.opsForValue().set("CART_PRE:"+userId,json);
        return 0;
    }*/

    @Override
    public List<Item> getCartList(long userId) {
        List<Object>  objList= redisTemplate.opsForHash().values("CART_PRE:" + userId);
        List<Item> itemList=new ArrayList<>();
        for (Object obj : objList) {
            Item item=new Gson().fromJson(obj.toString(),Item.class);
            itemList.add(item);
        }
        return itemList;
    }

    /*@Override
    public List<Cart> queryCartByUserId(long userId) {
        String json=redisTemplate.opsForValue().get("CART_PRE:"+userId);
        List<Cart> list = new Gson().fromJson(json, new TypeToken<List<Item>>() {
        }.getType());
        return list;
    }*/

    @Override
    public int updateCartNum(long userId, long itemId, int num) {
        Item item=(Item)redisTemplate.opsForHash().get("CART_PRE:" + userId, itemId);
        item.setNum(num);
        redisTemplate.opsForHash().put("CART_PRE:"+userId,itemId+"", new Gson().toJson(item));
        return 0;
    }

    @Override
    public int deleteCartItem(long userId, long itemId) {
        redisTemplate.opsForHash().delete("CART_PRE:" + userId, itemId);
        return 0;
    }

    @Override
    public int clearCartItem(long userId) {
        redisTemplate.delete("CART_PRE:" + userId);
        return 0;
    }
}
