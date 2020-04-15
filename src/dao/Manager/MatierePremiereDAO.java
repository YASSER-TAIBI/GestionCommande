package dao.Manager;

import java.util.List;

import dao.Entity.CategorieMp;
import dao.Entity.MatierePremier;
import javafx.collections.ListChangeListener;

public interface MatierePremiereDAO {
	


	public  void add(MatierePremier e);
	
	public  MatierePremier edit(MatierePremier e);
	
	public void delete(MatierePremier e) ;
	
	public List<MatierePremier> findAll();
	
	public MatierePremier findById(int id);
	
	public MatierePremier findByCode(String code);
	
	public List<MatierePremier> findMatierePremierSaufCatTHE();
	
	public  List<CategorieMp>  listeCategorieTHE();
	
	public List<MatierePremier> listeMatierePremierByCategorie(String idCat);

        public List<MatierePremier> listeMatierePremierByMp(String Mp);
        
        public List<MatierePremier> listeMatierePremierByNomMp(String nom);
 


}
