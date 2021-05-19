package dao.Manager;

import dao.Entity.HistoriquePrix;
import java.util.List;


public interface HistoriquePrixDAO {
	
	public  void add(HistoriquePrix e);
	
	public  HistoriquePrix edit(HistoriquePrix e);
	
	public  void delete(HistoriquePrix e); 
	
	public List<HistoriquePrix> findAll();
	
	public HistoriquePrix findById(int id);

        public List<HistoriquePrix> findHistoriquePrixByFour(int idFour, String categorie);

        public List<HistoriquePrix> findHistoriquePrixOulmesByFour(int idFour, String prixOulmes) ;

        public List<HistoriquePrix> findHistoriquePrixOulmes();

        public List<HistoriquePrix> findHistoriquePrix();
        
        public List<Object[]> findByHistoriquePrixGlobalMP();
        
        public List<HistoriquePrix> findByCategorieNom(String nom);
        
        public List<Object[]> findByHistoriquePrixGlobalMpAndGategorie(String categorie);
        
        public List<Object[]> findByHistoriquePrixGlobalPF();

        public List<HistoriquePrix> findByPrixOulmesNom(String nom);

        public List<Object[]> findByHistoriquePrixGlobalMpAndPrixOulmes(String prixOulmes);
        
}
