package com.mcnejas.restaurant;

import com.mcnejas.restaurant.store.repositories.DishRepository;
import com.mcnejas.restaurant.store.repositories.DishTypeRepository;
import com.mcnejas.restaurant.store.repositories.MenuRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(DishTypeRepository dishTypeRepository,
                                        DishRepository dishRepository,
                                        MenuRepository menuRepository) {
        return args -> {
            // ? Can we use this to initialize DB????
            // * Also initialize script in docker folder, idk is it needed
            // ! Run application using docker-compose
        };
    }

}
