package org.example;

import org.example.Classes.Dish;
import org.example.Classes.Ingredient;
import org.example.Database.Database;
import org.example.Service.DishServise;
import org.example.Service.IngredientService;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var val = new DishServise(Database.getInstance().getConnection()).getById(1);
        System.out.println();
    }
}