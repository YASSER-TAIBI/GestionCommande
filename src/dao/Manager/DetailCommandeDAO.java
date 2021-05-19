/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Commande;
import dao.Entity.DetailCommande;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author h
 */
public interface DetailCommandeDAO {
		
    public DetailCommande findById(int id);
		
    public void add(DetailCommande detailCommande);
		
    public  DetailCommande edit(DetailCommande e);
		
    public  void delete(DetailCommande e); 
       
    public List<DetailCommande> findByEtat(String etat) ;
    
    public List<DetailCommande> findDetailCommandeByEtat(Commande commande, String etat);
    
    public List<DetailCommande> findDetailNumCommandeByEtat(String Numcom, String etat);
    
    public List<DetailCommande> findDetailCommandeByMpAndEtat(int mp, String etat);
    
    public DetailCommande findDetailCommandeByBonRetour(String Mp ,String idFour,String etat,String nCom);

    public List<DetailCommande>  findBySituationGlobalCommandeMp (String etat, String req);
         
    public List<DetailCommande>  findBySituationGlobalCommandePf (String etat, String req);
    
}
