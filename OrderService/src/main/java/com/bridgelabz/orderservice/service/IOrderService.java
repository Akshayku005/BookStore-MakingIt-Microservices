package com.bridgelabz.orderservice.service;

import com.bridgelabz.orderservice.dto.OrderDTO;
import com.bridgelabz.orderservice.model.Order;

import java.util.List;

public interface IOrderService {

    Order insertOrder(OrderDTO dto);

    Order getByID(Integer orderId);

    List<Order> getAll();

    Order updateById(Integer orderId,OrderDTO dto);

    Order deleteById(Integer orderId);

}