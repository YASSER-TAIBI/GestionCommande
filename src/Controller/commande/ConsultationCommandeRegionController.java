/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.commande;

import Utils.Constantes;
import dao.Entity.CommandeRegion;
import dao.Entity.DetailCommandeRegion;
import dao.Manager.CommandeRegionDAO;
import dao.Manager.DetailCommandeRegionDAO;
import dao.ManagerImpl.CommandeRegionDAOImpl;
import dao.ManagerImpl.DetailCommandeRegionDAOImpl;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ConsultationCommandeRegionController implements Initializable {

     @FXML
    private Button btnRefrech;
    @FXML
    private TableView<CommandeRegion> tableCommande;
    @FXML
    private TableColumn<CommandeRegion, String> nComColumn;
    @FXML
    private TableColumn<CommandeRegion, Date> dateCreationColumn;
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

    /**
     * Initializes the controller class.
     */
    
      private final ObservableList<DetailCommandeRegion> listeDetailCommandeRegion = FXCollections.observableArrayList();
    private final ObservableList<CommandeRegion> listeCommandeRegion = FXCollections.observableArrayList();
    
    CommandeRegionDAO commandeRegionDAO = new CommandeRegionDAOImpl();
    DetailCommandeRegionDAO detailCommandeRegionDAO = new DetailCommandeRegionDAOImpl();
        CommandeRegion commandeRegion = new CommandeRegion();
     navigation nav = new navigation();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           
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
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));

 

    }

    void loadDetail() {

        listeCommandeRegion.clear();
        listeCommandeRegion.addAll(commandeRegionDAO.findByTypeComAndDepot(Constantes.COMMANDE_INTERNE,nav.getUtilisateur().getDepot().getId()));
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


    }
    
    
    @FXML
    private void refrechOnAction(ActionEvent event) {
        
                listeDetailCommandeRegion.clear();
        
        loadDetail();
        setColumnProperties();
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
    
}
