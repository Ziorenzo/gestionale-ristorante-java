package application.Controllers.Admin;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import application.Database.Manager.DatabaseManagerInventory;
import application.Models.data;
import application.Models.productData;
import application.Models.Base.BaseAdmin;
import application.Models.Utils.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class inventory_formController extends BaseAdmin {
    
    @FXML
    private AnchorPane inventory_form;   
    @FXML
    private TableView<productData> inventory_tableView;
    @FXML
    private TableColumn<productData, String> inventory_col_productID;
    @FXML
    private TableColumn<productData, String> inventory_col_productName;
    @FXML
    private TableColumn<productData, String> inventory_col_type;
    @FXML
    private TableColumn<productData, String> inventory_col_stock;
    @FXML
    private TableColumn<productData, String> inventory_col_price;
    @FXML
    private TableColumn<productData, String> inventory_col_status; 
    @FXML
    private TableColumn<productData, String> inventory_col_date;
    @FXML
    private ImageView inventory_imageView;
    @FXML
    private Button inventory_importBtn;
    @FXML
    private Button inventory_addBtn;
    @FXML
    private Button inventory_updateBtn;
    @FXML
    private Button inventory_clearBtn;
    @FXML
    private Button inventory_deleteBtn;
    @FXML
    private TextField inventory_productID;
    @FXML
    private TextField inventory_productName;
    @FXML
    private TextField inventory_stock;
    @FXML
    private TextField inventory_price;
    @FXML
    private ComboBox<?> inventory_status;
    @FXML
    private ComboBox<?> inventory_type;
    
    private Alert alert;
    private Image image;
    private ObservableList<productData> inventoryListData;
    private DatabaseManagerInventory databaseManager = new DatabaseManagerInventory();
    private String[] typeList = {"Meals", "Drinks"};
	private String[] statusList = {"Available", "Unavailable"};
    	
	// Metodo per selezionare dati nell'inventario
	public void inventorySelectData() {
        
        productData prodData = inventory_tableView.getSelectionModel().getSelectedItem();
        int num = inventory_tableView.getSelectionModel().getSelectedIndex();
        
        if ((num - 1) < -1) {
            return;
        }
        
        inventory_productID.setText(prodData.getProductId());
        inventory_productName.setText(prodData.getProductName());
        inventory_stock.setText(String.valueOf(prodData.getStock()));
        inventory_price.setText(String.valueOf(prodData.getPrice()));
        
        data.path = prodData.getImage();
        
        String path = "File:" + prodData.getImage();
        data.date = String.valueOf(prodData.getDate());
        data.id = prodData.getId();
        
        image = new Image(path, 120, 127, false, true);
        inventory_imageView.setImage(image);
    }
	
	 // Metodo per importare un'immagine nell'inventario
	public void inventoryImportBtn() {
	    FileChooser openFile = new FileChooser();
	    openFile.getExtensionFilters().addAll(
	        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
	    );

	    File file = openFile.showOpenDialog(main_form.getScene().getWindow());

	    if (file != null) {
	        try {
	            data.path = file.toURI().toString();
	            image = new Image(data.path, 120, 127, false, true);
	            inventory_imageView.setImage(image);
	        } catch (Exception e) {
	            e.printStackTrace();
	            AlertUtil.showErrorAlert("Failed to load the image. Please make sure it's a valid image file.");
	        }
	    }
	}


	// Metodo per aggiungere un elemento all'inventario
	 public void inventoryAddBtn() {
	        if (inventory_productID.getText().isEmpty()
	                || inventory_productName.getText().isEmpty()
	                || inventory_type.getSelectionModel().getSelectedItem() == null
	                || inventory_stock.getText().isEmpty()
	                || inventory_price.getText().isEmpty()
	                || inventory_status.getSelectionModel().getSelectedItem() == null
	                || data.path == null) {
	            AlertUtil.showErrorAlert("Please fill all blank fields");
	        } else {
	            String prodID = inventory_productID.getText();
	            String prodName = inventory_productName.getText();
	            String type = (String) inventory_type.getSelectionModel().getSelectedItem();
	            int stock = Integer.parseInt(inventory_stock.getText());
	            double price = Double.parseDouble(inventory_price.getText());
	            String status = (String) inventory_status.getSelectionModel().getSelectedItem();
	            String path = data.path;
	            path = path.replace("\\", "\\\\");
	            String date = data.date;

	            boolean success = databaseManager.addProduct(prodID, prodName, type, stock, price, status, path, date);
	            
	            if (success) {
	                AlertUtil.showSuccessAlert("Successfully Added!");
	                // Aggiorna la visualizzazione dei dati nell'inventario
	                inventoryShowData();
	                // Cancella i campi del form
	                inventoryClearBtn();
	            } else {
	                AlertUtil.showErrorAlert("Failed to add the product.");
	            }
	        }
	    }
	    // Metodo per aggiornare un elemento nell'inventario
	 public void inventoryUpdateBtn() {
	        if (inventory_productID.getText().isEmpty()
	                || inventory_productName.getText().isEmpty()
	                || inventory_type.getSelectionModel().getSelectedItem() == null
	                || inventory_stock.getText().isEmpty()
	                || inventory_price.getText().isEmpty()
	                || inventory_status.getSelectionModel().getSelectedItem() == null
	                || data.path == null || data.id == 0) {
	            AlertUtil.showErrorAlert("Please fill all blank fields");
	        } else {
	            int id = data.id;
	            String prodID = inventory_productID.getText();
	            String prodName = inventory_productName.getText();
	            String type = (String) inventory_type.getSelectionModel().getSelectedItem();
	            int stock = Integer.parseInt(inventory_stock.getText());
	            double price = Double.parseDouble(inventory_price.getText());
	            String status = (String) inventory_status.getSelectionModel().getSelectedItem();
	            String path = data.path;
	            path = path.replace("\\", "\\\\");
	            String date = data.date;

	            boolean success = databaseManager.updateProduct(id, prodID, prodName, type, stock, price, status, path, date);

	            if (success) {
	                AlertUtil.showSuccessAlert("Successfully Updated!");
	                // Aggiorna la visualizzazione dei dati nell'inventario
	                inventoryShowData();
	                // Cancella i campi del form
	                inventoryClearBtn();
	            } else {
	                AlertUtil.showErrorAlert("Failed to update the product.");
	            }
	        }
	    }
	 
	 // Metodo per eliminare un elemento dall'inventario
	 public void inventoryDeleteBtn() {
	        if (data.id == 0) {
	            AlertUtil.showErrorAlert("Please fill all blank fields");
	        } else {
	            alert = new Alert(AlertType.CONFIRMATION);
	            alert.setTitle("Confirmation Message");
	            alert.setHeaderText(null);
	            alert.setContentText("Are you sure you want to DELETE Product ID: " + inventory_productID.getText() + "?");
	            Optional<ButtonType> option = alert.showAndWait();

	            if (option.get().equals(ButtonType.OK)) {
	                int id = data.id;
	                boolean success = databaseManager.deleteProduct(id);

	                if (success) {
	                    AlertUtil.showSuccessAlert("Successfully Deleted!");
	                    // Aggiorna la visualizzazione dei dati nell'inventario
	                    inventoryShowData();
	                    // Cancella i campi del form
	                    inventoryClearBtn();
	                } else {
	                    AlertUtil.showErrorAlert("Failed to delete the product.");
	                }
	            } else {
	                AlertUtil.showErrorAlert("Cancelled");
	            }
	        }
	    }
	
	  
	// Metodo per mostrare i dati nell'inventario
	 public void inventoryShowData() {
		    List<productData> productList = databaseManager.getAllProducts();
		    inventoryListData = FXCollections.observableArrayList(productList);

		    inventory_col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
		    inventory_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
		    inventory_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
		    inventory_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		    inventory_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		    inventory_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
		    inventory_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

		    inventory_tableView.setItems(inventoryListData);
		}
	
	 
	 
	 // Metodo per cancellare i campi del form dell'inventario
	 public void inventoryClearBtn() {
	        
	        inventory_productID.setText("");
	        inventory_productName.setText("");
	        inventory_type.getSelectionModel().clearSelection();
	        inventory_stock.setText("");
	        inventory_price.setText("");
	        inventory_status.getSelectionModel().clearSelection();
	        data.path = "";
	        data.id = 0;
	        inventory_imageView.setImage(null);
	        
	    }
	
	    public void inventoryTypeList() {
	        List<String> typeL = new ArrayList<>();
	        for (String data : typeList) {
	            typeL.add(data);
	        }
	        ObservableList listData = FXCollections.observableArrayList(typeL);
	        inventory_type.setItems(listData);
	    }
	
	 // Metodo per popolare la lista degli stati di prodotto nell'inventario
	    public void inventoryStatusList() {
	        List<String> statusL = new ArrayList<>();
	        for (String data : statusList) {
	            statusL.add(data);
	        }
	        ObservableList listData = FXCollections.observableArrayList(statusL);
	        inventory_status.setItems(listData);  
	    }
	 
	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	    	super.initialize(location, resources);
            inventoryTypeList();
	        inventoryStatusList();
	        inventoryShowData();
	    }
	
}
