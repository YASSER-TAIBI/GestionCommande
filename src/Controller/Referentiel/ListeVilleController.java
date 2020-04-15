/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Ville;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.VilleDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ListeVilleController implements Initializable {

    @FXML
    private TableView<Ville> tableVille;
    @FXML
    private TableColumn<Ville, String> codeColumn;
    @FXML
    private TableColumn<Ville, String> libelleColumn;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField txtLibelle;
    @FXML
    private TextField txtCode;
     @FXML
    private TextField libelleRechField;
    @FXML
    private TextField codeRechField;
    @FXML
    private Label msgCode;
    

 private final ObservableList<Ville> listeVille=FXCollections.observableArrayList();
            
     VilleDAO villeDAO = new VilleDAOImpl();
     navigation nav = new navigation();
     Ville ville;
   
    
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           tableVille.setEditable(true);

   tableVille.setItems(listeVille);
 
 
      setColumnProperties();

      loadDetail(); 
    }    

     void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    
     }
    
     void loadDetail(){
        
        listeVille.clear();
        listeVille.addAll(villeDAO.findAll());
        tableVille.setItems(listeVille);
    }
     
     public void changeNomCellEvent (CellEditEvent editedCell){
        
        Ville utilisateurSelected =tableVille.getSelectionModel().getSelectedItem();
        utilisateurSelected.setCode(editedCell.getNewValue().toString());
        
    }
     
    @FXML
    private void SupprimerVille(ActionEvent event) {
          if(tableVille.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       Ville fournisseur=tableVille.getSelectionModel().getSelectedItem();
        villeDAO.delete(fournisseur);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnProperties();
      loadDetail();  
    }
    }

    @FXML
    private void ModifierVille(ActionEvent event) {
        if (tableVille.getSelectionModel().getSelectedItem() != null) {
              
              Ville ville= tableVille.getSelectionModel().getSelectedItem();
               txtCode.setText(ville.getCode());
               txtLibelle.setText(ville.getLibelle());
      
               
               
          villeDAO.edit(ville);
      
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
             setColumnProperties();
      loadDetail();
        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
        }
    }

    @FXML
    private void ajouterVille(ActionEvent event) {
         Ville ville =new Ville();
     if(txtCode.getText().equalsIgnoreCase("")){
        msgCode.setText(Constantes.CHAMP_OBLIGATOIRE);
        txtCode.setStyle("-fx-border-color: red;");
        
     }else {
       
  ville.setCode(txtCode.getText());
       ville.setLibelle(txtLibelle.getText());
      ville.setUtilisateurCreation(nav.getUtilisateur());
        villeDAO.add(ville);
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
           setColumnProperties();
      loadDetail(); 
    }
    }

    @FXML
    private void searche(KeyEvent event) {
    }

    @FXML
    private void rechercheLibelleVilleOnKeyPressed(KeyEvent event) {
             ObservableList<Ville> listeClientMP=FXCollections.observableArrayList();
    listeVille.clear();
   
   listeClientMP.addAll(villeDAO.findVilleByRechercheCode(libelleRechField.getText()));
   
   tableVille.setItems(listeVille);
    }

    @FXML
    private void rechercheCodeVilleOnKeyPressed(KeyEvent event) {
                 ObservableList<Ville> listeClientMP=FXCollections.observableArrayList();
    listeVille.clear();
   
   listeClientMP.addAll(villeDAO.findVilleByRechercheCode(codeRechField.getText()));
   
   tableVille.setItems(listeVille);
    }

    void clear(){
    
    txtCode.clear();
    txtLibelle.clear();
    }
    
    
    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        clear();
    }
    
}
