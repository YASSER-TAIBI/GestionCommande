/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.CommandeRegion;
import dao.Entity.Depot;
import dao.Entity.DetailCommande;
import java.util.List;

/**
 *
 * @author h
 */
public interface CommandeRegionDAO {
    
//                public List<Commande> findCommandeByEtat(String etat, Depot codeDepot);
                
                public List<CommandeRegion>  findCommandeByEtat(String etat);
		
		public CommandeRegion findById(int id);
		
		public void add(CommandeRegion commandeRegion);
		
		public  CommandeRegion edit(CommandeRegion e);
		
		public  void delete(CommandeRegion e); 
		
                public List<CommandeRegion>  findCommandeByEtatPF(String etat);
                
                public List<CommandeRegion> findAll(String typeCommandeRegion);
                
                public List<CommandeRegion> findByTypeComAndDepot(String typeCommandeRegion, int depot);
                
                 
}
