/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Livraision;

import Utils.Constantes;
import dao.Entity.Commande;
import dao.Entity.DetailCommande;
import dao.Entity.DetailReception;
import dao.Manager.CommandeDAO;
import dao.Manager.DetailCommandeDAO;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import function.navigation;
import java.math.BigDecimal;
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
public class RecuCommandeOulmesController implements Initializable {

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
    private TableColumn<DetailCommande, String> codeArtColumn;
    @FXML
    private TableColumn<DetailCommande, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteRecuColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteRestantColumn;
    
    @FXML
    private TableView<DetailReception> tableReception;
    @FXML
    private TableColumn<DetailReception, String> numRecepColumn;
    @FXML
    private TableColumn<DetailReception, String> codeMPRecepColumn;
    @FXML
    private TableColumn<DetailReception, String> libelleRecepColumn;
    @FXML
    private TableColumn<DetailReception, String> numLivrColumn;
    @FXML
    private TableColumn<DetailReception, BigDecimal> quantiteRecuRecepColumn;
    
    @FXML
    private TextField numComRechField;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnEnCours;
    
    /**
     * Initializes the controller class.
     */
    
    private ObservableList<DetailCommande> listeDetailCommande=FXCollections.observableArrayList();
    private final ObservableList<Commande> listeCommande=FXCollections.observableArrayList();
    private final ObservableList<DetailReception> listeDetailReception = FXCollections.observableArrayList();
    DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
    CommandeDAO commandeDAO = new CommandeDAOImpl();
    
     navigation nav = new navigation();
     
     Commande commande;
     
     DetailCommande detailCommande;
      public String numCommandeRecupere = null;
    
 

    
    
    
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
             etatColumn.setCellValueFactory(new PropertyValueFactory<Commande, String>("etat"));
        

        tableCommande.setEditable(false);
 
    }
    
     void loadDetail(){
        
        listeCommande.clear();
        listeCommande.addAll(commandeDAO.findCommandeByEtat(Constantes.ETAT_COMMANDE_RECU, Constantes.COMMANDE_INTERNE_ART));
        tableCommande.setItems(listeCommande);
    }
     
       void setColumnPropertiesDetailCommande(){
        
          codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
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
            

               
            quantiteRecuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRecu()));
                }
                
             });
         
              
            quantiteRestantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRestee()));
                }
                
             });

           
     
    }

  
    void loadDetailCommande (){
        
        Commande commande = detailCommande.getCommande();

        setColumnPropertiesDetailCommande();
        listeDetailCommande.clear();
            
        listeDetailCommande.addAll(commande.getDetailCommandes());
               
        tableDetailCommande.setItems(listeDetailCommande);
    }
    
    
     void setColumnPropertiesDetailReception() {

        codeMPRecepColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailReception, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailReception, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDetailCommande().getPrixOulmes().getProduit().getCode());
            }
        });

        libelleRecepColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailReception, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailReception, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDetailCommande().getPrixOulmes().getProduit().getLibelle());
            }
        });
        
         numRecepColumn.setCellValueFactory(new PropertyValueFactory<DetailReception, String>("numReception"));

         numLivrColumn.setCellValueFactory(new PropertyValueFactory<DetailReception, String>("livraisonFour"));
         
         quantiteRecuRecepColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailReception, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailReception, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRecu()));
                }
                
             });
        
      }
    
     
       
     
    @FXML
    private void afficherDetailOnMouse(MouseEvent event) {
        
         tableReception.getItems().clear();
            
            setColumnPropertiesDetailCommande();
            listeDetailCommande.clear();
        if (tableCommande.getSelectionModel().getSelectedIndex()!=-1){    
               
           commande = tableCommande.getSelectionModel().getSelectedItem();
                 
            listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
            
            tableDetailCommande.setItems(listeDetailCommande);
            
           numCommandeRecupere= nCommandeColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex());
           
              
    }}

    @FXML
    private void rechercheNumComOnKeyPressed(KeyEvent event) {
        
          ObservableList<Commande> listeCommande=FXCollections.observableArrayList();
    listeCommande.clear();
   
   listeCommande.addAll(commandeDAO.findFourByRechercheNumCommande(numComRechField.getText(),Constantes.ETAT_COMMANDE_RECU));
   
   tableCommande.setItems(listeCommande);
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
         try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(RecuCommandeController.class.getResource(nav.getiReportRecuCommande()));

            para.put("Fournisseur",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getNom());
            para.put("NumCommande",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getnCommande());
            para.put("Ville",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getVille().getLibelle());
            
    

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailCommande));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(RecuCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void rafraichirOnAction(ActionEvent event) {

    }

    @FXML
    private void enCoursOnAction(ActionEvent event) {


        tableReception.getItems().clear();
            listeDetailCommande.clear();
        
       if (tableCommande.getSelectionModel().getSelectedItem() != null) {

            commande=tableCommande.getSelectionModel().getSelectedItem();
         
            commande.setEtat(Constantes.ETAT_COMMANDE_ENCOURS);
            commande.setDateRecu(new Date());
            commandeDAO.edit(commande);
            
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.COMMANDE_RECU); 
  
            
              loadDetail();
                      
           
       }else {
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE); 
       }
    }

    @FXML
    private void afficherReceptionOnMouseClicked(MouseEvent event) {
        
                               listeDetailReception.clear();
        setColumnPropertiesDetailReception();
       listeDetailReception.addAll(tableDetailCommande.getSelectionModel().getSelectedItem().getDetailReception());
       tableReception.setItems(listeDetailReception);
        
if (tableDetailCommande.getSelectionModel().getSelectedIndex()!=-1){
    
          for( int i = 0;i<listeDetailReception.size() ;i++ ){
              
             DetailReception detailReception = listeDetailReception.get(i);
                  
                  if (detailReception.getQuantiteRecu().equals(BigDecimal.ZERO.setScale(2))){
                  
                  listeDetailReception.remove(i);

                  }
              
              }
 
        tableReception.setItems(listeDetailReception);
        
        setColumnPropertiesDetailReception();
        
        tableReception.setEditable(true);
}
    }

    
}
