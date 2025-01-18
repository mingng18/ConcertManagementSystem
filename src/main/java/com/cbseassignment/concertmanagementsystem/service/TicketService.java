package com.cbseassignment.concertmanagementsystem.service;
import com.cbseassignment.concertmanagementsystem.model.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    List<TicketDTO> getAllTickets();

    TicketDTO getTicket(Long ticketId);

    List<TicketDTO> getTicketsByConcertId(Long concertId);

    void addTicket(TicketDTO newTicketDTO);

    TicketDTO updateTicket(Long ticketId, TicketDTO updatedTicketDTO);

    void deleteTicket(Long ticketId);
}
