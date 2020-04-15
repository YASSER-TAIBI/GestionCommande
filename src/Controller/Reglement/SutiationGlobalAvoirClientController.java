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
import dao.Manager.FactureAvoirOulmesDAO;
import dao.ManagerImpl.AvoirOulmesDAOImpl;
import dao.ManagerImpl.FactureAvoirOulmesDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

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
    private TableColumn<SituationGlobalAvoir, BigDecimal> prixColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> montantColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> MontantTvaColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> MontantTtcColumn;
    @FXML
    private TextField montantTotalField;

    /**
     * Initializes the controller class.
     */
    
      FactureAvoirOulmesDAO factureAvoirOulmesDAO = new FactureAvoirOulmesDAOImpl();
    AvoirOulmesDAO avoirOulmesDAO = new AvoirOulmesDAOImpl();
    navigation nav = new navigation(); 
    
     private final  ObservableList<SituationGlobalAvoir> listeSituationGlobalAvoir = FXCollections.observableArrayList();    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
               
        Boolean exist = false;
        
      BigDecimal montantTotal = BigDecimal.ZERO;
        
        List <Object[]> listeObjectFactureAvoir =factureAvoirOulmesDAO.findBySituationGlobalClient();
        List <Object[]> listeObjectAvoir =avoirOulmesDAO.findBySituationGlobalClient();

             listeSituationGlobalAvoir.clear();
         
            for(int i=0; i<listeObjectFactureAvoir.size(); i++) {

                    Object[] object=listeObjectFactureAvoir.get(i);
                   
                    String client =(String)object[0];
                    BigDecimal factureAvoir = (BigDecimal)object[1]; 
                    PrixOulmes article = (PrixOulmes)object[2]; 
                    
                    
                    if(article == null && factureAvoir == null && client == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               SituationGlobalAvoir situationGlobalAvoir = new SituationGlobalAvoir();
               
                  situationGlobalAvoir.setFactureAvoirQte(factureAvoir);
                  situationGlobalAvoir.setPrixOulmes(article);
                  situationGlobalAvoir.setPrix(article.getPrix());
                  situationGlobalAvoir.setClient(client);
                  situationGlobalAvoir.setAvoirQte(BigDecimal.ZERO);
                  situationGlobalAvoir.setQteRestee(BigDecimal.ZERO);
                  situationGlobalAvoir.setMontant(BigDecimal.ZERO);
                  situationGlobalAvoir.setMontantTVA(BigDecimal.ZERO);
                  situationGlobalAvoir.setMontantTTC(BigDecimal.ZERO);
                  
                  listeSituationGlobalAvoir.add(situationGlobalAvoir);
                    }
            }
            
            
         
            for(int i=0; i<listeObjectAvoir.size(); i++) {

                exist = false;
                    Object[] object=listeObjectAvoir.get(i);

                    String client =(String)object[0];
                    BigDecimal avoir = (BigDecimal)object[1]; 
                    PrixOulmes article = (PrixOulmes)object[2]; 
                    
                    if(article == null && avoir == null && client == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{
                        
                      
                      
                         
                        for(int j=0; j<listeSituationGlobalAvoir.size(); j++) {
                     
                     SituationGlobalAvoir situationGlobalAvoir = listeSituationGlobalAvoir.get(j);
       
                     
                     if(situationGlobalAvoir.getClient().equals(client)){
                     
                     situationGlobalAvoir.setAvoirQte(avoir);
                     situationGlobalAvoir.setQteRestee(avoir.subtract(situationGlobalAvoir.getFactureAvoirQte()));
                     situationGlobalAvoir.setMontant(situationGlobalAvoir.getQteRestee().multiply(situationGlobalAvoir.getPrix()));
                     situationGlobalAvoir.setMontantTVA(situationGlobalAvoir.getMontant().multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR));
                     situationGlobalAvoir.setMontantTTC(situationGlobalAvoir.getMontant().add(situationGlobalAvoir.getMontantTVA()).setScale(2,RoundingMode.FLOOR));
                     
                     listeSituationGlobalAvoir.set(j, situationGlobalAvoir);
                     exist = true;
                     
                     }
                     
                     }
                        if (exist == false){
                          SituationGlobalAvoir situationGlobalAvoir = new SituationGlobalAvoir();
                          
                          situationGlobalAvoir.setAvoirQte(avoir);
                          situationGlobalAvoir.setClient(client);
                          situationGlobalAvoir.setPrixOulmes(article);
                          situationGlobalAvoir.setFactureAvoirQte(BigDecimal.ZERO);
                          situationGlobalAvoir.setPrix(article.getPrix());
                          situationGlobalAvoir.setQteRestee(avoir.subtract(situationGlobalAvoir.getFactureAvoirQte()));
                          situationGlobalAvoir.setMontant(situationGlobalAvoir.getQteRestee().multiply(situationGlobalAvoir.getPrix()));
                          situationGlobalAvoir.setMontantTVA(situationGlobalAvoir.getMontant().multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR));
                          situationGlobalAvoir.setMontantTTC(situationGlobalAvoir.getMontant().add(situationGlobalAvoir.getMontantTVA()).setScale(2,RoundingMode.FLOOR));
                          
                     listeSituationGlobalAvoir.add(situationGlobalAvoir);
                        }
            
                        
                    }
            }
            
             for(int k=0; k<listeSituationGlobalAvoir.size(); k++) {
                     
                     SituationGlobalAvoir situationGlobalAvoir = listeSituationGlobalAvoir.get(k);
              
                       montantTotal = montantTotal.add(situationGlobalAvoir.getMontantTTC()) ;
             }
            
                DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
            montantTotalField.setText(df.format(montantTotal));
            

            
            tableSituationAvoir.setItems(listeSituationGlobalAvoir);
            setColumnProperties();
        
        
    }    

      void setColumnProperties() {

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
            
            prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrix()));
                }
                
             });
      
              montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });

              MontantTvaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantTVA()));
                }
                
             });
              
              MontantTtcColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantTTC()));
                }
                
             });
              
               tableSituationAvoir.setEditable(false);
              
    }
    
    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {
    }
    
}
