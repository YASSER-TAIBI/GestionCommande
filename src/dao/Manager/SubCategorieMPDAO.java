package dao.Manager;

import java.util.List;

import dao.Entity.SubCategorieMp;

public interface SubCategorieMPDAO {
	


	public  void add(SubCategorieMp e);
	
	public  SubCategorieMp edit(SubCategorieMp e);
	
	public void delete(SubCategorieMp e);
        
	public List<SubCategorieMp> findAll();
	
	public SubCategorieMp findById(Long id);
	
        public List<SubCategorieMp> SubCategorieMpByBoxCartonAdf();


}
