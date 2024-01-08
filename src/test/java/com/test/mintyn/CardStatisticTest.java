package com.test.mintyn;

import com.test.mintyn.dto.response.CardStatisticResponse;
import com.test.mintyn.model.CardStatistic;
import com.test.mintyn.repository.CardStatisticsRepository;
import com.test.mintyn.service.CardStatisticService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CardStatisticTest {

    List<CardStatistic> cardStatistics;
    @SpyBean
    private CardStatisticService cardStatisticService;
    @MockBean
    private CardStatisticsRepository cardStatisticsRepository;

    @BeforeAll
    public void init() {
        cardStatistics = new ArrayList<>();
        CardStatistic cardStatistic = new CardStatistic(1, "12345", 6);
        cardStatistics.add(cardStatistic);
        CardStatistic cardStatistic2 = new CardStatistic(2, "67890", 9);
        cardStatistics.add(cardStatistic2);
    }


    @Test
    public void cardStatisticsRetrievalValidityTest() {
        Mockito.when(cardStatisticsRepository.findAll((Pageable) Mockito.any())).thenReturn(new PageImpl<>(cardStatistics));
        Mockito.when(cardStatisticsRepository.count()).thenReturn(2L);
        CardStatisticResponse request = new CardStatisticResponse();
        request.setStart(1);
        request.setLimit(2);
        CardStatisticResponse response = cardStatisticService.fetchData(request);
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getPayload());
    }

    @Test
    public void cardStatisticUpdateTest() {
        Mockito.when(cardStatisticsRepository.findByBin(Mockito.any())).thenReturn(cardStatistics.get(0));
        Mockito.when(cardStatisticsRepository.save(Mockito.any())).thenReturn(true);
        Assertions.assertDoesNotThrow(() -> cardStatisticService.updateCardStatistics(cardStatistics.get(0).getBin()));
    }

}
