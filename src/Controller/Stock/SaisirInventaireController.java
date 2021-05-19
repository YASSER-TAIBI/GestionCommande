/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.Inventaire;
import dao.Entity.MatierePremier;
import dao.Manager.InventaireDAO;
import dao.Manager.MatierePremiereDAO;
import dao.ManagerImpl.InventaireDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SaisirInventaireController implements Initializable {

    @FXML
    private DatePicker dateInventaire;
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField prixField;
    @FXML
    private Button ajouterSaisie;
    @FXML
    private Button btnRefreshGlobal;
    @FXML
    private TableView<Inventaire> inventaireTable;
    @FXML
    private TableColumn<Inventaire, String> codeColumn;
    @FXML
    private TableColumn<Inventaire, String> libelleColumn;
    @FXML
    private TableColumn<Inventaire, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<Inventaire, BigDecimal> prixColumn;
    @FXML
    private TableColumn<Inventaire, BigDecimal> montantColumn;
    @FXML
    private TextField codeMPField;
    @FXML
    private TextField libelleField;
   

    /**
     * Initializes the controller class.
     */
     MatierePremier matierePremiere=new MatierePremier();
    MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
    InventaireDAO inventaireDAO = new InventaireDAOImpl();
    private final ObservableList<Inventaire> listInventaire=FXCollections.observableArrayList();
    
    navigation nav = new navigation();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   void setColumnPropertiesList(){

                codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inventaire, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Inventaire, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
               

                libelleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inventaire, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Inventaire, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });

                quantiteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inventaire, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<Inventaire, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQte()));
                }
                
             });
           

                prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inventaire, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<Inventaire, BigDecimal> p) {
                    
                
                    return new ReadOnlyObjectWrapper(p.getValue().getPrix().setScale(6,RoundingMode.FLOOR));
                }
                
             });

           
                montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inventaire, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<Inventaire, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
             

      }
    
    
    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
        
           
        if (event.getCode().equals(KeyCode.ENTER))
            {

                         matierePremiere = matierePremiereDAO.findByCode(codeMPField.getText());
   
                libelleField.setText(matierePremiere.getNom());
       
             
            }
    }


    @FXML
    private void ajouterMouseClicked(MouseEvent event) throws ParseException {
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                     if(codeMPField.getText().equalsIgnoreCase("")|| 
                   libelleField.getText().equalsIgnoreCase("") || 
                   quantiteField.getText().equalsIgnoreCase("")

                   ){

         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,  Constantes.REMPLIR_CHAMPS);
         
    }
else {
              
                     LocalDate localDate=dateInventaire.getValue();
            
          Date dateInve=new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
                               
                               Inventaire inventaire = new Inventaire();
                
  
                inventaire.setPrix(new BigDecimal(prixField.getText()));

                inventaire.setQte(new BigDecimal(quantiteField.getText()));
                
                BigDecimal  montant= (inventaire.getQte().multiply(inventaire.getPrix())).setScale(2,RoundingMode.FLOOR);
                
                inventaire.setMontant(montant);
                

             inventaire.setMatierePremier(matierePremiere);
             inventaire.setDateInventaire(dateInve);
             inventaire.setUtilisateurCreation(nav.getUtilisateur());
             inventaire.setDateCreation(new Date());
             listInventaire.add(inventaire);
             
               inventaireTable.setItems(listInventaire);
          
             codeMPField.setText("MP_");
             libelleField.clear(); 
             quantiteField.clear();
//             dateInventaire.setValue(null);
             prixField.clear();
             
            setColumnPropertiesList();
         
}
            }
        
    }

    void clear(){
    
       codeMPField.setText("MP_");
             libelleField.clear(); 
             quantiteField.clear();
            dateInventaire.setValue(null);
             prixField.clear();
        
    }
    
    
    @FXML
    private void ajouterSaisieAction(ActionEvent event) throws ParseException {
        
        
            
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
        if( inventaireTable.getItems().isEmpty() || dateInventaire.getValue()== null ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
     }
        else {
          
               

            for (int i = 0; i < listInventaire.size(); i++) {
                
                 Inventaire inventaire = new Inventaire();
                
                Inventaire inventaireTmp = listInventaire.get(i);
                
             inventaire.setPrix(inventaireTmp.getPrix());
             inventaire.setQte(inventaireTmp.getQte());
             inventaire.setMontant(inventaireTmp.getMontant());
             inventaire.setMatierePremier(inventaireTmp.getMatierePremier());
             inventaire.setDateInventaire(inventaireTmp.getDateInventaire());
             inventaire.setUtilisateurCreation(inventaireTmp.getUtilisateurCreation());
             inventaire.setDateCreation(inventaireTmp.getDateCreation());
             
             inventaireDAO.add(inventaire);
                
                
            }
          
            listInventaire.clear();
            
         nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.AJOUTER_ENREGISTREMENT);
 
         clear();

        }

        }

        
        
    }

    @FXML
    private void refreshGlobal(ActionEvent event) {
        
        clear();
        
    }
    
}
