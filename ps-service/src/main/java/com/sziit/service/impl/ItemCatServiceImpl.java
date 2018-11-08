package com.sziit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sziit.mapper.ItemCatMapper;
import com.sziit.pojo.ItemCat;
import com.sziit.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.service.impl
 *  @文件名:   ItemCatServiceImpl
 *  @创建者:   dzy
 *  @创建时间:  2018/11/7 17:01
 *  @描述：    TODO
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public List<ItemCat> getCategoryByParentId(int parentId) {
		//按照普通列来查询。
		ItemCat itemCat = new ItemCat();
		Long id = Long.parseLong(parentId+"");
		itemCat.setParentId(id);
		//selectByExample 里面使用的是criteria 查询条件对象。
		List<ItemCat> list = itemCatMapper.select(itemCat);
       /* Example example = new Example(ItemCat.class);
        example.createCriteria().andEqualTo("username","zhangsan");
        itemCatMapper.selectByExample(example);*/
		//想按照学生的姓名来查询
        /*Student  stu = new Student();
        stu.setName("张三")
        itemCatMapper.select(Student);*/
		return list;
	}


	//宜立方版
//	@Override
//	public List<EasyUITreeNode> getItemCatList(long parentId) {
////		ItemCatExample example=new ItemCatExample();
////		Criteria criteria = example.createCriteria();
////		criteria.andParentIdEqualTo(parentId);
////		List<ItemCat> list=itemCatMapper.selectByExample(example);
//		//按照普通列来查询。
//		ItemCat itemCat = new ItemCat();
//		Long id = Long.parseLong(parentId+"");
//
//		//long val = Long.parseLong(parentId+"");
//		itemCat.setParentId(id);
//		//selectByExample 里面使用的是criteria 查询条件对象。
//		List<ItemCat> list = itemCatMapper.select(itemCat);
//
//		List<EasyUITreeNode> resultlist=new ArrayList<EasyUITreeNode>();
//		for (ItemCat tbItemCat : list) {
//			EasyUITreeNode node=new EasyUITreeNode();
//			node.setId(tbItemCat.getId());
//			node.setText(tbItemCat.getName());
//			node.setState(tbItemCat.isParent()?"closed":"open");
//			resultlist.add(node);
//		}
//		return resultlist;
//	}

}
