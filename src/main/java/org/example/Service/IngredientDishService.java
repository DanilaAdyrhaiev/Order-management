package org.example.Service;

import org.example.Classes.Dish;
import org.example.Classes.Ingredient;
import org.example.Database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDishService {
    private PreparedStatement createPrepareStatement;
    private PreparedStatement readByDishAndIngredientPrepareStatement;
    private PreparedStatement readByDishIdPrepareStatement;
    private PreparedStatement readByIngredientIdPrepareStatement;
    private PreparedStatement readAllPrepareStatement;
    private PreparedStatement deleteByIdPrepareStatement;

    public IngredientDishService(Connection connection){
        try {
            createPrepareStatement = connection.prepareStatement("insert ingredients_dishes(ingredient_id, dish_id) values(?,?)");
            readByDishAndIngredientPrepareStatement = connection.prepareStatement("select * from ingredients_dishes where ingredient_id = ?, dish_id = ?");
            readByDishIdPrepareStatement = connection.prepareStatement("select * from ingredients_dishes where dish_id = ?");
            readByIngredientIdPrepareStatement = connection.prepareStatement("select * from ingredients_dishes where ingredient_id = ?");
            readAllPrepareStatement = connection.prepareStatement("select * from ingredients_dishes where id = ?");
            deleteByIdPrepareStatement = connection.prepareStatement("delete from ingredients_dishes where ingredient_id = ? and dish_id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void create(int ingredient_id, int dish_id){
        try {
            createPrepareStatement.setInt(1, ingredient_id);
            createPrepareStatement.setInt(2, dish_id);
            createPrepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ingredient> readDishIngredients(int dish_id){
        List<Ingredient> ingredients = new ArrayList<>();
        IngredientService ingredService = new IngredientService(Database.getInstance().getConnection());
        try {
            readByDishIdPrepareStatement.setInt(1, dish_id);
            ResultSet rs = readByDishIdPrepareStatement.executeQuery();
            while (rs.next()){
                int ingredId = rs.getInt(2);
                ingredients.add(ingredService.getById(ingredId));
            }
            return ingredients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Dish> readIngredientDishes(int ingredient_id){
        List<Dish> dishes = new ArrayList<>();
        DishServise dishServise = new DishServise(Database.getInstance().getConnection());
        try {
            readByIngredientIdPrepareStatement.setInt(1, ingredient_id);
            ResultSet rs = readByIngredientIdPrepareStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt(3);
                dishes.add(dishServise.getById(id));
            }
            return dishes;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
