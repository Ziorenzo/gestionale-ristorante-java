package application.Controllers.Admin;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import application.Database.Manager.DatabaseManagerDashboard;
import application.Models.productData;
import application.Models.Base.BaseAdmin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class dashboardformController extends BaseAdmin  {
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private Label dashboard_NC; // Numero di Ricevute
    @FXML
    private Label dashboard_TI; // Totale Vendite Oggi
    @FXML
    private Label dashboard_TotalI; // Totale Vendite
    @FXML
    private Label dashboard_NSP; // Numero di Prodotti Venduti
    @FXML
    private TableView<productData> topSellingTable;
    @FXML
    private TableColumn<productData, String> dashboard_col_idprodotto; // Colonna ID Prodotto
    @FXML
    private TableColumn<productData, String> dashboard_col_nomeprodotto; // Colonna Nome Prodotto
    @FXML
    private TableColumn<productData, Integer> dashboard_col_quantita; // Colonna Quantità
    @FXML
    private ComboBox<String> su_periodo; // ComboBox per selezionare il periodo di visualizzazione
    
    // Elenco dei periodi temporali per la ComboBox
    private String[] periodoList = {"Daily", "Weekly", "Monthly"};
    private DatabaseManagerDashboard databaseManager = new DatabaseManagerDashboard();

    // Mostra il numero di ricevute
    public void dashboardDisplayNC() {
        int receiptCount = databaseManager.getReceiptCount();
        dashboard_NC.setText(String.valueOf(receiptCount));
    }
    
    // Mostra il totale delle vendite di oggi
    public void dashboardDisplayTI() {
        double todayTotalSales = databaseManager.getTodayTotalSales();
        dashboard_TI.setText("$" + todayTotalSales);
    }
    
    // Mostra il totale delle vendite
    public void dashboardTotalI() {
        double totalSales = databaseManager.getTotalSales();
        dashboard_TotalI.setText("$" + totalSales);
    }
    
    // Mostra il numero di prodotti venduti
    public void dashboardNSP() {
        int productsSoldCount = databaseManager.getProductsSoldCount();
        dashboard_NSP.setText(String.valueOf(productsSoldCount));
    }
    
    // Filtra la tabella in base al periodo selezionato
    public void filterTableByPeriod(String selectedPeriod) {
        ObservableList<productData> filteredData = databaseManager.filterTableByPeriod(selectedPeriod);
        
        // Aggiorna la TableView con i nuovi dati
        dashboard_col_idprodotto.setCellValueFactory(new PropertyValueFactory<>("productId"));
        dashboard_col_nomeprodotto.setCellValueFactory(new PropertyValueFactory<>("productName"));
        dashboard_col_quantita.setCellValueFactory(new PropertyValueFactory<>("totalSold"));
        topSellingTable.setItems(filteredData);
    }
    
 // Registra l'elenco dei periodi nella ComboBox
    public void regPeriodoList() {
        List<String> listP = new ArrayList<>();
        
        for (String data : periodoList) {
            listP.add(data);
        }
        
        ObservableList<String> periodoData = FXCollections.observableArrayList(listP);
        su_periodo.setItems(periodoData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	super.initialize(location, resources);
        dashboardDisplayNC();
        dashboardDisplayTI();
        dashboardTotalI();
        dashboardNSP();    
        regPeriodoList();
     
        su_periodo.setValue("Monthly");

        // Chiamata iniziale per mostrare la tabella mensilmente
        filterTableByPeriod("Monthly");
        
        // Aggiunge un listener per la ComboBox per gestire il cambio di periodo
        su_periodo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            filterTableByPeriod(newValue);
        });
    }
}