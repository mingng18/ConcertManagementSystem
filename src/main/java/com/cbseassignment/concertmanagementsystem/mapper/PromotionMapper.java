package com.cbseassignment.concertmanagementsystem.mapper;

import com.cbseassignment.concertmanagementsystem.model.dto.PromotionDTO;
import com.cbseassignment.concertmanagementsystem.model.entity.Promotion;

public class PromotionMapper {

    public static PromotionDTO toDTO(Promotion promotion) {
        PromotionDTO dto = new PromotionDTO();
        dto.setId(promotion.getId());
        dto.setPromoCode(promotion.getPromoCode());
        dto.setName(promotion.getName());
        dto.setMinimumTotalPrice(promotion.getMinimumTotalPrice());
        dto.setMinimumTicketAmount(promotion.getMinimumTicketAmount());
        dto.setDiscountRatePercentage(promotion.getDiscountRatePercentage());
        dto.setEffectiveStartTime(promotion.getEffectiveStartTime());
        dto.setEffectiveEndTime(promotion.getEffectiveEndTime());
        return dto;
    }

    public static Promotion toEntity(PromotionDTO promotionDTO) {
        Promotion promotion = new Promotion();
        promotion.setId(promotionDTO.getId());
        promotion.setPromoCode(promotionDTO.getPromoCode());
        promotion.setName(promotionDTO.getName());
        promotion.setMinimumTotalPrice(promotionDTO.getMinimumTotalPrice());
        promotion.setMinimumTicketAmount(promotionDTO.getMinimumTicketAmount());
        promotion.setDiscountRatePercentage(promotionDTO.getDiscountRatePercentage());
        promotion.setEffectiveStartTime(promotionDTO.getEffectiveStartTime());
        promotion.setEffectiveEndTime(promotionDTO.getEffectiveEndTime());
        return promotion;
    }
}
