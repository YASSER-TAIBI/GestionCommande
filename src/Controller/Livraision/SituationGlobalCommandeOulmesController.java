/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Livraision;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.DetailCommande;
import dao.Entity.Fournisseur;
import dao.Entity.MatierePremier;
import dao.Entity.PrixOulmes;
import dao.Entity.SubCategorieMp;
import dao.Manager.ClientMPDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.PrixOulmesDAO;
import dao.Manager.SubCategorieMPDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.PrixOulmesDAOImpl;
import dao.ManagerImpl.SubCategorieMPAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
public class SituationGlobalCommandeOulmesController implements Initializable {

    @FXML
    private TableView<DetailCommande> tableDetailCommande;
    @FXML
    private TableColumn<DetailCommande, String> ProduitColumn;
    @FXML
    private TableColumn<DetailCommande, String> fournisseurColumn;
    @FXML
    private TableColumn<DetailCommande, String> bonCommandeColumn;
    @FXML
    private TableColumn<DetailCommande, Date> dateCommandeColumn;
    @FXML
    private TableColumn<DetailCommande, String> etatColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> qteResteColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> qteReçuColumn;
    @FXML
    private TableColumn<DetailCommande, String> clientColumn;
    @FXML
    private TableColumn<DetailCommande, String> codeMpColumn;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> etatCombo;
    @FXML
    private Button imprimerBtn;
    @FXML
    private Button refrechBtn;
    @FXML
    private DatePicker dateDebutBonCommande;
    @FXML
    private DatePicker dateFinBonCommande;
    @FXML
    private ImageView rechDate;
    @FXML
    private Label sommeLabel;
    @FXML
    private TextField codeRechField;
    @FXML
    private ComboBox<String> libelleCombo;
     @FXML
    private ComboBox<String> clientCombo;
     @FXML
    private Label sommeRestantLabel;
    /**
     * Initializes the controller class.
     */
    
        MatierePremier matierePremiere=new MatierePremier();
        MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
        PrixOulmes prixOulmes =new PrixOulmes();
        
        PrixOulmesDAO prixOulmesDAO = new PrixOulmesDAOImpl();
        private Map<String,PrixOulmes> mapPrixOulmes=new HashMap<>();
        ObservableList<String> etat =FXCollections.observableArrayList(Constantes.ETAT_COMMANDE_RECU, Constantes.ETAT_COMMANDE_VALIDEE, Constantes.ETAT_COMMANDE_LANCE,Constantes.ETAT_COMMANDE_ENCOURS,Constantes.ETAT_COMMANDE_ANNULER);
    
         private final ObservableList<DetailCommande> listeDetailCommande=FXCollections.observableArrayList();

         DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
         
         ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
          private Map<String,ClientMP> mapClientMP=new HashMap<>();
          
         navigation nav = new navigation();
  
         private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
         FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    
   
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         etatCombo.setItems(etat);
//        
         List<PrixOulmes> listPrixOulmes = prixOulmesDAO.findAll();

        listPrixOulmes.stream().map((prixOulmes) -> {
            libelleCombo.getItems().addAll(prixOulmes.getProduit().getLibelle());
            return prixOulmes;
        }).forEachOrdered((prixOulmes) -> {
            mapPrixOulmes.put(prixOulmes.getProduit().getLibelle(), prixOulmes);
        });
          
//         
               List<Fournisseur> listFournisseur=fournisseurDAO.findAllPF();
        
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
            mapClientMP.put(client.getNom(), client);
        });
        
            sommeTotal();
        
    }    

      void sommeTotal(){
    
      BigDecimal sommeTotal= BigDecimal.ZERO;
      BigDecimal sommeRestanteTotal= BigDecimal.ZERO;
      
           for( int rows = 0;rows<tableDetailCommande.getItems().size() ;rows++ ){

               DetailCommande detailCommande = tableDetailCommande.getItems().get(rows);
               
            sommeTotal = sommeTotal.add(detailCommande.getQuantiteRecu());  
    
             sommeRestanteTotal = sommeRestanteTotal.add(detailCommande.getQuantiteRestee());
    }
                 DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
         sommeLabel.setText(df.format(sommeTotal));
         sommeRestantLabel.setText(df.format(sommeRestanteTotal));
    }
    
    void setColumnProperties(){
    
            codeMpColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getCode());
            }

        });
        
            ProduitColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPrixOulmes().getProduit().getLibelle());
            }

        });
     
            fournisseurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCommande().getFourisseur().getNom());
            }

        });
         
            clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCommande().getClientMP().getNom());
            }

        });
    
            bonCommandeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCommande().getnCommande());
            }

        });
      
            dateCommandeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande , Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<DetailCommande, Date> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCommande().getDateSaisie());
            }

        });
  
            etatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCommande().getEtat());
            }

        });
            qteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
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


            qteReçuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRecu()));
                }
                
             });
             
             
            qteResteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRestee()));
                }
                
             });

    }
    

    @FXML
    private void clickTablePrixBox(MouseEvent event) {
    }


    @FXML
    private void fourComboOnAction(ActionEvent event) {
    }

    @FXML
    private void etatComboOnAction(ActionEvent event) {
    }

    @FXML
    private void imprimerBtnOnAction(ActionEvent event) {
        
              try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SituationGlobalCommandeOulmesController.class.getResource(nav.getiReportSituationGlobalCommandeOulmes()));


               JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(tableDetailCommande.getItems()));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(SituationGlobalCommandeOulmesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void refrechBtnOnAction(ActionEvent event) {
        
        fourCombo.getSelectionModel().select(-1);
        clientCombo.getSelectionModel().select(-1);
        libelleCombo.getSelectionModel().select(-1);
        etatCombo.getSelectionModel().select(-1);
        dateDebutBonCommande.setValue(null);
        dateFinBonCommande.setValue(null);
        codeRechField.clear();

        tableDetailCommande.getItems().clear();
        
        sommeTotal();
        
    }

    @FXML
    private void dateDebit(ActionEvent event) {
    }

    @FXML
    private void dateFin(ActionEvent event) {
    }

    @FXML
    private void recherchMouseClicked(MouseEvent event) throws ParseException {
        
        
        
               if ( codeRechField.getText().equals("") && fourCombo.getSelectionModel().getSelectedIndex()== -1 && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1 && clientCombo.getSelectionModel().getSelectedIndex()== -1 ){
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
         return;
       }
            else{
               
                   Fournisseur fournisseur =mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
                   ClientMP clientMP =mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
                   String etat = etatCombo.getSelectionModel().getSelectedItem();
                   PrixOulmes prixOulmes = mapPrixOulmes.get(libelleCombo.getSelectionModel().getSelectedItem());
                   String code = codeRechField.getText();
                   
                   String req = "";
               
            if(fourCombo.getSelectionModel().getSelectedIndex()!= -1) {
             
              req= req+" and c.commande.fourisseur.nom='"+fournisseur.getNom()+"'";
              
             }
             
            if(libelleCombo.getSelectionModel().getSelectedIndex()!= -1) {
             
              req= req+" and c.prixOulmes.produit.libelle='"+prixOulmes.getProduit().getLibelle()+"'";
              
             }
                    
            if(clientCombo.getSelectionModel().getSelectedIndex()!= -1){
             
             req= req+" and c.commande.clientMP.nom='"+clientMP.getNom()+"'";

             }
        
            if(!codeRechField.getText().equals("")){
             
               req= req+" and c.prixOulmes.produit.code='"+code+"'";
      
             }
         
            if(etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
             req= req+" and c.commande.etat='"+etat+"'";

             } 
            
            if(dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null){
             
               LocalDate localDate=dateDebutBonCommande.getValue();
                    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String dateOperaDebut = localDate.format(formatters);

                    localDate=dateFinBonCommande.getValue();
                    String dateOperaFin = localDate.format(formatters);

            if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0){
            
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
            return;
            }else{
            
             req= req+" and c.commande.dateCreation BETWEEN '"+dateOperaDebut+"' and '"+dateOperaFin+"'";

             }
        }else if (dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()== null){
        
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_DATE);
            return;
        
        }else if (dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()!= null){
        
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.ERREUR_DATE);
            return;
        
        }
         
                    listeDetailCommande.clear();
        listeDetailCommande.addAll(detailCommandeDAO.findBySituationGlobalCommandePf(Constantes.ETAT_AFFICHAGE, req ));
        tableDetailCommande.setItems(listeDetailCommande);
        setColumnProperties();      
        sommeTotal();
            
               }

    }

    @FXML
    private void libelleComboOnAction(ActionEvent event) {
        
                 PrixOulmes prixOulmes  = mapPrixOulmes.get(libelleCombo.getSelectionModel().getSelectedItem());
         
          if(prixOulmes!=null){
              
         codeRechField.setText(prixOulmes.getProduit().getCode());
         
          }
        
    }

    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
        
                if (event.getCode().equals(KeyCode.ENTER) )
            {
                         prixOulmes = prixOulmesDAO.findPrixOulmesByCodeArt(codeRechField.getText());
                         libelleCombo.setValue(prixOulmes.getProduit().getLibelle());
            }
          
        
    }

    @FXML
    private void clientComboOnAction(ActionEvent event) {
    }
    
}
