package com.cbseassignment.concertmanagementsystem.model.dto;

public class OrderTicketDTO {
    private Long ticketId;
    private Integer quantity;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
