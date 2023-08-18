package com.mcnejas.restaurant.api.services;

import com.mcnejas.restaurant.api.dto.DishDTO;
import com.mcnejas.restaurant.api.dto.MenuDTO;

import java.util.List;

public interface MenuService extends Service<MenuDTO, MenuDTO> {

  void addDishToMenu(Long dishId, Long menuId);

  List<DishDTO> getAllMenuDishes(Long id);

  void deleteDishFromMenu(Long menuId, Long dishId);
}
