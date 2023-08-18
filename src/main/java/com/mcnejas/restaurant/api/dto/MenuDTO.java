package com.mcnejas.restaurant.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public record MenuDTO(
    Long id,
    @NotEmpty(message = "Menu title can't be null or blank string.")
    String title,

    @JsonFormat(pattern = "H:mm")
    @NotNull(message = "Menu start time can't be null.")
    LocalTime startTime,
    @JsonFormat(pattern = "H:mm")
    @NotNull(message = "Menu end time can't be null.")
    LocalTime endTime
) {

}
