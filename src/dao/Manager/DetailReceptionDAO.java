/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailCommande;
import dao.Entity.DetailReception;
import java.util.List;

/**
 *
 * @author h
 */
public interface DetailReceptionDAO {
    
               
                
                 public int getMaxId();
    
		public DetailReception findById(int id);
		
		public void add(DetailReception detailReception);

		public  DetailReception edit(DetailReception e);
		
		public  void delete(DetailReception e); 
		
		public List<DetailReception> findAll();
                
                public List<DetailReception>  findReceptionBycode(String code) ;
                
                public DetailReception findByNumLiv(String nLiv);
                
                public List<DetailReception> findByCommande(String ncom );

                public List<DetailReception> findByCommandePF(String ncom );


}
