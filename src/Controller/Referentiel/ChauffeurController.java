/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.CategorieMp;
import dao.Entity.Chauffeur;
import dao.Manager.ChauffeurDAO;
import dao.ManagerImpl.ChauffeurDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ChauffeurController implements Initializable {

    @FXML
    private TableView<Chauffeur> tableDimension;
    @FXML
    private TableColumn<Chauffeur, String> codeColumn;
    @FXML
    private TableColumn<Chauffeur, String> chauffeurColumn;
    @FXML
    private TableColumn<Chauffeur, String> matriculeColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField codeField;
    @FXML
    private TextField matriculeMod;
    @FXML
    private TextField chauffeurMod;

    /**
     * Initializes the controller class.
     */
    
      private final ObservableList<Chauffeur> listeChauffeur=FXCollections.observableArrayList();
            
     ChauffeurDAO chauffeurDAO = new ChauffeurDAOImpl();
     navigation nav = new navigation();
     Chauffeur chauffeur= new Chauffeur();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            setColumnProperties();
        loadDetail();
        
        // TODO
    }    

       void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        chauffeurColumn.setCellValueFactory(new PropertyValueFactory<>("chauffeur"));
         
        matriculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
    
     }
    
     void loadDetail(){
        
        listeChauffeur.clear();
        listeChauffeur.addAll(chauffeurDAO.findAll());
        tableDimension.setItems(listeChauffeur);
    }
    
    
    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
        
               Integer val= tableDimension.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              codeField.setText(codeColumn.getCellData(val));
              chauffeurMod.setText(chauffeurColumn.getCellData(val));
              matriculeMod.setText(matriculeColumn.getCellData(val));

          }
        
    }

    @FXML
    private void btnAjouterOnAction(ActionEvent event) {
        
        
        if (codeField.getText().isEmpty()||
            chauffeurMod.getText().isEmpty()||
            matriculeMod.getText().isEmpty()){
        
              nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
              return;
        }else{
        
        chauffeur = new Chauffeur();

        chauffeur.setCode(codeField.getText());   
        chauffeur.setChauffeur(chauffeurMod.getText());
        chauffeur.setMatricule(matriculeMod.getText());
        chauffeur.setDateCreation(new Date());
        chauffeur.setUtilisateurCreation(nav.getUtilisateur());
        chauffeurDAO.add(chauffeur);
        
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);


        codeField.setText("");
        chauffeurMod.setText("");
        matriculeMod.setText("");
        
     setColumnProperties();
        loadDetail();
     
         
        }
    }

    @FXML
    private void btnModifierOnAction(ActionEvent event) {
        
          if (tableDimension.getSelectionModel().getSelectedItem() != null) {
        
          chauffeur=tableDimension.getSelectionModel().getSelectedItem();
       
       chauffeur.setCode(codeField.getText());
       chauffeur.setChauffeur(chauffeurMod.getText());
       chauffeur.setMatricule(matriculeMod.getText());
          chauffeurDAO.edit(chauffeur);
          
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);

         codeField.setText("");
        chauffeurMod.setText("");
        matriculeMod.setText("");
       
      setColumnProperties();
      loadDetail();
      
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
        
    }

    @FXML
    private void btnSupprimerOnAction(ActionEvent event) {
        
             if(tableDimension.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
             
       Chauffeur chauffeur=tableDimension.getSelectionModel().getSelectedItem();

            chauffeurDAO.delete(chauffeur);
        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        codeField.setText("");
        chauffeurMod.setText("");
        matriculeMod.setText("");
        
        setColumnProperties();
      loadDetail();  
    
    }
        
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        
                codeField.setText("");
        chauffeurMod.setText("");
        matriculeMod.setText("");
        
    }
    
}
