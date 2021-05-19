package dao.Manager;
import java.util.List;
import dao.Entity.Client;


public interface ClientDAO {
	
	public void add(Client e);
	
	public Client edit(Client e);
	
	public void delete(int id); 
	
	public List<Client> findAll();
	
	public Client findById(int id);
	
}
