package application.Database.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.Database.database;
import application.Models.cucinaData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseManagerCucina {
    private Connection connection;
    private String foodType;
    
    public DatabaseManagerCucina(String foodType) {
        this.foodType = foodType;
        connection = database.connectDB(); // Assicurati che il metodo connectDB() sia disponibile nella classe database.
    }

    // Metodo per ottenere l'elenco delle bevande
    public ObservableList<cucinaData> getData() {
        ObservableList<cucinaData> listData = FXCollections.observableArrayList();
        String sql = "SELECT id, prod_name, quantity, customer_id FROM customer WHERE stato = 'pronto' AND type = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, foodType);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cucinaData cData = new cucinaData(
                    resultSet.getInt("id"),
                    resultSet.getString("prod_name"),
                    resultSet.getInt("quantity"),
                    resultSet.getString("customer_id")
                );
                listData.add(cData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    public boolean updateOrderStatus(int orderId, String newStatus) {
        String updateOrderStatus = "UPDATE customer SET stato = ? WHERE id = ?";
        try {
            connection = database.connectDB();
            PreparedStatement prepare = connection.prepareStatement(updateOrderStatus);
            prepare.setString(1, newStatus);
            prepare.setInt(2, orderId);
            int rowsUpdated = prepare.executeUpdate();

            // Restituisce true se almeno una riga è stata aggiornata con successo
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}