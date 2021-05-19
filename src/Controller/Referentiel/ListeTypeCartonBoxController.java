/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.TypeCartonBox;
import dao.Manager.TypeCartonBoxDAO;
import dao.ManagerImpl.TypeCartonBoxDAOImpl;
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
public class ListeTypeCartonBoxController implements Initializable {

    @FXML
    private TableView<TypeCartonBox> tableTypeCartonBox;
    @FXML
    private TableColumn<TypeCartonBox, String> codeColumn;
    @FXML
    private TableColumn<TypeCartonBox, String> libelleColumn;
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
     TypeCartonBoxDAO  typeCartonBoxDAO = new TypeCartonBoxDAOImpl();
      
     private final ObservableList<TypeCartonBox> listeTypeCartonBox=FXCollections.observableArrayList();
    
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
        
        listeTypeCartonBox.clear();
        listeTypeCartonBox.addAll(typeCartonBoxDAO.findAll());
        tableTypeCartonBox.setItems(listeTypeCartonBox);
    }
    
    @FXML
    private void ajouterBtn(ActionEvent event) {
            TypeCartonBox typeCartonBox =new TypeCartonBox();
     if(txtCode.getText().equalsIgnoreCase("")){
        msgCode.setText(Constantes.CHAMP_OBLIGATOIRE);
        txtCode.setStyle("-fx-border-color: red;");
     }else {
       
       typeCartonBox.setCode(txtCode.getText());
       typeCartonBox.setLibelle(txtLibelle.getText());
       typeCartonBox.setEtat(Constantes.ETAT_COMMANDE_LANCE);
       typeCartonBox.setUtilisateurCreation(nav.getUtilisateur());
      
        typeCartonBoxDAO.add(typeCartonBox);
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
           setColumnProperties();
      loadDetail(); 
       txtCode.setText("");
       txtLibelle.setText("");
    }
    }

    @FXML
    private void ModifierBtn(ActionEvent event) {
            if (tableTypeCartonBox.getSelectionModel().getSelectedItem() != null) {
              
            TypeCartonBox typeCartonBox = tableTypeCartonBox.getSelectionModel().getSelectedItem();
            
       typeCartonBox.setCode(txtCode.getText());
       typeCartonBox.setLibelle(txtLibelle.getText());
     
 
            typeCartonBoxDAO.edit(typeCartonBox);
      
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
          if(tableTypeCartonBox.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,  Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       TypeCartonBox typeCartonBox=tableTypeCartonBox.getSelectionModel().getSelectedItem();
 
       typeCartonBox.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);

            typeCartonBoxDAO.edit(typeCartonBox);
    
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
         Integer val= tableTypeCartonBox.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              txtCode.setText(codeColumn.getCellData(val));
              txtLibelle.setText(libelleColumn.getCellData(val));
    }
    
}

    @FXML
    private void rechercheCodeCartBoxOnKeyPressed(KeyEvent event) {
        
                ObservableList<TypeCartonBox> listeTypeCartonBox=FXCollections.observableArrayList();
          
        listeTypeCartonBox.clear();
   
        listeTypeCartonBox.addAll(typeCartonBoxDAO.findByCodeCartBox(codeRechField.getText()));
   
        tableTypeCartonBox.setItems(listeTypeCartonBox);
        
    }

    @FXML
    private void rechercheLibelleCartBoxOnKeyPressed(KeyEvent event) {
        
                ObservableList<TypeCartonBox> listeTypeCartonBox=FXCollections.observableArrayList();
          
        listeTypeCartonBox.clear();
   
        listeTypeCartonBox.addAll(typeCartonBoxDAO.findBylibelleCartBox(libelleRechField.getText()));
   
        tableTypeCartonBox.setItems(listeTypeCartonBox);
        
    }

    @FXML
    private void refrechRecheTableMouseClicked(MouseEvent event) {

            libelleRechField.clear();
            codeRechField.clear();

            setColumnProperties();
            loadDetail();

    }
}
