package dao.Manager;

import java.util.List;

import dao.Entity.Magasin;

public interface MagasinDAO {
	
	public  void add(Magasin e);
	
	public  Magasin edit(Magasin e);
	
	public  void delete(int id); 
	
	public List<Magasin> findAll();
        
        public Magasin findById(int id);
        
        public Magasin findStockByMagasinMP(int id);
        
        public List<Magasin> findByAllDepot(int idDepot);
        
        public List<Magasin> findByDepot(int idDepot);

        public List<Magasin> findByDepotOulmes(int idDepot);
}
