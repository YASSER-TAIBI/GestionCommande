/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import dao.Entity.CategorieMp;
import dao.Entity.Fournisseur;
import dao.Entity.HistoriquePrix;
import dao.Entity.HistoriquePrixGlobal;
import dao.Entity.SubCategorieMp;
import dao.Manager.CategorieMpDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.HistoriquePrixDAO;
import dao.Manager.SubCategorieMPDAO;
import dao.ManagerImpl.CategorieMpDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.HistoriquePrixDAOImpl;
import dao.ManagerImpl.SubCategorieMPAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class HistoriquePrixController implements Initializable {

    
    @FXML
    private TableView<HistoriquePrixGlobal> tableObjetHistoriquePrix;
    @FXML
    private TableColumn<HistoriquePrixGlobal, String> CategorieColumn;
    @FXML
    private TableColumn<HistoriquePrixGlobal, BigDecimal> ancienPrixColumn;
    @FXML
    private TableColumn<HistoriquePrixGlobal, BigDecimal> nouveauPrixColumn;
    @FXML
    private ComboBox<String> subCategorieCombo;
    @FXML
    private ComboBox<String> categorieCombo;
    
    @FXML
    private TableView<HistoriquePrix> tableHistoriquePrix;
    @FXML
    private TableColumn<HistoriquePrix, String> CategorieDetailColumn;
    @FXML
    private TableColumn<HistoriquePrix, Date> dateModificationColumn;
    @FXML
    private TableColumn<HistoriquePrix, String> fournisseurColumn;
    @FXML
    private TableColumn<HistoriquePrix, String> cheminColumn;
    @FXML
    private TableColumn<HistoriquePrix, BigDecimal> ancienPrixDetailColumn;
    @FXML
    private TableColumn<HistoriquePrix, BigDecimal> nouveauPrixDetailColumn;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private Button btnInitialiser;
    
    
    /**
     * Initializes the controller class.
     */
    
     private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
     private Map<String,CategorieMp> mapCategorieMp=new HashMap<>();
     private Map<String,SubCategorieMp> mapSubCategorieMp=new HashMap<>();
     
     
         CategorieMpDAO  categorieMpDAO = new CategorieMpDAOImpl();
         SubCategorieMPDAO subCategorieMPDAO = new SubCategorieMPAOImpl();
         FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
         HistoriquePrixDAO historiquePrixDAO = new HistoriquePrixDAOImpl();
          navigation nav = new navigation();
        Fournisseur fournisseur;
         
         
         
          private final ObservableList<HistoriquePrix> listHistoriquePrix=FXCollections.observableArrayList();
          private final ObservableList<HistoriquePrixGlobal> listHistoriquePrixGlobal=FXCollections.observableArrayList();
    
    
    
    
          
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
          List<SubCategorieMp> listSubCategorieMp=subCategorieMPDAO.findAll();
        
        listSubCategorieMp.stream().map((subCategorieMp) -> {
        subCategorieCombo.getItems().addAll(subCategorieMp.getNom());
        return subCategorieMp;
        }).forEachOrdered((subCategorieMp) -> {
        mapSubCategorieMp.put(subCategorieMp.getNom(), subCategorieMp);
        
        });
        
          List<Fournisseur> listFournisseurBox=fournisseurDAO.findAllMp();
        
        listFournisseurBox.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        // TODO
        
        
        
        setColumnProperties();
        loadDetail();
    }    

    void setColumnPropertiesDetail(){

          CategorieDetailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriquePrix , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HistoriquePrix, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCategorieMp().getNom());
            }

        });
     
         dateModificationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriquePrix , Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<HistoriquePrix, Date> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDateCreation());
            }

        });
         
    
         fournisseurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriquePrix , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HistoriquePrix, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFournisseur().getNom());
            }

        });
      
         cheminColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriquePrix , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HistoriquePrix, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getChemin());
            }

        });
         
          ancienPrixDetailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriquePrix , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<HistoriquePrix, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getAncienPrix());
            }

        });
  
            nouveauPrixDetailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriquePrix , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<HistoriquePrix, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNouveauPrix());
            }

        });

    }
    
     void setColumnProperties(){
        

          CategorieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriquePrixGlobal , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HistoriquePrixGlobal, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCategorieMp().getNom());
            }

        });
     
         
          ancienPrixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriquePrixGlobal , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<HistoriquePrixGlobal, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getAncienPrix());
            }

        });
  
            nouveauPrixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HistoriquePrixGlobal , BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<HistoriquePrixGlobal, BigDecimal> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNouveauPrix());
            }

        });
            
        

      
    }
    
    void loadDetail(){
        

           List <Object[]> listeObjectHistoriquePrix =historiquePrixDAO.findByHistoriquePrixGlobalMP();

        
         
            for(int i=0; i<listeObjectHistoriquePrix.size(); i++) {

                    Object[] object=listeObjectHistoriquePrix.get(i);
                   
                    CategorieMp categorieMp =(CategorieMp)object[0];
                    BigDecimal ancienPrix = (BigDecimal)object[1]; 
                    BigDecimal nouveauPrix = (BigDecimal)object[2]; 
                    


               HistoriquePrixGlobal historiquePrixGlobal = new HistoriquePrixGlobal();
               
                  historiquePrixGlobal.setCategorieMp(categorieMp);
                  historiquePrixGlobal.setAncienPrix(ancienPrix);
                  historiquePrixGlobal.setNouveauPrix(nouveauPrix);


                  listHistoriquePrixGlobal.add(historiquePrixGlobal);
            }
            
            tableObjetHistoriquePrix.setItems(listHistoriquePrixGlobal);
            setColumnProperties();
    }
    
    @FXML
    private void clickTablePrixBox(MouseEvent event) {
        
        if (tableObjetHistoriquePrix.getSelectionModel().getSelectedIndex()!=-1){

      listHistoriquePrix.clear();
    
   
        HistoriquePrixGlobal historiquePrixGlobal=tableObjetHistoriquePrix.getSelectionModel().getSelectedItem();

        listHistoriquePrix.addAll(historiquePrixDAO.findByCategorieNom(historiquePrixGlobal.getCategorieMp().getNom()));

        tableHistoriquePrix.setItems(listHistoriquePrix);
        
        setColumnPropertiesDetail();
        
        fourCombo.getSelectionModel().select(-1);
    }

    }

    @FXML
    private void fourComboOnAction(ActionEvent event) {
        
          if (tableObjetHistoriquePrix.getSelectionModel().getSelectedIndex()!=-1){ 
            listHistoriquePrix.clear();
            
           HistoriquePrixGlobal historiquePrixGlobal=tableObjetHistoriquePrix.getSelectionModel().getSelectedItem();
           Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
           
               if (fournisseur!=null){
           
           listHistoriquePrix.addAll(historiquePrixDAO.findHistoriquePrixByFour(fournisseur.getId(), historiquePrixGlobal.getCategorieMp().getNom()));
               }
        }
        
    }

    @FXML
    private void rechercheMouseClicked(MouseEvent event) {
        
            if(categorieCombo.getSelectionModel().getSelectedIndex()!= -1){    
            listHistoriquePrixGlobal.clear();
            CategorieMp ctgMp=mapCategorieMp.get(categorieCombo.getSelectionModel().getSelectedItem());
            
            
              List <Object[]> listeObjectHistoriquePrix =historiquePrixDAO.findByHistoriquePrixGlobalMpAndGategorie(ctgMp.getNom());

         
            for(int i=0; i<listeObjectHistoriquePrix.size(); i++) {

                    Object[] object=listeObjectHistoriquePrix.get(i);
                   
                    CategorieMp categorieMp =(CategorieMp)object[0];
                    BigDecimal ancienPrix = (BigDecimal)object[1]; 
                    BigDecimal nouveauPrix = (BigDecimal)object[2]; 
                    


               HistoriquePrixGlobal historiquePrixGlobal = new HistoriquePrixGlobal();
               
                  historiquePrixGlobal.setCategorieMp(categorieMp);
                  historiquePrixGlobal.setAncienPrix(ancienPrix);
                  historiquePrixGlobal.setNouveauPrix(nouveauPrix);


                  listHistoriquePrixGlobal.add(historiquePrixGlobal);
            }
            
            tableObjetHistoriquePrix.setItems(listHistoriquePrixGlobal);
            setColumnProperties();

            fourCombo.getSelectionModel().select(-1);
            listHistoriquePrix.clear();
        }
        
    }

    @FXML
    private void subCategorieOnAction(ActionEvent event) {
        
          if (subCategorieCombo.getSelectionModel().getSelectedIndex()!=-1){
        
              SubCategorieMp subCtgMp=mapSubCategorieMp.get(subCategorieCombo.getSelectionModel().getSelectedItem());
              
              if (subCtgMp!=null){
              
           List<CategorieMp> listCategorieMp=categorieMpDAO.findCategorieMpByID(subCtgMp.getId());
        
            listCategorieMp.stream().map((categorieMp) -> {
            categorieCombo.getItems().addAll(categorieMp.getNom());
            return categorieMp;
        }).forEachOrdered((categorieMp) -> {
            mapCategorieMp.put(categorieMp.getNom(), categorieMp);
        });
              }
          }
    }

    @FXML
    private void inisialiserOnAction(ActionEvent event) {
        
        
        listHistoriquePrix.clear();
        subCategorieCombo.getSelectionModel().select(-1);
        categorieCombo.getSelectionModel().select(-1);
        fourCombo.getSelectionModel().select(-1);
        
        
         setColumnProperties();
        loadDetail();
        
    }
    
}
