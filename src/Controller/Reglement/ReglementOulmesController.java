/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Utils.Constantes;
import dao.Entity.AvoirOulmes;
import dao.Entity.BonLivraison;
import dao.Entity.BonRetour;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.CompteFourMP;
import dao.Entity.DetailAvoirOulmes;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCompte;
import dao.Entity.Fournisseur;
import dao.Entity.Magasin;
import dao.Entity.PrixOulmes;
import dao.Entity.Reglement;
import dao.Entity.Sequenceur;
import dao.Manager.AvoirOulmesDAO;
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
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ReglementDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.AvoirOulmesDAOImpl;
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
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ReglementDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
public class ReglementOulmesController implements Initializable {

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
    private TableColumn<BonLivraison, BigDecimal> montantTVAColumn;
    @FXML
    private TableColumn<BonLivraison, BigDecimal> montantTTCColumn;
    @FXML
    private TableColumn<BonLivraison, BigDecimal> montantRgColumn;
    
    
    @FXML
    private TableView<DetailBonLivraison> tableDetailBonLivraison;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> prixColumn;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> totalColumn;
    @FXML
    private TableColumn<DetailBonLivraison, String> codeArtColumn;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> remiseColumn;
    @FXML
    private TableColumn<DetailBonLivraison, String> numFacDetailColumn;
    @FXML
    private TableColumn<DetailBonLivraison, String> bonAvoirColumn;
    @FXML
    private TableColumn<DetailBonLivraison, String> artColumn;
    @FXML
    private TableColumn<DetailBonLivraison, String> client2Column;
    @FXML
    private TableColumn<DetailBonLivraison, String> motifColumn;
    
    
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
    @FXML
    private TextField numFacture;
    @FXML
    private DatePicker dateLivraison;
    @FXML
    private DatePicker dateEcheance;
    @FXML
    private TextField montantReglementField;
    @FXML
    private Label montantRegLabel;
    @FXML
    private Label commentairesLabel;
    @FXML
    private TextField commentairesField;
    @FXML
    private TextField montantAnonymeField;
    @FXML
    private TextField factureAnonymeField;
    @FXML
    private CheckBox checkAnonyme;
    
    String codeReglement = "";
    
    /**
     * Initializes the controller class.
     */
    
  public   Date dateNow =null;
  private final  ObservableList<BonLivraison> listeBonLivraisonTMP = FXCollections.observableArrayList();
  private final  ObservableList<BonLivraison> listeBonLivraison = FXCollections.observableArrayList();
  private final  ObservableList<DetailBonLivraison> listeDetailBonLivraison = FXCollections.observableArrayList();
  private final ObservableList<DetailAvoirOulmes> listDetailAvoirOulmesList=FXCollections.observableArrayList();
  ObservableList<String> modeReglementlist =FXCollections.observableArrayList(Constantes.ESPECE,Constantes.CHEQUE,Constantes.ORDER_DE_VIREMENT,Constantes.TRAITE);
  BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
  CompteFourMPDAO compteFourMPDAO = new CompteFourMPDAOImpl();
  DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
    AvoirOulmesDAO avoirOulmesDAO = new AvoirOulmesDAOImpl();
  
  Fournisseur fournisseur =new Fournisseur();
  ClientMP clientMP =new ClientMP();
  AvoirOulmes avoirOulmes = new AvoirOulmes();
  
    DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
   DetailCompte detailCompte = new DetailCompte();
   DetailReceptionDAO  detailReceptionDAO = new DetailReceptionDAOImpl();
   BonRetourDAO bonRetourDAO =new  BonRetourDAOImpl();
   MagasinDAO magasinDAO = new MagasinDAOImpl();
   ReglementDAO reglementDAO= new ReglementDAOImpl();
   navigation nav = new navigation();   
   Commande commande = new Commande();
   FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
   SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();  
   ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
   DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
   DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
   PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
   
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    
    
    String numCommande="";
    String valeur="";
    
  BigDecimal avance= BigDecimal.ZERO;
    
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
  BigDecimal montantRGNonRegler=BigDecimal.ZERO;
  BigDecimal montantRGNonPayer=BigDecimal.ZERO;
  BigDecimal montantRG=BigDecimal.ZERO;
  BigDecimal montantReglement=BigDecimal.ZERO;
  BigDecimal montantRgModifier=BigDecimal.ZERO;
  BigDecimal montantRgSomme=BigDecimal.ZERO;
  BigDecimal montantRGNonReglerNonPayer=BigDecimal.ZERO;
  BigDecimal montantTTCSomme=BigDecimal.ZERO;
  BigDecimal montantTTCNonReglerNonPayer=BigDecimal.ZERO;

    static final long UNE_HEURE = 60*60*1000L;
    
    
    Sequenceur sequenceurF = sequenceurDAO.findByCode(Constantes.FACTURE_AVOIR_CODE);
          String FactCount = Constantes.FACTURE_AVOIR_CODE+(sequenceurF.getValeur()+1);
          
          Sequenceur sequenceurA = sequenceurDAO.findByCode(Constantes.BON_AVOIR_CODE);
          String AvoirCount = Constantes.BON_AVOIR_CODE+(sequenceurA.getValeur()+1);
    
    
    
        Sequenceur sequenceurBA = sequenceurDAO.findByCode(Constantes.AVOIR_ECART);
          String Avoir = Constantes.AVOIR_ECART+(sequenceurBA.getValeur()+1);


 

    void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.REGLEMENT_OULMES_CODE);
          codeReglement = Constantes.REGLEMENT_OULMES_CODE+" "+(sequenceur.getValeur()+1);
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
        
        
        Incrementation();
        
        numLivRech.setDisable(true);
        numFacture.setDisable(true);
        dateLivraison.setDisable(true);
        
        modeReglementCombo.setItems(modeReglementlist);
    
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
        
        paneView.setVisible(false);

        btnRegler.setDisable(true);
        

        
//       montantRegLabel.setVisible(false);
//       montantReglementField.setVisible(false);
//       commentairesField.setVisible(false);
//       commentairesLabel.setVisible(false);
      
    }    

      
    void setColumnProperties() {

      numLivraisonColumn.setCellValueFactory(new PropertyValueFactory<>("livraisonFour"));
      montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
      etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
      dateLivraisonColumn.setCellValueFactory(new PropertyValueFactory<>("dateLivraison"));
      NumFactureColumn.setCellValueFactory(new PropertyValueFactory<>("numFacture"));
      montantTVAColumn.setCellValueFactory(new PropertyValueFactory<>("montantTVA"));
      montantTTCColumn.setCellValueFactory(new PropertyValueFactory<>("montantTTC"));
      
      montantRgColumn.setCellValueFactory(new PropertyValueFactory<>("montantRG"));
      setColumnTextFieldConverter(getConverter(), montantRgColumn);
      
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
//          montantRgColumn.setEditable(true);
 
          tableBonLivraison.setEditable(true);  
          tableDetailBonLivraison.setEditable(true);
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
    
    void loadDetail() {

        listeBonLivraison.clear();
        listeBonLivraison.addAll(bonLivraisonDAO.findBonLivraisonByEtat(Constantes.ETAT_A_REGLE));
        tableBonLivraison.setItems(listeBonLivraison);
    }
    
    
    void refrech() throws ParseException{
    
     if (fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                clientCombo.getSelectionModel().getSelectedIndex()== -1 || 
                clientCombo.getSelectionModel().getSelectedItem().equals("") )
        {
        nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.VERIFIER_FOURNISSEUR_CLIENT);
        }else{

            setColumnProperties();
            loadDetailCombo();
          



     }

    }

    void setColumnPropertiesDetailReception(){

               codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonLivraison, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });

           artColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonLivraison, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
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
           
           client2Column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonLivraison, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClient2());
                }
             });
             
             prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal> p) {
                    
              
                    return new ReadOnlyObjectWrapper(p.getValue().getPrix().setScale(6));
                }
                
             });
           
             
             remiseColumn.setCellValueFactory(new PropertyValueFactory<DetailBonLivraison, BigDecimal>("remiseAchat"));

             
        
           numFacDetailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonLivraison, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumFacture());
                }
             });
           
            bonAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonLivraison, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getBonAvoir());
                }
             });
             
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
             
//________________________________________________________________________ Charge ComboBox in TableView _________________________________________________________________________________________________________________________________________________________________________________________________________
 
        motifColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonLivraison, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMotif());
                }
             });
   
        ObservableList<String> listMotif = FXCollections.observableArrayList();
        
        listMotif.add("Sans");
        listMotif.add("Prob Prix");
        listMotif.add("Prob Remise");
        listMotif.add("Prob Prix / Prob Remise");

        motifColumn.setCellFactory(ComboBoxTableCell.forTableColumn(listMotif));
//_____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
     
             
             
}
   
    
    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {

       if (tableBonLivraison.getSelectionModel().getSelectedIndex()!=-1){
           
            listeDetailBonLivraison.addAll(detailBonLivraisonDAO.findDetaiBonLivraisonBycode(tableBonLivraison.getSelectionModel().getSelectedItem().getLivraisonFour(),tableBonLivraison.getSelectionModel().getSelectedItem().getNumCommande()));
           
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
               
            sommeTotal = sommeTotal.add(detailBonLivraison.getMontant());  
        
    }
           
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
         sommeLabel.setText(df.format(sommeTotal));  
    }
        
           if(tableBonLivraison.getSelectionModel().getSelectedItem().getTypeBon().equals(Constantes.ETAT_OULMES)){
        montantRgColumn.setEditable(true);
       }else{
        montantRgColumn.setEditable(false);
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

        void loadDetailCombo() throws ParseException{
                  listeBonLivraison.clear();
         
            if (fourCombo.getSelectionModel().getSelectedIndex() == -1 && clientCombo.getSelectionModel().getSelectedIndex() == -1 && numLivRech.getText().equals("") && dateLivraison.getValue() == null && numFacture.getText().equals("") ){
 
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
            } else if (fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && numLivRech.getText().equals("") && dateLivraison.getValue() == null && numFacture.getText().equals("") ){
          
            listeBonLivraison.addAll(bonLivraisonDAO.findFourByRechercheNomReglementOulmes(fourCombo.getSelectionModel().getSelectedItem(),clientCombo.getSelectionModel().getSelectedItem(),Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          

          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && !numLivRech.getText().equals("") && dateLivraison.getValue() == null && numFacture.getText().equals("") ){ 
          
            listeBonLivraison.addAll(bonLivraisonDAO.findNumCommandeByNumLivraisonOulmes(numLivRech.getText(),fourCombo.getSelectionModel().getSelectedItem(),clientCombo.getSelectionModel().getSelectedItem(),Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER ));
          
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
          
            listeBonLivraison.addAll(bonLivraisonDAO.findNumCommandeByDateLivraisonOulmes(date,fourCombo.getSelectionModel().getSelectedItem(),clientCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER));
          
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
          
            listeBonLivraison.addAll(bonLivraisonDAO.findNumCommandeByDateLivraisonAndNumLivraisonOulmes(date, numLivRech.getText(),fourCombo.getSelectionModel().getSelectedItem(),clientCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
            
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && numLivRech.getText().equals("") && dateLivraison.getValue() == null && !numFacture.getText().equals("") ){ 
          
            listeBonLivraison.addAll(bonLivraisonDAO.findNumCommandeByNumFacOulmes(numFacture.getText() ,fourCombo.getSelectionModel().getSelectedItem(), clientCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER ));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && numLivRech.getText().equals("") && dateLivraison.getValue() != null && !numFacture.getText().equals("") ){ 
              
               LocalDate localDate=dateLivraison.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
            listeBonLivraison.addAll(bonLivraisonDAO.findNumCommandeByDateLivraisonAndNumFacOulmes(date,numFacture.getText() ,fourCombo.getSelectionModel().getSelectedItem(), clientCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           

          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && !numLivRech.getText().equals("") && dateLivraison.getValue() == null && !numFacture.getText().equals("") ){ 
             
          
            listeBonLivraison.addAll(bonLivraisonDAO.findNumCommandeByNumLivraisonAndNumFacOulmes(numLivRech.getText(),numFacture.getText() ,fourCombo.getSelectionModel().getSelectedItem(), clientCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
               
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && !numLivRech.getText().equals("") && dateLivraison.getValue() != null && !numFacture.getText().equals("") ){ 
              
               LocalDate localDate=dateLivraison.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
            listeBonLivraison.addAll(bonLivraisonDAO.findNumCommandeByDateLivraisonAndNumLivraisonAndNumFacOulmes(date,numLivRech.getText(),numFacture.getText() ,fourCombo.getSelectionModel().getSelectedItem(), clientCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_A_REGLE, Constantes.ETAT_A_PAYER));
          
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
        
            montantTotal=BigDecimal.ZERO;
            montantTVA=BigDecimal.ZERO;
            montantTTC=BigDecimal.ZERO;
            montantRG=BigDecimal.ZERO;
            montantTotalNonPayer=BigDecimal.ZERO;
            montantTVANonPayer=BigDecimal.ZERO;
            montantTTCNonPayer=BigDecimal.ZERO;
            montantTotalNonRegler=BigDecimal.ZERO;
            montantTVANonRegler=BigDecimal.ZERO;
            montantTTCNonRegler=BigDecimal.ZERO;
            montantRGNonRegler=BigDecimal.ZERO;
            montantRGNonPayer=BigDecimal.ZERO;
            montantRGNonReglerNonPayer=BigDecimal.ZERO;
            montantTTCNonReglerNonPayer=BigDecimal.ZERO;
            
            
           for( int rows = 0;rows<tableBonLivraison.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
        
             if (etatColumn.getCellData(rows).equals(Constantes.ETAT_A_PAYER)){

             montantTotalNonPayer= montantTotalNonPayer.add(montantColumn.getCellData(rows));

             montantTVANonPayer = montantTVANonPayer.add(montantTVAColumn.getCellData(rows));
             
             montantTTCNonPayer= montantTTCNonPayer.add(montantTTCColumn.getCellData(rows));
             
             montantRGNonPayer= montantRGNonPayer.add(montantRgColumn.getCellData(rows));
               
             
             }else{

             montantTotalNonRegler= montantTotalNonRegler.add(montantColumn.getCellData(rows));

             montantTVANonRegler = montantTVANonRegler.add(montantTVAColumn.getCellData(rows));
             
             montantTTCNonRegler = montantTTCNonRegler.add(montantTTCColumn.getCellData(rows));
             
             montantRGNonRegler= montantRGNonRegler.add(montantRgColumn.getCellData(rows));
               
             }
             
             montantRGNonReglerNonPayer= montantRGNonReglerNonPayer.add(montantRgColumn.getCellData(rows));
             montantTTCNonReglerNonPayer = montantTTCNonReglerNonPayer.add(montantTTCColumn.getCellData(rows));
        } 
    }
           
           montantTotal= montantTotalNonRegler.subtract(montantTotalNonPayer);
             montantTVA= montantTVANonRegler.subtract(montantTVANonPayer);
             montantTTC= montantTTCNonRegler.subtract(montantTTCNonPayer);
             
             montantRG=montantRGNonRegler.subtract(montantRGNonPayer);
             
             montantRgSomme= montantRGNonReglerNonPayer;
             montantTTCSomme = montantTTCNonReglerNonPayer;
             
           montantRgModifier= montantRG;
           montantReglement= montantTTC;
           
              DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
    
    monHT.setText(df.format(montantTotal));
    monTVA.setText(df.format(montantTVA));
    monTTC.setText(df.format(montantTTC));
           
         montantTotalField.setText(montantTTC+"");  
         montantReglementField.setText(montantRG+"");  
         
         if(!montantTTC.equals(BigDecimal.ZERO)){
         
         btnRegler.setDisable(false);
         }
         
    }

    @FXML
    private void reglerOnAction(ActionEvent event) throws ParseException {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
          boolean  variable =false;  
          BigDecimal montantCredit=BigDecimal.ZERO;
          BigDecimal Solde =BigDecimal.ZERO;
     

           BigDecimal montantTVA=BigDecimal.ZERO;
           BigDecimal montantTTC=BigDecimal.ZERO;
         
         
//           int Maxid = reglementDAO.getMaxId();
            if (modeReglementCombo.getSelectionModel().getSelectedItem() == null ||
                    modeReglementCombo.getSelectionModel().getSelectedItem().isEmpty()||
                    fourCombo.getSelectionModel().getSelectedItem() == null ||
                    fourCombo.getSelectionModel().getSelectedItem().isEmpty() ||
                    new BigDecimal(montantTotalField.getText()).compareTo(BigDecimal.ZERO)<0) {
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                
            } else{ 
                
           montantTotal=BigDecimal.ZERO;

             String facAvance="";
             String BlAvance= "";   
             
             String fac="";
             String BL= "";
           
             
                 LocalDate localDate=dateReglement.getValue();
           Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
      

              for( int rows = 0;rows<tableBonLivraison.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){

                
            fac+=" "+tableBonLivraison.getItems().get(rows).getNumFacture();
             System.out.println("fac "+fac);
             BL+=" "+tableBonLivraison.getItems().get(rows).getLivraisonFour();

             BonLivraison bonLivraisonTmp= tableBonLivraison.getItems().get(rows);
               fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
               clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
             
                     if  (bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_PF_RTR) || bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_PF_MNQ) ){


  BonRetour bonRetour = bonRetourDAO.findBonRetourByNumCommAndNumLiv(bonLivraisonTmp.getNumCommande(),bonLivraisonTmp.getLivraisonFour() , Constantes.RETOUR_EN_ATT_REGLEMENT);
  
  if (bonRetour != null){

  
 //===============================================================  Detail Compte / Retour & Manque ===============================================================================================================================             
      
                    ClientMP clientMPTmp = clientMPDAO.findClientMPByNom(bonRetour.getClient());
                    Fournisseur fournisseurTmp = fournisseurDAO.findByFournisseur(bonRetour.getFournisseur());
 
 
                  detailCompte = new DetailCompte();
              detailCompte.setDateOperation(new Date());
              
             if (bonRetour.getType().equals(Constantes.RETOUR)){
             
                detailCompte.setDesignation(Constantes.RETOUR_N+bonRetour.getNumRetour()+" "+Constantes.SUR_COMMANDE_N+bonRetour.getNumCommande()+Constantes.RECEPTION_BON_LIVRAISON+bonRetour.getNumLivraison());
             }
             else if (bonRetour.getType().equals(Constantes.MANQUE)) {
                   
                detailCompte.setDesignation(Constantes.MANQUE_N+bonRetour.getNumRetour()+" "+Constantes.SUR_COMMANDE_N+bonRetour.getNumCommande()+Constantes.RECEPTION_BON_LIVRAISON+bonRetour.getNumLivraison());
             }
              detailCompte.setMontantCredit(BigDecimal.ZERO);
              detailCompte.setDateBonLivraison(dateSaisie);
              detailCompte.setMontantDebit(bonLivraisonTmp.getMontantTTC());
              detailCompte.setCode(bonRetour.getNumRetour());
              detailCompte.setClientMP(clientMPTmp);
              detailCompte.setCompteFourMP(fournisseurTmp.getCompteFourMP());
              detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               
              detailCompteDAO.add(detailCompte);

              
//===============================================================  Bon Retour / Modification ===============================================================================================================================             
                                 
                 bonRetour.setEtat(Constantes.ETAT_PAYEE);
                 bonRetourDAO.edit(bonRetour);
                    variable = true;

  }

       }
                     
                     if (checkAnonyme.isSelected()==true){
                     
                         if(montantAnonymeField.getText().isEmpty() || factureAnonymeField.getText().isEmpty()){
                         
                            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                          
                            return;
                            
                         }else{
                         
                         if(bonLivraisonTmp.getMontantRG().compareTo(new BigDecimal(montantAnonymeField.getText()))<0){
                         
                         nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.VERIFIER_MONTANT_ANONYME);
                             return;
                         }
                         }
                     
                     }
                     
 //===============================================================  Bon Livraison / Modification ===============================================================================================================================             
                
                   
            bonLivraisonTmp.setEtat(Constantes.ETAT_REGLE);
            bonLivraisonTmp.setAction(false);

            bonLivraisonDAO.edit(bonLivraisonTmp);
            
//================================================================  Montant Ecart (-)  ===============================================================================================================================             
                    
              if (montantRgModifier.compareTo(BigDecimal.ZERO)!=0){
             
             if(bonLivraisonTmp.getMontantTTC().compareTo(bonLivraisonTmp.getMontantRG())>0 || bonLivraisonTmp.getMontantTTC().compareTo(bonLivraisonTmp.getMontantRG())<0){
                 
             List<DetailBonLivraison> listeDetailBonLivraison = detailBonLivraisonDAO.findDetaiBonLivraisonBycode(bonLivraisonTmp.getLivraisonFour(), bonLivraisonTmp.getNumCommande());
                 
//                                                           *****  DetailAvoirOulmes  *****

                String Client2 = "";

                 for (int i = 0; i < listeDetailBonLivraison.size(); i++) {
                     
                     DetailBonLivraison detailBonLivraison = listeDetailBonLivraison.get(i);
                     
                     DetailAvoirOulmes detailAvoirOulmes = new DetailAvoirOulmes();
                     
                     detailAvoirOulmes.setPrix(detailBonLivraison.getPrix());
                     detailAvoirOulmes.setQte(detailBonLivraison.getQuantite());
                     detailAvoirOulmes.setMontant((detailAvoirOulmes.getQte().multiply(detailAvoirOulmes.getPrix())).setScale(2,RoundingMode.FLOOR));
                     
                     if (detailBonLivraison.getRemiseAchat().compareTo(BigDecimal.ZERO)==0){
                 
                  detailAvoirOulmes.setMontantNet(detailAvoirOulmes.getMontant());
                  detailAvoirOulmes.setRemiseAvoir(detailBonLivraison.getRemiseAchat());
                  detailAvoirOulmes.setTypeRemise(Constantes.SANS);
                  
                     }else if (detailBonLivraison.getRemiseAchat().compareTo(BigDecimal.ZERO)!=0){

                  detailAvoirOulmes.setMontantNet(detailAvoirOulmes.getMontant().add((detailAvoirOulmes.getMontant().multiply(detailBonLivraison.getRemiseAchat())).divide(new BigDecimal(100))));
                  detailAvoirOulmes.setRemiseAvoir(detailBonLivraison.getRemiseAchat());
                  detailAvoirOulmes.setTypeRemise(Constantes.PLUS);

               }
                     
                  detailAvoirOulmes.setPrixOulmes(detailBonLivraison.getPrixOulmes());
                  detailAvoirOulmes.setAction(Boolean.FALSE);
                  detailAvoirOulmes.setPrixFactureAvoir(BigDecimal.ZERO.setScale(2));
                  detailAvoirOulmes.setMotif(detailBonLivraison.getMotif());
                  detailAvoirOulmes.setQteFactureAvoir(BigDecimal.ZERO.setScale(2));
                  detailAvoirOulmes.setRemiseFactureAvoir(BigDecimal.ZERO.setScale(2));
                  detailAvoirOulmes.setAvoirOulmes(avoirOulmes);
                  detailAvoirOulmes.setUtilisateurCreation(nav.getUtilisateur());
             
                  Client2 = detailBonLivraison.getClient2();
                  
                  listDetailAvoirOulmesList.add(detailAvoirOulmes);
                     
                 }
                 
//                                                           *****  AvoirOulmes  *****              
                 

               if(Client2!=null){
               avoirOulmes.setClient2(Client2);
               }else{
               avoirOulmes.setClient2(Constantes.SANS);
               }

             avoirOulmes.setLieuLivraison(Constantes.SANS);  
             avoirOulmes.setClient(bonLivraisonTmp.getClient());
             avoirOulmes.setAvoir(BigDecimal.ZERO.setScale(2));
             avoirOulmes.setNumFacture(bonLivraisonTmp.getNumFacture());
             avoirOulmes.setFactureAvoir(BigDecimal.ZERO.setScale(2));
             avoirOulmes.setFactureSysteme(bonLivraisonTmp.getMontantTTC());
             avoirOulmes.setFactureOulmes(bonLivraisonTmp.getMontantRG());
             avoirOulmes.setEcart(bonLivraisonTmp.getMontantRG().subtract(bonLivraisonTmp.getMontantTTC()));
             avoirOulmes.setAction(Boolean.FALSE);
             avoirOulmes.setDateCreation(new Date());
             avoirOulmes.setFournisseur(bonLivraisonTmp.getFourisseur());
             avoirOulmes.setDesignation(Constantes.ETAT_BL_AV+Avoir);
             avoirOulmes.setUtilisateurCreation(nav.getUtilisateur());
             avoirOulmes.setRegularisation(BigDecimal.ZERO.setScale(2));
             avoirOulmes.setEtat(Constantes.ETAT_EN_ATTENTE_AVOIR);
             avoirOulmes.setDetailAvoirOulmes(listDetailAvoirOulmesList);
             avoirOulmes.setTypeEcart("Oulmes");
             avoirOulmes.setNumLivraison(Avoir+" "+bonLivraisonTmp.getLivraisonFour());
             avoirOulmes.setDateSaisie(dateSaisie);
            
             avoirOulmesDAO.add(avoirOulmes);
             
             avoirOulmes = new AvoirOulmes();
             
             
             if (bonLivraisonTmp.getMontantTTC().compareTo(bonLivraisonTmp.getMontantRG())<0){
                 
                 avance = avance.add((bonLivraisonTmp.getMontantTTC().subtract(bonLivraisonTmp.getMontantRG())).multiply(new BigDecimal(-1)));
                 
                    facAvance+=" "+bonLivraisonTmp.getNumFacture();
                    BlAvance+=" "+bonLivraisonTmp.getLivraisonFour();
                    
             }
             
//                                                           *****  Sequenceur  *****          
             
            Sequenceur sequenceurBA = sequenceurDAO.findByCode(Constantes.AVOIR_ECART);
            sequenceurBA.setValeur(sequenceurBA.getValeur()+1);
            sequenceurDAO.edit(sequenceurBA);
     
             }            
//                                               
             
            
       }
         

    }
              }
              
 //=================================================== Montant Anonyme ===============================================================================================================================
                    if (checkAnonyme.isSelected()==true){
 
                     ClientMP clientMP= mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
                    Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
 
 //                                                           *****  AvoirOulmes  *****    
 
 
                   if( new BigDecimal(montantAnonymeField.getText()).compareTo(BigDecimal.ZERO)!=0){
                 
                 AvoirOulmes avoirOulmes = new AvoirOulmes();
                 
                    avoirOulmes.setClient2(Constantes.SANS);
             avoirOulmes.setLieuLivraison(Constantes.SANS);  
             avoirOulmes.setClient(clientMP.getNom());
             avoirOulmes.setNumFacture(factureAnonymeField.getText());
             avoirOulmes.setAvoir(BigDecimal.ZERO.setScale(2));
             avoirOulmes.setFactureAvoir(BigDecimal.ZERO.setScale(2));
             avoirOulmes.setFactureSysteme(BigDecimal.ZERO.setScale(2));
             avoirOulmes.setFactureOulmes(new BigDecimal(montantAnonymeField.getText()));
             avoirOulmes.setEcart(BigDecimal.ZERO.subtract(new BigDecimal(montantAnonymeField.getText())));
             avoirOulmes.setAction(Boolean.FALSE);
             avoirOulmes.setDateCreation(new Date());
             avoirOulmes.setFournisseur(fournisseur.getNom());
             avoirOulmes.setDesignation(Constantes.ETAT_BL_AV+Avoir);
             avoirOulmes.setUtilisateurCreation(nav.getUtilisateur());
             avoirOulmes.setRegularisation(BigDecimal.ZERO.setScale(2));
             avoirOulmes.setEtat(Constantes.ETAT_EN_ATTENTE_AVOIR);
             avoirOulmes.setNumLivraison(Avoir+" AN");
             avoirOulmes.setTypeEcart("Oulmes");
             avoirOulmes.setDateSaisie(dateSaisie);
            
             avoirOulmesDAO.add(avoirOulmes);
             
                
//                                                           *****  Sequenceur  *****          
             
            Sequenceur sequenceurBA = sequenceurDAO.findByCode(Constantes.AVOIR_ECART);
            sequenceurBA.setValeur(sequenceurBA.getValeur()+1);
            sequenceurDAO.edit(sequenceurBA);              
             }
                    }
 //=================================================== Reglement ===============================================================================================================================
 
 
            
         Reglement reglement = new Reglement();
         
             reglement.setDate(dateSaisie);
             reglement.setCodeReglement(codeReglement);
             
             if (montantRgSomme.compareTo(BigDecimal.ZERO)==0){
                
                 reglement.setMontantReglement(montantReglement);  
                 reglement.setMontantEcart(BigDecimal.ZERO);
                 reglement.setMontantAvance(BigDecimal.ZERO);
                 reglement.setCommentaires(Constantes.SANS);
             }else{
                 if (montantTTCSomme.compareTo(montantRgSomme)>0){
                     
                     BigDecimal ecart = montantTTCSomme.subtract(montantRgSomme);
                     
                     reglement.setMontantReglement(montantRgModifier);  
                     reglement.setMontantEcart(ecart);
                     reglement.setMontantAvance(BigDecimal.ZERO);
                     reglement.setCommentaires(commentairesField.getText());
                     
                     
                 }else if (montantTTCSomme.compareTo(montantRgSomme)<0){
                     
                      BigDecimal avance = (montantTTCSomme.subtract(montantRgSomme)).multiply(new BigDecimal(-1));
                     
                     reglement.setMontantReglement(montantReglement);  
                     reglement.setMontantEcart(BigDecimal.ZERO);
                     reglement.setMontantAvance(avance);
                     reglement.setCommentaires(commentairesField.getText());
                     
                 }else if (montantTTCSomme.compareTo(montantRgSomme)==0){
                     
                     reglement.setMontantReglement(montantReglement);  
                     reglement.setMontantEcart(BigDecimal.ZERO);
                     reglement.setMontantAvance(BigDecimal.ZERO);
                     reglement.setCommentaires(commentairesField.getText());
                     
                 } 
             }
                if (checkAnonyme.isSelected()==true){
             reglement.setMontantAnonyme(new BigDecimal(montantAnonymeField.getText()));
                }
             reglement.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
             reglement.setClientMP(mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem()));
             reglement.setAction(Boolean.FALSE);
             reglement.setNumFacture(fac);
             reglement.setUtilisateurCreation(nav.getUtilisateur());
            
             reglement.setModeReglement(valeur);
             
             if (valeur == Constantes.CHEQUE || valeur == Constantes.ORDER_DE_VIREMENT || valeur == Constantes.TRAITE){
             
                   LocalDate localDateTMP=dateEcheance.getValue();
            Date dateEche=new SimpleDateFormat("yyyy-MM-dd").parse(localDateTMP.toString());  
             String strDate = localDateTMP.toString();
                 
             reglement.setNumCritique(numCritique.getText());
             reglement.setDateEcheance(dateEche);
             reglement.setDesignation(Constantes.REGLEMENT_SUR_BL+"("+BL+") "+modeReglementCombo.getSelectionModel().getSelectedItem()+" "+Constantes.N+numCritique.getText()+" "+Constantes.SUR_DATE_ECHEANCE+strDate+Constantes.SUR_FACTURE_N+fac);
             }
             else{
                reglement.setNumCritique("###");
                reglement.setDesignation(Constantes.REGLEMENT_SUR_BL+"("+BL+") "+modeReglementCombo.getSelectionModel().getSelectedItem()+Constantes.SUR_FACTURE_N+fac);
             }

            
            reglementDAO.add(reglement);

             btnRegler.setDisable(true);
             
            variable = true;

            tableBonLivraison.refresh();
            
 //                                                        *****  DetailCompte  *****              
            
   
                DetailCompte detailCompte = new DetailCompte();
                
              detailCompte.setDateOperation(new Date());
              
              if (valeur == Constantes.CHEQUE || valeur == Constantes.ORDER_DE_VIREMENT || valeur == Constantes.TRAITE){
                  
                  LocalDate localDateTMP=dateEcheance.getValue();
                    String strDate = localDateTMP.toString();
                  
                   if (checkAnonyme.isSelected()==false){
                    detailCompte.setDesignation(Constantes.REGLEMENT_SUR_BL+"("+BL+") "+modeReglementCombo.getSelectionModel().getSelectedItem()+" "+Constantes.N+numCritique.getText()+" "+Constantes.SUR_DATE_ECHEANCE+strDate+Constantes.SUR_FACTURE_N+fac);
                }else{
                    detailCompte.setDesignation(Constantes.REGLEMENT_SUR_BL+"("+BL+") "+modeReglementCombo.getSelectionModel().getSelectedItem()+" "+Constantes.N+numCritique.getText()+" "+Constantes.SUR_DATE_ECHEANCE+strDate+Constantes.SUR_FACTURE_N+fac+" et facture anonyme N° "+factureAnonymeField.getText());
                }
              
             }else{
                     if (checkAnonyme.isSelected()==false){
                   detailCompte.setDesignation(Constantes.REGLEMENT_SUR_BL+"("+BL+") "+modeReglementCombo.getSelectionModel().getSelectedItem()+Constantes.SUR_FACTURE_N+fac);
                }else{
                   detailCompte.setDesignation(Constantes.REGLEMENT_SUR_BL+"("+BL+") "+modeReglementCombo.getSelectionModel().getSelectedItem()+Constantes.SUR_FACTURE_N+fac+" et facture anonyme N° "+factureAnonymeField.getText());
                }
                
             }
             
              detailCompte.setMontantCredit(BigDecimal.ZERO);
              detailCompte.setDateBonLivraison(dateSaisie);
                 if (checkAnonyme.isSelected()==true){
                 detailCompte.setMontantDebit(montantRG.subtract(avance).subtract(new BigDecimal(montantAnonymeField.getText())));
                 }else{
                  detailCompte.setMontantDebit(montantRG.subtract(avance));
                 }
              detailCompte.setCode(codeReglement);
              detailCompte.setClientMP(clientMP);
              detailCompte.setCompteFourMP(fournisseur.getCompteFourMP());
              detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               
              detailCompteDAO.add(detailCompte);
                 
  
              
//                                                           *****  DetailCompte 'Avance'  *****

               if (avance.compareTo(BigDecimal.ZERO)!=0){  

                  DetailCompte detailCompteTMP = new DetailCompte();
                
              detailCompteTMP.setDateOperation(new Date());

              detailCompteTMP.setDesignation(Constantes.AVANCE_SUR__BL+"("+BlAvance+") "+" // "+commentairesField.getText()+Constantes.SUR_FACTURE_N+facAvance);
   
              detailCompteTMP.setMontantCredit(BigDecimal.ZERO);
              detailCompteTMP.setDateBonLivraison(dateSaisie);
              detailCompteTMP.setMontantDebit(avance);
              detailCompteTMP.setCode(codeReglement);
              detailCompteTMP.setClientMP(clientMP);
              detailCompteTMP.setCompteFourMP(fournisseur.getCompteFourMP());
              detailCompteTMP.setUtilisateurCreation(nav.getUtilisateur());
               
              detailCompteDAO.add(detailCompteTMP);
             
             }

               
      if (variable == false){
           nav.showAlert(Alert.AlertType.ERROR, "Atention", null, Constantes.REMPLIR_COCHE);
             btnImprimer.setDisable(true);
      }else{
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.REGLER_ENREGISTREMENT);
          
             Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.REGLEMENT_OULMES_CODE);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();
          
          commentairesField.clear();
           montantReglementField.clear();

             btnImprimer.setDisable(false);
             loadDetailCombo();
             listeDetailBonLivraison.clear();
             montantTotalField.clear();
             sommeLabel.setText("");
             monHT.setText("");
             monTVA.setText("");
             monTTC.setText("");
             numLivRech.clear();
             dateLivraison.setValue(null);
             numFacture.clear();
             dateReglement.setValue(null);
             modeReglementCombo.getSelectionModel().select(-1);
             numCritique.clear();
             paneView.setVisible(false);
             
             
            montantAnonymeField.clear();
            factureAnonymeField.clear();
            
            checkAnonyme.setSelected(false);
            
            montantAnonymeField.setDisable(true);
            factureAnonymeField.setDisable(true);
            
             
    }}
            }
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
                 bonLivraison.setMontantRG(bonLivraison.getMontantRG().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
             }

             listeBonLivraisonTMP.add(bonLivraison); 
         }
          }
        
                          try {
                              
       if (listeBonLivraisonTMP.size()!= 0){
          HashMap para = new HashMap();
          
            JasperReport report = (JasperReport) JRLoader.loadObject(ReglementOulmesController.class.getResource(nav.getiReportReglementOulmes()));
                       
            para.put("MontantHT",monHT.getText());
            para.put("MontantTVA",monTVA.getText());
            para.put("MontantTTC",monTTC.getText());
            para.put("MontantRG",new BigDecimal(montantReglementField.getText()));
            
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
                 bonLivraison.setMontantRG(bonLivraison.getMontantRG().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
             }
             
         }
          }
                                
        } catch (JRException ex) {
            Logger.getLogger(ReglementOulmesController.class.getName()).log(Level.SEVERE, null, ex);
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

//    private void rgCheckOnAction(ActionEvent event) {
//        
//        
//        if (RgCheck.isSelected()==true){
//            
//            montantReglementField.clear();
//            commentairesField.clear();
//            
//        montantRegLabel.setVisible(true);
//        montantReglementField.setVisible(true);
//        
//        commentairesField.setVisible(true);
//       commentairesLabel.setVisible(true);
//        
//        }else{
//        
//              montantReglementField.clear();
//              commentairesField.clear();
//              
//        montantRegLabel.setVisible(false);
//        montantReglementField.setVisible(false);
//        
//        commentairesField.setVisible(false);
//       commentairesLabel.setVisible(false);
//        }
//    }

     @FXML
    private void editCommitMontantRGColumn(TableColumn.CellEditEvent<BonLivraison, BigDecimal> event) {
        
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    BigDecimal montantRg = BigDecimal.ZERO;
                    
                    
                ((BonLivraison) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setMontantRG(event.getNewValue());
               
                    tableBonLivraison.refresh();  

                 montantRg = montantRgColumn.getCellData(event.getTablePosition().getRow());
                
                 listeBonLivraison.get(tableBonLivraison.getSelectionModel().getSelectedIndex()).setMontantRG(montantRg.setScale(2, RoundingMode.FLOOR));
                
                 bonLivraisonDAO.edit(listeBonLivraison.get(tableBonLivraison.getSelectionModel().getSelectedIndex()));
                 
                setColumnProperties();

           

            } else if (result.get() == ButtonType.CANCEL) {
                tableBonLivraison.refresh();
            }
        
    }

     @FXML
    private void motifOnEditCommit(TableColumn.CellEditEvent<DetailBonLivraison, String> event) {
        
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
            
             ((DetailBonLivraison) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setMotif(event.getNewValue());       
                   
               String  motif = motifColumn.getCellData(event.getTablePosition().getRow());
             
                   
        listeDetailBonLivraison.get(tableDetailBonLivraison.getSelectionModel().getSelectedIndex()).setMotif(motif);   
       
        detailBonLivraisonDAO.edit(listeDetailBonLivraison.get(tableDetailBonLivraison.getSelectionModel().getSelectedIndex()));
           
            
            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailBonLivraison.refresh();
            }
        
    }


    @FXML
    private void checkAnonymeOnAction(ActionEvent event) {
        
        if(checkAnonyme.isSelected()== true){
        
            montantAnonymeField.clear();
            factureAnonymeField.clear();
            
            montantAnonymeField.setDisable(false);
            factureAnonymeField.setDisable(false);
            
        }else{
        
            montantAnonymeField.clear();
            factureAnonymeField.clear();
            
            montantAnonymeField.setDisable(true);
            factureAnonymeField.setDisable(true);
            
        }
        
    }


    @FXML
    private void montantAnonymeOnKeyReleased(KeyEvent event) {
        
        if (new BigDecimal(montantAnonymeField.getText()).compareTo(BigDecimal.ZERO)<0){
        
           nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
        montantAnonymeField.clear();
           
        }
        
    }



 


    
}