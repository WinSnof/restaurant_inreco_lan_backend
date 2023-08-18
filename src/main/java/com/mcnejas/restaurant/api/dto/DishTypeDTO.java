package com.mcnejas.restaurant.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DishTypeDTO(
        Long id,
        @NotBlank
        String title
) {
}
