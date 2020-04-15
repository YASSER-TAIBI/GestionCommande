/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.RetourGratuite;

import Utils.Constantes;
import dao.Entity.BonLivraison;
import dao.Entity.BonRetour;
import dao.Entity.Commande;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailBonRetour;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCompte;
import dao.Entity.DetailManqueFour;
import dao.Entity.DetailReception;
import dao.Entity.Fournisseur;
import dao.Entity.MatierePremier;
import dao.Entity.Sequenceur;
import dao.Entity.StockMP;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.BonRetourDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailBonRetourDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailManqueFourDAO;
import dao.Manager.DetailReceptionDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.StockMPDAO;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.BonRetourDAOImpl;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailBonRetourDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailManqueFourDAOImpl;
import dao.ManagerImpl.DetailReceptionDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.StockMPDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
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
public class SuiviRetourController implements Initializable {

    @FXML
    private TableView<DetailCommande> tableDetailBonRetour;
    @FXML
    private TableColumn<DetailCommande, String> codeMPColumn;
    @FXML
    private TableColumn<DetailCommande, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteRetourColumn;
    @FXML
    private Button btnValider;
    @FXML
    private ComboBox<String> livraisonCombo;
    @FXML
    private TextField nCommandeField;
    @FXML
    private RadioButton radioBonRetour;
    @FXML
    private DatePicker dateCreation;
    @FXML
    private CheckBox recuCheck;
    @FXML
    private TextField nRetourManqueField;
    @FXML
    private RadioButton radioManque;
    @FXML
    private Button btnRefresh;
    @FXML
    private RadioButton radioReception;
    @FXML
    private RadioButton radioUsine;
    @FXML
    private RadioButton radioOui;
    @FXML
    private RadioButton radioNon;
    @FXML
    private RadioButton radioFour;
    
    private ObservableList<DetailCommande> listeDetailBonRetour=FXCollections.observableArrayList();
    private final ObservableList<DetailCommande> listeDetailBonRetourTMP = FXCollections.observableArrayList();
    
     DetailCommandeDAO detailCommandeDAO =new DetailCommandeDAOImpl();
     DetailBonRetourDAO detailBonRetourDAO = new DetailBonRetourDAOImpl();
     DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
     BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
     BonRetourDAO bonRetourDAO = new BonRetourDAOImpl();
     CommandeDAO commandeDAO = new  CommandeDAOImpl();
     DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
     StockMPDAO stockMPDAO = new StockMPDAOImpl();
     SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
     DetailCommande detailCommande = new DetailCommande();
     Commande commande = new Commande();
     BonLivraison bonLivraison = new BonLivraison();
     Fournisseur fournisseur = new Fournisseur();
     navigation nav = new navigation();
     DetailReceptionDAO detailReceptionDAO = new DetailReceptionDAOImpl();
     DetailManqueFourDAO detailManqueFourDAO =new DetailManqueFourDAOImpl();
     
     private Map<String,DetailBonLivraison> mapDetailBonLivraison=new HashMap<>();
     
//     
//     BigDecimal qteRetour = BigDecimal.ZERO;


    
     Commande commandeTMP ;
    @FXML
    private Button btnImprimer;
    @FXML
    private Pane RecuPane;
    @FXML
    private ToggleGroup groupeType;
    @FXML
    private ToggleGroup groupeType1;
    @FXML
    private ToggleGroup groupeStock;




     
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
//      RecuPane.setVisible(false);

        recuCheck.setDisable(true);
        radioReception.setDisable(true);
        radioFour.setDisable(true);
        radioUsine.setDisable(true);
        radioOui.setDisable(true);
        radioNon.setDisable(true);

    }    

        void IncrementationRtr (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RETOUR_CODE);
          nRetourManqueField.setText(Constantes.RETOUR_CODE+(sequenceur.getValeur()+1));

   }
    
          void IncrementationMnq (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.MANQUE_CODE);
          nRetourManqueField.setText(Constantes.MANQUE_CODE+(sequenceur.getValeur()+1));

   }
        
       void setColumnPropertiesDetailCommande() {

        codeMPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
            }
        });

        libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
            }

        });

           quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });

      
        quantiteRetourColumn.setCellValueFactory(new PropertyValueFactory<DetailCommande, BigDecimal>("quantiteLivree"));

        setColumnTextFieldConverter(getConverter(), quantiteRetourColumn);

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
    private void editCommitQuantiteRetourColumn(TableColumn.CellEditEvent<DetailCommande, BigDecimal> event)  throws ParseException  {

        
        if (nCommandeField.getText().equalsIgnoreCase("") || livraisonCombo.getSelectionModel().getSelectedItem() == null || livraisonCombo.getSelectionModel().getSelectedItem().isEmpty() /*|| depot.getText().equals("")*/) {

            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
            tableDetailBonRetour.refresh();
        } else {
            
            if(radioBonRetour.isSelected()==false && radioManque.isSelected()==false){
            
              nav.showAlert(Alert.AlertType.ERROR, "Attention", null,Constantes.VERIFIER_BON_RETOUR_BON_GRATUITE_MANQUE);
            }
            else{

                    Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                ((DetailCommande) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteLivree(event.getNewValue());
            

                tableDetailBonRetour.refresh();
                
                BigDecimal qteRetour = BigDecimal.ZERO;
                
                  qteRetour = quantiteRetourColumn.getCellData(event.getTablePosition().getRow());
                   detailCommande= tableDetailBonRetour.getItems().get(event.getTablePosition().getRow());
               detailCommande.setQuantiteLivree(qteRetour);
               
               listeDetailBonRetour.set(event.getTablePosition().getRow(), detailCommande);

   }
       }   
           }   
    }

    @FXML
    private void btnValiderOnAction(ActionEvent event) throws ParseException {
            

         if (nCommandeField.getText().equalsIgnoreCase("") || livraisonCombo.getSelectionModel().getSelectedItem() == null || livraisonCombo.getSelectionModel().getSelectedItem().isEmpty() || dateCreation.getValue()==null) {

            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
            tableDetailBonRetour.refresh();
        } else {
            
            if(radioBonRetour.isSelected()==false && radioManque.isSelected()==false ){
            
              nav.showAlert(Alert.AlertType.ERROR, "Attention", null,Constantes.VERIFIER_BON_RETOUR_BON_GRATUITE_MANQUE);
            }
            else{
           
                
                if(radioBonRetour.isSelected()){
                    
                    BigDecimal montantTotal = BigDecimal.ZERO; 

  String  numCommande = nCommandeField.getText();
                commande = commandeDAO.findCommandeByNumCommande(numCommande, Constantes.ETAT_COMMANDE_SUPPRIMER);
                
                 fournisseur = commande.getFourisseur();
                 
           BonRetour bonRetour= new BonRetour();

              LocalDate localDate=dateCreation.getValue();
            
                Date dateCreation=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
              
              bonRetour.setnCommande(numCommande.toUpperCase());
              bonRetour.setnRetour(nRetourManqueField.getText());
              bonRetour.setnLivraison(livraisonCombo.getSelectionModel().getSelectedItem());
              bonRetour.setFournisseur(fournisseur.getNom());
              bonRetour.setDateCreation(dateCreation);
              bonRetour.setAction(Boolean.FALSE);
              
              
               if (radioOui.isSelected()==true){
                   bonRetour.setEnStock(Constantes.EN_STOCK_OUI);
                 }else if(radioNon.isSelected()==true){
                   bonRetour.setEnStock(Constantes.EN_STOCK_NON);
                 }
              
               if (radioReception.isSelected()==true){
                   bonRetour.setReceptionOrUsine(Constantes.RECEPTION_TYPE);
                 }else if(radioUsine.isSelected()==true){
                   bonRetour.setReceptionOrUsine(Constantes.USINE_TYPE);
                 }
               
              bonRetour.setUtilisateurCreation(nav.getUtilisateur());
              bonRetour.setType(Constantes.RETOUR);
              bonRetour.setEtat(Constantes.RETOUR_EN_ATT_REGLEMENT);
              bonRetour.setMontantTotal(montantTotal);
                       bonRetourDAO.add(bonRetour);

              
                      for( int rows = 0;rows<listeDetailBonRetour.size() ;rows++ ){


              DetailCommande detailCommande = listeDetailBonRetour.get(rows);

                    
                 if(!detailCommande.getQuantiteLivree().equals(BigDecimal.ZERO.setScale(2))){

                     
                 DetailBonRetour detailBonRetour = new DetailBonRetour();

                     
//____________________________________________________Calcule montant______________________________________________________________

BigDecimal pu = BigDecimal.ZERO;
BigDecimal montant = BigDecimal.ZERO; 

            pu = detailCommande.getPrixUnitaire();

             
            montant = pu.multiply(detailCommande.getQuantiteLivree()).setScale(2,RoundingMode.FLOOR);

            
            montantTotal = montantTotal.add(montant).setScale(2,RoundingMode.FLOOR);

//______________________________________________________ Detail Bon Retour ___________________________________________________________________________

        MatierePremier matierePremier = detailCommande.getMatierePremier();

        
                detailBonRetour.setBonRetour(bonRetour);
                detailBonRetour.setQuantiteRetour(detailCommande.getQuantiteLivree());
                detailBonRetour.setPrixUnitaire(detailCommande.getPrixUnitaire());
                detailBonRetour.setUtilisateurCreation(nav.getUtilisateur());
                detailBonRetour.setMontant(montant);
                detailBonRetour.setMatierePremier(matierePremier);
               
                detailBonRetourDAO.add(detailBonRetour);
                
                 }
                      }
                  if(radioReception.isSelected()==true){ 
                 
                  DetailCompte detailCompte = new DetailCompte();
              
             Commande commandeTMP = commandeDAO.findCommandeByNumCommande(nCommandeField.getText(), Constantes.ETAT_COMMANDE_SUPPRIMER); 
              
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
              

               detailCompte.setDateOperation(new Date());
               detailCompte.setDateBonLivraison(dateCreation);
               detailCompte.setCode(nRetourManqueField.getText());
               detailCompte.setDesignation(Constantes.RETOUR_N+nRetourManqueField.getText()+" "+Constantes.SUR_COMMANDE_N+nCommandeField.getText().toUpperCase()+"_"+Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+livraisonCombo.getSelectionModel().getSelectedItem());
               detailCompte.setMontantDebit(BigDecimal.ZERO);
               detailCompte.setMontantCredit(BigDecimal.ZERO);
               detailCompte.setClientMP(commandeTMP.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
               detailCompteDAO.add(detailCompte);  
                  }
//_______________________________________________________________________________________________________________________________________                              

                       else if(radioUsine.isSelected()==true){
                       
            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT=(detailCommande.getPrixUnitaire()).multiply(detailCommande.getQuantiteLivree()).setScale(2,RoundingMode.FLOOR);
                
            montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
             
            montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);

                            
                            
                            
                            
            DetailCompte detailCompte = new DetailCompte();
              
            Commande commandeTMP = commandeDAO.findCommandeByNumCommande(nCommandeField.getText(), Constantes.ETAT_COMMANDE_SUPPRIMER); 
              
            detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
              
            
               
               
               detailCompte.setDateOperation(new Date());
               detailCompte.setDateBonLivraison(dateCreation);
               detailCompte.setCode(nRetourManqueField.getText());
               detailCompte.setDesignation(Constantes.RETOUR_N+nRetourManqueField.getText()+" "+Constantes.SUR_COMMANDE_N+nCommandeField.getText().toUpperCase()+"_"+Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+livraisonCombo.getSelectionModel().getSelectedItem());
               detailCompte.setMontantDebit(BigDecimal.ZERO);
               detailCompte.setMontantCredit(montantTTC.multiply(new BigDecimal(-1)));
               detailCompte.setClientMP(commandeTMP.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
               detailCompteDAO.add(detailCompte);            
                        }

                      
               
                       bonRetour.setMontantTotal(montantTotal);
                       bonRetourDAO.edit(bonRetour);
                      
                      
               
                       
    
                  }else if (radioManque.isSelected()){
                      
                      if (radioReception.isSelected()==true || radioUsine.isSelected()== true || radioFour.isSelected()== true){
                    BigDecimal montantTotal = BigDecimal.ZERO; 

  String  numCommande = nCommandeField.getText();
                commande = commandeDAO.findCommandeByNumCommande(numCommande, Constantes.ETAT_COMMANDE_SUPPRIMER);
                
                 fournisseur = commande.getFourisseur();
                 
           BonRetour bonRetour= new BonRetour();

              LocalDate localDate=dateCreation.getValue();
            
                Date dateCreation=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
              
              bonRetour.setnCommande(numCommande.toUpperCase());
              bonRetour.setnRetour(nRetourManqueField.getText());
              bonRetour.setnLivraison(livraisonCombo.getSelectionModel().getSelectedItem());
              bonRetour.setFournisseur(fournisseur.getNom());
              bonRetour.setAction(Boolean.FALSE);
              bonRetour.setDateCreation(dateCreation);
              
               if (radioOui.isSelected()==true){
                   bonRetour.setEnStock(Constantes.EN_STOCK_OUI);
                 }else if(radioNon.isSelected()==true){
                   bonRetour.setEnStock(Constantes.EN_STOCK_NON);
                 }
              
               if (radioReception.isSelected()==true){
                   bonRetour.setReceptionOrUsine(Constantes.RECEPTION_TYPE);
                 }else if(radioUsine.isSelected()==true || radioFour.isSelected()== true){
                   bonRetour.setReceptionOrUsine(Constantes.USINE_TYPE);
                 }
               
              bonRetour.setUtilisateurCreation(nav.getUtilisateur());
              bonRetour.setType(Constantes.MANQUE);
              bonRetour.setEtat(Constantes.RETOUR_EN_ATT_REGLEMENT);
              bonRetour.setMontantTotal(montantTotal);
                       bonRetourDAO.add(bonRetour);

              
                      for( int rows = 0;rows<listeDetailBonRetour.size() ;rows++ ){


              DetailCommande detailCommande = listeDetailBonRetour.get(rows);

                    
                 if(!detailCommande.getQuantiteLivree().equals(BigDecimal.ZERO.setScale(2))){

                     
                 DetailBonRetour detailBonRetour = new DetailBonRetour();

                     
//____________________________________________________Calcule montant______________________________________________________________

BigDecimal pu = BigDecimal.ZERO;
BigDecimal montant = BigDecimal.ZERO; 

            pu = detailCommande.getPrixUnitaire();

             
            montant = pu.multiply(detailCommande.getQuantiteLivree()).setScale(2,RoundingMode.FLOOR);

            
            montantTotal = montantTotal.add(montant).setScale(2,RoundingMode.FLOOR);

//______________________________________________________ Detail Bon Retour ___________________________________________________________________________

        MatierePremier matierePremier = detailCommande.getMatierePremier();

        
                detailBonRetour.setBonRetour(bonRetour);
                detailBonRetour.setQuantiteRetour(detailCommande.getQuantiteLivree());
                detailBonRetour.setPrixUnitaire(detailCommande.getPrixUnitaire());
                detailBonRetour.setUtilisateurCreation(nav.getUtilisateur()); 
                detailBonRetour.setMontant(montant);
                detailBonRetour.setMatierePremier(matierePremier);
               
                detailBonRetourDAO.add(detailBonRetour);
                
                 }
                 
                 
                 
                      }
                  if(radioReception.isSelected()==true){ 
                 
                  DetailCompte detailCompte = new DetailCompte();
              
             Commande commandeTMP = commandeDAO.findCommandeByNumCommande(nCommandeField.getText(), Constantes.ETAT_COMMANDE_SUPPRIMER); 
              
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
              
            
               
               
               detailCompte.setDateOperation(new Date());
               detailCompte.setDateBonLivraison(dateCreation);
               detailCompte.setCode(nRetourManqueField.getText());
               detailCompte.setDesignation(Constantes.MANQUE_N+nRetourManqueField.getText()+" "+Constantes.SUR_COMMANDE_N+nCommandeField.getText().toUpperCase()+"_"+Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+livraisonCombo.getSelectionModel().getSelectedItem());
               detailCompte.setMontantDebit(BigDecimal.ZERO);
               detailCompte.setMontantCredit(BigDecimal.ZERO);
               detailCompte.setClientMP(commandeTMP.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
               detailCompteDAO.add(detailCompte);  
                  }
//_______________________________________________________________________________________________________________________________________                              

                       else if(radioUsine.isSelected()==true){
                       
            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT=(detailCommande.getPrixUnitaire()).multiply(detailCommande.getQuantiteLivree()).setScale(2,RoundingMode.FLOOR);
            

           
              montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  

             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
                            
                    
                            
                            
             DetailCompte detailCompte = new DetailCompte();
              
             Commande commandeTMP = commandeDAO.findCommandeByNumCommande(nCommandeField.getText(), Constantes.ETAT_COMMANDE_SUPPRIMER); 
              
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
              
            
               
               
               detailCompte.setDateOperation(new Date());
               detailCompte.setDateBonLivraison(dateCreation);
               detailCompte.setCode(nRetourManqueField.getText());
               detailCompte.setDesignation(Constantes.MANQUE_N+nRetourManqueField.getText()+" "+Constantes.SUR_COMMANDE_N+nCommandeField.getText().toUpperCase()+"_"+Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+livraisonCombo.getSelectionModel().getSelectedItem());
               detailCompte.setMontantDebit(BigDecimal.ZERO);
               detailCompte.setMontantCredit(montantTTC.multiply(new BigDecimal(-1)));
               detailCompte.setClientMP(commandeTMP.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
               detailCompteDAO.add(detailCompte);            
                        

                  

                  }else if(radioFour.isSelected()== true) {
                      
                      MatierePremier matierePremier = detailCommande.getMatierePremier();
                      
//                       String  numCommande = nCommandeField.getText();
                  
                          DetailManqueFour detailManqueFour = new DetailManqueFour();
                          
                      detailManqueFour.setEcartQuantite(BigDecimal.ZERO);
                      detailManqueFour.setNumCommande(numCommande.toUpperCase());
                      detailManqueFour.setQuantite(detailCommande.getQuantiteLivree());
                      detailManqueFour.setMatierePremier(matierePremier);
                      detailManqueFour.setDateSaisie(dateCreation);
                      detailManqueFour.setNumBonLiv(livraisonCombo.getSelectionModel().getSelectedItem());
                      detailManqueFour.setQuantiteRecu(BigDecimal.ZERO);
                      detailManqueFour.setNumRetour(nRetourManqueField.getText());
                      detailManqueFour.setUtilisateurCreation(nav.getUtilisateur());
                      
                      detailManqueFourDAO.add(detailManqueFour);
                      
//=================================================================  Detail Compte  ==============================================================================================================================================================================================================================================================================================================================================================================


            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT=(detailCommande.getPrixUnitaire()).multiply(detailCommande.getQuantiteLivree()).setScale(2,RoundingMode.FLOOR);
            

           
              montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  

             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
                            
                    
                            
                            
             DetailCompte detailCompte = new DetailCompte();
              
             Commande commandeTMP = commandeDAO.findCommandeByNumCommande(nCommandeField.getText(), Constantes.ETAT_COMMANDE_SUPPRIMER); 
              
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
              
            
               
               
               detailCompte.setDateOperation(new Date());
               detailCompte.setDateBonLivraison(dateCreation);
               detailCompte.setCode(nRetourManqueField.getText());
               detailCompte.setDesignation(Constantes.MANQUE_N+nRetourManqueField.getText()+" "+Constantes.SUR_COMMANDE_N+nCommandeField.getText().toUpperCase()+"_"+Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+livraisonCombo.getSelectionModel().getSelectedItem());
               detailCompte.setMontantDebit(BigDecimal.ZERO);
               detailCompte.setMontantCredit(montantTTC.multiply(new BigDecimal(-1)));
               detailCompte.setClientMP(commandeTMP.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
               detailCompteDAO.add(detailCompte);     
                      
                      
                      
                      
                      
                  }
                  
                      
               
                       bonRetour.setMontantTotal(montantTotal);
                       bonRetourDAO.edit(bonRetour);
                  
                  }
                  } 
                nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.TRAITEMENT_BIEN_ENREGESTRE);
            }
         }
    }


    @FXML
    private void imprimerOnAction(ActionEvent event ) throws ParseException{
     try {
              
         String bon = "";
         if ( radioBonRetour.isSelected()==false && radioManque.isSelected()==false)
         {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.VERIFIER_BON_RETOUR_BON_GRATUITE_MANQUE);
         }else {
             
         if(radioBonRetour.isSelected()== true) {
             
           bon =  Constantes.BON_RETOUR;
           
         }else {
             
           bon = Constantes.MANQUE;
           
         }
         
         }
             
         listeDetailBonRetourTMP.clear();
         
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SuiviRetourController.class.getResource(nav.getiReportBonRetour()));

              LocalDate localDate=dateCreation.getValue();
            
                Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
                Commande commande = commandeDAO.findCommandeByNumCommande(nCommandeField.getText(), Constantes.ETAT_COMMANDE_SUPPRIMER);
            
            para.put("Bon",bon);
            para.put("Fournisseur",commande.getFourisseur().getNom());
            para.put("NumCommande",nCommandeField.getText());
            para.put("NumRetour",nRetourManqueField.getText());
            para.put("Ville",commande.getFourisseur().getVille().getLibelle());
            para.put("NumLivraison",livraisonCombo.getSelectionModel().getSelectedItem());
            para.put("DateLivraison",dateSaisie);
            
            for(int i=0;i<listeDetailBonRetour.size();i++){
            
                DetailCommande detailCommande = listeDetailBonRetour.get(i);
                if (!detailCommande.getQuantiteLivree().setScale(2).equals(BigDecimal.ZERO.setScale(2)))
                {
                listeDetailBonRetourTMP.add(detailCommande);
                }
            }

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailBonRetourTMP));
               JasperViewer.viewReport(jp, false);
             
                
               for (int i =0;i<listeDetailBonRetour.size();i++)
               {            
                    if (!detailCommande.getQuantiteLivree().setScale(2).equals(BigDecimal.ZERO.setScale(2)))
               {
                    
                detailCommande.setQuantiteLivree(BigDecimal.ZERO.setScale(2));
                listeDetailBonRetour.set(i, detailCommande);
                detailCommandeDAO.edit(detailCommande);
                }
                   
               } 
   
               
               
               } catch (JRException ex) {
            Logger.getLogger(SuiviRetourController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     if (radioBonRetour.isSelected()){
           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RETOUR_CODE);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
                    IncrementationRtr();
                       
                   refresh();
                   
     }else if (radioManque.isSelected()){
              Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.MANQUE_CODE);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
                    IncrementationMnq();
                       
                   refresh();
     
     }else{
      refresh();
     }
        } 
    

    @FXML
    private void chargeNlivraisionTableKeyPressed(KeyEvent event) {
        
         tableDetailBonRetour.setEditable(true);
        tableDetailBonRetour.getItems().clear();
        livraisonCombo.getItems().clear();
        
        if (event.getCode().equals(KeyCode.ENTER))
            {
              listeDetailBonRetour.clear();
                
                
             String  numCommande = nCommandeField.getText();
           Commande commandeTMP = commandeDAO.findCommandeByNumCommande(numCommande , Constantes.ETAT_COMMANDE_SUPPRIMER); 
           
           if(commandeTMP!=null){
               
               
                listeDetailBonRetour.addAll(commandeTMP.getDetailCommandes());
                tableDetailBonRetour.setItems(listeDetailBonRetour);
                setColumnPropertiesDetailCommande();
                
              List<DetailBonLivraison> listDetailBonLivraison=new ArrayList<>();
              listDetailBonLivraison = detailBonLivraisonDAO.findCommandeByDetailBonLivraison(commandeTMP.getnCommande());
  
            listDetailBonLivraison.stream().map((detailBonLivraison) -> {
            livraisonCombo.getItems().addAll(detailBonLivraison.getLivraisonFour().toString());
            return detailBonLivraison;
        }).forEachOrdered((detailBonLivraison) -> {
            mapDetailBonLivraison.put(detailBonLivraison.getNumCommande(), detailBonLivraison);
        });          
           }else{
           nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.VERIFIER_NUM_COMMANDE);
           }
            }
   
    }




    @FXML
    private void radioBonRetourOnAction(ActionEvent event) {

//           RecuPane.setVisible(false);
             radioReception.setSelected(false);
             radioUsine.setSelected(false);
             radioOui.setSelected(false);
             radioNon.setSelected(false);
             recuCheck.setSelected(false);
             radioFour.setSelected(false);
             
             radioFour.setDisable(true);
             recuCheck.setDisable(true);
             radioReception.setDisable(false);
             radioUsine.setDisable(false);
             radioOui.setDisable(false);
             radioNon.setDisable(false);
             
             nRetourManqueField.clear();
             
                  IncrementationRtr();
             
    }

    @FXML
    private void livraisonComboOnAction(ActionEvent event) {
        
        listeDetailBonRetour.clear();
        
          String  numCommande = nCommandeField.getText();
          
           Commande commandeTMP = commandeDAO.findCommandeByNumCommande(numCommande , Constantes.ETAT_COMMANDE_SUPPRIMER); 
           
           if(commandeTMP!=null){
               
                listeDetailBonRetour.addAll(commandeTMP.getDetailCommandes());
                tableDetailBonRetour.setItems(listeDetailBonRetour);
                setColumnPropertiesDetailCommande();
        
           }
        
      String numLiv =  livraisonCombo.getSelectionModel().getSelectedItem();

   Boolean valeur = false;
for (int i =0;i<listeDetailBonRetour.size();i++){
  
 valeur = false;
DetailCommande detailCommande = listeDetailBonRetour.get(i);

for (int j =0;j<detailCommande.getDetailReception().size();j++){
    
    if(detailCommande.getDetailReception().get(j).getLivraisonFour().equals(numLiv)){
        
    valeur= true;
    
    }
}

   if (valeur == false)
   {
   listeDetailBonRetour.remove(i);
   }

}
                tableDetailBonRetour.setItems(listeDetailBonRetour);
                setColumnPropertiesDetailCommande();
    
    }

 

    @FXML
    private void radioManqueOnAction(ActionEvent event) {

          radioReception.setSelected(false);
          radioUsine.setSelected(false);
          radioOui.setSelected(false);
          radioNon.setSelected(false);
          recuCheck.setSelected(false);
          radioFour.setSelected(false);
          
          radioFour.setDisable(false);
          recuCheck.setDisable(true);
          radioReception.setDisable(false);
          radioUsine.setDisable(false);
          radioOui.setDisable(true);
          radioNon.setDisable(true);
          
          nRetourManqueField.clear();
        
          IncrementationMnq ();
          
    }

      void refresh(){
    
        nCommandeField.clear();
        livraisonCombo.getSelectionModel().clearSelection();
        dateCreation.setValue(null);
        nRetourManqueField.clear();
        radioBonRetour.setSelected(false);
        radioManque.setSelected(false);
        recuCheck.setSelected(false);
        tableDetailBonRetour.getItems().clear();
        radioReception.setDisable(true);
        radioReception.setSelected(false);
        radioFour.setDisable(true);
        radioFour.setSelected(false);
        radioUsine.setDisable(true);
        radioUsine.setSelected(false);
        radioOui.setDisable(true);
        radioOui.setSelected(false);
        radioNon.setDisable(true);
        radioNon.setSelected(false);
        recuCheck.setDisable(true);
    }
 
    
    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        
        refresh();
    }

 

    @FXML
    private void radioBonReceptionOnAction(ActionEvent event) {
    }

    @FXML
    private void radioUsineOnAction(ActionEvent event) {
    }

    @FXML
    private void radioFourOnAction(ActionEvent event) {
    }

    @FXML
    private void radioOuiOnAction(ActionEvent event) {
    }

    @FXML
    private void radioNonOnAction(ActionEvent event) {
    }


 


 
}

  
 

    