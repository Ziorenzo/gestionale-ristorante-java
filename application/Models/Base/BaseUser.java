package application.Models.Base;

import java.net.URL;
import java.util.ResourceBundle;

import application.Models.Utils.DisplayUtil;
import application.Models.Utils.LogoutUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public abstract class BaseUser implements Initializable {
	@FXML
	protected AnchorPane main_form;
    @FXML
    protected Label username;
    @FXML
    protected Button menu_btn;
    @FXML
    protected Button logout_btn;
	
    // Metodo per il logout
    @FXML
    public void logout(ActionEvent event) {
        LogoutUtil.logout(logout_btn.getScene().getWindow());
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DisplayUtil.displayUsername(username);  
		
	}
}