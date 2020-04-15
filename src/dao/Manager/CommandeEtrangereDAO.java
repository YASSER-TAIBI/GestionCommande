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
import java.util.List;

/**
 *
 * @author h
 */
public interface CommandeEtrangereDAO {
    

		public CommandeEtrangere findById(int id);
		
		public void add(CommandeEtrangere e);
		
		public  CommandeEtrangere edit(CommandeEtrangere e);
		
		public  void delete(CommandeEtrangere e); 
		
		public List<CommandeEtrangere> findAll();

                public List<CommandeEtrangere> findCommandeEtrangereByEtat (String etat );
             
                public List<CommandeEtrangere> findCommandeEtrangereByNumDossier (String numDossier ); 
}
