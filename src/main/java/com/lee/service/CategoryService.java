package com.lee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
