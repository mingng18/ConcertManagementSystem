package com.cbseassignment.concertmanagementsystem.service;

import com.cbseassignment.concertmanagementsystem.model.entity.Artist;
import com.cbseassignment.concertmanagementsystem.repository.ArtistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepo artistRepo;

    @Override
    public Artist getArtist(Long artistId) {
        return artistRepo.findById(artistId)
                .orElseThrow(() -> new ResourceNotFoundException("Artist id " + artistId + " not found"));
    }

    @Override
    public List<Artist> getArtistList(String gender) {
        if (gender != null && !gender.isEmpty()) {
            return artistRepo.findByGender(gender);
        }
        return getAllArtists();
    }

    @Override
    public void addArtist(Artist newArtist) {
        artistRepo.save(newArtist);
    }

    @Override
    public Artist updateArtist(Long artistId, Artist updatedArtist) {
        Artist existingArtist = getArtist(artistId);
        existingArtist.setName(updatedArtist.getName());
        existingArtist.setGender(updatedArtist.getGender());
        return artistRepo.save(existingArtist);
    }

    @Override
    public void deleteArtist(Long artistId) {
        if (!artistRepo.existsById(artistId)) {
            throw new ResourceNotFoundException("Artist id " + artistId + " not found");
        }
        artistRepo.deleteById(artistId);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepo.findAll();
    }
}
