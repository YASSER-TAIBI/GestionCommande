/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Utils.Constantes;
import dao.Entity.AvoirOulmes;
import dao.Entity.BonLivraison;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailBonRetour;
import dao.Entity.FactureAvoirOulmes;
import dao.Entity.Fournisseur;
import dao.Entity.MatierePremier;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
import dao.Entity.Sequenceur;
import dao.Manager.AvoirOulmesDAO;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.FactureAvoirOulmesDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.AvoirOulmesDAOImpl;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.FactureAvoirOulmesDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class FactureAvoirOulmesController implements Initializable {

     @FXML
    private TableView<FactureAvoirOulmes> avoireOulmestable;
    @FXML
    private TableColumn<FactureAvoirOulmes, String> codeArtColumn;
    @FXML
    private TableColumn<FactureAvoirOulmes, String> libelleColumn;
    @FXML
    private TableColumn<FactureAvoirOulmes, String> clientColumn;
    @FXML
    private TableColumn<FactureAvoirOulmes, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<FactureAvoirOulmes, BigDecimal> prixColumn;
    @FXML
    private TableColumn<FactureAvoirOulmes, BigDecimal> montantColumn;
    @FXML
    private TableColumn<FactureAvoirOulmes, BigDecimal> remiseColumn;
    @FXML
    private TableColumn<FactureAvoirOulmes, BigDecimal> montantNetColumn;
    @FXML
    private TableColumn<FactureAvoirOulmes, String> bonAvoirColumn;
    @FXML
    private TableColumn<FactureAvoirOulmes, String> numFacColumn;
    @FXML
    private TableColumn<FactureAvoirOulmes, Boolean> actionColumn;
    @FXML
    private TableColumn<FactureAvoirOulmes, String> client2Column;
    @FXML
    private Label monHT;
    @FXML
    private Label monTVA;
    @FXML
    private Label monTTC;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button validerSaisie;
    @FXML
    private TextField montantTotalField;
    
        @FXML
    private TextField quantiteField;
    @FXML
    private TextField codeArtField;
    @FXML
    private DatePicker dateSaisie;
    @FXML
    private Label qteAfficchage;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private RadioButton radOui;
    @FXML
    private ToggleGroup radOuiNon;
    @FXML
    private RadioButton radNon;
    @FXML
    private RadioButton radPlus;
    @FXML
    private ToggleGroup radPlusMoin;
    @FXML
    private RadioButton radmoin;
    @FXML
    private ComboBox<String> LibelleCombo;
    @FXML
    private TextField nLivraisonField;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField nFactureField;
    @FXML
    private RadioButton radClientOui;
    @FXML
    private RadioButton radClientNon;
    @FXML
    private ComboBox<String> client2Combo;
    @FXML
    private ToggleGroup radClientOuiNon;
    @FXML
    private ComboBox<String> lieuLivCombo;
    
    /**
     * Initializes the controller class.
     */
    
    
    
     private final ObservableList<FactureAvoirOulmes> listeFactureAvoirOulmes=FXCollections.observableArrayList();
    
    navigation nav = new navigation();
    PrixOulmes prixOulmes =new PrixOulmes();
    Date date = new  Date();
            
    ProduitDAO produitDAO = new ProduitDAOImpl();       
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    FactureAvoirOulmesDAO factureAvoirOulmesDAO = new FactureAvoirOulmesDAOImpl();
      
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    
    private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
      
    MatierePremiereDAO matierePremierDAO = new MatierePremierDAOImpl();  
    BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
    DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
    
    
    SimpleDateFormat dateFormat= new SimpleDateFormat("yy");
    
    
     BigDecimal montanNet =BigDecimal.ZERO;
     BigDecimal MHT = BigDecimal.ZERO ;
     BigDecimal MTVA = BigDecimal.ZERO ;
     BigDecimal MTTC = BigDecimal.ZERO ;
 
    BigDecimal montantHT = BigDecimal.ZERO;

     BigDecimal qteCaisse = BigDecimal.ZERO;
   
  ObservableList<String> client =FXCollections.observableArrayList(Constantes.CLIENT_MARJANE,Constantes.CLIENT_MINURSO);
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          client2Combo.setItems(client);
        
           loadDetail();
        setColumnProperties();
        
           radPlus.setVisible(false);
        radmoin.setVisible(false);
        
         List<Fournisseur> listFournisseur=fournisseurDAO.findAllPF();
        
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
        
           client2Combo.setDisable(true);
           lieuLivCombo.setDisable(true);
    }    


    
          Sequenceur sequenceurF = sequenceurDAO.findByCode(Constantes.FACTURE_AVOIR_CODE);
          String FactCount = Constantes.FACTURE_AVOIR_CODE+(sequenceurF.getValeur()+1);
          
          Sequenceur sequenceurA = sequenceurDAO.findByCode(Constantes.BON_AVOIR_CODE);
          String AvoirCount = Constantes.BON_AVOIR_CODE+(sequenceurA.getValeur()+1);
    
    void setColumnProperties(){

                codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FactureAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<FactureAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });

                libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FactureAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<FactureAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
             });
                    
                clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FactureAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<FactureAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClient());
                }
             });
       
                client2Column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FactureAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<FactureAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClient2());
                }
             });
                
                bonAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FactureAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<FactureAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumLivraison());
                }
             });
           
                numFacColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FactureAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<FactureAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumFacture());
                }
             });
                         
                quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FactureAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<FactureAvoirOulmes, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getQte());
                }
                
             });

                prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FactureAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<FactureAvoirOulmes, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrix());
                }
                
             });
           
                remiseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FactureAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<FactureAvoirOulmes, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getRemiseAvoir());
                }
             });
           
                montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FactureAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<FactureAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
             
                montantNetColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FactureAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<FactureAvoirOulmes, BigDecimal> p) {
                    

                    return new ReadOnlyObjectWrapper(p.getValue().getMontantNet().setScale(2,RoundingMode.FLOOR));
                }
                
             });
                 
                 
          actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          FactureAvoirOulmes cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionColumn.setEditable(true);
      
          
          avoireOulmestable.setEditable(true); 
}
    
       void loadDetail(){
           
        listeFactureAvoirOulmes.clear();
        listeFactureAvoirOulmes.addAll(factureAvoirOulmesDAO.findAvoirOulmesByEtat(Constantes.ETAT_BL_AVOIR));
        avoireOulmestable.setItems(listeFactureAvoirOulmes);
    }
    
         void clear(){
    
    codeArtField.clear();
    qteAfficchage.setText("");
    LibelleCombo.getSelectionModel().select(-1);
    client2Combo.getSelectionModel().select(-1);
    lieuLivCombo.getSelectionModel().select(-1);
    quantiteField.clear();
    nLivraisonField.clear();
    nFactureField.clear();

    
     client2Combo.setDisable(true);
    lieuLivCombo.setDisable(true);
     
    radClientNon.setSelected(false);
    radClientOui.setSelected(false);
    
    radOui.setSelected(false);
    radNon.setSelected(false);
    
    radPlus.setVisible(false);
    radmoin.setVisible(false);
    radOui.setSelected(false);
    radNon.setSelected(false);

    }  
    
    @FXML
    private void refresh(ActionEvent event) {
    }

    @FXML
    private void validerSaisieAction(ActionEvent event) throws ParseException {
        
          if (avoireOulmestable.equals(null) || monHT.getText().equals("")|| monTVA.getText().equals("")|| monTTC.getText().equals("")|| montantTotalField.getText().equals("") || clientCombo.getSelectionModel().getSelectedItem()== null ||fourCombo.getSelectionModel().getSelectedItem()==null || dateSaisie.getValue()== null ){
        
            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);

        }else{

          BigDecimal montantTotal= BigDecimal.ZERO;
          BigDecimal montantPalette= BigDecimal.ZERO;
              
         for( int rows = 0;rows<listeFactureAvoirOulmes.size() ;rows++ ){
    
               FactureAvoirOulmes factureAvoirOulmes = listeFactureAvoirOulmes.get(rows);
             
         if (actionColumn.getCellData(rows).booleanValue()==true){
 
             
              if (factureAvoirOulmes.getPrixOulmes().getProduit().getPalette() == true){
                
                montantPalette = montantPalette.add(montantNetColumn.getCellData(rows));
                
                }else{

                montantTotal = montantTotal.add(montantNetColumn.getCellData(rows));  
                }
             
                factureAvoirOulmes.setEtat(Constantes.ETAT_REGLEMENT_AVOIR);
                factureAvoirOulmes.setAction(false);

            factureAvoirOulmesDAO.edit(factureAvoirOulmes);
            

//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
                            String  client ="";         
                           if(factureAvoirOulmes.getClient2().equals(Constantes.CLIENT_MINURSO)){
                           client = factureAvoirOulmes.getClient2();
                           }else if (factureAvoirOulmes.getClient2().equals(Constantes.CLIENT_MARJANE)){
                           client =  Constantes.SANS;
                           }else{
                           client =  Constantes.SANS;
                           }
  
                PrixOulmes prixOulmes = prixOulmesDAO.findPrixOulmesByCodeArt(codeArtColumn.getCellData(rows),client,factureAvoirOulmes.getLieuLivraison());

                MatierePremier matierePremier = matierePremierDAO.findByCode(Constantes.CODE_MP);

                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(AvoirCount);
                detailBonLivraison.setMontant(montantNetColumn.getCellData(rows));
                detailBonLivraison.setRemiseAchat(remiseColumn.getCellData(rows));
                detailBonLivraison.setBonAvoir(bonAvoirColumn.getCellData(rows));
                detailBonLivraison.setNumCommande("OLF/19");
                detailBonLivraison.setPrix(prixColumn.getCellData(rows));
                detailBonLivraison.setPrixOulmes(prixOulmes);
                detailBonLivraison.setClient2(factureAvoirOulmes.getClient2());
                detailBonLivraison.setNumFacture(numFacColumn.getCellData(rows));
                detailBonLivraison.setQuantite(quantiteColumn.getCellData(rows));
                detailBonLivraison.setMatierePremier(matierePremier);
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);

           
                }
        }  
//_________________________________________________________________________ Bon Livraision __________________________________________________________________________________________

            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
           
            montantHT= montantTotal.add(montantPalette)  ; 
            montantTVA=  montantTotal.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
            montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
       
            
            LocalDate localDate=dateSaisie.getValue();
            
            Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
            Fournisseur fournisseur  = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
            ClientMP clientMP  = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());

          BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(fournisseur.getNom());
          bonLivraison.setMontant(montantHT);
          bonLivraison.setLivraisonFour(AvoirCount);
          bonLivraison.setEtat(Constantes.ETAT_A_PAYER);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setClient(clientMP.getNom());
          bonLivraison.setNumFacture(FactCount);
          bonLivraison.setNumCommande("OLF/19");
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_FACTURE);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(date);
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
        
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
          
            
            Sequenceur sequenceurF = sequenceurDAO.findByCode(Constantes.FACTURE_AVOIR_CODE);
            sequenceurF.setValeur(sequenceurF.getValeur()+1);
            sequenceurDAO.edit(sequenceurF);
         
            FactCount = Constantes.FACTURE_AVOIR_CODE+(sequenceurF.getValeur()+1);
            
             Sequenceur sequenceurA = sequenceurDAO.findByCode(Constantes.BON_AVOIR_CODE);
            sequenceurA.setValeur(sequenceurA.getValeur()+1);
            sequenceurDAO.edit(sequenceurA);
         
            AvoirCount = Constantes.BON_AVOIR_CODE+(sequenceurA.getValeur()+1);
            
            setColumnProperties();
            loadDetail();
            
            monHT.setText("");
            monTVA.setText("");
            monTTC.setText("");

            montantTotalField.clear();
        
          }}

    @FXML
    private void selectionnerToutMouseClicked(MouseEvent event) {
        
            ObservableList<FactureAvoirOulmes> list=avoireOulmestable.getItems();
        
        for (Iterator<FactureAvoirOulmes> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        avoireOulmestable.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutMouseClicked(MouseEvent event) {
        
        
               ObservableList<FactureAvoirOulmes> list=avoireOulmestable.getItems();
        
        for (Iterator<FactureAvoirOulmes> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }

        avoireOulmestable.refresh(); 
        
    }


    @FXML
    private void calculeMouseClicked(MouseEvent event) {
        
        BigDecimal montantTotal= BigDecimal.ZERO;
          BigDecimal montantPalette= BigDecimal.ZERO;
           MHT = BigDecimal.ZERO ;
           MTVA = BigDecimal.ZERO ;
           MTTC = BigDecimal.ZERO ;
            
            
           for( int rows = 0;rows<listeFactureAvoirOulmes.size() ;rows++ ){
 
                 FactureAvoirOulmes factureAvoirOulmes = listeFactureAvoirOulmes.get(rows);
               
        if (actionColumn.getCellData(rows).booleanValue()==true){
        
           if (factureAvoirOulmes.getPrixOulmes().getProduit().getPalette() == true){
                
                montantPalette = montantPalette.add(montantNetColumn.getCellData(rows));
                
                }else{

                montantTotal = montantTotal.add(montantNetColumn.getCellData(rows));  
                } 
             }
        } 
    
                MHT= montantTotal.add(montantPalette);

             MTVA = montantTotal.multiply(Constantes.TAUX_TVA_20);
            
             MTTC = MHT.add(MTVA);
           
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
    private void qteByIntervalleOnAction(KeyEvent event) {
        
           BigDecimal condCaisse = BigDecimal.ZERO;
            qteCaisse = BigDecimal.ZERO;
        
            qteAfficchage.setText("");

        PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
        
         if(prixOulmes!=null){
         
             condCaisse = prixOulmes.getConditionnementCaisse();
             
              if(prixOulmes!=null){
              
                   qteCaisse = new BigDecimal(quantiteField.getText()).multiply(condCaisse);
             
               DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

  

     qteAfficchage.setText(df.format(qteCaisse.setScale(2,RoundingMode.FLOOR)));
                  
              }else{
              
                       qteCaisse = new BigDecimal(quantiteField.getText()).multiply(new BigDecimal(1));
             
               DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

  

     qteAfficchage.setText(df.format(qteCaisse.setScale(2,RoundingMode.FLOOR)));
              
              }
             
             
            
         }else{
         
                            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.VERIFIER_CODE_LIBELLE);
         
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
    private void ajouterSaisieAction(ActionEvent event) throws ParseException {
        
                        FactureAvoirOulmes  factureAvoirOulmes = new FactureAvoirOulmes();

                if(codeArtField.getText().equalsIgnoreCase("")|| 
                   LibelleCombo.getValue().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("") ||
                   nFactureField.getText().equalsIgnoreCase("") ||
                   nLivraisonField.getText().equalsIgnoreCase("")

                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
    }
else {
            
                     Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());  
                    
                             Produit produit = produitDAO.findByCode(codeArtField.getText());
  
                                    String  client ="";         
                       if (client2Combo.getSelectionModel().getSelectedIndex()!=-1){
                           if(client2Combo.getSelectionModel().getSelectedItem().equals(Constantes.CLIENT_MINURSO)){
                           client = client2Combo.getSelectionModel().getSelectedItem();
                           }else if (client2Combo.getSelectionModel().getSelectedItem().equals(Constantes.CLIENT_MARJANE)){
                           client =  Constantes.SANS;
                           }
                       }else {
                       client= Constantes.SANS;
                       }
                               String lieu="";
                      if (lieuLivCombo.getSelectionModel().getSelectedIndex()!=-1){
                         lieu= lieuLivCombo.getSelectionModel().getSelectedItem();       
                       }else{
                       lieu = Constantes.SANS;
                       }  
                       
                    PrixOulmes prixOulmes =prixOulmesDAO.findPrixOulmesByFourAndArt(fournisseur.getNom(),produit.getId(),client,lieu );
                    
            BigDecimal  prixB= BigDecimal.ZERO ;
            BigDecimal remise= BigDecimal.ZERO ;

              ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
            
            if(prixOulmes==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            
            else {
                
                prixB= prixOulmes.getPrix();
      LocalDate localDate=dateSaisie.getValue();
            
                Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());

                factureAvoirOulmes.setPrix(prixB);
                
                     if(qteAfficchage.getText().equals("")){
                    
                     BigDecimal QteB= new BigDecimal(quantiteField.getText());
                factureAvoirOulmes.setQte(QteB);
                
                BigDecimal  montantB= (QteB.multiply(prixB)).setScale(2,RoundingMode.FLOOR);
                
                factureAvoirOulmes.setMontant(montantB);
                
                 if (radOui.isSelected()==true){
             
              radPlus.setVisible(true);
              radmoin.setVisible(true);
              
               if (radPlus.isSelected()==true){
               
                remise = prixOulmes.getRemiseAvoir();
                 
                 montanNet = montantB.add((montantB.multiply(remise)).divide(new BigDecimal(100)));
                 
                  factureAvoirOulmes.setMontantNet(montanNet);
                  factureAvoirOulmes.setRemiseAvoir(remise);
                   
               }else if (radmoin.isSelected()==true){
               
                remise = prixOulmes.getRemiseAvoir();
                 
                 montanNet = montantB.subtract((montantB.multiply(remise)).divide(new BigDecimal(100)));
                 
                  factureAvoirOulmes.setMontantNet(montanNet);
                  factureAvoirOulmes.setRemiseAvoir(remise);
               
               }
               
             }else{
             
                 montanNet = montantB;
                 
                 factureAvoirOulmes.setMontantNet(montanNet);
                 factureAvoirOulmes.setRemiseAvoir(BigDecimal.ZERO);
             
             }
                
                }else {

                factureAvoirOulmes.setQte(qteCaisse);
                
                 BigDecimal  montantB= (qteCaisse.multiply(prixB)).setScale(2,RoundingMode.FLOOR);
                
                factureAvoirOulmes.setMontant(montantB);
                
                 if (radOui.isSelected()==true){
             
              radPlus.setVisible(true);
              radmoin.setVisible(true);
              
               if (radPlus.isSelected()==true){
               
                remise = prixOulmes.getRemiseAvoir();
                 
                 montanNet = montantB.add((montantB.multiply(remise)).divide(new BigDecimal(100)));
                 
                  factureAvoirOulmes.setMontantNet(montanNet);
                  factureAvoirOulmes.setRemiseAvoir(remise);
                   
               }else if (radmoin.isSelected()==true){
               
                remise = prixOulmes.getRemiseAvoir();
                 
                 montanNet = montantB.subtract((montantB.multiply(remise)).divide(new BigDecimal(100)));
                 
                  factureAvoirOulmes.setMontantNet(montanNet);
                  factureAvoirOulmes.setRemiseAvoir(remise);
               
               }
               
             }else{
             
                 montanNet = montantB;
                 
                 factureAvoirOulmes.setMontantNet(montanNet);
                 factureAvoirOulmes.setRemiseAvoir(BigDecimal.ZERO);
             
             }
                
                }
                     
             factureAvoirOulmes.setPrixOulmes(prixOulmes);
             
             
                if (client2Combo.getSelectionModel().getSelectedIndex()!=-1){
                          factureAvoirOulmes.setClient2(client2Combo.getSelectionModel().getSelectedItem());
                       }else {
                         factureAvoirOulmes.setClient2(Constantes.SANS);  
                       }

                      if (lieuLivCombo.getSelectionModel().getSelectedIndex()!=-1){
                         factureAvoirOulmes.setLieuLivraison(lieuLivCombo.getSelectionModel().getSelectedItem());     
                       }else{
                         factureAvoirOulmes.setLieuLivraison(Constantes.SANS);  
                       }  
 
            
             factureAvoirOulmes.setClient(clientMP.getNom());
             factureAvoirOulmes.setDesignation(Constantes.ETAT_BL_FACTUR+nFactureField.getText());
             factureAvoirOulmes.setAction(false);
             factureAvoirOulmes.setUtilisateurCreation(nav.getUtilisateur());
             factureAvoirOulmes.setEtat(Constantes.ETAT_BL_AVOIR);
             factureAvoirOulmes.setNumLivraison(nLivraisonField.getText());
             factureAvoirOulmes.setNumFacture(nFactureField.getText());
             factureAvoirOulmes.setDateSaisie(dateSaisie);

            factureAvoirOulmesDAO.add(factureAvoirOulmes);

            clear();
            setColumnProperties();
            loadDetail();
    
         }
}
    }

    @FXML
    private void radOuiOnAction(ActionEvent event) {
        
    radPlus.setSelected(false);
    radmoin.setSelected(false);
    radPlus.setVisible(true);
    radmoin.setVisible(true);
        
    }

    @FXML
    private void radNonOnAction(ActionEvent event) {
        
            radPlus.setVisible(false);
    radmoin.setVisible(false);
    }

    @FXML
    private void LibelleComboOnAction(ActionEvent event) {
        
     
                 PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
           
        if(prixOulmes!=null){

         codeArtField.setText(prixOulmes.getProduit().getCode());

         }
        
    }

    @FXML
    private void fournisseurOnAction(ActionEvent event) {
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }

    @FXML
    private void radClient2OuiOnAction(ActionEvent event) {
        
       LibelleCombo.getItems().clear();  
       codeArtField.clear();
       client2Combo.setDisable(false);
        
    }

    @FXML
    private void radClient2NonOnAction(ActionEvent event) {
        
        LibelleCombo.getItems().clear();
        codeArtField.clear();
        client2Combo.getSelectionModel().select(-1);
        lieuLivCombo.getSelectionModel().select(-1);
        
                List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findPrixFilmByClient(Constantes.SANS);

         for (int i = 0; i < listPrixOulmes.size(); i++) {
                
                PrixOulmes prixOulmes = listPrixOulmes.get(i);
                
                LibelleCombo.getItems().add(prixOulmes.getProduit().getLibelle());
                
                mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);

            }
        
        client2Combo.setDisable(true);
        lieuLivCombo.setDisable(true);
        
    }

    @FXML
    private void client2OnAction(ActionEvent event) {
      LibelleCombo.getItems().clear();      
        codeArtField.clear();
        
        String valeur = client2Combo.getSelectionModel().getSelectedItem();
        
        if(valeur!=null){
        
        if (valeur.equals(Constantes.CLIENT_MINURSO)){
            
        lieuLivCombo.getItems().clear();
        lieuLivCombo.setDisable(false);
            
   List <String> listeObjectLieu =prixOulmesDAO.findPrixOulmesByClient(Constantes.CLIENT_MINURSO);

   System.out.println("listeObjectLieu "+listeObjectLieu.size());
         
            for(int i=0; i<listeObjectLieu.size(); i++) {

                    String prixOulmes = listeObjectLieu.get(i);

                       lieuLivCombo.getItems().add(prixOulmes);
                       
                    }

        
        }else if (valeur.equals(Constantes.CLIENT_MARJANE)){
            
               lieuLivCombo.getItems().clear();
            lieuLivCombo.setDisable(true);
            
             List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findPrixFilmByClient(Constantes.SANS);

            for (int i = 0; i < listPrixOulmes.size(); i++) {
                
                PrixOulmes prixOulmes = listPrixOulmes.get(i);
                
                LibelleCombo.getItems().add(prixOulmes.getProduit().getLibelle());
                
                mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
               
            }
        }
    }}

    @FXML
    private void lieuLivOnAction(ActionEvent event) {
        
            LibelleCombo.getItems().clear();
            codeArtField.clear();
            
             List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findPrixOulmesByLieu(lieuLivCombo.getSelectionModel().getSelectedItem());
       
       for (int i = 0; i < listPrixOulmes.size(); i++) {
                
                PrixOulmes prixOulmes = listPrixOulmes.get(i);

                LibelleCombo.getItems().add(prixOulmes.getProduit().getLibelle());
                
                mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
       
             
    }
    
        
    }
    
}
