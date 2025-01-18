package com.cbseassignment.concertmanagementsystem.service;

import com.cbseassignment.concertmanagementsystem.mapper.PromotionMapper;
import com.cbseassignment.concertmanagementsystem.model.dto.PromotionDTO;
import com.cbseassignment.concertmanagementsystem.model.entity.Promotion;
import com.cbseassignment.concertmanagementsystem.repository.PromotionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepo promotionRepo;

    @Override
    public PromotionDTO getPromotionById(Long id) {
        Promotion promotion = promotionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found with ID: " + id));
        return PromotionMapper.toDTO(promotion);
    }

    @Override
    public PromotionDTO getPromotionByPromoCode(String promoCode) {
        Promotion promotion = promotionRepo.findByPromoCode(promoCode)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found with promo code: " + promoCode));
        return PromotionMapper.toDTO(promotion);
    }

    @Override
    public List<PromotionDTO> getAllPromotions() {
        return promotionRepo.findAll().stream()
                .map(PromotionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void createPromotion(PromotionDTO promotionDTO) {
        Promotion promotion = PromotionMapper.toEntity(promotionDTO);
        promotionRepo.save(promotion);
    }

    @Override
    public PromotionDTO updatePromotion(Long id, PromotionDTO promotionDTO) {
        Promotion existingPromotion = promotionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found with ID: " + id));
        Promotion updatedPromotion = PromotionMapper.toEntity(promotionDTO);
        updatedPromotion.setId(existingPromotion.getId());
        return PromotionMapper.toDTO(promotionRepo.save(updatedPromotion));
    }

    @Override
    public void deletePromotion(Long id) {
        if (!promotionRepo.existsById(id)) {
            throw new ResourceNotFoundException("Promotion not found with ID: " + id);
        }
        promotionRepo.deleteById(id);
    }
}
