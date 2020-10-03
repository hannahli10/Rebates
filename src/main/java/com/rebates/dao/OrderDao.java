package com.rebates.dao;

import com.rebates.model.Order;
import com.rebates.model.Rebate;

import java.util.List;

public interface OrderDao {
    Order save (Order order, Rebate rebate);
    Order save (Order order);
    Order update (Order order);
    boolean delete (Order order);
    List<Order> getOrders();
    Order getOrderById(Long id);

}
