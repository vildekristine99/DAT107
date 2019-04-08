package data1.hib.no;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProsjektEAO {
	private EntityManagerFactory emf;
	
	public ProsjektEAO() {
    	emf = Persistence.createEntityManagerFactory("oblig3PU");
    }
	
	public void leggTilProsjekt(String navn, String beskrivelse) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Prosjekt nyttProsjekt = new Prosjekt();
		nyttProsjekt.setNavn(navn);
		nyttProsjekt.setBeskrivelse(beskrivelse);
		
		try {
			tx.begin();
			em.persist(nyttProsjekt);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	
	public void registrerProsjektdeltagelse(int ansattId, int prosjektId, int timer, String rolle) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Ansatt ansatt = em.find(Ansatt.class, ansattId);
		
		Prosjekt prosjekt = em.find(Prosjekt.class, prosjektId);
		
		AnsattProsjekt nyAp = new AnsattProsjekt();
		
		nyAp.setTimer(timer);
		nyAp.setRolle(rolle);
		nyAp.setAnsatt(ansatt);
		nyAp.setProsjekt(prosjekt);	
		
		
		try {
			tx.begin();
			em.persist(nyAp);
			prosjekt.getDeltagere().add(nyAp);
			ansatt.getProsjekter().add(nyAp);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public void foerTimer(int timer, int ansattProsjektId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		AnsattProsjekt ap = em.find(AnsattProsjekt.class, ansattProsjektId);
		
		List<Ansatt> ansatte;
		
		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT t FROM Ansatt t", 
					Ansatt.class);
			ansatte = query.getResultList();
			System.out.println(ansatte.toString());
		
		} finally {
			em.close();
		}
	}
	
	public void utskriftProsjekter(int prosjektId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		//Utskrift av info om prosjekt, inkl. liste av deltagere med rolle og timer, og totalt timetall for prosjektet
		Prosjekt prosjekt = em.find(Prosjekt.class, prosjektId);
		
		
		try {
			tx.begin();
			
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		
	}
}
