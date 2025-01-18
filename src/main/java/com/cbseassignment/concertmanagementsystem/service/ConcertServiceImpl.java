package com.cbseassignment.concertmanagementsystem.service_impl;

import com.cbseassignment.concertmanagementsystem.service.ConcertService;
import com.cbseassignment.concertmanagementsystem.mapper.ConcertMapper;
import com.cbseassignment.concertmanagementsystem.model.dto.ConcertDTO;
import com.cbseassignment.concertmanagementsystem.model.entity.Artist;
import com.cbseassignment.concertmanagementsystem.model.entity.Concert;
import com.cbseassignment.concertmanagementsystem.repository.ArtistRepo;
import com.cbseassignment.concertmanagementsystem.repository.ConcertRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertServiceImpl implements ConcertService {
    @Autowired
    private ConcertRepo concertRepository;

    @Autowired
    private ArtistRepo artistRepository;

    @Override
    public ConcertDTO getConcert(Long concertId) {
        return concertRepository.findById(concertId)
                .map(ConcertMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Concert not found with ID: " + concertId));
    }

    @Override
    public List<ConcertDTO> getConcertList(String artist_name, String genre, LocalDateTime start, LocalDateTime end) {
        return concertRepository.findByFilters(artist_name, genre, start).stream()
                .map(ConcertMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addConcert(ConcertDTO newConcertDTO) {
        Concert concert = ConcertMapper.toEntity(newConcertDTO);
        concertRepository.save(concert);
    }

    @Override
    public ConcertDTO updateBook(Long concertId, ConcertDTO updateConcertDTO) {
        Concert existingConcert = concertRepository.findById(concertId)
                .orElseThrow(() -> new ResourceNotFoundException("Concert not found with ID: " + concertId));

        Artist artist = artistRepository.findById(updateConcertDTO.getArtist_id()).orElseThrow();

        existingConcert.setArtist(artist);
        existingConcert.setGenre(updateConcertDTO.getGenre());
        existingConcert.setStartDateTime(updateConcertDTO.getStartDateTime());
        existingConcert.setLocation(updateConcertDTO.getLocation());

        Concert updatedConcert = concertRepository.save(existingConcert);
        return ConcertMapper.toDTO(updatedConcert);
    }

    @Override
    public void deleteBook(Long concertId) {
        if (!concertRepository.existsById(concertId)) {
            throw new ResourceNotFoundException("Concert not found with ID: " + concertId);
        }
        concertRepository.deleteById(concertId);
    }
}
