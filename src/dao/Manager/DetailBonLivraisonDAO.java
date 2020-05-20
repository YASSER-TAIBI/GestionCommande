/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailBonLivraison;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailBonLivraisonDAO {

		
		public DetailBonLivraison findById(int id);
		
		public void add(DetailBonLivraison detailBonRetour);

		public  DetailBonLivraison edit(DetailBonLivraison e);
		
		public  void delete(DetailBonLivraison e); 
		
		public List<DetailBonLivraison> findAll();
                
                public List<DetailBonLivraison>  findDetaiBonLivraisonByNumLivAndNumFac (String numLiv ,String numFact);
                
                public List<DetailBonLivraison>  findDetaiBonLivraisonBycode (String code , String cmd);
                
                public DetailBonLivraison  findDetaiBonLivraisonByNumlivAndNumCo (String code , String cmd);
                
                public void ViderSession();
                
                public DetailBonLivraison findDetailBonLivraisonByDetailCommande(String numCom,String numLiv, int mp);

                public List<DetailBonLivraison> findCommandeByDetailBonLivraison(String ncom );
                
                public List<DetailBonLivraison> findByDetailBonLivraisonAndBl() ;
                 
                public List<Object[]> findByPrixMoyen() ;
}
