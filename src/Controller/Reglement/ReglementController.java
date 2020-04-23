/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import static Controller.Reglement.EtatReglementController.setColumnTextFieldConverter;
import Utils.Constantes;
import dao.Entity.BonLivraison;
import dao.Entity.BonRetour;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.CompteFourMP;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCompte;
import dao.Entity.Fournisseur;
import dao.Entity.Magasin;
import dao.Entity.Reglement;
import dao.Entity.Sequenceur;
import dao.Entity.StockMP;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.BonRetourDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.CompteFourMPDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailReceptionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MagasinDAO;
import dao.Manager.ReglementDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.StockMPDAO;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.BonRetourDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CompteFourMPDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailReceptionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
public class ReglementController implements Initializable {

    @FXML
    private TableView<BonLivraison> tableBonLivraison;
    @FXML
    private TableColumn<BonLivraison, BigDecimal> montantColumn;
    @FXML
    private TableColumn<BonLivraison, String> etatColumn;
    @FXML
    private TableColumn<BonLivraison, String> numLivraisonColumn;
    @FXML
    private TableColumn<BonLivraison, String> NumFactureColumn;
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
    private TableColumn<DetailBonLivraison, String> libelleColumn;
    @FXML
    private TableColumn<DetailBonLivraison, String> codeMPColumn;
  
    @FXML
    private Label sommeLabel;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField numLivRech;
    @FXML
    private ComboBox<String> modeReglementCombo;
    @FXML
    private Pane paneView;
    @FXML
    private TextField numCritique;
    @FXML
    private DatePicker dateReglement;
    @FXML
    private TextField montantTotalField;
    @FXML
    private Button btnRegler;
    @FXML
    private Button btnImprimer;
    @FXML
    private Label monHT;
    @FXML
    private Label monTVA;
    @FXML
    private Label monTTC;

    /**
     * Initializes the controller class.
     */
    
      public   Date dateNow =null;
   
  private final  ObservableList<BonLivraison> listeBonLivraison = FXCollections.observableArrayList();
  private final  ObservableList<BonLivraison> listeBonLivraisonTMP = FXCollections.observableArrayList();
  private final  ObservableList<DetailBonLivraison> listeDetailBonLivraison = FXCollections.observableArrayList();
  ObservableList<String> modeReglementlist =FXCollections.observableArrayList(Constantes.ESPECE,Constantes.CHEQUE,Constantes.ORDER_DE_VIREMENT,Constantes.TRAITE);
  BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
  CompteFourMPDAO compteFourMPDAO = new CompteFourMPDAOImpl();
  DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
  
  BonLivraison bonLivraison = new BonLivraison();
  Fournisseur fournisseur =new Fournisseur();
  ClientMP clientMP =new ClientMP();

    DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
   DetailCompte detailCompte = new DetailCompte();
   DetailReceptionDAO  detailReceptionDAO = new DetailReceptionDAOImpl();
   BonRetourDAO bonRetourDAO =new  BonRetourDAOImpl();
   MagasinDAO magasinDAO = new MagasinDAOImpl();
   StockMPDAO stockMPDAO = new  StockMPDAOImpl();
   ReglementDAO reglementDAO= new ReglementDAOImpl();
   navigation nav = new navigation();   
   Commande commande = new Commande();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();  
    DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
     private Map<String,ClientMP> mapClientMP=new HashMap<>();
         String numCommande="";
    String valeur="";
    
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
  
  BigDecimal montantReglement=BigDecimal.ZERO;
  
   String codeReglement = "";

    static final long UNE_HEURE = 60*60*1000L;
    @FXML
    private TextField numFacture;
    @FXML
    private DatePicker dateLivraison;
    @FXML
    private DatePicker dateEcheance;
    

     void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.REGLEMENT_CODE);
          codeReglement = Constantes.REGLEMENT_CODE+" "+(sequenceur.getValeur()+1);
   }
   
  
    /**
     * Initializes the controller class.
     */
    
    //--------------------------------------- Methode date paiement + date now = nombre de jour restant -------------------------------------------------------------------------------
      
    public int daysBetween(Date d1, Date d2){
             return (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
     }
   
  //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------           
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Incrementation (); 
        
        numLivRech.setDisable(true);
        numFacture.setDisable(true);
        dateLivraison.setDisable(true);
        
        modeReglementCombo.setItems(modeReglementlist);
    
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
        
        paneView.setVisible(false);

        btnRegler.setDisable(true);
        
     
        
    }    

      
    void setColumnProperties() {

    
      numLivraisonColumn.setCellValueFactory(new PropertyValueFactory<>("livraisonFour"));
      montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
      etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
      dateLivraisonColumn.setCellValueFactory(new PropertyValueFactory<BonLivraison, Date>("dateLivraison"));
      NumFactureColumn.setCellValueFactory(new PropertyValueFactory<>("numFacture"));
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
      
          
//          tableBonLivraison.setEditable(true);
     

    }

    void loadDetail() {

        listeBonLivraison.clear();
        listeBonLivraison.addAll(bonLivraisonDAO.findBonLivraisonByEtat(Constantes.ETAT_A_REGLE));
        tableBonLivraison.setItems(listeBonLivraison);
    }
    
        public static void setColumnTextFieldConverter(StringConverter converter, TableColumn... columns) {

        for (TableColumn tableColumn : columns) {

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        }
    }

    private StringConverter getConverter() {
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
    
    void refrech() throws ParseException{
    
     if (fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                clientCombo.getSelectionModel().getSelectedIndex()== -1 || 
                clientCombo.getSelectionModel().getSelectedItem().equals("") )
        {
        nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.VERIFIER_FOURNISSEUR_CLIENT);
        }else{

            
            loadDetailCombo();
             setColumnProperties();
             tableBonLivraison.setEditable(true);
       

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
           
//           prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
           
             prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrix()));
                }
                
             });
           
//           totalColumn.setCellValueFactory(new PropertyValueFactory<>("Total"));
           
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
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        
         listeBonLivraisonTMP.clear();
             
       listeDetailBonLivraison.addAll(detailBonLivraisonDAO.findDetaiBonLivraisonBycode(tableBonLivraison.getSelectionModel().getSelectedItem().getLivraisonFour(),tableBonLivraison.getSelectionModel().getSelectedItem().getNumCommande()));

       if (tableBonLivraison.getSelectionModel().getSelectedIndex()!=-1){
         
           
              for( int i = 0;i<listeDetailBonLivraison.size() ;i++ ){
              
                  DetailBonLivraison detailBonLivraison = listeDetailBonLivraison.get(i);
                  
                  if (detailBonLivraison.getQuantite().equals(BigDecimal.ZERO.setScale(2))){
                  
                  detailBonLivraisonDAO.delete(detailBonLivraison);
                  }
              
              }
            listeDetailBonLivraison.clear();
            listeDetailBonLivraison.addAll(detailBonLivraisonDAO.findDetaiBonLivraisonBycode(tableBonLivraison.getSelectionModel().getSelectedItem().getLivraisonFour(),tableBonLivraison.getSelectionModel().getSelectedItem().getNumCommande()));
            tableDetailBonLivraison.setItems(listeDetailBonLivraison);
       
       setColumnPropertiesDetailReception();
         
         
sommeTotal= BigDecimal.ZERO;
           for( int rows = 0;rows<listeDetailBonLivraison.size() ;rows++ ){

               DetailBonLivraison detailBonLivraison = listeDetailBonLivraison.get(rows);
               
            sommeTotal = sommeTotal.add(detailBonLivraison.getTotal());  
        
    }
           
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
         sommeLabel.setText(df.format(sommeTotal));  
    }
       
        
        
    }

    @FXML
    private void tableDetailClicked(MouseEvent event) {
        
        
    }

    @FXML
    private void fournisseurOnAction(ActionEvent event) {
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }

    @FXML
    private void rechercheNumLivKeyPressed(KeyEvent event) {
        
      
    }

    @FXML
    private void modeReglementOnAction(ActionEvent event) {
        
            
         valeur= modeReglementCombo.getSelectionModel().getSelectedItem();
            if (valeur == (Constantes.CHEQUE)|| valeur == Constantes.ORDER_DE_VIREMENT || valeur == Constantes.TRAITE){
             paneView.setVisible(true);
            
             }else{
                  paneView.setVisible(false);
                
             }
    }

        void loadDetailCombo() throws ParseException, ParseException{
             listeBonLivraison.clear();
         
            if (fourCombo.getSelectionModel().getSelectedIndex() == -1 && clientCombo.getSelectionModel().getSelectedIndex() == -1 && numLivRech.getText().equals("") && dateLivraison.getValue() == null && numFacture.getText().equals("") ){
            
                
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
            } else if (fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && numLivRech.getText().equals("") && dateLivraison.getValue() == null && numFacture.getText().equals("") ){
          
          listeBonLivraison.addAll(bonLivraisonDAO.findFourByRechercheNomReglement(fourCombo.getSelectionModel().getSelectedItem(),clientCombo.getSelectionModel().getSelectedItem(),Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER,Constantes.ETAT_OULMES));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && !numLivRech.getText().equals("") && dateLivraison.getValue() == null && numFacture.getText().equals("") ){ 
          
            listeBonLivraison.addAll(bonLivraisonDAO.findBonLivraisonByRechercheNumLiv(numLivRech.getText(),clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(),Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER,Constantes.ETAT_OULMES));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && numLivRech.getText().equals("") && dateLivraison.getValue() != null && numFacture.getText().equals("") ){ 
              
               LocalDate localDate=dateLivraison.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
            listeBonLivraison.addAll(bonLivraisonDAO.findBonLivraisonByRechercheDateLiv(date,clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(),Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER,Constantes.ETAT_OULMES));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && !numLivRech.getText().equals("") && dateLivraison.getValue() != null && numFacture.getText().equals("") ){ 
              
               LocalDate localDate=dateLivraison.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
            listeBonLivraison.addAll(bonLivraisonDAO.findBonLivraisonByRechercheDateLivAndNumLiv(date,numLivRech.getText(),clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(),Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER,Constantes.ETAT_OULMES));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }else if (fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && numLivRech.getText().equals("") && dateLivraison.getValue() == null && !numFacture.getText().equals("") ){
          
          listeBonLivraison.addAll(bonLivraisonDAO.findBonLivraisonByRechercheNumFac(numFacture.getText(),clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(),Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER,Constantes.ETAT_OULMES));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          }else if (fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && numLivRech.getText().equals("") && dateLivraison.getValue() != null && !numFacture.getText().equals("") ){
          
                LocalDate localDate=dateLivraison.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
              
          listeBonLivraison.addAll(bonLivraisonDAO.findBonLivraisonByRechercheDateLivAndNumFac(date, numFacture.getText(),clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(),Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER,Constantes.ETAT_OULMES));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          }else if (fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && !numLivRech.getText().equals("") && dateLivraison.getValue() == null && !numFacture.getText().equals("") ){
          
              
          listeBonLivraison.addAll(bonLivraisonDAO.findBonLivraisonByRechercheNumLivAndNumFac(numLivRech.getText(), numFacture.getText(),clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(),Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER,Constantes.ETAT_OULMES));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          }else if (fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && !numLivRech.getText().equals("") && dateLivraison.getValue() != null && !numFacture.getText().equals("") ){
          
              
               LocalDate localDate=dateLivraison.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
              
          listeBonLivraison.addAll(bonLivraisonDAO.findBonLivraisonByRechercheDateLivAndNumLivAndNumFac(date, numLivRech.getText(), numFacture.getText(),clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(),Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER,Constantes.ETAT_OULMES));
          
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
    private void calculeMouseClicked(MouseEvent event) {
        
         listeBonLivraisonTMP.clear();
        
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
        
             if (etatColumn.getCellData(rows).equals(Constantes.ETAT_A_PAYER)){

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
           
           montantReglement= montantTTC;
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
    private void reglerOnAction(ActionEvent event) throws ParseException {
        
        
          boolean  variable =false;  
          BigDecimal montantCredit=BigDecimal.ZERO;
          BigDecimal Solde =BigDecimal.ZERO;
     

           BigDecimal montantTVA=BigDecimal.ZERO;
           BigDecimal montantTTC=BigDecimal.ZERO;
         
         
//           int Maxid = reglementDAO.getMaxId();
            if (modeReglementCombo.getSelectionModel().getSelectedItem() == null || modeReglementCombo.getSelectionModel().getSelectedItem().isEmpty()|| fourCombo.getSelectionModel().getSelectedItem() == null || fourCombo.getSelectionModel().getSelectedItem().isEmpty() ) {
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                
            } else{ 
                
           montantTotal=BigDecimal.ZERO;

             
             String fac="";
             String BL= "";
           
              for( int rows = 0;rows<tableBonLivraison.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){

                
            fac+=" "+tableBonLivraison.getItems().get(rows).getNumFacture();
             System.out.println("fac "+fac);
             BL+=" "+tableBonLivraison.getItems().get(rows).getLivraisonFour();

             BonLivraison bonLivraisonTmp= tableBonLivraison.getItems().get(rows);
               fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
               clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
             
                     if  (bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_RTR) || bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_MNQ) ){

  BonRetour bonRetourRes = bonRetourDAO.findBonRetourByNumCommAndNumLiv(bonLivraisonTmp.getNumCommande(),bonLivraisonTmp.getLivraisonFour() , Constantes.RETOUR_EN_ATT_REGLEMENT, Constantes.RECEPTION_TYPE);
  
  BonRetour bonRetourUsi = bonRetourDAO.findBonRetourByNumCommAndNumLiv(bonLivraisonTmp.getNumCommande(),bonLivraisonTmp.getLivraisonFour() , Constantes.RETOUR_EN_ATT_REGLEMENT, Constantes.USINE_TYPE);
  
  if (bonRetourRes != null){

  //____________________________________________________ Modifier Qte Restee ______________________________________________________________

           List<DetailBonLivraison> listDetailBonLivraison = detailBonLivraisonDAO.findDetaiBonLivraisonBycode(bonLivraisonTmp.getLivraisonFour(),bonLivraisonTmp.getNumCommande());
          
                     for (int i = 0; i < listDetailBonLivraison.size(); i++) {
                        DetailBonLivraison detailBonLivraison = listDetailBonLivraison.get(i);
                         
                    DetailCommande detailCommande = detailCommandeDAO.findDetailCommandeByBonRetour(detailBonLivraison.getMatierePremier().getCode(),bonLivraisonTmp.getFourisseur(),Constantes.ETAT_AFFICHAGE, bonLivraisonTmp.getNumCommande());

                    System.out.println("detailCommande "+detailCommande.getId());
                    
                  if (detailCommande!=null){
                  
                      BigDecimal qteRestee= BigDecimal.ZERO;
                              
                             qteRestee= detailCommande.getQuantiteRestee();
                      
                      BigDecimal newQteRestee =  BigDecimal.ZERO;
                              
                             newQteRestee = qteRestee.subtract(detailBonLivraison.getQuantite()).setScale(2,RoundingMode.FLOOR);

                      detailCommande.setQuantiteRestee(newQteRestee);
                      
                      detailCommandeDAO.edit(detailCommande);
                  
                  }

                     }   
                     
                                 
                 bonRetourRes.setEtat(Constantes.ETAT_PAYEE);
                 bonRetourDAO.edit(bonRetourRes);
                    variable = true;
      
      

  }else if (bonRetourUsi != null){
  
//____________________________________________________ Modifier Qte Stock ______________________________________________________________
      
             List<DetailBonLivraison> listDetailBonLivraison = detailBonLivraisonDAO.findDetaiBonLivraisonBycode(bonLivraisonTmp.getLivraisonFour(),bonLivraisonTmp.getNumCommande());
          
                     for (int i = 0; i < listDetailBonLivraison.size(); i++) {
                        DetailBonLivraison detailBonLivraison = listDetailBonLivraison.get(i);
       
                         Magasin magasin = magasinDAO.findStockByMagasinMP(detailBonLivraison.getUtilisateurCreation().getDepot().getId());
                        
                         StockMP stockMP = stockMPDAO.findStockByMagasinMP(detailBonLivraison.getMatierePremier().getId(),magasin.getId());
                        
                             if (stockMP!=null){
                  
                      BigDecimal qteStock= BigDecimal.ZERO ;
                              
                             qteStock=  stockMP.getStock();
                      
                      BigDecimal newQteStock = BigDecimal.ZERO;
                              
                             newQteStock = qteStock.subtract(detailBonLivraison.getQuantite());

                      stockMP.setStock(newQteStock);
                      
                      stockMPDAO.edit(stockMP);
                  
                  }
                        
                        
                     }
                     
                 bonRetourUsi.setEtat(Constantes.ETAT_PAYEE);
                 
                 bonRetourDAO.edit(bonRetourUsi);
                    variable = true;
                     
  }

       }
               
            
            bonLivraisonTmp.setEtat(Constantes.ETAT_REGLE);
            bonLivraisonTmp.setAction(false);

            bonLivraisonDAO.edit(bonLivraisonTmp);
       }
    }
           
              
              
           LocalDate localDate=dateReglement.getValue();
                Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
      
               LocalDate localDateTMP=dateEcheance.getValue();
                Date dateEche=new SimpleDateFormat("yyyy-MM-dd").parse(localDateTMP.toString());  
                String strDate = localDateTMP.toString();
                
         Reglement reglement = new Reglement();
         
             reglement.setDate(dateSaisie);
             reglement.setCodeReglement(codeReglement);
             reglement.setMontantReglement(montantReglement);       
             reglement.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
             reglement.setClientMP(mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem()));
           
             
             
             
             reglement.setAction(Boolean.FALSE);
             reglement.setNumFacture(fac);
             reglement.setUtilisateurCreation(nav.getUtilisateur());
            
             reglement.setModeReglement(valeur);
             
             if (valeur == Constantes.CHEQUE || valeur == Constantes.ORDER_DE_VIREMENT || valeur == Constantes.TRAITE){
             
             reglement.setNumCritique(numCritique.getText());
             reglement.setDateEcheance(dateEche);
             reglement.setDesignation(Constantes.REGLEMENT_SUR_BL+"("+BL+") "+modeReglementCombo.getSelectionModel().getSelectedItem()+" "+Constantes.MANQUE_RETOUR_N+numCritique.getText()+" "+Constantes.SUR_DATE_ECHEANCE+strDate);
             }
             else{
                reglement.setNumCritique("###");
                reglement.setDesignation(Constantes.REGLEMENT_SUR_BL+"("+BL+") "+modeReglementCombo.getSelectionModel().getSelectedItem());
             }

            
            reglementDAO.add(reglement);

             btnRegler.setDisable(true);
             
            variable = true;

            tableBonLivraison.refresh();
            
          
              detailCompte = new DetailCompte();
              detailCompte.setDateOperation(new Date());
              
             if (valeur == Constantes.CHEQUE || valeur == Constantes.ORDER_DE_VIREMENT || valeur == Constantes.TRAITE){
             
                detailCompte.setDesignation(Constantes.REGLEMENT_SUR_BL+"("+BL+") "+modeReglementCombo.getSelectionModel().getSelectedItem()+" "+Constantes.MANQUE_RETOUR_N+numCritique.getText()+" "+Constantes.SUR_DATE_ECHEANCE+strDate);
             }
             else{
                   
                detailCompte.setDesignation(Constantes.REGLEMENT_SUR_BL+"("+BL+") "+modeReglementCombo.getSelectionModel().getSelectedItem());
             }
              detailCompte.setMontantCredit(BigDecimal.ZERO);
              detailCompte.setDateBonLivraison(dateSaisie);
              detailCompte.setMontantDebit(montantReglement);
              detailCompte.setCode(codeReglement);
              detailCompte.setClientMP(clientMP);
              detailCompte.setCompteFourMP(fournisseur.getCompteFourMP());
              detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               
              detailCompteDAO.add(detailCompte);

              
              
      if (variable == false){
           nav.showAlert(Alert.AlertType.ERROR, "Atention", null, Constantes.REMPLIR_COCHE);
             btnImprimer.setDisable(true);
      }else{
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.REGLER_ENREGISTREMENT);
          
          
           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.REGLEMENT_CODE);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();
          
           
             btnImprimer.setDisable(false);
              loadDetailCombo();
             listeDetailBonLivraison.clear();
             montantTotalField.clear();
             sommeLabel.setText("");
             monHT.setText("");
             monTVA.setText("");
             monTTC.setText("");
             numLivRech.clear();
             numFacture.clear();
             dateLivraison.setValue(null);
             dateReglement.setValue(null);
             modeReglementCombo.getSelectionModel().select(-1);
             numCritique.clear();
             paneView.setVisible(false);
             
    }}
        
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {

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
            JasperReport report = (JasperReport) JRLoader.loadObject(ReglementController.class.getResource(nav.getiReportReglement()));
                       
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
            Logger.getLogger(ReglementController.class.getName()).log(Level.SEVERE, null, ex);
        }
                         
    }


    @FXML
    private void recherchMouseClicked(MouseEvent event) throws ParseException {
                
        numLivRech.setDisable(false);
        numFacture.setDisable(false);
        dateLivraison.setDisable(false);
        refrech();
        
    }

    @FXML
    private void rechercheNumFactureKeyPressed(KeyEvent event) {
        
            ObservableList<BonLivraison> listeBonLivraisonRech=FXCollections.observableArrayList();
                   
    listeBonLivraisonRech.clear();
   
   listeBonLivraisonRech.addAll(bonLivraisonDAO.findBonLivraisonByRechercheNumFac(numFacture.getText(),clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_A_REGLE));
   
   tableBonLivraison.setItems(listeBonLivraisonRech);
        
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
    private void refrechTableMouseClicked(MouseEvent event) {
        
        
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
    
}
