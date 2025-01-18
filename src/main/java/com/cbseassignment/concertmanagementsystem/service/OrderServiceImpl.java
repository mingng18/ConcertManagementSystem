package com.cbseassignment.concertmanagementsystem.service;

import com.cbseassignment.concertmanagementsystem.mapper.OrderMapper;
import com.cbseassignment.concertmanagementsystem.model.dto.OrderDTO;
import com.cbseassignment.concertmanagementsystem.model.dto.OrderTicketDTO;
import com.cbseassignment.concertmanagementsystem.model.entity.Order;
import com.cbseassignment.concertmanagementsystem.model.entity.Promotion;
import com.cbseassignment.concertmanagementsystem.model.entity.Ticket;
import com.cbseassignment.concertmanagementsystem.model.entity.User;
import com.cbseassignment.concertmanagementsystem.repository.OrderRepo;
import com.cbseassignment.concertmanagementsystem.repository.PromotionRepo;
import com.cbseassignment.concertmanagementsystem.repository.TicketRepo;
import com.cbseassignment.concertmanagementsystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PromotionRepo promotionRepo;

    @Autowired
    private TicketRepo ticketRepo;

    @Override
    public OrderDTO getOrderById(Long orderId) {
        return orderRepo.findById(orderId)
                .map(OrderMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return orderRepo.findByUserId(userId).stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepo.findAll().stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addOrder(OrderDTO orderDTO) {
        User user = userRepo.findById(orderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + orderDTO.getUserId()));

        Map<Ticket, Integer> ticketQuantities = new HashMap<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderTicketDTO ticketDTO : orderDTO.getOrderTicketDTOList()) {
            Ticket ticket = ticketRepo.findById(ticketDTO.getTicketId())
                    .orElseThrow(() -> new IllegalArgumentException("Ticket not found with ID: " + ticketDTO.getTicketId()));

            if (ticket.getStockQuantity() < ticketDTO.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for ticket ID: " + ticketDTO.getTicketId());
            }

            ticket.setStockQuantity(ticket.getStockQuantity() - ticketDTO.getQuantity());
            ticketRepo.save(ticket);

            totalPrice = totalPrice.add(ticket.getPrice().multiply(new BigDecimal(ticketDTO.getQuantity())));

            ticketQuantities.put(ticket, ticketDTO.getQuantity());
        }

        List<Promotion> promotions = promotionRepo.findAllById(orderDTO.getPromotionIds());
        promotions.sort((a, b) -> a.getDiscountRatePercentage().compareTo(b.getDiscountRatePercentage()));
        for (Promotion promotion : promotions) {
            BigDecimal discountPrice = totalPrice.multiply(new BigDecimal(promotion.getDiscountRatePercentage()));
            totalPrice = totalPrice.subtract(discountPrice);
        }

        Order order = OrderMapper.toEntity(orderDTO, user, promotions, ticketQuantities);
        order.setTotalPrice(totalPrice);
        orderRepo.save(order);
    }

    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        Order existingOrder = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        User user = userRepo.findById(orderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + orderDTO.getUserId()));

        if(!orderDTO.getOrderTicketDTOList().isEmpty()){
            throw new IllegalArgumentException("Not allowed to update order ticket once order is submitted");
        }


        BigDecimal newTotalPrice = BigDecimal.ZERO;
        for ( Map.Entry<Ticket,Integer> entry : existingOrder.getTicketQuantities().entrySet()){
            Ticket ticket = entry.getKey();
            Integer quantity = entry.getValue();
            newTotalPrice = newTotalPrice.add(ticket.getPrice().multiply(new BigDecimal(quantity)));
        }

        List<Promotion> promotions = promotionRepo.findAllById(orderDTO.getPromotionIds());
        promotions.sort((a, b) -> a.getDiscountRatePercentage().compareTo(b.getDiscountRatePercentage()));
        for (Promotion promotion : promotions) {
            BigDecimal discountPrice = newTotalPrice.multiply(new BigDecimal(promotion.getDiscountRatePercentage()));
            newTotalPrice = newTotalPrice.subtract(discountPrice);
        }

        existingOrder.setUser(user);
        existingOrder.setPromotions(promotions);
        existingOrder.setTicketQuantities(existingOrder.getTicketQuantities());
        existingOrder.setStatus(orderDTO.getStatus());
        existingOrder.setCreatedAt(orderDTO.getCreatedAt());
        existingOrder.setTotalPrice(newTotalPrice);

        Order updatedOrder = orderRepo.save(existingOrder);
        return OrderMapper.toDTO(updatedOrder);
    }

    @Override
    public void deleteOrder(Long orderId) {
        if (!orderRepo.existsById(orderId)) {
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }
        orderRepo.deleteById(orderId);
    }
}
