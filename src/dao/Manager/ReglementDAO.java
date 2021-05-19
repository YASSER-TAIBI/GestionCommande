/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Reglement;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface ReglementDAO {
     
    
    public int getMaxId();
		
		public Reglement findById(int id);
		
		public void add(Reglement reglement);
		
		public  Reglement edit(Reglement e);
		
		public  void delete(Reglement e); 
		
		public List<Reglement> findAll();
                
                public List<Reglement> findFourByRechercheNomReglement(int four,int clt);
                
                public List<Reglement> findReglementByCheque(String code,int client ,int four) ;
                  
                public List<Reglement> findReglementByFacture(String code,int client ,int four);
                  
                public List<Reglement> findByFourAndCltAndDateRg(int four,int clt,Date dateReglement);
}
