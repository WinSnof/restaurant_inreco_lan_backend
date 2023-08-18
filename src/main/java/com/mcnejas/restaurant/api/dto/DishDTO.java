package com.mcnejas.restaurant.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record DishDTO(
        Long id,
        @NotBlank
        String title,
        @Min(0)
        BigDecimal price,
        @NotBlank
        String type
) {
}
