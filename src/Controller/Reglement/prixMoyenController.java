/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;


import static Controller.Reglement.EtatReglementController.setColumnTextFieldConverter;
import Utils.Constantes;
import dao.Entity.BonLivraison;
import dao.Entity.DetailBonLivraison;
import dao.Entity.PrixMoyen;
import dao.Entity.PrixOulmes;
import dao.Entity.SituationGlobalAvoir;
import dao.Manager.DetailBonLivraisonDAO;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
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
public class prixMoyenController implements Initializable {

    

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
    private Button btnRegler;
    @FXML
    private Button btnImprimer;

    /**
     * Initializes the controller class.
     */
    
      private final  ObservableList<DetailBonLivraison> listeDetailBonLivraison = FXCollections.observableArrayList();
        private final  ObservableList<PrixMoyen> listePrixStock = FXCollections.observableArrayList();    
    
      DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
      
       Workbook workbook = new HSSFWorkbook();
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          listeDetailBonLivraison.clear();
          
       String req ="";   
       listeDetailBonLivraison.addAll(detailBonLivraisonDAO.findByDetailBonLivraisonAndBl(req));
 
    }    

    

     void setColumnProperties(){

             codeMPColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

             libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
           
             qteColumn.setCellValueFactory(new PropertyValueFactory<>("qte"));
           
             prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));  
           
}
    
    

    @FXML
    private void tableDetailClicked(MouseEvent event) {
    }

    @FXML
    private void reglerOnAction(ActionEvent event) {
        
//         List <Object[]> listePrixMoyen =detailBonLivraisonDAO.findByPrixMoyen();
//
//
//         
//            for(int i=0; i<listePrixMoyen.size(); i++) {
//
//                    Object[] object=listePrixMoyen.get(i);
//                   
//                    String code =(String)object[0];
//                    String libelle = (String)object[1]; 
//                    BigDecimal qte = (BigDecimal)object[2]; 
//                    BigDecimal prix = (BigDecimal)object[3]; 
//                    
//      
//
//               PrixMoyen prixMoyen = new PrixMoyen();
//               
//                  prixMoyen.setCode(code);
//                  prixMoyen.setLibelle(libelle);
//                  prixMoyen.setQte(qte);
//                  prixMoyen.setPrix(prix);
//
//                  listePrixStock.add(prixMoyen);
//  
//            }
//        
//            for (int i = 0; i < listePrixStock.size(); i++) {
//                
//                BigDecimal qteAct = BigDecimal.ZERO;
//                BigDecimal prixMoyen = BigDecimal.ZERO;
//                
//                PrixMoyen pm = listePrixStock.get(i);
//                
//                for (int j = 0; j < listeDetailBonLivraison.size(); j++) {
//                    
//                         DetailBonLivraison detailBonLivraison = listeDetailBonLivraison.get(j);
//                    
//                    if (pm.getCode().equals(detailBonLivraison.getMatierePremier().getCode())) {
//                    
//                    prixMoyen = (qteAct.multiply(prixMoyen)).add(detailBonLivraison.getQuantite().multiply(detailBonLivraison.getPrix())).divide(qteAct.add(detailBonLivraison.getQuantite()), 8, RoundingMode.FLOOR);
//                    qteAct= qteAct.add(detailBonLivraison.getQuantite());
//
//                }
//                }
//                
//               pm.setPrix(prixMoyen);
//            listePrixStock.set(i, pm);
//        }
//        
//         tableStock.setItems(listePrixStock);
//         setColumnProperties();
    }

    @FXML
    private void imprimerEtatOnAction(ActionEvent event) throws IOException {
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
