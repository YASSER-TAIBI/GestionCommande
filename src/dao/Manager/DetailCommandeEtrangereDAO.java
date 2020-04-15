/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Commande;
import dao.Entity.CommandeEtrangere;
import dao.Entity.Depot;
import dao.Entity.DetailCommande;
import dao.Entity.DetailCommandeEtrangere;
import java.util.List;

/**
 *
 * @author h
 */
public interface DetailCommandeEtrangereDAO {
    

		public DetailCommandeEtrangere findById(int id);
		
		public void add(DetailCommandeEtrangere e);
		
		public  DetailCommandeEtrangere edit(DetailCommandeEtrangere e);
		
		public  void delete(DetailCommandeEtrangere e); 
		
		public List<DetailCommandeEtrangere> findAll();

                public List<DetailCommandeEtrangere> findDetailCommandeEtrangereByCommandeEtr (CommandeEtrangere comEtr);
                 
}
