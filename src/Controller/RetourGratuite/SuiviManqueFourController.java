/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.RetourGratuite;

import Utils.Constantes;
import dao.Entity.Commande;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailCommande;
import dao.Entity.DetailManqueFour;
import dao.Entity.SousDetailManqueFour;
import dao.Manager.CommandeDAO;
import dao.Manager.DetailManqueFourDAO;
import dao.Manager.SousDetailManqueFourDAO;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DetailManqueFourDAOImpl;
import dao.ManagerImpl.SousDetailManqueFourDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
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
public class SuiviManqueFourController implements Initializable {

    @FXML
    private TableView<DetailManqueFour> tableDetailManqueFour;
    @FXML
    private TableColumn<DetailManqueFour, String> codeMPColumn;
    @FXML
    private TableColumn<DetailManqueFour, String> libelleColumn;
    @FXML
    private TableColumn<DetailManqueFour, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailManqueFour, BigDecimal> quantiteRecuColumn;
    @FXML
    private TableColumn<DetailManqueFour, BigDecimal> ecartQuantiteColumn;
    @FXML
    private TableColumn<DetailManqueFour, String> nComColumn;
    @FXML
    private TableColumn<DetailManqueFour, String> nBlColumn;
    @FXML
    private TableColumn<DetailManqueFour, String> fourColumn;
    @FXML
    private TableColumn<DetailManqueFour, String> depotColumn;
    @FXML
    private TableColumn<DetailManqueFour, String> magasinColumn;
    
    @FXML
    private TableView<SousDetailManqueFour> tableSousDetailManqueFour;
    @FXML
    private TableColumn<SousDetailManqueFour, String> codeMPHisColumn;
    @FXML
    private TableColumn<SousDetailManqueFour, String> libelleHisColumn;
    @FXML
    private TableColumn<SousDetailManqueFour, String> numManque;
    @FXML
    private TableColumn<SousDetailManqueFour, Date> dateSaisieColumn;
    @FXML
    private TableColumn<SousDetailManqueFour, BigDecimal> quantiteHisColumn;
    
    @FXML
    private TextField qteTotalField;
    @FXML
    private TextField qteRecuField;
    @FXML
    private DatePicker dateSaisie;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnImprimer;
    
    /**
     * Initializes the controller class.
     */
    
    private ObservableList<DetailManqueFour> listeDetailManqueFour=FXCollections.observableArrayList();
    private ObservableList<SousDetailManqueFour> listeSousDetailManqueFour=FXCollections.observableArrayList();
    navigation nav = new navigation();
      
    CommandeDAO commandeDAO = new  CommandeDAOImpl();
    DetailManqueFourDAO detailManqueFourDAO = new DetailManqueFourDAOImpl();
    SousDetailManqueFourDAO sousDetailManqueFourDAO = new SousDetailManqueFourDAOImpl();
        
    private Map<String,DetailManqueFour> mapDetailManqueFour=new HashMap<>();
    
    
    

    void qteTotal() {
          
               BigDecimal ecartQte = BigDecimal.ZERO;
               BigDecimal totalEcartQte = BigDecimal.ZERO;
              
              for (int i = 0; i < listeDetailManqueFour.size(); i++) {
                  
                  DetailManqueFour detailManqueFour = listeDetailManqueFour.get(i);
                  
                  ecartQte = detailManqueFour.getEcartQuantite();
                  
                  totalEcartQte= totalEcartQte.add(ecartQte);

    }
          
              qteTotalField.setText(totalEcartQte+"");
              
          }
          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDetail();
        setColumnPropertiesDetailCommande();
        qteTotal();
    }    
    
    
     void loadDetail(){

         listeDetailManqueFour.clear();
         listeDetailManqueFour.addAll(detailManqueFourDAO.findByStatutMP());
         tableDetailManqueFour.setItems(listeDetailManqueFour);
         
    }
    
      void setColumnPropertiesDetailCommande() {

        codeMPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailManqueFour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailManqueFour, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
            }
        });

        libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailManqueFour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailManqueFour, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
            }

        });

           quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailManqueFour, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailManqueFour, BigDecimal> p) {
                    
                     
                    return new ReadOnlyObjectWrapper(p.getValue().getQuantite());
                }
                
             });

      nComColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailManqueFour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailManqueFour, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNumCommande());
            }

        });
      
      
       fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailManqueFour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailManqueFour, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFourisseur().getNom());
            }

        });
       
       magasinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailManqueFour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailManqueFour, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMagasinn().getLibelle());
            }

        });
       
       depotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailManqueFour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailManqueFour, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDepot());
            }

        });
       
      nBlColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailManqueFour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailManqueFour, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNumBonLiv());
            }

        });
      
        quantiteRecuColumn.setCellValueFactory(new PropertyValueFactory<DetailManqueFour, BigDecimal>("quantiteRecu"));

        setColumnTextFieldConverter(getConverter(), quantiteRecuColumn);

        
              ecartQuantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailManqueFour, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailManqueFour, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getEcartQuantite()));
                }
                
             });
       
    }
       
      void setColumnPropertiesSousDetailManqueFour() {

        codeMPHisColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SousDetailManqueFour, String>, ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SousDetailManqueFour, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
            }
        });

        libelleHisColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SousDetailManqueFour, String>, ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SousDetailManqueFour, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
            }

        });

           quantiteHisColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SousDetailManqueFour, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SousDetailManqueFour, BigDecimal> p) {
                    
                     
                    return new ReadOnlyObjectWrapper(p.getValue().getQuantite());
                }
                
             });

      numManque.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SousDetailManqueFour, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SousDetailManqueFour, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNumRetour());
            }

        });
      
      dateSaisieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SousDetailManqueFour, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<SousDetailManqueFour, Date> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDateSaisie());
            }

        });
    } 
      
      
        public static void setColumnTextFieldConverter(StringConverter converter, TableColumn... columns) {

        for (TableColumn tableColumn : columns) {

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        }
    }

    private StringConverter getConverter() {
        StringConverter<BigDecimal> converter = new StringConverter<BigDecimal>(){
            @Override
            public BigDecimal fromString(String string) {

                try {
                    BigDecimal valeur;
                    valeur= new BigDecimal(string);
                    
                    return valeur;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString(BigDecimal object) {

                if (object == null) {
                    return null;
                }
                return  String.valueOf(object);
            }
        };

        return converter;
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        

        tableDetailManqueFour.getItems().clear();
        qteTotalField.clear();
       
           loadDetail();
        setColumnPropertiesDetailCommande();
        qteTotal();
    }

    private void editCommitQuantiteRetourColumn(TableColumn.CellEditEvent<DetailManqueFour, BigDecimal> event) {
        
         
    }


    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        
         
            if (tableDetailManqueFour.getSelectionModel().getSelectedIndex()!=-1){
 
               DetailManqueFour detailManqueFour=tableDetailManqueFour.getSelectionModel().getSelectedItem();    
                
//        qteRecuField.setText(detailManqueFour.getQuantiteRecu()+"");
//        
//        LocalDate date = new java.util.Date(detailManqueFour.getDateSaisie().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//        dateSaisie.setValue(date);
        
        listeSousDetailManqueFour.clear();
          listeSousDetailManqueFour.addAll(sousDetailManqueFourDAO.findByNumManque(detailManqueFour.getNumRetour()));
         tableSousDetailManqueFour.setItems(listeSousDetailManqueFour);
        
         setColumnPropertiesSousDetailManqueFour();
            
            }
            
        
    }

    @FXML
    private void btnValiderOnAction(ActionEvent event) throws ParseException {
        
        if (qteRecuField.getText().equalsIgnoreCase("") || dateSaisie.getValue()== null || tableDetailManqueFour.getSelectionModel().getSelectedIndex()==-1 ){
            
         nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
      
        }else{
            
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
                 LocalDate localDate=dateSaisie.getValue();
            
                Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
                
               DetailManqueFour detailManqueFour=tableDetailManqueFour.getSelectionModel().getSelectedItem();    
                
               BigDecimal  calculeEcartQuantite= BigDecimal.ZERO; 
               BigDecimal  qteRecuOld= BigDecimal.ZERO; 
               BigDecimal  qteRecuNew= BigDecimal.ZERO; 
               BigDecimal  qteRecu= BigDecimal.ZERO; 
               BigDecimal  qte  = BigDecimal.ZERO;       

            
              qteRecuOld =  detailManqueFour.getQuantiteRecu();
               
              qteRecuNew = new BigDecimal(qteRecuField.getText());

              qte = detailManqueFour.getQuantite();
           
              qteRecu = qteRecuOld.add(qteRecuNew);
               
              calculeEcartQuantite = qte.subtract(qteRecu).setScale(2, RoundingMode.FLOOR);
              
              detailManqueFour.setQuantiteRecu(qteRecu);
              detailManqueFour.setEcartQuantite(calculeEcartQuantite);
              
              detailManqueFourDAO.edit(detailManqueFour);
              
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

                SousDetailManqueFour sousDetailManqueFour = new SousDetailManqueFour();

                sousDetailManqueFour.setQuantite(qteRecuNew);
                sousDetailManqueFour.setStatut(Constantes.MP);
                sousDetailManqueFour.setNumRetour(detailManqueFour.getNumRetour());
                sousDetailManqueFour.setDateSaisie(dateSaisie);
                sousDetailManqueFour.setMatierePremier(detailManqueFour.getMatierePremier());
                sousDetailManqueFour.setUtilisateurCreation(nav.getUtilisateur());
                
                sousDetailManqueFourDAO.add(sousDetailManqueFour);
                
                
              setColumnPropertiesDetailCommande();
              qteTotal();

              
              
               listeSousDetailManqueFour.clear();
               listeSousDetailManqueFour.addAll(sousDetailManqueFourDAO.findByNumManque(detailManqueFour.getNumRetour()));
               tableSousDetailManqueFour.setItems(listeSousDetailManqueFour);
              
               setColumnPropertiesSousDetailManqueFour();
               
               qteRecuField.clear();
               this.dateSaisie.setValue(null);
               
               } else if (result.get() == ButtonType.CANCEL) {
                tableDetailManqueFour.refresh();
            }
        }
        
        
        
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        
        
        qteRecuField.clear();
        dateSaisie.setValue(null);
        
        listeSousDetailManqueFour.clear();
        
        loadDetail();
        setColumnPropertiesDetailCommande();
        qteTotal();
        
        
    }

    @FXML
    private void btnImprimerOnAction(ActionEvent event) throws JRException {
        
         try {
             
               DetailManqueFour detailManqueFour=tableDetailManqueFour.getSelectionModel().getSelectedItem();   
               
               if (detailManqueFour!=null){
                   
                            HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SuiviManqueFourController.class.getResource(nav.getiReportSituationManqueFourMp()));

             
            
            para.put("numBonLiv",detailManqueFour.getNumBonLiv());
            para.put("fournisseur",detailManqueFour.getFourisseur().getNom());
            para.put("numCommande",detailManqueFour.getNumCommande());
            para.put("depot",detailManqueFour.getDepot());
            para.put("magasin",detailManqueFour.getMagasinn().getLibelle());
            para.put("qte",detailManqueFour.getQuantite());
            para.put("qteRecu",detailManqueFour.getQuantiteRecu());
            para.put("qteEcart",detailManqueFour.getEcartQuantite());
            


             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeSousDetailManqueFour));
               JasperViewer.viewReport(jp, false);

         }else {
             
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.SELECTIONNER_UNE_LIGNE);
         
               }
               
               } catch (JRException ex) {
            Logger.getLogger(SuiviManqueFourController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
