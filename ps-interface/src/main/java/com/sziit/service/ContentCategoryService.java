package com.sziit.service;

import com.sziit.pojo.ContentCategory;

import java.util.List;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.service
 *  @文件名:   ContentCategoryService
 *  @创建者:   dzy
 *  @创建时间:  2018/10/15 8:51
 *  @描述：    TODO
 */
public interface ContentCategoryService {

    List<ContentCategory> list(long parentId);

    ContentCategory add(String name, long parentId);

    int update(ContentCategory contentCategory);

    int delete(ContentCategory contentCategory);
}
