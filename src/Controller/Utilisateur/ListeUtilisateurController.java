/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Utilisateur;


import Utils.Constantes;
import Utils.UtilsMenu;
import dao.Entity.CategorieMp;
import dao.ManagerImpl.UtilisateurDAOImpl;
import dao.Entity.Utilisateur;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import dao.Manager.UtilisateurDAO;
import java.util.Optional;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author khatari-pc
 */
public class ListeUtilisateurController implements Initializable {

    @FXML private Button btnValider;
    @FXML private Button btnModifier;
    @FXML private Button btnSupprimer;
    @FXML private Label msgCode;
    @FXML private TextField rechNomField;
    @FXML private TextField rechCodeDepotField;
    @FXML private TableView<Utilisateur> tableUtilisateur;
    @FXML private TableColumn<Utilisateur,String> nomColumn;
    @FXML private TableColumn<Utilisateur,String> loginColumn;
    @FXML private TableColumn<Utilisateur,String> codeDepotColumn;
    @FXML private TableColumn<Utilisateur, String> passwordColumn;
    @FXML private TableColumn<Utilisateur, String> typeColumn;
   
    
    private final ObservableList<Utilisateur> listeUtilisateur=FXCollections.observableArrayList();
    
     
     UtilisateurDAO utilisateurDAO =  new UtilisateurDAOImpl();
     navigation nav = new navigation();
     Utilisateur utilisateur;
    @FXML
    private Button genererMenuBtn;
   
   
   

    @FXML
    private void ajouterUtilisateur(ActionEvent event) throws IOException{
        
         AjouterUtilisateurController root = new AjouterUtilisateurController(Constantes.POUR_AJOUTER,new Utilisateur()) {
           @Override
           public void refresh() {
              tableUtilisateur.setItems(FXCollections.observableArrayList(new UtilisateurDAOImpl().findAll()));
              setColumnProperties();
           }
       };
      Stage stage = new Stage(); 
      Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
        
    
      loadDetail();  
         
         
//     }      
    }
  
    public void changeNomCellEvent (CellEditEvent editedCell){
        
        Utilisateur utilisateurSelected =tableUtilisateur.getSelectionModel().getSelectedItem();
        utilisateurSelected.setNom(editedCell.getNewValue().toString());
        
    }
    
    
    FilteredList filter = new FilteredList(listeUtilisateur,e->true); 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   tableUtilisateur.setEditable(true);
   tableUtilisateur.setItems(listeUtilisateur);
      setColumnProperties();

      loadDetail();  
    }   
    
    void setColumnProperties(){
        
  
        
         codeDepotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Utilisateur, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Utilisateur, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDepot().getLibelle());
                }
             });
        
        nomColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("password"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("typeUtilisateur"));
       
       
    }
    
    void loadDetail(){
        
        listeUtilisateur.clear();
        listeUtilisateur.addAll(utilisateurDAO.findAll());
        tableUtilisateur.setItems(listeUtilisateur);
    }

  
    
    
    @FXML
    private void ModifierUtilisateur(ActionEvent event) throws IOException {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
   
   if (tableUtilisateur.getSelectionModel().getSelectedItem() != null) {
              
              Utilisateur utilisateur= tableUtilisateur.getSelectionModel().getSelectedItem();
              
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getModifierUtilisateur()));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);
              
                ModifierUtilisateurController modifierController = fXMLLoader.getController();
               
       
                modifierController.utilisateur = utilisateur;
                modifierController.chargerLesDonnees();
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
           
                stage.show();
            } catch (IOException ex) {
            
              
                System.err.println("!!!!!!!!" +ex);
            }

         
        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur",Constantes.SELECTION_ERREUR , Constantes.SELECTION_LIGNE_MODIFIER);
        }
            }
 }
        
   
    @FXML
    private void SupprimerUtilisateur(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
     if(tableUtilisateur.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       Utilisateur utilisateur=tableUtilisateur.getSelectionModel().getSelectedItem();
        utilisateurDAO.delete(utilisateur);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null,Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnProperties();
      loadDetail();  
    }
            }
    }

    @FXML
    private void rechercheNomUtiliOnKeyPressed(KeyEvent event) {
          ObservableList<Utilisateur> listeUtilisateurs=FXCollections.observableArrayList();
    listeUtilisateurs.clear();
   
   listeUtilisateurs.addAll(utilisateurDAO.findUtiliByRechercheNom(rechNomField.getText()));
   
   tableUtilisateur.setItems(listeUtilisateurs);
    }

    @FXML
    private void rechercheCodeDepotUtiliOnKeyPressed(KeyEvent event) {
            ObservableList<Utilisateur> listeUtilisateurs=FXCollections.observableArrayList();
    listeUtilisateurs.clear();
   
   listeUtilisateurs.addAll(utilisateurDAO.findUtiliByRechercheCodeDepot(rechCodeDepotField.getText()));
   
   tableUtilisateur.setItems(listeUtilisateurs);
    }

    @FXML
    private void genererMenuOnAction(ActionEvent event) {
        UtilsMenu utilsMenu = new UtilsMenu();
        utilsMenu.genererMenuUtilisateur();
        
    }


    

   
}
