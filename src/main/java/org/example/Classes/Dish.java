package org.example.Classes;

import org.example.Service.DishServise;

import java.util.List;


public class Dish {
    private int id;
    private String name;
    private int cost;
    private int cookingTime;
    private List<Ingredient> ingredients;

    public Dish(int id, String name, int cost, int cookingTime, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
    }

    public Dish(String name, int cost, int cookingTime, List<Ingredient> ingredients) {
        this.name = name;
        this.cost = cost;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngregient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public void deleteIngredient(Ingredient ingredient){
        for(int i = 0; i < ingredients.size(); i++){
            if(ingredients.get(i).getName().equals(ingredient.getName())){
                ingredients.remove(i);
                break;
            }
        }
    }




}
