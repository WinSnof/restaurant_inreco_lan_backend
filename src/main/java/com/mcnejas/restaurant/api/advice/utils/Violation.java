package com.mcnejas.restaurant.api.advice.utils;


public record Violation(
    String fieldName,
    String message
) {

}