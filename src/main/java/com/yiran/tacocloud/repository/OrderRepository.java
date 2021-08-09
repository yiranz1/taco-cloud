package com.yiran.tacocloud.repository;

import com.yiran.tacocloud.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
