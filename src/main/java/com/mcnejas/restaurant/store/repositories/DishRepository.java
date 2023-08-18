package com.mcnejas.restaurant.store.repositories;

import com.mcnejas.restaurant.store.entities.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<DishEntity, Long> {
}
