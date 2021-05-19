/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Commande;

import dao.Entity.SousDetailManqueFour;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author h
 */
public interface SousDetailManqueFourDAO {
		
    public SousDetailManqueFour findById(int id);
		
    public void add(SousDetailManqueFour sousDetailManqueFour);
		
    public SousDetailManqueFour edit(SousDetailManqueFour e);
		
    public void delete(SousDetailManqueFour e); 
       
    public List<SousDetailManqueFour> findAll();
    
    public List<SousDetailManqueFour> findByNumManque(String nMnq);
    
}
