/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Manager;

import dao.Entity.Commande;
import dao.Entity.DetailCommande;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author h
 */
public interface DetailCommandeDAO {
     public  DetailCommande findUtilisateurByLoginMotPasse(String login,String motPasse);
		
    public DetailCommande findById(int id);
		
    public void add(DetailCommande detailCommande);
		
    public  DetailCommande edit(DetailCommande e);
		
    public  void delete(DetailCommande e); 
       
    public List<DetailCommande> findByEtat(String etat) ;
    
    public List<DetailCommande> findCommanByProduit(int produit ,String etat);
    
    public List<DetailCommande> findDetailCommandeByFournisseur(int idFour,String etat);
    
    public List<DetailCommande> findDetailCommandeByMp(String Mp, String etat);
      
    public List<DetailCommande> findFilterDetailCommandeByDateCommande(Date dateDebut,Date dateFin,String etat);
    
    public List<DetailCommande> findDetailCommandeByEtat(Commande commande, String etat);
    
    public List<DetailCommande> findDetailNumCommandeByEtat(String Numcom, String etat);
    
    public List<DetailCommande> findFilterDetailCommandeByDateCommandeAndProduit(Date dateDebut,Date dateFin,int produit,String etat);
     
    public List<DetailCommande> findFilterDetailCommandeByDateCommandeAndFour(Date dateDebut,Date dateFin,int idFour,String etat);
    
    public List<DetailCommande> findDetailCommandeByFournisseurAndSubCategorie(int idFour, int produit,String etat);
    
    public List<DetailCommande> findDetailCommandeByFournisseurAndSubCategorieAndDate(Date dateDebut, Date dateFin ,int idFour, int produit ,String etat);
    
    public List<DetailCommande> findDetailCommandeByMpAndDate(Date dateDebut, Date dateFin , String Mp ,String etat);
    
    public List<DetailCommande> findDetailCommandeByMpAndSubCategorie(String Mp , int produit,String etat);
      
    public List<DetailCommande> findDetailCommandeByMpAndDateAndSubCategorie(Date dateDebut, Date dateFin , String Mp , int produit,String etat);
    
    public List<DetailCommande> findDetailCommandeByMpAndFour(String Mp ,int idFour,String etat);
    
    public List<DetailCommande> findDetailCommandeByMpAndDateAndFour(Date dateDebut, Date dateFin , String Mp , int idFour,String etat);
    
    public List<DetailCommande> findDetailCommandeByMpAndFourAndSubCategorie(String Mp ,int idFour,int produit,String etat);
    
    public List<DetailCommande> findDetailCommandeByMpAndDateAndFourAndSubCategorie(Date dateDebut, Date dateFin ,String Mp ,int produit ,int idFour,String etat); 
    
//    public List<Object[]> findDetailCommandeByQteRestee(int mp, int depot, String etat, int util, String magasin );
		
    public List<DetailCommande> findDetailCommandeByMpAndEtat(int mp, String etat);
    
    public DetailCommande findDetailCommandeByBonRetour(String Mp ,String idFour,String etat,String nCom);
    
     public List<DetailCommande> findCommanByEtatCmd(String etatCmd ,String etat );
		
     public List<DetailCommande> findDetailCommandeBySubCategorieAndEtatCmd(int produit,String etatCmd ,String etat);

     public List<DetailCommande> findDetailCommandeByFournisseurAndEtatCmd(int idFour ,String etatCmd , String etat);

     public List<DetailCommande> findDetailCommandeByMpAndEtatCmd(String Mp ,String etatCmd , String etat);

     public List<DetailCommande> findFilterDetailCommandeByDateCommandeAndEtatCmd(Date dateDebut,Date dateFin, String etatCmd ,String etat);

     public List<DetailCommande> findDetailCommandeByFournisseurAndSubCategorieAndEtatCmd(int produit,int idFour ,String etatCmd , String etat);

     public List<DetailCommande> findDetailCommandeByMpAndSubCategorieAndEtatCmd(int produit, String Mp ,String etatCmd , String etat);

     public List<DetailCommande> findDetailCommandeByDateAndSubCategorieAndEtatCmd(Date dateDebut, Date dateFin ,int produit ,String etatCmd ,String etat);

     public List<DetailCommande> findDetailCommandeByDateAndFourAndSubCategorieAndEtatCmd(Date dateDebut, Date dateFin ,int produit, int idFour  ,String etatCmd ,String etat);

     public List<DetailCommande> findDetailCommandeByMpAndSubCategorieAndFourAndEtatCmd(int produit, int idFour , String Mp ,String etatCmd , String etat);

     public List<DetailCommande> findDetailCommandeByMpAndDateAndFourAndSubCategorieAndEtatCmd(Date dateDebut, Date dateFin ,int produit, String Mp, int idFour  ,String etatCmd ,String etat);
		
    
}
