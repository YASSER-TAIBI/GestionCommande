/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Controller.RetourGratuite.ConsultationBonRetourGratuite;
import Utils.Constantes;
import dao.Entity.BonLivraison;
import dao.Entity.BonRetour;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailBonRetour;
import dao.Entity.DetailCompte;
import dao.Entity.Fournisseur;
import dao.Entity.PrixOulmes;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.BonRetourDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.CompteFourMPDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailBonRetourDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.DetailReceptionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ReglementDAO;
import dao.Manager.StockMPDAO;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.BonRetourDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.CompteFourMPDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailBonRetourDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import dao.ManagerImpl.DetailReceptionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ReglementDAOImpl;
import dao.ManagerImpl.StockMPDAOImpl;
import function.navigation;
import java.math.BigDecimal;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
public class EtatReglementOulmesController implements Initializable {
    
    
    @FXML
    private TableView<BonLivraison> tableBonLivraison;
    @FXML
    private TableColumn<BonLivraison, BigDecimal> montantColumn;
    @FXML
    private TableColumn<BonLivraison, String> etatColumn;
    @FXML
    private TableColumn<BonLivraison, String> numLivraisonColumn;
    @FXML
    private TableColumn<BonLivraison, Date> datePaiementColumn;
    @FXML
    private TableColumn<BonLivraison, Integer> nombreJourColumn;
    @FXML
    private TableColumn<BonLivraison, Date> dateLivraisonColumn;
    @FXML
    private TableColumn<BonLivraison, Boolean> actionColumn;
    @FXML
    private TableColumn<BonLivraison, BigDecimal> montantTVAColumn;
    @FXML
    private TableColumn<BonLivraison, BigDecimal> montantTTCColumn;
    
    
    @FXML
    private TableView<DetailBonLivraison> tableDetailBonLivraison;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> prixColumn;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> totalColumn;
    @FXML
    private TableColumn<DetailBonLivraison, String> codeArtColumn;
    @FXML
    private TableColumn<DetailBonLivraison, String> articleColumn;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> remiseColumn;
    

    @FXML
    private Button btnRegler;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private TextField numFacture;
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
    private TextField numLivRech;
    @FXML
    private TextField prixTxt;
    @FXML
    private TextField qteTxt;
    @FXML
    private TextField totalTxt;
    @FXML
    private Button btnImprimer;
    @FXML
    private DatePicker dateLivraison;
    
    
    
    
    public   Date dateNow =null;
   
      private final  ObservableList<BonLivraison> listeBonLivraisonTMP = FXCollections.observableArrayList();
  private final  ObservableList<BonLivraison> listeBonLivraison = FXCollections.observableArrayList();
  private final  ObservableList<DetailBonLivraison> listeDetailBonLivraison = FXCollections.observableArrayList();
  private final  ObservableList<DetailBonRetour> listeDetailBonRetour = FXCollections.observableArrayList();

  BonLivraisonDAO bonLivraisonDAO = new BonLivraisonDAOImpl();
  CompteFourMPDAO compteFourMPDAO = new CompteFourMPDAOImpl();
  BonRetourDAO bonRetourDAO = new BonRetourDAOImpl();
  CommandeDAO commandeDAO = new CommandeDAOImpl();
  StockMPDAO stockMPDAO = new StockMPDAOImpl();
  DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
  DetailBonRetourDAO detailBonRetourDAO = new DetailBonRetourDAOImpl();
  DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
  BonLivraison bonLivraison = new BonLivraison();
  Fournisseur fournisseur =new Fournisseur();
//  DetailBonRetour detailBonRetour =new DetailBonRetour();
  ClientMP clientMP =new ClientMP();
  BonRetour bonRetour = new BonRetour();
  
   DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
   DetailReceptionDAO  detailReceptionDAO = new DetailReceptionDAOImpl();
   ReglementDAO reglementDAO= new ReglementDAOImpl();
   navigation nav = new navigation();   
   Commande commande = new Commande();
   FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
   ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
   DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
   PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
   
   private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
   private Map<String,ClientMP> mapClientMP=new HashMap<>();
   String numCommande="";
    String valeur="";
    
  BigDecimal newMontant= BigDecimal.ZERO;

  BigDecimal sommeTotal= BigDecimal.ZERO;
  BigDecimal montantTotal=BigDecimal.ZERO;
  BigDecimal montantTVA=BigDecimal.ZERO;
  BigDecimal montantTTC=BigDecimal.ZERO;
  BigDecimal montantTotalNonPayer=BigDecimal.ZERO;
  BigDecimal montantTVANonPayer=BigDecimal.ZERO;
  BigDecimal montantTTCNonPayer=BigDecimal.ZERO;
  BigDecimal montantTotalNonRegler=BigDecimal.ZERO;
  BigDecimal montantTVANonRegler=BigDecimal.ZERO;
  BigDecimal montantTTCNonRegler=BigDecimal.ZERO;


    static final long UNE_HEURE = 60*60*1000L;
    
   
  int pos = 0;
    
   

 

 
    /**
     * Initializes the controller class.
     */
    
    //--------------------------------------- Methode date paiement + date now = nombre de jour restant -------------------------------------------------------------------------------
      
    public int daysBetween(Date d1, Date d2){
             return (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
     }
   
  //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------           
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 

        numLivRech.setDisable(true);
        


    
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


        btnRegler.setDisable(true);
        tableDetailBonLivraison.setEditable(true);
     
    
    }    
    

    
    void setColumnProperties() {

    
      numLivraisonColumn.setCellValueFactory(new PropertyValueFactory<>("livraisonFour"));
      montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
      
      
      etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
      
      datePaiementColumn.setCellValueFactory(new PropertyValueFactory<BonLivraison, Date>("datePaiement"));
       setColumnTextFieldConverter(getConverterDate(),datePaiementColumn);
      
      nombreJourColumn.setCellValueFactory(new PropertyValueFactory<>("nombreJour"));
      dateLivraisonColumn.setCellValueFactory(new PropertyValueFactory<BonLivraison, Date>("dateLivraison"));

      montantTVAColumn.setCellValueFactory(new PropertyValueFactory<>("montantTVA"));

      montantTTCColumn.setCellValueFactory(new PropertyValueFactory<>("montantTTC"));

      actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          BonLivraison cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionColumn.setEditable(true);
      
          
          tableBonLivraison.setEditable(true);
     

    }


        public static void setColumnTextFieldConverter(StringConverter converter, TableColumn... columns) {

        for (TableColumn tableColumn : columns) {

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        }
    }

        
    private StringConverter getConverterDate() {
        StringConverter<Date> converter = new StringConverter<Date>() {
            @Override
            public Date fromString(String string) {

                try {
                    
             Date valeur;      
             valeur = new Date(string);
                    return valeur;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString(Date object) {

                if (object == null) {
                    return null;
                }
                return String.valueOf(object);
            }
        };

        return converter;
    }

     private StringConverter getConverterBigDecimal() {
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
    
    void loadDetailBonLiv(){
    
       listeDetailBonLivraison.clear();
        detailBonLivraisonDAO.ViderSession();
       listeDetailBonLivraison.addAll(detailBonLivraisonDAO.findDetaiBonLivraisonBycode(tableBonLivraison.getSelectionModel().getSelectedItem().getLivraisonFour(),tableBonLivraison.getSelectionModel().getSelectedItem().getNumCommande()));
       tableDetailBonLivraison.setItems(listeDetailBonLivraison);
    }
    
       
       
    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        


        if (tableBonLivraison.getSelectionModel().getSelectedIndex()!=-1){
            
            listeDetailBonLivraison.clear();
          
       listeDetailBonLivraison.addAll(detailBonLivraisonDAO.findDetaiBonLivraisonBycode(tableBonLivraison.getSelectionModel().getSelectedItem().getLivraisonFour(),tableBonLivraison.getSelectionModel().getSelectedItem().getNumCommande()));


              for( int i = 0;i<listeDetailBonLivraison.size() ;i++ ){
              
                  DetailBonLivraison detailBonLivraison = listeDetailBonLivraison.get(i);
                  
                  if (detailBonLivraison.getQuantite().equals(BigDecimal.ZERO.setScale(2))){
                  
                  detailBonLivraisonDAO.delete(detailBonLivraison);
                  }
              
              }
         
       loadDetailBonLiv();
       setColumnPropertiesDetailReception();
  
       
    }
         
       
    }
        void setColumnPropertiesDetailReception(){

               codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonLivraison, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });

               articleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonLivraison, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
             });
           
             qteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });
           
  qteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });
           
             prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrix()));
                }
                
             });
           
           remiseColumn.setCellValueFactory(new PropertyValueFactory<DetailBonLivraison, BigDecimal>("remiseAchat"));

        setColumnTextFieldConverter(getConverterBigDecimal(), remiseColumn);
           
             totalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
           
}
        

    @FXML
    private void reglerOnAction(ActionEvent event) throws ParseException {
        
         boolean  variable =false;  

            if (numFacture.getText().equalsIgnoreCase("") || fourCombo.getSelectionModel().getSelectedItem() == null || fourCombo.getSelectionModel().getSelectedItem().isEmpty() ) {
                nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                
            } else{ 
        
           montantTotal=BigDecimal.ZERO;
      for( int rows = 0;rows<tableBonLivraison.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
             
    
                 variable = true; 
             BonLivraison bonLivraisonTmp= tableBonLivraison.getItems().get(rows);
                 fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
               clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());

      
            
            if (bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_OULMES)){
            bonLivraisonTmp.setEtat(Constantes.ETAT_A_REGLE);
            }else if (bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_RTR) && bonLivraisonTmp.getEtat().equals(Constantes.ETAT_NON_REGLE) || bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_MNQ) && bonLivraisonTmp.getEtat().equals(Constantes.ETAT_NON_REGLE)  ) {
            bonLivraisonTmp.setEtat(Constantes.ETAT_A_REGLE);
            }else if (bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_RTR) && bonLivraisonTmp.getEtat().equals(Constantes.ETAT_NON_PAIEMENT) || bonLivraisonTmp.getTypeBon().equals(Constantes.ETAT_MNQ) && bonLivraisonTmp.getEtat().equals(Constantes.ETAT_NON_PAIEMENT) ) {
            bonLivraisonTmp.setEtat(Constantes.ETAT_A_PAYER);
            }else {
            break;
            }
            
            bonLivraisonTmp.setNumFacture(numFacture.getText());
            bonLivraisonTmp.setAction(false);
            bonLivraisonTmp.setSansTVA(false);
            bonLivraisonDAO.edit(bonLivraisonTmp);
         
            
             List<DetailBonLivraison> listDetailBonLivraison = detailBonLivraisonDAO.findDetaiBonLivraisonBycode(bonLivraisonTmp.getLivraisonFour(),bonLivraisonTmp.getNumCommande() );
            
             for (int i = 0; i < listDetailBonLivraison.size(); i++) {
                 {
                     DetailBonLivraison detailBonLivraison = listDetailBonLivraison.get(i);
                 detailBonLivraison.setNumFacture(numFacture.getText());

                 detailBonLivraisonDAO.edit(detailBonLivraison);
                 }
                 
             }
            
         }
    }
       tableBonLivraison.refresh();
   
               
      if (variable == false){
           nav.showAlert(Alert.AlertType.ERROR, "Atention", null, Constantes.REMPLIR_COCHE);
   
      }else{
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.REGLER_ENREGISTREMENT);

             loadDetailCombo();
             listeDetailBonLivraison.clear();
             numFacture.clear();
             montantTotalField.clear();
             monHT.setText("");
             monTVA.setText("");
             monTTC.setText("");
             numLivRech.clear();
             
    }}}

    
    
    @FXML
    private void fournisseurOnAction(ActionEvent event) {

    }




        void loadDetailCombo() throws ParseException{
             listeBonLivraison.clear();
         
            if (fourCombo.getSelectionModel().getSelectedIndex() == -1 && clientCombo.getSelectionModel().getSelectedIndex() == -1 && numLivRech.getText().equals("") && dateLivraison.getValue() == null ){
 
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
            }else if (fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && numLivRech.getText().equals("") && dateLivraison.getValue() == null ){
          
            listeBonLivraison.addAll(bonLivraisonDAO.findFourByRechercheNomReglementOulmes(fourCombo.getSelectionModel().getSelectedItem(),clientCombo.getSelectionModel().getSelectedItem(),Constantes.ETAT_NON_REGLE,Constantes.ETAT_NON_PAIEMENT,Constantes.ETAT_OULMES,Constantes.ETAT_FACTURE));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && !numLivRech.getText().equals("") && dateLivraison.getValue() == null ){ 
          
            listeBonLivraison.addAll(bonLivraisonDAO.findNumCommandeByNumLivraisonOulmes(numLivRech.getText(),fourCombo.getSelectionModel().getSelectedItem(),clientCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_NON_REGLE, Constantes.ETAT_NON_PAIEMENT ,Constantes.ETAT_OULMES,Constantes.ETAT_FACTURE));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && numLivRech.getText().equals("") && dateLivraison.getValue() != null ){ 
              
               LocalDate localDate=dateLivraison.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
            listeBonLivraison.addAll(bonLivraisonDAO.findNumCommandeByDateLivraisonOulmes(date,fourCombo.getSelectionModel().getSelectedItem(),clientCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_NON_REGLE, Constantes.ETAT_NON_PAIEMENT ,Constantes.ETAT_OULMES,Constantes.ETAT_FACTURE));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }else if(fourCombo.getSelectionModel().getSelectedIndex() != -1 && clientCombo.getSelectionModel().getSelectedIndex() != -1 && !numLivRech.getText().equals("") && dateLivraison.getValue() != null ){ 
              
               LocalDate localDate=dateLivraison.getValue();
            
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          
            listeBonLivraison.addAll(bonLivraisonDAO.findNumCommandeByDateLivraisonAndNumLivraisonOulmes(date, numLivRech.getText(),fourCombo.getSelectionModel().getSelectedItem(),clientCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_NON_REGLE, Constantes.ETAT_NON_PAIEMENT ,Constantes.ETAT_OULMES,Constantes.ETAT_FACTURE));
          
          for (int i=0 ;i<listeBonLivraison.size() ;i++)
          {

            BonLivraison bonLivraison = listeBonLivraison.get(i);
              
            bonLivraison.setNombreJour(daysBetween(bonLivraison.getDatePaiement(), new Date()));
              
            bonLivraisonDAO.edit(bonLivraison);          
           
              
          }
          tableBonLivraison.setItems(listeBonLivraison);
          
          
          
          }
 }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }


    void refrech() throws ParseException{
    
     if (fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                clientCombo.getSelectionModel().getSelectedIndex()== -1 || 
                clientCombo.getSelectionModel().getSelectedItem().equals("") )
        {
        nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.VERIFIER_FOURNISSEUR_CLIENT);
        }else{
      
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = simpleDateFormat.parse(Constantes.DATE);
         
            setColumnProperties();

            loadDetailCombo();
            
             tableBonLivraison.setEditable(true);
             dateLivraisonColumn.setEditable(true);
            


          tableBonLivraison.setRowFactory(new Callback<TableView<BonLivraison>, TableRow<BonLivraison>>(){
            //There can define some colors.
      
            @Override
            public TableRow<BonLivraison> call(TableView<BonLivraison> param) {
                final TableRow<BonLivraison> row = new TableRow<BonLivraison>() {
                    @Override
                    protected void updateItem(BonLivraison item, boolean empty) {
                        super.updateItem(item, empty);
                        
                     
                        if (item == null || empty) {
				setText(null);
				setStyle("");
                                
			} else {
          
                            
                       if (item.getDatePaiement().equals(date)){

			 setStyle("-fx-background-color: green");
                        }}
                    }
                };
                return row;
            }
        });
            

            
          nombreJourColumn.setCellFactory(column -> {
			return new TableCell<BonLivraison, Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty);
					
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						
						setText(String.valueOf(item));
						
                                                
						if (item <=7 && item >=0) {
							setTextFill(Color.WHITE);
							setStyle("-fx-background-color: red");
						} else {
							setTextFill(Color.BLACK);
							setStyle("");
						}
					}
				}
			};
		});

        }
    }
    
    @FXML
    private void tableDetailClicked(MouseEvent event) {
        
        BonLivraison bonLivraison = tableBonLivraison.getSelectionModel().getSelectedItem();
        
        DetailBonLivraison detailBonLivraisonsTmp = tableDetailBonLivraison.getSelectionModel().getSelectedItem();
        
//         detailBonRetour = detailBonRetourDAO.findDetailBonRetourByDetailBonLivraison(bonLivraison.getNumCommande(), bonLivraison.getLivraisonFour(),detailBonLivraisonsTmp.getMatierePremier().getId());

        
            qteTxt.setText(detailBonLivraisonsTmp.getQuantite()+"");
            prixTxt.setText(detailBonLivraisonsTmp.getPrix()+"");
            

            totalTxt.clear();
  
       }
   
    @FXML
    private void datePaiementOnEditCommit(TableColumn.CellEditEvent<BonLivraison, Date> event) throws ParseException {
        
        
        System.out.println("event.getNewValue() "+event.getNewValue());
        
            ((BonLivraison) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setDatePaiement(event.getNewValue());
            
            
            
            
              Date  datePaiement = datePaiementColumn.getCellData(event.getTablePosition().getRow());

              
              if (datePaiement== null){
                  
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.ERREUR_DATE , Constantes.VERIFICATION_DATE_SAISIE );
              
              }else{
              
              tableBonLivraison.refresh();
              BonLivraison bonLivraison = listeBonLivraison.get(event.getTablePosition().getRow());
              bonLivraison.setDatePaiement(datePaiement);
              listeBonLivraison.set(event.getTablePosition().getRow(), bonLivraison);
                
                bonLivraisonDAO.edit(bonLivraison);
                
                refrech();
              
              }
      
              
    }   


    @FXML
    private void recherchMouseClicked(MouseEvent event) throws ParseException {
        
        numLivRech.setDisable(false);
        refrech();
        
    }

    @FXML
    private void calculeMouseClicked(MouseEvent event) {

            montantTotal=BigDecimal.ZERO;
            montantTVA=BigDecimal.ZERO;
            montantTTC=BigDecimal.ZERO;
            montantTotalNonPayer=BigDecimal.ZERO;
            montantTVANonPayer=BigDecimal.ZERO;
            montantTTCNonPayer=BigDecimal.ZERO;
            montantTotalNonRegler=BigDecimal.ZERO;
            montantTVANonRegler=BigDecimal.ZERO;
            montantTTCNonRegler=BigDecimal.ZERO;
            
            
           for( int rows = 0;rows<tableBonLivraison.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
        
             if (etatColumn.getCellData(rows).equals(Constantes.ETAT_NON_PAIEMENT)){

             montantTotalNonPayer= montantTotalNonPayer.add(montantColumn.getCellData(rows));

             montantTVANonPayer = montantTVANonPayer.add(montantTVAColumn.getCellData(rows));
             
             montantTTCNonPayer= montantTTCNonPayer.add(montantTTCColumn.getCellData(rows));
               
             
             } else{

             montantTotalNonRegler= montantTotalNonRegler.add(montantColumn.getCellData(rows));

             montantTVANonRegler = montantTVANonRegler.add(montantTVAColumn.getCellData(rows));
             
             montantTTCNonRegler = montantTTCNonRegler.add(montantTTCColumn.getCellData(rows));
               
             }
        } 
    }
           
           montantTotal= montantTotalNonRegler.subtract(montantTotalNonPayer);
             montantTVA= montantTVANonRegler.subtract(montantTVANonPayer);
             montantTTC= montantTTCNonRegler.subtract(montantTTCNonPayer);
           
           
              DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
    
    monHT.setText(df.format(montantTotal));
    monTVA.setText(df.format(montantTVA));
    monTTC.setText(df.format(montantTTC));
           
         montantTotalField.setText(df.format(montantTTC));  
         
         if(!montantTTC.equals(BigDecimal.ZERO)){
         
         btnRegler.setDisable(false);
         }
         
    }

    @FXML
    private void rechercheNumLivKeyPressed(KeyEvent event) {
        
//    ObservableList<BonLivraison> listeBonLivraisonRech=FXCollections.observableArrayList();
//                   
//    listeBonLivraisonRech.clear();
//   
//   listeBonLivraisonRech.addAll(bonLivraisonDAO.findBonLivraisonByRechercheNumLiv(numLivRech.getText(),clientCombo.getSelectionModel().getSelectedItem(),fourCombo.getSelectionModel().getSelectedItem(), Constantes.ETAT_NON_REGLE, Constantes.ETAT_NON_PAIEMENT));
//   
//   tableBonLivraison.setItems(listeBonLivraisonRech);
//    
        
    }


    @FXML
    private void calculeTotalMouseClicked(MouseEvent event) throws ParseException {
        
            if (!qteTxt.getText().equals("") || !prixTxt.getText().equals("")){
            
                BigDecimal  valeur=BigDecimal.ZERO;
                   
        DetailBonLivraison detailBonLivraisonsTmp = tableDetailBonLivraison.getSelectionModel().getSelectedItem();        
                
               if (detailBonLivraisonsTmp.getPrixOulmes().getProduit().getPalette() == true){
                
                valeur = (new BigDecimal(prixTxt.getText()).multiply(new BigDecimal(qteTxt.getText())));
                
                }else{ 
                   
                 valeur = (new BigDecimal(prixTxt.getText()).multiply(new BigDecimal(qteTxt.getText()))).subtract(((new BigDecimal(prixTxt.getText()).multiply(new BigDecimal(qteTxt.getText()))).multiply(detailBonLivraisonsTmp.getRemiseAchat())).divide(new BigDecimal(100)));
                }

        totalTxt.setText(valeur.setScale(2,RoundingMode.FLOOR)+"");
        
       detailBonLivraisonsTmp.setQuantite(new BigDecimal(qteTxt.getText()));
       detailBonLivraisonsTmp.setPrix(new BigDecimal(prixTxt.getText()));
       detailBonLivraisonsTmp.setMontant(valeur);
      
       detailBonLivraisonDAO.edit(detailBonLivraisonsTmp);
        
    //_______________________________________________________________________________________________________________________________________________________________________
    
        BigDecimal montantHT= BigDecimal.ZERO;
        BigDecimal montantTVA =BigDecimal.ZERO;
        BigDecimal montantTTC =BigDecimal.ZERO;
        BigDecimal montantNormal = BigDecimal.ZERO;
        BigDecimal montantPalette = BigDecimal.ZERO;

          BonLivraison bonLivraison = tableBonLivraison.getSelectionModel().getSelectedItem();

                for(int i=0;i<listeDetailBonLivraison.size(); i++ ){

                    DetailBonLivraison detailBonLivraison = listeDetailBonLivraison.get(i);
                    
               if (detailBonLivraison.getPrixOulmes().getProduit().getPalette() == true){
                
                montantPalette = montantPalette.add(detailBonLivraison.getTotal().setScale(2,RoundingMode.FLOOR));
                
                }else{ 
                   
                montantNormal = montantNormal.add(detailBonLivraison.getTotal().subtract(((detailBonLivraison.getTotal()).multiply(detailBonLivraison.getRemiseAchat())).divide(new BigDecimal(100))));
                }

        }

             montantHT = montantNormal.add(montantPalette).setScale(2,RoundingMode.FLOOR);
             montantTVA =montantNormal.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
             montantTTC =montantHT.add(montantTVA).setScale(2,RoundingMode.FLOOR) ;
        
          bonLivraison.setMontant(montantHT);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          
        bonLivraisonDAO.edit(bonLivraison);

        BigDecimal MontantHTSpecial = BigDecimal.ZERO;
        BigDecimal MontantTVASpecial= BigDecimal.ZERO;
        BigDecimal MontantTTCSpecial= BigDecimal.ZERO;
        BigDecimal montantNormalSpecial = BigDecimal.ZERO;
        BigDecimal montantPaletteSpecial = BigDecimal.ZERO;
        
 for(int j=0;j<listeDetailBonLivraison.size(); j++ ){

      DetailBonLivraison detailBonLivraison = listeDetailBonLivraison.get(j);
     
      String designation = Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+bonLivraison.getLivraisonFour()+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+detailBonLivraison.getNumCommande();

     
         DetailCompte detailCompte = detailCompteDAO.findByDetailCompte(designation);
        
            if (detailBonLivraison.getPrixOulmes().getProduit().getPalette() == true){
                
                montantPaletteSpecial = montantPaletteSpecial.add(detailBonLivraison.getTotal().setScale(2,RoundingMode.FLOOR));
                
                }else{ 
                   
                montantNormalSpecial =  montantNormalSpecial.add(detailBonLivraison.getTotal().subtract(((detailBonLivraison.getTotal()).multiply(detailBonLivraison.getRemiseAchat())).divide(new BigDecimal(100)))); 
                }
         
         MontantHTSpecial = montantNormal.add(montantPalette);
         MontantTVASpecial =montantNormalSpecial.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
         MontantTTCSpecial =MontantHTSpecial.add(MontantTVASpecial).setScale(2,RoundingMode.FLOOR) ;


        detailCompte.setMontantCredit(MontantTTCSpecial);

        detailCompteDAO.edit(detailCompte);

       loadDetailBonLiv();
       loadDetailCombo();
            
 }
               
        }else {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.VERIFIER_QTE_PRIX);
        }
        
    }




    @FXML
    private void imprimerEtatOnAction(ActionEvent event) {

           listeBonLivraisonTMP.clear();
        
          for( int rows = 0;rows<tableBonLivraison.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
             
             BonLivraison bonLivraison = tableBonLivraison.getItems().get(rows); 
             
                        if (etatColumn.getCellData(rows).equals(Constantes.ETAT_A_PAYER)){
             
                 bonLivraison.setMontant(bonLivraison.getMontant().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
                 bonLivraison.setMontantTVA(bonLivraison.getMontantTVA().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
                 bonLivraison.setMontantTTC(bonLivraison.getMontantTTC().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
                 
             }
             
             listeBonLivraisonTMP.add(bonLivraison); 
         }
          }
        
                        try {
                            
          HashMap para = new HashMap();
           if (listeBonLivraisonTMP.size()!= 0){
          
            JasperReport report = (JasperReport) JRLoader.loadObject(EtatReglementOulmesController.class.getResource(nav.getiReportEtatReglementOulmes()));
                        
            para.put("MontantHT",monHT.getText());
            para.put("MontantTVA",monTVA.getText());
            para.put("MontantTTC",monTTC.getText());

             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeBonLivraisonTMP));
               JasperViewer.viewReport(jp, false);
               
           }
                             for( int rows = 0;rows<tableBonLivraison.getItems().size() ;rows++ ){
    
         if (actionColumn.getCellData(rows).booleanValue()==true){
             
             BonLivraison bonLivraison = tableBonLivraison.getItems().get(rows); 
             
                     if (etatColumn.getCellData(rows).equals(Constantes.ETAT_A_PAYER)){
             
                 bonLivraison.setMontant(bonLivraison.getMontant().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
                 bonLivraison.setMontantTVA(bonLivraison.getMontantTVA().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
                 bonLivraison.setMontantTTC(bonLivraison.getMontantTTC().multiply(new BigDecimal(-1)).setScale(2,RoundingMode.FLOOR));
                 
             }
             
         }
          }
        } catch (JRException ex) {
            Logger.getLogger(EtatReglementOulmesController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }


    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        
            listeBonLivraison.clear();
             listeDetailBonLivraison.clear();
             numFacture.clear();
             montantTotalField.clear();
             monHT.setText("");
             monTVA.setText("");
             monTTC.setText("");
             numLivRech.clear();
             dateLivraison.setValue(null);
             clientCombo.getSelectionModel().clearSelection();
             fourCombo.getSelectionModel().clearSelection();
        
    }

   @FXML
    private void editCommitRemiseColumn(TableColumn.CellEditEvent<DetailBonLivraison, BigDecimal> event) throws ParseException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    BigDecimal remise = BigDecimal.ZERO;
                    BigDecimal prix= BigDecimal.ZERO;
                    BigDecimal qte= BigDecimal.ZERO;
                    BigDecimal calculeMontant= BigDecimal.ZERO; 
                    
          
                    
                ((DetailBonLivraison) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setRemiseAchat(event.getNewValue());
               
                
                    tableDetailBonLivraison.refresh();  

  
                 remise = remiseColumn.getCellData(event.getTablePosition().getRow());
                
                 prix =  listeDetailBonLivraison.get(tableDetailBonLivraison.getSelectionModel().getSelectedIndex()).getPrix();
                
                 qte =  listeDetailBonLivraison.get(tableDetailBonLivraison.getSelectionModel().getSelectedIndex()).getQuantite();
                
                 calculeMontant = ((prix.multiply(qte)).subtract(((prix.multiply(qte)).multiply(remise)).divide(new BigDecimal(100)))).setScale(2, RoundingMode.FLOOR);
               

                 listeDetailBonLivraison.get(tableDetailBonLivraison.getSelectionModel().getSelectedIndex()).setMontant(calculeMontant);
//================================================================================================================================================================================================================================================================================================================================================              

                PrixOulmes prixOulmes = prixOulmesDAO.findById(listeDetailBonLivraison.get(tableDetailBonLivraison.getSelectionModel().getSelectedIndex()).getPrixOulmes().getId());
                
                prixOulmes.setRemiseAchat(remise);
                
                prixOulmesDAO.edit(prixOulmes);

  //================================================================================================================================================================================================================================================================================================================================================              
     DetailBonLivraison detailBonLivraisonTMP = tableDetailBonLivraison.getSelectionModel().getSelectedItem();               
  
  detailBonLivraisonTMP.setRemiseAchat(remise);
  detailBonLivraisonTMP.setMontant(calculeMontant);
                 
  detailBonLivraisonDAO.edit(detailBonLivraisonTMP);
//================================================================================================================================================================================================================================================================================================================================================
     BigDecimal montantHT= BigDecimal.ZERO;
        BigDecimal montantTVA =BigDecimal.ZERO;
        BigDecimal montantTTC =BigDecimal.ZERO;
        BigDecimal montantNormal = BigDecimal.ZERO;
        BigDecimal montantSansTVA = BigDecimal.ZERO;
        
                 BonLivraison bonLivraison = tableBonLivraison.getSelectionModel().getSelectedItem();
        
               for(int i=0;i<listeDetailBonLivraison.size(); i++ ){

                    DetailBonLivraison detailBonLivraison = listeDetailBonLivraison.get(i);
                    
               if (detailBonLivraison.getPrixOulmes().getProduit().getPalette() == true){
                
                montantSansTVA = montantSansTVA.add(detailBonLivraison.getMontant().setScale(2,RoundingMode.FLOOR));
                
                }else{ 
                   
                montantNormal = montantNormal.add(detailBonLivraison.getMontant().setScale(2,RoundingMode.FLOOR));
                }

        }

             montantHT = montantNormal.add(montantSansTVA).setScale(2,RoundingMode.FLOOR);
             montantTVA =montantNormal.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
             montantTTC =montantHT.add(montantTVA).setScale(2,RoundingMode.FLOOR) ;
        
          bonLivraison.setMontant(montantHT);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          
        bonLivraisonDAO.edit(bonLivraison);
  
                setColumnProperties();
                listeBonLivraison.clear();
                listeDetailBonLivraison.clear();
                loadDetailCombo();

           

            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailBonLivraison.refresh();
            }
        }




  
  

  

   





 



    



   

 

  


 

  



 
    
}