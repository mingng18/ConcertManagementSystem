package com.cbseassignment.concertmanagementsystem.controller;

import com.cbseassignment.concertmanagementsystem.model.dto.TicketDTO;
import com.cbseassignment.concertmanagementsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> ticketDTOList = ticketService.getAllTickets();
        return new ResponseEntity<>(ticketDTOList, HttpStatus.OK);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long ticketId) {
        TicketDTO ticket = ticketService.getTicket(ticketId);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/concert/{concertId}")
    public ResponseEntity<List<TicketDTO>> getTicketsByConcertId(@PathVariable Long concertId) {
        List<TicketDTO> tickets = ticketService.getTicketsByConcertId(concertId);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    public ResponseEntity<Void> addTicket(@RequestBody TicketDTO newTicketDTO) {
        ticketService.addTicket(newTicketDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long ticketId, @RequestBody TicketDTO updatedTicketDTO) {
        TicketDTO updatedTicket = ticketService.updateTicket(ticketId, updatedTicketDTO);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
}
