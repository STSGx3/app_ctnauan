package com.example.baitaplonadnc;

import java.sql.Time;

public class Dish {
    private String Name_ofDish;
    private String Food_ingredients;
    private String Directions;
    private Integer Calories;
    private Time Duration;
    private String Classify;

    public Integer getCalories() {
        return Calories;
    }

    public String getClassify() {
        return Classify;
    }

    public String getDirections() {
        return Directions;
    }

    public String getFood_ingredients() {
        return Food_ingredients;
    }

    public String getName_ofDish() {
        return Name_ofDish;
    }

    public Time getDuration() {
        return Duration;
    }

    public void setCalories(Integer calories) {
        Calories = calories;
    }

    public void setClassify(String classify) {
        Classify = classify;
    }

    public void setDirections(String directions) {
        Directions = directions;
    }

    public void setDuration(Time duration) {
        Duration = duration;
    }

    public void setFood_ingredients(String food_ingredients) {
        Food_ingredients = food_ingredients;
    }

    public void setName_ofDish(String name_ofDish) {
        Name_ofDish = name_ofDish;
    }
}
