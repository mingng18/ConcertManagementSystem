package com.cbseassignment.concertmanagementsystem.controller;

import com.cbseassignment.concertmanagementsystem.model.dto.TopEarningConcertAnalysisDTO;
import com.cbseassignment.concertmanagementsystem.model.dto.TopSaleArtistDTO;
import com.cbseassignment.concertmanagementsystem.model.dto.TopSaleConcertAnalysisDTO;
import com.cbseassignment.concertmanagementsystem.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    @GetMapping
    public ResponseEntity<TopSaleConcertAnalysisDTO> analyseTopSaleConcert() {
        TopSaleConcertAnalysisDTO topSaleAnalysisDTO = analysisService.assembleTopSaleConcert();
        return new ResponseEntity<>(topSaleAnalysisDTO, HttpStatus.OK);
    }

    @GetMapping("/top-earning")
    public ResponseEntity<TopEarningConcertAnalysisDTO> analyseTopEarningConcert() {
        TopEarningConcertAnalysisDTO topEarningAnalysisDTO = analysisService.assembleTopEarningConcert();
        return new ResponseEntity<>(topEarningAnalysisDTO, HttpStatus.OK);
    }

    @GetMapping("/top-sale-artist")
    public ResponseEntity<TopSaleArtistDTO> analyseTopSaleArtist() {
        TopSaleArtistDTO topSaleAnalysisDTO = analysisService.assembleTopSaleArtist();
        return new ResponseEntity<>(topSaleAnalysisDTO, HttpStatus.OK);
    }
}
