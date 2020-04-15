/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.commande;

import Utils.Constantes;
import dao.Entity.Commande;
import dao.Entity.DetailCommande;
import dao.Manager.CommandeDAO;
import dao.Manager.DetailCommandeDAO;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import function.navigation;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author h
 */
public class EnvoyerCommandeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private ObservableList<DetailCommande> listeDetailCommande=FXCollections.observableArrayList();
    private final ObservableList<Commande> listeCommande=FXCollections.observableArrayList();
    
    DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
    CommandeDAO commandeDAO = new CommandeDAOImpl();
    
     navigation nav = new navigation();
     
     Commande commande;
     DetailCommande detailCommande;
    
     @FXML
    private TableView<Commande> tableCommande;
    @FXML
    private TableColumn<Commande, String> nCommandeColumn;
    @FXML
    private TableColumn<Commande, Date> dateCreationColumn;
    @FXML
    private TableColumn<Commande, String> etatColumn;
    
    @FXML
    private TableView<DetailCommande> tableDetailCommande;
    @FXML
    private TableColumn<DetailCommande, String> codeMPColumn;
    @FXML
    private TableColumn<DetailCommande, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommande, Integer> quantiteColumn;
    
    @FXML
    private Button btnEnvoyer;
    @FXML
    private TextField numComRechField;
    @FXML
    private Button btnImprimer;
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setColumnProperties();
        loadDetail();
        btnEnvoyer.setDisable(true);
        
    }    
     void setColumnProperties(){
        
        nCommandeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getnCommande());
                }
                
             });
        dateCreationColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));

        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
      
    }
    
     void loadDetail(){
        
        listeCommande.clear();
        listeCommande.addAll(commandeDAO.findCommandeByEtat(Constantes.ETAT_COMMANDE_VALIDEE, Constantes.COMMANDE_INTERNE));
        tableCommande.setItems(listeCommande);
    }

      public void changeNomCellEvent (CellEditEvent editedCell){
        
        Commande utilisateurSelected =tableCommande.getSelectionModel().getSelectedItem();
        
        
    }
     

     void setColumnPropertiesDetailCommandeOn(){
        
          codeMPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
          
          libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });
          
          quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<DetailCommande, Integer> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });

    }


    @FXML
    private void EnvoyerCommande(ActionEvent event) {
        
         tableDetailCommande.getItems().clear();
         if (tableCommande.getSelectionModel().getSelectedItem() != null) {


            commande=tableCommande.getSelectionModel().getSelectedItem();
         
            commande.setEtat(Constantes.ETAT_COMMANDE_ENCOURS);
            commande.setDateEnvoi(new Date());
            commandeDAO.edit(commande);
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.COMMANDE_VALIDER);
            
            setColumnProperties();
            loadDetail();
            
           
       }else {
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Erreur", null,Constantes.VERIFICATION_SELECTION_LIGNE); 
       }
    }


    @FXML
    private void afficherDetailOnMouse(MouseEvent event) {
        
         
            listeDetailCommande.clear();
            if (tableCommande.getSelectionModel().getSelectedIndex()!=-1){
                
           commande=tableCommande.getSelectionModel().getSelectedItem();    
           
                 setColumnPropertiesDetailCommandeOn();
            
                      listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommande);
        
          btnEnvoyer.setDisable(true);
          
            
    }
    }

    @FXML
    private void rechercheNumComOnKeyPressed(KeyEvent event) {
        
     ObservableList<Commande> listeCommande=FXCollections.observableArrayList();
    listeCommande.clear();
   
   listeCommande.addAll(commandeDAO.findFourByRechercheNumCommande(numComRechField.getText(),Constantes.ETAT_COMMANDE_VALIDEE));
   
   tableCommande.setItems(listeCommande);
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        imprimer();
    }
    
    private void imprimer(){
        
        try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(EnvoyerCommandeController.class.getResource(nav.getiReportBonCommande()));
            para.put("Fournisseur",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getNom());
            para.put("NumCommande",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getnCommande());
            para.put("Ville",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getVille().getLibelle());
            para.put("DateLiv",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getDateCreation());
    

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailCommande));
               JasperViewer.viewReport(jp, false);
               
               btnEnvoyer.setDisable(false);
            
        } catch (JRException ex) {
            Logger.getLogger(EnvoyerCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
}
