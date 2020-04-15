package dao.Manager;

import java.util.List;
import java.util.Map;

import dao.Entity.Magasin;
import dao.Entity.StockArt;

public interface StockArtDAO {
	
	public  void add(StockArt e);
	
	public  StockArt edit(StockArt e);
	
	public  void delete(int id); 
	
	public List<StockArt> findAll();
	
	public StockArt findById(int id);
        
        public StockArt findStockByMagasinArt(int idPOlm, int idMA);
	

        
       
        
	
	

}
