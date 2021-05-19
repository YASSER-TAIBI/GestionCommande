/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.SubCategorieMp;
import dao.Entity.TypeCartonBox;
import dao.Manager.SubCategorieMPDAO;
import dao.ManagerImpl.SubCategorieMPAOImpl;
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
public class SubCategorieMpController implements Initializable {

    @FXML
    private TableView<SubCategorieMp> tableSubCategorie;
    @FXML
    private TableColumn<SubCategorieMp, String> codeColumn;
    @FXML
    private TableColumn<SubCategorieMp, String> nomColumn;
    @FXML
    private TableColumn<SubCategorieMp, String> unitesColumn;
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
    private TextField unitesField;
    @FXML
    private TextField rechCode;
    @FXML
    private TextField rechNom;

    /**
     * Initializes the controller class.
     */
    
      navigation nav = new navigation();
     SubCategorieMPDAO subCategorieMpDAO = new SubCategorieMPAOImpl();
      
     private final ObservableList<SubCategorieMp> listeSubCategorieMp=FXCollections.observableArrayList();
     
    
      void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        unitesColumn.setCellValueFactory(new PropertyValueFactory<>("unite"));
     
    
     }
    
     void loadDetail(){
        
        listeSubCategorieMp.clear();
        listeSubCategorieMp.addAll(subCategorieMpDAO.findAll());
        tableSubCategorie.setItems(listeSubCategorieMp);
    }
    
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          setColumnProperties();
                loadDetail();
        
    }    

    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
          Integer val= tableSubCategorie.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              codeField.setText(codeColumn.getCellData(val));
              nomField.setText(nomColumn.getCellData(val));
              unitesField.setText(unitesColumn.getCellData(val));

                   btnAjouter.setDisable(true);
          }
    }

    @FXML
    private void btnModifierOnAction(ActionEvent event) {
                  if (tableSubCategorie.getSelectionModel().getSelectedItem() != null) {
              
            SubCategorieMp subCategorieMp = tableSubCategorie.getSelectionModel().getSelectedItem();
            
       subCategorieMp.setCode(codeField.getText());
       subCategorieMp.setNom(nomField.getText());
       subCategorieMp.setUnite(unitesField.getText());
     
 
       subCategorieMpDAO.edit(subCategorieMp);
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);
       
             setColumnProperties();
             loadDetail();
              clear();
       
        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur",  Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
        }
    }

    @FXML
    private void btnSupprimerOnAction(ActionEvent event) {
           if(tableSubCategorie.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       SubCategorieMp subCategorieMp=tableSubCategorie.getSelectionModel().getSelectedItem();
          subCategorieMp.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);

            subCategorieMpDAO.edit(subCategorieMp);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnProperties();
      loadDetail();  
      clear();
    
    }
    }

    @FXML
    private void btnAjouterOnAction(ActionEvent event) {
          SubCategorieMp subCategorieMp =new SubCategorieMp();
     if(codeField.getText().equalsIgnoreCase("")){
         nav.showAlert(Alert.AlertType.WARNING, "Succès", null, Constantes.CHAMP_OBLIGATOIRE);
        codeField.setStyle("-fx-border-color: red;");
     }else {
       
       subCategorieMp.setCode(codeField.getText());
       subCategorieMp.setNom(nomField.getText());
       subCategorieMp.setUnite(unitesField.getText());
       subCategorieMp.setEtat(Constantes.ETAT_COMMANDE_LANCE);
       subCategorieMp.setUtilisateurCreation(nav.getUtilisateur());

      
        subCategorieMpDAO.add(subCategorieMp);
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
        
           setColumnProperties();
           loadDetail();
           clear();
           
    }
    }

    public void clear(){
        
       codeField.setText("");
       nomField.setText("");
       unitesField.setText("");
       rechCode.setText("");
       rechNom.setText("");
       btnAjouter.setDisable(false);
    
    }
    
    
    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        
        clear();
    }

    @FXML
    private void rechercheCodeOnKeyPressed(KeyEvent event) {
        
            ObservableList<SubCategorieMp> listeSubCategorieMp=FXCollections.observableArrayList();
          
        listeSubCategorieMp.clear();
   
        listeSubCategorieMp.addAll(subCategorieMpDAO.findByCodeSubCategorieMp(rechCode.getText()));
   
        tableSubCategorie.setItems(listeSubCategorieMp);   
        
    }

    @FXML
    private void rechercheLibelleOnKeyPressed(KeyEvent event) {
        
              ObservableList<SubCategorieMp> listeSubCategorieMp=FXCollections.observableArrayList();
          
        listeSubCategorieMp.clear();
   
        listeSubCategorieMp.addAll(subCategorieMpDAO.findBylibelleSubCategorieMp(rechNom.getText()));
   
        tableSubCategorie.setItems(listeSubCategorieMp);   
        
    }

    @FXML
    private void rechercheTableMouseClicked(MouseEvent event) {
        
                  rechNom.clear();
            rechCode.clear();

            setColumnProperties();
            loadDetail();
        
    }
    
}
