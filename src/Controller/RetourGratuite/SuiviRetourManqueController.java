/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.RetourGratuite;

import Utils.Constantes;
import dao.Entity.BonLivraison;
import dao.Entity.BonRetour;
import dao.Entity.ClientMP;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailBonRetour;
import dao.Entity.DetailReception;
import dao.Entity.Fournisseur;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.BonRetourDAO;
import dao.Manager.ClientMPDAO;
//import dao.Manager.CommandeDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailBonRetourDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailReceptionDAO;
import dao.Manager.FournisseurDAO;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.BonRetourDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
//import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailBonRetourDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailReceptionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
public class SuiviRetourManqueController implements Initializable {

    @FXML
    private TableView<BonRetour> tableBonRtr;
    @FXML
    private TableColumn<BonRetour, String> nRtrGrtMnqColumn;
    @FXML
    private TableColumn<BonRetour, String> nComColumn;
    @FXML
    private TableColumn<BonRetour, String> nLivColumn;
    @FXML
    private TableColumn<BonRetour, String> fourColumn;
    @FXML
    private TableColumn<BonRetour, String> clientColumn;
    @FXML
    private TableColumn<BonRetour, BigDecimal> montFactColumn;
    @FXML
    private TableColumn<BonRetour, String> typeBonColumn;
    @FXML
    private TableColumn<BonRetour, String> enStockColumn;
    @FXML
    private TableColumn<BonRetour, String> motifColumn;
    @FXML
    private TableColumn<BonRetour, String> etatColumn;
    @FXML
    private TableColumn<BonRetour, BigDecimal> montReglerColumn;
    @FXML
    private TableColumn<BonRetour, Boolean> actionColumn;
    @FXML
    private TableColumn<BonRetour, String> recepUsineColumn;
    
    @FXML
    private ComboBox<String> bonRetGraMnqCombo;
    
    @FXML
    private TableView<DetailBonRetour> tableDetailbonRtr;
    @FXML
    private TableColumn<DetailBonRetour, String> codeColumn;
    @FXML
    private TableColumn<DetailBonRetour, String> designationColumn;
    @FXML
    private TableColumn<DetailBonRetour, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailBonRetour, BigDecimal> prixColumn;
    @FXML
    private TableColumn<DetailBonRetour, BigDecimal> montantColumn;
    
    @FXML
    private RadioButton invaliderRadio;
    @FXML
    private TextField MotifTxt;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    @FXML
    private RadioButton noPaiementRadio;
    @FXML
    private RadioButton paiementRadio;
    @FXML
    private Button btnPayeBon;
    @FXML
    private ToggleGroup paiementRd;
//    private TextField prixTxt;
//    private TextField qteTxt;
//    private TextField totalTxt;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> respUsinCombo;
    @FXML
    private ComboBox<String> stockCombo;
    @FXML
    private ComboBox<String> etatCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField montantTotalField;
    @FXML
    private Label monHT;
    @FXML
    private Label monTVA;
    @FXML
    private Label monTTC;
    @FXML
    private CheckBox checkBonFrais;
    
    
     BigDecimal MHT = BigDecimal.ZERO ;
     BigDecimal MTVA = BigDecimal.ZERO ;
     BigDecimal MTTC = BigDecimal.ZERO ;
    
      BigDecimal M_Regler = BigDecimal.ZERO;
     
    /**
     * Initializes the controller class.
     */

    private final ObservableList<DetailBonRetour> listeDetailBonRetours =FXCollections.observableArrayList();
    
    private final ObservableList<BonRetour> listeBonRetours =FXCollections.observableArrayList();
     
    ObservableList<String> bonRetourGratuiteListe =FXCollections.observableArrayList(Constantes.RETOUR,Constantes.MANQUE);
    
    ObservableList<String> recpUsin =FXCollections.observableArrayList(Constantes.RECEPTION_TYPE,Constantes.USINE_TYPE);
    
    ObservableList<String> enStock =FXCollections.observableArrayList(Constantes.EN_STOCK_OUI,Constantes.EN_STOCK_NON);
    
    ObservableList<String> etat =FXCollections.observableArrayList(Constantes.ETAT_COMMANDE_LANCE,Constantes.ETAT_PAYEE,Constantes.RETOUR_EN_ATT_REGLEMENT,Constantes.ETAT_ANNULE,Constantes.INVALIDER);
   
    BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
    
    DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
    
    BonRetourDAO bonRetourDAO = new BonRetourDAOImpl();

    DetailBonRetourDAO detailBonRetourDAO = new DetailBonRetourDAOImpl();
     
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
     
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
      
    private Map<String,ClientMP> mapClient=new HashMap<>();
    
    Fournisseur fournisseur =new Fournisseur();
    
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    
    navigation nav = new navigation();  
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//        fourCombo.setDisable(true);
//        clientCombo.setDisable(true);
//        respUsinCombo.setDisable(true);
//        stockCombo.setDisable(true);
//        etatCombo.setDisable(true);
        
          bonRetGraMnqCombo.setItems(bonRetourGratuiteListe);
          respUsinCombo.setItems(recpUsin);
          stockCombo.setItems(enStock);
          etatCombo.setItems(etat);
    
                  List<Fournisseur> listFournisseur=fournisseurDAO.findAllMp();
        
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
            mapClient.put(client.getNom(), client);
        });
        
        
        setColumnProperties();
        loadDetail();

        checkBonFrais.setVisible(false);
    }    

     void loadDetail() {
     
     listeBonRetours.clear();
         
     listeBonRetours.addAll(bonRetourDAO.findTypeByRechercheBonRetourMP(Constantes.RETOUR, Constantes.MANQUE));

     tableBonRtr.setItems(listeBonRetours); 
     
     }
    
    void setDetailColumnProperties() {

      
      codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
      
       designationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });
        
       qteColumn.setCellValueFactory(new PropertyValueFactory<DetailBonRetour, BigDecimal>("quantiteRetour"));
        
       prixColumn.setCellValueFactory(new PropertyValueFactory<DetailBonRetour, BigDecimal>("prixUnitaire"));
        
       montantColumn.setCellValueFactory(new PropertyValueFactory<DetailBonRetour, BigDecimal>("montant"));

    }
    

     void setColumnProperties() {

      
      nRtrGrtMnqColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumRetour());
                }
             });
      
       nComColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumCommande());
                }
             });
        
        nLivColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getnLivraison());
                }
             });
       
        fourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFournisseur());
                }
             });
       
        clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClient());
                }
             });
        
        montFactColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<BonRetour, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMontantTotal());
                }
             });
        
        typeBonColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getType());
                }
             });
         
        motifColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMotif());
                }
             });
        

        
//________________________________________________________________________ Charge ComboBox in TableView _________________________________________________________________________________________________________________________________________________________________________________________________________
 
          enStockColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getEnStock());
                }
             }); 
   
        ObservableList<String> listStock = FXCollections.observableArrayList();
        
        listStock.add("Oui");
        listStock.add("Non");
     

        enStockColumn.setCellFactory(ComboBoxTableCell.forTableColumn(listStock));
//_____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
     
        
        
        
        recepUsineColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getReceptionOrUsine());
                }
             }); 
        
          etatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BonRetour, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<BonRetour, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getEtat());
                }
             });
     
          montReglerColumn.setCellValueFactory(new PropertyValueFactory<BonRetour, BigDecimal>("montantRegler"));
          setColumnTextFieldConverter(getConverter(), montReglerColumn);
          
        actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          BonRetour cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionColumn.setEditable(true);
      
          
          tableBonRtr.setEditable(true);
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
    private void refrechOnAction(ActionEvent event) {
        
        
//        fourCombo.setDisable(true);
//        clientCombo.setDisable(true);
//        respUsinCombo.setDisable(true);
//        stockCombo.setDisable(true);
//        etatCombo.setDisable(true);
        
        MotifTxt.setDisable(true);
        
        paiementRadio.setSelected(Boolean.FALSE);
        noPaiementRadio.setSelected(Boolean.FALSE);
        invaliderRadio.setSelected(Boolean.FALSE);
        
//        qteTxt.clear();
//        prixTxt.clear();
//        totalTxt.clear();
        
        MotifTxt.clear();
        montantTotalField.clear();
        listeBonRetours.clear();
        listeDetailBonRetours.clear();
        tableDetailbonRtr.getItems().clear();
        tableBonRtr.getItems().clear();
        bonRetGraMnqCombo.getSelectionModel().select(-1);
        stockCombo.getSelectionModel().select(-1);
        respUsinCombo.getSelectionModel().select(-1);
        fourCombo.getSelectionModel().select(-1);
        clientCombo.getSelectionModel().select(-1);
        etatCombo.getSelectionModel().select(-1);
        
        checkBonFrais.setVisible(false);
        checkBonFrais.setSelected(false);
        
        setColumnProperties();
        loadDetail();
    }

    @FXML
    private void recherchMouseClicked(MouseEvent event) {
        
        listeBonRetours.clear();
        listeDetailBonRetours.clear();
        tableDetailbonRtr.getItems().clear();
        tableBonRtr.getItems().clear();
        
       String bonRetGra = bonRetGraMnqCombo.getSelectionModel().getSelectedItem();
       String respUsin = respUsinCombo.getSelectionModel().getSelectedItem();
       String stock = stockCombo.getSelectionModel().getSelectedItem();
       Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
       String etat = etatCombo.getSelectionModel().getSelectedItem();
       ClientMP clientMP = mapClient.get(clientCombo.getSelectionModel().getSelectedItem());
       
             if (bonRetGraMnqCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 respUsinCombo.getSelectionModel().getSelectedIndex()==-1 &&
                 fourCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 clientCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 stockCombo.getSelectionModel().getSelectedIndex()== -1 &&
                 etatCombo.getSelectionModel().getSelectedIndex()== -1)
        {
        nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.VERIFIER_BON_GRATUITE_BON_ROUTEUR);
        
        }else{
             
         String req = "";
         
        if(bonRetGraMnqCombo.getSelectionModel().getSelectedIndex()!= -1) {
             
              req= req+" and c.type='"+bonRetGra+"'";
              
             }
             
        if(fourCombo.getSelectionModel().getSelectedIndex()!= -1){
             
             req= req+" and c.fournisseur='"+fournisseur.getNom()+"'";

             }
             
        if(etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
               req= req+" and c.etat='"+etat+"'";
      
             }
         
         
         
        if(respUsinCombo.getSelectionModel().getSelectedIndex()!= -1){
           
               req= req+" and c.receptionOrUsine='"+respUsin+"'";
             
             }
             
        if(stockCombo.getSelectionModel().getSelectedIndex() != -1){
             
            req= req+" and c.enStock='"+stock+"'";
        
             
             }
             
        if(clientCombo.getSelectionModel().getSelectedIndex() != -1){
             
            req= req+" and c.client='"+clientMP.getNom()+"'";
             
             }
        
        
                     listeBonRetours.clear();
             
            listeBonRetours.addAll(bonRetourDAO.findBonRetourByFilterMP(Constantes.RETOUR, Constantes.MANQUE,req));

        tableBonRtr.setItems(listeBonRetours); 
        
        
        
        
        
        
             }
    }

    @FXML
    private void payeBonOnAction(ActionEvent event) {
        
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
      
             BonRetour bonRetour = tableBonRtr.getSelectionModel().getSelectedItem(); 
             
             if (!bonRetour.getEtat().equals(Constantes.ETAT_COMMANDE_LANCE)){
                 
                nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.SELECTION_ERREUR);
                
                 return;
             }else{
             
             if (invaliderRadio.isSelected()==true){
             
                 
                 bonRetour.setMotif(MotifTxt.getText());
                 bonRetour.setEtat(Constantes.INVALIDER);
                 bonRetourDAO.edit(bonRetour);
                 
                 nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
           
                    refrechOnAction(event);
             
             
             }else{
                 
             if (bonRetour.getType().equals(Constantes.RETOUR)){

                if (paiementRadio.isSelected()==true){
                        
                    if(bonRetour.getMontantRegler().compareTo(bonRetour.getMontantTotal())!=0){
//___________________________________________________________________________________ Bon Livraision __________________________________________________________________________________________

            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT= bonRetour.getMontantRegler();
            
             System.out.println("montantHT"+montantHT);  
           
            montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
             System.out.println("montantTTC"+montantTTC);

    BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(bonRetour.getFournisseur());
          bonLivraison.setMontant(montantHT);
          bonLivraison.setNumCommande(bonRetour.getNumCommande());
          bonLivraison.setLivraisonFour(bonRetour.getNumRetour());
          bonLivraison.setEtat(Constantes.ETAT_NON_PAIEMENT);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setClient(bonRetour.getClient());
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_RTR);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(new Date());
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
          
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
   List<DetailBonRetour> listDetailBonRetour = detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId());
   
           for (int i = 0; i < listDetailBonRetour.size(); i++) {
                
                DetailBonRetour detailBonRetour = listDetailBonRetour.get(i);
                
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(detailBonRetour.getBonRetour().getNumRetour());
                detailBonLivraison.setMontant(detailBonRetour.getMontant());
                detailBonLivraison.setPrix(detailBonRetour.getPrixUnitaire());
                detailBonLivraison.setQuantite(detailBonRetour.getQuantiteRetour());
                detailBonLivraison.setNumCommande(detailBonRetour.getBonRetour().getNumCommande());
                detailBonLivraison.setMatierePremier(detailBonRetour.getMatierePremier());
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);

//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
                  
                
                
            }
                 bonRetour.setMontantTotal(bonRetour.getMontantTotal().subtract(bonRetour.getMontantRegler()));
                 bonRetour.setEtat(Constantes.ETAT_ANNULE);
                 bonRetourDAO.edit(bonRetour);
                 
                 nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
           
                    refrechOnAction(event);
               
                    }else if (bonRetour.getMontantRegler().compareTo(bonRetour.getMontantTotal())==0){
                    
//___________________________________________________________________________________ Bon Livraision __________________________________________________________________________________________

            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT= bonRetour.getMontantTotal();
            
             System.out.println("montantHT"+montantHT);  
           
            montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
             System.out.println("montantTTC"+montantTTC);

    BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(bonRetour.getFournisseur());
          bonLivraison.setMontant(montantHT);
          bonLivraison.setNumCommande(bonRetour.getNumCommande());
          bonLivraison.setLivraisonFour(bonRetour.getNumRetour());
          bonLivraison.setEtat(Constantes.ETAT_NON_PAIEMENT);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setClient(bonRetour.getClient());
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_RTR);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(new Date());
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
          
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
   List<DetailBonRetour> listDetailBonRetour = detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId());
   
           for (int i = 0; i < listDetailBonRetour.size(); i++) {
                
                DetailBonRetour detailBonRetour = listDetailBonRetour.get(i);
                
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(detailBonRetour.getBonRetour().getNumRetour());
                detailBonLivraison.setMontant(detailBonRetour.getMontant());
                detailBonLivraison.setPrix(detailBonRetour.getPrixUnitaire());
                detailBonLivraison.setQuantite(detailBonRetour.getQuantiteRetour());
                detailBonLivraison.setNumCommande(detailBonRetour.getBonRetour().getNumCommande());
                detailBonLivraison.setMatierePremier(detailBonRetour.getMatierePremier());
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);

            }
           
                 bonRetour.setEtat(Constantes.RETOUR_EN_ATT_REGLEMENT);
                 bonRetourDAO.edit(bonRetour);
                 
                 nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
           
                    refrechOnAction(event);
                    
                    }
                }else if (noPaiementRadio.isSelected()==true){
            
         bonRetour.setEtat(Constantes.ETAT_ANNULE);
         bonRetourDAO.edit(bonRetour);
           
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
              
                    refrechOnAction(event);
              
          
                      }
        }else if (bonRetour.getType().equals(Constantes.MANQUE)){
                 
                if (paiementRadio.isSelected()==true){
                       
 //___________________________________________________________________________________ Bon Livraision __________________________________________________________________________________________

            BigDecimal montantHT = BigDecimal.ZERO;
            BigDecimal montantTVA = BigDecimal.ZERO;
            BigDecimal montantTTC = BigDecimal.ZERO;
       
            montantHT= bonRetour.getMontantTotal();
            
             System.out.println("montantHT"+montantHT);  
           
              montantTVA=  montantHT.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
                  
             System.out.println("montantTVA"+montantTVA);
             
              montantTTC= montantTVA.add(montantHT).setScale(2,RoundingMode.FLOOR);
   
             System.out.println("montantTTC"+montantTTC);


    BonLivraison bonLivraison = new BonLivraison();

          bonLivraison.setFournisseur(bonRetour.getFournisseur());
          bonLivraison.setMontant(montantHT);
          bonLivraison.setNumCommande(bonRetour.getNumCommande());
          bonLivraison.setLivraisonFour(bonRetour.getNumRetour());
          bonLivraison.setEtat(Constantes.ETAT_NON_PAIEMENT);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          bonLivraison.setClient(bonRetour.getClient());
          bonLivraison.setDate(new Date());  
          bonLivraison.setNombreJour(30);
          bonLivraison.setTypeBon(Constantes.ETAT_MNQ);
          bonLivraison.setUtilisateurCreation(nav.getUtilisateur());
          bonLivraison.setAction(Boolean.FALSE);
          bonLivraison.setSansTVA(Boolean.FALSE);
          bonLivraison.setDateLivraison(new Date());
          bonLivraison.setDatePaiement(new Date());
          
          
          bonLivraisonDAO.add(bonLivraison);
          
//___________________________________________________________________________________ Detail Bon Livraision __________________________________________________________________________________________       
          
   List<DetailBonRetour> listDetailBonRetour = detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId());
   
           for (int i = 0; i < listDetailBonRetour.size(); i++) {
                
                DetailBonRetour detailBonRetour = listDetailBonRetour.get(i);
                
                DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
                
                detailBonLivraison.setLivraisonFour(detailBonRetour.getBonRetour().getNumRetour());
                detailBonLivraison.setMontant(detailBonRetour.getMontant());
                detailBonLivraison.setPrix(detailBonRetour.getPrixUnitaire());
                detailBonLivraison.setQuantite(detailBonRetour.getQuantiteRetour());
                detailBonLivraison.setNumCommande(detailBonRetour.getBonRetour().getNumCommande());
                detailBonLivraison.setMatierePremier(detailBonRetour.getMatierePremier());
                detailBonLivraison.setUtilisateurCreation(nav.getUtilisateur());
                detailBonLivraison.setDatedetailBonLivraison(new Date());
         
                
                detailBonLivraisonDAO.add(detailBonLivraison);
          
            }
           
                 bonRetour.setEtat(Constantes.RETOUR_EN_ATT_REGLEMENT);
                 bonRetourDAO.edit(bonRetour);
                 
                 nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
           
                    refrechOnAction(event);
                 
                }else if (noPaiementRadio.isSelected()==true){
            
         bonRetour.setEtat(Constantes.ETAT_ANNULE);
         bonRetourDAO.edit(bonRetour);
           
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.AJOUTER_ENREGISTREMENT);
           
                    refrechOnAction(event);
              
                }  
        }
             }
            }
            }
    }
  

    @FXML
    private void afficherDetailBonRetourOnMouseClicked(MouseEvent event) {
        
        if (tableBonRtr.getSelectionModel().getSelectedIndex()!=-1){
            
        MotifTxt.clear();
        MotifTxt.setDisable(true);
            
        paiementRadio.setSelected(Boolean.FALSE);
        noPaiementRadio.setSelected(Boolean.FALSE);
        invaliderRadio.setSelected(Boolean.FALSE);
            
      BonRetour bonRetour = tableBonRtr.getSelectionModel().getSelectedItem(); 
            
       listeDetailBonRetours.clear();
          
       listeDetailBonRetours.addAll(detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId()));

       tableDetailbonRtr.setItems(listeDetailBonRetours);
  
       setDetailColumnProperties();
  
       
       if(bonRetour.getType().equals(Constantes.RETOUR)){
        montReglerColumn.setEditable(true);
       }else{
        montReglerColumn.setEditable(false);
       }
       
    }
        
    }

//    private void calculeTotalMouseClicked(MouseEvent event) {
//        
//           if (tableDetailbonRtr.getSelectionModel().getSelectedIndex()!=-1){
//               
//               BonRetour bonRetour = tableBonRtr.getSelectionModel().getSelectedItem(); 
//               
//               BigDecimal newMontant = BigDecimal.ZERO;
//               
//               newMontant = new BigDecimal(prixTxt.getText()).multiply(new BigDecimal(qteTxt.getText()));
//               
//        DetailBonRetour detailBonRetour = tableDetailbonRtr.getSelectionModel().getSelectedItem();
//        
//        detailBonRetour.setPrixUnitaire(new BigDecimal(prixTxt.getText()));
//        detailBonRetour.setMontant(newMontant);
//        
//        detailBonRetourDAO.edit(detailBonRetour);
//        
//        bonRetour.setMontantTotal(newMontant);
//        
//        bonRetourDAO.edit(bonRetour);
// 
//        
//      listeDetailBonRetours.clear();
//      listeDetailBonRetours.addAll(detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId()));
//      tableDetailbonRtr.setItems(listeDetailBonRetours);
//  
//      setDetailColumnProperties();
//      
//      
//      loadDetail();
//      setColumnProperties();
//      
//        qteTxt.clear();
//        prixTxt.clear();
//        totalTxt.clear();
//       
//        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.TRAITEMENT_ENREGESTRE);
//        
//        
//           }
//        
//    }

    @FXML
    private void tableDetailClicked(MouseEvent event) {
        
//        
//          if (tableDetailbonRtr.getSelectionModel().getSelectedIndex()!=-1){
//               
//        DetailBonRetour detailBonRetour = tableDetailbonRtr.getSelectionModel().getSelectedItem();
//   
//            prixTxt.setText(detailBonRetour.getPrixUnitaire()+"");
//            qteTxt.setText(detailBonRetour.getQuantiteRetour()+""); 
//            totalTxt.setText(detailBonRetour.getMontant()+""); 
//        
//           }

        
    }

    @FXML
    private void fourOnAction(ActionEvent event) {
    }

    @FXML
    private void respUsinOnAction(ActionEvent event) {  
    }

    @FXML
    private void stockOnAction(ActionEvent event) {
    }

    @FXML
    private void calculeMouseClicked(MouseEvent event) {
       
      MHT = BigDecimal.ZERO ;
      MTVA = BigDecimal.ZERO ;
      MTTC = BigDecimal.ZERO ;
            
            
           for( int rows = 0;rows<tableBonRtr.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
        
//             if (typeBonColumn.getCellData(rows).equals(Constantes.RETOUR)){
//                 
//                  MHT= MHT.add(montReglerColumn.getCellData(rows));
//                 
//             }else{
                 
                  MHT= MHT.add(montFactColumn.getCellData(rows));
             
//             }
             }
        } 
    
           
             MTVA = MHT.multiply(Constantes.TAUX_TVA_20);
             MTTC = MHT.add(MTVA);
           
           
         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
            
            
            monHT.setText(df.format(MHT.setScale(2,RoundingMode.FLOOR)));
            monTTC.setText(df.format(MTTC.setScale(2,RoundingMode.FLOOR)));
            monTVA.setText(df.format(MTVA.setScale(2,RoundingMode.FLOOR)));
            
            
              montantTotalField.setText(df.format(MTTC));  

       
        
    }

    @FXML
    private void bonRetMnqOnAction(ActionEvent event) {
        
    }

    @FXML
    private void selectionnerToutMouseClicked(MouseEvent event) {
        
        
         ObservableList<BonRetour> list=tableBonRtr.getItems();
        
        for (Iterator<BonRetour> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        tableBonRtr.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutMouseClicked(MouseEvent event) {
        
        
                 ObservableList<BonRetour> list=tableBonRtr.getItems();
        
        for (Iterator<BonRetour> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }

        tableBonRtr.refresh(); 
        
    }

    @FXML
    private void imprimerBtnOnAction(ActionEvent event) {
        
        if (checkBonFrais.isSelected()==true){
        
        try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SuiviRetourManqueController.class.getResource(nav.getiReportBonFraisRetourManqueMp()));

            
                 Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
                     if(fournisseur!=null){
                 para.put("Fournisseur",fournisseur.getNom());
            }else {
                 para.put("Fournisseur","TOUT FOURNISSEUR");
            }

                     String bonRetGraMnq = bonRetGraMnqCombo.getSelectionModel().getSelectedItem();
                        if(bonRetGraMnq!=null){
                 para.put("BonRetMnq",bonRetGraMnq);
            }else {
                 para.put("BonRetMnq","TOUT BON RETOUR/MANQUE");
            }    
                        
                    String respUsin = respUsinCombo.getSelectionModel().getSelectedItem();    
                     if(respUsin!=null){
                 para.put("RespUsin",respUsin);
            }else {
                 para.put("RespUsin","TOUT RECEPTION/USINE");
            }  
                     
               String etat = etatCombo.getSelectionModel().getSelectedItem();    
                     if(etat!=null){
                 para.put("Etat",etat);
            }else {
                 para.put("Etat","TOUT ETAT");
            }  
                     
                     String stock = stockCombo.getSelectionModel().getSelectedItem();    
                     if(stock!=null){
                 para.put("Stock",stock);
            }else {
                 para.put("Stock","TOUT STOCK");
            }  
                 
                 ClientMP clientMP = mapClient.get(clientCombo.getSelectionModel().getSelectedItem());
                     if(clientMP!=null){
                 para.put("Client",clientMP.getNom());
            }else {
                 para.put("Client","TOUT CLIENT");
            }    
                       
                     BigDecimal valeurRetour = BigDecimal.ZERO;
                     BigDecimal valeurManque = BigDecimal.ZERO;
                     BigDecimal montantHT = BigDecimal.ZERO;
                     BigDecimal montantTVA = BigDecimal.ZERO;
                     BigDecimal montantTTC = BigDecimal.ZERO;
                     
                     
                     for (int i = 0; i < listeBonRetours.size(); i++) {
                
                      BonRetour bonRetour = listeBonRetours.get(i);
                      
//                    List<DetailBonRetour>  listDetailBonRetour = detailBonRetourDAO.findDetailBonRetourByBonRetour(bonRetour.getId());
//                    
//                    for (int j = 0; j < listDetailBonRetour.size(); j++) {
//                    
//                        DetailBonRetour detailBonRetour = listDetailBonRetour.get(j);
                        
                         if (bonRetour.getType().equals(Constantes.RETOUR)){
                        
                    valeurRetour= valeurRetour.add(bonRetour.getMontantRegler());
                    
                         }else if (bonRetour.getType().equals(Constantes.MANQUE)){
                        
                    valeurManque= valeurManque.add(bonRetour.getMontantRegler());
                    
//                         }
                    }
            }

                 montantHT= valeurRetour.add(valeurManque);
                 montantTVA= montantHT.multiply(new BigDecimal(0.2));
                 montantTTC= montantHT.add(montantTVA);
                     
                 para.put("GlobalRetour",valeurRetour);
                 
                 para.put("GlobalManque",valeurManque);
                     
                 para.put("MontantHt",montantHT);
                 
                 para.put("MontantTva",montantTVA);
                 
                 para.put("MontantTtc",montantTTC);
                 
                 
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeBonRetours));
               JasperViewer.viewReport(jp, false);
               
     
            
        } catch (JRException ex) {
            Logger.getLogger(SuiviRetourManqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        }else{
        
        
          try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SuiviRetourManqueController.class.getResource(nav.getiReportConsultationBonRetourGratuite()));

            
                 Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
                     if(fournisseur!=null){
                 para.put("Fournisseur",fournisseur.getNom());
            }else {
                 para.put("Fournisseur","TOUT FOURNISSEUR");
            }

                     String bonRetGraMnq = bonRetGraMnqCombo.getSelectionModel().getSelectedItem();
                        if(bonRetGraMnq!=null){
                 para.put("BonRetMnq",bonRetGraMnq);
            }else {
                 para.put("BonRetMnq","TOUT BON RETOUR/MANQUE");
            }    
                        
                    String respUsin = respUsinCombo.getSelectionModel().getSelectedItem();    
                     if(respUsin!=null){
                 para.put("RespUsin",respUsin);
            }else {
                 para.put("RespUsin","TOUT RECEPTION/USINE");
            }  
                     
               String etat = etatCombo.getSelectionModel().getSelectedItem();    
                     if(etat!=null){
                 para.put("Etat",etat);
            }else {
                 para.put("Etat","TOUT ETAT");
            }  
                     
                     String stock = stockCombo.getSelectionModel().getSelectedItem();    
                     if(stock!=null){
                 para.put("Stock",stock);
            }else {
                 para.put("Stock","TOUT STOCK");
            }  
                 
                 ClientMP clientMP = mapClient.get(clientCombo.getSelectionModel().getSelectedItem());
                     if(clientMP!=null){
                 para.put("Client",clientMP.getNom());
            }else {
                 para.put("Client","TOUT CLIENT");
            }    
                     
                     
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeBonRetours));
               JasperViewer.viewReport(jp, false);
               
     
            
        } catch (JRException ex) {
            Logger.getLogger(SuiviRetourManqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void etatOnAction(ActionEvent event) {
        
        String etat = etatCombo.getSelectionModel().getSelectedItem();
        
        if (etat != null){
        
        if (etat.equals(Constantes.ETAT_ANNULE)){

            checkBonFrais.setSelected(false);
            checkBonFrais.setVisible(true);
            
        }else{
        
            checkBonFrais.setSelected(false);
            checkBonFrais.setVisible(false);
            
        }
        }
    }

    @FXML
    private void invaliderOnAction(ActionEvent event) {
        
        MotifTxt.clear();
        MotifTxt.setDisable(false);

    }

    @FXML
    private void noPaiementOnAction(ActionEvent event) {
        
        MotifTxt.clear();
        MotifTxt.setDisable(true);
        
    }

    @FXML
    private void paiementOnAction(ActionEvent event) {
        
        MotifTxt.clear();
        MotifTxt.setDisable(true);
    }

    @FXML
    private void checkBonFraisOnAction(ActionEvent event) {
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }

    @FXML
    private void stockOnEditCommit(TableColumn.CellEditEvent<BonRetour, String> event) {
        
                  Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
            
             ((BonRetour) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setEnStock(event.getNewValue());       
                   
               String  stock = enStockColumn.getCellData(event.getTablePosition().getRow());
             
                   
        listeBonRetours.get(tableBonRtr.getSelectionModel().getSelectedIndex()).setEnStock(stock);   
       
        bonRetourDAO.edit(listeBonRetours.get(tableBonRtr.getSelectionModel().getSelectedIndex()));
           
            
            } else if (result.get() == ButtonType.CANCEL) {
                tableBonRtr.refresh();
            }
    }

    @FXML
    private void editCommitMontantReglerColumn(TableColumn.CellEditEvent<BonRetour, BigDecimal> event) {
        
//          
//         if (paiementRadio.isSelected()==false){
// 
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText(Constantes.REMPLIR_CHAMPS);
//            alert.setTitle("ERROR");
//            Optional<ButtonType> result = alert.showAndWait();
//
//            if (result.get() == ButtonType.OK) {
//   
//             listeBonRetours.get(tableBonRtr.getSelectionModel().getSelectedIndex()).setMontantRegler(BigDecimal.ZERO.setScale(2));
//             tableBonRtr.refresh();
//             return;
//            }
//
//
//                     }else{
        
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    
                ((BonRetour) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setMontantRegler(event.getNewValue());
               
                    tableBonRtr.refresh();  

                 M_Regler = montReglerColumn.getCellData(event.getTablePosition().getRow());
              
                 listeBonRetours.get(tableBonRtr.getSelectionModel().getSelectedIndex()).setMontantRegler(M_Regler.setScale(2, RoundingMode.FLOOR));

                setColumnProperties();



            } else if (result.get() == ButtonType.CANCEL) {
                tableBonRtr.refresh();
            }
//         }

        
    }
}
