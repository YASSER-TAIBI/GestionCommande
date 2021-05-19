/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.CompteFourMP;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCompte;
import dao.Entity.Fournisseur;
import dao.Manager.ClientMPDAO;
import dao.Manager.CompteFourMPDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.FournisseurDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CompteFourMPDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SaisirReportController implements Initializable {

    @FXML
    private TableView<DetailCompte> tableDetailCompte;
    @FXML
    private TableColumn<DetailCompte, Date> dateColumn;
    @FXML
    private TableColumn<DetailCompte, String> DesignationColumn;
    @FXML
    private TableColumn<DetailCompte, BigDecimal> montantCreditColumn;
    @FXML
    private TableColumn<DetailCompte, BigDecimal> montantDebitColumn;
    @FXML
    private TableColumn<DetailCompte, String> clientColumn;
      @FXML
    private TableColumn<DetailCompte, String> CompteColumn;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnModifier;
    @FXML
    private Pane paneMontant;
    @FXML
    private TextField montantCreditField;
    @FXML
    private TextField montantDebitField;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ComboBox<String> anneeReportCombo;
    @FXML
    private ComboBox<String> compteCombo;
  
  BigDecimal valeurDebit =BigDecimal.ZERO;
    BigDecimal valeurCredit = BigDecimal.ZERO;
    /**
     * Initializes the controller class.
     */
      private final ObservableList<DetailCompte> listeDetailCompte=FXCollections.observableArrayList();
       ObservableList<String> anneeReportlist =FXCollections.observableArrayList(Constantes.ANNEES_2018,Constantes.ANNEES_2017,Constantes.ANNEES_2016);
            FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
     DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    
     private Map<String,ClientMP> mapClientMP=new HashMap<>();
 DetailCompte detailCompte = new DetailCompte();
     
     navigation nav = new navigation();
        ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
       CompteFourMPDAO compteDAO = new CompteFourMPDAOImpl();
    private Map<String,CompteFourMP> mapCompte=new HashMap<>();
     private CompteFourMP compteFourMP = new  CompteFourMP();
    
    double valeur = 0;
    @FXML
    private DatePicker dateCreation;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        compteFourMP = new  CompteFourMP();
          anneeReportCombo.setItems(anneeReportlist);
        
                List<ClientMP> listClient=clientMPDAO.findAll();
        
        listClient.stream().map((client) -> {
            clientCombo.getItems().addAll(client.getNom());
            return client;
        }).forEachOrdered((client) -> {
            mapClientMP.put(client.getNom(), client);
        });

          tableDetailCompte.setEditable(true);

          
             List<CompteFourMP> listCompte=compteDAO.findAll();
        
        listCompte.stream().map((compte) -> {
            compteCombo.getItems().addAll(compte.getLibelle());
            return compte;
        }).forEachOrdered((compte) -> {
            mapCompte.put(compte.getLibelle(), compte);
        });
        
           setColumnProperties();
      loadDetail();  
        
    }    

     void setColumnProperties(){
        
    
         dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOperation"));
        DesignationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        montantDebitColumn.setCellValueFactory(new PropertyValueFactory<>("montantDebit"));
        montantCreditColumn.setCellValueFactory(new PropertyValueFactory<>("montantCredit"));
        
           clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCompte, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCompte, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getClientMP().getNom());
            }
        });
           
              CompteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCompte, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCompte, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCompteFourMP().getLibelle());
            }
        });
        
         
      
    }
    
    void loadDetail(){
        
        listeDetailCompte.clear();
        listeDetailCompte.addAll(detailCompteDAO.findAll());
        tableDetailCompte.setItems(listeDetailCompte);
    }
    
    
    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
         Integer val= tableDetailCompte.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          
          return;
          }
          else {

                    compteCombo.setValue(CompteColumn.getCellData(val));
                    clientCombo.setValue(clientColumn.getCellData(val));
                    montantCreditField.setText(montantCreditColumn.getCellData(val)+"");
                    montantDebitField.setText(montantDebitColumn.getCellData(val)+"");
        
          }
    }



    @FXML
    private void ajouterCompte(ActionEvent event) throws ParseException {

     if(compteCombo.getSelectionModel().getSelectedIndex()== -1 || 
                compteCombo.getSelectionModel().getSelectedItem().equals("") || 
                clientCombo.getSelectionModel().getSelectedIndex()== -1 || 
                clientCombo.getSelectionModel().getSelectedItem().equals("")||
             anneeReportCombo.getSelectionModel().getSelectedIndex()== -1 || 
                anneeReportCombo.getSelectionModel().getSelectedItem().equals("")

             ){
    
         nav.showAlert(Alert.AlertType.ERROR, "Succès", null,Constantes.REMPLIR_CHAMPS);
     }else {

    LocalDate localDate=dateCreation.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
       
       if(montantCreditField.getText().equals("")&& montantDebitField.getText().equals("") ){
           montantCreditField.setStyle("-fx-border-color: red;");
           montantDebitField.setStyle("-fx-border-color: red;");
           nav.showAlert(Alert.AlertType.ERROR, "Succès", null,Constantes.SELECTION_MONTANT);
           return ;
           }else {
         
                DetailCompte  detailCompte = new DetailCompte();

               detailCompte.setDateOperation(new Date());
               detailCompte.setDateBonLivraison(date);
               detailCompte.setClientMP(mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem()));
               detailCompte.setDesignation(Constantes.REPORT_SUR_ANNEE+anneeReportCombo.getSelectionModel().getSelectedItem());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
                if (!montantCreditField.getText().equals("")){
        detailCompte.setMontantCredit(new BigDecimal(montantCreditField.getText()));
        detailCompte.setMontantDebit(BigDecimal.ZERO);
       }else if (!montantDebitField.getText().equals("")){
           detailCompte.setMontantDebit(new BigDecimal(montantDebitField.getText()));
           detailCompte.setMontantCredit(BigDecimal.ZERO);
       }
                
                detailCompteDAO.add(detailCompte);

          
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
        
           clear();
       setColumnProperties();
      loadDetail();
      
       

    }
        
    }
    }
    
    
    @FXML
    private void ModifierFournisseur(ActionEvent event) throws ParseException {
             if (tableDetailCompte.getSelectionModel().getSelectedItem() != null) {
        
                     LocalDate localDate=dateCreation.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
       
                 
         DetailCompte detailCompte=tableDetailCompte.getSelectionModel().getSelectedItem();
         
               detailCompte.setDateOperation(new Date());
               detailCompte.setDateBonLivraison(date);
               detailCompte.setClientMP(mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem()));
               detailCompte.setDesignation(Constantes.REPORT_SUR_ANNEE+anneeReportCombo.getSelectionModel().getSelectedItem());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
                if (!montantCreditField.getText().equals("")){
        detailCompte.setMontantCredit(new BigDecimal(montantCreditField.getText()));
        detailCompte.setMontantDebit(BigDecimal.ZERO);
       }else if (!montantDebitField.getText().equals("")){
           detailCompte.setMontantDebit(new BigDecimal(montantDebitField.getText()));
           detailCompte.setMontantCredit(BigDecimal.ZERO);
       }
          detailCompteDAO.edit(detailCompte);
     
      clear();
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,  Constantes.MODIFIER_ENREGISTREMENT );
             setColumnProperties();
      loadDetail();
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
    }

    @FXML
    private void montantCreditOnMouseClick(MouseEvent event) {
            montantDebitField.setDisable(true);
    }

    @FXML
    private void montantDebitOnMouseClick(MouseEvent event) {
         montantCreditField.setDisable(true);
    }

     private void clear(){
        
     
        compteCombo.getSelectionModel().select(-1);
        montantCreditField.clear();
        montantDebitField.clear();
        anneeReportCombo.getSelectionModel().select(-1);
        clientCombo.getSelectionModel().select(-1);
        montantDebitField.setDisable(false);
        montantCreditField.setDisable(false);
         loadDetail();
    } 

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
         clear();
    }

    @FXML
    private void compteOnAction(ActionEvent event) {
          if(compteCombo.getSelectionModel().getSelectedIndex()!= -1){    
                   listeDetailCompte.clear();
            compteFourMP=mapCompte.get(compteCombo.getSelectionModel().getSelectedItem());
           listeDetailCompte.addAll(detailCompteDAO.findDetailCompteByCompte(compteFourMP.getId()));

           
    }
    }
}
