/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.DelaiPaiementFour;
import dao.Entity.Fournisseur;
import dao.Manager.DelaiPaiementFourDAO;
import dao.Manager.FournisseurDAO;
import dao.ManagerImpl.DelaiPaiementFourDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class DetaiPaiementFourController implements Initializable {

    @FXML
    private TableView<DelaiPaiementFour> tableDelaiPaiementFour;
    @FXML
    private TableColumn<DelaiPaiementFour, String> fourColumn;
    @FXML
    private TableColumn<DelaiPaiementFour, BigDecimal> nbJourColumn;
    @FXML
    private TableColumn<DelaiPaiementFour, BigDecimal> nbJourMinColumn;
    @FXML
    private TableColumn<DelaiPaiementFour, BigDecimal> commandeSPColumn;
    @FXML
    private TableColumn<DelaiPaiementFour, BigDecimal> nbJourSPColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField nbJourField;
    @FXML
    private TextField commandeSPField;
    @FXML
    private TextField nbJourSPField;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private TextField nbJourMinField;

    /**
     * Initializes the controller class.
     */
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    navigation nav = new navigation();
    
    private final ObservableList<DelaiPaiementFour> listeDelaiPaiementFour=FXCollections.observableArrayList();
    
     DelaiPaiementFourDAO delaiPaiementFourDAO = new DelaiPaiementFourDAOImpl();
     
     DelaiPaiementFour delaiPaiementFour = new DelaiPaiementFour();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           List<Fournisseur> listFournisseur=fournisseurDAO.findAllPfAndMp();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
        
        loadDetail();
        setColumnProperties();
        
    }    

       void setColumnProperties(){
        
    
        
          fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DelaiPaiementFour , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DelaiPaiementFour, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFournisseur().getNom());
            }

        });
     
         nbJourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DelaiPaiementFour , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DelaiPaiementFour, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNbJour());
            }

        });
         
    
         nbJourMinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DelaiPaiementFour , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DelaiPaiementFour, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNbJourMin());
            }

        });
      
         commandeSPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DelaiPaiementFour , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DelaiPaiementFour, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCommandeSP());
            }

        });
         
        nbJourSPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DelaiPaiementFour , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DelaiPaiementFour, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNbJourSP());
            }

        });
    }
    
    void loadDetail(){
        
        listeDelaiPaiementFour.clear();
        listeDelaiPaiementFour.addAll(delaiPaiementFourDAO.findAll());
        tableDelaiPaiementFour.setItems(listeDelaiPaiementFour);
    }
    
    
    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
        
           Integer val= tableDelaiPaiementFour.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              fourCombo.setValue(fourColumn.getCellData(val));
              nbJourField.setText(nbJourColumn.getCellData(val)+"");
              nbJourMinField.setText(nbJourMinColumn.getCellData(val)+"");
              nbJourSPField.setText(nbJourSPColumn.getCellData(val)+"");
              commandeSPField.setText(commandeSPColumn.getCellData(val)+"");
          }

    }

    private void btnModifierOnAction(ActionEvent event) {
        
          
         if (tableDelaiPaiementFour.getSelectionModel().getSelectedItem() != null) {
        
          delaiPaiementFour=tableDelaiPaiementFour.getSelectionModel().getSelectedItem();
         Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
        
        delaiPaiementFour.setFournisseur(fournisseur);
        delaiPaiementFour.setCommandeSP(new BigDecimal(commandeSPField.getText()));
        delaiPaiementFour.setNbJour(new BigDecimal(nbJourField.getText()));
        delaiPaiementFour.setNbJourMin(new BigDecimal(nbJourMinField.getText()));
        delaiPaiementFour.setNbJourSP(new BigDecimal(nbJourSPField.getText()));
        delaiPaiementFour.setUtilisateurCreation(nav.getUtilisateur());
        delaiPaiementFour.setDateCreation(new Date());
        
        delaiPaiementFourDAO.edit(delaiPaiementFour);
         
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);

        clear();
      
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
        
    }

    private void btnSupprimerOnAction(ActionEvent event) {
        
           if(tableDelaiPaiementFour.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
             
       DelaiPaiementFour delaiPaiementFour=tableDelaiPaiementFour.getSelectionModel().getSelectedItem();
          
       delaiPaiementFour.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);

            delaiPaiementFourDAO.edit(delaiPaiementFour);
        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
    clear();
    }
        
    }

    @FXML
    private void btnAjouterOnAction(ActionEvent event) {
        
        
          if(nbJourField.getText().isEmpty() ||
                  nbJourMinField.getText().isEmpty() ||
                  nbJourSPField.getText().isEmpty() ||
                  commandeSPField.getText().isEmpty() ||
                  fourCombo.getSelectionModel().isEmpty()
                  ){
        
        nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
        return;
        
     }else {
        
        delaiPaiementFour = new DelaiPaiementFour();
        
          Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
        
        delaiPaiementFour.setFournisseur(fournisseur);
        delaiPaiementFour.setCommandeSP(new BigDecimal(commandeSPField.getText()));
        delaiPaiementFour.setNbJour(new BigDecimal(nbJourField.getText()));
        delaiPaiementFour.setNbJourMin(new BigDecimal(nbJourMinField.getText()));
        delaiPaiementFour.setNbJourSP(new BigDecimal(nbJourSPField.getText()));
        delaiPaiementFour.setEtat(Constantes.ETAT_COMMANDE_LANCE);
        delaiPaiementFour.setUtilisateurCreation(nav.getUtilisateur());
        delaiPaiementFour.setDateCreation(new Date());
        
        delaiPaiementFourDAO.add(delaiPaiementFour);
        
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
         
       clear();
          }
    }

    void clear(){
    
      fourCombo.getSelectionModel().clearSelection();
        nbJourField.clear();
        nbJourMinField.clear();
        nbJourSPField.clear();
        commandeSPField.clear();
        
        loadDetail();
        setColumnProperties();
    
    
    }
    
    
    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        
        clear();
    }
    
}
