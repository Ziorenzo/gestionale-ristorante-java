package application.Database.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.Database.database;

public class DatabaseManagerHandler {

	
	public static void eseguiAggiornamentoDelDatabase(int id) {
        try {
            Connection connect = database.connectDB();
            if (connect == null) {
                throw new IllegalStateException("No database connection");
            }

            // Aggiorna lo stato dell'ordine nel database
            String updateQuery = "UPDATE customer SET stato = 'pronto' WHERE id = ?";

            // Creazione della PreparedStatement con la query SQL
            PreparedStatement prepare = connect.prepareStatement(updateQuery);

            // Impostazione del valore del parametro
            prepare.setInt(1, id);

            // Esecuzione della query di aggiornamento
            prepare.executeUpdate();

            // Chiudi la PreparedStatement
            prepare.close();

            // Chiudi la connessione
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
