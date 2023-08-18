package com.mcnejas.restaurant.api.services.impl;

import com.mcnejas.restaurant.api.dto.DishDTO;
import com.mcnejas.restaurant.api.dto.MenuDTO;
import com.mcnejas.restaurant.api.exceptions.BadRequestException;
import com.mcnejas.restaurant.api.mapper.DishMapper;
import com.mcnejas.restaurant.api.mapper.MenuMapper;
import com.mcnejas.restaurant.api.services.MenuService;
import com.mcnejas.restaurant.store.entities.DishEntity;
import com.mcnejas.restaurant.store.entities.MenuEntity;
import com.mcnejas.restaurant.store.repositories.DishRepository;
import com.mcnejas.restaurant.store.repositories.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MenuServiceImpl implements MenuService {

  public final MenuRepository menuRepository;
  public final DishRepository dishRepository;

  @Override
  public List<MenuDTO> findAll() {
    log.info("MenuServiceImpl - findAll");
    return menuRepository.findAll()
        .stream()
        .map(MenuMapper::convert)
        .collect(Collectors.toList());
  }

  @Override
  public MenuDTO save(MenuDTO menuDto) {
    log.info("MenuServiceImpl - save: MenuDTO := {}", menuDto);
//    LocalTime startTime = LocalTime.parse(menuDto.startTime()
//        .toString());
//    LocalTime endTime = LocalTime.parse(menuDto.endTime()
//        .toString());
//    boolean isBefore = startTime.isBefore(endTime);
//    if (!isBefore) {
//      log.info(
//          "MenuServiceImpl - save: Start time is greater then end time. StartTime := {}, EndTime := {}",
//          menuDto.startTime(), menuDto.endTime());
//      throw new BadRequestException("MenuServiceImpl - save: Start time is greater then end time.");
//    }
    MenuEntity saved = menuRepository.save(MenuMapper.convert(menuDto));
    log.info("MenuServiceImpl - save: SavedMenu := {}", saved);
    return MenuMapper.convert(saved);
  }

  @Override
  public void deleteById(Long menuId) {
    log.info("MenuServiceImpl - deleteById: MenuId := {}", menuId);
    menuRepository.deleteById(menuId);
  }

  @Override
  public void addDishToMenu(Long menuId, Long dishId) {
    log.info("MenuServiceImpl - addDishToMenu:MenuId := {},  DishId := {}", dishId, menuId);
    DishEntity dishEntity = dishRepository.findById(dishId)
        .orElseThrow(() -> {
          log.error("MenuServiceImpl - addDishToMenu: Dish doesn't exist. DishId := {}", dishId);
          return new BadRequestException("Dish doesn't exist. DishId := " + dishId);
        });
    MenuEntity menuEntity = menuRepository.findById(menuId)
        .orElseThrow(() -> {
          log.error("MenuServiceImpl - addDishToMenu: Menu doesn't exist. MenuId := {}", menuId);
          return new BadRequestException("Menu doesn't exist. MenuId := " + menuId);
        });
    if (menuEntity.getDishes()
        .contains(dishEntity)) {
      throw new BadRequestException(
          "MenuServiceImpl - addDishToMenu: Menu already have this dish. Dish := " + dishEntity);
    }
    menuEntity.getDishes()
        .add(dishEntity);
    log.info(
        "MenuServiceImpl - addDishToMenu: New dish added to menu. MenuId := {}, DishID := {}",
        menuId, dishId);
  }

  @Override
  public List<DishDTO> getAllMenuDishes(Long menuId) {
    log.info("MenuServiceImpl - getAllMenuDishes: MenuId := {}", menuId);
    MenuEntity menuEntity = menuRepository.findById(menuId)
        .orElseThrow(() -> {
          log.error("MenuServiceImpl - getAllMenuDishes: Menu doesn't exist. MenuId := {}",
              menuId);
          return new BadRequestException("Menu doesn't exist. MenuId := " + menuId);
        });

    return menuEntity.getDishes()
        .stream()
        .map(DishMapper::convert)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteDishFromMenu(Long menuId, Long dishId) {
    log.info("MenuServiceImpl - deleteDishFromMenu: DishId := {}, MenuId := {}", dishId, menuId);
    DishEntity dishEntity = dishRepository.findById(dishId)
        .orElseThrow(() -> {
          log.error("MenuServiceImpl - deleteDishFromMenu: Dish doesn't exist. DishId := {}",
              dishId);
          return new BadRequestException("Dish doesn't exist. DishId := " + dishId);
        });
    MenuEntity menuEntity = menuRepository.findById(menuId)
        .orElseThrow(() -> {
          log.error("MenuServiceImpl - deleteDishFromMenu: Menu doesn't exist. MenuId := {}",
              menuId);
          return new BadRequestException("Menu doesn't exist. MenuId := " + menuId);
        });
    if (menuEntity.getDishes()
        .isEmpty()) {
      log.error("MenuServiceImpl - deleteDishFromMenu: Menu dishes is empty.");
      throw new BadRequestException("Nothing to delete");
    }
    menuEntity.getDishes()
        .remove(dishEntity);
    log.info(
        "MenuServiceImpl - deleteDishFromMenu: Dish was deleted from menu. MenuId := {}, DishId := {}",
        dishId, menuId);
  }
}
