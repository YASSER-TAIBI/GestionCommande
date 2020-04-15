/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.RetourGratuite;


import Utils.Constantes;
import dao.Entity.DetailBonRetour;
import dao.Entity.DetailCommande;
import dao.Entity.Fournisseur;
import dao.Entity.ManqueMP;
import dao.Entity.MatierePremier;
import dao.Manager.DetailBonRetourDAO;
import dao.Manager.FournisseurDAO;
import dao.ManagerImpl.DetailBonRetourDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
public class ConsultationManqueController implements Initializable {

    @FXML
    private TableView<ManqueMP> tableStock;
    @FXML
    private TableColumn<ManqueMP, String> codeArtColumn;
    @FXML
    private TableColumn<ManqueMP, String> designationColumn;
    @FXML
    private TableColumn<ManqueMP, BigDecimal> montantColumn;
    @FXML
    private TableColumn<ManqueMP, String> nManqueColumn;
    @FXML
    private TableColumn<ManqueMP, Date> dateCreationColumn;
    @FXML
    private TableColumn<ManqueMP, String> fournisseurColumn;
    @FXML
    private TableColumn<ManqueMP, String> etatColumn;
    @FXML
    private TextField codeMpRechField;
    @FXML
    private TextField nManqueRechField;
    @FXML
    private DatePicker dateDebutManque;
    @FXML
    private DatePicker dateFinManque;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> etatCombo;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    

    /**
     * Initializes the controller class.
     */
    
      private final ObservableList<ManqueMP> listeManqueMP=FXCollections.observableArrayList();  
     DetailBonRetourDAO detailBonRetourDAO = new DetailBonRetourDAOImpl();
    
     navigation nav = new navigation();
     DetailBonRetour detailBonRetourArt = new DetailBonRetour();

     private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
     FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
     
    ObservableList<String> etat =FXCollections.observableArrayList(Constantes.ETAT_PAYEE,Constantes.RETOUR_EN_ATT_REGLEMENT,Constantes.ETAT_ANNULE);
    
    void ManqueGlobal(){
    
       List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByManque();

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
//====================================================================== Manque Global par MP ===========================================================================================================================================================================================================
        
       ManqueGlobal();
            
//============================================================================================================================================================================================================================================================================================           
    
      List<Fournisseur> listFournisseur=fournisseurDAO.findAll();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });

        etatCombo.setItems(etat);
    
    }    

      void setColumnProperties(){
        
      codeArtColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ManqueMP, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ManqueMP, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
   
      designationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ManqueMP, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ManqueMP, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });
      
      montantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ManqueMP, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<ManqueMP, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
             });
      
      
      dateCreationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ManqueMP, Date>, ObservableValue<Date>>() {
                @Override
                public ObservableValue<Date> call(TableColumn.CellDataFeatures<ManqueMP, Date> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getDateCreation());
                }
             });
      
      nManqueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ManqueMP, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ManqueMP, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumManque());
                }
             });

      etatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ManqueMP, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ManqueMP, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getEtat());
                }
             });
      
      fournisseurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ManqueMP, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ManqueMP, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFour());
                }
             });
      
    }
    
      
      
    @FXML
    private void afficherDetailAndStockProdOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void recherchMouseClicked(MouseEvent event) throws ParseException {
        
           if ( codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS_DATE);
     }

        else if( !codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeMp(codeMpRechField.getText());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
     } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByNmanque(nManqueRechField.getText());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
   } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
       if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
       
       
       
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByDate(dateOperaDebut,dateOperaFin );

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
     } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
         String etatMnq = etatCombo.getSelectionModel().getSelectedItem();
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByEtat(etatMnq);

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
     } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
        Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByFour(fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
      } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
      
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndNmnq(codeMpRechField.getText(),nManqueRechField.getText());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
  } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
      if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndDate(codeMpRechField.getText(),dateOperaDebut,dateOperaFin);

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
    } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
          String etatMnq = etatCombo.getSelectionModel().getSelectedItem();
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndEtat(codeMpRechField.getText(),etatMnq);

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
    } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
          Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndFour(codeMpRechField.getText(),fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
    } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 


         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndDate(nManqueRechField.getText(),dateOperaDebut,dateOperaFin);

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
    } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
           String etatMnq = etatCombo.getSelectionModel().getSelectedItem();
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByManqueAndEtat(nManqueRechField.getText(),etatMnq);

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
    } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
          Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByManqueAndFour(nManqueRechField.getText(),fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
      } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 

               Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByDateAndFour(dateOperaDebut,dateOperaFin,fournisseur.getNom() );

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
    } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 

                 String etatMnq = etatCombo.getSelectionModel().getSelectedItem();
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByDateAndEtat(dateOperaDebut,dateOperaFin,etatMnq);

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
   } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
                 Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
                 
                 String etatMnq = etatCombo.getSelectionModel().getSelectedItem();
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByEtatAndFour(etatMnq,fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
     } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 

         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndManqueAndDate(codeMpRechField.getText(),nManqueRechField.getText(),dateOperaDebut,dateOperaFin);

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
   } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
      
   String etatMnq = etatCombo.getSelectionModel().getSelectedItem();
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndManqueAndEtat(codeMpRechField.getText(),nManqueRechField.getText(),etatMnq);

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
  } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
      
       Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndManqueAndFour(codeMpRechField.getText(),nManqueRechField.getText(),fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
  } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 

                String etatMnq = etatCombo.getSelectionModel().getSelectedItem();
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndDateAndEtat(codeMpRechField.getText(),dateOperaDebut,dateOperaFin,etatMnq);

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
  } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()==-1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 

             Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()); 
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndDateAndFour(codeMpRechField.getText(),dateOperaDebut,dateOperaFin,fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
  } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()!=-1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 

             String etatMnq = etatCombo.getSelectionModel().getSelectedItem(); 
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByManqueAndDateAndEtat(nManqueRechField.getText(),dateOperaDebut,dateOperaFin,etatMnq);

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
 } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()==-1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 

             Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()); 
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByManqueAndDateAndFour(nManqueRechField.getText(),dateOperaDebut,dateOperaFin,fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
  } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!=-1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
                String etatMnq = etatCombo.getSelectionModel().getSelectedItem();

             Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()); 
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByManqueAndEtatAndFour(nManqueRechField.getText(),etatMnq,fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
  } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()!=-1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
                String etatMnq = etatCombo.getSelectionModel().getSelectedItem();

             Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()); 
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByDateAndEtatAndFour(dateOperaDebut,dateOperaFin,etatMnq,fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
  } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()!=-1 && fourCombo.getSelectionModel().getSelectedIndex()== -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
                String etatMnq = etatCombo.getSelectionModel().getSelectedItem();
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndManqueAndDateAndEtat(codeMpRechField.getText(),nManqueRechField.getText(),dateOperaDebut,dateOperaFin,etatMnq);

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
 } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()==-1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
                Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndManqueAndDateAndFour(codeMpRechField.getText(),nManqueRechField.getText(),dateOperaDebut,dateOperaFin,fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
  } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()!=-1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
                Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());

                String etatMnq = etatCombo.getSelectionModel().getSelectedItem();
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndDateAndEtatAndFour(codeMpRechField.getText(),dateOperaDebut,dateOperaFin,etatMnq,fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
   } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()== null && dateFinManque.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!=-1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
       
                Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());

                String etatMnq = etatCombo.getSelectionModel().getSelectedItem();
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndEtatAndFourAndManque(codeMpRechField.getText(),etatMnq,fournisseur.getNom(),nManqueRechField.getText());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
    } else if(codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()!=-1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
              
                Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());

                String etatMnq = etatCombo.getSelectionModel().getSelectedItem();
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByManqueAndDateAndEtatAndFour(nManqueRechField.getText(),dateOperaDebut,dateOperaFin,etatMnq,fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
   } else if(!codeMpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)&& !nManqueRechField.getText().equalsIgnoreCase("") && dateDebutManque.getValue()!= null && dateFinManque.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()!=-1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1){               
		    		
        if(!dateDebutManque.getValue().equals(dateFinManque.getValue()))
		    		{
		    			if(dateFinManque.getValue().compareTo(dateDebutManque.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutManque.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinManque.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
              
                Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());

                String etatMnq = etatCombo.getSelectionModel().getSelectedItem();
         
        List <Object[]> listeObjectManqueMp =detailBonRetourDAO.findDetailBonRetourByCodeAndManqueAndDateAndEtatAndFour(codeMpRechField.getText(),nManqueRechField.getText(),dateOperaDebut,dateOperaFin,etatMnq,fournisseur.getNom());

             listeManqueMP.clear();
         
            for(int i=0; i<listeObjectManqueMp.size(); i++) {

                    Object[] object=listeObjectManqueMp.get(i);
                   
                    MatierePremier matierePremier = (MatierePremier)object[0]; 
                    BigDecimal montant = (BigDecimal)object[1]; 
                    Date dateCreation = (Date)object[2]; 
                    String numManque = (String)object[3]; 
                    String Four = (String)object[4];
                    String etat = (String)object[5];
                    
                    if(matierePremier == null && montant == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

               ManqueMP manqueMP = new ManqueMP();
               
                  manqueMP.setMatierePremier(matierePremier);
                  manqueMP.setMontant(montant);
                  manqueMP.setDateCreation(dateCreation);
                  manqueMP.setNumManque(numManque);
                  manqueMP.setFour(Four);
                  manqueMP.setEtat(etat);
                  
                  listeManqueMP.add(manqueMP);
                    }
            }
            
            tableStock.setItems(listeManqueMP);
            setColumnProperties();
        
        
   }
        
    }

    @FXML
    private void dateDebit(ActionEvent event) {
    }

    @FXML
    private void dateFin(ActionEvent event) {
    }

    @FXML
    private void fourOnAction(ActionEvent event) {
    }

    @FXML
    private void etatOnAction(ActionEvent event) {
    }

    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
  try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationManqueController.class.getResource(nav.getiReportConsultationManqueMp()));

            
       
               JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeManqueMP));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationManqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
        
         fourCombo.getSelectionModel().select(-1);
        etatCombo.getSelectionModel().select(-1);
        codeMpRechField.setText(Constantes.MATIERE_PREMIER);
        dateDebutManque.setValue(null);
        dateFinManque.setValue(null);
        nManqueRechField.setText("");

        tableStock.getItems().clear();
        
         ManqueGlobal();
        
    }
    
}
