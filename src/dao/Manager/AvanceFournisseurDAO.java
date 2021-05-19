/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.AvanceFournisseur;
import java.util.Date;
import java.util.List;

/**
 *
 * @author h
 */
public interface AvanceFournisseurDAO {
    
                
		public AvanceFournisseur findById(int id);
		
		public void add(AvanceFournisseur avanceFournisseur);
		
		public  AvanceFournisseur edit(AvanceFournisseur e);
		
		public  void delete(AvanceFournisseur e); 
		
		public List<AvanceFournisseur> findAll();
                
                public List<AvanceFournisseur> findByClientAndFour(int client,int four);
                

             
}
