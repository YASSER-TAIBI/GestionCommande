/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.CategorieMp;
import dao.Entity.Depot;
import dao.Entity.Dimension;
import dao.Entity.Magasin;
import dao.Entity.MatierePremier;
import dao.Entity.Sequenceur;
import dao.Manager.CategorieMpDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DimensionDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.CategorieMpDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DimensionDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import function.navigation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.hql.internal.antlr.HqlSqlTokenTypes;
/**
 * FXML Controller class
 *
 * @author Hp
 */
public class MatierePremierController implements Initializable {

    @FXML
    private TableView<MatierePremier> tableMP;
    @FXML
    private TableColumn<MatierePremier, String> codeColumn;
    @FXML
    private TableColumn<MatierePremier, String> nomColumn;
    @FXML
    private TableColumn<MatierePremier, String> dimColumn;
    @FXML
    private TableColumn<MatierePremier, String> CategorieColumn;
    @FXML
    private TableColumn<MatierePremier, Boolean> actionColumn;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField codeMpField;
    @FXML
    private TextField nomMpField;
    @FXML
    private ComboBox<String> dimCombo;
    @FXML
    private ComboBox<String> categorieCombo;
    @FXML
    private TextField rechCode;
    @FXML
    private TextField rechNom;
    @FXML
    private Button btnImprimer;
    @FXML
    private CheckBox checkDim;
    @FXML
    private Button btnExel;
    /**
     * Initializes the controller class.
     */
    
     private final ObservableList<MatierePremier> listeMatierePremier=FXCollections.observableArrayList();
      private final ObservableList<MatierePremier> listeMatierePremierTMP =FXCollections.observableArrayList();
      
     MatierePremiereDAO matierePremierDAO = new MatierePremierDAOImpl();
     navigation nav = new navigation();

     CategorieMpDAO  categorieMpDAO = new CategorieMpDAOImpl();
     DimensionDAO dimensionDAO = new DimensionDAOImpl();
     SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
     DepotDAO depotDAO = new DepotDAOImpl();
    
    
      private Map<String,CategorieMp> mapCategorieMp=new HashMap<>();
      private Map<String,Dimension> mapDimension=new HashMap<>();
    
    
      Workbook workbook = new HSSFWorkbook();
      
       void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        CategorieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MatierePremier, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<MatierePremier, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getCategorieMp().getNom());
                }
             });
        
         dimColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MatierePremier, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<MatierePremier, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDimension().getLibelle());
                }
             });
     
               actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          MatierePremier cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.isDeleted());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setDeleted(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionColumn.setEditable(true);
    
     }
    
     void loadDetail(){
        
        listeMatierePremier.clear();
        listeMatierePremier.addAll(matierePremierDAO.findAll());
        tableMP.setItems(listeMatierePremier);
    }
      
      void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.MATIERE_PREMIER);
          codeMpField.setText(Constantes.MATIERE_PREMIER+(sequenceur.getValeur()+1));
   }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Incrementation ();
        
           List<CategorieMp> listCategorieMp=categorieMpDAO.findAll();
        
            listCategorieMp.stream().map((categorieMp) -> {
            categorieCombo.getItems().addAll(categorieMp.getNom());
            return categorieMp;
        }).forEachOrdered((categorieMp) -> {
            mapCategorieMp.put(categorieMp.getNom(), categorieMp);
        });
                
              List<Dimension> listDimension=dimensionDAO.findAll();
        
            listDimension.stream().map((dimension) -> {
            dimCombo.getItems().addAll(dimension.getLibelle());
            return dimension;
        }).forEachOrdered((dimension) -> {
            mapDimension.put(dimension.getLibelle(), dimension);
        });
            
        setColumnProperties();
        loadDetail();
        
           tableMP.setEditable(true);
    }    

    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
           Integer val= tableMP.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              codeMpField.setText(codeColumn.getCellData(val));
              nomMpField.setText(nomColumn.getCellData(val));
              categorieCombo.setValue(CategorieColumn.getCellData(val));
              
//________________________________________________________________________________DIMENSION_______________________________________________________________________________________________________________

Dimension dimensionTMP = dimensionDAO.findDimensionByCode(Constantes.CODE_DIMENSION);

if (dimensionTMP.getLibelle().equals(dimColumn.getCellData(tableMP.getSelectionModel().getSelectedIndex()))){
    
 dimCombo.getItems().clear(); 
    CategorieMp categorieMp = categorieMpDAO.findCategorieMpByCat(CategorieColumn.getCellData(tableMP.getSelectionModel().getSelectedIndex()));
    
 List<Dimension> listDimension=dimensionDAO.findDimensionByCat(categorieMp.getCode());
        
            listDimension.stream().map((dimension) -> {
            dimCombo.getItems().addAll(dimension.getLibelle());
            return dimension;
        }).forEachOrdered((dimension) -> {
            mapDimension.put(dimension.getLibelle(), dimension);
        });
                
          }

else{
 dimCombo.getItems().clear();

CategorieMp categorieMp = categorieMpDAO.findCategorieMpByCat(CategorieColumn.getCellData(tableMP.getSelectionModel().getSelectedIndex()));

      List<Dimension> listDimension=dimensionDAO.findDimensionByCat(categorieMp.getCode());
        
            listDimension.stream().map((dimension) -> {
            dimCombo.getItems().addAll(dimension.getLibelle());
            return dimension;
        }).forEachOrdered((dimension) -> {
            mapDimension.put(dimension.getLibelle(), dimension);
        });
                
          }
     

//___________________________________________________________________________________________________________________________________________________________________________________________________________________
          
//              dimCombo.setValue(dimColumn.getCellData(val));

              btnAjouter.setDisable(true);
          }
    }
    public void clear(){

      nomMpField.setText(""); 
        categorieCombo.getSelectionModel().select(-1);  
        dimCombo.getSelectionModel().select(-1);  
        rechCode.setText(Constantes.MATIERE_PREMIER);
    rechNom.setText("");
       btnAjouter.setDisable(false);
    Incrementation ();
    setColumnProperties();
        loadDetail();
}

    @FXML
    private void btnModifierOnAction(ActionEvent event) {
                
         if (tableMP.getSelectionModel().getSelectedItem() != null) {
        
         MatierePremier matierePremier =tableMP.getSelectionModel().getSelectedItem();
   CategorieMp categorieMp = mapCategorieMp.get(categorieCombo.getSelectionModel().getSelectedItem());
   Dimension dimension = mapDimension.get(dimCombo.getSelectionModel().getSelectedItem());
   
        matierePremier.setDimension(dimension);     
        matierePremier.setCode(codeMpField.getText());   
        matierePremier.setCategorieMp(categorieMp);
        matierePremier.setNom(nomMpField.getText());
        matierePremier.setUtilisateurCreation(nav.getUtilisateur());
        
        matierePremierDAO.edit(matierePremier);
          
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);

         loadDetail();
      clear();
      
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
    }

    @FXML
    private void btnSupprimerOnAction(ActionEvent event) {
        
           if(tableMP.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
             
       MatierePremier matierePremier=tableMP.getSelectionModel().getSelectedItem();
       
         matierePremier.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);

            matierePremierDAO.edit(matierePremier);
       
        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        
        setColumnProperties();
        loadDetail();  
        clear();
    
    }
    }

    @FXML
    private void btnAjouterOnAction(ActionEvent event) {
        
           MatierePremier matierePremier = new MatierePremier();
  
           CategorieMp categorieMp = mapCategorieMp.get(categorieCombo.getSelectionModel().getSelectedItem());
           Dimension dimension = mapDimension.get(dimCombo.getSelectionModel().getSelectedItem());
           
           
        matierePremier.setCode(codeMpField.getText());   
        matierePremier.setCategorieMp(categorieMp);
        matierePremier.setDimension(dimension);
        matierePremier.setNom(nomMpField.getText());
        matierePremier.setEtat(Constantes.ETAT_COMMANDE_LANCE);
        matierePremier.setValMinMP(new BigDecimal(100).setScale(2));
        matierePremier.setValMaxMP(new BigDecimal(1000000000).setScale(2));
        matierePremier.setUtilisateurCreation(nav.getUtilisateur());
        matierePremierDAO.add(matierePremier);
        
 
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
         
         
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.MATIERE_PREMIER);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           
         
           
     setColumnProperties();
        loadDetail();
        clear();
        
    }

    
    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
          clear();
    }

   


    @FXML
    private void btnImprimerOnAction(ActionEvent event) {
        
        listeMatierePremierTMP.clear();
        
          for( int rows = 0;rows<tableMP.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
             
             MatierePremier matierePremier= tableMP.getItems().get(rows); 
             
             listeMatierePremierTMP.add(matierePremier); 
         }
          }
             try {
                 if (listeMatierePremierTMP.size()!= 0){
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(MatierePremierController.class.getResource(nav.getiReportMatierePremier()));

               JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeMatierePremierTMP));
               JasperViewer.viewReport(jp, false);
                 }
        } catch (JRException ex) {
            Logger.getLogger(MatierePremierController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
    }

    @FXML
    private void rechercheCodeVilleOnKeyPressed(KeyEvent event) {
        
         ObservableList<MatierePremier> listMatierePremierRech=FXCollections.observableArrayList();
                   
    listMatierePremierRech.clear();
   
   listMatierePremierRech.addAll(matierePremierDAO.listeMatierePremierByMp(rechCode.getText()));
   
   tableMP.setItems(listMatierePremierRech);
   
    }

    @FXML
    private void checkDimOnAction(ActionEvent event) {
        
        if (checkDim.isSelected()){
            
         dimCombo.getItems().clear();
         
                  List<Dimension> listDimension=dimensionDAO.findDimensionByCodeDIM(Constantes.CODE_DIMENSION);
        
            listDimension.stream().map((dimension) -> {
            dimCombo.getItems().addAll(dimension.getLibelle());
            return dimension;
        }).forEachOrdered((dimension) -> {
            mapDimension.put(dimension.getLibelle(), dimension);
        });
            
        }else{
        
//________________________________________________________________________________DIMENSION_______________________________________________________________________________________________________________
              
 dimCombo.getItems().clear();

     if (tableMP.getSelectionModel().getSelectedItem() != null) {
 
CategorieMp categorieMp = categorieMpDAO.findCategorieMpByCat(CategorieColumn.getCellData(tableMP.getSelectionModel().getSelectedIndex()));

      List<Dimension> listDimension=dimensionDAO.findDimensionByCat(categorieMp.getCode());
        
            listDimension.stream().map((dimension) -> {
            dimCombo.getItems().addAll(dimension.getLibelle());
            return dimension;
        }).forEachOrdered((dimension) -> {
            mapDimension.put(dimension.getLibelle(), dimension);
        });
     }
//___________________________________________________________________________________________________________________________________________________________________________________________________________________
        
        }
        
    }

    @FXML
    private void rechercheNonMpOnKeyReleased(KeyEvent event) {
        
               ObservableList<MatierePremier> listMatierePremierNom=FXCollections.observableArrayList();
                   
    listMatierePremierNom.clear();
   
   listMatierePremierNom.addAll(matierePremierDAO.listeMatierePremierByNomMp(rechNom.getText()));
   
   tableMP.setItems(listMatierePremierNom);
        
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

        for (int j = 0; j < tableMP.getColumns().size(); j++) {
            row.createCell(j).setCellValue(tableMP.getColumns().get(j).getText());
   

        }

        for (int i = 0; i < tableMP.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tableMP.getColumns().size(); j++) {
                if (tableMP.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(tableMP.getColumns().get(j).getCellData(i).toString());
   

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


    @FXML
    private void rechercheTableMouseClicked(MouseEvent event) {
        
            rechNom.clear();
            rechCode.clear();

            setColumnProperties();
            loadDetail();
        
    }
    
}
