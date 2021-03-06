package com.sziit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.sziit.pojo.Item;
import com.sziit.service.ItemService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   IndexController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/11/19 10:20
 *  @描述：    TODO
 */

@RestController
public class IndexController {


    @Autowired
    private SolrClient solrClient;

    @Reference
    private ItemService itemService;

    @RequestMapping("initIndex")
    public String initIndex() throws Exception {

            int page = 1, rows = 500;

        do {
            List docList = new ArrayList();

            PageInfo<Item> pageInfo = itemService.list(page, rows);

            //得到了那500条数据 500 -500  -----300;
            List<Item> list = pageInfo.getList();

            for (Item item : list) {

                //遍历一件商品，就得到一个solr的文档对象
                //一个文档对象就是一条索引数据。有500件商品就有500个索引数据，也就是有500个文档对象
                SolrInputDocument document=new SolrInputDocument();
                document.addField("id", item.getId());
                document.addField("item_title", item.getTitle());
                document.addField("item_sell_point", item.getCid());
                document.addField("item_price", item.getPrice());
                document.addField("item_image", item.getImage());
                document.addField("item_category_name", item.getStatus());


                docList.add(document);

            }

            solrClient.add(docList);

            //提交
            solrClient.commit();

            page++;
            rows = list.size();
        }while(rows == 500);


        return "success";

    }
}
