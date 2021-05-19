/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Controller.Referentiel.ConsultationPrixCategorieController;
import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.DetailAvoirOulmes;
import dao.Entity.Fournisseur;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
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
public class SutiationGlobalAvoirController implements Initializable {

    @FXML
    private TableView<SituationGlobalAvoir> tableSituationAvoir;
    @FXML
    private TableColumn<SituationGlobalAvoir, String> codeColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, String> libelleColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> avoirColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> factureAvoirColumn;
    @FXML
    private TableColumn<SituationGlobalAvoir, BigDecimal> ecartColumn;

    @FXML
    private TableView<DetailAvoirOulmes> detailTableSituationAvoir;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> detailCodeColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> detailLibelleColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> detailAvoirColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> detailFactureAvoirColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> clientColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> detailEcartColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, Date> dateColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> bonAvrColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> nFactureColumn;

    @FXML
    private Label totalEcart;
    @FXML
    private Label totalFactureAv;
    @FXML
    private Label totalAvoir;
    @FXML
    private Label totalEcartDetail;
    @FXML
    private Label totalFactureAvDetail;
    @FXML
    private Label totalAvoirDetail;
    @FXML
    private Button imprimerBtn;
    @FXML
    private RadioButton listeAvoirRadio;
    @FXML
    private RadioButton detailAvoirRadio;
    @FXML
    private ToggleGroup avoir;
    @FXML
    private Button refrachBtn;
    @FXML
    private TextField codeArtField;
    @FXML
    private ComboBox<String> LibelleCombo;
    @FXML
    private TextField bonAvrField;
    @FXML
    private TextField numFactureField;
    @FXML
    private DatePicker dateAvr;
    @FXML
    private ComboBox<String> clientCombo;
    /**
     * Initializes the controller class.
     */
       
    DetailAvoirOulmesDAO detailAvoirOulmesDAO = new DetailAvoirOulmesDAOImpl();
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    
    navigation nav = new navigation(); 
    PrixOulmes prixOulmes =new PrixOulmes();
      
    private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    
     private final  ObservableList<SituationGlobalAvoir> listeSituationGlobalAvoir = FXCollections.observableArrayList();    
     private final  ObservableList<DetailAvoirOulmes> listeDetailAvoirOulmes = FXCollections.observableArrayList();    
    
    
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
                List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((clientMP) -> {
            clientCombo.getItems().addAll(clientMP.getNom());
            return clientMP;
        }).forEachOrdered((clientMP) -> {
            mapClientMP.put(clientMP.getNom(), clientMP);
        });

               List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findAll();

        listPrixOulmes.stream().map((prixOulmes) -> {
            LibelleCombo.getItems().addAll(prixOulmes.getProduit().getLibelle());
            return prixOulmes;
        }).forEachOrdered((prixOulmes) -> {
            mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
        });
          
        
            loadDetail();
            setColumnProperties();
            imprimerBtn.setDisable(true);
            
            
    }    
    
     void loadDetail(){
       
         BigDecimal montantAvoir = BigDecimal.ZERO;
      BigDecimal montantFactureAvoir = BigDecimal.ZERO;
      BigDecimal montantEcart = BigDecimal.ZERO;
        
        List <Object[]> listeObjectDetailAvoir =detailAvoirOulmesDAO.findBySitiationGlobalAvoir();

        System.out.println("listeObjectDetailAvoir     "+listeObjectDetailAvoir.size());
        
             listeSituationGlobalAvoir.clear();
         
            for(int i=0; i<listeObjectDetailAvoir.size(); i++) {

                    Object[] object=listeObjectDetailAvoir.get(i);
                   
                    Produit produit =(Produit)object[0];
                    BigDecimal avoir = (BigDecimal)object[1]; 
                    BigDecimal factureAvoir = (BigDecimal)object[2]; 
                    BigDecimal ecart = (BigDecimal)object[3]; 


               SituationGlobalAvoir situationGlobalAvoir = new SituationGlobalAvoir();
               
                  situationGlobalAvoir.setProduit(produit);
                  situationGlobalAvoir.setAvoirQte(avoir);
                  situationGlobalAvoir.setFactureAvoirQte(factureAvoir);
                  situationGlobalAvoir.setQteRestee(ecart);


                  montantAvoir = montantAvoir.add(situationGlobalAvoir.getAvoirQte()) ;
                  montantFactureAvoir = montantFactureAvoir.add(situationGlobalAvoir.getFactureAvoirQte()) ;
                  montantEcart = montantEcart.add(situationGlobalAvoir.getQteRestee()) ;
                    
                  listeSituationGlobalAvoir.add(situationGlobalAvoir);
            }
            
     
        
                      DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
            totalAvoir.setText(df.format(montantAvoir));
            totalFactureAv.setText(df.format(montantFactureAvoir));
            totalEcart.setText(df.format(montantEcart));
            
                  tableSituationAvoir.setItems(listeSituationGlobalAvoir);
            
       
   }
    
      void setColumnProperties() {

         codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, String> p) {
                    
        
                    return new ReadOnlyObjectWrapper(p.getValue().getProduit().getCode());
                }
                
             });
         
          libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, String> p) {
                    
             
                    return new ReadOnlyObjectWrapper(p.getValue().getProduit().getLibelle());
                }
                
             });
          
           avoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getAvoirQte()));
                }
                
             });
           
           
            factureAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getFactureAvoirQte()));
                }
                
             });
      
            ecartColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationGlobalAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQteRestee()));
                }
                
             });
            
         
              
               tableSituationAvoir.setEditable(false);
              
    }

     void setColumnPropertiesDetail(){
      
      
        detailCodeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    
        
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
                
             });
         
          detailLibelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    
             
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
                
             });
          
            clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    
        
                    return new ReadOnlyObjectWrapper(p.getValue().getAvoirOulmes().getClient());
                }
                
             });
          
            nFactureColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    
                     
                    return new ReadOnlyObjectWrapper(p.getValue().getAvoirOulmes().getNumFacture());
                }
                
             });
            
            bonAvrColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    
                     
                    return new ReadOnlyObjectWrapper(p.getValue().getAvoirOulmes().getNumLivraison());
                }
                
             });
            
             dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, Date> p) {
                    
                     
                    return new ReadOnlyObjectWrapper(p.getValue().getAvoirOulmes().getDateSaisie());
                }
                
             });
            
           detailAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
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
           
           
            detailFactureAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
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
      
            detailEcartColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQteEcart()));
                }
                
             });
      
      }
      
      
    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {
          
if (tableSituationAvoir.getSelectionModel().getSelectedIndex()!=-1){

      listeDetailAvoirOulmes.clear();
    
      BigDecimal montantAvoir = BigDecimal.ZERO;
      BigDecimal montantFactureAvoir = BigDecimal.ZERO;
      BigDecimal montantEcart = BigDecimal.ZERO;
    
         SituationGlobalAvoir situationGlobalAvoir=tableSituationAvoir.getSelectionModel().getSelectedItem();

        listeDetailAvoirOulmes.addAll(detailAvoirOulmesDAO.findByPrixOulmes(situationGlobalAvoir.getProduit().getLibelle()));

        detailTableSituationAvoir.setItems(listeDetailAvoirOulmes);
        
        setColumnPropertiesDetail();
        
        for (int i = 0; i < listeDetailAvoirOulmes.size(); i++) {
            
            DetailAvoirOulmes detailAvoirOulmes = listeDetailAvoirOulmes.get(i);
            
                  montantAvoir = montantAvoir.add(detailAvoirOulmes.getQte()) ;
                  montantFactureAvoir = montantFactureAvoir.add(detailAvoirOulmes.getQteFactureAvoir()) ;
                  montantEcart = montantEcart.add(detailAvoirOulmes.getQteEcart()) ;

    }
                DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
            totalAvoirDetail.setText(df.format(montantAvoir));
            totalFactureAvDetail.setText(df.format(montantFactureAvoir));
            totalEcartDetail.setText(df.format(montantEcart));
            
            
                dateAvr.setValue(null);
             clientCombo.getSelectionModel().select(-1);
             numFactureField.setText("");
             bonAvrField.setText("");
          
   
    listeAvoirRadio.setSelected(false);
    detailAvoirRadio.setSelected(false);
    
            imprimerBtn.setDisable(true);
            
}
        
    }


    @FXML
    private void imprimerBtnOnAction(ActionEvent event) {
        
         try {
               
               if(listeAvoirRadio.isSelected()== true){
                      
               
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SutiationGlobalAvoirController.class.getResource(nav.getiReportConsultationAvoirGlobal()));

                para.put("totalFactureAvoir",totalFactureAv.getText());
                para.put("totalAvoir",totalAvoir.getText());
                para.put("totalEcart",totalEcart.getText());
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeSituationGlobalAvoir));
               JasperViewer.viewReport(jp, false);
            
                  }else if(detailAvoirRadio.isSelected()== true)  {
                  
                       SituationGlobalAvoir situationGlobalAvoir = tableSituationAvoir.getSelectionModel().getSelectedItem();
                      
                      if (situationGlobalAvoir !=null){
                          
           HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SutiationGlobalAvoirController.class.getResource(nav.getiReportConsultationDetailAvoirGlobal()));
                          

                           para.put("totalDetailFactureAvoir",totalFactureAvDetail.getText());
                           para.put("totalDetailAvoir",totalAvoirDetail.getText());
                           para.put("totalDetailEcart",totalEcartDetail.getText());
                              
                           JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailAvoirOulmes));
               JasperViewer.viewReport(jp, false);
               
               
                      }else{
                            nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
                            return;
                      }
        
                  }
               
        } catch (JRException ex) {
            Logger.getLogger(SutiationGlobalAvoirController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    @FXML
    private void listeAvoirRadioOnAction(ActionEvent event) {
        
           imprimerBtn.setDisable(false);
        
    }

    @FXML
    private void detailAvoirRadioOnAction(ActionEvent event) {
        
           imprimerBtn.setDisable(false);
    }

    @FXML
    private void refrechBtnOnAction(ActionEvent event) {
        
    
             totalAvoirDetail.setText("");
             totalFactureAvDetail.setText("");
             totalEcartDetail.setText("");
             
             codeArtField.clear();
             LibelleCombo.getSelectionModel().select(-1);  
             
             
             dateAvr.setValue(null);
             clientCombo.getSelectionModel().select(-1);
             numFactureField.setText("");
             bonAvrField.setText("");
          

    listeAvoirRadio.setSelected(false);
    detailAvoirRadio.setSelected(false);
    
            imprimerBtn.setDisable(true);
    
    listeDetailAvoirOulmes.clear();
    
        loadDetail();
        setColumnProperties();
    

        
    }

    @FXML
    private void rechercheMouseClicked(MouseEvent event) {
        
        
        if(codeArtField.getText().isEmpty()){
            
                nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
        }else{
        
      BigDecimal montantAvoir = BigDecimal.ZERO;
      BigDecimal montantFactureAvoir = BigDecimal.ZERO;
      BigDecimal montantEcart = BigDecimal.ZERO;
        
        List <Object[]> listeObjectDetailAvoir =detailAvoirOulmesDAO.findBySitiationGlobalAvoirWithCodeArt(codeArtField.getText());

        System.out.println("listeObjectDetailAvoir     "+listeObjectDetailAvoir.size());
        
             listeSituationGlobalAvoir.clear();
         
            for(int i=0; i<listeObjectDetailAvoir.size(); i++) {

                    Object[] object=listeObjectDetailAvoir.get(i);
                   
                    Produit produit =(Produit)object[0];
                    BigDecimal avoir = (BigDecimal)object[1]; 
                    BigDecimal factureAvoir = (BigDecimal)object[2]; 
                    BigDecimal ecart = (BigDecimal)object[3]; 


               SituationGlobalAvoir situationGlobalAvoir = new SituationGlobalAvoir();
               
                  situationGlobalAvoir.setProduit(produit);
                  situationGlobalAvoir.setAvoirQte(avoir);
                  situationGlobalAvoir.setFactureAvoirQte(factureAvoir);
                  situationGlobalAvoir.setQteRestee(ecart);


                  montantAvoir = montantAvoir.add(situationGlobalAvoir.getAvoirQte()) ;
                  montantFactureAvoir = montantFactureAvoir.add(situationGlobalAvoir.getFactureAvoirQte()) ;
                  montantEcart = montantEcart.add(situationGlobalAvoir.getQteRestee()) ;
                    
                  listeSituationGlobalAvoir.add(situationGlobalAvoir);
            }
            
     
        
                      DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
            totalAvoir.setText(df.format(montantAvoir));
            totalFactureAv.setText(df.format(montantFactureAvoir));
            totalEcart.setText(df.format(montantEcart));
            
                  tableSituationAvoir.setItems(listeSituationGlobalAvoir);
        
                  setColumnProperties();
                  
             dateAvr.setValue(null);
             clientCombo.getSelectionModel().select(-1);
             numFactureField.setText("");
             bonAvrField.setText("");
          
             totalAvoirDetail.setText("");
             totalFactureAvDetail.setText("");
             totalEcartDetail.setText("");
             
    listeAvoirRadio.setSelected(false);
    detailAvoirRadio.setSelected(false);
    
            imprimerBtn.setDisable(true);
    
    listeDetailAvoirOulmes.clear();
                  
        }
        
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
    private void LibelleCombOnAction(ActionEvent event) {
        
        
                 PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());

        if(prixOulmes!=null){

         codeArtField.setText(prixOulmes.getProduit().getCode());

         }
        
    }

    @FXML
    private void rechercheDetailMouseClicked(MouseEvent event) {
        

        
           if (clientCombo.getSelectionModel().getSelectedIndex() == -1 &&
                   numFactureField.getText().equals("") &&
                   dateAvr.getValue()== null &&
                   bonAvrField.getText().equals("")
                   
                   ){
 
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
            } else{
           
               if (tableSituationAvoir.getSelectionModel().getSelectedIndex()!=-1){


            SituationGlobalAvoir situationGlobalAvoir=tableSituationAvoir.getSelectionModel().getSelectedItem();

             String req = "";
         
        if(clientCombo.getSelectionModel().getSelectedIndex()!= -1) {
             
            ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
            
              req= req+" and u.avoirOulmes.client='"+clientMP.getNom()+"'";
              
             }
             
        if(!bonAvrField.getText().equals("")){
             
            String bonAvoir = bonAvrField.getText();
            
             req= req+" and u.avoirOulmes.numLivraison='"+bonAvoir+"'";

            }
             
        if(!numFactureField.getText().equals("")){
             
            String numFact = numFactureField.getText();
            
               req= req+" and u.avoirOulmes.numFacture='"+numFact+"'";
      
             }

         if(dateAvr.getValue()!= null){
             
                         LocalDate localDate=dateAvr.getValue();
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String dateAvoir = localDate.format(formatters);

               req= req+" and u.avoirOulmes.dateSaisie='"+dateAvoir+"'";
      
             }
                 listeDetailAvoirOulmes.clear();
             
            listeDetailAvoirOulmes.addAll(detailAvoirOulmesDAO.findByPrixOulmesWithCodeArt(situationGlobalAvoir.getProduit().getCode(),req));

        detailTableSituationAvoir.setItems(listeDetailAvoirOulmes); 
        
        setColumnPropertiesDetail();
        
      BigDecimal montantAvoir = BigDecimal.ZERO;
      BigDecimal montantFactureAvoir = BigDecimal.ZERO;
      BigDecimal montantEcart = BigDecimal.ZERO;

        for (int i = 0; i < listeDetailAvoirOulmes.size(); i++) {
            
            DetailAvoirOulmes detailAvoirOulmes = listeDetailAvoirOulmes.get(i);
            
                  montantAvoir = montantAvoir.add(detailAvoirOulmes.getQte()) ;
                  montantFactureAvoir = montantFactureAvoir.add(detailAvoirOulmes.getQteFactureAvoir()) ;
                  montantEcart = montantEcart.add(detailAvoirOulmes.getQteEcart()) ;

    }
                DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
            totalAvoirDetail.setText(df.format(montantAvoir));
            totalFactureAvDetail.setText(df.format(montantFactureAvoir));
            totalEcartDetail.setText(df.format(montantEcart));

        
        
               }else{
               
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.SELECTIONNER_UNE_LIGNE);
                   return;
               }
        
           }
        
        
    }

    
}
