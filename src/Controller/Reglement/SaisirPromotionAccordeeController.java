/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Utils.Constantes;
import dao.Entity.Depot;
import dao.Entity.DetailAvoirOulmes;
import dao.Entity.DetailPromotionAccordee;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
import dao.Entity.PromotionAccordee;
import dao.Manager.DepotDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.Manager.PromotionAccordeeDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
import dao.ManagerImpl.PromotionAccordeeDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SaisirPromotionAccordeeController implements Initializable {

    @FXML
    private TextField nBonField;
    @FXML
    private DatePicker datePromotion;
    @FXML
    private TextField codeClientField;
    @FXML
    private TextField clientField;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private Button btnRefreshGlobal;
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
    private Label qteAfficchage;
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField codeArtField;
    @FXML
    private ComboBox<String> LibelleCombo;
    @FXML
    private ComboBox<String> depotCombo;
    /**
     * Initializes the controller class.
     */
    private final ObservableList<DetailPromotionAccordee> listDetailPromotionAccordee=FXCollections.observableArrayList();
    
    navigation nav = new navigation();
    PrixOulmes prixOulmes =new PrixOulmes();
    ProduitDAO produitDAO = new ProduitDAOImpl();       
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    PromotionAccordeeDAO promotionAccordeeDAO = new PromotionAccordeeDAOImpl();
    DepotDAO depotDAO = new DepotDAOImpl();
    
    PromotionAccordee promotionAccordee = new PromotionAccordee();
    BigDecimal qteCaisse = BigDecimal.ZERO;
    
    private Map<String, PrixOulmes> mapPrixOulmes=new HashMap<>();
    private Map<String, Depot> mapDepot = new HashMap<>();
     
    BigDecimal montanTotal=BigDecimal.ZERO;
    
    
    void chargeClient(){
    
        List<String> listClient=promotionAccordeeDAO.findByClient();
          TextFields.bindAutoCompletion(clientField, listClient);
    
    }
    
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
        
        chargeClient();
        
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
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
        
                                  if (event.getCode().equals(KeyCode.ENTER) )
            {

                        prixOulmes = prixOulmesDAO.findPrixOulmesByCodeArt(codeArtField.getText());

                         LibelleCombo.setValue(prixOulmes.getProduit().getLibelle());
              }
        
    }

    @FXML
    private void ajouterSaisieAction(ActionEvent event) throws ParseException {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        if( detailPromotionAccoTable.getItems().isEmpty() || datePromotion.getValue()== null ||codeClientField.getText().isEmpty() || clientField.getText().isEmpty() || nBonField.getText().isEmpty()){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
     }
        else {
          
            LocalDate localDate=datePromotion.getValue();
            
          Date datePromo=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
          Depot depot = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
          
        promotionAccordee.setUtilisateurCreation(nav.getUtilisateur());
        promotionAccordee.setDatePromotion(datePromo);
        promotionAccordee.setDateCreation(new Date());
        promotionAccordee.setDepot(depot);
        promotionAccordee.setNumBon(nBonField.getText());
        promotionAccordee.setDetailPromotionAccordee(listDetailPromotionAccordee);
        promotionAccordee.setCodeClient(codeClientField.getText());
        promotionAccordee.setClient(clientField.getText());
        promotionAccordee.setMontantTotal(montanTotal);
        
        promotionAccordeeDAO.add(promotionAccordee);
     
        promotionAccordee = new PromotionAccordee();
       
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
 
         chargeClient();
         clear();

        }

        }

    }

    
   void clear(){
   
          codeArtField.clear();
             LibelleCombo.getSelectionModel().select(-1);   
             depotCombo.getSelectionModel().select(-1);   
             quantiteField.clear();
             qteAfficchage.setText("");
   
             datePromotion.setValue(null);
             codeClientField.clear();
             clientField.clear();
             nBonField.clear();
             
              listDetailPromotionAccordee.clear();
   
   }
    
    @FXML
    private void refreshGlobal(ActionEvent event) {
        
        clear();
        
    }

    @FXML
    private void ajouterMouseClicked(MouseEvent event) {
        
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                     if(codeArtField.getText().equalsIgnoreCase("")|| 
                   LibelleCombo.getValue().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("")

                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
         
    }
else {
              
                          BigDecimal  prixB= BigDecimal.ZERO ;
                          
                          Produit produit = produitDAO.findByCode(codeArtField.getText()); 
                        
                               PrixOulmes prixOulmes =prixOulmesDAO.findPrixOulmesByFourAndArt(Constantes.FOURNISSEUR_OULMES,produit.getId(),Constantes.SANS,Constantes.SANS);

                               
                                       if(prixOulmes==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                               
                               DetailPromotionAccordee detailPromotionAccordee = new DetailPromotionAccordee();
                
                  
                prixB= prixOulmes.getPrix();
     
               
                
                detailPromotionAccordee.setPrix(prixB);
                
                if(qteCaisse.compareTo(BigDecimal.ZERO)==0){
                    
                     BigDecimal QteB= new BigDecimal(quantiteField.getText());
                detailPromotionAccordee.setQte(QteB);
                
                BigDecimal  montantB= (QteB.multiply(prixB)).setScale(2,RoundingMode.FLOOR);
                
                detailPromotionAccordee.setMontant(montantB);

                montanTotal= montanTotal.add(montantB);
                
                }else {

                detailPromotionAccordee.setQte(qteCaisse);
                
                 BigDecimal  montantB= (qteCaisse.multiply(prixB)).setScale(2,RoundingMode.FLOOR);
                
                detailPromotionAccordee.setMontant(montantB);
                
                 montanTotal= montanTotal.add(montantB);
                
                }
             detailPromotionAccordee.setPrixOulmes(prixOulmes);
             detailPromotionAccordee.setPromotionAccordee(promotionAccordee);
             detailPromotionAccordee.setUtilisateurCreation(nav.getUtilisateur());
             
             listDetailPromotionAccordee.add(detailPromotionAccordee);
             
               detailPromotionAccoTable.setItems(listDetailPromotionAccordee);
          
             codeArtField.clear();
             LibelleCombo.getSelectionModel().select(-1);   
             quantiteField.clear();
             qteAfficchage.setText("");

            setColumnPropertiesList();
         }
}
            }
        
            }
        
        
        
        
    

    @FXML
    private void qteByIntervalleOnAction(KeyEvent event) {
        
           BigDecimal condCaisse = BigDecimal.ZERO;
           qteCaisse = BigDecimal.ZERO;
        
           qteAfficchage.setText("");

        PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
        
         if(prixOulmes!=null){
         
             condCaisse = prixOulmes.getConditionnementCaisse();
             
              if(condCaisse.compareTo(BigDecimal.ZERO)>0){
              
                   qteCaisse = new BigDecimal(quantiteField.getText()).multiply(condCaisse);
             
               DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

  

     qteAfficchage.setText(df.format(qteCaisse.setScale(2,RoundingMode.FLOOR)));
     return;
     
              }else if(condCaisse.compareTo(BigDecimal.ZERO)==0) {
              
                    
             
               DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

  

     qteAfficchage.setText(df.format(BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR)));
              return;
              }
             
             
            
         }else{
         
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.VERIFIER_CODE_LIBELLE);
         return;
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
    private void depotCombOnAction(ActionEvent event) {
    }
    
}
