package com.mcnejas.restaurant.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record DishCreateDTO(
        @NotBlank
        @NotEmpty
        String title,
        @Min(0)
        BigDecimal price,
        @Min(0)
        Long type_id
) {
}
