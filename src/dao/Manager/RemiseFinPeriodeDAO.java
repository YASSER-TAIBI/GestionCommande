package dao.Manager;

import dao.Entity.RemiseFinPeriode;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
public interface RemiseFinPeriodeDAO {
    
    public RemiseFinPeriode findById(int id);
		
		public void add(RemiseFinPeriode remiseFinPeriode);
		
		public  RemiseFinPeriode edit(RemiseFinPeriode e);
		
		public  void delete(RemiseFinPeriode e); 
		
		public List<RemiseFinPeriode> findAll();
                
                public RemiseFinPeriode findByAnnee(String annee);
                

}