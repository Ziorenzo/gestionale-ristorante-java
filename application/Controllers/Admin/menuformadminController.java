package application.Controllers.Admin;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import application.Database.Manager.DatabaseManagerMenu;
import application.Models.data;
import application.Models.productData;
import application.Models.Base.BaseAdmin;
import application.Models.CommandPattern.RemoveFromCartCommand;
import application.Models.Prototype.ReceiptGenerator;
import application.Models.Prototype.Ricevuta;
import application.Models.Strategy.PaymentMethodSelector;
import application.Models.Strategy.PaymentStrategy;
import application.Models.Utils.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class menuformadminController extends BaseAdmin {
	// Dichiarazione dei componenti dell'interfaccia utente
	@FXML
	private AnchorPane menu_form;
	@FXML
	private TableView<productData> menu_tableView;
	@FXML
	private TableColumn<?, ?> menu_col_productName;
	@FXML
	private TableColumn<?, ?> menu_col_quantity;
	@FXML
	private TableColumn<?, ?> menu_col_price;
	@FXML
	private TableColumn<?, ?> menu_col_customer_id;
	@FXML
	private TableColumn<productData, String> menu_col_employee;
	@FXML
	private TextField menu_table;
	@FXML
	private Label menu_total;
	@FXML
	private TextField menu_amount;
	@FXML
	private Label menu_change;
	@FXML
	private ComboBox<String> su_pagamento;
	@FXML
	private Button menu_payBtn;
	@FXML
	private Button menu_removeBtn;
	@FXML
	private Button menu_receiptBtn;
	

	
    private String selectedTable;
    private String[] pagamentoList = {"Cash", "Credit Card", "Debit Card"};
    private double amount;
    private double change;
    private Date date = new Date();
    private java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    private Ricevuta ricevuta;
    private double totalP;
    private int getid;
    private DatabaseManagerMenu databaseManager = new DatabaseManagerMenu();
    private PaymentMethodSelector paymentMethodSelector = new PaymentMethodSelector();
    private ObservableList<productData> menuOrderListData;
    
    
    public void removeOrderFromCart() {
    	if (getid == 0) {
        	AlertUtil.showErrorAlert("Please select the order you want to remove");          	
        } else {
            databaseManager.removeOrderFromCart(getid);
            menuShowOrderData();
            AlertUtil.showSuccessAlert("The order has been removed.");     
        }
    }

 
    // Metodo per visualizzare i dati degli ordini
    public void menuShowOrderData() {
        menuOrderListData = databaseManager.menuGetOrder();
        
        menu_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        menu_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        menu_col_customer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        menu_col_employee.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        
        menu_tableView.setItems(menuOrderListData);
    }
    
 // Metodo per selezionare un ordine
    public void menuSelectOrder() {
    	productData prod = (productData) menu_tableView.getSelectionModel().getSelectedItem();
        int num = menu_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        // Imposta il tavolo corretto nella casella di testo
        menu_table.setText(prod.getCustomer_id());

        // Imposta l'ID dell'ordine selezionato
        getid = prod.getId();
        
        // Aggiorna il totale in base al tavolo selezionato
        setTotalForTable();
    }
    
 // Metodo per calcolare l'importo
    public void menuAmount() {
        if (menu_table.getText().isEmpty()) {           
            AlertUtil.showErrorAlert("Please enter the table number!");
        } else {
            
             selectedTable = menu_table.getText();
            double tableTotal = databaseManager.calculateTotalByTable(selectedTable);
            
            if (tableTotal == 0) {               
                AlertUtil.showErrorAlert("No orders found for this table or they are not delivered yet!");
            } else {
                if (menu_amount.getText().isEmpty()) {
                	AlertUtil.showErrorAlert("Invalid input");               	
                } else {
                    amount = Double.parseDouble(menu_amount.getText());
                    if (amount < tableTotal) {
                        menu_amount.setText("");
                    } else {
                        change = (amount - tableTotal);
                        menu_change.setText("$" + change);
                        menu_total.setText("$" + tableTotal);
                    }
                }
            }
        }
    }
     
    private void setTotalForTable() {
         selectedTable = menu_table.getText();
        totalP = databaseManager.calculateTotalByTable(selectedTable);
        if (totalP == 0) {
        	AlertUtil.showErrorAlert("No orders found for this table!");        	
        } else {   
            menu_total.setText("$" + totalP); // Aggiorna l'etichetta del totale
        }
    }
    
    //Metodo per il pagamento
    public void menuPayBtn() {
    	 selectedTable = menu_table.getText();
        if (menu_table.getText().isEmpty()) {
        	AlertUtil.showErrorAlert("Please enter the table number!");           	
        } else {
            
             totalP = databaseManager.calculateTotalByTable(selectedTable);
            
            if (totalP == 0) {
            	AlertUtil.showErrorAlert("No orders found for this table!");               
            } else {
                
                
                String selectedPaymentMethod = (String) su_pagamento.getSelectionModel().getSelectedItem();
                
                PaymentStrategy paymentStrategy = paymentMethodSelector.selectPaymentMethod(selectedPaymentMethod);
                
                if (paymentStrategy != null) {
                    // Esegui il pagamento utilizzando lo Strategy Pattern
                    paymentStrategy.makePayment(totalP);                
                }   
                
                databaseManager.insertPayment(selectedTable, selectedPaymentMethod, totalP, sqlDate, data.username);      
                ricevuta = ReceiptGenerator.generateReceipt(selectedPaymentMethod, databaseManager.getIdreceipt(), selectedTable, totalP, sqlDate, data.username, Integer.parseInt(menu_table.getText()));
            
                // Mostra un alert per indicare che il pagamento è avvenuto con successo.
                AlertUtil.showSuccessAlert("Payment has been successfully processed.");                
                menuShowOrderData();
                menuRestart();
    }}
        }
    
    
  
  //Metodo per mostrare la ricevuta a schermo
    public void menuReceiptBtn() {
        AlertUtil.showReceiptAlert(ricevuta);
    }
    
  //Metodo per resettare i valori del menu
    public void menuRestart() {
        totalP = 0;
        change = 0;
        amount = 0;
        menu_total.setText("$0.0");
        menu_amount.setText("");
        menu_change.setText("$0.0");
        menu_table.setText("");
    }
    
    public void regPagamentoList() {
    	List<String> listP = new ArrayList<>();
    	
    	for (String data : pagamentoList) {
            listP.add(data);
        }
    	
    	ObservableList<String> pagamentoData = FXCollections.observableArrayList(listP);
    	su_pagamento.setItems(pagamentoData);
    }
   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		super.initialize(arg0, arg1);
		databaseManager.menuGetOrder();
        menuShowOrderData();
        regPagamentoList();
    
        menu_table.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
            	setTotalForTable();
            }
        });

        
	}
	
}