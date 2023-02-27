package com.lee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.dto.SetmealDto;
import com.lee.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);
}
