/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.commande;

import Utils.Constantes;
import dao.Entity.CommandeRegion;
import dao.Entity.DetailCommandeRegion;
import dao.Entity.MatierePremier;
import dao.Entity.PrixOulmes;
import dao.Entity.Produit;
import dao.Entity.Sequenceur;
import dao.Manager.CommandeRegionDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.ProduitDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.CommandeRegionDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.ProduitDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SaisirCommandeRegionOulmesController implements Initializable {

    @FXML
    private TextField nCommandeField;
    @FXML
    private TextField etatField;
    @FXML
    private TableView<DetailCommandeRegion> detailCommandetable;
    @FXML
    private TableColumn<DetailCommandeRegion, String> libelleColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, Integer> quantiteColumn;
    @FXML
    private TableColumn<DetailCommandeRegion, String> codeArtColumn;
    @FXML
    private TextField quantiteField;
    @FXML
    private DatePicker dateSaisie;
    @FXML
    private Label qteAfficchage;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private TextField codeArtField;
    @FXML
    private ComboBox<String> LibelleCombo;
    @FXML
    private Button btnValiderTout;
    @FXML
    private Button validerSaisie;
    @FXML
    private TextField numMatriculeField;
    @FXML
    private DatePicker dateCharg;
    @FXML
    private Button imprimerSaisie;
    /**
     * Initializes the controller class.
     */
    
    MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
    CommandeRegionDAO commandeRegionDAO = new CommandeRegionDAOImpl(); 
    ProduitDAO produitDAO = new ProduitDAOImpl();
    PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
    SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
    MatierePremiereDAO matierePremierDAO = new MatierePremierDAOImpl();  
    
    
    private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
    
    private final ObservableList<DetailCommandeRegion> listeDetailCommandeRegion=FXCollections.observableArrayList();

  
    PrixOulmes prixOulmes=new PrixOulmes();
    CommandeRegion commandeRegion = new CommandeRegion();
    
   
         
      
       navigation nav = new navigation();
    
            Date date = new  Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yy");
    
    
    
       
        void setColumnProperties(){
         
          codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeRegion, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
                }
             });
          
          
          libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeRegion, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeRegion, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
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
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.COMMANDE_REGION_PF);
          nCommandeField.setText(Constantes.COMMANDE_REGION_PF+(sequenceur.getValeur()+1));

   }
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           Incrementation();

        etatField.setText(Constantes.ETAT_COMMANDE_LANCE_REGION);
        
        
              List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findPrixOulmes();

        listPrixOulmes.stream().map((prixOulmes) -> {
            LibelleCombo.getItems().addAll(prixOulmes.getProduit().getLibelle());
            return prixOulmes;
        }).forEachOrdered((prixOulmes) -> {
            mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
        });

          setColumnProperties();    
          
          validerSaisie.setDisable(true);
        
    }    

    @FXML
    private void qteByIntervalleOnAction(KeyEvent event) {
    }

    @FXML
    private void ajouterSaisieAction(ActionEvent event) {
        
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
           DetailCommandeRegion detailCommandeRegion = new DetailCommandeRegion();

     
       MatierePremier matierePremier = matierePremierDAO.findByCode(Constantes.CODE_MP);

            Produit produit = produitDAO.findByCode(codeArtField.getText());
 
       prixOulmes = prixOulmesDAO.findPrixOulmesByArt(produit.getId(), Constantes.SANS, Constantes.SANS);

                if(codeArtField.getText().equalsIgnoreCase("")|| 
                   LibelleCombo.getValue().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("")


                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
     }else {
              
                         

             detailCommandeRegion.setMatierePremier(matierePremier);
             detailCommandeRegion.setPrixOulmes(prixOulmes);
             detailCommandeRegion.setCmdRegle(Constantes.ETAT_CMNR);
             detailCommandeRegion.setQuantite(new BigDecimal(quantiteField.getText()).setScale(2));
             detailCommandeRegion.setQuantiteRestee(new BigDecimal(quantiteField.getText()).setScale(2));
             detailCommandeRegion.setQuantiteRecu(BigDecimal.ZERO.setScale(2));
             detailCommandeRegion.setQuantiteLivree(BigDecimal.ZERO.setScale(2));
             detailCommandeRegion.setCommandeRegion(commandeRegion);
             detailCommandeRegion.setPrixUnitaire(BigDecimal.ZERO.setScale(2));
             detailCommandeRegion.setRemiseAchat(prixOulmes.getRemiseAchat());
             detailCommandeRegion.setEtat(Constantes.ETAT_AFFICHAGE);
             detailCommandeRegion.setUtilisateurCreation(nav.getUtilisateur());
             detailCommandeRegion.setDimension(matierePremier.getDimension());
        

            listeDetailCommandeRegion.add(detailCommandeRegion);

            setColumnProperties();
            loadDetail();
            clear();
            
}
        
            }
              
        
    }

     private void clear(){

 codeArtField.clear();
     LibelleCombo.getSelectionModel().select(-1);
    quantiteField.clear();
    
        qteAfficchage.setText("");

    }
    
    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
        
           if (event.getCode().equals(KeyCode.ENTER) )
            {
                         prixOulmes = prixOulmesDAO.findPrixOulmesByCodeArt(codeArtField.getText());

                         LibelleCombo.setValue(prixOulmes.getProduit().getLibelle());
            }

    }

    @FXML
    private void LibelleComboOnAction(ActionEvent event) {
        
                 PrixOulmes prixOulmes  = mapPrixOulmes.get(LibelleCombo.getSelectionModel().getSelectedItem());
         
          if(prixOulmes!=null){
              
         codeArtField.setText(prixOulmes.getProduit().getCode());
         
          }
        
    }

    @FXML
    private void ValiderToutCommande(ActionEvent event) {
        
           clear();
        
    }

    @FXML
    private void validerSaisieAction(ActionEvent event) throws ParseException {
        
              
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        
         if( detailCommandetable.getItems().size() ==0 ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
     }
        else {

              Date dateChargement= null;
          if (dateCharg.getValue()!= null){
          
            dateChargement=new SimpleDateFormat("yyyy-MM-dd").parse(dateCharg.getValue().toString());
          }
          
          String numMat  = null;
           if (numMatriculeField.getText()!= null){
          
            numMat=numMatriculeField.getText();
          }
             
          Date dateS=new SimpleDateFormat("yyyy-MM-dd").parse(dateSaisie.getValue().toString());
            
        commandeRegion.setUtilisateurCreation(nav.getUtilisateur());
        commandeRegion.setDepot(nav.getUtilisateur().getDepot());
        commandeRegion.setDateSaisie(new Date());
        commandeRegion.setDateCreation(dateS);
        commandeRegion.setEtat(Constantes.ETAT_COMMANDE_LANCE_REGION);
        commandeRegion.setTypeCommande(Constantes.COMMANDE_INTERNE_ART);
        commandeRegion.setnCommande(nCommandeField.getText());
        commandeRegion.setDetailCommandeRegion(listeDetailCommandeRegion);
        commandeRegion.setnMatricule(numMat);
        commandeRegion.setDateChargement(dateChargement);
        
        commandeRegionDAO.add(commandeRegion);

        commandeRegion = new CommandeRegion();
        
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
 
        

     Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.COMMANDE_REGION_PF);
           sequenceur.setValeur(sequenceur.getValeur()+1);
           sequenceurDAO.edit(sequenceur);
           Incrementation ();

          
        detailCommandetable.getItems().clear();
        }
        
    }
        
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) throws ParseException {
        
          try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SaisirCommandeRegionOulmesController.class.getResource(nav.getiReportBonCommandeOulmesRegion()));
        
            para.put("NumCommande",nCommandeField.getText());
        

            
            Date dateS=new SimpleDateFormat("yyyy-MM-dd").parse(dateSaisie.getValue().toString());
            para.put("DateLiv",dateS);
    
            if (numMatriculeField.getText()!=null){
                
                 para.put("Matricule","Num Matricule:");
            para.put("NumMatricule",numMatriculeField.getText());
            
            
            }
            
            if (dateCharg.getValue()!=null){

            Date dateC=new SimpleDateFormat("yyyy-MM-dd").parse(dateCharg.getValue().toString());
                
            para.put("Chargement","Date Chargement:");
            para.put("DateChargement",dateC);
            }
            
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeDetailCommandeRegion));
               JasperViewer.viewReport(jp, false);
               
                validerSaisie.setDisable(false);
            
        } catch (JRException ex) {
            Logger.getLogger(SaisirCommandeRegionOulmesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
