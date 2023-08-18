package com.mcnejas.restaurant.api.mapper;

import com.mcnejas.restaurant.api.dto.MenuDTO;
import com.mcnejas.restaurant.store.entities.MenuEntity;

public class MenuMapper {

    public static MenuDTO convert(MenuEntity model) {
        return new MenuDTO(model.getId(), model.getTitle(), model.getStartTime(), model.getEndTime());
    }

    public static MenuEntity convert(MenuDTO model) {
        return MenuEntity.builder().title(model.title()).startTime(model.startTime()).endTime(model.endTime()).build();
    }

}
