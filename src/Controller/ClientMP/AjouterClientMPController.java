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
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author hp
 */
public abstract class AjouterClientMPController extends AnchorPane implements Initializable {
  private int POUR;
  ClientMP clientMP;
    @FXML
    private Button btnRafraichir;

    
    
    public AjouterClientMPController (int POUR,ClientMP clientMP){
    this.clientMP= clientMP;
    this.POUR=POUR;
    setAll(nav.getAjouterClientMP(), this);
    }
    
    public static void setAll(String path, Object root){
    FXMLLoader fxmlLoader = new FXMLLoader(root.getClass().getResource(path));
        fxmlLoader.setRoot(root);
        fxmlLoader.setController(root);
        try {
            System.out.println(path);
            fxmlLoader.load();
        } catch (IOException exception){
            throw new RuntimeException(exception);
        }

    }
    public abstract void refresh();
    @FXML
    private TextField emailField;
    @FXML
    private TextField telField;
    @FXML
    private Button btnajou;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField codeDepotField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField nomField;
    @FXML
    private ComboBox <String> typeclientBox;

    ObservableList<String> typeclientlist =FXCollections.observableArrayList(Constantes.COMBO_INTERNE,Constantes.COMBO_EXTERNE);
    ClientMPDAO clientMPDAO =  new ClientMPDAOImpl();
    
     navigation nav = new navigation();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeclientBox.setValue(Constantes.COMBO_INTERNE);
        typeclientBox.setItems(typeclientlist);
        
         int Maxid = clientMPDAO.getMaxId();

        codeField.setText(nav.generCode(Constantes.CLIENT , Maxid));
    }    

    @FXML
    private void ajouterClientAction(ActionEvent event) {
       clientMP =new ClientMP();
   
       
         if (nomField.getText().equalsIgnoreCase("")) {

                   nomField.setStyle("-fx-border-color: red;");
          
        } else {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

               
             clientMPDAO.add(getClientMP()); 
      
              refresh();
              Stage stage = (Stage)
              btnajou.getScene().getWindow();
              stage.close();

            } 
        }

    }
    
     public ClientMP getClientMP(){
     
       clientMP.setNom(nomField.getText());
       clientMP.setCode(codeField.getText());
       clientMP.setCodeDepot(codeDepotField.getText());
       clientMP.setAdresse(adresseField.getText());
       clientMP.setTel(telField.getText());
       clientMP.setEmail(emailField.getText());
       clientMP.setUtilisateurCreation(nav.getUtilisateur());
       clientMP.setTypeClient(typeclientBox.getSelectionModel().getSelectedItem());
       clientMP.setDateCreation(new Date());
       
    return clientMP;
     
     
     }

    @FXML
    private void rafraichirClientAction(ActionEvent event) {
    }
}
