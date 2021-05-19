/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.CategorieMp;
import dao.Entity.Fournisseur;
import dao.Entity.HistoriquePrix;
import dao.Entity.PrixBoxMetal;
import dao.Entity.SubCategorieMp;
import dao.Manager.CategorieMpDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.HistoriquePrixDAO;
import dao.Manager.PrixBoxMetalDAO;
import dao.ManagerImpl.CategorieMpDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.HistoriquePrixDAOImpl;
import dao.ManagerImpl.PrixBoxMetalDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ModifierPrixBoxMetalController implements Initializable {

    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> typeCategorieCombo;
    @FXML
    private TextField prixField;
    @FXML
    private Button modifierBtn;

    /**
     * Initializes the controller class.
     */
     
     private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
     private Map<String,SubCategorieMp> mapSubCategorieMp=new HashMap<>();
     private Map<String,CategorieMp> mapCategorieMp=new HashMap<>();
    
       PrixBoxMetal prixBoxMetal = new PrixBoxMetal();
       navigation nav = new navigation();
       
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    PrixBoxMetalDAO prixBoxMetalDAO = new PrixBoxMetalDAOImpl();
    CategorieMpDAO categorieMpDAO  = new CategorieMpDAOImpl();
    HistoriquePrixDAO historiquePrixDAO = new HistoriquePrixDAOImpl();
  
      public ObservableList<PrixBoxMetal> listePrixBoxMetalTMP=FXCollections.observableArrayList();
      
     public void chargerLesDonnees(){

        fourCombo.setValue(prixBoxMetal.getFournisseur().getNom()+"");
        typeCategorieCombo.setValue(prixBoxMetal.getCategorieMp().getNom()+"");
        prixField.setText(prixBoxMetal.getPrix()+"");
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
               List<Fournisseur> listFournisseur=fournisseurDAO.findAllMp();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
         List<CategorieMp> listCategorieMp=categorieMpDAO.findAll();
        
        listCategorieMp.stream().map((categorieMp) -> {
            typeCategorieCombo.getItems().addAll(categorieMp.getNom());
            return categorieMp;
        }).forEachOrdered((categorieMp) -> {
            mapCategorieMp.put(categorieMp.getNom(), categorieMp);
        });
        
        
    }    

    @FXML
    private void CatigorieAction(ActionEvent event) {
    }

    @FXML
    private void modifierBtnOnAction(ActionEvent event) {
        
             HistoriquePrix historiquePrix = new HistoriquePrix();
        
        historiquePrix.setAncienPrix(prixBoxMetal.getPrix());
        historiquePrix.setNouveauPrix(new BigDecimal(prixField.getText()));
        historiquePrix.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
        historiquePrix.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
        historiquePrix.setDateCreation(new Date());
        historiquePrix.setChemin(Constantes.PARAMETRAGE_PRIX);
        historiquePrix.setUtilisateurCreation(nav.getUtilisateur());
        
        historiquePrixDAO.add(historiquePrix);
        
  //######################################################################################################################################################################################################################################################################################################################      
      
  
       prixBoxMetal.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
       prixBoxMetal.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
       prixBoxMetal.setPrix(new BigDecimal(prixField.getText()));
       
       prixBoxMetalDAO.edit(prixBoxMetal);
       
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getConsultationPrixCategorie()));
       
               listePrixBoxMetalTMP.clear();
               listePrixBoxMetalTMP.addAll(prixBoxMetalDAO.findAll());
       
             
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
       
        Stage stage = (Stage) modifierBtn.getScene().getWindow();
           stage.close();
        
        
        
    }
    
}
