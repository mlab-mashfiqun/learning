package com.smarthostel.smarthostel.services;

import com.smarthostel.smarthostel.dtos.MenuDto;
import com.smarthostel.smarthostel.entities.Menu;
import com.smarthostel.smarthostel.entities.User;
import com.smarthostel.smarthostel.enums.MealType;
import com.smarthostel.smarthostel.repositories.MenuRepository;
import com.smarthostel.smarthostel.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;
import java.util.UUID;

@Service
public class MealService {
    private final MenuRepository menuRepository;
    private final UserService userService;

    public MealService(MenuRepository menuRepository, UserService userService) {
        this.menuRepository = menuRepository;
        this.userService = userService;
    }
    public List<Menu> fetchMenu() {
        return menuRepository.findAll();
    }
    public Menu addMenu(
            LocalDate date,
            String items,
            String description,
            BigDecimal price,
            MealType mealType
    ) {
        Menu menu = new Menu();
        menu.setDate(date);
        menu.setItems(items);
        menu.setDescription(description);
        menu.setPrice(price);
        menu.setMealType(mealType);
        menu.setIsAvailable(true);
        menu.setCreatedAt(Instant.now(Clock.system(ZoneId.systemDefault())));
        menu.setUpdatedAt(Instant.now(Clock.system(ZoneId.systemDefault())));
        User user = userService.findById(UUID.fromString("ffb0238b-13fe-4e97-8092-c106173f4925"));
        menu.setCreatedBy(user.getUsername());
        return menuRepository.save(menu);
    }
}
