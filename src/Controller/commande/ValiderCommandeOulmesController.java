/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.commande;

import Utils.Constantes;
import dao.Entity.Article;
import dao.Entity.Commande;
import dao.Entity.DetailCommande;
import dao.Entity.MatierePremier;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
import dao.Manager.CommandeDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.DetailReceptionDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.DetailReceptionDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
import function.navigation;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ValiderCommandeOulmesController implements Initializable {

    @FXML
    private TableView<Commande> tableCommande;
    @FXML
    private TableColumn<Commande, String> nCommandeColumn;
    @FXML
    private TableColumn<Commande, Date> dateCreationColumn;
    @FXML
    private TableColumn<Commande, String> etatColumn;
    @FXML
    private TableView<DetailCommande> tableDetailCommande;
    @FXML
    private TableColumn<DetailCommande, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantiteCaisseColumn;
    @FXML
    private TableColumn<DetailCommande, String> codeArticleColumn;
    @FXML
    private TextField numComRechField;
    @FXML
    private DatePicker dateSaisieField;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimerDC;
    @FXML
    private Button btnModifierCom;
    @FXML
    private Button btnSupprimerC;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField prixField;


    /**
     * Initializes the controller class.
     */
    
    private ObservableList<DetailCommande> listeDetailCommande=FXCollections.observableArrayList();
    private final ObservableList<Commande> listeCommande=FXCollections.observableArrayList();
    
    DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
    DetailReceptionDAO detailReceptionDAO = new DetailReceptionDAOImpl();
     PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
    CommandeDAO commandeDAO = new CommandeDAOImpl();
    
    ProduitDAO produitDAO = new ProduitDAOImpl();
    
     navigation nav = new navigation();
     
     Commande commande;
     
     DetailCommande detailCommande;
   
        BigDecimal montanTotal=BigDecimal.ZERO;
     
    public String numCommandeRecupere = null;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setColumnProperties();
        loadDetail();
        
    }    

      void setColumnProperties(){
        

          nCommandeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getnCommande());
                }
                
             });
             dateCreationColumn.setCellValueFactory(new PropertyValueFactory<Commande, Date>("dateCreation"));
             etatColumn.setCellValueFactory(new PropertyValueFactory<Commande, String>("etat"));
        

        tableCommande.setEditable(false);
 
    }
    
     void loadDetail(){
        
        listeCommande.clear();
        listeCommande.addAll(commandeDAO.findCommandeByEtat(Constantes.ETAT_COMMANDE_LANCE, Constantes.COMMANDE_INTERNE_ART));
        tableCommande.setItems(listeCommande);
    }
    
    
    
      void setColumnPropertiesDetailCommande(){
        
          codeArticleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
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
    }
    
    @FXML
    private void afficherDetailOnMouse(MouseEvent event) {
        
            if (tableCommande.getSelectionModel().getSelectedIndex()!=-1){
 
              listeDetailCommande.clear();
             
                commande=tableCommande.getSelectionModel().getSelectedItem();
              
                detailCommandeDAO = new DetailCommandeDAOImpl();  
                
            setColumnPropertiesDetailCommande();
            listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
            tableDetailCommande.setItems(listeDetailCommande);
            
           numCommandeRecupere= nCommandeColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex());
           
        numComRechField.setText(commande.getnCommande());
        
        LocalDate date = new java.util.Date(commande.getDateCreation().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        dateSaisieField.setValue(date);



            }
        
    }

      void loadDetailCommande (){
        
         commande = detailCommande.getCommande();

        setColumnPropertiesDetailCommande();
        listeDetailCommande.clear();
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommande);
    }
    
    
    @FXML
    private void afficherChampsOnMouse(MouseEvent event) {
        
           if (tableDetailCommande.getSelectionModel().getSelectedIndex()!=-1){
           detailCommande=tableDetailCommande.getSelectionModel().getSelectedItem();
        
           
               DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
           
            quantiteField.setText(df.format(detailCommande.getQuantite()));
        
            prixField.setText(detailCommande.getPrixUnitaire()+"");
           }
            
        
    }

    private void modifierDetailCommande(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
         if (tableDetailCommande.getSelectionModel().getSelectedItem() != null) {
        
             BigDecimal oldValeur = BigDecimal.ZERO;
             BigDecimal oldMontant = BigDecimal.ZERO;
             
             BigDecimal newValeur = BigDecimal.ZERO;
             BigDecimal newMontant = BigDecimal.ZERO;
             
             BigDecimal qteCaisse = BigDecimal.ZERO;
             
        DetailCommande  detailCommande=tableDetailCommande.getSelectionModel().getSelectedItem();
     
          Commande commande = detailCommande.getCommande();
        
       oldValeur=  commande.getPrixTotal();
       
       oldMontant= detailCommande.getQuantite().multiply(detailCommande.getPrixUnitaire());

       newValeur =oldValeur.subtract(oldMontant);
               
       newMontant=new BigDecimal(quantiteField.getText()).multiply(new BigDecimal(prixField.getText()));
        
         commande.setPrixTotal(newValeur.add(newMontant));
        
         commandeDAO.edit(commande);
         
         
         if (detailCommande.getPrixOulmes().getConditionnementCaisse().compareTo(BigDecimal.ZERO)>0){
         
            qteCaisse = new BigDecimal(quantiteField.getText()).multiply(detailCommande.getPrixOulmes().getConditionnementCaisse());
       
         }else if(detailCommande.getPrixOulmes().getConditionnementCaisse().compareTo(BigDecimal.ZERO)==0) {
         
            qteCaisse =   BigDecimal.ZERO.setScale(2,RoundingMode.FLOOR);
         
         }
         
       detailCommande.setQuantiteCaisse(qteCaisse);
       detailCommande.setQuantite(new BigDecimal(quantiteField.getText()));
       detailCommande.setQuantiteRestee(new BigDecimal(quantiteField.getText()));
  
          detailCommandeDAO.edit(detailCommande);

         
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);

     loadDetailCommande ();
      
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
        
            }
    }

    @FXML
    private void ValiderCommande(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
           tableDetailCommande.getItems().clear();
       if (tableCommande.getSelectionModel().getSelectedItem() != null) {


            commande=tableCommande.getSelectionModel().getSelectedItem();
         
            commande.setEtat(Constantes.ETAT_COMMANDE_VALIDEE);
            commande.setDateValidation(new Date());
       
            commandeDAO.edit(commande);
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.COMMANDE_VALIDER); 
  
              setColumnPropertiesDetailCommande();
              loadDetail();
                      
           
       }else {
          nav.showAlert(Alert.AlertType.CONFIRMATION, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE); 
       }
            }
    }

    @FXML
    private void supprimerDetailCommande(ActionEvent event) {
        
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        
             if(tableDetailCommande.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
                 
                     MatierePremier matierePremier = matierePremiereDAO.findByCode(Constantes.CODE_MP);
             
    DetailCommande detailCommande =tableDetailCommande.getSelectionModel().getSelectedItem();

           detailCommande.setEtat(Constantes.ETAT_SUPPRIMER);

        detailCommandeDAO.edit(detailCommande);

//############################################################################  Supp Emballages  #############################################################################################################################################################################################################################################
   
    for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommandeTMP = listeDetailCommande.get(i);
                  
                if(detailCommandeTMP.getPrixOulmes().getProduit().getCode().equals("1500") ||detailCommandeTMP.getPrixOulmes().getProduit().getCode().equals("1504") ||detailCommandeTMP.getPrixOulmes().getProduit().getCode().equals("1503")){

                    detailCommandeTMP.setEtat(Constantes.ETAT_SUPPRIMER);

                    detailCommandeDAO.edit(detailCommandeTMP);

                }
                  }

               listeDetailCommande.clear();
               listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
               tableDetailCommande.setItems(listeDetailCommande);
    
    
//###########################################################################  Detail Commande "Emballages"  #################################################################################################################################################################################################################################################################################################################################################################     
           
            BigDecimal qtePalette = BigDecimal.ZERO;
            BigDecimal qteCaisse = BigDecimal.ZERO;
            BigDecimal qteBouteille = BigDecimal.ZERO;

            for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommandeTMP = listeDetailCommande.get(i);
                
                if(detailCommandeTMP.getPrixOulmes().getProduit().getQtePalette().compareTo(BigDecimal.ZERO)>0){
                qtePalette = qtePalette.add(detailCommandeTMP.getQuantitePalette());
                }
                if(detailCommandeTMP.getPrixOulmes().getProduit().getQteCaisse().compareTo(BigDecimal.ZERO)>0){
                qteCaisse = qteCaisse.add(detailCommandeTMP.getQuantiteCaisseProduit());
                }
                
                if(detailCommandeTMP.getPrixOulmes().getProduit().getQteBouteille().compareTo(BigDecimal.ZERO)>0){
                qteBouteille = qteBouteille.add(detailCommandeTMP.getQuantiteBouteille());
                }
                
            }

             
            if(qtePalette.compareTo(BigDecimal.ZERO)>0){
                
                 DetailCommande detailCommandeTMP = new DetailCommande();
                      
                 
                    PrixOulmes prixOulmesTMP = prixOulmesDAO.findById(21);

             detailCommandeTMP.setDimension(matierePremier.getDimension());
             detailCommandeTMP.setMatierePremier(matierePremier);
             detailCommandeTMP.setQuantite(qtePalette);
             detailCommandeTMP.setQuantiteCaisse(BigDecimal.ZERO);
             detailCommandeTMP.setQuantitePalette(BigDecimal.ZERO);
             detailCommandeTMP.setQuantiteRestee(qtePalette);
             detailCommandeTMP.setQuantiteRecu(BigDecimal.ZERO);
             detailCommandeTMP.setQuantiteLivree(BigDecimal.ZERO);
             detailCommandeTMP.setCommande(commande);
             detailCommandeTMP.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeTMP.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeTMP.setRemiseAchat(prixOulmesTMP.getRemiseAchat());
             detailCommandeTMP.setPrixUnitaire(prixOulmesTMP.getPrix());
             detailCommandeTMP.setPrixOulmes(prixOulmesTMP);

             detailCommandeDAO.add(detailCommandeTMP);
             

                
            }
            
               if(qteCaisse.compareTo(BigDecimal.ZERO)>0){
                
                 DetailCommande detailCommandeTMP = new DetailCommande();
                      
                 
                    PrixOulmes prixOulmesTMP = prixOulmesDAO.findById(29);

             detailCommandeTMP.setDimension(matierePremier.getDimension());
             detailCommandeTMP.setMatierePremier(matierePremier);
             detailCommandeTMP.setQuantite(qteCaisse);
             detailCommandeTMP.setQuantiteCaisse(BigDecimal.ZERO);
             detailCommandeTMP.setQuantitePalette(BigDecimal.ZERO);
             detailCommandeTMP.setQuantiteRestee(qteCaisse);
             detailCommandeTMP.setQuantiteRecu(BigDecimal.ZERO);
             detailCommandeTMP.setQuantiteLivree(BigDecimal.ZERO);
             detailCommandeTMP.setCommande(commande);
             detailCommandeTMP.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeTMP.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeTMP.setRemiseAchat(prixOulmesTMP.getRemiseAchat());
             detailCommandeTMP.setPrixUnitaire(prixOulmesTMP.getPrix());
             detailCommandeTMP.setPrixOulmes(prixOulmesTMP);

             detailCommandeDAO.add(detailCommandeTMP);
             
   
                
            }
               
               
               if(qteBouteille.compareTo(BigDecimal.ZERO)>0){
                
                 DetailCommande detailCommandeTMP = new DetailCommande();
                      
                 
                    PrixOulmes prixOulmesTMP = prixOulmesDAO.findById(28);

             detailCommandeTMP.setDimension(matierePremier.getDimension());
             detailCommandeTMP.setMatierePremier(matierePremier);
             detailCommandeTMP.setQuantite(qteBouteille);
             detailCommandeTMP.setQuantiteCaisse(BigDecimal.ZERO);
             detailCommandeTMP.setQuantitePalette(BigDecimal.ZERO);
             detailCommandeTMP.setQuantiteRestee(qteBouteille);
             detailCommandeTMP.setQuantiteRecu(BigDecimal.ZERO);
             detailCommandeTMP.setQuantiteLivree(BigDecimal.ZERO);
             detailCommandeTMP.setCommande(commande);
             detailCommandeTMP.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeTMP.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeTMP.setRemiseAchat(prixOulmesTMP.getRemiseAchat());
             detailCommandeTMP.setPrixUnitaire(prixOulmesTMP.getPrix());
             detailCommandeTMP.setPrixOulmes(prixOulmesTMP);

             detailCommandeDAO.add(detailCommandeTMP);
             
       
                
            }      
    
         BigDecimal qteCommande = BigDecimal.ZERO;
            BigDecimal prixCommande = BigDecimal.ZERO;
            
                  for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommandeTMP = listeDetailCommande.get(i);
                  
                qteCommande = qteCommande.add(detailCommandeTMP.getQuantite());
                prixCommande = prixCommande.add(detailCommandeTMP.getPrixUnitaire());
                  
                  }
               
             montanTotal = qteCommande.multiply(prixCommande);
             
//###########################################################################   Commande   #################################################################################################################################################################################################################################################################################################################################################################     

               commande.setPrixTotal(montanTotal);
               commandeDAO.edit(commande);
    
               listeDetailCommande.clear();
               listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
               tableDetailCommande.setItems(listeDetailCommande);
               
               
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        
          quantiteField.clear();
          prixField.clear();
      
    }
            }
        
    }

    @FXML
    private void modifierCommande(ActionEvent event) throws ParseException {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
            if (tableCommande.getSelectionModel().getSelectedItem() != null) {
        
             
       Commande  commande=tableCommande.getSelectionModel().getSelectedItem();
     
        LocalDate localDate=dateSaisieField.getValue();
            
          Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
       
        
         commande.setDateCreation(dateSaisie);
         commande.setnCommande(numComRechField.getText());
        
         commandeDAO.edit(commande);
         

         
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);

     loadDetail();
      
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
        
            }
    }

    @FXML
    private void supprimerCommande(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
           if(tableCommande.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
        
         Commande  commande=tableCommande.getSelectionModel().getSelectedItem();
         
         commande.setEtat(Constantes.ETAT_COMMANDE_SUPPRIMER);
         commandeDAO.edit(commande);
        
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
         
         loadDetail();
          
         
         quantiteField.clear();
         prixField.clear();
         numComRechField.clear();
         dateSaisieField.setValue(null);
         
        listeDetailCommande.clear();
        tableDetailCommande.setItems(listeDetailCommande);
         
               }
            }
    }

    @FXML
    private void ajouterCommande(ActionEvent event) {
        
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
                  if(tableCommande.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
        
        Commande commande = tableCommande.getSelectionModel().getSelectedItem();

          FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getSaisirArticleOulmes()));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);
  
                SaisirArticleOulmesController saisirArticleOulmesController = fXMLLoader.getController();

                saisirArticleOulmesController.commande = commande;
                saisirArticleOulmesController.listeDetailCommandeTMP= listeDetailCommande;
                saisirArticleOulmesController.chargerLesDonnees();
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
     
                stage.show();
            } catch (IOException ex) {           
                System.err.println("!!!!!!!!" +ex);
            }
        
          }
            }
    }


   
    
}
