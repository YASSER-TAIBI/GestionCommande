/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.CategorieMp;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailCommande;
import dao.Entity.Magasin;
import dao.Entity.MatierePremier;
import dao.Entity.PrixMoyen;
import dao.Entity.SubCategorieMp;
import dao.Manager.CategorieMpDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.SubCategorieMPDAO;
import dao.ManagerImpl.CategorieMpDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.SubCategorieMPAOImpl;
import function.navigation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ConsultationStockController implements Initializable {

    
    
    
    
        @FXML
    private TableView<PrixMoyen> tableStock;
    @FXML
    private TableColumn<PrixMoyen, String> codeMPColumn;
    @FXML
    private TableColumn<PrixMoyen, String> libelleColumn;
    @FXML
    private TableColumn<PrixMoyen, BigDecimal> qteColumn;
    @FXML
    private TableColumn<PrixMoyen, BigDecimal> prixColumn;
    @FXML
    private TableColumn<PrixMoyen, BigDecimal> prixTTCColumn;
    @FXML
    private TableColumn<PrixMoyen, BigDecimal> montantHTColumn;
    @FXML
    private TableColumn<PrixMoyen, BigDecimal> montantTTCColumn;
    @FXML
    private TextField codeRechField;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    
   
    
    /**
     * Initializes the controller class.
     */
    

//       private final  ObservableList<DetailBonLivraison> listeDetailBonLivraison = FXCollections.observableArrayList();
        private final  ObservableList<PrixMoyen> listePrixStock = FXCollections.observableArrayList();    
    
      DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
        navigation nav = new navigation();   
    @FXML
    private Button btnExel;
    
       Workbook workbook = new HSSFWorkbook();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
                
            loadDetail();
      setColumnProperties();
        
    }    
    
        void setColumnProperties(){

             codeMPColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

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
     
    
    
    void loadDetail(){
        String req = "";
        
        List<DetailBonLivraison>listeDetailBonLivraison =detailBonLivraisonDAO.findByDetailBonLivraisonAndBl(req);
        
 List <Object[]> listePrixMoyen =detailBonLivraisonDAO.findByPrixMoyen(req);

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
                    prixMoyen.setMontantHT(qte.multiply(prix));

                  listePrixStock.add(prixMoyen);
  
            }
        
            for (int i = 0; i < listePrixStock.size(); i++) {
                
                BigDecimal qteAct = BigDecimal.ZERO;
                BigDecimal prixMoyen = BigDecimal.ZERO;
                
                PrixMoyen pm = listePrixStock.get(i);
                
                for (int j = 0; j < listeDetailBonLivraison.size(); j++) {
                    
                         DetailBonLivraison detailBonLivraison = listeDetailBonLivraison.get(j);
                    
                    if (pm.getCode().equals(detailBonLivraison.getMatierePremier().getCode())) {
                        
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
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationStockController.class.getResource(nav.getiReportConsultationStock()));


             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listePrixStock));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
         
        
        codeRechField.setText(Constantes.MATIERE_PREMIER);

         loadDetail();
         setColumnProperties();
    }




    @FXML
    private void codeMpRech(ActionEvent event) {
         

    }

    @FXML
    private void recherchMouseClicked(MouseEvent event) throws ParseException {
        
                
        String req = "";
        
        if (codeRechField.getText().equals(Constantes.MATIERE_PREMIER) ){
        
           nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
            
        }
        else if (!codeRechField.getText().equals(Constantes.MATIERE_PREMIER) ){
            
            listePrixStock.clear();
            
        req= "and c.matierePremier.code='"+codeRechField.getText()+"'";
        
        }
       
     
        List<DetailBonLivraison>listeDetailBonLivraison =detailBonLivraisonDAO.findByDetailBonLivraisonAndBl(req);
        
        List <Object[]> listePrixMoyen =detailBonLivraisonDAO.findByPrixMoyen(req);

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
                    
                    if (pm.getCode().equals(detailBonLivraison.getMatierePremier().getCode())) {
                    
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
    private void tableDetailClicked(MouseEvent event) {
        
 
    
}

    @FXML
    private void btnExelOnAction(ActionEvent event) throws IOException {
         exporter_Vers_Excel(event);
    }
    
    
   private void exporter_Vers_Excel(ActionEvent event) throws FileNotFoundException, IOException {

        int number = workbook.getNumberOfSheets();
        if (number == 1){
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet("simple");

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 13);
        font.setBold(true);
        font.setColor((short) HSSFColor.GREEN.index);

        Font font1 = workbook.createFont();
        font1.setFontHeightInPoints((short) 10);
        font1.setBold(false);
        font1.setColor((short) HSSFColor.BLACK.index);

        for (int j = 0; j < tableStock.getColumns().size(); j++) {
            row.createCell(j).setCellValue(tableStock.getColumns().get(j).getText());
   

        }

        for (int i = 0; i < tableStock.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tableStock.getColumns().size(); j++) {
                if (tableStock.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(tableStock.getColumns().get(j).getCellData(i).toString());
   

                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream("workbook.xls")) {

            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File("workbook.xls"));

        }

    }
}