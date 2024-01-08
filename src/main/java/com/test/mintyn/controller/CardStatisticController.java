package com.test.mintyn.controller;

import com.test.mintyn.dto.response.CardStatisticResponse;
import com.test.mintyn.service.CardStatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/card-scheme")
public class CardStatisticController {

    private final CardStatisticService cardStatisticService;

    public CardStatisticController(CardStatisticService cardStatisticService) {
        this.cardStatisticService = cardStatisticService;
    }

    @GetMapping("/stats")
    public ResponseEntity<CardStatisticResponse> retrieveCardStatistics(@RequestParam int start, @RequestParam int limit) {
        CardStatisticResponse response = new CardStatisticResponse();
        response.setStart(start);
        response.setLimit(limit);
        response = cardStatisticService.fetchData(response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
