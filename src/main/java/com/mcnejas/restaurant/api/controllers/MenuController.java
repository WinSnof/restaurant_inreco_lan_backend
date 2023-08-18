package com.mcnejas.restaurant.api.controllers;

import com.mcnejas.restaurant.api.dto.DishDTO;
import com.mcnejas.restaurant.api.dto.MenuDTO;
import com.mcnejas.restaurant.api.services.MenuService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@Validated
public class MenuController {

  private final MenuService menuService;
  private static final String FETCH_MENU_LIST = "/api/v1/menu-list";
  private static final String CREATE_OR_UPDATE_MENU_LIST = "/api/v1/menu-list";
  private static final String DELETE_MENU = "/api/v1/menu-list/{menu_id}";
  private static final String FETCH_MENU_DISHES = "/api/v1/menu-list/{menu_id}/dishes";
  private static final String DELETE_DISH_FROM_MENU = "/api/v1/menu-list/{menu_id}/dishes/{dish_id}";
  private static final String ADD_DISH_TO_MENU = "/api/v1/menu-list/{menu_id}/dishes/{dish_id}";

  @GetMapping(FETCH_MENU_LIST)
  public ResponseEntity<List<MenuDTO>> fetchMenu() {
    log.info("MenuController - fetchMenu");
    return ResponseEntity.ok(menuService.findAll());
  }

  @GetMapping(FETCH_MENU_DISHES)
  public ResponseEntity<List<DishDTO>> fetchMenuDishes(
      @PathVariable("menu_id") Long menuId) {
    log.info("MenuController - fetchMenuDishes: MenuId := {}", menuId);
    return ResponseEntity.ok(menuService.getAllMenuDishes(menuId));
  }

  @PostMapping(CREATE_OR_UPDATE_MENU_LIST)
  public ResponseEntity<MenuDTO> createMenu(
      @Valid @RequestBody MenuDTO menuDTO) {
    log.info("MenuController - createMenu: menuDTO := {}", menuDTO);
    return ResponseEntity.ok(menuService.save(menuDTO));
  }

  @PostMapping(ADD_DISH_TO_MENU)
  public void addDishToMenu(
      @PathVariable("menu_id") @Min(1) Long menuId,
      @PathVariable("dish_id") @Min(1) Long dishId) {
    log.info("MenuController - addDishToMenu: MenuId := {}, DishId := {}", menuId, dishId);
    menuService.addDishToMenu(menuId, dishId);
  }

  @DeleteMapping(DELETE_MENU)
  public void deleteMenu(
      @PathVariable("menu_id") @Min(1) Long menuId) {
    log.info("MenuController - deleteMenu: MenuId := {}", menuId);
    menuService.deleteById(menuId);
  }


  @DeleteMapping(DELETE_DISH_FROM_MENU)
  public void deleteDishFromMenu(
      @PathVariable(value = "menu_id") @Min(1) Long menuId,
      @PathVariable(value = "dish_id") @Min(1) Long dishId) {
    log.info("MenuController - deleteDishFromMenu: MenuId := {}, DishId := {}", menuId, dishId);
    menuService.deleteDishFromMenu(menuId, dishId);
  }
}
