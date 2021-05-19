/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import dao.Entity.HistoriqueAvoirOulmes;
import dao.Manager.HistoriqueAvoirOulmesDAO;
import dao.ManagerImpl.HistoriqueAvoirOulmesDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class HistoriqueAvoirPrixOulmesController implements Initializable {

    @FXML
    private TableView<HistoriqueAvoirOulmes> tableHistoriqueAvoirPrix;
    @FXML
    private TableColumn<HistoriqueAvoirOulmes, String> bonAvoirColumn;
    @FXML
    private TableColumn<HistoriqueAvoirOulmes, String> ProduitColumn;
    @FXML
    private TableColumn<HistoriqueAvoirOulmes, Date> dateModificationColumn;
    @FXML
    private TableColumn<HistoriqueAvoirOulmes, String> clientColumn;
    @FXML
    private TableColumn<HistoriqueAvoirOulmes, String> client2Column;
    @FXML
    private TableColumn<HistoriqueAvoirOulmes, BigDecimal> ancienPrixColumn;
    @FXML
    private TableColumn<HistoriqueAvoirOulmes, BigDecimal> nouveauPrixColumn;

    /**
     * Initializes the controller class.
     */
    
       HistoriqueAvoirOulmesDAO historiqueAvoirOulmesDAO = new HistoriqueAvoirOulmesDAOImpl();
          navigation nav = new navigation();
    
             private final ObservableList<HistoriqueAvoirOulmes> listHistoriqueAvoirOulmes=FXCollections.observableArrayList();
          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        setColumnProperties();
        loadDetail();
        
    }    

      void setColumnProperties(){
        
    
        
           bonAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriqueAvoirOulmes , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HistoriqueAvoirOulmes, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getBonAvoir());
            }

        });
          
          ProduitColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriqueAvoirOulmes , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HistoriqueAvoirOulmes, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
            }

        });
     
         dateModificationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriqueAvoirOulmes , Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<HistoriqueAvoirOulmes, Date> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDateSaisie());
            }

        });
         
    
         clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriqueAvoirOulmes , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HistoriqueAvoirOulmes, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getClient());
            }

        });
      
         client2Column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriqueAvoirOulmes , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HistoriqueAvoirOulmes, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getClient2());
            }

        });
         
          ancienPrixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriqueAvoirOulmes , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<HistoriqueAvoirOulmes, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getAncienPrix().setScale(6));
            }

        });
  
            nouveauPrixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriqueAvoirOulmes , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<HistoriqueAvoirOulmes, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNouveauPrix().setScale(6));
            }

        });
            
        

      
    }
    
    void loadDetail(){
        
        listHistoriqueAvoirOulmes.clear();
        listHistoriqueAvoirOulmes.addAll(historiqueAvoirOulmesDAO.findAll());
        tableHistoriqueAvoirPrix.setItems(listHistoriqueAvoirOulmes);
    }
    
    @FXML
    private void clickTablePrixBox(MouseEvent event) {
    }
    
}
