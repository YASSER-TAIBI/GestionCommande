/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ArticleEtrangere;

import Utils.Constantes;
import dao.Entity.FamilleArticle;
import dao.Entity.SousFamilleArticle;
import dao.Manager.FamilleArticleDAO;
import dao.Manager.SousFamilleArticleDAO;
import dao.ManagerImpl.FamilleArticleDAOImpl;
import dao.ManagerImpl.SousFamilleArticleDAOImpl;
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
import javafx.scene.control.ComboBox;
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
public class SousFamilleArticleController implements Initializable {

    @FXML
    private TableView<SousFamilleArticle> tableFamille;
    @FXML
    private TableColumn<SousFamilleArticle, String> codeColumn;
    @FXML
    private TableColumn<SousFamilleArticle, String> nomColumn;
    @FXML
    private TableColumn<SousFamilleArticle, String> familleArtColumn;
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
    @FXML
    private ComboBox<String> familleArticleCombo;

    /**
     * Initializes the controller class.
     */
    
      private Map<String,FamilleArticle> mapFamilleArticle=new HashMap<>();
     navigation nav = new navigation();
     SousFamilleArticleDAO sousFamilleArticleDAO = new SousFamilleArticleDAOImpl();
     FamilleArticleDAO familleArticleDAO = new FamilleArticleDAOImpl();
     
     private final ObservableList<SousFamilleArticle> listeSousFamilleArticle=FXCollections.observableArrayList();
    
      
      void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        familleArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SousFamilleArticle, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SousFamilleArticle, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFamilleArticle().getNom());
                }
             });
     }
    
     void loadDetail(){
        
        listeSousFamilleArticle.clear();
        listeSousFamilleArticle.addAll(sousFamilleArticleDAO.findAll());
        tableFamille.setItems(listeSousFamilleArticle);
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           setColumnProperties();
                loadDetail();
                
                
        List<FamilleArticle> listFamilleArticle= familleArticleDAO.findAll();
        
        listFamilleArticle.stream().map((familleArticle) -> {
            familleArticleCombo.getItems().addAll(familleArticle.getNom());
            return familleArticle;
        }).forEachOrdered((familleArticle) -> {
            mapFamilleArticle.put(familleArticle.getNom(), familleArticle);
        });
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
              familleArticleCombo.setValue(nomColumn.getCellData(val));

                   btnAjouter.setDisable(true);
          }
        
    }

    
    public void clear(){

    codeField.setText("");
    nomField.setText("");
    familleArticleCombo.getSelectionModel().select(-1);
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
        
             
           FamilleArticle familleArticle = mapFamilleArticle.get(familleArticleCombo.getSelectionModel().getSelectedItem());    
             
         SousFamilleArticle sousFamilleArticle   =tableFamille.getSelectionModel().getSelectedItem();
         
        sousFamilleArticle.setCode(codeField.getText());   
        sousFamilleArticle.setNom(nomField.getText());
        sousFamilleArticle.setFamilleArticle(familleArticle);
        sousFamilleArticle.setUtilisateurCreation(nav.getUtilisateur());
        sousFamilleArticleDAO.edit(sousFamilleArticle);
          
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
             
       SousFamilleArticle sousFamilleArticle =tableFamille.getSelectionModel().getSelectedItem();
        sousFamilleArticleDAO.delete(sousFamilleArticle);
        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        
        setColumnProperties();
        loadDetail();  
        clear();
    
    }}
        
    }

    @FXML
    private void btnAjouterOnAction(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
          FamilleArticle familleArticle = mapFamilleArticle.get(familleArticleCombo.getSelectionModel().getSelectedItem()); 
        
               SousFamilleArticle sousFamilleArticle = new SousFamilleArticle();
  
  
        sousFamilleArticle.setCode(codeField.getText());
        sousFamilleArticle.setNom(nomField.getText());
        sousFamilleArticle.setFamilleArticle(familleArticle);
        sousFamilleArticle.setUtilisateurCreation(nav.getUtilisateur());
        sousFamilleArticleDAO.add(sousFamilleArticle);
        
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
