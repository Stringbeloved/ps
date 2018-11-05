package com.sziit.service;

import com.github.pagehelper.PageInfo;
import com.sziit.pojo.Content;

public interface ContentService {

    int add(Content content);

    PageInfo<Content> list(long categoryId , int page , int rows);

    int edit(Content content);

    int delete(String ids);


   // List<Content> getContentByCategoryId(long categoryId);
    String getContentByCategoryId(long categoryId);
}
