package application.Models.Utils;

import application.Models.Prototype.Ricevuta;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class AlertUtil {

    public static void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showSuccessAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showConfirmationAlert(String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showInformationAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showReceiptAlert(Ricevuta ricevuta) {
        if (ricevuta != null) {
            try {
                Ricevuta nuovaCopia = ricevuta.clone();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Receipt " + nuovaCopia.getTipo_pagamento());
                alert.setHeaderText(null);
                alert.setContentText(
                    "Receipt ID: " + nuovaCopia.getId_ricevuta() + "\n" +
                    "Table: " + nuovaCopia.getCustomer_id() + "\n" +
                    "Total: $" + nuovaCopia.getTotal() + "\n" +
                    "Date: " + nuovaCopia.getDate().toString() + "\n" +
                    "Employee: " + nuovaCopia.getEm_username() + "\n" +
                    "Payment Method: " + nuovaCopia.getTipo_pagamento()
                );

                // Aggiungi un pulsante "Stampa"
                ButtonType printButton = new ButtonType("Print");
                alert.getButtonTypes().add(printButton);

                // Ottieni il pulsante "Stampa" dalla finestra di dialogo
                Button printBtn = (Button) alert.getDialogPane().lookupButton(printButton);

                // Gestisci l'azione del pulsante "Stampa"
                printBtn.setOnAction(event -> {
                    // Implementare la logica per la stampa della ricevuta
                });

                alert.showAndWait();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
}
