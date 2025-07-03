package application.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {

    // Metodo per stabilire la connessione al database
    public static Connection connectDB() {

        try {
            // Carica il driver JDBC per MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Crea una connessione al database "ristorante" su localhost con l'utente "root" e password vuota
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante", "root", "");
            
            // Restituisce la connessione
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // In caso di errore, restituisce null
        return null;
    }

}
