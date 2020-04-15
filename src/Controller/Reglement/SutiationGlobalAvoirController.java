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
public class SutiationGlobalAvoirController implements Initializable {

    @FXML
    private TableView<SituationGlobalAvoir> tableSituationAvoir;
    @FXML
    private TableColumn<SituationGlobalAvoir, String> codeColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, String> libelleColumn;
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

    @FXML
    private TableView<SituationGlobalAvoir> detailTableSituationAvoir;
    @FXML
    private TableColumn<SituationGlobalAvoir, String> detailCodeColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, String> detailLibelleColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> detailAvoirColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> detailFactureAvoirColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> detailQteResteeColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> detailPrixColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> detailMontantColumn;
     @FXML
    private TableColumn<SituationGlobalAvoir, String> clientColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> detailMontantTvaColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> detailMontantTtcColumn;
     
    @FXML
    private TextField detailMontantTotalField;
    /**
     * Initializes the controller class.
     */
       
    FactureAvoirOulmesDAO factureAvoirOulmesDAO = new FactureAvoirOulmesDAOImpl();
    AvoirOulmesDAO avoirOulmesDAO = new AvoirOulmesDAOImpl();
    navigation nav = new navigation(); 
    
     private final  ObservableList<SituationGlobalAvoir> listeSituationGlobalAvoir = FXCollections.observableArrayList();    
     private final  ObservableList<SituationGlobalAvoir> listeDetailSituationGlobalAvoir = FXCollections.observableArrayList();    
    
   
             
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Boolean exist = false;
        
      BigDecimal montantTotal = BigDecimal.ZERO;
        
        List <Object[]> listeObjectFactureAvoir =factureAvoirOulmesDAO.findBySituationGlobal();
        List <Object[]> listeObjectAvoir =avoirOulmesDAO.findBySituationGlobal();

             listeSituationGlobalAvoir.clear();
         
            for(int i=0; i<listeObjectFactureAvoir.size(); i++) {

                    Object[] object=listeObjectFactureAvoir.get(i);
                   
                    PrixOulmes article =(PrixOulmes)object[0];
                    BigDecimal factureAvoir = (BigDecimal)object[1]; 
                    String client = (String)object[2]; 
                    
                    
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

                    PrixOulmes article =(PrixOulmes)object[0];
                    BigDecimal avoir = (BigDecimal)object[1]; 
                    String client = (String)object[2]; 
                    
                    if(article == null && avoir == null && client == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{
                        
                      
                      
                         
                        for(int j=0; j<listeSituationGlobalAvoir.size(); j++) {
                     
                     SituationGlobalAvoir situationGlobalAvoir = listeSituationGlobalAvoir.get(j);
       
                     
                     if(situationGlobalAvoir.getPrixOulmes().getId() == article.getId() && situationGlobalAvoir.getClient().equals(client)){
                     
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

         codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, String> p) {
                    
        
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
                
             });
         
          libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, String> p) {
                    
             
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
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

     void setColumnPropertiesDetail(){
      
      
        detailCodeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, String> p) {
                    
        
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
                
             });
         
          detailLibelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, String> p) {
                    
             
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
                
             });
          
            clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, String> p) {
                    
        
                    return new ReadOnlyObjectWrapper(p.getValue().getClient());
                }
                
             });
          
           detailAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
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
           
           
            detailFactureAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
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
      
            detailQteResteeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
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
            
            detailPrixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
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
      
            
              detailMontantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
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
    
              
              detailMontantTvaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
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
              
              detailMontantTtcColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
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
      }
      
      
    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        
            detailTableSituationAvoir.getItems().clear();
          
if (tableSituationAvoir.getSelectionModel().getSelectedIndex()!=-1){

         SituationGlobalAvoir situationGlobalAvoir=tableSituationAvoir.getSelectionModel().getSelectedItem();
          
          
            BigDecimal montantTotal = BigDecimal.ZERO;
        
             List <Object[]> listeObjectAvoir =avoirOulmesDAO.findBySituationGlobalArticle(situationGlobalAvoir.getPrixOulmes().getProduit().getCode());
             List <Object[]> listeObjectFactureAvoir =factureAvoirOulmesDAO.findBySituationGlobalArticle(situationGlobalAvoir.getPrixOulmes().getProduit().getCode());
       
        
    System.out.println("listeObjectFactureAvoir "+listeObjectFactureAvoir.size());    
    System.out.println("listeObjectAvoir "+listeObjectAvoir.size());
    
            listeDetailSituationGlobalAvoir.clear();
         
            for(int i=0; i<listeObjectAvoir.size(); i++) {

                    Object[] object=listeObjectAvoir.get(i);
                   
                    PrixOulmes article =(PrixOulmes)object[0];
                    BigDecimal avoir = (BigDecimal)object[1]; 
                    String client = (String)object[2]; 
           
                    
                    
                    if(article == null && avoir == null && client == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{


               SituationGlobalAvoir situationGlobalAvoirTmp = new SituationGlobalAvoir();
               
                  situationGlobalAvoirTmp.setFactureAvoirQte(BigDecimal.ZERO);
                  situationGlobalAvoirTmp.setPrixOulmes(article);
                  situationGlobalAvoirTmp.setPrix(article.getPrix());
                  situationGlobalAvoirTmp.setClient(client);
                  situationGlobalAvoirTmp.setAvoirQte(avoir);
                  
                     System.out.println("avoir "+avoir);
                     
                  situationGlobalAvoirTmp.setQteRestee(avoir.subtract(situationGlobalAvoirTmp.getFactureAvoirQte()));
                  situationGlobalAvoirTmp.setMontant(situationGlobalAvoirTmp.getQteRestee().multiply(situationGlobalAvoirTmp.getPrix()));
                  situationGlobalAvoirTmp.setMontantTVA(situationGlobalAvoirTmp.getMontant().multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR));
                  situationGlobalAvoirTmp.setMontantTTC(situationGlobalAvoirTmp.getMontant().add(situationGlobalAvoirTmp.getMontantTVA()).setScale(2,RoundingMode.FLOOR));
                  
                  listeDetailSituationGlobalAvoir.add(situationGlobalAvoirTmp);
                  
                  
                    }
            }
            
            
         
            for(int i=0; i<listeObjectFactureAvoir.size(); i++) {

                    Object[] object=listeObjectFactureAvoir.get(i);
                   

                    PrixOulmes article =(PrixOulmes)object[0];
                    BigDecimal factureAvoir = (BigDecimal)object[1]; 
                    String client = (String)object[2]; 
                    
                    if(article == null && factureAvoir == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{
    
                         
                        for(int j=0; j<listeDetailSituationGlobalAvoir.size(); j++) {
                     
                 SituationGlobalAvoir   situationGlobalAvoirTmp = listeDetailSituationGlobalAvoir.get(j);
                     
  
                 
                     if(situationGlobalAvoirTmp.getPrixOulmes().getId() == article.getId() && situationGlobalAvoirTmp.getClient().equals(client) && situationGlobalAvoirTmp.getAvoirQte().compareTo(factureAvoir)==0){
                     
                     situationGlobalAvoirTmp.setFactureAvoirQte(factureAvoir);
    
                     
                     situationGlobalAvoirTmp.setQteRestee(situationGlobalAvoirTmp.getAvoirQte().subtract(factureAvoir));
                     situationGlobalAvoirTmp.setMontant(situationGlobalAvoirTmp.getQteRestee().multiply(situationGlobalAvoirTmp.getPrix()));
                     situationGlobalAvoirTmp.setMontantTVA(situationGlobalAvoirTmp.getMontant().multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR));
                     situationGlobalAvoirTmp.setMontantTTC(situationGlobalAvoirTmp.getMontant().add(situationGlobalAvoirTmp.getMontantTVA()).setScale(2,RoundingMode.FLOOR));
                     
                     listeDetailSituationGlobalAvoir.set(j, situationGlobalAvoirTmp);

                     }
                     }
                    }
            }
            
             for(int k=0; k<listeDetailSituationGlobalAvoir.size(); k++) {
                     
                     SituationGlobalAvoir situationGlobalAvoirTmp = listeDetailSituationGlobalAvoir.get(k);
              
                       montantTotal = montantTotal.add(situationGlobalAvoirTmp.getMontantTTC()) ;
             }
            
                DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
            detailMontantTotalField.setText(df.format(montantTotal));
            

            detailTableSituationAvoir.setItems(listeDetailSituationGlobalAvoir);
            setColumnPropertiesDetail();
        
        
         
}
        
    }
    
}
