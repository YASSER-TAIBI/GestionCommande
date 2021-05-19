/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.commande;

import Controller.Livraision.SuiviCommandeController;
import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.CommandeRegion;
import dao.Entity.Depot;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCommandeRegion;
import dao.Entity.DetailReceptionRegion;
import dao.Entity.Dimension;
import dao.Entity.Fournisseur;
import dao.Entity.Grammage;
import dao.Entity.GrammageFilm;
import dao.Entity.Intervalle;
import dao.Entity.Magasin;
import dao.Entity.MatierePremier;
import dao.Entity.PrixAdhesif;
import dao.Entity.PrixBox;
import dao.Entity.PrixBoxMetal;
import dao.Entity.PrixCarton;
import dao.Entity.PrixFilm;
import dao.Entity.Sequenceur;
import dao.Entity.TypeCarton;
import dao.Entity.TypeCartonBox;
import dao.Entity.TypeFilm;
import dao.Manager.ClientMPDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.CommandeRegionDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.DetailCommandeRegionDAO;
import dao.Manager.DetailReceptionDAO;
import dao.Manager.DetailReceptionRegionDAO;
import dao.Manager.DimensionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.GrammageDAO;
import dao.Manager.GrammageFilmDAO;
import dao.Manager.IntervalleDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixAdhesifDAO;
import dao.Manager.PrixBoxDAO;
import dao.Manager.PrixBoxMetalDAO;
import dao.Manager.PrixCartonDAO;
import dao.Manager.PrixDimFourDAO;
import dao.Manager.PrixFilmDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TypeCartonBoxDAO;
import dao.Manager.TypeCartonDAO;
import dao.Manager.TypeFilmDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.CommandeRegionDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.DetailCommandeRegionDAOImpl;
import dao.ManagerImpl.DetailReceptionDAOImpl;
import dao.ManagerImpl.DetailReceptionRegionDAOImpl;
import dao.ManagerImpl.DimensionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.GrammageDAOImpl;
import dao.ManagerImpl.GrammageFilmDAOImpl;
import dao.ManagerImpl.IntervalleDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixAdhesifDAOImpl;
import dao.ManagerImpl.PrixBoxDAOImpl;
import dao.ManagerImpl.PrixBoxMetalDAOImpl;
import dao.ManagerImpl.PrixCartonDAOImpl;
import dao.ManagerImpl.PrixDimFourDAOImpl;
import dao.ManagerImpl.PrixFilmDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.TypeCartonBoxDAOImpl;
import dao.ManagerImpl.TypeCartonDAOImpl;
import dao.ManagerImpl.TypeFilmDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
public class ValiderCommandeRegionController implements Initializable {

   
    
    @FXML    
    private TableView<CommandeRegion> tableCommande;
    @FXML    
    private TableColumn<CommandeRegion, String> nCommandeColumn;
    @FXML    
    private TableColumn<CommandeRegion, Date> dateCreationColumn;
    @FXML
    private TableColumn<CommandeRegion, String> depotColumn;
    @FXML    
    private TableColumn<CommandeRegion, String> etatColumn;

    @FXML    
    private TableView<DetailCommandeRegion> tableDetailCommande;
    @FXML    
    private TableColumn<DetailCommandeRegion, String> codeMPColumn;
    @FXML    
    private TableColumn<DetailCommandeRegion, String> libelleColumn;
    @FXML    
    private TableColumn<DetailCommandeRegion, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, BigDecimal> qteRecColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, BigDecimal> qteResColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, BigDecimal> qteLivColumn;

    @FXML
    private ComboBox<String> fornisseurCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ComboBox<String> GrammageCombo;
    @FXML
    private ComboBox<String> typeCartonCombo;
    @FXML
    private ComboBox<String> TypeFilmCombo;
    @FXML
    private ComboBox<String> grammageFilmCombo;
    @FXML
    private ComboBox<String> typeCarCombo;
    @FXML
    private ComboBox<String> intervalleCombo;
    @FXML
    private TextField codeMPField;
    @FXML
    private DatePicker dateSaisie;
    @FXML
    private ComboBox<String> dimCombo;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private Button btnModifierDC;
    @FXML
    private Button btnModifier;
    @FXML
    private TextField quantiteField;
    @FXML
    private Button btnImprimer;
    
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    private Map<String,Dimension> mapDimension=new HashMap<>();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    private Map<String,Grammage> mapGrammage=new HashMap<>();
    private Map<String,TypeCartonBox> mapTypeCarton=new HashMap<>();
    private Map<String,TypeFilm> mapTypeFilm=new HashMap<>();
    private Map<String,GrammageFilm> mapGrammageFilm=new HashMap<>();
    private Map<String,TypeCarton> mapTypeCar=new HashMap<>();
    private Map<String,Intervalle> mapIntervalle=new HashMap<>();

    
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    DetailCommandeRegionDAO detailCommandeRegionDAO = new DetailCommandeRegionDAOImpl(); 
    MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
    DimensionDAO dimensionDAO = new DimensionDAOImpl();
    CommandeRegionDAO commandeRegionDAO = new CommandeRegionDAOImpl(); 
    CommandeDAO commandeDAO = new CommandeDAOImpl(); 
    PrixDimFourDAO prixDimFourDAO = new PrixDimFourDAOImpl();
    GrammageDAO grammageDAO = new GrammageDAOImpl();
    TypeCartonBoxDAO typeCartonBoxDAO = new TypeCartonBoxDAOImpl();
    PrixBoxDAO prixBoxDAO = new PrixBoxDAOImpl();
    PrixAdhesifDAO prixAdhesifDAO = new PrixAdhesifDAOImpl();
    PrixCartonDAO prixCartonDAO =new PrixCartonDAOImpl();
    PrixFilmDAO prixFilmDAO =new  PrixFilmDAOImpl();
    TypeFilmDAO typeFilmDAO = new  TypeFilmDAOImpl();
    GrammageFilmDAO grammageFilmDAO = new GrammageFilmDAOImpl();
    TypeCartonDAO typeCartonDAO = new TypeCartonDAOImpl();
    IntervalleDAO intervalleDAO = new IntervalleDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
    PrixBoxMetalDAO prixBoxMetalDAO = new PrixBoxMetalDAOImpl();
     DetailReceptionRegionDAO detailReceptionRegionDAO = new DetailReceptionRegionDAOImpl();
    
    private final ObservableList<DetailCommande> listeDetailCommande=FXCollections.observableArrayList();
    private final ObservableList<DetailCommandeRegion> listeDetailCommandeRegion=FXCollections.observableArrayList();
    private final ObservableList<PrixBox> listeprixBox=FXCollections.observableArrayList();
    private final ObservableList<PrixCarton> listeprixCarton=FXCollections.observableArrayList();
    private final ObservableList<PrixFilm> listeprixFilmNormal=FXCollections.observableArrayList();
    private final ObservableList<PrixFilm> listeprixFilmGolde=FXCollections.observableArrayList();
    private final ObservableList<PrixAdhesif> listeprixAdhesif=FXCollections.observableArrayList();
    private final ObservableList<CommandeRegion> listeCommandeRegion=FXCollections.observableArrayList();
  
    MatierePremier matierePremiere=new MatierePremier();
    CommandeRegion commandeRegion = new CommandeRegion();
    Commande commande = new Commande();
    
    ClientMP clientMP;    

    Date date = new  Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yy");
    
    navigation nav = new navigation();

    BigDecimal montanTotal=BigDecimal.ZERO;

//    DetailCommandeRegion detailCommandeRegion;
   
//    public String numCommandeRecupere = null;
    
  
    String codeReceptionRegion = "";
         
         
         
           void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RECEPTION_REGION);
          codeReceptionRegion = Constantes.RECEPTION_REGION+" "+(sequenceur.getValeur()+1);
   }
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        if (!nav.getUtilisateur().getDepot().getLibelle().equals(Constantes.CODE_DEPOT_SIEGE)){
        
               GrammageCombo.setDisable(true);
               intervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               typeCarCombo.setDisable(true); 
               typeCartonCombo.setDisable(true); 
               fornisseurCombo.setDisable(true); 
               clientCombo.setDisable(true); 
               ajouterSaisie.setDisable(true);
               btnModifier.setDisable(true);
               btnModifierDC.setDisable(true);

             
        }
        
        loadDetail();
        setColumnProperties();
        
        ajouterSaisie.setDisable(true);
        
         List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((clientMP) -> {
            clientCombo.getItems().addAll(clientMP.getNom());
            return clientMP;
        }).forEachOrdered((clientMP) -> {
            mapClientMP.put(clientMP.getNom(), clientMP);
        });
       
        
        List<Fournisseur> listFournisseur=fournisseurDAO.findAllMp();
        
        listFournisseur.stream().map((fournisseur) -> {
            fornisseurCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
        
         List<Grammage> listGrammage=grammageDAO.findAll();
        
        listGrammage.stream().map((grammage) -> {
            GrammageCombo.getItems().addAll(grammage.getLibelle());
            return grammage;
        }).forEachOrdered((grammage) -> {
            mapGrammage.put(grammage.getLibelle(), grammage);
        });
        
        
         List<GrammageFilm> listGrammageFilm=grammageFilmDAO.findAll();
        
        listGrammageFilm.stream().map((grammageFilm) -> {
            grammageFilmCombo.getItems().addAll(grammageFilm.getLibelle());
            return grammageFilm;
        }).forEachOrdered((grammageFilm) -> {
            mapGrammageFilm.put(grammageFilm.getLibelle(), grammageFilm);
        });
        
        
        
         List<TypeCarton> listTypeCarton=typeCartonDAO.findAll();
        
        listTypeCarton.stream().map((typeCarton) -> {
            typeCarCombo.getItems().addAll(typeCarton.getLibelle());
            return typeCarton;
        }).forEachOrdered((typeCarton) -> {
            mapTypeCar.put(typeCarton.getLibelle(), typeCarton);
        });
        
        
        List<TypeCartonBox> listTypeCartonBox=typeCartonBoxDAO.findAll();
        
        listTypeCartonBox.stream().map((typeCartonBox) -> {
            typeCartonCombo.getItems().addAll(typeCartonBox.getLibelle());
            return typeCartonBox;
        }).forEachOrdered((typeCartonBox) -> {
            mapTypeCarton.put(typeCartonBox.getLibelle(), typeCartonBox);
        });

         List<TypeFilm> listTypeFilm=typeFilmDAO.findAll();
        
        listTypeFilm.stream().map((typeFilm) -> {
            TypeFilmCombo.getItems().addAll(typeFilm.getLibelle());
            return typeFilm;
        }).forEachOrdered((typeFilm) -> {
            mapTypeFilm.put(typeFilm.getLibelle(), typeFilm);
        });
        
               GrammageCombo.setDisable(true);
               intervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               typeCarCombo.setDisable(true); 
               typeCartonCombo.setDisable(true); 
               

               
                  montanTotal=BigDecimal.ZERO;
        
    }    

     void setColumnProperties(){
        

          nCommandeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeRegion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeRegion, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getnCommande());
                }
                
             });
          dateCreationColumn.setCellValueFactory(new PropertyValueFactory<CommandeRegion, Date>("dateCreation"));
             
          depotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeRegion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeRegion, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDepot().getLibelle());
                }
                
             });
             
             etatColumn.setCellValueFactory(new PropertyValueFactory<CommandeRegion, String>("etat"));
        

        tableCommande.setEditable(false);
 
    }
    
     void loadDetail(){
        
        listeCommandeRegion.clear();
        listeCommandeRegion.addAll(commandeRegionDAO.findCommandeByEtat(Constantes.ETAT_COMMANDE_LANCE_REGION));
        tableCommande.setItems(listeCommandeRegion);
    }
    
       void setColumnPropertiesDetailCommande(){
        
          codeMPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeRegion, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
          
          libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeRegion, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });
                      
          quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });
                
                qteRecColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRecu()));
                }
                
             });
                
                qteResColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRestee()));
                }
                
             });
                
             qteLivColumn.setCellValueFactory(new PropertyValueFactory<DetailCommandeRegion, BigDecimal>("quantiteLivree"));

             setColumnTextFieldConverter(getConverter(), qteLivColumn);
     
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
    
    
//        void loadDetailCommande (){
//        
//            DetailCommandeRegion detailCommandeRegion=tableDetailCommande.getSelectionModel().getSelectedItem();
//            
//        CommandeRegion commandeRegion = detailCommandeRegion.getCommandeRegion();
//
//        setColumnPropertiesDetailCommande();
//        listeDetailCommandeRegion.clear();
//        listeDetailCommandeRegion.addAll(detailCommandeRegionDAO.findDetailCommandeByEtat(commandeRegion, Constantes.ETAT_AFFICHAGE));
//        tableDetailCommande.setItems(listeDetailCommandeRegion);
//    }
     
    @FXML
    private void afficherDetailOnMouse(MouseEvent event) {
              setColumnPropertiesDetailCommande();
          
            listeDetailCommandeRegion.clear();
            if (tableCommande.getSelectionModel().getSelectedIndex()!=-1){
            commandeRegion=tableCommande.getSelectionModel().getSelectedItem();
               
             listeDetailCommandeRegion.addAll(detailCommandeRegionDAO.findDetailCommandeByEtat(commandeRegion, Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommandeRegion);
            
//           numCommandeRecupere= nCommandeColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex());

              tableDetailCommande.setEditable(true);
 
                }
              
             
           
            
            
    }

    @FXML
    private void afficherChampsOnMouse(MouseEvent event) {
        
        clear();
        
               GrammageCombo.setDisable(true);
               intervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               typeCarCombo.setDisable(true); 
               typeCartonCombo.setDisable(true); 
               ajouterSaisie.setDisable(true);
        
          if (tableDetailCommande.getSelectionModel().getSelectedIndex()!=-1){
        if(
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null || 
                   clientCombo.getSelectionModel().getSelectedItem()==null         
     
                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.VERIFIER_FOURNISSEUR_CLIENT);
     }else {
            
       DetailCommandeRegion detailCommandeRegion=tableDetailCommande.getSelectionModel().getSelectedItem();
       

       
//            if(detailCommandeRegion.getQuantiteRecu().equals(BigDecimal.ZERO.setScale(2))){
       


        if(listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).getCmdRegle().equals(Constantes.ETAT_CMNR) || detailCommandeRegion.equals(Constantes.ETAT_CMNR) ){
            
            
         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
           
            quantiteField.setText(df.format(detailCommandeRegion.getQuantite()));
            
            LocalDate date = new java.util.Date( detailCommandeRegion.getCommandeRegion().getDateCreation().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            dateSaisie.setValue(date);
            
            codeMPField.setText(detailCommandeRegion.getMatierePremier().getCode());
            
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    dimCombo.getItems().clear();


            if (detailCommandeRegion.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX)){
            
                     
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Dimansion <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        
                                 dimCombo.setValue(detailCommandeRegion.getMatierePremier().getDimension().getLibelle());

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Intervalle <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<               
     
                  Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());
           
                  List<PrixBox> listPrixBox= prixBoxDAO.findPrixBoxByCategorieMp(detailCommandeRegion.getMatierePremier().getCategorieMp().getId(), fournisseur.getId());


                listeprixBox.clear();
                
                    intervalleCombo.getItems().clear();
    
                if(listPrixBox.size()!=0){
                
                     intervalleCombo.setDisable(false);
                    
                for(int i=0;i<listPrixBox.size();i++){
                Boolean exist = false;
                 PrixBox prixBox = listPrixBox.get(i);
                 
                if(listeprixBox.size() ==0){
                    
                    listeprixBox.add(prixBox);
                
                }else {
                   for(int j=0;j<listeprixBox.size();j++){
                   
                   if(prixBox.getIntervalle().getLibelle().equals(listeprixBox.get(j).getIntervalle().getLibelle())){
                   
                       exist = true;
                   }
                
                }
                if(exist == false)
                {
                 listeprixBox.add(prixBox);
                }
                }
                }
                 listeprixBox.stream().map((prixBox) -> {
            intervalleCombo.getItems().addAll(prixBox.getIntervalle().getLibelle());
            return prixBox;
        }).forEachOrdered((prixBox) -> {
            mapIntervalle.put(prixBox.getIntervalle().getLibelle(), prixBox.getIntervalle());
        });          
                }else{
                   intervalleCombo.setDisable(true);
                }
                 
                TypeFilmCombo.setDisable(true);
                grammageFilmCombo.setDisable(true);
                typeCarCombo.setDisable(true);
                
                dimCombo.setDisable(false);
                GrammageCombo.setDisable(false);
                typeCartonCombo.setDisable(false);
                ajouterSaisie.setDisable(false);

            }else if (detailCommandeRegion.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.CARTON)){
                
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Dimansion <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
              
                    dimCombo.setValue(detailCommandeRegion.getMatierePremier().getDimension().getLibelle());

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Intervalle <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

                Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());

            List<PrixCarton> listPrixCartons= prixCartonDAO.findPrixCartonByCategorieMp(detailCommandeRegion.getMatierePremier().getCategorieMp().getId(), fournisseur.getId());

                listeprixCarton.clear();
                
                    intervalleCombo.getItems().clear();

                
                if(listPrixCartons.size()!=0){
                
                            intervalleCombo.setDisable(false);
                    
                for(int i=0;i<listPrixCartons.size();i++){
                Boolean exist = false;
                 PrixCarton prixCarton = listPrixCartons.get(i);
                 
                if(listeprixCarton.size() ==0){
                    
                    listeprixCarton.add(prixCarton);
                
                }else {
                   for(int j=0;j<listeprixCarton.size();j++){
                   
                   if(prixCarton.getIntervalle().getLibelle().equals(listeprixCarton.get(j).getIntervalle().getLibelle())){
                   
                       exist = true;
                   }
                
                }
                if(exist == false)
                {
                 listeprixCarton.add(prixCarton);
                }
                }
                }
                 listeprixCarton.stream().map((prixCarton) -> {
            intervalleCombo.getItems().addAll(prixCarton.getIntervalle().getLibelle());
            return prixCarton;
        }).forEachOrdered((prixCarton) -> {
            mapIntervalle.put(prixCarton.getIntervalle().getLibelle(), prixCarton.getIntervalle());
        });          
              
                   }else{
                   intervalleCombo.setDisable(true);
                }
                 
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);  
               GrammageCombo.setDisable(true);  
               typeCartonCombo.setDisable(true);
               
               dimCombo.setDisable(false);
               typeCarCombo.setDisable(false);
               ajouterSaisie.setDisable(false);
               
            }else if(detailCommandeRegion.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_GOLD)){
                
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Dimansion <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
              
                    dimCombo.setValue(detailCommandeRegion.getMatierePremier().getDimension().getLibelle());

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Intervalle <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                
                    listeprixFilmGolde.clear();
                    intervalleCombo.getItems().clear();
                
                         Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());
                    
                List<PrixFilm> listPrixFilm= prixFilmDAO.findPrixFilmByCategorieMp(detailCommandeRegion.getMatierePremier().getCategorieMp().getId(),fournisseur.getId());
 
                   if(listPrixFilm.size()!=0){
                
                            intervalleCombo.setDisable(false);
                
                    for(int i=0;i<listPrixFilm.size();i++){
                Boolean exist = false;
                 PrixFilm prixFilm = listPrixFilm.get(i);
                 
                if(listeprixFilmGolde.size() ==0){
                    
                    listeprixFilmGolde.add(prixFilm);
                
                }else {
                   for(int j=0;j<listeprixFilmGolde.size();j++){
                   
                   if(prixFilm.getIntervalle().getLibelle().equals(listeprixFilmGolde.get(j).getIntervalle().getLibelle())){
                   
                       exist = true;
                   }
                
                }
                if(exist == false)
                {
                 listeprixFilmGolde.add(prixFilm);
                }
                }
                }
                 listeprixFilmGolde.stream().map((prixFilm) -> {
            intervalleCombo.getItems().addAll(prixFilm.getIntervalle().getLibelle());
            return prixFilm;
        }).forEachOrdered((prixFilm) -> {
            mapIntervalle.put(prixFilm.getIntervalle().getLibelle(), prixFilm.getIntervalle());
        });    
                   }else{
                       intervalleCombo.setDisable(true);
                   }
                 
                typeCartonCombo.setDisable(true);
                GrammageCombo.setDisable(true); 
                typeCarCombo.setDisable(true);
                
                dimCombo.setDisable(false);
                TypeFilmCombo.setDisable(false);
                grammageFilmCombo.setDisable(false);
                ajouterSaisie.setDisable(false);

            }
            else if(detailCommandeRegion.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_NORMAL)){
                
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Dimansion <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
              
                    dimCombo.setValue(detailCommandeRegion.getMatierePremier().getDimension().getLibelle());

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Intervalle <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                
                      listeprixFilmNormal.clear();
                    intervalleCombo.getItems().clear();
                
                         Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());
                    
                   List<PrixFilm> listPrixFilm= prixFilmDAO.findPrixFilmByCategorieMp(detailCommandeRegion.getMatierePremier().getCategorieMp().getId(),fournisseur.getId());
                   
                       if(listPrixFilm.size()!=0){
                
                            intervalleCombo.setDisable(false);
                   
                      for(int i=0;i<listPrixFilm.size();i++){
                Boolean exist = false;
                 PrixFilm prixFilm = listPrixFilm.get(i);
                 
                if(listeprixFilmNormal.size() ==0){
                    
                    listeprixFilmNormal.add(prixFilm);
                
                }else {
                   for(int j=0;j<listeprixFilmNormal.size();j++){
                   
                   if(prixFilm.getIntervalle().getLibelle().equals(listeprixFilmNormal.get(j).getIntervalle().getLibelle())){
                   
                       exist = true;
                   }
                
                }
                if(exist == false)
                {
                 listeprixFilmGolde.add(prixFilm);
                }
                }
                }
                 listeprixFilmGolde.stream().map((prixFilm) -> {
            intervalleCombo.getItems().addAll(prixFilm.getIntervalle().getLibelle());
            return prixFilm;
        }).forEachOrdered((prixFilm) -> {
            mapIntervalle.put(prixFilm.getIntervalle().getLibelle(), prixFilm.getIntervalle());
        });    
                   }else{
                       intervalleCombo.setDisable(true);
                   } 
                
                typeCartonCombo.setDisable(true);
                GrammageCombo.setDisable(true); 
                typeCarCombo.setDisable(true);
               
                dimCombo.setDisable(false);
                TypeFilmCombo.setDisable(false);
                grammageFilmCombo.setDisable(false);
                ajouterSaisie.setDisable(false);

            }
            else if (detailCommandeRegion.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.ADHESIF)){
  
                dimCombo.setValue("Sélectionner...");
                dimCombo.getItems().clear();
                listeprixAdhesif.clear();
                
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Dimansion <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

                Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());

                 if (detailCommandeRegion.getMatierePremier().getCode().equals(Constantes.MP_901)|| detailCommandeRegion.getMatierePremier().getCode().equals(Constantes.MP_907) || detailCommandeRegion.getMatierePremier().getCode().equals(Constantes.MP_908)|| detailCommandeRegion.getMatierePremier().getCode().equals(Constantes.MP_757)|| detailCommandeRegion.getMatierePremier().getCode().equals(Constantes.MP_836)){
                  
                  
                  List<PrixAdhesif> listPrixAdhesif= prixAdhesifDAO.findPrixAdhesifByCategorieMp(detailCommandeRegion.getMatierePremier().getCategorieMp().getId(), fournisseur.getId());
                  
                  if(listPrixAdhesif.size()!=0){
                    

                     dimCombo.setDisable(false);
                    
                for(int i=0;i<listPrixAdhesif.size();i++){
                Boolean exist = false;
                 PrixAdhesif prixAdhesifD = listPrixAdhesif.get(i);
                 
                if(listeprixAdhesif.size() ==0){
                    
                    listeprixAdhesif.add(prixAdhesifD);
                
                }else {
                   for(int j=0;j<listeprixAdhesif.size();j++){
                   
                   if(prixAdhesifD.getDimension().getLibelle().equals(listeprixAdhesif.get(j).getDimension().getLibelle())){
                   
                       exist = true;
                   }
                
                }
                if(exist == false)
                {
                 listeprixAdhesif.add(prixAdhesifD);
                }
                }
                }
                     
                 listeprixAdhesif.stream().map((prixBox) -> {
            dimCombo.getItems().addAll(prixBox.getDimension().getLibelle());
            return prixBox;
        }).forEachOrdered((prixBox) -> {
            mapDimension.put(prixBox.getDimension().getLibelle(), prixBox.getDimension());
        });  
                }else{
                     
                   dimCombo.setDisable(true);
                }  
                 }else{
                 
                      dimCombo.setValue(detailCommandeRegion.getMatierePremier().getDimension().getLibelle());
                           dimCombo.setDisable(false);
                 }
                  
              
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);  
               GrammageCombo.setDisable(true);  
               typeCartonCombo.setDisable(true);
                intervalleCombo.setDisable(true);
               typeCarCombo.setDisable(true);
               
               ajouterSaisie.setDisable(false);
               
            }        
            else if(detailCommandeRegion.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX_METAL)){
                
         
                intervalleCombo.setDisable(true);
                typeCartonCombo.setDisable(true);
                GrammageCombo.setDisable(true); 
                typeCarCombo.setDisable(true);
                TypeFilmCombo.setDisable(true);
                grammageFilmCombo.setDisable(true);
                
                ajouterSaisie.setDisable(false);
               
                


            }}
        else{
            
               GrammageCombo.setDisable(true);
               intervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               typeCarCombo.setDisable(true); 
               typeCartonCombo.setDisable(true); 
               ajouterSaisie.setDisable(true);
                
            }
//                   for (int i = 0; i < listeDetailCommandeRegion.size(); i++) {
//                    DetailCommandeRegion detailCommandeRegionTmp = listeDetailCommandeRegion.get(i);
//                    
//                    if(detailCommandeRegionTmp.getCmdRegle().equals(Constantes.ETAT_CMR)){
//                    qteLivColumn.setEditable(true);
//                    
//               GrammageCombo.setDisable(true);
//               intervalleCombo.setDisable(true);
//               TypeFilmCombo.setDisable(true);
//               grammageFilmCombo.setDisable(true);
//               dimCombo.setDisable(true);
//               typeCarCombo.setDisable(true); 
//               typeCartonCombo.setDisable(true); 
//               ajouterSaisie.setDisable(true);
//                    
//                    }else{
//                     qteLivColumn.setEditable(false);
//                    }
//                   }
            }}
    }

    @FXML
    private void modifierDetailCommande(ActionEvent event) {
        
            loadDetail();
        setColumnProperties();
        
             GrammageCombo.setDisable(true);
               intervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               typeCarCombo.setDisable(true); 
               typeCartonCombo.setDisable(true); 
        
        ajouterSaisie.setDisable(true);
          clear();
          clientCombo.getSelectionModel().select(-1);
                  fornisseurCombo.getSelectionModel().select(-1);
          
          listeDetailCommandeRegion.clear();
        
    }

    @FXML
    private void ValiderCommande(ActionEvent event) throws ParseException {
        
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        Boolean existe = false; 
                  for(int i=0;i<listeDetailCommandeRegion.size();i++){
            
                 DetailCommandeRegion detailCommandeRegion = listeDetailCommandeRegion.get(i);
                if (detailCommandeRegion.getQuantiteLivree().compareTo(BigDecimal.ZERO)>0) {
                    
                    existe = true;
                    break;
                }
                  }
                  
                  
          if( clientCombo.getSelectionModel().getSelectedItem().isEmpty()|| fornisseurCombo.getSelectionModel().getSelectedItem().isEmpty() || tableDetailCommande.getItems().isEmpty() ||  existe == false){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
     }
        else {
            
              BigDecimal montantTotalMP = BigDecimal.ZERO;
              BigDecimal montant = BigDecimal.ZERO;
              
       
              
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.COMMANDE);
          String nComm = ((sequenceur.getValeur()+1)+"/"+dateFormat.format(date));
         
               for(int i=0;i<listeDetailCommandeRegion.size();i++){
            
                 DetailCommandeRegion detailCommandeRegion = listeDetailCommandeRegion.get(i);
                if (detailCommandeRegion.getQuantiteLivree().compareTo(BigDecimal.ZERO)>0)
                {

                  DetailCommande detailCommande = new DetailCommande();

            detailCommande.setDimension(detailCommandeRegion.getDimension());
            detailCommande.setMatierePremier(detailCommandeRegion.getMatierePremier());
            detailCommande.setQuantite(detailCommandeRegion.getQuantiteLivree());
            detailCommande.setCommande(commande);
            detailCommande.setQuantiteRestee(detailCommandeRegion.getQuantiteLivree());
            detailCommande.setQuantiteRecu(BigDecimal.ZERO);
            detailCommande.setQuantiteLivree(BigDecimal.ZERO);
            detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
            detailCommande.setUtilisateurCreation(nav.getUtilisateur());
            detailCommande.setPrixUnitaire(detailCommandeRegion.getPrixUnitaire());
           
             BigDecimal QteB= detailCommandeRegion.getQuantiteLivree();
             BigDecimal PrixB= detailCommandeRegion.getPrixUnitaire();
            
               montant= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montantTotalMP=montantTotalMP.add(montant).setScale(2,RoundingMode.FLOOR);
 
            listeDetailCommande.add(detailCommande);
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
          
             DetailReceptionRegion detailReceptionRegion = new DetailReceptionRegion();
                  
                detailReceptionRegion.setDetailCommandeRegion(detailCommandeRegion);
                detailReceptionRegion.setClientMP(mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem()));
                detailReceptionRegion.setFourisseur(mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem()));
                detailReceptionRegion.setNumCommande(nComm);
                detailReceptionRegion.setUtilisateurCreation(nav.getUtilisateur());
                detailReceptionRegion.setQuantiteRecu(detailCommandeRegion.getQuantiteLivree());
                detailReceptionRegion.setPrix(detailCommandeRegion.getPrixUnitaire());
                detailReceptionRegion.setNumReceptionRegion(codeReceptionRegion);

                
                detailReceptionRegionDAO.add(detailReceptionRegion);

           }}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
          
        CommandeRegion CommandeRegion=tableCommande.getSelectionModel().getSelectedItem();
        
        
        commande.setUtilisateurCreation(nav.getUtilisateur());
        commande.setDepot(CommandeRegion.getDepot());
        commande.setDateSaisie(new Date());
        commande.setDateCreation(CommandeRegion.getDateCreation());
        commande.setEtat(Constantes.ETAT_COMMANDE_LANCE);
        commande.setTypeCommande(Constantes.COMMANDE_INTERNE);
        commande.setnCommande(nComm);
        commande.setDetailCommandes(listeDetailCommande);
        commande.setFourisseur(mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem()));
        commande.setClientMP(mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem()));
        commande.setPrixTotal(montantTotalMP);
        
        commandeDAO.add(commande);
     
        commande = new Commande();
        
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
        
           
  // Sequenceur Commande        
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           
  // Sequenceur Reception
           Sequenceur sequenceurRCP = sequenceurDAO.findByCode(Constantes.RECEPTION_REGION);
           sequenceurRCP.setValeur(sequenceurRCP.getValeur()+1);
           sequenceurDAO.edit(sequenceurRCP);
           Incrementation ();
           
           
 //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------     

                boolean valeur=false;
               
               for(int i=0;i<listeDetailCommandeRegion.size();i++){

                DetailCommandeRegion detailCommandeRegion = listeDetailCommandeRegion.get(i);
                
               if (detailCommandeRegion.getQuantiteLivree().compareTo(BigDecimal.ZERO)>0)
                {
                    
                detailCommandeRegion.setCmdRegle(Constantes.ETAT_CMNR);
                detailCommandeRegion.setQuantiteLivree(BigDecimal.ZERO.setScale(2));
                listeDetailCommandeRegion.set(i, detailCommandeRegion);
                detailCommandeRegionDAO.edit(detailCommandeRegion);
                }
                
                if (detailCommandeRegion.getQuantiteRestee().compareTo(BigDecimal.ZERO)>0){
                        
                        valeur= true;

                    }
                }
               
                if(valeur== false){

            commandeRegion.setEtat(Constantes.ETAT_COMMANDE_LANCE);
            commandeRegionDAO.edit(commandeRegion);
         
                    modifierDetailCommande(event);
                }
        }
            }
    }

    @FXML
    private void fourOnAction(ActionEvent event) {
    }

    @FXML
    private void intervalleOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void qteByIntervalleOnAction(KeyEvent event) {
    }

    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
    }

    @FXML
    private void ajouterSaisieAction(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        
          DetailCommandeRegion  detailCommandeRegion =tableDetailCommande.getSelectionModel().getSelectedItem();

          
            if(listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).getCmdRegle().equals(Constantes.ETAT_CMR) || detailCommandeRegion.equals(Constantes.ETAT_CMR) ){
              
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.COMMANDE_DEJA_TRAITEE);
                  
              }else{
     
        matierePremiere = matierePremiereDAO.findByCode(codeMPField.getText());
       
            if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX)){

                     if(
                   dimCombo.getSelectionModel().getSelectedItem()==null || 
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null || 
                   clientCombo.getSelectionModel().getSelectedItem()==null ||           
                   intervalleCombo.getSelectionModel().getSelectedItem()==null || 
                   typeCartonCombo.getSelectionModel().getSelectedItem()==null|| 
                   GrammageCombo.getSelectionModel().getSelectedItem()==null

                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
            
                         
           Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());
           Grammage grammage = mapGrammage.get(GrammageCombo.getSelectionModel().getSelectedItem());
           TypeCartonBox typeCartonBox = mapTypeCarton.get(typeCartonCombo.getSelectionModel().getSelectedItem());
           Intervalle intervalle = mapIntervalle.get(intervalleCombo.getSelectionModel().getSelectedItem());
           
           
           PrixBox prixBox=prixBoxDAO.findDimensionByPrixBox(matierePremiere.getDimension().getId(), fournisseur.getId(), matierePremiere.getCategorieMp().getId(), grammage.getId(), typeCartonBox.getId(),intervalle.getId());
            BigDecimal  prixB= BigDecimal.ZERO ;
            
            if(prixBox==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                DetailCommandeRegion detailCommandeRegionTMP =tableDetailCommande.getSelectionModel().getSelectedItem();
                
                prixB= prixBox.getPrix();

             detailCommandeRegion.setCmdRegle(Constantes.ETAT_CMR);
             detailCommandeRegion.setDimension(matierePremiere.getDimension());
             detailCommandeRegion.setCommandeRegion(commandeRegion);
             detailCommandeRegion.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeRegion.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeRegion.setPrixUnitaire(prixB);
           
             BigDecimal QteB= detailCommandeRegionTMP.getQuantite();
             BigDecimal PrixB= prixB;
            
             BigDecimal  montantB= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantB).setScale(2,RoundingMode.FLOOR);

            listeDetailCommandeRegion.set(tableDetailCommande.getSelectionModel().getSelectedIndex(), detailCommandeRegion);

            
            
               GrammageCombo.setDisable(true);
               intervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               typeCarCombo.setDisable(true); 
               typeCartonCombo.setDisable(true); 
               ajouterSaisie.setDisable(true);
            
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.CONFIRMATION_ENREGISTREMENT);
              
           
         }
}
           
            }else if (matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.CARTON)){
       
                    if(
                   dimCombo.getSelectionModel().getSelectedItem()==null || 
                   clientCombo.getSelectionModel().getSelectedItem()==null ||
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null||
                   typeCarCombo.getSelectionModel().getSelectedItem()==null|| 
                   intervalleCombo.getSelectionModel().getSelectedItem()== null

                 
   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
                        
              
           Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());
           TypeCarton typeCarton = mapTypeCar.get(typeCarCombo.getSelectionModel().getSelectedItem());
           Intervalle intervalle = mapIntervalle.get(intervalleCombo.getSelectionModel().getSelectedItem());
           
           
           PrixCarton prixCarton=prixCartonDAO.findDimensionByPrixCarton(matierePremiere.getDimension().getId(), fournisseur.getId(), matierePremiere.getCategorieMp().getId(), typeCarton.getId(),intervalle.getId());
            BigDecimal  prixB= BigDecimal.ZERO ;
            
            if(prixCarton==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                DetailCommandeRegion detailCommandeRegionTMP =tableDetailCommande.getSelectionModel().getSelectedItem();
                
                prixB= prixCarton.getPrix();

           detailCommandeRegion.setCmdRegle(Constantes.ETAT_CMR);
             detailCommandeRegion.setDimension(matierePremiere.getDimension());
             detailCommandeRegion.setCommandeRegion(commandeRegion);
             detailCommandeRegion.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeRegion.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeRegion.setPrixUnitaire(prixB);
           
             BigDecimal QteB= detailCommandeRegionTMP.getQuantite();
             BigDecimal PrixB= prixB;
            
             BigDecimal  montantB= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantB).setScale(2,RoundingMode.FLOOR);
 
            listeDetailCommandeRegion.set(tableDetailCommande.getSelectionModel().getSelectedIndex(), detailCommandeRegion);

            GrammageCombo.setDisable(true);
               intervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               typeCarCombo.setDisable(true); 
               typeCartonCombo.setDisable(true); 
               ajouterSaisie.setDisable(true);
            
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.CONFIRMATION_ENREGISTREMENT);
         }
               }
 
            }else if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_NORMAL)){

                
                   if(
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null||
                   TypeFilmCombo.getSelectionModel().getSelectedItem()==null|| 
                   clientCombo.getSelectionModel().getSelectedItem()==null ||
                   intervalleCombo.getSelectionModel().getSelectedItem()==null|| 
                   grammageFilmCombo.getSelectionModel().getSelectedItem()==null
    ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
                   
             
           Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());
           Dimension dimension =dimensionDAO.findDimensionByCode(Constantes.CODE_DIMENSION);
           TypeFilm typeFilm = mapTypeFilm.get(TypeFilmCombo.getSelectionModel().getSelectedItem());
           GrammageFilm grammageFilm = mapGrammageFilm.get(grammageFilmCombo.getSelectionModel().getSelectedItem());
           Intervalle intervalle = mapIntervalle.get(intervalleCombo.getSelectionModel().getSelectedItem());
           
           
           PrixFilm prixFilmtmp=prixFilmDAO.findDimensionByPrixFilm(fournisseur.getId(), matierePremiere.getCategorieMp().getId(), grammageFilm.getId(), typeFilm.getId(),intervalle.getId());
     
           DetailCommandeRegion detailCommandeRegionTMP =tableDetailCommande.getSelectionModel().getSelectedItem();

            BigDecimal  prixB= BigDecimal.ZERO ;
            
            if(prixFilmtmp==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                prixB= prixFilmtmp.getPrix();

           detailCommandeRegion.setCmdRegle(Constantes.ETAT_CMR);
             detailCommandeRegion.setDimension(dimension);
             detailCommandeRegion.setCommandeRegion(commandeRegion);
             detailCommandeRegion.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeRegion.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeRegion.setPrixUnitaire(prixB);
           
             BigDecimal QteB= detailCommandeRegionTMP.getQuantite();
             BigDecimal PrixB= prixB;
            
             BigDecimal  montantB= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantB).setScale(2,RoundingMode.FLOOR);
 
            listeDetailCommandeRegion.set(tableDetailCommande.getSelectionModel().getSelectedIndex(), detailCommandeRegion);

            GrammageCombo.setDisable(true);
               intervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               typeCarCombo.setDisable(true); 
               typeCartonCombo.setDisable(true); 
               ajouterSaisie.setDisable(true);
            
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.CONFIRMATION_ENREGISTREMENT);
    
         }
                            
                   }        
             
            }
            else if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_GOLD)){

 
              if(
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null || 
                   TypeFilmCombo.getSelectionModel().getSelectedItem()==null|| 
                   intervalleCombo.getSelectionModel().getSelectedItem()==null|| 
                   grammageFilmCombo.getSelectionModel().getSelectedItem()==null
    ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
                  
           Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());
           Dimension dimension =dimensionDAO.findDimensionByCode(Constantes.CODE_DIMENSION);
           TypeFilm typeFilm = mapTypeFilm.get(TypeFilmCombo.getSelectionModel().getSelectedItem());
           GrammageFilm grammageFilm = mapGrammageFilm.get(grammageFilmCombo.getSelectionModel().getSelectedItem());
           Intervalle intervalle = mapIntervalle.get(intervalleCombo.getSelectionModel().getSelectedItem());
           
           
           PrixFilm prixFilmTm=prixFilmDAO.findDimensionByPrixFilm(fournisseur.getId(), typeFilm.getId(), grammageFilm.getId(),matierePremiere.getCategorieMp().getId(), intervalle.getId());
              
             DetailCommandeRegion detailCommandeRegionTMP =tableDetailCommande.getSelectionModel().getSelectedItem();     
           
            BigDecimal  prixB= BigDecimal.ZERO ;

            if(prixFilmTm==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                prixB= prixFilmTm.getPrix();

           detailCommandeRegion.setCmdRegle(Constantes.ETAT_CMR);
             detailCommandeRegion.setDimension(dimension);
             detailCommandeRegion.setCommandeRegion(commandeRegion);
             detailCommandeRegion.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeRegion.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeRegion.setPrixUnitaire(prixB);
           
             BigDecimal QteB= detailCommandeRegionTMP.getQuantite();
             BigDecimal PrixB= prixB;
            
             BigDecimal montantB= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantB).setScale(2,RoundingMode.FLOOR);
 
            listeDetailCommandeRegion.set(tableDetailCommande.getSelectionModel().getSelectedIndex(), detailCommandeRegion);
       
            GrammageCombo.setDisable(true);
               intervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               typeCarCombo.setDisable(true); 
               typeCartonCombo.setDisable(true); 
               ajouterSaisie.setDisable(true);
            
    nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.CONFIRMATION_ENREGISTREMENT);
         }
              }    
               }
            
            else if (matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.ADHESIF)){
              
                
               if(
                   dimCombo.getSelectionModel().getSelectedItem()==null || 
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null 
                
   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {

                    if (codeMPField.getText().equals(Constantes.MP_901)|| matierePremiere.getCode().equals(Constantes.MP_907) || matierePremiere.getCode().equals(Constantes.MP_908)|| matierePremiere.getCode().equals(Constantes.MP_757)|| matierePremiere.getCode().equals(Constantes.MP_836)){
                     
           Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());
           Dimension dimension=mapDimension.get(dimCombo.getSelectionModel().getSelectedItem());
         
           
           
           
           PrixAdhesif prixAdhesif =prixAdhesifDAO.findDimensionByPrix(dimension.getId(), fournisseur.getId(), matierePremiere.getCategorieMp().getId());
            BigDecimal  prixA=BigDecimal.ZERO ;
            
            if(prixAdhesif==null){
                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
             return;
            }
            else {
                
                DetailCommandeRegion detailCommandeRegionTMP =tableDetailCommande.getSelectionModel().getSelectedItem();
                
                prixA=prixAdhesif.getPrix();

                detailCommandeRegion.setCmdRegle(Constantes.ETAT_CMR);
             detailCommandeRegion.setDimension(dimension);
             detailCommandeRegion.setCommandeRegion(commandeRegion);
             detailCommandeRegion.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeRegion.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeRegion.setPrixUnitaire(prixA);
           
             BigDecimal QteA= detailCommandeRegionTMP.getQuantite();
             BigDecimal PrixA=prixA;
            
             BigDecimal montantA= (QteA.multiply(PrixA)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantA).setScale(2,RoundingMode.FLOOR);

             
            listeDetailCommandeRegion.set(tableDetailCommande.getSelectionModel().getSelectedIndex(), detailCommandeRegion);

            GrammageCombo.setDisable(true);
               intervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               typeCarCombo.setDisable(true); 
               typeCartonCombo.setDisable(true); 
               ajouterSaisie.setDisable(true);
            
      nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.CONFIRMATION_ENREGISTREMENT);
         }
                    } else{
  
            Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());
         
           PrixAdhesif prixAdhesif =prixAdhesifDAO.findDimensionByPrix(matierePremiere.getDimension().getId(), fournisseur.getId(), matierePremiere.getCategorieMp().getId());
            BigDecimal  prixA=BigDecimal.ZERO ;
            
            if(prixAdhesif==null){
                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
             return;
            }
            else {
                
                DetailCommandeRegion detailCommandeRegionTMP =tableDetailCommande.getSelectionModel().getSelectedItem();
                
                prixA=prixAdhesif.getPrix();

                detailCommandeRegion.setCmdRegle(Constantes.ETAT_CMR);
             detailCommandeRegion.setDimension(matierePremiere.getDimension());
             detailCommandeRegion.setCommandeRegion(commandeRegion);
             detailCommandeRegion.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeRegion.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeRegion.setPrixUnitaire(prixA);
           
             BigDecimal QteA= detailCommandeRegionTMP.getQuantite();
             BigDecimal PrixA=prixA;
            
             BigDecimal montantA= (QteA.multiply(PrixA)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantA).setScale(2,RoundingMode.FLOOR);

             
            listeDetailCommandeRegion.set(tableDetailCommande.getSelectionModel().getSelectedIndex(), detailCommandeRegion);
            
            GrammageCombo.setDisable(true);
               intervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               typeCarCombo.setDisable(true); 
               typeCartonCombo.setDisable(true); 
               ajouterSaisie.setDisable(true);
            
               nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.CONFIRMATION_ENREGISTREMENT);

            }
                    }
              }
            }     
            else if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX_METAL)){

 
              if(codeMPField.getText().equalsIgnoreCase("")|| 
                   quantiteField.getText().equalsIgnoreCase("")|| 
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null 

    ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
                 
           Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());
           Dimension dimension =dimensionDAO.findDimensionByCode(Constantes.CODE_DIMENSION);
          

           PrixBoxMetal prixBoxMetalTm=prixBoxMetalDAO.findDimensionByPrixBoxMetal(fournisseur.getId(), matierePremiere.getCategorieMp().getId());
              
             DetailCommandeRegion detailCommandeRegionTMP =tableDetailCommande.getSelectionModel().getSelectedItem();     
           
            BigDecimal  prixB= BigDecimal.ZERO ;

            if(prixBoxMetalTm==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                prixB= prixBoxMetalTm.getPrix();

           detailCommandeRegion.setCmdRegle(Constantes.ETAT_CMR);
             detailCommandeRegion.setDimension(dimension);
             detailCommandeRegion.setCommandeRegion(commandeRegion);
             detailCommandeRegion.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeRegion.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeRegion.setPrixUnitaire(prixB);
           
             BigDecimal QteB= detailCommandeRegionTMP.getQuantite();
             BigDecimal PrixB= prixB;
            
             BigDecimal montantB= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantB).setScale(2,RoundingMode.FLOOR);
 
            listeDetailCommandeRegion.set(tableDetailCommande.getSelectionModel().getSelectedIndex(), detailCommandeRegion);
            
            GrammageCombo.setDisable(true);
               intervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               typeCarCombo.setDisable(true); 
               typeCartonCombo.setDisable(true); 
               ajouterSaisie.setDisable(true);
            
    nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.CONFIRMATION_ENREGISTREMENT);
    
         }
              }     
               }
            
//                for (int i = 0; i < listeDetailCommandeRegion.size(); i++) {
//                    DetailCommandeRegion detailCommandeRegionTmp = listeDetailCommandeRegion.get(i);
//                    
//                    if(detailCommandeRegionTmp.getCmdRegle().equals(Constantes.ETAT_CMR)){
//                    qteLivColumn.setEditable(true);
//                    
//               GrammageCombo.setDisable(true);
//               intervalleCombo.setDisable(true);
//               TypeFilmCombo.setDisable(true);
//               grammageFilmCombo.setDisable(true);
//               dimCombo.setDisable(true);
//               typeCarCombo.setDisable(true); 
//               typeCartonCombo.setDisable(true); 
//               ajouterSaisie.setDisable(true);
//                    
//                    
//                    }else{
//                     qteLivColumn.setEditable(false);
//                    }
//                }
            }}
    }
    
       private void clear(){
        codeMPField.clear();
        quantiteField.clear();
    
        dimCombo.getItems().clear();
               
                 intervalleCombo.getSelectionModel().select(-1);
                 typeCarCombo.getSelectionModel().select(-1);
                 dimCombo.getSelectionModel().select(-1);
                 GrammageCombo.getSelectionModel().select(-1);
                 grammageFilmCombo.getSelectionModel().select(-1);
                 TypeFilmCombo.getSelectionModel().select(-1);
                 typeCartonCombo.getSelectionModel().select(-1);


    } 

    @FXML
    private void editCommitQuantiteLivreeColumn(TableColumn.CellEditEvent<DetailCommandeRegion, BigDecimal> event) {
        
//         if (ajouterSaisie.isDisable() == false) {
//
//            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
//       
//        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    BigDecimal qteLiv = BigDecimal.ZERO;
                    BigDecimal qteRestee= BigDecimal.ZERO;
                    BigDecimal qteRecu= BigDecimal.ZERO;
                    BigDecimal calculeQuantiteRecu= BigDecimal.ZERO; 
                    BigDecimal getCalculeQuantiteRestee= BigDecimal.ZERO;
                    
//                     Magasin magasin = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
                    
                ((DetailCommandeRegion) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteLivree(event.getNewValue());
               
                
                    tableDetailCommande.refresh();  

  
                 qteLiv = qteLivColumn.getCellData(event.getTablePosition().getRow());
                
                 qteRestee =  listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).getQuantiteRestee();
                
                 qteRecu =  listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).getQuantiteRecu();
                
                 calculeQuantiteRecu = qteRecu.add(qteLiv).setScale(2, RoundingMode.FLOOR);
               
                 getCalculeQuantiteRestee =  qteRestee.subtract(qteLiv).setScale(2, RoundingMode.FLOOR);
                
//                 listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).setMagasin(magasin.getLibelle());
                 listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).setQuantiteRecu(calculeQuantiteRecu);
                 listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).setQuantiteRestee(getCalculeQuantiteRestee);
                
                 
                 
                setColumnProperties();

           

            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailCommande.refresh();
//            }
        }
//        
    }

    

    @FXML
    private void imprimerOnAction(ActionEvent event) {
     
        try{
                  if (tableCommande.getSelectionModel().getSelectedIndex()==-1) {

            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.VERIFICATION_SELECTION_LIGNE);
            tableDetailCommande.refresh();
        } else {
   
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ValiderCommandeRegionController.class.getResource(nav.getiReportValiderCommandeRegion()));

     para.put("NumCommande",listeCommandeRegion.get(tableCommande.getSelectionModel().getSelectedIndex()).getnCommande());
        
            para.put("DateLiv",listeCommandeRegion.get(tableCommande.getSelectionModel().getSelectedIndex()).getDateCreation());

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailCommandeRegion));
               JasperViewer.viewReport(jp, false);

               }
        } catch (JRException ex) {
            Logger.getLogger(ValiderCommandeRegionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    




    
}
