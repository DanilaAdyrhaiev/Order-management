package org.example.Service;

import org.example.Classes.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//ingredient management service
public class IngredientService {
    //Using CRUD method
    private PreparedStatement createPrepareStatement;
    private PreparedStatement readByIDPrepareStatement;
    private PreparedStatement readAllPrepareStatement;
    private PreparedStatement readByNamePrepareStatement;
    private PreparedStatement updateNamePrepareStatement;
    private PreparedStatement updateIsUsingPrepareStatement;
    private PreparedStatement deleteByIdPrepareStatement;
    private PreparedStatement deleteByNamePrepareStatement;

    //database queries
    public IngredientService(Connection connection){
        try{
            createPrepareStatement = connection.prepareStatement("insert ingredient(name, isUsing) values (?, ?)");
            readByNamePrepareStatement = connection.prepareStatement("SELECT * FROM ingredient where name = ?");
            readByIDPrepareStatement = connection.prepareStatement("SELECT * FROM ingredient where id = ?");
            readAllPrepareStatement = connection.prepareStatement("select * from ingredient where id != ?");
            updateNamePrepareStatement = connection.prepareStatement("update ingredient set name = ? where id = ?");
            updateIsUsingPrepareStatement = connection.prepareStatement("update ingredient set isUsing = ? where id = ?");
            deleteByIdPrepareStatement = connection.prepareStatement("delete from ingredient where id = ?");
            deleteByNamePrepareStatement = connection.prepareStatement("delete from ingredient where name = ?");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    //Ingredient Creation Method
    public void create(String name){
        try{
            createPrepareStatement.setString(1, name);
            createPrepareStatement.setBoolean(2, false);
            createPrepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ingredient getById(int id){
        try {
            readByIDPrepareStatement.setInt(1, id);
            ResultSet rs = readByIDPrepareStatement.executeQuery();
            if (!rs.next()){
                return null;
            }
            String name = rs.getString(2);
            Boolean isUsing = rs.getBoolean(3);
            return new Ingredient(id, name, isUsing);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ingredient> getAll(){
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            readAllPrepareStatement.setInt(1, 0);
            ResultSet rs = readAllPrepareStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Boolean isUsing = rs.getBoolean(3);
                ingredients.add(new Ingredient(id, name, isUsing));
            }
            return ingredients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ingredient getByName(String name){
        try {
            readByNamePrepareStatement.setString(1, name);
            ResultSet rs = readByNamePrepareStatement.executeQuery();
            if (!rs.next()){
                return null;
            }
            int id = rs.getInt(1);
            Boolean isUsing = rs.getBoolean(3);
            return new Ingredient(id, name, isUsing);
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

    public void updateStatus(Boolean status, int id){
        try {
            updateIsUsingPrepareStatement.setBoolean(1, status);
            updateIsUsingPrepareStatement.setInt(2, id);
            updateIsUsingPrepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
