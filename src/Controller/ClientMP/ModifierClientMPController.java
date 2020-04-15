/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ClientMP;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Manager.ClientMPDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ModifierClientMPController implements Initializable {

    @FXML
    private ComboBox<String> typeClientCombo;
    @FXML
    private TextField nomField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField codeDepotField;
    @FXML
    private PasswordField adresseField;
    @FXML
    private Button btnModif;
    @FXML
    private TextField telField;
    @FXML
    private TextField emailField;

    /**
     * Initializes the controller class.
     */
     ClientMPDAO clientMPDAO =  new ClientMPDAOImpl();
     navigation nav = new navigation();
     ClientMP clientMP;
     private Map<String,ClientMP> mapClientMP=new HashMap<>();
     ObservableList<String> typeclientlist =FXCollections.observableArrayList(Constantes.COMBO_INTERNE,Constantes.COMBO_EXTERNE); 
    @FXML
    private Button btnRafraichir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeClientCombo.setItems(typeclientlist);
    }    

    public void chargerLesDonnees(){

        
        nomField.setText(clientMP.getNom());
        codeField.setText(clientMP.getCode());
        codeDepotField.setText(clientMP.getCodeDepot());
        adresseField.setText(clientMP.getAdresse()); 
        telField.setText(clientMP.getTel());
        emailField.setText(clientMP.getEmail());
        typeClientCombo.setValue(clientMP.getTypeClient());

    }
    
    
    @FXML
    private void ModifierClientAction(ActionEvent event) {
       clientMP.setNom(nomField.getText());
       clientMP.setCode(codeField.getText());
       clientMP.setCodeDepot(codeDepotField.getText());
       clientMP.setAdresse(adresseField.getText());
       clientMP.setTel(telField.getText());
       clientMP.setEmail(emailField.getText());
       clientMP.setTypeClient(typeClientCombo.getSelectionModel().getSelectedItem());
       clientMPDAO.edit(clientMP);
       
       
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, "Modifié avec succès");

        Stage stage = (Stage) btnModif.getScene().getWindow();
           stage.close();

    }

    @FXML
    private void rafraichirClientAction(ActionEvent event) {
    }
    
}
