package org.example.Service;

import org.example.Classes.Dish;
import org.example.Classes.Ingredient;
import org.example.Database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishServise {
    private PreparedStatement createPrepareStatement;
    private PreparedStatement readByIDPrepareStatement;
    private PreparedStatement readByNamePrepareStatement;
    private PreparedStatement readAllPrepareStatement;
    private PreparedStatement updateNamePrepareStatement;
    private PreparedStatement updateCostPrepareStatement;
    private PreparedStatement updateCookingTimePrepareStatement;
    private PreparedStatement deleteByIdPrepareStatement;
    private PreparedStatement deleteByNamePrepareStatement;

    public DishServise(Connection connection){
        try{
            createPrepareStatement = connection.prepareStatement("insert dish(name, cost, cookingTime) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            readByNamePrepareStatement = connection.prepareStatement("SELECT * FROM dish where name = ?");
            readByIDPrepareStatement = connection.prepareStatement("SELECT * FROM dish where id = ?");
            readAllPrepareStatement = connection.prepareStatement("select * from dish where id != ?");
            updateNamePrepareStatement = connection.prepareStatement("update dish set name = ? where id = ?");
            updateCostPrepareStatement = connection.prepareStatement("update dish set cost = ? where id = ?");
            updateCookingTimePrepareStatement = connection.prepareStatement("update dish set cookingTime = ? where id = ?");
            deleteByIdPrepareStatement = connection.prepareStatement("delete from dish where id = ?");
            deleteByNamePrepareStatement = connection.prepareStatement("delete from dish where name = ?");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    //Метод створеняя блюда в базі даних.
    public void create(String name, int cost, int cookingTime, List<Ingredient> ingredients){
        try{
            //Встановлення параметрів
            createPrepareStatement.setString(1, name);
            createPrepareStatement.setInt(2, cost);
            createPrepareStatement.setInt(3, cookingTime);
            //Забит до бази даних на створення об'єкту
            createPrepareStatement.executeUpdate();
            //Запит на отримання ключів об'єктів
            ResultSet keys = createPrepareStatement.getGeneratedKeys();
            int id = 0;
            if(keys.next())
                id = keys.getInt(1);
            //Виклик сервіса для праці з таблицею ingredients_dishes
            IngredientDishService ingredientDishService = new IngredientDishService(Database.getInstance().getConnection());
            //Виклик сервіса для праці з таблицею ingredients
            IngredientService ingredientService = new IngredientService(Database.getInstance().getConnection());
            //отримання списку всіх інгрідієнтів блюда
            List<Ingredient> allIngredients = ingredientService.getAll();
            for(int i = 0; i < ingredients.size(); i++){
                for(int k = 0; k < allIngredients.size(); k++){
                    if(ingredients.get(i).getId() == allIngredients.get(k).getId()){
                        ingredientDishService.create(allIngredients.get(k).getId(), (int) id);
                        ingredientService.updateStatus(true, allIngredients.get(k).getId());
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dish getById(int id){
        try {
            readByIDPrepareStatement.setInt(1, id);
            ResultSet rs = readByIDPrepareStatement.executeQuery();
            if (!rs.next()){
                return null;
            }
            String name = rs.getString(2);
            int price = rs.getInt(3);
            int time = rs.getInt(4);
            List<Ingredient> ingredients = new IngredientDishService(Database.getInstance().getConnection()).readDishIngredients(id);
            return new Dish(id, name, price, time, ingredients);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Dish> getAll(){
        List<Dish> dishes = new ArrayList<>();
        try {
            readAllPrepareStatement.setInt(1, 0);
            ResultSet rs = readAllPrepareStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                int time = rs.getInt(4);
                List<Ingredient> ingredients = new IngredientDishService(Database.getInstance().getConnection()).readDishIngredients(id);
                dishes.add(new Dish(id, name, price, time, ingredients));
            }
            return dishes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dish getByName(String name){
        try {
            readByNamePrepareStatement.setString(1, name);
            ResultSet rs = readByIDPrepareStatement.executeQuery();
            if (!rs.next()){
                return null;
            }
            int id = rs.getInt(1);
            int price = rs.getInt(3);
            int time = rs.getInt(4);
            List<Ingredient> ingredients = new IngredientDishService(Database.getInstance().getConnection()).readDishIngredients(id);
            return new Dish(id, name, price, time, ingredients);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateName(String name, int id){
        try {
            updateNamePrepareStatement.setString(1, name);
            updateNamePrepareStatement.setInt(2, id);
            updateNamePrepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCost(int cost, int id){
        try {
            updateCostPrepareStatement.setInt(1, cost);
            updateCostPrepareStatement.setInt(2, id);
            updateCostPrepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCookingTime(int time, int id){
        try{
            updateCookingTimePrepareStatement.setInt(1, time);
            updateCookingTimePrepareStatement.setInt(2, id);
            updateCookingTimePrepareStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateAddedIngredients(){

    }

    public void updateRemovedIngredients(){

    }

    public void deleteById(int id){
        try {
            deleteByIdPrepareStatement.setInt(1, id);
            deleteByIdPrepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByName(String name){
        try {
            deleteByNamePrepareStatement.setString(1, name);
            deleteByNamePrepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    
}
