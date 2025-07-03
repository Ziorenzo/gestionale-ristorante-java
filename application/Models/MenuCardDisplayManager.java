package application.Models;

import application.Controllers.Employee.cardProductController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class MenuCardDisplayManager {
    public static void displayMenuCard(GridPane menuGridPane, ObservableList<productData> dataToShow) {
        menuGridPane.getChildren().clear();
        menuGridPane.getRowConstraints().clear();
        menuGridPane.getColumnConstraints().clear();

        int row = 0;
        int column = 0;

        for (int q = 0; q < dataToShow.size(); q++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MenuCardDisplayManager.class.getResource("/application/cardProduct.fxml"));
                AnchorPane pane = loader.load();
                cardProductController cardC = loader.getController();
                cardC.setData(dataToShow.get(q));

                if (column == 3) {
                    column = 0;
                    row += 1;
                }

                menuGridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(10));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
