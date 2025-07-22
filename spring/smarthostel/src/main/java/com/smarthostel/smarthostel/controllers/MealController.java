package com.smarthostel.smarthostel.controllers;

import com.smarthostel.smarthostel.dtos.MenuDto;
import com.smarthostel.smarthostel.entities.Menu;
import com.smarthostel.smarthostel.repositories.MenuRepository;
import com.smarthostel.smarthostel.services.MealService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/menu")
    public List<Menu> menu() {
        return mealService.fetchMenu();
    }

    @PostMapping("/menu")
    public Menu addMenu(@Valid @RequestBody MenuDto menuDto) {
        return mealService.addMenu(
                menuDto.getDate(),
                menuDto.getItems(),
                menuDto.getDescription(),
                menuDto.getPrice(),
                menuDto.getMealType()
        );
    }
}
