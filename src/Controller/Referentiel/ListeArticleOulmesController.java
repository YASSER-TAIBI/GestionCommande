/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Produit;
import dao.Manager.ProduitDAO;
import dao.ManagerImpl.ProduitDAOImpl;
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
import javafx.scene.control.CheckBox;
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
public class ListeArticleOulmesController implements Initializable {

    @FXML
    private TableView<Produit> tableArticle;
    @FXML
    private TableColumn<Produit, String> codeColumn;
    @FXML
    private TableColumn<Produit, String> libelleColumn;
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
    @FXML
    private CheckBox paletteCheck;

 private final ObservableList<Produit> listeProduit=FXCollections.observableArrayList();
            
     ProduitDAO produitDAO = new ProduitDAOImpl();
     navigation nav = new navigation();
     Produit produit;
 
   
    
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           tableArticle.setEditable(true);

   tableArticle.setItems(listeProduit);
 
 
      setColumnProperties();

      loadDetail(); 
    }    

     void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    
     }
    
     void loadDetail(){
        
        listeProduit.clear();
        listeProduit.addAll(produitDAO.findAll());
        tableArticle.setItems(listeProduit);
    }
     
     public void changeNomCellEvent (TableColumn.CellEditEvent editedCell){
        
        Produit utilisateurSelected =tableArticle.getSelectionModel().getSelectedItem();
        utilisateurSelected.setCode(editedCell.getNewValue().toString());
        
    }
     
    @FXML
    private void SupprimerVille(ActionEvent event) {
          if(tableArticle.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       Produit produit=tableArticle.getSelectionModel().getSelectedItem();
        produitDAO.delete(produit);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnProperties();
      loadDetail();  
    }
    }

    @FXML
    private void ModifierVille(ActionEvent event) {
        if (tableArticle.getSelectionModel().getSelectedItem() != null) {
              
              Produit produit= tableArticle.getSelectionModel().getSelectedItem();
               txtCode.setText(produit.getCode());
               txtLibelle.setText(produit.getLibelle());
        if (paletteCheck.isSelected()== true){
       produit.setPalette(Boolean.TRUE);
       }else{
       produit.setPalette(Boolean.FALSE);
       }
               
               
          produitDAO.edit(produit);
      
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
             setColumnProperties();
      loadDetail();
        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
        }
    }

    @FXML
    private void ajouterVille(ActionEvent event) {
         Produit produit =new Produit();
     if(txtCode.getText().equalsIgnoreCase("")){
        msgCode.setText(Constantes.CHAMP_OBLIGATOIRE);
        txtCode.setStyle("-fx-border-color: red;");
        
     }else {
       
  produit.setCode(txtCode.getText());
       produit.setLibelle(txtLibelle.getText());
       
       if (paletteCheck.isSelected()== true){
       produit.setPalette(Boolean.TRUE);
       }else{
       produit.setPalette(Boolean.FALSE);
       }
      produit.setUtilisateurCreation(nav.getUtilisateur());
        produitDAO.add(produit);
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
    }

    @FXML
    private void rechercheCodeVilleOnKeyPressed(KeyEvent event) {

    }

    void clear(){
    
    txtCode.clear();
    txtLibelle.clear();
    paletteCheck.setSelected(false);
    
    }
    
    
    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        clear();
    }

    @FXML
    private void clickChargeOnMouse(MouseEvent event) {
        
        
           Integer val= tableArticle.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              txtCode.setText(codeColumn.getCellData(val));
              txtLibelle.setText(libelleColumn.getCellData(val));

          }
        
        
    }

    @FXML
    private void paletteCheckOnAction(ActionEvent event) {
    }
    
}
