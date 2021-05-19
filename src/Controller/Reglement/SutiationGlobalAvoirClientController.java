/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Utils.Constantes;
import dao.Entity.PrixOulmes;
import dao.Entity.SituationGlobalAvoir;
import dao.Manager.AvoirOulmesDAO;
import dao.Manager.DetailAvoirOulmesDAO;
import dao.Manager.FactureAvoirOulmesDAO;
import dao.ManagerImpl.AvoirOulmesDAOImpl;
import dao.ManagerImpl.DetailAvoirOulmesDAOImpl;
import dao.ManagerImpl.FactureAvoirOulmesDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
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
import javafx.scene.control.Label;
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
public class SutiationGlobalAvoirClientController implements Initializable {

    @FXML
    private TableView<SituationGlobalAvoir> tableSituationAvoir;
    @FXML
    private TableColumn<SituationGlobalAvoir, String> clientColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> avoirColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> factureAvoirColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> qteResteeColumn;

    @FXML
    private Label totalEcartDetail;
    @FXML
    private Label totalFactureAvDetail;
    @FXML
    private Label totalAvoirDetail;

    /**
     * Initializes the controller class.
     */
    
    AvoirOulmesDAO avoirOulmesDAO = new AvoirOulmesDAOImpl();
    navigation nav = new navigation(); 
    
     private final  ObservableList<SituationGlobalAvoir> listeSituationGlobalAvoir = FXCollections.observableArrayList();    
    @FXML
    private Button imprimerBtn;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
               
      BigDecimal montantAvoir = BigDecimal.ZERO;
      BigDecimal montantFactureAvoir = BigDecimal.ZERO;
      BigDecimal montantEcart = BigDecimal.ZERO;
        
        

        List <Object[]> listeObjectAvoir =avoirOulmesDAO.findBySituationGlobalClient();

             listeSituationGlobalAvoir.clear();
         
             System.out.println("listeObjectDetailAvoir: "+listeObjectAvoir.size());
             
            for(int i=0; i<listeObjectAvoir.size(); i++) {

                    Object[] object=listeObjectAvoir.get(i);
                   
                    String client =(String)object[0];
                    BigDecimal avoir = (BigDecimal)object[1]; 
                    BigDecimal factureAvoir = (BigDecimal)object[2]; 
                    BigDecimal ecart = (BigDecimal)object[3]; 
                    

               SituationGlobalAvoir situationGlobalAvoir = new SituationGlobalAvoir();
               
                  situationGlobalAvoir.setFactureAvoirQte(factureAvoir);
                  situationGlobalAvoir.setClient(client);
                  situationGlobalAvoir.setAvoirQte(avoir);
                  situationGlobalAvoir.setQteRestee(ecart);
                  
                  
                  montantAvoir = montantAvoir.add(situationGlobalAvoir.getAvoirQte()) ;
                  montantFactureAvoir = montantFactureAvoir.add(situationGlobalAvoir.getFactureAvoirQte()) ;
                  montantEcart = montantEcart.add(situationGlobalAvoir.getQteRestee()) ;
                  
                  listeSituationGlobalAvoir.add(situationGlobalAvoir);
                    
            }
            
              tableSituationAvoir.setItems(listeSituationGlobalAvoir);
            setColumnProperties();
            
                DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
            totalAvoirDetail.setText(df.format(montantAvoir));
            totalFactureAvDetail.setText(df.format(montantFactureAvoir));
            totalEcartDetail.setText(df.format(montantEcart));
            

          
        
        
    }    

      void setColumnProperties(){

         clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, String> p) {
                    
        
                    return new ReadOnlyObjectWrapper(p.getValue().getClient());
                }
                
             });
         
       
          
           avoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getAvoirQte()));
                }
                
             });
           
           
            factureAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getFactureAvoirQte()));
                }
                
             });
      
            qteResteeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQteRestee()));
                }
                
             });

               tableSituationAvoir.setEditable(false);
              
    }
    
    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void imprimerBtnOnAction(ActionEvent event) {
        
              try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationAvoirOulmesController.class.getResource(nav.getiReportSituationGlobalAvoirClient()));

            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeSituationGlobalAvoir));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationAvoirOulmesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
