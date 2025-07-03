package application.Models.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import java.io.IOException;

public class FormSwitcher {
	public static void switchForm(ActionEvent event, String resource) throws IOException {
	    Parent root = FXMLLoader.load(FormSwitcher.class.getResource(resource));
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    Scene scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
}