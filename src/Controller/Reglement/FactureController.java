/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Reglement;

import dao.Entity.AvoirOulmes;
import dao.Entity.DetailAvoirOulmes;
import dao.Entity.FactureAvoirOulmes;
import dao.Manager.AvoirOulmesDAO;
import dao.Manager.DetailAvoirOulmesDAO;
import dao.Manager.FactureAvoirOulmesDAO;
import dao.ManagerImpl.AvoirOulmesDAOImpl;
import dao.ManagerImpl.DetailAvoirOulmesDAOImpl;
import dao.ManagerImpl.FactureAvoirOulmesDAOImpl;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class FactureController implements Initializable {

    @FXML
    private Button ajouterSaisie;
    @FXML
    private TableView<FactureAvoirOulmes> factureTable;
    @FXML
    private TableColumn<FactureAvoirOulmes, String> bonLivColumn;

    /**
     * Initializes the controller class.
     */

      private final ObservableList<FactureAvoirOulmes> listeFactureAvoirOulmes=FXCollections.observableArrayList();
      private final ObservableList<FactureAvoirOulmes> listeFactureAvoirOulmesTmp=FXCollections.observableArrayList();
      private final ObservableList<AvoirOulmes> listeAvoirOulmesTmp=FXCollections.observableArrayList();
      private final ObservableList<DetailAvoirOulmes> listeDetailAvoirOulmesTmp=FXCollections.observableArrayList();
      
      FactureAvoirOulmesDAO factureAvoirOulmesDAO = new FactureAvoirOulmesDAOImpl();
      AvoirOulmesDAO avoirOulmesDAO = new AvoirOulmesDAOImpl();
      DetailAvoirOulmesDAO detailAvoirOulmesDAO = new DetailAvoirOulmesDAOImpl();
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadDetail();
        setColumnPropertiesList();
        // TODO
    }    

      void setColumnPropertiesList(){

               bonLivColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FactureAvoirOulmes, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<FactureAvoirOulmes, String> p) {
                    return new ReadOnlyObjectWrapper(p.getValue().getNumLivraison());
                }
             });    
      }
    
          void loadDetail(){
           
        listeFactureAvoirOulmes.clear();
        listeFactureAvoirOulmes.addAll(factureAvoirOulmesDAO.findByFactureAvoirOulmes());
        factureTable.setItems(listeFactureAvoirOulmes);
    }
    
      
      
    @FXML
    private void ajouterSaisieAction(ActionEvent event) {
         listeFactureAvoirOulmesTmp.addAll(factureAvoirOulmesDAO.findAll());
         listeAvoirOulmesTmp.addAll(avoirOulmesDAO.findAll());
        
         
         
         
        for (int i = 0; i < listeFactureAvoirOulmes.size(); i++) {
            
            FactureAvoirOulmes factureAvoirOulmes= listeFactureAvoirOulmes.get(i);
            
              BigDecimal montantFact = BigDecimal.ZERO;
              String numFact = "";
              String etat = "";
            
            for (int j = 0; j < listeFactureAvoirOulmesTmp.size(); j++) {
                
                 FactureAvoirOulmes factureAvoirOulmesTmp= listeFactureAvoirOulmesTmp.get(j);
                
                 if (factureAvoirOulmes.getNumLivraison().equals(factureAvoirOulmesTmp.getNumLivraison())){
                 
                     for (int k = 0; k < listeAvoirOulmesTmp.size(); k++) {
                         
                         AvoirOulmes avoirOulmes = listeAvoirOulmesTmp.get(k);
                         
                       if (avoirOulmes.getNumLivraison().equals(factureAvoirOulmesTmp.getNumLivraison())){
                       
                         List<DetailAvoirOulmes> listeDetailAvoirOulmesTmp = detailAvoirOulmesDAO.findByAvoirOulmes(avoirOulmes.getId());
                         
                         String val = "";
                         
                           for (int l = 0; l < listeDetailAvoirOulmesTmp.size(); l++) {
                               
                               DetailAvoirOulmes detailAvoirOulmes = listeDetailAvoirOulmesTmp.get(l);
                               
                               if (detailAvoirOulmes.getAvoirOulmes().getNumLivraison().equals(factureAvoirOulmesTmp.getNumLivraison()) && detailAvoirOulmes.getPrixOulmes().getId()== factureAvoirOulmesTmp.getPrixOulmes().getId()){
                               
                                   if (detailAvoirOulmes.getPrix().compareTo(factureAvoirOulmesTmp.getPrix())==0){
                                   
                                   detailAvoirOulmes.setPrixFactureAvoir(factureAvoirOulmesTmp.getPrix());
                                       
                                   }else{
                                   
                                       val = val+" Prob Prix";
                                    detailAvoirOulmes.setPrixFactureAvoir(factureAvoirOulmesTmp.getPrix());
                                   }
                                   
                                    if (detailAvoirOulmes.getQte().compareTo(factureAvoirOulmesTmp.getQte())==0){
                                   
                                   detailAvoirOulmes.setQteFactureAvoir(factureAvoirOulmesTmp.getQte());
                                       
                                   }else{
                                   
                                       val = val+" Prob Qte";
                                    detailAvoirOulmes.setQteFactureAvoir(factureAvoirOulmesTmp.getQte());
                                   }
                                   
                                    if (detailAvoirOulmes.getRemiseAvoir().compareTo(factureAvoirOulmesTmp.getRemiseAvoir())==0){
                                   
                                   detailAvoirOulmes.setRemiseFactureAvoir(factureAvoirOulmesTmp.getRemiseAvoir());
                                       
                                   }else{
                                   
                                       val = val+" Prob Remise";
                                     detailAvoirOulmes.setRemiseFactureAvoir(factureAvoirOulmesTmp.getRemiseAvoir());
                                   }
                                
                                    detailAvoirOulmes.setMotif(val);
                                    detailAvoirOulmes.setMontantNetFactureAvoir(factureAvoirOulmesTmp.getMontantNet());
                                    
                                    detailAvoirOulmesDAO.edit(detailAvoirOulmes);
                                    
                                    
                                    montantFact= montantFact.add(factureAvoirOulmesTmp.getMontantNet());
                                    
                                    numFact = factureAvoirOulmesTmp.getNumFacture();
                                    
                                    etat = factureAvoirOulmesTmp.getEtat();
                                    
                                    
                               }
                               
                               
                           }
                         
              
                       
                       }
                         
                     }
                     
           
                 
                 }
                 
            }
            
            System.out.println("factureAvoirOulmes.getNumLivraison() "+factureAvoirOulmes.getNumLivraison());
            
            AvoirOulmes avoirOulmes = avoirOulmesDAO.findBonRetourByNumCommAndNumLiv(factureAvoirOulmes.getNumLivraison());
            
            if (avoirOulmes!=null){
            
               System.out.println("avoirOulmes "+avoirOulmes.getNumLivraison());
            
                       avoirOulmes.setEtat(etat);
                         avoirOulmes.setNumFacture(numFact);
                         avoirOulmes.setFactureAvoir(montantFact);
                         
                         avoirOulmesDAO.edit(avoirOulmes);
            }else {
                
              System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
              System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+factureAvoirOulmes.getNumLivraison()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
              System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
              
            }
        }
        
    }
    
}
