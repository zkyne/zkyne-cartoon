package com.zkyne.zkynecartoon.service;

import com.zkyne.zkynecartoon.entity.Category;

import java.util.List;

/**
 * @ClassName: ICategoryService
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/19 15:57
 */
public interface ICategoryService {
    /**
     * 查询所有分类
     * @return
     */
    List<Category> findAll();
}
