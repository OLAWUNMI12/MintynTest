package com.test.mintyn.repository;

import com.test.mintyn.model.CardStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardStatisticsRepository extends PagingAndSortingRepository<CardStatistic, Long>, JpaRepository<CardStatistic, Long> {

    CardStatistic findByBin(String bin);
}
