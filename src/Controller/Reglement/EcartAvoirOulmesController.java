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
import dao.Entity.MatierePremier;
import dao.Entity.PrixOulmes;
import dao.Entity.RegularisationAvoirOulmes;
import dao.Entity.Sequenceur;
import dao.Manager.AvoirOulmesDAO;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.ClientDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.DetailAvoirOulmesDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.AvoirOulmesDAOImpl;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.DetailAvoirOulmesDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
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
import java.util.List;
import java.util.Locale;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import dao.Manager.RegularisationAvoirOulmesDAO;
import dao.ManagerImpl.ClientDAOImpl;
import dao.ManagerImpl.RegularisationAvoirOulmesDAOImpl;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
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
public class EcartAvoirOulmesController implements Initializable {

    @FXML
    private TableView<AvoirOulmes> tableSituationAvoir;
    @FXML
    private TableColumn<AvoirOulmes, String> bonAvoirColumn;
    @FXML
    private TableColumn<AvoirOulmes, String> clientColumn;
    @FXML
    private TableColumn<AvoirOulmes, String> client2Column;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> avoirColumn;
    @FXML
    private TableColumn<AvoirOulmes, Date> dateBonAvoirColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> factureAvoirColumn;
    @FXML
    private TableColumn<AvoirOulmes, String> nFactColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> factSystemeColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> factOulmesColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> ecartColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> regularColumn;
    @FXML
    private TableColumn<AvoirOulmes, Date> dateCreationColumn;
    @FXML
    private TableColumn<AvoirOulmes, Boolean> actionColumn;
    
    @FXML
    private TableView<DetailAvoirOulmes> tableDetailSituationAvoir;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> CodeColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> LibelleColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> QteColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> PrixColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> montantColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> remiseColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> montantNetColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> qteFactAvoirColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> prixFactAvoirColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, BigDecimal> remiseFactAvoirColumn;
    @FXML
    private TableColumn<DetailAvoirOulmes, String> motifColumn;
    
    @FXML
    private TableView<RegularisationAvoirOulmes> tableHistoriqueQteAvoir;
    @FXML
    private TableColumn<RegularisationAvoirOulmes, String> bonAvoirHistColumn;
    @FXML
    private TableColumn<RegularisationAvoirOulmes, Date> dateSaisieHistColumn;
    @FXML
    private TableColumn<RegularisationAvoirOulmes, BigDecimal> MontHistColumn;
    @FXML
    private TableColumn<RegularisationAvoirOulmes, String> numFactureColumn;
    
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ComboBox<String> client2Combo;
    @FXML
    private TextField nLivraisonRechField;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnValider;
    @FXML
    private TextField numFactureField;
    @FXML
    private DatePicker dateSaisie;
    @FXML
    private Label totalEcart;
    @FXML
    private Label totalFactureAv;
    @FXML
    private Label totalAvoir;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnValiderAdmin;
    
    /**
     * Initializes the controller class.
     */
    
    RegularisationAvoirOulmesDAO regularisationAvoirOulmesDAO = new RegularisationAvoirOulmesDAOImpl();
    AvoirOulmesDAO avoirOulmesDAO = new AvoirOulmesDAOImpl();
    ProduitDAO produitDAO = new ProduitDAOImpl();       
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    DetailAvoirOulmesDAO detailAvoirOulmesDAO = new DetailAvoirOulmesDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();  
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    MatierePremiereDAO matierePremierDAO = new MatierePremierDAOImpl();  
    BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
    DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();

//    ObservableList<String> client =FXCollections.observableArrayList(Constantes.CLIENT_MARJANE,Constantes.CLIENT_MINURSO);
    
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    private Map<String,Client> mapClient=new HashMap<>();
    ClientDAO clientDAO =new ClientDAOImpl();
    navigation nav = new navigation(); 
    
    private final ObservableList<AvoirOulmes> listeAvoirOulmes=FXCollections.observableArrayList();
        
    private final ObservableList<DetailAvoirOulmes> listeDetailAvoirOulmes=FXCollections.observableArrayList();
   
    private final ObservableList<RegularisationAvoirOulmes> listeRegularisationAvoirOulmes=FXCollections.observableArrayList();
         
    AvoirOulmes avoirOulmes;
    BigDecimal regular = BigDecimal.ZERO;
   
   

               Sequenceur sequenceurF = sequenceurDAO.findByCode(Constantes.FACTURE_AVOIR_CODE);
          String FactCount = Constantes.FACTURE_AVOIR_CODE+(sequenceurF.getValeur()+1);
          
          Sequenceur sequenceurA = sequenceurDAO.findByCode(Constantes.BON_AVOIR_CODE);
          String AvoirCount = Constantes.BON_AVOIR_CODE+(sequenceurA.getValeur()+1);
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//        client2Combo.setItems(client);
        
    List<Client> listCliient=clientDAO.findAll();
        
        listCliient.stream().map((client) -> {
            client2Combo.getItems().addAll(client.getLibelle());
            return client;
        }).forEachOrdered((client) -> {
            mapClient.put(client.getLibelle(), client);
        });


         List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((client) -> {
            clientCombo.getItems().addAll(client.getNom());
            return client;
        }).forEachOrdered((client) -> {
            mapClientMP.put(client.getNom(), client);
        });
        
        
        
        setColumnProperties();
        loadDetail();
        totalAvoirFactureEcart();
        
        tableSituationAvoir.setEditable(true); 
        
        tableDetailSituationAvoir.setEditable(true);

    }    

    
    void totalAvoirFactureEcart(){
    
    BigDecimal avoir = BigDecimal.ZERO;
    BigDecimal factureAvoir = BigDecimal.ZERO;
    BigDecimal ecart = BigDecimal.ZERO;
    
        for (int i = 0; i < listeAvoirOulmes.size(); i++) {
            
            AvoirOulmes avoirOulmes = listeAvoirOulmes.get(i);
         
            avoir = avoir.add(avoirOulmes.getAvoir());
            factureAvoir = factureAvoir.add(avoirOulmes.getFactureAvoir());
            
            if(avoirOulmes.getTypeEcart().equals("Avoir")){
            ecart = ecart.add(avoirOulmes.getEcart());
            }
        }
    
           DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
        
                totalAvoir.setText(df.format(avoir));
                totalFactureAv.setText(df.format(factureAvoir));
                totalEcart.setText(df.format(ecart));
        
    }
    
    
       void setColumnProperties(){


               bonAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumLivraison());
                }
             });

               
            clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClient());
                }
             });
                    
            client2Column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClient2());
                }
             });

                dateBonAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<AvoirOulmes, Date> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDateSaisie());
                }
             });
            
               
                   dateCreationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<AvoirOulmes, Date> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDateCreation());
                }
             });
                   
             avoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getAvoir()));
                }
                
             });
           
             nFactColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumFacture());
                }
             });
             
             factureAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getFactureAvoir()));
                }
                
             });
           
             factSystemeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getFactureSysteme()));
                }
                
             });
             
             factOulmesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getFactureOulmes()));
                }
                
             });
             
             ecartColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getEcart()));
                }
                
             });
             
             regularColumn.setCellValueFactory(new PropertyValueFactory<AvoirOulmes, BigDecimal>("regularisation"));

        setColumnTextFieldConverter(getConverter(), regularColumn);

        
           actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          AvoirOulmes cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionColumn.setEditable(true);
      
          
          tableSituationAvoir.setEditable(true);
        
}
    
        void setColumnPropertiesHist(){


               bonAvoirHistColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegularisationAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<RegularisationAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getBonAvoir());
                }
             });
               
               dateSaisieHistColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegularisationAvoirOulmes, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<RegularisationAvoirOulmes, Date> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDateSaisie());
                }
             });
               
                MontHistColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegularisationAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<RegularisationAvoirOulmes, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getQte());
                }
             });
                
                numFactureColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegularisationAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<RegularisationAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumFacture());
                }
             });
                
              
}
       
        void setDetailColumnProperties(){


                 CodeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });
               
                    LibelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
             });
        
            
             QteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
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
           

             PrixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailAvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrix()));
                }
                
             });
           
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


                          qteFactAvoirColumn.setCellValueFactory(new PropertyValueFactory<DetailAvoirOulmes, BigDecimal>("qteFactureAvoir"));

        setColumnTextFieldConverter(getConverter(), qteFactAvoirColumn);
        
        prixFactAvoirColumn.setCellValueFactory(new PropertyValueFactory<DetailAvoirOulmes, BigDecimal>("prixFactureAvoir"));

        setColumnTextFieldConverter(getConverter(), prixFactAvoirColumn);
        
        remiseFactAvoirColumn.setCellValueFactory(new PropertyValueFactory<DetailAvoirOulmes, BigDecimal>("remiseFactureAvoir"));

        setColumnTextFieldConverter(getConverter(), remiseFactAvoirColumn);
                     
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
     
                     
}
    
       void loadDetail(){

        listeAvoirOulmes.clear();   
        listeAvoirOulmes.addAll(avoirOulmesDAO.findAvoirOulmesByEtat(Constantes.ETAT_EN_ATTENTE_AVOIR));
        tableSituationAvoir.setItems(listeAvoirOulmes);
                  
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
        
        
        if (tableSituationAvoir.getSelectionModel().getSelectedIndex()!=-1){
            
            listeDetailAvoirOulmes.clear();
          
                    avoirOulmes=tableSituationAvoir.getSelectionModel().getSelectedItem();   
            
            
       listeDetailAvoirOulmes.addAll(detailAvoirOulmesDAO.findByAvoirOulmes(avoirOulmes.getId()));

       tableDetailSituationAvoir.setItems(listeDetailAvoirOulmes);
       
       setDetailColumnProperties();

    loadHistoAvoir();
    } 
    }
    
    
    
   void loadHistoAvoir(){
      listeRegularisationAvoirOulmes.clear();
       listeRegularisationAvoirOulmes.addAll(regularisationAvoirOulmesDAO.findRegularisationAvoirOulmesBybonAvoir(avoirOulmes.getNumLivraison()));
       
       tableHistoriqueQteAvoir.setItems(listeRegularisationAvoirOulmes);

       setColumnPropertiesHist();
    }
   
   
  
    @FXML
    private void rechercheBonAvoirKeyPressed(KeyEvent event) {

    }

    @FXML
    private void rechercheMouseClicked(MouseEvent event) {
        
         String bonAvoir = nLivraisonRechField.getText();
       String client2 = client2Combo.getSelectionModel().getSelectedItem();
       ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
            
           if (nLivraisonRechField.getText().equals("") && clientCombo.getSelectionModel().isEmpty() && client2Combo.getSelectionModel().isEmpty()){
 
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
            } else{

                 String req = "";
         
        if(clientCombo.getSelectionModel().getSelectedIndex()!= -1) {
             
              req= req+" and u.client='"+clientMP.getNom()+"'";
              
             }
             
        if(!nLivraisonRechField.getText().equals("")){
             
             req= req+" and u.numLivraison='"+bonAvoir+"'";

            }
             
        if(client2Combo.getSelectionModel().getSelectedIndex()!= -1){
             
               req= req+" and u.client2='"+client2+"'";
      
             }
               
            listeAvoirOulmes.clear();
            listeDetailAvoirOulmes.clear();
            listeRegularisationAvoirOulmes.clear();
               
            listeAvoirOulmes.addAll(avoirOulmesDAO.findByAllFilter(Constantes.ETAT_EN_ATTENTE_AVOIR,req));
          
          tableSituationAvoir.setItems(listeAvoirOulmes);
 
          totalAvoirFactureEcart();
          
    }
    
}

    @FXML
    private void refresh(ActionEvent event) {
        
        
        clear();
        
    }

    @FXML
    private void valider(ActionEvent event) throws ParseException {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
            if(tableSituationAvoir.getSelectionModel().getSelectedIndex()==-1|| 
                        numFactureField.getText().equals("")|| 
                        dateSaisie.getValue()== null
                   ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
    }
else {
      
                  avoirOulmes=tableSituationAvoir.getSelectionModel().getSelectedItem();   
                 List<DetailAvoirOulmes> listDetailAvoirOulmese = detailAvoirOulmesDAO.findByAvoirOulmes(avoirOulmes.getId());
                
      LocalDate localDate=dateSaisie.getValue();
                    Date dtSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
                 
                 
                   for( int rows = 0;rows<listDetailAvoirOulmese.size() ;rows++ ){
                         
               DetailAvoirOulmes detailAvoirOulmes= listDetailAvoirOulmese.get(rows);
               
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
            detailAvoirOulmesDAO.edit(detailAvoirOulmes);              
                                    
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
                            String  client ="";         
                           if(avoirOulmes.getClient2().equals(Constantes.CLIENT_MINURSO)){
                           client = avoirOulmes.getClient2();
                           }else if (avoirOulmes.getClient2().equals(Constantes.CLIENT_MARJANE)){
                           client =  Constantes.SANS;
                           }else{
                           client =  Constantes.SANS;
                           }
  
                         System.out.println("avoirOulmes.getNumLivraison() "+avoirOulmes.getNumLivraison());  
                         System.out.println("client "+client); 
                         System.out.println("avoirOulmes.getLieuLivraison() "+avoirOulmes.getLieuLivraison()); 
                           
                PrixOulmes prixOulmes = prixOulmesDAO.findPrixOulmesByCodeArt(detailAvoirOulmes.getPrixOulmes().getProduit().getCode(),client,avoirOulmes.getLieuLivraison());

                MatierePremier matierePremier = matierePremierDAO.findByCode(Constantes.CODE_MP);

                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(AvoirCount);
                detailBonLivraison.setMontant(detailAvoirOulmes.getMontantNet());
                detailBonLivraison.setRemiseAchat(detailAvoirOulmes.getRemiseAvoir());
                detailBonLivraison.setBonAvoir(avoirOulmes.getNumLivraison());
                detailBonLivraison.setNumCommande("OLF/19");
                detailBonLivraison.setMotif(Constantes.SANS);
                detailBonLivraison.setPrix(detailAvoirOulmes.getPrix());
                detailBonLivraison.setPrixOulmes(prixOulmes);
                detailBonLivraison.setClient2(avoirOulmes.getClient2());
                detailBonLivraison.setNumFacture(numFactureField.getText());
                detailBonLivraison.setQuantite(detailAvoirOulmes.getQte());
                detailBonLivraison.setMatierePremier(matierePremier);
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);
                
                
            }

//_________________________________________________________________________ Bon Livraision __________________________________________________________________________________________________________________________

            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
           
            montantHT= regular.divide(new BigDecimal(1.2), 2, RoundingMode.FLOOR); 
            montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
            montantTTC= regular;
                  

          BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(avoirOulmes.getFournisseur());
          bonLivraison.setMontant(montantHT);
          bonLivraison.setLivraisonFour(AvoirCount);
          bonLivraison.setEtat(Constantes.ETAT_A_PAYER);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setClient(avoirOulmes.getClient());
          bonLivraison.setNumFacture(FactCount);
          bonLivraison.setNumCommande("OLF/19");
          bonLivraison.setMontantRG(montantTTC);
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_FACTURE);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(dtSaisie);
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
            
//_____________________________________________________________________________ AvoirOulmes ______________________________________________________________________________________________________________________________________________________________________________________


                for (int i = 0; i < listeAvoirOulmes.size(); i++) {
                    
                    AvoirOulmes avoirOulmes = listeAvoirOulmes.get(i);
                    
                    avoirOulmes.setRegularisation(BigDecimal.ZERO.setScale(2));
                    
                    if (avoirOulmes.getEcart().compareTo(BigDecimal.ZERO)==0){    
                        avoirOulmes.setEtat(Constantes.ETAT_REGLEMENT_AVOIR);
                    }

                    avoirOulmesDAO.edit(avoirOulmes);
                    
                }
                 
 //_____________________________________________________________________________ RegularisationAvoirOulmes ______________________________________________________________________________________________________________________________________________________________________________________
 
        RegularisationAvoirOulmes regularisationAvoirOulmes =new RegularisationAvoirOulmes();
        
        regularisationAvoirOulmes.setBonAvoir(avoirOulmes.getNumLivraison());
        regularisationAvoirOulmes.setQte(regular);
        regularisationAvoirOulmes.setNumFacture(numFactureField.getText());
        regularisationAvoirOulmes.setDateSaisie(dtSaisie);
        
        regularisationAvoirOulmesDAO.add(regularisationAvoirOulmes); 

                     nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);    
            
            
                     clear();
  
            setColumnPropertiesHist();
            loadHistoAvoir();
            
            }}
    }

    void clear(){
    
            numFactureField.clear();
            dateSaisie.setValue(null);
            nLivraisonRechField.clear();
            clientCombo.getSelectionModel().select(-1);
            client2Combo.getSelectionModel().select(-1);
            
            setColumnProperties();
            loadDetail();    
            totalAvoirFactureEcart();
            
            listeDetailAvoirOulmes.clear();
            listeRegularisationAvoirOulmes.clear();
    
    }
    
    @FXML
    private void editCommitRegularisationColumn(TableColumn.CellEditEvent<AvoirOulmes, BigDecimal> event) {
        
        
        
         if (numFactureField.getText().isEmpty() || dateSaisie.getValue() == null){
 
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Constantes.REMPLIR_CHAMPS);
            alert.setTitle("ERROR");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
   
             listeAvoirOulmes.get(tableSituationAvoir.getSelectionModel().getSelectedIndex()).setRegularisation(BigDecimal.ZERO.setScale(2));
             tableSituationAvoir.refresh();
             return;
            }


                     }else{
        
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    BigDecimal ecart = BigDecimal.ZERO;
                    BigDecimal factureAvoir = BigDecimal.ZERO;
                    BigDecimal calculEcart = BigDecimal.ZERO;
                    BigDecimal calculFacture = BigDecimal.ZERO;
                    
                ((AvoirOulmes) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setRegularisation(event.getNewValue());
               
                    tableSituationAvoir.refresh();  

                 regular = regularColumn.getCellData(event.getTablePosition().getRow());
                 ecart = listeAvoirOulmes.get(tableSituationAvoir.getSelectionModel().getSelectedIndex()).getEcart();
                 factureAvoir = listeAvoirOulmes.get(tableSituationAvoir.getSelectionModel().getSelectedIndex()).getFactureAvoir();
                 
                 listeAvoirOulmes.get(tableSituationAvoir.getSelectionModel().getSelectedIndex()).setRegularisation(regular.setScale(2, RoundingMode.FLOOR));
                 
                 calculEcart = ecart.subtract(regular);
                 
                listeAvoirOulmes.get(tableSituationAvoir.getSelectionModel().getSelectedIndex()).setEcart(calculEcart.setScale(2, RoundingMode.FLOOR));
                
                calculFacture = factureAvoir.add(regular);
                
                listeAvoirOulmes.get(tableSituationAvoir.getSelectionModel().getSelectedIndex()).setFactureAvoir(calculFacture.setScale(2, RoundingMode.FLOOR));
                 
                setColumnProperties();

                totalAvoirFactureEcart();

            } else if (result.get() == ButtonType.CANCEL) {
                tableSituationAvoir.refresh();
            }
         }

    }

    @FXML
    private void motifOnEditCommit(TableColumn.CellEditEvent<DetailAvoirOulmes, String> event) {
        
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
            
             ((DetailAvoirOulmes) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setMotif(event.getNewValue());       
                   
               String  motif = motifColumn.getCellData(event.getTablePosition().getRow());
             
                   
        listeDetailAvoirOulmes.get(tableDetailSituationAvoir.getSelectionModel().getSelectedIndex()).setMotif(motif);   
       
        detailAvoirOulmesDAO.edit(listeDetailAvoirOulmes.get(tableDetailSituationAvoir.getSelectionModel().getSelectedIndex()));
           
            
            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailSituationAvoir.refresh();
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
               
                    tableDetailSituationAvoir.refresh();  

                 factAvoirQte = qteFactAvoirColumn.getCellData(event.getTablePosition().getRow());
                
                 listeDetailAvoirOulmes.get(tableDetailSituationAvoir.getSelectionModel().getSelectedIndex()).setQteFactureAvoir(factAvoirQte.setScale(2, RoundingMode.FLOOR));
                
                 detailAvoirOulmesDAO.edit(listeDetailAvoirOulmes.get(tableDetailSituationAvoir.getSelectionModel().getSelectedIndex()));
                 
                setColumnProperties();

                totalAvoirFactureEcart();

            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailSituationAvoir.refresh();
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
               
                    tableDetailSituationAvoir.refresh();  

                 factAvoirPrix = prixFactAvoirColumn.getCellData(event.getTablePosition().getRow());
                
                 listeDetailAvoirOulmes.get(tableDetailSituationAvoir.getSelectionModel().getSelectedIndex()).setPrixFactureAvoir(factAvoirPrix.setScale(2, RoundingMode.FLOOR));
                
                 detailAvoirOulmesDAO.edit(listeDetailAvoirOulmes.get(tableDetailSituationAvoir.getSelectionModel().getSelectedIndex()));
                 
                setColumnProperties();
                totalAvoirFactureEcart();
           

            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailSituationAvoir.refresh();
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
               
                    tableDetailSituationAvoir.refresh();  

                 factAvoirRemise = remiseFactAvoirColumn.getCellData(event.getTablePosition().getRow());
                
                 listeDetailAvoirOulmes.get(tableDetailSituationAvoir.getSelectionModel().getSelectedIndex()).setRemiseFactureAvoir(factAvoirRemise.setScale(2, RoundingMode.FLOOR));
                
                 detailAvoirOulmesDAO.edit(listeDetailAvoirOulmes.get(tableDetailSituationAvoir.getSelectionModel().getSelectedIndex()));
                 
                setColumnProperties();

                totalAvoirFactureEcart();

            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailSituationAvoir.refresh();
            }
        
        
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }

    @FXML
    private void client2OnAction(ActionEvent event) {
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) throws IOException {
        
        
        
           if (tableSituationAvoir.getSelectionModel().getSelectedIndex()!=-1){
             
                avoirOulmes=tableSituationAvoir.getSelectionModel().getSelectedItem();   
             
          try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(EcartAvoirOulmesController.class.getResource(nav.getiReportEcartAvoirPF()));

            
            try (InputStream source1 = this.getClass().getClassLoader().getResourceAsStream("iReport/ConsultationDetailAvoirPF.jasper")) {
	    			
	    			File theDir = new File("C:/report");
					if (!theDir.exists()){
					    theDir.mkdirs();
					}
					
	
	    		    java.nio.file.Files.copy (source1, Paths.get("C:/report/ConsultationDetailAvoirPF.jasper"),StandardCopyOption.REPLACE_EXISTING);
	    		} catch (IOException ex) {
	    		    // An error occurred copying the resource
	    		}
	    		
	    		
	    		
try (InputStream source2 = this.getClass().getClassLoader().getResourceAsStream("iReport/ConsultationRegularisationAvoirPF.jasper")) {
	    			
	    			File theDir = new File("C:/report");
					if (!theDir.exists()){
					    theDir.mkdirs();
					}
					
	    		    java.nio.file.Files.copy (source2, Paths.get("C:/report/ConsultationRegularisationAvoirPF.jasper"),StandardCopyOption.REPLACE_EXISTING);
	    		} catch (IOException ex) {
	    		    // An error occurred copying the resource
	    		}
Path dest1 = Paths.get("C://report");
				
            para.put("SUBREPORT_DIR",dest1+"//");
            para.put("bonAvoir",avoirOulmes.getNumLivraison());
            para.put("client",avoirOulmes.getClient());
            para.put("client2",avoirOulmes.getClient2());
            para.put("dateAvoir",avoirOulmes.getDateSaisie());
            para.put("avoir",avoirOulmes.getAvoir());
            para.put("factureAvoir",avoirOulmes.getFactureAvoir());
            para.put("ecart",avoirOulmes.getEcart());
            
            para.put("listeDetailAvoirPF",listeDetailAvoirOulmes);
            para.put("listeRegularisationAvoirPF",listeRegularisationAvoirOulmes);
            
            para.put("totalAvoir",totalAvoir.getText());
            para.put("totalAvoirFact",totalFactureAv.getText());
            para.put("totalEcart",totalEcart.getText());
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeRegularisationAvoirOulmes));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(EcartAvoirOulmesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }else{
         
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.SELECTIONNER_UNE_LIGNE);
         }
        
    }

    @FXML
    private void validerAdminOnAction(ActionEvent event) {
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
            
                   for( int rows = 0;rows<tableSituationAvoir.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
             
              AvoirOulmes avoirOulmes = tableSituationAvoir.getItems().get(rows);
              
              avoirOulmes.setEtat(Constantes.ETAT_VALIDER_ADMIN_AVOIR);
              
              avoirOulmesDAO.edit(avoirOulmes);
         }
                
                }
            
                   clear();
                   
            }
        
    }


}