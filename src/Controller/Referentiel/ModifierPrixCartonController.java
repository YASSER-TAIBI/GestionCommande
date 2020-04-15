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
public class ModifierPrixCartonController implements Initializable {

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
    @FXML
    private ComboBox<String> TypeCarCombo;
    /**
     * Initializes the controller class.
     */
    
     private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
     private Map<String,SubCategorieMp> mapSubCategorieMp=new HashMap<>();
     private Map<String,CategorieMp> mapCategorieMp=new HashMap<>();
      private Map<String,Dimension> mapDimension=new HashMap<>();
      private Map<String,TypeCarton> mapTypeCar=new HashMap<>();
    
       PrixCarton prixCarton = new PrixCarton();
       navigation nav = new navigation();
       
        FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
     PrixCartonDAO prixCartonDAO = new PrixCartonDAOImpl();
    CategorieMpDAO categorieMpDAO  = new CategorieMpDAOImpl();
    DimensionDAO dimensionDAO = new DimensionDAOImpl();
    TypeCartonDAO typeCartonDAO = new TypeCartonDAOImpl();
  
   
      
     public void chargerLesDonnees(){

          fourCombo.setValue(prixCarton.getFournisseur().getNom()+"");
          typeCategorieCombo.setValue(prixCarton.getCategorieMp().getNom()+"");
          TypeCarCombo.setValue(prixCarton.getTypeCarton().getLibelle()+"");
          dimCombo.setValue(prixCarton.getDimension().getLibelle()+"");
          prixField.setText(prixCarton.getPrix()+"");
          
          
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
        
     
      
        
        
        List<TypeCarton> listTypeCarton=typeCartonDAO.findAll();
        
        listTypeCarton.stream().map((typeCarton) -> {
            TypeCarCombo.getItems().addAll(typeCarton.getLibelle());
            return typeCarton;
        }).forEachOrdered((typeCarton) -> {
            mapTypeCar.put(typeCarton.getLibelle(), typeCarton);
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
        
       
        
       prixCarton.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
       prixCarton.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
       prixCarton.setDimension(mapDimension.get(dimCombo.getSelectionModel().getSelectedItem()));
       prixCarton.setTypeCarton(mapTypeCar.get(TypeCarCombo.getSelectionModel().getSelectedItem()));

       prixCarton.setPrix(new BigDecimal(prixField.getText()));
       
       prixCartonDAO.edit(prixCarton);
             
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
       
        Stage stage = (Stage) modifierBtn.getScene().getWindow();
           stage.close();
    }
    
}
