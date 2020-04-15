/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Referentiel;

import Utils.Constantes;
import dao.Entity.CategorieMp;
import dao.Entity.Dimension;
import dao.Entity.Fournisseur;
import dao.Entity.Grammage;
import dao.Entity.GrammageFilm;
import dao.Entity.Intervalle;
import dao.Entity.PrixAdhesif;
import dao.Entity.PrixBox;
import dao.Entity.PrixBoxMetal;
import dao.Entity.PrixCarton;
import dao.Entity.PrixFilm;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
import dao.Entity.SubCategorieMp;
import dao.Entity.TypeCarton;
import dao.Entity.TypeCartonBox;
import dao.Entity.TypeFilm;
import dao.Manager.CategorieMpDAO;
import dao.Manager.DimensionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.GrammageDAO;
import dao.Manager.GrammageFilmDAO;
import dao.Manager.IntervalleDAO;
import dao.Manager.PrixAdhesifDAO;
import dao.Manager.PrixBoxDAO;
import dao.Manager.PrixBoxMetalDAO;
import dao.Manager.PrixCartonDAO;
import dao.Manager.PrixFilmDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.Manager.SubCategorieMPDAO;
import dao.Manager.TypeCartonBoxDAO;
import dao.Manager.TypeCartonDAO;
import dao.Manager.TypeFilmDAO;
import dao.ManagerImpl.CategorieMpDAOImpl;
import dao.ManagerImpl.DimensionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.GrammageDAOImpl;
import dao.ManagerImpl.GrammageFilmDAOImpl;
import dao.ManagerImpl.IntervalleDAOImpl;
import dao.ManagerImpl.PrixAdhesifDAOImpl;
import dao.ManagerImpl.PrixBoxDAOImpl;
import dao.ManagerImpl.PrixBoxMetalDAOImpl;
import dao.ManagerImpl.PrixCartonDAOImpl;
import dao.ManagerImpl.PrixFilmDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
import dao.ManagerImpl.SubCategorieMPAOImpl;
import dao.ManagerImpl.TypeCartonBoxDAOImpl;
import dao.ManagerImpl.TypeCartonDAOImpl;
import dao.ManagerImpl.TypeFilmDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.print.DocFlavor;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class PrixDimesionFourController implements Initializable {

    @FXML
    private ComboBox<String> dimCombo;
    @FXML
    private ComboBox<String> sousCatCombo;
    @FXML
    private ComboBox<String> fourCombo;
     @FXML
    private ComboBox<String> typeCategorieCombo;
    @FXML
    private ComboBox<String> TypeCarBoxCombo;
    @FXML
    private ComboBox<String> grammageCombo;
    @FXML
    private ComboBox<String> TypeFilmCombo;
     @FXML
    private ComboBox<String> TypeCarCombo;
    @FXML
    private TextField prixField;
    @FXML
    private Button btnValider;
    @FXML
    private ComboBox<String> grammageFilmCombo;
    @FXML
    private ComboBox<String> IntervalleCombo;
    @FXML
    private Button btnRefrech;
    @FXML
    private ComboBox<String> artCombo;
    @FXML
    private RadioButton radioPrixMp;
    @FXML
    private ToggleGroup groupeType1;
    @FXML
    private RadioButton radioPrixArt;
    @FXML
    private TextField remiseAchatField;
    @FXML
    private TextField remiseAvoirField;
    @FXML
    private RadioButton radPiece;
    @FXML
    private RadioButton radPack;
    @FXML
    private TextField conditionnementField;
    @FXML
    private TextField conditionnementCaisseField;
    @FXML
    private ToggleGroup radPackPiece;
    /**
     * Initializes the controller class.
     */

     String valeur= "";
     PrixBox prixBox = new PrixBox();
     PrixCarton prixCarton = new PrixCarton();
     PrixFilm prixFilm = new PrixFilm();
     PrixAdhesif prixAdhesif = new PrixAdhesif();
     PrixBoxMetal prixBoxMetal = new PrixBoxMetal();
     PrixOulmes prixOulmes = new PrixOulmes();
              
     
     private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
     private Map<String,SubCategorieMp> mapSubCategorieMp=new HashMap<>();
     private Map<String,CategorieMp> mapCategorieMp=new HashMap<>();
     private Map<String,Dimension> mapDimension=new HashMap<>();
     private Map<String,Grammage> mapGrammage=new HashMap<>();
     private Map<String,Intervalle> mapIntervalle=new HashMap<>();
     private Map<String,TypeCartonBox> mapTypeCarBox=new HashMap<>();
     private Map<String,TypeFilm> mapTypeFilm=new HashMap<>();
     private Map<String,GrammageFilm> mapGrammageFilm=new HashMap<>();
     private Map<String,TypeCarton> mapTypeCar=new HashMap<>();
     private Map<String,Produit> mapProduit=new HashMap<>();
    
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
     PrixBoxDAO prixBoxDAO = new PrixBoxDAOImpl();
     PrixBoxMetalDAO prixBoxMetalDAO = new PrixBoxMetalDAOImpl();
    SubCategorieMPDAO subcategorieMpDAO = new SubCategorieMPAOImpl();
    CategorieMpDAO categorieMpDAO  = new CategorieMpDAOImpl();
    DimensionDAO dimensionDAO = new DimensionDAOImpl();
    IntervalleDAO intervalleDAO = new IntervalleDAOImpl();
    GrammageDAO grammageDAO = new GrammageDAOImpl();
    TypeCartonBoxDAO typeCartonBoxDAO = new TypeCartonBoxDAOImpl();
    TypeCartonDAO typeCartonDAO = new TypeCartonDAOImpl();
    PrixCartonDAO prixCartonDAO = new PrixCartonDAOImpl();
    TypeFilmDAO typeFilmDAO = new  TypeFilmDAOImpl();
    PrixFilmDAO prixFilmDAO = new PrixFilmDAOImpl();
    GrammageFilmDAO grammageFilmDAO = new GrammageFilmDAOImpl();
    PrixAdhesifDAO prixAdhesifDAO = new PrixAdhesifDAOImpl();
    ProduitDAO produitDAO = new ProduitDAOImpl();
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    
    navigation nav = new navigation();
    @FXML
    private RadioButton radClientOui;
    @FXML
    private RadioButton radClientNon;
    @FXML
    private ComboBox<String> clientCombo;
   
  ObservableList<String> client =FXCollections.observableArrayList(Constantes.CLIENT_MARJANE,Constantes.CLIENT_MINURSO);
    @FXML
    private ToggleGroup radOuiNon;
    @FXML
    private TextField lieuLivraisonField;
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
      clientCombo.setItems(client);
      
        valeur= sousCatCombo.getSelectionModel().getSelectedItem();
        
        List<Produit> listProduit=produitDAO.findAll();
        
        listProduit.stream().map((produit) -> {
            artCombo.getItems().addAll(produit.getLibelle());
            return produit;
        }).forEachOrdered((produit) -> {
            mapProduit.put(produit.getLibelle(), produit);
        });
          
          List<Fournisseur> listFournisseur=fournisseurDAO.findAll();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });
        
          List<SubCategorieMp> listSubCategorieMp=subcategorieMpDAO.findAll();
        
        listSubCategorieMp.stream().map((subCategorieMp) -> {
            sousCatCombo.getItems().addAll(subCategorieMp.getNom());
            return subCategorieMp;
        }).forEachOrdered((subCategorieMp) -> {
            mapSubCategorieMp.put(subCategorieMp.getNom(), subCategorieMp);
        });
        
        List<Grammage> listGrammage=grammageDAO.findAll();
        
        listGrammage.stream().map((grammage) -> {
            grammageCombo.getItems().addAll(grammage.getLibelle());
            return grammage;
        }).forEachOrdered((grammage) -> {
            mapGrammage.put(grammage.getLibelle(), grammage);
        });
        
        List<GrammageFilm> listGrammageFilm=grammageFilmDAO.findAll();
        
        listGrammageFilm.stream().map((grammageFilm) -> {
            grammageFilmCombo.getItems().addAll(grammageFilm.getLibelle());
            return grammageFilm;
        }).forEachOrdered((grammageFilm) -> {
            mapGrammageFilm.put(grammageFilm.getLibelle(), grammageFilm);
        });
        
        List<TypeCartonBox> listTypeCartonBox=typeCartonBoxDAO.findAll();
        
        listTypeCartonBox.stream().map((typeCartonBox) -> {
            TypeCarBoxCombo.getItems().addAll(typeCartonBox.getLibelle());
            return typeCartonBox;
        }).forEachOrdered((typeCartonBox) -> {
            mapTypeCarBox.put(typeCartonBox.getLibelle(), typeCartonBox);
        });
        
         List<TypeCarton> listTypeCarton=typeCartonDAO.findAll();
        
        listTypeCarton.stream().map((typeCarton) -> {
            TypeCarCombo.getItems().addAll(typeCarton.getLibelle());
            return typeCarton;
        }).forEachOrdered((typeCarton) -> {
            mapTypeCar.put(typeCarton.getLibelle(), typeCarton);
        });
        
         List<CategorieMp> listCategorieMp=categorieMpDAO.findAll();
        
        listCategorieMp.stream().map((categorieMp) -> {
            typeCategorieCombo.getItems().addAll(categorieMp.getNom());
            return categorieMp;
        }).forEachOrdered((categorieMp) -> {
            mapCategorieMp.put(categorieMp.getNom(), categorieMp);
        });

           
     List<Intervalle> listIntervalle=intervalleDAO.findAll();

        listIntervalle.stream().map((intervalle) -> {
            IntervalleCombo.getItems().addAll(intervalle.getLibelle());
            return intervalle;
        }).forEachOrdered((intervalle) -> {
            mapIntervalle.put(intervalle.getLibelle(), intervalle);
        });

        
         List<TypeFilm> listTypeFilm=typeFilmDAO.findAll();
        
        listTypeFilm.stream().map((typeFilm) -> {
            TypeFilmCombo.getItems().addAll(typeFilm.getLibelle());
            return typeFilm;
        }).forEachOrdered((typeFilm) -> {
            mapTypeFilm.put(typeFilm.getLibelle(), typeFilm);
        });
        
        
               fourCombo.setDisable(true);
               artCombo.setDisable(true);
               typeCategorieCombo.setDisable(true);
               sousCatCombo.setDisable(true);
               grammageCombo.setDisable(true);
               IntervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               TypeCarBoxCombo.setDisable(true); 
               TypeCarCombo.setDisable(true); 
               prixField.setDisable(true);
               remiseAchatField.setDisable(true);
               remiseAvoirField.setDisable(true);
               conditionnementField.setDisable(true);
               conditionnementCaisseField.setDisable(true);
               clientCombo.setDisable(true);
               lieuLivraisonField.setDisable(true);
               radPack.setDisable(true);
               radPiece.setDisable(true);
               radClientOui.setDisable(true);
               radClientNon.setDisable(true);
    }    

    @FXML
    private void btnValiderOnAction(ActionEvent event) {
        
                    if(radioPrixMp.isSelected()){
                        
                     
                  valeur= sousCatCombo.getSelectionModel().getSelectedItem();
            if (valeur.equals(Constantes.BOX)){
                
                         if(fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                        sousCatCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        sousCatCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        typeCategorieCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        typeCategorieCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        TypeCarBoxCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        TypeCarBoxCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        dimCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        dimCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        IntervalleCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        IntervalleCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        grammageCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        grammageCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        prixField.getText().equalsIgnoreCase("") 

                        ){
                  nav.showAlert(Alert.AlertType.ERROR, "Attention", null,Constantes.REMPLIR_CHAMPS);
                }else{
            
           Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
           Dimension dimension=mapDimension.get(dimCombo.getSelectionModel().getSelectedItem());
           Grammage grammage = mapGrammage.get(grammageCombo.getSelectionModel().getSelectedItem());
           TypeCartonBox typeCartonBox = mapTypeCarBox.get(TypeCarBoxCombo.getSelectionModel().getSelectedItem()); 
           CategorieMp categorieMp  = mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem());    
           Intervalle intervalle=mapIntervalle.get(IntervalleCombo.getSelectionModel().getSelectedItem());
            

                         
                PrixBox prixBoxTmp=prixBoxDAO.findDimensionByPrixBox(dimension.getId(), fournisseur.getId(), categorieMp.getId(), grammage.getId(), typeCartonBox.getId(), intervalle.getId());
         
            
            if(prixBoxTmp==null)
            {       
                     
                     
                 prixBox = new PrixBox();
              prixBox.setSubCategorieMp(mapSubCategorieMp.get(sousCatCombo.getSelectionModel().getSelectedItem()));
              prixBox.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
              prixBox.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
              prixBox.setTypeCartonBox(mapTypeCarBox.get(TypeCarBoxCombo.getSelectionModel().getSelectedItem()));
              prixBox.setDimension(mapDimension.get(dimCombo.getSelectionModel().getSelectedItem()));
              prixBox.setGrammage(mapGrammage.get(grammageCombo.getSelectionModel().getSelectedItem()));
              prixBox.setUtilisateurCreation(nav.getUtilisateur());
              prixBox.setIntervalle(mapIntervalle.get(IntervalleCombo.getSelectionModel().getSelectedItem()));
              prixBox.setPrix(new BigDecimal(prixField.getText()));
              


                 prixBoxDAO.add(prixBox);

                    nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.AJOUTER_ENREGISTREMENT);
                    
                  refrech ();
                 return;
                   }else{
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_COORDONEES_EXISTE);  
            }
                  
          
                   }
                
             }else  if (valeur.equals(Constantes.CARTON)){
             
   
                if(fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                        sousCatCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        sousCatCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        typeCategorieCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        typeCategorieCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        TypeCarCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        TypeCarCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        dimCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        dimCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        IntervalleCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        IntervalleCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        prixField.getText().equalsIgnoreCase("") 

                        ){
                  nav.showAlert(Alert.AlertType.ERROR, "Attention", null,Constantes.REMPLIR_CHAMPS);
                }else{

           Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
           Dimension dimension=mapDimension.get(dimCombo.getSelectionModel().getSelectedItem());
           TypeCarton typeCarton = mapTypeCar.get(TypeCarCombo.getSelectionModel().getSelectedItem()); 
           CategorieMp categorieMp  = mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem());    
           Intervalle intervalle=mapIntervalle.get(IntervalleCombo.getSelectionModel().getSelectedItem());
            

                         
                PrixCarton prixCartonTmp=prixCartonDAO.findDimensionByPrixCarton(dimension.getId(), fournisseur.getId(), categorieMp.getId(), typeCarton.getId(), intervalle.getId());
         
            
            if(prixCartonTmp==null)
            {       
                     
                     
              prixCarton = new PrixCarton();
              prixCarton.setSubCategorieMp(mapSubCategorieMp.get(sousCatCombo.getSelectionModel().getSelectedItem()));
              prixCarton.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
              prixCarton.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
              prixCarton.setTypeCarton(mapTypeCar.get(TypeCarCombo.getSelectionModel().getSelectedItem()));
              prixCarton.setDimension(mapDimension.get(dimCombo.getSelectionModel().getSelectedItem()));
              prixCarton.setUtilisateurCreation(nav.getUtilisateur());
              prixCarton.setIntervalle(mapIntervalle.get(IntervalleCombo.getSelectionModel().getSelectedItem()));
              prixCarton.setPrix(new BigDecimal(prixField.getText()));

                 prixCartonDAO.add(prixCarton);

                    nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.AJOUTER_ENREGISTREMENT);
                    
                 refrech ();
                 return;
                   }else{
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_COORDONEES_EXISTE);  
            }

                }
                
             }else if (valeur.equals(Constantes.FILM_NORMAL)){
                
      
                      if(fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                        sousCatCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        sousCatCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        typeCategorieCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        typeCategorieCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        TypeFilmCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        TypeFilmCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        grammageFilmCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        grammageFilmCombo.getSelectionModel().getSelectedItem().equals("")||
                        IntervalleCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        IntervalleCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        prixField.getText().equalsIgnoreCase("") 

                        ){
                  nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                }else{
          
            TypeFilm typeFilm = mapTypeFilm.get(TypeFilmCombo.getSelectionModel().getSelectedItem());
            CategorieMp categorieMp  = mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem());    
            GrammageFilm grammageFilm = mapGrammageFilm.get(grammageFilmCombo.getSelectionModel().getSelectedItem());
            Dimension dimension=mapDimension.get(dimCombo.getSelectionModel().getSelectedItem());
            Intervalle intervalle=mapIntervalle.get(IntervalleCombo.getSelectionModel().getSelectedItem());
             Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
                         
                    PrixFilm prixFimTmp=prixFilmDAO.findDimensionByPrixFilm(fournisseur.getId(),typeFilm.getId(), grammageFilm.getId(),categorieMp.getId() , intervalle.getId());
         
            
            if(prixFimTmp==null)
            {       
                     
                     
              prixFilm = new PrixFilm();
              prixFilm.setSubCategorieMp(mapSubCategorieMp.get(sousCatCombo.getSelectionModel().getSelectedItem()));
              prixFilm.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
              prixFilm.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
              prixFilm.setGrammageFilm(mapGrammageFilm.get(grammageFilmCombo.getSelectionModel().getSelectedItem()));
              prixFilm.setTypeFilm(mapTypeFilm.get(TypeFilmCombo.getSelectionModel().getSelectedItem()));
              prixFilm.setDimension(dimension);
              prixFilm.setUtilisateurCreation(nav.getUtilisateur());
              prixFilm.setIntervalle(mapIntervalle.get(IntervalleCombo.getSelectionModel().getSelectedItem()));
              prixFilm.setPrix(new BigDecimal(prixField.getText()));
              

                 prixFilmDAO.add(prixFilm);

                    nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.AJOUTER_ENREGISTREMENT);
                    
                  refrech ();
                  
                 return;
                   }else{
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_COORDONEES_EXISTE);  
            }
                  
            }
             
                  }
             
             
             else if (valeur.equals(Constantes.FILM_GOLD)){
                

                          if(fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                        sousCatCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        sousCatCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        typeCategorieCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        typeCategorieCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        TypeFilmCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        TypeFilmCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        grammageFilmCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        grammageFilmCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        IntervalleCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        IntervalleCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        prixField.getText().equalsIgnoreCase("") 

                        ){
                  nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                }else{
           
            TypeFilm typeFilm = mapTypeFilm.get(TypeFilmCombo.getSelectionModel().getSelectedItem());
            CategorieMp categorieMp  = mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem());    
            GrammageFilm grammageFilm = mapGrammageFilm.get(grammageFilmCombo.getSelectionModel().getSelectedItem());
            Dimension dimension=mapDimension.get(dimCombo.getSelectionModel().getSelectedItem());
            Intervalle intervalle=mapIntervalle.get(IntervalleCombo.getSelectionModel().getSelectedItem());
             Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
                         
                          PrixFilm prixFimTmp=prixFilmDAO.findDimensionByPrixFilm(fournisseur.getId(),typeFilm.getId(), grammageFilm.getId(),categorieMp.getId() , intervalle.getId());
         
            
            if(prixFimTmp==null)
            {       
                     
                     
              prixFilm = new PrixFilm();
              prixFilm.setSubCategorieMp(mapSubCategorieMp.get(sousCatCombo.getSelectionModel().getSelectedItem()));
              prixFilm.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
              prixFilm.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
              prixFilm.setTypeFilm(mapTypeFilm.get(TypeFilmCombo.getSelectionModel().getSelectedItem()));
              prixFilm.setGrammageFilm(mapGrammageFilm.get(grammageFilmCombo.getSelectionModel().getSelectedItem()));
              prixFilm.setDimension(dimension);
              prixFilm.setUtilisateurCreation(nav.getUtilisateur());
              prixFilm.setIntervalle(mapIntervalle.get(IntervalleCombo.getSelectionModel().getSelectedItem()));
              prixFilm.setPrix(new BigDecimal(prixField.getText()));
              


                 prixFilmDAO.add(prixFilm);

                    nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.AJOUTER_ENREGISTREMENT);
                    
                  refrech ();
                  
                 return;
                   }else{
                
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_COORDONEES_EXISTE);  
            }

                  }
                  }
             
             else if (valeur.equals(Constantes.ADHESIF)){
                 
                     if(fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                        sousCatCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        sousCatCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        typeCategorieCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        typeCategorieCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        dimCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        dimCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        prixField.getText().equalsIgnoreCase("") 

                        ){
                  nav.showAlert(Alert.AlertType.ERROR, "Attention", null,Constantes.REMPLIR_CHAMPS);
                }else{
               
                
                 
                 Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
           Dimension dimension=mapDimension.get(dimCombo.getSelectionModel().getSelectedItem());
            CategorieMp categorieMp  = mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem());    
                      
                      
            
                          PrixAdhesif prixAdhesif=prixAdhesifDAO.findDimensionByPrix(dimension.getId(),fournisseur.getId(),categorieMp.getId() ); 

         
            
            if(prixAdhesif==null)
            {                 
                      
                         
                 prixAdhesif = new PrixAdhesif();
                 prixAdhesif.setSubCategorieMp(mapSubCategorieMp.get(sousCatCombo.getSelectionModel().getSelectedItem()));
                 prixAdhesif.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
                 prixAdhesif.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
                 prixAdhesif.setDimension(mapDimension.get(dimCombo.getSelectionModel().getSelectedItem()));
                 prixAdhesif.setUtilisateurCreation(nav.getUtilisateur());
                 prixAdhesif.setPrix(new BigDecimal(prixField.getText()));
                 
                  prixAdhesifDAO.add(prixAdhesif);
                 
                    nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
                    
                   refrech ();
                                  
                 }else{
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null,  Constantes.VERIFIER_COORDONEES_EXISTE);  
            }
                  }
             }else if (valeur.equals(Constantes.BOX_METAL)){
                
      
                      if(fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                        sousCatCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        sousCatCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        typeCategorieCombo.getSelectionModel().getSelectedIndex()== -1 || 
                        typeCategorieCombo.getSelectionModel().getSelectedItem().equals("")|| 
                        prixField.getText().equalsIgnoreCase("") 

                        ){
                  nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                }else{
          
       
            CategorieMp categorieMp  = mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem());    
            Dimension dimension=dimensionDAO.findDimensionByCode(Constantes.CODE_DIMENSION);
            Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
                         
                    PrixBoxMetal prixBoxMetalTmp =prixBoxMetalDAO.findDimensionByPrixBoxMetal(fournisseur.getId(),categorieMp.getId());
         
            
            if(prixBoxMetalTmp==null)
            {       
                     
                     
              prixBoxMetal = new PrixBoxMetal();
              prixBoxMetal.setSubCategorieMp(mapSubCategorieMp.get(sousCatCombo.getSelectionModel().getSelectedItem()));
              prixBoxMetal.setFournisseur(mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()));
              prixBoxMetal.setCategorieMp(mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem()));
              prixBoxMetal.setDimension(dimension);
              prixBoxMetal.setUtilisateurCreation(nav.getUtilisateur());
              prixBoxMetal.setPrix(new BigDecimal(prixField.getText()));
              

                 prixBoxMetalDAO.add(prixBoxMetal);

                    nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.AJOUTER_ENREGISTREMENT);
                    
                refrech ();
                 return;
                   }else{
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_COORDONEES_EXISTE);  
            }
                  
            }
             }
             else{
               nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.VERIFIER_AJOUTER_BOX_CARTON_FILMGOLD_FILMNORMAL_ADHESIF);
                      
            }
                    }else if(radioPrixArt.isSelected()) {
                    
                            if(
                        fourCombo.getSelectionModel().getSelectedItem().isEmpty() || 
                        prixField.getText().isEmpty() ||
                        remiseAchatField.getText().isEmpty() ||
                        remiseAvoirField.getText().isEmpty() ||
                        conditionnementField.getText().isEmpty() ||
                       ( radPack.isSelected()== false && 
                        radPiece.isSelected()== false) ||
                         ( radClientNon.isSelected()== false && 
                        radClientOui.isSelected()== false)            

                        ){
                  nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
                }else{
          
       
            Produit produit = mapProduit.get(artCombo.getSelectionModel().getSelectedItem());
            Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
               
                     String  client ="";  
                     
                       if (clientCombo.getSelectionModel().getSelectedIndex()!=-1){
                      client = clientCombo.getSelectionModel().getSelectedItem();          
                       }else{
                       client= Constantes.SANS;
                       }

                       String lieu="";
                       if (lieuLivraisonField.getText().equals("")){
                      lieu= Constantes.SANS;         
                       }else{
                       lieu = lieuLivraisonField.getText(); 
                       }
                    PrixOulmes prixOulmesTmp =prixOulmesDAO.findPrixOulmesByFourAndArt(fournisseur.getNom(),produit.getId(),client ,lieu );
         
            
            if(prixOulmesTmp==null)
            {       
                     
                     
              prixOulmes = new PrixOulmes();
              prixOulmes.setProduit(produit);
              prixOulmes.setFournisseur(fournisseur.getNom());
              prixOulmes.setUtilisateurCreation(nav.getUtilisateur());
              prixOulmes.setPrix(new BigDecimal(prixField.getText()));
              prixOulmes.setRemiseAchat(new BigDecimal(remiseAchatField.getText()));
              prixOulmes.setRemiseAvoir(new BigDecimal(remiseAvoirField.getText()));
              prixOulmes.setConditionnement(new BigDecimal(conditionnementField.getText()));
              
              if (!conditionnementCaisseField.getText().equals("")){
              prixOulmes.setConditionnementCaisse(new BigDecimal(conditionnementCaisseField.getText()));
              }
              
              if (radPack.isSelected()==true){
                  
                   prixOulmes.setTypeArticle(Constantes.PACK);
              
              }else if(radPiece.isSelected()==true){
                  
                   prixOulmes.setTypeArticle(Constantes.PIECE);
              
              }

              if (radClientOui.isSelected()==true){
              prixOulmes.setClient(client);
              prixOulmes.setLieuLivraison(lieuLivraisonField.getText());
              }else if (radClientNon.isSelected()==true){
              prixOulmes.setClient(Constantes.SANS);
              prixOulmes.setLieuLivraison(Constantes.SANS);
              }
              
                 prixOulmesDAO.add(prixOulmes);

                    nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.AJOUTER_ENREGISTREMENT);
                    
            refrech ();
                 
                 
                 return;
                   }else{
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.VERIFIER_COORDONEES_EXISTE);  
            }
                  
            }
                        
                    }else{
                    
                           nav.showAlert(Alert.AlertType.ERROR, "Attention", null,Constantes.REMPLIR_CHAMPS);
                        
                    }
    }

    void refrech (){
    
                 sousCatCombo.getSelectionModel().select(-1);
                 clientCombo.getSelectionModel().select(-1);
                 typeCategorieCombo.getSelectionModel().select(-1);
                 IntervalleCombo.getSelectionModel().select(-1);
                 TypeCarBoxCombo.getSelectionModel().select(-1);
                 dimCombo.getSelectionModel().select(-1);
                 grammageCombo.getSelectionModel().select(-1);
                 grammageFilmCombo.getSelectionModel().select(-1);
                 TypeFilmCombo.getSelectionModel().select(-1);
                 TypeCarCombo.getSelectionModel().select(-1);
                 prixField.clear();
                 remiseAchatField.clear();
                 remiseAvoirField.clear();
                 lieuLivraisonField.clear();
                 conditionnementField.clear();
                 conditionnementCaisseField.clear();
                 radPack.setSelected(false);
                 radPiece.setSelected(false);
                 radClientNon.setSelected(false);
                 radClientOui.setSelected(false);
                 artCombo.getSelectionModel().select(-1);
                 fourCombo.getSelectionModel().select(-1);

    }
    
    
    @FXML
    private void subCatigorieAction(ActionEvent event) {
 
        if( sousCatCombo.getSelectionModel().getSelectedIndex()!= -1){

           typeCategorieCombo.getItems().clear();
         SubCategorieMp SubCategorie = mapSubCategorieMp.get(sousCatCombo.getSelectionModel().getSelectedItem());
          
        
           List<CategorieMp> listeCategorieMp= new ArrayList<>();
           
               listeCategorieMp = categorieMpDAO.findCategorieMpByID(SubCategorie.getId());
               
        listeCategorieMp.stream().map((categorieMp) -> {
            typeCategorieCombo.getItems().addAll(categorieMp.getNom());
            return categorieMp;
        }).forEachOrdered((categorieMp) -> {
            mapCategorieMp.put(categorieMp.getNom(), categorieMp);
        });

        
          valeur= sousCatCombo.getSelectionModel().getSelectedItem();
             
                  
            if(!valeur.equals(Constantes.CARTON)&& !valeur.equals(Constantes.BOX) && !valeur.equals(Constantes.FILM_NORMAL) && !valeur.equals(Constantes.FILM_GOLD) && !valeur.equals(Constantes.ADHESIF) && !valeur.equals(Constantes.BOX_METAL)){
               grammageCombo.setDisable(true);
               IntervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               TypeCarBoxCombo.setDisable(true); 
               TypeCarCombo.setDisable(true); 
                prixField.setDisable(true);
                remiseAchatField.setDisable(true);
               remiseAvoirField.setDisable(true);
               conditionnementField.setDisable(true);
               conditionnementCaisseField.setDisable(true);
               clientCombo.setDisable(true);
               lieuLivraisonField.setDisable(true);
               radPack.setDisable(true);
               radPiece.setDisable(true);
               radClientNon.setDisable(true);
               radClientOui.setDisable(true);
                
            }   else{
                  
              if (valeur.equals(Constantes.CARTON)){
              
               grammageCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               TypeCarBoxCombo.setDisable(true);
               remiseAchatField.setDisable(true);
               remiseAvoirField.setDisable(true);
               conditionnementField.setDisable(true);
               conditionnementCaisseField.setDisable(true);
               clientCombo.setDisable(true);
               lieuLivraisonField.setDisable(true);
               radPack.setDisable(true);
               radPiece.setDisable(true);
               radClientNon.setDisable(true);
               radClientOui.setDisable(true);
               
               IntervalleCombo.setDisable(false);
               dimCombo.setDisable(false);
               TypeCarCombo.setDisable(false); 
               prixField.setDisable(false);
          

          }else if(valeur.equals(Constantes.BOX)){
              
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               TypeCarCombo.setDisable(true);
              remiseAchatField.setDisable(true);
               remiseAvoirField.setDisable(true);
               conditionnementField.setDisable(true);
               conditionnementCaisseField.setDisable(true);
               clientCombo.setDisable(true);
               lieuLivraisonField.setDisable(true);
               radPack.setDisable(true);
               radPiece.setDisable(true);
               radClientNon.setDisable(true);
               radClientOui.setDisable(true);
               
               grammageCombo.setDisable(false);
               IntervalleCombo.setDisable(false);
               dimCombo.setDisable(false);
               TypeCarBoxCombo.setDisable(false);
               prixField.setDisable(false);
                  
                  
          }else if (valeur.equals(Constantes.FILM_GOLD)){
             
             
             TypeCarBoxCombo.setDisable(true);
             grammageCombo.setDisable(true);
             TypeCarCombo.setDisable(true);
             remiseAchatField.setDisable(true);
             remiseAvoirField.setDisable(true);
             conditionnementField.setDisable(true);
               conditionnementCaisseField.setDisable(true);
               clientCombo.setDisable(true);
               lieuLivraisonField.setDisable(true);
               radPack.setDisable(true);
               radPiece.setDisable(true);
               radClientNon.setDisable(true);
               radClientOui.setDisable(true);
             
             dimCombo.setDisable(false);
             IntervalleCombo.setDisable(false);
             grammageFilmCombo.setDisable(false);
             TypeFilmCombo.setDisable(false);
             prixField.setDisable(false);
  
            }
          else if (valeur.equals(Constantes.FILM_NORMAL)){
             
             
             TypeCarBoxCombo.setDisable(true);
             grammageCombo.setDisable(true);
             TypeCarCombo.setDisable(true);
             remiseAchatField.setDisable(true);
             remiseAvoirField.setDisable(true);
             conditionnementField.setDisable(true);
               conditionnementCaisseField.setDisable(true);
               clientCombo.setDisable(true);
               lieuLivraisonField.setDisable(true);
               radPack.setDisable(true);
               radPiece.setDisable(true);
               radClientNon.setDisable(true);
               radClientOui.setDisable(true);
             
             dimCombo.setDisable(false);
             IntervalleCombo.setDisable(false);
             grammageFilmCombo.setDisable(false);
             TypeFilmCombo.setDisable(false);
             prixField.setDisable(false);
  
            }
          else if (valeur.equals(Constantes.ADHESIF)){
             
               grammageCombo.setDisable(true);
               IntervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               TypeCarBoxCombo.setDisable(true); 
               TypeCarCombo.setDisable(true); 
               remiseAchatField.setDisable(true);
               remiseAvoirField.setDisable(true);
               conditionnementField.setDisable(true);
               conditionnementCaisseField.setDisable(true);
               clientCombo.setDisable(true);
               lieuLivraisonField.setDisable(true);
               radPack.setDisable(true);
               radPiece.setDisable(true);
               radClientNon.setDisable(true);
               radClientOui.setDisable(true);
               
               dimCombo.setDisable(false);
               prixField.setDisable(false);
  
            }
          else if (valeur.equals(Constantes.BOX_METAL)){
             
               grammageCombo.setDisable(true);
               IntervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               TypeCarBoxCombo.setDisable(true); 
               TypeCarCombo.setDisable(true); 
               dimCombo.setDisable(true);
               remiseAchatField.setDisable(true);
               remiseAvoirField.setDisable(true);
               conditionnementField.setDisable(true);
               conditionnementCaisseField.setDisable(true);
               clientCombo.setDisable(true);
               lieuLivraisonField.setDisable(true);
               radPack.setDisable(true);
               radPiece.setDisable(true);
               radClientNon.setDisable(true);
               radClientOui.setDisable(true);
               
               prixField.setDisable(false);
  
            }
            }
        }
          }

    @FXML
    private void CatigorieAction(ActionEvent event) {
        
         if( typeCategorieCombo.getSelectionModel().getSelectedIndex()!= -1){
             
            dimCombo.getItems().clear();
         CategorieMp categorie = mapCategorieMp.get(typeCategorieCombo.getSelectionModel().getSelectedItem());
          

           List<Dimension> listeDimension=new ArrayList<>();
           
               listeDimension = dimensionDAO.findDimensionByFamille(categorie.getCode());
               
        listeDimension.stream().map((dimension) -> {
            dimCombo.getItems().addAll(dimension.getLibelle());
            return dimension;
        }).forEachOrdered((dimension) -> {
            mapDimension.put(dimension.getLibelle(), dimension);
        });
        
         }
    }


    @FXML
    private void btnRefrechOnAction(ActionEvent event) {
    }

    @FXML
    private void fourComboOnAction(ActionEvent event) {
    }

    @FXML
    private void artComboOnAction(ActionEvent event) {
        

               grammageCombo.setDisable(true);
               IntervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               TypeCarBoxCombo.setDisable(true); 
               TypeCarCombo.setDisable(true); 
               conditionnementCaisseField.setDisable(true);
               clientCombo.setDisable(true);
               lieuLivraisonField.setDisable(true);
               
               prixField.setDisable(false);
               remiseAchatField.setDisable(false);
               remiseAvoirField.setDisable(false);
               conditionnementField.setDisable(false);
               radPack.setDisable(false);
               radPiece.setDisable(false);
               radClientNon.setDisable(false);
               radClientOui.setDisable(false);
               
    }

    @FXML
    private void radioPrixMpOnAction(ActionEvent event) {
        
               fourCombo.setDisable(false);
               artCombo.setDisable(true);
               typeCategorieCombo.setDisable(false);
               sousCatCombo.setDisable(false);
            
               conditionnementCaisseField.setDisable(true);
               clientCombo.setDisable(true);
               lieuLivraisonField.setDisable(true);
               grammageCombo.setDisable(true);
               IntervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               TypeCarBoxCombo.setDisable(true); 
               TypeCarCombo.setDisable(true); 
               prixField.setDisable(true);
               remiseAchatField.setDisable(true);
               remiseAvoirField.setDisable(true);
               conditionnementField.setDisable(true);
               radPack.setDisable(true);
               radPiece.setDisable(true);
               radClientNon.setDisable(true);
               radClientOui.setDisable(true);
               
            
            refrech ();
        
    }

    @FXML
    private void radioPrixArtOnAction(ActionEvent event) {
     
               fourCombo.setDisable(false);
               artCombo.setDisable(false);
            
               typeCategorieCombo.setDisable(true);
               sousCatCombo.setDisable(true);
               clientCombo.setDisable(true);
               lieuLivraisonField.setDisable(true);
               grammageCombo.setDisable(true);
               IntervalleCombo.setDisable(true);
               TypeFilmCombo.setDisable(true);
               grammageFilmCombo.setDisable(true);
               dimCombo.setDisable(true);
               TypeCarBoxCombo.setDisable(true); 
               TypeCarCombo.setDisable(true); 
               prixField.setDisable(true);
               remiseAchatField.setDisable(true);
               remiseAvoirField.setDisable(true);
               conditionnementField.setDisable(true);
               conditionnementCaisseField.setDisable(true);
               radPack.setDisable(true);
               radPiece.setDisable(true);
               radClientNon.setDisable(true);
               radClientOui.setDisable(true);
               
               refrech ();
    }

    @FXML
    private void radPieceOnAction(ActionEvent event) {
        
        conditionnementCaisseField.setDisable(true);
        conditionnementCaisseField.clear();
        
    }

    @FXML
    private void radPackOnAction(ActionEvent event) {
        
        conditionnementCaisseField.setDisable(false);
        
    }

    @FXML
    private void clientOnAction(ActionEvent event) {
        
    }

    @FXML
    private void radClientOuiOnAction(ActionEvent event) {
        
        clientCombo.setDisable(false);
        lieuLivraisonField.setDisable(false);
        
    }

    @FXML
    private void radClientNonOnAction(ActionEvent event) {
        
         clientCombo.setDisable(true);
         lieuLivraisonField.setDisable(true);
        clientCombo.getSelectionModel().select(-1);
        lieuLivraisonField.clear();
    }
}
