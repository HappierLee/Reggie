package com.lee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.entity.Orders;

public interface OrderService extends IService<Orders> {
    public void submit(Orders orders);
}
