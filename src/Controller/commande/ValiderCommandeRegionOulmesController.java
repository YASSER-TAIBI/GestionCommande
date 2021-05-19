/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.commande;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.CommandeRegion;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCommandeRegion;
import dao.Entity.DetailReceptionRegion;
import dao.Entity.Fournisseur;
import dao.Entity.PrixOulmes;
import dao.Entity.Sequenceur;
import dao.Manager.ClientMPDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.CommandeRegionDAO;
import dao.Manager.DetailCommandeRegionDAO;
import dao.Manager.DetailReceptionRegionDAO;
import dao.Manager.DimensionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.CommandeRegionDAOImpl;
import dao.ManagerImpl.DetailCommandeRegionDAOImpl;
import dao.ManagerImpl.DetailReceptionRegionDAOImpl;
import dao.ManagerImpl.DimensionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
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
public class ValiderCommandeRegionOulmesController implements Initializable {

    @FXML
    private TableView<CommandeRegion> tableCommande;
    @FXML
    private TableColumn<CommandeRegion, String> nCommandeColumn;
    @FXML
    private TableColumn<CommandeRegion, Date> dateCreationColumn;
    @FXML
    private TableColumn<CommandeRegion, String> depotColumn;
    @FXML
    private TableColumn<CommandeRegion, String> etatColumn;
    @FXML
    private TableView<DetailCommandeRegion> tableDetailCommande;
    @FXML
    private TableColumn<DetailCommandeRegion, String> codeMPColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, BigDecimal> qteRecColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, BigDecimal> qteResColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, BigDecimal> qteLivColumn;
    @FXML
    private ComboBox<String> fornisseurCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private Button btnModifierDC;
    @FXML
    private Button btnModifier;
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField codeMPField;
    @FXML
    private DatePicker dateSaisie;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private TextField numMatriculeField;
    @FXML
    private DatePicker dateCharge;
    @FXML
    private TextField libelleField;
    /**
     * Initializes the controller class.
     */
    
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    
    
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    DetailCommandeRegionDAO detailCommandeRegionDAO = new DetailCommandeRegionDAOImpl(); 
    MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
    CommandeRegionDAO commandeRegionDAO = new CommandeRegionDAOImpl(); 
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
   SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
     CommandeDAO commandeDAO = new CommandeDAOImpl(); 
   DetailReceptionRegionDAO detailReceptionRegionDAO = new DetailReceptionRegionDAOImpl();
   
   
    private final ObservableList<DetailCommandeRegion> listeDetailCommandeRegion=FXCollections.observableArrayList();
    private final ObservableList<CommandeRegion> listeCommandeRegion=FXCollections.observableArrayList();
  private final ObservableList<DetailCommande> listeDetailCommande=FXCollections.observableArrayList();
       

  CommandeRegion commandeRegion = new CommandeRegion();
          Commande commande = new Commande();
           Date date = new  Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yy");
         
        navigation nav = new navigation();     
    
         BigDecimal montanTotal=BigDecimal.ZERO;

         String codeReceptionRegionOulmes = "";
    @FXML
    private Button btnImprimer;
         
         
         
           void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RECEPTION_REGION_PF);
          codeReceptionRegionOulmes = Constantes.RECEPTION_REGION_PF+" "+(sequenceur.getValeur()+1);
   }
  
         
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Incrementation (); 
        
          List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((clientMP) -> {
            clientCombo.getItems().addAll(clientMP.getNom());
            return clientMP;
        }).forEachOrdered((clientMP) -> {
            mapClientMP.put(clientMP.getNom(), clientMP);
        });
       
        
        List<Fournisseur> listFournisseur=fournisseurDAO.findAllPF();
        
        listFournisseur.stream().map((fournisseur) -> {
            fornisseurCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
        loadDetail();
        setColumnProperties();
        
        ajouterSaisie.setDisable(true);
    }    
    
    
 void setColumnProperties(){
        

          nCommandeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeRegion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeRegion, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getnCommande());
                }
                
             });
          dateCreationColumn.setCellValueFactory(new PropertyValueFactory<CommandeRegion, Date>("dateCreation"));
             
          depotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeRegion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeRegion, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDepot().getLibelle());
                }
                
             });
             
             etatColumn.setCellValueFactory(new PropertyValueFactory<CommandeRegion, String>("etat"));
        

        tableCommande.setEditable(false);
 
    }
 
      void loadDetail(){
        
        listeCommandeRegion.clear();
        listeCommandeRegion.addAll(commandeRegionDAO.findCommandeByEtatPF(Constantes.ETAT_COMMANDE_LANCE_REGION));
        tableCommande.setItems(listeCommandeRegion);
    }
 
          void setColumnPropertiesDetailCommande(){
        
          codeMPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeRegion, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });
          
          libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeRegion, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
             });
                      
          quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });
                
                qteRecColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRecu()));
                }
                
             });
                
                qteResColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeRegion, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRestee()));
                }
                
             });
                
             qteLivColumn.setCellValueFactory(new PropertyValueFactory<DetailCommandeRegion, BigDecimal>("quantiteLivree"));

             setColumnTextFieldConverter(getConverter(), qteLivColumn);
     
    }
     
       
           public static void setColumnTextFieldConverter(StringConverter converter, TableColumn... columns) {

        for (TableColumn tableColumn : columns) {

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        }
    }

    private StringConverter getConverter() {
        StringConverter<BigDecimal> converter = new StringConverter<BigDecimal>(){
            @Override
            public BigDecimal fromString(String string) {

                try {
                    BigDecimal valeur;
                    valeur= new BigDecimal(string);
                    
                    return valeur;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString(BigDecimal object) {

                if (object == null) {
                    return null;
                }
                return  String.valueOf(object);
            }
        };

        return converter;
    }
    

    @FXML
    private void afficherDetailOnMouse(MouseEvent event) {
        
            setColumnPropertiesDetailCommande();
          
            listeDetailCommandeRegion.clear();
            
            if (tableCommande.getSelectionModel().getSelectedIndex()!=-1){
                
            commandeRegion=tableCommande.getSelectionModel().getSelectedItem();
               
            listeDetailCommandeRegion.addAll(detailCommandeRegionDAO.findDetailCommandeByEtat(commandeRegion, Constantes.ETAT_AFFICHAGE));
            
            tableDetailCommande.setItems(listeDetailCommandeRegion);
            
            tableDetailCommande.setEditable(true);
 
                }
              
        
    }

      private void clear(){
          
        codeMPField.clear();
        libelleField.clear();
        quantiteField.clear();
        dateSaisie.setValue(null);
        dateCharge.setValue(null);
        numMatriculeField.clear();
        
    } 
    

    @FXML
    private void afficherChampsOnMouse(MouseEvent event) {
        
        clear();
        ajouterSaisie.setDisable(true);
           if (tableDetailCommande.getSelectionModel().getSelectedIndex()!=-1){
        if(
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null || 
                   clientCombo.getSelectionModel().getSelectedItem()==null         
     
                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.VERIFIER_FOURNISSEUR_CLIENT);
     }else {
            
       DetailCommandeRegion detailCommandeRegion=tableDetailCommande.getSelectionModel().getSelectedItem();

        if(listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).getCmdRegle().equals(Constantes.ETAT_CMNR) || detailCommandeRegion.equals(Constantes.ETAT_CMNR) ){
            
            
         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
           
            quantiteField.setText(df.format(detailCommandeRegion.getQuantite()));
            
            numMatriculeField.setText(detailCommandeRegion.getCommandeRegion().getnMatricule());
            
            LocalDate dateS = new java.util.Date( detailCommandeRegion.getCommandeRegion().getDateCreation().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
         
          if (detailCommandeRegion.getCommandeRegion().getDateChargement()!= null){
              
             LocalDate dateC = new java.util.Date( detailCommandeRegion.getCommandeRegion().getDateChargement().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                   
             dateCharge.setValue(dateC);
  
          }

            dateSaisie.setValue(dateS);
  
            codeMPField.setText(detailCommandeRegion.getPrixOulmes().getProduit().getCode());
            libelleField.setText(detailCommandeRegion.getPrixOulmes().getProduit().getLibelle());

            ajouterSaisie.setDisable(false);
            
        }else{
            
               ajouterSaisie.setDisable(true);
                
            }
        
    }}}

    @FXML
    private void fourOnAction(ActionEvent event) {
    }

    @FXML
    private void modifierDetailCommande(ActionEvent event) {
        
              loadDetail();
        setColumnProperties();
        

        ajouterSaisie.setDisable(true);
          clear();
          clientCombo.getSelectionModel().select(-1);
                  fornisseurCombo.getSelectionModel().select(-1);
          
          listeDetailCommandeRegion.clear();
        
    }

    @FXML
    private void ValiderCommande(ActionEvent event) {
        
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        
                  Boolean existe = false; 
                  for(int i=0;i<listeDetailCommandeRegion.size();i++){
            
                 DetailCommandeRegion detailCommandeRegion = listeDetailCommandeRegion.get(i);
                if (detailCommandeRegion.getQuantiteLivree().compareTo(BigDecimal.ZERO)>0) {
                    
                    existe = true;
                    break;
                }
                  }
                
                
          if( clientCombo.getSelectionModel().getSelectedItem().isEmpty()|| fornisseurCombo.getSelectionModel().getSelectedItem().isEmpty() || tableDetailCommande.getItems().isEmpty() ||  existe == false ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
     }
        else {
            
              BigDecimal montantTotalMP = BigDecimal.ZERO;
              BigDecimal montant = BigDecimal.ZERO;
              
       
              
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.COMMANDE_OULMES);
          String nComm = ((sequenceur.getValeur()+1)+"/21");
         
               for(int i=0;i<listeDetailCommandeRegion.size();i++){
            
                 DetailCommandeRegion detailCommandeRegion = listeDetailCommandeRegion.get(i);
                if (detailCommandeRegion.getQuantiteLivree().compareTo(BigDecimal.ZERO)>0)
                {
 
                           DetailCommande detailCommande = new DetailCommande();

            detailCommande.setDimension(detailCommandeRegion.getDimension());
            detailCommande.setMatierePremier(detailCommandeRegion.getMatierePremier());
            detailCommande.setQuantite(detailCommandeRegion.getQuantiteLivree());
            detailCommande.setCommande(commande);
            detailCommande.setRemiseAchat(detailCommandeRegion.getRemiseAchat());
            detailCommande.setPrixOulmes(detailCommandeRegion.getPrixOulmes());
            detailCommande.setQuantiteRestee(detailCommandeRegion.getQuantiteLivree());
            detailCommande.setQuantiteRecu(BigDecimal.ZERO);
            detailCommande.setQuantiteLivree(BigDecimal.ZERO);
            detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
            detailCommande.setUtilisateurCreation(nav.getUtilisateur());
            detailCommande.setPrixUnitaire(detailCommandeRegion.getPrixUnitaire());
           
             BigDecimal QteB= detailCommandeRegion.getQuantiteLivree();
             BigDecimal PrixB= detailCommandeRegion.getPrixUnitaire();
            
               montant= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montantTotalMP=montantTotalMP.add(montant).setScale(2,RoundingMode.FLOOR);
 
            listeDetailCommande.add(detailCommande);
 //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------         
         
                       DetailReceptionRegion detailReceptionRegion = new DetailReceptionRegion();
                  
                detailReceptionRegion.setDetailCommandeRegion(detailCommandeRegion);
                detailReceptionRegion.setClientMP(mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem()));
                detailReceptionRegion.setFourisseur(mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem()));
                detailReceptionRegion.setUtilisateurCreation(nav.getUtilisateur());
                detailReceptionRegion.setNumCommande(nComm);
                detailReceptionRegion.setQuantiteRecu(detailCommandeRegion.getQuantiteLivree());
                detailReceptionRegion.setPrix(detailCommandeRegion.getPrixUnitaire());
                detailReceptionRegion.setNumReceptionRegion(codeReceptionRegionOulmes);

                
                detailReceptionRegionDAO.add(detailReceptionRegion);
 
                
                
                }}
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
          
        CommandeRegion CommandeRegion=tableCommande.getSelectionModel().getSelectedItem();

        commande.setUtilisateurCreation(nav.getUtilisateur());
        commande.setDepot(CommandeRegion.getDepot());
        commande.setDateSaisie(new Date());
        commande.setDateCreation(CommandeRegion.getDateCreation());
        commande.setEtat(Constantes.ETAT_COMMANDE_LANCE);
        commande.setTypeCommande(Constantes.COMMANDE_INTERNE_ART);
        commande.setnCommande(nComm);
        commande.setDetailCommandes(listeDetailCommande);
        commande.setFourisseur(mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem()));
        commande.setClientMP(mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem()));
        commande.setPrixTotal(montantTotalMP);
        
        commandeDAO.add(commande);
     
        commande = new Commande();
        
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
   
         
  // Sequenceur Commande        
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           
  // Sequenceur Reception
           Sequenceur sequenceurRCP = sequenceurDAO.findByCode(Constantes.RECEPTION_REGION_PF);
           sequenceurRCP.setValeur(sequenceurRCP.getValeur()+1);
           sequenceurDAO.edit(sequenceurRCP);
           Incrementation ();
           
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------       

                boolean valeur=false;
               
               for(int i=0;i<listeDetailCommandeRegion.size();i++){

                DetailCommandeRegion detailCommandeRegion = listeDetailCommandeRegion.get(i);
                
               if (detailCommandeRegion.getQuantiteLivree().compareTo(BigDecimal.ZERO)>0)
                {
                    
                detailCommandeRegion.setCmdRegle(Constantes.ETAT_CMNR);
                detailCommandeRegion.setQuantiteLivree(BigDecimal.ZERO.setScale(2));
                listeDetailCommandeRegion.set(i, detailCommandeRegion);
                detailCommandeRegionDAO.edit(detailCommandeRegion);
                }
                
                if (detailCommandeRegion.getQuantiteRestee().compareTo(BigDecimal.ZERO)>0){
                        
                        valeur= true;

                    }
                }
               
                if(valeur== false){

            commandeRegion.setEtat(Constantes.ETAT_COMMANDE_LANCE);
            commandeRegionDAO.edit(commandeRegion);
         

 ajouterSaisie.setDisable(true);
 
          clear();
          
          clientCombo.getSelectionModel().select(-1);
                  fornisseurCombo.getSelectionModel().select(-1);
          
          listeDetailCommandeRegion.clear();
                }
        }
            }
        
    }

    @FXML
    private void qteByIntervalleOnAction(KeyEvent event) {
    }

    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
    }

    @FXML
    private void ajouterSaisieAction(ActionEvent event) {
   
    
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        
          DetailCommandeRegion  detailCommandeRegion =tableDetailCommande.getSelectionModel().getSelectedItem();

            if(listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).getCmdRegle().equals(Constantes.ETAT_CMR) || detailCommandeRegion.equals(Constantes.ETAT_CMR) ){
              
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.COMMANDE_DEJA_TRAITEE);
                    return;
              }else{

                     if(
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null || 
                   clientCombo.getSelectionModel().getSelectedItem()==null       
                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
           return;
     }else {
            
                         
           Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());

                         PrixOulmes prixOulmes = prixOulmesDAO.findPrixOulmesByFourAndArt(fournisseur.getNom(), detailCommandeRegion.getPrixOulmes().getProduit().getId(), Constantes.SANS, Constantes.SANS);
           
           
            BigDecimal  prixB= BigDecimal.ZERO ;
            
            if(prixOulmes==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                DetailCommandeRegion detailCommandeRegionTMP =tableDetailCommande.getSelectionModel().getSelectedItem();
                
                prixB= prixOulmes.getPrix();

             detailCommandeRegion.setCmdRegle(Constantes.ETAT_CMR);
             detailCommandeRegion.setCommandeRegion(commandeRegion);
             detailCommandeRegion.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeRegion.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeRegion.setPrixUnitaire(prixB);
           
             BigDecimal QteB= detailCommandeRegionTMP.getQuantite();
             BigDecimal PrixB= prixB;
            
             BigDecimal  montantB= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantB).setScale(2,RoundingMode.FLOOR);

            listeDetailCommandeRegion.set(tableDetailCommande.getSelectionModel().getSelectedIndex(), detailCommandeRegion);

               ajouterSaisie.setDisable(true);
            
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.CONFIRMATION_ENREGISTREMENT);
              
            
         }
}
    
            }
    }
    }
    
    
     @FXML
    private void editCommitQuantiteLivreeColumn(TableColumn.CellEditEvent<DetailCommandeRegion, BigDecimal> event) {
        
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    BigDecimal qteLiv = BigDecimal.ZERO;
                    BigDecimal qteRestee= BigDecimal.ZERO;
                    BigDecimal qteRecu= BigDecimal.ZERO;
                    BigDecimal calculeQuantiteRecu= BigDecimal.ZERO; 
                    BigDecimal getCalculeQuantiteRestee= BigDecimal.ZERO;
                    

                ((DetailCommandeRegion) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteLivree(event.getNewValue());
               
                
                    tableDetailCommande.refresh();  

  
                 qteLiv = qteLivColumn.getCellData(event.getTablePosition().getRow());
                
                 qteRestee =  listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).getQuantiteRestee();
                
                 qteRecu =  listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).getQuantiteRecu();
                
                 calculeQuantiteRecu = qteRecu.add(qteLiv).setScale(2, RoundingMode.FLOOR);
               
                 getCalculeQuantiteRestee =  qteRestee.subtract(qteLiv).setScale(2, RoundingMode.FLOOR);
                
                 listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).setQuantiteRecu(calculeQuantiteRecu);
                 listeDetailCommandeRegion.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).setQuantiteRestee(getCalculeQuantiteRestee);
                
                 
                 
                setColumnProperties();

           

            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailCommande.refresh();
//            }
        }
    }

  

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
            try{
                  if (tableCommande.getSelectionModel().getSelectedIndex()==-1) {

            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.VERIFICATION_SELECTION_LIGNE);
            tableDetailCommande.refresh();
        } else {
   
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ValiderCommandeRegionOulmesController.class.getResource(nav.getiReportValiderCommandeRegionOulmes()));

     para.put("NumCommande",listeCommandeRegion.get(tableCommande.getSelectionModel().getSelectedIndex()).getnCommande());
        
            para.put("DateLiv",listeCommandeRegion.get(tableCommande.getSelectionModel().getSelectedIndex()).getDateCreation());
            
              if (listeCommandeRegion.get(tableCommande.getSelectionModel().getSelectedIndex()).getnMatricule()!=null){
                
            para.put("Matricule","Num Matricule:");
            para.put("NumMatricule",listeCommandeRegion.get(tableCommande.getSelectionModel().getSelectedIndex()).getnMatricule());
            }
            
            if (listeCommandeRegion.get(tableCommande.getSelectionModel().getSelectedIndex()).getDateChargement()!=null){
                
            para.put("Chargement","Date Chargement:");
            para.put("DateChargement",listeCommandeRegion.get(tableCommande.getSelectionModel().getSelectedIndex()).getDateChargement());
            }
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailCommandeRegion));
               JasperViewer.viewReport(jp, false);

               }
        } catch (JRException ex) {
            Logger.getLogger(ValiderCommandeRegionOulmesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}