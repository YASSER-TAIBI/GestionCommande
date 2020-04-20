/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ModifierPrixOulmesController implements Initializable {

    @FXML
    private Button modifierBtn;
    @FXML
    private TextField remiseAchatField;
    @FXML
    private TextField remiseAvoirField;
    @FXML
    private RadioButton radPiece;
    @FXML
    private ToggleGroup radPackPiece;
    @FXML
    private RadioButton radPack;
    @FXML
    private TextField conditionnementField;
    @FXML
    private TextField conditionnementCaisseField;
    @FXML
    private ComboBox<String> artCombo;
    @FXML
    private TextField prixField;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField lieuLivraisonField;
    
    navigation nav = new navigation();
    ProduitDAO produitDAO = new ProduitDAOImpl();
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    private Map<String,Produit> mapProduit=new HashMap<>();
    
    public ObservableList<PrixOulmes> listePrixOulmesTMP=FXCollections.observableArrayList();
    
    ObservableList<String> client =FXCollections.observableArrayList(Constantes.CLIENT_MARJANE,Constantes.CLIENT_MINURSO);
    
    /**
     * Initializes the controller class.
     */
     public PrixOulmes  prixOulmes;
  
    

             public  void chargerLesDonnees(){

        artCombo.setValue(prixOulmes.getProduit().getLibelle());
        remiseAchatField.setText(prixOulmes.getRemiseAchat()+"");
        remiseAvoirField.setText(prixOulmes.getRemiseAvoir()+"");
        conditionnementField.setText(prixOulmes.getConditionnement()+"");
        conditionnementCaisseField.setText(prixOulmes.getConditionnementCaisse()+"");
        prixField.setText(prixOulmes.getPrix()+"");
        clientCombo.setValue(prixOulmes.getClient());
        lieuLivraisonField.setText(prixOulmes.getLieuLivraison());
        
        if (prixOulmes.getTypeArticle().equals(Constantes.PACK)){
         radPack.setSelected(true);
        }else{
         radPiece.setSelected(true);
        }
        
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        clientCombo.setItems(client);
        
          List<Produit> listProduit=produitDAO.findAll();
        
        listProduit.stream().map((produit) -> {
            artCombo.getItems().addAll(produit.getLibelle());
            return produit;
        }).forEachOrdered((produit) -> {
            mapProduit.put(produit.getLibelle(), produit);
        });
        
    }    

    @FXML
    private void modifierBtnOnAction(ActionEvent event) {
        
          if( artCombo.getSelectionModel().getSelectedItem()==null || prixField.getText().equals("")){
         nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, "Vous devez remplir les champs Svp !!");
     }
        else {
              
  
              prixOulmes.setProduit(mapProduit.get(artCombo.getSelectionModel().getSelectedItem()));
              prixOulmes.setClient(clientCombo.getSelectionModel().getSelectedItem());
              prixOulmes.setConditionnement(new BigDecimal(conditionnementField.getText()));
              
              if (prixOulmes.getConditionnementCaisse()== null){
                  
                  if (!conditionnementCaisseField.getText().equals("null")){
                      
                   prixOulmes.setConditionnementCaisse(new BigDecimal(conditionnementCaisseField.getText()));
                   
                  }
              
              }else{
                  
              prixOulmes.setConditionnementCaisse(new BigDecimal(conditionnementCaisseField.getText()));
              
              }
             
              prixOulmes.setRemiseAchat(new BigDecimal(remiseAchatField.getText()));
              prixOulmes.setRemiseAvoir(new BigDecimal(remiseAvoirField.getText()));
              prixOulmes.setLieuLivraison(lieuLivraisonField.getText());
              
              if (radPack.isSelected()== true){
                prixOulmes.setTypeArticle(Constantes.PACK);
              }else{
                prixOulmes.setTypeArticle(Constantes.PIECE);
              }
              
               prixOulmes.setPrix(new BigDecimal(prixField.getText()));
               
               prixOulmesDAO.edit(prixOulmes);
               
               
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getConsultationPrixCategorie()));

               
               listePrixOulmesTMP.clear();
               listePrixOulmesTMP.addAll(prixOulmesDAO.findAll());
               
  


         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, "Modifier avec Succès");
         
     Stage stage = (Stage) modifierBtn.getScene().getWindow();
            stage.close();
     
  
        }
        
    }

    @FXML
    private void radPieceOnAction(ActionEvent event) {
    }

    @FXML
    private void radPackOnAction(ActionEvent event) {
    }

    @FXML
    private void artComboOnAction(ActionEvent event) {
    }


    @FXML
    private void clientOnAction(ActionEvent event) {
    }
    
}
