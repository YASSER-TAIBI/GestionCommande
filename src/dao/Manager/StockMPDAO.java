package dao.Manager;

import java.util.List;
import java.util.Map;

import dao.Entity.Magasin;
import dao.Entity.StockMP;

public interface StockMPDAO {
	
	public  void add(StockMP e);
	
	public  StockMP edit(StockMP e);
	
	public  void delete(int id); 
	
	public List<StockMP> findAll();
	
	public List<StockMP> findAllByMagasin(int idMagasin);
	
	public StockMP findById(int id);
	
	public StockMP findStockByMatierePremier(int id);
	
	public StockMP findStockByMagasinMP(int idMP,int idMagasin);
	
	public List<StockMP> listeStockNouveauMP(int idMagasin);
	
	public List<StockMP> findSockNonVideByMagasin(int idMagasin);

	public List<Magasin> findMagasinByCodeSaufEnParametre(int idMagasin,String codeCatMagasin, String typeMagasin);

	public List<StockMP> findStockMin(int  idDepot);

	//public Map<Integer, Float> findStockTotalByMagasin(int idDepot);
        
        public List<StockMP> findStockMPByCateg(int idcatg);
        
        public List<StockMP> findStockMPBySubCateg(int idSubcatg);
        
        public List<StockMP> findStockMPByMp(String mp) ;
             
        public List<StockMP> findStockMPByCategAndSubCateg(int idcatg, int idSubcatg) ;
              
        public List<StockMP> findStockMPByCategAndMp(int idcatg, String mp);
        
        public List<StockMP> findStockMPByCategAndMpAndSubCateg(int idcatg, String mp, int idSubcatg);
          
        public List<StockMP> findStockMPBySubCategAndMp(String mp, int idSubcatg);
        
	
	

}
