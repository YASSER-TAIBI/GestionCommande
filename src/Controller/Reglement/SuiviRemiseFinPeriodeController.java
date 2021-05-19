/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Utils.Constantes;
import dao.Entity.BaremeTaux;
import dao.Entity.BonLivraison;
import dao.Entity.ChiffreAffaireBrut;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailRecuPromotionAccordee;
import dao.Entity.DetailRemiseFinPeriode;
import dao.Entity.RecuPromotionAccordee;
import dao.Entity.RemiseFinPeriode;
import dao.Entity.SituationRecuPromoAcco;
import dao.Entity.SituationRemiseFinPeriode;
import dao.Manager.AvoirOulmesDAO;
import dao.Manager.BaremeTauxDAO;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.ChiffreAffaireBrutDAO;
import dao.Manager.DetailAvoirOulmesDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailPromotionAccordeeDAO;
import dao.Manager.DetailPromotionDAO;
import dao.Manager.DetailRecuPromotionAccordeeDAO;
import dao.Manager.DetailRemiseFinPeriodeDAO;
import dao.Manager.RecuPromotionAccordeeDAO;
import dao.Manager.RemiseFinPeriodeDAO;
import dao.ManagerImpl.AvoirOulmesDAOImpl;
import dao.ManagerImpl.BaremeTauxDAOImpl;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.ChiffreAffaireBrutDAOImpl;
import dao.ManagerImpl.DetailAvoirOulmesDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailPromotionAccordeeDAOImpl;
import dao.ManagerImpl.DetailPromotionDAOImpl;
import dao.ManagerImpl.DetailRecuPromotionAccordeeDAOImpl;
import dao.ManagerImpl.DetailRemiseFinPeriodeDAOImpl;
import dao.ManagerImpl.RecuPromotionAccordeeDAOImpl;
import dao.ManagerImpl.RemiseFinPeriodeDAOImpl;
import function.navigation;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SuiviRemiseFinPeriodeController implements Initializable {

    @FXML
    private TableView<SituationRemiseFinPeriode> tableRemiseFinPeriode;
    @FXML
    private TableColumn<SituationRemiseFinPeriode, Integer> moisColumn;
    @FXML
    private TableColumn<SituationRemiseFinPeriode, BigDecimal> caColumn;
    @FXML
    private TableColumn<SituationRemiseFinPeriode, BigDecimal> objColumn;
    @FXML
    private TableColumn<SituationRemiseFinPeriode, BigDecimal> troColumn;
    @FXML
    private TableColumn<SituationRemiseFinPeriode, BigDecimal> tauxRfpColumn;
    @FXML
    private TableColumn<SituationRemiseFinPeriode, BigDecimal> rfpColumn;
    @FXML
    private TableColumn<SituationRemiseFinPeriode, String> validerColumn;
    @FXML
    private TableColumn<SituationRemiseFinPeriode, BigDecimal> livGmsColumn;
    @FXML
    private TableColumn<SituationRemiseFinPeriode, BigDecimal> remiseColumn;
    @FXML
    private TableColumn<SituationRemiseFinPeriode, BigDecimal> rfpReelColumn;
    @FXML
    private TableColumn<SituationRemiseFinPeriode, BigDecimal> caNetColumn;
    
    @FXML
    private TableView<RecuPromotionAccordee> tableDetailRemiseFinPeriode;
    @FXML
    private TableColumn<RecuPromotionAccordee, Integer> moisDetailColumn;
    @FXML
    private TableColumn<RecuPromotionAccordee, BigDecimal> rfpEnAttColumn;
    @FXML
    private TableColumn<RecuPromotionAccordee, BigDecimal> recuColumn;
    @FXML
    private TableColumn<RecuPromotionAccordee, BigDecimal> factRecuColumn;
    @FXML
    private TableColumn<RecuPromotionAccordee, BigDecimal> promoAccoColumn;
    @FXML
    private TableColumn<RecuPromotionAccordee, String> validerDetailColumn;
    @FXML
    private TableColumn<RecuPromotionAccordee, BigDecimal> totalRecuColumn;
    
    @FXML
    private TableView<DetailRecuPromotionAccordee> tableDetailRecuPromotionAccordee;
    @FXML
    private TableColumn<DetailRecuPromotionAccordee, Date> dateCreationColumn;
    @FXML
    private TableColumn<DetailRecuPromotionAccordee, String> numFactColumn;
    @FXML
    private TableColumn<DetailRecuPromotionAccordee, Date> dateFactColumn;
    @FXML
    private TableColumn<DetailRecuPromotionAccordee, BigDecimal> factureRecuColumn;
    
    
    @FXML
    private Label montantRecu;
    @FXML
    private Label montantPromoAcco;
    @FXML
    private Label solde;
    
    @FXML
    private TextField numFactField;
    @FXML
    private DatePicker dateFact;
    /**
     * Initializes the controller class.
     */
    
      private final  ObservableList<SituationRemiseFinPeriode> listSituationRemiseFinPeriode = FXCollections.observableArrayList();    
      private final  ObservableList<RecuPromotionAccordee> listRecuPromotionAccordee = FXCollections.observableArrayList();
      private final  ObservableList<DetailRecuPromotionAccordee> listDetailRecuPromotionAccordee = FXCollections.observableArrayList();
      private final  ObservableList<DetailRemiseFinPeriode> listDetailRemiseFinPeriode = FXCollections.observableArrayList();
              
      DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
      ChiffreAffaireBrutDAO chiffreAffaireBrutDAO = new ChiffreAffaireBrutDAOImpl();
      DetailAvoirOulmesDAO detailAvoirOulmesDAO = new DetailAvoirOulmesDAOImpl();
      BaremeTauxDAO baremeTauxDAO = new BaremeTauxDAOImpl();
      RemiseFinPeriodeDAO remiseFinPeriodeDAO = new RemiseFinPeriodeDAOImpl();
      DetailRemiseFinPeriodeDAO detailRemiseFinPeriodeDAO = new DetailRemiseFinPeriodeDAOImpl();
      DetailPromotionDAO detailPromotionDAO = new DetailPromotionDAOImpl();
      DetailPromotionAccordeeDAO detailPromotionAccordeeDAO = new DetailPromotionAccordeeDAOImpl();
      RecuPromotionAccordeeDAO recuPromotionAccordeeDAO = new RecuPromotionAccordeeDAOImpl();
      DetailRecuPromotionAccordeeDAO detailRecuPromotionAccordeeDAO = new DetailRecuPromotionAccordeeDAOImpl();
      AvoirOulmesDAO avoirOulmesDAO = new AvoirOulmesDAOImpl();
      RemiseFinPeriode remiseFinPeriode= new RemiseFinPeriode();
      
        navigation nav = new navigation();  

    
    
    
    
    
    
         
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        loadDetail();
        setColumnProperties();
        
        
      loadDetailRecuPromoAcc();               
      setColumnPropertiesDetail();  
      calcule ();
    }    

    void calcule (){
    
          BigDecimal montantRecu= BigDecimal.ZERO;
             BigDecimal montantPromoAcco= BigDecimal.ZERO;
             BigDecimal somme=BigDecimal.ZERO;
             
           for( int rows = 0;rows<listRecuPromotionAccordee.size() ;rows++ ){

               RecuPromotionAccordee recuPromotionAccordee = listRecuPromotionAccordee.get(rows);
               
            montantRecu = montantRecu.add(recuPromotionAccordee.getTotalRecu());  

            montantPromoAcco = montantPromoAcco.add(recuPromotionAccordee.getPromoAcco());  
            
    }
           
              DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
           
         this.montantRecu.setText(df.format(montantRecu));  
         this.montantPromoAcco.setText(df.format(montantPromoAcco));  

         somme = montantRecu.subtract(montantPromoAcco) ;
          
         solde.setText(df.format(somme));  
  
    }
    
       void setColumnPropertiesDetailFact(){

          
             DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
          
                
             dateCreationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailRecuPromotionAccordee, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<DetailRecuPromotionAccordee, Date> p) {

                    return new ReadOnlyObjectWrapper(p.getValue().getDateCreation());
                }
                
             });

             
             numFactColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailRecuPromotionAccordee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailRecuPromotionAccordee, String> p) {

                    return new ReadOnlyObjectWrapper(p.getValue().getNumFacture());
                }
                
             });  
             
              dateFactColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailRecuPromotionAccordee, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<DetailRecuPromotionAccordee, Date> p) {

                    return new ReadOnlyObjectWrapper(p.getValue().getDateFacture());
                }
                
             }); 


              factureRecuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailRecuPromotionAccordee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailRecuPromotionAccordee, BigDecimal> p) {
                    

                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getRecuFacture()));
                }
                
             }); }
    
       
       void loadDetailRecuFacture(){
    
       listDetailRecuPromotionAccordee.clear();
       listDetailRecuPromotionAccordee.addAll(detailRecuPromotionAccordeeDAO.findAll());
       tableDetailRecuPromotionAccordee.setItems(listDetailRecuPromotionAccordee);
    
    }
       
   void loadDetailRecuPromoAcc(){
    
       listRecuPromotionAccordee.clear();
       listRecuPromotionAccordee.addAll(recuPromotionAccordeeDAO.findAll());
       tableDetailRemiseFinPeriode.setItems(listRecuPromotionAccordee);
    
    }
    
      void setColumnPropertiesDetail(){

          
             DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
          
                
             moisDetailColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RecuPromotionAccordee, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<RecuPromotionAccordee, Integer> p) {

                    return new ReadOnlyObjectWrapper(p.getValue().getMois());
                }
                
             });

             
             recuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RecuPromotionAccordee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<RecuPromotionAccordee, BigDecimal> p) {

                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getRecu()));
                }
                
             });  
             
              totalRecuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RecuPromotionAccordee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<RecuPromotionAccordee, BigDecimal> p) {

                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getTotalRecu()));
                }
                
             }); 

             factRecuColumn.setCellValueFactory(new PropertyValueFactory<RecuPromotionAccordee, BigDecimal>("recuFacture"));
             setColumnTextFieldConverter(getConverter(), factRecuColumn);

             
              promoAccoColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RecuPromotionAccordee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<RecuPromotionAccordee, BigDecimal> p) {
                    

                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPromoAcco()));
                }
                
             }); 
             
         rfpEnAttColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RecuPromotionAccordee, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<RecuPromotionAccordee, BigDecimal> p) {
                    

                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getRfpEnAtt()));
                }
                
             });   
         
//###################################################################################################################################################################################################################################################################################################################################################################
//##############################################################################################################################################################################################################################################################################################################################                   
             
         //add cell of button edit 
         Callback<TableColumn<RecuPromotionAccordee, String>, TableCell<RecuPromotionAccordee, String>> cellFoctory = (TableColumn<RecuPromotionAccordee, String> param) -> {
            // make cell containing buttons
            final TableCell<RecuPromotionAccordee, String> cell = new TableCell<RecuPromotionAccordee, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                          Image image= new Image(getClass().getResourceAsStream("/Styles/img/Pngtreevalidvectoricon_3723583-ConvertImage.png"));
                          ImageView validerImage = new ImageView(image);

                        validerImage.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:31px;"
                                + "-fx-fill:#00E676;"
                        );
               
                        validerImage.setOnMouseClicked((MouseEvent event) -> {
                   
                            
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
                            alert.setTitle("Confirmation");
                            Optional<ButtonType> result = alert.showAndWait();

                            if (result.get() == ButtonType.OK) {
  
                                    RecuPromotionAccordee recuPromotionAccordee = tableDetailRemiseFinPeriode.getSelectionModel().getSelectedItem();
                                
                                if(dateFact.getValue()== null || numFactField.getText().isEmpty() || recuPromotionAccordee.getRecuFacture().compareTo(BigDecimal.ZERO)==0){
                                
                                   nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.REMPLIR_CHAMPS);
                                   return;
                                   
                                }else{
                                
                                    Date dateFacture = null;
                                try {
                                    dateFacture = new SimpleDateFormat("yyyy-MM-dd").parse(dateFact.getValue().toString());
                                } catch (ParseException ex) {
                                    Logger.getLogger(SuiviRemiseFinPeriodeController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                      DetailRecuPromotionAccordee detailRecuPromotionAccordee = new DetailRecuPromotionAccordee();
                                   
                                   detailRecuPromotionAccordee.setDateFacture(dateFacture);
                                   detailRecuPromotionAccordee.setNumFacture(numFactField.getText());
                                   detailRecuPromotionAccordee.setRecuFacture(recuPromotionAccordee.getRecuFacture());
                                   detailRecuPromotionAccordee.setRecuPromotionAccordee(recuPromotionAccordee);
                                   detailRecuPromotionAccordee.setUtilisateurCreation(nav.getUtilisateur());
                                   detailRecuPromotionAccordee.setDateCreation(new Date());
                                   
                                  detailRecuPromotionAccordeeDAO.add(detailRecuPromotionAccordee);

                                           
                                recuPromotionAccordee.setTotalRecu(recuPromotionAccordee.getTotalRecu().add(recuPromotionAccordee.getRecuFacture()));
                                recuPromotionAccordee.setRfpEnAtt(recuPromotionAccordee.getRfpEnAtt().subtract(recuPromotionAccordee.getRecuFacture()));
                                recuPromotionAccordee.setRecuFacture(BigDecimal.ZERO);

                                recuPromotionAccordeeDAO.edit(recuPromotionAccordee);
                                
                                
                             
            
      loadDetailRecuPromoAcc();               
      setColumnPropertiesDetail();  
      calcule ();
      
      numFactField.clear();
      dateFact.setValue(null);
      
      
                            }}
                            });

                        HBox managebtn = new HBox(validerImage);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(validerImage, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         validerDetailColumn.setCellFactory(cellFoctory);

//###################################################################################################################################################################################################################################################################################################################################################################
     
        validerDetailColumn.setEditable(true);
        
        tableDetailRemiseFinPeriode.setEditable(true);         
         
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
    
      void setColumnProperties(){

             moisColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationRemiseFinPeriode, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<SituationRemiseFinPeriode, Integer> p) {
                    
  
                    return new ReadOnlyObjectWrapper(p.getValue().getMois());
                }
                
             });

            caColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getChiffreAffaire()));
                }
                
             });
           
         objColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getObjectif()));
                }
                
             });
         
         
         troColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getTroCumule()));
                }
                
             });
                 
         tauxRfpColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getTauxRfp()));
                }
                
             });
                 
         rfpColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getRfp()));
                }
                
             });        
                 
         
          livGmsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getLivGms()));
                }
                
             });        
          
           remiseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getRemise()));
                }
                
             });        
           
           rfpReelColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getRfpReel()));
                }
                
             });    
           
           caNetColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal>, ObservableValue<BigDecimal>>() {
            @Override
            public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<SituationRemiseFinPeriode, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getCaNet()));
                }
                
             });  
//###################################################################################################################################################################################################################################################################################################################################################################
//##############################################################################################################################################################################################################################################################################################################################                   
             
         //add cell of button edit 
         Callback<TableColumn<SituationRemiseFinPeriode, String>, TableCell<SituationRemiseFinPeriode, String>> cellFoctory = (TableColumn<SituationRemiseFinPeriode, String> param) -> {
            // make cell containing buttons
            final TableCell<SituationRemiseFinPeriode, String> cell = new TableCell<SituationRemiseFinPeriode, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                          Image image= new Image(getClass().getResourceAsStream("/Styles/img/Pngtreevalidvectoricon_3723583-ConvertImage.png"));
                          ImageView validerImage = new ImageView(image);

                        validerImage.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:31px;"
                                + "-fx-fill:#00E676;"
                        );
               
                        validerImage.setOnMouseClicked((MouseEvent event) -> {
                   
                            
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText(Constantes.MESSAGE_ALERT_CONTINUER);
                            alert.setTitle("Confirmation");
                            Optional<ButtonType> result = alert.showAndWait();

                            if (result.get() == ButtonType.OK) {
  
      
                                SituationRemiseFinPeriode situationRemiseFinPeriode = tableRemiseFinPeriode.getSelectionModel().getSelectedItem();

                                RemiseFinPeriode remiseFinPeriodeTMP = remiseFinPeriodeDAO.findByAnnee("2021");

                           if (remiseFinPeriodeTMP==null){
                               
                                DetailRemiseFinPeriode detailRemiseFinPeriodeTMP =new DetailRemiseFinPeriode();
                               
                               detailRemiseFinPeriodeTMP.setChiffreAffaire(situationRemiseFinPeriode.getChiffreAffaire());
                               detailRemiseFinPeriodeTMP.setChiffreAffaireNet(situationRemiseFinPeriode.getCaNet());
                               detailRemiseFinPeriodeTMP.setLivraisonGMS(situationRemiseFinPeriode.getLivGms());
                               detailRemiseFinPeriodeTMP.setRemise(situationRemiseFinPeriode.getRemise());
                               detailRemiseFinPeriodeTMP.setRfpReel(situationRemiseFinPeriode.getRfpReel());
                               detailRemiseFinPeriodeTMP.setMois(situationRemiseFinPeriode.getMois());
                               detailRemiseFinPeriodeTMP.setObjectif(situationRemiseFinPeriode.getObjectif());
                               detailRemiseFinPeriodeTMP.setDateCreation(new Date());
                               detailRemiseFinPeriodeTMP.setTauxRfp(situationRemiseFinPeriode.getTauxRfp());
                               detailRemiseFinPeriodeTMP.setTroCumule(situationRemiseFinPeriode.getTroCumule());
                               detailRemiseFinPeriodeTMP.setRfp(situationRemiseFinPeriode.getRfp());
                               detailRemiseFinPeriodeTMP.setRemiseFinPeriode(remiseFinPeriode);
                               detailRemiseFinPeriodeTMP.setUtilisateurCreation(nav.getUtilisateur());
                               
                               listDetailRemiseFinPeriode.add(detailRemiseFinPeriodeTMP);
                               
                               
                               remiseFinPeriode.setAnnee("2021");
                               remiseFinPeriode.setEtat("Ouverte");
                               remiseFinPeriode.setDetailRemiseFinPeriode(listDetailRemiseFinPeriode);
                               remiseFinPeriode.setDateCreation(new Date());
                               remiseFinPeriode.setUtilisateurCreation(nav.getUtilisateur());
                               
                               remiseFinPeriodeDAO.add(remiseFinPeriode);
                               
                               remiseFinPeriode = new RemiseFinPeriode();
                               
//##############################################################################################################################################################################################################################################################################################################################                   

                             BigDecimal montantPromo =  detailPromotionAccordeeDAO.findByMontantPromoAcco(situationRemiseFinPeriode.getMois());
                             
//##############################################################################################################################################################################################################################################################################################################################                   
     
                       RecuPromotionAccordee recuPromotionAccordee =new RecuPromotionAccordee();
                                   
                                recuPromotionAccordee.setAnnee("2021");
                                recuPromotionAccordee.setMois(situationRemiseFinPeriode.getMois());
                                recuPromotionAccordee.setRfpEnAtt(situationRemiseFinPeriode.getRfpReel());
                                recuPromotionAccordee.setPromoAcco(montantPromo);
                                recuPromotionAccordee.setRecu(situationRemiseFinPeriode.getRemise());
                                recuPromotionAccordee.setRecuFacture(BigDecimal.ZERO);
                                recuPromotionAccordee.setTotalRecu(situationRemiseFinPeriode.getRemise());
                                
                                
                                recuPromotionAccordeeDAO.add(recuPromotionAccordee);
  
       loadDetailRecuPromoAcc();               
      setColumnPropertiesDetail();  
      calcule ();      
                           }else{
                           
                                        List<DetailRemiseFinPeriode> listDetailRemiseFinPeriode  = detailRemiseFinPeriodeDAO.findByRemiseFinPeriode(remiseFinPeriodeTMP.getId());
                                        
                                        for (int i = 0; i < listDetailRemiseFinPeriode.size(); i++) {
                                   
                                     DetailRemiseFinPeriode detailRemiseFinPeriode = listDetailRemiseFinPeriode.get(i);
                                            
                                      if (detailRemiseFinPeriode.getMois()==situationRemiseFinPeriode.getMois()){
                                          
                                             nav.showAlert(Alert.AlertType.ERROR, "Erreur", null, Constantes.ATTENTION_MOIS_TRAITE);  
                                            return;
                                      }
                               }
                                        
                                                      
                              DetailRemiseFinPeriode detailRemiseFinPeriodeTMP =new DetailRemiseFinPeriode();
                               
                               detailRemiseFinPeriodeTMP.setChiffreAffaire(situationRemiseFinPeriode.getChiffreAffaire());
                               detailRemiseFinPeriodeTMP.setChiffreAffaireNet(situationRemiseFinPeriode.getCaNet());
                               detailRemiseFinPeriodeTMP.setLivraisonGMS(situationRemiseFinPeriode.getLivGms());
                               detailRemiseFinPeriodeTMP.setRemise(situationRemiseFinPeriode.getRemise());
                               detailRemiseFinPeriodeTMP.setRfpReel(situationRemiseFinPeriode.getRfpReel());
                               detailRemiseFinPeriodeTMP.setMois(situationRemiseFinPeriode.getMois());
                               detailRemiseFinPeriodeTMP.setObjectif(situationRemiseFinPeriode.getObjectif());
                               detailRemiseFinPeriodeTMP.setDateCreation(new Date());
                               detailRemiseFinPeriodeTMP.setTauxRfp(situationRemiseFinPeriode.getTauxRfp());
                               detailRemiseFinPeriodeTMP.setTroCumule(situationRemiseFinPeriode.getTroCumule());
                               detailRemiseFinPeriodeTMP.setRfp(situationRemiseFinPeriode.getRfp());
                               detailRemiseFinPeriodeTMP.setRemiseFinPeriode(remiseFinPeriodeTMP);
                               detailRemiseFinPeriodeTMP.setUtilisateurCreation(nav.getUtilisateur());
                               
                               
                               detailRemiseFinPeriodeDAO.add(detailRemiseFinPeriodeTMP);
                               
//##############################################################################################################################################################################################################################################################################################################################                   

                             BigDecimal montantPromo =  detailPromotionAccordeeDAO.findByMontantPromoAcco(situationRemiseFinPeriode.getMois());
                             
//##############################################################################################################################################################################################################################################################################################################################                   
      
            RecuPromotionAccordee recuPromotionAccordee =new RecuPromotionAccordee();
                                   
                                recuPromotionAccordee.setAnnee("2021");
                                recuPromotionAccordee.setMois(situationRemiseFinPeriode.getMois());
                                recuPromotionAccordee.setRfpEnAtt(situationRemiseFinPeriode.getRfpReel());
                                recuPromotionAccordee.setPromoAcco(montantPromo);
                                recuPromotionAccordee.setRecu(situationRemiseFinPeriode.getRemise());
                                recuPromotionAccordee.setRecuFacture(BigDecimal.ZERO);
                                recuPromotionAccordee.setTotalRecu(situationRemiseFinPeriode.getRemise());
                                
                                
                                recuPromotionAccordeeDAO.add(recuPromotionAccordee);
  
      loadDetailRecuPromoAcc();               
      setColumnPropertiesDetail();  
      calcule ();                                                        }
      
                            };
                            });

                        HBox managebtn = new HBox(validerImage);
                        managebtn.setStyle("-fx-alignment:center");
//                        HBox.setMargin(validerImage, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         validerColumn.setCellFactory(cellFoctory);

//###################################################################################################################################################################################################################################################################################################################################################################
     
        validerColumn.setEditable(true);
        
        tableRemiseFinPeriode.setEditable(true); 
    }
         

    void loadDetail() {

 List <Object[]> listDetailBonLivraison =detailBonLivraisonDAO.findByChiffreAffraire();

listSituationRemiseFinPeriode.clear();
         
                BigDecimal sommeCA = BigDecimal.ZERO;
                BigDecimal sommeObj = BigDecimal.ZERO;
                BigDecimal sommeLivGms = BigDecimal.ZERO;
                BigDecimal sommeRFP = BigDecimal.ZERO;
                BigDecimal sommeRemise = BigDecimal.ZERO;
                
                BigDecimal Obj = BigDecimal.ZERO;
                BigDecimal CaNet = BigDecimal.ZERO;
                BigDecimal TRO = BigDecimal.ZERO;
                BigDecimal tauxRFP = BigDecimal.ZERO;
                BigDecimal RFP = BigDecimal.ZERO;
                BigDecimal RFP_Reel = BigDecimal.ZERO;

            for(int i=0; i<listDetailBonLivraison.size(); i++) {

                    Object[] object=listDetailBonLivraison.get(i);
 
//##############################################################################################################################################################################################################################################################################################################################                   
        
                    int mois =(int)object[0];
                    BigDecimal chiffreAffaire = (BigDecimal)object[1]; 

                sommeCA = sommeCA.add(chiffreAffaire);

//##############################################################################################################################################################################################################################################################################################################################                   
 
                ChiffreAffaireBrut chiffreAffaireBrut = chiffreAffaireBrutDAO.findByNumMois(mois);

                     Obj=chiffreAffaireBrut.getEau();
                     
                   sommeObj = sommeObj.add(chiffreAffaireBrut.getEau());
                   
//##############################################################################################################################################################################################################################################################################################################################                   
                
                BigDecimal livGMS = detailAvoirOulmesDAO.findByLivGMS(mois);
  
                if (livGMS!= null){
                    sommeLivGms = sommeLivGms.add(livGMS);
                }else{
                    livGMS=BigDecimal.ZERO;
                    sommeLivGms= sommeLivGms.add(livGMS);
                }
                
//##############################################################################################################################################################################################################################################################################################################################                   
                  
                CaNet = (sommeCA.subtract(sommeLivGms));

//##############################################################################################################################################################################################################################################################################################################################                   
               
                TRO = (CaNet.divide(sommeObj,2,RoundingMode.CEILING)).multiply(new BigDecimal(100));

//##############################################################################################################################################################################################################################################################################################################################                   
 
                BaremeTaux baremeTaux = baremeTauxDAO.findByClasseTRO(TRO);
                 
                tauxRFP = baremeTaux.getRemiseFinPeriode();

//##############################################################################################################################################################################################################################################################################################################################                   
                   
                RFP = ((CaNet.multiply(tauxRFP)).divide(new BigDecimal(100),1,RoundingMode.CEILING)).subtract(sommeRFP);

                sommeRFP = sommeRFP.add(RFP);
                    
//##############################################################################################################################################################################################################################################################################################################################                   
 
                BigDecimal remise =  detailPromotionDAO.findByMontantPromo(mois);

                 if (remise!= null){
                    sommeRemise = sommeRemise.add(remise);
                }else{
                    remise=BigDecimal.ZERO;
                    sommeRemise= sommeRemise.add(remise);
                }
//##############################################################################################################################################################################################################################################################################################################################                   

                 RFP_Reel = (sommeRFP.subtract(sommeRemise));

//##############################################################################################################################################################################################################################################################################################################################                   
 
                SituationRemiseFinPeriode situationRemiseFinPeriode = new SituationRemiseFinPeriode();
               
                    situationRemiseFinPeriode.setMois(mois);
                    situationRemiseFinPeriode.setChiffreAffaire(chiffreAffaire);
                    situationRemiseFinPeriode.setObjectif(Obj);
                    situationRemiseFinPeriode.setCaNet(CaNet);
                    situationRemiseFinPeriode.setLivGms(livGMS);
                    situationRemiseFinPeriode.setTroCumule(TRO);
                    situationRemiseFinPeriode.setTauxRfp(tauxRFP);
                    situationRemiseFinPeriode.setRemise(remise);
                    situationRemiseFinPeriode.setRfp(RFP);
                    situationRemiseFinPeriode.setRfpReel(RFP_Reel);
  
                  listSituationRemiseFinPeriode.add(situationRemiseFinPeriode);
  
            }
         tableRemiseFinPeriode.setItems(listSituationRemiseFinPeriode);
   

    }
    
    
    @FXML
    private void tableDetailClicked(MouseEvent event) {
    }

    @FXML
    private void chargeL0ibelleKeyPressed(KeyEvent event) {
    }

    @FXML
    private void editCommitFactureRecuColumn(TableColumn.CellEditEvent<RecuPromotionAccordee, BigDecimal> event) {
        
          if (numFactField.getText().isEmpty() || dateFact.getValue()== null) {

            nav.showAlert(Alert.AlertType.ERROR, "Attention", null, Constantes.REMPLIR_CHAMPS);
            tableDetailRemiseFinPeriode.refresh();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(Constantes.MESSAGE_ALERT_VALIDER_QTE_LIVRAISON);
            alert.setTitle("Confirmation");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

//                    BigDecimal RecuFacture = BigDecimal.ZERO;
//                    BigDecimal Recu= BigDecimal.ZERO;
//                    BigDecimal newRecu= BigDecimal.ZERO;

                    

                    
                ((RecuPromotionAccordee) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setRecuFacture(event.getNewValue());
               
                
                    tableDetailRemiseFinPeriode.refresh();  

  
//                 RecuFacture = factRecuColumn.getCellData(event.getTablePosition().getRow());
//                
//                 Recu =  listRecuPromotionAccordee.get(tableDetailRemiseFinPeriode.getSelectionModel().getSelectedIndex()).getRecu();
//                
//                 newRecu =  Recu.add(RecuFacture).setScale(2, RoundingMode.FLOOR);
//                
//
//                 listRecuPromotionAccordee.get(tableDetailRemiseFinPeriode.getSelectionModel().getSelectedIndex()).setRecu(newRecu);
                 
                 setColumnPropertiesDetail();  

           

            } else if (result.get() == ButtonType.CANCEL) {
                tableDetailRemiseFinPeriode.refresh();
            }
        }
        
    }


    @FXML
    private void tableDetailFactureClicked(MouseEvent event) {
        
          if (tableDetailRemiseFinPeriode.getSelectionModel().getSelectedIndex()!=-1){
            
            listDetailRecuPromotionAccordee.clear();
          
                   RecuPromotionAccordee recuPromotionAccordee=tableDetailRemiseFinPeriode.getSelectionModel().getSelectedItem();   
            
            
       listDetailRecuPromotionAccordee.addAll(detailRecuPromotionAccordeeDAO.findByRecuPromotionAccordee(recuPromotionAccordee.getId()));

       tableDetailRecuPromotionAccordee.setItems(listDetailRecuPromotionAccordee);
       
       setColumnPropertiesDetailFact();

    } 
        
    }


    
}
