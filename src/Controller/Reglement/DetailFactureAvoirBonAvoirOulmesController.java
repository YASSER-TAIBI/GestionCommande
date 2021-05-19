/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Controller.Livraision.SituationGlobalCommandeController;
import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.DetailFactureAvoirBonAvoir;
import dao.Entity.Fournisseur;
import dao.Entity.PrixOulmes;
import dao.Entity.SituationGlobalAvoir;
import dao.Manager.AvoirOulmesDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.DetailAvoirOulmesDAO;
import dao.Manager.FactureAvoirOulmesDAO;
import dao.Manager.PrixOulmesDAO;
import dao.ManagerImpl.AvoirOulmesDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.DetailAvoirOulmesDAOImpl;
import dao.ManagerImpl.FactureAvoirOulmesDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import function.navigation;
import java.math.BigDecimal;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class DetailFactureAvoirBonAvoirOulmesController implements Initializable {

    @FXML
    private TableView<DetailFactureAvoirBonAvoir> detailFactureAvoirOulmestable;
    @FXML
    private TableColumn<DetailFactureAvoirBonAvoir, Date> dateSaisieColumn;
    @FXML
    private TableColumn<DetailFactureAvoirBonAvoir, String> designationColumn;
    @FXML
    private TableColumn<DetailFactureAvoirBonAvoir, BigDecimal> blAvoirColumn;
    @FXML
    private TableColumn<DetailFactureAvoirBonAvoir, String> nFactureColumn;
    @FXML
    private TableColumn<DetailFactureAvoirBonAvoir, BigDecimal> factureAvoirColumn;
    @FXML
    private TextField codeArtField;
    @FXML
    private ComboBox<String> LibelleCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private Label montantFacture;
    @FXML
    private Label montantAvoir;
    @FXML
    private Label montantEcart;
    @FXML
    private DatePicker dateDebutBonCommande;
    @FXML
    private DatePicker dateFinBonCommande;
    
       PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
       ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
       navigation nav = new navigation();
      PrixOulmes prixOulmes =new PrixOulmes();
       
       
        FactureAvoirOulmesDAO factureAvoirOulmesDAO = new FactureAvoirOulmesDAOImpl();
    DetailAvoirOulmesDAO detailAvoirOulmesDAO = new DetailAvoirOulmesDAOImpl();
       
    private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    /**
     * Initializes the controller class.
     */
    
      private final  ObservableList<DetailFactureAvoirBonAvoir> listeDetailFactureAvoirBonAvoir = FXCollections.observableArrayList();   
    @FXML
    private Button btnRefreshGlobal;
    @FXML
    private Button imprimerBtn;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
                    List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findAll();

        listPrixOulmes.stream().map((prixOulmes) -> {
            LibelleCombo.getItems().addAll(prixOulmes.getProduit().getLibelle());
            return prixOulmes;
        }).forEachOrdered((prixOulmes) -> {
            mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
        });
        
          List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((client) -> {
            clientCombo.getItems().addAll(client.getNom());
            return client;
        }).forEachOrdered((client) -> {
            mapClientMP.put(client.getNom(), client);
        });
        
      
        
    }    

     void setColumnProperties() {

         dateSaisieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, Date> p) {
                    
        
                    return new ReadOnlyObjectWrapper(p.getValue().getDateSaisie());
                }
                
             });
         
          designationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, String> p) {
                    
             
                    return new ReadOnlyObjectWrapper(p.getValue().getDesignation());
                }
                
             });
          
           nFactureColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, String> p) {
                    
             
                    return new ReadOnlyObjectWrapper(p.getValue().getNumFacture());
                }
                
             });
          
           blAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getAvoirQte()));
                }
                
             });
           
           
            factureAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getFactureAvoirQte()));
                }
                
             });
      
         
              
    }
    
    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
        
                           if (event.getCode().equals(KeyCode.ENTER) )
            {

                        prixOulmes = prixOulmesDAO.findPrixOulmesByCodeArt(codeArtField.getText());

                         LibelleCombo.setValue(prixOulmes.getProduit().getLibelle());
              }
    }

    @FXML
    private void LibelleComboOnAction(ActionEvent event) {
        
            PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
         if(prixOulmes!=null){
         codeArtField.setText(prixOulmes.getProduit().getCode());
         }
        
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }

    void calcule (){
    
          BigDecimal calculAvoir= BigDecimal.ZERO;
             BigDecimal calculFacture= BigDecimal.ZERO;
             BigDecimal somme=BigDecimal.ZERO;
             
           for( int rows = 0;rows<listeDetailFactureAvoirBonAvoir.size() ;rows++ ){

               DetailFactureAvoirBonAvoir detailFactureAvoirBonAvoir = listeDetailFactureAvoirBonAvoir.get(rows);
               
            calculAvoir = calculAvoir.add(detailFactureAvoirBonAvoir.getAvoirQte());  

            calculFacture = calculFacture.add(detailFactureAvoirBonAvoir.getFactureAvoirQte());  
            
    }
           
              DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
           
         montantAvoir.setText(df.format(calculAvoir));  

         montantFacture.setText(df.format(calculFacture));  

         somme = calculAvoir.subtract(calculFacture) ;
          
         montantEcart.setText(df.format(somme));  
        
    }
    
    
    @FXML
    private void rechercheMouseClicked(MouseEvent event) {
        
          if ( codeArtField.getText().equalsIgnoreCase("") || LibelleCombo.getSelectionModel().getSelectedItem() == null || LibelleCombo.getSelectionModel().getSelectedItem().isEmpty()|| clientCombo.getSelectionModel().getSelectedItem() == null || clientCombo.getSelectionModel().getSelectedItem().isEmpty() ) {
            
              nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                
            } else{ 
        
          ClientMP clientMP  = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
        
           String req = "";
          
            if(dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null){
             
               LocalDate localDate=dateDebutBonCommande.getValue();
                    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String dateOperaDebut = localDate.format(formatters);

                    localDate=dateFinBonCommande.getValue();
                    String dateOperaFin = localDate.format(formatters);

            if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0){
            
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
            return;
            }else{
            
             req= req+" and u.avoirOulmes.dateSaisie BETWEEN '"+dateOperaDebut+"' and '"+dateOperaFin+"'";

             }
        }else if (dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()== null){
        
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_DATE);
            return;
        
        }else if (dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()!= null){
        
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_DATE);
            return;
        
        }
          
          List <Object[]> listeObjectDetailAvoir =detailAvoirOulmesDAO.findByCodeAndClient(codeArtField.getText(), clientMP.getNom(),req);

             listeDetailFactureAvoirBonAvoir.clear();
         
            for(int i=0; i<listeObjectDetailAvoir.size(); i++) {

                    Object[] object=listeObjectDetailAvoir.get(i);
                   
                    
                    Date dateSaisie = (Date)object[0];
                    String Designation= (String)object[1];
                    String numFacture = (String)object[2];
                    BigDecimal avoir = (BigDecimal)object[3]; 
                    BigDecimal factureAvoir = (BigDecimal)object[4]; 

                  DetailFactureAvoirBonAvoir detailFactureAvoirBonAvoir = new DetailFactureAvoirBonAvoir();
               
                  detailFactureAvoirBonAvoir.setAvoirQte(avoir);
                  detailFactureAvoirBonAvoir.setFactureAvoirQte(factureAvoir);
                  detailFactureAvoirBonAvoir.setNumFacture(numFacture);
                  detailFactureAvoirBonAvoir.setDateSaisie(dateSaisie);
                  detailFactureAvoirBonAvoir.setDesignation(Designation);
                  
                  listeDetailFactureAvoirBonAvoir.add(detailFactureAvoirBonAvoir);
                  

    }
  
     detailFactureAvoirOulmestable.setItems(listeDetailFactureAvoirBonAvoir);
            setColumnProperties();
            
            calcule ();
}
    }

    @FXML
    private void dateDebit(ActionEvent event) {
    }

    @FXML
    private void dateFin(ActionEvent event) {
    }

    @FXML
    private void refreshGlobal(ActionEvent event) {
        
        codeArtField.clear();
        LibelleCombo.getSelectionModel().clearSelection();
        dateDebutBonCommande.setValue(null);
        dateFinBonCommande.setValue(null);
        clientCombo.getSelectionModel().clearSelection();
        listeDetailFactureAvoirBonAvoir.clear();
        montantAvoir.setText("");
        montantEcart.setText("");
        montantFacture.setText("");

    }

    @FXML
    private void imprimerOnAction(ActionEvent event) throws ParseException {
        
            try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(DetailFactureAvoirBonAvoirOulmesController.class.getResource(nav.getiReportConsultationBonAvoirFactureAvoir()));

            
               para.put("totalAvoir",montantAvoir.getText());
               para.put("totalFactureAvoir",montantFacture.getText());
               para.put("totalEcart",montantEcart.getText());
               para.put("codeArt",codeArtField.getText());
               para.put("libelle",LibelleCombo.getSelectionModel().getSelectedItem());
               para.put("client",clientCombo.getSelectionModel().getSelectedItem());
               
               if(dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null){ 

                Date dateDebut=new SimpleDateFormat("yyyy-MM-dd").parse(dateDebutBonCommande.getValue().toString());
                Date dateFin=new SimpleDateFormat("yyyy-MM-dd").parse(dateFinBonCommande.getValue().toString());
                   
               para.put("dateDebut","Date Debut: ");
               para.put("dateFin","Date Fin: ");
               para.put("dateDebutParam",dateDebut);
               para.put("dateFinParam",dateFin);
               
               }
               
               JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailFactureAvoirBonAvoir));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(DetailFactureAvoirBonAvoirOulmesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
