/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reception;

import Utils.Constantes;
import dao.Entity.Commande;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCompte;
import dao.Entity.DetailReception;
import dao.Entity.Fournisseur;
import dao.Manager.CommandeDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailReceptionDAO;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailReceptionDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SuiviReceptionOulmesController implements Initializable {
    
   @FXML
    private TableView<Commande> tableCommande;
    @FXML
    private TableColumn<Commande, String> nComColumn;
    @FXML
    private TableColumn<Commande, Date> dateCreationColumn;
    @FXML
    private TableColumn<Commande, String> etatColumn;
    
    @FXML
    private TextField numComRechField;
    
    @FXML
    private DatePicker dateCreationPicker;
    
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
    private TableColumn<DetailReception, String> codeArtRecepColumn;
    @FXML
    private TableColumn<DetailReception, String> libelleRecepColumn;
    @FXML
    private TableColumn<DetailReception, String> numLivrColumn;
    @FXML
    private TableColumn<DetailReception, BigDecimal> quantiteRecuRecepColumn;

    /**
     * Initializes the controller class.
     */
    DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
    CommandeDAO commandeDAO = new CommandeDAOImpl();
    DetailReceptionDAO detailReceptionDAO = new DetailReceptionDAOImpl();
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
            
    
    navigation nav = new navigation();
    Commande commande = new Commande();
    Fournisseur fournisseur = new Fournisseur();
    DetailCommande detailCommande = new DetailCommande();
    DetailReception detailReception = new DetailReception();
    DetailCompte detailCompte = new DetailCompte();
    
       private final ObservableList<DetailCommande> listeDetailCommande = FXCollections.observableArrayList();
       private final ObservableList<DetailReception> listeDetailReception = FXCollections.observableArrayList();
       private final ObservableList<Commande> listeCommande = FXCollections.observableArrayList();
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          setColumnProperties();
        loadDetail();
        
    }    

      void setColumnProperties() {

        nComColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getnCommande());
            }

        });
        dateCreationColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));

    }

    void loadDetail() {

        listeCommande.clear();
        listeCommande.addAll(commandeDAO.findCommandeByEtat(Constantes.ETAT_COMMANDE_ENCOURS, Constantes.COMMANDE_INTERNE_ART));
        tableCommande.setItems(listeCommande);
    }

    
    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        
        tableReception.getItems().clear();
               setColumnPropertiesDetailCommande();
        listeDetailCommande.clear();
if (tableCommande.getSelectionModel().getSelectedIndex()!=-1){
    
        commande = tableCommande.getSelectionModel().getSelectedItem();

        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));

        tableDetailCommande.setItems(listeDetailCommande);

        tableDetailCommande.setEditable(true);

}
        
        
    }

     void setColumnPropertiesDetailCommande() {

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
     
      void setColumnPropertiesDetailReception() {

        codeArtRecepColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailReception, String>, ObservableValue<String>>() {
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
    private void rechercheNumComOnKeyPressed(KeyEvent event) {
        
          ObservableList<Commande> listeCommande=FXCollections.observableArrayList();
          
    listeCommande.clear();
   
   listeCommande.addAll(commandeDAO.findFourByRechercheNumCommandeOulmes(numComRechField.getText(),Constantes.ETAT_COMMANDE_ENCOURS));
   
   tableCommande.setItems(listeCommande);
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

    @FXML
    private void creationDate(ActionEvent event) throws ParseException {
        
            
          LocalDate localDate=dateCreationPicker.getValue();
             if(localDate!=null){
                 
             Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
             
            
              listeCommande.clear();
   
   listeCommande.addAll(commandeDAO.findByDateCommandeOulmes(dateSaisie,Constantes.ETAT_COMMANDE_ENCOURS));
   
   tableCommande.setItems(listeCommande);
             }
        
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        
            numComRechField.clear();
        dateCreationPicker.setValue(null);
        tableCommande.getItems().clear();
        tableDetailCommande.getItems().clear();
        tableReception.getItems().clear();
        
          setColumnProperties();
        loadDetail();
    }
 
}
