package dao.Manager;

import dao.Entity.Article;
import java.util.List;


public interface ArticleDAO {
	
	public  void add(Article e);
	
	public  Article edit(Article e);
	
	public  void delete(Article e); 
	
	public List<Article> findAll();
	
	public Article findById(int id);
        
        public Article findArticleByCodeArt( String codeArt) ;
	

	

}
