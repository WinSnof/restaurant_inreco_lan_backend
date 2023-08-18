package com.mcnejas.restaurant.api.advice.utils;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationErrorResponse {

  private List<Violation> violations = new ArrayList<>();
}
