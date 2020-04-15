/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Dimension;
import dao.Entity.Fournisseur;
import dao.Entity.MatierePremier;
import dao.Entity.PrixDimFour;
import dao.Manager.DimensionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixDimFourDAO;
import dao.ManagerImpl.DimensionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixDimFourDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class MatierePrixController implements Initializable {

    @FXML
    private TextField codeField;
    @FXML
    private ComboBox<String> dimCombo;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private TextField prixField;
    @FXML
    private TextField libelleField;
    @FXML
    private TableView<PrixDimFour> tablePrixDimFour;
    @FXML
    private TableColumn<PrixDimFour, String> codeColumn;
    @FXML
    private TableColumn<PrixDimFour, String> fourColumn;
    @FXML
    private TableColumn<PrixDimFour, String> dimColumn;
    @FXML    
    private TableColumn<PrixDimFour, BigDecimal> prixColumn;
    @FXML
    private TableColumn<PrixDimFour, String> libelleColumn;

    
    
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnRefresh;
    

     private final ObservableList<PrixDimFour> listePrixDimFour=FXCollections.observableArrayList();
     private final ObservableList<Dimension> listDimension=FXCollections.observableArrayList();
            
     PrixDimFourDAO prixDimFourDAO = new PrixDimFourDAOImpl();
     navigation nav = new navigation();
     PrixDimFour prixDimFour=new PrixDimFour();
    
    
   private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
   FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
   
   private Map<String,Dimension> mapDimension=new HashMap<>();
   DimensionDAO dimensionDAO = new DimensionDAOImpl();
    
    MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
    MatierePremier matierePremiere=new MatierePremier();
    
   
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
       
        List<Fournisseur> listFournisseur=fournisseurDAO.findAll();
        
        listFournisseur.stream().map((fournisseur) -> {
        fourCombo.getItems().addAll(fournisseur.getNom());
        return fournisseur;
        }).forEachOrdered((fournisseur) -> {
        mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
       tablePrixDimFour.setItems(listePrixDimFour);
       setColumnProperties();
       loadDetail();
        
    }    
    
    
     void setColumnProperties(){
         
          codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixDimFour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixDimFour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremiern().getCode());
                }
             });
         
          libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixDimFour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixDimFour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremiern().getNom());
                }
             });
          
          fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixDimFour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixDimFour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFournisseur().getNom());
                }
             });
          
          dimColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixDimFour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixDimFour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDimension().getLibelle());
                }
             });
          
          prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }
    
    void loadDetail(){

        listePrixDimFour.clear();
        listePrixDimFour.addAll(prixDimFourDAO.findAll());
        tablePrixDimFour.setItems(listePrixDimFour);
        
    }
    

    @FXML
    private void btnAjouterOnAction(ActionEvent event) {

           if(codeField.getText().equalsIgnoreCase("") || libelleField.getText().equalsIgnoreCase("") ||fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                fourCombo.getSelectionModel().getSelectedItem().equals("")||dimCombo.getSelectionModel().getSelectedIndex()== -1 || 
                dimCombo.getSelectionModel().getSelectedItem().equals("")||prixField.getText().equalsIgnoreCase("") ){
             nav.showAlert(Alert.AlertType.ERROR, "Attention", null,Constantes.REMPLIR_CHAMPS);
       }

           else {
        prixDimFour=new PrixDimFour();
        prixDimFour.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
        prixDimFour.setDimension(mapDimension.get(dimCombo.getSelectionModel().getSelectedItem()));
        prixDimFour.setPrix(new BigDecimal(prixField.getText()));
        prixDimFour.setMatierePremiern(matierePremiere);
        prixDimFour.setAction(Boolean.FALSE);
        prixDimFour.setUtilisateurCreation(nav.getUtilisateur());
        prixDimFourDAO.add(prixDimFour);

         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
          setColumnProperties();
      
        clear();
        
    }}

     private void clear(){
    
    codeField.clear();
    libelleField.clear();
    prixField.clear();
    dimCombo.getItems().clear();
    fourCombo.getSelectionModel().select(-1);
     loadDetail();
    
    } 
     
    @FXML
    private void chargeLibelleKeyPressed(KeyEvent event) {
          if (event.getCode().equals(KeyCode.ENTER))
            {
                matierePremiere = matierePremiereDAO.findByCode(codeField.getText());
                libelleField.setText(matierePremiere.getNom());
                listDimension.clear();
                
            List<Dimension> listDimension=dimensionDAO.findDimensionByFamille(matierePremiere.getCategorieMp().getCode());
        
            listDimension.stream().map((dimension) -> {
            dimCombo.getItems().addAll(dimension.getLibelle());
            return dimension;
        }).forEachOrdered((dimension) -> {
            mapDimension.put(dimension.getLibelle(), dimension);
        });
                
            }
        
    }

    @FXML
    private void modifierDetailCommande(ActionEvent event) {
    }

    @FXML
    private void refreshDetailCommande(ActionEvent event) {
    }
    
}
