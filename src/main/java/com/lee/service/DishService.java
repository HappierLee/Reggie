package com.lee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.dto.DishDto;
import com.lee.entity.Dish;

public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);
}
