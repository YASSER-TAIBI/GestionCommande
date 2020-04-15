/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ClientMP;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Manager.ClientMPDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ListeClientMPController implements Initializable {

    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;
    @FXML
    private TextField codeRechField;
    @FXML
    private TextField nomRechField;
    @FXML
    private DatePicker dateRechPicker;
    @FXML
    private TableView<ClientMP> tableClientMP;
    @FXML
    private TableColumn<ClientMP, String> nomColumn;
    @FXML
    private TableColumn<ClientMP, String> codeColumn;
    @FXML
    private TableColumn<ClientMP, String> codeDepotColumn;
    @FXML
    private TableColumn<ClientMP, String> adresseColumn;
    @FXML
    private TableColumn<ClientMP, String> emailColumn;
    @FXML
    private TableColumn<ClientMP, String> telColumn;
    @FXML
    private TableColumn<ClientMP, LocalDate> datCreationColumn;
    @FXML
    private TableColumn<ClientMP, String> typeClientColumn;
    @FXML
    private Button btnValider;

    /**
     * Initializes the controller class.
     */
      private final ObservableList<ClientMP> listeClientMP=FXCollections.observableArrayList();
            
           ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
     navigation nav = new navigation();
     ClientMP clientMP;

     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         tableClientMP.setEditable(true);

   tableClientMP.setItems(listeClientMP);
 
 
      setColumnProperties();

      loadDetail();  
    }    

    
      void setColumnProperties(){
        
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        codeDepotColumn.setCellValueFactory(new PropertyValueFactory<>("codeDepot"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        datCreationColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        typeClientColumn.setCellValueFactory(new PropertyValueFactory<>("typeClient"));
       

      
    }
    
    void loadDetail(){
        
        listeClientMP.clear();
        listeClientMP.addAll(clientMPDAO.findAll());
        tableClientMP.setItems(listeClientMP);
    }

    

    @FXML
    private void SupprimerClientMP(ActionEvent event) {
        if(tableClientMP.getSelectionModel().getSelectedItem()==null){

         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
             Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
    
       ClientMP clientMP=tableClientMP.getSelectionModel().getSelectedItem();
        clientMPDAO.delete(clientMP);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnProperties();
      loadDetail();  
                }
    }
    }

    @FXML
    private void ModifierClientMP(ActionEvent event) {
        if (tableClientMP.getSelectionModel().getSelectedItem() != null) {
              
              ClientMP clientMP= tableClientMP.getSelectionModel().getSelectedItem();
              
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getModifierClientMP()));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);
         
                ModifierClientMPController modifierController = fXMLLoader.getController();
               
               

                modifierController.clientMP = clientMP;
                modifierController.chargerLesDonnees();
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.show();
            } catch (IOException ex) {

              
                System.err.println("!!!!!!!!" +ex);
            }

        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Errreur",Constantes.SELECTION_ERREUR , Constantes.SELECTION_LIGNE_MODIFIER);
        }
    }

    @FXML
    private void ajouterClientMP(ActionEvent event) throws IOException {
       AjouterClientMPController root = new AjouterClientMPController(Constantes.POUR_AJOUTER,new ClientMP()) {
           @Override
           public void refresh() {
              tableClientMP.setItems(FXCollections.observableArrayList(new ClientMPDAOImpl().findAll()));
              setColumnProperties();
           }
       };
      Stage stage = new Stage(); 
      Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
        
    
      loadDetail();  
    }
    public void changeNomCellEvent (CellEditEvent editedCell){
        
        ClientMP utilisateurSelected =tableClientMP.getSelectionModel().getSelectedItem();
        utilisateurSelected.setNom(editedCell.getNewValue().toString());
        
    }

    @FXML
    private void rechercheNomClientOnKeyPressed(KeyEvent event) {
            
        ObservableList<ClientMP> listeClientMP=FXCollections.observableArrayList();
        
    listeClientMP.clear();
   
   listeClientMP.addAll(clientMPDAO.findClientMPByRechercheNom(nomRechField.getText()));
   
   tableClientMP.setItems(listeClientMP);
        
    
    }
    

    @FXML
    private void rechercheCodeClientOnKeyPressed(KeyEvent event) {
        
        ObservableList<ClientMP> listeClientMP=FXCollections.observableArrayList();
        
    listeClientMP.clear();
   
   listeClientMP.addAll(clientMPDAO.findClientMPByRechercheCode(codeRechField.getText()));
   
   tableClientMP.setItems(listeClientMP);
        
    }

    @FXML
    private void rechercheDateClientOnKeyPressed(KeyEvent event) {
   
    }
}
