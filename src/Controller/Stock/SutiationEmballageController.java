/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Controller.Reglement.SutiationGlobalAvoirController;
import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.Fournisseur;
import dao.Entity.PrixOulmes;
import dao.Entity.SituationEmballage;
import dao.Entity.SituationEmballageMois;
import dao.Entity.SituationGlobalAvoir;
import dao.Manager.ClientMPDAO;
import dao.Manager.DetailAvoirOulmesDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.InitialEmballageDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.DetailAvoirOulmesDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.InitialEmballageDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class SutiationEmballageController implements Initializable {

    @FXML
    private TableView<SituationEmballage> tableSituationEmballageArt;
    @FXML
    private TableColumn<SituationEmballage, String> codeColumn;
    @FXML
    private TableColumn<SituationEmballage, String> libelleColumn;
    @FXML
    private TableColumn<SituationEmballage, BigDecimal> initialColumn;
    @FXML
    private TableColumn<SituationEmballage, BigDecimal> achatColumn;
    @FXML
    private TableColumn<SituationEmballage, BigDecimal> avoirColumn;
    @FXML
    private TableColumn<SituationEmballage, BigDecimal> finalColumn;
    @FXML
    private TableColumn<SituationEmballage, BigDecimal> prixColumn;
    @FXML
    private TableColumn<SituationEmballage, BigDecimal> montantColumn;
    
    
    @FXML
    private Button imprimerBtn;
    @FXML
    private RadioButton listeAvoirRadio;
    @FXML
    private ToggleGroup avoir;
    @FXML
    private RadioButton detailEmballageRadio;
    @FXML
    private Button refrachBtn;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private Label monTotal;
    @FXML
    private Label monTotalMois;
    
    @FXML
    private TableView<SituationEmballageMois> tableSituationEmballageMois;
    @FXML
    private TableColumn<SituationEmballageMois, Integer> moisColumn;
    @FXML
    private TableColumn<SituationEmballageMois, BigDecimal> initialMoisColumn;
    @FXML
    private TableColumn<SituationEmballageMois, BigDecimal> achatMoisColumn;
    @FXML
    private TableColumn<SituationEmballageMois, BigDecimal> avoirMoisColumn;
    @FXML
    private TableColumn<SituationEmballageMois, BigDecimal> finalMoisColumn;
    @FXML
    private TableColumn<SituationEmballageMois, BigDecimal> prixMoisColumn;
    @FXML
    private TableColumn<SituationEmballageMois, BigDecimal> montantMoisColumn;

    
    DetailAvoirOulmesDAO detailAvoirOulmesDAO = new DetailAvoirOulmesDAOImpl();
    DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
    InitialEmballageDAO initialEmballageDAO = new InitialEmballageDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    
       navigation nav = new navigation();
    
      private final ObservableList<SituationEmballage> listSituationEmballage=FXCollections.observableArrayList();
      private final ObservableList<SituationEmballageMois> listSituationEmballageMois=FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDetail();
        setColumnProperties();
        
        
        
         List<Fournisseur> listFournisseur=fournisseurDAO.findAllPF();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
         List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((clientMP) -> {
            clientCombo.getItems().addAll(clientMP.getNom());
            return clientMP;
        }).forEachOrdered((clientMP) -> {
            mapClientMP.put(clientMP.getNom(), clientMP);
        });
        

        imprimerBtn.setDisable(true);
    }    

       void setColumnProperties() {

        codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballage, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationEmballage, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
            }

        });
        
        libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballage, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SituationEmballage, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
            }

        });
        
        initialColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballage, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationEmballage, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getInitial());
            }

        });
        
        achatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballage, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationEmballage, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getAchat());
            }

        });
        
        avoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballage, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationEmballage, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getAvoir());
            }

        });
        
        finalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballage, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationEmballage, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCalculeSfinal());
            }

        });
        
        prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballage, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationEmballage, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPrix());
            }

        });     
        
          montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballage, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationEmballage, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCalculeMontant());
            }

        });      

 

    }
    
       
       
       
       void setColumnPropertiesMois() {

        moisColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballageMois, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<SituationEmballageMois, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMois());
            }

        });

        initialMoisColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballageMois, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationEmballageMois, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getInitial());
            }

        });
        
        achatMoisColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballageMois, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationEmballageMois, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getAchat());
            }

        });
        
        avoirMoisColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballageMois, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationEmballageMois, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getAvoir());
            }

        });
        
        finalMoisColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballageMois, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationEmballageMois, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCalculeSfinal());
            }

        });
        
        prixMoisColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballageMois, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationEmballageMois, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPrix());
            }

        });     
        
          montantMoisColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationEmballageMois, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationEmballageMois, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCalculeMontant());
            }

        });      

 

    }
       
    void loadDetail(){
    

      
        List <Object[]> listObjectInitialEmballage =initialEmballageDAO.findBySituationEmballage();

        List <Object[]> listObjectDetailBonLivraison =detailBonLivraisonDAO.findByAchatEmballage();

        List <Object[]> listObjectDetailAvoir =detailAvoirOulmesDAO.findByAvoirEmballage();

        
             listSituationEmballage.clear();
         
            for(int i=0; i<listObjectInitialEmballage.size(); i++) {
                
                    Object[] object=listObjectInitialEmballage.get(i);
                   
                    PrixOulmes prixOulmes =(PrixOulmes)object[0];
                    BigDecimal qteEmb = (BigDecimal)object[1]; 


                   SituationEmballage situationEmballage = new SituationEmballage();
  
  
                  situationEmballage.setInitial(qteEmb);
                  situationEmballage.setPrixOulmes(prixOulmes);
                  situationEmballage.setPrix(prixOulmes.getPrix());
                  situationEmballage.setAchat(BigDecimal.ZERO);
                  situationEmballage.setAvoir(BigDecimal.ZERO);
 
                  listSituationEmballage.add(situationEmballage);

            }
            
     for(int i=0; i<listObjectDetailBonLivraison.size(); i++) {
                
                    Object[] object=listObjectDetailBonLivraison.get(i);
                   
                    PrixOulmes prixOulmes =(PrixOulmes)object[0];
                    BigDecimal qteEmb = (BigDecimal)object[1]; 

                    Boolean exist= false;
      
                    for (int j = 0; j <listSituationEmballage.size() ; j++) {
                    
                   SituationEmballage situationEmballage = listSituationEmballage.get(j);

                       if(prixOulmes.getId()==situationEmballage.getPrixOulmes().getId()){

                  situationEmballage.setAchat(qteEmb);

                  exist = true;

                  listSituationEmballage.set(j,situationEmballage); 
  
                       
                   }
                }
                    
                if( exist == false){
                
                   SituationEmballage situationEmballage = new SituationEmballage();
  
  
                  situationEmballage.setInitial(BigDecimal.ZERO);
                  situationEmballage.setPrixOulmes(prixOulmes);
                  situationEmballage.setPrix(prixOulmes.getPrix());
                  situationEmballage.setAchat(qteEmb);
                  situationEmballage.setAvoir(BigDecimal.ZERO);

                  listSituationEmballage.add(situationEmballage);
                
                
                }    
                    
            }
     
        for(int i=0; i<listObjectDetailAvoir.size(); i++) {
                
                    Object[] object=listObjectDetailAvoir.get(i);
                   
                    PrixOulmes prixOulmes =(PrixOulmes)object[0];
                    BigDecimal qteEmb = (BigDecimal)object[1]; 


                     Boolean exist= false;
                    
                    for (int j = 0; j <listSituationEmballage.size() ; j++) {
                    
                   SituationEmballage situationEmballage = listSituationEmballage.get(j);
 
  
                       if(prixOulmes.getId()==situationEmballage.getPrixOulmes().getId()){

                  situationEmballage.setAvoir(qteEmb);

                   exist= true;
                  
                  listSituationEmballage.set(j,situationEmballage); 
  
                   }
                }
                    if( exist == false){
                
                   SituationEmballage situationEmballage = new SituationEmballage();
  
  
                  situationEmballage.setInitial(BigDecimal.ZERO);
                  situationEmballage.setPrixOulmes(prixOulmes);
                  situationEmballage.setPrix(prixOulmes.getPrix());
                  situationEmballage.setAchat(BigDecimal.ZERO);
                  situationEmballage.setAvoir(qteEmb);
 
                  listSituationEmballage.add(situationEmballage);
                
                
                }   

            }
        
                  tableSituationEmballageArt.setItems(listSituationEmballage);
                  calculeMontantEmb();
                  
    }
    
    
    void calculeMontantEmb(){
    
              BigDecimal montantTotal = BigDecimal.ZERO;
        
           for (int j = 0; j <listSituationEmballage.size() ; j++) {
                    
                   SituationEmballage situationEmballage = listSituationEmballage.get(j);
 
                  montantTotal=montantTotal.add(situationEmballage.getCalculeMontant());
                   
           }
            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
            monTotal.setText(df.format(montantTotal));
    
    }
    
      void calculeMontantEmbMois(){
    
              BigDecimal montantTotal = BigDecimal.ZERO;
        
           for (int j = 0; j <listSituationEmballageMois.size() ; j++) {
                    
                   SituationEmballageMois situationEmballageMois = listSituationEmballageMois.get(j);
 
                  montantTotal=montantTotal.add(situationEmballageMois.getCalculeMontant());
                   
           }
            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
            monTotalMois.setText(df.format(montantTotal));
    
    }
    
    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        
        
             if (tableSituationEmballageArt.getSelectionModel().getSelectedIndex()!=-1){
        
                   SituationEmballage situationEmballage = tableSituationEmballageArt.getSelectionModel().getSelectedItem();
                 
                 if((fourCombo.getSelectionModel().isEmpty() || clientCombo.getSelectionModel().isEmpty())){
                 
        List <Object[]> listObjectInitialEmballage =initialEmballageDAO.findBySituationEmballageAndMois(situationEmballage.getPrixOulmes().getId());

        List <Object[]> listObjectDetailBonLivraison =detailBonLivraisonDAO.findByAchatEmballageAndMois(situationEmballage.getPrixOulmes().getId());

        List <Object[]> listObjectDetailAvoir =detailAvoirOulmesDAO.findByAvoirEmballageAndMois(situationEmballage.getPrixOulmes().getId());

        
             listSituationEmballageMois.clear();
         
            for(int i=0; i<listObjectInitialEmballage.size(); i++) {
                
                    Object[] object=listObjectInitialEmballage.get(i);
                   
                    Integer mois =(Integer)object[0];
                    PrixOulmes prixOulmes =(PrixOulmes)object[1];
                    BigDecimal qteEmb = (BigDecimal)object[2]; 


                   SituationEmballageMois situationEmballageMois = new SituationEmballageMois();
  
  
                  situationEmballageMois.setInitial(qteEmb);
                  situationEmballageMois.setMois(mois);
                  situationEmballageMois.setPrix(prixOulmes.getPrix());
                  situationEmballageMois.setAchat(BigDecimal.ZERO);
                  situationEmballageMois.setAvoir(BigDecimal.ZERO);
 
                  listSituationEmballageMois.add(situationEmballageMois);

            }
            
     for(int i=0; i<listObjectDetailBonLivraison.size(); i++) {
                
                    Object[] object=listObjectDetailBonLivraison.get(i);
                   
                    Integer mois =(Integer)object[0];
                    PrixOulmes prixOulmes =(PrixOulmes)object[1];
                    BigDecimal qteEmb = (BigDecimal)object[2]; 

                    Boolean exist= false;
      
                    for (int j = 0; j <listSituationEmballageMois.size() ; j++) {
                    
                   SituationEmballageMois situationEmballageMois = listSituationEmballageMois.get(j);

                       if(mois==situationEmballageMois.getMois()){

                  situationEmballageMois.setAchat(qteEmb);

                  exist = true;

                  listSituationEmballageMois.set(j,situationEmballageMois); 
  
                       
                   }
                }
                    
                if( exist == false){
                
                   SituationEmballageMois situationEmballageMois = new SituationEmballageMois();
  

                  situationEmballageMois.setInitial(BigDecimal.ZERO);
                  situationEmballageMois.setMois(mois);
                  situationEmballageMois.setPrix(prixOulmes.getPrix());
                  situationEmballageMois.setAchat(qteEmb);
                  situationEmballageMois.setAvoir(BigDecimal.ZERO);

                  listSituationEmballageMois.add(situationEmballageMois);

                
                }    
                    
            }
     
        for(int i=0; i<listObjectDetailAvoir.size(); i++) {
                
                    Object[] object=listObjectDetailAvoir.get(i);
                   
                    Integer mois =(Integer)object[0];
                    PrixOulmes prixOulmes =(PrixOulmes)object[1];
                    BigDecimal qteEmb = (BigDecimal)object[2]; 


                     Boolean exist= false;
                    
                    for (int j = 0; j <listSituationEmballageMois.size() ; j++) {
                    
                   SituationEmballageMois situationEmballageMois = listSituationEmballageMois.get(j);
 
  
                       if(mois==situationEmballageMois.getMois()){

                  situationEmballageMois.setAvoir(qteEmb);

                   exist= true;
                  
                  listSituationEmballageMois.set(j,situationEmballageMois); 
  
                   }
                }
                    if( exist == false){
                
                   SituationEmballageMois situationEmballageMois = new SituationEmballageMois();
  
  
                  situationEmballageMois.setInitial(BigDecimal.ZERO);
                  situationEmballageMois.setMois(mois);
                  situationEmballageMois.setPrix(prixOulmes.getPrix());
                  situationEmballageMois.setAchat(BigDecimal.ZERO);
                  situationEmballageMois.setAvoir(qteEmb);
 
                  listSituationEmballageMois.add(situationEmballageMois);
                
                
                }   

            }
        
         for (int j = 0; j <listSituationEmballageMois.size() ; j++) {
                    
                   SituationEmballageMois situationEmballageMois = listSituationEmballageMois.get(j);
 
                   if(j!=0){
                   
                       situationEmballageMois.setInitial(listSituationEmballageMois.get(j-1).getCalculeSfinal());
                       
                       listSituationEmballageMois.set(j, situationEmballageMois);
                   
                   }
                   
         }
                    
                  tableSituationEmballageMois.setItems(listSituationEmballageMois);
                  setColumnPropertiesMois();
                  calculeMontantEmbMois();
                  
                   imprimerBtn.setDisable(true);
                   
                  listeAvoirRadio.setSelected(false);
                  detailEmballageRadio.setSelected(false);
                  
             } else if(!fourCombo.getSelectionModel().isEmpty() && !clientCombo.getSelectionModel().isEmpty()){
                 
              Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());  
              ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());  

              
        List <Object[]> listObjectInitialEmballage =initialEmballageDAO.findBySituationEmballageAndMoisAndClientAndFour(situationEmballage.getPrixOulmes().getId(),clientMP.getNom(),fournisseur.getNom());

        List <Object[]> listObjectDetailBonLivraison =detailBonLivraisonDAO.findByAchatEmballageAndMoisAndClientAndFour(situationEmballage.getPrixOulmes().getId(),clientMP.getNom(),fournisseur.getNom());

        List <Object[]> listObjectDetailAvoir =detailAvoirOulmesDAO.findByAvoirEmballageAndMoisAndClientAndFour(situationEmballage.getPrixOulmes().getId(),clientMP.getNom(),fournisseur.getNom());

        
             listSituationEmballageMois.clear();
         
            for(int i=0; i<listObjectInitialEmballage.size(); i++) {
                
                    Object[] object=listObjectInitialEmballage.get(i);
                   
                    Integer mois =(Integer)object[0];
                    PrixOulmes prixOulmes =(PrixOulmes)object[1];
                    BigDecimal qteEmb = (BigDecimal)object[2]; 


                   SituationEmballageMois situationEmballageMois = new SituationEmballageMois();
  
  
                  situationEmballageMois.setInitial(qteEmb);
                  situationEmballageMois.setMois(mois);
                  situationEmballageMois.setPrix(prixOulmes.getPrix());
                  situationEmballageMois.setAchat(BigDecimal.ZERO);
                  situationEmballageMois.setAvoir(BigDecimal.ZERO);
 
                  listSituationEmballageMois.add(situationEmballageMois);

            }
            
     for(int i=0; i<listObjectDetailBonLivraison.size(); i++) {
                
                    Object[] object=listObjectDetailBonLivraison.get(i);
                   
                    Integer mois =(Integer)object[0];
                    PrixOulmes prixOulmes =(PrixOulmes)object[1];
                    BigDecimal qteEmb = (BigDecimal)object[2]; 

                    Boolean exist= false;
      
                    for (int j = 0; j <listSituationEmballageMois.size() ; j++) {
                    
                   SituationEmballageMois situationEmballageMois = listSituationEmballageMois.get(j);

                       if(mois==situationEmballageMois.getMois()){

                  situationEmballageMois.setAchat(qteEmb);

                  exist = true;

                  listSituationEmballageMois.set(j,situationEmballageMois); 
  
                       
                   }
                }
                    
                if( exist == false){
                
                   SituationEmballageMois situationEmballageMois = new SituationEmballageMois();
  

                  situationEmballageMois.setInitial(BigDecimal.ZERO);
                  situationEmballageMois.setMois(mois);
                  situationEmballageMois.setPrix(prixOulmes.getPrix());
                  situationEmballageMois.setAchat(qteEmb);
                  situationEmballageMois.setAvoir(BigDecimal.ZERO);

                  listSituationEmballageMois.add(situationEmballageMois);

                
                }    
                    
            }
     
        for(int i=0; i<listObjectDetailAvoir.size(); i++) {
                
                    Object[] object=listObjectDetailAvoir.get(i);
                   
                    Integer mois =(Integer)object[0];
                    PrixOulmes prixOulmes =(PrixOulmes)object[1];
                    BigDecimal qteEmb = (BigDecimal)object[2]; 


                     Boolean exist= false;
                    
                    for (int j = 0; j <listSituationEmballageMois.size() ; j++) {
                    
                   SituationEmballageMois situationEmballageMois = listSituationEmballageMois.get(j);
 
  
                       if(mois==situationEmballageMois.getMois()){

                  situationEmballageMois.setAvoir(qteEmb);

                   exist= true;
                  
                  listSituationEmballageMois.set(j,situationEmballageMois); 
  
                   }
                }
                    if( exist == false){
                
                   SituationEmballageMois situationEmballageMois = new SituationEmballageMois();
  
  
                  situationEmballageMois.setInitial(BigDecimal.ZERO);
                  situationEmballageMois.setMois(mois);
                  situationEmballageMois.setPrix(prixOulmes.getPrix());
                  situationEmballageMois.setAchat(BigDecimal.ZERO);
                  situationEmballageMois.setAvoir(qteEmb);
 
                  listSituationEmballageMois.add(situationEmballageMois);
                
                
                }   

            }
        
         for (int j = 0; j <listSituationEmballageMois.size() ; j++) {
                    
                   SituationEmballageMois situationEmballageMois = listSituationEmballageMois.get(j);
 
                   if(j!=0){
                   
                       situationEmballageMois.setInitial(listSituationEmballageMois.get(j-1).getCalculeSfinal());
                       
                       listSituationEmballageMois.set(j, situationEmballageMois);
                   
                   }
                   
         }
                    
                  tableSituationEmballageMois.setItems(listSituationEmballageMois);
                  setColumnPropertiesMois();
                  calculeMontantEmbMois();
                 
                   imprimerBtn.setDisable(true);
                   
                  listeAvoirRadio.setSelected(false);
                  detailEmballageRadio.setSelected(false);
             }
    }
             
             
             
    }
    @FXML
    private void imprimerBtnOnAction(ActionEvent event) throws JRException {
        
          
         try {
               
               if(listeAvoirRadio.isSelected()== true){
                      
               
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SutiationEmballageController.class.getResource(nav.getiReportSituationGlobalEmballage()));

              if((fourCombo.getSelectionModel().isEmpty() || clientCombo.getSelectionModel().isEmpty())){
            
                para.put("client","Sans Client");
                para.put("fournisseur","Sans Fournisseur");
                
              } else if(!fourCombo.getSelectionModel().isEmpty() && !clientCombo.getSelectionModel().isEmpty()){
              
                  Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());  
                  ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());  
                  
                para.put("client",clientMP.getNom());
                para.put("fournisseur",fournisseur.getNom());
              
              }
              
              para.put("montantTotal",monTotal.getText());
              
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listSituationEmballage));
               JasperViewer.viewReport(jp, false);
            
                  }else if(detailEmballageRadio.isSelected()== true)  {
                  
                       SituationEmballage situationEmballage = tableSituationEmballageArt.getSelectionModel().getSelectedItem();
                      
                      if (situationEmballage !=null){
                          
           HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SutiationEmballageController.class.getResource(nav.getiReportSituationDetailEmballageMois()));
                          

            if((fourCombo.getSelectionModel().isEmpty() || clientCombo.getSelectionModel().isEmpty())){
            
                para.put("client","Sans Client");
                para.put("fournisseur","Sans Fournisseur");
                
              } else if(!fourCombo.getSelectionModel().isEmpty() && !clientCombo.getSelectionModel().isEmpty()){
              
                  Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());  
                  ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());  
                  
                para.put("client",clientMP.getNom());
                para.put("fournisseur",fournisseur.getNom());
              
              }
            
                para.put("emballage",situationEmballage.getPrixOulmes().getProduit().getLibelle());
                para.put("initial",situationEmballage.getInitial());
                para.put("achat",situationEmballage.getAchat());
                para.put("avoir",situationEmballage.getAvoir());
                para.put("prix",situationEmballage.getPrix());    
                para.put("montant",situationEmballage.getCalculeMontant());   
                para.put("montantTotal",monTotalMois.getText());
                        
                
                           JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listSituationEmballageMois));
               JasperViewer.viewReport(jp, false);
               
               
                      }else{
                            nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
                            return;
                      }
        
                  }
               
        } catch (JRException ex) {
            Logger.getLogger(SutiationEmballageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    @FXML
    private void listeAvoirRadioOnAction(ActionEvent event) {
          imprimerBtn.setDisable(false);
    }

    @FXML
    private void detailEmballageRadioOnAction(ActionEvent event) {
          imprimerBtn.setDisable(false);
    }

    @FXML
    private void refrechBtnOnAction(ActionEvent event) { 
        
        loadDetail();
        setColumnProperties();
        calculeMontantEmb();
        
        clientCombo.getSelectionModel().clearSelection();
        fourCombo.getSelectionModel().clearSelection();
        
        listSituationEmballageMois.clear();
        monTotalMois.setText("");
        
        imprimerBtn.setDisable(true);
        
        listeAvoirRadio.setSelected(false);
        detailEmballageRadio.setSelected(false);
    }

    @FXML
    private void rechercheMouseClicked(MouseEvent event) {
        
        if (fourCombo.getSelectionModel().isEmpty() || 
            clientCombo.getSelectionModel().isEmpty()){

                 nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.VERIFIER_FOURNISSEUR_CLIENT);
            
         }else{
            
              Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());  
              ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());  
            
        List <Object[]> listObjectInitialEmballage =initialEmballageDAO.findBySituationEmballageAndClientAndFour(clientMP.getNom(), fournisseur.getNom());

        List <Object[]> listObjectDetailBonLivraison =detailBonLivraisonDAO.findByAchatEmballageAndClientAndFour(clientMP.getNom(), fournisseur.getNom());

        List <Object[]> listObjectDetailAvoir =detailAvoirOulmesDAO.findByAvoirEmballageAndClientAndFour(clientMP.getNom(), fournisseur.getNom());

        
             listSituationEmballage.clear();
         
            for(int i=0; i<listObjectInitialEmballage.size(); i++) {
                
                    Object[] object=listObjectInitialEmballage.get(i);
                   
                    PrixOulmes prixOulmes =(PrixOulmes)object[0];
                    BigDecimal qteEmb = (BigDecimal)object[1]; 


                   SituationEmballage situationEmballage = new SituationEmballage();
  
  
                  situationEmballage.setInitial(qteEmb);
                  situationEmballage.setPrixOulmes(prixOulmes);
                  situationEmballage.setPrix(prixOulmes.getPrix());
                  situationEmballage.setAchat(BigDecimal.ZERO);
                  situationEmballage.setAvoir(BigDecimal.ZERO);
 
                  listSituationEmballage.add(situationEmballage);

            }
            
     for(int i=0; i<listObjectDetailBonLivraison.size(); i++) {
                
                    Object[] object=listObjectDetailBonLivraison.get(i);
                   
                    PrixOulmes prixOulmes =(PrixOulmes)object[0];
                    BigDecimal qteEmb = (BigDecimal)object[1]; 

                    Boolean exist= false;
      
                    for (int j = 0; j <listSituationEmballage.size() ; j++) {
                    
                   SituationEmballage situationEmballage = listSituationEmballage.get(j);

                       if(prixOulmes.getId()==situationEmballage.getPrixOulmes().getId()){

                  situationEmballage.setAchat(qteEmb);

                  exist = true;

                  listSituationEmballage.set(j,situationEmballage); 
  
                       
                   }
                }
                    
                if( exist == false){
                
                   SituationEmballage situationEmballage = new SituationEmballage();
  
  
                  situationEmballage.setInitial(BigDecimal.ZERO);
                  situationEmballage.setPrixOulmes(prixOulmes);
                  situationEmballage.setPrix(prixOulmes.getPrix());
                  situationEmballage.setAchat(qteEmb);
                  situationEmballage.setAvoir(BigDecimal.ZERO);

                  listSituationEmballage.add(situationEmballage);
                
                
                }    
                    
            }
     
        for(int i=0; i<listObjectDetailAvoir.size(); i++) {
                
                    Object[] object=listObjectDetailAvoir.get(i);
                   
                    PrixOulmes prixOulmes =(PrixOulmes)object[0];
                    BigDecimal qteEmb = (BigDecimal)object[1]; 


                     Boolean exist= false;
                    
                    for (int j = 0; j <listSituationEmballage.size() ; j++) {
                    
                   SituationEmballage situationEmballage = listSituationEmballage.get(j);
 
  
                       if(prixOulmes.getId()==situationEmballage.getPrixOulmes().getId()){

                  situationEmballage.setAvoir(qteEmb);

                   exist= true;
                  
                  listSituationEmballage.set(j,situationEmballage); 
  
                   }
                }
                    if( exist == false){
                
                   SituationEmballage situationEmballage = new SituationEmballage();
  
  
                  situationEmballage.setInitial(BigDecimal.ZERO);
                  situationEmballage.setPrixOulmes(prixOulmes);
                  situationEmballage.setPrix(prixOulmes.getPrix());
                  situationEmballage.setAchat(BigDecimal.ZERO);
                  situationEmballage.setAvoir(qteEmb);
 
                  listSituationEmballage.add(situationEmballage);
                
                
                }   

            }
        
                  tableSituationEmballageArt.setItems(listSituationEmballage);
                  calculeMontantEmb();

                  imprimerBtn.setDisable(true);
                  
                  listeAvoirRadio.setSelected(false);
                  detailEmballageRadio.setSelected(false);
                  
                  listSituationEmballageMois.clear();
                  monTotalMois.setText("");
              }
        
    }

    @FXML
    private void fourCombOnAction(ActionEvent event) {
    }

    @FXML
    private void clientCombOnAction(ActionEvent event) {
    }
    
}
