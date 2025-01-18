package com.cbseassignment.concertmanagementsystem.service;

import com.cbseassignment.concertmanagementsystem.model.dto.PromotionDTO;

import java.util.List;

public interface PromotionService {
    PromotionDTO getPromotionById(Long id);

    PromotionDTO getPromotionByPromoCode(String promoCode);

    List<PromotionDTO> getAllPromotions();

    void createPromotion(PromotionDTO promotionDTO);

    PromotionDTO updatePromotion(Long id, PromotionDTO promotionDTO);

    void deletePromotion(Long id);
}
