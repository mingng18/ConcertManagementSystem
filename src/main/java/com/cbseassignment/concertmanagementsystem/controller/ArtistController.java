package com.cbseassignment.concertmanagementsystem.controller;

import com.cbseassignment.concertmanagementsystem.model.entity.Artist;
import com.cbseassignment.concertmanagementsystem.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping(value = "/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long artistId) {
        Artist artist = artistService.getArtist(artistId);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists(@RequestParam(required = false) String gender) {
        List<Artist> artists;
        if (gender != null && !gender.isEmpty()) {
            artists = artistService.getArtistList(gender);
        } else {
            artists = artistService.getAllArtists();
        }
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        artistService.addArtist(artist);
        return new ResponseEntity<>(artist, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{artistId}")
    public ResponseEntity<Artist> deleteArtist(@PathVariable Long artistId) {
        artistService.deleteArtist(artistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{artistId}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long artistId, @RequestBody Artist artist) {
        artistService.updateArtist(artistId, artist);
        artist.setId(artistId);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }
}
