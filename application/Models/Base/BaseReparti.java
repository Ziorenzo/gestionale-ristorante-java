package application.Models.Base;

import java.net.URL;
import java.util.ResourceBundle;

import application.Database.Manager.DatabaseManagerCucina;
import application.Models.cucinaData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public abstract class BaseReparti extends BaseEmployee {
    @FXML
    protected AnchorPane customers_form;
    @FXML
    protected TableView<cucinaData> customers_tableView;
    @FXML
    protected TableColumn<?, ?> customers_col_ordineID;
    @FXML
    protected TableColumn<?, ?> customers_col_prodottoID;
    @FXML
    protected TableColumn<?, ?> customers_col_quantity;
    @FXML
    protected TableColumn<?, ?> customers_col_customer_id;
    

    private cucinaData selectedOrder;
    private DatabaseManagerCucina databaseManager;

    public BaseReparti(String tableName) {
        this.databaseManager = new DatabaseManagerCucina(tableName);
    }

    @FXML
    private void serve() {
        if (selectedOrder != null) {
            int orderId = selectedOrder.getId();
            boolean success = databaseManager.updateOrderStatus(orderId, "Consegnato");

            if (success) {
                ShowData();
            } else {
                AlertUtil.showErrorAlert("Failed to update order status.");
            }
        } else {
            AlertUtil.showErrorAlert("Select an order before pressing the 'Serve Beverage' button.");
        }
    }

    public void ShowData() {
        ObservableList<cucinaData> dataList = databaseManager.getData();

        customers_col_ordineID.setCellValueFactory(new PropertyValueFactory<>("id"));
        customers_col_prodottoID.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
        customers_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        customers_col_customer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

        customers_tableView.setItems(dataList);

        customers_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedOrder = newSelection;
            }
        });
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        super.initialize(arg0, arg1);
        ShowData();
    }
}
