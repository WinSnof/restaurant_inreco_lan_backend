package com.mcnejas.restaurant.api.services.impl;

import com.mcnejas.restaurant.api.dto.DishTypeDTO;
import com.mcnejas.restaurant.api.mapper.DishTypeMapper;
import com.mcnejas.restaurant.api.services.DishTypeService;
import com.mcnejas.restaurant.store.entities.DishTypeEntity;
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
public class DishTypeServiceImpl implements DishTypeService {

  private final DishTypeRepository dishTypeRepository;

  @Override
  public List<DishTypeDTO> findAll() {
    log.info("DishTypeServiceImpl - findAll");
    return dishTypeRepository.findAll()
        .stream()
        .map(DishTypeMapper::convert)
        .collect(Collectors.toList());
  }

  @Override
  public DishTypeDTO save(DishTypeDTO dishDTO) {
    log.info("DishTypeServiceImpl - save: DishDTO := ${}", dishDTO);
    DishTypeEntity saved = dishTypeRepository.save(DishTypeMapper.convert(dishDTO));
    log.info("DishTypeServiceImpl - save: SavedDish := ${}", saved);
    return DishTypeMapper.convert(saved);
  }

  @Override
  public void deleteById(Long dishTypeId) {
    log.info("DishTypeServiceImpl - delete: DishTypeId := ${}", dishTypeId);
    dishTypeRepository.deleteById(dishTypeId);
  }
}
