/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.commande;

import Utils.Constantes;
import dao.Entity.CommandeRegion;
import dao.Entity.DetailCommandeRegion;
import dao.Entity.DetailReceptionRegion;
import dao.Manager.CommandeRegionDAO;
import dao.Manager.DetailCommandeRegionDAO;
import dao.Manager.DetailReceptionRegionDAO;
import dao.ManagerImpl.CommandeRegionDAOImpl;
import dao.ManagerImpl.DetailCommandeRegionDAOImpl;
import dao.ManagerImpl.DetailReceptionRegionDAOImpl;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ConsultionGlobalCommandeRegionController implements Initializable {

    @FXML
    private TableView<CommandeRegion> tableCommande;
    @FXML
    private TableColumn<CommandeRegion, String> nComColumn;
    @FXML
    private TableColumn<CommandeRegion, Date> dateCreationColumn;
    @FXML
    private TableColumn<CommandeRegion, String> depotColumn;
    @FXML
    private TableColumn<CommandeRegion, String> etatColumn;
    @FXML
    private TableView<DetailCommandeRegion> tableDetailCommande;
    @FXML
    private TableColumn<DetailCommandeRegion, String> codeMPColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, BigDecimal> quantiteRecuColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, BigDecimal> quantiteRestantColumn;
    @FXML
    private TableView<DetailReceptionRegion> tableReception;
    @FXML
    private TableColumn<DetailReceptionRegion, String> numRecepColumn;
    @FXML
    private TableColumn<DetailReceptionRegion, String> codeMPRecepColumn;
    @FXML
    private TableColumn<DetailReceptionRegion, String> libelleRecepColumn;
    @FXML
    private TableColumn<DetailReceptionRegion, String> numComRecepColumn;
    @FXML
    private TableColumn<DetailReceptionRegion, String> clientColumn;
    @FXML
    private TableColumn<DetailReceptionRegion, String> fourColumn;
    @FXML
    private TableColumn<DetailReceptionRegion, BigDecimal> quantiteRecuRecepColumn;
    @FXML
    private Button btnRefrech;
    /**
     * Initializes the controller class.
     */
    private final ObservableList<DetailReceptionRegion> listeDetailReceptionRegion = FXCollections.observableArrayList();
          private final ObservableList<DetailCommandeRegion> listeDetailCommandeRegion = FXCollections.observableArrayList();
    private final ObservableList<CommandeRegion> listeCommandeRegion = FXCollections.observableArrayList();
    
      CommandeRegionDAO commandeRegionDAO = new CommandeRegionDAOImpl();
      DetailReceptionRegionDAO detailReceptionRegionDAO = new DetailReceptionRegionDAOImpl();
    DetailCommandeRegionDAO detailCommandeRegionDAO = new DetailCommandeRegionDAOImpl();
        CommandeRegion commandeRegion = new CommandeRegion();
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
           loadDetail();
        setColumnProperties();
        
        
    }    

        void setColumnProperties() {

        nComColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeRegion, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeRegion, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getnCommande());
            }

        });
        dateCreationColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        
        depotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeRegion, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeRegion, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDepot().getLibelle());
            }

        });
        
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));

 

    }

    void loadDetail() {

        listeCommandeRegion.clear();
        listeCommandeRegion.addAll(commandeRegionDAO.findAll(Constantes.COMMANDE_INTERNE));
        tableCommande.setItems(listeCommandeRegion);
    }
    
     void setColumnPropertiesDetailCommande() {

        codeMPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeRegion, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
            }
        });

        libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeRegion, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
            }

        });

        quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal> p) {
                    
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                 
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });

    quantiteRecuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal> p) {
                    
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                 
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRecu()));
                }
                
             });

    quantiteRestantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal> p) {
                    
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

        codeMPRecepColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailReceptionRegion, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailReceptionRegion, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDetailCommandeRegion().getMatierePremier().getCode());
            }
        });

        libelleRecepColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailReceptionRegion, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailReceptionRegion, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDetailCommandeRegion().getMatierePremier().getNom());
            }
        });
        
        numComRecepColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailReceptionRegion, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailReceptionRegion, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNumCommande());
            }
        });
        
        numRecepColumn.setCellValueFactory(new PropertyValueFactory<DetailReceptionRegion, String>("numReceptionRegion"));
         
            clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailReceptionRegion, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailReceptionRegion, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getClientMP().getNom());
            }
        });

        fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailReceptionRegion, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailReceptionRegion, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFourisseur().getNom());
            }
        });
            
        quantiteRecuRecepColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailReceptionRegion, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailReceptionRegion, BigDecimal> p) {
                    
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
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        
             setColumnPropertiesDetailCommande();
        listeDetailCommandeRegion.clear();
        
if (tableCommande.getSelectionModel().getSelectedIndex()!=-1){
    
        commandeRegion = tableCommande.getSelectionModel().getSelectedItem();

      listeDetailCommandeRegion.addAll(detailCommandeRegionDAO.findDetailCommandeByEtat(commandeRegion, Constantes.ETAT_AFFICHAGE));
      
        tableDetailCommande.setItems(listeDetailCommandeRegion);
        
}
        
    }

    @FXML
    private void afficherReceptionOnMouseClicked(MouseEvent event) {
        
        
          listeDetailReceptionRegion.clear();
        setColumnPropertiesDetailReception();
        
        if (tableDetailCommande.getSelectionModel().getSelectedIndex()!=-1){
        
         DetailCommandeRegion detailCommandeRegion = tableDetailCommande.getSelectionModel().getSelectedItem();
        
        
       listeDetailReceptionRegion.addAll(detailReceptionRegionDAO.findReceptionRegionBydetailCom(detailCommandeRegion.getId()));
       tableReception.setItems(listeDetailReceptionRegion);
    
//          for( int i = 0;i<listeDetailReceptionRegion.size() ;i++ ){
//              
//             DetailReceptionRegion detailReceptionRegion = listeDetailReceptionRegion.get(i);
//                  
//                  if (detailReceptionRegion.getQuantiteRecu().compareTo(BigDecimal.ZERO)==0){
//                  
//                  listeDetailReceptionRegion.remove(i);
//                     
//                  }
//              
//              }
//    
//        tableReception.setItems(listeDetailReceptionRegion);
        
}
    
        
        
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
        
              listeDetailCommandeRegion.clear();
              listeDetailReceptionRegion.clear();
        
        loadDetail();
        setColumnProperties();
        
    }
    
}
