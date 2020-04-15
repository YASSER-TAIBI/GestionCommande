
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.commande;

import Utils.Constantes;
import dao.Entity.Commande;
import dao.Entity.DetailCommande;
import dao.Entity.MatierePremier;
import dao.Manager.CommandeDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.DetailReceptionDAO;
import dao.Manager.MatierePremiereDAO;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.DetailReceptionDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import function.navigation;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author h
 */
public class ValiderCommandeController implements Initializable {

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
    private TableColumn<DetailCommande, String> codeMPColumn;
    @FXML    
    private TableColumn<DetailCommande, String> libelleColumn;
    @FXML    
    private TableColumn<DetailCommande, Integer> quantiteColumn;
    
    @FXML
    private TextField numComRechField;
    @FXML    
    private Button btnModifier;
    @FXML
    private DatePicker dateSaisieField;
    @FXML
    private Button btnModifierDC;
    @FXML
    private Button btnModifierCom;
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField prixField;
     @FXML
    private Button btnSupprimerDC;
    @FXML
    private Button btnSupprimerC;
    @FXML
    private Button btnAjouter;
    
    
    private ObservableList<DetailCommande> listeDetailCommande=FXCollections.observableArrayList();
    private final ObservableList<Commande> listeCommande=FXCollections.observableArrayList();
    
    DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
    DetailReceptionDAO detailReceptionDAO = new DetailReceptionDAOImpl();
    CommandeDAO commandeDAO = new CommandeDAOImpl();
    
    MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
    
     navigation nav = new navigation();
     
     Commande commande;
     
     DetailCommande detailCommande;
   
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
        listeCommande.addAll(commandeDAO.findCommandeByEtat(Constantes.ETAT_COMMANDE_LANCE, Constantes.COMMANDE_INTERNE));
        tableCommande.setItems(listeCommande);
    }
    

    public void changeNomCellEvent (CellEditEvent editedCell){
 
    }


    @FXML
    private void ValiderCommande(ActionEvent event) {
       
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
    
      void setColumnPropertiesDetailCommande(){
        
          codeMPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
          
          libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });
                      
          quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<DetailCommande, Integer> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });
           
     
    }

 

  
    void loadDetailCommande (){
        
         commande = detailCommande.getCommande();

        setColumnPropertiesDetailCommande();
        listeDetailCommande.clear();
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommande);
    }
  

    @FXML
    private void afficherDetailOnMouse(MouseEvent event) {
          
        
            listeDetailCommande.clear();
            if (tableCommande.getSelectionModel().getSelectedIndex()!=-1){
 
             commande=tableCommande.getSelectionModel().getSelectedItem();    
                
                
            setColumnPropertiesDetailCommande();
            listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
            tableDetailCommande.setItems(listeDetailCommande);
            
           numCommandeRecupere= nCommandeColumn.getCellData(tableCommande.getSelectionModel().getSelectedIndex());
           
        numComRechField.setText(commande.getnCommande());
        
        LocalDate date = new java.util.Date(commande.getDateCreation().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        dateSaisieField.setValue(date);
        
        
            
            }
            
    }


    private void rechercheNumComOnKeyPressed(KeyEvent event) {
        
                 ObservableList<Commande> listeCommande=FXCollections.observableArrayList();
    listeCommande.clear();
   
   listeCommande.addAll(commandeDAO.findFourByRechercheNumCommande(numComRechField.getText(),Constantes.ETAT_COMMANDE_LANCE));
   
   tableCommande.setItems(listeCommande);
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

    @FXML
    private void modifierDetailCommande(ActionEvent event) {
        

         if (tableDetailCommande.getSelectionModel().getSelectedItem() != null) {
        
             BigDecimal oldValeur = BigDecimal.ZERO;
             BigDecimal oldMontant = BigDecimal.ZERO;
             
             BigDecimal newValeur = BigDecimal.ZERO;
             BigDecimal newMontant = BigDecimal.ZERO;
             
        DetailCommande  detailCommande=tableDetailCommande.getSelectionModel().getSelectedItem();
     
          Commande commande = detailCommande.getCommande();
        
       oldValeur=  commande.getPrixTotal();
       
       oldMontant= detailCommande.getQuantite().multiply(detailCommande.getPrixUnitaire());

       newValeur =oldValeur.subtract(oldMontant);
               
       newMontant=new BigDecimal(quantiteField.getText()).multiply(new BigDecimal(prixField.getText()));
        
         commande.setPrixTotal(newValeur.add(newMontant));
        
         commandeDAO.edit(commande);
         
       detailCommande.setQuantite(new BigDecimal(quantiteField.getText()));
       detailCommande.setQuantiteRestee(new BigDecimal(quantiteField.getText()));
  
          detailCommandeDAO.edit(detailCommande);

         
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);

     loadDetailCommande ();
      
        }else{
             nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.SELECTION_ERREUR, Constantes.SELECTION_LIGNE_MODIFIER);
         }
        
        
    }

    @FXML
    private void modifierCommande(ActionEvent event) throws ParseException {
        
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

    @FXML
    private void supprimerDetailCommande(ActionEvent event) {
        
        BigDecimal valeur = BigDecimal.ZERO;
        
             if(tableDetailCommande.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
              DetailCommande detailCommande =tableDetailCommande.getSelectionModel().getSelectedItem();
              
               commande = detailCommande.getCommande();
                 
            valeur= detailCommande.getQuantite().multiply(detailCommande.getPrixUnitaire());
            
            commande.setPrixTotal(commande.getPrixTotal().subtract(valeur));
            
            commandeDAO.edit(commande);

           detailCommande.setEtat(Constantes.ETAT_SUPPRIMER);

        detailCommandeDAO.edit(detailCommande);
        
        nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.SUPRIMER_ENREGISTREMENT);
        

      loadDetailCommande();  
    
          quantiteField.clear();
          prixField.clear();
      
    }
        
    }

    @FXML
    private void supprimerCommande(ActionEvent event) {
        
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

    @FXML
    private void ajouterCommande(ActionEvent event) {
        
          if(tableCommande.getSelectionModel().getSelectedItem()==null){
              
         nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE);
        
     }else {
        
        Commande commande = tableCommande.getSelectionModel().getSelectedItem();

        SaisirMatierePremiereController root = new SaisirMatierePremiereController(Constantes.POUR_AJOUTER ,commande) {
           @Override
           public void refresh() {
              tableDetailCommande.setItems(FXCollections.observableArrayList(commande.getDetailCommandes()));
              setColumnProperties();
           }
       };
      Stage stage = new Stage(); 
      Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
        
    
      loadDetail();   
        
          }
    }





   
   

    
  
    
  

      


}
