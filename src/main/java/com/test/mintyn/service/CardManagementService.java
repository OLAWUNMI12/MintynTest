package com.test.mintyn.service;


import com.test.mintyn.dto.response.BinListAPIResponse;
import com.test.mintyn.dto.response.CardResponse;
import com.test.mintyn.dto.response.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.rmi.ServerException;
import java.util.Objects;

@Service
@Slf4j
public class CardManagementService {

    private final WebClient webClient;
    private final String binListAPIUrl;
    private final CardStatisticService cardStatisticService;


    public CardManagementService(WebClient webClient,
                                 @Value("${binList.api.url}") String binListAPIUrl,
                                 CardStatisticService cardStatisticService) {
        this.webClient = webClient;
        this.binListAPIUrl = binListAPIUrl;
        this.cardStatisticService = cardStatisticService;
    }

    public CardResponse verifyCardScheme(String bin) throws ServerException {
        log.info("Verifying card scheme {}", bin);
        CardResponse cardResponse = null;

        BinListAPIResponse binListAPIResponse = null;

        try {
            binListAPIResponse = webClient.get().uri(binListAPIUrl + bin).retrieve().bodyToMono(BinListAPIResponse.class).block();
        } catch (Exception e) {
            throw new ServerException("Internal server error");
        }

        if (Objects.nonNull(binListAPIResponse)) {
            log.info("Successfully retrieved card scheme {}", bin);
            cardResponse = new CardResponse();
            cardResponse.setSuccess(true);
            Payload payload = new Payload();
            payload.setScheme(binListAPIResponse.getScheme());
            payload.setType(binListAPIResponse.getType());
            if (Objects.nonNull(binListAPIResponse.getBank())) {
                payload.setBank(binListAPIResponse.getBank().getName());
            }
            cardResponse.setPayload(payload);
            cardStatisticService.updateCardStatistics(bin);
        }
        return cardResponse;
    }


}
