/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CommandeEtrangere;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.CommandeEtrangere;
import dao.Entity.DetailCommandeEtrangere;
import dao.Entity.Devise;
import dao.Entity.FamilleArticle;
import dao.Entity.Fournisseur;

import dao.Entity.Sequenceur;
import dao.Entity.SousFamilleArticle;
import dao.Entity.TauxDevise;
import dao.Manager.ArticleDAO;
import dao.Manager.CommandeEtrangereDAO;
import dao.Manager.DeviseDAO;
import dao.Manager.FamilleArticleDAO;
import dao.Manager.FournisseurDAO;

import dao.Manager.SequenceurDAO;
import dao.Manager.TauxDeviseDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import dao.ManagerImpl.CommandeEtrangereDAOImpl;
import dao.ManagerImpl.DeviseDAOImpl;
import dao.ManagerImpl.FamilleArticleDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;

import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.TauxDeviseDAOImpl;
import function.navigation;
import java.math.BigDecimal;
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
public class SaisirCommandeEtrangereController implements Initializable {

    @FXML
    private TextField nCommandeField;
    @FXML
    private TextField etatField;
    @FXML
    private ComboBox<String> fornisseurCombo;
    @FXML
    private DatePicker dateCommande;
    @FXML
    private TableView<DetailCommandeEtrangere> Commandetable;
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> articleColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> codeColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> familleColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> sousFamilleColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> typeColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> quantiteColumn;
    @FXML
    private TextField codeField;
    @FXML
    private DatePicker dateDepart;
    @FXML
    private DatePicker dateArrivee;
    @FXML
    private Button validier;
    @FXML
    private TextField quantiteField;
    @FXML
    private Label qteAfficchage;
    @FXML
    private TextField departField;
    @FXML
    private TextField arriveeField;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private ComboBox<String> familleCombo;
    @FXML
    private ComboBox<String> sousFamilleCombo;
    @FXML
    private ComboBox<String> articleCombo;
    @FXML
    private ComboBox<String> typeArticleCombo;
    
    String valeur="";
    /**
     * Initializes the controller class.
     */
        private final Map<String,Fournisseur> mapFournisseur=new HashMap<>();
           FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
           
               private final Map<String,FamilleArticle> mapFamilleArticle=new HashMap<>();
           FamilleArticleDAO familleArticleDAO = new FamilleArticleDAOImpl();
        
             private final Map<String,Article> mapArticle=new HashMap<>();
             private final Map<String,SousFamilleArticle> mapSousFamilleArticle=new HashMap<>();
           
           SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
           ArticleDAO articleDAO = new ArticleDAOImpl();
           CommandeEtrangereDAO commandeEtrangereDAO = new CommandeEtrangereDAOImpl();
           navigation nav = new navigation();
           CommandeEtrangere commandeEtrangere = new CommandeEtrangere();
             
                Date date = new  Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yy");
           
    private final ObservableList<DetailCommandeEtrangere> listeDetailCommandeEtrangere=FXCollections.observableArrayList();
    ObservableList<String> typeArticle =FXCollections.observableArrayList(Constantes.EN_VRAC,Constantes.CONDITIONNÉ);
    
    
    




    
      void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.COMMANDE_ETRANGERE);
          nCommandeField.setText((sequenceur.getValeur()+1)+"/"+dateFormat.format(date));

   }
           
      void setColumnProperties(){
         
        
            articleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getArticle().getLibelle());
                }
             });
          
            codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getArticle().getCode());
                }
             });
            
             familleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFamille());
                }
             });
             
             sousFamilleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getSousFamille());
                }
             });
             
             typeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getTypeArt());
                }
             });

            quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal> p) {
                    
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

        Commandetable.setItems(listeDetailCommandeEtrangere);
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         Incrementation();
         
         
         typeArticleCombo.setItems(typeArticle);
         etatField.setText(Constantes.ETAT_COMMANDE_LANCE);
        
        List<Fournisseur> listFournisseur=fournisseurDAO.findFourByTypeFour();
        
        listFournisseur.stream().map((fournisseur) -> {
            fornisseurCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
  
           List<FamilleArticle> listFamilleArticle=familleArticleDAO.findAll();
        
        listFamilleArticle.stream().map((familleArticle) -> {
            familleCombo.getItems().addAll(familleArticle.getNom());
            return familleArticle;
        }).forEachOrdered((familleArticle) -> {
            mapFamilleArticle.put(familleArticle.getNom(), familleArticle);
        });
        
               List<Article> listArticle=articleDAO.findAll();
        
        listArticle.stream().map((article) -> {
            articleCombo.getItems().addAll(article.getLibelle());
            return article;
        }).forEachOrdered((article) -> {
            mapArticle.put(article.getLibelle(), article);
        });
        
        
    }    

    @FXML
    private void fourOnAction(ActionEvent event) {
    }

    @FXML
    private void qteByIntervalleOnAction(KeyEvent event) {
    }

    private void chargeL0ibelleKeyPressed(KeyEvent event) {
        String code = "CHAARA N°4";
        
        String codeFour = "B";
        
     String resulta =   code.replaceAll("N°", "N°"+codeFour);
        
        System.out.println(resulta);
        
        
    }

    @FXML
    private void ajouterSaisieAction(ActionEvent event) throws ParseException {
        
            if(articleCombo.getSelectionModel().getSelectedItem()==null || 
                    familleCombo.getSelectionModel().getSelectedItem()==null || 
                    sousFamilleCombo.getSelectionModel().getSelectedItem()==null || 
                    typeArticleCombo.getSelectionModel().getSelectedItem()==null || 
                   departField.getText().equalsIgnoreCase("") ||
                   quantiteField.getText().equalsIgnoreCase("")|| 
                   arriveeField.getText().equalsIgnoreCase("")|| 
                   dateArrivee.getValue()== null||
                   dateCommande.getValue()== null||
                   dateDepart.getValue()== null||
                   fornisseurCombo.getSelectionModel().getSelectedItem()==null 
                   

                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
              
            
                BigDecimal prixMAD = BigDecimal.ZERO;
                
                
                valeur = typeArticleCombo.getSelectionModel().getSelectedItem();
                DetailCommandeEtrangere detailcommandeEtrangere = new DetailCommandeEtrangere();
                Article article = articleDAO.findArticleByCodeArt(codeField.getText());        
                FamilleArticle familleArticle = mapFamilleArticle.get(familleCombo.getSelectionModel().getSelectedItem());
                SousFamilleArticle sousFamilleArticle = mapSousFamilleArticle.get(sousFamilleCombo.getSelectionModel().getSelectedItem());
                Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());
                 
                 
                 
                   String codeFour =   article.getLibelle().replaceAll("N°", "N° "+fournisseur.getCode()+" ");
                 
                   prixMAD = article.getPrix().multiply(article.getTauxDevise().getDeviseMontant());

                
             detailcommandeEtrangere.setArticle(article);
             
             if (familleArticle.getNom().equals(Constantes.THE_CHAARA)|| familleArticle.getNom().equals(Constantes.THE_MKARKAB)){
             detailcommandeEtrangere.setCodeFournisseur(codeFour);
             }
             detailcommandeEtrangere.setFamille(familleArticle.getNom());
             detailcommandeEtrangere.setSousFamille(sousFamilleArticle.getNom());
             detailcommandeEtrangere.setPrix(prixMAD);
             detailcommandeEtrangere.setTypeArt(valeur);
             detailcommandeEtrangere.setQuantiteRestant(new BigDecimal(quantiteField.getText()));
             detailcommandeEtrangere.setPrixDevise(article.getPrix());
             detailcommandeEtrangere.setQuantite(new BigDecimal(quantiteField.getText()));
             detailcommandeEtrangere.setQuantiteLivree(BigDecimal.ZERO);
             detailcommandeEtrangere.setMontant(BigDecimal.ZERO);
             detailcommandeEtrangere.setTauxDevise(article.getTauxDevise());
             detailcommandeEtrangere.setUtilisateurCreation(nav.getUtilisateur());
             detailcommandeEtrangere.setCommandeEtrangere(commandeEtrangere);
             detailcommandeEtrangere.setDateCreation(new Date());
  
   
            listeDetailCommandeEtrangere.add(detailcommandeEtrangere);

            setColumnProperties();
            loadDetail();
            codeField.clear();
            articleCombo.getSelectionModel().select(-1);
            quantiteField.clear();
            familleCombo.getSelectionModel().select(-1);
            sousFamilleCombo.getSelectionModel().select(-1);
            typeArticleCombo.getSelectionModel().select(-1);
         }
    
    }

    void clear(){
        
    articleCombo.getSelectionModel().select(-1);
    departField.clear();
    arriveeField.clear();
    dateArrivee.setValue(null);
    dateDepart.setValue(null);
    dateCommande.setValue(null);
    quantiteField.clear();
    
    } 
    
    
    @FXML
    private void supprimerCommandeOnAction(ActionEvent event) {
    }

    @FXML
    private void validerSaisieAction(ActionEvent event) throws ParseException {
        
          if(fornisseurCombo.getSelectionModel().getSelectedItem()== null || fornisseurCombo.getSelectionModel().getSelectedItem().isEmpty() || Commandetable.getItems().size() ==0 ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
     }
        else {
            

           Fournisseur fournisseur=mapFournisseur.get(fornisseurCombo.getSelectionModel().getSelectedItem());

           LocalDate localDate=dateCommande.getValue();
           Date dateCom=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
  
           localDate=dateArrivee.getValue();
           Date dateArr=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           
           localDate=dateDepart.getValue();
           Date dateDep=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
        commandeEtrangere.setUtilisateurCreation(nav.getUtilisateur());
        commandeEtrangere.setFourisseur(fournisseur);
        commandeEtrangere.setDateCreation(new Date());
        commandeEtrangere.setDateCommande(dateCom);
        commandeEtrangere.setLieuArrivee(arriveeField.getText());
        commandeEtrangere.setLieuDepart(departField.getText());
        commandeEtrangere.setDateArrivee(dateArr);
        commandeEtrangere.setDateDepart(dateDep);
        commandeEtrangere.setEtat(Constantes.ETAT_COMMANDE_LANCE);
        commandeEtrangere.setnCommande(nCommandeField.getText());
        commandeEtrangere.setDetailCommandeEtrangere(listeDetailCommandeEtrangere);

        commandeEtrangereDAO.add(commandeEtrangere);
     
        commandeEtrangere = new CommandeEtrangere();

     Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.COMMANDE_ETRANGERE);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();
          
           Commandetable.getItems().clear();

         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
           
        }

    }

    @FXML
    private void modifierCommandeOnAction(ActionEvent event) {
    }

    @FXML
    private void qteOnAction(KeyEvent event) {
    }

    @FXML
    private void codeArticleOnAction(KeyEvent event) {
        
                    if (event.getCode().equals(KeyCode.ENTER)){
            

               Article article = articleDAO.findArticleByCodeArt(codeField.getText());
                if (article!=null){
                articleCombo.setValue(article.getLibelle());
                }else{
              nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.VERIFIER_CODE_ARTICLE);
                }
            }
        
    }

    @FXML
    private void familleOnAction(ActionEvent event) {
        
               sousFamilleCombo.getItems().clear();
            FamilleArticle familleArticle  = mapFamilleArticle.get(familleCombo.getSelectionModel().getSelectedItem());
            
             if(familleArticle!=null){
                 
            List<SousFamilleArticle> listSousFamilleArticle = familleArticle.getSousFamilleArticle();
            listSousFamilleArticle.stream().map((sousFamilleArticle) -> {
                sousFamilleCombo.getItems().addAll(sousFamilleArticle.getNom());
                return sousFamilleArticle;
            }).forEachOrdered((sousFamilleArticle) -> {
                mapSousFamilleArticle.put(sousFamilleArticle.getNom(), sousFamilleArticle);
            });
    }
        
    }

    @FXML
    private void sousFamilleOnAction(ActionEvent event) {
    }

    @FXML
    private void articleOnAction(ActionEvent event) {
        
                 Article article  = mapArticle.get(articleCombo.getSelectionModel().getSelectedItem());
         
          if(article!=null){
              
         codeField.setText(article.getCode());
         
          }
        
    }

    @FXML
    private void typeArticleOnAction(ActionEvent event) {
    }

    
}
