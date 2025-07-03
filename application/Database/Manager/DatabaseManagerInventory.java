package application.Database.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.Database.database;
import application.Models.productData;

public class DatabaseManagerInventory {
    private Connection connection;

    public DatabaseManagerInventory() {
        connection = database.connectDB(); // Assicurati che il metodo connectDB() sia disponibile nella classe database.
    }

    public boolean addProduct(String prodID, String prodName, String type, int stock, double price, String status, String image, String date) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO product (prod_id, prod_name, type, stock, price, status, image, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, prodID);
            preparedStatement.setString(2, prodName);
            preparedStatement.setString(3, type);
            preparedStatement.setInt(4, stock);
            preparedStatement.setDouble(5, price);
            preparedStatement.setString(6, status);
            preparedStatement.setString(7, image);
            preparedStatement.setString(8, date);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(int id, String prodID, String prodName, String type, int stock, double price, String status, String image, String date) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE product SET prod_id = ?, prod_name = ?, type = ?, stock = ?, price = ?, status = ?, image = ?, date = ? WHERE id = ?")) {
            preparedStatement.setString(1, prodID);
            preparedStatement.setString(2, prodName);
            preparedStatement.setString(3, type);
            preparedStatement.setInt(4, stock);
            preparedStatement.setDouble(5, price);
            preparedStatement.setString(6, status);
            preparedStatement.setString(7, image);
            preparedStatement.setString(8, date);
            preparedStatement.setInt(9, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<productData> getAllProducts() {
        List<productData> productList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product")) {

            while (resultSet.next()) {
                productData prodData = new productData(resultSet.getInt("id"),
                        resultSet.getString("prod_id"),
                        resultSet.getString("prod_name"),
                        resultSet.getString("type"),
                        resultSet.getInt("stock"),
                        resultSet.getDouble("price"),
                        resultSet.getString("status"),
                        resultSet.getString("image"),
                        resultSet.getDate("date"));
                productList.add(prodData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }
}
