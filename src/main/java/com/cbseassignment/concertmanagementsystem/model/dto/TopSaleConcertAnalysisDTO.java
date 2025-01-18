package com.cbseassignment.concertmanagementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopSaleConcertAnalysisDTO {
    private Long concertId;
    private Integer totalSales;
}
