package com.test.mintyn.service;

import com.test.mintyn.dto.response.CardStatisticResponse;
import com.test.mintyn.model.CardStatistic;
import com.test.mintyn.repository.CardStatisticsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class CardStatisticService {

    private final CardStatisticsRepository cardStatisticsRepository;

    public CardStatisticService(CardStatisticsRepository cardStatisticsRepository) {
        this.cardStatisticsRepository = cardStatisticsRepository;
    }

    @Async
    public void updateCardStatistics(String bin) {
        log.info("Updating card statistic {}", bin);
        CardStatistic statistics = cardStatisticsRepository.findByBin(bin);
        if (Objects.nonNull(statistics)) {
            long requests = statistics.getRequests();
            statistics.setRequests(++requests);
            cardStatisticsRepository.save(statistics);
        } else {
            statistics = new CardStatistic();
            statistics.setBin(bin);
            statistics.setRequests(1L);
            cardStatisticsRepository.save(statistics);
        }
        log.info("Successfully updated card statistic {}", bin);
    }

    public CardStatisticResponse fetchData(CardStatisticResponse response) {
        Pageable pageable = PageRequest.of(response.getStart(), response.getLimit());
        List<CardStatistic> statistics = cardStatisticsRepository.findAll(pageable).stream().toList();
        long size = cardStatisticsRepository.count();
        response.setSize(size);
        Map<String, String> data = new HashMap<>();
        if (!CollectionUtils.isEmpty(statistics)) {
            for (CardStatistic statistic : statistics) {
                data.put(statistic.getBin(), String.valueOf(statistic.getRequests()));
            }
            response.setSuccess(true);
            response.setPayload(data);
        }
        return response;
    }

}
