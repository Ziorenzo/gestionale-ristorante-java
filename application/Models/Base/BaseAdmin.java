package application.Models.Base;

import java.io.IOException;

import application.Models.Utils.FormSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public abstract class BaseAdmin extends BaseUser {
    // Elementi comuni come pulsanti, etichette, ecc. possono essere dichiarati qui
	
    @FXML
    protected Button dashboard_btn;
    @FXML
    protected Button inventory_btn;
    @FXML
    protected Button customers_btn;
	
    // Metodo per cambiare alla schermata del dashboard
    public void switchToDashboard(ActionEvent event) throws IOException {
        FormSwitcher.switchForm(event, "/application/dashboardform.fxml");
    }

    // Metodo per cambiare alla schermata dell'inventario
    public void switchToInventory(ActionEvent event) throws IOException {
        FormSwitcher.switchForm(event, "/application/inventory_form.fxml");
    }

    // Metodo per cambiare alla schermata del menu
    public void switchToMenuForm(ActionEvent event) throws IOException {
        FormSwitcher.switchForm(event, "/application/menuformadmin.fxml");
    }

    // Metodo per cambiare alla schermata dei clienti
    public void switchToCustomersForm(ActionEvent event) throws IOException {
        FormSwitcher.switchForm(event, "/application/customersform.fxml");
    }
}