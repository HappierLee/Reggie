package com.lee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.common.CustomException;
import com.lee.entity.Category;
import com.lee.entity.Dish;
import com.lee.entity.Setmeal;
import com.lee.mapper.CategoryMapper;
import com.lee.service.CategoryService;
import com.lee.service.DishService;
import com.lee.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    public void remove(Long id){
        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper();
        dishQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(dishQueryWrapper);

        if(count1 > 0){
            throw new CustomException("当前分类下关联了菜品，不能删除！");
        }

        LambdaQueryWrapper<Setmeal> setmealQueryWrapper = new LambdaQueryWrapper();
        setmealQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(setmealQueryWrapper);

        if(count2 > 0){
            throw new CustomException("当前分类下关联了套餐，不能删除！");
        }

        super.removeById(id);
    }
}
