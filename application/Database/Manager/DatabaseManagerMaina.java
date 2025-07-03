package application.Database.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Database.database;
import application.Models.Utils.AlertUtil;

public class DatabaseManagerMaina {
	
	
	 private Connection connection;

	    public DatabaseManagerMaina() {
	        connection = database.connectDB(); // Assicurati che il metodo connectDB() sia disponibile nella classe database.
	    }
	    
    // Metodo per l'accesso
    public  boolean login(String username, String password) {
        String selectData = "SELECT username, password, role FROM employee WHERE username = ? and password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectData)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Error" + e.getMessage());
            return false;
        }
    }

    // Metodo per la registrazione
    public  boolean register(String username, String password, String question, String answer, String role) {
        String checkUsername = "SELECT username FROM employee WHERE username = ?";
        String insertData = "INSERT INTO employee (username, password, question, answer, date, role) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement checkUsernameStatement = connection.prepareStatement(checkUsername);
             PreparedStatement insertDataStatement = connection.prepareStatement(insertData)) {
            checkUsernameStatement.setString(1, username);
            try (ResultSet resultSet = checkUsernameStatement.executeQuery()) {
                if (resultSet.next()) {
                    AlertUtil.showErrorAlert("Username already taken.");
                    return false; // Username già esistente
                }
            }

            if (password.length() < 8) {
                AlertUtil.showErrorAlert("The password is too short. at least 8 characters are required");
                return false; // Password troppo corta
            }

            insertDataStatement.setString(1, username);
            insertDataStatement.setString(2, password);
            insertDataStatement.setString(3, question);
            insertDataStatement.setString(4, answer);
            insertDataStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            insertDataStatement.setString(6, role);

            int rowsAffected = insertDataStatement.executeUpdate();
            if (rowsAffected <= 0) {
                AlertUtil.showErrorAlert("Errore during the registration.");
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Errore during the registration: " + e.getMessage());
            return false;
        }
    }

    // Metodo per il recupero della password
    public  boolean checkSecurityQuestion(String username, String question, String answer) {
        String selectData = "SELECT username, question, answer FROM employee WHERE username = ? AND question = ? AND answer = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectData)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, question);
            preparedStatement.setString(3, answer);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Error during password recovery: " + e.getMessage());
            return false;
        }
    }

    // Metodo per il cambio della password
    public boolean changePassword(String username, String newPassword, String question, String answer) {
        String getDateQuery = "SELECT date FROM employee WHERE username = ?";
        String updatePasswordQuery = "UPDATE employee SET password = ?, question = ?, answer = ?, date = ? WHERE username = ?";

        try (Connection connect = database.connectDB();
             PreparedStatement getDateStatement = connect.prepareStatement(getDateQuery);
             PreparedStatement updatePasswordStatement = connect.prepareStatement(updatePasswordQuery)) {

            getDateStatement.setString(1, username);
            ResultSet dateResultSet = getDateStatement.executeQuery();

            String date = dateResultSet.next() ? dateResultSet.getString("date") : "";

            updatePasswordStatement.setString(1, newPassword);
            updatePasswordStatement.setString(2, question);
            updatePasswordStatement.setString(3, answer);
            updatePasswordStatement.setString(4, date);
            updatePasswordStatement.setString(5, username);

            int rowsAffected = updatePasswordStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Error during changing password: " + e.getMessage());
            return false;
        }
    }
    
    public  String getUserRole(String username) {
        String selectUserRole = "SELECT role FROM employee WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectUserRole)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Restituisci null se il ruolo non è stato trovato o se si è verificato un errore
    }
    
    public  boolean isUsernameTaken(String username) {
        String checkUsernameQuery = "SELECT username FROM employee WHERE username = ?";
        try (Connection connection = database.connectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(checkUsernameQuery)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
