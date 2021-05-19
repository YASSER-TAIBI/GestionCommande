package dao.Manager;

import dao.Entity.RemiseArticleOulmes;
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
public interface RemiseArticleOulmesDAO {
    
    public RemiseArticleOulmes findById(int id);
		
		public void add(RemiseArticleOulmes remiseArticleOulmes);
		
		public  RemiseArticleOulmes edit(RemiseArticleOulmes e);
		
		public  void delete(RemiseArticleOulmes e); 
		
		public List<RemiseArticleOulmes> findAll();
                

}