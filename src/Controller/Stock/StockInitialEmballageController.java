/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.Fournisseur;
import dao.Entity.InitialEmballage;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
import dao.Manager.ClientMPDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.InitialEmballageDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.InitialEmballageDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class StockInitialEmballageController implements Initializable {

    
    @FXML
    private TableView<InitialEmballage> tableEmballage;
    @FXML
    private TableColumn<InitialEmballage, BigDecimal> qteColumn;
    @FXML
    private TableColumn<InitialEmballage, String> codeColumn;
    @FXML
    private TableColumn<InitialEmballage, String> libelleColumn;
    @FXML
    private TableColumn<InitialEmballage, String> fourColumn;
    @FXML
    private TableColumn<InitialEmballage, String> clientColumn;
    @FXML
    private TableColumn<InitialEmballage, Date> dateInitialColumn;
    
    
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnAjouter;
    @FXML
    private DatePicker dateInitial; 
    @FXML
    private TextField txtCode;
    @FXML
    private Label msgCode;
    
    @FXML
    private TextField txtQte;
    @FXML
    private ComboBox<String> LibelleCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ComboBox<String> fourCombo;
    
 private final ObservableList<InitialEmballage> listInitialEmballage=FXCollections.observableArrayList();
            
    InitialEmballageDAO initialEmballageDAO = new InitialEmballageDAOImpl();
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
    
    navigation nav = new navigation();
    
    InitialEmballage initialEmballage;
    
    
    
    

    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findByPallete();

        listPrixOulmes.stream().map((prixOulmes) -> {
            LibelleCombo.getItems().addAll(prixOulmes.getProduit().getLibelle());
            return prixOulmes;
        }).forEachOrdered((prixOulmes) -> {
            mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
        });
 
            List<Fournisseur> listFournisseur=fournisseurDAO.findAllPF();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
            List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((clientMP) -> {
            clientCombo.getItems().addAll(clientMP.getNom());
            return clientMP;
        }).forEachOrdered((clientMP) -> {
            mapClientMP.put(clientMP.getNom(), clientMP);
        });
        
      setColumnProperties();
      loadDetail(); 
      
    }    

     void setColumnProperties(){
        
         codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InitialEmballage, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<InitialEmballage, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });
          
          
          libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InitialEmballage, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<InitialEmballage, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
             });
          
          
        qteColumn.setCellValueFactory(new PropertyValueFactory<>("qteEmballage"));
        
        
           fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InitialEmballage, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<InitialEmballage, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFourisseur().getNom());
                }
             });
          
          
          clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InitialEmballage, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<InitialEmballage, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClientMP().getNom());
                }
             });
        
           dateInitialColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InitialEmballage, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<InitialEmballage, Date> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDateInitial());
                }
             });
          
     }
    
     void loadDetail(){
        
        listInitialEmballage.clear();
        listInitialEmballage.addAll(initialEmballageDAO.findAll());
        tableEmballage.setItems(listInitialEmballage);
    }
     

     
    @FXML
    private void Supprimer(ActionEvent event) {
        
          if(tableEmballage.getSelectionModel().getSelectedItem()==null){
         
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
              
       InitialEmballage initialEmballage=tableEmballage.getSelectionModel().getSelectedItem();

            initialEmballageDAO.delete(initialEmballage);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        clear();
    }
    }

    @FXML
    private void Modifier(ActionEvent event) throws ParseException {
        
        if (tableEmballage.getSelectionModel().getSelectedItem() != null) {
              
            ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
            Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
            PrixOulmes prixOulmes =  mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
            
            Date dateInit=new SimpleDateFormat("yyyy-MM-dd").parse(dateInitial.getValue().toString());
            
              InitialEmballage initialEmballage= tableEmballage.getSelectionModel().getSelectedItem();

               initialEmballage.setPrixOulmes(prixOulmes);
               initialEmballage.setClientMP(clientMP);
               initialEmballage.setDateInitial(dateInit);
               initialEmballage.setFourisseur(fournisseur);
               initialEmballage.setQteEmballage(new BigDecimal(txtQte.getText()));
               initialEmballage.setUtilisateurCreation(nav.getUtilisateur());
              
               initialEmballageDAO.edit(initialEmballage);
      
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
       
             clear();
        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws ParseException {

     if(txtCode.getText().equalsIgnoreCase("") || txtQte.getText().equalsIgnoreCase("")){
         
        msgCode.setText(Constantes.CHAMP_OBLIGATOIRE);
        txtCode.setStyle("-fx-border-color: red;");
        
     }else {
       
          ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
            Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
         PrixOulmes prixOulmes =  mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
         
          Date dateInit=new SimpleDateFormat("yyyy-MM-dd").parse(dateInitial.getValue().toString());
         
             InitialEmballage initialEmballage =new InitialEmballage();
         
       initialEmballage.setPrixOulmes(prixOulmes);
       initialEmballage.setQteEmballage(new BigDecimal(txtQte.getText()));
       initialEmballage.setClientMP(clientMP);
       initialEmballage.setDateInitial(dateInit);
       initialEmballage.setFourisseur(fournisseur);
       initialEmballage.setUtilisateurCreation(nav.getUtilisateur());
       
        initialEmballageDAO.add(initialEmballage);
        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
        
           clear();
    }
    }

    @FXML
    private void searche(KeyEvent event) {
    }


    void clear(){
    
    txtCode.clear();
    txtQte.clear();
    dateInitial.setValue(null);
    LibelleCombo.getSelectionModel().clearSelection();
    fourCombo.getSelectionModel().clearSelection();
    clientCombo.getSelectionModel().clearSelection();

    setColumnProperties();
    loadDetail(); 
    
    }
    
    
    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        clear();
    }

    @FXML
    private void clickChargeOnMouse(MouseEvent event) {
        
        
           Integer val= tableEmballage.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              txtCode.setText(codeColumn.getCellData(val));
              LibelleCombo.setValue(libelleColumn.getCellData(val));
              clientCombo.setValue(clientColumn.getCellData(val));
              fourCombo.setValue(fourColumn.getCellData(val));
              txtQte.setText(qteColumn.getCellData(val)+"");

          }

    }


    @FXML
    private void LibelleComboOnAction(ActionEvent event) {
        
                 PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
         
          if(prixOulmes!=null){
              
         txtCode.setText(prixOulmes.getProduit().getCode());
         
          }
        
    }

    @FXML
    private void fournisseurOnAction(ActionEvent event) {
    }
    
}
