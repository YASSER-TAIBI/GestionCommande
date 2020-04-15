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
import dao.Entity.GrammageFilm;
import dao.Entity.Intervalle;
import dao.Entity.PrixBox;
import dao.Entity.PrixFilm;
import dao.Entity.SubCategorieMp;
import dao.Entity.TypeCartonBox;
import dao.Entity.TypeFilm;
import dao.Manager.CategorieMpDAO;
import dao.Manager.DimensionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.GrammageDAO;
import dao.Manager.GrammageFilmDAO;
import dao.Manager.IntervalleDAO;
import dao.Manager.PrixBoxDAO;
import dao.Manager.PrixFilmDAO;
import dao.Manager.SubCategorieMPDAO;
import dao.Manager.TypeCartonBoxDAO;
import dao.Manager.TypeFilmDAO;
import dao.ManagerImpl.CategorieMpDAOImpl;
import dao.ManagerImpl.DimensionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.GrammageDAOImpl;
import dao.ManagerImpl.GrammageFilmDAOImpl;
import dao.ManagerImpl.IntervalleDAOImpl;
import dao.ManagerImpl.PrixBoxDAOImpl;
import dao.ManagerImpl.PrixFilmDAOImpl;
import dao.ManagerImpl.SubCategorieMPAOImpl;
import dao.ManagerImpl.TypeCartonBoxDAOImpl;
import dao.ManagerImpl.TypeFilmDAOImpl;
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
public class ModifierPrixFilmController implements Initializable {

    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> typeCategorieCombo;
    @FXML
    private ComboBox<String> GrammageFilmCombo;
    @FXML
    private ComboBox<String> TypeFilmCombo;
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
     private Map<String,GrammageFilm> mapGrammageFilm=new HashMap<>();
     private Map<String,TypeFilm> mapTypeFilm=new HashMap<>();
    
       PrixFilm prixFilm = new PrixFilm();
       navigation nav = new navigation();
       
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    PrixFilmDAO prixFilmDAO = new PrixFilmDAOImpl();
    SubCategorieMPDAO subcategorieMpDAO = new SubCategorieMPAOImpl();
    CategorieMpDAO categorieMpDAO  = new CategorieMpDAOImpl();
    GrammageFilmDAO grammageFilmDAO = new GrammageFilmDAOImpl();
    TypeFilmDAO typeFilmDAO = new TypeFilmDAOImpl();
   
   
      
     public void chargerLesDonnees(){

          fourCombo.setValue(prixFilm.getFournisseur().getNom()+"");
          typeCategorieCombo.setValue(prixFilm.getCategorieMp().getNom()+"");
          TypeFilmCombo.setValue(prixFilm.getTypeFilm().getLibelle()+"");
          GrammageFilmCombo.setValue(prixFilm.getGrammageFilm().getLibelle()+"");
          prixField.setText(prixFilm.getPrix()+"");
          
        
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
        
     
        List<GrammageFilm> listGrammageFilm=grammageFilmDAO.findAll();
        
        listGrammageFilm.stream().map((grammageFilm) -> {
            GrammageFilmCombo.getItems().addAll(grammageFilm.getLibelle());
            return grammageFilm;
        }).forEachOrdered((grammageFilm) -> {
            mapGrammageFilm.put(grammageFilm.getLibelle(), grammageFilm);
        });
        
        
        List<TypeFilm> listTypeFilm=typeFilmDAO.findAll();
        
        listTypeFilm.stream().map((typeFilm) -> {
            TypeFilmCombo.getItems().addAll(typeFilm.getLibelle());
            return typeFilm;
        }).forEachOrdered((typeFilm) -> {
            mapTypeFilm.put(typeFilm.getLibelle(), typeFilm);
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
        
        
        
       prixFilm.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
       prixFilm.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
       prixFilm.setGrammageFilm(mapGrammageFilm.get(GrammageFilmCombo.getSelectionModel().getSelectedItem()));
       prixFilm.setTypeFilm(mapTypeFilm.get(TypeFilmCombo.getSelectionModel().getSelectedItem()));
       prixFilm.setPrix(new BigDecimal(prixField.getText()));
       
      prixFilmDAO.edit(prixFilm);
             
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,  Constantes.MODIFIER_ENREGISTREMENT);
       
        Stage stage = (Stage) modifierBtn.getScene().getWindow();
           stage.close();
    }
    
}
