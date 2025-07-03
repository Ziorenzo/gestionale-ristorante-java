package application.Models.Base;

import java.io.IOException;

import application.Models.Utils.FormSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public abstract class BaseEmployee extends BaseUser {
	@FXML
    protected Button menu_cucina;
    @FXML
    protected Button menu_bevande;
 // Metodo chiamato quando il pulsante "Cucina" viene premuto
    @FXML
    public void switchToMenuCucina(ActionEvent event) throws IOException {
        FormSwitcher.switchForm(event, "/application/repartocucina.fxml");
    }

    // Metodo chiamato quando il pulsante "Bevande" viene premuto
    @FXML
    public void switchToMenuBevande(ActionEvent event) throws IOException {
        FormSwitcher.switchForm(event, "/application/repartobevande.fxml");
    }

    // Metodo chiamato quando il pulsante "Menu" viene premuto
    @FXML
    public void switchToMenuForm(ActionEvent event) throws IOException {
        FormSwitcher.switchForm(event, "/application/menuform.fxml");
    }
}
