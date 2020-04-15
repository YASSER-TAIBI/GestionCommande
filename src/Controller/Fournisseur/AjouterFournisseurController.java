/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Fournisseur;

import Utils.Constantes;
import dao.Entity.CompteFourMP;
import dao.Entity.Fournisseur;
import dao.Entity.Ville;
import dao.Manager.CompteFourMPDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.VilleDAO;
import dao.ManagerImpl.CompteFourMPDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.VilleDAOImpl;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.Spring;

/**
 * FXML Controller class
 *abstract
 * extends AnchorPane
 * 
 * @author h
 */
public abstract class AjouterFournisseurController extends AnchorPane implements Initializable {
private int POUR;
  Fournisseur fournisseur;
    @FXML
    private TextField nombreDuJour;
    @FXML
    private Button btnRafraichir;
    @FXML
    private RadioButton marocainFour;
    @FXML
    private ToggleGroup typeFour;
    @FXML
    private RadioButton etrangerFour;
    @FXML
    private TextField nomField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField adresseField;
    @FXML
    private Button btnAjou;
    @FXML
    private TextField telField;
    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<String> villeCombo;
    @FXML
    private ComboBox<String> compteCombo;
    
    
    public AjouterFournisseurController (int POUR,Fournisseur fournisseur){
    this.fournisseur= fournisseur;
    this.POUR=POUR;
    setAll(nav.getAjouterFournisseur(), this);
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
   
  
    
    FournisseurDAO fournisseurDAO =  new FournisseurDAOImpl();
    VilleDAO villeDAO = new VilleDAOImpl();
    private Map<String,Ville> mapVille=new HashMap<>();
     CompteFourMPDAO compteDAO = new CompteFourMPDAOImpl();
    private Map<String,CompteFourMP> mapCompte=new HashMap<>();
     navigation nav = new navigation();
       String valeur="";
     
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Ville> listVille=villeDAO.findAll();
        
        listVille.stream().map((ville) -> {
            villeCombo.getItems().addAll(ville.getLibelle());
            return ville;
        }).forEachOrdered((ville) -> {
            mapVille.put(ville.getLibelle(), ville);
        });

          List<CompteFourMP> listCompte=compteDAO.findAll();
        
        listCompte.stream().map((compte) -> {
            compteCombo.getItems().addAll(compte.getLibelle());
            return compte;
        }).forEachOrdered((compte) -> {
            mapCompte.put(compte.getLibelle(), compte);
        });
        
           int Maxid = fournisseurDAO.getMaxId();

        codeField.setText(nav.generCode(Constantes.FOURNISSEUR , Maxid));
    }    

    @FXML
    private void ajouterFournisserOnAction(ActionEvent event) throws ParseException {
        
        
     if(nomField.getText().equalsIgnoreCase("") || villeCombo.getSelectionModel().getSelectedIndex()== -1 || compteCombo.getSelectionModel().getSelectedIndex()== -1 ||marocainFour.isSelected()== false && etrangerFour.isSelected()== false){

        nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);

     }else {
         

            
          
         
           fournisseur =new Fournisseur();
           
           if (marocainFour.isSelected()){
             
              fournisseur.setTypeFournisseur(Constantes.MAROCAINE);
              
             }else if(etrangerFour.isSelected()){
               fournisseur.setTypeFournisseur(Constantes.ETRANGER);
             }
        
       fournisseur.setNom(nomField.getText());
       fournisseur.setCode(codeField.getText());
       fournisseur.setVille(mapVille.get(villeCombo.getSelectionModel().getSelectedItem()));
       fournisseur.setAdresse(adresseField.getText());
       fournisseur.setDelaiPaiement(Integer.valueOf(nombreDuJour.getText()));
       fournisseur.setTel(telField.getText());
       fournisseur.setEmail(emailField.getText());
       fournisseur.setUtilisateurCreation(nav.getUtilisateur());
       fournisseur.setDateCreation(new Date());
       fournisseur.setCompteFourMP(mapCompte.get(compteCombo.getSelectionModel().getSelectedItem()));
          
        fournisseurDAO.add(fournisseur);
        
    nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.AJOUTER_ENREGISTREMENT);
      refresh();
     Stage stage = (Stage)
     btnAjou.getScene().getWindow();
     stage.close();
  
    }
    }
    
    @FXML
    private void rafraichirFourAction(ActionEvent event) {
    }

}
