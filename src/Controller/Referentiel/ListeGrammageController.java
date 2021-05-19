/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Grammage;
import dao.Entity.TypeCartonBox;
import dao.Manager.GrammageDAO;
import dao.ManagerImpl.GrammageDAOImpl;
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
public class ListeGrammageController implements Initializable {

    @FXML
    private TableView<Grammage> tableGrammage;
    @FXML
    private TableColumn<Grammage, String> codeColumn;
    @FXML
    private TableColumn<Grammage, String> libelleColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtLibelle;
    @FXML
    private Label msgCode;
    @FXML
    private TextField codeRechField;
    @FXML
    private TextField libelleRechField;

    /**
     * Initializes the controller class.
     */
    
     navigation nav = new navigation();
     GrammageDAO grammageDAO = new GrammageDAOImpl();
      
     private final ObservableList<Grammage> listeGrammage=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setColumnProperties();
         loadDetail();
    }    

     
      void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
     
    
     }
    
     void loadDetail(){
        
        listeGrammage.clear();
        listeGrammage.addAll(grammageDAO.findAll());
        tableGrammage.setItems(listeGrammage);
    }
    
    @FXML
    private void ajouterBtn(ActionEvent event) {
            Grammage grammage =new Grammage();
     if(txtCode.getText().equalsIgnoreCase("")){
        msgCode.setText( Constantes.CHAMP_OBLIGATOIRE);
        txtCode.setStyle("-fx-border-color: red;");
     }else {
       
       grammage.setCode(txtCode.getText());
       grammage.setLibelle(txtLibelle.getText());
       grammage.setEtat(Constantes.ETAT_COMMANDE_LANCE);
       grammage.setUtilisateurCreation(nav.getUtilisateur());

      
        grammageDAO.add(grammage);
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
           setColumnProperties();
      loadDetail(); 
       txtCode.setText("");
       txtLibelle.setText("");
    }
    }

    @FXML
    private void ModifierBtn(ActionEvent event) {
          if (tableGrammage.getSelectionModel().getSelectedItem() != null) {
              
            Grammage grammage = tableGrammage.getSelectionModel().getSelectedItem();
            
       grammage.setCode(txtCode.getText());
       grammage.setLibelle(txtLibelle.getText());
     
 
            grammageDAO.edit(grammage);
      
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);
             setColumnProperties();
             loadDetail();
      
        txtCode.setText("");
        txtLibelle.setText("");         
       
        
        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Errreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
        }
    }

    @FXML
    private void SupprimerBtn(ActionEvent event) {
                if(tableGrammage.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       Grammage grammage=tableGrammage.getSelectionModel().getSelectedItem();
        
       grammage.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);
    
       grammageDAO.edit(grammage);
       
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnProperties();
      loadDetail();  
             txtCode.setText("");
       txtLibelle.setText("");
    
    }
    }

    @FXML
    private void searche(KeyEvent event) {
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
         txtCode.setText("");
        txtLibelle.setText("");         
 
    }


    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
                  Integer val= tableGrammage.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              txtCode.setText(codeColumn.getCellData(val));
              txtLibelle.setText(libelleColumn.getCellData(val));

          }
    }

    @FXML
    private void rechercheCodeOnKeyPressed(KeyEvent event) {
        
                ObservableList<Grammage> listeGrammage=FXCollections.observableArrayList();
          
        listeGrammage.clear();
   
        listeGrammage.addAll(grammageDAO.findByCodeGrammage(codeRechField.getText()));
   
        tableGrammage.setItems(listeGrammage);
        
        
    }

    @FXML
    private void rechercheLibelleOnKeyPressed(KeyEvent event) {
        
              ObservableList<Grammage> listeGrammage=FXCollections.observableArrayList();
          
        listeGrammage.clear();
   
        listeGrammage.addAll(grammageDAO.findBylibelleGrammage(libelleRechField.getText()));
   
        tableGrammage.setItems(listeGrammage);
    }

    @FXML
    private void rechercheTableMouseClicked(MouseEvent event) {
        
              libelleRechField.clear();
        codeRechField.clear();
        
        setColumnProperties();
        loadDetail();
        
    }
    
}
