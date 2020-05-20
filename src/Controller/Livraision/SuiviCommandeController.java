/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Livraision;

import Controller.commande.EnvoyerCommandeController;
import Utils.Constantes;
import dao.Entity.BonLivraison;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.CompteFourMP;
import dao.Entity.Depot;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCompte;
import dao.Entity.DetailReception;
import dao.Entity.Fournisseur;
import dao.Entity.Magasin;
import dao.Entity.MatierePremier;
import dao.Entity.Sequenceur;
import dao.Entity.StockMP;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailReceptionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MagasinDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.StockMPDAO;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailReceptionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.StockMPDAOImpl;
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
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
 * @author h
 */
public class SuiviCommandeController implements Initializable {

    @FXML
    private TableView<Commande> tableCommande;
    @FXML
    private TableColumn<Commande, String> dateCreationColumn;
    @FXML
    private TableColumn<Commande, String> etatColumn;
    @FXML
    private TableView<DetailCommande> tableDetailCommande;
    @FXML
    private TableColumn<DetailCommande, String> codeMPColumn;
    @FXML
    private TableColumn<DetailCommande, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteRecuColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteRestantColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteLivreeColumn;
    @FXML
    private TableColumn<Commande, String> nComColumn;
    @FXML
    private Button btnValider;
    @FXML
    private TextField nLivraisonField;
    @FXML
    private DatePicker dateCreationPicker;
    @FXML
    private DatePicker dateLivraisonPicker;
    @FXML
    private TextField fournisseurField;
    @FXML
    private TextField clientField;
    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private TextField numComRechField;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRecu;
    @FXML
    private Button btnImprimerBc;
    @FXML
    private CheckBox checkDd;
    @FXML
    private CheckBox checkSc;
    
    
    private final ObservableList<DetailCommande> listeDetailCommande = FXCollections.observableArrayList();
    private final ObservableList<DetailCommande> listeDetailCommandeTMP = FXCollections.observableArrayList();
    private final ObservableList<Commande> listeCommande = FXCollections.observableArrayList();

   
    List<Fournisseur> listeFour = new ArrayList<>();
    List<ClientMP> listeClient = new ArrayList<>();
    
    
    DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    CommandeDAO commandeDAO = new CommandeDAOImpl();
    StockMPDAO stockMPDAO = new StockMPDAOImpl();
    DetailReceptionDAO detailReceptionDAO = new DetailReceptionDAOImpl();
    DepotDAO depotDAO = new DepotDAOImpl();
    MagasinDAO magasinDAO = new MagasinDAOImpl();
    BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
    
    navigation nav = new navigation();
    Commande commande = new Commande();
    Fournisseur fournisseur = new Fournisseur();
    DetailCompte detailCompte = new DetailCompte();
    
    private Map<String, Depot> mapDepot = new HashMap<>();
    private Map<String, Magasin> mapMagasin = new HashMap<>();
    private Map<String, Fournisseur> mapFournisseur = new HashMap<>();
    private Map<String, ClientMP> mapClientMP = new HashMap<>();
    
    String codeReception = "";
    
//    BigDecimal montantTotal = BigDecimal.ZERO;
//    public BigDecimal qteLivree =BigDecimal.ZERO;
    public BigDecimal montantDebit =BigDecimal.ZERO;

    
   
   

  void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RECEPTION_CODE);
          codeReception = Constantes.RECEPTION_CODE+" "+(sequenceur.getValeur()+1);
   }
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try{
        setColumnProperties();
        loadDetail();

        
//        int Maxid = detailReceptionDAO.getMaxId();
//        numReception = (nav.generCode(Constantes.RECEPTION, Maxid));
          Incrementation (); 
        
        List<Depot> listDepot = depotDAO.findAll();

        listDepot.stream().map((depot) -> {
            depotCombo.getItems().addAll(depot.getLibelle());
            return depot;
        }).forEachOrdered((depot) -> {
            mapDepot.put(depot.getLibelle(), depot);
        });
        
        btnValider.setDisable(true);
        btnImprimer.setDisable(false);
        
        } catch (Exception e) {
                     nav.showAlert(Alert.AlertType.ERROR, "Attention", null, e.getMessage());
                }
    }

    void setColumnProperties() {

        nComColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getnCommande());
            }

        });
        dateCreationColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));

 

    }

    void loadDetail() {

        listeCommande.clear();
        listeCommande.addAll(commandeDAO.findCommandeByEtat(Constantes.ETAT_COMMANDE_ENCOURS, Constantes.COMMANDE_INTERNE ));
        tableCommande.setItems(listeCommande);
    }

    public void changeNomCellEvent(CellEditEvent editedCell) {

        Commande utilisateurSelected = tableCommande.getSelectionModel().getSelectedItem();
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
          

        
        quantiteRecuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                        
                    DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRecu()));
                }
                
             });
                


        quantiteRestantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                    
                  DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRestee()));
                }
                
             });


        quantiteLivreeColumn.setCellValueFactory(new PropertyValueFactory<DetailCommande, BigDecimal>("quantiteLivree"));

        setColumnTextFieldConverter(getConverter(), quantiteLivreeColumn);

    }

   //--------------------------------------- Methode date paiement + date now = nombre de jour restant -------------------------------------------------------------------------------
      
    public int daysBetween(Date d1, Date d2){
             return (int)( (d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
             
     }
   
    //--------------------------------------- Methode date paiement + date now = nombre de jour restant -------------------------------------------------------------------------------
      
    public int daysBetween2(Date d1, Date d2){
             return  d1.compareTo(d2);
                     
                     }
   
  //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------         
  
    @FXML
    private void btnValiderOnAction(ActionEvent event) throws ParseException {
   
            BigDecimal montantTotal= BigDecimal.ZERO;
            
            
             LocalDate localDate=dateLivraisonPicker.getValue();
            
                Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
           for( int rows = 0;rows<listeDetailCommande.size() ;rows++ ){
        

               DetailCommande detailCommande = listeDetailCommande.get(rows);

              if (detailCommande.getQuantiteLivree().compareTo(BigDecimal.ZERO)>0){
             
                  DetailReception detailReception = new DetailReception();
                  
                detailReception.setDetailCommande(detailCommande);
                detailReception.setDateReception(dateSaisie);
                detailReception.setUtilisateurCreation(nav.getUtilisateur());
                detailReception.setQuantiteRecu(detailCommande.getQuantiteLivree());
                detailReception.setPrix(detailCommande.getPrixUnitaire());
                
                          if (commande.getnCommande().contains("F")){

                               Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_F);

                             
                      detailReception.setLivraisonFour(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1));
                      
                      }else if(commande.getnCommande().contains("D")){
                          
                           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_D);
                      
                      detailReception.setLivraisonFour(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1));
                          
                      }else if (checkDd.isSelected()== true){
                       
                           detailReception.setLivraisonFour(nLivraisonField.getText()+" "+"DD");
                      
                      }else if (checkSc.isSelected()== true){
                      
                           detailReception.setLivraisonFour(nLivraisonField.getText()+" "+"SC");
                      
                      }else{
                      
                       detailReception.setLivraisonFour(nLivraisonField.getText());
                      }
                
                detailReception.setNumReception(codeReception);

                detailReceptionDAO.add(detailReception);
              
//------------------------------- Traitement Compte par Four ------------------------------------------------------------------------------------------------------------------------------------------

       BigDecimal montant = BigDecimal.ZERO;
       BigDecimal pu = BigDecimal.ZERO;
            
               pu =detailCommande.getPrixUnitaire();
               montant = pu.multiply(detailCommande.getQuantiteLivree()).setScale(2,RoundingMode.FLOOR);
               
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------       
   
     MatierePremier matierePremier = detailCommande.getMatierePremier();

      
      
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();  
         
                    if (commande.getnCommande().contains("F")){
                          
                           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_F);
                      detailBonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1));
                      
                    }else if(commande.getnCommande().contains("D")){
                      
                          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_D);
                      detailBonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1));
                        
                      }else if ( checkDd.isSelected()== true){
                      
                           detailBonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"DD");
                      
                      }else if ( checkSc.isSelected()== true){
                      
                           detailBonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"SC");
                      
                      }else{
                      
                        detailBonLivraison.setLivraisonFour(nLivraisonField.getText());
                      }
                    
          detailBonLivraison.setMatierePremier(matierePremier);
          detailBonLivraison.setQuantite(detailCommande.getQuantiteLivree());
          detailBonLivraison.setMontant(montant);
          detailBonLivraison.setDatedetailBonLivraison(dateSaisie);
          detailBonLivraison.setPrix(pu);
          detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          detailBonLivraison.setNumCommande(nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
          detailBonLivraison.setNumReception(codeReception);
          
          
                detailBonLivraisonDAO.add(detailBonLivraison);
             
                

            montantTotal = montantTotal.add(quantiteLivreeColumn.getCellData(rows).multiply(detailCommande.getPrixUnitaire()));  
        
              }
    }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String date ="00-00-0000 00:00:00";
       
        Date date1 = simpleDateFormat.parse(date);
        

         if (nLivraisonField.getText().equalsIgnoreCase("") || depotCombo.getSelectionModel().getSelectedItem() == null || depotCombo.getSelectionModel().getSelectedItem().isEmpty() || tableDetailCommande.getItems().isEmpty() /*|| depot.getText().equals("")*/) {
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
        } else {

            validationStock();
//____________________________________________________________________________________________________________________________________________________
       
String BL = null ;

                    if (commande.getnCommande().contains("F")){
                          
        
                          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_F);
                        BL=  nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1);
                      
                    }else if (commande.getnCommande().contains("D")){   
                        
                         Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_D);
                        BL=  nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1);
                          
                    }else if ( checkDd.isSelected()== true){
                      
                              BL= nLivraisonField.getText()+" "+"DD";
                      
                      }else if ( checkSc.isSelected()== true){
                      
                             BL= nLivraisonField.getText()+" "+"SC";
                      
                      }else if (!commande.getnCommande().contains("F") && !commande.getnCommande().contains("D") && !commande.getnCommande().contains("P") && checkDd.isSelected()== false && checkSc.isSelected()== false ) {
                      
                        BL= nLivraisonField.getText();
              
                      }

       
 BonLivraison bonLivraison = bonLivraisonDAO.findNumCommandeByNumLivraison(BL,commande.getnCommande());
   
   if (bonLivraison == null){
       
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
             System.out.println("montantTotal"+montantTotal);  
           
  
              montantTVA=  montantTotal.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantTotal).setScale(2,RoundingMode.FLOOR);
   
             System.out.println("montantTTC"+montantTTC);
         
             bonLivraison = new BonLivraison();
            
             
             
                

//--------------------------------------- Methode date livraison + periode = date paiement -------------------------------------------------------------------------------          
      
int valeur= commande.getFourisseur().getDelaiPaiement();

         Calendar calendar = Calendar.getInstance();
         calendar.setTime(dateSaisie);   
         calendar.add(Calendar.DAY_OF_MONTH, Integer.valueOf(valeur));
         Date dt=calendar.getTime();
            
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------           
         int nbrJr = (daysBetween(dt, new Date()));
         
            bonLivraison.setDate(new Date());
        
                      if (commande.getnCommande().contains("F")){
  
                             Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_F);
                          bonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1));
                          
                       }else if ( commande.getnCommande().contains("D") ){
                           
                            Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_D);
                          bonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1));
    
                      }else if ( checkDd.isSelected()== true){
                          
                               bonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"DD");
                      
                      }else if ( checkSc.isSelected()== true){

                               bonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"SC");
                      
                      }else if (!commande.getnCommande().contains("F") && !commande.getnCommande().contains("D") && !commande.getnCommande().contains("P") && checkDd.isSelected()== false && checkSc.isSelected()== false) {

                         bonLivraison.setLivraisonFour(nLivraisonField.getText());
                      }
            
            bonLivraison.setFournisseur(fournisseurField.getText());
            bonLivraison.setClient(clientField.getText());
            bonLivraison.setEtat(Constantes.ETAT_NON_REGLE);
            bonLivraison.setDateLivraison(dateSaisie);
            
            boolean existe = false;
            List <Commande> listeCommandeTMP = commandeDAO.findByNumCommande(Constantes.CODE_SPECIAL_DEPOT, Constantes.CODE_SPECIAL_FOUR);
            Commande commandeNum = listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex());
            
            for(int i=0 ;i<listeCommandeTMP.size();i++){
                
              Commande commande = listeCommandeTMP.get(i);
                
            if (commandeNum.getnCommande().equals(commande.getnCommande())){
            
                existe=true;
                
                
            bonLivraison.setDatePaiement(date1);
 
            }
            }
       
            if(existe == false ){
            bonLivraison.setDatePaiement(dt);
            }
            
            bonLivraison.setNombreJour(nbrJr);
            bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
            bonLivraison.setMontant(montantTotal);
            bonLivraison.setTypeBon(Constantes.ETAT_BL);
            bonLivraison.setMontantTVA(montantTVA);
            bonLivraison.setMontantTTC(montantTTC);
            bonLivraison.setAction(Boolean.FALSE);
            bonLivraison.setSansTVA(Boolean.FALSE);
            bonLivraison.setNumCommande(nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));

            
            
            bonLivraisonDAO.add(bonLivraison);
            
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Detail Compte // Montant Debit <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<     
            
              detailCompte = new DetailCompte();
                        
               detailCompte.setCompteFourMP(commande.getFourisseur().getCompteFourMP());
               detailCompte.setClientMP(commande.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setDateOperation(new Date());
               detailCompte.setDateBonLivraison(dateSaisie);
               
                      if (commande.getnCommande().contains("F")){

                             Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_F);
                      detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1)+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      
                      }else if ( commande.getnCommande().contains("D")){
                          
                             Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_D);
                      detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1)+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                          
                      }else if ( checkDd.isSelected()== true){

                      detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" DD_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      

                      
                      }else if ( checkSc.isSelected()== true){

                      detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" SC_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      

                      
                      }else{
                      
                      detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      
                      }
                         
               detailCompte.setCode(codeReception);
               detailCompte.setMontantDebit(BigDecimal.ZERO);
               detailCompte.setMontantCredit(montantTTC.setScale(2,RoundingMode.FLOOR));
        
               detailCompteDAO.add(detailCompte);            

   }else
   {

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Detail Compte // Montant TTC <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<           
       
       BigDecimal montantHT_TMP= BigDecimal.ZERO;
       BigDecimal montantTVA_TMP= BigDecimal.ZERO;
       BigDecimal montantTTC_TMP= BigDecimal.ZERO;
       
       
      montantHT_TMP = montantTotal.setScale(2,RoundingMode.FLOOR);
      montantTVA_TMP=  montantHT_TMP.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
      montantTTC_TMP= montantTVA_TMP.add(montantHT_TMP).setScale(2,RoundingMode.FLOOR);
       
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<           
       
       BigDecimal montantHT = BigDecimal.ZERO;
       BigDecimal montantTVA= BigDecimal.ZERO;
       BigDecimal montantTTC= BigDecimal.ZERO;
       
       
      montantHT = montantTotal.add(bonLivraison.getMontant()).setScale(2,RoundingMode.FLOOR);
      
      montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);  
      
      montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
      
            bonLivraison.setMontant(montantHT);
            bonLivraison.setMontantTVA(montantTVA);
            bonLivraison.setAction(Boolean.FALSE);
            bonLivraison.setSansTVA(Boolean.FALSE);
            bonLivraison.setMontantTTC(montantTTC);
  
            bonLivraisonDAO.edit(bonLivraison);
            
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Detail Compte // Montant Debit <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<     
               



                        detailCompte = new DetailCompte();
                        
               detailCompte.setCompteFourMP(commande.getFourisseur().getCompteFourMP());
               detailCompte.setClientMP(commande.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setDateOperation(new Date());
               detailCompte.setDateBonLivraison(dateSaisie);
               
                         if (commande.getnCommande().contains("F") ){
        
                             Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_F);
                       detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1)+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      
                       }else if (commande.getnCommande().contains("D")){
                       
                             Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_D);
                       detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1)+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                           
                      }else if ( checkDd.isSelected()== true){
                      

                              detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" DD_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      
                      }else if ( checkSc.isSelected()== true){
                      

                              detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" SC_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      
                      }else{
                      
                        detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      }
              
               detailCompte.setCode(codeReception);
               detailCompte.setMontantDebit(BigDecimal.ZERO);
               detailCompte.setMontantCredit(montantTTC_TMP.setScale(2,RoundingMode.FLOOR));
        
               detailCompteDAO.add(detailCompte);
            
   }

                
        Fournisseur fournisseur = commande.getFourisseur();
        CompteFourMP compteFourMP = fournisseur.getCompteFourMP();
   

        montantDebit= montantTotal.add(compteFourMP.getMontantDebit()).setScale(2,RoundingMode.FLOOR);
        
        compteFourMP.setMontantDebit(montantDebit);
        
        BigDecimal solde = (compteFourMP.getMontantDebit().subtract(compteFourMP.getMontantCredit())).setScale(2,RoundingMode.FLOOR);
        
        compteFourMP.setSolde(solde);
        
        commande.getFourisseur().setCompteFourMP(compteFourMP);
        
        commandeDAO.edit(commande);
            
//            int Maxid = detailReceptionDAO.getMaxId();
//            numReception = (nav.generCode(Constantes.RECEPTION, Maxid));
            
           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RECEPTION_CODE);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();
//===========================================================================================================================================================================================================================================================================

  boolean valeur=false;
               
               for(int i=0;i<listeDetailCommande.size();i++){

                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if (!detailCommande.getQuantiteLivree().setScale(2).equals(BigDecimal.ZERO.setScale(2)))
                {
                    
                detailCommande.setQuantiteLivree(BigDecimal.ZERO.setScale(2));
                listeDetailCommande.set(i, detailCommande);
                detailCommandeDAO.edit(detailCommande);
                }
                
                if (!detailCommande.getQuantiteRestee().setScale(2).equals(BigDecimal.ZERO.setScale(2))){
                        
                        valeur= true;

                    }
                }
               
               if (commande.getnCommande().contains("F")){

           Sequenceur sequenceurSPF = sequenceurDAO.findByCode(Constantes.ETAT_F);
           sequenceurSPF.setValeur(sequenceurSPF.getValeur()+1);
           sequenceurDAO.edit(sequenceurSPF);
                     
                      
               }else if ( commande.getnCommande().contains("D")){
                          
           Sequenceur sequenceurSPD = sequenceurDAO.findByCode(Constantes.ETAT_D);
           sequenceurSPD.setValeur(sequenceurSPD.getValeur()+1);
           sequenceurDAO.edit(sequenceurSPD);
                          
                      }
    
                if(valeur== false){
               
                     commande=tableCommande.getSelectionModel().getSelectedItem();
         
            commande.setEtat(Constantes.ETAT_COMMANDE_RECU);
            commande.setDateRecu(new Date());

            commandeDAO.edit(commande);
                 
            
              setColumnPropertiesDetailCommande();
              loadDetail();
               
               }
               
               
               boolean existe=false;
               
               for (int i =0;i<tableDetailCommande.getItems().size();i++)
               {            
                    commande.getDetailCommandes().get(i).setQuantiteLivree(BigDecimal.ZERO.setScale(2));
                    commandeDAO.edit(commande);
                     
                    if (quantiteRestantColumn.getCellData(i)!=(BigDecimal.ZERO.setScale(2))){
                        
                        existe= true;

                    }
                    
               } 
               
               if(existe== false){
               
                     commande=tableCommande.getSelectionModel().getSelectedItem();
         
            commande.setEtat(Constantes.ETAT_COMMANDE_RECU);
            commande.setDateRecu(new Date());

            commandeDAO.edit(commande);
                 
  
              setColumnPropertiesDetailCommande();
              loadDetail();
               
               }
               
               
               
                tableDetailCommande.refresh();
                listeDetailCommande.clear();
                listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
                tableDetailCommande.setItems(listeDetailCommande);
                
               btnValider.setDisable(true);
               btnImprimer.setDisable(false);
               nLivraisonField.clear();
               checkDd.setSelected(false);
               checkSc.setSelected(false);
               magasinCombo.getSelectionModel().select(-1);
               depotCombo.getSelectionModel().select(-1);
               dateLivraisonPicker.setValue(null);
               tableDetailCommande.setEditable(true);
               



            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.COMMANDE_VALIDER);

            
            tableDetailCommande.setEditable(false);
        
    } }
    
     @FXML
    private void editCommitQuantiteLivreeColumn(CellEditEvent<DetailCommande, BigDecimal> event) throws ParseException {
        if (nLivraisonField.getText().isEmpty() || depotCombo.getSelectionModel().getSelectedItem() == null || depotCombo.getSelectionModel().getSelectedItem().isEmpty()) {

            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
            tableDetailCommande.refresh();
        } else {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    BigDecimal qteLiv = BigDecimal.ZERO;
                    BigDecimal qteRestee= BigDecimal.ZERO;
                    BigDecimal qteRecu= BigDecimal.ZERO;
                    BigDecimal calculeQuantiteRecu= BigDecimal.ZERO; 
                    BigDecimal getCalculeQuantiteRestee= BigDecimal.ZERO;
                    
                     Magasin magasin = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
                    
                ((DetailCommande) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteLivree(event.getNewValue());
               
                
                    tableDetailCommande.refresh();  

  
                 qteLiv = quantiteLivreeColumn.getCellData(event.getTablePosition().getRow());
                
                 qteRestee =  listeDetailCommande.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).getQuantiteRestee();
                
                 qteRecu =  listeDetailCommande.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).getQuantiteRecu();
                
                 calculeQuantiteRecu = qteRecu.add(qteLiv).setScale(2, RoundingMode.FLOOR);
               
                 getCalculeQuantiteRestee =  qteRestee.subtract(qteLiv).setScale(2, RoundingMode.FLOOR);
                
                 listeDetailCommande.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).setMagasin(magasin.getLibelle());
                 listeDetailCommande.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).setQuantiteRecu(calculeQuantiteRecu);
                 listeDetailCommande.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).setQuantiteRestee(getCalculeQuantiteRestee);
                
                 
                 
                setColumnProperties();

           

            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailCommande.refresh();
            }
        }
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
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        
        
        setColumnPropertiesDetailCommande();
        listeDetailCommande.clear();
        
if (tableCommande.getSelectionModel().getSelectedIndex()!=-1){
    
        commande = tableCommande.getSelectionModel().getSelectedItem();

      listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
      
        tableDetailCommande.setItems(listeDetailCommande);
        
        tableDetailCommande.setEditable(true);

        fournisseurField.setText(commande.getFourisseur().getNom());
        
        clientField.setText(commande.getClientMP().getNom());
}


    }

   
    void validationStock() {

        Depot depot = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
        Magasin magasin = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
        List<DetailReception> listDetailReceptionTmp = detailReceptionDAO.findReceptionBycode(codeReception);

        

        if (depot != null && magasin != null) {
            for (int i = 0; i < listDetailReceptionTmp.size(); i++) {

                DetailReception detailReceptionTmp = listDetailReceptionTmp.get(i);
                MatierePremier matierePremier = detailReceptionTmp.getDetailCommande().getMatierePremier();

                BigDecimal prixU = detailReceptionTmp.getDetailCommande().getPrixUnitaire();
 
                BigDecimal QteLivree = detailReceptionTmp.getQuantiteRecu();
               
                StockMP stockMP = stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasin.getId());

                if (stockMP == null) {

                    StockMP stockMPTmp = new StockMP();

                    stockMPTmp.setStock(QteLivree);
                    stockMPTmp.setMatierePremier(matierePremier);
                    stockMPTmp.setUtilisateurCreation(nav.getUtilisateur());
                    stockMPTmp.setMagasin(magasin);
                    stockMPTmp.setPrixUnitaire(prixU);

                    stockMPDAO.add(stockMPTmp);

                } else if (stockMP != null) {

                    BigDecimal Qte = stockMP.getStock();
                    
                    BigDecimal prix = stockMP.getPrixUnitaire();
 
                    BigDecimal pmc = ((QteLivree.multiply(prixU)).add(Qte.multiply(prix))).divide(QteLivree.add(Qte),2);

                    BigDecimal QteTotal = QteLivree.add(Qte).setScale(2,RoundingMode.FLOOR);
                    stockMP.setPrixUnitaire(pmc);
                    stockMP.setStock(QteTotal);

                    stockMPDAO.edit(stockMP);

                }
                
            }
        }
    }

    @FXML
    private void rechercheNumComOnKeyPressed(KeyEvent event) {
        
    
    listeCommande.clear();
   
   listeCommande.addAll(commandeDAO.findFourByRechercheNumCommande(numComRechField.getText(),Constantes.ETAT_COMMANDE_ENCOURS));
   
   tableCommande.setItems(listeCommande);
   
    }

    
    @FXML
    private void imprimerOnAction(ActionEvent event) throws ParseException {
        
          try {
                     if (nLivraisonField.getText().isEmpty() || depotCombo.getSelectionModel().getSelectedItem() == null || depotCombo.getSelectionModel().getSelectedItem().isEmpty()) {

            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
            tableDetailCommande.refresh();
        } else {
              listeDetailCommandeTMP.clear();
              
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SuiviCommandeController.class.getResource(nav.getiReportBonReception()));

              LocalDate localDate=dateLivraisonPicker.getValue();
            
                Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
                  Depot depot = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
            
            para.put("Fournisseur",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getNom());
            para.put("NumCommande",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getnCommande());
            para.put("Ville",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getVille().getLibelle());
            para.put("Client",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getClientMP().getNom());
            para.put("Depot",depot.getLibelle());
            para.put("NumLivraison",nLivraisonField.getText());
            para.put("DateLivraison",dateSaisie);
            
            for(int i=0;i<listeDetailCommande.size();i++){
            
                DetailCommande detailCommande = listeDetailCommande.get(i);
                if (!detailCommande.getQuantiteLivree().setScale(2).equals(BigDecimal.ZERO.setScale(2)))
                {
                listeDetailCommandeTMP.add(detailCommande);
                }
            }
            
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailCommandeTMP));
               JasperViewer.viewReport(jp, false);

               
            btnImprimer.setDisable(false);
            btnValider.setDisable(false);
                     }
        } catch (JRException ex) {
            Logger.getLogger(EnvoyerCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    }

    @FXML
    private void recuCommande(ActionEvent event) {
        
          tableDetailCommande.getItems().clear();
       if (tableCommande.getSelectionModel().getSelectedItem() != null) {

            commande=tableCommande.getSelectionModel().getSelectedItem();
         
            commande.setEtat(Constantes.ETAT_COMMANDE_RECU);
            commande.setDateRecu(new Date());
            commandeDAO.edit(commande);
            
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.COMMANDE_RECU); 
  
              setColumnPropertiesDetailCommande();
              loadDetail();
                      
           
       }else {
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE); 
       }
    }



    @FXML
    private void imprimerBcOnAction(ActionEvent event) {
           try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SuiviCommandeController.class.getResource(nav.getiReportBonCommande()));

            
            
            para.put("Fournisseur",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getNom());
            para.put("NumCommande",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getnCommande());
            para.put("Ville",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getVille().getLibelle());
            para.put("DateLiv",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getDateCreation());
            
    
            for (int i =0;i<listeDetailCommande.size();i++){
            
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                detailCommande.setQuantiteLivree(BigDecimal.ZERO);
            
                listeDetailCommande.set(i, detailCommande);
            }
            

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailCommande));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(SuiviCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void creationDate(ActionEvent event) throws ParseException {
         
        
        
          LocalDate localDate=dateCreationPicker.getValue();
             if(localDate!=null){
                 
             Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
             
            
              listeCommande.clear();
   
   listeCommande.addAll(commandeDAO.findByDateCommande(dateSaisie,Constantes.ETAT_COMMANDE_ENCOURS));
   
   tableCommande.setItems(listeCommande);
             }
             
        
    }

 

    @FXML
    private void depotComboOnAction(ActionEvent event) {
        
              magasinCombo.getItems().clear();
            Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
            
             if(depot!=null){
                 
            List<Magasin> listMagasin = depot.getListMagasin();
            listMagasin.stream().map((magasin) -> {
                magasinCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
    }
    }



    @FXML
    private void checkDdOnAction(ActionEvent event) {
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        
        numComRechField.clear();
        dateCreationPicker.setValue(null);
        tableCommande.getItems().clear();
        tableDetailCommande.getItems().clear();
        fournisseurField.clear();
        clientField.clear();
        nLivraisonField.clear();
        checkDd.setSelected(false);
        checkSc.setSelected(false);
        dateLivraisonPicker.setValue(null);
        magasinCombo.getSelectionModel().select(-1);
        depotCombo.getSelectionModel().select(-1);
        
          setColumnProperties();
        loadDetail();
                
                
              
 
           
                
    }

   
  



    
  


  

      





 

   
 
}
