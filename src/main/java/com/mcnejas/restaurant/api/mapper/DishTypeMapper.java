package com.mcnejas.restaurant.api.mapper;

import com.mcnejas.restaurant.api.dto.DishTypeDTO;
import com.mcnejas.restaurant.store.entities.DishTypeEntity;


public class DishTypeMapper {
    public static DishTypeDTO convert(DishTypeEntity model) {
        return new DishTypeDTO(model.getId(),model.getType());
    }

    public static DishTypeEntity convert(DishTypeDTO model) {
        return DishTypeEntity.builder().type(model.title()).build();
    }
}
