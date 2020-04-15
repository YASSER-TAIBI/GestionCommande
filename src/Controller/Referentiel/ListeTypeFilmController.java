/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.TypeFilm;
import dao.Manager.TypeFilmDAO;
import dao.ManagerImpl.TypeFilmDAOImpl;
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
public class ListeTypeFilmController implements Initializable {

    @FXML
    private TableView<TypeFilm> tableTypeFilm;
    @FXML
    private TableColumn<TypeFilm, String> codeColumn;
    @FXML
    private TableColumn<TypeFilm, String> libelleColumn;
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
     TypeFilmDAO  typeFilmDAO = new TypeFilmDAOImpl();
      
     private final ObservableList<TypeFilm> listeTypeFilm=FXCollections.observableArrayList();
    
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
        
        listeTypeFilm.clear();
        listeTypeFilm.addAll(typeFilmDAO.findAll());
        tableTypeFilm.setItems(listeTypeFilm);
    }
    
    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
        Integer val= tableTypeFilm.getSelectionModel().getSelectedIndex();
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
               TypeFilm typeFilm =new TypeFilm();
     if(txtCode.getText().equalsIgnoreCase("")){
        msgCode.setText(Constantes.CHAMP_OBLIGATOIRE);
        txtCode.setStyle("-fx-border-color: red;");
     }else {
       
       typeFilm.setCode(txtCode.getText());
       typeFilm.setLibelle(txtLibelle.getText());
       typeFilm.setUtilisateurCreation(nav.getUtilisateur());
      
        typeFilmDAO.add(typeFilm);
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
           setColumnProperties();
      loadDetail(); 
       txtCode.setText("");
       txtLibelle.setText("");
    
    }
    }

    @FXML
    private void ModifierBtn(ActionEvent event) {
          if (tableTypeFilm.getSelectionModel().getSelectedItem() != null) {
              
            TypeFilm typeFilm = tableTypeFilm.getSelectionModel().getSelectedItem();
            
       typeFilm.setCode(txtCode.getText());
       typeFilm.setLibelle(txtLibelle.getText());
     
 
            typeFilmDAO.edit(typeFilm);
      
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,  Constantes.MODIFIER_ENREGISTREMENT);
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
              if(tableTypeFilm.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       TypeFilm typeFilm=tableTypeFilm.getSelectionModel().getSelectedItem();
        typeFilmDAO.delete(typeFilm);
    
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
    private void rechercheCodeVilleOnKeyPressed(KeyEvent event) {
    }

    @FXML
    private void rechercheLibelleVilleOnKeyPressed(KeyEvent event) {
    }
    
}
