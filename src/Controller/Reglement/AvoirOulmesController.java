
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Utils.Constantes;
import dao.Entity.AvoirOulmes;
import dao.Entity.BonLivraison;
import dao.Entity.Client;
import dao.Entity.ClientMP;
import dao.Entity.DetailAvoirOulmes;
import dao.Entity.DetailBonLivraison;
import dao.Entity.Fournisseur;
import dao.Entity.HistoriqueAvoirOulmes;
import dao.Entity.MatierePremier;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
import dao.Entity.RegularisationAvoirOulmes;
import dao.Entity.Sequenceur;
import dao.Manager.AvoirOulmesDAO;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.ClientDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.DetailAvoirOulmesDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.HistoriqueAvoirOulmesDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.Manager.RegularisationAvoirOulmesDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.AvoirOulmesDAOImpl;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.ClientDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.DetailAvoirOulmesDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.HistoriqueAvoirOulmesDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
import dao.ManagerImpl.RegularisationAvoirOulmesDAOImpl;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
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
 * @author Hp
 */
public class AvoirOulmesController implements Initializable {

    @FXML
    private TableView<DetailAvoirOulmes> detailAvoireOulmestable;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> codeArtColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> libelleColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> clientColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> prixColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> montantColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> remiseColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> montantNetColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, Boolean> actionColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> bonAvoirColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> client2Column;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> factAvoirQteColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> factAvoirPrixColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> factAvoirRemiseColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> motifColumn;
    @FXML
    private TableView<DetailAvoirOulmes> detailAvoireOulmesListTable;
    @FXML
    private TableColumn<DetailAvoirOulmes, Date> dateBonAvoirColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> codeArtListColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> libelleListColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> quantiteListColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> prixListColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> montantListColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> remiseListColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> montantNetListColumn;
    
    @FXML
    private Button ajouterSaisie;
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField codeArtField;
    @FXML
    private DatePicker dateSaisie;
    @FXML
    private RadioButton radOui;
    @FXML
    private RadioButton radNon;
    @FXML
    private Label monHT;
    @FXML
    private Label monTVA;
    @FXML
    private Label monTTC;
    @FXML
    private TextField montantTotalField;
    @FXML
    private RadioButton radPlus;
    @FXML
    private RadioButton radmoin;
    @FXML
    private Label qteAfficchage;
    @FXML
    private ToggleGroup radOuiNon;
    @FXML
    private ToggleGroup radPlusMoin;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ComboBox<String> LibelleCombo;
    @FXML
    private TextField nLivraisonField;
    @FXML
    private TextField nLivraisonRechField;
    @FXML
    private ComboBox<String> clientCombo1;
    @FXML
    private RadioButton radClientOui;
    @FXML
    private RadioButton radClientNon;
    @FXML
    private TextField factureAvoirField;
    @FXML
    private Button validerSaisie;
    @FXML
    private Button btnRefreshGlobal;
    @FXML
    private ComboBox<String> client2Combo;
    @FXML
    private ComboBox<String> lieuLivCombo;
    @FXML
    private TextField numFactureField;
    @FXML
    private ToggleGroup radClientOuiNon2;
    @FXML
    private DatePicker dateFacture;
    @FXML
    private ComboBox<String> client2RechCombo;
    @FXML
    private Button imprimerBtn;
    @FXML
    private Label monTotal;
    
    private final ObservableList<DetailAvoirOulmes> listeDetailAvoirOulmes=FXCollections.observableArrayList();
    private final ObservableList<DetailAvoirOulmes> listDetailAvoirOulmesList=FXCollections.observableArrayList();
    
    navigation nav = new navigation();
    PrixOulmes prixOulmes =new PrixOulmes();
    AvoirOulmes avoirOulmes = new AvoirOulmes();
    Client client2 = new  Client();
//    Date date = new  Date();
            
    RegularisationAvoirOulmesDAO regularisationAvoirOulmesDAO = new RegularisationAvoirOulmesDAOImpl();
    ProduitDAO produitDAO = new ProduitDAOImpl();       
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    DetailAvoirOulmesDAO detailAvoirOulmesDAO = new DetailAvoirOulmesDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();  
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    MatierePremiereDAO matierePremierDAO = new MatierePremierDAOImpl();  
    BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
    DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
    AvoirOulmesDAO avoirOulmesDAO = new AvoirOulmesDAOImpl();
    HistoriqueAvoirOulmesDAO historiqueAvoirOulmesDAO =new HistoriqueAvoirOulmesDAOImpl();
    ClientDAO clientDAO =new ClientDAOImpl();
    
    private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    private Map<String,Client> mapClient=new HashMap<>();
    
    SimpleDateFormat dateFormat= new SimpleDateFormat("yy");
    
     BigDecimal prixOld = BigDecimal.ZERO;
    
     BigDecimal montanNet =BigDecimal.ZERO;
     BigDecimal MHT = BigDecimal.ZERO ;
     BigDecimal MTVA = BigDecimal.ZERO ;
     BigDecimal MTTC = BigDecimal.ZERO ;
    
     BigDecimal qteCaisse = BigDecimal.ZERO;
  
     BigDecimal montantHT = BigDecimal.ZERO;
    
//    ObservableList<String> client =FXCollections.observableArrayList(Constantes.CLIENT_MARJANE,Constantes.CLIENT_MINURSO);
    
    
          Sequenceur sequenceurF = sequenceurDAO.findByCode(Constantes.FACTURE_AVOIR_CODE);
          String FactCount = Constantes.FACTURE_AVOIR_CODE+(sequenceurF.getValeur()+1);
          
          Sequenceur sequenceurA = sequenceurDAO.findByCode(Constantes.BON_AVOIR_CODE);
          String AvoirCount = Constantes.BON_AVOIR_CODE+(sequenceurA.getValeur()+1);
 
    
    
    
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
//        client2Combo.setItems(client);
        
         List<Client> listCliient=clientDAO.findAll();
        
        listCliient.stream().map((client) -> {
            client2Combo.getItems().addAll(client.getLibelle());
             client2RechCombo.getItems().addAll(client.getLibelle());
            return client;
        }).forEachOrdered((client) -> {
            mapClient.put(client.getLibelle(), client);
        });
        
//        client2RechCombo.setItems(client);
        
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
        
        
            List<ClientMP> listClient=clientMPDAO.findAll();
        
        listClient.stream().map((client) -> {
            clientCombo1.getItems().addAll(client.getNom());
            return client;
        }).forEachOrdered((client) -> {
            mapClientMP.put(client.getNom(), client);
        });
        
          client2Combo.setDisable(true);
          lieuLivCombo.setDisable(true);
          
                detailAvoireOulmestable.setEditable(true);
                factAvoirQteColumn.setEditable(true);
                factAvoirPrixColumn.setEditable(true);
                factAvoirRemiseColumn.setEditable(true);
                prixColumn.setEditable(true);
                motifColumn.setEditable(true);
    }    

     void setColumnProperties(){

               codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });
               
               bonAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    
                    if (p.getValue().getAvoirOulmes().getNumLivraison()== null){
                    
                    p.getValue().getAvoirOulmes().setNumLivraison("");
                    }
                    
                    return new ReadOnlyObjectWrapper(p.getValue().getAvoirOulmes().getNumLivraison());
                    
                }
             });

                    libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
             });
        
            clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    
                    if (p.getValue().getAvoirOulmes().getClient()== null){
                    
                    p.getValue().getAvoirOulmes().setClient("");
                    }
                    
                    return new ReadOnlyObjectWrapper(p.getValue().getAvoirOulmes().getClient());
                }
             });
                    
            client2Column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    
                    if (p.getValue().getAvoirOulmes().getClient2()== null){
                    
                    p.getValue().getAvoirOulmes().setClient2("");
                    }
                    
                    return new ReadOnlyObjectWrapper(p.getValue().getAvoirOulmes().getClient2());
                }
             });
            
             quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQte()));
                }
                
             });
           
             dateBonAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, Date> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getAvoirOulmes().getDateSaisie());
                }
             });
             
             prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
        
                    return new ReadOnlyObjectWrapper(p.getValue().getPrix().setScale(6,RoundingMode.FLOOR));
                }
                
             });
             
//               setColumnTextFieldConverter(getConverter(), prixColumn);
               
               
             remiseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getRemiseAvoir());
                }
             });
           
             
             montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
             
                 montantNetColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    

                    return new ReadOnlyObjectWrapper(p.getValue().getMontantNet().setScale(2,RoundingMode.FLOOR));
                }
                
             });

        factAvoirQteColumn.setCellValueFactory(new PropertyValueFactory<DetailAvoirOulmes, BigDecimal>("qteFactureAvoir"));

        setColumnTextFieldConverter(getConverter(), factAvoirQteColumn);
        
        factAvoirPrixColumn.setCellValueFactory(new PropertyValueFactory<DetailAvoirOulmes, BigDecimal>("prixFactureAvoir"));

        setColumnTextFieldConverter(getConverter(), factAvoirPrixColumn);
        
        factAvoirRemiseColumn.setCellValueFactory(new PropertyValueFactory<DetailAvoirOulmes, BigDecimal>("remiseFactureAvoir"));

        setColumnTextFieldConverter(getConverter(), factAvoirRemiseColumn);

//________________________________________________________________________ Charge ComboBox in TableView _________________________________________________________________________________________________________________________________________________________________________________________________________
 
        motifColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMotif());
                }
             });
   
        ObservableList<String> listMotif = FXCollections.observableArrayList();
        
        listMotif.add("Sans");
        listMotif.add("Prob Prix");
        listMotif.add("Prob Qte");
        listMotif.add("Prob Remise");
        listMotif.add("Prob Prix / Prob Remise");
        listMotif.add("Prob Prix / Prob Qte");
        listMotif.add("Prob Remise / Prob Qte");
        listMotif.add("Prob Prix / Prob Remise / Prob Qte");

        motifColumn.setCellFactory(ComboBoxTableCell.forTableColumn(listMotif));
//_____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
     
        
          actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          DetailAvoirOulmes cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionColumn.setEditable(true);
      
          
          detailAvoireOulmestable.setEditable(true); 
          
         
     }
    
      void setColumnPropertiesList(){

               codeArtListColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });
               

                    libelleListColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
             });

             quantiteListColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQte()));
                }
                
             });
           

             prixListColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                
                    return new ReadOnlyObjectWrapper(p.getValue().getPrix().setScale(6,RoundingMode.FLOOR));
                }
                
             });
           
             remiseListColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getRemiseAvoir());
                }
             });
           
             montantListColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
             
                 montantNetListColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    

                    return new ReadOnlyObjectWrapper(p.getValue().getMontantNet().setScale(2,RoundingMode.FLOOR));
                }
                
             });

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
     
     
       void loadDetail(){
           
        listeDetailAvoirOulmes.clear();
        listeDetailAvoirOulmes.addAll(detailAvoirOulmesDAO.findByEtat(Constantes.ETAT_BL_AVOIR));
        detailAvoireOulmestable.setItems(listeDetailAvoirOulmes);
    }
    
         void clear(){
    
             monHT.setText("");
             monTVA.setText("");
             monTTC.setText("");

             monTotal.setText("");
             
             montantTotalField.clear();
             dateSaisie.setValue(null);
             clientCombo.getSelectionModel().select(-1);
             fourCombo.getSelectionModel().select(-1);
             client2Combo.getSelectionModel().select(-1);
             client2RechCombo.getSelectionModel().select(-1);
             lieuLivCombo.getSelectionModel().select(-1);
             
             nLivraisonRechField.setText("");
             clientCombo1.getSelectionModel().select(-1);
             
    LibelleCombo.getSelectionModel().select(-1);        
    
    codeArtField.clear();
    quantiteField.clear();
    nLivraisonField.clear();
    qteAfficchage.setText("");
    

    factureAvoirField.clear();
    numFactureField.clear();
    
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
    
    listDetailAvoirOulmesList.clear();
    
            loadDetail();
        setColumnProperties();
    
    }  
       

    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
        
                          if (event.getCode().equals(KeyCode.ENTER) )
            {

                        prixOulmes = prixOulmesDAO.findPrixOulmesByCodeArt(codeArtField.getText());

                         LibelleCombo.setValue(prixOulmes.getProduit().getLibelle());
              }
  
    }

void calculeDetailAvoir(){

           BigDecimal montantTotal= BigDecimal.ZERO;

    for (int i = 0; i < listDetailAvoirOulmesList.size(); i++) {
        
        DetailAvoirOulmes detailAvoirOulmes = listDetailAvoirOulmesList.get(i);
        
        montantTotal = montantTotal.add(detailAvoirOulmes.getMontantNet());
    }

             DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
    
    monTotal.setText(df.format(montantTotal.setScale(2,RoundingMode.FLOOR)));
    
}

    @FXML
    private void calculeMouseClicked(MouseEvent event) {
        
         BigDecimal montantTotal= BigDecimal.ZERO;
          BigDecimal montantPalette= BigDecimal.ZERO;
           MHT = BigDecimal.ZERO ;
           MTVA = BigDecimal.ZERO ;
           MTTC = BigDecimal.ZERO ;
            
            
           for( int rows = 0;rows<listeDetailAvoirOulmes.size() ;rows++ ){
 
                 DetailAvoirOulmes detailAvoirOulmes = listeDetailAvoirOulmes.get(rows);
               
        if (detailAvoirOulmes.getAction()==true){
        
           if (detailAvoirOulmes.getPrixOulmes().getProduit().getPalette() == true){
                
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
            
            
              montantTotalField.setText(df.format(MTTC.setScale(2,RoundingMode.FLOOR)));  

       
        
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
    private void fournisseurOnAction(ActionEvent event) {
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }

    private void LibelleComboOnAction(ActionEvent event) {
    }

    @FXML
    private void rechercheBonAvoirKeyPressed(KeyEvent event) {
    }
        

    @FXML
    private void rechercheMouseClicked(MouseEvent event) {
        
         listeDetailAvoirOulmes.clear();
        
         String bonAvoir = nLivraisonRechField.getText();
       String client2Rech = client2RechCombo.getSelectionModel().getSelectedItem();
       ClientMP clientMP = mapClientMP.get(clientCombo1.getSelectionModel().getSelectedItem());
         
           if (clientCombo1.getSelectionModel().getSelectedIndex() == -1 &&
                   nLivraisonRechField.getText().equals("") &&
                   client2RechCombo.getSelectionModel().getSelectedIndex() == -1
                   ){
 
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
            } else{
           
             String req = "";
         
        if(clientCombo1.getSelectionModel().getSelectedIndex()!= -1) {
             
              req= req+" and u.avoirOulmes.client='"+clientMP.getNom()+"'";
              
             }
             
        if(!nLivraisonRechField.getText().equals("")){
             
             req= req+" and u.avoirOulmes.numLivraison='"+bonAvoir+"'";

            }
             
        if(client2RechCombo.getSelectionModel().getSelectedIndex()!= -1){
             
               req= req+" and u.avoirOulmes.client2='"+client2Rech+"'";
      
             }
           
                 listeDetailAvoirOulmes.clear();
             
            listeDetailAvoirOulmes.addAll(detailAvoirOulmesDAO.findByAllFilter(Constantes.ETAT_BL_AVOIR,req));

        detailAvoireOulmestable.setItems(listeDetailAvoirOulmes); 
        
        
        
           }
        
        
        
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
        
        if (valeur!=null){

        if (valeur.equals(Constantes.CLIENT_MINURSO)){
            
            lieuLivCombo.getItems().clear();
            lieuLivCombo.setDisable(false);
            
   List <String> listeObjectLieu =prixOulmesDAO.findPrixOulmesByClient(Constantes.CLIENT_MINURSO);

   System.out.println("listeObjectLieu "+listeObjectLieu.size());
         
            for(int i=0; i<listeObjectLieu.size(); i++) {

                    String prixOulmes = listeObjectLieu.get(i);

                       lieuLivCombo.getItems().add(prixOulmes);
                       
                    }

        }
        else if (valeur.equals(Constantes.CLIENT_MARJANE)){
            
            lieuLivCombo.getItems().clear();
            lieuLivCombo.setDisable(true);
            
             List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findPrixFilmByClient(Constantes.SANS);

            for (int i = 0; i < listPrixOulmes.size(); i++) {
                
                PrixOulmes prixOulmes = listPrixOulmes.get(i);
                
                LibelleCombo.getItems().add(prixOulmes.getProduit().getLibelle());
//              lieuLivCombo.getItems().add(prixOulmes.getLieuLivraison());
                
                mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
               
            }
            
            
        }if (valeur.equals(Constantes.CLIENT_MAROCEAN)){
            
            lieuLivCombo.getItems().clear();
            lieuLivCombo.setDisable(true);
            
      List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findPrixFilmByClient(Constantes.CLIENT_MAROCEAN);

            for (int i = 0; i < listPrixOulmes.size(); i++) {
                
                PrixOulmes prixOulmes = listPrixOulmes.get(i);
                
                LibelleCombo.getItems().add(prixOulmes.getProduit().getLibelle());
                lieuLivCombo.getItems().add(prixOulmes.getLieuLivraison());
                
                mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);

        }
    }

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
    private void lieuLivOnAction(ActionEvent event) {
        
        
        String valeur = client2Combo.getSelectionModel().getSelectedItem();
        
        if (valeur!= null){
           if (valeur.equals(Constantes.CLIENT_MINURSO)){
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
}

    @FXML
    private void validerSaisieAction(ActionEvent event) throws ParseException {
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
    
                if(detailAvoireOulmestable.equals(null)|| 
                        monHT.getText().equals("")|| 
                        monTVA.getText().equals("")|| 
                        monTTC.getText().equals("")|| 
                        montantTotalField.getText().equals("")|| 
                   numFactureField.getText().equalsIgnoreCase("")||
                   factureAvoirField.getText().equalsIgnoreCase("")

                   ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
    }
else {
              String BonAvoir = null;
              boolean valeur = false;
              boolean exist = false;
              
                     for( int rows = 0; rows<detailAvoireOulmestable.getItems().size() ;rows++ ){
                         
               DetailAvoirOulmes detailAvoirOulmes= detailAvoireOulmestable.getItems().get(rows);
                
          if (detailAvoirOulmes.getAction()==true){
        
           if (BonAvoir == null){
                
               BonAvoir =detailAvoirOulmes.getAvoirOulmes().getNumLivraison();
               valeur = true;
                }else{
               if (BonAvoir.equals(detailAvoirOulmes.getAvoirOulmes().getNumLivraison())){
               
               valeur = true;
               
               }else{
               
               exist= true;
               }
                } 
             }if (exist== true){
                 nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.VERIFIER_BON_AVOIR);
                 return;
               }
        }    
                    
               if (valeur == true){
                   
                   Date dateFact=new SimpleDateFormat("yyyy-MM-dd").parse(dateFacture.getValue().toString());
                   
            if (MTTC.setScale(2,RoundingMode.FLOOR).compareTo(new BigDecimal(factureAvoirField.getText()))==0 ){
            
               String four = "";
               String clientAVR = "";
               Date dateSaisie = null;
               String bonAvoir = "";
               BigDecimal montantTotal= BigDecimal.ZERO;
               BigDecimal montantPalette= BigDecimal.ZERO;

              
               
               
                    for( int rows = 0;rows<detailAvoireOulmestable.getItems().size() ;rows++ ){
                         
               DetailAvoirOulmes detailAvoirOulmes= detailAvoireOulmestable.getItems().get(rows);
               
          if (detailAvoirOulmes.getAction()==true){

              if (detailAvoirOulmes.getPrixOulmes().getProduit().getPalette() == true){
                
                montantPalette = montantPalette.add(montantNetColumn.getCellData(rows));
                
                }else{

                montantTotal = montantTotal.add(montantNetColumn.getCellData(rows));  
                }
             
//___________________________________________________________________________________ DetailAvoirOulmes  __________________________________________________________________________________________       
     
            BigDecimal montantNetFactureAvr = BigDecimal.ZERO;

            if(detailAvoirOulmes.getTypeRemise().equals(Constantes.PLUS)){
                
                montantNetFactureAvr = (detailAvoirOulmes.getPrixFactureAvoir().multiply(detailAvoirOulmes.getQteFactureAvoir())).add(detailAvoirOulmes.getPrixFactureAvoir().multiply(detailAvoirOulmes.getQteFactureAvoir()).multiply(detailAvoirOulmes.getRemiseFactureAvoir()).divide(new BigDecimal(100),2,RoundingMode.CEILING));
            
            }else if(detailAvoirOulmes.getTypeRemise().equals(Constantes.MOINS)){
                
                  montantNetFactureAvr = (detailAvoirOulmes.getPrixFactureAvoir().multiply(detailAvoirOulmes.getQteFactureAvoir())).subtract(detailAvoirOulmes.getPrixFactureAvoir().multiply(detailAvoirOulmes.getQteFactureAvoir()).multiply(detailAvoirOulmes.getRemiseFactureAvoir()).divide(new BigDecimal(100),2,RoundingMode.CEILING));
                
            }else{
            
                 montantNetFactureAvr = (detailAvoirOulmes.getPrixFactureAvoir().multiply(detailAvoirOulmes.getQteFactureAvoir()));
                
            }


            detailAvoirOulmes.setMontantNetFactureAvoir(montantNetFactureAvr);
            detailAvoirOulmes.setAction(Boolean.FALSE);
            detailAvoirOulmesDAO.edit(detailAvoirOulmes);
               
//___________________________________________________________________________________ DetailBonLivraision __________________________________________________________________________________________       
          
                            String  client ="";         
                           if(detailAvoirOulmes.getAvoirOulmes().getClient2().equals(Constantes.CLIENT_MINURSO)){
                           client = detailAvoirOulmes.getAvoirOulmes().getClient2();
                           }else if (detailAvoirOulmes.getAvoirOulmes().getClient2().equals(Constantes.CLIENT_MARJANE)){
                           client =  Constantes.SANS;
                           }else if (detailAvoirOulmes.getAvoirOulmes().getClient2().equals(Constantes.CLIENT_MAROCEAN)){
                           client = detailAvoirOulmes.getAvoirOulmes().getClient2();
                           }else{
                           client =  Constantes.SANS;
                           }
  
                PrixOulmes prixOulmes = prixOulmesDAO.findPrixOulmesByCodeArt(codeArtColumn.getCellData(rows),client,detailAvoirOulmes.getAvoirOulmes().getLieuLivraison());

                MatierePremier matierePremier = matierePremierDAO.findByCode(Constantes.CODE_MP);

                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(AvoirCount);
                detailBonLivraison.setMontant(detailAvoirOulmes.getMontantNet());
                detailBonLivraison.setRemiseAchat(detailAvoirOulmes.getRemiseAvoir());
                detailBonLivraison.setBonAvoir(detailAvoirOulmes.getAvoirOulmes().getNumLivraison());
                detailBonLivraison.setNumCommande("OLF/19");
                detailBonLivraison.setMotif(Constantes.SANS);
                detailBonLivraison.setPrix(detailAvoirOulmes.getPrix());
                detailBonLivraison.setPrixOulmes(prixOulmes);
                detailBonLivraison.setClient2(detailAvoirOulmes.getAvoirOulmes().getClient2());
                detailBonLivraison.setNumFacture(numFactureField.getText());
                detailBonLivraison.setQuantite(detailAvoirOulmes.getQte());
                detailBonLivraison.setMatierePremier(matierePremier);
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);
           
                four = detailAvoirOulmes.getAvoirOulmes().getFournisseur();
                clientAVR = detailAvoirOulmes.getAvoirOulmes().getClient();
                dateSaisie = detailAvoirOulmes.getAvoirOulmes().getDateSaisie();
                bonAvoir = detailAvoirOulmes.getAvoirOulmes().getNumLivraison();
                
                }
        } 
          
//_________________________________________________________________________ AvoirOulmes  __________________________________________________________________________________________________________________________


            AvoirOulmes avoirOulmes = avoirOulmesDAO.findBonRetourByNumCommAndNumLiv(bonAvoir);

            avoirOulmes.setAvoir(MTTC.setScale(2,RoundingMode.FLOOR));
            avoirOulmes.setFactureAvoir(new BigDecimal(factureAvoirField.getText()));
            avoirOulmes.setNumFacture(numFactureField.getText());
            avoirOulmes.setDateFacture(dateFact);
            avoirOulmes.setTypeEcart("Avoir");
            avoirOulmes.setFactureOulmes(BigDecimal.ZERO.setScale(2));
            avoirOulmes.setDateCreation(new Date());
            avoirOulmes.setFactureSysteme(BigDecimal.ZERO.setScale(2));
            avoirOulmes.setEcart(BigDecimal.ZERO.setScale(2));
            avoirOulmes.setRegularisation(BigDecimal.ZERO.setScale(2));
            avoirOulmes.setEtat(Constantes.ETAT_REGLEMENT_AVOIR);
            avoirOulmesDAO.edit(avoirOulmes);
                    
//_________________________________________________________________________ Bon Livraision __________________________________________________________________________________________________________________________

            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
           
            montantHT= montantTotal.add(montantPalette); 
            montantTVA=  montantTotal.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
            montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
                  

          BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(four);
          bonLivraison.setMontant(montantHT);
          bonLivraison.setLivraisonFour(AvoirCount);
          bonLivraison.setEtat(Constantes.ETAT_A_PAYER);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setClient(clientAVR);
          bonLivraison.setMontantRG(montantTTC);
          bonLivraison.setNumFacture(FactCount);
          bonLivraison.setNumCommande("OLF/19");
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_FACTURE);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(dateSaisie);
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
        
     
            Sequenceur sequenceurF = sequenceurDAO.findByCode(Constantes.FACTURE_AVOIR_CODE);
            sequenceurF.setValeur(sequenceurF.getValeur()+1);
            sequenceurDAO.edit(sequenceurF);
         
            FactCount = Constantes.FACTURE_AVOIR_CODE+(sequenceurF.getValeur()+1);
            
             Sequenceur sequenceurA = sequenceurDAO.findByCode(Constantes.BON_AVOIR_CODE);
            sequenceurA.setValeur(sequenceurA.getValeur()+1);
            sequenceurDAO.edit(sequenceurA);
         
            AvoirCount = Constantes.BON_AVOIR_CODE+(sequenceurA.getValeur()+1);
            
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);    
            
            setColumnProperties();
            loadDetail();
             clear();
            
            
            }else{

               String four = "";
               String clientAVR = "";
               Date dateSaisie = null;
               String bonAvoir = "";
               BigDecimal montantTotal= BigDecimal.ZERO;
               BigDecimal montantPalette= BigDecimal.ZERO;
                
                   for( int rows = 0;rows<detailAvoireOulmestable.getItems().size() ;rows++ ){
                         
               DetailAvoirOulmes detailAvoirOulmes= detailAvoireOulmestable.getItems().get(rows);
               
          if (detailAvoirOulmes.getAction()==true){

                 if (detailAvoirOulmes.getPrixOulmes().getProduit().getPalette() == true){
                
                montantPalette = montantPalette.add(montantNetColumn.getCellData(rows));
                
                }else{

                montantTotal = montantTotal.add(montantNetColumn.getCellData(rows));  
                }
                 
//___________________________________________________________________________________ DetailAvoirOulmes  __________________________________________________________________________________________       
     
              BigDecimal montantNetFactureAvr = BigDecimal.ZERO;

            if(detailAvoirOulmes.getTypeRemise().equals(Constantes.PLUS)){
                
                montantNetFactureAvr = (detailAvoirOulmes.getPrixFactureAvoir().multiply(detailAvoirOulmes.getQteFactureAvoir())).add(detailAvoirOulmes.getPrixFactureAvoir().multiply(detailAvoirOulmes.getQteFactureAvoir()).multiply(detailAvoirOulmes.getRemiseFactureAvoir()).divide(new BigDecimal(100),2,RoundingMode.CEILING));
            
            }else if(detailAvoirOulmes.getTypeRemise().equals(Constantes.MOINS)){
                
                  montantNetFactureAvr = (detailAvoirOulmes.getPrixFactureAvoir().multiply(detailAvoirOulmes.getQteFactureAvoir())).subtract(detailAvoirOulmes.getPrixFactureAvoir().multiply(detailAvoirOulmes.getQteFactureAvoir()).multiply(detailAvoirOulmes.getRemiseFactureAvoir()).divide(new BigDecimal(100),2,RoundingMode.CEILING));
                
            }else{
            
                 montantNetFactureAvr = (detailAvoirOulmes.getPrixFactureAvoir().multiply(detailAvoirOulmes.getQteFactureAvoir()));
                
            }


            detailAvoirOulmes.setMontantNetFactureAvoir(montantNetFactureAvr);
            detailAvoirOulmes.setAction(Boolean.FALSE);
            detailAvoirOulmesDAO.edit(detailAvoirOulmes);
                             
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
                            String  client ="";         
                           if(detailAvoirOulmes.getAvoirOulmes().getClient2().equals(Constantes.CLIENT_MINURSO)){
                           client = detailAvoirOulmes.getAvoirOulmes().getClient2();
                           }else if (detailAvoirOulmes.getAvoirOulmes().getClient2().equals(Constantes.CLIENT_MARJANE)){
                           client =  Constantes.SANS;
                           }else if (detailAvoirOulmes.getAvoirOulmes().getClient2().equals(Constantes.CLIENT_MAROCEAN)){
                           client = detailAvoirOulmes.getAvoirOulmes().getClient2();
                           }else{
                           client =  Constantes.SANS;
                           }
  
                PrixOulmes prixOulmes = prixOulmesDAO.findPrixOulmesByCodeArt(codeArtColumn.getCellData(rows),client,detailAvoirOulmes.getAvoirOulmes().getLieuLivraison());

                MatierePremier matierePremier = matierePremierDAO.findByCode(Constantes.CODE_MP);

                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(AvoirCount);
                detailBonLivraison.setMontant(detailAvoirOulmes.getMontantNet());
                detailBonLivraison.setRemiseAchat(detailAvoirOulmes.getRemiseAvoir());
                detailBonLivraison.setBonAvoir(detailAvoirOulmes.getAvoirOulmes().getNumLivraison());
                detailBonLivraison.setNumCommande("OLF/19");
                detailBonLivraison.setPrix(detailAvoirOulmes.getPrix());
                detailBonLivraison.setPrixOulmes(prixOulmes);
                detailBonLivraison.setMotif(Constantes.SANS);
                detailBonLivraison.setClient2(detailAvoirOulmes.getAvoirOulmes().getClient2());
                detailBonLivraison.setNumFacture(numFactureField.getText());
                detailBonLivraison.setQuantite(detailAvoirOulmes.getQte());
                detailBonLivraison.setMatierePremier(matierePremier);
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);

           
                four = detailAvoirOulmes.getAvoirOulmes().getFournisseur();
                clientAVR = detailAvoirOulmes.getAvoirOulmes().getClient();
                dateSaisie = detailAvoirOulmes.getAvoirOulmes().getDateSaisie();
                bonAvoir = detailAvoirOulmes.getAvoirOulmes().getNumLivraison();
                
                }
            }
//_____________________________________________________________________________ AvoirOulmes ______________________________________________________________________________________________________________________________________________________________________________________



            AvoirOulmes avoirOulmes = avoirOulmesDAO.findBonRetourByNumCommAndNumLiv(bonAvoir);

            avoirOulmes.setFactureAvoir(new BigDecimal(factureAvoirField.getText()));
            avoirOulmes.setAvoir(MTTC.setScale(2,RoundingMode.FLOOR));
            avoirOulmes.setNumFacture(numFactureField.getText());
            avoirOulmes.setDateFacture(dateFact);
            avoirOulmes.setTypeEcart("Avoir");
            avoirOulmes.setFactureOulmes(BigDecimal.ZERO.setScale(2));
            avoirOulmes.setDateCreation(new Date());
            avoirOulmes.setFactureSysteme(BigDecimal.ZERO.setScale(2));
            avoirOulmes.setEcart(MTTC.setScale(2,RoundingMode.FLOOR).subtract(new BigDecimal(factureAvoirField.getText())).setScale(2,RoundingMode.FLOOR));
            avoirOulmes.setRegularisation(BigDecimal.ZERO.setScale(2));
            avoirOulmes.setEtat(Constantes.ETAT_EN_ATTENTE_AVOIR);
            avoirOulmesDAO.edit(avoirOulmes); 
//_________________________________________________________________________ Regularisation Avoir __________________________________________________________________________________________________________________________

                RegularisationAvoirOulmes regularisationAvoirOulmes = new RegularisationAvoirOulmes();

                regularisationAvoirOulmes.setBonAvoir(bonAvoir);
                regularisationAvoirOulmes.setDateSaisie(dateFact);
                regularisationAvoirOulmes.setNumFacture(numFactureField.getText());
                regularisationAvoirOulmes.setQte(new BigDecimal(factureAvoirField.getText()).setScale(2,RoundingMode.FLOOR));
                regularisationAvoirOulmes.setUtilisateurCreation(nav.getUtilisateur());
                
                regularisationAvoirOulmesDAO.add(regularisationAvoirOulmes);
                
//_________________________________________________________________________ Bon Livraision __________________________________________________________________________________________________________________________

            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
           
            montantHT= new BigDecimal(factureAvoirField.getText()).divide(new BigDecimal(1.2), 2, RoundingMode.FLOOR); 
            montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
            montantTTC= new BigDecimal(factureAvoirField.getText());
                  

          BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(four);
          bonLivraison.setMontant(montantHT);
          bonLivraison.setLivraisonFour(AvoirCount);
          bonLivraison.setEtat(Constantes.ETAT_A_PAYER);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setMontantRG(montantTTC);
          bonLivraison.setClient(clientAVR);
          bonLivraison.setNumFacture(FactCount);
          bonLivraison.setNumCommande("OLF/19");
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_FACTURE);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(dateSaisie);
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
        
     
            Sequenceur sequenceurF = sequenceurDAO.findByCode(Constantes.FACTURE_AVOIR_CODE);
            sequenceurF.setValeur(sequenceurF.getValeur()+1);
            sequenceurDAO.edit(sequenceurF);
         
            FactCount = Constantes.FACTURE_AVOIR_CODE+(sequenceurF.getValeur()+1);
            
             Sequenceur sequenceurA = sequenceurDAO.findByCode(Constantes.BON_AVOIR_CODE);
            sequenceurA.setValeur(sequenceurA.getValeur()+1);
            sequenceurDAO.edit(sequenceurA);
         
            AvoirCount = Constantes.BON_AVOIR_CODE+(sequenceurA.getValeur()+1);
            
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);    
            
            setColumnProperties();
            loadDetail();
             clear();
                   
        }
                      
                    
    }
               }   }
            }
        
         
    @FXML
    private void refreshGlobal(ActionEvent event) {
        
            clear();
        
    }


    @FXML
    private void ajouterMouseClicked(MouseEvent event) throws ParseException {
        
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
              
                          Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());  
                             Produit produit = produitDAO.findByCode(codeArtField.getText());
                             
                                    String  client ="";         
                       if(client2Combo.getSelectionModel().getSelectedIndex()!=-1){
                       if(client2Combo.getSelectionModel().getSelectedItem().equals(Constantes.CLIENT_MINURSO)){
                           client = client2Combo.getSelectionModel().getSelectedItem();
                          }else if (client2Combo.getSelectionModel().getSelectedItem().equals(Constantes.CLIENT_MARJANE)){
                           client =  Constantes.SANS;
                           }else if (client2Combo.getSelectionModel().getSelectedItem().equals(Constantes.CLIENT_MAROCEAN)){
                           client = client2Combo.getSelectionModel().getSelectedItem();
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
                
//############################################################################# DetailAvoirOulmes #######################################################################################################################################
                
                  DetailAvoirOulmes detailAvoirOulmes = new DetailAvoirOulmes();
                
                  
                prixB= prixOulmes.getPrix();
     
               
                
                detailAvoirOulmes.setPrix(prixB);
                
                if(qteCaisse.compareTo(BigDecimal.ZERO)==0){
                    
                     BigDecimal QteB= new BigDecimal(quantiteField.getText());
                detailAvoirOulmes.setQte(QteB);
                
                BigDecimal  montantB= (QteB.multiply(prixB)).setScale(2,RoundingMode.FLOOR);
                
                detailAvoirOulmes.setMontant(montantB);
                
                 if (radOui.isSelected()==true){
             
              radPlus.setVisible(true);
              radmoin.setVisible(true);
              
               if (radPlus.isSelected()==true){
               
                remise = prixOulmes.getRemiseAvoir();
                 
                montanNet = montantB.add((montantB.multiply(remise)).divide(new BigDecimal(100)));
                 
                  detailAvoirOulmes.setMontantNet(montanNet);
                  detailAvoirOulmes.setRemiseAvoir(remise);
                  detailAvoirOulmes.setTypeRemise(Constantes.PLUS);
                  
               }else if (radmoin.isSelected()==true){
               
                remise = prixOulmes.getRemiseAvoir();
 
                montanNet = montantB.subtract((montantB.multiply(remise)).divide(new BigDecimal(100)));
                 
                  detailAvoirOulmes.setMontantNet(montanNet);
                  detailAvoirOulmes.setRemiseAvoir(remise);
                  detailAvoirOulmes.setTypeRemise(Constantes.MOINS);
                  
               }
               
             }else{
             
                 montanNet = montantB;
                 
                 detailAvoirOulmes.setMontantNet(montanNet);
                 detailAvoirOulmes.setRemiseAvoir(BigDecimal.ZERO.setScale(2));
                 detailAvoirOulmes.setTypeRemise(Constantes.SANS);
             }
                
                }else {

                detailAvoirOulmes.setQte(qteCaisse);
                
                 BigDecimal  montantB= (qteCaisse.multiply(prixB)).setScale(2,RoundingMode.FLOOR);
                
                detailAvoirOulmes.setMontant(montantB);
                
                 if (radOui.isSelected()==true){
             
              radPlus.setVisible(true);
              radmoin.setVisible(true);
              
               if (radPlus.isSelected()==true){
               
                remise = prixOulmes.getRemiseAvoir();
                 
                 montanNet = montantB.add((montantB.multiply(remise)).divide(new BigDecimal(100)));
                 
                  detailAvoirOulmes.setMontantNet(montanNet);
                  detailAvoirOulmes.setRemiseAvoir(remise);
                  detailAvoirOulmes.setTypeRemise(Constantes.PLUS);
                   
               }else if (radmoin.isSelected()==true){
               
                remise = prixOulmes.getRemiseAvoir();
                 
                 montanNet = montantB.subtract((montantB.multiply(remise)).divide(new BigDecimal(100)));
                 
                  detailAvoirOulmes.setMontantNet(montanNet);
                  detailAvoirOulmes.setRemiseAvoir(remise);
                  detailAvoirOulmes.setTypeRemise(Constantes.MOINS);
               
               }
               
             }else{
             
                 montanNet = montantB;
                 
                 detailAvoirOulmes.setMontantNet(montanNet);
                 detailAvoirOulmes.setRemiseAvoir(BigDecimal.ZERO.setScale(2));
                 detailAvoirOulmes.setTypeRemise(Constantes.SANS);
             
             }
                
                }
             detailAvoirOulmes.setPrixOulmes(prixOulmes);
             detailAvoirOulmes.setAction(Boolean.FALSE);
             detailAvoirOulmes.setPrixFactureAvoir(BigDecimal.ZERO.setScale(2));
             detailAvoirOulmes.setMontantNetFactureAvoir(BigDecimal.ZERO.setScale(2));
             detailAvoirOulmes.setMotif(Constantes.SANS);
             detailAvoirOulmes.setQteFactureAvoir(BigDecimal.ZERO.setScale(2));
             detailAvoirOulmes.setRemiseFactureAvoir(BigDecimal.ZERO.setScale(2));
             detailAvoirOulmes.setAvoirOulmes(avoirOulmes);
             detailAvoirOulmes.setUtilisateurCreation(nav.getUtilisateur());
             
             listDetailAvoirOulmesList.add(detailAvoirOulmes);
             
               detailAvoireOulmesListTable.setItems(listDetailAvoirOulmesList);
          
             codeArtField.clear();
             LibelleCombo.getSelectionModel().select(-1);   
             quantiteField.clear();
             qteAfficchage.setText("");
             radOui.setSelected(false);
             radNon.setSelected(false);
    
    radPlus.setVisible(false);
    radmoin.setVisible(false);
    radOui.setSelected(false);
    radNon.setSelected(false);
    
            setColumnPropertiesList();
calculeDetailAvoir();
         }
}
        
            }
        
    }

    @FXML
    private void editCommitQteAvoirArtColumn(TableColumn.CellEditEvent<DetailAvoirOulmes, BigDecimal> event) {
        
         
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    BigDecimal factAvoirQte = BigDecimal.ZERO;
                    
                    
                ((DetailAvoirOulmes) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQteFactureAvoir(event.getNewValue());
               
                    detailAvoireOulmestable.refresh();  

                 factAvoirQte = factAvoirQteColumn.getCellData(event.getTablePosition().getRow());
                
                 listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).setQteFactureAvoir(factAvoirQte.setScale(2, RoundingMode.FLOOR));
                
                 detailAvoirOulmesDAO.edit(listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()));
                 
                setColumnProperties();

           

            } else if (result.get() == ButtonType.CANCEL) {
                detailAvoireOulmestable.refresh();
            }
        
        
    }

    @FXML
    private void editCommitFactAvoirPrixColumn(TableColumn.CellEditEvent<DetailAvoirOulmes, BigDecimal> event) {
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    BigDecimal factAvoirPrix = BigDecimal.ZERO;
                    
                    
                ((DetailAvoirOulmes) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setPrixFactureAvoir(event.getNewValue());
               
                    detailAvoireOulmestable.refresh();  

                 factAvoirPrix = factAvoirPrixColumn.getCellData(event.getTablePosition().getRow());
                
                 listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).setPrixFactureAvoir(factAvoirPrix.setScale(2, RoundingMode.FLOOR));
                
                 detailAvoirOulmesDAO.edit(listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()));
                 
                setColumnProperties();

           

            } else if (result.get() == ButtonType.CANCEL) {
                detailAvoireOulmestable.refresh();
            }
        
        
    }

    @FXML
    private void editCommitFactAvoirRemiseColumn(TableColumn.CellEditEvent<DetailAvoirOulmes, BigDecimal> event) {
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    BigDecimal factAvoirRemise = BigDecimal.ZERO;
                    
                    
                ((DetailAvoirOulmes) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setRemiseFactureAvoir(event.getNewValue());
               
                    detailAvoireOulmestable.refresh();  

                 factAvoirRemise = factAvoirRemiseColumn.getCellData(event.getTablePosition().getRow());
                
                 listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).setRemiseFactureAvoir(factAvoirRemise.setScale(2, RoundingMode.FLOOR));
                
                 detailAvoirOulmesDAO.edit(listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()));
                 
                setColumnProperties();

           

            } else if (result.get() == ButtonType.CANCEL) {
                detailAvoireOulmestable.refresh();
            }
        
        
    }

    @FXML
    private void ajouterSaisieAction(ActionEvent event) throws ParseException {
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
   
//########################################################################### AvoirOulmes #####################################################################################################################################################         
              
Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());  
                
            ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
            
            
                  LocalDate localDate=dateSaisie.getValue();
            
                Date dateSaisieAvoir=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());

                if (listDetailAvoirOulmesList.size()==0){
                
                    return;
                
                }
  
                      if (client2Combo.getSelectionModel().getSelectedIndex()!=-1){
                          avoirOulmes.setClient2(client2Combo.getSelectionModel().getSelectedItem());
                       }else {
                         avoirOulmes.setClient2(Constantes.SANS);  
                       }

                      if (lieuLivCombo.getSelectionModel().getSelectedIndex()!=-1){
                         avoirOulmes.setLieuLivraison(lieuLivCombo.getSelectionModel().getSelectedItem());     
                       }else{
                         avoirOulmes.setLieuLivraison(Constantes.SANS);  
                       }  
  

             avoirOulmes.setClient(clientMP.getNom());
             avoirOulmes.setAvoir(BigDecimal.ZERO);
             avoirOulmes.setFactureAvoir(BigDecimal.ZERO);
             avoirOulmes.setEcart(BigDecimal.ZERO);
             avoirOulmes.setAction(Boolean.FALSE);
             avoirOulmes.setFournisseur(fournisseur.getNom());
             avoirOulmes.setFactureSysteme(BigDecimal.ZERO);
             avoirOulmes.setDateCreation(new Date());
             avoirOulmes.setTypeEcart("Avoir");
             avoirOulmes.setFactureOulmes(BigDecimal.ZERO);
             avoirOulmes.setDesignation(Constantes.ETAT_BL_AV+nLivraisonField.getText());
             avoirOulmes.setUtilisateurCreation(nav.getUtilisateur());
             avoirOulmes.setEtat(Constantes.ETAT_BL_AVOIR);
             avoirOulmes.setNumLivraison(nLivraisonField.getText());
             avoirOulmes.setDateSaisie(dateSaisieAvoir);
             

            avoirOulmesDAO.add(avoirOulmes);
            
            for (int i = 0; i < listDetailAvoirOulmesList.size(); i++) {
            
                
                DetailAvoirOulmes detailAvoirOulmes = listDetailAvoirOulmesList.get(i);
                
                detailAvoirOulmesDAO.add(detailAvoirOulmes);
                
        }
            

            avoirOulmes = new AvoirOulmes();
            
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
       
              
              loadDetail();
            setColumnProperties();
               clear();

            }
    }

    @FXML
    private void prixOnEditCommit(TableColumn.CellEditEvent<DetailAvoirOulmes, BigDecimal> event) {
        
//        
//          Alert alert = new Alert(AlertType.CONFIRMATION);
//            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
//            alert.setTitle("Confirmation");
//            Optional<ButtonType> result = alert.showAndWait();
//
//            if (result.get() == ButtonType.OK) {
//
//                    BigDecimal prix = BigDecimal.ZERO;
//                    BigDecimal qte= BigDecimal.ZERO;
//                    BigDecimal remise= BigDecimal.ZERO;
//                    String typeRemise = "";
//                    BigDecimal montant= BigDecimal.ZERO;
//                    BigDecimal montantNet= BigDecimal.ZERO; 
//                    
//  
//                    
//                ((DetailAvoirOulmes) event.getTableView().getItems().get(event.getTablePosition().getRow()))
//                        .setPrix(event.getNewValue());
//               
//                
//                    detailAvoireOulmestable.refresh();  
//
//  
//                 prix = prixColumn.getCellData(event.getTablePosition().getRow());
//                
//                 qte =  listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).getQte();
//
//                 remise = listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).getRemiseAvoir();
//
//                 typeRemise = listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).getTypeRemise();
//                                  
//                 montant = qte.multiply(prix).setScale(2, RoundingMode.FLOOR);
//               
//                 if (typeRemise.equals(Constantes.PLUS)){
//                 
//                 montantNet = montant.add((montant.multiply(remise)).divide(new BigDecimal(100)));
//                 
//                 }else if (typeRemise.equals(Constantes.MOINS)){
//                 
//                 montantNet = montant.subtract((montant.multiply(remise)).divide(new BigDecimal(100)));
//                 
//                 }else {
//                 
//                 montantNet = montant;
//                 
//                 }
//                 
//                 listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).setPrix(prix);
//                 listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).setMontant(montant);
//                 listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).setMontantNet(montantNet);
//                 
//                 detailAvoirOulmesDAO.edit(listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()));
//                 
////#######################################################################################################################################################################################################################################################################################################################                
//                     
//
//                HistoriqueAvoirOulmes historiqueAvoirOulmes = new HistoriqueAvoirOulmes();
//        
//        historiqueAvoirOulmes.setAncienPrix(prixOld);
//        historiqueAvoirOulmes.setNouveauPrix(prix);
//        historiqueAvoirOulmes.setBonAvoir(listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).getAvoirOulmes().getNumLivraison());
//        historiqueAvoirOulmes.setClient(listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).getAvoirOulmes().getClient());
//        historiqueAvoirOulmes.setClient2(listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).getAvoirOulmes().getClient2());
//        historiqueAvoirOulmes.setPrixOulmes(listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).getPrixOulmes());
//        historiqueAvoirOulmes.setDateSaisie(new Date());
//        
//        historiqueAvoirOulmes.setUtilisateurCreation(nav.getUtilisateur());
//        
//        historiqueAvoirOulmesDAO.add(historiqueAvoirOulmes);
//        
////######################################################################################################################################################################################################################################################################################################################      
//    
//                setColumnProperties();
//
//            } else if (result.get() == ButtonType.CANCEL) {
//                detailAvoireOulmestable.refresh();
//            }

    }

    
    @FXML
    private void tableDetailClicked(MouseEvent event) {
        
          DetailAvoirOulmes detailAvoirOulmes = detailAvoireOulmestable.getSelectionModel().getSelectedItem();
if (detailAvoirOulmes!=null){
            prixOld= detailAvoirOulmes.getPrix();

}
    }

    @FXML
    private void motifOnEditCommit(TableColumn.CellEditEvent<DetailAvoirOulmes, String> event) {

             Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
            
             ((DetailAvoirOulmes) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setMotif(event.getNewValue());       
                   
               String  motif = motifColumn.getCellData(event.getTablePosition().getRow());
             
                   
        listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()).setMotif(motif);   
       
        detailAvoirOulmesDAO.edit(listeDetailAvoirOulmes.get(detailAvoireOulmestable.getSelectionModel().getSelectedIndex()));
           
            
            } else if (result.get() == ButtonType.CANCEL) {
                detailAvoireOulmestable.refresh();
            }
        
    }

    @FXML
    private void selectionnerToutBoxMouseClicked(MouseEvent event) {
        
                ObservableList<DetailAvoirOulmes> list=detailAvoireOulmestable.getItems();
        
        for (Iterator<DetailAvoirOulmes> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        detailAvoireOulmestable.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutBoxMouseClicked(MouseEvent event) {
        
                         ObservableList<DetailAvoirOulmes> list=detailAvoireOulmestable.getItems();
        
        for (Iterator<DetailAvoirOulmes> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }

        detailAvoireOulmestable.refresh(); 
        
    }

    @FXML
    private void client2RechOnAction(ActionEvent event) {
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
               try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(AvoirOulmesController.class.getResource(nav.getiReportAvoirPF()));

               BigDecimal montantHT = BigDecimal.ZERO;
               BigDecimal montantTVA = BigDecimal.ZERO;
               BigDecimal montantTTC = BigDecimal.ZERO;
    
        for (int i = 0; i < listeDetailAvoirOulmes.size(); i++) {
            
            DetailAvoirOulmes detailAvoirOulmes = listeDetailAvoirOulmes.get(i);
         
            montantHT = montantHT.add(detailAvoirOulmes.getMontantNet());
        }
    
            montantTVA = montantHT.multiply(new BigDecimal(0.2));
            montantTTC = montantHT.add(montantTVA);
        
        
           DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
        

            para.put("montantHT",df.format(montantHT));
            para.put("montantTVA",df.format(montantTVA));
            para.put("montantTTC",df.format(montantTTC));
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailAvoirOulmes));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(AvoirOulmesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

  


}