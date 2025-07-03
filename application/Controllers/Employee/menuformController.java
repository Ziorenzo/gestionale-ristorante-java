package application.Controllers.Employee;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Database.Manager.DatabaseManagerMenu;
import application.Models.MenuCardDisplayManager;
import application.Models.productData;
import application.Models.Base.BaseEmployee;
import application.Models.CommandPattern.RemoveFromCartCommand;
import application.Models.Utils.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class menuformController extends BaseEmployee {
	@FXML
	private AnchorPane menu_form;
	@FXML
	private ScrollPane menu_scrollPane;
	@FXML
	private GridPane menu_gridPane;
	@FXML
	private TableView<productData> menu_tableView;
	@FXML
    private ComboBox<?> su_pagamento;
	@FXML
    private ComboBox<String> su_categoria;
	@FXML
	private TableColumn<?, ?> menu_col_productName;
	@FXML
	private TableColumn<?, ?> menu_col_quantity;
	@FXML
	private TableColumn<?, ?> menu_col_price;
	@FXML
	private TableColumn<?, ?> menu_col_customer_id;
	@FXML
    private Button menu_cucina;
    @FXML
    private Button menu_bevande;
	@FXML
	private Label menu_change;
	@FXML
	private TextField menu_table;
	@FXML
	private Button menu_payBtn;
	@FXML
	private Button menu_removeBtn;

	private RemoveFromCartCommand removeFromCartCommand;
    private String[] categoriaList = {"All","Meals", "Drinks"};
    private int getid;
    private DatabaseManagerMenu databaseManager = new DatabaseManagerMenu();
    private ObservableList<productData> menuOrderListData;
    // Metodo chiamato quando il pulsante "Rimuovi" viene premuto
    @FXML
    public void handleRemoveButtonClick(ActionEvent event) {
        if (removeFromCartCommand != null) {
            removeFromCartCommand.execute();
        }
    }
   
 // Metodo per mostrare i dati degli ordini nella tabella
    public void menuShowOrderData() {
        menuOrderListData = databaseManager.menuGetOrder();   
        menu_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        menu_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        menu_col_customer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));  
        menu_tableView.setItems(menuOrderListData);
    }
    
 // Metodo per selezionare un ordine dalla tabella
    public void menuSelectOrder() {
        productData prod = menu_tableView.getSelectionModel().getSelectedItem();
        int num = menu_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        // Imposta l'ID dell'ordine selezionato
        getid = prod.getId();
        
        }
    
 // Metodo per rimuovere un ordine dal carrello
    public void removeOrderFromCart() {
        if (getid == 0) {
            AlertUtil.showErrorAlert("Please select the order you want to remove");
        } else {
            databaseManager.removeOrderFromCart(getid);
            menuShowOrderData();
        }
    }
    
    // Metodo per registrare le categorie nella ComboBox
    public void regCategoriaList() {
    	List<String> listP = new ArrayList<>();
    	
    	for (String data : categoriaList) {
            listP.add(data);
        }
    	
    	ObservableList categoriaData = FXCollections.observableArrayList(listP);
    	su_categoria.setItems(categoriaData);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		MenuCardDisplayManager.displayMenuCard(menu_gridPane, databaseManager.getProductsByCategory(null));
        databaseManager.menuGetOrder();
        menuShowOrderData();
        regCategoriaList();
 
        removeFromCartCommand = new RemoveFromCartCommand(this);
        // Aggiungi un gestore degli eventi al pulsante "Rimuovi"
        menu_removeBtn.setOnAction(event -> {
            // Esegui il comando quando il pulsante "Rimuovi" viene premuto
            removeFromCartCommand.execute();
        });
        
        su_categoria.setOnAction(event -> {
            String selectedCategory = su_categoria.getSelectionModel().getSelectedItem();
            if ("All".equals(selectedCategory)) {
            	MenuCardDisplayManager.displayMenuCard(menu_gridPane, databaseManager.getProductsByCategory(null));
            } else {
            	MenuCardDisplayManager.displayMenuCard(menu_gridPane, databaseManager.getProductsByCategory(selectedCategory));
            }
        });
	}
	
}