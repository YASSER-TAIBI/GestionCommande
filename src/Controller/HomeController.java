/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Utils.Constantes;
import animasi.FadeInTransition;
import dao.Entity.Habilitation;
import dao.Entity.StockMP;
import dao.Entity.StockPF;
import dao.Entity.Utilisateur;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.HabilitationDAO;
import dao.Manager.StockMPDAO;
import dao.Manager.StockPFDAO;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.HabilitationDAOImpl;
import dao.ManagerImpl.StockMPDAOImpl;
import dao.ManagerImpl.StockPFDAOImpl;
import function.navigation;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 * FXML Controller class
 *
 * @author khatari-pc
 */
public class HomeController implements Initializable {
          navigation nav = new navigation(); 
    /**
     * Initializes the controller class.
     */
    
    @FXML    
    private AnchorPane centrePane;
    @FXML    
    private AnchorPane menuPane;
    @FXML
    private Label utilisateurConnecte;
    @FXML
    private Menu commandeMenu;
    @FXML
    private Menu livraisonMenu;
    @FXML
    private Menu reglementMenu;
    @FXML
    private Menu clientMenu;
    @FXML
    private Menu fournisseurMenu;
    @FXML
    private Menu referentielMenu;
    @FXML
    private Menu parametreMenu;
    

    @FXML
    private ImageView deconnecterUtilisBtn;
    @FXML
    private Menu receptionMenu;
    @FXML
    private Menu retourGratuiteMenu;
    @FXML
    private MenuItem saisirCommandeMenu;
    @FXML
    private MenuItem validerCommandeMenu;
    @FXML
    private MenuItem envoyerCommandeMenu;
    @FXML
    private MenuItem suiviLivraisonMenu;
    @FXML
    private MenuItem situationGlobalCommandeMenu;
    @FXML
    private MenuItem recuCommandeMenu;
    @FXML
    private MenuItem suiviReceptionMenu;
    @FXML
    private MenuItem suiviRetourGratuiteMenu;
    @FXML
    private MenuItem consultationBonRetourGratuiteMenu;
    @FXML
    private MenuItem suiviEtatReglementMenu;
    @FXML
    private MenuItem suiviReglementMenu;
    @FXML
    private MenuItem reglementReportMenu;
    @FXML
    private MenuItem consultationReglementMenu;
    @FXML
    private Menu stockMenu;
    @FXML
    private MenuItem consultationStockMenu;
    @FXML
    private MenuItem ajouterClientMenu;
    @FXML
    private MenuItem consultationSoldeClientMenu;
    @FXML
    private MenuItem ajouterFournisseurMenu;
    @FXML
    private MenuItem consultationSoldeFourMenu;
    @FXML
    private MenuItem dimensionMenu;
    @FXML
    private MenuItem gammageMenu;
    @FXML
    private MenuItem gammageFilmMenu;
    @FXML
    private MenuItem typeCartonBoxMenu;
    @FXML
    private MenuItem typeCartonMenu;
    @FXML
    private MenuItem typeFilmMenu;
    @FXML
    private MenuItem intervalleMenu;
    @FXML
    private MenuItem villeMenu;
    @FXML
    private MenuItem compteMenu;
    @FXML
    private MenuItem saisirReportMenu;
    @FXML
    private MenuItem prixDimensionFourMenu;
    @FXML
    private MenuItem consultationPrixCategorieMenu;
    @FXML
    private MenuItem ajouteUtilisateurMenu;
    @FXML
    private MenuItem gererAuthorisationMenu;
    @FXML
    private MenuItem saisirCommandeRegionMenu;
    
    HabilitationDAO habilitationDAO = new HabilitationDAOImpl();
    Utilisateur utilisateur = new Utilisateur();
    @FXML
    private MenuItem validerCommandeRegionMenu;
    @FXML
    private Menu matierePremiereMenu;
    @FXML
    private MenuItem saisirMatierePremiereMenu;
    @FXML
    private MenuItem categorieMatierePremiereMenu;
    @FXML
    private MenuItem subCategorieMatierePremiereMenu;
    @FXML
    private Menu commandeRegionMenu;
    @FXML
    private Menu commandeSiegeMenu;
    @FXML
    private Menu materielBoxMenu;
    @FXML
    private Menu materielCartonMenu;
    @FXML
    private Menu materielFilmMenu;
    @FXML
    private Menu commandeInterneMenu;
    @FXML
    private Menu commandeExterneMenu;
    @FXML
    private MenuItem saisirCommandeEtrangereMenu;
    @FXML
    private Menu materialCommandeEtrangereMenu;
    @FXML
    private MenuItem articleEtrangereMenu;
    @FXML
    private MenuItem chargementEtrangereMenu;
    @FXML
    private MenuItem enPortEtrangereMenu;
    @FXML
    private MenuItem dossierDouaneMenu;
    @FXML
    private Menu douaneMenu;
    @FXML
    private MenuItem suiviDouaneMenu;

    
     private final ObservableList<StockPF> listeStockProd=FXCollections.observableArrayList();
    
      StockPFDAO  stockPFDAO = new StockPFDAOImpl();
         DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
      private List <Object[]> listeObject=new ArrayList<Object[]>();
    @FXML
    private ImageView alerticone;
    @FXML
    private ListView<String> listeViewAlertMax;
    @FXML
    private ImageView alerticone1;
    @FXML
    private ImageView alerticone2;
    @FXML
    private ListView<String> listeViewAlertMin;
    @FXML
    private ImageView alerticone11;
    @FXML
    private MenuItem regularisationDesPrixMenu;
    @FXML
    private MenuItem saisirCommandeOulmesMenu;
    @FXML
    private MenuItem validerCommandeOulmesMenu;
    @FXML
    private MenuItem envoyerCommandeOulmesMenu;
    @FXML
    private MenuItem suiviLivraisonOulmesMenu;
    @FXML
    private Menu reglementMpMenu;
    @FXML
    private Menu reglementOulmesMenu;
    @FXML
    private MenuItem suiviEtatReglementOulmesMenu;
    @FXML
    private MenuItem suiviReglementOulmesMenu;
    @FXML
    private MenuItem avoirOulmesMenu;
    @FXML
    private MenuItem articleOulmesMenu;
    @FXML
    private MenuItem consultationStockOulmesMenu;
    @FXML
    private MenuItem factureAvoirOulmesMenu;
    @FXML
    private MenuItem situationGlobalAvoirOulmesMenu;
    @FXML
    private MenuItem ConsultationRegularisationPrixMenu;
    @FXML
    private MenuItem recuCommandeOulmesMenu;
    @FXML
    private MenuItem suiviReceptionOulmesMenu;
    @FXML
    private MenuItem detailFactureAvoirBonAvoirOulmesMenu;
    @FXML
    private MenuItem familleArticleEtrangereMenu;
    @FXML
    private MenuItem sousFamilleArticleEtrangereMenu;
    @FXML
    private MenuItem situationGlobalAvoirClientOulmesMenu;
    @FXML
    private MenuItem suiviManqueFourMenu;
    @FXML
    private MenuItem consultationManqueMpMenu;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           utilisateur=nav.utilisateur;

     utilisateurConnecte.setText (LoginController.utilisateur.getNom());
     autoriseMenuUtilisateur();
        
        
//     
//     
//    listeStockProd.clear();
//    listeStockProd.addAll(stockPFDAO.findAll());
//     
//    
//    
//    
//    
//       for (int i = 0; i < listeStockProd.size(); i++) {
//            
//             StockPF stockPF = listeStockProd.get(i);
//             
//            BigDecimal qteRestee = BigDecimal.ZERO;
//            BigDecimal qteStock = BigDecimal.ZERO;
//            
//            listeObject = detailCommandeDAO.findDetailCommandeByQteRestee(stockPF.getMatierePremier().getId(), stockPF.getMagasin().getDepot().getId(),Constantes.ETAT_AFFICHAGE,nav.utilisateur.getId(), stockPF.getMagasin().getLibelle());
//             
//    
//      if(!listeObject.isEmpty()){
//
//                     Object[] object = listeObject.get(0);
//                      qteRestee=(BigDecimal) object[0];
//
//      }
//                       
//               qteStock= qteRestee.add(stockPF.getStock());
//               
//          
//               
//             if (qteStock.compareTo(stockPF.getMatierePremier().getStockAlert())<0){
//                 
//             stockPF.setManque(qteStock.subtract(stockPF.getMatierePremier().getValMinMP()));
//             
//          listeStockProd.set(i, stockPF);
//
//             }else if (qteStock.compareTo(stockPF.getMatierePremier().getStockAlert())>0){
//             
//                     stockPF.setManque(qteStock.subtract(stockPF.getMatierePremier().getValMaxMP()));
//             
//          listeStockProd.set(i, stockPF);
//
//             }
//             else {
//             
//               stockPF.setManque(BigDecimal.ZERO);
//             
//          listeStockProd.set(i, stockPF);  
//             
//             }
// 
//        }
//         ObservableList<String> alertMin = FXCollections.observableArrayList();
//         ObservableList<String> alertMax = FXCollections.observableArrayList();
//         
//        for (int i = 0; i < listeStockProd.size(); i++) {
//            
//             StockPF stockPF = listeStockProd.get(i);
//             
//            if (stockPF.getManque().compareTo(BigDecimal.ZERO)<0)
//            {
//                
//                alertMin.add("MP: "+stockPF.getMatierePremier().getCode()+" || PRODUIT: "+stockPF.getMatierePremier().getNom()+" || QUANTITE: "+stockPF.getManque());
//
//            }else if (stockPF.getManque().compareTo(BigDecimal.ZERO)>0){
//                
//                alertMax.add("MP: "+stockPF.getMatierePremier().getCode()+" || PRODUIT: "+stockPF.getMatierePremier().getNom()+" || QUANTITE: "+stockPF.getManque()); 
//            }
//        }
//           listeViewAlertMin.setItems(alertMin);
//           
//           listeViewAlertMax.setItems(alertMax);
    }    
 
   
    
    
     void autoriseMenuUtilisateur(){
	 
	 List<Habilitation> listHabilitation=habilitationDAO.findHabilitationByUtilisateur(utilisateur.getId());
	 
	 for(int i=0;i<listHabilitation.size();i++){
		 Habilitation habilitation =listHabilitation.get(i);
		 
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_COMMANDE)){
			 commandeMenu.setDisable(habilitation.isAutorise());
		 }
                  if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_COMMANDE_REGION)){
			 commandeRegionMenu.setDisable(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_SAISIR_COMMANDE_REGION)){
			 saisirCommandeRegionMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_VALIDER_COMMANDE_REGION)){
			 validerCommandeRegionMenu.setDisable(habilitation.isAutorise());
		 }
                   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_COMMANDE_SIEGE)){
			 commandeSiegeMenu.setDisable(habilitation.isAutorise());
		 }
                  if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_SAISIR_COMMANDE)){
			 saisirCommandeMenu.setDisable(habilitation.isAutorise());
		 }
                  if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_VALIDER_COMMANDE)){
			 validerCommandeMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_ENVOYER_COMMANDE)){
			 envoyerCommandeMenu.setDisable(habilitation.isAutorise());
		 }  
                
                 
                 
		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_LIVRAISON)){
			 livraisonMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_SUIVI_LIVRAISON)){
			 suiviLivraisonMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_SITUATION_GLOBAL_COMMANDE)){
			 situationGlobalCommandeMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_Recu_COMMANDE)){
			 recuCommandeMenu.setDisable(habilitation.isAutorise());
		 }
                    
                    
                    
                  if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_RECEPTION)){
			 receptionMenu.setDisable(habilitation.isAutorise());
		 }
                   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_SUIVI_RECEPTION)){
			 suiviReceptionMenu.setDisable(habilitation.isAutorise());
		 }
                  
                   
                   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_RETOUR_GRATUITE)){
			 retourGratuiteMenu.setDisable(habilitation.isAutorise());
		 }
                   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_SUIVI_RETOUR_GRATUITE)){
			 suiviRetourGratuiteMenu.setDisable(habilitation.isAutorise());
		 }
                   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_CONSULTATION_RETOUR_GRATUITE)){
			 consultationBonRetourGratuiteMenu.setDisable(habilitation.isAutorise());
		 }
                   
                   
                   
		   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_REGLEMENT)){
			 reglementMenu.setDisable(habilitation.isAutorise());
		 }
                    if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_SUIVI_ETAT_REGLEMENT)){
			 suiviEtatReglementMenu.setDisable(habilitation.isAutorise());
		 }
                    if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_SUIVI_REGLEMENT)){
			 suiviReglementMenu.setDisable(habilitation.isAutorise());
		 }
                    if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_REPORT_REGLEMENT)){
			 reglementReportMenu.setDisable(habilitation.isAutorise());
		 }
                    if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_CONSULTATION_REGLEMENT)){
			 consultationReglementMenu.setDisable(habilitation.isAutorise());
		 }
                    
                   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_STOCK)){
			 stockMenu.setDisable(habilitation.isAutorise());
		 }
                   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_CONSULTATION_STOCK)){
			 consultationStockMenu.setDisable(habilitation.isAutorise());
		 }
                    
                    
		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_CLIENT)){
			 clientMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_AJOUTER_CLIENT)){
			 ajouterClientMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_CONSULTATION_SOLDE_CLIENT)){
			 consultationSoldeClientMenu.setDisable(habilitation.isAutorise());
		 }
                 
                 
		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_FOURNISSEUR)){
			 fournisseurMenu.setDisable(habilitation.isAutorise());
		 }
                   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_AJOUTER_FOURNISSEUR)){
			 ajouterFournisseurMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_CONSULTATION_SOLDE_FOURNISSEUR)){
			 consultationSoldeFourMenu.setDisable(habilitation.isAutorise());
		 }
                 
                 
                 
		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_REFERENTIEL)){
			 referentielMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_MATERIAL_BOX)){
			 materielBoxMenu.setDisable(habilitation.isAutorise());
		 }
                  if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_MATERIAL_CARTON)){
			 materielCartonMenu.setDisable(habilitation.isAutorise());
		 }
                   if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_MATERIAL_FILM)){
			 materielFilmMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_DIMENSION)){
			 dimensionMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_GRAMMAGE)){
			 gammageMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_GRAMMAGE_FILM)){
			 gammageFilmMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_TYPE_CARTON_BOX)){
			 typeCartonBoxMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_TYPE_CARTON)){
			 typeCartonMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_TYPE_FILM)){
			 typeFilmMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_INTERVALLE)){
			 intervalleMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_VILLE)){
			 villeMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_COMPTE)){
			 compteMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_SAISIR_REPORT)){
			 saisirReportMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_MATIERE_PREMIERE)){
			 matierePremiereMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_SAISIR_MATIERE_PREMIERE)){
			 saisirMatierePremiereMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_CATEGORIE_MATIERE_PREMIERE)){
			 categorieMatierePremiereMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_SUB_CATEGORIE_MATIERE_PREMIERE)){
			 subCategorieMatierePremiereMenu.setDisable(habilitation.isAutorise());
		 }
                 
                 
                 
		 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_PARAMETRE)){
			 parametreMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_PRIX_DIMENSION)){
			 prixDimensionFourMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_CONSULTATION_PRIX_CATEGORIE)){
			 consultationPrixCategorieMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_AJOUTER_UTILISATEUR)){
			 ajouteUtilisateurMenu.setDisable(habilitation.isAutorise());
		 }
                 if(habilitation.getMenu().getCode().equals(Constantes.COD_MENU_GERER_AUTHORISATION)){
			 gererAuthorisationMenu.setDisable(habilitation.isAutorise());
		 }

	 }
	 
 }
    
    @FXML  
    public void menuAjtFournisseur() throws IOException{
        try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getListeFournisseur()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
        }
    }
    
    public void menuDelete() throws IOException{
        try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getUser()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
        }
    }

    @FXML
    private void menuAjtClient(ActionEvent event) {
       try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getListeClientMP()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
        }
    }


    @FXML
    private void menuAjouUtilisateur(ActionEvent event) {
        try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getListeUtilisateur()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
        }
    }

    @FXML
    private void menuSaisir(ActionEvent event) {
          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getSaisirCommande()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuValiderCommande(ActionEvent event) {
           try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getValiderCommande()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuEnvoyerCommande(ActionEvent event) {
          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getEnvoyerCommande()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuSuiviCommande(ActionEvent event) {
        try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getSuiviCommande()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuMajVille(ActionEvent event) {
          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getVille()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuDimension(ActionEvent event) {
          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getDimension()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }}


    @FXML
    private void menuCompteUse(ActionEvent event) {
          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getCompte()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuReglement(ActionEvent event) {
               try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getReglement()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void consultationSoldeOnAction(ActionEvent event) {
             try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getConsultationSoldeFour()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuGererAuthorisation(ActionEvent event) {
                   try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getGererAuthUtilisateur()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuSuiviRetour(ActionEvent event) {
         try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getBonRetour()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void deconnecterUtilisOnMouseClick(MouseEvent event){
         Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_QUITTER_APP);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                      System.exit(0);

            } 
        
    }

    @FXML
    private void fermerAppOnMouseClick(MouseEvent event) throws IOException {
        
     
            
                  Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_FERMER_SESSION);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                  
                Stage stage = (Stage)
              deconnecterUtilisBtn.getScene().getWindow();
              stage.close();
                Parent root = FXMLLoader.load(getClass().getResource(nav.getLogin()));
                Stage stage1 = new Stage();
                Scene scence = new Scene(root);
                stage1.setScene(scence);
                
                 //supprimer la bar fermer reduire agrandir
                 
                stage1.initStyle(StageStyle.UNDECORATED);
                stage1.show();

            } 
    }

    @FXML
    private void homeOnMouseClick(MouseEvent event) throws IOException {

           try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getHome()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
        
    }

    
    @FXML
    private void consultationReglementOnAction(ActionEvent event) {
            try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getConsultationReglement()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void consultationSoldeClientOnAction(ActionEvent event) {
               try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getConsultationSoldeClient()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuReglementReport(ActionEvent event) {
          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getReglementReport()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuGrammage(ActionEvent event) {
               try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getGrammage()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuTypeCartonBox(ActionEvent event) {
               try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getTypeCartonBox()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuIntervalle(ActionEvent event) {
               try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getIntervalle()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuPrixDimFour(ActionEvent event) {
         try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getPrixDimFour()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuTypeFilm(ActionEvent event) {
         try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getTypeFilm()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuGrammageFilm(ActionEvent event) {
        
            try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getGrammageFilm()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
             
        
        }
    }

    @FXML
    private void menuTypeCarton(ActionEvent event) {
           try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getTypeCarton()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuConsultationPrixCategorie(ActionEvent event) {
         try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getConsultationPrixCategorie()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuSaisirReport(ActionEvent event) {
         try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getSaisirReport()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuRecuCommande(ActionEvent event) {
           try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getRecuCommande()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    private void menuConsultationCommande(ActionEvent event) {
          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getConsultationCommande()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuConsultationStock(ActionEvent event) {
             try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getStock()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }

    @FXML
    private void menuBonRetGra(ActionEvent event) {
          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getConsultationBonRetourGratuite()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
    }
   
}

    @FXML
    private void menuSituationGlobalCommande(ActionEvent event) {
        
         try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getSituationGlobalCommande()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

    }
        
    }

    @FXML
    private void menuSuiviReception(ActionEvent event) {
        
            try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getReception()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    @FXML
    private void menuEtatReglement(ActionEvent event) {
        
                      try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane1 = FXMLLoader.load(getClass().getResource(nav.getEtatReglement()));
            centrePane.getChildren().setAll(pane1);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
        
        }
    }


    @FXML
    private void menuSaisirCommandeRegion(ActionEvent event) {
           try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getSaisirCommandeRegion()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuValiderCommandeRegion(ActionEvent event) {
          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getValiderCommandeRegion()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuSaisirMatierePremiere(ActionEvent event) {
         try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getMatierePremier()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuCategorieMatierePremiere(ActionEvent event) {
             try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getCategorieMp()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuSubCategorieMatierePremiere(ActionEvent event) {
         try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getSubCategorieMp()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuSaisirCommandeEtrangere(ActionEvent event) {
        
          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getSaisirCommandeEtrangere()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuArticleEtrangere(ActionEvent event) {
        
              try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getArticleEtrangere()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
        
    }

    @FXML
    private void menuChargementEtrangare(ActionEvent event) {
        
                try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getChargementEtrangere()));
            centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
        
    }

    private void menuPrixArticleFourEtrangere(ActionEvent event) {
        
                      try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getPrixArtFourEtrangere()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
        
    }

    @FXML
    private void menuEnPortEtrangare(ActionEvent event) {
        
          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getEnPortEtrangere()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuDossierDouane(ActionEvent event) {
        
               try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getDossierDouane()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
        
    }

    @FXML
    private void menuSuiviDouane(ActionEvent event) {
        
                   try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getSuiviDouane()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
        
    }

    @FXML
    private void menuRegularisationDesPrix(ActionEvent event) {
        
          try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getRegularisationDesPrix()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuSaisirOulmes(ActionEvent event) {
        
                try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getSaisirCommandeOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
    }

    @FXML
    private void menuValiderOulmesCommande(ActionEvent event) {
        
             try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getValiderCommandeOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
        
    }

    @FXML
    private void menuEnvoyerOulmesCommande(ActionEvent event) {
        
        
             try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getEnvoyerCommandeOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
             System.out.println("Exception !!!!!!!!!");
            System.out.println(e);
            
            
        }
        
        
    }

    @FXML
    private void menuSuiviOulmesCommande(ActionEvent event) {

             try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getSuiviCommandeOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    @FXML
    private void menuEtatOulmesReglement(ActionEvent event) {
        
             try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getEtatReglementOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
    }

    @FXML
    private void menuReglementOulmes(ActionEvent event) {
        
                try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getReglementOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    @FXML
    private void menuAvoirOulmes(ActionEvent event) {
        
                try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getAvoirOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
    }

    @FXML
    private void menuMajArticleOulmes(ActionEvent event) {
        
                      try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getArticleOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    @FXML
    private void menuConsultationStockOulmes(ActionEvent event) {
        
              try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getStockOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    @FXML
    private void menuFactureAvoirOulmes(ActionEvent event) {
        
            try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getFactureAvoirOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    @FXML
    private void menuSituationGlobalAvoirOulmes(ActionEvent event) {
        
                try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getSituationGlobalAvoirOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    @FXML
    private void menuConsultationRegularisationPrix(ActionEvent event) {
        
                   try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getConsultationregularisationDesPrix()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    @FXML
    private void menuRecuCommandeOulmes(ActionEvent event) {
        
                      try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getRecuCommandeOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    @FXML
    private void menuSuiviReceptionOulmes(ActionEvent event) {
        
                              try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getReceptionOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
        
    }

    @FXML
    private void menuDetailFactureAvoirBonAvoirOulmes(ActionEvent event) {
        
        
                              try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getDetailFactureAvoirBonAvoirOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
        
    }

    @FXML
    private void menuFamilleArticleEtrangere(ActionEvent event) {
        
              try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getFamilleArticleEtrangere()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
        
    }

    @FXML
    private void menuSousFamilleArticleEtrangere(ActionEvent event) {
        
                     try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getSousFamilleArticleEtrangere()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    @FXML
    private void menuSituationGlobalAvoirClientOulmes(ActionEvent event) {
        
                         try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getSituationGlobalAvoirClientOulmes()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    @FXML
    private void menuSuiviManqueFourMenu(ActionEvent event) {
        
                            try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getSuiviManqueFour()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    @FXML
    private void menuconsultationManqueMp(ActionEvent event) {
        
                                  try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getConsultationManqueMp()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
    }

    private void prixMoyenOnAction(ActionEvent event) {
        
                                  try {
            centrePane.getChildren().clear();
            centrePane.setOpacity(0);
            new FadeInTransition(centrePane).play();
           AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getPrixMoyen()));
           centrePane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            System.out.println("Exception !!!!!!!!!");
            System.out.println(e);

        }
        
        
    }

}
