package com.cbseassignment.concertmanagementsystem.controller;

import com.cbseassignment.concertmanagementsystem.model.dto.PromotionDTO;
import com.cbseassignment.concertmanagementsystem.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/{id}")
    public ResponseEntity<PromotionDTO> getPromotionById(@PathVariable Long id) {
        PromotionDTO promotion = promotionService.getPromotionById(id);
        return ResponseEntity.ok(promotion);
    }

    @GetMapping("/code/{promoCode}")
    public ResponseEntity<PromotionDTO> getPromotionByPromoCode(@PathVariable String promoCode) {
        PromotionDTO promotion = promotionService.getPromotionByPromoCode(promoCode);
        return ResponseEntity.ok(promotion);
    }

    @GetMapping
    public ResponseEntity<List<PromotionDTO>> getAllPromotions() {
        List<PromotionDTO> promotions = promotionService.getAllPromotions();
        return ResponseEntity.ok(promotions);
    }

    @PostMapping
    public ResponseEntity<Void> createPromotion(@RequestBody PromotionDTO promotionDTO) {
        promotionService.createPromotion(promotionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromotionDTO> updatePromotion(@PathVariable Long id, @RequestBody PromotionDTO promotionDTO) {
        PromotionDTO updatedPromotion = promotionService.updatePromotion(id, promotionDTO);
        return ResponseEntity.ok(updatedPromotion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.noContent().build();
    }
}
