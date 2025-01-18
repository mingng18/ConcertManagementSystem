package com.cbseassignment.concertmanagementsystem.service;

import com.cbseassignment.concertmanagementsystem.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO getOrderById(Long orderId);

    List<OrderDTO> getOrdersByUserId(Long userId);

    List<OrderDTO> getAllOrders();

    void addOrder(OrderDTO orderDTO);

    OrderDTO updateOrder(Long orderId, OrderDTO orderDTO);

    void deleteOrder(Long orderId);
}
