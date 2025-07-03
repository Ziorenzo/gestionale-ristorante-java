package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Carica la radice dell'interfaccia utente dal file FXML chiamato "Maina.fxml"
            Parent root = FXMLLoader.load(getClass().getResource("Accessform.fxml"));
            
            // Crea una nuova scena con la radice appena caricata
            Scene scene = new Scene(root);
            
            // Imposta la scena sullo stage principale (finestra principale dell'applicazione)
            primaryStage.setScene(scene);
            primaryStage.setTitle("My Restaurant");
            
            
            // Mostra la finestra principale
            primaryStage.show();
            
        } catch(Exception e) {
            // In caso di errore durante il caricamento o la visualizzazione dell'interfaccia utente,
            // stampa le informazioni sull'errore sulla console
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        // Metodo di avvio dell'applicazione JavaFX
        launch(args);
    }
}
