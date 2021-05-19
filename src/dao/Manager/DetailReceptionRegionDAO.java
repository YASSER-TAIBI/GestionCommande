/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailReceptionRegion;
import java.util.List;

/**
 *
 * @author h
 */
public interface DetailReceptionRegionDAO {
    
              
    
		public DetailReceptionRegion findById(int id);
		
		public void add(DetailReceptionRegion detailReceptionRegion);

		public  DetailReceptionRegion edit(DetailReceptionRegion e);
		
		public  void delete(DetailReceptionRegion e); 
		
		public List<DetailReceptionRegion> findAll();
                
                public List<DetailReceptionRegion>  findReceptionRegionBydetailCom(int detailCom);

}
