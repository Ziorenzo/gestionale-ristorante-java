package application.Database.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Database.database;
import application.Models.productData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseManagerDashboard {

    // Restituisci il numero di ricevute
    public int getReceiptCount() {
        int count = 0;
        String sql = "SELECT COUNT(id) FROM receipt";
        try (Connection connect = database.connectDB();
             PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {
            if (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    // Restituisci il totale delle vendite di oggi
    public double getTodayTotalSales() {
        double total = 0;
        String sql = "SELECT SUM(total) FROM receipt WHERE date = CURDATE()";
        try (Connection connect = database.connectDB();
             PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {
            if (result.next()) {
                total = result.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    // Restituisci il totale delle vendite
    public double getTotalSales() {
        double total = 0;
        String sql = "SELECT SUM(total) FROM receipt";
        try (Connection connect = database.connectDB();
             PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {
            if (result.next()) {
                total = result.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    // Restituisci il numero di prodotti venduti
    public int getProductsSoldCount() {
        int count = 0;
        String sql = "SELECT COUNT(quantity) FROM customer";
        try (Connection connect = database.connectDB();
             PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {
            if (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public ObservableList<productData> filterTableByPeriod(String selectedPeriod) {
        String sql = "";
        ObservableList<productData> topSellingProductsList = FXCollections.observableArrayList();
        try (Connection connect = database.connectDB()) {
            switch (selectedPeriod) {
                case "Daily":
                    sql = "SELECT prod_id, prod_name, SUM(quantity) as totalSold " +
                          "FROM customer " +
                          "WHERE date = CURDATE() " + // Filtra per la data corrente
                          "GROUP BY prod_id, prod_name " +
                          "ORDER BY totalSold DESC " +
                          "LIMIT 5"; // Limita ai primi 5 prodotti
                    break;
                case "Weekly":
                    sql = "SELECT prod_id, prod_name, SUM(quantity) as totalSold " +
                          "FROM customer " +
                          "WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) AND CURDATE() " + // Filtra per gli ultimi 7 giorni
                          "GROUP BY prod_id, prod_name " +
                          "ORDER BY totalSold DESC " +
                          "LIMIT 5"; 
                    break;
                case "Monthly":
                    sql = "SELECT prod_id, prod_name, SUM(quantity) as totalSold " +
                          "FROM customer " +
                          "WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 30 DAY) AND CURDATE() " + // Filtra per gli ultimi 30 giorni
                          "GROUP BY prod_id, prod_name " +
                          "ORDER BY totalSold DESC " +
                          "LIMIT 5"; 
                    break;
            }

            try (PreparedStatement prepare = connect.prepareStatement(sql);
                 ResultSet result = prepare.executeQuery()) {
                while (result.next()) {
                    String productId = result.getString("prod_id");
                    String productName = result.getString("prod_name");
                    int totalSold = result.getInt("totalSold");
                    topSellingProductsList.add(new productData(productId, productName, totalSold));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return topSellingProductsList;
    }
}
