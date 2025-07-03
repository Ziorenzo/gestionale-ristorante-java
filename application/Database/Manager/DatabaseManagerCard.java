package application.Database.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import application.Database.database;

public class DatabaseManagerCard {
    
    public int getStock(String prodID) {
        int stock = 0;
        try (Connection connect = database.connectDB();
             PreparedStatement prepare = connect.prepareStatement("SELECT stock FROM product WHERE prod_id = ?")) {
            prepare.setString(1, prodID);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    stock = result.getInt("stock");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stock;
    }

    public String getProductStatus(String prodID) {
        String status = "";
        try (Connection connect = database.connectDB();
             PreparedStatement prepare = connect.prepareStatement("SELECT status FROM product WHERE prod_id = ?")) {
            prepare.setString(1, prodID);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    status = result.getString("status");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public void updateProductStock(String prodID, int newStock, double price, String status) {
        try (Connection connect = database.connectDB();
             PreparedStatement prepare = connect.prepareStatement("UPDATE product SET stock = ?, price = ?, status = ? WHERE prod_id = ?")) {
            prepare.setInt(1, newStock);
            prepare.setDouble(2, price);
            prepare.setString(3, status);
            prepare.setString(4, prodID);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int insertOrderToCart(String prodID, String prodName, String type, int quantity, double price, String image, String em_username, String stato, int tvl) {
        int customerID = -1; // Valore predefinito in caso di errore
        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
        try (Connection connect = database.connectDB()) {
            String insertData = "INSERT INTO customer (customer_id, prod_id, prod_name, type, quantity, price, date, image, em_username, stato) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
            
            try (PreparedStatement prepare = connect.prepareStatement(insertData, PreparedStatement.RETURN_GENERATED_KEYS)) {
                prepare.setString(1, String.valueOf(tvl));
                prepare.setString(2, prodID);
                prepare.setString(3, prodName);
                prepare.setString(4, type);
                prepare.setString(5, String.valueOf(quantity));
                prepare.setString(6, String.valueOf(price));
                prepare.setString(7, String.valueOf(sqlDate));
                prepare.setString(8, image);
                prepare.setString(9, em_username);
                prepare.setString(10, stato);

                int rowsInserted = prepare.executeUpdate();

                if (rowsInserted > 0) {
                    try (ResultSet generatedKeys = prepare.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            customerID = generatedKeys.getInt(1); // ID del cliente appena inserito
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerID;
    }
}
