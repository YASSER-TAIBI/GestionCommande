/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.TypeCarton;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface TypeCartonDAO {
     public TypeCarton findById(int id);
          
		public void add(TypeCarton typeCarton);
		
		public  TypeCarton edit(TypeCarton e);
		
		public  void delete(TypeCarton e); 
		
		public List<TypeCarton> findAll();
}
