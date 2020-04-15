/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Dimension;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DimensionDAO {
    
       public int getMaxId();
       
       public Dimension findById(int id);
       
       public void add(Dimension dimension);
		
       public  Dimension edit(Dimension e);
		
       public  void delete(Dimension e); 
		
       public List<Dimension> findAll();
       
       public List<Dimension>  findDimensionByFamille(String id);

       public List<Dimension>  findDimensionByID(int id);
      
       public Dimension findByCode(String code) ;

       public Dimension findDimensionByCode(String code);
       
       public List<Dimension> findDimensionByCodeDIM(String code);
       
       public List<Dimension>  findDimensionByCat(String cat);
}
