/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CommandeEtrangere;

import Utils.Constantes;
import dao.Entity.CommandeEtrangere;
import dao.Entity.DetailCommandeEtrangere;
import dao.Entity.DetailDossier;
import dao.Entity.MatierePremier;
import dao.Manager.CommandeEtrangereDAO;
import dao.Manager.DetailCommandeEtrangereDAO;
import dao.Manager.DetailDossierDAO;
import dao.ManagerImpl.CommandeEtrangereDAOImpl;
import dao.ManagerImpl.DetailCommandeEtrangereDAOImpl;
import dao.ManagerImpl.DetailDossierDAOImpl;
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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class DossierDouaneController implements Initializable {

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
    private TableColumn<CommandeEtrangere, Boolean> actionColumn;
    
    
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
    private DatePicker dateDateDossier;
    @FXML
    private TextField numDossier;
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
    DetailDossierDAO detailDossierDAO = new DetailDossierDAOImpl();

    
     navigation nav = new navigation();
     CommandeEtrangere commandeEtrangere;
     
     DetailCommandeEtrangere detailCommandeEtrangere;
    
   
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
        
             actionColumn.cellValueFactoryProperty();
             actionColumn.setCellValueFactory((cellData) -> {
              CommandeEtrangere cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.isAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionColumn.setEditable(true);
             
        tableCommandeEtr.setEditable(true);
 
    }
    
     void loadDetail(){
        
        listeCommandeEtrangere.clear();
        listeCommandeEtrangere.addAll(commandeEtrangereDAO.findCommandeEtrangereByEtat(Constantes.ETAT_COMMANDE_EN_PORT));
        tableCommandeEtr.setItems(listeCommandeEtrangere);
    }
    
    
     void setColumnPropertiesDetailCommande()   {
        
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
                    return new ReadOnlyObjectWrapper(p.getValue().getTauxDevise().getDevise());
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        setColumnProperties();
        loadDetail();
        
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
//
    @FXML
    private void ajouterOnAction(ActionEvent event) throws ParseException {

          boolean  variable =false; 
            if (numDossier.getText().equalsIgnoreCase("") || dateDateDossier.getValue()== null ) {
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                
            } else{ 
                

      for( int rows = 0;rows<tableCommandeEtr.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
             

          LocalDate localDateC=dateDateDossier.getValue();
          Date dateDos=new SimpleDateFormat("yyyy-MM-dd").parse(localDateC.toString());
             
            CommandeEtrangere commandeEtrangere= tableCommandeEtr.getItems().get(rows);
//            commandeEtrangere.setNumDoussier(numDossier.getText());
//            commandeEtrangere.setDateDoussier(dateDos);
            commandeEtrangere.setEtat(Constantes.ETAT_COMMANDE_EN_ATTENTE);
            commandeEtrangere.setAction(false);

            commandeEtrangereDAO.edit(commandeEtrangere);
            

             variable = true;
       }
    }
       tableCommandeEtr.refresh();
   
               
      if (variable == false){
           nav.showAlert(Alert.AlertType.ERROR, "Atention", null, Constantes.REMPLIR_COCHE);
      }else{
          
          LocalDate localDateC=dateDateDossier.getValue();
          Date dateDos=new SimpleDateFormat("yyyy-MM-dd").parse(localDateC.toString());
          
           DetailDossier detailDossier = new DetailDossier();
           detailDossier.setDateCreation(new Date());
           detailDossier.setDateDossier(dateDos);
           detailDossier.setUtilisateurCreation(nav.getUtilisateur());
           detailDossier.setEtat(Constantes.ETAT_COMMANDE_EN_ATTENTE);
           detailDossier.setNumDossier(numDossier.getText());
            
            detailDossierDAO.add(detailDossier);
          

          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.REGLER_ENREGISTREMENT);

             setColumnProperties();
             loadDetail();
          
             listeDetailCommandeEtrangere.clear();
             numDossier.clear();
             dateDateDossier.setValue(null);

             
    }}
        
    }

    @FXML
    private void supprimerOnAction(ActionEvent event) {
    }

    @FXML
    private void modifierOnAction(ActionEvent event) {
    }
    
}
