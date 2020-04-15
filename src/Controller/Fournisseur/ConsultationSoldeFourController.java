/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Fournisseur;

import Controller.commande.EnvoyerCommandeController;
import Utils.Constantes;
import dao.Entity.DetailCompte;
import dao.Entity.Fournisseur;
import dao.Manager.DetailCompteDAO;
import dao.Manager.FournisseurDAO;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class ConsultationSoldeFourController implements Initializable {

    @FXML
    private TableView<DetailCompte> tableDetailCompte;
    @FXML
    private TableColumn<DetailCompte, BigDecimal> montantDebitColumn;
    @FXML
    private TableColumn<DetailCompte, BigDecimal> montantCreditColumn;
    @FXML
    private TableColumn<DetailCompte, Date> dateOperationColumn;
    @FXML
    private TableColumn<DetailCompte, String> designationColumn;
    @FXML
    private Button btnImprimer;
    @FXML
    private Label totalCredit;
    @FXML
    private Label totalDebit;
    @FXML
    private Label solde;
      @FXML
    private Button btnRefrech;
    
    @FXML
    private ComboBox<String> fourCombo;
    
     private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    /**
     * Initializes the controller class.
     */
        private final ObservableList<DetailCompte> listeDetailCompte=FXCollections.observableArrayList();
            FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
     DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    
     navigation nav = new navigation();
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
  
    
   
    
     void calcule (){
    
          BigDecimal calculCreditTotal= BigDecimal.ZERO;
             BigDecimal calculDebitTotal= BigDecimal.ZERO;
             BigDecimal somme=BigDecimal.ZERO;
             
           for( int rows = 0;rows<listeDetailCompte.size() ;rows++ ){

               DetailCompte detailCompte = listeDetailCompte.get(rows);
               
            calculCreditTotal = calculCreditTotal.add(detailCompte.getMontantCredit());  

    }
           
              DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
           
         totalCredit.setText(df.format(calculCreditTotal));  
        
     
           for( int rows = 0;rows<listeDetailCompte.size() ;rows++ ){
               
                  DetailCompte detailCompte = listeDetailCompte.get(rows);

            calculDebitTotal = calculDebitTotal.add(detailCompte.getMontantDebit());  
        
    }
           
                  
           
         totalDebit.setText(df.format(calculDebitTotal));  

         somme = calculCreditTotal.subtract(calculDebitTotal) ;
          
         solde.setText(df.format(somme));  
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
      setColumnProperties();
      loadDetail();  
        
           List<Fournisseur> listFournisseur=fournisseurDAO.findAll();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });

    calcule ();


    }    

      void setColumnProperties(){
        
    
         dateOperationColumn.setCellValueFactory(new PropertyValueFactory<>("dateOperation"));
        designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
//        montantDebitColumn.setCellValueFactory(new PropertyValueFactory<>("montantDebit"));
        
           montantDebitColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCompte, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCompte, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantDebit()));
                }
                
             });
      
        
//        montantCreditColumn.setCellValueFactory(new PropertyValueFactory<>("montantCredit"));
         
                 montantCreditColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCompte, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCompte, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantCredit()));
                }
                
             });
      
    }
    
    void loadDetail(){
        
        listeDetailCompte.clear();
        listeDetailCompte.addAll(detailCompteDAO.findAll());
        tableDetailCompte.setItems(listeDetailCompte);
    }
    

    @FXML
    private void imprimerOnAction(ActionEvent event) {
          try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationSoldeFourController.class.getResource(nav.getiReportConsultationSolde()));

               Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
            
                     if(fournisseur!=null){
                 para.put("Fournisseur",fournisseur.getNom());
            }else {
                   para.put("Fournisseur","TOUT FOURNISSEUR");
            }

            para.put("totalCredit",totalCredit.getText());
            para.put("totalDebit",totalDebit.getText());
            para.put("solde",solde.getText());

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(tableDetailCompte.getItems()));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(EnvoyerCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void fourOnAction(ActionEvent event) {
        
        if(fourCombo.getSelectionModel().getSelectedIndex()!= -1){    
            listeDetailCompte.clear();
           Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
           listeDetailCompte.addAll(detailCompteDAO.findDetailCompteByCompte(fournisseur.getCompteFourMP().getId()));
           
            calcule();
        
        } 
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
        

      loadDetail();
      calcule();
         
    fourCombo.getSelectionModel().select(-1);
        
    dateDebut.setValue(null);
    dateFin.setValue(null);
    }

    @FXML
    private void recherchMouseClicked(MouseEvent event) throws ParseException {
        if(
          dateDebut.getValue()== null ||
          dateFin.getValue()== null ||
                fourCombo.getSelectionModel().getSelectedIndex()== -1
          )
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
     }
        
        
        else if(dateDebut.getValue()!=null && dateFin.getValue()!=null)
		    		{

		    		if(!dateDebut.getValue().equals(dateFin.getValue()))
		    		{
		    			if(dateFin.getValue().compareTo(dateDebut.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

		    		}
        
        
        
        else if (dateDebut.getValue()==null){
        
            dateFin.setValue(null);
            
        }
        
        listeDetailCompte.clear();
           Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
        
           LocalDate localDate=dateDebut.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFin.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
         
       
        List<DetailCompte> detailCompte = detailCompteDAO.findFilterDetailCompteByDateOperationAndFour(dateOperaDebut, dateOperaFin, fournisseur.getId());
        
        
        listeDetailCompte.addAll(detailCompte);
        tableDetailCompte.setItems(listeDetailCompte);
         calcule();
        setColumnProperties();
    }

    @FXML
    private void dateDebutOnAction(ActionEvent event) {
        
    }

    @FXML
    private void dateFinOnAction(ActionEvent event) {
        
    }
}
