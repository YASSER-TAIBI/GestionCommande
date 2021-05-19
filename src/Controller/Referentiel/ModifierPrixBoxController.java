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
import dao.Entity.HistoriquePrix;
import dao.Entity.Intervalle;
import dao.Entity.PrixBox;
import dao.Entity.SubCategorieMp;
import dao.Entity.TypeCartonBox;
import dao.Manager.CategorieMpDAO;
import dao.Manager.DimensionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.GrammageDAO;
import dao.Manager.HistoriquePrixDAO;
import dao.Manager.IntervalleDAO;
import dao.Manager.PrixBoxDAO;
import dao.Manager.SubCategorieMPDAO;
import dao.Manager.TypeCartonBoxDAO;
import dao.ManagerImpl.CategorieMpDAOImpl;
import dao.ManagerImpl.DimensionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.GrammageDAOImpl;
import dao.ManagerImpl.HistoriquePrixDAOImpl;
import dao.ManagerImpl.IntervalleDAOImpl;
import dao.ManagerImpl.PrixBoxDAOImpl;
import dao.ManagerImpl.SubCategorieMPAOImpl;
import dao.ManagerImpl.TypeCartonBoxDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ModifierPrixBoxController implements Initializable {

    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> typeCategorieCombo;
    @FXML
    private ComboBox<String> grammageCombo;
    @FXML
    private ComboBox<String> dimCombo;
    @FXML
    private TextField prixField;
    @FXML
    private ComboBox<String> TypeCarBoxCombo;
    @FXML
    private Button modifierBtn;
    @FXML
    private ComboBox<String> IntervalleCombo;
    /**
     * Initializes the controller class.
     */
    
     private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
     private Map<String,SubCategorieMp> mapSubCategorieMp=new HashMap<>();
     private Map<String,CategorieMp> mapCategorieMp=new HashMap<>();
      private Map<String,Dimension> mapDimension=new HashMap<>();
      private Map<String,Grammage> mapGrammage=new HashMap<>();
      private Map<String,Intervalle> mapIntervalle=new HashMap<>();
      private Map<String,TypeCartonBox> mapTypeCarBox=new HashMap<>();
    
       PrixBox prixBox;
       navigation nav = new navigation();
       
        FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
     PrixBoxDAO prixBoxDAO = new PrixBoxDAOImpl();
    SubCategorieMPDAO subcategorieMpDAO = new SubCategorieMPAOImpl();
    CategorieMpDAO categorieMpDAO  = new CategorieMpDAOImpl();
    DimensionDAO dimensionDAO = new DimensionDAOImpl();
    IntervalleDAO intervalleDAO = new IntervalleDAOImpl();
    GrammageDAO grammageDAO = new GrammageDAOImpl();
    TypeCartonBoxDAO typeCartonBoxDAO = new TypeCartonBoxDAOImpl();
    HistoriquePrixDAO historiquePrixDAO = new HistoriquePrixDAOImpl();
      
        public ObservableList<PrixBox> listePrixBoxTMP=FXCollections.observableArrayList();
    
     public void chargerLesDonnees(){

          fourCombo.setValue(prixBox.getFournisseur().getNom()+"");
          typeCategorieCombo.setValue(prixBox.getCategorieMp().getNom()+"");
          TypeCarBoxCombo.setValue(prixBox.getTypeCartonBox().getLibelle()+"");
          IntervalleCombo.setValue(prixBox.getIntervalle().getLibelle()+"");
          dimCombo.setValue(prixBox.getDimension().getLibelle()+"");
          grammageCombo.setValue(prixBox.getGrammage().getLibelle()+"");
          prixField.setText(prixBox.getPrix()+"");
          
 
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
        
     
        List<Grammage> listGrammage=grammageDAO.findAll();
        
        listGrammage.stream().map((grammage) -> {
            grammageCombo.getItems().addAll(grammage.getLibelle());
            return grammage;
        }).forEachOrdered((grammage) -> {
            mapGrammage.put(grammage.getLibelle(), grammage);
        });
        
        
        List<TypeCartonBox> listTypeCartonBox=typeCartonBoxDAO.findAll();
        
        listTypeCartonBox.stream().map((typeCartonBox) -> {
            TypeCarBoxCombo.getItems().addAll(typeCartonBox.getLibelle());
            return typeCartonBox;
        }).forEachOrdered((typeCartonBox) -> {
            mapTypeCarBox.put(typeCartonBox.getLibelle(), typeCartonBox);
        });
        
        
         List<CategorieMp> listCategorieMp=categorieMpDAO.findAll();
        
        listCategorieMp.stream().map((categorieMp) -> {
            typeCategorieCombo.getItems().addAll(categorieMp.getNom());
            return categorieMp;
        }).forEachOrdered((categorieMp) -> {
            mapCategorieMp.put(categorieMp.getNom(), categorieMp);
        });
        
        List<Intervalle> listIntervalle=intervalleDAO.findAll();
        
        listIntervalle.stream().map((intervalle) -> {
            IntervalleCombo.getItems().addAll(intervalle.getLibelle());
            return intervalle;
        }).forEachOrdered((intervalle) -> {
            mapIntervalle.put(intervalle.getLibelle(), intervalle);
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

    private void radioFixAction(ActionEvent event) {
          IntervalleCombo.getItems().clear();
          List<Intervalle> listIntervalle=new ArrayList<>();
           listIntervalle =  intervalleDAO.findIntervalleByCode(Constantes.CODE_INTERVALLE);
        
        listIntervalle.stream().map((intervalle) -> {
            IntervalleCombo.getItems().addAll(intervalle.getLibelle());
            return intervalle;
        }).forEachOrdered((intervalle) -> {
            mapIntervalle.put(intervalle.getLibelle(), intervalle);
        });
    }

    private void radioVariableAction(ActionEvent event) {
           IntervalleCombo.getItems().clear();
     List<Intervalle> listIntervalle=new ArrayList<>();
           listIntervalle =  intervalleDAO.findIntervalleSufCodeI0ByCode(Constantes.CODE_INTERVALLE);
        
        listIntervalle.stream().map((intervalle) -> {
            IntervalleCombo.getItems().addAll(intervalle.getLibelle());
            return intervalle;
        }).forEachOrdered((intervalle) -> {
            mapIntervalle.put(intervalle.getLibelle(), intervalle);
        });
    }

    @FXML
    private void modifierBtnOnAction(ActionEvent event) {
        
   
        HistoriquePrix historiquePrix = new HistoriquePrix();
        
        historiquePrix.setAncienPrix(prixBox.getPrix());
        historiquePrix.setNouveauPrix(new BigDecimal(prixField.getText()));
        historiquePrix.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
        historiquePrix.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
        historiquePrix.setDateCreation(new Date());
        historiquePrix.setChemin(Constantes.PARAMETRAGE_PRIX);
        historiquePrix.setUtilisateurCreation(nav.getUtilisateur());
        
        historiquePrixDAO.add(historiquePrix);
        
  //######################################################################################################################################################################################################################################################################################################################      
       
        
       prixBox.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
       prixBox.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
       prixBox.setDimension(mapDimension.get(dimCombo.getSelectionModel().getSelectedItem()));
       prixBox.setGrammage(mapGrammage.get(grammageCombo.getSelectionModel().getSelectedItem()));
       prixBox.setTypeCartonBox(mapTypeCarBox.get(TypeCarBoxCombo.getSelectionModel().getSelectedItem()));
       prixBox.setIntervalle(mapIntervalle.get(IntervalleCombo.getSelectionModel().getSelectedItem()));
       prixBox.setPrix(new BigDecimal(prixField.getText()));
       
       prixBoxDAO.edit(prixBox);
       
        FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getConsultationPrixCategorie()));
       
               listePrixBoxTMP.clear();
               listePrixBoxTMP.addAll(prixBoxDAO.findAll());
       
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
       

       
        Stage stage = (Stage) modifierBtn.getScene().getWindow();
           stage.close();
    }
    
}
