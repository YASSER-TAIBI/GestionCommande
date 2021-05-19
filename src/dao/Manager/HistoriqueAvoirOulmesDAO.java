package dao.Manager;

import dao.Entity.HistoriqueAvoirOulmes;
import java.util.List;


public interface HistoriqueAvoirOulmesDAO {
	
	public  void add(HistoriqueAvoirOulmes e);
	
	public  HistoriqueAvoirOulmes edit(HistoriqueAvoirOulmes e);
	
	public  void delete(HistoriqueAvoirOulmes e);
	
	public List<HistoriqueAvoirOulmes> findAll();
	
	public HistoriqueAvoirOulmes findById(int id);
       
}
