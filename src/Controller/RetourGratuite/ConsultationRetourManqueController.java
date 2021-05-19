/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.RetourGratuite;


import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.DetailBonRetour;
import dao.Entity.DetailCommande;
import dao.Entity.Fournisseur;
import dao.Entity.RetourManque;
import dao.Entity.MatierePremier;
import dao.Manager.ClientMPDAO;
import dao.Manager.DetailBonRetourDAO;
import dao.Manager.FournisseurDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.DetailBonRetourDAOImpl;
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
public class ConsultationRetourManqueController implements Initializable {

    @FXML
    private TableView<RetourManque> tableGlobalRetour;
    @FXML
    private TableColumn<RetourManque, String> codeArtColumn;
    @FXML
    private TableColumn<RetourManque, String> designationColumn;
    @FXML
    private TableColumn<RetourManque, BigDecimal> qteRetourColumn;
    @FXML
    private TableColumn<RetourManque, BigDecimal> qteManqueColumn;
    @FXML
    private TableColumn<RetourManque, BigDecimal> montantRetourColumn;
    @FXML
    private TableColumn<RetourManque, BigDecimal> montantManqueColumn;
    
    @FXML
    private TableView<DetailBonRetour> tableDetailRetour;
    @FXML
    private TableColumn<DetailBonRetour, String> codeMpDetailColumn;
    @FXML
    private TableColumn<DetailBonRetour, String> designationDetailColumn;
    @FXML
    private TableColumn<DetailBonRetour, String> retourManqueColumn;
    @FXML
    private TableColumn<DetailBonRetour, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailBonRetour, BigDecimal> prixColumn;
    @FXML
    private TableColumn<DetailBonRetour, String> fourColumn;
    @FXML
    private TableColumn<DetailBonRetour, String> clientColumn;
    @FXML
    private TableColumn<DetailBonRetour, String> etatColumn;
    
    @FXML
    private TextField codeMpRechField;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    @FXML
    private ComboBox<String> clientCombo;

    /**
     * Initializes the controller class.
     */
    
      private final ObservableList<RetourManque> listeRetourManqueMP=FXCollections.observableArrayList();  
      private final ObservableList<DetailBonRetour> listeDetailBonRetour=FXCollections.observableArrayList(); 
      
     DetailBonRetourDAO detailBonRetourDAO = new DetailBonRetourDAOImpl();
    
     navigation nav = new navigation();
     DetailBonRetour detailBonRetourArt = new DetailBonRetour();
     ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
     private Map<String,ClientMP> mapClient=new HashMap<>();
     
     
    void RetourManqueGlobal(){
    
       List <Object[]> listeObjectRetourManqueMp =detailBonRetourDAO.findBySituationRetourManque();

             listeRetourManqueMP.clear();
         
            for(int i=0; i<listeObjectRetourManqueMp.size(); i++) {

                    Object[] object=listeObjectRetourManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal qteRetour = (BigDecimal)object[1]; 
                    BigDecimal qteManque = (BigDecimal)object[2]; 
                    BigDecimal montantRetour = (BigDecimal)object[3]; 
                    BigDecimal montantManque = (BigDecimal)object[4];
                  
                    
                    if(matierePremier == null && qteRetour == null && qteManque == null && montantRetour == null && montantManque == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               RetourManque retourManqueMP = new RetourManque();
               
                  retourManqueMP.setMatierePremier(matierePremier);
                  retourManqueMP.setQteRetour(qteRetour);
                  retourManqueMP.setQteManque(qteManque);
                  retourManqueMP.setMontantRetour(montantRetour);
                  retourManqueMP.setMontantManque(montantManque);
                  
                  listeRetourManqueMP.add(retourManqueMP);
                    }
            }
            
            tableGlobalRetour.setItems(listeRetourManqueMP);
            setColumnProperties();
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
//====================================================================== Manque Global par MP ===========================================================================================================================================================================================================
        
       RetourManqueGlobal();
            
//============================================================================================================================================================================================================================================================================================           
    
    List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((client) -> {
            clientCombo.getItems().addAll(client.getNom());
            return client;
        }).forEachOrdered((client) -> {
            mapClient.put(client.getNom(), client);
        });


    }    

      void setColumnProperties(){
        
      codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RetourManque, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<RetourManque, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
   
      designationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RetourManque, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<RetourManque, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });
      
      qteRetourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RetourManque, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<RetourManque, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getQteRetour());
                }
             });
  
      qteManqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RetourManque, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<RetourManque, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getQteManque());
                }
             });
      
      montantRetourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RetourManque, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<RetourManque, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMontantRetour());
                }
             });
      
      montantManqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RetourManque, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<RetourManque, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMontantManque());
                }
             });
      
    }
    
     void setColumnPropertiesDetail(){
          
            codeMpDetailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
   
      designationDetailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });
      
      qteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonRetour, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getQuantiteRetour());
                }
             });
          
          retourManqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getBonRetour().getType());
                }
             });
                  
                  prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonRetour, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixUnitaire());
                }
             });
                  
          fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getBonRetour().getFournisseur());
                }
             });
                  
                  clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getBonRetour().getClient());
                }
             });
          
          
          etatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getBonRetour().getEtat());
                }
             });
          
          
      }
      

    @FXML
    private void recherchMouseClicked(MouseEvent event) throws ParseException {
        
           if ( codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
     }else{
           
                  List <Object[]> listeObjectRetourManqueMp =detailBonRetourDAO.findBySituationRetourManque();

             listeRetourManqueMP.clear();

             listeDetailBonRetour.clear();
             
            for(int i=0; i<listeObjectRetourManqueMp.size(); i++) {

                    Object[] object=listeObjectRetourManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal qteRetour = (BigDecimal)object[1]; 
                    BigDecimal qteManque = (BigDecimal)object[2]; 
                    BigDecimal montantRetour = (BigDecimal)object[3]; 
                    BigDecimal montantManque = (BigDecimal)object[4];
                  
                    
                    if(matierePremier == null && qteRetour == null && qteManque == null && montantRetour == null && montantManque == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

                        if (matierePremier.getCode().equals(codeMpRechField.getText())){
                        
               RetourManque retourManqueMP = new RetourManque();
               
                  retourManqueMP.setMatierePremier(matierePremier);
                  retourManqueMP.setQteRetour(qteRetour);
                  retourManqueMP.setQteManque(qteManque);
                  retourManqueMP.setMontantRetour(montantRetour);
                  retourManqueMP.setMontantManque(montantManque);
                  
                  listeRetourManqueMP.add(retourManqueMP);
                    }
            }
            }
            
            tableGlobalRetour.setItems(listeRetourManqueMP);
            setColumnProperties();
    
    
               
           
           }

       
        
    }


    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
  if (!tableDetailRetour.getItems().isEmpty()){
   
        try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationRetourManqueController.class.getResource(nav.getiReportConsultationDetailRetourManqueMp()));

            
       
               JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailBonRetour));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationRetourManqueController.class.getName()).log(Level.SEVERE, null, ex);
        }

  }else {
  
      nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.VERIFICATION_SELECTION_LIGNE);
  
  }
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
        

        codeMpRechField.setText(Constantes.MATIERE_PREMIER);
        
        clientCombo.getSelectionModel().select(-1);
        
        RetourManqueGlobal();
        
        listeDetailBonRetour.clear();
    }

    @FXML
    private void afficherDetailRetourManqueOnMouseClicked(MouseEvent event) {
        
        
        if (tableGlobalRetour.getSelectionModel().getSelectedIndex()!=-1){
 
                RetourManque retourManqueMP=tableGlobalRetour.getSelectionModel().getSelectedItem();    
                
        
        listeDetailBonRetour.clear();
        listeDetailBonRetour.addAll(detailBonRetourDAO.findByMatierePremier(retourManqueMP.getMatierePremier().getId()));
        tableDetailRetour.setItems(listeDetailBonRetour);
        
        setColumnPropertiesDetail();
            
        clientCombo.getSelectionModel().select(-1);
        
    }
    }


    @FXML
    private void imprimerGlobalMouseClicked(MouseEvent event) {
        
              try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationRetourManqueController.class.getResource(nav.getiReportConsultationRetourManqueMp()));

            
       
               JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeRetourManqueMP));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationRetourManqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void recherchClientMouseClicked(MouseEvent event) {
        
              if (tableGlobalRetour.getSelectionModel().getSelectedIndex()!=-1){
 
                RetourManque retourManqueMP=tableGlobalRetour.getSelectionModel().getSelectedItem();    

                ClientMP clientMP = mapClient.get(clientCombo.getSelectionModel().getSelectedItem());
        
        listeDetailBonRetour.clear();
        listeDetailBonRetour.addAll(detailBonRetourDAO.findByMatierePremierAndClient(retourManqueMP.getMatierePremier().getId(), clientMP.getNom()));
        tableDetailRetour.setItems(listeDetailBonRetour);
        
        setColumnPropertiesDetail();
            
        
        
    }
        
    }

    @FXML
    private void clientOnAction(ActionEvent event) {

    }
}
