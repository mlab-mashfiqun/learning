package com.smarthostel.smarthostel.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smarthostel.smarthostel.enums.MealType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link com.smarthostel.smarthostel.entities.Menu}
 */
public class MenuDto implements Serializable {
    private final LocalDate date;
    private final String items;
    private final String description;
    private final BigDecimal price;
    @JsonProperty("meal_type")
    private final MealType mealType;

    public MenuDto(LocalDate date, String items, String description, BigDecimal price, MealType mealType) {
        this.date = date;
        this.items = items;
        this.description = description;
        this.price = price;
        this.mealType = mealType;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getItems() {
        return items;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public MealType getMealType() {
        return mealType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuDto entity = (MenuDto) o;
        return Objects.equals(this.date, entity.date) &&
                Objects.equals(this.items, entity.items) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.price, entity.price) &&
                Objects.equals(this.mealType, entity.mealType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, items, description, price, mealType);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "date = " + date + ", " +
                "items = " + items + ", " +
                "description = " + description + ", " +
                "price = " + price + ", " +
                "mealType = " + mealType + ")";
    }
}