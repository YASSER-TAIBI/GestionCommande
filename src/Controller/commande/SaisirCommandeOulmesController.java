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
import dao.Entity.Sequenceur;
import dao.Manager.ClientMPDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.DimensionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.Manager.SequenceurDAO;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TextField fourField;
    @FXML
    private TableView<DetailCommande> detailCommandetable;
    @FXML
    private TableColumn<DetailCommande, String> codeArtColumn;
    @FXML
    private TableColumn<DetailCommande, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteColumn;
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
    
    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
    
    PrixOulmes prixOulmes =new PrixOulmes();
    
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();  
    ProduitDAO produitDAO = new ProduitDAOImpl();
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    DimensionDAO dimensionDAO = new DimensionDAOImpl();
    MatierePremiereDAO matierePremierDAO = new MatierePremierDAOImpl();
    CommandeDAO commandeDAO = new CommandeDAOImpl();
     
    private final ObservableList<DetailCommande> listeDetailCommande=FXCollections.observableArrayList();
    
    BigDecimal montanTotal=BigDecimal.ZERO;
    Commande commande = new Commande();
    
    ClientMP clientMP;    
         
      
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

    }
    
    void loadDetail(){

        detailCommandetable.setItems(listeDetailCommande);
    }
    
    void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.COMMANDE_OULMES);
          nCommandeField.setText((sequenceur.getValeur()+1)+"/20");   
//        nCommandeField.setText((sequenceur.getValeur()+1)+"/"+dateFormat.format(date));
          
             List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findAll();

        listPrixOulmes.stream().map((prixOulmes) -> {
            LibelleCombo.getItems().addAll(prixOulmes.getProduit().getLibelle());
            return prixOulmes;
        }).forEachOrdered((prixOulmes) -> {
            mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
        });
          

   }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
Incrementation ();        

   etatField.setText(Constantes.ETAT_COMMANDE_LANCE);
   
   fourField.setText(Constantes.FOURNISSEUR_OULMES);
        
         List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((clientMP) -> {
            clientCombo.getItems().addAll(clientMP.getNom());
            return clientMP;
        }).forEachOrdered((clientMP) -> {
            mapClientMP.put(clientMP.getNom(), clientMP);
        });

          montanTotal=BigDecimal.ZERO;
// TODO
    }    
    
    

    @FXML
    private void qteByIntervalleOnAction(KeyEvent event) {
        

        
    }

    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
                  if (event.getCode().equals(KeyCode.ENTER) )
            {
              if(clientCombo.getSelectionModel().getSelectedItem().equals(null) || fourField.getText().isEmpty() || codeArtField.getText().equals(null)){
                
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
                         

            Produit produit = produitDAO.findByCode(codeArtField.getText());
            
                         
                    PrixOulmes prixOulmes =prixOulmesDAO.findPrixOulmesByFourAndArt(fourField.getText(),produit.getId(), Constantes.SANS, Constantes.SANS);
            BigDecimal  prixB= BigDecimal.ZERO ;
            
            if(prixOulmes==null){
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_PRIX_SAISIE);  
                return;
            }
            else {
                
                prixB= prixOulmes.getPrix();

                  MatierePremier matierePremier = matierePremierDAO.findByCode(Constantes.CODE_MP);
           
                 
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

    void clear(){
    
    codeArtField.clear();
      LibelleCombo.getSelectionModel().select(-1);;
    quantiteField.clear();

    }
    
    @FXML
    private void refreshCommande(ActionEvent event) {
        
        clear();
        
    }

    @FXML
    private void validerSaisieAction(ActionEvent event) throws ParseException {
        
          
        if(clientCombo.getSelectionModel().getSelectedItem().isEmpty()||fourField.getText().isEmpty() || detailCommandetable.getItems().size() ==0 ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
     }
        else {
            
            Fournisseur fournisseur = fournisseurDAO.findByFournisseur(fourField.getText());
                
            LocalDate localDate=dateSaisie.getValue();
            
          Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
        commande.setUtilisateurCreation(nav.getUtilisateur());
        commande.setDepot(nav.getUtilisateur().getDepot());
        commande.setDateSaisie(new Date());
        commande.setDateCreation(dateSaisie);
        commande.setEtat(Constantes.ETAT_COMMANDE_LANCE);
        commande.setTypeCommande(Constantes.COMMANDE_INTERNE_ART);
        commande.setnCommande(nCommandeField.getText());
     
        
        commande.setDetailCommandes(listeDetailCommande);
        commande.setFourisseur(fournisseur);
        commande.setClientMP(mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem()));
        commande.setPrixTotal(montanTotal);
        
        commandeDAO.add(commande);
     
        commande = new Commande();
       
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
 
     Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.COMMANDE_OULMES);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();
          }
        
        detailCommandetable.getItems().clear();
        }

    @FXML
    private void LibelleComboOnAction(ActionEvent event) {
        
         PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
         
          if(prixOulmes!=null){
              
         codeArtField.setText(prixOulmes.getProduit().getCode());
         
          }
    }
        
    
    
}
