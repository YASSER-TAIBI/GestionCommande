/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ClientMP;

import Controller.Fournisseur.ConsultationSoldeFourController;
import Controller.commande.EnvoyerCommandeController;
import Utils.Constantes;
import dao.Entity.BonLivraison;
import dao.Entity.ClientMP;
import dao.Entity.DetailCompte;
import dao.Entity.Fournisseur;
import dao.Manager.ClientMPDAO;
import dao.Manager.DetailCompteDAO;
import dao.Manager.FournisseurDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
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
public class ConsultationSoldeClientController implements Initializable {

    @FXML
    private TableView<DetailCompte> tableDetailCompte;
    @FXML
    private TableColumn<DetailCompte, Date> dateOperationColumn;
    @FXML
    private TableColumn<DetailCompte, String> designationColumn;
    @FXML
    private TableColumn<DetailCompte, BigDecimal> montantCreditColumn;
    @FXML
    private TableColumn<DetailCompte, BigDecimal> montantDebitColumn;
    @FXML
    private TableColumn<DetailCompte, Date> dateLivraisonColumn;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private Label totalCredit;
    @FXML
    private Label totalDebit;
    @FXML
    private Label solde;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private Label totalCreditAncien;
    @FXML
    private Label totalDebitAncien;
    @FXML
    private Label soldeAncien;
    
    /**
     * Initializes the controller class.
     */
    ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
       private Map<String,ClientMP> mapClient=new HashMap<>();
        FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
        private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
          private final ObservableList<DetailCompte> listeDetailCompte=FXCollections.observableArrayList();
          
             private final  ObservableList<DetailCompte> listMontantCreditDebit = FXCollections.observableArrayList();  
               DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
                 navigation nav = new navigation();
    @FXML
    private Label soldeTotal;
        
    BigDecimal soldeAn = BigDecimal.ZERO;
  

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            List<Fournisseur> listFournisseur=fournisseurDAO.findAllPfAndMp();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });

        
          List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((client) -> {
            clientCombo.getItems().addAll(client.getNom());
            return client;
        }).forEachOrdered((client) -> {
            mapClient.put(client.getNom(), client);
        });
        
           setColumnProperties();
           loadDetail();  
        
           calcule ();
           
             tableDetailCompte.setEditable(true);
             dateLivraisonColumn.setEditable(true);
           
           
           
       
    }    
    
    void calcule (){
    
          BigDecimal calculCreditTotal= BigDecimal.ZERO;
             BigDecimal calculDebitTotal= BigDecimal.ZERO;
             BigDecimal somme=BigDecimal.ZERO;
             BigDecimal sommeTotal=BigDecimal.ZERO;
             
           for( int rows = 0;rows<listeDetailCompte.size() ;rows++ ){

               DetailCompte detailCompte = listeDetailCompte.get(rows);
               
            calculCreditTotal = calculCreditTotal.add(detailCompte.getMontantCredit());  

    }
           
              DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
           
         totalCredit.setText(df.format(calculCreditTotal));  
        
     
           for( int rows = 0;rows<listeDetailCompte.size() ;rows++ ){
               
                  DetailCompte detailCompte = listeDetailCompte.get(rows);

            calculDebitTotal = calculDebitTotal.add(detailCompte.getMontantDebit());  
        
    }
           
                  
           
         totalDebit.setText(df.format(calculDebitTotal));  

         somme = calculCreditTotal.subtract(calculDebitTotal) ;
          
         sommeTotal= somme.add(soldeAn);
         
         solde.setText(df.format(somme));  
         soldeTotal.setText(df.format(sommeTotal));  
        
         
         
    }
    
   void setColumnProperties(){
        
    
        dateOperationColumn.setCellValueFactory(new PropertyValueFactory<>("dateOperation"));
        
        designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        setColumnTextFieldConverterString(designationColumn);
        
        dateLivraisonColumn.setCellValueFactory(new PropertyValueFactory<DetailCompte, Date>("dateBonLivraison"));
        setColumnTextFieldConverter(getConverter(),dateLivraisonColumn);
        
        montantDebitColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCompte, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCompte, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantDebit()));
                }
                
             });
      
        
//        montantCreditColumn.setCellValueFactory(new PropertyValueFactory<>("montantCredit"));
         
            montantCreditColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCompte, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCompte, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontantCredit()));
                }
                
             });
      
            
    }
   
           public static void setColumnTextFieldConverter(StringConverter converter, TableColumn... columns) {

        for (TableColumn tableColumn : columns) {

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        }
    }

        
    private StringConverter getConverter() {
        StringConverter<Date> converter = new StringConverter<Date>() {
            @Override
            public Date fromString(String string) {

                try {
                    
             Date valeur;      
             valeur = new Date(string);
                    return valeur;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString(Date object) {

                if (object == null) {
                    return null;
                }
                return String.valueOf(object);
            }
        };

        return converter;
    }
   
     void loadDetail(){
        
        listeDetailCompte.clear();
        listeDetailCompte.addAll(detailCompteDAO.findAll());
        tableDetailCompte.setItems(listeDetailCompte);
    }

             public static void setColumnTextFieldConverterString(TableColumn... columns) {

        for (TableColumn tableColumn : columns) {

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        }
    }

            
        

     
    @FXML
    private void fourOnAction(ActionEvent event) {
    }

    @FXML
    private void recherchMouseClicked(MouseEvent event) {
          if (fourCombo.getSelectionModel().getSelectedIndex()== -1 || 
                fourCombo.getSelectionModel().getSelectedItem().equals("") || 
                clientCombo.getSelectionModel().getSelectedIndex()== -1 || 
                clientCombo.getSelectionModel().getSelectedItem().equals("") )
        {
        nav.showAlert(Alert.AlertType.ERROR, "Alert", null, Constantes.VERIFIER_FOURNISSEUR_CLIENT);
        }else{
               listeDetailCompte.clear();
               Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
               ClientMP client=mapClient.get(clientCombo.getSelectionModel().getSelectedItem());
                 listeDetailCompte.addAll(detailCompteDAO.findFourByRechercheNomReglement(fournisseur.getCompteFourMP().getId(),client.getId()));
     
      soldeAn = BigDecimal.ZERO;           
                 
    totalCreditAncien.setText("");
    totalDebitAncien.setText("");
    soldeAncien.setText("");
                
    totalCredit.setText("");
    totalDebit.setText("");
    solde.setText("");
    soldeTotal.setText("");
                 
                 calcule ();
          } 

    }

    @FXML
    private void imprimerOnAction(ActionEvent event) throws ParseException {
       try {
          HashMap para = new HashMap(); 
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationSoldeClientController.class.getResource(nav.getiReportConsultationSoldeClientFour()));

               Fournisseur fournisseur = mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
               ClientMP clientMP = mapClient.get(clientCombo.getSelectionModel().getSelectedItem());
            
                     if(fournisseur!=null && clientMP!=null){
                 para.put("Fournisseur",fournisseur.getNom());
                 para.put("Client",clientMP.getNom());
            }else {
                   para.put("Fournisseur","TOUT FOURNISSEUR");
                   para.put("Client","TOUT CLIENT");
            }

   
           LocalDate localDate=dateDebut.getValue();
           Date Debut =null;
           if(localDate!=null)
           {
          Debut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                
            localDate=dateFin.getValue();
                  Date Fin =null;
              if(localDate!=null)
           {     
          Fin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }    
                     if(dateDebut.getValue()!=null && dateFin.getValue()!=null){
                         
                       para.put("DateDebut",Debut);
                       para.put("DateFin",Fin);     
                     }
                     
                     if(!totalCreditAncien.getText().equals("") && !totalDebitAncien.getText().equals("") && !soldeAncien.getText().equals("")){
                     
                            para.put("totalCreditAncien",totalCreditAncien.getText());
                            para.put("totalDebitAncien",totalDebitAncien.getText());
                            para.put("soldeAncien",soldeAncien.getText());
                     
                     }
                     
            para.put("totalCredit",totalCredit.getText());
            para.put("totalDebit",totalDebit.getText());
            para.put("solde",solde.getText());
            para.put("soldeTotal",soldeTotal.getText());
            
             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(tableDetailCompte.getItems()));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(EnvoyerCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
        
    
      loadDetail();
   
    clientCombo.getSelectionModel().select(-1);     
    fourCombo.getSelectionModel().select(-1);    
    
    soldeAn = BigDecimal.ZERO;
    
    totalCreditAncien.setText("");
    totalDebitAncien.setText("");
    soldeAncien.setText("");
                
    totalCredit.setText("");
    totalDebit.setText("");
    solde.setText("");
    soldeTotal.setText("");
    
    dateDebut.setValue(null);
    dateFin.setValue(null);
    
           calcule();
    }

    @FXML
    private void clientComboOnAction(ActionEvent event) {
    }

    @FXML
    private void dateDebutOnAction(ActionEvent event) {
    }

    @FXML
    private void dateFinOnAction(ActionEvent event) {
        
    }

    @FXML
    private void recherchDateMouseClicked(MouseEvent event) throws ParseException {
        if(
          dateDebut.getValue()== null||
          dateFin.getValue()== null||
                fourCombo.getSelectionModel().getSelectedIndex()== -1 ||
                clientCombo.getSelectionModel().getSelectedIndex()==-1
          )
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
     }
        
        
        else if(dateDebut.getValue()!=null && dateFin.getValue()!=null)
		    		{

		    		if(!dateDebut.getValue().equals(dateFin.getValue()))
		    		{
		    			if(dateFin.getValue().compareTo(dateDebut.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

		    		}
        
        
        
        else if (dateDebut.getValue()==null){
        
            dateFin.setValue(null);
            
        }
            listeDetailCompte.clear();
           Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
           ClientMP client=mapClient.get(clientCombo.getSelectionModel().getSelectedItem());
           
           LocalDate localDate=dateDebut.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFin.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
         
       
        List<DetailCompte> detailCompte = detailCompteDAO.findFilterDetailCompteByDateOperationAndFourAndClient(dateOperaDebut, dateOperaFin, fournisseur.getId(), client.getId());
        
        
        listeDetailCompte.addAll(detailCompte);
        tableDetailCompte.setItems(listeDetailCompte);
         calcule ();
        setColumnProperties();
        
//==================================================================== Montant Credit Debit Ancien ============================================================================================================================
 
 
            List <Object[]> listeObjectMontant =detailCompteDAO.findBy(dateOperaDebut, client.getId(), fournisseur.getId());

             listMontantCreditDebit.clear();
         
            for(int i=0; i<listeObjectMontant.size(); i++) {

                    Object[] object=listeObjectMontant.get(i);
                   
                    BigDecimal debit =(BigDecimal)object[0];
                    BigDecimal credit = (BigDecimal)object[1]; 
           
                    soldeAn= credit.subtract(debit);
                    
                    if(credit == null && debit == null){
                    
                         nav.showAlert(Alert.AlertType.INFORMATION, "Alert", null, Constantes.MESSAGE_ALERT_AUCUN_TRAITEMENT);
                        break;
                    }else{

                          DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                        
               totalCreditAncien.setText(df.format(credit));
               totalDebitAncien.setText(df.format(debit));
               soldeAncien.setText(df.format(soldeAn));
                        
                    }
            }
        
           calcule ();
        
    }

    @FXML
    private void dateLivraisonOnEditCommit(TableColumn.CellEditEvent<DetailCompte, Date> event) {
        
         
        System.out.println("event.getNewValue() "+event.getNewValue());
        
            ((DetailCompte) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setDateBonLivraison(event.getNewValue());
            
            
            
            
              Date  dateLivraison = dateLivraisonColumn.getCellData(event.getTablePosition().getRow());

              
              if (dateLivraison== null){
                  
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.ERREUR_DATE , Constantes.VERIFICATION_DATE_SAISIE );
              
              }else{
              
              tableDetailCompte.refresh();
              DetailCompte detailCompte = listeDetailCompte.get(event.getTablePosition().getRow());
              detailCompte.setDateBonLivraison(dateLivraison);
              listeDetailCompte.set(event.getTablePosition().getRow(), detailCompte);
                
              detailCompteDAO.edit(detailCompte);

              
              }
      
        
    }

    @FXML
    private void designationOnEditCommit(TableColumn.CellEditEvent<DetailCompte, String> event) {
        
         System.out.println("event.getNewValue() "+event.getNewValue());
        
          
            ((DetailCompte) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setDesignation(event.getNewValue());
            
            
            
            
              String  designation = designationColumn.getCellData(event.getTablePosition().getRow());

              
              if (designation== null){
                  
                nav.showAlert(Alert.AlertType.ERROR, "Erreur", Constantes.ERREUR , Constantes.SELECTION_ERREUR);
              
              }else{
              
              tableDetailCompte.refresh();
              DetailCompte detailCompte = listeDetailCompte.get(event.getTablePosition().getRow());
              detailCompte.setDesignation(designation);
              listeDetailCompte.set(event.getTablePosition().getRow(), detailCompte);
                
              detailCompteDAO.edit(detailCompte);

              
              }
      
         
    }

 
    

}