package dao.Manager;
import java.util.List;
import dao.Entity.Menu;

public interface MenuDAO {
	
	public  void add( Menu e);
	
	public   Menu edit( Menu e);
	
	public  void delete(int id); 
	
	public List<Menu> findAll();
	
	public  Menu findById(int id);
	
	public  Menu findByCode(String code);

}
