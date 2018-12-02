package com.accenture.repository;

import com.accenture.dao.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepo extends CrudRepository<Orders, Integer> {

    List<Orders> findByTag(String tag);
}