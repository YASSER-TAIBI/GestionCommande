/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.RetourGratuite;

import Controller.commande.EnvoyerCommandeController;
import Utils.Constantes;
import dao.Entity.BonRetour;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCompte;
import dao.Entity.Fournisseur;
import dao.Entity.Sequenceur;
import dao.Manager.BonRetourDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.BonRetourDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;



/**
 * FXML Controller class
 *
 * @author Hp
 */
public class RegularisationDeDifferenceDePrixController implements Initializable {

    @FXML
    private TextField nFactureField;
    @FXML
    private TextField paiementField;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnRefrech;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ToggleGroup plusOuMoins;
    @FXML
    private RadioButton plusRadio;
    @FXML
    private RadioButton moinsRadio;
    @FXML
    private TextField nTraiteField;
    @FXML
    private DatePicker dateCreation;
    @FXML
    private Button btnImprimer;
    
  Fournisseur fournisseur =new Fournisseur();
  ClientMP clientMP =new ClientMP();
  DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
  BonRetourDAO bonRetourDAO = new BonRetourDAOImpl();
 SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
  
  private ObservableList<BonRetour> listeBonRetour=FXCollections.observableArrayList();
 
   private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
   private Map<String,ClientMP> mapClientMP=new HashMap<>();
    
         navigation nav = new navigation();
         
         FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
         
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    
    
   String codeRegularisation = "";
   
  
    /**
     * Initializes the controller class.
     */
   
   void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.REGULARISATION_CODE);
          codeRegularisation = Constantes.REGULARISATION_CODE+" "+(sequenceur.getValeur()+1);
   }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
          Incrementation ();
          
          List<Fournisseur> listFournisseur=fournisseurDAO.findAll();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        

          List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((client) -> {
            clientCombo.getItems().addAll(client.getNom());
            return client;
        }).forEachOrdered((client) -> {
            mapClientMP.put(client.getNom(), client);
        });

         btnImprimer.setDisable(true);
        
        // TODO
    }    

    @FXML
    private void btnValiderOnAction(ActionEvent event) throws ParseException {
        
          if (paiementField.getText().equalsIgnoreCase("")|| nFactureField.getText().equalsIgnoreCase("") || clientCombo.getValue().isEmpty()|| fourCombo.getValue().isEmpty()
                   ) {
            
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                
        }else{
              
              listeBonRetour.clear();
              
                            LocalDate localDate=dateCreation.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
              
              
            Fournisseur  fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
            ClientMP  clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());

              
//################################################################################# BonRetour ######################################################################################################
              
          BonRetour bonRetour = new BonRetour();

          bonRetour.setDateCreation(date);
          bonRetour.setFournisseur(fournisseur.getNom());
          bonRetour.setClient(clientMP.getNom());
          bonRetour.setUtilisateurCreation(nav.getUtilisateur());
          bonRetour.setNumTraite(nTraiteField.getText());
          bonRetour.setNumRetour(codeRegularisation);
          bonRetour.setNumFacture(nFactureField.getText());

           if (plusRadio.isSelected()==true)
               {
            bonRetour.setType(Constantes.AUGMENTATION);
              bonRetour.setMontantPaye(new BigDecimal(paiementField.getText()));
               
               }else {
            bonRetour.setType(Constantes.DIMINUTION);
              bonRetour.setMontantPaye(new BigDecimal(paiementField.getText()).multiply(new BigDecimal(-1)));
               }
          
           listeBonRetour.add(bonRetour);
       
           bonRetourDAO.add(bonRetour);
              
//################################################################################# DetailCompte ######################################################################################################

               DetailCompte detailCompte = new DetailCompte();

               detailCompte.setCompteFourMP(fournisseur.getCompteFourMP());
               detailCompte.setDateOperation(new Date());
               detailCompte.setDateBonLivraison(date);
                
                 
               if (plusRadio.isSelected()==true)
               {
                detailCompte.setMontantDebit(new BigDecimal(paiementField.getText()));
                detailCompte.setMontantCredit(BigDecimal.ZERO);
                
                
                detailCompte.setDesignation(Constantes.REGULARISATION_AUGMENTATION_FACTURE+nFactureField.getText()+", "+Constantes.TRAITE_N+nTraiteField.getText());
               
               }else {
                detailCompte.setMontantDebit(new BigDecimal(paiementField.getText()).multiply(new BigDecimal(-1)));
                detailCompte.setMontantCredit(BigDecimal.ZERO);
                detailCompte.setDesignation(Constantes.REGULARISATION_DIMINUTION_FACTURE+nFactureField.getText()+", "+Constantes.TRAITE_N+nTraiteField.getText());
               }
               
             
                detailCompte.setCode(codeRegularisation);
                detailCompte.setUtilisateurCreation(nav.getUtilisateur());
                detailCompte.setClientMP(clientMP);

               detailCompteDAO.add(detailCompte);
               
                        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.OFFRE_ENREGISTREMENT);
                        
                           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.REGULARISATION_CODE);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();
                        
                         btnImprimer.setDisable(false);
                
                        }
        
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
        Clear();
    }

        void Clear() {

    fourCombo.getSelectionModel().isEmpty();
    clientCombo.getSelectionModel().isEmpty();
    plusRadio.setSelected(false);
    moinsRadio.setSelected(false);
    nFactureField.clear();
    nTraiteField.clear();
    paiementField.clear();
        
     listeBonRetour.clear();
    
    }
    
    
    @FXML
    private void fournisseurOnAction(ActionEvent event) {
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }

    @FXML
    private void plusRadioonAction(ActionEvent event) {
        
 
    }

    @FXML
    private void moinsRadioOnAction(ActionEvent event) {

    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
           ClientMP  clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
        
          try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(RegularisationDeDifferenceDePrixController.class.getResource(nav.getiReportRegularisationPrix()));

            para.put("Client",clientMP.getNom());
            para.put("NumFacture",nFactureField.getText());

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeBonRetour));
               JasperViewer.viewReport(jp, false);
               
               btnImprimer.setDisable(true);
            
        } catch (JRException ex) {
            Logger.getLogger(RegularisationDeDifferenceDePrixController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
     Clear();
        
        
    }
    
}
