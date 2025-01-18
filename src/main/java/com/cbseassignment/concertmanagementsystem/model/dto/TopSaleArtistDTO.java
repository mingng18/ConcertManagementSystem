package com.cbseassignment.concertmanagementsystem.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TopSaleArtistDTO {
    private Long artistId;
    private Integer totalSales;

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

}
