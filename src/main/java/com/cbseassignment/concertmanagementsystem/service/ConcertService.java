package com.cbseassignment.concertmanagementsystem.service;

import com.cbseassignment.concertmanagementsystem.model.dto.ConcertDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ConcertService {
    ConcertDTO getConcert(Long concertId);

    List<ConcertDTO> getConcertList(String artist_name, String genre, LocalDateTime start, LocalDateTime end);

    void addConcert(ConcertDTO newConcertDTO);

    ConcertDTO updateConcert(Long concertId, ConcertDTO updateConcertDTO);

    void deleteConcert(Long concertId);
}