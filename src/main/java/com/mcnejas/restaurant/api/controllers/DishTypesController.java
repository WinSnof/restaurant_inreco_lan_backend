package com.mcnejas.restaurant.api.controllers;

import com.mcnejas.restaurant.api.dto.DishTypeDTO;
import com.mcnejas.restaurant.api.services.DishTypeService;
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
public class DishTypesController {

  private final DishTypeService dishTypeService;
  private static final String FETCH_DISH_TYPES = "/api/v1/dish-types";
  private static final String CREATE_OR_UPDATE_DISH_TYPES = "/api/v1/dish-types";
  private static final String DELETE_DISH_TYPE = "/api/dish-types/{dish_type_id}";

  @GetMapping(FETCH_DISH_TYPES)
  public ResponseEntity<List<DishTypeDTO>> fetchDishTypes() {
    log.info("DishTypesController - fetchDishTypes");
    return ResponseEntity.ok(dishTypeService.findAll());
  }

  @PostMapping(CREATE_OR_UPDATE_DISH_TYPES)
  public ResponseEntity<DishTypeDTO> createDishType(@Valid @RequestBody DishTypeDTO dishTypeDTO) {
    log.info("DishTypesController - createDishType: DishTypeDTO := {}", dishTypeDTO);
    return ResponseEntity.ok(dishTypeService.save(dishTypeDTO));
  }

  @DeleteMapping(DELETE_DISH_TYPE)
  public HttpStatus deleteDishType(@PathVariable(value = "dish_type_id") @Min(1) Long dishTypeId) {
    log.info("DishTypesController - deleteDishType: DishTypeId := {}", dishTypeId);
    dishTypeService.deleteById(dishTypeId);
    return HttpStatus.OK;
  }

}
