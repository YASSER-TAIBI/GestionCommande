/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.DetailBonLivraison;
import dao.Entity.PrixMoyen;
import dao.Entity.StockArt;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.StockArtDAO;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.StockArtDAOImpl;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TextField codeRechField;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    
    /**
     * Initializes the controller class.
     */
            private final  ObservableList<PrixMoyen> listePrixStock = FXCollections.observableArrayList();    
    
      DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl(); 
    
     navigation nav = new navigation();
     
     
    @FXML
    private TableView<PrixMoyen> tableStock; 
    @FXML 
    private TableColumn<PrixMoyen, String> codeColumn;
    @FXML
    private TableColumn<PrixMoyen, String> libelleColumn;
    @FXML
    private TableColumn<PrixMoyen, BigDecimal> qteColumn;
    @FXML
    private TableColumn<PrixMoyen, BigDecimal> prixColumn;
    @FXML
    private TableColumn<PrixMoyen, BigDecimal> montantHTColumn;
    @FXML
    private TableColumn<PrixMoyen, BigDecimal> montantTTCColumn;
    @FXML
    private TableColumn<PrixMoyen, BigDecimal> prixTTCColumn;
    
 
     
     
           void setColumnProperties(){

             codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

             libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
           
             qteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixMoyen, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<PrixMoyen, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQte()));
                }
                
             });
           
             prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));  
 
             prixTTCColumn.setCellValueFactory(new PropertyValueFactory<>("prixTTC"));  
             
                  montantHTColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixMoyen, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<PrixMoyen, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantHT()));
                }
                
             });
            
            montantTTCColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixMoyen, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<PrixMoyen, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantTTC()));
                }
                
             });
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
         loadDetail();
         setColumnProperties();
    }    

      void loadDetail(){
          
         String req = "";
        
        List<DetailBonLivraison>listeDetailBonLivraison =detailBonLivraisonDAO.findByDetailBonLivraisonAndPf(req);
        
 List <Object[]> listePrixMoyen =detailBonLivraisonDAO.findByPrixMoyenAndPf(req);

listePrixStock.clear();
         
            for(int i=0; i<listePrixMoyen.size(); i++) {

                    Object[] object=listePrixMoyen.get(i);
                   
                    String code =(String)object[0];
                    String libelle = (String)object[1]; 
                    BigDecimal qte = (BigDecimal)object[2]; 
                    BigDecimal prix = (BigDecimal)object[3]; 
                    
                    
               PrixMoyen prixMoyen = new PrixMoyen();
               
               
                  prixMoyen.setCode(code);
                  prixMoyen.setLibelle(libelle);
                  prixMoyen.setQte(qte);
                  prixMoyen.setPrix(prix);

                  listePrixStock.add(prixMoyen);
  
            }
        
            for (int i = 0; i < listePrixStock.size(); i++) {
                
                BigDecimal qteAct = BigDecimal.ZERO;
                BigDecimal prixMoyen = BigDecimal.ZERO;
                
                PrixMoyen pm = listePrixStock.get(i);
                
                for (int j = 0; j < listeDetailBonLivraison.size(); j++) {
                    
                         DetailBonLivraison detailBonLivraison = listeDetailBonLivraison.get(j);
                    
                    if (pm.getCode().equals(detailBonLivraison.getPrixOulmes().getProduit().getCode())) {
                        
                    
                    if (detailBonLivraison.getQuantite().compareTo(BigDecimal.ZERO)>0){
                    prixMoyen = (qteAct.multiply(prixMoyen)).add(detailBonLivraison.getQuantite().multiply(detailBonLivraison.getPrix())).divide(qteAct.add(detailBonLivraison.getQuantite()), 9, RoundingMode.FLOOR).setScale(9, RoundingMode.FLOOR);
                    qteAct= qteAct.add(detailBonLivraison.getQuantite());
                    }
                }
                }
                
               pm.setPrix(prixMoyen);
               pm.setPrixTTC(prixMoyen.multiply(new BigDecimal(1.2)).setScale(9, RoundingMode.FLOOR));
               pm.setMontantHT(pm.getQte().multiply(prixMoyen).setScale(2, RoundingMode.FLOOR));    
               pm.setMontantTTC((pm.getQte().multiply(prixMoyen).multiply(new BigDecimal(1.2))).setScale(2, RoundingMode.FLOOR));
            listePrixStock.set(i, pm);
        }
        
         tableStock.setItems(listePrixStock);
   

          
    }
    


    @FXML
    private void recherchMouseClicked(MouseEvent event) {

                  
        String req = "";
        
        if (codeRechField.getText().equals("") ){
        
           nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
            
        }
        else if (!codeRechField.getText().equals("") ){
            
            listePrixStock.clear();
            
        req= "and c.prixOulmes.produit.code='"+codeRechField.getText()+"'";
        
        }
       
     
        List<DetailBonLivraison>listeDetailBonLivraison =detailBonLivraisonDAO.findByDetailBonLivraisonAndPf(req);

        List <Object[]> listePrixMoyen =detailBonLivraisonDAO.findByPrixMoyenAndPf(req);

         
            for(int i=0; i<listePrixMoyen.size(); i++) {

                    Object[] object=listePrixMoyen.get(i);
                   
                    String code =(String)object[0];
                    String libelle = (String)object[1]; 
                    BigDecimal qte = (BigDecimal)object[2]; 
                    BigDecimal prix = (BigDecimal)object[3]; 
                    
      

               PrixMoyen prixMoyen = new PrixMoyen();
               
                  prixMoyen.setCode(code);
                  prixMoyen.setLibelle(libelle);
                  prixMoyen.setQte(qte);
                  prixMoyen.setPrix(prix);

                  listePrixStock.add(prixMoyen);
  
            }
        
            for (int i = 0; i < listePrixStock.size(); i++) {
                
                BigDecimal qteAct = BigDecimal.ZERO;
                BigDecimal prixMoyen = BigDecimal.ZERO;
                
                PrixMoyen pm = listePrixStock.get(i);
                
                for (int j = 0; j < listeDetailBonLivraison.size(); j++) {
                    
                         DetailBonLivraison detailBonLivraison = listeDetailBonLivraison.get(j);
                    
                    if (pm.getCode().equals(detailBonLivraison.getPrixOulmes().getProduit().getCode())) {
                        
                    if (detailBonLivraison.getQuantite().compareTo(BigDecimal.ZERO)>0){
                    prixMoyen = (qteAct.multiply(prixMoyen)).add(detailBonLivraison.getQuantite().multiply(detailBonLivraison.getPrix())).divide(qteAct.add(detailBonLivraison.getQuantite()), 9, RoundingMode.FLOOR).setScale(9, RoundingMode.FLOOR);
                    qteAct= qteAct.add(detailBonLivraison.getQuantite());
                    }
                }
                }
                
               pm.setPrix(prixMoyen);
               pm.setPrixTTC(prixMoyen.multiply(new BigDecimal(1.2)).setScale(9, RoundingMode.FLOOR));
               pm.setMontantHT(pm.getQte().multiply(prixMoyen).setScale(2, RoundingMode.FLOOR));    
               pm.setMontantTTC((pm.getQte().multiply(prixMoyen).multiply(new BigDecimal(1.2))).setScale(2, RoundingMode.FLOOR));
            listePrixStock.set(i, pm);
        }
        
         tableStock.setItems(listePrixStock);

         
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
                 try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationStockOulmesController.class.getResource(nav.getiReportConsultationStockOulmes()));


             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listePrixStock));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
        
                      codeRechField.setText("");

         loadDetail();
         setColumnProperties();
        
}

    @FXML
    private void codeRech(ActionEvent event) {
        
  
    }

    @FXML
    private void tableDetailClicked(MouseEvent event) {
    }

}
