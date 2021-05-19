/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailBonRetour;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailBonRetourDAO {

		
		public DetailBonRetour findById(int id);
		
		public void add(DetailBonRetour detailBonRetour);

		public  DetailBonRetour edit(DetailBonRetour e);
		
		public  void delete(DetailBonRetour e); 
		
		public List<DetailBonRetour> findAll();
                
                public List<DetailBonRetour>  findDetailBonRetourByBonRetour (int cmd);
                
                public DetailBonRetour findDetailBonRetourByDetailBonLivraison(String numCom,String numLiv, int mp);

                public List<DetailBonRetour>  findDetailBonRetourByType (String type);

            	public List<DetailBonRetour>  findDetailBonRetourByMpAndFourAndClient (String type, String req);

//                public List<DetailBonRetour>  findDetailBonRetourByListFour (String four, String type);
//
//                public List<DetailBonRetour>  findDetailBonRetourByFourAndMp (String mp ,String four,String type );
                
                public List<Object[]> findBySituationRetourManque();
                
                public List<DetailBonRetour>  findByMatierePremier (int mp);
                
                public List<Object[]> findBySituationRetourManquePF();
                
                public List<DetailBonRetour>  findByPrixOulmes (int pf);
                
                public List<DetailBonRetour>  findByMatierePremierAndClient (int mp, String client );
                
                public List<DetailBonRetour>  findByPrixOulmesAndClient (int pf, String client);
}