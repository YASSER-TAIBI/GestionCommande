/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.TypeCarton;
import dao.Manager.TypeCartonDAO;
import dao.ManagerImpl.TypeCartonDAOImpl;
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
public class ListeTypeCartonController implements Initializable {

    @FXML
    private TableColumn<TypeCarton, String> codeColumn;
    @FXML
    private TableColumn<TypeCarton, String> libelleColumn;
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
     TypeCartonDAO  typeCartonDAO = new TypeCartonDAOImpl();
      
     private final ObservableList<TypeCarton> listeTypeCarton=FXCollections.observableArrayList();
    @FXML
    private TableView<TypeCarton> tableTypeCarton;
    
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
        
        listeTypeCarton.clear();
        listeTypeCarton.addAll(typeCartonDAO.findAll());
        tableTypeCarton.setItems(listeTypeCarton);
    }

    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
          Integer val= tableTypeCarton.getSelectionModel().getSelectedIndex();
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
         TypeCarton typeCarton =new TypeCarton();
     if(txtCode.getText().equalsIgnoreCase("")){
        msgCode.setText(Constantes.CHAMP_OBLIGATOIRE);
        txtCode.setStyle("-fx-border-color: red;");
     }else {
       
       typeCarton.setCode(txtCode.getText());
       typeCarton.setLibelle(txtLibelle.getText());
       typeCarton.setUtilisateurCreation(nav.getUtilisateur());

      
        typeCartonDAO.add(typeCarton);
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
           setColumnProperties();
      loadDetail(); 
       txtCode.setText("");
       txtLibelle.setText("");
    }
    }

    @FXML
    private void ModifierBtn(ActionEvent event) {
            if (tableTypeCarton.getSelectionModel().getSelectedItem() != null) {
              
            TypeCarton typeCarton = tableTypeCarton.getSelectionModel().getSelectedItem();
            
       typeCarton.setCode(txtCode.getText());
       typeCarton.setLibelle(txtLibelle.getText());
     
 
            typeCartonDAO.edit(typeCarton);
      
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
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
          if(tableTypeCarton.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       TypeCarton typeCartonBox=tableTypeCarton.getSelectionModel().getSelectedItem();
        typeCartonDAO.delete(typeCartonBox);
    
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
    private void rechercheCodeVilleOnKeyPressed(KeyEvent event) {
    }

    @FXML
    private void rechercheLibelleVilleOnKeyPressed(KeyEvent event) {
    }
    
}
