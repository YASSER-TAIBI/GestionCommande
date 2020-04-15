/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.CategorieMp;
import dao.Entity.SubCategorieMp;
import dao.Manager.CategorieMpDAO;
import dao.Manager.SubCategorieMPDAO;
import dao.ManagerImpl.CategorieMpDAOImpl;
import dao.ManagerImpl.SubCategorieMPAOImpl;
import function.navigation;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class CategorieMpController implements Initializable {

    @FXML
    private TableView<CategorieMp> tableCategorie;
    @FXML
    private TableColumn<CategorieMp, String> codeColumn;
    @FXML
    private TableColumn<CategorieMp, String> nomColumn;
    @FXML
    private TableColumn<CategorieMp, String> subCategorieColumn;
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
    private ComboBox<String> subCategorieCombo;
    @FXML
    private TextField rechCode;
    @FXML
    private TextField rechNom;

    /**
     * Initializes the controller class.
     */
      private Map<String,SubCategorieMp> mapSubCategorieMp=new HashMap<>();
     navigation nav = new navigation();
     CategorieMpDAO categorieMpDAO = new CategorieMpDAOImpl();
     SubCategorieMPDAO subCategorieMPDAO = new SubCategorieMPAOImpl();
     
     private final ObservableList<CategorieMp> listeCategorieMp=FXCollections.observableArrayList();
    
      
      void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        subCategorieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CategorieMp, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<CategorieMp, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getSubCategorieMp().getNom());
                }
             });
     
    
     }
    
     void loadDetail(){
        
        listeCategorieMp.clear();
        listeCategorieMp.addAll(categorieMpDAO.findAll());
        tableCategorie.setItems(listeCategorieMp);
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           setColumnProperties();
                loadDetail();
        
          List<SubCategorieMp> listSubCategorieMp=subCategorieMPDAO.findAll();
        
        listSubCategorieMp.stream().map((subCategorieMp) -> {
        subCategorieCombo.getItems().addAll(subCategorieMp.getNom());
        return subCategorieMp;
        }).forEachOrdered((subCategorieMp) -> {
        mapSubCategorieMp.put(subCategorieMp.getNom(), subCategorieMp);
        
        });
    }    

    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
                Integer val= tableCategorie.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              codeField.setText(codeColumn.getCellData(val));
              nomField.setText(nomColumn.getCellData(val));
              subCategorieCombo.setValue(subCategorieColumn.getCellData(val));

                   btnAjouter.setDisable(true);
          }
    }
public void clear(){

    codeField.setText("");
    nomField.setText(""); 
    subCategorieCombo.getSelectionModel().select(-1);  
    rechCode.setText("");
    rechNom.setText("");
    btnAjouter.setDisable(false);
    
}
    
    @FXML
    private void btnModifierOnAction(ActionEvent event) {
            
         if (tableCategorie.getSelectionModel().getSelectedItem() != null) {
        
         CategorieMp categorieMp   =tableCategorie.getSelectionModel().getSelectedItem();
   SubCategorieMp subCategorieMp = mapSubCategorieMp.get(subCategorieCombo.getSelectionModel().getSelectedItem());
        categorieMp.setCode(codeField.getText());   
        categorieMp.setSubCategorieMp(subCategorieMp);
        categorieMp.setNom(nomField.getText());
        categorieMp.setUtilisateurCreation(nav.getUtilisateur());
        categorieMpDAO.edit(categorieMp);
          
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);

        
      setColumnProperties();
      loadDetail();
      clear();
      
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
    }

    @FXML
    private void btnSupprimerOnAction(ActionEvent event) {
        
             if(tableCategorie.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
             
       CategorieMp categorieMp=tableCategorie.getSelectionModel().getSelectedItem();
        categorieMpDAO.delete(categorieMp);
        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        
        setColumnProperties();
        loadDetail();  
        clear();
    
    }
    }

    @FXML
    private void btnAjouterOnAction(ActionEvent event) {
     
       CategorieMp categorieMp = new CategorieMp();
  
        SubCategorieMp subCategorieMp = mapSubCategorieMp.get(subCategorieCombo.getSelectionModel().getSelectedItem());
        categorieMp.setCode(codeField.getText());   
        categorieMp.setSubCategorieMp(subCategorieMp);
        categorieMp.setNom(nomField.getText());
        categorieMp.setUtilisateurCreation(nav.getUtilisateur());
        categorieMpDAO.add(categorieMp);
        
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
         
        
     setColumnProperties();
        loadDetail();
        clear();
         
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        clear();
    }

    @FXML
    private void rechercheCodeVilleOnKeyPressed(KeyEvent event) {
    }

    @FXML
    private void rechercheLibelleVilleOnKeyPressed(KeyEvent event) {
    }
    
}
