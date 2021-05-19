package dao.Manager;

import dao.Entity.RegularisationAvoirOulmes;
import java.util.List;


public interface RegularisationAvoirOulmesDAO {
	
	public  void add(RegularisationAvoirOulmes e);
	
	public  RegularisationAvoirOulmes edit(RegularisationAvoirOulmes e);
	
	public  void delete(RegularisationAvoirOulmes e);
	
	public List<RegularisationAvoirOulmes> findAll();
	
	public RegularisationAvoirOulmes findById(int id);
       
        public List<RegularisationAvoirOulmes> findRegularisationAvoirOulmesBybonAvoir(String bonAvoir);
}
