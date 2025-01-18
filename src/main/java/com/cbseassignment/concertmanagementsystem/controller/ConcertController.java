package com.cbseassignment.concertmanagementsystem.controller;

import com.cbseassignment.concertmanagementsystem.model.dto.ConcertDTO;
import com.cbseassignment.concertmanagementsystem.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/concerts")
public class ConcertController {
    @Autowired
    ConcertService concertService;

    // Get a concert by ID
    @GetMapping(value = "/{concertId}")
    public ResponseEntity<ConcertDTO> getConcertById(@PathVariable Long concertId) {
        ConcertDTO concert = concertService.getConcert(concertId);
        return new ResponseEntity<>(concert, HttpStatus.OK);
    }

    // Get a list of concerts based on filters
    @GetMapping
    public ResponseEntity<List<ConcertDTO>> getConcertList(
            @RequestParam(required = false) String artist_name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end) {
        List<ConcertDTO> concerts = concertService.getConcertList(artist_name, genre, start, end);
        return new ResponseEntity<>(concerts, HttpStatus.OK);
    }

    // Add a new concert
    @PostMapping
    public ResponseEntity<Void> addConcert(@RequestBody ConcertDTO newConcertDTO) {
        concertService.addConcert(newConcertDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update an existing concert
    @PutMapping(value = "/{concertId}")
    public ResponseEntity<ConcertDTO> updateConcert(@PathVariable Long concertId, @RequestBody ConcertDTO updateConcertDTO) {
        ConcertDTO updatedConcert = concertService.updateConcert(concertId, updateConcertDTO);
        updateConcertDTO.setId(concertId);
        return new ResponseEntity<>(updatedConcert, HttpStatus.OK);
    }

    // Delete a concert by ID
    @DeleteMapping(value = "/{concertId}")
    public ResponseEntity<Void> deleteConcert(@PathVariable Long concertId) {
        concertService.deleteConcert(concertId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
