/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.CompteFourMP;
import dao.Entity.DetailCompte;
import dao.Entity.Fournisseur;
import dao.Entity.Reglement;
import dao.Entity.Sequenceur;
import dao.Manager.ClientMPDAO;
import dao.Manager.CompteFourMPDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.ReglementDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CompteFourMPDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.ReglementDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ReglementReportController implements Initializable {

    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private TextField numFacture;
    @FXML
    private ComboBox<String> modeReglementCombo;
    @FXML
    private Pane paneView;
    @FXML
    private Button btnRegler;
    @FXML
    private TextField codeReglementField;
    @FXML
    private TextField numCheque;
    @FXML
    private Button btnInitialiser;
    @FXML
    private TextField montantReportClient;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField montantRegler;
    @FXML
    private TextField anneeField;
    
    ObservableList<String> modeReglementlist =FXCollections.observableArrayList(Constantes.ESPECE, Constantes.CHEQUE,Constantes.ORDER_DE_VIREMENT,Constantes.TRAITE);

    ReglementDAO reglementDAO= new ReglementDAOImpl();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    CompteFourMPDAO compteFourMPDAO = new CompteFourMPDAOImpl();
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();  
     
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    private Map<String,CompteFourMP> mapCompteFourMP=new HashMap<>();
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    
    navigation nav = new navigation();
    DetailCompte detailCompte = new DetailCompte();
    
    String valeur="";
   
    
    @FXML
    private DatePicker dateCreation;
    
   void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.REPORT_CODE);
          codeReglementField.setText(Constantes.REPORT_CODE+" "+(sequenceur.getValeur()+1));
   }
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//         int Maxid = reglementDAO.getMaxId();
//        codeReglementField.setText(nav.generCode(Constantes.REGLEMENT , Maxid));
        
        Incrementation ();

        modeReglementCombo.setItems(modeReglementlist);
        
            List<Fournisseur> listFournisseur=fournisseurDAO.findAllMp();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
          List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((clientMP) -> {
            clientCombo.getItems().addAll(clientMP.getNom());
            return clientMP;
        }).forEachOrdered((clientMP) -> {
            mapClientMP.put(clientMP.getNom(), clientMP);
        });
        
           paneView.setVisible(false);
           clientCombo.setDisable(true);
    }    

    @FXML
    private void fournisseurOnAction(ActionEvent event) {
       
        clientCombo.setDisable(false);
        
     
    }

    @FXML
    private void modeReglementOnAction(ActionEvent event) {
          valeur= modeReglementCombo.getSelectionModel().getSelectedItem();
            if (valeur == (Constantes.CHEQUE)||valeur == (Constantes.ORDER_DE_VIREMENT)||valeur == (Constantes.TRAITE)){
             paneView.setVisible(true);
            
             }else{
                  paneView.setVisible(false);
                
             }
    }

    @FXML
    private void reglerOnAction(ActionEvent event) throws ParseException {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
//           int Maxid = reglementDAO.getMaxId();

          if ( numFacture.getText().equalsIgnoreCase("") || modeReglementCombo.getSelectionModel().getSelectedItem() == null || modeReglementCombo.getSelectionModel().getSelectedItem().isEmpty()|| fourCombo.getSelectionModel().getSelectedItem() == null || fourCombo.getSelectionModel().getSelectedItem().isEmpty() ) {
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                
            } else{ 
              
                            LocalDate localDate=dateCreation.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
              
               Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
    
             Reglement reglement = new Reglement();
             reglement.setDate(date);
             reglement.setCodeReglement(codeReglementField.getText());
             reglement.setMontantReglement(new BigDecimal(montantRegler.getText()));       
             reglement.setClientMP(mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem()));
             reglement.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
             reglement.setNumFacture(numFacture.getText());
             reglement.setAction(Boolean.FALSE);
             reglement.setDesignation(Constantes.REGLEMENT_SUR_REPORT+anneeField.getText()+" "+Constantes.FACTURE_N+numFacture.getText());
             reglement.setUtilisateurCreation(nav.getUtilisateur());
             reglement.setNumCritique("###");
             reglement.setModeReglement(valeur);
             
             if (valeur== Constantes.CHEQUE ||valeur == (Constantes.ORDER_DE_VIREMENT)||valeur == (Constantes.TRAITE)){
             
             reglement.setNumCritique(numCheque.getText());
             }else{
                reglement.setNumCritique(" ");
             }

            reglementDAO.add(reglement);
            

              detailCompte = new DetailCompte();
              detailCompte.setDateOperation(new Date());
              detailCompte.setDateBonLivraison(date);
              detailCompte.setCode(codeReglementField.getText());
              detailCompte.setDesignation(Constantes.REGLEMENT_SUR_REPORT+anneeField.getText()+" "+Constantes.FACTURE_N+numFacture.getText());
              detailCompte.setMontantDebit(new BigDecimal(montantRegler.getText()));
              detailCompte.setMontantCredit(new BigDecimal(BigInteger.ZERO));
              detailCompte.setClientMP(mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem()));
              detailCompte.setCompteFourMP(fournisseur.getCompteFourMP());
              detailCompte.setUtilisateurCreation(nav.getUtilisateur());
              
               detailCompteDAO.add(detailCompte);
               
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.REGLER_ENREGISTREMENT);
          
//                Maxid = reglementDAO.getMaxId();
//        codeReglementField.setText(nav.generCode(Constantes.REGLEMENT , Maxid));
             
           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.REPORT_CODE);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();

               numFacture.clear();
               numCheque.clear();
               anneeField.clear();
               montantReportClient.clear();
               montantRegler.clear();
               modeReglementCombo.getSelectionModel().select(-1);
               clientCombo.getSelectionModel().clearSelection();
               fourCombo.getSelectionModel().clearSelection();
               clientCombo.setDisable(true);
               dateCreation.setValue(null);
//          }
          }}
    }

    @FXML
    private void InitialiserOnAction(ActionEvent event) {
        
         numFacture.clear();
               numCheque.clear();
               anneeField.clear();
               montantReportClient.clear();
               montantRegler.clear();
               modeReglementCombo.getSelectionModel().select(-1);
               clientCombo.getSelectionModel().clearSelection();
               fourCombo.getSelectionModel().clearSelection();
               clientCombo.setDisable(true);
               dateCreation.setValue(null);

    }

    @FXML
    private void clientOnAction(ActionEvent event) {
        
         ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem()); 
   Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()); 
   
   List<DetailCompte> listeDetailCompte= new ArrayList<>();
   
   listeDetailCompte.clear();
   
     if(clientMP != null || fournisseur != null){
         
   listeDetailCompte.addAll(detailCompteDAO.findClientByDetailCompte(Constantes.REPORT , clientMP.getId(),fournisseur.getCompteFourMP().getId()));
   
   BigDecimal  montantCredit=BigDecimal.ZERO;
   BigDecimal  montantDebit=BigDecimal.ZERO;
   BigDecimal  montantReport=BigDecimal.ZERO;
  
  
   
   for (int i =0;i<listeDetailCompte.size() ; i++){
    
         
             montantCredit = montantCredit.add(listeDetailCompte.get(i).getMontantCredit()); 

               
             montantDebit = montantDebit.add(listeDetailCompte.get(i).getMontantDebit());

         
         }
         
      montantReport = montantDebit.subtract(montantCredit);
 
         montantReportClient.setText(montantReport+"");
         
    }
    }

    
}
