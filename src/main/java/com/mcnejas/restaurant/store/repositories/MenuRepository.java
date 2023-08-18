package com.mcnejas.restaurant.store.repositories;

import com.mcnejas.restaurant.store.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

}
