package application.Models.Utils;

import application.Models.data;
import javafx.scene.control.Label;

public class DisplayUtil {
    public static void displayUsername(Label usernameLabel) {
        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        usernameLabel.setText(user);
    }
}
