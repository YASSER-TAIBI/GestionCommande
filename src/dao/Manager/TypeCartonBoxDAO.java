/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.TypeCartonBox;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface TypeCartonBoxDAO {
       public TypeCartonBox findById(int id);
          
		public void add(TypeCartonBox typeCartonBox);

		public  TypeCartonBox edit(TypeCartonBox e);
		
		public  void delete(TypeCartonBox e); 
		
		public List<TypeCartonBox> findAll();
    
                public List<TypeCartonBox> findByCodeCartBox(String code) ;
                 
         	public List<TypeCartonBox> findBylibelleCartBox(String lib);
                
}
