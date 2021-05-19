package dao.Manager;

import dao.Entity.InitialEmballage;
import java.util.List;


public interface InitialEmballageDAO {
	
	public  void add(InitialEmballage e);
	
	public  InitialEmballage edit(InitialEmballage e);
	
	public  void delete(InitialEmballage e); 
	
	public List<InitialEmballage> findAll();
        
        public List<Object[]> findBySituationEmballage();
        
        public List<Object[]> findBySituationEmballageAndClientAndFour(String client, String four);
        
        public List<Object[]> findBySituationEmballageAndMois(int prixOulmes);

        public List<Object[]> findBySituationEmballageAndMoisAndClientAndFour(int prixOulmes,String client, String four);
     
}
