/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailRemiseFinPeriode;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailRemiseFinPeriodeDAO {

		
		public DetailRemiseFinPeriode findById(int id);
		
		public void add(DetailRemiseFinPeriode detailRemiseFinPeriode);

		public  DetailRemiseFinPeriode edit(DetailRemiseFinPeriode e);
		
		public  void delete(DetailRemiseFinPeriode e); 
		
		public List<DetailRemiseFinPeriode> findAll();
                
                public List<DetailRemiseFinPeriode> findByRemiseFinPeriode(int rfp);

}
