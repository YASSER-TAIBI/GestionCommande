/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Fournisseur;
import dao.Entity.PrixAdhesif;
import dao.Entity.PrixBox;
import dao.Entity.PrixBoxMetal;
import dao.Entity.PrixCarton;
import dao.Entity.PrixFilm;
import dao.Entity.PrixOulmes;
import dao.Entity.ReferencePromo;
import dao.Manager.FournisseurDAO;
import dao.Manager.PrixAdhesifDAO;
import dao.Manager.PrixBoxDAO;
import dao.Manager.PrixBoxMetalDAO;
import dao.Manager.PrixCartonDAO;
import dao.Manager.PrixFilmDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ReferencePromoDAO;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.PrixAdhesifDAOImpl;
import dao.ManagerImpl.PrixBoxDAOImpl;
import dao.ManagerImpl.PrixBoxMetalDAOImpl;
import dao.ManagerImpl.PrixCartonDAOImpl;
import dao.ManagerImpl.PrixFilmDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ReferencePromoDAOImpl;
import function.navigation;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
public class ConsultationPrixCategorieController implements Initializable {

    @FXML
    private Tab prixBoxConsultation;

    
    @FXML
    private TableView<PrixBox> tablePrixBox;
    @FXML
    private TableColumn<PrixBox, String> CategorieColumn;
    @FXML
    private TableColumn<PrixBox, String> typeCartonColumn;
    @FXML
    private TableColumn<PrixBox, String> grammageColumn;
    @FXML
    private TableColumn<PrixBox, String> dimensionColumn;
    @FXML
    private TableColumn<PrixBox, String> fournisseurColumn;
    @FXML
    private TableColumn<PrixBox, String> intervalleColumn;
    @FXML
    private TableColumn<PrixBox, BigDecimal> prixColumn;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private Button imprimerBtn;
    @FXML
    private Button refrechBtn;
    @FXML
    private Button modifierBtn;
    @FXML
    private Button supprimerBtn;
    
    
    
    @FXML
    private TableView<PrixCarton> tablePrixCarton;
    @FXML
    private TableColumn<PrixCarton, String> CategorieCarColumn;
    @FXML
    private TableColumn<PrixCarton, String> typeCartonCarColumn;
    @FXML
    private TableColumn<PrixCarton, String> dimensionCarColumn;
    @FXML
    private TableColumn<PrixCarton, String> fournisseurCarColumn;
    @FXML
    private TableColumn<PrixCarton, BigDecimal> prixCarColumn;
    @FXML
    private TableColumn<PrixCarton, String> intervalleCarColumn;
    @FXML
    private ComboBox<String> fourCarCombo;
    @FXML
    private Button imprimerCarBtn;
    @FXML
    private Button refrechCarBtn;
    @FXML
    private Button modifierCarBtn;
    @FXML
    private Button supprimerCarBtn;
    
    
    
    @FXML
    private TableView<PrixFilm> tablePrixFilm;
    @FXML
    private TableColumn<PrixFilm, String> CategorieFlmColumn;
    @FXML
    private TableColumn<PrixFilm, String> typeFlmColumn;
    @FXML
    private TableColumn<PrixFilm, String> grammageFlmColumn;
    @FXML
    private TableColumn<PrixFilm, String> fournisseurFlmColumn;
    @FXML
    private TableColumn<PrixFilm, BigDecimal> prixFlmColumn;
    @FXML
    private TableColumn<PrixFilm, String> intervalleFlmColumn;
    @FXML
    private ComboBox<String> fourFilmCombo;
    @FXML
    private Button imprimerFlmBtn;
    @FXML
    private Button refrechFlmBtn;
    @FXML
    private Button modifierFlmBtn;
    @FXML
    private Button supprimerFlmBtn;
    
    
    @FXML
    private AnchorPane consultationPrixFilm;
    @FXML
    private AnchorPane consultationPrixFilm1;
    
    
    @FXML
    private TableView<PrixAdhesif> tablePrixAdhesif;
    @FXML
    private TableColumn<PrixAdhesif, String> CategorieAdhesifColumn;
    @FXML
    private TableColumn<PrixAdhesif, String> dimAdhesifColumn;
    @FXML
    private TableColumn<PrixAdhesif, String> fournisseurAdhesifColumn;
    @FXML
    private TableColumn<PrixAdhesif, BigDecimal> prixAdhesifColumn;
    @FXML
    private ComboBox<String> fourAdhesifCombo;
    @FXML
    private Button imprimerAdhesifBtn;
    @FXML
    private Button refrechAdhesifBtn;
    @FXML
    private Button modifierAdhesifBtn;
    @FXML
    private Button supprimerAdhesifBtn;
    
    
    @FXML
    private TableView<PrixBoxMetal> tablePrixBoxMetal;
    @FXML
    private TableColumn<PrixBoxMetal, String> CategorieBoxMetalColumn;
    @FXML
    private TableColumn<PrixBoxMetal, String> fournisseurBoxMetalColumn;
    @FXML
    private TableColumn<PrixBoxMetal, BigDecimal> prixBoxMetalColumn;
    @FXML
    private ComboBox<String> fourBoxMetalCombo;
    @FXML
    private Button imprimerBoxMetalBtn;
    @FXML
    private Button refrechBoxMetalBtn;
    @FXML
    private Button modifierBoxMetalBtn;
    @FXML
    private Button supprimerBoxMetalBtn;
    
    
    @FXML
    private TableView<PrixOulmes> tablePrixOulmes;
    @FXML
    private TableColumn<PrixOulmes, String> codeOulmesColumn;
    @FXML
    private TableColumn<PrixOulmes, String> libelleOulmesColumn;
    @FXML
    private TableColumn<PrixOulmes, BigDecimal> prixOulmesColumn;
    @FXML
    private TableColumn<PrixOulmes, BigDecimal> conditionnementOulmesColumn;
    @FXML
    private TableColumn<PrixOulmes, BigDecimal> conditionnementCaisseOulmesColumn;
    @FXML
    private TableColumn<PrixOulmes, BigDecimal> remiseAchatOulmesColumn;
    @FXML
    private TableColumn<PrixOulmes, BigDecimal> remiseAvoirOulmesColumn;
    @FXML
    private TableColumn<PrixOulmes, String> fourOulmesColumn;
    @FXML
    private TableColumn<PrixOulmes, String> typeOulmesColumn;
    @FXML
    private TableColumn<PrixOulmes, String> client2OulmesColumn;
    
    @FXML
    private TableColumn<PrixBox, Boolean> actionBoxColumn;
    @FXML
    private TableColumn<PrixCarton, Boolean> actionCarColumn;
    @FXML
    private TableColumn<PrixFilm, Boolean> actionFlmColumn;
    @FXML
    private TableColumn<PrixAdhesif, Boolean> actionAdhesifColumn;
    @FXML
    private TableColumn<PrixBoxMetal, Boolean> actionBoxMetalColumn;
    @FXML
    private TableColumn<PrixOulmes, Boolean> actionOulmesColumn;
    
    @FXML
    private ComboBox<String> fourOulmesCombo;
    @FXML
    private Button imprimerOulmesBtn;
    @FXML
    private Button refrechOulmesBtn;
    @FXML
    private Button modifierOulmesBtn;
    @FXML
    private Button supprimerOulmesBtn;
    
     @FXML
    private RadioButton listeBoxRadio;
    @FXML
    private RadioButton modBoxRadio;
    @FXML
    private RadioButton listeCartRadio;
    @FXML
    private RadioButton modCartRadio;
    @FXML
    private RadioButton listeFilmRadio;
    @FXML
    private RadioButton modFilmRadio;
    @FXML
    private RadioButton listeAdRadio;
    @FXML
    private RadioButton modAdRadio;
    @FXML
    private RadioButton modBmRadio;
    @FXML
    private RadioButton listeBmRadio;
    @FXML
    private RadioButton modPfRadio;
    @FXML
    private RadioButton listePfRadio;
    @FXML
    private ToggleGroup box;
    @FXML
    private ToggleGroup carton;
    @FXML
    private ToggleGroup film;
    @FXML
    private ToggleGroup adhesif;
    @FXML
    private ToggleGroup bMetal;
    @FXML
    private ToggleGroup Pf;
    
    /**
     * Initializes the controller class.
     */
    
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
         FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
          navigation nav = new navigation();
     Fournisseur fournisseur;
         
         
          private final ObservableList<PrixBox> listePrixBox=FXCollections.observableArrayList();
          private final ObservableList<PrixCarton> listePrixCarton=FXCollections.observableArrayList();
          private final ObservableList<PrixFilm> listePrixFilm=FXCollections.observableArrayList();
          private final ObservableList<PrixAdhesif> listePrixAdhesif=FXCollections.observableArrayList();
          private final ObservableList<PrixBoxMetal> listePrixBoxMetal=FXCollections.observableArrayList();
          private final ObservableList<PrixOulmes> listePrixOulmes=FXCollections.observableArrayList();
          
          
          private final ObservableList<PrixBox> listePrixBoxTMP=FXCollections.observableArrayList();
          private final ObservableList<PrixCarton> listePrixCartonTMP=FXCollections.observableArrayList();
          private final ObservableList<PrixFilm> listePrixFilmTMP=FXCollections.observableArrayList();
          private final ObservableList<PrixAdhesif> listePrixAdhesifTMP=FXCollections.observableArrayList();
          private final ObservableList<PrixBoxMetal> listePrixBoxMetalTMP=FXCollections.observableArrayList();
          private final ObservableList<PrixOulmes> listePrixOulmesTMP=FXCollections.observableArrayList();
          
          PrixBoxDAO prixBoxDAO = new PrixBoxDAOImpl();
          PrixCartonDAO prixCartonDAO = new PrixCartonDAOImpl();
          PrixFilmDAO prixFilmDAO = new PrixFilmDAOImpl();
          PrixAdhesifDAO prixAdhesifDAO = new PrixAdhesifDAOImpl();
          PrixBoxMetalDAO prixBoxMetalDAO = new PrixBoxMetalDAOImpl();
          PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
          ReferencePromoDAO referencePromoDAO = new ReferencePromoDAOImpl();
    
   
    
    

          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
 
        
           List<Fournisseur> listFournisseurBox=fournisseurDAO.findAllMp();
        
        listFournisseurBox.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });

           List<Fournisseur> listFournisseurCarton=fournisseurDAO.findAllMp();
        
        listFournisseurCarton.stream().map((fournisseur) -> {
            fourCarCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });

           List<Fournisseur> listFournisseurFilm=fournisseurDAO.findAllMp();
        
        listFournisseurFilm.stream().map((fournisseur) -> {
            fourFilmCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
          List<Fournisseur> listFournisseurAdhesif=fournisseurDAO.findAllMp();
        
        listFournisseurAdhesif.stream().map((fournisseur) -> {
            fourAdhesifCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });

         List<Fournisseur> listFournisseurBoxMetal=fournisseurDAO.findAllMp();
        
        listFournisseurBoxMetal.stream().map((fournisseur) -> {
            fourBoxMetalCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
        List<Fournisseur> listeFournisseurOulmes=fournisseurDAO.findAllPF();
        
        listeFournisseurOulmes.stream().map((fournisseur) -> {
            fourOulmesCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
        setColumnPropertiesPrixBox();
         loadDetailPrixBox();
         
         setColumnPropertiesPrixCarton();
         loadDetailPrixCarton();
         
         setColumnPropertiesPrixFilm();
         loadDetailPrixFilm();
         
         setColumnPropertiesPrixAdhesif();
         loadDetailPrixAdhesif();
        
         setColumnPropertiesPrixBoxMetal();
         loadDetailPrixBoxMetal();
         
         setColumnPropertiesPrixOulmes();
         loadDetailPrixOulmes();
         
         
         imprimerBtn.setDisable(true);
         imprimerAdhesifBtn.setDisable(true);
         imprimerBoxMetalBtn.setDisable(true);
         imprimerCarBtn.setDisable(true);
         imprimerOulmesBtn.setDisable(true);
         imprimerFlmBtn.setDisable(true);
    }    

      void setColumnPropertiesPrixBox(){
        
    
        
          CategorieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixBox , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixBox, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCategorieMp().getNom());
            }

        });
     
         dimensionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixBox , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixBox, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDimension().getLibelle());
            }

        });
         
    
         typeCartonColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixBox , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixBox, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTypeCartonBox().getLibelle());
            }

        });
      
          grammageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixBox , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixBox, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getGrammage().getLibelle());
            }

        });
  
            intervalleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixBox , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixBox, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getIntervalle().getLibelle());
            }

        });
            
        
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
     
                    fournisseurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixBox , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixBox, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFournisseur().getNom());
            }

        });
      
                    
                      actionBoxColumn.cellValueFactoryProperty();
          actionBoxColumn.setCellValueFactory((cellData) -> {
          PrixBox cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionBoxColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionBoxColumn.setEditable(true);
      
          
          tablePrixBox.setEditable(true);
    }
    
    void loadDetailPrixBox(){
        
        listePrixBox.clear();
        listePrixBox.addAll(prixBoxDAO.findAll());
        tablePrixBox.setItems(listePrixBox);
    }
    
    void setColumnPropertiesPrixCarton(){
        
    
        
          CategorieCarColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixCarton , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixCarton, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCategorieMp().getNom());
            }

        });
     
         dimensionCarColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixCarton , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixCarton, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDimension().getLibelle());
            }

        });
         
    
         typeCartonCarColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixCarton , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixCarton, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTypeCarton().getLibelle());
            }

        });
      
          intervalleCarColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixCarton , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixCarton, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getIntervalle().getLibelle());
            }

        });
        
        prixCarColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
     
                    fournisseurCarColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixCarton , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixCarton, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFournisseur().getNom());
            }

        });
      
                    
                        actionCarColumn.cellValueFactoryProperty();
          actionCarColumn.setCellValueFactory((cellData) -> {
          PrixCarton cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionCarColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionCarColumn.setEditable(true);
      
          
          tablePrixCarton.setEditable(true);
    }
    
    void loadDetailPrixCarton(){
        
        listePrixCarton.clear();
        listePrixCarton.addAll(prixCartonDAO.findAll());
        tablePrixCarton.setItems(listePrixCarton);
    }
    
     void setColumnPropertiesPrixFilm(){
        
    
        
          CategorieFlmColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixFilm , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixFilm, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCategorieMp().getNom());
            }

        });
     
          typeFlmColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixFilm , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixFilm, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTypeFilm().getLibelle());
            }

        });
         
    
         grammageFlmColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixFilm , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixFilm, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getGrammageFilm().getLibelle());
            }

        });
      
       
         intervalleFlmColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixFilm , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixFilm, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getIntervalle().getLibelle());
            }

        });
        
         
        prixFlmColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
     
                    fournisseurFlmColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixFilm , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixFilm, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFournisseur().getNom());
            }

        });
      
                                 actionFlmColumn.cellValueFactoryProperty();
          actionFlmColumn.setCellValueFactory((cellData) -> {
          PrixFilm cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionFlmColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionFlmColumn.setEditable(true);
      
          
          tablePrixFilm.setEditable(true); 
                    
    }
    
    void loadDetailPrixFilm(){
        
        listePrixFilm.clear();
        listePrixFilm.addAll(prixFilmDAO.findAll());
        tablePrixFilm.setItems(listePrixFilm);
        
    }
    
    
      void setColumnPropertiesPrixAdhesif(){
        
    
        
          CategorieAdhesifColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixAdhesif , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixAdhesif, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCategorieMp().getNom());
            }

        });
     
         dimAdhesifColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixAdhesif , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixAdhesif, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDimension().getLibelle());
            }

        });
       
        
        prixAdhesifColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
     
         fournisseurAdhesifColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixAdhesif , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixAdhesif, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFournisseur().getNom());
            }

        });
      
            actionAdhesifColumn.cellValueFactoryProperty();
          actionAdhesifColumn.setCellValueFactory((cellData) -> {
          PrixAdhesif cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionAdhesifColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionAdhesifColumn.setEditable(true);
      
          
          tablePrixAdhesif.setEditable(true); 
         
    }
    
    void loadDetailPrixAdhesif(){
        
        listePrixAdhesif.clear();
        listePrixAdhesif.addAll(prixAdhesifDAO.findAll());
        tablePrixAdhesif.setItems(listePrixAdhesif);
    }
    
      void setColumnPropertiesPrixBoxMetal(){
        
         CategorieBoxMetalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixBoxMetal , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixBoxMetal, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCategorieMp().getNom());
            }

        });
     
        
        prixBoxMetalColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
     
         fournisseurBoxMetalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixBoxMetal , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixBoxMetal, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFournisseur().getNom());
            }

        });
      
          actionBoxMetalColumn.cellValueFactoryProperty();
          actionBoxMetalColumn.setCellValueFactory((cellData) -> {
              PrixBoxMetal cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionBoxMetalColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionBoxMetalColumn.setEditable(true);
      
          
          tablePrixBoxMetal.setEditable(true); 
         
    }
    
    void loadDetailPrixBoxMetal(){
        
        listePrixBoxMetal.clear();
        listePrixBoxMetal.addAll(prixBoxMetalDAO.findAll());
        tablePrixBoxMetal.setItems(listePrixBoxMetal);
    }
    
          void setColumnPropertiesPrixOulmes(){
        
    
        
          codeOulmesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixOulmes , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixOulmes, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getProduit().getCode());
            }

        });
     
          libelleOulmesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixOulmes , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixOulmes, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getProduit().getLibelle());
            }

        });
          
          
         typeOulmesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixOulmes , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixOulmes, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTypeArticle());
            }

            
        });
       
        
        prixOulmesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixOulmes , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<PrixOulmes, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPrix());
            }

        });
        
         conditionnementOulmesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixOulmes , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<PrixOulmes, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getConditionnement());
            }

        });
      
         conditionnementCaisseOulmesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixOulmes , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<PrixOulmes, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getConditionnementCaisse());
            }

        });
         
         remiseAchatOulmesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixOulmes , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<PrixOulmes, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRemiseAchat());
            }

        });
         
         remiseAvoirOulmesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixOulmes , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<PrixOulmes, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRemiseAvoir());
            }

        });
         
         fourOulmesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixOulmes , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixOulmes, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFournisseur());
            }

        });
         
         client2OulmesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PrixOulmes , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PrixOulmes, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getClient());
            }

        });
         
          actionOulmesColumn.cellValueFactoryProperty();
          actionOulmesColumn.setCellValueFactory((cellData) -> {
              PrixOulmes cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionOulmesColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionOulmesColumn.setEditable(true);
      
          
          tablePrixOulmes.setEditable(true); 
    }
    
    void loadDetailPrixOulmes(){
        
        listePrixOulmes.clear();
        listePrixOulmes.addAll(prixOulmesDAO.findAll());
        tablePrixOulmes.setItems(listePrixOulmes);
    }
    
    
    @FXML
    private void clickTablePrixBox(MouseEvent event) {
    }

    @FXML
    private void fourComboOnAction(ActionEvent event) {
        if(fourCombo.getSelectionModel().getSelectedIndex()!= -1){    
            listePrixBox.clear();
           Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
           listePrixBox.addAll(prixBoxDAO.findPrixBoxByFour(fournisseur.getId()));
        }
    }

    @FXML
    private void imprimerBtnOnAction(ActionEvent event) {
        
        
        
        
            try {
                
                     listePrixBoxTMP.clear();
            
                for( int rows = 0;rows<tablePrixBox.getItems().size() ;rows++ ){
    
         if (actionBoxColumn.getCellData(rows).booleanValue()==true){
             
              PrixBox prixBox = tablePrixBox.getItems().get(rows);
            
                listePrixBoxTMP.add(prixBox); 
         }
                
                }
                
                
                  if (listePrixBoxTMP.size()!= 0){
                
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationPrixCategorieController.class.getResource(nav.getiReportPrixBox()));

            Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
            
                    if(fournisseur!=null){
                   para.put("Fournisseur",fournisseur.getNom());
                    }else {
                   para.put("Fournisseur","TOUT FOURNISSEUR");
                    }
           
                   if(listeBoxRadio.isSelected()== true){
                     para.put("prixBox","Liste Prix Box"); 
                   }else{
                     para.put("prixBox","Modifier Prix Box"); 
                   }  
                     
                     
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listePrixBoxTMP));
               JasperViewer.viewReport(jp, false);
            
                 }else{
                  
                  nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
                      
                  }

               
        } catch (JRException ex) {
            Logger.getLogger(ConsultationPrixCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            
    }

    @FXML
    private void refrechBtnOnAction(ActionEvent event) {
             loadDetailPrixBox();
          fourCombo.getSelectionModel().select(-1);
          
          listeBoxRadio.setSelected(false);
          modBoxRadio.setSelected(false);
          
          imprimerBtn.setDisable(true);
    }

    @FXML
    private void clickTablePrixCarton(MouseEvent event) {
    }

    @FXML
    private void fourCarComboOnAction(ActionEvent event) {
         if(fourCarCombo.getSelectionModel().getSelectedIndex()!= -1){    
            listePrixCarton.clear();
           Fournisseur fournisseur=mapFournisseur.get(fourCarCombo.getSelectionModel().getSelectedItem());
           listePrixCarton.addAll(prixCartonDAO.findPrixCartonByFour(fournisseur.getId()));
        }
    }

    @FXML
    private void imprimerCarBtnOnAction(ActionEvent event) {
            try {
                
                
                     listePrixCartonTMP.clear();
            
                for( int rows = 0;rows<tablePrixCarton.getItems().size() ;rows++ ){
    
         if (actionCarColumn.getCellData(rows).booleanValue()==true){
             
              PrixCarton prixCarton = tablePrixCarton.getItems().get(rows);
            
                listePrixCartonTMP.add(prixCarton); 
         }
                
                }
                
                
                  if (listePrixCartonTMP.size()!= 0){
                
          HashMap para = new HashMap();
          
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationPrixCategorieController.class.getResource(nav.getiReportCartonBox()));

                Fournisseur fournisseur = mapFournisseur.get(fourCarCombo.getSelectionModel().getSelectedItem());
            
            if(fournisseur!=null){
                
                 para.put("Fournisseur",fournisseur.getNom());
            }else {
                   para.put("Fournisseur","TOUT FOURNISSEUR");
            }

            
               if(listeCartRadio.isSelected()== true){
                     para.put("prixCarton","Liste Prix Carton"); 
                   }else{
                     para.put("prixCarton","Modifier Prix Carton"); 
                   }  
            
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listePrixCartonTMP));
               JasperViewer.viewReport(jp, false);
                  
                  
                  }else{
                  
                  nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
                      
                  }
                  
                  
        } catch (JRException ex) {
            Logger.getLogger(ConsultationPrixCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrechCarBtnOnAction(ActionEvent event) {
             loadDetailPrixCarton();
          fourCarCombo.getSelectionModel().select(-1);
          
           listeCartRadio.setSelected(false);
          modCartRadio.setSelected(false);
          
          imprimerCarBtn.setDisable(true);
    }

    @FXML
    private void clickTablePrixFilm(MouseEvent event) {
    }

    @FXML
    private void fourFilmComboOnAction(ActionEvent event) {
           if(fourFilmCombo.getSelectionModel().getSelectedIndex()!= -1){    
            listePrixFilm.clear();
           Fournisseur fournisseur=mapFournisseur.get(fourFilmCombo.getSelectionModel().getSelectedItem());
           listePrixFilm.addAll(prixFilmDAO.findPrixFilmByFour(fournisseur.getId()));
        }
    }

    @FXML
    private void imprimerFlmBtnOnAction(ActionEvent event) {
        
        
        
              try {
                  
                  
                         listePrixFilmTMP.clear();
            
                for( int rows = 0;rows<tablePrixFilm.getItems().size() ;rows++ ){
    
         if (actionFlmColumn.getCellData(rows).booleanValue()==true){
             
              PrixFilm prixFilm = tablePrixFilm.getItems().get(rows);
            
                listePrixFilmTMP.add(prixFilm); 
         }
                
                }
                
                
                  if (listePrixFilmTMP.size()!= 0){
                  
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationPrixCategorieController.class.getResource(nav.getiReportFilmBox()));

           Fournisseur fournisseur=mapFournisseur.get(fourFilmCombo.getSelectionModel().getSelectedItem());
            
            if(fournisseur!=null){
                 para.put("Fournisseur",fournisseur.getNom());
            }else {
                   para.put("Fournisseur","TOUT FOURNISSEUR");
            }
            
               if(listeFilmRadio.isSelected()== true){
                     para.put("prixFilm","Liste Prix Film"); 
                   }else{
                     para.put("prixFilm","Modifier Prix Film"); 
                   }  
            
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listePrixFilmTMP));
               JasperViewer.viewReport(jp, false);
               
                  }else{
                  
                  nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
                      
                  }
                  
        } catch (JRException ex) {
            Logger.getLogger(ConsultationPrixCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrechFlmBtnOnAction(ActionEvent event) {
            loadDetailPrixFilm();
          fourFilmCombo.getSelectionModel().select(-1);
          
          listeFilmRadio.setSelected(false);
          modFilmRadio.setSelected(false);
          
          imprimerFlmBtn.setDisable(true);
    }

    @FXML
    private void modifierBtnOnAction(ActionEvent event) {
           if (tablePrixBox.getSelectionModel().getSelectedItem() != null) {
              
              PrixBox prixBox= tablePrixBox.getSelectionModel().getSelectedItem();
              
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getModifierPrixBox()));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);
  
                ModifierPrixBoxController modifierPrixBoxController = fXMLLoader.getController();
               
           
                modifierPrixBoxController.prixBox = prixBox;
                modifierPrixBoxController.chargerLesDonnees();
                modifierPrixBoxController.listePrixBoxTMP=listePrixBox;
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
     
                stage.show();
            } catch (IOException ex) {           
                System.err.println("!!!!!!!!" +ex);
            }

        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Errreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
       }
    }

    @FXML
    private void supprimerBtnOnAction(ActionEvent event) {
          if(tablePrixBox.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE );
        
     }else {
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                      PrixBox prixBox =tablePrixBox.getSelectionModel().getSelectedItem();
        
        prixBoxDAO.delete(prixBox);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,  Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnPropertiesPrixBox();
      loadDetailPrixBox();  

            } else if (result.get() == ButtonType.CANCEL) {
                tablePrixBox.refresh();
            }

    }
    }

    @FXML
    private void modifierCarBtnOnAction(ActionEvent event) {
        
           if (tablePrixCarton.getSelectionModel().getSelectedItem() != null) {
              
              PrixCarton prixCarton= tablePrixCarton.getSelectionModel().getSelectedItem();
              
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getModifierPrixCarton()));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);
   
                ModifierPrixCartonController modifierPrixCartonController = fXMLLoader.getController();
               

                modifierPrixCartonController.prixCarton = prixCarton;
                modifierPrixCartonController.chargerLesDonnees();
                modifierPrixCartonController.listePrixCartonTMP=listePrixCarton;
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.show();
            } catch (IOException ex) {

                System.err.println("!!!!!!!!" +ex);
            }

        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Errreur",  Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
       }
    }

    @FXML
    private void supprimerCarBtnOnAction(ActionEvent event) {
          if(tablePrixCarton.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,  Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
        
              
                                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                             PrixCarton prixCarton=tablePrixCarton.getSelectionModel().getSelectedItem();
        
        prixCartonDAO.delete(prixCarton);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null,  Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnPropertiesPrixCarton();
      loadDetailPrixCarton();  

            } else if (result.get() == ButtonType.CANCEL) {
                tablePrixCarton.refresh();
            }

    }
    }

    @FXML
    private void supprimerFlmBtnOnAction(ActionEvent event) {
          if(tablePrixFilm.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
        
                              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                     PrixFilm prixFilm =tablePrixFilm.getSelectionModel().getSelectedItem();
        
        prixFilmDAO.delete(prixFilm);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnPropertiesPrixFilm();
      loadDetailPrixFilm();    
           

            } else if (result.get() == ButtonType.CANCEL) {
                tablePrixFilm.refresh();
            }

    }
    }

    @FXML
    private void modifierFlmBtnOnAction(ActionEvent event) {
           if (tablePrixFilm.getSelectionModel().getSelectedItem() != null) {
              
              PrixFilm prixFilm= tablePrixFilm.getSelectionModel().getSelectedItem();
              
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getModifierPrixFilm()));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);

                ModifierPrixFilmController modifierPrixFilmController = fXMLLoader.getController();
               
               

                modifierPrixFilmController.prixFilm = prixFilm;
                modifierPrixFilmController.chargerLesDonnees();
                modifierPrixFilmController.listePrixFilmTMP=listePrixFilm;
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
           
                stage.show();
            } catch (IOException ex) {
              
                System.err.println("!!!!!!!!" +ex);
            }

        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur",  Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
       }
    }

    @FXML
    private void fourAdhesifComboOnAction(ActionEvent event) {
          if(fourAdhesifCombo.getSelectionModel().getSelectedIndex()!= -1){    
            listePrixAdhesif.clear();
           Fournisseur fournisseur=mapFournisseur.get(fourAdhesifCombo.getSelectionModel().getSelectedItem());
           listePrixAdhesif.addAll(prixAdhesifDAO.findPrixAdhesifByFour(fournisseur.getId()));
        }
    }

    @FXML
    private void imprimerAdhesifBtnOnAction(ActionEvent event) {
        
        
          try {

                    listePrixAdhesifTMP.clear();
            
                for( int rows = 0;rows<tablePrixAdhesif.getItems().size() ;rows++ ){
    
         if (actionAdhesifColumn.getCellData(rows).booleanValue()==true){
             
              PrixAdhesif prixAdhesif = tablePrixAdhesif.getItems().get(rows);
            
                listePrixAdhesifTMP.add(prixAdhesif); 
         }
                
                }
                
                  if (listePrixAdhesifTMP.size()!= 0){
                      
              
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationPrixCategorieController.class.getResource(nav.getiReportAdhesifBox()));

               Fournisseur fournisseur=mapFournisseur.get(fourAdhesifCombo.getSelectionModel().getSelectedItem());
            
            if(fournisseur!=null){
                 para.put("Fournisseur",fournisseur.getNom());
            }else {
                   para.put("Fournisseur","TOUT FOURNISSEUR");
            }
            
            
            if(listeAdRadio.isSelected()== true){
                     para.put("prixAdhesif","Liste Prix Adhesif"); 
                   }else{
                     para.put("prixAdhesif","Modifier Prix Adhesif"); 
                   }  
            
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listePrixAdhesifTMP));
               JasperViewer.viewReport(jp, false);
            
                  }else{
                  
                  nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
                      
                  }
               
        } catch (JRException ex) {
            Logger.getLogger(ConsultationPrixCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierAdhesifBtnOnAction(ActionEvent event) {
         if (tablePrixAdhesif.getSelectionModel().getSelectedItem() != null) {
              
              PrixAdhesif prixAdhesif= tablePrixAdhesif.getSelectionModel().getSelectedItem();
              
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getModifierPrixAdhesif()));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);

                ModifierPrixAdhesifController modifierPrixAdhesifController = fXMLLoader.getController();
               
               

                modifierPrixAdhesifController.prixAdhesif = prixAdhesif;
                modifierPrixAdhesifController.chargerLesDonnees();
                modifierPrixAdhesifController.listePrixAdhesifTMP=listePrixAdhesif;
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
           
                stage.show();
            } catch (IOException ex) {
              
                System.err.println("!!!!!!!!" +ex);
            }

        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
       }
    }

    @FXML
    private void supprimerAdhesifBtnOnAction(ActionEvent event) {
        

           if(tablePrixAdhesif.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
        
                       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

        PrixAdhesif prixAdhesif =tablePrixAdhesif.getSelectionModel().getSelectedItem();
        
        prixAdhesifDAO.delete(prixAdhesif);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnPropertiesPrixAdhesif();
      loadDetailPrixAdhesif();   
           

            } else if (result.get() == ButtonType.CANCEL) {
                 tablePrixAdhesif.refresh();
            }
    }
    }

    @FXML
    private void refrechAdhesifBtnOnAction(ActionEvent event) {
           loadDetailPrixAdhesif();
          fourAdhesifCombo.getSelectionModel().select(-1);
        
           listeAdRadio.setSelected(false);
          modAdRadio.setSelected(false);
          
          imprimerAdhesifBtn.setDisable(true);
          
    }

    @FXML
    private void fourBoxMetalComboOnAction(ActionEvent event) {
           if(fourBoxMetalCombo.getSelectionModel().getSelectedIndex()!= -1){    
            listePrixBoxMetal.clear();
           Fournisseur fournisseur=mapFournisseur.get(fourBoxMetalCombo.getSelectionModel().getSelectedItem());
           listePrixBoxMetal.addAll(prixBoxMetalDAO.findPrixBoxMetalByFour(fournisseur.getId()));
        }
        
    }

    @FXML
    private void imprimerBoxMetalBtnOnAction(ActionEvent event) {
        
        
           try {
         
             listePrixBoxMetalTMP.clear();
            
                for( int rows = 0;rows<tablePrixBoxMetal.getItems().size() ;rows++ ){
    
         if (actionBoxMetalColumn.getCellData(rows).booleanValue()==true){
             
              PrixBoxMetal prixBoxMetal = tablePrixBoxMetal.getItems().get(rows);
            
                listePrixBoxMetalTMP.add(prixBoxMetal); 
         }
                
                }
                
                
                  if (listePrixBoxMetalTMP.size()!= 0){
                      
                      HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationPrixCategorieController.class.getResource(nav.getiReportPrixBoxMetal()));

               Fournisseur fournisseur=mapFournisseur.get(fourBoxMetalCombo.getSelectionModel().getSelectedItem());
            
            if(fournisseur!=null){
                 para.put("Fournisseur",fournisseur.getNom());
            }else {
                   para.put("Fournisseur","TOUT FOURNISSEUR");
            } 
   
             if(listeBmRadio.isSelected()== true){
                     para.put("prixBoxMetal","Liste Prix Box Metal"); 
                   }else{
                     para.put("prixBoxMetal","Modifier Prix Box Metal"); 
                   }  
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listePrixBoxMetalTMP));
               JasperViewer.viewReport(jp, false);
            
                  }else{
                  
                  nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
                      
                  }
        } catch (JRException ex) {
            Logger.getLogger(ConsultationPrixCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void refrechBoxMetalBtnOnAction(ActionEvent event) {
        
          loadDetailPrixBoxMetal();
          fourBoxMetalCombo.getSelectionModel().select(-1);
          
           listeBmRadio.setSelected(false);
          modBmRadio.setSelected(false);
          
          imprimerBoxMetalBtn.setDisable(true);
    }

    @FXML
    private void modifierBoxMetalBtnOnAction(ActionEvent event) {
         if (tablePrixBoxMetal.getSelectionModel().getSelectedItem() != null) {
              
              PrixBoxMetal prixBoxMetal= tablePrixBoxMetal.getSelectionModel().getSelectedItem();
              
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getModifierPrixBoxMetal()));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);

                ModifierPrixBoxMetalController modifierPrixBoxMetalController = fXMLLoader.getController();
               
               

                modifierPrixBoxMetalController.prixBoxMetal = prixBoxMetal;
                modifierPrixBoxMetalController.chargerLesDonnees();
                modifierPrixBoxMetalController.listePrixBoxMetalTMP=listePrixBoxMetal;
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
           
                stage.show();
            } catch (IOException ex) {
              
                System.err.println("!!!!!!!!" +ex);
            }

        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
       }
    }

    @FXML
    private void supprimerBoxMetalBtnOnAction(ActionEvent event) {
        
                   if(tablePrixBoxMetal.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
        
                       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

        PrixBoxMetal prixBoxMetal =tablePrixBoxMetal.getSelectionModel().getSelectedItem();
        
        prixBoxMetalDAO.delete(prixBoxMetal);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnPropertiesPrixBoxMetal();
      loadDetailPrixBoxMetal();   
           

            } else if (result.get() == ButtonType.CANCEL) {
                 tablePrixBoxMetal.refresh();
            }
    }
        
        
    }

    
    
    
    @FXML
    private void fourOulmesComboOnAction(ActionEvent event) {
        
                if(fourOulmesCombo.getSelectionModel().getSelectedIndex()!= -1){    
            listePrixOulmes.clear();
           Fournisseur fournisseur=mapFournisseur.get(fourOulmesCombo.getSelectionModel().getSelectedItem());
           listePrixOulmes.addAll(prixOulmesDAO.findPrixFilmByFour(fournisseur.getNom()));
        }
        
    }

    @FXML
    private void imprimerOulmesBtnOnAction(ActionEvent event) {
        
        
           try {
               
                    listePrixOulmesTMP.clear();
            
                for( int rows = 0;rows<tablePrixOulmes.getItems().size() ;rows++ ){
    
         if (actionOulmesColumn.getCellData(rows).booleanValue()==true){
             
              PrixOulmes prixOulmes = tablePrixOulmes.getItems().get(rows);
            
                listePrixOulmesTMP.add(prixOulmes); 
         }
                
                }
                
                
                  if (listePrixOulmesTMP.size()!= 0){
                      
               
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationPrixCategorieController.class.getResource(nav.getiReportPrixOulmes()));

               Fournisseur fournisseur=mapFournisseur.get(fourOulmesCombo.getSelectionModel().getSelectedItem());
            
            if(fournisseur!=null){
                 para.put("Fournisseur",fournisseur.getNom());
            }else {
                   para.put("Fournisseur","TOUT FOURNISSEUR");
            }
            
                 if(listePfRadio.isSelected()== true){
                     para.put("prixPf","Liste Prix Produit Fini"); 
                   }else{
                     para.put("prixPf","Modifier Prix Produit Fini"); 
                   }  
            
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listePrixOulmesTMP));
               JasperViewer.viewReport(jp, false);
            
                  }else{
                  
                  nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
                      
                  }
               
        } catch (JRException ex) {
            Logger.getLogger(ConsultationPrixCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void refrechOulmesBtnOnAction(ActionEvent event) {
        
               loadDetailPrixOulmes();
          fourOulmesCombo.getSelectionModel().select(-1);
          
           listePfRadio.setSelected(false);
          modPfRadio.setSelected(false);
          
          imprimerOulmesBtn.setDisable(true);
        
    }

    @FXML
    private void modifierOulmesBtnOnAction(ActionEvent event) {
        
                if (tablePrixOulmes.getSelectionModel().getSelectedItem() != null) {
              
              PrixOulmes prixOulmes = tablePrixOulmes.getSelectionModel().getSelectedItem();
              
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getModifierPrixOulmes()));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);

                ModifierPrixOulmesController modifierPrixOulmesController = fXMLLoader.getController();
               

                modifierPrixOulmesController.prixOulmes = prixOulmes;
                modifierPrixOulmesController.chargerLesDonnees();
                modifierPrixOulmesController.listePrixOulmesTMP=listePrixOulmes ;
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
           

                stage.show();
            } catch (IOException ex) {
              
                System.err.println("!!!!!!!!" +ex);
            }

        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
       }
        
    }

    @FXML
    private void supprimerOulmesBtnOnAction(ActionEvent event) {

           if(tablePrixOulmes.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
        
                       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

        PrixOulmes prixOulmes =tablePrixOulmes.getSelectionModel().getSelectedItem();
        
          prixOulmes.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);

            prixOulmesDAO.edit(prixOulmes);
    
            ReferencePromo referencePromo = referencePromoDAO.findByPrixOulmes(prixOulmes.getId());
            
            if(referencePromo!=null){
            
                referencePromo.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);
                        
                referencePromoDAO.edit(referencePromo);
            }
                
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnPropertiesPrixOulmes();
      loadDetailPrixOulmes();   
           

            } else if (result.get() == ButtonType.CANCEL) {
                 tablePrixOulmes.refresh();
            }
    }
      
           
           
           
           
    }

    @FXML
    private void selectionnerToutBoxMouseClicked(MouseEvent event) {
        
               
        ObservableList<PrixBox> list=tablePrixBox.getItems();
        
        for (Iterator<PrixBox> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        tablePrixBox.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutBoxMouseClicked(MouseEvent event) {
        
                 ObservableList<PrixBox> list=tablePrixBox.getItems();
        
        for (Iterator<PrixBox> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }

        tablePrixBox.refresh(); 
        
    }

    @FXML
    private void selectionnerToutCartonMouseClicked(MouseEvent event) {
        
                  
        ObservableList<PrixCarton> list=tablePrixCarton.getItems();
        
        for (Iterator<PrixCarton> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        tablePrixCarton.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutCartonMouseClicked(MouseEvent event) {
        
                         ObservableList<PrixCarton> list=tablePrixCarton.getItems();
        
        for (Iterator<PrixCarton> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }

        tablePrixCarton.refresh(); 
        
    }

    @FXML
    private void selectionnerToutFilmMouseClicked(MouseEvent event) {
        
                  
        ObservableList<PrixFilm> list=tablePrixFilm.getItems();
        
        for (Iterator<PrixFilm> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        tablePrixFilm.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutFilmMouseClicked(MouseEvent event) {
        
                         ObservableList<PrixFilm> list=tablePrixFilm.getItems();
        
        for (Iterator<PrixFilm> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }

        tablePrixFilm.refresh(); 
        
    }

    @FXML
    private void selectionnerToutAdhesifMouseClicked(MouseEvent event) {
        
        
                  
        ObservableList<PrixAdhesif> list=tablePrixAdhesif.getItems();
        
        for (Iterator<PrixAdhesif> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        tablePrixAdhesif.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutAdhesifMouseClicked(MouseEvent event) {
        
                         ObservableList<PrixAdhesif> list=tablePrixAdhesif.getItems();
        
        for (Iterator<PrixAdhesif> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }

        tablePrixAdhesif.refresh(); 
        
        
    }

    @FXML
    private void selectionnerToutBoxMetalMouseClicked(MouseEvent event) {
        
        
                  
        ObservableList<PrixBoxMetal> list=tablePrixBoxMetal.getItems();
        
        for (Iterator<PrixBoxMetal> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        tablePrixBoxMetal.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutBoxMetalMouseClicked(MouseEvent event) {
        
                         ObservableList<PrixBoxMetal> list=tablePrixBoxMetal.getItems();
        
        for (Iterator<PrixBoxMetal> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }

        tablePrixBoxMetal.refresh(); 
        
    }

    @FXML
    private void selectionnerToutPrixOulmesMouseClicked(MouseEvent event) {
        
                  
        ObservableList<PrixOulmes> list=tablePrixOulmes.getItems();
        
        for (Iterator<PrixOulmes> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        tablePrixOulmes.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutPrixOulmesMouseClicked(MouseEvent event) {
        
                         ObservableList<PrixOulmes> list=tablePrixOulmes.getItems();
        
        for (Iterator<PrixOulmes> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }
        
        tablePrixOulmes.refresh(); 
    }

    @FXML
    private void listeBoxRadioOnAction(ActionEvent event) {
        
        imprimerBtn.setDisable(false);
        
    }

    @FXML
    private void modBoxRadioOnAction(ActionEvent event) {
        
        imprimerBtn.setDisable(false);
        
    }

    @FXML
    private void listeCartRadioOnAction(ActionEvent event) {
        
        imprimerCarBtn.setDisable(false);
    }

    @FXML
    private void modCartRadioOnAction(ActionEvent event) {
        
        imprimerCarBtn.setDisable(false);
    }

    @FXML
    private void listeFilmRadioOnAction(ActionEvent event) {
        
        imprimerFlmBtn.setDisable(false);
        
    }

    @FXML
    private void modFilmRadioOnAction(ActionEvent event) {
        
        imprimerFlmBtn.setDisable(false);
        
    }

    @FXML
    private void listeAdRadioOnAction(ActionEvent event) {
        
        imprimerAdhesifBtn.setDisable(false);
        
    }

    @FXML
    private void modAdRadioOnAction(ActionEvent event) {
        
        imprimerAdhesifBtn.setDisable(false);
        
    }

    @FXML
    private void modBmRadioOnAction(ActionEvent event) {
        
        imprimerBoxMetalBtn.setDisable(false);
        
    }

    @FXML
    private void listeBmRadioOnAction(ActionEvent event) {
        
        imprimerBoxMetalBtn.setDisable(false);
        
    }

    @FXML
    private void modPfRadioOnAction(ActionEvent event) {
        
        imprimerOulmesBtn.setDisable(false);
        
    }

    @FXML
    private void listePfRadioOnAction(ActionEvent event) {
        
        imprimerOulmesBtn.setDisable(false);
        
    }
    
}
