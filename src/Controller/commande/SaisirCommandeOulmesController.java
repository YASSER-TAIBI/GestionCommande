/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.commande;

import Utils.Constantes;
import dao.Entity.Chauffeur;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.DetailCommande;
import dao.Entity.Fournisseur;
import dao.Entity.MatierePremier;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
import dao.Entity.Sequenceur;
import dao.Manager.ChauffeurDAO;
import dao.Manager.ClientMPDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.DimensionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.ChauffeurDAOImpl;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DimensionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SaisirCommandeOulmesController implements Initializable {

    @FXML
    private TextField nCommandeField;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField etatField;
    @FXML
    private TableView<DetailCommande> detailCommandetable;
    @FXML
    private TableColumn<DetailCommande, String> codeArtColumn;
    @FXML
    private TableColumn<DetailCommande, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteCaisseColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantitePaletteColumn;
    @FXML
    private Label qtePaletteAfficchage;
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField codeArtField;
    @FXML
    private DatePicker dateSaisie;
    @FXML
    private Label qteAfficchage;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button validerSaisie;
    @FXML
    private ComboBox<String> LibelleCombo;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private DatePicker dateCharg;
    @FXML
    private CheckBox checkCommande;
    @FXML
    private RadioButton depotRadio;
    @FXML
    private RadioButton fourRadio;
    @FXML
    private ToggleGroup DepotFour;
    @FXML
    private TextField quantiteCaisseField;
    @FXML
    private ComboBox<String> matriculeCombo;
    @FXML
    private ComboBox<String> chauffeurCombo;
    
    private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    private Map<String,Chauffeur> mapChauffeur=new HashMap<>();
    
    PrixOulmes prixOulmes =new PrixOulmes();
    
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();  
    ProduitDAO produitDAO = new ProduitDAOImpl();
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    ChauffeurDAO chauffeurDAO = new ChauffeurDAOImpl();
    DimensionDAO dimensionDAO = new DimensionDAOImpl();
    MatierePremiereDAO matierePremierDAO = new MatierePremierDAOImpl();
    CommandeDAO commandeDAO = new CommandeDAOImpl();
     
    private final ObservableList<DetailCommande> listeDetailCommande=FXCollections.observableArrayList();
    
    BigDecimal montanTotal=BigDecimal.ZERO;
    Commande commande = new Commande();
    
    ClientMP clientMP;    
         
       BigDecimal qteCaisse = BigDecimal.ZERO;
       BigDecimal qtePack = BigDecimal.ZERO;
       navigation nav = new navigation();
    
            Date date = new  Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yy");
    
    
    
    
    
   
   

       
      
    /**
     * Initializes the controller class.
     */
    
      void setColumnProperties(){
         
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

         quantiteCaisseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteCaisse()));
                }
                
             });
         
         quantitePaletteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantitePalette()));
                }
                
             });
    }
    
    void loadDetail(){

        detailCommandetable.setItems(listeDetailCommande);
    }
    
    void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.COMMANDE_OULMES);
          nCommandeField.setText((sequenceur.getValeur()+1)+"/21");   
//        nCommandeField.setText((sequenceur.getValeur()+1)+"/"+dateFormat.format(date));
   }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
Incrementation ();        

   etatField.setText(Constantes.ETAT_COMMANDE_LANCE);
   
         List<Fournisseur> listFournisseur=fournisseurDAO.findAllPF();
        
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

               List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findAll();

        listPrixOulmes.stream().map((prixOulmes) -> {
            LibelleCombo.getItems().addAll(prixOulmes.getProduit().getLibelle());
            return prixOulmes;
        }).forEachOrdered((prixOulmes) -> {
            mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
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
        
        
        
        depotRadio.setVisible(false);
        fourRadio.setVisible(false);
        
        quantiteCaisseField.setDisable(true);
        quantiteField.setDisable(true);
        
// TODO
    }    
    
    

    @FXML
    private void qteByIntervalleOnAction(KeyEvent event) {
        
    BigDecimal condCaisse = BigDecimal.ZERO;
    BigDecimal QtePalette = BigDecimal.ZERO;
    Boolean palette = null;
    
           qteCaisse = BigDecimal.ZERO;
           qtePack = BigDecimal.ZERO;
        
           qtePaletteAfficchage.setText("");
           qteAfficchage.setText("");

        PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
        
         if(prixOulmes!=null){
         
             condCaisse = prixOulmes.getConditionnementCaisse();
             QtePalette = prixOulmes.getProduit().getQtePalette();
             palette = prixOulmes.getProduit().getPalette();
             
                DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);

       if(palette.equals(Boolean.TRUE)){
       
        qtePack = new BigDecimal(quantiteField.getText());
        
            qtePaletteAfficchage.setText(df.format(qtePack));
            
            qteAfficchage.setText(df.format(BigDecimal.ZERO.setScale(2)));    
           
       }else if(palette.equals(Boolean.FALSE) && QtePalette.compareTo(BigDecimal.ZERO)>0){
              
                   qtePack = new BigDecimal(quantiteField.getText()).multiply(QtePalette).setScale(2);
                   
                        qtePaletteAfficchage.setText(df.format(qtePack));
                   
                        if(condCaisse.compareTo(BigDecimal.ZERO)>0){
              
                   qteCaisse = qtePack.divide(condCaisse,2, RoundingMode.CEILING);

     qteAfficchage.setText(df.format(qteCaisse));

     
              }else if(condCaisse.compareTo(BigDecimal.ZERO)==0) {

     qteAfficchage.setText(df.format(BigDecimal.ZERO.setScale(2)));
     
              }
                        

              }else if(palette.equals(Boolean.FALSE) && QtePalette.compareTo(BigDecimal.ZERO)==0){

    qtePaletteAfficchage.setText(df.format(BigDecimal.ZERO.setScale(2)));
    
    qteAfficchage.setText(df.format(BigDecimal.ZERO.setScale(2)));
    
     nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.VERIFIER_QTE_PALETTE);
     
      codeArtField.clear();
                LibelleCombo.getSelectionModel().select(-1);
                quantiteField.clear();
                qteAfficchage.setText("");
                qtePaletteAfficchage.setText("");
     
         return;
              }
       
         }else{
         
            nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.VERIFIER_CODE_LIBELLE);
         return;
         }
    }

    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
                  if (event.getCode().equals(KeyCode.ENTER) )
            {
              if(clientCombo.getSelectionModel().getSelectedItem().equals(null) || fourCombo.getSelectionModel().getSelectedItem().equals(null) || codeArtField.getText().equals(null)){
                
                      nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.VERIFIER_FOURNISSEUR_CLIENT);
                  
                }else{
            
                         prixOulmes = prixOulmesDAO.findPrixOulmesByCodeArt(codeArtField.getText());
                         
                         
                         LibelleCombo.setValue(prixOulmes.getProduit().getLibelle());

              }
            }
          
    }

    @FXML
    private void ajouterSaisieAction(ActionEvent event) {
    
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
          DetailCommande detailCommande = new DetailCommande();

                     if(codeArtField.getText().equalsIgnoreCase("")|| 
                   LibelleCombo.getValue().equalsIgnoreCase("") || 
                   fourCombo.getValue().equalsIgnoreCase("") || 
                   clientCombo.getValue().equalsIgnoreCase("")
                 

                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
              
            BigDecimal Qte = BigDecimal.ZERO;
                         
             Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());  
            Produit produit = produitDAO.findByCode(codeArtField.getText());
            
                         
                    PrixOulmes prixOulmes =prixOulmesDAO.findPrixOulmesByFourAndArt(fournisseur.getNom(),produit.getId(), Constantes.SANS, Constantes.SANS);
                    
            BigDecimal  prixB= BigDecimal.ZERO ;
            
            if(prixOulmes==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                prixB= prixOulmes.getPrix();

                  MatierePremier matierePremier = matierePremierDAO.findByCode(Constantes.CODE_MP);
           
                 
     
                  
                  
             detailCommande.setDimension(matierePremier.getDimension());
             detailCommande.setMatierePremier(matierePremier);
             
             if(!fournisseur.getNom().equals(Constantes.FOURNISSEUR_OULMES)){
             
                 BigDecimal qtePack = BigDecimal.ZERO;
                 BigDecimal qteCaisse = new BigDecimal(quantiteCaisseField.getText());
                 
                 qtePack= qteCaisse.multiply(prixOulmes.getConditionnementCaisse()).setScale(2, RoundingMode.CEILING);
                 
             detailCommande.setQuantite(qtePack);
             detailCommande.setQuantiteCaisse(qteCaisse);
             detailCommande.setQuantitePalette(BigDecimal.ZERO);
             detailCommande.setQuantiteRestee(qtePack);
                 
                 
             }else{
             
                 Qte = new BigDecimal(quantiteField.getText());
                 
             detailCommande.setQuantite(qtePack);
             detailCommande.setQuantiteCaisse(qteCaisse);
             detailCommande.setQuantitePalette(Qte);
             detailCommande.setQuantiteRestee(qtePack);
             
             }
             if(prixOulmes.getProduit().getQteCaisse().compareTo(BigDecimal.ZERO)>0 && prixOulmes.getProduit().getQteBouteille().compareTo(BigDecimal.ZERO)>0){
             detailCommande.setQuantiteCaisseProduit(Qte.multiply(prixOulmes.getProduit().getQteCaisse()));
             detailCommande.setQuantiteBouteille(Qte.multiply(prixOulmes.getProduit().getQteBouteille()));
             
             }else {
             detailCommande.setQuantiteCaisseProduit(BigDecimal.ZERO);
             detailCommande.setQuantiteBouteille(BigDecimal.ZERO);
             }
             
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setCommande(commande);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setRemiseAchat(prixOulmes.getRemiseAchat());
             detailCommande.setPrixUnitaire(prixB);
             detailCommande.setPrixOulmes(prixOulmes);
           
         

            listeDetailCommande.add(detailCommande);

            
            
            setColumnProperties();
            loadDetail();
            
                codeArtField.clear();
                LibelleCombo.getSelectionModel().select(-1);
                quantiteField.clear();
                qteAfficchage.setText("");
                qtePaletteAfficchage.setText("");
                quantiteCaisseField.clear();
         }
}
            }
    }

    void clear(){
    
    codeArtField.clear();
    LibelleCombo.getSelectionModel().select(-1);
    quantiteField.clear();
    chauffeurCombo.getSelectionModel().select(-1);
    matriculeCombo.getSelectionModel().select(-1);
    dateCharg.setValue(null);

    qteAfficchage.setText("");
    qtePaletteAfficchage.setText("");
    quantiteCaisseField.clear();
    
    quantiteCaisseField.setDisable(true);
    quantiteField.setDisable(true);
    
    }
    
    @FXML
    private void refreshCommande(ActionEvent event) {
        
        clear();
        
    }

    @FXML
    private void validerSaisieAction(ActionEvent event) throws ParseException {
        
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        if(clientCombo.getSelectionModel().getSelectedItem().isEmpty()||clientCombo.getSelectionModel().getSelectedItem().isEmpty() || detailCommandetable.getItems().size() ==0 ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
     }
        else {
          
             Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.COMMANDE_SPECIAL_PF);
                  int comSpPF = sequenceur.getValeur()+1;
            
            Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());    
           ClientMP clientMP = mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
           Chauffeur chauffeur  = mapChauffeur.get(chauffeurCombo.getSelectionModel().getSelectedItem());
            LocalDate localDate=dateSaisie.getValue();
            
          Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
          Date dateChargement= null;
          if (dateCharg.getValue()!= null){
          
            localDate=dateCharg.getValue();
            
           dateChargement=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
          }
//###########################################################################  Detail Commande "Emballages"  #################################################################################################################################################################################################################################################################################################################################################################     
           
            BigDecimal qtePalette = BigDecimal.ZERO;
            BigDecimal qteCaisse = BigDecimal.ZERO;
            BigDecimal qteBouteille = BigDecimal.ZERO;

            for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if(detailCommande.getPrixOulmes().getProduit().getQtePalette().compareTo(BigDecimal.ZERO)>0){
                qtePalette = qtePalette.add(detailCommande.getQuantitePalette());
                }
                if(detailCommande.getPrixOulmes().getProduit().getQteCaisse().compareTo(BigDecimal.ZERO)>0){
                qteCaisse = qteCaisse.add(detailCommande.getQuantiteCaisseProduit());
                }
                
                if(detailCommande.getPrixOulmes().getProduit().getQteBouteille().compareTo(BigDecimal.ZERO)>0){
                qteBouteille = qteBouteille.add(detailCommande.getQuantiteBouteille());
                }
                
            }

             MatierePremier matierePremier = matierePremierDAO.findByCode(Constantes.CODE_MP);
            
             
            if(qtePalette.compareTo(BigDecimal.ZERO)>0){
                
                 DetailCommande detailCommande = new DetailCommande();
                      
                 
                    PrixOulmes prixOulmes = prixOulmesDAO.findById(21);

             detailCommande.setDimension(matierePremier.getDimension());
             detailCommande.setMatierePremier(matierePremier);
             detailCommande.setQuantite(qtePalette);
             detailCommande.setQuantiteCaisse(BigDecimal.ZERO);
             detailCommande.setQuantitePalette(BigDecimal.ZERO);
             detailCommande.setQuantiteRestee(qtePalette);
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setCommande(commande);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setRemiseAchat(prixOulmes.getRemiseAchat());
             detailCommande.setPrixUnitaire(prixOulmes.getPrix());
             detailCommande.setPrixOulmes(prixOulmes);

            listeDetailCommande.add(detailCommande);
                
            }
            
               if(qteCaisse.compareTo(BigDecimal.ZERO)>0){
                
                 DetailCommande detailCommande = new DetailCommande();
                      
                 
                    PrixOulmes prixOulmes = prixOulmesDAO.findById(29);

             detailCommande.setDimension(matierePremier.getDimension());
             detailCommande.setMatierePremier(matierePremier);
             detailCommande.setQuantite(qteCaisse);
             detailCommande.setQuantiteCaisse(BigDecimal.ZERO);
             detailCommande.setQuantitePalette(BigDecimal.ZERO);
             detailCommande.setQuantiteRestee(qteCaisse);
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setCommande(commande);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setRemiseAchat(prixOulmes.getRemiseAchat());
             detailCommande.setPrixUnitaire(prixOulmes.getPrix());
             detailCommande.setPrixOulmes(prixOulmes);

            listeDetailCommande.add(detailCommande);
                
            }
               
               
               if(qteBouteille.compareTo(BigDecimal.ZERO)>0){
                
                 DetailCommande detailCommande = new DetailCommande();
                      
                 
                    PrixOulmes prixOulmes = prixOulmesDAO.findById(28);

             detailCommande.setDimension(matierePremier.getDimension());
             detailCommande.setMatierePremier(matierePremier);
             detailCommande.setQuantite(qteBouteille);
             detailCommande.setQuantiteCaisse(BigDecimal.ZERO);
             detailCommande.setQuantitePalette(BigDecimal.ZERO);
             detailCommande.setQuantiteRestee(qteBouteille);
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setCommande(commande);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setRemiseAchat(prixOulmes.getRemiseAchat());
             detailCommande.setPrixUnitaire(prixOulmes.getPrix());
             detailCommande.setPrixOulmes(prixOulmes);

            listeDetailCommande.add(detailCommande);
                
            }
            
            BigDecimal qteCommande = BigDecimal.ZERO;
            BigDecimal prixCommande = BigDecimal.ZERO;
            
                  for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                  
                qteCommande = qteCommande.add(detailCommande.getQuantite());
                prixCommande = prixCommande.add(detailCommande.getPrixUnitaire());
                  
                  }
               
             montanTotal = qteCommande.multiply(prixCommande);
                  
//###########################################################################   Commande   #################################################################################################################################################################################################################################################################################################################################################################     
           if (chauffeur!= null){
          
            commande.setChauffeur(chauffeur);
          }
          
        commande.setUtilisateurCreation(nav.getUtilisateur());
        commande.setDepot(nav.getUtilisateur().getDepot());
        commande.setDateSaisie(new Date());
        commande.setDateCreation(dateSaisie);
        commande.setEtat(Constantes.ETAT_COMMANDE_LANCE);
        commande.setTypeCommande(Constantes.COMMANDE_INTERNE_ART);
        
        if (checkCommande.isSelected() && depotRadio.isSelected()){
            commande.setnCommande(nCommandeField.getText()+" "+Constantes.CODE_SPECIAL_DEPOT+comSpPF);
        }
        else if(checkCommande.isSelected() && fourRadio.isSelected()) {
            commande.setnCommande(nCommandeField.getText()+" "+Constantes.CODE_SPECIAL_FOUR+comSpPF);
        }
        else {
            commande.setnCommande(nCommandeField.getText());
        }
        
        commande.setDateChargement(dateChargement);
        
        commande.setDetailCommandes(listeDetailCommande);
        commande.setFourisseur(fournisseur);
        commande.setClientMP(clientMP);
        commande.setPrixTotal(montanTotal);
        
        commandeDAO.add(commande);
     
        commande = new Commande();
       
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
 
         
               if (checkCommande.isSelected()== false){
                     Sequenceur sequenceurCom = sequenceurDAO.findByCode(Constantes.COMMANDE_OULMES);
           sequenceurCom.setValeur(sequenceurCom.getValeur()+1);
           sequenceurDAO.edit(sequenceurCom);
           Incrementation (); 
                  }else if (checkCommande.isSelected()== true){
              
     Sequenceur sequenceurComSp = sequenceurDAO.findByCode(Constantes.COMMANDE_SPECIAL_PF);
     
           sequenceurComSp.setValeur(sequenceurComSp.getValeur()+1);
           sequenceurDAO.edit(sequenceurComSp);
          }
  
            clear();
           this.dateSaisie.setValue(null);
           depotRadio.setVisible(false);
            fourRadio.setVisible(false);

            clientCombo.getItems().clear();
            fourCombo.getItems().clear();
          }
        
        detailCommandetable.getItems().clear();
        }}

    @FXML
    private void LibelleComboOnAction(ActionEvent event) {
        
         PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
         
          if(prixOulmes!=null){
              
         codeArtField.setText(prixOulmes.getProduit().getCode());
         
          }
          
    }

    @FXML
    private void fournisseurOnAction(ActionEvent event) {
        
          Fournisseur fournisseur  = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
         
          if(fournisseur!=null){

                        if(!fournisseur.getNom().equals(Constantes.FOURNISSEUR_OULMES)){
                        
                        quantiteCaisseField.clear();
                        quantiteField.clear();
                        qtePaletteAfficchage.setText("");
                        qteAfficchage.setText("");
                        
                        quantiteCaisseField.setDisable(false);
                        quantiteField.setDisable(true);

                        }else{
                        
                        quantiteCaisseField.clear();
                        quantiteField.clear();
                        qtePaletteAfficchage.setText("");
                        qteAfficchage.setText("");
                        
                        quantiteCaisseField.setDisable(true);
                        quantiteField.setDisable(false);
                            
                        }
          }
        
    }

    @FXML
    private void checkCommandeOnAction(ActionEvent event) {
        
                if(checkCommande.isSelected())
        {
            depotRadio.setVisible(true);
            fourRadio.setVisible(true);
            
        }else{
            
         depotRadio.setVisible(false);
         fourRadio.setVisible(false);
            
         depotRadio.setSelected(false);
         fourRadio.setSelected(false);
            
            Incrementation ();
           nCommandeField.setDisable(true);
        } 
        
    }

    @FXML
    private void depotRadioOnAction(ActionEvent event) {
        
            if(depotRadio.isSelected())
        {
        nCommandeField.setDisable(false);
        
        }else{
  
           Incrementation ();
           nCommandeField.setDisable(true);
        }   nCommandeField.clear();
        
        
    }

    @FXML
    private void fourRadioOnAction(ActionEvent event) {
        
            if(fourRadio.isSelected())
        {
        nCommandeField.setDisable(false);
        
        }else{
  
           Incrementation ();
           nCommandeField.setDisable(true);
        }   nCommandeField.clear();
        
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
