/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CommandeEtrangere;

import Utils.Constantes;
import dao.Entity.ClientMP;
import dao.Entity.CommandeEtrangere;
import dao.Entity.DetailCommandeEtrangere;
import dao.Entity.DetailReceptionEtrangere;
import dao.Entity.Devise;
import dao.Entity.Sequenceur;
import dao.Manager.ClientMPDAO;
import dao.Manager.CommandeEtrangereDAO;
import dao.Manager.DetailCommandeEtrangereDAO;
import dao.Manager.DetailReceptionDAO;
import dao.Manager.DetailReceptionEtrangereDAO;
import dao.Manager.DeviseDAO;
import dao.Manager.SequenceurDAO;
import dao.ManagerImpl.ClientMPDAOImpl;
import dao.ManagerImpl.CommandeEtrangereDAOImpl;
import dao.ManagerImpl.DetailCommandeEtrangereDAOImpl;
import dao.ManagerImpl.DetailReceptionDAOImpl;
import dao.ManagerImpl.DetailReceptionEtrangereDAOImpl;
import dao.ManagerImpl.DeviseDAOImpl;
import dao.ManagerImpl.SequenceurDAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javax.swing.SpringLayout;
/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ChargementEtrangereController implements Initializable {


    @FXML
    private TableView<CommandeEtrangere> tableCommandeEtr;
    @FXML
    private TableColumn<CommandeEtrangere, String> numCommandeColumn;
    @FXML
    private TableColumn<CommandeEtrangere, Date> dateCommandeColumn;
    @FXML
    private TableColumn<CommandeEtrangere, String> fournisseurColumn;
    @FXML
    private TableColumn<CommandeEtrangere, String> lieuDepartColumn;
    @FXML
    private TableColumn<CommandeEtrangere, String> lieuArriveeColumn;
    @FXML
    private TableColumn<CommandeEtrangere, Date> dateDepartColumn;
    @FXML
    private TableColumn<CommandeEtrangere, Date> dateArriveeColumn;
    @FXML
    private TableColumn<CommandeEtrangere, String> etatColumn;
    
    
    @FXML
    private TableView<DetailCommandeEtrangere> tableDetailCommandeEtr;
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> codeColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> articleColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> qteColumn; 
    @FXML
    private TableColumn<DetailCommandeEtrangere, String> deviseColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> prixDeviseColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> prixMadColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> qteRestantColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> qteLivreeColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> montantMadColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> montantNbrColumn;
    @FXML
    private TableColumn<DetailCommandeEtrangere, BigDecimal> poidsColumn;
    @FXML
    private ComboBox<String> clientCombo;
    @FXML
    private TextField numComRech;
    @FXML
    private DatePicker dateComRech;
    @FXML
    private TextField numConteneur;
    @FXML
    private TextField numFacture;
    @FXML
    private DatePicker dateFacture;
    @FXML
    private DatePicker dateChargement;
    @FXML
    private TextField numPoking;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;
    
     private ObservableList<DetailCommandeEtrangere> listeDetailCommandeEtrangere=FXCollections.observableArrayList();
    private final ObservableList<CommandeEtrangere> listeCommandeEtrangere=FXCollections.observableArrayList();
    
    DetailCommandeEtrangereDAO  detailCommandeEtrangereDAO = new DetailCommandeEtrangereDAOImpl();

    CommandeEtrangereDAO commandeEtrangereDAO = new CommandeEtrangereDAOImpl();
     SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
        DetailReceptionEtrangereDAO detailReceptionEtrangereDAO = new DetailReceptionEtrangereDAOImpl();
      private final Map<String,ClientMP> mapClientMP=new HashMap<>();
           ClientMPDAO clientMPDAO = new ClientMPDAOImpl();
    
     navigation nav = new navigation();
     CommandeEtrangere commandeEtrangere;
     
     DetailCommandeEtrangere detailCommandeEtrangere;

   String codeReceptionEtrangere = "";

   
       BigDecimal qteLiv = BigDecimal.ZERO;
    
   
    /**
     * Initializes the controller class.
    */ 
       
     void Incrementation (){
       
          Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RECEPTION_ETRANGERE);
          codeReceptionEtrangere = Constantes.RECEPTION_ETRANGERE+" "+(sequenceur.getValeur()+1);
   }  
       
       
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setColumnProperties();
        loadDetail();

         Incrementation();
        
           List<ClientMP> listClientMP=clientMPDAO.findAll();
        
        listClientMP.stream().map((clientMP) -> {
            clientCombo.getItems().addAll(clientMP.getNom());
            return clientMP;
        }).forEachOrdered((clientMP) -> {
            mapClientMP.put(clientMP.getNom(), clientMP);
        });
        
    }    
    
    
     void setColumnProperties(){
        

             numCommandeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getnCommande());
                }
              });
             
             dateCommandeColumn.setCellValueFactory(new PropertyValueFactory<CommandeEtrangere, Date>("dateCommande"));
             
             fournisseurColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<CommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getFourisseur().getNom());
                }
              });
             
             lieuArriveeColumn.setCellValueFactory(new PropertyValueFactory<CommandeEtrangere, String>("lieuArrivee"));
                
             lieuDepartColumn.setCellValueFactory(new PropertyValueFactory<CommandeEtrangere, String>("lieuDepart"));
             
             dateArriveeColumn.setCellValueFactory(new PropertyValueFactory<CommandeEtrangere, Date>("dateArrivee"));

             dateDepartColumn.setCellValueFactory(new PropertyValueFactory<CommandeEtrangere, Date>("dateDepart"));

             etatColumn.setCellValueFactory(new PropertyValueFactory<CommandeEtrangere, String>("etat"));
        

        tableCommandeEtr.setEditable(false);
 
    }
    
     void loadDetail(){
        
        listeCommandeEtrangere.clear();
        listeCommandeEtrangere.addAll(commandeEtrangereDAO.findCommandeEtrangereByEtat(Constantes.ETAT_COMMANDE_LANCE));
        tableCommandeEtr.setItems(listeCommandeEtrangere);
    }
    
    
     void setColumnPropertiesDetailCommande(){
        
          codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getArticle().getCode());
                }
             });
          
          articleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getArticle().getLibelle());
                }
             });
          
          deviseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getTauxDevise().getDevise().getCode());
                }
             });
                      
          qteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });

          
           qteLivreeColumn.setCellValueFactory(new PropertyValueFactory<DetailCommandeEtrangere, BigDecimal>("quantiteLivree"));

        setColumnTextFieldConverter(getConverter(), qteLivreeColumn);
          
          qteRestantColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal> p) {
  
                      DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantiteRestant()));
                }
                
             });
          
           
          prixMadColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrix()));
                }
                
             });
     
           
          prixDeviseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal> p) {
                                DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrixDevise()));
                }
                
                    });
          
         poidsColumn.setCellValueFactory(new PropertyValueFactory<DetailCommandeEtrangere, BigDecimal>("poidsCarton"));

        setColumnTextFieldConverter(getConverter(), poidsColumn);
          
          
         montantNbrColumn.setCellValueFactory(new PropertyValueFactory<DetailCommandeEtrangere, BigDecimal>("nbrCarton"));

        setColumnTextFieldConverter(getConverter(), montantNbrColumn);
        
        
          
          montantMadColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommandeEtrangere, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getMontant()));
                }
                
                    });
    }

  public static void setColumnTextFieldConverter(StringConverter converter, TableColumn... columns) {

        for (TableColumn tableColumn : columns) {

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        }
    }

    private StringConverter getConverter() {
        StringConverter<BigDecimal> converter = new StringConverter<BigDecimal>(){
            @Override
            public BigDecimal fromString(String string) {

                try {
                    BigDecimal valeur;
                    valeur= new BigDecimal(string);
                    
                    return valeur;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString(BigDecimal object) {

                if (object == null) {
                    return null;
                }
                return  String.valueOf(object);
            }
        };

        return converter;
    }
  
    void loadDetailCommande (){

        commandeEtrangere = detailCommandeEtrangere.getCommandeEtrangere();

        setColumnPropertiesDetailCommande();
        listeDetailCommandeEtrangere.clear();
        listeDetailCommandeEtrangere.addAll(detailCommandeEtrangereDAO.findDetailCommandeEtrangereByCommandeEtr(commandeEtrangere));
        tableDetailCommandeEtr.setItems(listeDetailCommandeEtrangere);
    }

    @FXML
    private void afficherDetailOnMouseClicked(MouseEvent event) {
        
          setColumnPropertiesDetailCommande();
          
            listeDetailCommandeEtrangere.clear();
            if (tableCommandeEtr.getSelectionModel().getSelectedIndex()!=-1){
            commandeEtrangere=tableCommandeEtr.getSelectionModel().getSelectedItem();
               
            
            tableDetailCommandeEtr.setEditable(true);
            
              listeDetailCommandeEtrangere.addAll(detailCommandeEtrangereDAO.findDetailCommandeEtrangereByCommandeEtr(commandeEtrangere));
        tableDetailCommandeEtr.setItems(listeDetailCommandeEtrangere);

            }

    }

    @FXML
    private void tableDetailClicked(MouseEvent event) {
    }


    @FXML
    private void ajouterOnAction(ActionEvent event) throws ParseException {
        
        
      
       if (tableCommandeEtr.getSelectionModel().getSelectedItem() == null ||
               numConteneur.getText().isEmpty() ||
               numFacture.getText().isEmpty() ||
               numPoking.getText().isEmpty() ||
               dateChargement.getValue()== null ||
               dateFacture.getValue()== null
               ) {
           
             nav.showAlert(Alert.AlertType.CONFIRMATION, "Erreur", null, Constantes.VERIFICATION_SELECTION_LIGNE); 
             
       }else {
       
            ClientMP clientMP=mapClientMP.get(clientCombo.getSelectionModel().getSelectedItem());
          
            LocalDate localDateC=dateChargement.getValue();
            
            Date dateCharg=new SimpleDateFormat("yyyy-MM-dd").parse(localDateC.toString());
          
          
            LocalDate localDateF=dateFacture.getValue();
            Date dateFact=new SimpleDateFormat("yyyy-MM-dd").parse(localDateF.toString());

            System.out.println("listeDetailCommandeEtrangere "+listeDetailCommandeEtrangere.size());
            
            for( int rows = 0;rows<listeDetailCommandeEtrangere.size() ;rows++ ){
        

            DetailCommandeEtrangere detailCommandeEtrangere = listeDetailCommandeEtrangere.get(rows);

             
            DetailReceptionEtrangere detailReceptionEtrangere = new DetailReceptionEtrangere();
            
            
                detailReceptionEtrangere.setDetailCommandeEtrangere(detailCommandeEtrangere);
                detailReceptionEtrangere.setDateReception(new Date());
                detailReceptionEtrangere.setDateFacture(dateFact);
                detailReceptionEtrangere.setImportateur(clientMP.getNom());
                detailReceptionEtrangere.setNumFacture(numFacture.getText());
                detailReceptionEtrangere.setNumPoking(numPoking.getText());
                detailReceptionEtrangere.setNumConteneur(numConteneur.getText());
                detailReceptionEtrangere.setNbrCarton(detailCommandeEtrangere.getNbrCarton());
                detailReceptionEtrangere.setPoidCarton(detailCommandeEtrangere.getPoidsCarton());
                detailReceptionEtrangere.setUtilisateurCreation(nav.getUtilisateur());
                detailReceptionEtrangere.setQuantiteLivree(detailCommandeEtrangere.getQuantiteLivree());
                detailReceptionEtrangere.setQuantiteRecu(BigDecimal.ZERO);
                detailReceptionEtrangere.setPrix(detailCommandeEtrangere.getPrix());
                detailReceptionEtrangere.setNumReception(codeReceptionEtrangere);

                detailReceptionEtrangereDAO.add(detailReceptionEtrangere);
                
  
                    
                listeDetailCommandeEtrangere.set(rows, detailCommandeEtrangere);
                detailCommandeEtrangereDAO.edit(detailCommandeEtrangere);
                
                }
          
           
            commandeEtrangere=tableCommandeEtr.getSelectionModel().getSelectedItem();
            commandeEtrangere.setEtat(Constantes.ETAT_COMMANDE_EN_ROUTE);
            commandeEtrangere.setDateChargement(dateCharg);
            
            commandeEtrangereDAO.edit(commandeEtrangere);
            
                   nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null, Constantes.COMMANDE_VALIDER); 
  
                   
            Sequenceur sequenceur = sequenceurDAO.findByCode(Constantes.RECEPTION_ETRANGERE);
            sequenceur.setValeur(sequenceur.getValeur()+1);
            sequenceurDAO.edit(sequenceur);
            Incrementation ();
                   
            setColumnPropertiesDetailCommande();
            loadDetail();
              
               numConteneur.clear();
               numFacture.clear();
               numPoking.clear();
               dateChargement.setValue(null);
               dateFacture.setValue(null);
                         
              
       }
        
        
    }

    @FXML  
    private void supprimerOnAction(ActionEvent event) {
    }

    @FXML
    private void modifierOnAction(ActionEvent event) {
    }

    @FXML
    private void rechercheNumLivKeyPressed(KeyEvent event) {
    }

    @FXML
    private void editCommitQuantiteLivreeColumn(TableColumn.CellEditEvent<DetailCommandeEtrangere, BigDecimal> event) {
        
          if (numConteneur.getText().equalsIgnoreCase("") || 
                  numFacture.getText().equalsIgnoreCase("") ||
                  numPoking.getText().equalsIgnoreCase("") || 
                  clientCombo.getSelectionModel().getSelectedItem()== null ||
                  clientCombo.getSelectionModel().getSelectedItem().isEmpty() ||
                  dateChargement.getValue()== null ||
                  dateFacture.getValue() == null){

            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
            tableDetailCommandeEtr.refresh();
        } else {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    qteLiv = BigDecimal.ZERO;
                    BigDecimal qteRestee= BigDecimal.ZERO;
                    BigDecimal getCalculeQuantiteRestee= BigDecimal.ZERO;
                    BigDecimal montantMad = BigDecimal.ZERO;
                    
                
                    
                ((DetailCommandeEtrangere) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setQuantiteLivree(event.getNewValue());
               
                
                    tableDetailCommandeEtr.refresh();  

  
                 qteLiv = qteLivreeColumn.getCellData(event.getTablePosition().getRow());

                 qteRestee =  listeDetailCommandeEtrangere.get(tableDetailCommandeEtr.getSelectionModel().getSelectedIndex()).getQuantiteRestant();

                 getCalculeQuantiteRestee =  qteRestee.subtract(qteLiv).setScale(2, RoundingMode.FLOOR);

                 montantMad =listeDetailCommandeEtrangere.get(tableDetailCommandeEtr.getSelectionModel().getSelectedIndex()).getPrix().multiply(qteLiv).setScale(2, RoundingMode.FLOOR);

                 listeDetailCommandeEtrangere.get(tableDetailCommandeEtr.getSelectionModel().getSelectedIndex()).setQuantiteRestant(getCalculeQuantiteRestee);
                 listeDetailCommandeEtrangere.get(tableDetailCommandeEtr.getSelectionModel().getSelectedIndex()).setMontant(montantMad);
 
                 
                setColumnProperties();

           

            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailCommandeEtr.refresh();
            }
        }
        
    }

    @FXML
    private void editCommitPoidsColumn(TableColumn.CellEditEvent<DetailCommandeEtrangere, BigDecimal> event) {
        
           if (numConteneur.getText().equalsIgnoreCase("") || 
                   numFacture.getText().equalsIgnoreCase("") ||
                   numPoking.getText().equalsIgnoreCase("") ||
                   clientCombo.getSelectionModel().getSelectedItem()== null ||
                  clientCombo.getSelectionModel().getSelectedItem().isEmpty() ||
                  dateChargement.getValue()== null ||
                  dateFacture.getValue() == null) {

            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
     
        
           }else if(qteLiv == BigDecimal.ZERO){
                    
                    nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.VERIFIER_QTE_LIVREE);
            
           } else {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                    BigDecimal poidsCarton = BigDecimal.ZERO;
                    BigDecimal nbrCarton = BigDecimal.ZERO;

                    
                
                    
                ((DetailCommandeEtrangere) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setPoidsCarton(event.getNewValue());
               
                
                    tableDetailCommandeEtr.refresh();  

  
                 poidsCarton = poidsColumn.getCellData(event.getTablePosition().getRow());

                 nbrCarton =  poidsCarton.multiply(qteLiv).setScale(2, RoundingMode.FLOOR);

                 
                 listeDetailCommandeEtrangere.get(tableDetailCommandeEtr.getSelectionModel().getSelectedIndex()).setNbrCarton(nbrCarton);

 
                 
                setColumnProperties();

           

            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailCommandeEtr.refresh();
            }
        }
        
    }


    @FXML
    private void clientOnAction(ActionEvent event) {
    }




}
