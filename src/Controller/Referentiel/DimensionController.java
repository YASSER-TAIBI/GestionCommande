/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.CategorieMp;
import dao.Entity.Dimension;
import dao.Entity.TypeCartonBox;
import dao.Manager.CategorieMpDAO;
import dao.Manager.DimensionDAO;
import dao.ManagerImpl.CategorieMpDAOImpl;
import dao.ManagerImpl.DimensionDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
public class DimensionController implements Initializable {

    @FXML
    private TextField codeField;
    @FXML
    private ComboBox <String> familleCombo;

    @FXML
    private TableView<Dimension> tableDimension;
    @FXML
    private TableColumn<Dimension, String> codeColumn;
    @FXML
    private TableColumn<Dimension, String> familleColumn;
    @FXML
    private TableColumn<Dimension, String> libelleColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField libelleMod;
    @FXML
    private TextField codeRechField;
    @FXML
    private TextField libelleRechField;
     @FXML
    private Button btnImprimer;
    
    private final ObservableList<Dimension> listeDimension=FXCollections.observableArrayList();
            
     DimensionDAO dimensionDAO = new DimensionDAOImpl();
     navigation nav = new navigation();
     Dimension dimension= new Dimension();
     CategorieMpDAO  categorieMpDAO = new CategorieMpDAOImpl();
//     CategorieMp categorieMp =new CategorieMp();
    
      private Map<String,CategorieMp> mapCategorieMp=new HashMap<>();
   
    
   
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
            List<CategorieMp> listCategorieMp=categorieMpDAO.findAll();
        
            listCategorieMp.stream().map((categorieMp) -> {
            familleCombo.getItems().addAll(categorieMp.getCode());
            return categorieMp;
        }).forEachOrdered((categorieMp) -> {
            mapCategorieMp.put(categorieMp.getCode(), categorieMp);
        });
                
           
        setColumnProperties();
        loadDetail();
        
         int Maxid = dimensionDAO.getMaxId();
        codeField.setText(nav.generCode(Constantes.DIMENSION , Maxid));

    }    

     void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        
        familleColumn.setCellValueFactory(new PropertyValueFactory<>("famille"));
         
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    
     }
    
     void loadDetail(){
        
        listeDimension.clear();
        listeDimension.addAll(dimensionDAO.findAll());
        tableDimension.setItems(listeDimension);
    }
     
   
    
    @FXML
    private void btnAjouterOnAction(ActionEvent event) {

       
         dimension = new Dimension();
  
      CategorieMp categorieMp = mapCategorieMp.get(familleCombo.getSelectionModel().getSelectedItem());
        dimension.setCode(codeField.getText());   
        dimension.setFamille(categorieMp.getCode());
        dimension.setLibelle(libelleMod.getText());
        dimension.setEtat(Constantes.ETAT_COMMANDE_LANCE);
        dimension.setUtilisateurCreation(nav.getUtilisateur());
        dimensionDAO.add(dimension);
        
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
         
             int Maxid = dimensionDAO.getMaxId();
        codeField.setText(nav.generCode(Constantes.DIMENSION , Maxid));
        

        familleCombo.getSelectionModel().select(-1);
        libelleMod.setText("");
        
     setColumnProperties();
        loadDetail();
     
         
    }

    @FXML
    private void btnModifierOnAction(ActionEvent event) {
   
          
         if (tableDimension.getSelectionModel().getSelectedItem() != null) {
        
          dimension=tableDimension.getSelectionModel().getSelectedItem();
          CategorieMp categorieMp = mapCategorieMp.get(familleCombo.getSelectionModel().getSelectedItem());
       dimension.setCode(codeField.getText());
       dimension.setLibelle(libelleMod.getText());
        dimension.setFamille(categorieMp.getCode());
          dimensionDAO.edit(dimension);
          
        libelleMod.setText("");
        familleCombo.getSelectionModel().select(-1);
         
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);

        
      setColumnProperties();
      loadDetail();
      
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
    }

    @FXML
    private void btnSupprimerOnAction(ActionEvent event) {
         if(tableDimension.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
             
       Dimension dimension=tableDimension.getSelectionModel().getSelectedItem();
          
       dimension.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);

            dimensionDAO.edit(dimension);
        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
              libelleMod.setText("");
        familleCombo.getSelectionModel().select(-1);
        
        setColumnProperties();
      loadDetail();  
    
    }
    }



    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
        
 
           Integer val= tableDimension.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              codeField.setText(codeColumn.getCellData(val));
              libelleMod.setText(libelleColumn.getCellData(val));
              familleCombo.setValue(familleColumn.getCellData(val));

          }
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {

        libelleMod.setText("");
        familleCombo.getSelectionModel().select(-1);
           
    }

    @FXML
    private void rechercheCodeOnKeyPressed(KeyEvent event) {
        
                       ObservableList<Dimension> listeDimension=FXCollections.observableArrayList();
          
        listeDimension.clear();
   
        listeDimension.addAll(dimensionDAO.findByCodeDimension(codeRechField.getText()));
   
        tableDimension.setItems(listeDimension);
        
    }

    @FXML
    private void rechercheLibelleOnKeyPressed(KeyEvent event) {
        
                 ObservableList<Dimension> listeDimension=FXCollections.observableArrayList();
          
        listeDimension.clear();
   
        listeDimension.addAll(dimensionDAO.findBylibelleDimension(libelleRechField.getText()));
   
        tableDimension.setItems(listeDimension);
        
    }

    @FXML
    private void rechercheTableMouseClicked(MouseEvent event) {
        
        
            libelleRechField.clear();
            codeRechField.clear();

            setColumnProperties();
            loadDetail();
        
        
    }

    @FXML
    private void btnImprimerOnAction(ActionEvent event) {
        
        
             try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(DimensionController.class.getResource(nav.getiReportConsultationDimension()));

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDimension));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(DimensionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    

 
    
    }    
    

