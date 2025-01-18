package com.cbseassignment.concertmanagementsystem.mapper;

import com.cbseassignment.concertmanagementsystem.model.dto.TicketDTO;
import com.cbseassignment.concertmanagementsystem.model.entity.Ticket;

public class TicketMapper {
    public static TicketDTO toDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setConcertId(ticket.getConcert().getId());
        dto.setName(ticket.getName());
        dto.setPrice(ticket.getPrice());
        dto.setStockQuantity(ticket.getStockQuantity());
        return dto;
    }

    public static Ticket toEntity(TicketDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setId(dto.getId());
        ticket.setName(dto.getName());
        ticket.setPrice(dto.getPrice());
        ticket.setStockQuantity(dto.getStockQuantity());
        return ticket;
    }
}