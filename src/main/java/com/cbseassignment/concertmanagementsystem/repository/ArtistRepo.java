package com.cbseassignment.concertmanagementsystem.repository;

import com.cbseassignment.concertmanagementsystem.model.entity.Artist;
import com.cbseassignment.concertmanagementsystem.model.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long>, JpaSpecificationExecutor<Artist> {
    List<Artist> findByGender(String gender);
}
