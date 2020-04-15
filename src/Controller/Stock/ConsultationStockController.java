/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Stock;

import Utils.Constantes;
import dao.Entity.CategorieMp;
import dao.Entity.DetailCommande;
import dao.Entity.Magasin;
import dao.Entity.MatierePremier;
import dao.Entity.StockMP;
import dao.Entity.StockPF;
import dao.Entity.SubCategorieMp;
import dao.Manager.CategorieMpDAO;
import dao.Manager.DetailCommandeDAO;
import dao.Manager.StockMPDAO;
import dao.Manager.StockPFDAO;
import dao.Manager.SubCategorieMPDAO;
import dao.ManagerImpl.CategorieMpDAOImpl;
import dao.ManagerImpl.DetailCommandeDAOImpl;
import dao.ManagerImpl.StockMPDAOImpl;
import dao.ManagerImpl.StockPFDAOImpl;
import dao.ManagerImpl.SubCategorieMPAOImpl;
import function.navigation;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
public class ConsultationStockController implements Initializable {

    
    
    
    
    @FXML
    private TableView<StockMP> tableStock;
    @FXML
    private TableColumn<StockMP, String> codeMPColumn;
    @FXML
    private TableColumn<StockMP, String> designationColumn;
    @FXML
    private TableColumn<StockMP, Integer> stockColumn;
    @FXML
    private TableColumn<StockMP, BigDecimal> stockMinColumn;
    @FXML
    private TableColumn<StockMP, BigDecimal> manqueColumn;
    
    @FXML
    private TableView<DetailCommande> tableDetailCommande;
    @FXML
    private TableColumn<DetailCommande, String> codeMPDCColumn;
    @FXML
    private TableColumn<DetailCommande, String> designationDCColumn;
    @FXML
    private TableColumn<DetailCommande, BigDecimal> qteCommandeEnCourColumn;
    
    @FXML
    private TableView<StockPF> tableStockProd;
    @FXML
    private TableColumn<StockPF, String> codeMPSProdColumn;
    @FXML
    private TableColumn<StockPF, String> designationSProdColumn;
    @FXML
    private TableColumn<StockPF, String> magasinColumn;
    @FXML
    private TableColumn<StockPF, String> depotColumn;
    @FXML
    private TableColumn<StockPF, BigDecimal> stockProdColumn;
    
    @FXML
    private TextField codeRechField;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button btnRefrech;
    
    @FXML
    private ComboBox<String> subCatgCombo;
    @FXML
    private ComboBox<String> familleCombo;
    
    
    /**
     * Initializes the controller class.
     */
    
       private final ObservableList<StockMP> listeStockMP=FXCollections.observableArrayList();
       private final ObservableList<DetailCommande> listeDetailCommande = FXCollections.observableArrayList();
       private final ObservableList<StockPF> listeStockPF = FXCollections.observableArrayList();
       
          private List <Object[]> listeObject=new ArrayList<Object[]>();
          private List <Object[]> listeObjectStock=new ArrayList<Object[]>();
          
          
     StockMPDAO stockMPDAO = new StockMPDAOImpl();
    
     navigation nav = new navigation();
     
     private Map<String,SubCategorieMp> mapSubCategorieMp=new HashMap<>();
     private Map<String,CategorieMp> mapCategorieMp=new HashMap<>();
     
    
     SubCategorieMPDAO subcategorieMpDAO = new SubCategorieMPAOImpl();
    CategorieMpDAO categorieMpDAO  = new CategorieMpDAOImpl();
    DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAOImpl();
     StockPFDAO stockPFDAO = new StockPFDAOImpl();
     StockMP stockMP = new StockMP();
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

           List<SubCategorieMp> listSubCategorieMp=subcategorieMpDAO.findAll();
        
        listSubCategorieMp.stream().map((subCategorieMp) -> {
            subCatgCombo.getItems().addAll(subCategorieMp.getNom());
            return subCategorieMp;
        }).forEachOrdered((subCategorieMp) -> {
            mapSubCategorieMp.put(subCategorieMp.getNom(), subCategorieMp);
        });
        
             List<CategorieMp> listCategorieMp=categorieMpDAO.findAll();
        
        listCategorieMp.stream().map((categorieMp) -> {
            familleCombo.getItems().addAll(categorieMp.getNom());
            return categorieMp;
        }).forEachOrdered((categorieMp) -> {
            mapCategorieMp.put(categorieMp.getNom(), categorieMp);
        });
        
        
        setColumnProperties();
         loadDetail();
         
           manqueColumn.setCellFactory(column -> {
			return new TableCell<StockMP, BigDecimal>() {
				@Override
				protected void updateItem(BigDecimal item, boolean empty) {
					super.updateItem(item, empty);
					
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						
						setText(String.valueOf(item));
						
						// Style all dates in March with a different color.
						if (item.compareTo(BigDecimal.ZERO)<0) {
							setTextFill(Color.WHITE);
							setStyle("-fx-background-color: red");
						}else if (item.compareTo(BigDecimal.ZERO)>0){
                                                setTextFill(Color.WHITE);
							setStyle("-fx-background-color: green");
                                                
                                                }

                                                else {
							setTextFill(Color.BLACK);
							setStyle("");
						}
					}
				}
			};
		});
         
         
         
    }    
    
     void setColumnProperties(){
        
      codeMPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockMP, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<StockMP, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
   
      designationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockMP, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<StockMP, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });
      
      manqueColumn.setCellValueFactory(new PropertyValueFactory<>("manque"));
       
             
      stockColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockMP, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<StockMP, Integer> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getStock()));
                }
                
             });
               
      stockMinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockMP, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<StockMP, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getValMinMP());
                }
             });
    }
     
      void setColumnPropertiesDetail(){
        
      codeMPDCColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
   
      designationDCColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailCommande, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             });
      
      qteCommandeEnCourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailCommande, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailCommande, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getQuantiteRestee());
                }
             });
    }
      
       void setColumnPropertiesStockProd(){
        
      codeMPSProdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockPF, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<StockPF, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });
   
      designationSProdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockPF, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<StockPF, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getNom());
                }
             }); 
      
      magasinColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockPF, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<StockPF, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMagasin().getLibelle());
                }
             });
      
       depotColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockPF, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<StockPF, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMagasin().getDepot().getLibelle());
                }
             });
      
       stockProdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockPF, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<StockPF, BigDecimal> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getStock());
                }
             });
    }
    
    void loadDetail(){
        
//          listeStockProd.clear();
//    listeStockProd.addAll(stockPFDAO.findAll());
//     
//       for (int i = 0; i < listeStockProd.size(); i++) {
//            
//             StockPF stockPF = listeStockProd.get(i);
//             
//            BigDecimal qteRestee = BigDecimal.ZERO;
//            BigDecimal qteStock = BigDecimal.ZERO;
//            
//            listeObject = detailCommandeDAO.findDetailCommandeByQteRestee(stockPF.getMatierePremier().getId(), stockPF.getMagasin().getDepot().getId(),Constantes.ETAT_AFFICHAGE,nav.utilisateur.getId(), stockPF.getMagasin().getLibelle());
//             
//    
//      if(!listeObject.isEmpty()){
//
//                     Object[] object = listeObject.get(0);
//                      qteRestee=(BigDecimal) object[0];
//
//      }
//                       
//               qteStock= qteRestee.add(stockPF.getStock());
//               
//          
//               
//             if (qteStock.compareTo(stockPF.getMatierePremier().getValMinMP())<0){
//                 
//             stockPF.setManque(qteStock.subtract(stockPF.getMatierePremier().getValMinMP()));
//             
//          listeStockProd.set(i, stockPF);
//
//             }else if (qteStock.compareTo(stockPF.getMatierePremier().getValMaxMP())>0){
//             
//                     stockPF.setManque(qteStock.subtract(stockPF.getMatierePremier().getValMaxMP()));
//             
//          listeStockProd.set(i, stockPF);
//
//             }
//             else {
//             
//               stockPF.setManque(BigDecimal.ZERO);
//             
//          listeStockProd.set(i, stockPF);  
//             
//             }
// 
//        }
        
        listeStockMP.clear();
        listeStockMP.addAll(stockMPDAO.findAll());
        
//           for (int i = 0; i < listeStockMP.size(); i++) {
//            
//             StockMP stockMP = listeStockMP.get(i);
//             
//            BigDecimal qteRestee = BigDecimal.ZERO;
//             BigDecimal qteStock = BigDecimal.ZERO;
//            
//            listeObject = detailCommandeDAO.findDetailCommandeByQteRestee(stockMP.getMatierePremier().getId(), stockMP.getMagasin().getDepot().getId(),Constantes.ETAT_AFFICHAGE);
//             
//    
//      if(!listeObject.isEmpty()){
//
//                     Object[] object = listeObject.get(0);
//                      qteRestee=(BigDecimal) object[0];
//
//      }
//                       
//               qteStock= qteRestee.add(stockMP.getStock());
//               
//          
//               
//             if (qteStock.compareTo(stockMP.getMatierePremier().getValMinMP())<0){
//             
//             stockMP.setManque(qteStock.subtract(stockMP.getMatierePremier().getValMinMP()));
//             
//          listeStockMP.set(i, stockMP);
//
//             }else if (qteStock.compareTo(stockMP.getMatierePremier().getValMaxMP())>0){
//             
//                     stockMP.setManque(qteStock.subtract(stockMP.getMatierePremier().getValMaxMP()));
//             
//          listeStockMP.set(i, stockMP);
//
//             }
//             else {
//             
//               stockMP.setManque(BigDecimal.ZERO);
//             
//          listeStockMP.set(i, stockMP);  
//             
//             }
// 
//        }
           
        tableStock.setItems(listeStockMP);
    }

    


    @FXML
    private void imprimerOnAction(ActionEvent event) {
        
             try {
          HashMap para = new HashMap();
            JasperReport report = (JasperReport) JRLoader.loadObject(ConsultationStockController.class.getResource(nav.getiReportConsultationStock()));


             JasperPrint jp = JasperFillManager.fillReport(report, para, new JRBeanCollectionDataSource(listeStockMP));
               JasperViewer.viewReport(jp, false);
            
        } catch (JRException ex) {
            Logger.getLogger(ConsultationStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrechOnAction(ActionEvent event) {
         
        
        subCatgCombo.getSelectionModel().select(-1);
        familleCombo.getSelectionModel().select(-1);
        codeRechField.setText(Constantes.MATIERE_PREMIER);
        
           List<CategorieMp> listCategorieMp=categorieMpDAO.findAll();
        
        listCategorieMp.stream().map((categorieMp) -> {
            familleCombo.getItems().addAll(categorieMp.getNom());
            return categorieMp;
        }).forEachOrdered((categorieMp) -> {
            mapCategorieMp.put(categorieMp.getNom(), categorieMp);
        });
        
         loadDetail();
         setColumnProperties();
    }


    @FXML
    private void subCatgComboOnAction(ActionEvent event) {
        
        
   familleCombo.getItems().clear();
   
   if(subCatgCombo.getSelectionModel().getSelectedIndex()!=-1){
         SubCategorieMp SubCategorie = mapSubCategorieMp.get(subCatgCombo.getSelectionModel().getSelectedItem());
          
        
           List<CategorieMp> listeCategorieMp= new ArrayList<>();
           
               listeCategorieMp = categorieMpDAO.findCategorieMpByID(SubCategorie.getId());
               
        listeCategorieMp.stream().map((categorieMp) -> {
            familleCombo.getItems().addAll(categorieMp.getNom());
            return categorieMp;
        }).forEachOrdered((categorieMp) -> {
            mapCategorieMp.put(categorieMp.getNom(), categorieMp);
        });

    }}

    @FXML
    private void familleComboOnAction(ActionEvent event) {
        
//            tableStock.getItems().clear();
//
//             if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1){    
//            listeStockMP.clear();
//           CategorieMp categorieMp =mapCategorieMp.get(familleCombo.getSelectionModel().getSelectedItem());
//           listeStockMP.addAll(stockMPDAO.findStockMPByCateg(categorieMp.getId()));
//           tableStock.setItems(listeStockMP);
//           setColumnProperties();
//           
//    }
    }

    @FXML
    private void codeMpRech(ActionEvent event) {
         
//          tableStock.getItems().clear();
//        
//
//        
//             if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1){    
//            listeStockMP.clear();
//           SubCategorieMp categorieMp =mapSubCatg.get(subCatgCombo.getSelectionModel().getSelectedItem());
//           listeStockMP.addAll(stockMPDAO.findStockMPByCateg(categorieMp.getId()));
//           tableDetailCommande.setItems(listeStockMP);
//           setColumnProperties();
    }

    @FXML
    private void recherchMouseClicked(MouseEvent event) {
        
        //subCategorie = false <> Categorie = false <> Mp = false
        if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && familleCombo.getSelectionModel().getSelectedIndex()== -1 && codeRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)){
            nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.REMPLIR_CHAMPS);
        
        //subCategorie = true <> Categorie = false <> Mp = false    
        }else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && familleCombo.getSelectionModel().getSelectedIndex()== -1 && codeRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)) {

         listeStockMP.clear();
         
         SubCategorieMp SubCategorie = mapSubCategorieMp.get(subCatgCombo.getSelectionModel().getSelectedItem());
   
         listeStockMP.addAll(stockMPDAO.findStockMPBySubCateg(SubCategorie.getId()));
           tableStock.setItems(listeStockMP);
           setColumnProperties();

        //subCategorie = false <> Categorie = true <> Mp = false
        }else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && familleCombo.getSelectionModel().getSelectedIndex()!= -1 && codeRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)) {
        
        listeStockMP.clear();
        
         CategorieMp categorie = mapCategorieMp.get(familleCombo.getSelectionModel().getSelectedItem());
   
         listeStockMP.addAll(stockMPDAO.findStockMPByCateg(categorie.getId()));
           tableStock.setItems(listeStockMP);
           setColumnProperties();
            
        //subCategorie = false <> Categorie = false <> Mp = true    
        }else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && familleCombo.getSelectionModel().getSelectedIndex()== -1 && !codeRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)) {
        
            listeStockMP.clear();
            
           String Mp = codeRechField.getText();
           
           listeStockMP.addAll(stockMPDAO.findStockMPByMp(Mp));
           tableStock.setItems(listeStockMP);
           setColumnProperties();
            
        //subCategorie = true <> Categorie = true <> Mp = false   
        }else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && familleCombo.getSelectionModel().getSelectedIndex()!= -1 && codeRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)) {
        
            listeStockMP.clear();
            
              SubCategorieMp SubCategorie = mapSubCategorieMp.get(subCatgCombo.getSelectionModel().getSelectedItem());
              CategorieMp categorie = mapCategorieMp.get(familleCombo.getSelectionModel().getSelectedItem());
              
             listeStockMP.addAll(stockMPDAO.findStockMPByCategAndSubCateg(categorie.getId(),SubCategorie.getId()));
           tableStock.setItems(listeStockMP);
           setColumnProperties();   
            
        //subCategorie = true <> Categorie = true <> Mp = true    
        }else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && familleCombo.getSelectionModel().getSelectedIndex()!= -1 && !codeRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)) {
        
            listeStockMP.clear();
            
               SubCategorieMp SubCategorie = mapSubCategorieMp.get(subCatgCombo.getSelectionModel().getSelectedItem());
               CategorieMp categorie = mapCategorieMp.get(familleCombo.getSelectionModel().getSelectedItem());
                  String Mp = codeRechField.getText();
                  
                    listeStockMP.addAll(stockMPDAO.findStockMPByCategAndMpAndSubCateg(categorie.getId(), Mp, SubCategorie.getId()));
           tableStock.setItems(listeStockMP);
           setColumnProperties();   
           
        //subCategorie = false <> Categorie = true <> Mp = true   
        }else if(subCatgCombo.getSelectionModel().getSelectedIndex()== -1 && familleCombo.getSelectionModel().getSelectedIndex()!= -1 && !codeRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)) {
        
            listeStockMP.clear();
            
               CategorieMp categorie = mapCategorieMp.get(familleCombo.getSelectionModel().getSelectedItem());
                  String Mp = codeRechField.getText();
                  
                    listeStockMP.addAll(stockMPDAO.findStockMPByCategAndMp(categorie.getId(), Mp));
           tableStock.setItems(listeStockMP);
           setColumnProperties();   
           
        //subCategorie = false <> Categorie = true <> Mp = true   
        }else if(subCatgCombo.getSelectionModel().getSelectedIndex()!= -1 && familleCombo.getSelectionModel().getSelectedIndex()== -1 && !codeRechField.getText().equalsIgnoreCase(Constantes.MATIERE_PREMIER)) {
        
            listeStockMP.clear();
            
               SubCategorieMp SubCategorie = mapSubCategorieMp.get(subCatgCombo.getSelectionModel().getSelectedItem());
                  String Mp = codeRechField.getText();
                  
                    listeStockMP.addAll(stockMPDAO.findStockMPBySubCategAndMp(Mp, SubCategorie.getId()));
           tableStock.setItems(listeStockMP);
           setColumnProperties();   
           
           
        }else {
            
            listeStockMP.clear();
            
          nav.showAlert(Alert.AlertType.WARNING, "Attention", null, Constantes.VERIFIER_FILTRE);
        }
        
    }

    @FXML
    private void afficherDetailAndStockProdOnMouseClicked(MouseEvent event) {
        
//             setColumnPropertiesDetail();
//             setColumnPropertiesStockProd();
//        listeDetailCommande.clear();
//        listeStockPF.clear();
//        
//        
//if (tableStock.getSelectionModel().getSelectedIndex()!=-1){
//    
//        stockMP = tableStock.getSelectionModel().getSelectedItem();
//
//      listeDetailCommande.addAll(detailCommandeDAO.findDetailCommandeByMpAndEtat(stockMP.getMatierePremier().getId() , Constantes.ETAT_AFFICHAGE));
//      
//      tableDetailCommande.setItems(listeDetailCommande);
//      
//      
//        listeObjectStock = stockPFDAO.findStockPFByMp(stockMP.getMatierePremier().getId(), stockMP.getMagasin().getId(), Constantes.CATEGORIE_MAGASIN);
//             
//         
//     
//           for(int i=0; i<listeObjectStock.size(); i++) {
//
//                    Object[] object=listeObjectStock.get(i);
//                   
//                    BigDecimal sumStock =(BigDecimal)object[0];
//                    Magasin magasin = (Magasin)object[1]; 
//                    MatierePremier mp = (MatierePremier)object[2]; 
//
//
//               StockPF stockPF = new StockPF();
//               
//                  stockPF.setStock(sumStock);
//                  stockPF.setMatierePremier(mp);
//                  stockPF.setMagasin(magasin);
//                  
//                  listeStockPF.add(stockPF);
//                    
//            }
//            
//            
//            tableStockProd.setItems(listeStockPF);
//            setColumnPropertiesStockProd();
//   
//        
//        
//        
//        
//        tableDetailCommande.setEditable(true);
//
//}
//
//        
//        
    
}
}