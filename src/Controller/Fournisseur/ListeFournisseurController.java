/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Fournisseur;


import Utils.Constantes;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.Entity.Fournisseur;
import function.navigation;
import java.io.IOException;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import dao.Manager.FournisseurDAO;
import java.time.LocalDate;
import java.util.Optional;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author h
 */
public class ListeFournisseurController implements Initializable {

    @FXML
    private Button btnValider;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    
    @FXML
    private TableView<Fournisseur> tableFournisseur;
     @FXML
    private TableColumn<Fournisseur, String> nomColumn;
    @FXML
    private TableColumn<Fournisseur, String> codeColumn;
    @FXML
    private TableColumn<Fournisseur, String> villeColumn;
    @FXML
    private TableColumn<Fournisseur, String> adresseColumn;
    @FXML
    private TableColumn<Fournisseur, String> emailColumn;
    @FXML
    private TableColumn<Fournisseur, String> telColumn;
    @FXML
    private TableColumn<Fournisseur, LocalDate> datCreationColumn;
    @FXML
    private TextField nomRechField;
    @FXML
    private TextField codeRechField;
    
    
      private final ObservableList<Fournisseur> listeFournisseur=FXCollections.observableArrayList();
            
     FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    
     navigation nav = new navigation();
     
     Fournisseur fournisseur;
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        
        tableFournisseur.setEditable(true);

  
 
 
       setColumnProperties();
      loadDetail();  
    }    
    
    
    void setColumnProperties(){
        
       nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
       codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

       villeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Fournisseur, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Fournisseur, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getVille().getLibelle());
                }
             });
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        datCreationColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
      
    }
    
    void loadDetail(){
        
        listeFournisseur.clear();
        listeFournisseur.addAll(fournisseurDAO.findAllPfAndMp());
        tableFournisseur.setItems(listeFournisseur);
    }

    
    @FXML
    private void ajouterFournisseur(ActionEvent event) throws IOException {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
          AjouterFournisseurController root = new AjouterFournisseurController(Constantes.POUR_AJOUTER ,new Fournisseur()) {
           @Override
           public void refresh() {
              tableFournisseur.setItems(FXCollections.observableArrayList(new FournisseurDAOImpl().findAllPfAndMp()));
              setColumnProperties();
           }
       };
      Stage stage = new Stage(); 
      Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
        
    
      loadDetail();  
    }}

    
      public void changeNomCellEvent (CellEditEvent editedCell){
        
        Fournisseur utilisateurSelected =tableFournisseur.getSelectionModel().getSelectedItem();
        utilisateurSelected.setNom(editedCell.getNewValue().toString());
        
        
    }
    
    
    @FXML
    private void ModifierFournisseur(ActionEvent event) {
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
   
   if (tableFournisseur.getSelectionModel().getSelectedItem() != null) {
              
              Fournisseur fournisseur= tableFournisseur.getSelectionModel().getSelectedItem();
              
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getModifierFournisseur()));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);

                ModifierFournisseurController modifierController = fXMLLoader.getController();
               
               
             
                modifierController.fournisseur = fournisseur;
                modifierController.chargerLesDonnees();
                
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
    
                stage.show();
            } catch (IOException ex) {
              
                System.err.println("!!!!!!!!" +ex);
            }
              
              
         
        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Errreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
        }}
    }

    @FXML
    private void SupprimerFournisseur(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
         if(tableFournisseur.getSelectionModel().getSelectedItem()==null){
         
    
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.SELECTION_ERREUR);
        
     }else {
        
             fournisseur=tableFournisseur.getSelectionModel().getSelectedItem();
        
        fournisseurDAO.delete(fournisseur);
    
             nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnProperties();
      loadDetail();  
    }}
    }


    @FXML
    private void rechercheNomFourOnKeyPressed(KeyEvent event) {
           ObservableList<Fournisseur> listeFournisseurs=FXCollections.observableArrayList();
    listeFournisseurs.clear();
   
   listeFournisseurs.addAll(fournisseurDAO.findFourByRechercheNom(nomRechField.getText()));
   
   tableFournisseur.setItems(listeFournisseurs);
    }

    @FXML
    private void rechercheCodeFourOnKeyPressed(KeyEvent event) {
               ObservableList<Fournisseur> listeFournisseurs=FXCollections.observableArrayList();
    listeFournisseurs.clear();
   
   listeFournisseurs.addAll(fournisseurDAO.findFourByRechercheCode(codeRechField.getText()));
   
   tableFournisseur.setItems(listeFournisseurs);
    }

}
