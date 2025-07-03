package application.Database.Manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.Database.database;
import application.Models.customersData;
import application.Models.productData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseManagerMenu {
    // Gestione del database
    private int idreceipt;
	
	 // Metodo per ottenere l'elenco degli ordini
	public ObservableList<productData> menuGetOrder() {
	    ObservableList<productData> listData = FXCollections.observableArrayList();
	    // Query per ottenere i dati degli ordini dal database
	    String sql = "SELECT * FROM customer WHERE stato = 'Consegnato'";

	    try (Connection connect = database.connectDB();
	         PreparedStatement prepare = connect.prepareStatement(sql);
	         ResultSet result = prepare.executeQuery()) {

	        while (result.next()) {
	            productData prod = new productData(result.getInt("id"),
	                    result.getString("prod_id"),
	                    result.getString("prod_name"),
	                    result.getString("type"),
	                    result.getInt("quantity"),
	                    result.getDouble("price"),
	                    result.getString("image"),
	                    result.getDate("date"));

	            prod.setCustomer_id(result.getString("customer_id"));
	            prod.setEmployeeName(result.getString("em_username"));
	            listData.add(prod);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return listData;
	}

 // Metodo per ottenere i prodotti in base alla categoria selezionata
	public ObservableList<productData> getProductsByCategory(String category) {
	    String sql;
	    if (category == null || category.isEmpty()) {
	        sql = "SELECT * FROM product";
	    } else {
	        sql = "SELECT * FROM product WHERE type = ?";
	    }

	    ObservableList<productData> listData = FXCollections.observableArrayList();

	    try (	Connection connect = database.connectDB();
	    		PreparedStatement prepare = (category == null || category.isEmpty()) ?
	            connect.prepareStatement(sql) : connect.prepareStatement(sql)) {
	        if (category != null && !category.isEmpty()) {
	            prepare.setString(1, category);
	        }
	        try (ResultSet result = prepare.executeQuery()) {
	            while (result.next()) {
	                productData prod = new productData(result.getInt("id"),
	                        result.getString("prod_id"),
	                        result.getString("prod_name"),
	                        result.getString("type"),
	                        result.getInt("stock"),
	                        result.getDouble("price"),
	                        result.getString("image"),
	                        result.getDate("date"));
	                listData.add(prod);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return listData;
	}

 // Metodo per ottenere i dati dei clienti
	public ObservableList<customersData> customersDataList() {
	    ObservableList<customersData> listData = FXCollections.observableArrayList();
	    String sql = "SELECT * FROM receipt";

	    try (Connection connect = database.connectDB();
	    	 PreparedStatement prepare = connect.prepareStatement(sql);
	         ResultSet result = prepare.executeQuery()) {

	        while (result.next()) {
	            customersData cData = new customersData(result.getInt("id"),
	                    result.getInt("customer_id"),
	                    result.getDouble("total"),
	                    result.getDate("date"),
	                    result.getString("em_username"));

	            listData.add(cData);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return listData;
	}
    
 // Metodo per rimuovere un ordine dal carrello
	public void removeOrderFromCart(int orderId) {
	    String deleteData = "DELETE FROM customer WHERE id = ?";
	   
	    try (	Connection connect = database.connectDB();
	    		PreparedStatement prepare = connect.prepareStatement(deleteData)) {
	        	prepare.setInt(1, orderId);
	        	prepare.executeUpdate();
	        	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
    
	public double calculateTotalByTable(String tableNumber) {
	    // Effettua una query al database per ottenere il totale in base al tavolo specificato
	    String query = "SELECT SUM(price) AS total FROM customer WHERE customer_id = ? AND stato = 'Consegnato'";

	    try (	Connection connect = database.connectDB();
	    		PreparedStatement prepare = connect.prepareStatement(query)) {
	        	prepare.setString(1, tableNumber);
	        try (ResultSet result = prepare.executeQuery()) {
	            if (result.next()) {
	                return result.getDouble("total");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return 0.0; // Restituisce 0 se non viene trovato alcun ordine "Consegnato" per il tavolo specificato
	}
    
	public void insertPayment(String tableNumber, String paymentMethod, double total, Date date, String username) {
	    if (total == 0) {
	        // Tratta il caso in cui il totale sia 0 (gestione dell'errore).
	        return;
	    }

	    String insertPay = "INSERT INTO receipt (customer_id, total, date, em_username, tipo_pagamento) "
	                        + "VALUES (?, ?, ?, ?, ?)";

	    try (Connection connect = database.connectDB();
	    	PreparedStatement prepare = connect.prepareStatement(insertPay, PreparedStatement.RETURN_GENERATED_KEYS)) {
	        prepare.setString(1, tableNumber);
	        prepare.setDouble(2, total);
	        prepare.setDate(3, date);
	        prepare.setString(4, username);
	        prepare.setString(5, paymentMethod);

	        int rowsInserted = prepare.executeUpdate();

	        if (rowsInserted > 0) {
	            try (ResultSet generatedKeys = prepare.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    idreceipt = generatedKeys.getInt(1);

	                    String updateOrderStatus = "UPDATE customer SET stato = 'Pagato' WHERE customer_id = ?";
	                    try (PreparedStatement updatePrepare = connect.prepareStatement(updateOrderStatus)) {
	                        updatePrepare.setString(1, tableNumber);
	                        updatePrepare.executeUpdate();
	                    }
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}



    public int getIdreceipt() {
		return idreceipt;
	}
    
    

}