/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Livraision;

import Utils.Constantes;
import dao.Entity.Commande;
import dao.Entity.DetailCommande;
import dao.Manager.CommandeDAO;
import dao.Manager.DetailCommandeDAO;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
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
 * @author Hp
 */
public class AnnulerCommandeOulmesController implements Initializable {

    @FXML
    private TableView<Commande> tableCommande;
    @FXML
    private TableColumn<Commande, String> nCommandeColumn;
    @FXML
    private TableColumn<Commande, Date> dateCreationColumn;
    @FXML
    private TableColumn<Commande, String> motifColumn;
    @FXML
    private TableColumn<Commande, String> etatColumn;
    
    @FXML
    private TableView<DetailCommande> tableDetailCommande;
    @FXML
    private TableColumn<DetailCommande, String> codeMPColumn;
    @FXML
    private TableColumn<DetailCommande, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteColumn;
    
    @FXML
    private TextField numComRechField;
    @FXML
    private DatePicker dateCreationPicker;
    @FXML
    private Button btnEnCours;

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
    private Button btnImprimer;
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           setColumnProperties();
        loadDetail();
        
    }    

       void setColumnProperties(){
        
             nCommandeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getnCommande());
                }
                
             });
             
             dateCreationColumn.setCellValueFactory(new PropertyValueFactory<Commande, Date>("dateCreation"));
             
             motifColumn.setCellValueFactory(new PropertyValueFactory<Commande, String>("motif"));
             
             etatColumn.setCellValueFactory(new PropertyValueFactory<Commande, String>("etat"));
        

        tableCommande.setEditable(false);
 
    }
    
     void loadDetail(){
        
        listeCommande.clear();
        listeCommande.addAll(commandeDAO.findCommandeByEtat(Constantes.ETAT_COMMANDE_ANNULER, Constantes.COMMANDE_INTERNE_ART));
        tableCommande.setItems(listeCommande);
    }
    
       void setColumnPropertiesDetailCommande(){
        
          codeMPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });
          
          libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
             });
          
          
       
            
               quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                    
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
    private void afficherDetailOnMouse(MouseEvent event) {
        
             setColumnPropertiesDetailCommande();
            listeDetailCommande.clear();
        if (tableCommande.getSelectionModel().getSelectedIndex()!=-1){    
               
           commande = tableCommande.getSelectionModel().getSelectedItem();
                 
            listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
            
            tableDetailCommande.setItems(listeDetailCommande);
                   
    }
        
    }

    @FXML
    private void afficherReceptionOnMouseClicked(MouseEvent event) {

    }

    @FXML
    private void rechercheNumComOnKeyPressed(KeyEvent event){
        
     ObservableList<Commande> listeCommande=FXCollections.observableArrayList();
    listeCommande.clear();
   
   listeCommande.addAll(commandeDAO.findFourByRechercheNumCommandeOulmes(numComRechField.getText(),Constantes.ETAT_COMMANDE_ANNULER));
   
   tableCommande.setItems(listeCommande);
        
    }

    @FXML
    private void creationDate(ActionEvent event) throws ParseException {
        
                    LocalDate localDate=dateCreationPicker.getValue();
             if(localDate!=null){
                 
             Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
             
             listeCommande.clear();
   
   listeCommande.addAll(commandeDAO.findByDateCommandeOulmes(dateSaisie,Constantes.ETAT_COMMANDE_ANNULER));
   
   tableCommande.setItems(listeCommande);
             }
        
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        
                           numComRechField.clear();
        dateCreationPicker.setValue(null);
        tableCommande.getItems().clear();
        tableDetailCommande.getItems().clear();

        
        setColumnProperties();
        loadDetail();
        
    }

    @FXML
    private void enCoursOnAction(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
               
            listeDetailCommande.clear();
        
       if (tableCommande.getSelectionModel().getSelectedItem() != null) {

            commande=tableCommande.getSelectionModel().getSelectedItem();
         
            commande.setEtat(Constantes.ETAT_COMMANDE_ENCOURS);
            commandeDAO.edit(commande);
            
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.COMMANDE_ANNULER); 
  
            
              loadDetail();
                      
           
       }else {
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE); 
       }
        
        
    }
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
         
           if (tableCommande.getSelectionModel().getSelectedItem() != null) {
           try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SuiviCommandeController.class.getResource(nav.getiReportBonCommandeAnnulerPF()));


            para.put("Fournisseur",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getNom());
            para.put("NumCommande",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getnCommande());
            para.put("Ville",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getVille().getLibelle());
            para.put("DateLiv",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getDateCreation());
            para.put("Motif",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getMotif());
    
            for (int i =0;i<listeDetailCommande.size();i++){
            
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                detailCommande.setQuantiteLivree(BigDecimal.ZERO);
            
                listeDetailCommande.set(i, detailCommande);
            }
            

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailCommande));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(SuiviCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        }else {
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE); 
       }

    }
}
