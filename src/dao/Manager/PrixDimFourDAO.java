/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.MatierePremier;
import dao.Entity.PrixDimFour;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface PrixDimFourDAO {
    
       
       public PrixDimFour findById(int id);
       
       public void add(PrixDimFour prixDimFour);
		
       public  PrixDimFour edit(PrixDimFour e);
		
       public  void delete(PrixDimFour e); 
		
       public List<PrixDimFour> findAll();
       
       public PrixDimFour findDimensionByPrix(int idDim, int idFour, int idMP);
     
       public List<PrixDimFour> findDimensionByFournisseur(String codeFour);
 
       public List<PrixDimFour> findMatierePremierByRecherche(String text,String codeFour);

 

    
}
