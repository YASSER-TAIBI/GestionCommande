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
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author h
 */
public class ModifierFournisseurController implements Initializable {

    @FXML
    private TextField nomField;
    @FXML
    private TextField codeField;
    @FXML
    private ComboBox<String> compteCombo;
    @FXML
    private Button btnRafraichir;
    @FXML
    private RadioButton marocainFour;
    @FXML
    private ToggleGroup typeFour;
    @FXML
    private RadioButton etrangerFour;
    @FXML
    private TextField adresseField;
    @FXML
    private Button btnModif;
    @FXML
    private TextField telField;
    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<String> villeCombo;
    
    
    FournisseurDAO fournisseurDAO =  new FournisseurDAOImpl();
    private Map<String,Ville> mapVille=new HashMap<>();
    private Map<String,CompteFourMP> mapCompteFourMP=new HashMap<>();
    VilleDAO villeDAO = new VilleDAOImpl();
    
     navigation nav = new navigation();

    CompteFourMPDAO compteFourMPDAO = new CompteFourMPDAOImpl();
    public Fournisseur fournisseur;

   
    /**
     * Initializes the controller class.
     */
    
    
    public  void chargerLesDonnees(){
    
    //  utilisateur=tableUtilisateur.getSelectionModel().getSelectedItem();
    
    if (fournisseur.getTypeFournisseur().equals(Constantes.MAROCAINE)){
    marocainFour.setSelected(true);
    }
    else if (fournisseur.getTypeFournisseur().equals(Constantes.ETRANGER)){
    etrangerFour.setSelected(true);
    }
        nomField.setText(fournisseur.getNom());
        codeField.setText(fournisseur.getCode());
        adresseField.setText(fournisseur.getAdresse()); 
        telField.setText(fournisseur.getTel());
        emailField.setText(fournisseur.getEmail());
        compteCombo.setValue(fournisseur.getCompteFourMP().getLibelle());
        villeCombo.setValue(fournisseur.getVille().getLibelle());
  
    }
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          List<Ville> listVille=villeDAO.findAll();
        
        listVille.stream().map((ville) -> {
            villeCombo.getItems().addAll(ville.getLibelle());
            return ville;
        }).forEachOrdered((ville) -> {
            mapVille.put(ville.getLibelle(), ville);
        });
        
        
           List<CompteFourMP> listCompteFour=compteFourMPDAO.findAll();
        
        listCompteFour.stream().map((compte) -> {
            compteCombo.getItems().addAll(compte.getLibelle());
            return compte;
        }).forEachOrdered((compte) -> {
            mapCompteFourMP.put(compte.getLibelle(), compte);
        });
    }    

    @FXML
    private void ModifierFournisseurAction(ActionEvent event) {
        
            if (marocainFour.isSelected()){
             
              fournisseur.setTypeFournisseur(Constantes.MAROCAINE);
              
             }else if(etrangerFour.isSelected()){
               fournisseur.setTypeFournisseur(Constantes.ETRANGER);
             }
        
            
       fournisseur.setNom(nomField.getText());
       fournisseur.setCode(codeField.getText());
       fournisseur.setVille(mapVille.get(villeCombo.getSelectionModel().getSelectedItem()));
       fournisseur.setCompteFourMP(mapCompteFourMP.get(compteCombo.getSelectionModel().getSelectedItem()));
       fournisseur.setAdresse(adresseField.getText());
       fournisseur.setTel(telField.getText());
       fournisseur.setEmail(emailField.getText());
       fournisseur.setDateMAJ(new Date());
       fournisseurDAO.edit(fournisseur);
             
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
       
        Stage stage = (Stage) btnModif.getScene().getWindow();
           stage.close();
    }
    
    
    
      @FXML
    private void rafraichirFourAction(ActionEvent event) {
    }

}
