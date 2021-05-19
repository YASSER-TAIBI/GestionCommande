/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.ClientMP;
import java.util.List;

/**
 *
 * @author h
 */
public interface ClientMPDAO {
    
    public  ClientMP findUtilisateurByLoginMotPasse(String login,String motPasse);
    
                public int getMaxId();
                
		public ClientMP findById(int id);
		
		public void add(ClientMP clientMP);
		
		public ClientMP findClientMPByNom(String nom) ;
                
		public  ClientMP edit(ClientMP e);
		
		public  void delete(ClientMP e); 
		
		public List<ClientMP> findAll();

                public List<ClientMP> findClientMPByRechercheNom(String nom);
                
                public List<ClientMP> findClientMPByRechercheCode(String code);
                
               
		
    
}
