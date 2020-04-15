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
import dao.Entity.DetailDossier;
import java.util.List;

/**
 *
 * @author h
 */
public interface DetailDossierDAO {
    

		public DetailDossier findById(int id);
		
		public void add(DetailDossier e);
		
		public  DetailDossier edit(DetailDossier e);
		
		public  void delete(DetailDossier e); 
		
		public List<DetailDossier> findAll();

             
                 
}
