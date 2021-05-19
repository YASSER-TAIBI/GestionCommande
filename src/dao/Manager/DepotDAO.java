package dao.Manager;

import java.util.List;

import dao.Entity.Depot;
import dao.Entity.Magasin;

public interface DepotDAO {
	
	public  void add(Depot e);
	
	public  Depot edit(Depot e);
	
	public  void delete(int id); 
	
	public List<Depot> findAll();
	
	public Depot findById(int id);
	
	public Depot findByCode(String code);
	
	public List<Magasin> listeMagasinByTypeMagasin(String codeType);
	
	public List<Magasin> listeMagasinByTypeMagasinDepot(int idDepot,String codeType);

	public List<Depot>  findDepotByCodeSaufEnParametre(String code);

        public List<Depot> findDepotBySiege(String code);
        
	public Magasin magasinByCode(String codeMagasin);
	
	public List<Magasin> listeMagasinByTypeMagasinDepotMachine(int idDepot,String codeType,String codeMachine);

	public List<Magasin> findMagasinByCodeSaufEnParametre(int idDepot,String codeCatMagasin, String typeMagasin);



	

}
