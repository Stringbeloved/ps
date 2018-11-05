package com.sziit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sziit.mapper.ItemCatMapper;
import com.sziit.mapper.ItemDescMapper;
import com.sziit.mapper.ItemMapper;
import com.sziit.pojo.EasyUIDataGridResult;
import com.sziit.pojo.Item;
import com.sziit.pojo.ItemDesc;
import com.sziit.service.ItemService;
import com.sziit.utils.E3Result;
import com.sziit.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/*
 *  @项目名：  ps-parent 
 *  @包名：    com.sziit.service.impl
 *  @文件名:   ItemServiceImpl
 *  @创建者:   dzy
 *  @创建时间:  2018/10/15 20:57
 *  @描述：    TODO
 */
@Service
public class ItemServiceImpl implements ItemService {

    //@Autowired
   // private UserDao userDao;

    @Autowired
   private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public Item getItemById(long itemId) {
//
//        //根据主键查询
//        //TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
//        TbItemExample example = new TbItemExample();
//        Criteria criteria = example.createCriteria();
//        //设置查询条件
//        criteria.andIdEqualTo(itemId);
//        //执行查询
//        List<TbItem> list = itemMapper.selectByExample(example);
//        if (list != null && list.size() > 0) {
//            //把结果添加到缓存
//            try {
//                jedisClient.set(REDIS_ITEM_PRE + ":" + itemId + ":BASE", JsonUtils.objectToJson(list.get(0)));
//                //设置过期时间
//                jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":BASE", ITEM_CACHE_EXPIRE);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return list.get(0);
//        }
        return null;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        //执行查询
        List<Item> list = itemMapper.selectAll();
        //创建一个返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        //取分页结果
        PageInfo<Item> pageInfo = new PageInfo<>(list);
        //取总记录数
        long total = pageInfo.getTotal();
        result.setTotal(total);
        return result;
    }

    @Override
    public E3Result addItem(Item item, String desc) {
        //生成商品id
        final long itemId = IDUtils.genItemId();
        //补全item的属性
        item.setId(itemId);
        //1-正常，2-下架，3-删除
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //向商品表插入数据
        itemMapper.insert(item);
        //创建一个商品描述表对应的pojo对象。
        ItemDesc itemDesc = new ItemDesc();
        //补全属性
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        //向商品描述表插入数据
        itemDescMapper.insert(itemDesc);
        //发送商品添加消息
//        jmsTemplate.send(topicDestination, new MessageCreator() {
//
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage textMessage = session.createTextMessage(itemId + "");
//                return textMessage;
//            }
//        });

        //返回成功
        return E3Result.ok();
    }

//    @Override
//    public ItemDesc getItemDescById(long itemId) {
//        //查询缓存
//        try {
//            String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":DESC");
//            if(StringUtils.isNotBlank(json)) {
//                TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
//                return tbItemDesc;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
//        //把结果添加到缓存
//        try {
//            jedisClient.set(REDIS_ITEM_PRE + ":" + itemId + ":DESC", JsonUtils.objectToJson(itemDesc));
//            //设置过期时间
//            jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":DESC", ITEM_CACHE_EXPIRE);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return itemDesc;
//    }

}
