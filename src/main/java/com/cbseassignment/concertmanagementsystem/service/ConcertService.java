package com.cbseassignment.concertmanagementsystem.facade;

import com.cbseassignment.concertmanagementsystem.model.dto.ConcertDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ConcertService {
    ConcertDTO getConcert(Long concertId);

    List<ConcertDTO> getConcertList(String artist_name, String genre, LocalDateTime start, LocalDateTime end);

    void addConcert(ConcertDTO newConcertDTO);

    ConcertDTO updateBook(Long concertId, ConcertDTO updateConcertDTO);

    void deleteBook(Long concertId);
}