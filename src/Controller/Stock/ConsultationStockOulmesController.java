/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import dao.Entity.StockArt;
import dao.Manager.StockArtDAO;
import dao.ManagerImpl.StockArtDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class ConsultationStockOulmesController implements Initializable {

    @FXML
    private TableView<StockArt> tableStock;
    @FXML
    private TableColumn<StockArt, String> codeArtColumn;
    @FXML
    private TableColumn<StockArt, String> designationColumn;
    @FXML
    private TableColumn<StockArt, BigDecimal> stockColumn;
    @FXML
    private TextField codeRechField;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    @FXML
    private TextField designationRechField;
    
    /**
     * Initializes the controller class.
     */
         private final ObservableList<StockArt> listeStockArt=FXCollections.observableArrayList();  
     StockArtDAO stockArtDAO = new StockArtDAOImpl();
    
     navigation nav = new navigation();
     StockArt stockArt = new StockArt();
    
 
     
     
      void setColumnProperties(){
        
      codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockArt, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<StockArt, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });
   
      designationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockArt, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<StockArt, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
             });
      
      stockColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockArt, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<StockArt, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getStock()));
                }
                
             });

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setColumnProperties();
         loadDetail();
    }    

      void loadDetail(){
          
        listeStockArt.clear();
        listeStockArt.addAll(stockArtDAO.findAll());
        tableStock.setItems(listeStockArt);
    }
    
    @FXML
    private void afficherDetailAndStockProdOnMouseClicked(MouseEvent event) {
    }


    @FXML
    private void recherchMouseClicked(MouseEvent event) {

    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
                 try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationStockOulmesController.class.getResource(nav.getiReportConsultationStock()));


             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeStockArt));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
}

}
