package com.example.baitaplonadnc;

import android.net.Uri;

import java.sql.Time;

public class Dish {
    private  String ID;
    private String Name_ofDish;
    private String Food_ingredients;
    private String Directions;
    private String Calories;
    private String Duration;
    private String Classify;
    private String Ower;
    private String LinkAnh;

    public Dish(){

    }

    public Dish(String id, String name, String ingredients, String directions, String calories, String duration, String classify, String owner,String LinkAnh) {
        this.ID = id;
        this.Name_ofDish = name;
        this.Food_ingredients = ingredients;
        this.Directions = directions;
        this.Calories= calories;
        this.Duration = duration;
        this.Classify = classify;
        this.Ower = owner;
        this.LinkAnh=LinkAnh;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }

    public Dish(String ID, String Name_ofDish, String Calories) {
        this.ID = ID;
        this.Name_ofDish = Name_ofDish;
        this.Calories = Calories;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setOwer(String ower) {
        Ower = ower;
    }

    public String getOwer() {
        return Ower;
    }

    public String getID() {
        return ID;
    }

    public String getCalories() {
        return Calories;
    }

    public String getClassify() {
        return Classify;
    }

    public String getDirections() {
        return Directions;
    }

    public String getDuration() {
        return Duration;
    }

    public String getName_ofDish() {
        return Name_ofDish;
    }

    public String getFood_ingredients() {
        return Food_ingredients;
    }

    public void setCalories(String calories) {
        Calories = calories;
    }

    public void setClassify(String classify) {
        Classify = classify;
    }

    public void setDirections(String directions) {
        Directions = directions;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public void setName_ofDish(String name_ofDish) {
        Name_ofDish = name_ofDish;
    }

    public void setFood_ingredients(String food_ingredients) {
        Food_ingredients = food_ingredients;
    }
}
