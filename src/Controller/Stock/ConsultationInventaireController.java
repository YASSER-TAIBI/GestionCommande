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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ConsultationInventaireController implements Initializable {

    @FXML
    private DatePicker dateDebutInv;
    @FXML
    private DatePicker dateFinInv;
    @FXML
    private TextField codeMPField;
    @FXML
    private Button btnRefreshGlobal;
    @FXML
    private TableView<Inventaire> inventaireTable;
    @FXML
    private TableColumn<Inventaire, String> codeArtColumn;
    @FXML
    private TableColumn<Inventaire, String> libelleColumn;
    @FXML
    private TableColumn<Inventaire, Date> dateInvColumn;
    @FXML
    private TableColumn<Inventaire, BigDecimal> quantiteColumn;
    @FXML
    private TableColumn<Inventaire, BigDecimal> prixColumn;
    @FXML
    private TableColumn<Inventaire, BigDecimal> montantColumn;

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
        
        loadDetail();
        setColumnPropertiesList();
        
    }    

     void loadDetail(){

        listInventaire.clear();   
        listInventaire.addAll(inventaireDAO.findAll());
        inventaireTable.setItems(listInventaire);
    }
    
      void setColumnPropertiesList(){

                codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inventaire, String>, ObservableValue<String>>() {
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
           

                dateInvColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inventaire, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<Inventaire, Date> p) {
                    
                
                    return new ReadOnlyObjectWrapper(p.getValue().getDateInventaire());
                }
                
             });
                
                prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Inventaire, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<Inventaire, BigDecimal> p) {
                    
                
                    return new ReadOnlyObjectWrapper(p.getValue().getPrix());
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
    private void ajouterMouseClicked(MouseEvent event) {
        
           if (codeMPField.getText().equals("MP_") &&
                   dateDebutInv.getValue()== null &&
                   dateFinInv.getValue()== null 

                   ){
 
                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                
            } else{
           
             String req = "";
         
        if(!codeMPField.getText().equals("MP_")) {
             
              req= req+" and c.matierePremier.code='"+codeMPField.getText()+"'";
              
             }
             
      
          if(dateDebutInv.getValue()!= null && dateFinInv.getValue()!= null){
             
               LocalDate localDate=dateDebutInv.getValue();
                    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String dateDebut = localDate.format(formatters);

                    localDate=dateFinInv.getValue();
                    String dateFin = localDate.format(formatters);

            if(dateFinInv.getValue().compareTo(dateDebutInv.getValue())<0){
            
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
            return;
            }else{
            
             req= req+" and c.dateInventaire BETWEEN '"+dateDebut+"' AND '"+dateFin+"'";

             }
        }else if (dateDebutInv.getValue()!= null && dateFinInv.getValue()== null){
        
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_DATE);
            return;
        
        }else if (dateDebutInv.getValue()== null && dateFinInv.getValue()!= null){
        
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_DATE);
            return;
        
        }

                 listInventaire.clear();
             
            listInventaire.addAll(inventaireDAO.findByAllInventaire(req));

        inventaireTable.setItems(listInventaire); 
        
        setColumnPropertiesList();

             
           }
        
        
    }

    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
    }

     void clear(){
    
       codeMPField.setText("MP_");

            dateDebutInv.setValue(null);
            dateFinInv.setValue(null);
            
            loadDetail();
           setColumnPropertiesList();

        
    }
    
    @FXML
    private void refreshGlobal(ActionEvent event) {
        
        clear();
    }
    
}
