/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Utilisateur;

import Utils.Constantes;
import dao.Entity.Depot;
import dao.Entity.Utilisateur;
import dao.Manager.DepotDAO;
import dao.Manager.UtilisateurDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.UtilisateurDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */


public class ModifierUtilisateurController implements Initializable {

    @FXML
    private Button btnModif;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField nomField;
    @FXML
    private ComboBox<String> depotCombo;


  UtilisateurDAO utilisateurDAO =  new UtilisateurDAOImpl();
     navigation nav = new navigation();

    
    public Utilisateur utilisateur;
    DepotDAO depotDAO = new DepotDAOImpl();
    private Map<String,Depot> mapDepot=new HashMap<>();
    @FXML
    private Button btnRafraichir;
    @FXML
    private RadioButton siegeRadio;
    @FXML
    private ToggleGroup typeUtil;
    @FXML
    private RadioButton regionRadio;
   
  
    
    public void chargerLesDonnees(){
        
        if (utilisateur.getTypeUtilisateur().equals(Constantes.SIEGE)){
        siegeRadio.setSelected(true);
        }else {
        regionRadio.setSelected(true);
        }
        
        depotCombo.setValue(utilisateur.getDepot().getLibelle());
        nomField.setText(utilisateur.getNom());
        loginField.setText(utilisateur.getLogin());
        passwordField.setText(utilisateur.getPassword()); 
  
    }
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           List<Depot> listDepot=depotDAO.findAll();
        
        listDepot.stream().map((depot) -> {
            depotCombo.getItems().addAll((depot.getLibelle()));
            return depot;
        }).forEachOrdered((depot) -> {
            mapDepot.put(depot.getLibelle(), depot);
        });
        
       }    

    @FXML
    private void ModifierUtilisateurAction(ActionEvent event) {

          Depot depot = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
          
           if (siegeRadio.isSelected()){
             
              utilisateur.setTypeUtilisateur(Constantes.SIEGE);
              
             }else if(regionRadio.isSelected()){
               utilisateur.setTypeUtilisateur(Constantes.REGION);
             }
           
       utilisateur.setDepot(depot);
       utilisateur.setNom(nomField.getText());
       utilisateur.setLogin(loginField.getText());
       utilisateur.setPassword(passwordField.getText());
       utilisateurDAO.edit(utilisateur);
      
       Stage stage = (Stage) btnModif.getScene().getWindow();
       stage.close();
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.MODIFIER_ENREGISTREMENT);
      

    }

    @FXML
    private void depotComboOnAction(ActionEvent event) {
    }

    @FXML
    private void rafraichirFourAction(ActionEvent event) {
    }
}
    

