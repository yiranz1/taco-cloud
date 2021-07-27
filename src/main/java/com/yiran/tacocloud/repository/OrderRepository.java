package com.yiran.tacocloud.repository;

import com.yiran.tacocloud.models.Order;

public interface OrderRepository {
    Order save(Order order);
}
