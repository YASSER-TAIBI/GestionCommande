/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ClientMP;

import Controller.Reglement.BonValidationAdministrationController;
import Utils.Constantes;
import dao.Entity.CompteFourMP;
import dao.Entity.DetailCompte;
import dao.Entity.Fournisseur;
import dao.Entity.SoldeFinAnnee;
import dao.Manager.DetailCompteDAO;
import dao.Manager.FournisseurDAO;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
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
public class ConsultationSoldeFinAnneeController implements Initializable {

    @FXML
    private TableView<SoldeFinAnnee> tableSoldeFinAnnee;
    @FXML
    private TableColumn<SoldeFinAnnee, String> fourColumn;
    @FXML
    private TableColumn<SoldeFinAnnee, BigDecimal> ahlBrahimColumn;
    @FXML
    private TableColumn<SoldeFinAnnee, BigDecimal> nassPackingColumn;
    @FXML
    private TableColumn<SoldeFinAnnee, BigDecimal> saharaPackingColumn;
    @FXML
    private TableColumn<SoldeFinAnnee, BigDecimal> allaouiFilaliColumn;
    @FXML
    private TableColumn<SoldeFinAnnee, BigDecimal> greenTeaColumn;
    @FXML
    private TableColumn<SoldeFinAnnee, BigDecimal> montantColumn;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    @FXML
    private Button btnExel;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextField montantTotalField;
    
    /**
     * Initializes the controller class.
     */
    
      private final  ObservableList<SoldeFinAnnee> listeSoldeFinAnnee = FXCollections.observableArrayList();    
    
      DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
      
      FournisseurDAO fournisseurDAO =  new FournisseurDAOImpl();
        navigation nav = new navigation();  
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            // TODO
            loadDetail();
      setColumnProperties();
      calculeMontantTotal();  
      
    }    

      void setColumnProperties(){

             fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SoldeFinAnnee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SoldeFinAnnee, String> p) {
                    
  
                    return new ReadOnlyObjectWrapper(p.getValue().getFournisseur().getNom());
                }
                
             });

            ahlBrahimColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SoldeFinAnnee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SoldeFinAnnee, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getAhlBrahim()));
                }
                
             });
           
             nassPackingColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SoldeFinAnnee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SoldeFinAnnee, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getNassTeaPacking()));
                }
                
             });
             
             saharaPackingColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SoldeFinAnnee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SoldeFinAnnee, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getSaharaPacking()));
                }
                
             });
             
             allaouiFilaliColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SoldeFinAnnee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SoldeFinAnnee, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getElAllaouiFilali()));
                }
                
             });
             
             
             greenTeaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SoldeFinAnnee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SoldeFinAnnee, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getGreenTea()));
                }
                
             });
             
             montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SoldeFinAnnee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SoldeFinAnnee, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
 
}
    
      
      
      void calculeMontantTotal(){
      
      BigDecimal montantTotal = BigDecimal.ZERO;
          for (int i = 0; i < listeSoldeFinAnnee.size(); i++) {
              
              SoldeFinAnnee soldeFinAnnee = listeSoldeFinAnnee.get(i);
              
              montantTotal= montantTotal.add(soldeFinAnnee.getMontant());
              
          }
          
           DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
            montantTotalField.setText(df.format(montantTotal));
          
          
          
      }
    
       void loadDetail() {

           
           Date dateMin = detailCompteDAO.findByMinDate();
           
           SimpleDateFormat dminFormat = new SimpleDateFormat("yyyy/MM/dd");
           String dmin = dminFormat.format(dateMin);
           
           Date myDate = new Date();
       SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy/MM/dd");
           String dmy = dmyFormat.format(myDate);
           
         String req= " and c.dateBonLivraison between '"+dmin+"' and '"+dmy+"'";
        
        
 List <Object[]> listeDetailCompte =detailCompteDAO.findBySoldeFA(req);

listeSoldeFinAnnee.clear();
         
            for(int i=0; i<listeDetailCompte.size(); i++) {

                    Object[] object=listeDetailCompte.get(i);
                   
                    CompteFourMP compte =(CompteFourMP)object[0];
                    BigDecimal ahlBrahim = (BigDecimal)object[1]; 
                    BigDecimal nassTeaPacking = (BigDecimal)object[2]; 
                    BigDecimal saharaPacking = (BigDecimal)object[3]; 
                    BigDecimal elAllaouiFilali = (BigDecimal)object[4]; 
                    BigDecimal greenTea = (BigDecimal)object[5]; 
                            
                SoldeFinAnnee soldeFinAnnee = new SoldeFinAnnee();
                
                Fournisseur fournisseur = fournisseurDAO.findByCompte(compte.getId());
               
                    soldeFinAnnee.setFournisseur(fournisseur);
                    soldeFinAnnee.setAhlBrahim(ahlBrahim);
                    soldeFinAnnee.setNassTeaPacking(nassTeaPacking);
                    soldeFinAnnee.setSaharaPacking(saharaPacking);
                    soldeFinAnnee.setElAllaouiFilali(elAllaouiFilali);
                    soldeFinAnnee.setGreenTea(greenTea);
                    
                  listeSoldeFinAnnee.add(soldeFinAnnee);
  
            }
         tableSoldeFinAnnee.setItems(listeSoldeFinAnnee);
   

    }
    
    
    @FXML
    private void recherchMouseClicked(MouseEvent event) throws ParseException {
        
        String req = "";
        
        if(dateFin.getValue()!= null){
             
            Date dateMin = detailCompteDAO.findByMinDate();
            SimpleDateFormat dminFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dmin = dminFormat.format(dateMin);
            

            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate=dateFin.getValue();
//            Date dateFin=new SimpleDateFormat("yyyy/MM/dd").parse(localDate.toString());
            String dateOperaFin = localDate.format(formatters);

//            if(dateFin.compareTo(dateMin)<0){
//            
//            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_DATE);
//            return;
//            }else{
            
             req=" and c.dateBonLivraison BETWEEN '"+dmin+"' AND '"+dateOperaFin+"'";

//             }
        }else if (dateFin.getValue()== null){
        
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_DATE);
            return;
        
        }
           List <Object[]> listeDetailCompte =detailCompteDAO.findBySoldeFA(req);

listeSoldeFinAnnee.clear();
         
            for(int i=0; i<listeDetailCompte.size(); i++) {

                    Object[] object=listeDetailCompte.get(i);
                   
                    CompteFourMP compte =(CompteFourMP)object[0];
                    BigDecimal ahlBrahim = (BigDecimal)object[1]; 
                    BigDecimal nassTeaPacking = (BigDecimal)object[2]; 
                    BigDecimal saharaPacking = (BigDecimal)object[3]; 
                    BigDecimal elAllaouiFilali = (BigDecimal)object[4]; 
                    BigDecimal greenTea = (BigDecimal)object[5]; 
                            
                SoldeFinAnnee soldeFinAnnee = new SoldeFinAnnee();
                
                Fournisseur fournisseur = fournisseurDAO.findByCompte(compte.getId());
               
                    soldeFinAnnee.setFournisseur(fournisseur);
                    soldeFinAnnee.setAhlBrahim(ahlBrahim);
                    soldeFinAnnee.setNassTeaPacking(nassTeaPacking);
                    soldeFinAnnee.setSaharaPacking(saharaPacking);
                    soldeFinAnnee.setElAllaouiFilali(elAllaouiFilali);
                    soldeFinAnnee.setGreenTea(greenTea);
                    

                  listeSoldeFinAnnee.add(soldeFinAnnee);
  
            }
            
         tableSoldeFinAnnee.setItems(listeSoldeFinAnnee);
        calculeMontantTotal();
        
    }

    @FXML
    private void tableDetailClicked(MouseEvent event) {
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
                      try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationSoldeFinAnneeController.class.getResource(nav.getiReportConsultationSoldeFinAnnee()));

             DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate=dateFin.getValue();
//            Date dateFin=new SimpleDateFormat("yyyy/MM/dd").parse(localDate.toString());
            String dateOperaFin = localDate.format(formatters);
            
            if(dateFin.getValue()!=null){
                
            para.put("dateSituationText","Date Situation: ");    
            para.put("dateSituation",dateOperaFin);
            }
            para.put("montantTotal",montantTotalField.getText());
            
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeSoldeFinAnnee));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationSoldeFinAnneeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void refrechOnAction(ActionEvent event){

         loadDetail();
         setColumnProperties();
        calculeMontantTotal();
         
        dateFin.setValue(null);
         
    }

    @FXML
    private void btnExelOnAction(ActionEvent event) {
    }


    @FXML
    private void dateFinOnAction(ActionEvent event) {
    }
    
}
