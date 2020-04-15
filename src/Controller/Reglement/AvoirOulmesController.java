
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Utils.Constantes;
import dao.Entity.AvoirOulmes;
import dao.Entity.BonLivraison;
import dao.Entity.ClientMP;
import dao.Entity.DetailCommande;
import dao.Entity.Fournisseur;
import dao.Entity.MatierePremier;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
import dao.Manager.AvoirOulmesDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.ManagerImpl.AvoirOulmesDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import static javassist.CtMethod.ConstParameter.integer;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class AvoirOulmesController implements Initializable {

    @FXML
    private TableView<AvoirOulmes> avoireOulmestable;
    @FXML
    private TableColumn<AvoirOulmes, String> codeArtColumn;
    @FXML
    private TableColumn<AvoirOulmes, String> libelleColumn;
    @FXML
    private TableColumn<AvoirOulmes, String> clientColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> prixColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> montantColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> remiseColumn;
    @FXML
    private TableColumn<AvoirOulmes, BigDecimal> montantNetColumn;
    @FXML
    private TableColumn<AvoirOulmes, Boolean> actionColumn;
    @FXML
    private TableColumn<AvoirOulmes, String> bonAvoirColumn;
    @FXML
    private TableColumn<AvoirOulmes, String> client2Column;
    
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField codeArtField;
    @FXML
    private DatePicker dateSaisie;
    @FXML
    private RadioButton radOui;
    @FXML
    private RadioButton radNon;
    @FXML
    private Label monHT;
    @FXML
    private Label monTVA;
    @FXML
    private Label monTTC;
    @FXML
    private Button btnRefresh;
    @FXML
    private TextField montantTotalField;
    @FXML
    private RadioButton radPlus;
    @FXML
    private RadioButton radmoin;
    @FXML
    private Label qteAfficchage;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private ToggleGroup radOuiNon;
    @FXML
    private ToggleGroup radPlusMoin;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ComboBox<String> LibelleCombo;
    @FXML
    private TextField nLivraisonField;
    @FXML
    private TextField nLivraisonRechField;
    @FXML
    private ComboBox<String> clientCombo1;
    @FXML
    private RadioButton radClientOui;
    @FXML
    private RadioButton radClientNon;
    @FXML
    private ComboBox<String> client2Combo;
    @FXML
    private ToggleGroup radClientOuiNon;
    @FXML
    private ComboBox<String> lieuLivCombo;
    
    
    private final ObservableList<AvoirOulmes> listeAvoirOulmes=FXCollections.observableArrayList();
    
    navigation nav = new navigation();
    PrixOulmes prixOulmes =new PrixOulmes();
    Date date = new  Date();
            
    ProduitDAO produitDAO = new ProduitDAOImpl();       
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    AvoirOulmesDAO avoirOulmesDAO = new AvoirOulmesDAOImpl();
      
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    
    private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
      
    SimpleDateFormat dateFormat= new SimpleDateFormat("yy");
    
    
     BigDecimal montanNet =BigDecimal.ZERO;
     BigDecimal MHT = BigDecimal.ZERO ;
     BigDecimal MTVA = BigDecimal.ZERO ;
     BigDecimal MTTC = BigDecimal.ZERO ;
    
     BigDecimal qteCaisse = BigDecimal.ZERO;
  
    
    
    
    
       ObservableList<String> client =FXCollections.observableArrayList(Constantes.CLIENT_MARJANE,Constantes.CLIENT_MINURSO);
    
    
    
   
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
        client2Combo.setItems(client);
        
        loadDetail();
        setColumnProperties();
        
        radPlus.setVisible(false);
        radmoin.setVisible(false);
        
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
        
        
             List<ClientMP> listClient=clientMPDAO.findAll();
        
        listClient.stream().map((client) -> {
            clientCombo1.getItems().addAll(client.getNom());
            return client;
        }).forEachOrdered((client) -> {
            mapClientMP.put(client.getNom(), client);
        });
        
          client2Combo.setDisable(true);
          lieuLivCombo.setDisable(true);
    }    

     void setColumnProperties(){

               codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });
               
               bonAvoirColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumLivraison());
                }
             });

                    libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
                }
             });
        
            clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClient());
                }
             });
                    
            client2Column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<AvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getClient2());
                }
             });
            
             quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQte()));
                }
                
             });
           

           
             prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrix()));
                }
                
             });
           
             remiseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getRemiseAvoir());
                }
             });
           
             montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
             
                 montantNetColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<AvoirOulmes, BigDecimal> p) {
                    

                    return new ReadOnlyObjectWrapper(p.getValue().getMontantNet().setScale(2,RoundingMode.FLOOR));
                }
                
             });
                 
                 
          actionColumn.cellValueFactoryProperty();
          actionColumn.setCellValueFactory((cellData) -> {
          AvoirOulmes cellvalue= cellData.getValue();
              BooleanProperty property = new SimpleBooleanProperty();
                      property.set(cellvalue.getAction());
                      
                      property.addListener((observabel, oldvalue,newvalue)->cellvalue.setAction(newvalue));
              
              return property; 
          });
          actionColumn.setCellFactory(act-> new CheckBoxTableCell<>());
    
          actionColumn.setEditable(true);
      
          
          avoireOulmestable.setEditable(true); 
}
    
       void loadDetail(){
           
        listeAvoirOulmes.clear();
        listeAvoirOulmes.addAll(avoirOulmesDAO.findAvoirOulmesByEtat(Constantes.ETAT_BL_AVOIR));
        avoireOulmestable.setItems(listeAvoirOulmes);
    }
    
         void clear(){
    
      

             monHT.setText("");
             monTVA.setText("");
             monTTC.setText("");

             montantTotalField.clear();
             dateSaisie.setValue(null);
             clientCombo.getSelectionModel().select(-1);
             fourCombo.getSelectionModel().select(-1);
             client2Combo.getSelectionModel().select(-1);
             lieuLivCombo.getSelectionModel().select(-1);
             
    LibelleCombo.getSelectionModel().select(-1);        
    
    codeArtField.clear();
    quantiteField.clear();
    nLivraisonField.clear();
    qteAfficchage.setText("");
    
    client2Combo.setDisable(true);
    lieuLivCombo.setDisable(true);
    
    radClientNon.setSelected(false);
    radClientOui.setSelected(false);
    
    
    radOui.setSelected(false);
    radNon.setSelected(false);
    
    radPlus.setVisible(false);
    radmoin.setVisible(false);
    radOui.setSelected(false);
    radNon.setSelected(false);

    }  
       

    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
        
                          if (event.getCode().equals(KeyCode.ENTER) )
            {

                        prixOulmes = prixOulmesDAO.findPrixOulmesByCodeArt(codeArtField.getText());

                         LibelleCombo.setValue(prixOulmes.getProduit().getLibelle());
              }
  
    }

    @FXML
    private void ajouterSaisieAction(ActionEvent event) throws ParseException {
        
                AvoirOulmes avoirOulmes = new AvoirOulmes();

                     if(codeArtField.getText().equalsIgnoreCase("")|| 
                   LibelleCombo.getValue().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("")

                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
         
    }
else {
              
                             Produit produit = produitDAO.findByCode(codeArtField.getText());
                             
                                    String  client ="";         
                       if (client2Combo.getSelectionModel().getSelectedIndex()!=-1){
                           if(client2Combo.getSelectionModel().getSelectedItem().equals(Constantes.CLIENT_MINURSO)){
                           client = client2Combo.getSelectionModel().getSelectedItem();
                           }else if (client2Combo.getSelectionModel().getSelectedItem().equals(Constantes.CLIENT_MARJANE)){
                           client =  Constantes.SANS;
                           }
                       }else {
                       client= Constantes.SANS;
                       }
                               String lieu="";
                      if (lieuLivCombo.getSelectionModel().getSelectedIndex()!=-1){
                         lieu= lieuLivCombo.getSelectionModel().getSelectedItem();       
                       }else{
                       lieu = Constantes.SANS;
                       }  
                       
                    PrixOulmes prixOulmes =prixOulmesDAO.findPrixOulmesByFourAndArt(Constantes.FOURNISSEUR_OULMES,produit.getId(),client,lieu );
                    
            BigDecimal  prixB= BigDecimal.ZERO ;
            BigDecimal remise= BigDecimal.ZERO ;

            ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
            
            if(prixOulmes==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                prixB= prixOulmes.getPrix();
     
                     LocalDate localDate=dateSaisie.getValue();
            
                Date dateSaisieAvoir=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
                
                avoirOulmes.setPrix(prixB);
                
                if(qteAfficchage.getText().equals("")){
                    
                     BigDecimal QteB= new BigDecimal(quantiteField.getText());
                avoirOulmes.setQte(QteB);
                
                BigDecimal  montantB= (QteB.multiply(prixB)).setScale(2,RoundingMode.FLOOR);
                
                avoirOulmes.setMontant(montantB);
                
                 if (radOui.isSelected()==true){
             
              radPlus.setVisible(true);
              radmoin.setVisible(true);
              
               if (radPlus.isSelected()==true){
               
                remise = prixOulmes.getRemiseAvoir();
                 
                 montanNet = montantB.add((montantB.multiply(remise)).divide(new BigDecimal(100)));
                 
                  avoirOulmes.setMontantNet(montanNet);
                  avoirOulmes.setRemiseAvoir(remise);
                   
               }else if (radmoin.isSelected()==true){
               
                remise = prixOulmes.getRemiseAvoir();
                 
                 montanNet = montantB.subtract((montantB.multiply(remise)).divide(new BigDecimal(100)));
                 
                  avoirOulmes.setMontantNet(montanNet);
                  avoirOulmes.setRemiseAvoir(remise);
               
               }
               
             }else{
             
                 montanNet = montantB;
                 
                 avoirOulmes.setMontantNet(montanNet);
                 avoirOulmes.setRemiseAvoir(BigDecimal.ZERO);
             
             }
                
                }else {

                avoirOulmes.setQte(qteCaisse);
                
                 BigDecimal  montantB= (qteCaisse.multiply(prixB)).setScale(2,RoundingMode.FLOOR);
                
                avoirOulmes.setMontant(montantB);
                
                 if (radOui.isSelected()==true){
             
              radPlus.setVisible(true);
              radmoin.setVisible(true);
              
               if (radPlus.isSelected()==true){
               
                remise = prixOulmes.getRemiseAvoir();
                 
                 montanNet = montantB.add((montantB.multiply(remise)).divide(new BigDecimal(100)));
                 
                  avoirOulmes.setMontantNet(montanNet);
                  avoirOulmes.setRemiseAvoir(remise);
                   
               }else if (radmoin.isSelected()==true){
               
                remise = prixOulmes.getRemiseAvoir();
                 
                 montanNet = montantB.subtract((montantB.multiply(remise)).divide(new BigDecimal(100)));
                 
                  avoirOulmes.setMontantNet(montanNet);
                  avoirOulmes.setRemiseAvoir(remise);
               
               }
               
             }else{
             
                 montanNet = montantB;
                 
                 avoirOulmes.setMontantNet(montanNet);
                 avoirOulmes.setRemiseAvoir(BigDecimal.ZERO);
             
             }
                
                }
             avoirOulmes.setPrixOulmes(prixOulmes);
             
             
                   if (client2Combo.getSelectionModel().getSelectedIndex()!=-1){
                          avoirOulmes.setClient2(client2Combo.getSelectionModel().getSelectedItem());
                       }else {
                         avoirOulmes.setClient2(Constantes.SANS);  
                       }

                      if (lieuLivCombo.getSelectionModel().getSelectedIndex()!=-1){
                         avoirOulmes.setLieuLivraison(lieuLivCombo.getSelectionModel().getSelectedItem());     
                       }else{
                         avoirOulmes.setLieuLivraison(Constantes.SANS);  
                       }  
  

             avoirOulmes.setClient(clientMP.getNom());
             avoirOulmes.setDesignation(Constantes.ETAT_BL_AV+nLivraisonField.getText());
             avoirOulmes.setAction(Boolean.FALSE);
             avoirOulmes.setUtilisateurCreation(nav.getUtilisateur());
             avoirOulmes.setEtat(Constantes.ETAT_BL_AVOIR);
             avoirOulmes.setNumLivraison(nLivraisonField.getText());
             avoirOulmes.setDateSaisie(dateSaisieAvoir);

            avoirOulmesDAO.add(avoirOulmes);

            
              nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
       
              
       
          
                clear();
            setColumnProperties();
            loadDetail();
        

            
         }
}
        
        
    }

    @FXML
    private void refresh(ActionEvent event) {
        
        nLivraisonRechField.clear();
        clientCombo1.getSelectionModel().select(-1);
         setColumnProperties();
            loadDetail();
    }



    @FXML
    private void selectionnerToutMouseClicked(MouseEvent event) {
        
          ObservableList<AvoirOulmes> list=avoireOulmestable.getItems();
        
        for (Iterator<AvoirOulmes> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(true);
        }

        avoireOulmestable.refresh(); 
        
    }

    @FXML
    private void deselectionnerToutMouseClicked(MouseEvent event) {
        
               ObservableList<AvoirOulmes> list=avoireOulmestable.getItems();
        
        for (Iterator<AvoirOulmes> iterator = list.iterator(); iterator.hasNext();) {

            iterator.next().setAction(false);
        }

        avoireOulmestable.refresh(); 
        
    }

    @FXML
    private void calculeMouseClicked(MouseEvent event) {
        
         BigDecimal montantTotal= BigDecimal.ZERO;
          BigDecimal montantPalette= BigDecimal.ZERO;
           MHT = BigDecimal.ZERO ;
           MTVA = BigDecimal.ZERO ;
           MTTC = BigDecimal.ZERO ;
            
            
           for( int rows = 0;rows<listeAvoirOulmes.size() ;rows++ ){
 
                 AvoirOulmes avoirOulmes = listeAvoirOulmes.get(rows);
               
        if (actionColumn.getCellData(rows).booleanValue()==true){
        
           if (avoirOulmes.getPrixOulmes().getProduit().getPalette() == true){
                
                montantPalette = montantPalette.add(montantNetColumn.getCellData(rows));
                
                }else{

                montantTotal = montantTotal.add(montantNetColumn.getCellData(rows));  
                } 
             }
        } 
    
                MHT= montantTotal.add(montantPalette);

             MTVA = montantTotal.multiply(Constantes.TAUX_TVA_20);
            
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
    private void radOuiOnAction(ActionEvent event) {
        
    radPlus.setSelected(false);
    radmoin.setSelected(false);
    radPlus.setVisible(true);
    radmoin.setVisible(true);
        
    }

    @FXML
    private void radNonOnAction(ActionEvent event) {

    radPlus.setVisible(false);
    radmoin.setVisible(false);
    
    }

    @FXML
    private void qteByIntervalleOnAction(KeyEvent event) {
        
                      BigDecimal condCaisse = BigDecimal.ZERO;
           qteCaisse = BigDecimal.ZERO;
        
            qteAfficchage.setText("");

        PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
        
         if(prixOulmes!=null){
         
             condCaisse = prixOulmes.getConditionnementCaisse();
             
              if(prixOulmes!=null){
              
                   qteCaisse = new BigDecimal(quantiteField.getText()).multiply(condCaisse);
             
               DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

  

     qteAfficchage.setText(df.format(qteCaisse.setScale(2,RoundingMode.FLOOR)));
                  
              }else{
              
                       qteCaisse = new BigDecimal(quantiteField.getText()).multiply(new BigDecimal(1));
             
               DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

  

     qteAfficchage.setText(df.format(qteCaisse.setScale(2,RoundingMode.FLOOR)));
              
              }
             
             
            
         }else{
         
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.VERIFIER_CODE_LIBELLE);
         
         }
            
    }

    @FXML
    private void fournisseurOnAction(ActionEvent event) {
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
    }

    private void LibelleComboOnAction(ActionEvent event) {
        
   
    }

    @FXML
    private void rechercheBonAvoirKeyPressed(KeyEvent event) {
        
//    ObservableList<AvoirOulmes> listeAvoirOulmesRech=FXCollections.observableArrayList();
//                   
//    listeAvoirOulmesRech.clear();
//   
//   listeAvoirOulmesRech.addAll(avoirOulmesDAO.findAvoirOulmesByBonAvoir(nLivraisonRechField.getText()));
//   
//   avoireOulmestable.setItems(listeAvoirOulmesRech);
        
    }
        

    @FXML
    private void rechercheMouseClicked(MouseEvent event) {
        
         listeAvoirOulmes.clear();
        
           if (clientCombo1.getSelectionModel().getSelectedIndex() == -1 && nLivraisonRechField.getText().equals("") ){
 
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
            } else if (clientCombo1.getSelectionModel().getSelectedIndex() != -1 && nLivraisonRechField.getText().equals("") ){
          
            listeAvoirOulmes.addAll(avoirOulmesDAO.findAvoirOulmesByClient(clientCombo1.getSelectionModel().getSelectedItem()));
          
          avoireOulmestable.setItems(listeAvoirOulmes);
          
          }else if (clientCombo1.getSelectionModel().getSelectedIndex() == -1 && !nLivraisonRechField.getText().equals("") ){
          
            listeAvoirOulmes.addAll(avoirOulmesDAO.findAvoirOulmesByBonAvoir(nLivraisonRechField.getText()));
          
          avoireOulmestable.setItems(listeAvoirOulmes);
          
          }else if (clientCombo1.getSelectionModel().getSelectedIndex() != -1 && !nLivraisonRechField.getText().equals("") ){
          
            listeAvoirOulmes.addAll(avoirOulmesDAO.findAvoirOulmesByBonAvoirAndClient(nLivraisonRechField.getText(),clientCombo1.getSelectionModel().getSelectedItem()));
          
          avoireOulmestable.setItems(listeAvoirOulmes);
          
          }
        
        
        
    }

    @FXML
    private void radClient2OuiOnAction(ActionEvent event) {
       LibelleCombo.getItems().clear();  
       codeArtField.clear();
        client2Combo.setDisable(false);
        
    }

    @FXML
    private void radClient2NonOnAction(ActionEvent event) {
        
         LibelleCombo.getItems().clear();
        codeArtField.clear();
        client2Combo.getSelectionModel().select(-1);
        lieuLivCombo.getSelectionModel().select(-1);
        
        
                List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findPrixFilmByClient(Constantes.SANS);

        for (int i = 0; i < listPrixOulmes.size(); i++) {
                
                PrixOulmes prixOulmes = listPrixOulmes.get(i);
                
                LibelleCombo.getItems().add(prixOulmes.getProduit().getLibelle());
                
                mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);

            }
        
        client2Combo.setDisable(true);
        lieuLivCombo.setDisable(true);
        
    }

    @FXML
    private void client2OnAction(ActionEvent event) {
        
         LibelleCombo.getItems().clear();      
        codeArtField.clear();
        
        String valeur = client2Combo.getSelectionModel().getSelectedItem();
        
        if (valeur!=null){

        if (valeur.equals(Constantes.CLIENT_MINURSO)){
            
            lieuLivCombo.getItems().clear();
            lieuLivCombo.setDisable(false);
            
   List <String> listeObjectLieu =prixOulmesDAO.findPrixOulmesByClient(Constantes.CLIENT_MINURSO);

   System.out.println("listeObjectLieu "+listeObjectLieu.size());
         
            for(int i=0; i<listeObjectLieu.size(); i++) {

                    String prixOulmes = listeObjectLieu.get(i);

                       lieuLivCombo.getItems().add(prixOulmes);
                       
                    }

        }
        else if (valeur.equals(Constantes.CLIENT_MARJANE)){
            
              lieuLivCombo.getItems().clear();
            lieuLivCombo.setDisable(true);
            
             List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findPrixFilmByClient(Constantes.SANS);

            for (int i = 0; i < listPrixOulmes.size(); i++) {
                
                PrixOulmes prixOulmes = listPrixOulmes.get(i);
                
                LibelleCombo.getItems().add(prixOulmes.getProduit().getLibelle());
                
                mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
               
            }
        }
    }

    }

    @FXML
    private void LibelleCombOnAction(ActionEvent event) {
        

                 PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());

        if(prixOulmes!=null){

         codeArtField.setText(prixOulmes.getProduit().getCode());

         }
        
    }

    @FXML
    private void lieuLivOnAction(ActionEvent event) {
        
            LibelleCombo.getItems().clear();
            codeArtField.clear();
            
             List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findPrixOulmesByLieu(lieuLivCombo.getSelectionModel().getSelectedItem());

       for (int i = 0; i < listPrixOulmes.size(); i++) {
                
                PrixOulmes prixOulmes = listPrixOulmes.get(i);

                LibelleCombo.getItems().add(prixOulmes.getProduit().getLibelle());
                
                mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
       
             
    }
    
}
}