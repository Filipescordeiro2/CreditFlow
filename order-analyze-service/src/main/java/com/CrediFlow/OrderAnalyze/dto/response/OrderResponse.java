package com.CrediFlow.OrderAnalyze.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderResponse(String message,
                            LocalDateTime creatAt) {
}
