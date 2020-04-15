/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import dao.Entity.CategorieMp;
import dao.Entity.Fournisseur;
import dao.Entity.PrixBoxMetal;
import dao.Entity.SubCategorieMp;
import dao.Manager.CategorieMpDAO;
import dao.Manager.DimensionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.PrixAdhesifDAO;
import dao.ManagerImpl.CategorieMpDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.PrixAdhesifDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
    PrixAdhesifDAO prixAdhesifDAO = new PrixAdhesifDAOImpl();
    CategorieMpDAO categorieMpDAO  = new CategorieMpDAOImpl();

  
   
      
     public void chargerLesDonnees(){

        fourCombo.setValue(prixBoxMetal.getFournisseur().getNom()+"");
        typeCategorieCombo.setValue(prixBoxMetal.getCategorieMp().getNom()+"");
        prixField.setText(prixBoxMetal.getPrix()+"");
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CatigorieAction(ActionEvent event) {
    }

    @FXML
    private void modifierBtnOnAction(ActionEvent event) {
    }
    
}
