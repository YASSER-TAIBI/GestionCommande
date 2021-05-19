/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailAvoirOulmes;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailAvoirOulmesDAO {
		
		public DetailAvoirOulmes findById(int id);
		
		public void add(DetailAvoirOulmes detailBonRetour);

		public  DetailAvoirOulmes edit(DetailAvoirOulmes e);
		
		public  void delete(DetailAvoirOulmes e); 
		
		public List<DetailAvoirOulmes> findAll();
                
                public List<DetailAvoirOulmes> findByAvoirOulmes(int idAvoirOulmes);
                
                public List<DetailAvoirOulmes> findByEtat(String etat);
                
                public List<DetailAvoirOulmes> findByAllFilter(String etat, String req);
                
                public List<Object[]> findBySitiationGlobalAvoir();
                
                public List<Object[]> findBySitiationGlobalAvoirWithCodeArt(String code);
                
                public List<DetailAvoirOulmes> findByPrixOulmes(String libelle);

                public List<DetailAvoirOulmes> findByPrixOulmesWithCodeArt(String code, String req);
                
                public List<Object[]> findByCodeAndClient(String code, String client, String req);
                
                public List<Object[]> findByAvoirEmballage();
                
                public List<Object[]> findByAvoirEmballageAndClientAndFour(String client, String four);
                
                public List<Object[]> findByAvoirEmballageAndMois(int prixO);

                public List<Object[]> findByAvoirEmballageAndMoisAndClientAndFour(int prixO, String client, String four);
                
                public BigDecimal findByLivGMS(int mois);
}
