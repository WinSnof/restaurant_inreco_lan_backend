package com.mcnejas.restaurant.store.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.util.HashSet;
import lombok.*;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "menu_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "menu_title")
    private String title;
    @JsonFormat(pattern = "HH:MM")
    @Column(name = "menu_start_time")
    private LocalTime startTime;
    @JsonFormat(pattern = "HH:MM")
    @Column(name = "menu_end_time")
    private LocalTime endTime;
    @ManyToMany
    @JoinTable(
            name = "menu_dishes",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private Set<DishEntity> dishes = new HashSet<>();

    @Override
    public String toString() {
        return "MenuEntity{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            '}';
    }
}
