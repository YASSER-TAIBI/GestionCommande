/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Livraision;

import Utils.Constantes;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailCommande;
import dao.Entity.Fournisseur;
import dao.Entity.MatierePremier;
import dao.Entity.SubCategorieMp;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.FournisseurDAO;
import dao.Manager.MatierePremiereDAO;
import dao.Manager.SubCategorieMPDAO;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.FournisseurDAOImpl;
import dao.ManagerImpl.MatierePremierDAOImpl;
import dao.ManagerImpl.SubCategorieMPAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
public class SituationGlobalCommandeController implements Initializable {

    @FXML
    private Button imprimerBtn;
    @FXML
    private Button refrechBtn;
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
    private TableColumn<DetailCommande, String> codeMpColumn;
    @FXML
    private ComboBox<String> subCatgCombo;
    @FXML
    private TextField mpRechField;
    @FXML
    private ComboBox<String> fourCombo;
    @FXML
    private ComboBox<String> etatCombo;
    @FXML
    private DatePicker dateDebutBonCommande;
    @FXML
    private DatePicker dateFinBonCommande;
    @FXML
    private ImageView rechDate;
    @FXML
    private Label sommeLabel;
    
  
        MatierePremier matierePremiere=new MatierePremier();
        MatierePremiereDAO matierePremiereDAO = new MatierePremierDAOImpl();
        
        ObservableList<String> etat =FXCollections.observableArrayList(Constantes.ETAT_COMMANDE_RECU, Constantes.ETAT_COMMANDE_VALIDEE, Constantes.ETAT_COMMANDE_LANCE,Constantes.ETAT_COMMANDE_ENCOURS);
    
         private final ObservableList<DetailCommande> listeDetailCommande=FXCollections.observableArrayList();
         private final ObservableList<DetailCommande> listeDetailCommandeTMP = FXCollections.observableArrayList();
          
         private Map<String,SubCategorieMp> mapSubCatg=new HashMap<>();
         
         DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
         SubCategorieMPDAO subcategorieMpDAO = new SubCategorieMPAOImpl();
  
         navigation nav = new navigation();
  
         private Map<String,Fournisseur> mapFournisseur=new HashMap<>();
         FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    @FXML
    private TextField qteResteeField;
    

        void setColumnProperties(){
    
            codeMpColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
            }

        });
        
            ProduitColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
            }

        });
     
            fournisseurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande , String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCommande().getFourisseur().getNom());
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
    
             void loadDetail(){
        
        listeDetailCommande.clear();
        listeDetailCommande.addAll(detailCommandeDAO.findByEtat(Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommande);
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  
        etatCombo.setItems(etat);
        
        List<SubCategorieMp> listSubCategorieMp=subcategorieMpDAO.SubCategorieMpByBoxCartonAdf();
        
        listSubCategorieMp.stream().map((subCategorieMp) -> {
            subCatgCombo.getItems().addAll(subCategorieMp.getNom());
            return subCategorieMp;
        }).forEachOrdered((subCategorieMp) -> {
            mapSubCatg.put(subCategorieMp.getNom(), subCategorieMp);
        });
         
               List<Fournisseur> listFournisseur=fournisseurDAO.findAll();
        
        listFournisseur.stream().map((fournisseur) -> {
            fourCombo.getItems().addAll(fournisseur.getNom());
            return fournisseur;
        }).forEachOrdered((fournisseur) -> {
            mapFournisseur.put(fournisseur.getNom(), fournisseur);
        });

            sommeTotal();
    }    

    
    
    
    @FXML
    private void clickTablePrixBox(MouseEvent event) {
        
    }


    @FXML
    private void imprimerBtnOnAction(ActionEvent event) {
        
               try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(SituationGlobalCommandeController.class.getResource(nav.getiReportSituationGlobalCommande()));

            
               para.put("produit",subCatgCombo.getSelectionModel().getSelectedItem());
       
               JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(tableDetailCommande.getItems()));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(SituationGlobalCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void refrechBtnOnAction(ActionEvent event) {
        
        fourCombo.getSelectionModel().select(-1);
        subCatgCombo.getSelectionModel().select(-1);
        etatCombo.getSelectionModel().select(-1);
        mpRechField.setText(Constantes.MATIERE_PREMIER);
        dateDebutBonCommande.setValue(null);
        dateFinBonCommande.setValue(null);
        
        fourCombo.setDisable(false);
        subCatgCombo.setDisable(false);
        mpRechField.setDisable(false);
        dateDebutBonCommande.setDisable(false);
        dateFinBonCommande.setDisable(false);
        rechDate.setDisable(false);
        
        tableDetailCommande.getItems().clear();
        
        sommeTotal();
    }

    @FXML
    private void subCatgComboOnAction(ActionEvent event) {

    }

    @FXML
    private void fourComboOnAction(ActionEvent event) {

    }
    
    void sommeTotal(){
    
      BigDecimal sommeTotal= BigDecimal.ZERO;
           for( int rows = 0;rows<tableDetailCommande.getItems().size() ;rows++ ){

               DetailCommande detailCommande = tableDetailCommande.getItems().get(rows);
               
            sommeTotal = sommeTotal.add(detailCommande.getQuantiteRecu());  
    
    }
                 DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
         sommeLabel.setText(df.format(sommeTotal));
    }
    

    @FXML
    private void rechercheNumComOnKeyPressed(ActionEvent event) {
    
    }

    @FXML
    private void recherchMouseClicked(MouseEvent event) throws ParseException {
        
    
        if (subCatgCombo.getSelectionModel().getSelectedIndex()==-1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1 )
          {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS_DATE);
     }

        else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1 )
		    		{
                                     listeDetailCommande.clear();
                                     
		    		if(!dateDebutBonCommande.getValue().equals(dateFinBonCommande.getValue()))
		    		{
		    			if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutBonCommande.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinBonCommande.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
         
       
        List<DetailCommande> detailCommandes = detailCommandeDAO.findFilterDetailCommandeByDateCommande(dateOperaDebut, dateOperaFin, Constantes.ETAT_AFFICHAGE);
        
        
        listeDetailCommande.addAll(detailCommandes);
        tableDetailCommande.setItems(listeDetailCommande);
        setColumnProperties();
        sommeTotal();
        
        
    } else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1)
                    {
                        
         listeDetailCommande.clear();
         
           SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem());
           listeDetailCommande.addAll(detailCommandeDAO.findCommanByProduit(categorieMp.getId(),Constantes.ETAT_AFFICHAGE));
           tableDetailCommande.setItems(listeDetailCommande);
           setColumnProperties();
           sommeTotal();
    }
    
      else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1)
                    {
                        
         listeDetailCommande.clear();

         	if(!dateDebutBonCommande.getValue().equals(dateFinBonCommande.getValue()))
		    		{
		    			if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutBonCommande.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinBonCommande.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 
              
           SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem());
           
           listeDetailCommande.addAll(detailCommandeDAO.findFilterDetailCommandeByDateCommandeAndProduit(dateOperaDebut, dateOperaFin, categorieMp.getId(), Constantes.ETAT_AFFICHAGE));
           tableDetailCommande.setItems(listeDetailCommande);
           setColumnProperties();
           sommeTotal();
    }
           else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1){
           
            listeDetailCommande.clear();
            
           Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
           
           listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByFournisseur(fournisseur.getId(), Constantes.ETAT_AFFICHAGE));
           tableDetailCommande.setItems(listeDetailCommande);
               setColumnProperties();
               sommeTotal();
            
           
           }
           
           else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1){
           
            listeDetailCommande.clear();
            
            	if(!dateDebutBonCommande.getValue().equals(dateFinBonCommande.getValue()))
		    		{
		    			if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutBonCommande.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinBonCommande.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 

           Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
           
           listeDetailCommande.addAll(detailCommandeDAO.findFilterDetailCommandeByDateCommandeAndFour(dateOperaDebut, dateOperaFin,fournisseur.getId(),Constantes.ETAT_AFFICHAGE ));
           tableDetailCommande.setItems(listeDetailCommande);
               setColumnProperties();
               sommeTotal();
            
           
           }
            else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1){
           
            listeDetailCommande.clear();
            
              Fournisseur fournisseur=mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
              SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem());
              
               listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByFournisseurAndSubCategorie(fournisseur.getId(),categorieMp.getId(),Constantes.ETAT_AFFICHAGE));
           tableDetailCommande.setItems(listeDetailCommande);
               setColumnProperties();
               sommeTotal();

            }
               else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1){
           
            listeDetailCommande.clear();
            
            	if(!dateDebutBonCommande.getValue().equals(dateFinBonCommande.getValue()))
		    		{
		    			if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutBonCommande.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinBonCommande.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           } 

            
             Fournisseur fournisseur =mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
             
             SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem());
            
           listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByFournisseurAndSubCategorieAndDate(dateOperaDebut, dateOperaFin, fournisseur.getId(), categorieMp.getId(),Constantes.ETAT_AFFICHAGE));
           tableDetailCommande.setItems(listeDetailCommande);
           setColumnProperties();
           sommeTotal();
              
               }
               
             else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && !mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
            listeDetailCommande.clear();      
              
        matierePremiere = matierePremiereDAO.findByCode(mpRechField.getText());

        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMp(matierePremiere.getCode(), Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommande);
        setColumnProperties();
        sommeTotal();
             
             
             }
        
            else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && !mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
            listeDetailCommande.clear();      
              
            	if(!dateDebutBonCommande.getValue().equals(dateFinBonCommande.getValue()))
		    		{
		    			if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutBonCommande.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinBonCommande.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }   
            
        matierePremiere = matierePremiereDAO.findByCode(mpRechField.getText());

        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMpAndDate(dateOperaDebut, dateOperaFin, matierePremiere.getCode(), Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommande);
        setColumnProperties();
        sommeTotal();
             
             
             }        
            else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && !mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
            listeDetailCommande.clear();      
              
        matierePremiere = matierePremiereDAO.findByCode(mpRechField.getText());

        SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem());
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMpAndSubCategorie(matierePremiere.getCode(), categorieMp.getId(), Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommande);
        setColumnProperties();
        sommeTotal();
             
             
             }
                else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && !mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
            listeDetailCommande.clear();      
              
              	if(!dateDebutBonCommande.getValue().equals(dateFinBonCommande.getValue()))
		    		{
		    			if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutBonCommande.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinBonCommande.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }   
            
        matierePremiere = matierePremiereDAO.findByCode(mpRechField.getText());

        SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem());
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMpAndDateAndSubCategorie(dateOperaDebut, dateOperaFin, matierePremiere.getCode(),categorieMp.getId() , Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommande);
        setColumnProperties();
        sommeTotal();
             
             
             }
                    else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && !mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
           listeDetailCommande.clear();  
        
        matierePremiere = matierePremiereDAO.findByCode(mpRechField.getText());

          Fournisseur fournisseur =mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMpAndFour(matierePremiere.getCode(), fournisseur.getId(), Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommande);
        setColumnProperties();
        sommeTotal();
             
             
             }
                else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && !mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
            listeDetailCommande.clear();      
              
               
              
            	if(!dateDebutBonCommande.getValue().equals(dateFinBonCommande.getValue()))
		    		{
		    			if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutBonCommande.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinBonCommande.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
            
        matierePremiere = matierePremiereDAO.findByCode(mpRechField.getText());

          Fournisseur fournisseur =mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMpAndDateAndFour(dateOperaDebut, dateOperaFin, matierePremiere.getCode(),fournisseur.getId(), Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommande);
        setColumnProperties();
        sommeTotal();
             
             
             }
          else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && !mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
            listeDetailCommande.clear();      
              
        matierePremiere = matierePremiereDAO.findByCode(mpRechField.getText());

        SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem());
        
        Fournisseur fournisseur =mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMpAndFourAndSubCategorie(matierePremiere.getCode(), fournisseur.getId(), categorieMp.getId(),Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommande);
        setColumnProperties();
        sommeTotal();
             
             
             }
          else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && !mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()== -1){
             
            listeDetailCommande.clear();      
              

            	if(!dateDebutBonCommande.getValue().equals(dateFinBonCommande.getValue()))
		    		{
		    			if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutBonCommande.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinBonCommande.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
            
        matierePremiere = matierePremiereDAO.findByCode(mpRechField.getText());

        SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem());
        
        Fournisseur fournisseur =mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMpAndDateAndFourAndSubCategorie(dateOperaDebut, dateOperaFin, matierePremiere.getCode(), categorieMp.getId(), fournisseur.getId(),Constantes.ETAT_AFFICHAGE));
        tableDetailCommande.setItems(listeDetailCommande);
        setColumnProperties();
        sommeTotal();
             
             
             }else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                 
            listeDetailCommande.clear();      
            
              String etat = etatCombo.getSelectionModel().getSelectedItem();
        
        listeDetailCommande.addAll(detailCommandeDAO.findCommanByEtatCmd( etat ,Constantes.ETAT_AFFICHAGE));
        
               for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if( etat.equals(Constantes.ETAT_COMMANDE_ENCOURS) ){
                    
                   if(detailCommande.getQuantiteRestee().compareTo(new BigDecimal(BigInteger.ZERO).setScale(2))==0){
                
                listeDetailCommande.remove(detailCommande);

                }
                }
            }
  
         tableDetailCommande.setItems(listeDetailCommande);

        setColumnProperties();
        sommeTotal();
             
             
            }else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                 
            listeDetailCommande.clear();
            
            String etat = etatCombo.getSelectionModel().getSelectedItem();
            
            SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem()); 
       
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeBySubCategorieAndEtatCmd(categorieMp.getId() ,etat ,Constantes.ETAT_AFFICHAGE));
        
             for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if( etat.equals(Constantes.ETAT_COMMANDE_ENCOURS) ){
                    
                   if(detailCommande.getQuantiteRestee().compareTo(new BigDecimal(BigInteger.ZERO).setScale(2))==0){
                
                listeDetailCommande.remove(i);

                }
                }
            }
  
         tableDetailCommande.setItems(listeDetailCommande);

        setColumnProperties();
        sommeTotal();
             
         
      }else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                 
            listeDetailCommande.clear();
            
            String etat = etatCombo.getSelectionModel().getSelectedItem();
        
        Fournisseur fournisseur =mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
       
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByFournisseurAndEtatCmd(fournisseur.getId() ,etat ,Constantes.ETAT_AFFICHAGE));
        
              for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if( etat.equals(Constantes.ETAT_COMMANDE_ENCOURS) ){
                    
                   if(detailCommande.getQuantiteRestee().compareTo(new BigDecimal(BigInteger.ZERO).setScale(2))==0){
                
                listeDetailCommande.remove(detailCommande);

                }
                }
            }
  
         tableDetailCommande.setItems(listeDetailCommande);

        setColumnProperties();
        sommeTotal();
             
             
     }else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && !mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                 
            listeDetailCommande.clear();
            
            String etat = etatCombo.getSelectionModel().getSelectedItem();
        
         matierePremiere = matierePremiereDAO.findByCode(mpRechField.getText());
       
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMpAndEtatCmd(matierePremiere.getCode() ,etat ,Constantes.ETAT_AFFICHAGE));
             for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if( etat.equals(Constantes.ETAT_COMMANDE_ENCOURS) ){
                    
                   if(detailCommande.getQuantiteRestee().compareTo(new BigDecimal(BigInteger.ZERO).setScale(2))==0){
                
                listeDetailCommande.remove(detailCommande);

                }
                }
            }
  
         tableDetailCommande.setItems(listeDetailCommande);

        setColumnProperties();
        sommeTotal();
             
             
            }else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                 
            listeDetailCommande.clear();
            
            	if(!dateDebutBonCommande.getValue().equals(dateFinBonCommande.getValue()))
		    		{
		    			if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutBonCommande.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinBonCommande.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
            
            String etat = etatCombo.getSelectionModel().getSelectedItem();
        

        listeDetailCommande.addAll(detailCommandeDAO.findFilterDetailCommandeByDateCommandeAndEtatCmd(dateOperaDebut ,dateOperaFin ,etat ,Constantes.ETAT_AFFICHAGE));
          
         for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if( etat.equals(Constantes.ETAT_COMMANDE_ENCOURS) ){
                    
                   if(detailCommande.getQuantiteRestee().compareTo(new BigDecimal(BigInteger.ZERO).setScale(2))==0){
                
                listeDetailCommande.remove(detailCommande);

                }
                }
            }
  
         tableDetailCommande.setItems(listeDetailCommande);

        setColumnProperties();
        sommeTotal();
             

          }else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                 
            listeDetailCommande.clear();

            String etat = etatCombo.getSelectionModel().getSelectedItem();
        
       Fournisseur fournisseur =mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem());
       
        SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem()); 
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByFournisseurAndSubCategorieAndEtatCmd(categorieMp.getId() ,fournisseur.getId() ,etat ,Constantes.ETAT_AFFICHAGE));
          
         for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if( etat.equals(Constantes.ETAT_COMMANDE_ENCOURS) ){
                    
                   if(detailCommande.getQuantiteRestee().compareTo(new BigDecimal(BigInteger.ZERO).setScale(2))==0){
                
                listeDetailCommande.remove(detailCommande);
     
                
                }
                }
            }
        

         tableDetailCommande.setItems(listeDetailCommande);

        setColumnProperties();
        sommeTotal();
             
             
        }else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && !mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                 
            listeDetailCommande.clear();

        String etat = etatCombo.getSelectionModel().getSelectedItem();
        
         matierePremiere = matierePremiereDAO.findByCode(mpRechField.getText());
       
        SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem()); 
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMpAndSubCategorieAndEtatCmd(categorieMp.getId() ,matierePremiere.getCode() ,etat ,Constantes.ETAT_AFFICHAGE));
            
         for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if( etat.equals(Constantes.ETAT_COMMANDE_ENCOURS) ){
                    
                   if(detailCommande.getQuantiteRestee().compareTo(new BigDecimal(BigInteger.ZERO).setScale(2))==0){
                
                listeDetailCommande.remove(detailCommande);
     
                
                }
                }
            }
        

         tableDetailCommande.setItems(listeDetailCommande);

        setColumnProperties();
        sommeTotal();
             
             
        }else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()== -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                 
            listeDetailCommande.clear();

         if(!dateDebutBonCommande.getValue().equals(dateFinBonCommande.getValue()))
		    		{
		    			if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutBonCommande.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinBonCommande.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }   
            
        String etat = etatCombo.getSelectionModel().getSelectedItem();

        SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem()); 
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByDateAndSubCategorieAndEtatCmd(dateOperaDebut ,dateOperaFin ,categorieMp.getId(), etat ,Constantes.ETAT_AFFICHAGE));
        
        for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if( etat.equals(Constantes.ETAT_COMMANDE_ENCOURS) ){
                    
                   if(detailCommande.getQuantiteRestee().compareTo(new BigDecimal(BigInteger.ZERO).setScale(2))==0){
                
                listeDetailCommande.remove(detailCommande);
     
                
                }
                }
            }
        

         tableDetailCommande.setItems(listeDetailCommande);

        setColumnProperties();
        sommeTotal();

             
        }else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                 
            listeDetailCommande.clear();

         if(!dateDebutBonCommande.getValue().equals(dateFinBonCommande.getValue()))
		    		{
		    			if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutBonCommande.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinBonCommande.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }   
            
        String etat = etatCombo.getSelectionModel().getSelectedItem();

         Fournisseur fournisseur =mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()); 
         
           SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem()); 
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByDateAndFourAndSubCategorieAndEtatCmd(dateOperaDebut ,dateOperaFin , categorieMp.getId() , fournisseur.getId(), etat ,Constantes.ETAT_AFFICHAGE));

            for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if( etat.equals(Constantes.ETAT_COMMANDE_ENCOURS) ){
                    
                   if(detailCommande.getQuantiteRestee().compareTo(new BigDecimal(BigInteger.ZERO).setScale(2))==0){
                
                listeDetailCommande.remove(detailCommande);
     
                
                }
                }
            }
        

         tableDetailCommande.setItems(listeDetailCommande);

        setColumnProperties();
        sommeTotal();
             
             
        }else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && !mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()== null && dateFinBonCommande.getValue()== null && etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                 
            listeDetailCommande.clear();

        String etat = etatCombo.getSelectionModel().getSelectedItem();

         Fournisseur fournisseur =mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()); 
         
             matierePremiere = matierePremiereDAO.findByCode(mpRechField.getText());
         
           SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem()); 
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMpAndSubCategorieAndFourAndEtatCmd(categorieMp.getId() , fournisseur.getId(), matierePremiere.getCode() , etat ,Constantes.ETAT_AFFICHAGE));

            for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if( etat.equals(Constantes.ETAT_COMMANDE_ENCOURS) ){
                    
                   if(detailCommande.getQuantiteRestee().compareTo(new BigDecimal(BigInteger.ZERO).setScale(2))==0){
                
                listeDetailCommande.remove(detailCommande);

                }
                }
            }

         tableDetailCommande.setItems(listeDetailCommande);
        setColumnProperties();
        sommeTotal();
             
             
        }else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && fourCombo.getSelectionModel().getSelectedIndex()!= -1 && !mpRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER) && dateDebutBonCommande.getValue()!= null && dateFinBonCommande.getValue()!= null && etatCombo.getSelectionModel().getSelectedIndex()!= -1){
             
                 
            listeDetailCommande.clear();

             if(!dateDebutBonCommande.getValue().equals(dateFinBonCommande.getValue()))
		    		{
		    			if(dateFinBonCommande.getValue().compareTo(dateDebutBonCommande.getValue())<0)
		    			{
		    				  nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT);
		    				return;
		    			}
		    			
		    		}

           LocalDate localDate=dateDebutBonCommande.getValue();
           Date dateOperaDebut =null;
           if(localDate!=null)
           {
          dateOperaDebut =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }
                 localDate=dateFinBonCommande.getValue();
                  Date dateOperaFin =null;
              if(localDate!=null)
           {     
          dateOperaFin =new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
           }   
  
        String etat = etatCombo.getSelectionModel().getSelectedItem();

         Fournisseur fournisseur =mapFournisseur.get(fourCombo.getSelectionModel().getSelectedItem()); 
         
             matierePremiere = matierePremiereDAO.findByCode(mpRechField.getText());
         
           SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem()); 
        
        listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMpAndDateAndFourAndSubCategorieAndEtatCmd(dateOperaDebut ,dateOperaFin ,categorieMp.getId() ,matierePremiere.getCode() , fournisseur.getId() , etat ,Constantes.ETAT_AFFICHAGE));
   
        
            for (int i = 0; i < listeDetailCommande.size(); i++) {
                
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if( etat.equals(Constantes.ETAT_COMMANDE_ENCOURS) ){
                    
                   if(detailCommande.getQuantiteRestee().compareTo(new BigDecimal(BigInteger.ZERO).setScale(2))==0){
                
                listeDetailCommande.remove(i);
         
                }
                }
            }
        
        
     
         tableDetailCommande.setItems(listeDetailCommande);
        setColumnProperties();
        sommeTotal();
             
             
             }
    }

    @FXML
    private void dateDebit(ActionEvent event) {

    }

    @FXML
    private void dateFin(ActionEvent event) {
       
        
    }

    @FXML
    private void etatComboOnAction(ActionEvent event) {
    }

    @FXML
    private void qteOnKeyPressed(KeyEvent event) {
        
         if (event.getCode().equals(KeyCode.ENTER))
            {
                
          
             BigDecimal Qte = new BigDecimal(qteResteeField.getText());

        if (tableDetailCommande.getItems().isEmpty()){
        
             nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.VERIFIER_TABLE);
        
        }else {
          listeDetailCommandeTMP.clear();
            for (int i = 0; i < listeDetailCommande.size(); i++) {
                DetailCommande detailCommande = listeDetailCommande.get(i);
                
                if (detailCommande.getQuantiteRestee().compareTo(Qte)>0){
                
                       listeDetailCommandeTMP.add(detailCommande);
                    
            }
            }
        
             tableDetailCommande.setItems(listeDetailCommandeTMP);
              setColumnProperties();
        sommeTotal();
 
    }
            } 
    }
}