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
import dao.Entity.Fournisseur;
import dao.Entity.Reglement;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.BonRetourDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailBonRetourDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.FournisseurDAO;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.BonRetourDAOImpl;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailBonRetourDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ConsultationBonRetourGratuite implements Initializable {

    @FXML
    private TableView<BonRetour> tableBonRtr;
    @FXML
    private TableColumn<BonRetour, String> nRtrGrtMnqColumn;
    @FXML
    private TableColumn<BonRetour, String> nComColumn;
    @FXML
    private TableColumn<BonRetour, String> nLivColumn;
    @FXML
    private TableColumn<BonRetour, String> fourColumn;
    @FXML
    private TableColumn<BonRetour, BigDecimal> montFactColumn;
    @FXML
    private TableColumn<BonRetour, String> typeBonColumn;
    @FXML
    private TableColumn<BonRetour, String> enStockColumn;
    @FXML
    private TableColumn<BonRetour, String> etatColumn;
    @FXML
    private TableColumn<BonRetour, Boolean> actionColumn;
    @FXML
    private TableColumn<BonRetour, String> recepUsineColumn;
    
    @FXML
    private ComboBox<String> bonRetGraMnqCombo;
    
    @FXML
    private TableView<DetailBonRetour> tableDetailbonRtr;
    @FXML
    private TableColumn<DetailBonRetour, String> codeColumn;
    @FXML
    private TableColumn<DetailBonRetour, String> designationColumn;
    @FXML
    private TableColumn<DetailBonRetour, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailBonRetour, BigDecimal> prixColumn;
    @FXML
    private TableColumn<DetailBonRetour, BigDecimal> montantColumn;
    
    
    
    
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    @FXML
    private RadioButton noPaiementRadio;
    @FXML
    private RadioButton paiementRadio;
    @FXML
    private Button btnPayeBon;
    @FXML
    private ToggleGroup paiementRd;
    @FXML
    private TextField prixTxt;
    @FXML
    private TextField qteTxt;
    @FXML
    private TextField totalTxt;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> respUsinCombo;
    @FXML
    private ComboBox<String> stockCombo;
    @FXML
    private ComboBox<String> etatCombo;
    @FXML
    private TextField montantTotalField;
    @FXML
    private Label monHT;
    @FXML
    private Label monTVA;
    @FXML
    private Label monTTC;
    
     BigDecimal MHT = BigDecimal.ZERO ;
     BigDecimal MTVA = BigDecimal.ZERO ;
     BigDecimal MTTC = BigDecimal.ZERO ;
    
    /**
     * Initializes the controller class.
     */

    private final ObservableList<DetailBonRetour> listeDetailBonRetours =FXCollections.observableArrayList();
    
    private final ObservableList<BonRetour> listeBonRetours =FXCollections.observableArrayList();
     
    ObservableList<String> bonRetourGratuiteListe =FXCollections.observableArrayList(Constantes.RETOUR,Constantes.MANQUE);
    
    ObservableList<String> recpUsin =FXCollections.observableArrayList(Constantes.RECEPTION_TYPE,Constantes.USINE_TYPE);
    
    ObservableList<String> enStock =FXCollections.observableArrayList(Constantes.EN_STOCK_OUI,Constantes.EN_STOCK_NON);
    
    ObservableList<String> etat =FXCollections.observableArrayList(Constantes.ETAT_PAYEE,Constantes.RETOUR_EN_ATT_REGLEMENT,Constantes.ETAT_ANNULE);
   
    BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
    
    DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
    
    BonRetourDAO bonRetourDAO = new BonRetourDAOImpl();
    
    CommandeDAO commandeDAO = new CommandeDAOImpl();
    
    DetailBonRetourDAO detailBonRetourDAO = new DetailBonRetourDAOImpl();
     
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
     
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    
    Fournisseur fournisseur =new Fournisseur();
    
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    
    navigation nav = new navigation();  
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        fourCombo.setDisable(true);
        respUsinCombo.setDisable(true);
        stockCombo.setDisable(true);
        etatCombo.setDisable(true);
        
          bonRetGraMnqCombo.setItems(bonRetourGratuiteListe);
          respUsinCombo.setItems(recpUsin);
          stockCombo.setItems(enStock);
          etatCombo.setItems(etat);
    
                  List<Fournisseur> listFournisseur=fournisseurDAO.findAll();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
          
        setColumnProperties();
        loadDetail();

    }    

     void loadDetail() {
     
         listeBonRetours.clear();
         
     listeBonRetours.addAll(bonRetourDAO.findTypeByRechercheBonRetour(Constantes.RETOUR, Constantes.MANQUE));

        tableBonRtr.setItems(listeBonRetours); 
     }
    
    void setDetailColumnProperties() {

      
      codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
      
       designationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });
        
       qteColumn.setCellValueFactory(new PropertyValueFactory<DetailBonRetour, BigDecimal>("quantiteRetour"));
        
       prixColumn.setCellValueFactory(new PropertyValueFactory<DetailBonRetour, BigDecimal>("prixUnitaire"));
        
       montantColumn.setCellValueFactory(new PropertyValueFactory<DetailBonRetour, BigDecimal>("montant"));

    }
    

     void setColumnProperties() {

      
      nRtrGrtMnqColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumRetour());
                }
             });
      
       nComColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumCommande());
                }
             });
        
        nLivColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getnLivraison());
                }
             });
       
        fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFournisseur());
                }
             });
       
        montFactColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<BonRetour, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMontantTotal());
                }
             });
        
        typeBonColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getType());
                }
             });
         
        enStockColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getEnStock());
                }
             }); 
        
        recepUsineColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getReceptionOrUsine());
                }
             }); 
        
          etatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getEtat());
                }
             });
     
        actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          BonRetour cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionColumn.setEditable(true);
      
          
          tableBonRtr.setEditable(true);
    }
    

    @FXML
    private void refrechOnAction(ActionEvent event) {
        
        listeBonRetours.clear();
        listeDetailBonRetours.clear();
        tableDetailbonRtr.getItems().clear();
        tableBonRtr.getItems().clear();
        bonRetGraMnqCombo.getSelectionModel().select(-1);
        stockCombo.getSelectionModel().select(-1);
        respUsinCombo.getSelectionModel().select(-1);
        fourCombo.getSelectionModel().select(-1);
        etatCombo.getSelectionModel().select(-1);
        
        
    }

    @FXML
    private void recherchMouseClicked(MouseEvent event) {
        
        listeBonRetours.clear();
        listeDetailBonRetours.clear();
        tableDetailbonRtr.getItems().clear();
        tableBonRtr.getItems().clear();
        
       String bonRetGra = bonRetGraMnqCombo.getSelectionModel().getSelectedItem();
       String respUsin = respUsinCombo.getSelectionModel().getSelectedItem();
       String stock = stockCombo.getSelectionModel().getSelectedItem();
       Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
       String etat = etatCombo.getSelectionModel().getSelectedItem();
        
             if (bonRetGraMnqCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex()==-1 &&
                 fourCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 etatCombo.getSelectionModel().getSelectedIndex()== -1)
        {
        nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.VERIFIER_BON_GRATUITE_BON_ROUTEUR);
        }
         
         
               else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex()!= -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex()==-1 &&
                 fourCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
               setColumnProperties();
               
                listeBonRetours.addAll(bonRetourDAO.findBonRetour(bonRetGra));

           tableBonRtr.setItems(listeBonRetours);
             
             }
             
         else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex()!= -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex()==-1 &&
                 fourCombo.getSelectionModel().getSelectedIndex()!= -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
               setColumnProperties(); 
               
                listeBonRetours.addAll(bonRetourDAO.findBonRetourByFour(fournisseur.getNom(), bonRetGra));

           tableBonRtr.setItems(listeBonRetours);
             
             }
             
                  else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex()!= -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex()==-1 &&
                 fourCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
               setColumnProperties(); 
               
                listeBonRetours.addAll(bonRetourDAO.findBonRetourByEtatAndRetMnq(bonRetGra, etat));

           tableBonRtr.setItems(listeBonRetours);
             
             }
         
         
         
                      else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex()!= -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex()!= -1 &&
                 fourCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
               setColumnProperties(); 
               
                listeBonRetours.addAll(bonRetourDAO.findBonRetourByRecUsine(respUsin, bonRetGra));

           tableBonRtr.setItems(listeBonRetours);
             
             }
             
                       else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 fourCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
               setColumnProperties(); 
               
                listeBonRetours.addAll(bonRetourDAO.findBonRetourByStock(stock, bonRetGra));

           tableBonRtr.setItems(listeBonRetours);
             
             }
             
            else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 fourCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 etatCombo.getSelectionModel().getSelectedIndex()== -1){

               setColumnProperties();
         
       
        
           listeBonRetours.addAll(bonRetourDAO.findBonRetourByFourAndRec(respUsin,fournisseur.getNom(),bonRetGra));

           tableBonRtr.setItems(listeBonRetours);

    }
            
            
            else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 fourCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 etatCombo.getSelectionModel().getSelectedIndex()!= -1){

               setColumnProperties();
         
       
        
           listeBonRetours.addAll(bonRetourDAO.findBonRetourByFourAndRecAndEtat(respUsin,fournisseur.getNom(),bonRetGra, etat));

           tableBonRtr.setItems(listeBonRetours);

    }
            
            else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 fourCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex() != -1 &&
                    etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
                             setColumnProperties();
        
           listeBonRetours.addAll(bonRetourDAO.findBonRetourByFourAndStockAndRec(respUsin,stock,fournisseur.getNom(),bonRetGra));

           tableBonRtr.setItems(listeBonRetours);

             }
            
            else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 fourCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex() != -1 &&
                    etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                             setColumnProperties();
        
           listeBonRetours.addAll(bonRetourDAO.findBonRetourByFourAndStockAndRecAndEtat(respUsin,stock,fournisseur.getNom(),bonRetGra,etat));

           tableBonRtr.setItems(listeBonRetours);

             }
            
            
                         else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 fourCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex() == -1 &&
                                 etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
                             setColumnProperties();

        
           listeBonRetours.addAll(bonRetourDAO.findByFourAndRec(respUsin,fournisseur.getNom()));

           tableBonRtr.setItems(listeBonRetours);

             
             }

                  else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 fourCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex() == -1 &&
                          etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
                             setColumnProperties();

        
           listeBonRetours.addAll(bonRetourDAO.findByFour(fournisseur.getNom()));

           tableBonRtr.setItems(listeBonRetours);

             
             }
        

                   else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 fourCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex() == -1 &&
                          etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                             setColumnProperties();

        
           listeBonRetours.addAll(bonRetourDAO.findEtatByRechercheBonRetour(etat));

           tableBonRtr.setItems(listeBonRetours);

             
             
        }
             
                   else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 fourCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                             setColumnProperties();

        
           listeBonRetours.addAll(bonRetourDAO.findBonRetourByEtatAndFour(fournisseur.getNom(), etat));

           tableBonRtr.setItems(listeBonRetours);

             
             
        }
             
                 else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 fourCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                             setColumnProperties();

        
           listeBonRetours.addAll(bonRetourDAO.findBonRetourByEtatAndResUsi(respUsin, etat));

           tableBonRtr.setItems(listeBonRetours);

             
             
        }
             
                 else if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 fourCombo.getSelectionModel().getSelectedIndex() != -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex() == -1 &&
                 etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                             setColumnProperties();

        
           listeBonRetours.addAll(bonRetourDAO.findBonRetourByEtatAndResUsiAndFour(respUsin, etat,fournisseur.getNom()));

           tableBonRtr.setItems(listeBonRetours);

             
             
        }
    }

    @FXML
    private void payeBonOnAction(ActionEvent event) {
        

             BonRetour bonRetour = tableBonRtr.getSelectionModel().getSelectedItem(); 
             
             if (bonRetour.getType().equals(Constantes.RETOUR) && bonRetour.getReceptionOrUsine().equals(Constantes.RECEPTION_TYPE)){
                 
                if (paiementRadio.isSelected()==true){
                       
 //___________________________________________________________________________________ Bon Livraision __________________________________________________________________________________________

            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT= bonRetour.getMontantTotal();
            
             System.out.println("montantHT"+montantHT);  
           
              montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
             System.out.println("montantTTC"+montantTTC);

   Commande commandeTMP = commandeDAO.findCommandeByNumCommande(bonRetour.getNumCommande(), Constantes.ETAT_COMMANDE_SUPPRIMER); 

    BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(bonRetour.getFournisseur());
          bonLivraison.setMontant(montantHT);
          bonLivraison.setNumCommande(bonRetour.getNumCommande());
          bonLivraison.setLivraisonFour(bonRetour.getNumRetour());
          bonLivraison.setEtat(Constantes.ETAT_NON_REGLE);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setClient(commandeTMP.getClientMP().getNom());
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_RTR);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(new Date());
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
          
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
   List<DetailBonRetour> listDetailBonRetour = detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId());
   
           for (int i = 0; i < listDetailBonRetour.size(); i++) {
                
                DetailBonRetour detailBonRetour = listDetailBonRetour.get(i);
                
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(detailBonRetour.getBonRetour().getNumRetour());
                detailBonLivraison.setMontant(detailBonRetour.getMontant());
                detailBonLivraison.setPrix(detailBonRetour.getPrixUnitaire());
                detailBonLivraison.setQuantite(detailBonRetour.getQuantiteRetour());
                detailBonLivraison.setNumCommande(detailBonRetour.getBonRetour().getNumCommande());
                detailBonLivraison.setMatierePremier(detailBonRetour.getMatierePremier());
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);
                
//___________________________________________________________________________________ Detail Compte __________________________________________________________________________________________       
           
             DetailCompte detailCompte = new DetailCompte();
              
              
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
              
   
               
               
               detailCompte.setDateOperation(new Date());
               detailCompte.setDesignation(Constantes.VALIDATION_SUR_BON_RETOUR_N+" "+bonRetour.getNumRetour().toUpperCase());
               
               detailCompte.setDateBonLivraison(bonRetour.getDateCreation());
               detailCompte.setCode(bonRetour.getNumRetour());
                detailCompte.setMontantDebit(BigDecimal.ZERO);

               detailCompte.setMontantCredit(montantTTC);
               detailCompte.setClientMP(commandeTMP.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
               detailCompteDAO.add(detailCompte);            
            
            }
           
                 bonRetour.setEtat(Constantes.RETOUR_EN_ATT_REGLEMENT);
                 bonRetourDAO.edit(bonRetour);
                 
                 nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
           
                }else if (noPaiementRadio.isSelected()==true){
            
            BigDecimal montantHT  = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT= bonRetour.getMontantTotal();
            
             System.out.println("montantHT"+montantHT);  
           
              montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
             System.out.println("montantTTC"+montantTTC);

   Commande commandeTMP = commandeDAO.findCommandeByNumCommande(bonRetour.getNumCommande(), Constantes.ETAT_COMMANDE_SUPPRIMER); 
           
          BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(bonRetour.getFournisseur());
          bonLivraison.setMontant(BigDecimal.ZERO);
          bonLivraison.setNumCommande(bonRetour.getNumCommande());
          bonLivraison.setLivraisonFour(bonRetour.getNumRetour());
          bonLivraison.setEtat(Constantes.ETAT_NON_REGLE);
          bonLivraison.setMontantTVA(BigDecimal.ZERO);
          bonLivraison.setMontantTTC(BigDecimal.ZERO);
          bonLivraison.setClient(commandeTMP.getClientMP().getNom());
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_RTR);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(new Date());
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
          
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
   List<DetailBonRetour> listDetailBonRetour = detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId());
   
           for (int i = 0; i < listDetailBonRetour.size(); i++) {
                
                DetailBonRetour detailBonRetour = listDetailBonRetour.get(i);
                
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(detailBonRetour.getBonRetour().getNumRetour());
                detailBonLivraison.setMontant(detailBonRetour.getMontant());
                detailBonLivraison.setPrix(detailBonRetour.getPrixUnitaire());
                detailBonLivraison.setQuantite(detailBonRetour.getQuantiteRetour());
                detailBonLivraison.setNumCommande(detailBonRetour.getBonRetour().getNumCommande());
                detailBonLivraison.setMatierePremier(detailBonRetour.getMatierePremier());
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);
                
              }    
           
         bonRetour.setEtat(Constantes.ETAT_ANNULE);
         bonRetourDAO.edit(bonRetour);
           
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
            }
                 
        }else if (bonRetour.getType().equals(Constantes.RETOUR) && bonRetour.getReceptionOrUsine().equals(Constantes.USINE_TYPE)){
     
             if (paiementRadio.isSelected()==true){
                 
               //___________________________________________________________________________________ Detail Compte __________________________________________________________________________________________       

            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT= bonRetour.getMontantTotal();
            
             System.out.println("montantHT"+montantHT);  
           
              montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
             System.out.println("montantTTC"+montantTTC);

   Commande commandeTMP = commandeDAO.findCommandeByNumCommande(bonRetour.getNumCommande(), Constantes.ETAT_COMMANDE_SUPPRIMER); 
           
          BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(bonRetour.getFournisseur());
          bonLivraison.setMontant(BigDecimal.ZERO);
          bonLivraison.setNumCommande(bonRetour.getNumCommande());
          bonLivraison.setLivraisonFour(bonRetour.getNumRetour());
          bonLivraison.setEtat(Constantes.ETAT_NON_REGLE);
          bonLivraison.setMontantTVA(BigDecimal.ZERO);
          bonLivraison.setMontantTTC(BigDecimal.ZERO);
          bonLivraison.setClient(commandeTMP.getClientMP().getNom());
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_RTR);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(new Date());
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
          
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
   List<DetailBonRetour> listDetailBonRetour = detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId());
   
           for (int i = 0; i < listDetailBonRetour.size(); i++) {
                
                DetailBonRetour detailBonRetour = listDetailBonRetour.get(i);
                
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(detailBonRetour.getBonRetour().getNumRetour());
                detailBonLivraison.setMontant(detailBonRetour.getMontant());
                detailBonLivraison.setPrix(detailBonRetour.getPrixUnitaire());
                detailBonLivraison.setQuantite(detailBonRetour.getQuantiteRetour());
                detailBonLivraison.setNumCommande(detailBonRetour.getBonRetour().getNumCommande());
                detailBonLivraison.setMatierePremier(detailBonRetour.getMatierePremier());
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);
                
           }
             DetailCompte detailCompte = new DetailCompte();
              
              
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
 
               detailCompte.setDateOperation(new Date());
               detailCompte.setDesignation(Constantes.VALIDATION_SUR_BON_RETOUR_N+" "+bonRetour.getNumRetour().toUpperCase());

                detailCompte.setMontantDebit(BigDecimal.ZERO);
                detailCompte.setDateBonLivraison(bonRetour.getDateCreation());
                detailCompte.setCode(bonRetour.getNumRetour());
               detailCompte.setMontantCredit(montantTTC);
               detailCompte.setClientMP(commandeTMP.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
               detailCompteDAO.add(detailCompte); 
            
            
       
         bonRetour.setEtat(Constantes.RETOUR_EN_ATT_REGLEMENT);
         bonRetourDAO.edit(bonRetour);
           
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
             
        } else if (noPaiementRadio.isSelected()==true){
            
//___________________________________________________________________________________ Bon Livraision __________________________________________________________________________________________

            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT= bonRetour.getMontantTotal();
            
             System.out.println("montantHT"+montantHT);  
           
              montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
             System.out.println("montantTTC"+montantTTC);

   Commande commandeTMP = commandeDAO.findCommandeByNumCommande(bonRetour.getNumCommande(), Constantes.ETAT_COMMANDE_SUPPRIMER); 

    BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(bonRetour.getFournisseur());
          bonLivraison.setMontant(montantHT);
          bonLivraison.setNumCommande(bonRetour.getNumCommande());
          bonLivraison.setLivraisonFour(bonRetour.getNumRetour());
          bonLivraison.setEtat(Constantes.ETAT_NON_PAIEMENT);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setClient(commandeTMP.getClientMP().getNom());
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_RTR);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(new Date());
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
          
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
   List<DetailBonRetour> listDetailBonRetour = detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId());
   
           for (int i = 0; i < listDetailBonRetour.size(); i++) {
                
                DetailBonRetour detailBonRetour = listDetailBonRetour.get(i);
                
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(detailBonRetour.getBonRetour().getNumRetour());
                detailBonLivraison.setMontant(detailBonRetour.getMontant());
                detailBonLivraison.setPrix(detailBonRetour.getPrixUnitaire());
                detailBonLivraison.setQuantite(detailBonRetour.getQuantiteRetour());
                detailBonLivraison.setNumCommande(detailBonRetour.getBonRetour().getNumCommande());
                detailBonLivraison.setMatierePremier(detailBonRetour.getMatierePremier());
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);
                
 
           }

         bonRetour.setEtat(Constantes.ETAT_ANNULE);
         bonRetourDAO.edit(bonRetour);
           
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
        }

    }else if (bonRetour.getType().equals(Constantes.MANQUE) && bonRetour.getReceptionOrUsine().equals(Constantes.RECEPTION_TYPE)){
                 
                if (paiementRadio.isSelected()==true){
                       
 //___________________________________________________________________________________ Bon Livraision __________________________________________________________________________________________

            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT= bonRetour.getMontantTotal();
            
             System.out.println("montantHT"+montantHT);  
           
              montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
             System.out.println("montantTTC"+montantTTC);

   Commande commandeTMP = commandeDAO.findCommandeByNumCommande(bonRetour.getNumCommande(), Constantes.ETAT_COMMANDE_SUPPRIMER); 

    BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(bonRetour.getFournisseur());
          bonLivraison.setMontant(montantHT);
          bonLivraison.setNumCommande(bonRetour.getNumCommande());
          bonLivraison.setLivraisonFour(bonRetour.getNumRetour());
          bonLivraison.setEtat(Constantes.ETAT_NON_REGLE);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setClient(commandeTMP.getClientMP().getNom());
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_MNQ);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(new Date());
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
          
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
   List<DetailBonRetour> listDetailBonRetour = detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId());
   
           for (int i = 0; i < listDetailBonRetour.size(); i++) {
                
                DetailBonRetour detailBonRetour = listDetailBonRetour.get(i);
                
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(detailBonRetour.getBonRetour().getNumRetour());
                detailBonLivraison.setMontant(detailBonRetour.getMontant());
                detailBonLivraison.setPrix(detailBonRetour.getPrixUnitaire());
                detailBonLivraison.setQuantite(detailBonRetour.getQuantiteRetour());
                detailBonLivraison.setNumCommande(detailBonRetour.getBonRetour().getNumCommande());
                detailBonLivraison.setMatierePremier(detailBonRetour.getMatierePremier());
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);
                
//___________________________________________________________________________________ Detail Compte __________________________________________________________________________________________       
           
             DetailCompte detailCompte = new DetailCompte();
              
              
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
              
   
               
               
               detailCompte.setDateOperation(new Date());
               detailCompte.setDesignation(Constantes.VALIDATION_SUR_BON_MANQUE_N+" "+bonRetour.getNumRetour().toUpperCase());
               

                detailCompte.setMontantDebit(BigDecimal.ZERO);
                detailCompte.setCode(bonRetour.getNumRetour());
               detailCompte.setDateBonLivraison(bonRetour.getDateCreation());
               detailCompte.setMontantCredit(montantTTC);
               detailCompte.setClientMP(commandeTMP.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
               detailCompteDAO.add(detailCompte);            
            
            }
           
                 bonRetour.setEtat(Constantes.RETOUR_EN_ATT_REGLEMENT);
                 bonRetourDAO.edit(bonRetour);
                 
                 nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
           
                }else if (noPaiementRadio.isSelected()==true){
            
            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT= bonRetour.getMontantTotal();
            
             System.out.println("montantHT"+montantHT);  
           
              montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
             System.out.println("montantTTC"+montantTTC);

   Commande commandeTMP = commandeDAO.findCommandeByNumCommande(bonRetour.getNumCommande(), Constantes.ETAT_COMMANDE_SUPPRIMER); 
           
          BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(bonRetour.getFournisseur());
          bonLivraison.setMontant(BigDecimal.ZERO);
          bonLivraison.setNumCommande(bonRetour.getNumCommande());
          bonLivraison.setLivraisonFour(bonRetour.getNumRetour());
          bonLivraison.setEtat(Constantes.ETAT_NON_REGLE);
          bonLivraison.setMontantTVA(BigDecimal.ZERO);
          bonLivraison.setMontantTTC(BigDecimal.ZERO);
          bonLivraison.setClient(commandeTMP.getClientMP().getNom());
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_MNQ);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(new Date());
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
          
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
   List<DetailBonRetour> listDetailBonRetour = detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId());
   
           for (int i = 0; i < listDetailBonRetour.size(); i++) {
                
                DetailBonRetour detailBonRetour = listDetailBonRetour.get(i);
                
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(detailBonRetour.getBonRetour().getNumRetour());
                detailBonLivraison.setMontant(detailBonRetour.getMontant());
                detailBonLivraison.setPrix(detailBonRetour.getPrixUnitaire());
                detailBonLivraison.setQuantite(detailBonRetour.getQuantiteRetour());
                detailBonLivraison.setNumCommande(detailBonRetour.getBonRetour().getNumCommande());
                detailBonLivraison.setMatierePremier(detailBonRetour.getMatierePremier());
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);
                
        
            }
           
              
         bonRetour.setEtat(Constantes.ETAT_ANNULE);
         bonRetourDAO.edit(bonRetour);
           
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
           
                }  
        }else if (bonRetour.getType().equals(Constantes.MANQUE) && bonRetour.getReceptionOrUsine().equals(Constantes.USINE_TYPE)){
     
             if (paiementRadio.isSelected()==true){
                 
//___________________________________________________________________________________ Detail Compte __________________________________________________________________________________________       

            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT= bonRetour.getMontantTotal();
            
             System.out.println("montantHT"+montantHT);  
           
              montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
             System.out.println("montantTTC"+montantTTC);

   Commande commandeTMP = commandeDAO.findCommandeByNumCommande(bonRetour.getNumCommande(), Constantes.ETAT_COMMANDE_SUPPRIMER); 
           
          BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(bonRetour.getFournisseur());
          bonLivraison.setMontant(BigDecimal.ZERO);
          bonLivraison.setNumCommande(bonRetour.getNumCommande());
          bonLivraison.setLivraisonFour(bonRetour.getNumRetour());
          bonLivraison.setEtat(Constantes.ETAT_NON_REGLE);
          bonLivraison.setMontantTVA(BigDecimal.ZERO);
          bonLivraison.setMontantTTC(BigDecimal.ZERO);
          bonLivraison.setClient(commandeTMP.getClientMP().getNom());
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_MNQ);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(new Date());
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
          
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
   List<DetailBonRetour> listDetailBonRetour = detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId());
   
           for (int i = 0; i < listDetailBonRetour.size(); i++) {
                
                DetailBonRetour detailBonRetour = listDetailBonRetour.get(i);
                
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(detailBonRetour.getBonRetour().getNumRetour());
                detailBonLivraison.setMontant(detailBonRetour.getMontant());
                detailBonLivraison.setPrix(detailBonRetour.getPrixUnitaire());
                detailBonLivraison.setQuantite(detailBonRetour.getQuantiteRetour());
                detailBonLivraison.setNumCommande(detailBonRetour.getBonRetour().getNumCommande());
                detailBonLivraison.setMatierePremier(detailBonRetour.getMatierePremier());
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);
                
           }
             DetailCompte detailCompte = new DetailCompte();
              
              
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
 
               detailCompte.setDateOperation(new Date());
               detailCompte.setDesignation(Constantes.VALIDATION_SUR_BON_MANQUE_N+" "+bonRetour.getNumRetour().toUpperCase());

                detailCompte.setMontantDebit(BigDecimal.ZERO);
                detailCompte.setDateBonLivraison(bonRetour.getDateCreation());
                detailCompte.setCode(bonRetour.getNumRetour());
               detailCompte.setMontantCredit(montantTTC);
               detailCompte.setClientMP(commandeTMP.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
               detailCompteDAO.add(detailCompte); 
            
            
   
         bonRetour.setEtat(Constantes.RETOUR_EN_ATT_REGLEMENT);
         bonRetourDAO.edit(bonRetour);
           
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
             
        } else if (noPaiementRadio.isSelected()==true){
            
//___________________________________________________________________________________ Bon Livraision __________________________________________________________________________________________

            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT= bonRetour.getMontantTotal();
            
             System.out.println("montantHT"+montantHT);  
           
              montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
             System.out.println("montantTTC"+montantTTC);

   Commande commandeTMP = commandeDAO.findCommandeByNumCommande(bonRetour.getNumCommande(), Constantes.ETAT_COMMANDE_SUPPRIMER); 

    BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(bonRetour.getFournisseur());
          bonLivraison.setMontant(montantHT);
          bonLivraison.setNumCommande(bonRetour.getNumCommande());
          bonLivraison.setLivraisonFour(bonRetour.getNumRetour());
          bonLivraison.setEtat(Constantes.ETAT_NON_PAIEMENT);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setClient(commandeTMP.getClientMP().getNom());
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_MNQ);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(new Date());
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
          
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
   List<DetailBonRetour> listDetailBonRetour = detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId());
   
           for (int i = 0; i < listDetailBonRetour.size(); i++) {
                
                DetailBonRetour detailBonRetour = listDetailBonRetour.get(i);
                
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(detailBonRetour.getBonRetour().getNumRetour());
                detailBonLivraison.setMontant(detailBonRetour.getMontant());
                detailBonLivraison.setPrix(detailBonRetour.getPrixUnitaire());
                detailBonLivraison.setQuantite(detailBonRetour.getQuantiteRetour());
                detailBonLivraison.setNumCommande(detailBonRetour.getBonRetour().getNumCommande());
                detailBonLivraison.setMatierePremier(detailBonRetour.getMatierePremier());
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);
                
 
         
           }

         bonRetour.setEtat(Constantes.ETAT_ANNULE);
         bonRetourDAO.edit(bonRetour);
           
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
        }

    }
    }
  

    @FXML
    private void afficherDetailBonRetourOnMouseClicked(MouseEvent event) {
        
        if (tableBonRtr.getSelectionModel().getSelectedIndex()!=-1){
            
      BonRetour bonRetour = tableBonRtr.getSelectionModel().getSelectedItem(); 
            
            
            listeDetailBonRetours.clear();
          
       listeDetailBonRetours.addAll(detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId()));

         tableDetailbonRtr.setItems(listeDetailBonRetours);
  
       setDetailColumnProperties();
  
    }
        
    }

    @FXML
    private void calculeTotalMouseClicked(MouseEvent event) {
        
           if (tableDetailbonRtr.getSelectionModel().getSelectedIndex()!=-1){
               
                BonRetour bonRetour = tableBonRtr.getSelectionModel().getSelectedItem(); 
               
               BigDecimal newMontant = BigDecimal.ZERO;
               
               newMontant = new BigDecimal(prixTxt.getText()).multiply(new BigDecimal(qteTxt.getText()));
               
        DetailBonRetour detailBonRetour = tableDetailbonRtr.getSelectionModel().getSelectedItem();
        
        detailBonRetour.setPrixUnitaire(new BigDecimal(prixTxt.getText()));
        detailBonRetour.setMontant(newMontant);
        
        detailBonRetourDAO.edit(detailBonRetour);
        
        bonRetour.setMontantTotal(newMontant);
        
        bonRetourDAO.edit(bonRetour);
        
//=======================================================================================================================================================================================================================================================================================================================================        
      
        BigDecimal MontantTVA= BigDecimal.ZERO;
        BigDecimal MontantTTC= BigDecimal.ZERO;


        String designation = detailBonRetour.getBonRetour().getType()+" "+Constantes.MANQUE_RETOUR_N+detailBonRetour.getBonRetour().getNumRetour()+" "+Constantes.SUR_COMMANDE_N+detailBonRetour.getBonRetour().getnCommande()+Constantes.RECEPTION_BON_LIVRAISON+detailBonRetour.getBonRetour().getNumLivraison();

               System.out.println("designation "+designation);
     
        DetailCompte detailCompte = detailCompteDAO.findByDetailCompte(designation);
         

               
         MontantTVA =newMontant.multiply(Constantes.TAUX_TVA_20);
         MontantTTC =newMontant.add(MontantTVA) ;

        detailCompte.setMontantCredit(MontantTTC.multiply(new BigDecimal(-1)));

        detailCompteDAO.edit(detailCompte);

        
      listeDetailBonRetours.clear();
          
       listeDetailBonRetours.addAll(detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId()));

         tableDetailbonRtr.setItems(listeDetailBonRetours);
  
       setDetailColumnProperties();
        
        qteTxt.clear();
        prixTxt.clear();
        totalTxt.clear();
       
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.TRAITEMENT_ENREGESTRE);
        
        
           }
        
    }

    @FXML
    private void tableDetailClicked(MouseEvent event) {
        
        
          if (tableDetailbonRtr.getSelectionModel().getSelectedIndex()!=-1){
               
        DetailBonRetour detailBonRetour = tableDetailbonRtr.getSelectionModel().getSelectedItem();
   
            prixTxt.setText(detailBonRetour.getPrixUnitaire()+"");
            qteTxt.setText(detailBonRetour.getQuantiteRetour()+""); 
            totalTxt.setText(detailBonRetour.getMontant()+""); 
        
           }

        
    }

    @FXML
    private void fourOnAction(ActionEvent event) {
    }

    @FXML
    private void respUsinOnAction(ActionEvent event) {  
    }

    @FXML
    private void stockOnAction(ActionEvent event) {
    }

    @FXML
    private void calculeMouseClicked(MouseEvent event) {
       
      MHT = BigDecimal.ZERO ;
      MTVA = BigDecimal.ZERO ;
      MTTC = BigDecimal.ZERO ;
            
            
           for( int rows = 0;rows<tableBonRtr.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
        
          

             MHT= MHT.add(montFactColumn.getCellData(rows));

             MTVA = MHT.multiply(Constantes.TAUX_TVA_20);
            
             MTTC = MHT.add(MTVA);
            
            
               
             }
        } 
    
           
         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
            
            
            monHT.setText(df.format(MHT.setScale(2,RoundingMode.FLOOR)));
            monTTC.setText(df.format(MTTC.setScale(2,RoundingMode.FLOOR)));
            monTVA.setText(df.format(MTVA.setScale(2,RoundingMode.FLOOR)));
            
            
              montantTotalField.setText(df.format(MTTC));  

       
        
    }

    @FXML
    private void bonRetMnqOnAction(ActionEvent event) {
        
        if( bonRetGraMnqCombo.getSelectionModel().getSelectedIndex()!=-1) {
        
        if ( bonRetGraMnqCombo.getSelectionModel().getSelectedItem().equals(Constantes.MANQUE)){
            
            
        
        fourCombo.setDisable(false);
        respUsinCombo.setDisable(false);
        stockCombo.setDisable(true);
        etatCombo.setDisable(false);
            
        }else{
        
        fourCombo.setDisable(false);
        respUsinCombo.setDisable(false);
        stockCombo.setDisable(false);
        etatCombo.setDisable(false);
        }
        
        }
    }

    @FXML
    private void selectionnerToutMouseClicked(MouseEvent event) {
        
        
         ObservableList<BonRetour> list=tableBonRtr.getItems();
        
        for (Iterator<BonRetour> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        tableBonRtr.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutMouseClicked(MouseEvent event) {
        
        
                 ObservableList<BonRetour> list=tableBonRtr.getItems();
        
        for (Iterator<BonRetour> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }

        tableBonRtr.refresh(); 
        
    }

    @FXML
    private void imprimerBtnOnAction(ActionEvent event) {
        

          try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationBonRetourGratuite.class.getResource(nav.getiReportConsultationBonRetourGratuite()));


             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(tableBonRtr.getItems()));
               JasperViewer.viewReport(jp, false);
               
     
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationBonRetourGratuite.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void etatOnAction(ActionEvent event) {
    }
}
