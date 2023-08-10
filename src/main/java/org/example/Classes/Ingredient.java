package org.example.Classes;


public class Ingredient {
    private int id;
    private String name;
    private boolean isUsing;

    public Ingredient(int id, String name, boolean isUsing) {
        this.id = id;
        this.name = name;
        this.isUsing = isUsing;
    }

    public Ingredient(String name) {
        this.name = name;
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

    public boolean isUsing() {
        return isUsing;
    }

    public void setUsing(boolean using) {
        isUsing = using;
    }
}
