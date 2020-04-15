/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * 
and open the template in the editor.
 */
package Controller.commande;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.Commande;
import dao.Entity.CommandeRegion;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCommandeRegion;
import dao.Entity.Dimension;
import dao.Entity.Fournisseur;
import dao.Entity.Grammage;
import dao.Entity.GrammageFilm;
import dao.Entity.Intervalle;
import dao.Entity.MatierePremier;
import dao.Entity.Sequenceur;
import dao.Entity.TypeCarton;
import dao.Entity.TypeCartonBox;
import dao.Entity.TypeFilm;
import dao.Manager.ClientMPDAO;
import dao.Manager.CommandeDAO;
import dao.Manager.CommandeRegionDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.DetailCommandeRegionDAO;
import dao.Manager.DimensionDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.GrammageDAO;
import dao.Manager.GrammageFilmDAO;
import dao.Manager.IntervalleDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixAdhesifDAO;
import dao.Manager.PrixBoxDAO;
import dao.Manager.PrixCartonDAO;
import dao.Manager.PrixDimFourDAO;
import dao.Manager.PrixFilmDAO;
import dao.Manager.SequenceurDAO;
import dao.Manager.TypeCartonBoxDAO;
import dao.Manager.TypeCartonDAO;
import dao.Manager.TypeFilmDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CommandeDAOImpl;
import dao.ManagerImpl.CommandeRegionDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.DetailCommandeRegionDAOImpl;
import dao.ManagerImpl.DimensionDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.GrammageDAOImpl;
import dao.ManagerImpl.GrammageFilmDAOImpl;
import dao.ManagerImpl.IntervalleDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixAdhesifDAOImpl;
import dao.ManagerImpl.PrixBoxDAOImpl;
import dao.ManagerImpl.PrixCartonDAOImpl;
import dao.ManagerImpl.PrixDimFourDAOImpl;
import dao.ManagerImpl.PrixFilmDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import dao.ManagerImpl.TypeCartonBoxDAOImpl;
import dao.ManagerImpl.TypeCartonDAOImpl;
import dao.ManagerImpl.TypeFilmDAOImpl;
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
public class SaisirCommandeRegionController implements Initializable {

    @FXML
    private TextField nCommandeField;
    @FXML
    private TextField etatField;
    @FXML    
    private TableView<DetailCommandeRegion> detailCommandetable;
    @FXML    
    private TableColumn<DetailCommandeRegion, String> codeMPColumn;
    @FXML    
    private TableColumn<DetailCommandeRegion, String> libelleColumn;
    @FXML    
    private TableColumn<DetailCommandeRegion, Integer> quantiteColumn;
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField codeMPField;
    @FXML
    private DatePicker dateSaisie;
    @FXML
    private TextField libelleField;
    @FXML
    private Label qteAfficchage;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private Button btnValiderTout;
    @FXML
    private Button validerSaisie;

    private Map<String,ClientMP> mapClientMP=new HashMap<>();
    private Map<String,Dimension> mapDimension=new HashMap<>();
    private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
    private Map<String,Grammage> mapGrammage=new HashMap<>();
    private Map<String,TypeCartonBox> mapTypeCarton=new HashMap<>();
    private Map<String,TypeFilm> mapTypeFilm=new HashMap<>();
    private Map<String,GrammageFilm> mapGrammageFilm=new HashMap<>();
    private Map<String,TypeCarton> mapTypeCar=new HashMap<>();
    private Map<String,Intervalle> mapIntervalle=new HashMap<>();
    
    FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    DetailCommandeRegionDAO detailCommandeRegionDAO = new DetailCommandeRegionDAOImpl(); 
    MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
    DimensionDAO dimensionDAO = new DimensionDAOImpl();
    CommandeRegionDAO commandeRegionDAO = new CommandeRegionDAOImpl(); 
    PrixDimFourDAO prixDimFourDAO = new PrixDimFourDAOImpl();
    GrammageDAO grammageDAO = new GrammageDAOImpl();
    TypeCartonBoxDAO typeCartonBoxDAO = new TypeCartonBoxDAOImpl();
    PrixBoxDAO prixBoxDAO = new PrixBoxDAOImpl();
    PrixAdhesifDAO prixAdhesifDAO = new PrixAdhesifDAOImpl();
    PrixCartonDAO prixCartonDAO =new PrixCartonDAOImpl();
    PrixFilmDAO prixFilmDAO =new  PrixFilmDAOImpl();
    TypeFilmDAO typeFilmDAO = new  TypeFilmDAOImpl();
    GrammageFilmDAO grammageFilmDAO = new GrammageFilmDAOImpl();
    TypeCartonDAO typeCartonDAO = new TypeCartonDAOImpl();
    IntervalleDAO intervalleDAO = new IntervalleDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
      
    private final ObservableList<DetailCommandeRegion> listeDetailCommandeRegion=FXCollections.observableArrayList();

  
    MatierePremier matierePremiere=new MatierePremier();
    CommandeRegion commandeRegion = new CommandeRegion();
    
   
         
      
       navigation nav = new navigation();
    
            Date date = new  Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yy");
       
 
  
    
     void setColumnProperties(){
         
          codeMPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeRegion, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
          
          
          libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeRegion, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });

          quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<DetailCommandeRegion, Integer> p) {
                    
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

        detailCommandetable.setItems(listeDetailCommandeRegion);
    }
    
    void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.COMMANDE_REGION);
          nCommandeField.setText(Constantes.COMMANDE_REGION+(sequenceur.getValeur()+1));

   }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           Incrementation();

        etatField.setText(Constantes.ETAT_COMMANDE_LANCE_REGION);
        
          quantiteField.setDisable(true);
          dateSaisie.setDisable(true);
          detailCommandetable.setEditable(true);
          quantiteColumn.setEditable(true);

          setColumnProperties();    
        
    }    


    @FXML
    private void qteByIntervalleOnAction(KeyEvent event) {
     
    }

    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
       
        if (event.getCode().equals(KeyCode.ENTER))
            {
              
            
                         matierePremiere = matierePremiereDAO.findByCode(codeMPField.getText());
                libelleField.setText(matierePremiere.getNom());
       
                    quantiteField.setDisable(false);
                    dateSaisie.setDisable(false);
            }
    }

    @FXML
    private void ajouterSaisieAction(ActionEvent event) {
           DetailCommandeRegion detailCommandeRegion = new DetailCommandeRegion();

     
        matierePremiere = matierePremiereDAO.findByCode(codeMPField.getText());
 
     

                if(codeMPField.getText().equalsIgnoreCase("")|| 
                   libelleField.getText().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("")


                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
              
                         

             detailCommandeRegion.setMatierePremier(matierePremiere);
             detailCommandeRegion.setCmdRegle(Constantes.ETAT_CMNR);
             detailCommandeRegion.setQuantite(new BigDecimal(quantiteField.getText()).setScale(2));
             detailCommandeRegion.setQuantiteRestee(new BigDecimal(quantiteField.getText()).setScale(2));
             detailCommandeRegion.setQuantiteRecu(BigDecimal.ZERO.setScale(2));
             detailCommandeRegion.setQuantiteLivree(BigDecimal.ZERO.setScale(2));
             detailCommandeRegion.setCommandeRegion(commandeRegion);
             detailCommandeRegion.setPrixUnitaire(BigDecimal.ZERO.setScale(2));
             detailCommandeRegion.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeRegion.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeRegion.setDimension(matierePremiere.getDimension());
        

            listeDetailCommandeRegion.add(detailCommandeRegion);

            setColumnProperties();
            loadDetail();
            clear();
            
}
        
         
              
}
       
    

     private void clear(){

        codeMPField.setText("MP_");
        libelleField.clear();
        quantiteField.clear();
    
        qteAfficchage.setText("");

    }
    
    @FXML
    private void ValiderToutCommande(ActionEvent event) {
      clear();
    }

    
    
    @FXML
    private void validerSaisieAction(ActionEvent event) throws ParseException {
          
         if( detailCommandetable.getItems().size() ==0 ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
     }
        else {

            LocalDate localDate=dateSaisie.getValue();
            
          Date dateSaisie=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
            
        commandeRegion.setUtilisateurCreation(nav.getUtilisateur());
        commandeRegion.setDepot(nav.getUtilisateur().getDepot());
        commandeRegion.setDateSaisie(new Date());
        commandeRegion.setDateCreation(dateSaisie);
        commandeRegion.setEtat(Constantes.ETAT_COMMANDE_LANCE_REGION);
        commandeRegion.setnCommande(nCommandeField.getText());
        commandeRegion.setDetailCommandeRegion(listeDetailCommandeRegion);

        
        commandeRegionDAO.add(commandeRegion);

        commandeRegion = new CommandeRegion();
        
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
 
        

     Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.COMMANDE_REGION);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();

          
        detailCommandetable.getItems().clear();
        }
        
    }
    
}
