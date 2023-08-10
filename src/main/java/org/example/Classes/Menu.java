package org.example.Classes;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static Menu instance;

    private List<Dish> dishes;

    private Menu(){
        dishes = new ArrayList<>();
    }

    public static Menu getInstance() {
        if(instance ==null){
            instance = new Menu();
        }
        return instance;
    }
}
