/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.CategorieMp;
import dao.Entity.Dimension;
import dao.Entity.Fournisseur;
import dao.Entity.Grammage;
import dao.Entity.Intervalle;
import dao.Entity.PrixAdhesif;
import dao.Entity.PrixBox;
import dao.Entity.PrixCarton;
import dao.Entity.SubCategorieMp;
import dao.Entity.TypeCarton;
import dao.Entity.TypeCartonBox;
import dao.Manager.CategorieMpDAO;
import dao.Manager.DimensionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.GrammageDAO;
import dao.Manager.IntervalleDAO;
import dao.Manager.PrixAdhesifDAO;
import dao.Manager.PrixBoxDAO;
import dao.Manager.PrixCartonDAO;
import dao.Manager.SubCategorieMPDAO;
import dao.Manager.TypeCartonBoxDAO;
import dao.Manager.TypeCartonDAO;
import dao.ManagerImpl.CategorieMpDAOImpl;
import dao.ManagerImpl.DimensionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.GrammageDAOImpl;
import dao.ManagerImpl.IntervalleDAOImpl;
import dao.ManagerImpl.PrixAdhesifDAOImpl;
import dao.ManagerImpl.PrixBoxDAOImpl;
import dao.ManagerImpl.PrixCartonDAOImpl;
import dao.ManagerImpl.SubCategorieMPAOImpl;
import dao.ManagerImpl.TypeCartonDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
public class ModifierPrixAdhesifController implements Initializable {

    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> typeCategorieCombo;
    @FXML
    private ComboBox<String> dimCombo;
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
      private Map<String,Dimension> mapDimension=new HashMap<>();
    
       PrixAdhesif prixAdhesif = new PrixAdhesif();
       navigation nav = new navigation();
       
        FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
     PrixAdhesifDAO prixAdhesifDAO = new PrixAdhesifDAOImpl();
    CategorieMpDAO categorieMpDAO  = new CategorieMpDAOImpl();
    DimensionDAO dimensionDAO = new DimensionDAOImpl();
  
   
      
     public void chargerLesDonnees(){

          fourCombo.setValue(prixAdhesif.getFournisseur().getNom()+"");
          typeCategorieCombo.setValue(prixAdhesif.getCategorieMp().getNom()+"");
          dimCombo.setValue(prixAdhesif.getDimension().getLibelle()+"");
          prixField.setText(prixAdhesif.getPrix()+"");
          
          
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          List<Fournisseur> listFournisseur=fournisseurDAO.findAll();
        
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
        
   

            List<Dimension> listeDimension=dimensionDAO.findAll();
               
        listeDimension.stream().map((dimension) -> {
            dimCombo.getItems().addAll(dimension.getLibelle());
            return dimension;
        }).forEachOrdered((dimension) -> {
            mapDimension.put(dimension.getLibelle(), dimension);
        });
       
    }    

    @FXML
    private void CatigorieAction(ActionEvent event) {
    }


    @FXML
    private void modifierBtnOnAction(ActionEvent event) {
        
       
        
       prixAdhesif.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
       prixAdhesif.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
       prixAdhesif.setDimension(mapDimension.get(dimCombo.getSelectionModel().getSelectedItem()));
     

       prixAdhesif.setPrix(new BigDecimal(prixField.getText()));
       
       prixAdhesifDAO.edit(prixAdhesif);
             
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
       
        Stage stage = (Stage) modifierBtn.getScene().getWindow();
           stage.close();
    }
    
}
