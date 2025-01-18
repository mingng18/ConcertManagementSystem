package com.cbseassignment.concertmanagementsystem.mapper;

import com.cbseassignment.concertmanagementsystem.model.dto.ConcertDTO;
import com.cbseassignment.concertmanagementsystem.model.entity.Artist;
import com.cbseassignment.concertmanagementsystem.model.entity.Concert;
import com.cbseassignment.concertmanagementsystem.repository.ArtistRepo;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class ConcertMapper {


    // Convert Concert entity to ConcertDTO
    public static ConcertDTO toDTO(Concert concert) {
        if (concert == null) {
            return null;
        }

        ConcertDTO concertDTO = new ConcertDTO();
        concertDTO.setId(concert.getId());
        concertDTO.setArtist_id(concert.getArtist().getId());
        concertDTO.setTitle(concert.getTitle());
        concertDTO.setGenre(concert.getGenre());
        concertDTO.setStartDateTime(concert.getStartDateTime());
        concertDTO.setLocation(concert.getLocation());
        return concertDTO;
    }

    // Convert ConcertDTO to Concert entity
    public static Concert toEntity(ConcertDTO concertDTO) {
        if (concertDTO == null) {
            return null;
        }
        Concert concert = new Concert();
        concert.setId(concertDTO.getId());
        concert.setTitle(concertDTO.getTitle());
        concert.setGenre(concertDTO.getGenre());
        concert.setStartDateTime(concertDTO.getStartDateTime());
        concert.setLocation(concertDTO.getLocation());
        return concert;
    }
}
