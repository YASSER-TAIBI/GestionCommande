/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Utilisateur;

import Utils.Constantes;
import dao.Entity.Depot;
import dao.Entity.Utilisateur;
import dao.Manager.DepotDAO;
import dao.Manager.UtilisateurDAO;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.UtilisateurDAOImpl;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author admin
 */
public abstract class AjouterUtilisateurController extends AnchorPane implements Initializable {
  private int POUR;
  Utilisateur utilisateur;
    @FXML
    private RadioButton siegeRadio;
    @FXML
    private ToggleGroup typeUtil;
    @FXML
    private RadioButton regionRadio;
    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private Button btnRafraichir;
   
    
    public AjouterUtilisateurController (int POUR,Utilisateur utilisateur){
    this.utilisateur= utilisateur;
    this.POUR=POUR;
    setAll(nav.getAjouterUtilisateur(), this);
    }
    
    public static void setAll(String path, Object root){
    FXMLLoader fxmlLoader = new FXMLLoader(root.getClass().getResource(path));
        fxmlLoader.setRoot(root);
        fxmlLoader.setController(root);
        try {
            System.out.println(path);
            fxmlLoader.load();
        } catch (IOException exception){
            throw new RuntimeException(exception);
        }

    }
    public abstract void refresh();
    @FXML
    private Label msgNom;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField nomField;
    @FXML
    private Button btnajou;

    /**
     * Initializes the controller class.
     */
    
    UtilisateurDAO utilisateurDAO =  new UtilisateurDAOImpl();
    DepotDAO depotDAO = new DepotDAOImpl();
    ListeUtilisateurController listeUtilisateur = new ListeUtilisateurController();
     navigation nav = new navigation();
      private Map<String,Depot> mapDepot=new HashMap<>();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           List<Depot> listDepot=depotDAO.findAll();
        
        listDepot.stream().map((depot) -> {
            depotCombo.getItems().addAll((depot.getLibelle()));
            return depot;
        }).forEachOrdered((depot) -> {
            mapDepot.put(depot.getLibelle(), depot);
        });
        
    }    

    @FXML
    private void ajouterUtilisateurOnAction(ActionEvent event) {
        Utilisateur utilisateur =new Utilisateur();
     if(nomField.getText().equalsIgnoreCase("")||depotCombo.getItems().isEmpty()||loginField.getText().equalsIgnoreCase("")||passwordField.getText().equalsIgnoreCase("")){
        msgNom.setText(Constantes.CHAMP_OBLIGATOIRE);
     }else {
       
        utilisateurDAO.add(getUtilisateur());
     }      
      refresh();
     Stage stage = (Stage)
     btnajou.getScene().getWindow();
     stage.close();
     nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
     
    }
     public Utilisateur getUtilisateur(){
         
          Depot depot = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
         
        utilisateur.setDepot(depot);
        utilisateur.setNom(nomField.getText());
        utilisateur.setLogin(loginField.getText());
        utilisateur.setPassword(passwordField.getText());
        if (siegeRadio.isSelected()== true)
        utilisateur.setTypeUtilisateur(Constantes.SIEGE);
        else if (regionRadio.isSelected()== true)
        utilisateur.setTypeUtilisateur(Constantes.REGION);
        
        
        
        return utilisateur;

     }

    @FXML
    private void depotComboOnAction(ActionEvent event) {
    }

    @FXML
    private void rafraichirFourAction(ActionEvent event) {
    }
}
