/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CommandeEtrangere;

import Utils.Constantes;
import dao.Entity.CommandeEtrangere;
import dao.Entity.DetailCommandeEtrangere;
import dao.Manager.CommandeEtrangereDAO;
import dao.Manager.DetailCommandeEtrangereDAO;
import dao.ManagerImpl.CommandeEtrangereDAOImpl;
import dao.ManagerImpl.DetailCommandeEtrangereDAOImpl;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class EnPortEtrangereController implements Initializable {

  
    @FXML
    private TableView<CommandeEtrangere> tableCommandeEtr;
    @FXML
    private TableColumn<CommandeEtrangere, String> numCommandeColumn;
    @FXML
    private TableColumn<CommandeEtrangere, Date> dateCommandeColumn;
    @FXML
    private TableColumn<CommandeEtrangere, String> fournisseurColumn;
    @FXML
    private TableColumn<CommandeEtrangere, String> lieuDepartColumn;
    @FXML
    private TableColumn<CommandeEtrangere, String> lieuArriveeColumn;
    @FXML
    private TableColumn<CommandeEtrangere, Date> dateDepartColumn;
    @FXML
    private TableColumn<CommandeEtrangere, Date> dateArriveeColumn;
    @FXML
    private TableColumn<CommandeEtrangere, String> etatColumn;
    
    
    @FXML
    private TableView<DetailCommandeEtrangere> tableDetailCommandeEtr;
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> codeColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> articleColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> prixColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> montantColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> deviseColumn;
    
    @FXML
    private TextField numComRech;
    @FXML
    private DatePicker dateComRech;
    @FXML
    private DatePicker dateEnPort;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;

    /**
     * Initializes the controller class.
     */
    
     private ObservableList<DetailCommandeEtrangere> listeDetailCommandeEtrangere=FXCollections.observableArrayList();
    private final ObservableList<CommandeEtrangere> listeCommandeEtrangere=FXCollections.observableArrayList();
    
    DetailCommandeEtrangereDAO  detailCommandeEtrangereDAO = new DetailCommandeEtrangereDAOImpl();

    CommandeEtrangereDAO commandeEtrangereDAO = new CommandeEtrangereDAOImpl();
    

    
     navigation nav = new navigation();
     CommandeEtrangere commandeEtrangere;
     
     DetailCommandeEtrangere detailCommandeEtrangere;
    
   

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
             setColumnProperties();
        loadDetail();
    }    

     void setColumnProperties(){
        

             numCommandeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getnCommande());
                }
              });
             
             dateCommandeColumn.setCellValueFactory(new PropertyValueFactory<CommandeEtrangere, Date>("dateCommande"));
             
             fournisseurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFourisseur().getNom());
                }
              });
             
             lieuArriveeColumn.setCellValueFactory(new PropertyValueFactory<CommandeEtrangere, String>("lieuArrivee"));
                
             lieuDepartColumn.setCellValueFactory(new PropertyValueFactory<CommandeEtrangere, String>("lieuDepart"));
             
             dateArriveeColumn.setCellValueFactory(new PropertyValueFactory<CommandeEtrangere, Date>("dateArrivee"));

             dateDepartColumn.setCellValueFactory(new PropertyValueFactory<CommandeEtrangere, Date>("dateDepart"));

             etatColumn.setCellValueFactory(new PropertyValueFactory<CommandeEtrangere, String>("etat"));
        

        tableCommandeEtr.setEditable(false);
 
    }
    
     void loadDetail(){
        
        listeCommandeEtrangere.clear();
        listeCommandeEtrangere.addAll(commandeEtrangereDAO.findCommandeEtrangereByEtat(Constantes.ETAT_COMMANDE_EN_ROUTE));
        tableCommandeEtr.setItems(listeCommandeEtrangere);
    }
    
    
     void setColumnPropertiesDetailCommande(){
        
          codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getArticle().getCode());
                }
             });
          
          articleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getArticle().getLibelle());
                }
             });
          
          deviseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getTauxDevise().getDevise().getLibelle());
                }
             });
                      
          qteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });
           
          prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrix()));
                }
                
             });
     
           
          montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
                    });
    }


  
    void loadDetailCommande (){

        commandeEtrangere = detailCommandeEtrangere.getCommandeEtrangere();

        setColumnPropertiesDetailCommande();
        listeDetailCommandeEtrangere.clear();
        listeDetailCommandeEtrangere.addAll(detailCommandeEtrangereDAO.findDetailCommandeEtrangereByCommandeEtr(commandeEtrangere));
        tableDetailCommandeEtr.setItems(listeDetailCommandeEtrangere);
    }

    
    
    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        
            setColumnPropertiesDetailCommande();
          
            listeDetailCommandeEtrangere.clear();
            if (tableCommandeEtr.getSelectionModel().getSelectedIndex()!=-1){
            commandeEtrangere=tableCommandeEtr.getSelectionModel().getSelectedItem();
               
              listeDetailCommandeEtrangere.addAll(detailCommandeEtrangereDAO.findDetailCommandeEtrangereByCommandeEtr(commandeEtrangere));
        tableDetailCommandeEtr.setItems(listeDetailCommandeEtrangere);

            }
    }

    @FXML
    private void tableDetailClicked(MouseEvent event) {
    }

    @FXML
    private void rechercheNumLivKeyPressed(KeyEvent event) {
    }

    @FXML
    private void ajouterOnAction(ActionEvent event) throws ParseException {
        
           
        tableDetailCommandeEtr.getItems().clear();
       if (tableCommandeEtr.getSelectionModel().getSelectedItem() == null ||
               dateEnPort.getValue().equals(null) 

               ) {
           
             nav.showAlert(Alert.AlertType.CONFIRMATION, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE); 
             
       }else {
       
          
            LocalDate localDateC=dateEnPort.getValue();
            
          Date EnPort=new SimpleDateFormat("yyyy-MM-dd").parse(localDateC.toString());
          
          
            commandeEtrangere=tableCommandeEtr.getSelectionModel().getSelectedItem();
         
            commandeEtrangere.setEtat(Constantes.ETAT_COMMANDE_EN_PORT);
            commandeEtrangere.setDateEnPort(EnPort);

            commandeEtrangereDAO.edit(commandeEtrangere);
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.COMMANDE_VALIDER); 
  
              setColumnPropertiesDetailCommande();
              loadDetail();
              
 
               dateEnPort.setValue(null);

                         
              
       }
        
        
        
    }

    @FXML
    private void supprimerOnAction(ActionEvent event) {
    }

    @FXML
    private void modifierOnAction(ActionEvent event) {
    }
    
}
