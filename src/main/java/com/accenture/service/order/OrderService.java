package com.accenture.service.order;

import com.accenture.entity.orders.Orders;
import com.accenture.entity.tag.Tag;
import com.accenture.enums.Status;
import com.accenture.repository.orders.OrdersRepo;
import com.accenture.repository.tag.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrdersRepo ordersRepo;

    public Orders changeOrderStatusToClosed(Long orderId) {
        Orders orders = ordersRepo.findById(orderId).get();
        orders.setStatus(Status.CLOSED);
        orders.setUpdatedAt(new Date());

        return ordersRepo.save(orders);
    }

    public Orders changeOrderStatusToInProgress(Long orderId) {
        Orders orders = ordersRepo.findById(orderId).get();
        orders.setStatus(Status.IN_PROGRESS);
        orders.setUpdatedAt(new Date());

        return ordersRepo.save(orders);
    }

}
