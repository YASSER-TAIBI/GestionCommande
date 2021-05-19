package dao.Manager;

import dao.Entity.AvoirOulmes;
import java.math.BigDecimal;
import java.util.List;


public interface AvoirOulmesDAO {
	
	public  void add(AvoirOulmes e);
	
	public  AvoirOulmes edit(AvoirOulmes e);
	
	public  void delete(AvoirOulmes e);
	
	public List<AvoirOulmes> findAll();
	
	public AvoirOulmes findById(int id);
        
        public List<AvoirOulmes>  findAvoirOulmesByEtat(String etat);

	public List<Object[]> findBySituationGlobal();
        
	public List<Object[]> findBySituationGlobalArticle(String code);

        public List<AvoirOulmes> findAvoirOulmesByBonAvoir(String bonAv, String etat);

        public List<AvoirOulmes>  findAvoirOulmesByClient(String client);

        public List<AvoirOulmes>  findAvoirOulmesByBonAvoirAndClient(String bonAv ,String client);
        
        public List<AvoirOulmes>  findAvoirOulmesByBonAvoir();
        
        public AvoirOulmes findBonRetourByNumCommAndNumLiv(String bonAvoir) ;
        
        public List<AvoirOulmes> findByAllFilter(String etat, String req);
        
        public List<Object[]> findBySituationGlobalClient();
        
//        public BigDecimal findByLivGMS(int mois);
}
