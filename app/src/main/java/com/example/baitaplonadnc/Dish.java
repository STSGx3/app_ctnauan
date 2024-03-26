package com.example.baitaplonadnc;

import java.sql.Time;

public class Dish {
    private  int ID;
    private String Name_ofDish;
    private String Food_ingredients;
    private String Directions;
    private Integer Calories;
    private Time Duration;
    private String Classify;
    private String Ower;
    public Dish(int ID, String Name_ofDish, String Food_ingredients, String Directions, Integer Calories,Time Duration,String Classify,String Ower){
        this.ID=ID;
        this.Name_ofDish=Name_ofDish;
        this.Food_ingredients=Food_ingredients;
        this.Directions=Directions;
        this.Calories=Calories;
        this.Duration=Duration;
        this.Classify=Classify;
        this.Ower = Ower;
    }

    public Dish(int ID, String Name_ofDish, Integer Calories) {
        this.ID=ID;
        this.Name_ofDish=Name_ofDish;
        this.Calories=Calories;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getOwer() {
        return Ower;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setOwer(String ower) {
        Ower = ower;
    }

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
