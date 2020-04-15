/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Douane;

import static Controller.Livraision.SuiviCommandeController.setColumnTextFieldConverter;
import Utils.Constantes;
import dao.Entity.CommandeEtrangere;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCommandeEtrangere;
import dao.Entity.DetailDossier;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SuiviDouaneController implements Initializable {

    @FXML
    private TableView<DetailDossier> tableDossier;
    @FXML
    private TableColumn<DetailDossier, String> nDossierColumn;
    @FXML
    private TableColumn<DetailDossier, Date> dateDossierColumn;
    @FXML
    private TableColumn<DetailDossier, String> etatColumn;
    @FXML
    private TextField numComRechField;
    @FXML
    private DatePicker dateCreationPicker;
    @FXML
    private TextField tauxDouaneField;
    @FXML
    private TextField valeurPayerField;
    @FXML
    private TextField montantUnitaireField;
    @FXML
    private TextField fraisTransitaireField;
    @FXML
    private TextField fraisPortField;
    @FXML
    private TextField autreFraisField;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnrefrech;
    @FXML
    private TableView<CommandeEtrangere> tableCommande;
    @FXML
    private TableColumn<CommandeEtrangere, String> conteneurColumn;
    @FXML
    private TableColumn<CommandeEtrangere, String> pokingColumn;
    @FXML
    private TableColumn<CommandeEtrangere, String> numCommandeColumn;
    @FXML
    private TableColumn<CommandeEtrangere, Date> dateCommandeColumn;
    @FXML
    private TableColumn<CommandeEtrangere, String> fourColumn;
    @FXML
    private TableColumn<CommandeEtrangere, String> numFactureColumn;
    
    @FXML
    private TableView<DetailCommandeEtrangere> tableDetailCommande;
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> codeArtColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> quantiteRecuColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> qteAppliquer1Column;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> valeurDouane1Column;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> qteAppliquer2Column;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> valeurDouane2Column;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> valeurGlobalColumn;
    /**
     * Initializes the controller class.
     */

    navigation nav = new navigation();
    
    CommandeEtrangere commandeEtrangere = new CommandeEtrangere();
    DetailDossier detailDossier = new DetailDossier();
    DetailCommandeEtrangereDAO detailCommandeEtrangereDAO = new DetailCommandeEtrangereDAOImpl();
    CommandeEtrangereDAO commandeEtrangereDAO = new CommandeEtrangereDAOImpl();
    DetailDossierDAO detailDossierDAO = new DetailDossierDAOImpl();
   
    private final ObservableList<DetailDossier> listeDetailDossier=FXCollections.observableArrayList();
    private final ObservableList<DetailCommandeEtrangere> listeDetailCommandeEtrangere=FXCollections.observableArrayList();
    private final ObservableList<CommandeEtrangere> listeCommandeEtrangere=FXCollections.observableArrayList();
    

    
    
    
    
    
       void setColumnProperties(){

        nDossierColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailDossier, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailDossier, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumDossier());
                }
             });
        
        dateDossierColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailDossier, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<DetailDossier, Date> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDateDossier());
                }
             });
        
         etatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailDossier, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailDossier, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getEtat());
                }
             });
    
     }
    
       
       
     void loadDetail(){
        
        listeDetailDossier.clear();
        listeDetailDossier.addAll(detailDossierDAO.findAll());
        tableDossier.setItems(listeDetailDossier);
    }
    
     
     

      void setColumnPropertiesCommande() {

//        conteneurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeEtrangere, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeEtrangere, String> p) {
//                return new ReadOnlyObjectWrapper(p.getValue().getNumConteneur());
//            }
//        });
//
//        pokingColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeEtrangere, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeEtrangere, String> p) {
//                return new ReadOnlyObjectWrapper(p.getValue().getNumPoking());
//            }
//
//        });

      
         numCommandeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeEtrangere, String> p) {

                    return new ReadOnlyObjectWrapper((p.getValue().getnCommande()));
                }
                
             });
        
           dateCommandeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeEtrangere, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<CommandeEtrangere, Date> p) {

                    return new ReadOnlyObjectWrapper(p.getValue().getDateCommande());
                }
                
             });
         
         fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeEtrangere, String> p) {

                    return new ReadOnlyObjectWrapper((p.getValue().getFourisseur().getNom()));
                }
                
             });
        
    

//          numFactureColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeEtrangere, String>, ObservableValue<String>>() {
//                @Override
//                public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeEtrangere, String> p) {
//
//                    return new ReadOnlyObjectWrapper((p.getValue().getNumFacture()));
//                }
//                
//             });
        

    }
     
      void setColumnPropertiesDetailCommande(){

         codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getArticle().getCode());
            }
        });

         libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getArticle().getLibelle());
            }
        });
        
         qteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal> p) {
                  
                    return new ReadOnlyObjectWrapper(p.getValue().getQuantite());
                }
                
             });

         
         quantiteRecuColumn.setCellValueFactory(new PropertyValueFactory<DetailCommandeEtrangere, BigDecimal>("quantiteRecu"));

                setColumnTextFieldConverter(getConverter(), quantiteRecuColumn);
         
//         qteAppliquer1Column.setCellValueFactory(new PropertyValueFactory<DetailCommandeEtrangere, BigDecimal>("quantiteApplique1"));
//
//                setColumnTextFieldConverter(getConverter(), qteAppliquer1Column);
//                
//        
//          qteAppliquer2Column.setCellValueFactory(new PropertyValueFactory<DetailCommandeEtrangere, BigDecimal>("quantiteApplique2"));
//
//                setColumnTextFieldConverter(getConverter(), qteAppliquer2Column);
//         
//                
//         valeurDouane1Column.setCellValueFactory(new PropertyValueFactory<DetailCommandeEtrangere, BigDecimal>("valeurDouane1"));
//
//                setColumnTextFieldConverter(getConverter(), valeurDouane1Column);
//                
//                
//         valeurDouane2Column.setCellValueFactory(new PropertyValueFactory<DetailCommandeEtrangere, BigDecimal>("valeurDouane2"));
//
//                setColumnTextFieldConverter(getConverter(), valeurDouane2Column);
//         
//         valeurGlobalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal>, ObservableValue<BigDecimal>>() {
//                @Override
//                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal> p) {
//                    
//                  DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
//                dfs.setDecimalSeparator(',');
//                dfs.setGroupingSeparator('.');
//                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
//                df.setGroupingUsed(true);
//                
//                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getValeurGlobal()));
//                }
//                
//             });
                
    }
      
          public static void setColumnTextFieldConverter(StringConverter converter, TableColumn... columns) {

        for (TableColumn tableColumn : columns) {

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        }
    }

    private StringConverter getConverter() {
        StringConverter<BigDecimal> converter = new StringConverter<BigDecimal>(){
            @Override
            public BigDecimal fromString(String string) {

                try {
                    BigDecimal valeur;
                    valeur= new BigDecimal(string);
                    
                    return valeur;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString(BigDecimal object) {

                if (object == null) {
                    return null;
                }
                return  String.valueOf(object);
            }
        };

        return converter;
    }
      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setColumnProperties();
        loadDetail();
    }    


    @FXML
    private void rechercheNumComOnKeyPressed(KeyEvent event) {
    }

    @FXML
    private void afficherCmdOnMouseClicked(MouseEvent event) {
        
               tableDetailCommande.getItems().clear();
               setColumnPropertiesCommande();
               listeCommandeEtrangere.clear();
if (tableDossier.getSelectionModel().getSelectedIndex()!=-1){
    
        detailDossier = tableDossier.getSelectionModel().getSelectedItem();

        listeCommandeEtrangere.addAll(commandeEtrangereDAO.findCommandeEtrangereByNumDossier(detailDossier.getNumDossier()));

        tableCommande.setItems(listeCommandeEtrangere);

        tableCommande.setEditable(true);

      
}
        
    }

    @FXML
    private void afficherDetailCmdOnMouseClicked(MouseEvent event) {
        
                      listeDetailCommandeEtrangere.clear();
        setColumnPropertiesDetailCommande();
        
        if (tableCommande.getSelectionModel().getSelectedIndex()!=-1){
            
       commandeEtrangere = tableCommande.getSelectionModel().getSelectedItem();
            
       listeDetailCommandeEtrangere.addAll(commandeEtrangere.getDetailCommandeEtrangere());
       
       tableDetailCommande.setItems(listeDetailCommandeEtrangere);
        

       tableDetailCommande.setEditable(true);
}
    
        
    }

    @FXML
    private void btnAjouterOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRefrechOnAction(ActionEvent event) {
    }
    
}
