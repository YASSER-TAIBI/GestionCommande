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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class SaisirArticleOulmesController implements Initializable {
    
    
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
    private TableColumn<DetailCommande, BigDecimal> quantiteCaisseColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> quantitePaletteColumn;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button validerSaisie;

    @FXML
    private ComboBox<String> chauffeurCombo;
    @FXML
    private ComboBox<String> matriculeCombo;
    @FXML
    private DatePicker dateCharg;
    @FXML
    private Label qteAfficchage;
    @FXML
    private Label qtePaletteAfficchage;
    
    ObservableList<DetailCommande> listeDetailCommandeTMP =FXCollections.observableArrayList();
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
       
     Commande commande;  
    
       BigDecimal qteCaisse = BigDecimal.ZERO;
       BigDecimal qtePack = BigDecimal.ZERO;
    
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
    
           public void chargerLesDonnees(){
           
        nCommandeField.setText(commande.getnCommande());
        clientCombo.setValue(commande.getClientMP().getNom());
        chauffeurCombo.setValue(commande.getChauffeur().getChauffeur());
        matriculeCombo.setValue(commande.getChauffeur().getMatricule());
        LocalDate dateCharge = new java.util.Date( commande.getDateCreation().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date = new java.util.Date( commande.getDateCreation().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dateSaisie.setValue(date);
        dateCharg.setValue(dateCharge);
        fornisseurCombo.setValue(commande.getFourisseur().getNom());
        
         detailCommandetable.setItems(listeDetailCommandeTMP);
        
         setColumnProperties();  
         
           }
       
       

       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


                     List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findAll();
        listPrixOulmes.stream().map((prixOulmes) -> {
            LibelleCombo.getItems().addAll(prixOulmes.getProduit().getLibelle());
            return prixOulmes;
        }).forEachOrdered((prixOulmes) -> {
            mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
        });
          
        

        
          detailCommandetable.setEditable(true);
          quantiteColumn.setEditable(true);

        
    }    

    @FXML
    private void commandeKeyPressed(KeyEvent event) {
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
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
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
                
//############################################################################  Supp Emballages  #############################################################################################################################################################################################################################################
                
    for (int i = 0; i < listeDetailCommandeTMP.size(); i++) {
                
                DetailCommande detailCommandeTMP = listeDetailCommandeTMP.get(i);
                  
                if(detailCommandeTMP.getPrixOulmes().getProduit().getCode().equals("1500") ||detailCommandeTMP.getPrixOulmes().getProduit().getCode().equals("1504") ||detailCommandeTMP.getPrixOulmes().getProduit().getCode().equals("1503")){
                    
//                  detailCommandeDAO.delete(detailCommandeTMP);
                    
                    detailCommandeTMP.setEtat(Constantes.ETAT_SUPPRIMER);

                    detailCommandeDAO.edit(detailCommandeTMP);
                
//                 listeDetailCommandeTMP.remove(detailCommandeTMP);

                }
                  }
    
                    setColumnProperties();
               listeDetailCommandeTMP.clear();
               listeDetailCommandeTMP.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
               detailCommandetable.setItems(listeDetailCommandeTMP);
               
//############################################################################################################################################################################################################################################################################################################################################
                
                prixB= prixOulmes.getPrix();

                  MatierePremier matierePremier = matierePremiereDAO.findByCode(Constantes.CODE_MP);
           
                 
                  Qte = new BigDecimal(quantiteField.getText());
                  
                  
             detailCommande.setDimension(matierePremier.getDimension());
             detailCommande.setMatierePremier(matierePremier);
             detailCommande.setQuantite(qtePack);
             detailCommande.setQuantiteCaisse(qteCaisse);
             detailCommande.setQuantitePalette(Qte);
             
             if(prixOulmes.getProduit().getQteCaisse().compareTo(BigDecimal.ZERO)>0 && prixOulmes.getProduit().getQteBouteille().compareTo(BigDecimal.ZERO)>0){
                 
             detailCommande.setQuantiteCaisseProduit(Qte.multiply(prixOulmes.getProduit().getQteCaisse()));
             detailCommande.setQuantiteBouteille(Qte.multiply(prixOulmes.getProduit().getQteBouteille()));
             
             }else {
             detailCommande.setQuantiteCaisseProduit(BigDecimal.ZERO);
             detailCommande.setQuantiteBouteille(BigDecimal.ZERO);
             }
             
             detailCommande.setQuantiteRestee(Qte);
             detailCommande.setQuantiteRecu(BigDecimal.ZERO);
             detailCommande.setQuantiteLivree(BigDecimal.ZERO);
             detailCommande.setCommande(commande);
             detailCommande.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommande.setUtilisateurCreation(nav.getUtilisateur());
             detailCommande.setRemiseAchat(prixOulmes.getRemiseAchat());
             detailCommande.setPrixUnitaire(prixB);
             detailCommande.setPrixOulmes(prixOulmes);

            detailCommandeDAO.add(detailCommande);
             
            listeDetailCommandeTMP.add(detailCommande);
 
            setColumnProperties();
            listeDetailCommandeTMP.clear();
               listeDetailCommandeTMP.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
               detailCommandetable.setItems(listeDetailCommandeTMP);
            clear();

         }
}
            }
    }

      private void clear(){
          
        codeArtField.setText("");
        quantiteField.clear();
        qteAfficchage.setText("");
        qtePaletteAfficchage.setText("");
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
        
        clear();
        
    }

    @FXML
    private void validerSaisieAction(ActionEvent event) {
        
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
         if(clientCombo.getSelectionModel().getSelectedItem()== null || clientCombo.getSelectionModel().getSelectedItem().isEmpty() ||fornisseurCombo.getSelectionModel().getSelectedItem()== null || fornisseurCombo.getSelectionModel().getSelectedItem().isEmpty() || detailCommandetable.getItems().size() ==0 ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
     }
        else {
          
             
                  MatierePremier matierePremier = matierePremiereDAO.findByCode(Constantes.CODE_MP);

//###########################################################################  Detail Commande "Emballages"  #################################################################################################################################################################################################################################################################################################################################################################     
           
            BigDecimal qtePalette = BigDecimal.ZERO;
            BigDecimal qteCaisse = BigDecimal.ZERO;
            BigDecimal qteBouteille = BigDecimal.ZERO;

            for (int i = 0; i < listeDetailCommandeTMP.size(); i++) {
                
                DetailCommande detailCommandeTMP = listeDetailCommandeTMP.get(i);
                
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
             
            listeDetailCommandeTMP.add(detailCommandeTMP);
                
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
             
            listeDetailCommandeTMP.add(detailCommandeTMP);
                
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
             
            listeDetailCommandeTMP.add(detailCommandeTMP);
                
            }       
             
                 BigDecimal qteCommande = BigDecimal.ZERO;
            BigDecimal prixCommande = BigDecimal.ZERO;
            
                  for (int i = 0; i < listeDetailCommandeTMP.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommandeTMP.get(i);
                  
                qteCommande = qteCommande.add(detailCommande.getQuantite());
                prixCommande = prixCommande.add(detailCommande.getPrixUnitaire());
                  
                  }
               
             montanTotal = qteCommande.multiply(prixCommande);
             
//###########################################################################   Commande   #################################################################################################################################################################################################################################################################################################################################################################     

               commande.setPrixTotal(montanTotal);
               commandeDAO.edit(commande);
               

       FXMLLoader fXMLLoader = new FXMLLoader();
         fXMLLoader.setLocation(getClass().getResource(nav.getValiderCommandeOulmes()));
       
               listeDetailCommandeTMP.clear();
               listeDetailCommandeTMP.addAll(detailCommandeDAO.findDetailCommandeByEtat(commande, Constantes.ETAT_AFFICHAGE));
       
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
 
    
            Stage stage = (Stage) validerSaisie.getScene().getWindow();
            stage.close();
        }
      
            }
        
    }
    
}
