package com.mcnejas.restaurant.store.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "dish_name")
  private String title;
  @Column(name = "dish_price")
  private BigDecimal price;
  @ManyToMany(mappedBy = "dishes")
  private Set<MenuEntity> menuSet = new HashSet<>();
  @ManyToOne
  private DishTypeEntity type;

  @Override
  public String toString() {
    return "DishEntity{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", price=" + price +
        ", type=" + type +
        '}';
  }
}
