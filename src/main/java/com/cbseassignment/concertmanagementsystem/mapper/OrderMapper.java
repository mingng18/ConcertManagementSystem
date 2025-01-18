package com.cbseassignment.concertmanagementsystem.mapper;

import com.cbseassignment.concertmanagementsystem.model.dto.OrderDTO;
import com.cbseassignment.concertmanagementsystem.model.dto.OrderTicketDTO;
import com.cbseassignment.concertmanagementsystem.model.entity.Order;
import com.cbseassignment.concertmanagementsystem.model.entity.Promotion;
import com.cbseassignment.concertmanagementsystem.model.entity.Ticket;
import com.cbseassignment.concertmanagementsystem.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderDTO toDTO(Order order) {
        if (order == null)
            return null;

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setPromotionIds(order.getPromotions().stream().map(Promotion::getId).collect(Collectors.toList()));
        orderDTO.setOrderTicketDTOList(order.getTicketQuantities().entrySet().stream()
                .map(entry -> {
                    OrderTicketDTO dto = new OrderTicketDTO();
                    dto.setTicketId(entry.getKey().getId());
                    dto.setQuantity(entry.getValue());
                    return dto;
                })
                .collect(Collectors.toList()));
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setCreatedAt(order.getCreatedAt());
        return orderDTO;
    }

    public static Order toEntity(OrderDTO orderDTO, User user, List<Promotion> promotions,
            Map<Ticket, Integer> ticketQuantities) {
        if (orderDTO == null)
            return null;

        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setUser(user);
        order.setPromotions(promotions);
        order.setTicketQuantities(ticketQuantities);
        order.setStatus(orderDTO.getStatus());
        order.setCreatedAt(orderDTO.getCreatedAt());
        return order;
    }
}
