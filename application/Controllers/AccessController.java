package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Database.Manager.DatabaseManagerMaina;
import application.Models.data;
import application.Models.Utils.AlertUtil;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller principale per la schermata di accesso e registrazione.
 */

public class AccessController implements Initializable {
	// Dichiarazioni delle variabili FXML
    @FXML
    private AnchorPane si_loginForm;
    
    @FXML
    private TextField si_username;
    
    @FXML
    private PasswordField si_password;
    
    @FXML
    private Button si_loginBtn;
    
    @FXML
    private Hyperlink si_forgotPass;
    
    @FXML
    private AnchorPane su_signupForm;
    
    @FXML
    private TextField su_username;
    
    @FXML
    private PasswordField su_password;
    
    @FXML
    private ComboBox<?> su_question;
    
    @FXML
    private ComboBox<?> su_role;
    
    @FXML
    private TextField su_answer;
    
    @FXML
    private Button su_signupBtn;
    
    @FXML
    private TextField fp_username;
    
    @FXML
    private AnchorPane fp_questionForm;
    
    @FXML
    private Button fp_proceedBtn;
    
    @FXML
    private ComboBox<?> fp_question;
    
    @FXML
    private TextField fp_answer;
    
    @FXML
    private Button fp_back;
    
    @FXML
    private AnchorPane np_newPassForm;
    
    @FXML
    private PasswordField np_newPassword;
    
    @FXML
    private PasswordField np_confirmPassword;
    
    @FXML
    private Button np_changePassBtn;
    
    @FXML
    private Button np_back;
    
    @FXML
    private AnchorPane side_form;
    
    @FXML
    private Button side_CreateBtn;
    
    @FXML
    private Button side_alreadyHave;
    // Elenco delle domande e dei ruoli per la registrazione
    private String[] questionList = {"What is your favorite Color?", "What is your favorite food?", "what is your birth date?"};
    private String[] roleList = {"administrator", "employee"};
    DatabaseManagerMaina databaseManager = new DatabaseManagerMaina();
    
    // Metodo chiamato quando si preme il pulsante di accesso.
     
    public void loginBtn() {
        if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Incorrect Username/Password");
        } else {
            String username = si_username.getText();
            String password = si_password.getText();
            String userRole = databaseManager.getUserRole(username);

            if (userRole != null && databaseManager.login(username, password)) {
                data.username = username;
                AlertUtil.showSuccessAlert("Successfully Login!");

                try {
                    Parent root;
                    Stage stage = new Stage();
                    Scene scene;

                    if (userRole.equals("employee")) {
                        root = FXMLLoader.load(getClass().getResource("/application/menuform.fxml"));
                    } else if (userRole.equals("administrator")) {
                        root = FXMLLoader.load(getClass().getResource("/application/dashboardform.fxml"));
                    } else {
                        AlertUtil.showErrorAlert("Unknown user role.");
                        return;
                    }

                    scene = new Scene(root);
                    stage.setTitle("My Restaurant");
                    stage.setMinWidth(1100);
                    stage.setMinHeight(600);
                    stage.setScene(scene);
                    stage.show();

                    si_loginBtn.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                AlertUtil.showErrorAlert("Incorrect Username/Password");
            }
        }
    }

    // Metodo chiamato quando si preme il pulsante di registrazione.
    public void regBtn() {
        if (su_username.getText().isEmpty() || su_password.getText().isEmpty()
                || su_question.getSelectionModel().getSelectedItem() == null
                || su_answer.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Please fill all blank fields");
        } else {
            String username = su_username.getText();
            String password = su_password.getText();
            String question = (String) su_question.getSelectionModel().getSelectedItem();
            String answer = su_answer.getText();
            String role = (String) su_role.getSelectionModel().getSelectedItem();

            if (databaseManager.isUsernameTaken(username)) {
                AlertUtil.showErrorAlert(username + " is already taken");
            } else if (password.length() < 8) {
                AlertUtil.showErrorAlert("Invalid Password, at least 8 characters are needed");
            } else {
                if (databaseManager.register(username, password, question, answer, role)) {
                    AlertUtil.showSuccessAlert("Successfully registered Account!");
                    su_username.setText("");
                    su_password.setText("");
                    su_question.getSelectionModel().clearSelection();
                    su_answer.setText("");
                } else {
                    AlertUtil.showErrorAlert("Error during registration.");
                }
            }
        }
    }
    
  
    // Metodo chiamato quando si preme il pulsante proceed nella schermata di recupero password.
    public void proceedBtn() {
        if (fp_username.getText().isEmpty() || fp_question.getSelectionModel().getSelectedItem() == null
                || fp_answer.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Please fill all blank fields");
        } else {
            String username = fp_username.getText();
            String question = (String) fp_question.getSelectionModel().getSelectedItem();
            String answer = fp_answer.getText();

            // Utilizza il DatabaseManagerMaina per controllare la correttezza delle informazioni.
            if (databaseManager.checkSecurityQuestion(username, question, answer)) {
                np_newPassForm.setVisible(true);
                fp_questionForm.setVisible(false);
            } else {
                AlertUtil.showErrorAlert("Incorrect Information");
            }
        }
    }
    
  //  Metodo chiamato quando si preme il pulsante "change password" nella schermata di cambio password.
    public void changePassBtn() {
        if (np_newPassword.getText().isEmpty() || np_confirmPassword.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Please fill all blank fields");
        } else {
            String newPassword = np_newPassword.getText();
            String username = fp_username.getText();
            String question = (String) fp_question.getSelectionModel().getSelectedItem();
            String answer = fp_answer.getText();

            if (newPassword.length() < 8) {
                AlertUtil.showErrorAlert("Invalid Password, at least 8 characters are needed");
            } else if (newPassword.equals(np_confirmPassword.getText())) {
                // Utilizza il DatabaseManagerMaina per effettuare il cambio della password.
                if (databaseManager.changePassword(username, newPassword, question, answer)) {
                    AlertUtil.showSuccessAlert("Successfully changed Password!");
                    si_loginForm.setVisible(true);
                    np_newPassForm.setVisible(false);
                    
                    // Resetta i campi
                    np_confirmPassword.setText("");
                    np_newPassword.setText("");
                    fp_question.getSelectionModel().clearSelection();
                    fp_answer.setText("");
                    fp_username.setText("");
                } else {
                    AlertUtil.showErrorAlert("Error during password change.");
                }
            } else {
                AlertUtil.showErrorAlert("Passwords do not match.");
            }
        }
    }
    
    // Cambia tra la schermata di accesso e quella di registrazione.
    public void switchForm(ActionEvent event) {
        
        TranslateTransition slider = new TranslateTransition();
        
        if (event.getSource() == side_CreateBtn) {
            slider.setNode(side_form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));
            
            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyHave.setVisible(true);
                side_CreateBtn.setVisible(false);
                
                fp_questionForm.setVisible(false);
                si_loginForm.setVisible(true);
                np_newPassForm.setVisible(false);
                
                regLquestionList();
            });
            
            slider.play();
        } else if (event.getSource() == side_alreadyHave) {
            slider.setNode(side_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));
            
            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyHave.setVisible(false);
                side_CreateBtn.setVisible(true);      
                fp_questionForm.setVisible(false);
                si_loginForm.setVisible(true);
                np_newPassForm.setVisible(false);
            });
            
            slider.play();
        }
        
    }
    
    //Torna alla schermata di accesso.
    public void backToLoginForm(){
        si_loginForm.setVisible(true);
        fp_questionForm.setVisible(false);
    }
    // Torna alla schermata delle domande nella schermata di recupero password.   
    public void backToQuestionForm(){
        fp_questionForm.setVisible(true);
        np_newPassForm.setVisible(false);
    }
    
    //Passa alla schermata di recupero password.
    public void switchForgotPass() {
        fp_questionForm.setVisible(true);
        si_loginForm.setVisible(false);
        
        forgotPassQuestionList();
    }
    
    // Popola le liste delle domande per la registrazione.
    public void regLquestionList() {
        List<String> listQ = new ArrayList<>();
        List<String> listR = new ArrayList<>();
        
        
        for (String data : questionList) {
            listQ.add(data);
        }
        
        for (String data : roleList) {
            listR.add(data);
        }
        
        
        ObservableList listData = FXCollections.observableArrayList(listQ);
        ObservableList roleData = FXCollections.observableArrayList(listR);
        
        su_question.setItems(listData);
        su_role.setItems(roleData);
    }
    // Popola la lista delle domande nella schermata di recupero password.
    public void forgotPassQuestionList() {
        
        List<String> listQ = new ArrayList<>();
        
        for (String data : questionList) {
            listQ.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(listQ);
        fp_question.setItems(listData);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
