/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.DetailFactureAvoirBonAvoir;
import dao.Entity.Fournisseur;
import dao.Entity.PrixOulmes;
import dao.Entity.SituationGlobalAvoir;
import dao.Manager.AvoirOulmesDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.FactureAvoirOulmesDAO;
import dao.Manager.PrixOulmesDAO;
import dao.ManagerImpl.AvoirOulmesDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.FactureAvoirOulmesDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
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
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class DetailFactureAvoirBonAvoirOulmesController implements Initializable {

    @FXML
    private TableView<DetailFactureAvoirBonAvoir> detailFactureAvoirOulmestable;
    @FXML
    private TableColumn<DetailFactureAvoirBonAvoir, Date> dateSaisieColumn;
    @FXML
    private TableColumn<DetailFactureAvoirBonAvoir, String> designationColumn;
    @FXML
    private TableColumn<DetailFactureAvoirBonAvoir, BigDecimal> blAvoirColumn;
    @FXML
    private TableColumn<DetailFactureAvoirBonAvoir, BigDecimal> factureAvoirColumn;
    @FXML
    private TextField codeArtField;
    @FXML
    private ComboBox<String> LibelleCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private Label montantFacture;
    @FXML
    private Label montantAvoir;
    @FXML
    private Label montantEcart;
    @FXML
    private Label qteAfficchage;

    
       PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
       ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
       navigation nav = new navigation();
      PrixOulmes prixOulmes =new PrixOulmes();
       
       
        FactureAvoirOulmesDAO factureAvoirOulmesDAO = new FactureAvoirOulmesDAOImpl();
    AvoirOulmesDAO avoirOulmesDAO = new AvoirOulmesDAOImpl();
       
    private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    /**
     * Initializes the controller class.
     */
    
      private final  ObservableList<DetailFactureAvoirBonAvoir> listeDetailFactureAvoirBonAvoir = FXCollections.observableArrayList();   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
                    List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findAll();

        listPrixOulmes.stream().map((prixOulmes) -> {
            LibelleCombo.getItems().addAll(prixOulmes.getProduit().getLibelle());
            return prixOulmes;
        }).forEachOrdered((prixOulmes) -> {
            mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
        });
        
          List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((client) -> {
            clientCombo.getItems().addAll(client.getNom());
            return client;
        }).forEachOrdered((client) -> {
            mapClientMP.put(client.getNom(), client);
        });
        
      
        
    }    

     void setColumnProperties() {

         dateSaisieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, Date> p) {
                    
        
                    return new ReadOnlyObjectWrapper(p.getValue().getDateSaisie());
                }
                
             });
         
          designationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, String> p) {
                    
             
                    return new ReadOnlyObjectWrapper(p.getValue().getDesignation());
                }
                
             });
          
           blAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getAvoirQte()));
                }
                
             });
           
           
            factureAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailFactureAvoirBonAvoir, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getFactureAvoirQte()));
                }
                
             });
      
         
              
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
    private void LibelleComboOnAction(ActionEvent event) {
        
            PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
         if(prixOulmes!=null){
         codeArtField.setText(prixOulmes.getProduit().getCode());
         }
        
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }

    void calcule (){
    
          BigDecimal calculAvoir= BigDecimal.ZERO;
             BigDecimal calculFacture= BigDecimal.ZERO;
             BigDecimal somme=BigDecimal.ZERO;
             
           for( int rows = 0;rows<listeDetailFactureAvoirBonAvoir.size() ;rows++ ){

               DetailFactureAvoirBonAvoir detailFactureAvoirBonAvoir = listeDetailFactureAvoirBonAvoir.get(rows);
               
            calculAvoir = calculAvoir.add(detailFactureAvoirBonAvoir.getAvoirQte());  

    }
           
              DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
           
         montantAvoir.setText(df.format(calculAvoir));  
        
     
           for( int rows = 0;rows<listeDetailFactureAvoirBonAvoir.size() ;rows++ ){
               
                 DetailFactureAvoirBonAvoir detailFactureAvoirBonAvoir = listeDetailFactureAvoirBonAvoir.get(rows);

            calculFacture = calculFacture.add(detailFactureAvoirBonAvoir.getFactureAvoirQte());  
        
    }
           
                  
           
         montantFacture.setText(df.format(calculFacture));  

         somme = calculAvoir.subtract(calculFacture) ;
          
         montantEcart.setText(df.format(somme));  
        
    }
    
    
    @FXML
    private void rechercheMouseClicked(MouseEvent event) {
        
          if ( codeArtField.getText().equalsIgnoreCase("") || LibelleCombo.getSelectionModel().getSelectedItem() == null || LibelleCombo.getSelectionModel().getSelectedItem().isEmpty()|| clientCombo.getSelectionModel().getSelectedItem() == null || clientCombo.getSelectionModel().getSelectedItem().isEmpty() ) {
            
              nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                
            } else{ 
        
          ClientMP clientMP  = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
        
          List <Object[]> listeObjectAvoir =avoirOulmesDAO.findAvoirOulmesByCodeAndClient(codeArtField.getText(), clientMP.getNom());

             listeDetailFactureAvoirBonAvoir.clear();
         
            for(int i=0; i<listeObjectAvoir.size(); i++) {

                    Object[] object=listeObjectAvoir.get(i);
                   
                    PrixOulmes article =(PrixOulmes)object[0];
                    BigDecimal avoir = (BigDecimal)object[1]; 
                    Date dateSaisie = (Date)object[2];
                    String Designation= (String)object[3];
                    
           
                    
                    
                    if(article == null && avoir == null && dateSaisie == null && Designation == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

                        DetailFactureAvoirBonAvoir detailFactureAvoirBonAvoir = new DetailFactureAvoirBonAvoir();
               
                  detailFactureAvoirBonAvoir.setAvoirQte(avoir);
                  detailFactureAvoirBonAvoir.setFactureAvoirQte(BigDecimal.ZERO);
                  detailFactureAvoirBonAvoir.setDateSaisie(dateSaisie);
                  detailFactureAvoirBonAvoir.setDesignation(Designation);
                  
                  listeDetailFactureAvoirBonAvoir.add(detailFactureAvoirBonAvoir);
                    }

    }
            
                 List <Object[]> listeObjectFacture =factureAvoirOulmesDAO.findFactureOulmesByCodeAndClient(codeArtField.getText(), clientMP.getNom());

       
         
            for(int i=0; i<listeObjectFacture.size(); i++) {

                    Object[] object=listeObjectFacture.get(i);
                   
                    PrixOulmes article =(PrixOulmes)object[0];
                    BigDecimal facture = (BigDecimal)object[1]; 
                    Date dateSaisie = (Date)object[2];
                    String Designation= (String)object[3]; 
                    
           
                    
                    
                    if(article == null && facture == null && dateSaisie == null && Designation == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

                        DetailFactureAvoirBonAvoir detailFactureAvoirBonAvoir = new DetailFactureAvoirBonAvoir();
               
                  detailFactureAvoirBonAvoir.setFactureAvoirQte(facture);
                  detailFactureAvoirBonAvoir.setAvoirQte(BigDecimal.ZERO);
                  detailFactureAvoirBonAvoir.setDateSaisie(dateSaisie);
                  detailFactureAvoirBonAvoir.setDesignation(Designation);
                  
                  listeDetailFactureAvoirBonAvoir.add(detailFactureAvoirBonAvoir);
                    }

    }
            
            
     detailFactureAvoirOulmestable.setItems(listeDetailFactureAvoirBonAvoir);
            setColumnProperties();
            
            calcule ();
}
    }
}
