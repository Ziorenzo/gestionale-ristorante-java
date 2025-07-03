package application.Controllers.Admin;

import java.net.URL;
import java.util.ResourceBundle;

import application.Database.Manager.DatabaseManagerMenu;
import application.Models.customersData;
import application.Models.Base.BaseAdmin;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class customersformController extends BaseAdmin {
    @FXML
    private AnchorPane customers_form;
    @FXML
    private TableView<customersData> customers_tableView;
    @FXML
    private TableColumn<?, ?> customers_col_customerID;
    @FXML
    private TableColumn<?, ?> customers_col_total;
    @FXML
    private TableColumn<?, ?> customers_col_date;
    @FXML
    private TableColumn<?, ?> customers_col_cashier;

    private DatabaseManagerMenu databaseManager = new DatabaseManagerMenu();
    
    private ObservableList<customersData> customersListData;

    // Mostra i dati dei clienti nella tabella
    public void customersShowData() {       
		customersListData = databaseManager.customersDataList();
        customers_col_customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customers_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        customers_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customers_col_cashier.setCellValueFactory(new PropertyValueFactory<>("emUsername"));
        customers_tableView.setItems(customersListData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {	
    	super.initialize(location, resources);
        customersShowData();
    }

}