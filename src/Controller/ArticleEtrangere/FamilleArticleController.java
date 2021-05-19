/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ArticleEtrangere;


import Utils.Constantes;
import dao.Entity.FamilleArticle;
import dao.Manager.FamilleArticleDAO;
import dao.ManagerImpl.FamilleArticleDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class FamilleArticleController implements Initializable {

    @FXML
    private TableView<FamilleArticle> tableFamille;
    @FXML
    private TableColumn<FamilleArticle, String> codeColumn;
    @FXML
    private TableColumn<FamilleArticle, String> nomColumn;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField codeField;
    @FXML
    private TextField nomField;

    /**
     * Initializes the controller class.
     */

     navigation nav = new navigation();
     FamilleArticleDAO familleArticleDAO = new FamilleArticleDAOImpl();
     
     private final ObservableList<FamilleArticle> listeFamilleArticle=FXCollections.observableArrayList();
    
      
      void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

     }
    
     void loadDetail(){
        
        listeFamilleArticle.clear();
        listeFamilleArticle.addAll(familleArticleDAO.findAll());
        tableFamille.setItems(listeFamilleArticle);
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           setColumnProperties();
                loadDetail();
    }     

    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
        
                 Integer val= tableFamille.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              codeField.setText(codeColumn.getCellData(val));
              nomField.setText(nomColumn.getCellData(val));

                   btnAjouter.setDisable(true);
          }
        
    }

    public void clear(){

    codeField.setText("");
      nomField.setText(""); 
         btnAjouter.setDisable(false);
}
    
    @FXML
    private void btnModifierOnAction(ActionEvent event) {
        
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
         
         if (tableFamille.getSelectionModel().getSelectedItem() != null) {
        
         FamilleArticle familleArticle   =tableFamille.getSelectionModel().getSelectedItem();
         
        familleArticle.setCode(codeField.getText());   
        familleArticle.setNom(nomField.getText());
        familleArticle.setUtilisateurCreation(nav.getUtilisateur());
        familleArticleDAO.edit(familleArticle);
          
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);

        
      setColumnProperties();
      loadDetail();
      clear();
      
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
           }
    }

    @FXML
    private void btnSupprimerOnAction(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
                 if(tableFamille.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
             
       FamilleArticle familleArticle=tableFamille.getSelectionModel().getSelectedItem();
        familleArticleDAO.delete(familleArticle);
        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        
        setColumnProperties();
        loadDetail();  
        clear();
    
    }
            }
    }

    @FXML
    private void btnAjouterOnAction(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
               FamilleArticle familleArticle = new FamilleArticle();
  
  
        familleArticle.setCode(codeField.getText());
        familleArticle.setNom(nomField.getText());
        familleArticle.setUtilisateurCreation(nav.getUtilisateur());
        familleArticleDAO.add(familleArticle);
        
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
         
        
     setColumnProperties();
        loadDetail();
        clear();
        
    }}

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        clear();
    }
    
}
