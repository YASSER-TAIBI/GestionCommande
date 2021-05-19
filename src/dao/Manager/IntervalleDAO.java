/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Intervalle;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface IntervalleDAO {
    	
          public Intervalle findById(int id);
          
		public void add(Intervalle intervalle);

		public  Intervalle edit(Intervalle e);
		
		public  void delete(Intervalle e); 
		
		public List<Intervalle> findAll();
                
                public List<Intervalle> findIntervalleByQte(int qte);
                      
                public Intervalle findIntervalleByCodeIO(String code);
                      
                public List<Intervalle> findIntervalleSufCodeI0ByCode(String codeI0) ;
                       
                public  List<Intervalle> findIntervalleByCode(String code);
                      
                public List<Intervalle> findByCodeIntervalle(String code);
    
         	public List<Intervalle> findBylibelleIntervalle(String lib);

}
