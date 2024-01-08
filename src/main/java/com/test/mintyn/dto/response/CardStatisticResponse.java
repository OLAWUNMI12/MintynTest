package com.test.mintyn.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class CardStatisticResponse {

    private boolean success;
    private int start;
    private int limit;
    private long size;
    private Map<String, String> payload;
}
