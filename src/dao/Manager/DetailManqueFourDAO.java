/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Commande;
import dao.Entity.DetailManqueFour;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author h
 */
public interface DetailManqueFourDAO {
		
    public DetailManqueFour findById(int id);
		
    public void add(DetailManqueFour detailManqueFour);
		
    public  DetailManqueFour edit(DetailManqueFour e);
		
    public  void delete(DetailManqueFour e); 
       
    public List<DetailManqueFour> findAll();
    
    public List<DetailManqueFour> findDetailManqueFourByNumCom(String numCom);
    
    public DetailManqueFour findDetailManqueFourByNumComAndNumBL(String numCom, String numBl);
  
}
