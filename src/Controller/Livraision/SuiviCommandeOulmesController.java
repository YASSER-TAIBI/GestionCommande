/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Livraision;

import Controller.commande.EnvoyerCommandeController;
import Utils.Constantes;
import dao.Entity.BonLivraison;
import dao.Entity.Chauffeur;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.CompteFourMP;
import dao.Entity.DelaiPaiementFour;
import dao.Entity.Depot;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCompte;
import dao.Entity.DetailPromotion;
import dao.Entity.DetailReception;
import dao.Entity.Fournisseur;
import dao.Entity.Magasin;
import dao.Entity.MatierePremier;
import dao.Entity.PrixOulmes;
import dao.Entity.Promotion;
import dao.Entity.ReferencePromo;
import dao.Entity.Sequenceur;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.ChauffeurDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.DelaiPaiementFourDAO;
import dao.Manager.DepotDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailReceptionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MagasinDAO;
import dao.Manager.PromotionDAO;
import dao.Manager.ReferencePromoDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.StockArtDAO;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.ChauffeurDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DelaiPaiementFourDAOImpl;
import dao.ManagerImpl.DepotDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailReceptionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MagasinDAOImpl;
import dao.ManagerImpl.PromotionDAOImpl;
import dao.ManagerImpl.ReferencePromoDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.StockArtDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.CheckBox;
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
public class SuiviCommandeOulmesController implements Initializable {

      @FXML
    private TableView<Commande> tableCommande;
    @FXML
    private TableColumn<Commande, String> dateCreationColumn;
    @FXML
    private TableColumn<Commande, String> etatColumn;
    @FXML
    private TableView<DetailCommande> tableDetailCommande;
    @FXML
    private TableColumn<DetailCommande, String> codeArtColumn;
    @FXML
    private TableColumn<DetailCommande, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteRecuColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteRestantColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteLivreeColumn;
    @FXML
    private TableColumn<Commande, String> nComColumn;
    @FXML
    private Button btnValider;
    @FXML
    private TextField nLivraisonField;
    @FXML
    private DatePicker dateCreationPicker;
    @FXML
    private DatePicker dateLivraisonPicker;
    @FXML
    private TextField fournisseurField;
    @FXML
    private TextField clientField;
    @FXML
    private ComboBox<String> depotCombo;
    @FXML
    private ComboBox<String> magasinCombo;
    @FXML
    private TextField numComRechField;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRecu;
    @FXML
    private Button btnImprimerBc;
    @FXML
    private CheckBox checkDd;
    @FXML
    private CheckBox checkSc;
    @FXML
    private Button btnAnnuler;
    @FXML
    private TextField MotifTxt;
    @FXML
    private CheckBox annulationCheckBox;
    @FXML
    private ComboBox<String> matriculeCombo;
    @FXML
    private ComboBox<String> chauffeurCombo;
    
    
    private final ObservableList<DetailCommande> listeDetailCommande = FXCollections.observableArrayList();
    private final ObservableList<DetailCommande> listeDetailCommandeTMP = FXCollections.observableArrayList();
    private final ObservableList<DetailCommande> listeDetailCommandeBC = FXCollections.observableArrayList();
    private final ObservableList<Commande> listeCommande = FXCollections.observableArrayList();
    private final ObservableList<DetailPromotion> listeDetailPromotion = FXCollections.observableArrayList();
   
    List<Fournisseur> listeFour = new ArrayList<>();
    List<ClientMP> listeClient = new ArrayList<>();
    
    
    DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    CommandeDAO commandeDAO = new CommandeDAOImpl();
    DetailReceptionDAO detailReceptionDAO = new DetailReceptionDAOImpl();
    DepotDAO depotDAO = new DepotDAOImpl();
    MagasinDAO magasinDAO = new MagasinDAOImpl();
    BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
    ReferencePromoDAO referencePromoDAO = new ReferencePromoDAOImpl();
    PromotionDAO promotionDAO = new  PromotionDAOImpl();
    DelaiPaiementFourDAO delaiPaiementFourDAO = new DelaiPaiementFourDAOImpl();
    ChauffeurDAO chauffeurDAO = new ChauffeurDAOImpl();
    
    navigation nav = new navigation();
    Commande commande = new Commande();
    Fournisseur fournisseur = new Fournisseur();
    DetailCompte detailCompte = new DetailCompte();
    Promotion promotion = new Promotion();
    
    
    
    private Map<String, Depot> mapDepot = new HashMap<>();
    private Map<String, Magasin> mapMagasin = new HashMap<>();
    private Map<String, Fournisseur> mapFournisseur = new HashMap<>();
    private Map<String, ClientMP> mapClientMP = new HashMap<>();
    private Map<String,Chauffeur> mapChauffeur=new HashMap<>();
    
      String codeReception = "";
    
//    BigDecimal montantTotal = BigDecimal.ZERO;
//    public BigDecimal qteLivree =BigDecimal.ZERO;
    public BigDecimal montantDebit =BigDecimal.ZERO;
    

    
    
    
void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RECEPTION_PF_CODE);
          codeReception = Constantes.RECEPTION_PF_CODE+" "+(sequenceur.getValeur()+1);
   }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          try{
        setColumnProperties();
        loadDetail();

        Incrementation ();



        
        List<Depot> listDepot = depotDAO.findAll();

        listDepot.stream().map((depot) -> {
            depotCombo.getItems().addAll(depot.getLibelle());
            return depot;
        }).forEachOrdered((depot) -> {
            mapDepot.put(depot.getLibelle(), depot);
        });
        
        
           List<Chauffeur> listChauffeur=chauffeurDAO.findAll();
        
            listChauffeur.stream().map((chauffeur) -> {
            chauffeurCombo.getItems().addAll(chauffeur.getChauffeur());
            matriculeCombo.getItems().addAll(chauffeur.getMatricule());
            return chauffeur;
        }).forEachOrdered((chauffeur) -> {
            mapChauffeur.put(chauffeur.getChauffeur(), chauffeur);
            mapChauffeur.put(chauffeur.getMatricule(), chauffeur);
        });
        
        
           btnValider.setDisable(true);
        btnImprimer.setDisable(false);
        
        } catch (Exception e) {
                     nav.showAlert(Alert.AlertType.ERROR, "Attention", null, e.getMessage());
                }
        
    }    

      void setColumnProperties() {

        nComColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getnCommande());
            }

        });
        dateCreationColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));


    }

    void loadDetail() {

        listeCommande.clear();
        listeCommande.addAll(commandeDAO.findCommandeByEtat(Constantes.ETAT_COMMANDE_ENCOURS, Constantes.COMMANDE_INTERNE_ART ));
        tableCommande.setItems(listeCommande);
    }

    public void changeNomCellEvent(TableColumn.CellEditEvent editedCell) {

        Commande utilisateurSelected = tableCommande.getSelectionModel().getSelectedItem();

    }

    void setColumnPropertiesDetailCommande() {

        codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
            }
        });

        libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
            }

        });

        quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                    
                            DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                 
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });

        quantiteRecuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                        
                    DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
              
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRecu()));
                }
                
             });

        quantiteRestantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                    
                  DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRestee()));
                }
                
             });

        quantiteLivreeColumn.setCellValueFactory(new PropertyValueFactory<DetailCommande, BigDecimal>("quantiteLivree"));

        setColumnTextFieldConverter(getConverter(), quantiteLivreeColumn);

    }
    
    //--------------------------------------- Methode date paiement + date now = nombre de jour restant -------------------------------------------------------------------------------
      
    public int daysBetween(Date d1, Date d2){
             return (int)( (d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
             
     }
   
    //--------------------------------------- Methode date paiement + date now = nombre de jour restant -------------------------------------------------------------------------------
      
    public int daysBetween2(Date d1, Date d2){
             return  d1.compareTo(d2);
                     
                     }
   
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------         
    
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
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        
                setColumnPropertiesDetailCommande();
        listeDetailCommande.clear();
        
if (tableCommande.getSelectionModel().getSelectedIndex()!=-1){
    
        commande = tableCommande.getSelectionModel().getSelectedItem();

      listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
      
        tableDetailCommande.setItems(listeDetailCommande);
        
        tableDetailCommande.setEditable(true);

        fournisseurField.setText(commande.getFourisseur().getNom());
        
        clientField.setText(commande.getClientMP().getNom());
        
        chauffeurCombo.setValue(commande.getChauffeur().getChauffeur());
        
        matriculeCombo.setValue(commande.getChauffeur().getMatricule());
}

        
    }

    
    @FXML
    private void rechercheNumComOnKeyPressed(KeyEvent event) {
        
            
    listeCommande.clear();
   
   listeCommande.addAll(commandeDAO.findFourByRechercheNumCommandeOulmes(numComRechField.getText(),Constantes.ETAT_COMMANDE_ENCOURS));
   
   tableCommande.setItems(listeCommande);
        
    }

    @FXML
    private void creationDate(ActionEvent event) throws ParseException  {
        
           
          LocalDate localDate=dateCreationPicker.getValue();
             if(localDate!=null){
                 
             Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
             
            
              listeCommande.clear();
   
   listeCommande.addAll(commandeDAO.findByDateCommandeOulmes(dateSaisie,Constantes.ETAT_COMMANDE_ENCOURS));
   
   tableCommande.setItems(listeCommande);
             }
    }

    @FXML
    private void recuCommande(ActionEvent event) {
        
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                 tableDetailCommande.getItems().clear();
       if (tableCommande.getSelectionModel().getSelectedItem() != null) {

            commande=tableCommande.getSelectionModel().getSelectedItem();
         
            commande.setEtat(Constantes.ETAT_COMMANDE_RECU);
            commande.setDateRecu(new Date());
            commandeDAO.edit(commande);
            
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.COMMANDE_RECU); 
  
              setColumnPropertiesDetailCommande();
              loadDetail();
                      
           
       }else {
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE); 
       }
            }
    }


    @FXML
    private void imprimerBcOnAction(ActionEvent event) {
        
             try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SuiviCommandeOulmesController.class.getResource(nav.getiReportBonCommandeOulmes()));

            listeDetailCommandeBC.clear();
            
            para.put("Fournisseur",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getNom());
            para.put("NumCommande",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getnCommande());
            para.put("Ville",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getVille().getLibelle());
            para.put("DateLiv",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getDateCreation());
            
              if (listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getChauffeur()!=null){
                
            para.put("Matricule","Num Matricule:");
            para.put("NumMatricule",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getChauffeur().getMatricule());
            
            para.put("Chauffeur","Nom Chauffeur:");
            para.put("nomChauffeur",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getChauffeur().getChauffeur());
            
            }else if(listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getDateChargement()!=null){

             para.put("Chargement","Date Chargement:");
            para.put("DateChargement",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getDateChargement());
            }
    
          for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if(!detailCommande.getPrixOulmes().getProduit().getCode().equals("1500") && !detailCommande.getPrixOulmes().getProduit().getCode().equals("1504") && !detailCommande.getPrixOulmes().getProduit().getCode().equals("1503")){
 
                    listeDetailCommandeBC.add(detailCommande);
                
                }
            }

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailCommandeBC));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(SuiviCommandeOulmesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void editCommitQuantiteLivreeColumn(TableColumn.CellEditEvent<DetailCommande, BigDecimal> event) {
        
            if (nLivraisonField.getText().equalsIgnoreCase("") || depotCombo.getSelectionModel().getSelectedItem() == null || depotCombo.getSelectionModel().getSelectedItem().isEmpty() || matriculeCombo.getSelectionModel().getSelectedItem().isEmpty() || chauffeurCombo.getSelectionModel().getSelectedItem().isEmpty() ) {

            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
            tableDetailCommande.refresh();
        } else {

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
                    
                     Magasin magasin = mapMagasin.get(magasinCombo.getSelectionModel().getSelectedItem());
                    
                ((DetailCommande) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteLivree(event.getNewValue());
               
                
                    tableDetailCommande.refresh();  

  
                 qteLiv = quantiteLivreeColumn.getCellData(event.getTablePosition().getRow());
                
                 qteRestee =  listeDetailCommande.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).getQuantiteRestee();
                
                 qteRecu =  listeDetailCommande.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).getQuantiteRecu();
                
                 calculeQuantiteRecu = qteRecu.add(qteLiv).setScale(2, RoundingMode.FLOOR);
               
                 getCalculeQuantiteRestee =  qteRestee.subtract(qteLiv).setScale(2, RoundingMode.FLOOR);
                
                 listeDetailCommande.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).setMagasinn(magasin);
                 listeDetailCommande.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).setQuantiteRecu(calculeQuantiteRecu);
                 listeDetailCommande.get(tableDetailCommande.getSelectionModel().getSelectedIndex()).setQuantiteRestee(getCalculeQuantiteRestee);
                

                setColumnProperties();

           

            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailCommande.refresh();
            }
        }
        
    }

    @FXML
    private void btnValiderOnAction(ActionEvent event) throws ParseException {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
                try {
                    
               
                
          BigDecimal montantTotal= BigDecimal.ZERO;
          BigDecimal montantPalette= BigDecimal.ZERO;
            
          LocalDate localDate=dateLivraisonPicker.getValue();
            
                Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
                Chauffeur chauffeur  = mapChauffeur.get(chauffeurCombo.getSelectionModel().getSelectedItem());
                
                
           for( int rows = 0;rows<listeDetailCommande.size() ;rows++ ){
        

               DetailCommande detailCommande = listeDetailCommande.get(rows);

               if (detailCommande.getQuantiteLivree().compareTo(BigDecimal.ZERO)>0){
    
                  DetailReception detailReception = new DetailReception();
                  
                detailReception.setDetailCommande(detailCommande);
                detailReception.setDateReception(dateSaisie);
                detailReception.setChauffeur(chauffeur);
                detailReception.setUtilisateurCreation(nav.getUtilisateur());
                detailReception.setQuantiteRecu(detailCommande.getQuantiteLivree());
                detailReception.setPrix(detailCommande.getPrixUnitaire());
                
                          if (commande.getnCommande().contains("F")){

                               Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_F);

                             
                      detailReception.setLivraisonFour(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1));
                      
                      }else if(commande.getnCommande().contains("D")){
                          
                           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_D);
                      
                      detailReception.setLivraisonFour(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1));
                          
                      }else if ( checkDd.isSelected()== true){
                      
                           detailReception.setLivraisonFour(nLivraisonField.getText()+" "+"DD");
                      
                      }else if ( checkSc.isSelected()== true){
                      
                           detailReception.setLivraisonFour(nLivraisonField.getText()+" "+"SC");
                      
                      }else{
                      
                       detailReception.setLivraisonFour(nLivraisonField.getText());
                      }
                
                detailReception.setNumReception(codeReception);

                detailReceptionDAO.add(detailReception);
               
//------------------------------- Traitement Compte par Four ------------------------------------------------------------------------------------------------------------------------------------------
           
        PrixOulmes prixOulmes = detailCommande.getPrixOulmes();
        MatierePremier matierePremier = detailCommande.getMatierePremier();
        ReferencePromo referencePromo = referencePromoDAO.findByPrixOulmes(prixOulmes.getId());


       BigDecimal montant = BigDecimal.ZERO;
       BigDecimal montantRms = BigDecimal.ZERO;
       BigDecimal pu = BigDecimal.ZERO;
       BigDecimal remise = BigDecimal.ZERO;
       BigDecimal valeur = BigDecimal.ZERO;   
       
       if (referencePromo==null){
       
       valeur= BigDecimal.ZERO;
           
       }else {
       
       valeur= referencePromo.getDefautPromo();
       }
       
       
               remise = detailCommande.getRemiseAchat().add(valeur);
               pu =detailCommande.getPrixUnitaire();
               montant = pu.multiply(detailCommande.getQuantiteLivree()).setScale(2,RoundingMode.FLOOR);
               montantRms = montant.subtract((montant.multiply(remise)).divide(new BigDecimal(100)));
               
               
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------       
     
 
      
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();  
         
                    if (commande.getnCommande().contains("F")){
                          
                           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_F);
                      detailBonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1));
                      detailBonLivraison.setBonAvoir(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1));
                      
                    }else if(commande.getnCommande().contains("D")){
                      
                          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_D);
                      detailBonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1));
                      detailBonLivraison.setBonAvoir(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1));  
                      
                      }else if ( checkDd.isSelected()== true){
                      
                           detailBonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"DD");
                           detailBonLivraison.setBonAvoir(nLivraisonField.getText()+" "+"DD");
                           
                      }else if ( checkSc.isSelected()== true){
                      
                           detailBonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"SC");
                           detailBonLivraison.setBonAvoir(nLivraisonField.getText()+" "+"SC");
                      
                      }else{
                      
                        detailBonLivraison.setLivraisonFour(nLivraisonField.getText());
                        detailBonLivraison.setBonAvoir(nLivraisonField.getText());
                      }
                    
          detailBonLivraison.setMatierePremier(matierePremier);
          detailBonLivraison.setRemiseAchat(remise);
          detailBonLivraison.setPrixOulmes(prixOulmes);
          detailBonLivraison.setQuantite(detailCommande.getQuantiteLivree());
          detailBonLivraison.setMontant(montantRms);
          detailBonLivraison.setMotif(Constantes.SANS);
          detailBonLivraison.setDatedetailBonLivraison(dateSaisie);
          detailBonLivraison.setPrix(pu);
          detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          detailBonLivraison.setNumCommande(nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
          detailBonLivraison.setNumReception(codeReception);
          
                detailBonLivraisonDAO.add(detailBonLivraison);

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------       
                
                    if(fournisseurField.getText().equals(Constantes.FOURNISSEUR_OULMES) && prixOulmes.getProduit().getPalette()== Boolean.FALSE ){


                DetailPromotion detailPromotion = new DetailPromotion();
                
                detailPromotion.setPrixOulmes(prixOulmes);
                detailPromotion.setQuantite(detailCommande.getQuantiteLivree().multiply(referencePromo.getDefautPromo()).divide(new BigDecimal(100)));
                detailPromotion.setPrix(pu);
                detailPromotion.setRemiseDefaut(referencePromo.getDefautPromo());
                detailPromotion.setUtilisateurCreation(nav.getUtilisateur());
                detailPromotion.setMontant(pu.multiply(detailPromotion.getQuantite()));
                detailPromotion.setPromotion(promotion);
                
                listeDetailPromotion.add(detailPromotion);
                    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------       
                  
                if (detailBonLivraison.getPrixOulmes().getProduit().getPalette() == true){
                
                montantPalette = montantPalette.add(quantiteLivreeColumn.getCellData(rows).multiply(detailCommande.getPrixUnitaire()));
                
                }else{

                montantTotal = montantTotal.add((quantiteLivreeColumn.getCellData(rows).multiply(detailCommande.getPrixUnitaire())).subtract(((quantiteLivreeColumn.getCellData(rows).multiply(detailCommande.getPrixUnitaire())).multiply(remise)).divide(new BigDecimal(100))));  
                }

               }
    }
        
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//        String date ="00-00-0000 00:00:00";
//       
//        Date date1 = simpleDateFormat.parse(date);
        

         if (nLivraisonField.getText().equalsIgnoreCase("") || depotCombo.getSelectionModel().getSelectedItem() == null || depotCombo.getSelectionModel().getSelectedItem().isEmpty() || tableDetailCommande.getItems().isEmpty() /*|| depot.getText().equals("")*/) {
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
        } else {
   
String BL = null ;

                    if (commande.getnCommande().contains("F")){
                          
        
                          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_F);
                        BL=  nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1);
                      
                    }else if (commande.getnCommande().contains("D")){   
                        
                         Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_D);
                        BL=  nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1);
                          
                    }else if ( checkDd.isSelected()== true){
                      
                              BL= nLivraisonField.getText()+" "+"DD";
                      
                      }else if ( checkSc.isSelected()== true){
                      
                             BL= nLivraisonField.getText()+" "+"SC";
                      
                      }else if (!commande.getnCommande().contains("F") && !commande.getnCommande().contains("D") && !commande.getnCommande().contains("P") && checkDd.isSelected()== false && checkSc.isSelected()== false ) {
                      
                        BL= nLivraisonField.getText();
              
                      }

       
 BonLivraison bonLivraison = bonLivraisonDAO.findNumCommandeByNumLivraison(BL,commande.getnCommande());
   
   if (bonLivraison == null){
       
      
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
             System.out.println("montantTotal"+montantTotal.add(montantPalette));  
           
  
              montantTVA=  montantTotal.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantTotal).setScale(2,RoundingMode.FLOOR).add(montantPalette);
   
             System.out.println("montantTTC"+montantTTC);
         
             bonLivraison = new BonLivraison();

//--------------------------------------- Methode date livraison + periode = date paiement -------------------------------------------------------------------------------          
      
DelaiPaiementFour delaiPaiementFour = delaiPaiementFourDAO.findByFour(commande.getFourisseur().getNom());

         Calendar calendar = Calendar.getInstance();
         calendar.setTime(dateSaisie);   
         calendar.add(Calendar.DAY_OF_MONTH,delaiPaiementFour.getNbJour().intValue());
         Date dt=calendar.getTime();
            
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------           
         int nbrJr = (daysBetween(dt, new Date()));
         
            bonLivraison.setDate(new Date());
        
                      if (commande.getnCommande().contains("F")){
  
                             Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_F);
                          bonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1));
                          
                       }else if ( commande.getnCommande().contains("D") ){
                           
                            Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_D);
                          bonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1));
    
                      }else if ( checkDd.isSelected()== true){
                          
                               bonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"DD");
                      
                      }else if ( checkSc.isSelected()== true){

                               bonLivraison.setLivraisonFour(nLivraisonField.getText()+" "+"SC");
                      
                      }else if (!commande.getnCommande().contains("F") && !commande.getnCommande().contains("D") && !commande.getnCommande().contains("P") && checkDd.isSelected()== false && checkSc.isSelected()== false) {

                         bonLivraison.setLivraisonFour(nLivraisonField.getText());
                      }
            
            bonLivraison.setFournisseur(fournisseurField.getText());
            bonLivraison.setClient(clientField.getText());
            bonLivraison.setEtat(Constantes.ETAT_NON_REGLE);
            bonLivraison.setDateLivraison(dateSaisie);
            bonLivraison.setDatePaiement(dt);
            bonLivraison.setNombreJour(nbrJr);
            bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
            bonLivraison.setMontant(montantTotal.add(montantPalette));
            bonLivraison.setTypeBon(Constantes.ETAT_OULMES);
            bonLivraison.setMontantTVA(montantTVA);
            bonLivraison.setMontantTTC(montantTTC);
            bonLivraison.setMontantRG(montantTTC);
            bonLivraison.setAction(Boolean.FALSE);
            bonLivraison.setSansTVA(Boolean.FALSE);
            bonLivraison.setNumCommande(nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));

            
            
            bonLivraisonDAO.add(bonLivraison);
            
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Detail Compte // Montant Debit <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<     
            
              detailCompte = new DetailCompte();
                        
               detailCompte.setCompteFourMP(commande.getFourisseur().getCompteFourMP());
               detailCompte.setClientMP(commande.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setDateOperation(new Date());
               detailCompte.setDateBonLivraison(dateSaisie);
               
                      if (commande.getnCommande().contains("F")){

                             Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_F);
                      detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1)+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      
                      }else if ( commande.getnCommande().contains("D")){
                          
                             Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_D);
                      detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1)+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                          
                      }else if ( checkDd.isSelected()== true){

                      detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" DD_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      

                      
                      }else if ( checkSc.isSelected()== true){

                      detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" SC_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      

                      
                      }else{
                      
                      detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      
                      }
                         
               detailCompte.setCode(codeReception);
               detailCompte.setMontantDebit(BigDecimal.ZERO);
               detailCompte.setMontantCredit(montantTTC.setScale(2,RoundingMode.FLOOR));
        
               detailCompteDAO.add(detailCompte);            

   }else
   {

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Detail Compte // Montant TTC <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<           
       
       BigDecimal montantHT_TMP= BigDecimal.ZERO;
       BigDecimal montantTVA_TMP= BigDecimal.ZERO;
       BigDecimal montantTTC_TMP= BigDecimal.ZERO;
       
       
      montantTVA_TMP=  montantTotal.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
      montantHT_TMP = montantTotal.add(montantPalette).setScale(2,RoundingMode.FLOOR);
      montantTTC_TMP= montantTVA_TMP.add(montantHT_TMP).setScale(2,RoundingMode.FLOOR);
       
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<           
       
       BigDecimal montantHT = BigDecimal.ZERO;
       BigDecimal montantTVA= BigDecimal.ZERO;
       BigDecimal montantTTC= BigDecimal.ZERO;
       
       
       montantHT = montantTotal.add(montantPalette).add(bonLivraison.getMontant()).setScale(2,RoundingMode.FLOOR);
       montantTVA=  montantTotal.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);  
       montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
      
            bonLivraison.setMontant(montantHT);
            bonLivraison.setMontantTVA(montantTVA);
            bonLivraison.setAction(Boolean.FALSE);
            bonLivraison.setSansTVA(Boolean.FALSE);
            bonLivraison.setMontantTTC(montantTTC);
  
            bonLivraisonDAO.edit(bonLivraison);
            
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Detail Compte // Montant Debit <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<     



                        detailCompte = new DetailCompte();
                        
               detailCompte.setCompteFourMP(commande.getFourisseur().getCompteFourMP());
               detailCompte.setClientMP(commande.getClientMP());
               detailCompte.setUtilisateurCreation(nav.getUtilisateur());
               detailCompte.setDateOperation(new Date());
               detailCompte.setDateBonLivraison(dateSaisie);
               
                         if (commande.getnCommande().contains("F") ){
        
                             Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_F);
                       detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" BCS "+Constantes.ETAT_F+(sequenceur.getValeur()+1)+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      
                       }else if (commande.getnCommande().contains("D")){
                       
                             Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.ETAT_D);
                       detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" BCS "+Constantes.ETAT_D+(sequenceur.getValeur()+1)+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                           
                      }else if ( checkDd.isSelected()== true){
                      

                              detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" DD_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      
                      }else if ( checkSc.isSelected()== true){
                      

                              detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+" SC_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      
                      }else{
                      
                        detailCompte.setDesignation(Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+nLivraisonField.getText()+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+nComColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex()));
                      }
              detailCompte.setCode(codeReception);
               detailCompte.setMontantDebit(BigDecimal.ZERO);
               detailCompte.setMontantCredit(montantTTC_TMP.setScale(2,RoundingMode.FLOOR));
        
               detailCompteDAO.add(detailCompte);
            
   }
//===========================================================================================================================================================================================================================================================================

       if(!listeDetailPromotion.isEmpty()){

    Promotion promotionTmp = promotionDAO.findByNumCommandeAndNumLivraison(BL,commande.getnCommande());
   
   if (promotionTmp == null){
       
       promotion.setClient(clientField.getText());
       promotion.setFournisseur(fournisseurField.getText());
       promotion.setDateLivraison(dateSaisie);
       promotion.setDetailPromotion(listeDetailPromotion);
       promotion.setLivraisonFour(bonLivraison.getLivraisonFour());
       promotion.setNumCommande(bonLivraison.getNumCommande());
       promotion.setUtilisateurCreation(nav.getUtilisateur());
       
       promotionDAO.add(promotion);
       
       promotion = new Promotion();
       
   }else {
   
       promotion.setDetailPromotion(listeDetailPromotion);
       
       promotionDAO.edit(promotion);
   
   }
       }
//===========================================================================================================================================================================================================================================================================
                
        Fournisseur fournisseur = commande.getFourisseur();
        CompteFourMP compteFourMP = fournisseur.getCompteFourMP();

        commande.getFourisseur().setCompteFourMP(compteFourMP);
        
        commandeDAO.edit(commande);
            


           Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RECEPTION_PF_CODE);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();
           
//===========================================================================================================================================================================================================================================================================
            
   boolean valeur=false;
               
               for(int i=0;i<listeDetailCommande.size();i++){

                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if (!detailCommande.getQuantiteLivree().setScale(2).equals(BigDecimal.ZERO.setScale(2)))
                {
                    
                detailCommande.setQuantiteLivree(BigDecimal.ZERO.setScale(2));
                listeDetailCommande.set(i, detailCommande);
                detailCommandeDAO.edit(detailCommande);
                }
                
                if (!detailCommande.getQuantiteRestee().setScale(2).equals(BigDecimal.ZERO.setScale(2))){
                        
                        valeur= true;

                    }
                }
               
               if (commande.getnCommande().contains("F")){

                             Sequenceur sequenceurSPF = sequenceurDAO.findByCode(Constantes.ETAT_F);
           sequenceurSPF.setValeur(sequenceurSPF.getValeur()+1);
           sequenceurDAO.edit(sequenceurSPF);
                     
                      
                      }else if ( commande.getnCommande().contains("D")){
                          
                               Sequenceur sequenceurSPD = sequenceurDAO.findByCode(Constantes.ETAT_D);
           sequenceurSPD.setValeur(sequenceurSPD.getValeur()+1);
           sequenceurDAO.edit(sequenceurSPD);
                          
                      }
    
                if(valeur== false){
               
                     commande=tableCommande.getSelectionModel().getSelectedItem();
         
            commande.setEtat(Constantes.ETAT_COMMANDE_RECU);
            commande.setDateRecu(new Date());

            commandeDAO.edit(commande);
                 
  
              setColumnPropertiesDetailCommande();
              loadDetail();
               
               }
               
               
               boolean existe=false;
               
               for (int i =0;i<tableDetailCommande.getItems().size();i++)
               {            
                    commande.getDetailCommandes().get(i).setQuantiteLivree(BigDecimal.ZERO.setScale(2));
                    commandeDAO.edit(commande);
                     
                    if (quantiteRestantColumn.getCellData(i)!=(BigDecimal.ZERO.setScale(2))){
                        
                        existe= true;

                    }
                    
               } 
               
               if(existe== false){
               
                     commande=tableCommande.getSelectionModel().getSelectedItem();
         
            commande.setEtat(Constantes.ETAT_COMMANDE_RECU);
            commande.setDateRecu(new Date());

            commandeDAO.edit(commande);
                 
  
              setColumnPropertiesDetailCommande();
              loadDetail();
               
               }
               

                tableDetailCommande.refresh();
                listeDetailCommande.clear();
                listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
                tableDetailCommande.setItems(listeDetailCommande);
                
               btnValider.setDisable(true);
               btnImprimer.setDisable(false);
               nLivraisonField.clear();
               checkDd.setSelected(false);
               checkSc.setSelected(false);
               depotCombo.getSelectionModel().select(-1);
               dateLivraisonPicker.setValue(null);
               tableDetailCommande.setEditable(true);

            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.COMMANDE_VALIDER);
            
            btnImprimer.setDisable(false);
            btnValider.setDisable(true);
            
            tableDetailCommande.setEditable(false);
        
    } 
            } catch (Exception e) {
                    
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null, e.toString());
                    
                }
            }
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) throws ParseException {
        
              try {
               if (nLivraisonField.getText().isEmpty() || depotCombo.getSelectionModel().getSelectedItem() == null || depotCombo.getSelectionModel().getSelectedItem().isEmpty()) {

            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
            tableDetailCommande.refresh();
        } else {
              listeDetailCommandeTMP.clear();
              
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SuiviCommandeController.class.getResource(nav.getiReportBonReceptionOulmes()));

              LocalDate localDate=dateLivraisonPicker.getValue();
            
                Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
                  Depot depot = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
                  
                  Chauffeur chauffeur = mapChauffeur.get(chauffeurCombo.getSelectionModel().getSelectedItem());
            
            para.put("Fournisseur",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getNom());
            para.put("NumCommande",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getnCommande());
            para.put("Ville",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getFourisseur().getVille().getLibelle());
            para.put("Client",listeCommande.get(tableCommande.getSelectionModel().getSelectedIndex()).getClientMP().getNom());
            para.put("Depot",depot.getLibelle());
            para.put("NumLivraison",nLivraisonField.getText());
            para.put("DateLivraison",dateSaisie);
            para.put("NumReception",codeReception);
            
            para.put("Matricule","Num Matricule:");
            para.put("NumMatricule",chauffeur.getMatricule());
            
            para.put("Chauffeur","Nom Chauffeur:");
            para.put("nomChauffeur",chauffeur.getChauffeur());
            
            for(int i=0;i<listeDetailCommande.size();i++){
            
                DetailCommande detailCommande = listeDetailCommande.get(i);
                if (!detailCommande.getQuantiteLivree().setScale(2).equals(BigDecimal.ZERO.setScale(2)))
                {
                listeDetailCommandeTMP.add(detailCommande);
                }
            }
            
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailCommandeTMP));
               JasperViewer.viewReport(jp, false);
             
                btnImprimer.setDisable(false);
            btnValider.setDisable(false);
               }
        } catch (JRException ex) {
            Logger.getLogger(EnvoyerCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void depotComboOnAction(ActionEvent event) {
        
        
              magasinCombo.getItems().clear();
            Depot depot  = mapDepot.get(depotCombo.getSelectionModel().getSelectedItem());
            
            
            
            if(depot!=null){
            List<Magasin> listMagasin = magasinDAO.findByDepotOulmes(depot.getId());
            listMagasin.stream().map((magasin) -> {
                magasinCombo.getItems().addAll(magasin.getLibelle());
                return magasin;
            }).forEachOrdered((magasin) -> {
                mapMagasin.put(magasin.getLibelle(), magasin);
            });
    
    }
    }
    
    @FXML
    private void checkDdOnAction(ActionEvent event) {
        
        
    }

    @FXML
    private void annulerCommande(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
                if(annulationCheckBox.isSelected()== true){
                
               
       if (tableCommande.getSelectionModel().getSelectedItem() != null) {
           
            tableDetailCommande.getItems().clear();
            
            commande=tableCommande.getSelectionModel().getSelectedItem();
         
            commande.setEtat(Constantes.ETAT_COMMANDE_ANNULER);
            commande.setMotif(MotifTxt.getText());
            commandeDAO.edit(commande);
            
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.COMMANDE_RECU); 
  
              setColumnPropertiesDetailCommande();
              loadDetail();
                      
              annulationCheckBox.setSelected(false);
              MotifTxt.clear();
              MotifTxt.setDisable(true);
           
       }else {
                    nav.showAlert(Alert.AlertType.CONFIRMATION, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE); 
       }
       }else{
                
                    nav.showAlert(Alert.AlertType.CONFIRMATION, "Erreur", null, Constantes.VERIFIER_CASE_ANNULATION); 
                
                }
            }
    }

    @FXML
    private void annulationCheckBoxOnAction(ActionEvent event) {
        
        if (annulationCheckBox.isSelected()==true){
        
        MotifTxt.clear();
        MotifTxt.setDisable(false);
            
        }else{
        
        MotifTxt.clear();
        MotifTxt.setDisable(true);
        }
        
    }

    @FXML
    private void matriculeOnAction(ActionEvent event) {
        
                 Chauffeur chauffeur  = mapChauffeur.get(matriculeCombo.getSelectionModel().getSelectedItem());
         
          if(chauffeur!=null){

                         chauffeurCombo.setValue(chauffeur.getChauffeur());
          }

    }

    @FXML
    private void chauffeurOnAction(ActionEvent event) {
        
                  Chauffeur chauffeur  = mapChauffeur.get(chauffeurCombo.getSelectionModel().getSelectedItem());
         
          if(chauffeur!=null){

                         matriculeCombo.setValue(chauffeur.getMatricule());
          }
        
        
    }



    
}
