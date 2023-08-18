package com.mcnejas.restaurant.api.services.impl;

import com.mcnejas.restaurant.api.dto.DishCreateDTO;
import com.mcnejas.restaurant.api.dto.DishDTO;
import com.mcnejas.restaurant.api.exceptions.BadRequestException;
import com.mcnejas.restaurant.api.mapper.DishMapper;
import com.mcnejas.restaurant.api.services.DishService;
import com.mcnejas.restaurant.store.entities.DishEntity;
import com.mcnejas.restaurant.store.entities.DishTypeEntity;
import com.mcnejas.restaurant.store.repositories.DishRepository;
import com.mcnejas.restaurant.store.repositories.DishTypeRepository;
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
public class DishServiceImpl implements DishService {

  private final DishRepository dishRepository;
  private final DishTypeRepository dishTypeRepository;
  @Override
  public List<DishDTO> findAll() {
    log.info("DishServiceImpl - findAll");
    return dishRepository.findAll()
        .stream()
        .map(DishMapper::convert)
        .collect(Collectors.toList());
  }

  @Override
  public DishDTO save(DishCreateDTO dishDTO) {
    log.info("DishServiceImpl - save: DishDTO := ${}", dishDTO);
    DishTypeEntity dishTypeEntity = dishTypeRepository.findById(dishDTO.type_id())
        .orElseThrow(() -> {
          log.error("DishServiceImpl - save: DishTypeId := ${}", dishDTO.title());
          return new BadRequestException("Dish type doesn't exist. DishTypeId := " + dishDTO.type_id());
        });
    DishEntity toSave = DishEntity.builder()
        .title(dishDTO.title())
        .price(dishDTO.price())
        .type(dishTypeEntity)
        .build();
    DishEntity saved = dishRepository.save(toSave);
    log.info("DishServiceImpl - save: SavedDish := ${}", saved);
    return DishMapper.convert(saved);
  }

  @Override
  public void deleteById(Long dishId) {
    log.info("DishServiceImpl - deleteById: DishId := ${}", dishId);
    DishEntity dishEntity = dishRepository.findById(dishId)
        .orElseThrow(() -> {
          log.error("DishServiceImpl - deleteById: DishId := ${}", dishId);
          return new BadRequestException("Dish doesn't exist. DishId := " + dishId);
        });
    if (!dishEntity.getMenuSet()
        .isEmpty()) {
      log.error("DishServiceImpl - deleteById: DishId := ${}. Dish uses in menu.", dishId);
      throw new BadRequestException("This dish uses in menu. Delete menu first.");
    }
    dishRepository.deleteById(dishId);
  }

}
