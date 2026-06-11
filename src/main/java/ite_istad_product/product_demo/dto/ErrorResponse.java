package ite_istad_product.product_demo.dto;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record ErrorResponse<T>(
        LocalDateTime timeStamp,
        String message,
        T  error,
        Integer status
) {
}
