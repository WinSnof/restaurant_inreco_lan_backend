package com.mcnejas.restaurant.api.services;

import java.util.List;

public interface Service<T, V> {

  List<T> findAll();

  T save(V dishDTO);

  void deleteById(Long id);
}
