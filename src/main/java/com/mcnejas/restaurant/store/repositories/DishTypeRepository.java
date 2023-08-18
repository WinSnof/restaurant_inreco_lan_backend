package com.mcnejas.restaurant.store.repositories;

import com.mcnejas.restaurant.store.entities.DishTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishTypeRepository extends JpaRepository<DishTypeEntity, Long> {
}
