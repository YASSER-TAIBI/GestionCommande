/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Client;
import dao.Manager.ClientDAO;
import dao.ManagerImpl.ClientDAOImpl;
import function.navigation;
import java.net.URL;
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
public class ClientController implements Initializable {

    @FXML
    private TableView<Client> tableClient;
    @FXML
    private TableColumn<Client, String> codeColumn;
    @FXML
    private TableColumn<Client, String> libelleColumn;
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

    /**
     * Initializes the controller class.
     */
    
        private final ObservableList<Client> listClient=FXCollections.observableArrayList();
            
     ClientDAO clientDAO = new ClientDAOImpl();
     navigation nav = new navigation();
     Client client= new Client();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        setColumnProperties();
        loadDetail();
        
    }    

           void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
         
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    
     }
    
     void loadDetail(){
        
        listClient.clear();
        listClient.addAll(clientDAO.findAll());
        tableClient.setItems(listClient);
    }
    
    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
        
                           Integer val= tableClient.getSelectionModel().getSelectedIndex();
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
        
                    if (tableClient.getSelectionModel().getSelectedItem() != null) {
        
          client=tableClient.getSelectionModel().getSelectedItem();

       client.setCode(codeField.getText());
       client.setLibelle(libelleMod.getText());

          clientDAO.edit(client);
          

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
        
              if(tableClient.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
             
             Client client=tableClient.getSelectionModel().getSelectedItem();

                 clientDAO.delete(client.getId());


        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
              libelleMod.setText("");
              codeField.setText("");
        
      setColumnProperties();
      loadDetail();  
    
    }
        
        
    }

    @FXML
    private void btnAjouterOnAction(ActionEvent event) {
        
             client = new Client();
  

        client.setCode(codeField.getText());   
        client.setLibelle(libelleMod.getText());
        client.setUtilisateurCreation(nav.getUtilisateur());
        clientDAO.add(client);

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
