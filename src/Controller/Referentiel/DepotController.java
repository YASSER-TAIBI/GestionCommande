/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Depot;
import dao.Entity.Magasin;
import dao.Manager.DepotDAO;
import dao.Manager.MagasinDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
import function.navigation;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class DepotController implements Initializable {

    @FXML
    private TableColumn<Depot, String> codeColumn;
    @FXML
    private TableColumn<Depot, String> libelleColumn;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField codeField;
    @FXML
    private TextField libelleMod;
    @FXML
    private TableView<Depot> tableDepot;

    /**
     * Initializes the controller class.
     */
    
      private final ObservableList<Depot> listeDepot=FXCollections.observableArrayList();
            
     DepotDAO depotDAO = new DepotDAOImpl();
     MagasinDAO magasinDAO = new MagasinDAOImpl();
     navigation nav = new navigation();
     Depot depot= new Depot();
     Magasin magasin = new Magasin();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
               setColumnProperties();
        loadDetail();
        
        // TODO
    }    

       void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
         
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    
     }
    
     void loadDetail(){
        
        listeDepot.clear();
        listeDepot.addAll(depotDAO.findAll());
        tableDepot.setItems(listeDepot);
    }
    
    
    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
        
                   Integer val= tableDepot.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              codeField.setText(codeColumn.getCellData(val));
              libelleMod.setText(libelleColumn.getCellData(val));

          }
        
        
    }

    @FXML
    private void btnModifierOnAction(ActionEvent event) {
        
            if (tableDepot.getSelectionModel().getSelectedItem() != null) {
        
          depot=tableDepot.getSelectionModel().getSelectedItem();

       depot.setCode(codeField.getText());
       depot.setLibelle(libelleMod.getText());

          depotDAO.edit(depot);
          

        libelleMod.setText("");
         codeField.setText("");
         
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);

        
      setColumnProperties();
      loadDetail();
      
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
        
    }

    @FXML
    private void btnSupprimerOnAction(ActionEvent event) {
       if(tableDepot.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
             
             Depot depot=tableDepot.getSelectionModel().getSelectedItem();
           
           List<Magasin> listMagasin = magasinDAO.findByAllDepot(depot.getId());
           
           
           for (int i = 0; i < listMagasin.size(); i++) {
               
              Magasin magasin = listMagasin.get(i);
              
              magasinDAO.delete(magasin.getId());

           }
     
          
       depotDAO.delete(depot.getId());


        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
              libelleMod.setText("");
              codeField.setText("");
        
      setColumnProperties();
      loadDetail();  
    
    }
        
        
    }

    @FXML
    private void btnAjouterOnAction(ActionEvent event) {
        
         
         depot = new Depot();
  

        depot.setCode(codeField.getText());   
        depot.setLibelle(libelleMod.getText());
        depot.setUtilisateurCreation(nav.getUtilisateur());
        depotDAO.add(depot);
        
  
                 String codeMag = libelleMod.getText().toUpperCase();
         
//========================================================================= Magasin Mp  ===============================================================================================================================================
     
         magasin = new Magasin();
        


        magasin.setCatMagasin(Constantes.CATEGORIE_MAGASIN);
        magasin.setCode(codeMag.substring(0, 3)+"_MAG_MP_1");
        magasin.setLibelle("STOCKAGE MP");
        magasin.setTypeMagasin(Constantes.MAGASIN_CODE_TYPE_MP);
        magasin.setDepot(depot);
        magasin.setUtilisateurCreation(nav.getUtilisateur());
        
        magasinDAO.add(magasin);
        
//========================================================================= Magasin PF  ===============================================================================================================================================
     
         magasin = new Magasin();
        
        magasin.setCatMagasin(Constantes.CATEGORIE_MAGASIN);
        magasin.setCode(codeMag.substring(0, 3)+"_MAG_PF_1");
        magasin.setLibelle("STOCKAGE PF");
        magasin.setTypeMagasin(Constantes.MAGASIN_CODE_TYPE_PF);
        magasin.setDepot(depot);
        magasin.setUtilisateurCreation(nav.getUtilisateur());
        
        magasinDAO.add(magasin);      
         
         

         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
               
        
        codeField.setText("");
        libelleMod.setText("");
        
     setColumnProperties();
        loadDetail();
     
         
        
        
        
    }
    

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        
        codeField.setText("");
        libelleMod.setText("");
        
     setColumnProperties();
        loadDetail();
        
    }
    
}
