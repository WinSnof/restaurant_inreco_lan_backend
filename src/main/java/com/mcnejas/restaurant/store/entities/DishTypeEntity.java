package com.mcnejas.restaurant.store.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dish_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DishTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dish_type")
    private String type;
}
