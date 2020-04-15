/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Fournisseur;
import java.util.Date;
import java.util.List;

/**
 *
 * @author h
 */
public interface FournisseurDAO {
		
                public int getMaxId();
                 
		public Fournisseur findById(int id);
		
		public void add(Fournisseur fournisseur);
		
		public  Fournisseur edit(Fournisseur e);
		
		public  void delete(Fournisseur e); 
		
		public List<Fournisseur> findAll();

                public List<Fournisseur> findFourByRechercheNom(String nom);
                
                public List<Fournisseur> findFourByRechercheCode(String code);
                
                public List<Fournisseur> findFourByRechercheVille(String ville);
                
                public Fournisseur findByFournisseur(String four);
                
                public List<Fournisseur> findFourByTypeFour();
            
        
           

		
}
