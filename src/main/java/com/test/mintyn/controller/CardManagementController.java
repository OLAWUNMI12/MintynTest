package com.test.mintyn.controller;

import com.test.mintyn.dto.response.CardResponse;
import com.test.mintyn.dto.response.Payload;
import com.test.mintyn.exception.CardNotFoundException;
import com.test.mintyn.exception.EmptyBinException;
import com.test.mintyn.service.CardManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.ServerException;
import java.util.Objects;


@RestController
@RequestMapping("/api/v1/card-scheme")
public class CardManagementController {

    private final CardManagementService cardManagementService;

    public CardManagementController(CardManagementService cardManagementService) {
        this.cardManagementService = cardManagementService;
    }

    @GetMapping("/verify/{bin}")
    public ResponseEntity<CardResponse> verifyCardDetails(@PathVariable("bin") String bin) throws ServerException {
        CardResponse cardResponse = new CardResponse();
        cardResponse.setPayload(new Payload());
        if (StringUtils.hasText(bin)) {
            cardResponse = cardManagementService.verifyCardScheme(bin);
            if (Objects.nonNull(cardResponse)) {
                return ResponseEntity.status(HttpStatus.OK).body(cardResponse);
            }
            throw new CardNotFoundException("BIN does not exist");
        } else {
            throw new EmptyBinException("BIN cannot be empty");

        }
    }
}
