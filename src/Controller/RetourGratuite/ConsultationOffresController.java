/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.RetourGratuite;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.DetailBonRetour;
import dao.Entity.Fournisseur;
import dao.Entity.RetourManque;
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
import javafx.scene.control.Label;
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
public class ConsultationOffresController implements Initializable {

    @FXML
    private TableView<DetailBonRetour> tableOffre;
    @FXML
    private TableColumn<DetailBonRetour, String> codeColumn;
    @FXML
    private TableColumn<DetailBonRetour, String> designationColumn;
    @FXML
    private TableColumn<DetailBonRetour, String> nLivraisonColumn;
    @FXML
    private TableColumn<DetailBonRetour, Date> dateCreationColumn;
    @FXML
    private TableColumn<DetailBonRetour, String> fournisseurColumn;
    @FXML
    private TableColumn<DetailBonRetour, BigDecimal> prixColumn;
    @FXML
    private TableColumn<DetailBonRetour, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailBonRetour, BigDecimal> montantColumn;
     @FXML
    private TableColumn<DetailBonRetour, String> clientColumn;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField codeMpRechField;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    @FXML
    private Label totalTtcDebit;
    @FXML
    private Label qteTotal;
    
    /**
     * Initializes the controller class.
     */
    
      private final ObservableList<DetailBonRetour> listeDetailBonRetour=FXCollections.observableArrayList();  
     DetailBonRetourDAO detailBonRetourDAO = new DetailBonRetourDAOImpl();
    
     navigation nav = new navigation();
     DetailBonRetour detailBonRetour = new DetailBonRetour();

     private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
     FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
   
     private Map<String,ClientMP> mapClientMP=new HashMap<>();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        loadDetail();
        setColumnProperties();
        
           List<Fournisseur> listFournisseur=fournisseurDAO.findAllMp();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
          List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((client) -> {
            clientCombo.getItems().addAll(client.getNom());
            return client;
        }).forEachOrdered((client) -> {
            mapClientMP.put(client.getNom(), client);
        });
        
        calcule ();
    }    

      void calcule (){
    
          BigDecimal TotalTTC= BigDecimal.ZERO;
          BigDecimal somme=BigDecimal.ZERO;
          BigDecimal qteT= BigDecimal.ZERO;
             
           for( int rows = 0;rows<listeDetailBonRetour.size() ;rows++ ){

               DetailBonRetour detailBonRetour = listeDetailBonRetour.get(rows);
               
            TotalTTC = TotalTTC.add(detailBonRetour.getMontant());  
            qteT = qteT.add(detailBonRetour.getQuantiteRetour());

    }
           
           somme=TotalTTC.multiply(new BigDecimal(1.2));
           
              DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
           
         totalTtcDebit.setText(df.format(somme));  
         qteTotal.setText(df.format(qteT)); 

    }
    
         void loadDetail(){
        
        listeDetailBonRetour.clear();
        listeDetailBonRetour.addAll(detailBonRetourDAO.findDetailBonRetourByType(Constantes.ETAT_OFFRE));
        tableOffre.setItems(listeDetailBonRetour);
    }
    
     void setColumnProperties(){
        
      codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
   
      designationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });
      
      montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonRetour, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
      
          prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonRetour, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrixUnitaire()));
                }
                
             });
      
              qteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonRetour, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRetour()));
                }
                
             });
          
      dateCreationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<DetailBonRetour, Date> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getBonRetour().getDateCreation());
                }
             });
      
      nLivraisonColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getBonRetour().getNumLivraison());
                }
             });

      fournisseurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
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
      
    }
    
    
    
    @FXML
    private void afficherDetailAndStockProdOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void recherchMouseClicked(MouseEvent event) {
        
              try {
            
          if ( codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&&
                  clientCombo.getSelectionModel().getSelectedIndex()== -1 &&
                  fourCombo.getSelectionModel().getSelectedIndex()== -1){
              
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS_DATE);
         
     }else{
          
              
              Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
              ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
              String mp = codeMpRechField.getText();
              
         String req = "";
         
        if(fourCombo.getSelectionModel().getSelectedIndex()!= -1) {
             
              req= req+" and c.bonRetour.fournisseur='"+fournisseur.getNom()+"'";
              
             }
             
        if(clientCombo.getSelectionModel().getSelectedIndex()!= -1){
             
             req= req+" and c.bonRetour.client='"+clientMP.getNom()+"'";

             }
             
        if(!codeMpRechField.getText().equals(Constantes.MATIERE_PREMIER)){
             
               req= req+" and c.matierePremier.code='"+mp+"'";
      
             }

        
                     listeDetailBonRetour.clear();
        listeDetailBonRetour.addAll(detailBonRetourDAO.findDetailBonRetourByMpAndFourAndClient( Constantes.ETAT_OFFRE, req ));
        tableOffre.setItems(listeDetailBonRetour);
        setColumnProperties();      
        calcule ();
        
             }

             } catch (Exception e) {
                 
                    nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, e.toString());
        }
        
    }

    @FXML
    private void fourOnAction(ActionEvent event) {

    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
          try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationOffresController.class.getResource(nav.getiReportConsultationListeOffre()));

            
            para.put("montantTTC",totalTtcDebit.getText());
            para.put("qteTotal",qteTotal.getText());
       
               JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailBonRetour));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationOffresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
        
        fourCombo.getSelectionModel().select(-1);
        codeMpRechField.setText(Constantes.MATIERE_PREMIER);
        clientCombo.getSelectionModel().select(-1);
        tableOffre.getItems().clear();
        
        loadDetail();
        setColumnProperties();
        calcule ();
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }
    
}
 