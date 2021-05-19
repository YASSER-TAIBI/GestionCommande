/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.CommandeRegion;
import dao.Entity.DetailCommandeRegion;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author h
 */
public interface DetailCommandeRegionDAO {

		
    public DetailCommandeRegion findById(int id);
		
    public void add(DetailCommandeRegion detailCommandeRegion);
		
    public  DetailCommandeRegion edit(DetailCommandeRegion e);
		
    public  void delete(DetailCommandeRegion e); 
       
    public List<DetailCommandeRegion> findByEtat(String etat) ;
    
    public List<DetailCommandeRegion> findDetailCommandeByEtat(CommandeRegion commande, String etat);
   
    
}
