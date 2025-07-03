package application.Models.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Window;
import java.io.IOException;
import java.util.Optional;

public class LogoutUtil {
    public static void logout(Window window) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                if (window instanceof Stage) {
                    ((Stage) window).close();
                }

                Parent root = FXMLLoader.load(LogoutUtil.class.getResource("/application/Accessform.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setTitle("My Restaurant");

                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}







