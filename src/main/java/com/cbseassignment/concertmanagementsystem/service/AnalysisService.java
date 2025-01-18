package com.cbseassignment.concertmanagementsystem.service;

import com.cbseassignment.concertmanagementsystem.model.dto.TopEarningConcertAnalysisDTO;
import com.cbseassignment.concertmanagementsystem.model.dto.TopSaleArtistDTO;
import com.cbseassignment.concertmanagementsystem.model.dto.TopSaleConcertAnalysisDTO;

public interface AnalysisService {

    public TopSaleConcertAnalysisDTO assembleTopSaleConcert();

    public TopEarningConcertAnalysisDTO assembleTopEarningConcert();

    public TopSaleArtistDTO assembleTopSaleArtist();
}
