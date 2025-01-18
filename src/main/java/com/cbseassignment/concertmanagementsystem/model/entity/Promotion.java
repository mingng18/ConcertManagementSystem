package com.cbseassignment.concertmanagementsystem.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String promoCode;

    private String name;

    private BigDecimal minimumTotalPrice;

    private Integer minimumTicketAmount;

    private Integer discountRatePercentage;

    private LocalDateTime effectiveStartTime;

    private LocalDateTime effectiveEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMinimumTotalPrice() {
        return minimumTotalPrice;
    }

    public void setMinimumTotalPrice(BigDecimal minimumTotalPrice) {
        this.minimumTotalPrice = minimumTotalPrice;
    }

    public Integer getMinimumTicketAmount() {
        return minimumTicketAmount;
    }

    public void setMinimumTicketAmount(Integer minimumTicketAmount) {
        this.minimumTicketAmount = minimumTicketAmount;
    }

    public Integer getDiscountRatePercentage() {
        return discountRatePercentage;
    }

    public void setDiscountRatePercentage(Integer discountRatePercentage) {
        this.discountRatePercentage = discountRatePercentage;
    }

    public LocalDateTime getEffectiveStartTime() {
        return effectiveStartTime;
    }

    public void setEffectiveStartTime(LocalDateTime effectiveStartTime) {
        this.effectiveStartTime = effectiveStartTime;
    }

    public LocalDateTime getEffectiveEndTime() {
        return effectiveEndTime;
    }

    public void setEffectiveEndTime(LocalDateTime effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
    }
}
