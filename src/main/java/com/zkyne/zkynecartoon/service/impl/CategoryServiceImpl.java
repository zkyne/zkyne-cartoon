package com.zkyne.zkynecartoon.service.impl;

import com.zkyne.zkynecartoon.entity.Category;
import com.zkyne.zkynecartoon.repository.CategoryRepository;
import com.zkyne.zkynecartoon.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CategoryServiceImpl
 * @Description:
 * @Author: zhangkunjsww
 * @Date: 2019/4/19 15:57
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
