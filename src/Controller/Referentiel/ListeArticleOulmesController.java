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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
public class ListeArticleOulmesController implements Initializable {

    @FXML
    private TableView<Produit> tableArticle;
    @FXML
    private TableColumn<Produit, String> codeColumn;
    @FXML
    private TableColumn<Produit, String> libelleColumn;
    @FXML
    private TableColumn<Produit, BigDecimal> qtePaletteColumn;
    @FXML
    private TableColumn<Produit, BigDecimal> qteCaisseColumn;
    @FXML
    private TableColumn<Produit, BigDecimal> qteBouteilleColumn;
    @FXML
    private TableColumn<Produit, Boolean> emballageColumn;
    
     @FXML
    private CheckBox emballageCheck;
    @FXML
    private TextField txtQtePalette;
    @FXML
    private TextField txtQteCaisse;
    @FXML
    private TextField txtQteBouteille;
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
        qtePaletteColumn.setCellValueFactory(new PropertyValueFactory<>("qtePalette"));
        qteCaisseColumn.setCellValueFactory(new PropertyValueFactory<>("qteCaisse"));
        qteBouteilleColumn.setCellValueFactory(new PropertyValueFactory<>("qteBouteille"));
        
           emballageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Produit, Boolean> param) {
                return  new ReadOnlyObjectWrapper(param.getValue().getPalette());
            }
        });
    
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
        
  produit.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);

            produitDAO.edit(produit);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnProperties();
      loadDetail();  
    }
    }

    @FXML
    private void ModifierVille(ActionEvent event) {
        if (tableArticle.getSelectionModel().getSelectedItem() != null) {
              
              Produit produit= tableArticle.getSelectionModel().getSelectedItem();

               produit.setCode(txtCode.getText());
               produit.setLibelle(txtLibelle.getText());
               
                if(emballageCheck.isSelected()==true){
       
       produit.setPalette(true);
       produit.setQtePalette(BigDecimal.ZERO);
       produit.setQteCaisse(BigDecimal.ZERO);
       produit.setQteBouteille(BigDecimal.ZERO);
               
               }else{
           
       produit.setPalette(false);        
       produit.setQtePalette(new BigDecimal(txtQtePalette.getText()));
       produit.setQteCaisse(new BigDecimal(txtQteCaisse.getText()));
       produit.setQteBouteille(new BigDecimal(txtQteBouteille.getText()));
       
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
       
       if(emballageCheck.isSelected()==true){
       
       produit.setPalette(true);
       produit.setQtePalette(BigDecimal.ZERO);
       produit.setQteCaisse(BigDecimal.ZERO);
       produit.setQteBouteille(BigDecimal.ZERO);
               
               }else{
           
       produit.setPalette(false);        
       produit.setQtePalette(new BigDecimal(txtQtePalette.getText()));
       produit.setQteCaisse(new BigDecimal(txtQteCaisse.getText()));
       produit.setQteBouteille(new BigDecimal(txtQteBouteille.getText()));
       
               }

       produit.setEtat(Constantes.ETAT_COMMANDE_LANCE);
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


    void clear(){
    
    txtCode.clear();
    txtQtePalette.clear();
    
    txtLibelle.clear();
    txtQteCaisse.clear();
    txtQteBouteille.clear();
    
    txtQtePalette.setDisable(false);
    txtQteBouteille.setDisable(false);
    txtQteCaisse.setDisable(false);
        
    emballageCheck.setSelected(false);
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
              txtQtePalette.setText(qtePaletteColumn.getCellData(val)+"");
              txtQteCaisse.setText(qteCaisseColumn.getCellData(val)+"");
              txtQteBouteille.setText(qteBouteilleColumn.getCellData(val)+"");
              
              if (emballageColumn.getCellData(val)== Boolean.TRUE){
            emballageCheck.setSelected(true);
            
            txtQtePalette.setDisable(true);
            txtQteBouteille.setDisable(true);
            txtQteCaisse.setDisable(true);
            
            }else{
            emballageCheck.setSelected(false);
            
            txtQtePalette.setDisable(false);
            txtQteBouteille.setDisable(false);
            txtQteCaisse.setDisable(false);
            
            }
          }
        
        
    }


    @FXML
    private void rechercheCodeOnKeyPressed(KeyEvent event) {
        
                        ObservableList<Produit> listeProduit=FXCollections.observableArrayList();
          
        listeProduit.clear();
   
        listeProduit.addAll(produitDAO.findByCodeProduit(codeRechField.getText()));
   
        tableArticle.setItems(listeProduit);
        
        
    }

    @FXML
    private void rechercheLibelleOnKeyPressed(KeyEvent event) {
        
                       ObservableList<Produit> listeProduit=FXCollections.observableArrayList();
          
        listeProduit.clear();
   
        listeProduit.addAll(produitDAO.findBylibelleProduit(libelleRechField.getText()));
   
        tableArticle.setItems(listeProduit);
        
        
    }

    @FXML
    private void rechercheTableMouseClicked(MouseEvent event) {
        
                    libelleRechField.clear();
            codeRechField.clear();

            setColumnProperties();
            loadDetail();
        
    }

    @FXML
    private void emballageCheckOnAction(ActionEvent event) {
        
        if (emballageCheck.isSelected() == true){
        txtQtePalette.setDisable(true);
        txtQteBouteille.setDisable(true);
        txtQteCaisse.setDisable(true);
        
        txtQtePalette.clear();
        txtQteBouteille.clear();
        txtQteCaisse.clear();
        }else{
        
        txtQtePalette.setDisable(false);
        txtQteBouteille.setDisable(false);
        txtQteCaisse.setDisable(false);
        
        txtQtePalette.clear();
        txtQteBouteille.clear();
        txtQteCaisse.clear();
        
        }
    }
    
}
