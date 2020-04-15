/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.commande;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.DetailCommande;
import dao.Entity.Dimension;
import dao.Entity.Fournisseur;
import dao.Entity.Grammage;
import dao.Entity.GrammageFilm;
import dao.Entity.Intervalle;
import dao.Entity.MatierePremier;
import dao.Entity.PrixAdhesif;
import dao.Entity.PrixBox;
import dao.Entity.PrixBoxMetal;
import dao.Entity.PrixCarton;
import dao.Entity.PrixFilm;
import dao.Entity.TypeCarton;
import dao.Entity.TypeCartonBox;
import dao.Entity.TypeFilm;
import dao.Manager.ClientMPDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.DetailCommandeDAO;
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
import dao.ManagerImpl.DetailCommandeDAOImpl;
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
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *  
 * @author Hp
 */
public abstract class SaisirMatierePremiereController extends AnchorPane implements Initializable {
    private int POUR;
    Commande commande;
    
    @FXML    
    private TableView<DetailCommande> detailCommandetable;
    @FXML    
    private TableColumn<DetailCommande, String> codeMPColumn;
    @FXML    
    private TableColumn<DetailCommande, String> libelleColumn;
    @FXML    
    private TableColumn<DetailCommande, Integer> quantiteColumn;
    @FXML    
    private TableColumn<DetailCommande, String> dimColumn;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private Button ajouterMp;
    @FXML
    private Button initialiserBtn;
    @FXML
    private Label qteAfficchage;
    
    
    public SaisirMatierePremiereController (int POUR,Commande commande){
    this.commande= commande;
    this.POUR=POUR;
    setAll(nav.getSaisirMatierePremiere(), this);
    }
    
    public static void setAll(String path, Object root){
    FXMLLoader fxmlLoader = new FXMLLoader(root.getClass().getResource(path));
        fxmlLoader.setRoot(root);
        fxmlLoader.setController(root);
        try {
            System.out.println(path);
            fxmlLoader.load();
        } catch (IOException exception){
            throw new RuntimeException(exception);
        }

    }
    public abstract void refresh();
    
    @FXML
    private TextField nCommandeField;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ComboBox<String> fornisseurCombo;
    @FXML
    private DatePicker dateSaisie;
    @FXML
    private TextField codeMPField;
    @FXML
    private TextField libelleField;
    @FXML
    private ComboBox<String> dimCombo;
    @FXML
    private TextField quantiteField;
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
    

    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    private Map<String,Dimension> mapDimension=new HashMap<>();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    private Map<String,Grammage> mapGrammage=new HashMap<>();
    private Map<String,TypeCartonBox> mapTypeCarton=new HashMap<>();
    private Map<String,TypeFilm> mapTypeFilm=new HashMap<>();
    private Map<String,GrammageFilm> mapGrammageFilm=new HashMap<>();
     private Map<String,TypeCarton> mapTypeCar=new HashMap<>();
    private Map<String,Intervalle> mapIntervalle=new HashMap<>();
//
    
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl(); 
    MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
    DimensionDAO dimensionDAO = new DimensionDAOImpl();
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
    PrixBoxMetalDAO prixBoxMetalDAO = new PrixBoxMetalDAOImpl();
    TypeCartonDAO typeCartonDAO = new TypeCartonDAOImpl();
    IntervalleDAO intervalleDAO = new IntervalleDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
      
    
    private ObservableList<DetailCommande> listeDetailCommande;
  
     private final ObservableList<PrixBox> listeprixBox=FXCollections.observableArrayList();
    private final ObservableList<PrixCarton> listeprixCarton=FXCollections.observableArrayList();
    private final ObservableList<PrixFilm> listeprixFilmNormal=FXCollections.observableArrayList();
    private final ObservableList<PrixFilm> listeprixFilmGolde=FXCollections.observableArrayList();
    private final ObservableList<PrixAdhesif> listeprixAdhesif=FXCollections.observableArrayList();
    
    navigation nav = new navigation();
    MatierePremier matierePremiere=new MatierePremier();
    
       BigDecimal montanTotal=BigDecimal.ZERO;
    
      void setColumnProperties(){
         
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


        dimColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDimension().getLibelle());
                }
                
             });
        
        quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<DetailCommande, Integer> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });



    }
    
    void loadDetail(){

        detailCommandetable.setItems(listeDetailCommande);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
       listeDetailCommande =FXCollections.observableArrayList(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
       
       
    
        nCommandeField.setText(commande.getnCommande());
        clientCombo.setValue(commande.getClientMP().getNom());
        LocalDate date = new java.util.Date( commande.getDateCreation().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dateSaisie.setValue(date);
        fornisseurCombo.setValue(commande.getFourisseur().getNom());
        ////
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
               quantiteField.setDisable(true);
       
        
        

     
          detailCommandetable.setEditable(true);
          quantiteColumn.setEditable(true);
          montanTotal=BigDecimal.ZERO;
    
          setColumnProperties();    
          loadDetail();
    }    

    @FXML
    private void commandeKeyPressed(KeyEvent event) {
    }

    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
         dimCombo.getItems().clear();
       
        if (event.getCode().equals(KeyCode.ENTER))
            {
                if(clientCombo.getSelectionModel().getSelectedItem()== null ||fornisseurCombo.getSelectionModel().getSelectedItem()==null ){
                
                      nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.VERIFIER_FOURNISSEUR_CLIENT);
                 
                }else{
            
                         matierePremiere = matierePremiereDAO.findByCode(codeMPField.getText());
                libelleField.setText(matierePremiere.getNom());
       
             
              if(!matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX) && !matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.CARTON) && !matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_NORMAL)&& !matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_GOLD)&& !matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.ADHESIF)&& !matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX_METAL)){
              
                dimCombo.setDisable(true);
                typeCartonCombo.setDisable(true);
                typeCarCombo.setDisable(true);
                GrammageCombo.setDisable(true); 
                TypeFilmCombo.setDisable(true);
                grammageFilmCombo.setDisable(true);
                intervalleCombo.setDisable(true);
                quantiteField.setDisable(true);
                
              }else{
            
            if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX)){
                
                
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Dimansion <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                
                   dimCombo.setValue(matierePremiere.getDimension().getLibelle());

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Intervalle <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<               
            
            List<PrixBox> listPrixBox= prixBoxDAO.findPrixBoxByCategorieMp(matierePremiere.getCategorieMp().getId(), commande.getFourisseur().getId());

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
                
                quantiteField.setDisable(false);
                GrammageCombo.setDisable(false);
                typeCartonCombo.setDisable(false);

            }else if (matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.CARTON)){
                
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Dimansion <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

                      dimCombo.setValue(matierePremiere.getDimension().getLibelle());
                  
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Intervalle <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

                       List<PrixCarton> listPrixCartons= prixCartonDAO.findPrixCartonByCategorieMp(matierePremiere.getCategorieMp().getId(), commande.getFourisseur().getId());
                  
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
               
               quantiteField.setDisable(false);
               typeCarCombo.setDisable(false);
               
            }else if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_GOLD)){
                
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Dimansion <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

                      dimCombo.setValue(matierePremiere.getDimension().getLibelle());
                  
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Intervalle <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<                          
             
                    listeprixFilmGolde.clear();
                    intervalleCombo.getItems().clear();
    
                List<PrixFilm> listPrixFilm= prixFilmDAO.findPrixFilmByCategorieMp(matierePremiere.getCategorieMp().getId(),commande.getFourisseur().getId());
 
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
                 quantiteField.setDisable(false);
                TypeFilmCombo.setDisable(false);
                grammageFilmCombo.setDisable(false);


            }
            else if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_NORMAL)){
                    
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Dimansion <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

                      dimCombo.setValue(matierePremiere.getDimension().getLibelle());
                  
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Intervalle <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<                
                   
                    listeprixFilmNormal.clear();
                    intervalleCombo.getItems().clear();
                
                    
                   List<PrixFilm> listPrixFilm= prixFilmDAO.findPrixFilmByCategorieMp(matierePremiere.getCategorieMp().getId(),commande.getFourisseur().getId());
                   
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
                 listeprixFilmNormal.add(prixFilm);
                }
                }
                }
                 listeprixFilmNormal.stream().map((prixFilm) -> {
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
                 quantiteField.setDisable(false);
                TypeFilmCombo.setDisable(false);
                grammageFilmCombo.setDisable(false);


            }
            else if (matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.ADHESIF)){
  
                
                
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Dimansion <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

           
        if (matierePremiere.getCode().equals(Constantes.MP_901)|| matierePremiere.getCode().equals(Constantes.MP_907) || matierePremiere.getCode().equals(Constantes.MP_908)|| matierePremiere.getCode().equals(Constantes.MP_757)|| matierePremiere.getCode().equals(Constantes.MP_836)){
                  
                  
                  List<PrixAdhesif> listPrixAdhesif= prixAdhesifDAO.findPrixAdhesifByCategorieMp(matierePremiere.getCategorieMp().getId(), commande.getFourisseur().getId());
                  
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
        
                   dimCombo.setValue(matierePremiere.getDimension().getLibelle());
                           dimCombo.setDisable(false);
        }
                  
              
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);  
               GrammageCombo.setDisable(true);  
               typeCartonCombo.setDisable(true);
                intervalleCombo.setDisable(true);
               typeCarCombo.setDisable(true);
               
                quantiteField.setDisable(false);
            }              
            else if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX_METAL)){
                
  
                  
                intervalleCombo.setDisable(true);
                typeCartonCombo.setDisable(true);
                GrammageCombo.setDisable(true); 
                typeCarCombo.setDisable(true);
                TypeFilmCombo.setDisable(true);
                grammageFilmCombo.setDisable(true);
                
                quantiteField.setDisable(false);
                


            }          

              }
            }
            }
        
    }


    @FXML
    private void intervalleOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void ajouterMpAction(ActionEvent event) {
        if(clientCombo.getSelectionModel().getSelectedItem()== null || clientCombo.getSelectionModel().getSelectedItem().isEmpty() ||fornisseurCombo.getSelectionModel().getSelectedItem()== null || fornisseurCombo.getSelectionModel().getSelectedItem().isEmpty() || detailCommandetable.getItems().size() ==0 ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
     }
        else {
            

        commande.setDetailCommandes(listeDetailCommande);
        commandeDAO.edit(commande);
     
        
       
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
 
            refresh();
            Stage stage = (Stage)
            ajouterMp.getScene().getWindow();
            stage.close();
        }
      

        
    }

    @FXML
    private void initialiserAction(ActionEvent event) {
    }

    @FXML
    private void ajouterSaisieAction(ActionEvent event) {
           DetailCommande detailCommande = new DetailCommande();

     
        matierePremiere = matierePremiereDAO.findByCode(codeMPField.getText());
       
       if(!matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX) && !matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.CARTON) && !matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_NORMAL) && !matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_GOLD)&& !matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.ADHESIF)&& !matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX_METAL)){
              
               nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                
              }else{
            
            if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX)){

                     if(codeMPField.getText().equalsIgnoreCase("")|| 
                   libelleField.getText().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("")|| 
                   dimCombo.getSelectionModel().getSelectedItem()==null || 
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null || 
                   intervalleCombo.getSelectionModel().getSelectedItem()==null || 
                   typeCartonCombo.getSelectionModel().getSelectedItem()==null|| 
                   GrammageCombo.getSelectionModel().getSelectedItem()==null

                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
              
                         
           Fournisseur fournisseur=commande.getFourisseur();
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
                
                prixB= prixBox.getPrix();

           
             detailCommande.setDimension(matierePremiere.getDimension());
             detailCommande.setMatierePremier(matierePremiere);
             detailCommande.setQuantite(new BigDecimal(quantiteField.getText()));
             detailCommande.setQuantiteRestee(new BigDecimal(quantiteField.getText()));
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setCommande(commande);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setPrixUnitaire(prixB);
           
             BigDecimal QteB= new BigDecimal(quantiteField.getText());
             BigDecimal PrixB= prixB;
            
             BigDecimal  montantB= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantB).setScale(2,RoundingMode.FLOOR);

            listeDetailCommande.add(detailCommande);

            setColumnProperties();
            loadDetail();
            clear();
         }
}
           
            }else if (matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.CARTON)){
       
                    if(codeMPField.getText().equalsIgnoreCase("")|| 
                   libelleField.getText().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("")|| 
                   dimCombo.getSelectionModel().getSelectedItem()==null || 
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null||
                   typeCarCombo.getSelectionModel().getSelectedItem()==null|| 
                  intervalleCombo.getSelectionModel().getSelectedItem()== null

                 
   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
              
            Fournisseur fournisseur=commande.getFourisseur();
           TypeCarton typeCarton = mapTypeCar.get(typeCarCombo.getSelectionModel().getSelectedItem());
           Intervalle intervalle = mapIntervalle.get(intervalleCombo.getSelectionModel().getSelectedItem());
           
           
           PrixCarton prixCarton=prixCartonDAO.findDimensionByPrixCarton(matierePremiere.getDimension().getId(), fournisseur.getId(), matierePremiere.getCategorieMp().getId(), typeCarton.getId(),intervalle.getId());
            BigDecimal  prixB= BigDecimal.ZERO ;
            
            if(prixCarton==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                prixB= prixCarton.getPrix();

           
             detailCommande.setDimension(matierePremiere.getDimension());
             detailCommande.setMatierePremier(matierePremiere);
             detailCommande.setQuantite(new BigDecimal(quantiteField.getText()));
             detailCommande.setQuantiteRestee(new BigDecimal(quantiteField.getText()));
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setCommande(commande);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setPrixUnitaire(prixB);
           
             BigDecimal QteB= new BigDecimal(quantiteField.getText());
             BigDecimal PrixB= prixB;
            
             BigDecimal  montantB= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantB).setScale(2,RoundingMode.FLOOR);
 
            listeDetailCommande.add(detailCommande);

            setColumnProperties();
            loadDetail();
            clear();
         }
               }
 
            }else if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_NORMAL)){
             

                
                   if(codeMPField.getText().equalsIgnoreCase("")|| 
                   libelleField.getText().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("")|| 
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null ||
                   TypeFilmCombo.getSelectionModel().getSelectedItem()==null|| 
                   intervalleCombo.getSelectionModel().getSelectedItem()==null|| 
                   grammageFilmCombo.getSelectionModel().getSelectedItem()==null
    ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
                   
             
            Fournisseur fournisseur=commande.getFourisseur();
//           Dimension dimension =dimensionDAO.findDimensionByCode(Constantes.CODE_DIMENSION);
           TypeFilm typeFilm = mapTypeFilm.get(TypeFilmCombo.getSelectionModel().getSelectedItem());
           GrammageFilm grammageFilm = mapGrammageFilm.get(grammageFilmCombo.getSelectionModel().getSelectedItem());
           Intervalle intervalle = mapIntervalle.get(intervalleCombo.getSelectionModel().getSelectedItem());
           
           
           PrixFilm prixFilmtmp=prixFilmDAO.findDimensionByPrixFilm(fournisseur.getId(),typeFilm.getId() , grammageFilm.getId(), matierePremiere.getCategorieMp().getId() ,intervalle.getId());
     

            BigDecimal  prixB= BigDecimal.ZERO ;
            
            if(prixFilmtmp==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                prixB= prixFilmtmp.getPrix();

           
             detailCommande.setDimension(matierePremiere.getDimension());
             detailCommande.setMatierePremier(matierePremiere);
             detailCommande.setQuantite(new BigDecimal(quantiteField.getText()));
             detailCommande.setQuantiteRestee(new BigDecimal(quantiteField.getText()));
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setCommande(commande);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setPrixUnitaire(prixB);
           
             BigDecimal QteB= new BigDecimal(quantiteField.getText());
             BigDecimal PrixB= prixB;
            
             BigDecimal  montantB= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantB).setScale(2,RoundingMode.FLOOR);
 
            listeDetailCommande.add(detailCommande);

            setColumnProperties();
            loadDetail();
            clear();
         }
                            
                   }        
             
            }
            else if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_GOLD)){

 
              if(codeMPField.getText().equalsIgnoreCase("")|| 
                   libelleField.getText().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("")|| 
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null || 
                   TypeFilmCombo.getSelectionModel().getSelectedItem()==null|| 
                   intervalleCombo.getSelectionModel().getSelectedItem()==null|| 
                   grammageFilmCombo.getSelectionModel().getSelectedItem()==null
    ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
                  
            Fournisseur fournisseur=commande.getFourisseur();
//           Dimension dimension =dimensionDAO.findDimensionByCode(Constantes.CODE_DIMENSION);
           TypeFilm typeFilm = mapTypeFilm.get(TypeFilmCombo.getSelectionModel().getSelectedItem());
           GrammageFilm grammageFilm = mapGrammageFilm.get(grammageFilmCombo.getSelectionModel().getSelectedItem());
           Intervalle intervalle = mapIntervalle.get(intervalleCombo.getSelectionModel().getSelectedItem());
           
           
           PrixFilm prixFilmTm=prixFilmDAO.findDimensionByPrixFilm(fournisseur.getId(), typeFilm.getId(), grammageFilm.getId(),matierePremiere.getCategorieMp().getId(), intervalle.getId());
              
                                              
            BigDecimal  prixB= BigDecimal.ZERO ;
             System.out.println("prixFilmTm "+prixFilmTm);
            if(prixFilmTm==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                prixB= prixFilmTm.getPrix();

           
             detailCommande.setDimension(matierePremiere.getDimension());
             detailCommande.setMatierePremier(matierePremiere);
             detailCommande.setQuantite(new BigDecimal(quantiteField.getText()));
             detailCommande.setQuantiteRestee(new BigDecimal(quantiteField.getText()));
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setCommande(commande);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setPrixUnitaire(prixB);
           
             BigDecimal QteB= new BigDecimal(quantiteField.getText());
             BigDecimal PrixB= prixB;
            
             BigDecimal  montantB= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantB).setScale(2,RoundingMode.FLOOR);
 
            listeDetailCommande.add(detailCommande);

            setColumnProperties();
            loadDetail();
            clear();
         }
              }    
               }      
            else if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX_METAL)){

 
              if(codeMPField.getText().equalsIgnoreCase("")|| 
                   libelleField.getText().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("")|| 
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null 

    ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
                  
            Fournisseur fournisseur=commande.getFourisseur();
           Dimension dimension =dimensionDAO.findDimensionByCode(Constantes.CODE_DIMENSION);


           PrixBoxMetal prixBoxMetalTm=prixBoxMetalDAO.findDimensionByPrixBoxMetal(fournisseur.getId(),matierePremiere.getCategorieMp().getId());
              
                                              
            BigDecimal  prixB= BigDecimal.ZERO ;
            
            if(prixBoxMetalTm==null){
                
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                
                return;
            }
            else {
                
                prixB= prixBoxMetalTm.getPrix();

           
             detailCommande.setDimension(dimension);
             detailCommande.setMatierePremier(matierePremiere);
             detailCommande.setQuantite(new BigDecimal(quantiteField.getText()));
             detailCommande.setCommande(commande);
             detailCommande.setQuantiteRestee(new BigDecimal(quantiteField.getText()));
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setPrixUnitaire(prixB);
           
             BigDecimal QteB= new BigDecimal(quantiteField.getText());
             BigDecimal PrixB= prixB;
            
             BigDecimal  montantB= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantB).setScale(2,RoundingMode.FLOOR);
 
            listeDetailCommande.add(detailCommande);

            setColumnProperties();
            loadDetail();
            clear();
         }
              }    
               }
            
            else if (matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.ADHESIF)){
              
               if(codeMPField.getText().equalsIgnoreCase("")|| 
                   libelleField.getText().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("")|| 
                   dimCombo.getSelectionModel().getSelectedItem()==null || 
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null 
                
   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {

                     if (codeMPField.getText().equals(Constantes.MP_901)|| matierePremiere.getCode().equals(Constantes.MP_907) || matierePremiere.getCode().equals(Constantes.MP_908)|| matierePremiere.getCode().equals(Constantes.MP_757)|| matierePremiere.getCode().equals(Constantes.MP_836)){
                     
            Fournisseur fournisseur=commande.getFourisseur();
           Dimension dimension=mapDimension.get(dimCombo.getSelectionModel().getSelectedItem());

           PrixAdhesif prixAdhesif =prixAdhesifDAO.findDimensionByPrix(dimension.getId(), fournisseur.getId(), matierePremiere.getCategorieMp().getId());
            BigDecimal  prixA=BigDecimal.ZERO ;
            
            if(prixAdhesif==null){
                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
             return;
            }
            else {
                prixA=prixAdhesif.getPrix();

                
             detailCommande.setDimension(dimension);
             detailCommande.setMatierePremier(matierePremiere);
             detailCommande.setQuantite(new BigDecimal(quantiteField.getText()));
             detailCommande.setQuantiteRestee(new BigDecimal(quantiteField.getText()));
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setCommande(commande);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setPrixUnitaire(prixA);
           
             BigDecimal QteA= new BigDecimal(quantiteField.getText());
             BigDecimal PrixA=prixA;
            
             BigDecimal montantA= (QteA.multiply(PrixA)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantA).setScale(2,RoundingMode.FLOOR);

             
            listeDetailCommande.add(detailCommande);

            setColumnProperties();
            loadDetail();
            clear();
         }
               }else{
                           Fournisseur fournisseur=commande.getFourisseur();

           PrixAdhesif prixAdhesif =prixAdhesifDAO.findDimensionByPrix(matierePremiere.getDimension().getId(), fournisseur.getId(), matierePremiere.getCategorieMp().getId());
            BigDecimal  prixA=BigDecimal.ZERO ;
            
            if(prixAdhesif==null){
                 nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
             return;
            }
            else {
                prixA=prixAdhesif.getPrix();

                
             detailCommande.setDimension(matierePremiere.getDimension());
             detailCommande.setMatierePremier(matierePremiere);
             detailCommande.setQuantite(new BigDecimal(quantiteField.getText()));
             detailCommande.setCommande(commande);
             detailCommande.setQuantiteRestee(new BigDecimal(quantiteField.getText()));
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setPrixUnitaire(prixA);
           
             BigDecimal QteA= new BigDecimal(quantiteField.getText());
             BigDecimal PrixA=prixA;
            
             BigDecimal montantA= (QteA.multiply(PrixA)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantA).setScale(2,RoundingMode.FLOOR);

             
            listeDetailCommande.add(detailCommande);

            setColumnProperties();
            loadDetail();
            clear();
                         
                     }
                     }
               }
            }
            
              }
       
    }
    
    
        private void clear(){
        codeMPField.setText("MP_");
        libelleField.clear();
        quantiteField.clear();
        qteAfficchage.setText("");
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
    private void qteByIntervalleOnAction(KeyEvent event) {
           
       qteAfficchage.setText("");
       
         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);
////       dfs.getDecimalFormatSymbols()= DecimalFormatSymbols(Locale.getDefault());
     qteAfficchage.setText(df.format(new BigDecimal(quantiteField.getText()).setScale(2,RoundingMode.DOWN)));
  

  
        
        if (event.getCode().equals(KeyCode.ENTER) )
            {
                  intervalleCombo.getItems().clear();
                  
             if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.BOX)){
              
                        List<Intervalle> listIntervalle=new ArrayList<>();
//               listIntervalle.add(listeprixBox.get(0).getIntervalle());
               Boolean exist = false;
                 for(int i=0;i<listeprixBox.size();i++){
                     
                     int Qte =Integer.parseInt(quantiteField.getText());
                     
                     if(Qte>=listeprixBox.get(i).getIntervalle().getValeurMin() && Qte<=listeprixBox.get(i).getIntervalle().getValeurMax() )
                     {
                          listIntervalle.add(listeprixBox.get(i).getIntervalle());
                          exist = true;
                     }
                  
                 }
                   if(exist == false ){
                         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_QTE);
                    }
                
        
            listIntervalle.stream().map((intervalle) -> {
            intervalleCombo.getItems().addAll(intervalle.getLibelle());
            return intervalle;
        }).forEachOrdered((intervalle) -> {
            mapIntervalle.put(intervalle.getLibelle(), intervalle);
        });  
              }else if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.CARTON)){
                  
                                     List<Intervalle> listIntervalle=new ArrayList<>();
//               listIntervalle.add(listeprixBox.get(0).getIntervalle());
                    Boolean exist = false;
                 for(int i=0;i<listeprixCarton.size();i++){
                     
                     int Qte =Integer.parseInt(quantiteField.getText());
                     
                     if(Qte>=listeprixCarton.get(i).getIntervalle().getValeurMin() && Qte<=listeprixCarton.get(i).getIntervalle().getValeurMax() )
                     {
                         
                          listIntervalle.add(listeprixCarton.get(i).getIntervalle());
                            exist = true;
                     }
                 } 
                 if(exist == false ){
                         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_QTE);
                    }
                 
                
        
            listIntervalle.stream().map((intervalle) -> {
            intervalleCombo.getItems().addAll(intervalle.getLibelle());
            return intervalle;
        }).forEachOrdered((intervalle) -> {
            mapIntervalle.put(intervalle.getLibelle(), intervalle);
        }); 
              }  
            
            }else if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_GOLD)){
                  
                                     List<Intervalle> listIntervalle=new ArrayList<>();
//               listIntervalle.add(listeprixBox.get(0).getIntervalle());
                   Boolean exist = false;
                 for(int i=0;i<listeprixFilmGolde.size();i++){
                     
                     int Qte =Integer.parseInt(quantiteField.getText());
                     
                     if(Qte>=listeprixFilmGolde.get(i).getIntervalle().getValeurMin() && Qte<=listeprixFilmGolde.get(i).getIntervalle().getValeurMax() )
                     {
                         
                          listIntervalle.add(listeprixFilmGolde.get(i).getIntervalle());
                              exist = true;
                     }
                 }
                   if(exist == false ){
                         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_QTE);
                    }
        
            listIntervalle.stream().map((intervalle) -> {
            intervalleCombo.getItems().addAll(intervalle.getLibelle());
            return intervalle;
        }).forEachOrdered((intervalle) -> {
            mapIntervalle.put(intervalle.getLibelle(), intervalle);
        }); 
       
    } 
    else if(matierePremiere.getCategorieMp().getSubCategorieMp().getNom().equals(Constantes.FILM_NORMAL)){
                  
                                     List<Intervalle> listIntervalle=new ArrayList<>();
//               listIntervalle.add(listeprixBox.get(0).getIntervalle());
                 Boolean exist = false;
                 for(int i=0;i<listeprixFilmNormal.size();i++){
                     
                     int Qte =Integer.parseInt(quantiteField.getText());
                     
                     if(Qte>=listeprixFilmNormal.get(i).getIntervalle().getValeurMin() && Qte<=listeprixFilmNormal.get(i).getIntervalle().getValeurMax() )
                     {
                         
                          listIntervalle.add(listeprixFilmNormal.get(i).getIntervalle());
                            exist = true;
                     }
                 }
                   if(exist == false ){
                         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_QTE);
                    }
        
            listIntervalle.stream().map((intervalle) -> {
            intervalleCombo.getItems().addAll(intervalle.getLibelle());
            return intervalle;
        }).forEachOrdered((intervalle) -> {
            mapIntervalle.put(intervalle.getLibelle(), intervalle);
        }); 
              
    }
    }
}
