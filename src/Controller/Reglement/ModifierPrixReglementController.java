/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import Utils.Constantes;
import dao.Entity.BonLivraison;
import dao.Entity.DetailBonLivraison;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCompte;
import dao.Manager.BonLivraisonDAO;
import dao.Manager.DetailBonLivraisonDAO;
import dao.Manager.DetailCompteDAO;
import dao.ManagerImpl.BonLivraisonDAOImpl;
import dao.ManagerImpl.DetailBonLivraisonDAOImpl;
import dao.ManagerImpl.DetailCompteDAOImpl;
import function.navigation;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.SpringLayout;


/**
 * FXML Controller.Reglement.ModifierPrixReglementController Controller class
 *
 * @author Hp
 */


public abstract class ModifierPrixReglementController extends AnchorPane implements Initializable {
    private int POUR;
    BonLivraison bonLivraison;
    ObservableList<DetailBonLivraison> listeDetailBonLivraison;

     
public ModifierPrixReglementController (int POUR, BonLivraison bonLivraison,ObservableList<DetailBonLivraison> listeDetailBonLivraison){
    this.bonLivraison= bonLivraison;
    this.POUR=POUR;
    this.listeDetailBonLivraison = listeDetailBonLivraison;
 
    
//    if (!listeDetailBonLivraison.equals(null)){
//        
//     loadDetail(listeDetailBonLivraison);
//    
//    }

    setAll(nav.getModifierPrixReglement(), this);
    }
    
    public static void setAll(String path, Object root){
    FXMLLoader fxmlLoader = new FXMLLoader(root.getClass().getResource(path));
        fxmlLoader.setRoot(root);
        fxmlLoader.setController(root);
        try {
            System.out.println(path);
            fxmlLoader.load();
        } catch (IOException exception){
            throw new RuntimeException(exception);
        }

    }
    public abstract void refresh();
    
    @FXML
    private TextField prixTxt;
    @FXML
    private TextField qteTxt;
    @FXML
    private Button modifierBtn;
    @FXML
    private TextField totalTxt;
    @FXML
    private TextField numLiv;
    @FXML
    private TableView<DetailBonLivraison> tableDetailBonLivraison;
    @FXML
    private TableColumn<DetailBonLivraison, String> codeMPColumn;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> qteColumn;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> prixColumn;
    @FXML
    private TableColumn<DetailBonLivraison, BigDecimal> totalColumn;
    @FXML
    private TextField clientTxt;
    @FXML
    private TextField fourTxt;
    
    int pos= 0;
    
  
    DetailCompteDAO detailCompteDAO = new DetailCompteDAOImpl();
    /**
     * Initializes the controller class.
     */
    
    navigation nav = new navigation();
    DetailBonLivraison detailBonLivraison = new DetailBonLivraison();
    DetailBonLivraisonDAO detailBonLivraisonDAO = new DetailBonLivraisonDAOImpl();
    BonLivraisonDAO bonLivraisonDAO = new  BonLivraisonDAOImpl();
    

         public void chargerLesDonnees(){

         numLiv.setText(bonLivraison.getLivraisonFour());
         fourTxt.setText(bonLivraison.getFourisseur());
         clientTxt.setText(bonLivraison.getClient());
             
    }

         
         void loadDetail() {
             
        listeDetailBonLivraison.clear();
        listeDetailBonLivraison.addAll(detailBonLivraisonDAO.findDetaiBonLivraisonBycode(bonLivraison.getLivraisonFour(),bonLivraison.getNumCommande()));
        tableDetailBonLivraison.setItems(listeDetailBonLivraison);
         
         }
         
           void setColumnPropertiesDetailReception(){

               codeMPColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<DetailBonLivraison, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getMatierePremier().getCode());
                }
             });

//           qteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
           
             qteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getQuantite()));
                }
                
             });
           
//           prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
           
             prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getPrix()));
                }
                
             });
           
//           totalColumn.setCellValueFactory(new PropertyValueFactory<>("Total"));
           
             totalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal>, ObservableValue<BigDecimal>>() {
                @Override
                public ObservableValue<BigDecimal> call(TableColumn.CellDataFeatures<DetailBonLivraison, BigDecimal> p) {
                    
                       DecimalFormatSymbols dfs = new  DecimalFormatSymbols(Locale.ROOT);
                dfs.setDecimalSeparator(',');
                dfs.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("#,##0.00",dfs);
                df.setGroupingUsed(true);
                    return new ReadOnlyObjectWrapper(df.format(p.getValue().getTotal()));
                }
                
             });
           
}
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        chargerLesDonnees();
        loadDetail();
        setColumnPropertiesDetailReception();
        // TODO
    }    

    @FXML
    private void modifierBtnOnAction(ActionEvent event) {

        BigDecimal montantHT= BigDecimal.ZERO;
        BigDecimal montantTVA =BigDecimal.ZERO;
        BigDecimal montantTTC =BigDecimal.ZERO;
        BigDecimal somme= BigDecimal.ZERO;
        

        for(int i=0;i<listeDetailBonLivraison.size(); i++ ){

          montantHT = listeDetailBonLivraison.get(i).getTotal().setScale(2,RoundingMode.FLOOR);

          somme = somme.add(montantHT);

        }
        
             montantTVA =somme.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
             montantTTC =somme.add(montantTVA).setScale(2,RoundingMode.FLOOR) ;
        
          bonLivraison.setMontant(somme);
          bonLivraison.setMontantTVA(montantTVA);
          bonLivraison.setMontantTTC(montantTTC);
          
        bonLivraisonDAO.edit(bonLivraison);

        BigDecimal MontantHTSpecial = BigDecimal.ZERO;
        BigDecimal MontantTVASpecial= BigDecimal.ZERO;
        BigDecimal MontantTTCSpecial= BigDecimal.ZERO;
        
          for(int j=0;j<listeDetailBonLivraison.size(); j++ ){

                      String designation = Constantes.DESIGNATION_RECEPTION_BON_LIVRAISION+" "+bonLivraison.getLivraisonFour()+"_"+Constantes.DESIGNATION_COMMANDE_N+" "+listeDetailBonLivraison.get(j).getNumCommande();

              System.out.println("designation "+designation);
              System.out.println("listeDetailBonLivraison.get(i).getMontant() "+listeDetailBonLivraison.get(j).getMontant());       
         DetailCompte detailCompte = detailCompteDAO.findByDetailCompte(designation);
        
         MontantHTSpecial = listeDetailBonLivraison.get(j).getMontant().setScale(2,RoundingMode.FLOOR);
         MontantTVASpecial =MontantHTSpecial.multiply(Constantes.TAUX_TVA_20).setScale(2,RoundingMode.FLOOR);
         MontantTTCSpecial =MontantHTSpecial.add(MontantTVASpecial).setScale(2,RoundingMode.FLOOR) ;
         

         
         
        detailCompte.setMontantCredit(MontantTTCSpecial);

        detailCompteDAO.edit(detailCompte);

        }
    
        
        
       nav.showAlert(Alert.AlertType.CONFIRMATION, "Succès", null,Constantes.MODIFIER_ENREGISTREMENT);
       
       refresh();
       Stage stage = (Stage) 
        modifierBtn.getScene().getWindow();
           stage.close();
    }


    @FXML
    private void tableDetailClicked(MouseEvent event) {
        
        pos = tableDetailBonLivraison.getSelectionModel().getSelectedIndex(); 
        
                 if (pos!=-1){
           detailBonLivraison=tableDetailBonLivraison.getSelectionModel().getSelectedItem();
        
            qteTxt.setText(detailBonLivraison.getQuantite()+"");
        
            prixTxt.setText(detailBonLivraison.getPrix()+"");

           }
    }

    @FXML
    private void calculeMouseClicked(MouseEvent event) {
        
         if (!qteTxt.getText().equals("") || !prixTxt.getText().equals("")){
            
        BigDecimal  valeur=BigDecimal.ZERO;
        
        valeur = (new BigDecimal(prixTxt.getText()).multiply(new BigDecimal(qteTxt.getText())));
        
        totalTxt.setText(valeur.setScale(2,RoundingMode.FLOOR)+"");
        
       detailBonLivraison.setQuantite(new BigDecimal(qteTxt.getText()));
       detailBonLivraison.setPrix(new BigDecimal(prixTxt.getText()));
       detailBonLivraison.setMontant(valeur);
      
       
      listeDetailBonLivraison.set(pos, detailBonLivraison);
       detailBonLivraisonDAO.edit(detailBonLivraison);
        
       loadDetail();
       
        
        }else {
         nav.showAlert(Alert.AlertType.WARNING, "Attention", null,Constantes.VERIFIER_QTE_PRIX);
        }
        
    }
    
}
