package dao.Manager;

import dao.Entity.DelaiPaiementFour;
import dao.Entity.Fournisseur;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
public interface DelaiPaiementFourDAO {
    
                public DelaiPaiementFour findById(int id);
		
		public void add(DelaiPaiementFour delaiPaiementFour);
		
		public  DelaiPaiementFour edit(DelaiPaiementFour e);
		
		public  void delete(DelaiPaiementFour e); 
		
		public List<DelaiPaiementFour> findAll();
                
                public DelaiPaiementFour findByFour(String four);

}