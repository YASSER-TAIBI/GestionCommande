package dao.Manager;

import dao.Entity.Article;
import dao.Entity.Produit;
import java.util.List;


public interface ProduitDAO {
	
	public  void add(Produit e);
	
	public  Produit edit(Produit e);
	
	public  void delete(Produit e); 
	
	public List<Produit> findAll();
	
        public Produit findByCode(String code);

}
