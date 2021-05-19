/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.GrammageFilm;
import dao.Entity.TypeCartonBox;
import dao.Manager.GrammageFilmDAO;
import dao.ManagerImpl.GrammageFilmDAOImpl;
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
public class ListeGrammageFilmController implements Initializable {

       @FXML
    private TableView<GrammageFilm> tableGrammageFilm;
    @FXML
    private TableColumn<GrammageFilm, String> codeColumn;
    @FXML
    private TableColumn<GrammageFilm, String> libelleColumn;
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
     GrammageFilmDAO grammageFilmDAO = new GrammageFilmDAOImpl();
      
     private final ObservableList<GrammageFilm> listeGrammageFilm=FXCollections.observableArrayList();
     
     
    
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
        
        listeGrammageFilm.clear();
        listeGrammageFilm.addAll(grammageFilmDAO.findAll());
        tableGrammageFilm.setItems(listeGrammageFilm);
    }
    
    
    
    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
            Integer val= tableGrammageFilm.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              txtCode.setText(codeColumn.getCellData(val));
              txtLibelle.setText(libelleColumn.getCellData(val));

          }
    }

    @FXML
    private void ajouterBtn(ActionEvent event) {
           GrammageFilm grammageFilm =new GrammageFilm();
     if(txtCode.getText().equalsIgnoreCase("")){
        msgCode.setText(Constantes.CHAMP_OBLIGATOIRE);
        txtCode.setStyle("-fx-border-color: red;");
     }else {
       
       grammageFilm.setCode(txtCode.getText());
       grammageFilm.setLibelle(txtLibelle.getText());
       grammageFilm.setEtat(Constantes.ETAT_COMMANDE_LANCE);
       grammageFilm.setUtilisateurCreation(nav.getUtilisateur());

      
        grammageFilmDAO.add(grammageFilm);
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
           setColumnProperties();
      loadDetail(); 
       txtCode.setText("");
       txtLibelle.setText("");
    }
    }

    @FXML
    private void ModifierBtn(ActionEvent event) {
              if (tableGrammageFilm.getSelectionModel().getSelectedItem() != null) {
              
            GrammageFilm grammageFilm = tableGrammageFilm.getSelectionModel().getSelectedItem();
            
       grammageFilm.setCode(txtCode.getText());
       grammageFilm.setLibelle(txtLibelle.getText());
     
 
            grammageFilmDAO.edit(grammageFilm);
      
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);
             setColumnProperties();
             loadDetail();
      
        txtCode.setText("");
        txtLibelle.setText("");         
       
        
        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur",  Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
        }
    }

    @FXML
    private void SupprimerBtn(ActionEvent event) {
          if(tableGrammageFilm.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       GrammageFilm grammageFilm=tableGrammageFilm.getSelectionModel().getSelectedItem();
        
       grammageFilm.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);
       
       grammageFilmDAO.edit(grammageFilm);
                
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.SUPRIMER_ENREGISTREMENT);
        
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
    private void rechercheCodeOnKeyPressed(KeyEvent event) {
        
                        ObservableList<GrammageFilm> listeGrammageFilm=FXCollections.observableArrayList();
          
        listeGrammageFilm.clear();
   
        listeGrammageFilm.addAll(grammageFilmDAO.findByCodeGrammageFilm(codeRechField.getText()));
   
        tableGrammageFilm.setItems(listeGrammageFilm);
        
    }

    @FXML
    private void rechercheLibelleOnKeyPressed(KeyEvent event) {
        
                       ObservableList<GrammageFilm> listeGrammageFilm=FXCollections.observableArrayList();
          
        listeGrammageFilm.clear();
   
        listeGrammageFilm.addAll(grammageFilmDAO.findBylibelleGrammageFilm(libelleRechField.getText()));
   
        tableGrammageFilm.setItems(listeGrammageFilm);
        
    }

    @FXML
    private void rechercheTableMouseClicked(MouseEvent event) {
        
        
            libelleRechField.clear();
            codeRechField.clear();

            setColumnProperties();
            loadDetail();

        
    }
    
}
