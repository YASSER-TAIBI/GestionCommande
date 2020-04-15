/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Commande;
import dao.Entity.Depot;
import dao.Entity.DetailCommande;
import java.util.Date;
import java.util.List;

/**
 *
 * @author h
 */
public interface CommandeDAO {
    
//                public List<Commande> findCommandeByEtat(String etat, Depot codeDepot);
                
                public List<Commande>  findCommandeByEtat(String etat, String typeCommande);
                
                public int getMaxId();
                
                public int getMaxIdSpecial();
		
		public Commande findById(int id);
		
		public void add(Commande commande);
		
		public  Commande edit(Commande e);
		
		public  void delete(Commande e); 
		
		public List<Commande> findAll(String typeCommande);

                public List<Commande> findFourByRechercheNumCommande(String nCommande,String etat);
                
                public Commande findCommandeByNumCommande(String nCommande,String etat);
                 
                public void delete(DetailCommande e);

		public List<Commande> findByNumCommande(String nComDepot, String nComfour) ;
                 
                public Commande findNumLivraisonByNumCommande();
                
                public List<Commande> findByDateCommande(Date dateCreation ,String etat);
}
