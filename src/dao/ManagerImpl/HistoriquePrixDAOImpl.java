package dao.ManagerImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import Utils.HibernateUtil;
import dao.Entity.HistoriquePrix;
import dao.Manager.HistoriquePrixDAO;


public class HistoriquePrixDAOImpl implements HistoriquePrixDAO {
	Session session=HibernateUtil.openSession();

	public void add(HistoriquePrix e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public HistoriquePrix edit(HistoriquePrix e) {
		
	session.beginTransaction();
	HistoriquePrix p= (HistoriquePrix)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(HistoriquePrix e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}


	public List<HistoriquePrix> findAll() {
		return session.createQuery("select c from HistoriquePrix c ").list();
		}

	public HistoriquePrix findById(int id) {
		return (HistoriquePrix)session.get(HistoriquePrix.class, id);
		}


            public List<HistoriquePrix> findHistoriquePrixByFour(int idFour, String categorie) {
		
		Query query = session.createQuery("select c from HistoriquePrix c where c.fournisseur.id =:idFour and c.categorieMp.nom =:categorie");
		query.setParameter("idFour",idFour);
                query.setParameter("categorie",categorie);
		
                return query.list();
 }
            
                    public List<HistoriquePrix> findHistoriquePrixOulmesByFour(int idFour, String prixOulmes) {
		
		Query query = session.createQuery("select c from HistoriquePrix c where c.fournisseur.id =:idFour and c.prixOulmes.produit.libelle =:prixOulmes");
		query.setParameter("idFour",idFour);
                query.setParameter("prixOulmes",prixOulmes);
		
                return query.list();
 }

                    
                public List<HistoriquePrix> findHistoriquePrixOulmes() {
		
		Query query = session.createQuery("select c from HistoriquePrix c where c.categorieMp is null");
                return query.list();
 }
                                      
                public List<HistoriquePrix> findHistoriquePrix() {
		
		Query query = session.createQuery("select c from HistoriquePrix c where c.prixOulmes is null");
               
		
                return query.list();
 } 
                                      
                public List<Object[]> findByHistoriquePrixGlobalMP() {
		
		Query query = session.createQuery("select c.categorieMp, c.ancienPrix , c.nouveauPrix from HistoriquePrix c where c.id in (select MAX(c.id) from HistoriquePrix c group by c.categorieMp) and c.categorieMp is not null)");

                return query.list();
 }
                             
                public List<HistoriquePrix> findByCategorieNom(String nom) {
		
		Query query = session.createQuery("select c from HistoriquePrix c where c.categorieMp.nom =:nom");
		query.setParameter("nom",nom);
               
		
                return query.list();
 }
                
                public List<Object[]> findByHistoriquePrixGlobalMpAndGategorie(String categorie) {
		
		Query query = session.createQuery("select c.categorieMp, c.ancienPrix , c.nouveauPrix from HistoriquePrix c where c.id in (select MAX(c.id) from HistoriquePrix c group by c.categorieMp) and c.categorieMp.nom =:categorie)");

                query.setParameter("categorie",categorie);
                return query.list();
 }
                
                public List<Object[]> findByHistoriquePrixGlobalPF() {
		
		Query query = session.createQuery("select c.prixOulmes, c.ancienPrix , c.nouveauPrix from HistoriquePrix c where c.id in (select MAX(c.id) from HistoriquePrix c group by c.prixOulmes) and c.prixOulmes is not null)");

                return query.list();
 }
                
                public List<HistoriquePrix> findByPrixOulmesNom(String nom) {
		
		Query query = session.createQuery("select c from HistoriquePrix c where c.prixOulmes.produit.libelle =:nom");
		query.setParameter("nom",nom);
               
		
                return query.list();
 }
                 
                public List<Object[]> findByHistoriquePrixGlobalMpAndPrixOulmes(String prixOulmes) {
		
		Query query = session.createQuery("select c.prixOulmes, c.ancienPrix , c.nouveauPrix from HistoriquePrix c where c.id in (select MAX(c.id) from HistoriquePrix c group by c.prixOulmes) and c.prixOulmes.produit.libelle =:prixOulmes)");

                query.setParameter("prixOulmes",prixOulmes);
                return query.list();
 }
}
