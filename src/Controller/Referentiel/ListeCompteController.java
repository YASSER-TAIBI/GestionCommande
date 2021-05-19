/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;


import Utils.Constantes;
import dao.Entity.CompteFourMP;
import dao.ManagerImpl.CompteFourMPDAOImpl;
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
import dao.Manager.CompteFourMPDAO;
import java.math.BigDecimal;
import javafx.scene.input.KeyEvent;


/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ListeCompteController implements Initializable {

    @FXML
    private TableView<CompteFourMP> tableCompte;
    @FXML
    private TableColumn<CompteFourMP, String> codeColumn;
    @FXML
    private TableColumn<CompteFourMP, String> libelleColumn;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnModifier;
    @FXML
    private TextField codeRechField;
    @FXML
    private TextField libelleRechField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField libelleField;
   

    /**
     * Initializes the controller class.
//     */
//    double valeurDebit =0;
//    double valeurCredit = 0;
     private final ObservableList<CompteFourMP> listeCompte=FXCollections.observableArrayList();
  
     

     CompteFourMPDAO compteDAO = new CompteFourMPDAOImpl();
     navigation nav = new navigation();
      CompteFourMP compte = new CompteFourMP();
//    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

           tableCompte.setEditable(true);

        setColumnProperties();
        loadDetail();
    }    

     void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
     }
    
     void loadDetail(){
        
        listeCompte.clear();
        listeCompte.addAll(compteDAO.findAll());
        tableCompte.setItems(listeCompte);
    }
    
    @FXML
    private void SupprimerFournisseur(ActionEvent event) {
                    if(tableCompte.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       CompteFourMP compteFourMP=tableCompte.getSelectionModel().getSelectedItem();
        compteFourMP.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);
       compteDAO.edit(compteFourMP);
 
    clear();
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnProperties();
      loadDetail();  
    }
    }

    @FXML
    private void ajouterCompte(ActionEvent event) {
             if(codeField.getText().equalsIgnoreCase("") && libelleField.getText().equalsIgnoreCase("") ){
  
    
         nav.showAlert(Alert.AlertType.ERROR, "Succès", null, Constantes.REMPLIR_CHAMPS);
         
             }else{
        CompteFourMP compte = new  CompteFourMP();
         
       compte.setCode(codeField.getText());
       compte.setLibelle(libelleField.getText());
       compte.setEtat(Constantes.ETAT_COMMANDE_LANCE);
       compte.setUtilisateurCreation(nav.getUtilisateur());
  

          compteDAO.add(compte);
                clear();
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
             setColumnProperties();
      loadDetail();
    }
             }
    
    
    @FXML
    private void ModifierFournisseur(ActionEvent event) {
         
         if (tableCompte.getSelectionModel().getSelectedItem() != null) {
        
         CompteFourMP compte=tableCompte.getSelectionModel().getSelectedItem();
         
       compte.setCode(codeField.getText());
       compte.setLibelle(libelleField.getText());

          compteDAO.edit(compte);

      clear();
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
             setColumnProperties();
      loadDetail();
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Errreur",  Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
    }
    
    
    
    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
                Integer val= tableCompte.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          
          return;
          }
          else {
          
              codeField.setText(codeColumn.getCellData(val));
              libelleField.setText(libelleColumn.getCellData(val));
              
          }
    }

    private void clear(){
        
        codeField.clear();
        libelleField.clear();
    } 

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        clear();
    }

    @FXML
    private void rechercheCodeOnKeyPressed(KeyEvent event) {
        
                        ObservableList<CompteFourMP> listeCompteFourMPs=FXCollections.observableArrayList();
          
        listeCompteFourMPs.clear();
   
        listeCompteFourMPs.addAll(compteDAO.findByCodeCompteFourMP(codeRechField.getText()));
   
        tableCompte.setItems(listeCompteFourMPs);
        
    }

    @FXML
    private void rechercheLibelleOnKeyPressed(KeyEvent event) {
        
                             ObservableList<CompteFourMP> listeCompteFourMPs=FXCollections.observableArrayList();
          
        listeCompteFourMPs.clear();
   
        listeCompteFourMPs.addAll(compteDAO.findBylibelleCompteFourMP(libelleField.getText()));
   
        tableCompte.setItems(listeCompteFourMPs);
        
    }

    @FXML
    private void rechercheTableMouseClicked(MouseEvent event) {
        
                    libelleRechField.clear();
            codeRechField.clear();

            setColumnProperties();
            loadDetail();
        
    }

 

    
  
    
}
