package dao.Manager;

import java.util.List;

import dao.Entity.SousFamilleArticle;

public interface SousFamilleArticleDAO {
	
	public  SousFamilleArticle findById(int id);
	
	public   List<SousFamilleArticle> findAll();
        
        public void add(SousFamilleArticle e);
             
        public SousFamilleArticle edit(SousFamilleArticle e);
                
        public void delete(SousFamilleArticle e) ;
        


}
