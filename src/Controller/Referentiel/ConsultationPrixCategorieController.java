/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Controller.Reglement.ConsultationReglementController;
import Controller.commande.EnvoyerCommandeController;
import Utils.Constantes;
import dao.Entity.DetailCommande;
import dao.Entity.Fournisseur;
import dao.Entity.Magasin;
import dao.Entity.PrixAdhesif;
import dao.Entity.PrixBox;
import dao.Entity.PrixBoxMetal;
import dao.Entity.PrixCarton;
import dao.Entity.PrixFilm;
import dao.Entity.PrixOulmes;
import dao.Manager.FournisseurDAO;
import dao.Manager.PrixAdhesifDAO;
import dao.Manager.PrixBoxDAO;
import dao.Manager.PrixBoxMetalDAO;
import dao.Manager.PrixCartonDAO;
import dao.Manager.PrixFilmDAO;
import dao.Manager.PrixOulmesDAO;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.PrixAdhesifDAOImpl;
import dao.ManagerImpl.PrixBoxDAOImpl;
import dao.ManagerImpl.PrixBoxMetalDAOImpl;
import dao.ManagerImpl.PrixCartonDAOImpl;
import dao.ManagerImpl.PrixFilmDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import function.navigation;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableColumn<PrixOulmes, String> typeOulmesColumn;
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
          
          PrixBoxDAO prixBoxDAO = new PrixBoxDAOImpl();
          PrixCartonDAO prixCartonDAO = new PrixCartonDAOImpl();
          PrixFilmDAO prixFilmDAO = new PrixFilmDAOImpl();
          PrixAdhesifDAO prixAdhesifDAO = new PrixAdhesifDAOImpl();
          PrixBoxMetalDAO prixBoxMetalDAO = new PrixBoxMetalDAOImpl();
          PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    

          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
 
        
           List<Fournisseur> listFournisseurBox=fournisseurDAO.findAll();
        
        listFournisseurBox.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });

           List<Fournisseur> listFournisseurCarton=fournisseurDAO.findAll();
        
        listFournisseurCarton.stream().map((fournisseur) -> {
            fourCarCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });

           List<Fournisseur> listFournisseurFilm=fournisseurDAO.findAll();
        
        listFournisseurFilm.stream().map((fournisseur) -> {
            fourFilmCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
          List<Fournisseur> listFournisseurAdhesif=fournisseurDAO.findAll();
        
        listFournisseurAdhesif.stream().map((fournisseur) -> {
            fourAdhesifCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });

         List<Fournisseur> listFournisseurBoxMetal=fournisseurDAO.findAll();
        
        listFournisseurBoxMetal.stream().map((fournisseur) -> {
            fourBoxMetalCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
        List<Fournisseur> listeFournisseurOulmes=fournisseurDAO.findAll();
        
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
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationPrixCategorieController.class.getResource(nav.getiReportPrixBox()));

            Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
            
                     if(fournisseur!=null){
                 para.put("Fournisseur",fournisseur.getNom());
            }else {
                   para.put("Fournisseur","TOUT FOURNISSEUR");
            }
            
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(tablePrixBox.getItems()));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationPrixCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrechBtnOnAction(ActionEvent event) {
             loadDetailPrixBox();
          fourCombo.getSelectionModel().select(-1);
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
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationPrixCategorieController.class.getResource(nav.getiReportCartonBox()));

                Fournisseur fournisseur = mapFournisseur.get(fourCarCombo.getSelectionModel().getSelectedItem());
            
            if(fournisseur!=null){
                 para.put("Fournisseur",fournisseur.getNom());
            }else {
                   para.put("Fournisseur","TOUT FOURNISSEUR");
            }

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(tablePrixCarton.getItems()));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationPrixCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrechCarBtnOnAction(ActionEvent event) {
             loadDetailPrixCarton();
          fourCarCombo.getSelectionModel().select(-1);
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
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationPrixCategorieController.class.getResource(nav.getiReportFilmBox()));

           Fournisseur fournisseur=mapFournisseur.get(fourFilmCombo.getSelectionModel().getSelectedItem());
            
            if(fournisseur!=null){
                 para.put("Fournisseur",fournisseur.getNom());
            }else {
                   para.put("Fournisseur","TOUT FOURNISSEUR");
            }
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(tablePrixFilm.getItems()));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationPrixCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrechFlmBtnOnAction(ActionEvent event) {
            loadDetailPrixFilm();
          fourFilmCombo.getSelectionModel().select(-1);
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
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationPrixCategorieController.class.getResource(nav.getiReportAdhesifBox()));

               Fournisseur fournisseur=mapFournisseur.get(fourAdhesifCombo.getSelectionModel().getSelectedItem());
            
            if(fournisseur!=null){
                 para.put("Fournisseur",fournisseur.getNom());
            }else {
                   para.put("Fournisseur","TOUT FOURNISSEUR");
            }
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(tablePrixAdhesif.getItems()));
               JasperViewer.viewReport(jp, false);
            
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
    }

    @FXML
    private void refrechBoxMetalBtnOnAction(ActionEvent event) {
        
          loadDetailPrixBoxMetal();
          fourBoxMetalCombo.getSelectionModel().select(-1);
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
    }

    @FXML
    private void refrechOulmesBtnOnAction(ActionEvent event) {
        
               loadDetailPrixOulmes();
          fourOulmesCombo.getSelectionModel().select(-1);
        
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
               
//                typeVenteMixteController.detailTournee = detailTournee;
//                typeVenteMixteController.chargerLesDonnees();
//                typeVenteMixteController.listeDetailTourneeTMP = listeDetailTournee;
//                typeVenteMixteController.loadDetail();

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
        
        prixOulmesDAO.delete(prixOulmes);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnPropertiesPrixOulmes();
      loadDetailPrixOulmes();   
           

            } else if (result.get() == ButtonType.CANCEL) {
                 tablePrixOulmes.refresh();
            }
    }
        
    }
    
}
