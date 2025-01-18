package com.cbseassignment.concertmanagementsystem.repository;

import com.cbseassignment.concertmanagementsystem.model.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConcertRepo extends JpaRepository<Concert, Long>, JpaSpecificationExecutor<Concert> {
    @Query("SELECT c FROM Concert c WHERE " +
            "(:artistName IS NULL OR c.artist.name = :artistName) AND " +
            "(:genre IS NULL OR c.genre = :genre) AND " +
            "(:start IS NULL OR c.startDateTime >= :start)")
    List<Concert> findByFilters(@Param("artistName") String artistName,
                                @Param("genre") String genre,
                                @Param("start") LocalDateTime start
    );
}
