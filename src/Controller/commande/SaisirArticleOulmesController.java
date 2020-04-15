/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.commande;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.DetailCommande;
import dao.Entity.Dimension;
import dao.Entity.Fournisseur;
import dao.Entity.MatierePremier;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
import dao.Manager.ClientMPDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import function.navigation;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public abstract class SaisirArticleOulmesController extends AnchorPane implements Initializable {
    private int POUR;
    Commande commande;   
    
    @FXML
    private TextField nCommandeField;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private ComboBox<String> fornisseurCombo;
    @FXML
    private DatePicker dateSaisie;
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField codeArtField;
    @FXML
    private DatePicker dateSaisie1;
    @FXML
    private Label qteAfficchage;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private ComboBox<String> LibelleCombo;
    @FXML
    private TableView<DetailCommande> detailCommandetable;
    @FXML
    private TableColumn<DetailCommande, String> codeArtColumn;
    @FXML
    private TableColumn<DetailCommande, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteColumn;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button validerSaisie;

    public SaisirArticleOulmesController (int POUR,Commande commande){
    this.commande= commande;
    this.POUR=POUR;
    setAll(nav.getSaisirArticleOulmes(), this);
    }
    
    public static void setAll(String path, Object root){
    FXMLLoader fxmlLoader = new FXMLLoader(root.getClass().getResource(path));
        fxmlLoader.setRoot(root);
        fxmlLoader.setController(root);
        try {
            System.out.println(path);
            fxmlLoader.load();
        } catch (IOException exception){
            throw new RuntimeException(exception);
        }

    }
    public abstract void refresh();
    
    private ObservableList<DetailCommande> listeDetailCommande;
    navigation nav = new navigation();
    
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
    
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl(); 
    ProduitDAO produitDAO = new ProduitDAOImpl(); 
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
    CommandeDAO commandeDAO = new CommandeDAOImpl(); 
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
    
    PrixOulmes prixOulmes =new PrixOulmes();
    
       BigDecimal montanTotal=BigDecimal.ZERO;
    
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



    }
    
       void loadDetail(){

        detailCommandetable.setItems(listeDetailCommande);
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
             listeDetailCommande =FXCollections.observableArrayList(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
       
       
    
        nCommandeField.setText(commande.getnCommande());
        clientCombo.setValue(commande.getClientMP().getNom());
        LocalDate date = new java.util.Date( commande.getDateCreation().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dateSaisie.setValue(date);
        fornisseurCombo.setValue(commande.getFourisseur().getNom());

                     List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findAll();

        listPrixOulmes.stream().map((prixOulmes) -> {
            LibelleCombo.getItems().addAll(prixOulmes.getProduit().getLibelle());
            return prixOulmes;
        }).forEachOrdered((prixOulmes) -> {
            mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
        });
          
        

        
          detailCommandetable.setEditable(true);
          quantiteColumn.setEditable(true);
          montanTotal=BigDecimal.ZERO;
    
          setColumnProperties();    
          loadDetail();
        
    }    

    @FXML
    private void commandeKeyPressed(KeyEvent event) {
    }

    @FXML
    private void qteByIntervalleOnAction(KeyEvent event) {
        
          qteAfficchage.setText("");
       
         DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
         dfs.setDecimalSeparator(',');
         dfs.setGroupingSeparator('.');
       DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
       df.setGroupingUsed(true);
////       dfs.getDecimalFormatSymbols()= DecimalFormatSymbols(Locale.getDefault());
     qteAfficchage.setText(df.format(new BigDecimal(quantiteField.getText()).setScale(2,RoundingMode.DOWN)));
  
        
    }

    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
        
           if (event.getCode().equals(KeyCode.ENTER) )
            {
              if(clientCombo.getSelectionModel().getSelectedItem().equals(null) || fornisseurCombo.getSelectionModel().getSelectedItem().equals(null) || codeArtField.getText().equals(null)){
                
                      nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.VERIFIER_FOURNISSEUR_CLIENT);
                  
                }else{
            
                         prixOulmes = prixOulmesDAO.findPrixOulmesByCodeArt(codeArtField.getText());
                         
                         
                         LibelleCombo.setValue(prixOulmes.getProduit().getLibelle());

              }
            }
        
    }

    @FXML
    private void ajouterSaisieAction(ActionEvent event) {
        
         DetailCommande detailCommande = new DetailCommande();

                     if(codeArtField.getText().equalsIgnoreCase("")|| 
                   LibelleCombo.getValue().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("")
                 

                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
              
            BigDecimal Qte = BigDecimal.ZERO;
                         
            Fournisseur fournisseur =commande.getFourisseur();
            
            Produit produit = produitDAO.findByCode(codeArtField.getText());
                       
                         
                    PrixOulmes prixOulmes =prixOulmesDAO.findPrixOulmesByFourAndArt(fournisseur.getNom(),produit.getId(), Constantes.SANS,Constantes.SANS);
            BigDecimal  prixB= BigDecimal.ZERO ;
            
            if(prixOulmes==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                prixB= prixOulmes.getPrix();

                  MatierePremier matierePremier = matierePremiereDAO.findByCode(Constantes.CODE_MP);
           
                 
                  Qte = new BigDecimal(quantiteField.getText());
                  
                  
             detailCommande.setDimension(matierePremier.getDimension());
             detailCommande.setMatierePremier(matierePremier);
             detailCommande.setQuantite(Qte);
             detailCommande.setQuantiteRestee(Qte);
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setCommande(commande);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setRemiseAchat(prixOulmes.getRemiseAchat());
             detailCommande.setPrixUnitaire(prixB);
             detailCommande.setPrixOulmes(prixOulmes);
           
             BigDecimal QteB= Qte;
             BigDecimal PrixB= prixB;
            
             BigDecimal  montantB= (QteB.multiply(PrixB)).setScale(2,RoundingMode.FLOOR);

             montanTotal=montanTotal.add(montantB).setScale(2,RoundingMode.FLOOR);

            listeDetailCommande.add(detailCommande);

            setColumnProperties();
            loadDetail();
            clear();

            
         }
}
        
        
    }

      private void clear(){
          
        codeArtField.setText("");
        quantiteField.clear();
        qteAfficchage.setText("");
        LibelleCombo.getSelectionModel().select(-1);

    } 
    
    @FXML
    private void LibelleComboOnAction(ActionEvent event) {
        
                 PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
         
          if(prixOulmes!=null){
              
         codeArtField.setText(prixOulmes.getProduit().getCode());
         
          }
        
    }

    @FXML
    private void refreshCommande(ActionEvent event) {
    }

    @FXML
    private void validerSaisieAction(ActionEvent event) {
        
         if(clientCombo.getSelectionModel().getSelectedItem()== null || clientCombo.getSelectionModel().getSelectedItem().isEmpty() ||fornisseurCombo.getSelectionModel().getSelectedItem()== null || fornisseurCombo.getSelectionModel().getSelectedItem().isEmpty() || detailCommandetable.getItems().size() ==0 ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
     }
        else {
            

        commande.setDetailCommandes(listeDetailCommande);
        commandeDAO.edit(commande);
     
        
       
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
 
            refresh();
            Stage stage = (Stage)
            validerSaisie.getScene().getWindow();
            stage.close();
        }
      

        
    }
    
}
