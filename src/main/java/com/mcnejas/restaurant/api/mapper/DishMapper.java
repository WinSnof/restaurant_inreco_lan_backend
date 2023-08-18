package com.mcnejas.restaurant.api.mapper;

import com.mcnejas.restaurant.api.dto.DishDTO;
import com.mcnejas.restaurant.store.entities.DishEntity;

public class DishMapper {
    public static DishDTO convert(DishEntity model) {
        return new DishDTO(model.getId(), model.getTitle(), model.getPrice(), model.getType().getType());
    }
}
