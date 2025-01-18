package com.cbseassignment.concertmanagementsystem.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TopEarningConcertAnalysisDTO {
    private Long concertId;
    private BigDecimal totalEarnings;

    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(BigDecimal totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

}
