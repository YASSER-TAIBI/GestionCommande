/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Intervalle;
import dao.Entity.TypeCartonBox;
import dao.Manager.IntervalleDAO;
import dao.ManagerImpl.IntervalleDAOImpl;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ListeIntervalleController implements Initializable {

   
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField txtCode;
    @FXML
    private Label msgCode;
    @FXML
    private TextField codeRechField;
    @FXML
    private TextField libelleRechField;
    @FXML
    private TextField txtValeurMin;
    @FXML
    private TextField txtValeurMax;
     @FXML
    private TableView<Intervalle> tableIntervale;
    @FXML
    private TableColumn<Intervalle, String> codeColumn;
    @FXML
    private TableColumn<Intervalle, Integer> valeurMinColumn;
    @FXML
    private TableColumn<Intervalle, Integer> valeurMaxColumn;

    /**
     * Initializes the controller class.
     */

     navigation nav = new navigation();
     IntervalleDAO intervalleDAO = new IntervalleDAOImpl();
      
     private final ObservableList<Intervalle> listeIntervalle=FXCollections.observableArrayList();
    @FXML
    private Pane paneLibelle;
    @FXML
    private TextField txtLibelle;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         setColumnProperties();
         loadDetail();
    }    

    
      void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        valeurMinColumn.setCellValueFactory(new PropertyValueFactory<>("valeurMin"));
        valeurMaxColumn.setCellValueFactory(new PropertyValueFactory<>("valeurMax"));
    
     }
    
     void loadDetail(){
        
        listeIntervalle.clear();
        listeIntervalle.addAll(intervalleDAO.findAll());
        tableIntervale.setItems(listeIntervalle);
    }
    
    @FXML
    private void ajouterBtn(ActionEvent event) {
              Intervalle intervalle =new Intervalle();
     if(txtCode.getText().equalsIgnoreCase("")){
        msgCode.setText(Constantes.CHAMP_OBLIGATOIRE);
        txtCode.setStyle("-fx-border-color: red;");
     }else {
         
         int valeurMax = Integer.valueOf(txtValeurMax.getText());
           int valeurMin =  Integer.valueOf(txtValeurMin.getText());
           
       intervalle.setCode(txtCode.getText());
       intervalle.setValeurMax(valeurMax);
       intervalle.setValeurMin(valeurMin);
       intervalle.setLibelle("["+valeurMin+","+valeurMax+"]");
       intervalle.setEtat(Constantes.ETAT_COMMANDE_LANCE);
       intervalle.setUtilisateurCreation(nav.getUtilisateur());
      
        intervalleDAO.add(intervalle);
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
           setColumnProperties();
      loadDetail(); 
      
       txtCode.setText("");
        txtValeurMax.setText("");         
        txtValeurMin.setText("");
    }
    }

    
    @FXML
    private void ModifierBtn(ActionEvent event) {
          if (tableIntervale.getSelectionModel().getSelectedItem() != null) {
           int valeurMax = Integer.valueOf(txtValeurMax.getText());
           int valeurMin =  Integer.valueOf(txtValeurMin.getText());
              
            Intervalle intervalle = tableIntervale.getSelectionModel().getSelectedItem();
            
       intervalle.setCode(txtCode.getText());
       intervalle.setValeurMax(valeurMax);
       intervalle.setValeurMin(valeurMin);
       intervalle.setLibelle("["+valeurMin+","+valeurMax+"]");
 
            intervalleDAO.edit(intervalle);
      
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);
             setColumnProperties();
             loadDetail();
      
        txtCode.setText("");
        txtValeurMax.setText("");         
        txtValeurMin.setText("");
        
        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
        }
    }

    @FXML
    private void SupprimerBtn(ActionEvent event) {
         if(tableIntervale.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       Intervalle intervalle=tableIntervale.getSelectionModel().getSelectedItem();
          intervalle.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);

            intervalleDAO.edit(intervalle);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnProperties();
      loadDetail();  

         txtCode.setText("");
        txtValeurMax.setText("");         
        txtValeurMin.setText("");
    }
    }

    @FXML
    private void searche(KeyEvent event) {
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        txtCode.setText("");
        txtValeurMax.setText("");         
        txtValeurMin.setText("");
    }


    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
             Integer val= tableIntervale.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              txtCode.setText(codeColumn.getCellData(val));
              txtValeurMin.setText(valeurMinColumn.getCellData(val)+"");
              txtValeurMax.setText(valeurMaxColumn.getCellData(val)+"");

          }
    }

    @FXML
    private void rechercheCodeOnKeyPressed(KeyEvent event) {
        
                      ObservableList<Intervalle> listeIntervalle=FXCollections.observableArrayList();
          
        listeIntervalle.clear();
   
        listeIntervalle.addAll(intervalleDAO.findByCodeIntervalle(codeRechField.getText()));
   
        tableIntervale.setItems(listeIntervalle);
        
    }

    @FXML
    private void rechercheLibelleOnKeyPressed(KeyEvent event) {
        
                         ObservableList<Intervalle> listeIntervalle=FXCollections.observableArrayList();
          
        listeIntervalle.clear();
   
        listeIntervalle.addAll(intervalleDAO.findBylibelleIntervalle(libelleRechField.getText()));
   
        tableIntervale.setItems(listeIntervalle);
        
        
    }

    @FXML
    private void rechercheTableMouseClicked(MouseEvent event) {
        
                    libelleRechField.clear();
            codeRechField.clear();

            setColumnProperties();
            loadDetail();
        
    }
    
}
