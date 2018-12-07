package com.accenture.repository.orders;

import com.accenture.entity.orders.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepo extends CrudRepository<Orders, Long> {

    List<Orders> findByTag(String tag);
}