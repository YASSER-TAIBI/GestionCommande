package dao.Manager;

import dao.Entity.FactureAvoirOulmes;
import java.util.List;


public interface FactureAvoirOulmesDAO {
	
	public  void add(FactureAvoirOulmes e);
	
	public  FactureAvoirOulmes edit(FactureAvoirOulmes e);
	
	public  void delete(FactureAvoirOulmes e); 
	
	public List<FactureAvoirOulmes> findAll();
	
	public FactureAvoirOulmes findById(int id);
        
        public List<FactureAvoirOulmes>  findAvoirOulmesByEtat(String etat);

	public int getMaxId();

        public List<Object[]> findBySituationGlobal();	
        
        public List<Object[]> findBySituationGlobalArticle(String code);
        
        public List<Object[]> findFactureOulmesByCodeAndClient(String code, String client);
         
        public List<Object[]> findBySituationGlobalClient();

}
