package com.mcnejas.restaurant.api.controllers;

import com.mcnejas.restaurant.api.dto.DishCreateDTO;
import com.mcnejas.restaurant.api.dto.DishDTO;
import com.mcnejas.restaurant.api.services.DishService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@Validated
public class DishController {

  private final DishService dishService;
  public static final String FETCH_DISHES = "/api/v1/dishes";
  public static final String CREATE_OR_UPDATE_DISHES = "/api/v1/dishes";
  public static final String DELETE_DISH = "/api/v1/dishes/{dish_id}";

  @GetMapping(FETCH_DISHES)
  public ResponseEntity<List<DishDTO>> fetchDishes() {
    log.info("DishController - fetchDishes");
    return ResponseEntity.ok(dishService.findAll());
  }

  @PostMapping(CREATE_OR_UPDATE_DISHES)
  public ResponseEntity<DishDTO> createDish(@Valid @RequestBody DishCreateDTO dishCreateDTO) {
    log.info("DishController - createDish: dishCreateDTO := {}", dishCreateDTO);
    return ResponseEntity.ok(dishService.save(dishCreateDTO));
  }

  @DeleteMapping(DELETE_DISH)
  public HttpStatus deleteDish(
      @PathVariable(value = "dish_id") @Min(0) Long dishId) {
    log.info("DishController - createDish: dishId := {}", dishId);
    dishService.deleteById(dishId);
    return HttpStatus.OK;
  }

}
