/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Utils.Constantes;
import dao.Entity.AvoirOulmes;
import dao.Entity.Client;
import dao.Entity.ClientMP;
import dao.Entity.DetailAvoirOulmes;
import dao.Manager.AvoirOulmesDAO;
import dao.Manager.ClientDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.DetailAvoirOulmesDAO;
import dao.ManagerImpl.AvoirOulmesDAOImpl;
import dao.ManagerImpl.ClientDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.DetailAvoirOulmesDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class BonValidationAdministrationController implements Initializable {

    @FXML
    private TableView<AvoirOulmes> tableSituationAvoir;
    @FXML
    private TableColumn<AvoirOulmes, String> bonAvoirColumn;
    @FXML
    private TableColumn<AvoirOulmes, String> clientColumn;
    @FXML
    private TableColumn<AvoirOulmes, String> client2Column;
    @FXML
    private TableColumn<AvoirOulmes, Date> dateAvoirColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> avoirColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> factureAvoirColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> ecartColumn;
    @FXML
    private Button btnRefresh;
    @FXML
    private RadioButton listeAvoirRadio;
    @FXML
    private RadioButton detailAvoirRadio;
    @FXML
    private Button btnImprimer;
    @FXML
    private TableView<DetailAvoirOulmes> tableDetailSituationAvoir;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> CodeColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> LibelleColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> QteColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> PrixColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> montantColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> remiseColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> montantNetColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> qteFactAvoirColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> prixFactAvoirColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> remiseFactAvoirColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> motifColumn;
    @FXML
    private TextField nLivraisonRechField;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ComboBox<String> client2RechCombo;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private Label totalEcart;
    @FXML
    private Label totalFactureAv;
    @FXML
    private Label totalAvoir;
    @FXML
    private ToggleGroup Bon;

    /**
     * Initializes the controller class.
     */
    
      ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
      AvoirOulmesDAO avoirOulmesDAO = new AvoirOulmesDAOImpl();
      DetailAvoirOulmesDAO detailAvoirOulmesDAO = new DetailAvoirOulmesDAOImpl();
      ClientDAO clientDAO =new ClientDAOImpl();
      navigation nav = new navigation(); 
      
      private Map<String,Client> mapClient=new HashMap<>();
      
      private Map<String,ClientMP> mapClientMP=new HashMap<>();
      
      private final ObservableList<AvoirOulmes> listeAvoirOulmes=FXCollections.observableArrayList();
        
     private final ObservableList<DetailAvoirOulmes> listeDetailAvoirOulmes=FXCollections.observableArrayList();
    
//    ObservableList<String> client =FXCollections.observableArrayList(Constantes.CLIENT_MARJANE,Constantes.CLIENT_MINURSO);
    
     AvoirOulmes avoirOulmes;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//                   client2RechCombo.setItems(client);
        
        List<Client> listCliient=clientDAO.findAll();
        
        listCliient.stream().map((client) -> {
             client2RechCombo.getItems().addAll(client.getLibelle());
            return client;
        }).forEachOrdered((client) -> {
            mapClient.put(client.getLibelle(), client);
        });
                   
        
        
         List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((client) -> {
            clientCombo.getItems().addAll(client.getNom());
            return client;
        }).forEachOrdered((client) -> {
            mapClientMP.put(client.getNom(), client);
        });
        
        setColumnProperties();
        loadDetail();
        totalAvoirFactureEcart();
        
        btnImprimer.setDisable(true);

    }    

      void totalAvoirFactureEcart(){
    
    BigDecimal avoir = BigDecimal.ZERO;
    BigDecimal factureAvoir = BigDecimal.ZERO;
    BigDecimal ecart = BigDecimal.ZERO;
    
        for (int i = 0; i < listeAvoirOulmes.size(); i++) {
            
            AvoirOulmes avoirOulmes = listeAvoirOulmes.get(i);
         
            avoir = avoir.add(avoirOulmes.getAvoir());
            factureAvoir = factureAvoir.add(avoirOulmes.getFactureAvoir());
            ecart = ecart.add(avoirOulmes.getEcart());
        }
    
           DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
        
                totalAvoir.setText(df.format(avoir));
                totalFactureAv.setText(df.format(factureAvoir));
                totalEcart.setText(df.format(ecart));
    }
    
       void setColumnProperties(){


               bonAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumLivraison());
                }
             });

               
            clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClient());
                }
             });
                    
            client2Column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClient2());
                }
             });
            
             avoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getAvoir()));
                }
                
             });
           

             factureAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getFactureAvoir()));
                }
                
             });
           
             ecartColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getEcart()));
                }
                
             });
             
             
             dateAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<AvoirOulmes, Date> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDateSaisie());
                }
             });

}
    
       
        void setDetailColumnProperties(){


                 CodeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });
               
                    LibelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
             });
        
            
             QteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQte()));
                }
                
             });
           

             PrixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrix()));
                }
                
             });
           
             remiseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getRemiseAvoir());
                }
             });
           
             montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
             
                 montantNetColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    

                    return new ReadOnlyObjectWrapper(p.getValue().getMontantNet().setScale(2,RoundingMode.FLOOR));
                }
                
             });


                 qteFactAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQteFactureAvoir()));
                }
                
             });
                 
                prixFactAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrixFactureAvoir()));
                }
                
             });
                
                remiseFactAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getRemiseFactureAvoir()));
                }
                
             });
                
                motifColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    

                    return new ReadOnlyObjectWrapper(p.getValue().getMotif());
                }
                
             });
}
    
       void loadDetail(){

        listeAvoirOulmes.clear();   
        listeAvoirOulmes.addAll(avoirOulmesDAO.findAvoirOulmesByEtat(Constantes.ETAT_VALIDER_ADMIN_AVOIR));
        tableSituationAvoir.setItems(listeAvoirOulmes);
    }
    
    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        
              if (tableSituationAvoir.getSelectionModel().getSelectedIndex()!=-1){
            
            listeDetailAvoirOulmes.clear();
          
                    avoirOulmes=tableSituationAvoir.getSelectionModel().getSelectedItem();   
            
            
       listeDetailAvoirOulmes.addAll(detailAvoirOulmesDAO.findByAvoirOulmes(avoirOulmes.getId()));

       tableDetailSituationAvoir.setItems(listeDetailAvoirOulmes);
       
       setDetailColumnProperties();


    } 
        
    }

    @FXML
    private void refresh(ActionEvent event) {
        
        nLivraisonRechField.clear();
        clientCombo.getSelectionModel().clearSelection();
        client2RechCombo.getSelectionModel().clearSelection();
        dateDebut.setValue(null);
        dateFin.setValue(null);
        listeAvoirRadio.setSelected(false);
        detailAvoirRadio.setSelected(false);
        
        btnImprimer.setDisable(true);
        
        setColumnProperties();
        loadDetail(); 
        
        listeDetailAvoirOulmes.clear();
        
        totalAvoirFactureEcart();
    }

    @FXML
    private void listeAvoirRadioOnAction(ActionEvent event) {
        
        btnImprimer.setDisable(false);
        
    }

    @FXML
    private void detailAvoirRadioOnAction(ActionEvent event) {
        
         btnImprimer.setDisable(false);
        
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
        if (listeAvoirRadio.isSelected()==true){
        
                try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(BonValidationAdministrationController.class.getResource(nav.getiReportBonValidationAdministration()));


            para.put("totalAvoir",totalAvoir.getText());
            para.put("totalFactureAvoir",totalFactureAv.getText());
            para.put("totalEcart",totalEcart.getText());
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeAvoirOulmes));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(BonValidationAdministrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }else {
            
        
         if (tableSituationAvoir.getSelectionModel().getSelectedIndex()!=-1){
             
                avoirOulmes=tableSituationAvoir.getSelectionModel().getSelectedItem();   
             
                       try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(BonValidationAdministrationController.class.getResource(nav.getiReportBonDetailValidationAdministration()));

         
            para.put("bonAvoir",avoirOulmes.getNumLivraison());
            para.put("client",avoirOulmes.getClient());
            para.put("client2",avoirOulmes.getClient2());
            para.put("dateAvoir",avoirOulmes.getDateSaisie());
            para.put("avoir",avoirOulmes.getAvoir());
            para.put("factureAvoir",avoirOulmes.getFactureAvoir());
            para.put("ecart",avoirOulmes.getEcart());
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailAvoirOulmes));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(BonValidationAdministrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }else{
         
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.SELECTIONNER_UNE_LIGNE);
         }
        }
        
        
    }

    @FXML
    private void rechercheBonAvoirKeyPressed(KeyEvent event) {
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }

    @FXML
    private void rechercheMouseClicked(MouseEvent event) {
        
       String bonAvoir = nLivraisonRechField.getText();
       String client2 = client2RechCombo.getSelectionModel().getSelectedItem();
       ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());

       
           if (nLivraisonRechField.getText().equals("") &&
                   clientCombo.getSelectionModel().isEmpty() &&
                   client2RechCombo.getSelectionModel().isEmpty() &&
                   dateDebut.getValue()== null &&
                   dateFin.getValue()== null){
 
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
            } else{

                 String req = "";
         
        if(clientCombo.getSelectionModel().getSelectedIndex()!= -1) {
             
              req= req+" and u.client='"+clientMP.getNom()+"'";
              
             }
             
        if(!nLivraisonRechField.getText().equals("")){
             
             req= req+" and u.numLivraison='"+bonAvoir+"'";

            }
             
        if(client2RechCombo.getSelectionModel().getSelectedIndex()!= -1){
             
               req= req+" and u.client2='"+client2+"'";
      
             }
        
        if(dateDebut.getValue()!= null && dateFin.getValue()!= null){
             
               LocalDate localDate=dateDebut.getValue();
                    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String dateOperaDebut = localDate.format(formatters);

                    localDate=dateFin.getValue();
                    String dateOperaFin = localDate.format(formatters);

            if(dateFin.getValue().compareTo(dateDebut.getValue())<0){
            
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
            return;
            }else{
            
             req= req+" and u.dateSaisie BETWEEN '"+dateOperaDebut+"' AND '"+dateOperaFin+"'";

             }
        }else if (dateDebut.getValue()!= null && dateFin.getValue()== null){
        
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_DATE);
            return;
        
        }else if (dateDebut.getValue()== null && dateFin.getValue()!= null){
        
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_DATE);
            return;
        
        }
        
        
            listeAvoirOulmes.clear();
            listeDetailAvoirOulmes.clear();

               
          listeAvoirOulmes.addAll(avoirOulmesDAO.findByAllFilter(Constantes.ETAT_VALIDER_ADMIN_AVOIR,req));
          
          tableSituationAvoir.setItems(listeAvoirOulmes);
 
          totalAvoirFactureEcart();
          
    }

        
    }

    @FXML
    private void client2RechOnAction(ActionEvent event) {
    }

    @FXML
    private void dateDebutOnAction(ActionEvent event) {
    }

    @FXML
    private void dateFinOnAction(ActionEvent event) {
    }
    
}
