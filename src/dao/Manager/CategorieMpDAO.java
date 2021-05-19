package dao.Manager;

import java.util.List;

import dao.Entity.CategorieMp;

public interface CategorieMpDAO {
	
	public  CategorieMp findById(int id);
	
	public   List<CategorieMp> findAll();
        
        public void add(CategorieMp e);
             
        public CategorieMp edit(CategorieMp e);
                
        public void delete(CategorieMp e) ;
        
        public List<CategorieMp> findCategorieMpByID(int id);

        public List<CategorieMp> findCategorieMpByBox(String box);
        
        public CategorieMp findCategorieMpByCat(String cat);
        
        public List<CategorieMp> findByCodeCategorieMp(String code);

        public List<CategorieMp> findBylibelleCategorieMp(String lib);
}
