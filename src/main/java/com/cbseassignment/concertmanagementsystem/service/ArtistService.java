package com.cbseassignment.concertmanagementsystem.service;

import com.cbseassignment.concertmanagementsystem.model.dto.ConcertDTO;
import com.cbseassignment.concertmanagementsystem.model.entity.Artist;

import java.time.LocalDateTime;
import java.util.List;

public interface ArtistService {
    Artist getArtist(Long concertId);

    List<Artist> getArtistList(String gender);

    void addArtist(Artist newArtist);

    Artist updateArtist(Long artistId, Artist updatedArtist);

    void deleteArtist(Long artistId);
}
