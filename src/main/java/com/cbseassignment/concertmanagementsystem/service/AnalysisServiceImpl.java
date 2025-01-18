package com.cbseassignment.concertmanagementsystem.service;

import com.cbseassignment.concertmanagementsystem.model.dto.TopEarningConcertAnalysisDTO;
import com.cbseassignment.concertmanagementsystem.model.dto.TopSaleArtistDTO;
import com.cbseassignment.concertmanagementsystem.model.dto.TopSaleConcertAnalysisDTO;
import com.cbseassignment.concertmanagementsystem.model.entity.Artist;
import com.cbseassignment.concertmanagementsystem.model.entity.Concert;
import com.cbseassignment.concertmanagementsystem.model.entity.Order;
import com.cbseassignment.concertmanagementsystem.model.entity.Ticket;
import com.cbseassignment.concertmanagementsystem.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {

    private final OrderRepo orderRepository;

    @Override
    public TopSaleConcertAnalysisDTO assembleTopSaleConcert() {
        List<Order> allOrders = orderRepository.findAll();
        Map<Long, Integer> concertSalesCount = new HashMap<>();

        // Count sales for each concert
        for (Order order : allOrders) {
            for (Ticket ticket : order.getTicketQuantities().keySet()) {
                Concert concert = ticket.getConcert();
                concertSalesCount.merge(concert.getId(), order.getTicketQuantities().get(ticket), Integer::sum);
            }
        }

        // Find concert with maximum sales
        Map.Entry<Long, Integer> topSale = concertSalesCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        if (topSale == null) {
            return new TopSaleConcertAnalysisDTO(null, 0);
        }

        return new TopSaleConcertAnalysisDTO(topSale.getKey(), topSale.getValue());
    }

    @Override
    public TopEarningConcertAnalysisDTO assembleTopEarningConcert() {
        List<Order> allOrders = orderRepository.findAll();
        Map<Long, BigDecimal> concertEarnings = new HashMap<Long, BigDecimal>();

        // Calculate earnings for each concert
        for (Order order : allOrders) {
            for (Ticket ticket : order.getTicketQuantities().keySet()) {
                Concert concert = ticket.getConcert();
                concertEarnings.merge(concert.getId(), order.getTotalPrice(), BigDecimal::add);
            }
        }

        // Find concert with maximum earnings
        Map.Entry<Long, BigDecimal> topEarning = concertEarnings.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        if (topEarning == null) {
            return new TopEarningConcertAnalysisDTO(null, BigDecimal.ZERO);
        }

        return new TopEarningConcertAnalysisDTO(topEarning.getKey(), topEarning.getValue());
    }

    @Override
    public TopSaleArtistDTO assembleTopSaleArtist() {
        List<Order> allOrders = orderRepository.findAll();
        Map<Long, Integer> artistSalesCount = new HashMap<>();

        // Count sales for each artist
        for (Order order : allOrders) {
            for (Ticket ticket : order.getTicketQuantities().keySet()) {
                Concert concert = ticket.getConcert();
                Artist artist = concert.getArtist();
                artistSalesCount.merge(artist.getId(), order.getTicketQuantities().get(ticket), Integer::sum);
            }
        }

        // Find artist with maximum sales
        Map.Entry<Long, Integer> topSale = artistSalesCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        if (topSale == null) {
            return new TopSaleArtistDTO(null, 0);
        }

        return new TopSaleArtistDTO(topSale.getKey(), topSale.getValue());
    }
}
