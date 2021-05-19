/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.Client;
import dao.Entity.Fournisseur;
import dao.Entity.HistoriquePrix;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
import dao.Manager.ClientDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.HistoriquePrixDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.ManagerImpl.ClientDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.HistoriquePrixDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ModifierPrixOulmesController implements Initializable {

    @FXML
    private Button modifierBtn;
    @FXML
    private TextField remiseAchatField;
    @FXML
    private TextField remiseAvoirField;
    @FXML
    private RadioButton radPiece;
    @FXML
    private ToggleGroup radPackPiece;
    @FXML
    private RadioButton radPack;
    @FXML
    private TextField conditionnementField;
    @FXML
    private TextField conditionnementCaisseField;
    @FXML
    private ComboBox<String> artCombo;
    @FXML
    private TextField prixField;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField lieuLivraisonField;
    
    navigation nav = new navigation();
    ProduitDAO produitDAO = new ProduitDAOImpl();
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    HistoriquePrixDAO historiquePrixDAO = new HistoriquePrixDAOImpl();
    ClientDAO clientDAO =new ClientDAOImpl();
     
    private Map<String,Produit> mapProduit=new HashMap<>();
    private Map<String,Client> mapClient=new HashMap<>();
    
    public ObservableList<PrixOulmes> listePrixOulmesTMP=FXCollections.observableArrayList();
    
//    ObservableList<String> client =FXCollections.observableArrayList(Constantes.CLIENT_MARJANE,Constantes.CLIENT_MINURSO);
    
    /**
     * Initializes the controller class.
     */
     public PrixOulmes  prixOulmes;
  
             public  void chargerLesDonnees(){

        artCombo.setValue(prixOulmes.getProduit().getLibelle());
        remiseAchatField.setText(prixOulmes.getRemiseAchat()+"");
        remiseAvoirField.setText(prixOulmes.getRemiseAvoir()+"");
        conditionnementField.setText(prixOulmes.getConditionnement()+"");
        conditionnementCaisseField.setText(prixOulmes.getConditionnementCaisse()+"");
        prixField.setText(prixOulmes.getPrix()+"");
        clientCombo.setValue(prixOulmes.getClient());
        lieuLivraisonField.setText(prixOulmes.getLieuLivraison());
        
        
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//        clientCombo.setItems(client);
        
       List<Client> listCliient=clientDAO.findAll();
        
        listCliient.stream().map((client) -> {
            clientCombo.getItems().addAll(client.getLibelle());
            return client;
        }).forEachOrdered((client) -> {
            mapClient.put(client.getLibelle(), client);
        });

          List<Produit> listProduit=produitDAO.findAll();
        
        listProduit.stream().map((produit) -> {
            artCombo.getItems().addAll(produit.getLibelle());
            return produit;
        }).forEachOrdered((produit) -> {
            mapProduit.put(produit.getLibelle(), produit);
        });
        
    }    

    @FXML
    private void modifierBtnOnAction(ActionEvent event) {
        
          if( artCombo.getSelectionModel().getSelectedItem()==null || prixField.getText().equals("")){
         nav.showAlert(Alert.AlertType.WARNING, "Attention" , null, "Vous devez remplir les champs Svp !!");
     }
        else {
              
              
              Fournisseur fournisseur = fournisseurDAO.findByFournisseur();
              
                                HistoriquePrix historiquePrix = new HistoriquePrix();
        
        historiquePrix.setAncienPrix(prixOulmes.getPrix());
        historiquePrix.setNouveauPrix(new BigDecimal(prixField.getText()));
        historiquePrix.setFournisseur(fournisseur);
        historiquePrix.setPrixOulmes(prixOulmes);
        historiquePrix.setDateCreation(new Date());
        historiquePrix.setChemin(Constantes.PARAMETRAGE_PRIX);
        historiquePrix.setUtilisateurCreation(nav.getUtilisateur());
        
        historiquePrixDAO.add(historiquePrix);
        
 //######################################################################################################################################################################################################################################################################################################################      
       

              prixOulmes.setProduit(mapProduit.get(artCombo.getSelectionModel().getSelectedItem()));
              prixOulmes.setClient(clientCombo.getSelectionModel().getSelectedItem());
              prixOulmes.setConditionnement(new BigDecimal(conditionnementField.getText()));
              prixOulmes.setConditionnementCaisse(new BigDecimal(conditionnementCaisseField.getText()));
              prixOulmes.setRemiseAchat(new BigDecimal(remiseAchatField.getText()));
              prixOulmes.setRemiseAvoir(new BigDecimal(remiseAvoirField.getText()));
              prixOulmes.setLieuLivraison(lieuLivraisonField.getText());
              
              if (radPack.isSelected()== true){
                prixOulmes.setTypeArticle(Constantes.PACK);
              }else{
                prixOulmes.setTypeArticle(Constantes.PIECE);
              }
              
               prixOulmes.setPrix(new BigDecimal(prixField.getText()).setScale(6, RoundingMode.FLOOR));
               
               prixOulmesDAO.edit(prixOulmes);
               
               
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource(nav.getConsultationPrixCategorie()));

               
               listePrixOulmesTMP.clear();
               listePrixOulmesTMP.addAll(prixOulmesDAO.findAll());


         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, "Modifier avec Succès");
         
     Stage stage = (Stage) modifierBtn.getScene().getWindow();
            stage.close();
     
  
        }
        
    }

    @FXML
    private void radPieceOnAction(ActionEvent event) {
    }

    @FXML
    private void radPackOnAction(ActionEvent event) {
    }

    @FXML
    private void artComboOnAction(ActionEvent event) {
    }


    @FXML
    private void clientOnAction(ActionEvent event) {
    }
    
}
