/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ArticleEtrangere;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.Fournisseur;
import dao.Entity.TauxDevise;
import dao.Manager.ArticleDAO;
import dao.Manager.FamilleArticleDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.TauxDeviseDAO;
import dao.ManagerImpl.ArticleDAOImpl;
import dao.ManagerImpl.FamilleArticleDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.TauxDeviseDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ListeArticleEtrangereController implements Initializable {

    @FXML
    private TableView<Article> tableArticle;
    @FXML
    private TableColumn<Article, String> codeColumn;
    @FXML
    private TableColumn<Article, String> libelleColumn;
    @FXML
    private TableColumn<Article, String> uniteColumn;
    @FXML
    private TableColumn<Article, String> fournisserColumn;
    @FXML
    private TableColumn<Article, BigDecimal> prixColumn;
    @FXML
    private TableColumn<Article, String> typeArtDouColumn;
    @FXML
    private TableColumn<Article, String> deviseColumn;
    
    @FXML
    private TextField txtUnite;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtLibelle;
    @FXML
    private Label msgCode;
    @FXML
    private ImageView btnRafrechaire;
    @FXML
    private ComboBox<String> deviseCombo;
    @FXML
    private TextField prixField;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private RadioButton offreArtDo;
    @FXML
    private ToggleGroup typArtDouane;
    @FXML
    private RadioButton normalArtDou;
    
    
    /**
     * Initializes the controller class.
     */
    
       private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    private Map<String,TauxDevise> mapTauxDevise=new HashMap<>();
    TauxDeviseDAO tauxDeviseDAO = new TauxDeviseDAOImpl();
     private final ObservableList<Article> listeArticle=FXCollections.observableArrayList();
            
     ArticleDAO articleDAO = new ArticleDAOImpl();
     navigation nav = new navigation();
     Article article = new Article();
    
    
    
       void setColumnProperties(){
        
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        uniteColumn.setCellValueFactory(new PropertyValueFactory<>("unite"));
        
          fournisserColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Article, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Article, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFournisseur().getNom());
                }
             });
         
          typeArtDouColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Article, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Article, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getTypeArticleDouane());
                }
             });
         
         deviseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Article, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Article, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getTauxDevise().getDevise().getLibelle());
                }
             });
         
         
         prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Article, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<Article, BigDecimal> p) {
                    
                DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrix()));
                }
                
             });
     }
    
     void loadDetail(){
        
        listeArticle.clear();
        listeArticle.addAll(articleDAO.findAll());
        tableArticle.setItems(listeArticle);
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      setColumnProperties();
      loadDetail();
         
       List<Fournisseur> listFournisseur=fournisseurDAO.findFourByTypeFour();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
          List<TauxDevise> listTauxDevise= tauxDeviseDAO.findAll();
        
        listTauxDevise.stream().map((tauxDevise) -> {
            deviseCombo.getItems().addAll(tauxDevise.getDevise().getLibelle());
            return tauxDevise;
        }).forEachOrdered((tauxDevise) -> {
            mapTauxDevise.put(tauxDevise.getDevise().getLibelle(), tauxDevise);
        });
      
    }    

    @FXML
    private void ajouterVille(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                
            
         if(txtCode.getText().equalsIgnoreCase("")|| 
                 txtLibelle.getText().equalsIgnoreCase("") ||
                 txtUnite.getText().equalsIgnoreCase("") ||
                 prixField.getText().equalsIgnoreCase("")|| 
                 deviseCombo.getSelectionModel().getSelectedItem()==null ||
                 fourCombo.getSelectionModel().getSelectedItem()==null 
                 
                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else { 
         article = new Article();
  
        Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
         TauxDevise tauxDevise = mapTauxDevise.get(deviseCombo.getSelectionModel().getSelectedItem());
               
        
          article.setCode(txtCode.getText());
       article.setLibelle(txtLibelle.getText());
       article.setUnite(txtUnite.getText());
       article.setUtilisateurCreation(nav.getUtilisateur());
       article.setDateCreation(new Date());
      article.setFournisseur(fournisseur);
      
       if(offreArtDo.isSelected())
        {
        article.setTypeArticleDouane("Offre");
        }
        else if(normalArtDou.isSelected())
        { 
        article.setTypeArticleDouane("Normal");
        }
        
        article.setPrix(new BigDecimal(prixField.getText()));
        article.setTauxDevise(tauxDevise);
        
        
        articleDAO.add(article);
        
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);

        
     setColumnProperties();
        loadDetail();
         clear();
           }
            }
    }

    @FXML
    private void ModifierVille(ActionEvent event) {
        
    
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
           if (tableArticle.getSelectionModel().getSelectedItem() != null) {
              
        Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
        TauxDevise tauxDevise = mapTauxDevise.get(deviseCombo.getSelectionModel().getSelectedItem());
               
        article= tableArticle.getSelectionModel().getSelectedItem();
        
        article.setCode(txtCode.getText());
        article.setLibelle(txtLibelle.getText());
        article.setUnite(txtUnite.getText());
        article.setTauxDevise(tauxDevise);       
        article.setFournisseur(fournisseur);
        article.setPrix(new BigDecimal(prixField.getText()));
        
        if(offreArtDo.isSelected())
        {
        article.setTypeArticleDouane("Offre");
        }
        else if(normalArtDou.isSelected())
        { 
        article.setTypeArticleDouane("Normal");
        }
        
        
               
          articleDAO.edit(article);
      
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.MODIFIER_ENREGISTREMENT);
       
      setColumnProperties();
      loadDetail();
       clear();
        } else {
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
        }
            }
    }

    @FXML
    private void SupprimerVille(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
            if(tableArticle.getSelectionModel().getSelectedItem()==null){
         
    
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
       Article article=tableArticle.getSelectionModel().getSelectedItem();
        articleDAO.delete(article);
    
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succés", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
        setColumnProperties();
      loadDetail();
       clear();
    }
    }}

       void clear (){
    
        txtCode.clear();
        txtLibelle.clear();
        txtUnite.clear();
        prixField.clear();
        deviseCombo.getSelectionModel().select(-1);
        fourCombo.getSelectionModel().select(-1);
        
    }
    
    @FXML
    private void searche(KeyEvent event) {
    }

    @FXML
    private void refrechTableMouseClicked(MouseEvent event) {
        clear();
    }

    @FXML
    private void clickChargeOnMouseEntered(MouseEvent event) {
        
             Integer val= tableArticle.getSelectionModel().getSelectedIndex();
          if (val<= -1 ){
          return;
          
          }
          else {
          
              txtCode.setText(codeColumn.getCellData(val));
              txtLibelle.setText(libelleColumn.getCellData(val));
              txtUnite.setText(uniteColumn.getCellData(val));
              prixField.setText(prixColumn.getCellData(val)+"");
              
              if (typeArtDouColumn.getCellData(val).equals("Offre"))
              {
              offreArtDo.setSelected(true);
              }
              else if (typeArtDouColumn.getCellData(val).equals("Normal"))
              {
              normalArtDou.setSelected(true);
              }
              deviseCombo.setValue(deviseColumn.getCellData(val));
              fourCombo.setValue(fournisserColumn.getCellData(val));
              

          }
    }

    
}
