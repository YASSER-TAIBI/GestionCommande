/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.DetailCompte;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailCompteDAO {
     public DetailCompte findById(int id);
		
		public void add(DetailCompte detailCompte);
		
		public  DetailCompte edit(DetailCompte e);
		
		public  void delete(DetailCompte e); 
		
		public List<DetailCompte> findAll();
                
                public List<DetailCompte> findFourByRechercheNom(String four) ;
                
                public List<DetailCompte> findDetailCompteByCompte(int idCompte);
                
                public List<DetailCompte> findFourByRechercheNomReglement(int four,int clt) ;
                   
                public List<DetailCompte> findClientByDetailCompte(String report, int clt, int idCompte);
                     
                public  DetailCompte findByDetailCompte(String recpBL) ;
                
                public List<DetailCompte> findFilterDetailCompteByDateOperationAndFour(Date dateDebut,Date dateFin, int four);
                
                public List<DetailCompte> findFilterDetailCompteByDateOperationAndFourAndClient(Date dateDebut,Date dateFin, int four, int client);
                
                public List<Object[]> findBy(Date dateDebut,int client, int comptFour);
                
                public List<Object[]> findBySoldeFA(String req);
                
                public  Date findByMinDate();
}
