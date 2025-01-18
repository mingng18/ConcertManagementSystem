package com.cbseassignment.concertmanagementsystem.service;

import com.cbseassignment.concertmanagementsystem.mapper.TicketMapper;
import com.cbseassignment.concertmanagementsystem.model.dto.TicketDTO;
import com.cbseassignment.concertmanagementsystem.model.entity.Concert;
import com.cbseassignment.concertmanagementsystem.model.entity.Ticket;
import com.cbseassignment.concertmanagementsystem.repository.ConcertRepo;
import com.cbseassignment.concertmanagementsystem.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private ConcertRepo concertRepo;

    @Override
    public List<TicketDTO> getAllTickets() {
        return ticketRepo.findAll().stream().map(TicketMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TicketDTO getTicket(Long ticketId) {
        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with ID: " + ticketId));
        return TicketMapper.toDTO(ticket);
    }

    @Override
    public List<TicketDTO> getTicketsByConcertId(Long concertId) {
        return ticketRepo.findByConcertId(concertId).stream()
                .map(TicketMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addTicket(TicketDTO newTicketDTO) {
        Concert concert = concertRepo.findById(newTicketDTO.getConcertId())
                .orElseThrow(() -> new ResourceNotFoundException("Concert not found with ID: " + newTicketDTO.getConcertId()));

        Ticket ticket = TicketMapper.toEntity(newTicketDTO);
        ticket.setConcert(concert);
        ticketRepo.save(ticket);
    }

    @Override
    public TicketDTO updateTicket(Long ticketId, TicketDTO updatedTicketDTO) {
        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with ID: " + ticketId));

        ticket.setName(updatedTicketDTO.getName());
        ticket.setPrice(updatedTicketDTO.getPrice());
        ticket.setStockQuantity(updatedTicketDTO.getStockQuantity());

        Ticket updatedTicket = ticketRepo.save(ticket);
        return TicketMapper.toDTO(updatedTicket);
    }

    @Override
    public void deleteTicket(Long ticketId) {
        if (!ticketRepo.existsById(ticketId)) {
            throw new ResourceNotFoundException("Ticket not found with ID: " + ticketId);
        }
        ticketRepo.deleteById(ticketId);
    }
}
