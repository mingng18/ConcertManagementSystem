package com.cbseassignment.concertmanagementsystem.repository;

import com.cbseassignment.concertmanagementsystem.model.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionRepo extends JpaRepository<Promotion, Long>, JpaSpecificationExecutor<Promotion> {
    Optional<Promotion> findByPromoCode(String promoCode);
}
