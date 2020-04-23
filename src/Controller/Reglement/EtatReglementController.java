
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Controller.RetourGratuite.RegularisationDeDifferenceDePrixController;
import Controller.commande.EnvoyerCommandeController;
import Utils.Constantes;
import dao.Entity.BonLivraison;
import dao.Entity.BonRetour;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.CompteFourMP;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailBonRetour;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCompte;
import dao.Entity.DetailReception;
import dao.Entity.Fournisseur;
import dao.Entity.Reglement;
import dao.Entity.Sequenceur;
import dao.Entity.StockMP;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.BonRetourDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.CompteFourMPDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailBonRetourDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailReceptionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.ReglementDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.StockMPDAO;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.BonRetourDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.CompteFourMPDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailBonRetourDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailReceptionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.ReglementDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.StockMPDAOImpl;
import function.navigation;
import java.io.IOException;
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
import java.util.Optional;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
public class EtatReglementController implements Initializable {
    
    
    @FXML
    private TableView<BonLivraison> tableBonLivraison;
    @FXML
    private TableColumn<BonLivraison, BigDecimal> montantColumn;
    @FXML
    private TableColumn<BonLivraison, String> etatColumn;
    @FXML
    private TableColumn<BonLivraison, String> numLivraisonColumn;
    @FXML
    private TableColumn<BonLivraison, Date> datePaiementColumn;
    @FXML
    private TableColumn<BonLivraison, Integer> nombreJourColumn;
    @FXML
    private TableColumn<BonLivraison, Date> dateLivraisonColumn;
    @FXML
    private TableColumn<BonLivraison, Boolean> actionColumn;
    @FXML
    private TableColumn<BonLivraison, Boolean> sansTvaColumn;
    @FXML
    private TableColumn<BonLivraison, BigDecimal> montantTVAColumn;
    
    @FXML
    private TableColumn<BonLivraison, BigDecimal> montantTTCColumn;
    @FXML
    private TableView<DetailBonLivraison> tableDetailBonLivraison;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> prixColumn;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> totalColumn;
    @FXML
    private TableColumn<DetailBonLivraison, String> codeMPColumn;
    @FXML
    private TableColumn<DetailBonLivraison, String> libelleColumn;
    
     @FXML
    private CheckBox offreCheck;
    @FXML
    private DatePicker dateCreation;
    @FXML
    private Button btnRegler;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private TextField numFacture;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField montantTotalField;
    @FXML
    private Label monHT;
    @FXML
    private Label monTVA;
    @FXML
    private Label monTTC;
    @FXML
    private TextField numLivRech;
    @FXML
    private Button btnOffre;
    @FXML
    private TextField prixTxt;
    @FXML
    private TextField qteTxt;
    @FXML
    private TextField totalTxt;
    @FXML
    private Button btnImprimer;
    @FXML
    private DatePicker dateLivraison;
    
    
    
    public   Date dateNow =null;
   
   private final  ObservableList<BonLivraison> listeBonLivraisonTMP = FXCollections.observableArrayList();
  private final  ObservableList<BonLivraison> listeBonLivraison = FXCollections.observableArrayList();
  private final  ObservableList<DetailBonLivraison> listeDetailBonLivraison = FXCollections.observableArrayList();
  private final  ObservableList<DetailBonRetour> listeDetailBonRetour = FXCollections.observableArrayList();

  BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
  CompteFourMPDAO compteFourMPDAO = new CompteFourMPDAOImpl();
  BonRetourDAO bonRetourDAO = new BonRetourDAOImpl();
  CommandeDAO commandeDAO = new CommandeDAOImpl();
  StockMPDAO stockMPDAO = new StockMPDAOImpl();
  DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
  DetailBonRetourDAO detailBonRetourDAO = new DetailBonRetourDAOImpl();
  DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
  SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
  BonLivraison bonLivraison = new BonLivraison();
  Fournisseur fournisseur =new Fournisseur();
//  DetailBonRetour detailBonRetour =new DetailBonRetour();
  ClientMP clientMP =new ClientMP();
  BonRetour bonRetour = new BonRetour();
  
    DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
   DetailReceptionDAO  detailReceptionDAO = new DetailReceptionDAOImpl();
   ReglementDAO reglementDAO= new ReglementDAOImpl();
   navigation nav = new navigation();   
   Commande commande = new Commande();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
     private Map<String,ClientMP> mapClientMP=new HashMap<>();
     
     
         String numCommande="";
    String valeur="";
    String codeOffre = "";
    
 BigDecimal newMontant= BigDecimal.ZERO;

  BigDecimal sommeTotal= BigDecimal.ZERO;
  BigDecimal montantTotal=BigDecimal.ZERO;
  BigDecimal montantTVA=BigDecimal.ZERO;
  BigDecimal montantTTC=BigDecimal.ZERO;
  BigDecimal montantTotalNonPayer=BigDecimal.ZERO;
  BigDecimal montantTVANonPayer=BigDecimal.ZERO;
  BigDecimal montantTTCNonPayer=BigDecimal.ZERO;
  BigDecimal montantTotalNonRegler=BigDecimal.ZERO;
  BigDecimal montantTVANonRegler=BigDecimal.ZERO;
  BigDecimal montantTTCNonRegler=BigDecimal.ZERO;


    static final long UNE_HEURE = 60*60*1000L;
    
   
  int pos = 0;

    /**
     * Initializes the controller class.
     */
    
    //--------------------------------------- Methode date paiement + date now = nombre de jour restant -------------------------------------------------------------------------------
      
    public int daysBetween(Date d1, Date d2){
             return (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
     }
   
  //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------           
    
    void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OFFRE_CODE);
          codeOffre = Constantes.OFFRE_CODE+" "+(sequenceur.getValeur()+1);
   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 

        numLivRech.setDisable(true);
        dateLivraison.setDisable(true);

        Incrementation ();
    
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


        btnRegler.setDisable(true);
        
     
    
    }    
    

    
    void setColumnProperties() {

    
      numLivraisonColumn.setCellValueFactory(new PropertyValueFactory<>("livraisonFour"));
      montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
      
      
      etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
      
      datePaiementColumn.setCellValueFactory(new PropertyValueFactory<BonLivraison, Date>("datePaiement"));
       setColumnTextFieldConverterDate(getConverterDate(),datePaiementColumn);
      
      nombreJourColumn.setCellValueFactory(new PropertyValueFactory<>("nombreJour"));
      dateLivraisonColumn.setCellValueFactory(new PropertyValueFactory<BonLivraison, Date>("dateLivraison"));

      montantTVAColumn.setCellValueFactory(new PropertyValueFactory<>("montantTVA"));

      montantTTCColumn.setCellValueFactory(new PropertyValueFactory<>("montantTTC"));

       sansTvaColumn.cellValueFactoryProperty();
          sansTvaColumn.setCellValueFactory((cellData) -> {
          BonLivraison cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getSansTVA());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setSansTVA(newvalue));
              
              return property; 
          });
          sansTvaColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          sansTvaColumn.setEditable(true);
          
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
   
            actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          BonLivraison cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionColumn.setEditable(true);
      
          
          tableBonLivraison.setEditable(true);
     

    }


        public static void setColumnTextFieldConverterDate(StringConverter converter, TableColumn... columns) {

        for (TableColumn tableColumn : columns) {

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        }
    }

        
    private StringConverter getConverterDate() {
        StringConverter<Date> converter = new StringConverter<Date>() {
            @Override
            public Date fromString(String string) {

                try {
                    
             Date valeur;      
             valeur = new Date(string);
                    return valeur;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString(Date object) {

                if (object == null) {
                    return null;
                }
                return String.valueOf(object);
            }
        };

        return converter;
    }

    void loadDetailBonLiv(){
    
       listeDetailBonLivraison.clear();
       listeDetailBonLivraison.addAll(detailBonLivraisonDAO.findDetaiBonLivraisonBycode(tableBonLivraison.getSelectionModel().getSelectedItem().getLivraisonFour(),tableBonLivraison.getSelectionModel().getSelectedItem().getNumCommande()));
       tableDetailBonLivraison.setItems(listeDetailBonLivraison);
    }
    
       
       
    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        


        if (tableBonLivraison.getSelectionModel().getSelectedIndex()!=-1){
            
            listeDetailBonLivraison.clear();
          
       listeDetailBonLivraison.addAll(detailBonLivraisonDAO.findDetaiBonLivraisonBycode(tableBonLivraison.getSelectionModel().getSelectedItem().getLivraisonFour(),tableBonLivraison.getSelectionModel().getSelectedItem().getNumCommande() ));


//              for( int i = 0;i<listeDetailBonLivraison.size() ;i++ ){
//              
//                  DetailBonLivraison detailBonLivraison = listeDetailBonLivraison.get(i);
//                  
//                  if (detailBonLivraison.getQuantite().equals(BigDecimal.ZERO.setScale(2))){
//                  
//                  detailBonLivraisonDAO.delete(detailBonLivraison);
//                  }
//              
//              }
         
       loadDetailBonLiv();
       setColumnPropertiesDetailReception();
       tableDetailBonLivraison.setEditable(true);
    }
         
       
    }
        void setColumnPropertiesDetailReception(){

               codeMPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonLivraison, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });

               
               libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonLivraison, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });
           
             qteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });
           

           
             prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
           
               setColumnTextFieldConverter(getConverter(), prixColumn);
           
             totalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal> p) {
                    
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
    private void reglerOnAction(ActionEvent event) throws ParseException {
        
         boolean  variable =false;  

            if (numFacture.getText().equalsIgnoreCase("") || fourCombo.getSelectionModel().getSelectedItem() == null || fourCombo.getSelectionModel().getSelectedItem().isEmpty() ) {
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                
            } else{ 
        
           montantTotal=BigDecimal.ZERO;
      for( int rows = 0;rows<tableBonLivraison.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
             
    
                 variable = true; 
             BonLivraison bonLivraisonTmp= tableBonLivraison.getItems().get(rows);
                 fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
               clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());

      
            
            if (bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_BL)){
            bonLivraisonTmp.setEtat(Constantes.ETAT_A_REGLE);
            }else if (bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_RTR) && bonLivraisonTmp.getEtat().equals(Constantes.ETAT_NON_REGLE) || bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_MNQ) && bonLivraisonTmp.getEtat().equals(Constantes.ETAT_NON_REGLE)  ) {
            bonLivraisonTmp.setEtat(Constantes.ETAT_A_REGLE);
            }else if (bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_RTR) && bonLivraisonTmp.getEtat().equals(Constantes.ETAT_NON_PAIEMENT) || bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_MNQ) && bonLivraisonTmp.getEtat().equals(Constantes.ETAT_NON_PAIEMENT) ) {
            bonLivraisonTmp.setEtat(Constantes.ETAT_A_PAYER);
            }else {
            break;
            }
            
            bonLivraisonTmp.setNumFacture(numFacture.getText());
            bonLivraisonTmp.setAction(false);
            bonLivraisonTmp.setSansTVA(Boolean.FALSE);
            bonLivraisonDAO.edit(bonLivraisonTmp);
         
         }
    }
       tableBonLivraison.refresh();
   
               
      if (variable == false){
           nav.showAlert(Alert.AlertType.ERROR, "Atention", null, Constantes.REMPLIR_COCHE);
   
      }else{
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.REGLER_ENREGISTREMENT);

             loadDetailCombo();
             listeDetailBonLivraison.clear();
             numFacture.clear();
             montantTotalField.clear();
             monHT.setText("");
             monTVA.setText("");
             monTTC.setText("");
             numLivRech.clear();
             dateLivraison.setValue(null);
             
    }}}

    
    
    @FXML
    private void fournisseurOnAction(ActionEvent event) {

    }




        void loadDetailCombo() throws ParseException{
            
          listeBonLivraison.clear();
         
            if (fourCombo.getSelectionModel().getSelectedIndex() == -1 && clientCombo.getSelectionModel().getSelectedIndex() == -1 && numLivRech.getText().equals("") && dateLivraison.getValue() == null ){
            
                
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
            } else if (fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && numLivRech.getText().equals("") && dateLivraison.getValue() == null ){
          
          listeBonLivraison.addAll(bonLivraisonDAO.findFourByRechercheNomReglement(fourCombo.getSelectionModel().getSelectedItem(),clientCombo.getSelectionModel().getSelectedItem(),Constantes.ETAT_NON_REGLE,Constantes.ETAT_NON_PAIEMENT,Constantes.ETAT_OULMES));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && !numLivRech.getText().equals("") && dateLivraison.getValue() == null ){ 
          
            listeBonLivraison.addAll(bonLivraisonDAO.findBonLivraisonByRechercheNumLiv(numLivRech.getText(),clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_NON_REGLE, Constantes.ETAT_NON_PAIEMENT ,Constantes.ETAT_OULMES));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && numLivRech.getText().equals("") && dateLivraison.getValue() != null ){ 
              
               LocalDate localDate=dateLivraison.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
            listeBonLivraison.addAll(bonLivraisonDAO.findBonLivraisonByRechercheDateLiv(date,clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_NON_REGLE, Constantes.ETAT_NON_PAIEMENT ,Constantes.ETAT_OULMES));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && !numLivRech.getText().equals("") && dateLivraison.getValue() != null ){ 
              
               LocalDate localDate=dateLivraison.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
            listeBonLivraison.addAll(bonLivraisonDAO.findBonLivraisonByRechercheDateLivAndNumLiv(date,numLivRech.getText(),clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_NON_REGLE, Constantes.ETAT_NON_PAIEMENT ,Constantes.ETAT_OULMES));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }
 }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }


    void refrech() throws ParseException{
    
     if (fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                clientCombo.getSelectionModel().getSelectedIndex()== -1 || 
                clientCombo.getSelectionModel().getSelectedItem().equals("") )
        {
        nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.VERIFIER_FOURNISSEUR_CLIENT);
        }else{
      
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = simpleDateFormat.parse(Constantes.DATE);
         
            setColumnProperties();

            loadDetailCombo();
            
             tableBonLivraison.setEditable(true);
             dateLivraisonColumn.setEditable(true);
            


          tableBonLivraison.setRowFactory(new Callback<TableView<BonLivraison>, TableRow<BonLivraison>>(){
            //There can define some colors.
      
            @Override
            public TableRow<BonLivraison> call(TableView<BonLivraison> param) {
                final TableRow<BonLivraison> row = new TableRow<BonLivraison>() {
                    @Override
                    protected void updateItem(BonLivraison item, boolean empty) {
                        super.updateItem(item, empty);
                        
                     
                        if (item == null || empty) {
				setText(null);
				setStyle("");
                                
			} else {
          
                            
                       if (item.getDatePaiement().equals(date)){

			 setStyle("-fx-background-color: green");
                        }}
                    }
                };
                return row;
            }
        });
            

            
          nombreJourColumn.setCellFactory(column -> {
			return new TableCell<BonLivraison, Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty);
					
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						
						setText(String.valueOf(item));
						
                                                
						if (item <=7 && item >=0) {
							setTextFill(Color.WHITE);
							setStyle("-fx-background-color: red");
						} else {
							setTextFill(Color.BLACK);
							setStyle("");
						}
					}
				}
			};
		});

        }
    }
    
    @FXML
    private void tableDetailClicked(MouseEvent event) {
        
        
        BonLivraison bonLivraison = tableBonLivraison.getSelectionModel().getSelectedItem();
        
        DetailBonLivraison detailBonLivraisonsTmp = tableDetailBonLivraison.getSelectionModel().getSelectedItem();
        
//         detailBonRetour = detailBonRetourDAO.findDetailBonRetourByDetailBonLivraison(bonLivraison.getNumCommande(), bonLivraison.getLivraisonFour(),detailBonLivraisonsTmp.getMatierePremier().getId());

        
            qteTxt.setText(detailBonLivraisonsTmp.getQuantite()+"");
            prixTxt.setText(detailBonLivraisonsTmp.getPrix()+"");
            

            totalTxt.clear();
  
       }
    
    @FXML
    private void datePaiementOnEditCommit(CellEditEvent<BonLivraison, Date> event) throws ParseException {
        
        
        System.out.println("event.getNewValue() "+event.getNewValue());
        
            ((BonLivraison) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setDatePaiement(event.getNewValue());
            
            
            
            
              Date  datePaiement = datePaiementColumn.getCellData(event.getTablePosition().getRow());

              
              if (datePaiement== null){
                  
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.ERREUR_DATE , Constantes.VERIFICATION_DATE_SAISIE );
              
              }else{
              
              tableBonLivraison.refresh();
              BonLivraison bonLivraison = listeBonLivraison.get(event.getTablePosition().getRow());
              bonLivraison.setDatePaiement(datePaiement);
              listeBonLivraison.set(event.getTablePosition().getRow(), bonLivraison);
                
                bonLivraisonDAO.edit(bonLivraison);
                
                refrech();
              
              }
      
              
    }   


    @FXML
    private void recherchMouseClicked(MouseEvent event) throws ParseException {
        
        numLivRech.setDisable(false);
        dateLivraison.setDisable(false);
        refrech();
        
    }

    @FXML
    private void calculeMouseClicked(MouseEvent event) {

            montantTotal=BigDecimal.ZERO;
            montantTVA=BigDecimal.ZERO;
            montantTTC=BigDecimal.ZERO;
            montantTotalNonPayer=BigDecimal.ZERO;
            montantTVANonPayer=BigDecimal.ZERO;
            montantTTCNonPayer=BigDecimal.ZERO;
            montantTotalNonRegler=BigDecimal.ZERO;
            montantTVANonRegler=BigDecimal.ZERO;
            montantTTCNonRegler=BigDecimal.ZERO;
            
            
           for( int rows = 0;rows<tableBonLivraison.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
        
             if (etatColumn.getCellData(rows).equals(Constantes.ETAT_NON_PAIEMENT)){

                  if (sansTvaColumn.getCellData(rows).booleanValue()==true){
                  
                   montantTotalNonPayer= montantTotalNonPayer.add(montantColumn.getCellData(rows));
                   
                   montantTVANonPayer = montantTotalNonPayer;
                   
                   montantTTCNonPayer= montantTotalNonPayer;
                  
                  }else{
             montantTotalNonPayer= montantTotalNonPayer.add(montantColumn.getCellData(rows));

             montantTVANonPayer = montantTVANonPayer.add(montantTVAColumn.getCellData(rows));
             
             montantTTCNonPayer= montantTTCNonPayer.add(montantTTCColumn.getCellData(rows));
                  }
             
             } else{

                 if (sansTvaColumn.getCellData(rows).booleanValue()==true){
                  
                   montantTotalNonRegler= montantTotalNonRegler.add(montantColumn.getCellData(rows));
                   
                   montantTVANonRegler = montantTotalNonRegler;
                   
                   montantTTCNonRegler = montantTotalNonRegler;
                  
                  }else{
                     
             montantTotalNonRegler= montantTotalNonRegler.add(montantColumn.getCellData(rows));

             montantTVANonRegler = montantTVANonRegler.add(montantTVAColumn.getCellData(rows));
             
             montantTTCNonRegler = montantTTCNonRegler.add(montantTTCColumn.getCellData(rows));
                  }
           
               
             }
        } 
    }
           
           montantTotal= montantTotalNonRegler.subtract(montantTotalNonPayer);
           montantTVA= montantTVANonRegler.subtract(montantTVANonPayer);
           montantTTC= montantTTCNonRegler.subtract(montantTTCNonPayer);
           
           
              DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
    
    monHT.setText(df.format(montantTotal));
    monTVA.setText(df.format(montantTVA));
    monTTC.setText(df.format(montantTTC));
           
         montantTotalField.setText(df.format(montantTTC));  
         
         if(!montantTTC.equals(BigDecimal.ZERO)){
         
         btnRegler.setDisable(false);
         }
         
    }

    @FXML
    private void rechercheNumLivKeyPressed(KeyEvent event) {
        
//    ObservableList<BonLivraison> listeBonLivraisonRech=FXCollections.observableArrayList();
//                   
//    listeBonLivraisonRech.clear();
//   
//   listeBonLivraisonRech.addAll(bonLivraisonDAO.findBonLivraisonByRechercheNumLiv(numLivRech.getText(),clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_NON_REGLE, Constantes.ETAT_NON_PAIEMENT));
//   
//   tableBonLivraison.setItems(listeBonLivraisonRech);
    
        
    }

    
  

    @FXML
    private void offreOnAction(ActionEvent event) throws ParseException {
        
        if(offreCheck.isSelected()== true){
        
           if (clientCombo.getSelectionModel().getSelectedItem().isEmpty() || fourCombo.getSelectionModel().getSelectedItem().isEmpty() ) {
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
            } else{ 
               
                  LocalDate localDate=dateCreation.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
               
               BonLivraison bonLivraison = tableBonLivraison.getSelectionModel().getSelectedItem();
               
               bonRetour.setFournisseur(bonLivraison.getFourisseur());
               bonRetour.setDateCreation(date);
               bonRetour.setNumCommande(bonLivraison.getNumCommande());
               bonRetour.setDetailBonRetour(listeDetailBonRetour);
               bonRetour.setNumRetour(codeOffre);
               bonRetour.setNumLivraison(bonLivraison.getLivraisonFour());
               bonRetour.setUtilisateurCreation(nav.getUtilisateur());
               bonRetour.setType(Constantes.ETAT_OFFRE);
               bonRetour.setMontantTotal(newMontant);
                       
               bonRetourDAO.add(bonRetour);
               
               
//==============================================================================================================================================================================================================================================================

        BigDecimal montantHT= BigDecimal.ZERO;
        BigDecimal montantTVA =BigDecimal.ZERO;
        BigDecimal montantTTC =BigDecimal.ZERO;


     DetailCompte detailCompte = new DetailCompte();
              
             Commande commandeTMP = commandeDAO.findCommandeByNumCommande(bonLivraison.getNumCommande(), Constantes.ETAT_COMMANDE_SUPPRIMER); 
              
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
              
               montantHT = new BigDecimal(totalTxt.getText());
               montantTVA = montantHT.multiply(Constantes.TAUX_TVA_20);
               montantTTC = montantHT.add(montantTVA);
               
               detailCompte.setDateOperation(new Date());
               detailCompte.setDesignation(Constantes.OFFRE+Constantes.SUR_COMMANDE_N+bonLivraison.getNumCommande()+"_"+Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+bonLivraison.getLivraisonFour());
               detailCompte.setMontantDebit(BigDecimal.ZERO);
               detailCompte.setDateBonLivraison(date);
               detailCompte.setCode(codeOffre);
               detailCompte.setMontantCredit(montantTTC.multiply(new BigDecimal(-1)));
               detailCompte.setClientMP(commandeTMP.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setCompteFourMP(commandeTMP.getFourisseur().getCompteFourMP());
               detailCompteDAO.add(detailCompte);   


               if (bonLivraison.getMontant()== BigDecimal.ZERO.setScale(2) && bonLivraison.getMontantTVA()== BigDecimal.ZERO.setScale(2) && bonLivraison.getMontantTTC()== BigDecimal.ZERO.setScale(2)){
               
               bonLivraison.setEtat(Constantes.ETAT_OFFRE);
               bonLivraison.setAction(false);
               bonLivraison.setSansTVA(false);
 
               bonLivraisonDAO.edit(bonLivraison);
               
               }
               
               
               bonRetour = new BonRetour();
               listeDetailBonRetour.clear();
               newMontant= BigDecimal.ZERO;
               
                numLivRech.setDisable(false);
                dateLivraison.setDisable(false);
                btnOffre.setDisable(true);
                offreCheck.setSelected(false);
                qteTxt.setDisable(true);
                
                monHT.setText("");
                monTTC.setText("");
                monTVA.setText("");
                
                qteTxt.clear();
                prixTxt.clear();
                totalTxt.clear();
                
                dateCreation.setValue(null);
                
                montantTotalField.clear();
                numFacture.clear();
                numLivRech.clear();
                dateLivraison.setValue(null);
                
                refrech();
                
                nav.showAlert(Alert.AlertType.CONFIRMATION, "Attention", null,Constantes.TRAITEMENT_ENREGESTRE);
                
                Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.OFFRE_CODE);
                sequenceur.setValeur(sequenceur.getValeur()+1);
                sequenceurDAO.edit(sequenceur);
                Incrementation();

           }
        }else{
         boolean variable =false;  
            if (numFacture.getText().equalsIgnoreCase("") || fourCombo.getSelectionModel().getSelectedItem() == null || fourCombo.getSelectionModel().getSelectedItem().isEmpty()) {
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                
                
            } else{ 
                
 
      for( int rows = 0;rows<tableBonLivraison.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
             

             BonLivraison bonLivraisonTmp= tableBonLivraison.getItems().get(rows);
               fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
               clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
             
            
            bonLivraisonTmp.setNumFacture(numFacture.getText());
            bonLivraisonTmp.setEtat(Constantes.ETAT_OFFRE);
            bonLivraisonTmp.setAction(false);
            bonLivraisonTmp.setSansTVA(false);
            numCommande= bonLivraisonTmp.getNumCommande();
            bonLivraisonDAO.edit(bonLivraisonTmp);
            

             variable = true;
       }
    }
       tableBonLivraison.refresh();
   
               
      if (variable == false){
           nav.showAlert(Alert.AlertType.ERROR, "Atention", null, Constantes.REMPLIR_COCHE);
           
      }else{
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.OFFRE_ENREGISTREMENT);

             loadDetailCombo();
             listeDetailBonLivraison.clear();
             numFacture.clear();
             montantTotalField.clear();
             monHT.setText("");
             monTVA.setText("");
             monTTC.setText("");
             numLivRech.clear();
             dateLivraison.setValue(null);
             
    }}
    }
    }

    private void actualiserMouseClicked(MouseEvent event) throws ParseException {
        
     refrech();
//     tableDetailBonLivraison.getItems().clear();
     tableDetailBonLivraison.refresh();
    }


    @FXML
    private void calculeTotalMouseClicked(MouseEvent event) throws ParseException {
        
            if (!qteTxt.getText().equals("") || !prixTxt.getText().equals("")){
            
                if(offreCheck.isSelected()== true){
                 
                    if (tableDetailBonLivraison.getSelectionModel().getSelectedIndex()!=-1){ 
                        
                    BigDecimal valeur = BigDecimal.ZERO;
                    BigDecimal OldQte = BigDecimal.ZERO;
                    BigDecimal NewQte = BigDecimal.ZERO;
                    BigDecimal OffreQte = BigDecimal.ZERO;
                    BigDecimal montantOffre = BigDecimal.ZERO;
                    BigDecimal prixOffre = BigDecimal.ZERO;
                    
                DetailBonLivraison detailBonLivraison = tableDetailBonLivraison.getSelectionModel().getSelectedItem();
                
                OldQte= detailBonLivraison.getQuantite();
                OffreQte= new BigDecimal(qteTxt.getText());
                NewQte = OldQte.subtract(OffreQte);

                prixOffre = detailBonLivraison.getPrix();
                montantOffre = OffreQte.multiply(prixOffre);
                
                newMontant = newMontant.add(montantOffre);
                
                DetailBonRetour detailBonRetour = new DetailBonRetour();
                
                detailBonRetour.setQuantiteRetour(OffreQte);
                detailBonRetour.setPrixUnitaire(prixOffre);
                detailBonRetour.setMatierePremier(detailBonLivraison.getMatierePremier());
                detailBonRetour.setMontant(montantOffre);
                detailBonRetour.setBonRetour(bonRetour);
                detailBonRetour.setUtilisateurCreation(nav.getUtilisateur());
                
                listeDetailBonRetour.add(detailBonRetour);
                
//===================================================================================================================================================================================================================================================================

        valeur = (new BigDecimal(prixTxt.getText()).multiply(NewQte));
        
        
        totalTxt.setText(montantOffre.setScale(2,RoundingMode.FLOOR)+"");
        
       detailBonLivraison.setQuantite(NewQte);
       detailBonLivraison.setPrix(new BigDecimal(prixTxt.getText()));
       detailBonLivraison.setMontant(valeur);
      
       listeDetailBonLivraison.set(tableDetailBonLivraison.getSelectionModel().getSelectedIndex(), detailBonLivraison);
       
       detailBonLivraisonDAO.edit(detailBonLivraison);

//===================================================================================================================================================================================================================================================================
  
        BigDecimal montantHT= BigDecimal.ZERO;
        BigDecimal montantTVA =BigDecimal.ZERO;
        BigDecimal montantTTC =BigDecimal.ZERO;
        BigDecimal somme= BigDecimal.ZERO;
        

          BonLivraison bonLivraison = tableBonLivraison.getSelectionModel().getSelectedItem();


                for(int i=0;i<listeDetailBonLivraison.size(); i++ ){

          montantHT = listeDetailBonLivraison.get(i).getTotal().setScale(2,RoundingMode.FLOOR);

          somme = somme.add(montantHT);

        }

             montantTVA =somme.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
             montantTTC =somme.add(montantTVA).setScale(2,RoundingMode.FLOOR) ;
        
          bonLivraison.setMontant(somme);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          
        bonLivraisonDAO.edit(bonLivraison);
                    
 
        tableDetailBonLivraison.setItems(listeDetailBonLivraison);
        
        btnOffre.setDisable(false);
        
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Attention", null,Constantes.TRAITEMENT_ENREGESTRE);
                }else{
                        
                     nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.SELECTIONNER_UNE_LIGNE);
                    
                    }
                }else{
                   
                      BigDecimal montantHT =BigDecimal.ZERO;
                    for (int i = 0; i < listeDetailBonLivraison.size(); i++) {
                        
                        DetailBonLivraison detailBonLivraisonsTmp = listeDetailBonLivraison.get(i);
                        
                       montantHT= montantHT.add(detailBonLivraisonsTmp.getMontant());
                       
                        detailBonLivraisonDAO.edit(detailBonLivraisonsTmp);
                    }

//_______________________________________________________________________________________________________________________________________________________________________

        BigDecimal montantTVA =BigDecimal.ZERO;
        BigDecimal montantTTC =BigDecimal.ZERO;

          BonLivraison bonLivraison = tableBonLivraison.getSelectionModel().getSelectedItem();

       

             montantTVA =montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
             montantTTC =montantHT.add(montantTVA).setScale(2,RoundingMode.FLOOR) ;
        
          bonLivraison.setMontant(montantHT);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          
        bonLivraisonDAO.edit(bonLivraison);

//______________________________________________________________________________________________________________________________________________________________________________________________________________________________

                      String designation = Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+bonLivraison.getLivraisonFour()+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+bonLivraison.getNumCommande();

     
         DetailCompte detailCompte = detailCompteDAO.findByDetailCompte(designation);

        detailCompte.setMontantCredit(montantTTC);

        detailCompteDAO.edit(detailCompte);

       loadDetailBonLiv();
       loadDetailCombo();

                }
        }else {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.VERIFIER_QTE_PRIX);
        }
        
    }

    @FXML
    private void offreCheckOnAction(ActionEvent event) {
        if (offreCheck.isSelected()== true){
        qteTxt.setDisable(false);
        }else {
        qteTxt.setDisable(true);
        }
    }



    @FXML
    private void imprimerEtatOnAction(ActionEvent event) {
        
           listeBonLivraisonTMP.clear();
        
          for( int rows = 0;rows<tableBonLivraison.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
             
              BonLivraison bonLivraison = tableBonLivraison.getItems().get(rows);
              
             if (etatColumn.getCellData(rows).equals(Constantes.ETAT_A_PAYER)){
             
                 bonLivraison.setMontant(bonLivraison.getMontant().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
                 bonLivraison.setMontantTVA(bonLivraison.getMontantTVA().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
                 bonLivraison.setMontantTTC(bonLivraison.getMontantTTC().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
                 
             }

             listeBonLivraisonTMP.add(bonLivraison); 
         }
          }
        
                    try {
                        
                        if (listeBonLivraisonTMP.size()!= 0){
                            
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(EtatReglementController.class.getResource(nav.getiReportEtatReglement()));
                      
            para.put("MontantHT",monHT.getText());
            para.put("MontantTVA",monTVA.getText());
            para.put("MontantTTC",monTTC.getText());
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeBonLivraisonTMP));
               JasperViewer.viewReport(jp, false);
                          }
                             for( int rows = 0;rows<tableBonLivraison.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
             
             BonLivraison bonLivraison = tableBonLivraison.getItems().get(rows); 
             
                     if (etatColumn.getCellData(rows).equals(Constantes.ETAT_A_PAYER)){
             
                 bonLivraison.setMontant(bonLivraison.getMontant().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
                 bonLivraison.setMontantTVA(bonLivraison.getMontantTVA().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
                 bonLivraison.setMontantTTC(bonLivraison.getMontantTTC().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
             }
             
         }
          }
        } catch (JRException ex) {
            Logger.getLogger(EtatReglementController.class.getName()).log(Level.SEVERE, null, ex);
        }            
        
    }


     @FXML
    private void selectionnerToutMouseClicked(MouseEvent event) {
        
            
        ObservableList<BonLivraison> list=tableBonLivraison.getItems();
        
        for (Iterator<BonLivraison> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        tableBonLivraison.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutMouseClicked(MouseEvent event) {
           ObservableList<BonLivraison> list=tableBonLivraison.getItems();
        
        for (Iterator<BonLivraison> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }

        tableBonLivraison.refresh(); 
        
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) throws ParseException {
        
 
            listeBonLivraison.clear();
             listeDetailBonLivraison.clear();
             numFacture.clear();
             montantTotalField.clear();
             monHT.setText("");
             monTVA.setText("");
             monTTC.setText("");
             numLivRech.clear();
             dateLivraison.setValue(null);
             clientCombo.getSelectionModel().clearSelection();
               fourCombo.getSelectionModel().clearSelection();
        
    }

    @FXML
    private void imprimerOffreMouseClicked(MouseEvent event) throws ParseException {
        
           BonLivraison bonLivraison = tableBonLivraison.getSelectionModel().getSelectedItem();

            DetailBonLivraison detailBonLivraison = tableDetailBonLivraison.getSelectionModel().getSelectedItem();
            
                LocalDate localDate=dateCreation.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           
          try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(EtatReglementController.class.getResource(nav.getiReportConsultationOffre()));


            para.put("dateOffre",date);
            para.put("qteOffre",new BigDecimal(qteTxt.getText()));
            para.put("prixOffre",new BigDecimal(prixTxt.getText()));
            para.put("totalOffre",new BigDecimal(totalTxt.getText()));
            para.put("numBl",bonLivraison.getLivraisonFour());
            para.put("codeMp",detailBonLivraison.getMatierePremier().getCode());
            para.put("libelle",detailBonLivraison.getMatierePremier().getNom());
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailBonLivraison));
     
               JasperViewer.viewReport(jp, false);
               
               
            
        } catch (JRException ex) {
            Logger.getLogger(EtatReglementController.class.getName()).log(Level.SEVERE, null, ex);
        }   

        
    }


    @FXML
    private void prixUnitOnEditCommit(CellEditEvent<DetailBonLivraison, BigDecimal> event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    BigDecimal prix = BigDecimal.ZERO;
                    BigDecimal qte= BigDecimal.ZERO;
                    BigDecimal calculeTotal= BigDecimal.ZERO; 
                
                    

                    
                ((DetailBonLivraison) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setPrix(event.getNewValue());
               
                
                    tableDetailBonLivraison.refresh();  

  
                 prix = prixColumn.getCellData(event.getTablePosition().getRow());
                
                 qte =  listeDetailBonLivraison.get(tableDetailBonLivraison.getSelectionModel().getSelectedIndex()).getQuantite();

                 calculeTotal = qte.multiply(prix).setScale(2, RoundingMode.FLOOR);
               

                 listeDetailBonLivraison.get(tableDetailBonLivraison.getSelectionModel().getSelectedIndex()).setPrix(prix);
                 listeDetailBonLivraison.get(tableDetailBonLivraison.getSelectionModel().getSelectedIndex()).setMontant(calculeTotal);
                 
                 
                setColumnProperties();

           

            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailBonLivraison.refresh();
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
 
  


}

