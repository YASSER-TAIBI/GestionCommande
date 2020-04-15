package dao.Manager;

import java.util.List;

import dao.Entity.FamilleArticle;

public interface FamilleArticleDAO {
	
	public  FamilleArticle findById(int id);
	
	public   List<FamilleArticle> findAll();
        
        public void add(FamilleArticle e);
             
        public FamilleArticle edit(FamilleArticle e);
                
        public void delete(FamilleArticle e) ;
        
        public List<FamilleArticle> findCategorieMpByID(int id);

}
