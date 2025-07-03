package application.Controllers.Employee;

import java.net.URL;
import java.util.ResourceBundle;
import application.Database.Manager.DatabaseManagerCard;
import application.Models.data;
import application.Models.productData;
import application.Models.ChainofResponsibility.BevandeHandler;
import application.Models.ChainofResponsibility.CucinaHandler;
import application.Models.ChainofResponsibility.RepartoHandler;
import application.Models.CommandPattern.AddToCartCommand;
import application.Models.Utils.AlertUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class cardProductController implements Initializable {

    @FXML
    private AnchorPane card_form;
    @FXML
    private Label prod_name;
    @FXML
    private Label prod_price;
    @FXML
    private ImageView prod_imageView;
    @FXML
    private Spinner<Integer> prod_spinner;
    @FXML
    private Spinner<Integer> prod_tavolo;
    @FXML
    private Button prod_addBtn;
    
    private Image image;
    private String prodID;
    private String type;
    private String prod_image;
    private SpinnerValueFactory<Integer> spin;
    private SpinnerValueFactory<Integer> spin_tavolo;
    private AddToCartCommand addToCartCommand;
    private int customerID;
    private int qty;
    private int tvl;
    private double totalP;
    private double pr;
    private DatabaseManagerCard databaseManager = new DatabaseManagerCard();
    private RepartoHandler chain;
   
 
    public cardProductController() {
        
        RepartoHandler cucinaHandler = new CucinaHandler();
        RepartoHandler bevandeHandler = new BevandeHandler();
        
        cucinaHandler.setNextHandler(bevandeHandler);
        
        chain = cucinaHandler; // Rappresenta la catena di gestori
    }
    
    // Imposta i dati del prodotto nella vista
    public void setData(productData prodData) {
        prod_image = prodData.getImage();
        String.valueOf(prodData.getDate());
        type = prodData.getType();
        prodID = prodData.getProductId();
        prod_name.setText(prodData.getProductName());
        prod_price.setText("$" + String.valueOf(prodData.getPrice()));
        String path = "File:" + prodData.getImage();
        image = new Image(path, 190, 94, false, true);
        prod_imageView.setImage(image);
        pr = prodData.getPrice();
    }

    // Imposta il valore iniziale dello spinner per il tavolo
    public void setTavolo() {
        spin_tavolo = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_tavolo.setValueFactory(spin_tavolo);
    }

    // Imposta il valore iniziale dello spinner per la quantitÃ 
    public void setQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_spinner.setValueFactory(spin);
    }

    // Aggiungi l'ordine al carrello
    public void addOrderToCart() {
        tvl = prod_tavolo.getValue();
        qty = prod_spinner.getValue();
        String check = "";

        int checkStck = databaseManager.getStock(prodID);

        if (checkStck == 0) {
            databaseManager.updateProductStock(prodID, 0, pr, "Unavailable");
        }

        check = databaseManager.getProductStatus(prodID);

        if (!check.equals("Available") || qty == 0) {
            AlertUtil.showErrorAlert("Something went wrong!");
        } else {

            if (checkStck < qty) {
                AlertUtil.showErrorAlert("Not valid. This product is unavailable");
            } else {
                prod_image = prod_image.replace("\\", "\\\\");
                totalP = (qty * pr);
                customerID = databaseManager.insertOrderToCart(prodID, prod_name.getText(), type, qty, totalP, prod_image, data.username, "In preparazione", tvl);

                int upStock = checkStck - qty;

                databaseManager.updateProductStock(prodID, upStock, pr, check);

                AlertUtil.showSuccessAlert("Successfully Added!");
            }
        }
        chain.preparaOrdine(customerID, qty, type); // Passa la richiesta alla catena di gestori
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTavolo();
        setQuantity();
        
        addToCartCommand = new AddToCartCommand(this);
        // Aggiungi un gestore degli eventi al pulsante "Aggiungi"
        prod_addBtn.setOnAction(event -> {
            // Esegui il comando quando il pulsante "Aggiungi" viene premuto
            addToCartCommand.execute();
        });
    }
}