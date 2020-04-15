/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Controller.commande.EnvoyerCommandeController;
import Utils.Constantes;
import dao.Entity.BonLivraison;
import dao.Entity.ClientMP;
import dao.Entity.Fournisseur;
import dao.Entity.Reglement;
import dao.Manager.ClientMPDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.ReglementDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.ReglementDAOImpl;
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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class ConsultationReglementController implements Initializable {

    @FXML
    private TableView<Reglement> tableReglement;
    @FXML
    private TableColumn<Reglement, String> codeColumn;
    @FXML
    private TableColumn<Reglement, Date> DateColumn;
    @FXML
    private TableColumn<Reglement, BigDecimal> montantReglementColumn;
    @FXML
    private TableColumn<Reglement, Integer> fourColumn;
    @FXML
    private TableColumn<Reglement, Integer> ClientColumn;
    @FXML
    private TableColumn<Reglement, String> designationColumn;
    @FXML
    private TableColumn<Reglement, String> numFactureColumn;
    @FXML
    private TableColumn<Reglement, String> modeReglementColumn;
    @FXML
    private TableColumn<Reglement, String> numChequeColumn;
    @FXML
    private TableColumn<Reglement, Boolean> actionColumn;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    @FXML
    private TextField montantTotalField;
    @FXML
    private TextField numFactureRech;
    @FXML
    private TextField numChequeRech;
    
private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
private Map<String,ClientMP> mapClientMP=new HashMap<>();

  navigation nav = new navigation();
  private final ObservableList<Reglement> listeReglement=FXCollections.observableArrayList();
  FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
  ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
ReglementDAO  reglementDAO = new ReglementDAOImpl();
Reglement reglement = new  Reglement();
  
  BigDecimal  montantTotal=BigDecimal.ZERO;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            List<Fournisseur> listFournisseur=fournisseurDAO.findAll();
        
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
        
            tableReglement.setEditable(true);
            
            numChequeRech.setDisable(true);
            numFactureRech.setDisable(true);
    }    

    
    void setColumnProperties() {

    
      codeColumn.setCellValueFactory(new PropertyValueFactory<>("codeReglement"));
      DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
      
      montantReglementColumn.setCellValueFactory(new PropertyValueFactory<>("montantReglement"));
      
//               montantReglementColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reglement, BigDecimal>, ObservableValue<BigDecimal>>() {
//                @Override
//                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<Reglement, BigDecimal> p) {
//                    
//                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
//                dfs.setDecimalSeparator(',');
//                dfs.setGroupingSeparator('.');
//                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
//                df.setGroupingUsed(true);
//                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantReglement()));
//                }
//                
//             });
      
      fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reglement, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reglement, Integer> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFournisseur().getNom());
                }
             });
      
      ClientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reglement, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reglement, Integer> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClientMP().getNom());
                }
             });
          
      designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
      numFactureColumn.setCellValueFactory(new PropertyValueFactory<>("numFacture"));
      modeReglementColumn.setCellValueFactory(new PropertyValueFactory<>("modeReglement"));
      numChequeColumn.setCellValueFactory(new PropertyValueFactory<>("numCritique"));
      
         actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          Reglement cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionColumn.setEditable(true);
      
          
          tableReglement.setEditable(true);
      
    }
    
    
    @FXML
    private void fournisseurOnAction(ActionEvent event) {

    }

    @FXML
    private void clientOnAction(ActionEvent event) {

        
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
            try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationReglementController.class.getResource(nav.getiReportBonReglement()));


            para.put("Fournisseur",fourCombo.getSelectionModel().getSelectedItem());
            para.put("Client",clientCombo.getSelectionModel().getSelectedItem());
            
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(tableReglement.getItems()));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(EnvoyerCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
      
        
              clientCombo.getSelectionModel().select(-1);
              fourCombo.getSelectionModel().select(-1);
        
              clientCombo.setDisable(false);
              fourCombo.setDisable(false);

              numChequeRech.setText("");
              numFactureRech.setText("");
              
              numChequeRech.setDisable(true);
              numFactureRech.setDisable(true);
              
              montantTotalField.setText("");
              
              for(int i =0;i<listeReglement.size();i++){
              
                  Reglement reglement = listeReglement.get(i);
                  
             reglement.setAction(Boolean.FALSE);
                  
              }
        
              tableReglement.getItems().clear();
    }

    @FXML
    private void recherchMouseClicked(MouseEvent event) {
            if (fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                clientCombo.getSelectionModel().getSelectedIndex()== -1 || 
                clientCombo.getSelectionModel().getSelectedItem().equals("") )
        {
        nav.showAlert(Alert.AlertType.ERROR, "Alert", null,Constantes.VERIFIER_FOURNISSEUR_CLIENT);
        }else{
              
                 setColumnProperties();
                  listeReglement.clear();
                  
       ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
       Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
                
        listeReglement.addAll(reglementDAO.findFourByRechercheNomReglement(fournisseur.getId(),clientMP.getId()));
        tableReglement.setItems(listeReglement);

              numChequeRech.setDisable(false);
              numFactureRech.setDisable(false);
        }
    }

    @FXML
    private void calculeMouseClicked(MouseEvent event) {
        
          montantTotal=BigDecimal.ZERO;
        
          for( int rows = 0;rows<tableReglement.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){

             montantTotal= montantTotal.add(montantReglementColumn.getCellData(rows));

        }  
    }
                    DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
         montantTotalField.setText(df.format(montantTotal));  
 
    }

    @FXML
    private void rechercheNumFactureKeyPressed(KeyEvent event) {
               
    ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
    Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
        
    ObservableList<Reglement> listeReglementRech=FXCollections.observableArrayList();
                   
    listeReglementRech.clear();
   
   listeReglementRech.addAll(reglementDAO.findReglementByFacture(numFactureRech.getText(),clientMP.getId(),fournisseur.getId()));
   
   tableReglement.setItems(listeReglementRech);
    
    }

    @FXML
    private void rechercheNumChequeKeyPressed(KeyEvent event) {
               
          ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
       Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
        
    ObservableList<Reglement> listeReglementRech=FXCollections.observableArrayList();
                   
    listeReglementRech.clear();
   
   listeReglementRech.addAll(reglementDAO.findReglementByCheque(numChequeRech.getText(),clientMP.getId(),fournisseur.getId()));
   
   tableReglement.setItems(listeReglementRech);
    
    }

    @FXML
    private void rechFactureOnCllicked(MouseEvent event) {
             numChequeRech.setDisable(true);
        
    }

    @FXML
    private void rechChequeOnCllicked(MouseEvent event) {
             numFactureRech.setDisable(true);
    }
    
    
}
