/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Controller.RetourGratuite.ConsultationRegularisationController;
import Utils.Constantes;
import dao.Entity.AvanceFournisseur;
import dao.Entity.BonLivraison;
import dao.Entity.ClientMP;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailCompte;
import dao.Entity.Fournisseur;
import dao.Entity.MatierePremier;
import dao.Entity.PrixOulmes;
import dao.Entity.Sequenceur;
import dao.Manager.AvanceFournisseurDAO;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.AvanceFournisseurDAOImpl;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
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
public class AvanceFournisseurController implements Initializable {

    @FXML
    private TableView<AvanceFournisseur> avanceFourtable;
    @FXML
    private TableColumn<AvanceFournisseur, String> fourColumn;
    @FXML
    private TableColumn<AvanceFournisseur, String> clientColumn;
    @FXML
    private TableColumn<AvanceFournisseur, String> refColumn;
    @FXML
    private TableColumn<AvanceFournisseur, BigDecimal> montantColumn;
    @FXML
    private TableColumn<AvanceFournisseur, Date> dateAvanceColumn;
    @FXML
    private TableColumn<AvanceFournisseur, String> modeAvanceColumn;
    @FXML
    private TableColumn<AvanceFournisseur, String> numAvanceColumn;
    @FXML
    private TableColumn<AvanceFournisseur, String> etatColumn;
    @FXML
    private TableColumn<AvanceFournisseur, Boolean> actionColumn;
    @FXML
    private Button btnRefresh;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField montantField;
    @FXML
    private TextField refField;
    @FXML
    private TextField montantTotalField;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private DatePicker dateAvance;
    @FXML
    private TextField codeField;
    @FXML
    private ComboBox<String> fourRechCombo;
    @FXML
    private ComboBox<String> clientRechCombo;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button validerSaisie;
    @FXML
    private ComboBox<String> modeAvanceCombo;
    @FXML
    private Pane paneView;
    @FXML
    private TextField numAvance;
    @FXML
    private DatePicker dateEcheance;
    
    /**
     * Initializes the controller class.
     */
        private final ObservableList<AvanceFournisseur> listeAvanceFournisseur=FXCollections.observableArrayList();
        private final ObservableList<AvanceFournisseur> listeAvanceFournisseurTmp=FXCollections.observableArrayList();
        ObservableList<String> modeAvancelist =FXCollections.observableArrayList(Constantes.SANS,Constantes.ESPECE,Constantes.CHEQUE,Constantes.ORDER_DE_VIREMENT,Constantes.TRAITE);
    navigation nav = new navigation();
            

    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();    
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
     DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    AvanceFournisseurDAO avanceFournisseurDAO = new AvanceFournisseurDAOImpl();
    DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
    BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
    
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
      
    SimpleDateFormat dateFormat= new SimpleDateFormat("yy");
    
   
       String valeur=null;
    
    
      
         void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.AVANCE_CODE_MP);
          codeField.setText(Constantes.AVANCE_CODE_MP+" "+(sequenceur.getValeur()+1));
   }
      
          void IncrementationPF (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.AVANCE_CODE_PF);
          codeField.setText(Constantes.AVANCE_CODE_PF+" "+(sequenceur.getValeur()+1));
   }
         
          Sequenceur sequenceurMP = sequenceurDAO.findByCode(Constantes.REGLEMENT_AVANCE_MP);
          String ReglAvcMp = Constantes.REGLEMENT_AVANCE_MP+(sequenceurMP.getValeur()+1);
         
          Sequenceur sequenceurPF = sequenceurDAO.findByCode(Constantes.REGLEMENT_AVANCE_PF);
          String ReglAvcPf = Constantes.REGLEMENT_AVANCE_PF+(sequenceurPF.getValeur()+1);
          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
             loadDetail();
        setColumnProperties();
        
        modeAvanceCombo.setItems(modeAvancelist);
        
         List<Fournisseur> listFournisseur=fournisseurDAO.findAllPfAndMp();
        
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
     
        
         List<Fournisseur> listFournisseurRech=fournisseurDAO.findAllPfAndMp();
        
        listFournisseurRech.stream().map((fournisseur) -> {
            fourRechCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        

          List<ClientMP> listClientMPRech=clientMPDAO.findAll();
        
        listClientMPRech.stream().map((client) -> {
            clientRechCombo.getItems().addAll(client.getNom());
            return client;
        }).forEachOrdered((client) -> {
            mapClientMP.put(client.getNom(), client);
        });
        
        calculeMontant();
        
         paneView.setVisible(false);
    }    
    
      void setColumnProperties(){

           
        
            clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceFournisseur, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvanceFournisseur, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClientMP().getNom());
                }
             });
                    
        

            fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceFournisseur, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvanceFournisseur, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFourisseur().getNom());
                }
             });
        
            refColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceFournisseur, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvanceFournisseur, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getReference());
                }
             });
           
             modeAvanceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceFournisseur, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvanceFournisseur, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getModeAvance());
                }
             });
    
             numAvanceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceFournisseur, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvanceFournisseur, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumAvance());
                }
             });
             
            dateAvanceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceFournisseur, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<AvanceFournisseur, Date> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDateAvanceFour());
                }
             });
    

    
            montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceFournisseur, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvanceFournisseur, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
             
             
            etatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvanceFournisseur, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvanceFournisseur, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getEtat());
                }
             });
             
            actionColumn.cellValueFactoryProperty();
            actionColumn.setCellValueFactory((cellData) -> {
            AvanceFournisseur cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
            });
            
            actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
            actionColumn.setEditable(true);
      
          
            avanceFourtable.setEditable(true); 
}
    
       void loadDetail(){
           
        listeAvanceFournisseur.clear();
        listeAvanceFournisseur.addAll(avanceFournisseurDAO.findAll());
        avanceFourtable.setItems(listeAvanceFournisseur);
    }
    
    @FXML
    private void refresh(ActionEvent event) {
        
        clear();
        
    }

    void clear(){

             montantTotalField.clear();
             dateAvance.setValue(null);
             clientCombo.getSelectionModel().select(-1);
             fourCombo.getSelectionModel().select(-1);
             montantField.clear();
             refField.clear();
             codeField.clear();
             modeAvanceCombo.getSelectionModel().select(-1);
             numAvance.clear();
             dateEcheance.setValue(null);
             clientRechCombo.getSelectionModel().select(-1);
             fourRechCombo.getSelectionModel().select(-1);
             
             paneView.setVisible(false);
             
             loadDetail();
             setColumnProperties();
             
              calculeMontant();

}  
    
    
    @FXML
    private void fournisseurOnAction(ActionEvent event) {

          Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
        
          if(fournisseur!=null){
          
        if (fournisseur.getFournisseur().equals(Constantes.PRODUIT_FINI)){
        
           codeField.clear();
            
        IncrementationPF ();
        
        }else{
            
            codeField.clear();
            
         Incrementation();
        
        }
          }
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }


    @FXML
    private void ajouterSaisieAction(ActionEvent event) throws ParseException {
        
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
            
                if(refField.getText().equals("")|| 
                   montantField.getText().equals("")|| 
                   clientCombo.getSelectionModel().getSelectedIndex()==-1||
                   fourCombo.getSelectionModel().getSelectedIndex()==-1 ||
                   modeAvanceCombo.getSelectionModel().getSelectedIndex()==-1 ||
                   dateAvance.getValue() == null

                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
    }
else {
                    
                    ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());  
                    Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
                    
                    LocalDate localDate=dateAvance.getValue();
                    Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
                    
                    Date dateEche= null;  
                    String strDate = "";
                
                    LocalDate localDateTMP=dateEcheance.getValue();
               if (localDateTMP!=null){
                 dateEche=new SimpleDateFormat("yyyy-MM-dd").parse(localDateTMP.toString());  
                 strDate = localDateTMP.toString();
               }
                    
            AvanceFournisseur avanceFournisseur = new AvanceFournisseur();
                           
            avanceFournisseur.setClientMP(clientMP);
            avanceFournisseur.setFourisseur(fournisseur);
            avanceFournisseur.setDateAvanceFour(date);
            avanceFournisseur.setCode(codeField.getText());
            

            if (valeur.equals(Constantes.CHEQUE)|| valeur.equals(Constantes.ORDER_DE_VIREMENT) || valeur.equals(Constantes.TRAITE)){
             
             avanceFournisseur.setModeAvance(modeAvanceCombo.getSelectionModel().getSelectedItem());
             avanceFournisseur.setNumAvance(numAvance.getText());
             avanceFournisseur.setDateEcheance(dateEche);
                
            
             }else {
                  
                avanceFournisseur.setModeAvance(modeAvanceCombo.getSelectionModel().getSelectedItem());
                
             }

            avanceFournisseur.setEtat(Constantes.NON_TRAITE);
            avanceFournisseur.setAction(Boolean.FALSE);
            avanceFournisseur.setMontant(new BigDecimal(montantField.getText()));
            avanceFournisseur.setReference(refField.getText());
            avanceFournisseur.setUtilisateurCreation(nav.getUtilisateur());
            avanceFournisseur.setDateCreation(new Date());
            
            avanceFournisseurDAO.add(avanceFournisseur);

//############################################################################# Detail Compte #####################################################################################################################################################################
            
              DetailCompte detailCompte = new DetailCompte();

              detailCompte.setDateOperation(new Date());
              detailCompte.setDesignation(Constantes.AVANCE_N+codeField.getText()+Constantes.SUR_FOURNISSEUR+fournisseur.getNom().toUpperCase()+Constantes.SUR_REFERENCE+refField.getText());
              detailCompte.setMontantCredit(BigDecimal.ZERO);
              detailCompte.setDateBonLivraison(date);
              detailCompte.setMontantDebit(new BigDecimal(montantField.getText()));
              detailCompte.setCode(codeField.getText());
              detailCompte.setClientMP(clientMP);
              detailCompte.setCompteFourMP(fournisseur.getCompteFourMP());
              detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               
              detailCompteDAO.add(detailCompte);
               
                nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.REGLER_ENREGISTREMENT);
          
          
               if (fournisseur.getFournisseur().equals(Constantes.PRODUIT_FINI)){
        
           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.AVANCE_CODE_PF);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           IncrementationPF();
        
        }else{
            
           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.AVANCE_CODE_MP);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();
        }   
                
                
           
           
           
            clear();
            setColumnProperties();
            loadDetail();
            
                }
         }
}

    @FXML
    private void fournisseurRechOnAction(ActionEvent event) {
    }

    @FXML
    private void clientRechOnAction(ActionEvent event) {
    }

    void calculeMontant(){
    
         BigDecimal montantTotal = BigDecimal.ZERO;
        
     for (int i = 0; i < listeAvanceFournisseur.size(); i++) {
                          
                          AvanceFournisseur avanceFournisseur = listeAvanceFournisseur.get(i);
                          
                           montantTotal = montantTotal.add(avanceFournisseur.getMontant());
                          
                      }
           DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
            montantTotalField.setText(df.format(montantTotal));
    
    
    }
    
    
    @FXML
    private void recherchMouseClicked(MouseEvent event) {
     

                  if (fourRechCombo.getSelectionModel().getSelectedIndex()== -1 ||  
                clientRechCombo.getSelectionModel().getSelectedIndex()== -1   )
        {
        nav.showAlert(Alert.AlertType.ERROR, "Alert", null,Constantes.VERIFIER_FOURNISSEUR_CLIENT);
        }else{
              
                 setColumnProperties();
                  listeAvanceFournisseur.clear();
                  
       ClientMP clientMP = mapClientMP.get(clientRechCombo.getSelectionModel().getSelectedItem());
       Fournisseur fournisseur = mapFournisseur.get(fourRechCombo.getSelectionModel().getSelectedItem());
                
        listeAvanceFournisseur.addAll(avanceFournisseurDAO.findByClientAndFour(clientMP.getId(), fournisseur.getId()));
        avanceFourtable.setItems(listeAvanceFournisseur);

                     calculeMontant();
        }
        
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
            try {
                
                       listeAvanceFournisseurTmp.clear();
            
                for( int rows = 0;rows<avanceFourtable.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
             
              AvanceFournisseur avanceFournisseur = avanceFourtable.getItems().get(rows);
            
                listeAvanceFournisseurTmp.add(avanceFournisseur); 
         }
                
                }
                
                  if (listeAvanceFournisseurTmp.size()!= 0){
                
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(AvanceFournisseurController.class.getResource(nav.getiReportConsultationAvanceFournisseur()));

              para.put("montantTTC",montantTotalField.getText());
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeAvanceFournisseurTmp));
               JasperViewer.viewReport(jp, false);
               
                  }else{
                  
                  nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
                      
                  }
            
        } catch (JRException ex) {
            Logger.getLogger(AvanceFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }

    @FXML
    private void validerSaisieAction(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
            if (avanceFourtable.equals(null) || montantTotalField.getText().equals("") ){
        
            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);

        }else{
                   Fournisseur four = null;
               ClientMP client = null;
               Date dateSaisie = null;
              BigDecimal montantTotal= BigDecimal.ZERO;
              BigDecimal montantTTC= BigDecimal.ZERO;
              BigDecimal montantTVA = BigDecimal.ZERO;
                    for( int rows = 0;rows<avanceFourtable.getItems().size() ;rows++ ){
                         
               AvanceFournisseur avanceFournisseur= avanceFourtable.getItems().get(rows);
               
          if (avanceFournisseur.getAction()==true){

            avanceFournisseur.setEtat(Constantes.TRAITE);
            avanceFournisseur.setAction(Boolean.FALSE);
            avanceFournisseurDAO.edit(avanceFournisseur);
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          

            if (avanceFournisseur.getFourisseur().getFournisseur().equals(Constantes.MP)){
 
                MatierePremier matierePremier = matierePremiereDAO.findByCode(Constantes.CODE_MP);

                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(ReglAvcMp);
                detailBonLivraison.setMontant(avanceFournisseur.getMontant().divide(new BigDecimal(1.2),2,RoundingMode.FLOOR));
                detailBonLivraison.setNumCommande("AVC_MP_FOUR/20");
                detailBonLivraison.setPrix(BigDecimal.ZERO);
                detailBonLivraison.setQuantite(BigDecimal.ZERO);
                detailBonLivraison.setMatierePremier(matierePremier);
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);

                
              montantTotal = montantTotal.add(avanceFournisseur.getMontant().divide(new BigDecimal(1.2),2,RoundingMode.FLOOR));  
              montantTTC = montantTTC.add(avanceFournisseur.getMontant());
              
              four = avanceFournisseur.getFourisseur();
              client = avanceFournisseur.getClientMP();
              dateSaisie = avanceFournisseur.getDateAvanceFour();                
                
                }
                if (avanceFournisseur.getFourisseur().getFournisseur().equals(Constantes.PRODUIT_FINI)){
            
                PrixOulmes prixOulmes = prixOulmesDAO.findByCode(Constantes.CODE_MP);

                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(ReglAvcPf);
                detailBonLivraison.setMontant(avanceFournisseur.getMontant().divide(new BigDecimal(1.2),2,RoundingMode.FLOOR));
                detailBonLivraison.setNumCommande("AVC_PF_FOUR/20");
                detailBonLivraison.setPrix(BigDecimal.ZERO);
                detailBonLivraison.setNumFacture("XXX");
                detailBonLivraison.setRemiseAchat(BigDecimal.ZERO);
                detailBonLivraison.setQuantite(BigDecimal.ZERO);
                detailBonLivraison.setPrixOulmes(prixOulmes);
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);

                
              montantTotal = montantTotal.add(avanceFournisseur.getMontant().divide(new BigDecimal(1.2),2,RoundingMode.FLOOR));  
              montantTTC = montantTTC.add(avanceFournisseur.getMontant());
              
              four = avanceFournisseur.getFourisseur();
              client = avanceFournisseur.getClientMP();
              dateSaisie = avanceFournisseur.getDateAvanceFour();            
            
            }
          }
        } 
            
//_________________________________________________________________________ Bon Livraision __________________________________________________________________________________________________________________________

            if (four.getFournisseur().equals(Constantes.MP)){

          montantTVA = montantTotal.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);

          BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(four.getNom());
          bonLivraison.setMontant(montantTotal);
          bonLivraison.setLivraisonFour(ReglAvcMp);
          bonLivraison.setEtat(Constantes.ETAT_A_PAYER);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setClient(client.getNom());
          bonLivraison.setNumFacture("XXX");
          bonLivraison.setNumCommande("AVC_MP_FOUR/20");
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_AVANCE_FOUR_MP);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(dateSaisie);
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
        
            
             Sequenceur sequenceurMP = sequenceurDAO.findByCode(Constantes.REGLEMENT_AVANCE_MP);
            sequenceurMP.setValeur(sequenceurMP.getValeur()+1);
            sequenceurDAO.edit(sequenceurMP);
         
            ReglAvcMp = Constantes.REGLEMENT_AVANCE_MP+(sequenceurMP.getValeur()+1);
            
            }else {
            
            montantTVA = montantTotal.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);

          BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(four.getNom());
          bonLivraison.setMontant(montantTotal);
          bonLivraison.setLivraisonFour(ReglAvcPf);
          bonLivraison.setEtat(Constantes.ETAT_A_PAYER);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setClient(client.getNom());
          bonLivraison.setMontantRG(BigDecimal.ZERO);
          bonLivraison.setNumFacture("XXX");
          bonLivraison.setNumCommande("AVC_PF_FOUR/20");
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_AVANCE_FOUR_PF);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(dateSaisie);
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
        
            
            Sequenceur sequenceurPF = sequenceurDAO.findByCode(Constantes.REGLEMENT_AVANCE_PF);
            sequenceurPF.setValeur(sequenceurPF.getValeur()+1);
            sequenceurDAO.edit(sequenceurPF);
         
            ReglAvcPf = Constantes.REGLEMENT_AVANCE_PF+(sequenceurPF.getValeur()+1);
            
            }
            
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);    
            
            setColumnProperties();
            loadDetail();
             clear();
        
            }
        
    }}

    @FXML
    private void calculeMouseClicked(MouseEvent event) {
    }

    @FXML
    private void modeAvanceOnAction(ActionEvent event) {
        
              
         valeur= modeAvanceCombo.getSelectionModel().getSelectedItem();

         if (valeur!=null){
         
           if (valeur.equals(Constantes.CHEQUE)|| valeur.equals(Constantes.ORDER_DE_VIREMENT) || valeur.equals(Constantes.TRAITE)){
             paneView.setVisible(true);
            
             }else {
                  paneView.setVisible(false);
                
             }
         }
        
    }
        
    
    
}
