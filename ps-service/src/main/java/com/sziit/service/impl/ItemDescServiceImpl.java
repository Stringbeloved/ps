package com.sziit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sziit.mapper.ItemDescMapper;
import com.sziit.pojo.ItemDesc;
import com.sziit.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public ItemDesc findDescById(long id) {
        return itemDescMapper.selectByPrimaryKey(id);
    }
}

