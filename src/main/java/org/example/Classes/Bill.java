package org.example.Classes;

import java.util.List;

public class Bill {

    private int id;
    private int tableNumber;
    private List<Dish> dishes;
    private int total;

    public Bill(int id, int tableNumber, List<Dish> dishes, int total) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.dishes = dishes;
        this.total = total;
    }

    public Bill(int tableNumber, List<Dish> dishes, int total) {
        this.tableNumber = tableNumber;
        this.dishes = dishes;
        this.total = total;
    }

    public int getTableNumb() {
        return tableNumber;
    }

    public int getId() {
        return id;
    }

    public void setTableNumb(int tableNumb) {
        this.tableNumber = tableNumb;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
