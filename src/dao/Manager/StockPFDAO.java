package dao.Manager;

import java.util.List;

import dao.Entity.StockPF;

public interface StockPFDAO {
	
	public  void add(StockPF e);
	
	public  StockPF edit(StockPF e);
	
	public  void delete(int id); 
	
	public List<StockPF> findAll();
	
        public List<Object[]> findStockPFByMp(int mp, int magasin, String categMag);
	
        public StockPF findStockByMagasinMP(int idPF, int idMA);
}
