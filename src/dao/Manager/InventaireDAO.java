package dao.Manager;

import dao.Entity.Inventaire;
import java.util.List;


public interface InventaireDAO {
	
	public  void add(Inventaire e);
	
	public  Inventaire edit(Inventaire e);
	
	public  void delete(Inventaire e); 
	
	public List<Inventaire> findAll();
	
	public Inventaire findById(int id);
        
	public List<Inventaire> findByAllInventaire(String req);

	

}
