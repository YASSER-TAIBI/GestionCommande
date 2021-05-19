/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.Depot;
import dao.Entity.DetailAvoirOulmes;
import dao.Entity.DetailPromotionAccordee;
import dao.Entity.PrixOulmes;
import dao.Entity.PromotionAccordee;
import dao.Entity.SituationGlobalAvoir;
import dao.Manager.DepotDAO;
import dao.Manager.DetailPromotionAccordeeDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.PromotionAccordeeDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailPromotionAccordeeDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.PromotionAccordeeDAOImpl;
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
public class ConsultationPromotionAccordeeController implements Initializable {

    @FXML
    private DatePicker dateDebutPromotion;
    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ComboBox<String> LibelleCombo;
    @FXML
    private DatePicker dateFinPromotion;
    @FXML
    private Button btnRefreshGlobal;
    @FXML
    private TextField codeArtField;
    @FXML
    private RadioButton listePromotionRadio;
    @FXML
    private RadioButton detailPromotionRadio;
    @FXML
    private Button imprimerBtn;
    @FXML
    private ToggleGroup promotion;
    
    @FXML
    private TableView<DetailPromotionAccordee> detailPromotionAccoTable;
    @FXML
    private TableColumn<DetailPromotionAccordee, String> codeArtColumn;
    @FXML
    private TableColumn<DetailPromotionAccordee, String> libelleColumn;
    @FXML
    private TableColumn<DetailPromotionAccordee, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailPromotionAccordee, BigDecimal> prixColumn;
    @FXML
    private TableColumn<DetailPromotionAccordee, BigDecimal> montantColumn;
    @FXML
    private TableView<PromotionAccordee> promotionAccoTable;
    @FXML
    private TableColumn<PromotionAccordee, String> codeClientColumn;
    @FXML
    private TableColumn<PromotionAccordee, String> depotColumn;
    @FXML
    private TableColumn<PromotionAccordee, String> clientColumn;
    @FXML
    private TableColumn<PromotionAccordee, String> nBonColumn;
    @FXML
    private TableColumn<PromotionAccordee, Date> datePromoColumn;

    /**
     * Initializes the controller class.
     */
    
     navigation nav = new navigation();
    PromotionAccordee promotionAccordee = new PromotionAccordee();
    PrixOulmes prixOulmes =new PrixOulmes();
    
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    PromotionAccordeeDAO promotionAccordeeDAO = new PromotionAccordeeDAOImpl();
    DetailPromotionAccordeeDAO detailPromotionAccordeeDAO = new DetailPromotionAccordeeDAOImpl();
    DepotDAO depotDAO = new DepotDAOImpl();
    
    
    private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
    private Map<String, Depot> mapDepot = new HashMap<>();
    
     private final ObservableList<PromotionAccordee> listPromotionAccordee=FXCollections.observableArrayList();
        
     private final ObservableList<DetailPromotionAccordee> listDetailPromotionAccordee=FXCollections.observableArrayList();
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         
        List<PrixOulmes> listPrixOulmes=prixOulmesDAO.findAll();
        
        listPrixOulmes.stream().map((prixOulmes) -> {
            LibelleCombo.getItems().addAll(prixOulmes.getProduit().getLibelle());
            return prixOulmes;
        }).forEachOrdered((prixOulmes) -> {
            mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
        });
        
        
        List<Depot> listDepot = depotDAO.findAll();

        listDepot.stream().map((depot) -> {
            depotCombo.getItems().addAll(depot.getLibelle());
            return depot;
        }).forEachOrdered((depot) -> {
            mapDepot.put(depot.getLibelle(), depot);
        });
        
        List<String> listClient = promotionAccordeeDAO.findByClient();
            clientCombo.getItems().addAll(listClient);

        loadDetail();
        setColumnProperties();
        
         imprimerBtn.setDisable(true);
    }    

       void loadDetail(){

        listPromotionAccordee.clear();   
        listPromotionAccordee.addAll(promotionAccordeeDAO.findAll());
        promotionAccoTable.setItems(listPromotionAccordee);
    }
    
    void setColumnProperties(){
    
     codeClientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PromotionAccordee, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<PromotionAccordee, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getCodeClient());
                }
             });
               

                clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PromotionAccordee, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<PromotionAccordee, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClient());
                }
             });

                nBonColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PromotionAccordee, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<PromotionAccordee, String> p) {
                    
                    return new ReadOnlyObjectWrapper(p.getValue().getNumBon());
                }
                
             });
                
                depotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PromotionAccordee, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<PromotionAccordee, String> p) {
                    
                    return new ReadOnlyObjectWrapper(p.getValue().getDepot().getLibelle());
                }
                
             });
        
                datePromoColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PromotionAccordee, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<PromotionAccordee, Date> p) {
                    
                    return new ReadOnlyObjectWrapper(p.getValue().getDatePromotion());
                }
                
             });
    }
    
    void setColumnPropertiesList(){

                codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailPromotionAccordee, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailPromotionAccordee, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });
               

                libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailPromotionAccordee, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailPromotionAccordee, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
             });

                quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailPromotionAccordee, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailPromotionAccordee, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQte()));
                }
                
             });
           

                prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailPromotionAccordee, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailPromotionAccordee, BigDecimal> p) {
                    
                
                    return new ReadOnlyObjectWrapper(p.getValue().getPrix().setScale(6,RoundingMode.FLOOR));
                }
                
             });

           
                montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailPromotionAccordee, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailPromotionAccordee, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
             

      }
    
    @FXML
    private void depotCombOnAction(ActionEvent event) {
    }

    @FXML
    private void clientCombOnAction(ActionEvent event) {
    }

    @FXML
    private void LibelleCombOnAction(ActionEvent event) {
        
    PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());

        if(prixOulmes!=null){

         codeArtField.setText(prixOulmes.getProduit().getCode());

         }
    }

    @FXML
    private void ajouterMouseClicked(MouseEvent event) {
        
        
           if (clientCombo.getSelectionModel().getSelectedIndex() == -1 &&
                  depotCombo.getSelectionModel().getSelectedIndex() == -1 &&
                   dateDebutPromotion.getValue()== null &&
                   dateFinPromotion.getValue()== null 

                   ){
 
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
            } else{
           
             String req = "";
         
        if(clientCombo.getSelectionModel().getSelectedIndex()!= -1) {
             
              req= req+" and c.client='"+clientCombo.getSelectionModel().getSelectedItem()+"'";
              
             }
             
        if(depotCombo.getSelectionModel().getSelectedIndex()!= -1){
             
           Depot depot = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
            
             req= req+" and c.depot.libelle='"+depot.getLibelle()+"'";

            }
             
      
          if(dateDebutPromotion.getValue()!= null && dateFinPromotion.getValue()!= null){
             
               LocalDate localDate=dateDebutPromotion.getValue();
                    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String dateDebut = localDate.format(formatters);

                    localDate=dateFinPromotion.getValue();
                    String dateFin = localDate.format(formatters);

            if(dateFinPromotion.getValue().compareTo(dateDebutPromotion.getValue())<0){
            
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
            return;
            }else{
            
             req= req+" and c.datePromotion BETWEEN '"+dateDebut+"' AND '"+dateFin+"'";

             }
        }else if (dateDebutPromotion.getValue()!= null && dateFinPromotion.getValue()== null){
        
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_DATE);
            return;
        
        }else if (dateDebutPromotion.getValue()== null && dateFinPromotion.getValue()!= null){
        
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_DATE);
            return;
        
        }

                 listPromotionAccordee.clear();
             
            listPromotionAccordee.addAll(promotionAccordeeDAO.findByAllPromotionAccordee(req));

        promotionAccoTable.setItems(listPromotionAccordee); 
        
        setColumnProperties();

        
          listDetailPromotionAccordee.clear();
           codeArtField.clear();
             LibelleCombo.getSelectionModel().select(-1);  
             
                 listePromotionRadio.setSelected(false);
    detailPromotionRadio.setSelected(false);
    
            imprimerBtn.setDisable(true);
           }
        
    }

    @FXML
    private void refreshGlobal(ActionEvent event) {
 
        
             
             codeArtField.clear();
             LibelleCombo.getSelectionModel().select(-1);  
             
             
             dateDebutPromotion.setValue(null);
             dateFinPromotion.setValue(null);
             clientCombo.getSelectionModel().select(-1);
             depotCombo.getSelectionModel().select(-1);
          
                listePromotionRadio.setSelected(false);
    detailPromotionRadio.setSelected(false);
    
            imprimerBtn.setDisable(true);
             
    listDetailPromotionAccordee.clear();
    
        loadDetail();
        setColumnProperties();

    }
    

    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {

           if (promotionAccoTable.getSelectionModel().getSelectedIndex()!=-1){
            
            listDetailPromotionAccordee.clear();
          
                    promotionAccordee=promotionAccoTable.getSelectionModel().getSelectedItem();   
            
            
       listDetailPromotionAccordee.addAll(detailPromotionAccordeeDAO.findByPromotionAccordee(promotionAccordee.getId()));

       detailPromotionAccoTable.setItems(listDetailPromotionAccordee);
       
       setColumnPropertiesList();
       
       codeArtField.clear();
       LibelleCombo.getSelectionModel().select(-1); 
       
       
        listePromotionRadio.setSelected(false);
    detailPromotionRadio.setSelected(false);
    
            imprimerBtn.setDisable(true);

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
    private void ajouterDetailMouseClicked(MouseEvent event) {
        
        
          if (codeArtField.getText().isEmpty() &&
                  LibelleCombo.getSelectionModel().getSelectedIndex() == -1 &&
                  promotionAccoTable.getSelectionModel().getSelectedIndex()== -1
                   ){
 
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
            } else{
   
            listDetailPromotionAccordee.clear();

              promotionAccordee=promotionAccoTable.getSelectionModel().getSelectedItem();   
             
            listDetailPromotionAccordee.addAll(detailPromotionAccordeeDAO.findByPromotionAccordee(promotionAccordee.getId(), codeArtField.getText()));

        detailPromotionAccoTable.setItems(listDetailPromotionAccordee); 
        
        setColumnPropertiesList();

           }
        
        
    }

    @FXML
    private void listePromotionRadioOnAction(ActionEvent event) {
        
                imprimerBtn.setDisable(false);
        
    }

    @FXML
    private void detailPromotionRadioOnAction(ActionEvent event) {
        
                imprimerBtn.setDisable(false);
        
    }

    @FXML
    private void imprimerBtnOnAction(ActionEvent event) {
        
          try {
               
               if(listePromotionRadio.isSelected()== true){
                      
               
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationPromotionAccordeeController.class.getResource(nav.getiReportConsultationPromotion()));

                            
                            if (clientCombo.getSelectionModel().getSelectedItem()!= null){
                           para.put("client",clientCombo.getSelectionModel().getSelectedItem());
                            }else{
                             para.put("client","Sans");
                            }
                            
                            if (depotCombo.getSelectionModel().getSelectedItem()!= null){
                          para.put("depot",depotCombo.getSelectionModel().getSelectedItem());
                            }else{
                             para.put("depot","Sans");
                            }
                            
                            if (dateDebutPromotion.getValue()!= null){
                                
                            LocalDate localDate=dateDebutPromotion.getValue();
                            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                            String dateDebut = localDate.format(formatters);

                           para.put("debut",dateDebut);
                           
                            }else{
                             para.put("debut","Sans");
                            }
                            
                            if (dateFinPromotion.getValue()!= null){
                                
                            LocalDate localDate=dateFinPromotion.getValue();
                            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                            String dateFin = localDate.format(formatters);
                                
                           para.put("fin",dateFin);
                            }else{
                             para.put("fin","Sans");
                            }
                            
           
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listPromotionAccordee));
               JasperViewer.viewReport(jp, false);
            
                  }else if(detailPromotionRadio.isSelected()== true)  {
                  
                       PromotionAccordee promotionAccordee = promotionAccoTable.getSelectionModel().getSelectedItem();
                      
                      if (promotionAccordee !=null){
                          
           HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationPromotionAccordeeController.class.getResource(nav.getiReportConsultationDetailPromotion()));
                          

                           para.put("codeClient",promotionAccordee.getCodeClient());
                           para.put("client",promotionAccordee.getClient());
                           para.put("nBon",promotionAccordee.getNumBon());
                           para.put("datePromotion",promotionAccordee.getDatePromotion());   
                           para.put("depot",promotionAccordee.getDepot().getLibelle());
                           
                           JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listDetailPromotionAccordee));
               JasperViewer.viewReport(jp, false);
               
               
                      }else{
                            nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
                            return;
                      }
        
                  }
               
        } catch (JRException ex) {
            Logger.getLogger(ConsultationPromotionAccordeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
    }
}