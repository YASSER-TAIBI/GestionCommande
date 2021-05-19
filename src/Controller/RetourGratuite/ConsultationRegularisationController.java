/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.RetourGratuite;

import Utils.Constantes;
import dao.Entity.BonRetour;
import dao.Entity.ClientMP;
import dao.Entity.Fournisseur;
import dao.Manager.BonRetourDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.FournisseurDAO;
import dao.ManagerImpl.BonRetourDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
public class ConsultationRegularisationController implements Initializable {

    @FXML
    private TableView<BonRetour> tableRegularisation;
    @FXML
    private TableColumn<BonRetour, String> numFactureColumn;
    @FXML
    private TableColumn<BonRetour, String> typeColumn;
    @FXML
    private TableColumn<BonRetour, String> fourColumn;
    @FXML
    private TableColumn<BonRetour, BigDecimal> montantColumn;
    @FXML
    private TableColumn<BonRetour, String> numTraiteColumn;
    @FXML
    private TableColumn<BonRetour, String> clientColumn;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField montantTotalField;
    
    /**
     * Initializes the controller class.
     */
         private final ObservableList<BonRetour> listeBonRetour=FXCollections.observableArrayList();  
     BonRetourDAO bonRetourDAO = new BonRetourDAOImpl();
    
     navigation nav = new navigation();
     BonRetour bonRetour = new BonRetour();
    
private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
private Map<String,ClientMP> mapClientMP=new HashMap<>();

  FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
  ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
   
  BigDecimal montantTotal = BigDecimal.ZERO;
     
      void setColumnProperties(){
        
      numFactureColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumFacture());
                }
             });    
          
      typeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getType());
                }
             });
   
      clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClient());
                }
             });
      
      fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFournisseur());
                }
                
             });

       numTraiteColumn.setCellValueFactory(new PropertyValueFactory<BonRetour, String>("numTraite"));
        setColumnTextFieldConverter(getConverter(),numTraiteColumn);
      
      montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<BonRetour, BigDecimal> p) {
                    
                    DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantPaye()));
                }
                
             });
    }
    
                public static void setColumnTextFieldConverter(StringConverter converter, TableColumn... columns) {

        for (TableColumn tableColumn : columns) {

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        }
    }

        
    private StringConverter getConverter() {
        StringConverter<String> converter = new StringConverter<String>() {
            @Override
            public String fromString(String string) {

                try {
                    
             String valeur;      
             valeur = string;
                    return valeur;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString(String object) {

                if (object == null) {
                    return null;
                }
                return String.valueOf(object);
            }
        };

        return converter;
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
        
        
        
        setColumnProperties();
         loadDetail();
        calcule();
            
              tableRegularisation.setEditable(true);
              numTraiteColumn.setEditable(true);
            
    }    

    
      void loadDetail(){
        listeBonRetour.clear();
        listeBonRetour.addAll(bonRetourDAO.findTypeByRechercheBonRetour(Constantes.DIMINUTION, Constantes.AUGMENTATION));
        tableRegularisation.setItems(listeBonRetour);
    }
      
      
    @FXML
    private void afficherDetailAndStockProdOnMouseClicked(MouseEvent event) {
    }


    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
        
          try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationRegularisationController.class.getResource(nav.getiReportConsultationRegularisationPrix()));

              para.put("montantTTC",montantTotalField.getText());
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(tableRegularisation.getItems()));
               JasperViewer.viewReport(jp, false);
               
     
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationRegularisationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    
        
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
        
        fourCombo.getSelectionModel().select(-1);
        clientCombo.getSelectionModel().select(-1);
        
        tableRegularisation.getItems().clear();
        listeBonRetour.clear();
        
        setColumnProperties();
        loadDetail();
        calcule();
    }

    @FXML
    private void fournisseurOnAction(ActionEvent event) {
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }

    @FXML
    private void recherchMouseClicked(MouseEvent event) {
        
          montantTotal = BigDecimal.ZERO;
        
        
                  if (fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                clientCombo.getSelectionModel().getSelectedIndex()== -1 || 
                clientCombo.getSelectionModel().getSelectedItem().equals("") )
        {
        nav.showAlert(Alert.AlertType.ERROR, "Alert", null,Constantes.VERIFIER_FOURNISSEUR_CLIENT);
        }else{
              
                 setColumnProperties();
                  listeBonRetour.clear();
                  
       ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
       Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
                
        listeBonRetour.addAll(bonRetourDAO.findBonRetourByRechercheFourAndClient(fournisseur.getNom(),clientMP.getNom(),Constantes.DIMINUTION, Constantes.AUGMENTATION));
        tableRegularisation.setItems(listeBonRetour);
        
        calcule();
                   
        }
    }

    void calcule(){

        montantTotal = BigDecimal.ZERO;
           for (int i = 0; i < listeBonRetour.size(); i++) {
                          
                          BonRetour bonRetour = listeBonRetour.get(i);
                          
                           montantTotal = montantTotal.add(bonRetour.getMontantPaye());
                          
                      }
           DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
            montantTotalField.setText(df.format(montantTotal));  
}
    
    @FXML
    private void numTraiteOnEditCommit(TableColumn.CellEditEvent<BonRetour, String> event) {
        
            System.out.println("event.getNewValue() "+event.getNewValue());
        
            ((BonRetour) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setNumTraite(event.getNewValue());
            
            
            
            
              String numTraite = numTraiteColumn.getCellData(event.getTablePosition().getRow());

              
              if (numTraite== null){
                  
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.ERREUR , Constantes.REMPLIR_CHAMPS );
              
              }else{
              
              tableRegularisation.refresh();
              BonRetour bonRetour = listeBonRetour.get(event.getTablePosition().getRow());
              bonRetour.setNumTraite(numTraite);
              listeBonRetour.set(event.getTablePosition().getRow(), bonRetour);
                
              bonRetourDAO.edit(bonRetour);

              
              }
        
    }
    
}
