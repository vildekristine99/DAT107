package data1.hib.no;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class AnsattEAO {
	private EntityManagerFactory emf;

    public AnsattEAO() {
    	emf = Persistence.createEntityManagerFactory("oblig3PU");
    }

	public Ansatt finnAnsattMedId(int ansattId) {
		EntityManager em = emf.createEntityManager();
	
		try {
			return em.find(Ansatt.class, ansattId);
		
		} finally {
			em.close();
		}
	}
	
	public List<Ansatt> finnAnsattMedBrukernavn(String brukernavn) {
		EntityManager em = emf.createEntityManager();
		
		List<Ansatt> ansatte;
		
		try {
			//https://docs.oracle.com/javaee/6/tutorial/doc/bnbtg.html
			TypedQuery<Ansatt> query = em.createQuery(
					"SELECT t FROM Ansatt t WHERE t.brukernavn LIKE :brukernavn", Ansatt.class);
			query.setParameter("brukernavn", brukernavn);
			ansatte = query.getResultList();
		
		} finally {
			em.close();
		}
		return ansatte;
	}
	
	public void utlistingAvAlleAnsatte() {
		EntityManager em = emf.createEntityManager();
		
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
	
	public void oppdaterStilling(Ansatt ansatt, String nyStilling) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			ansatt.setStilling(nyStilling);
			em.merge(ansatt);
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
	
	public void leggTilAnsatt(String brukernavn, String fornavn, 
			String etternavn, LocalDate ansettelsedato, String stilling, long mandeslonn, int avdelingsId) {
		
		Ansatt nyAnsatt = new Ansatt();
		nyAnsatt.setBrukernavn(brukernavn);
		nyAnsatt.setFornavn(fornavn);
		nyAnsatt.setEtternavn(etternavn);
		nyAnsatt.setAnsettelsedato(ansettelsedato);
		nyAnsatt.setStilling(stilling);
		nyAnsatt.setMandeslonn(mandeslonn);
		nyAnsatt.setAvdelingId(avdelingsId);
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(nyAnsatt);
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
	
	public void oppdaterAvdeling(Ansatt ansatt, int avdelingId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Avdeling gammelAvdeling = em.find(Avdeling.class, ansatt.getAvdelingId());
		AvdelingEAO avdelingEAO = new AvdelingEAO();
		Avdeling nyAvdeling = avdelingEAO.finnAvdelingMedId(avdelingId);
		
		try {
			if(ansatt.getAnsattId() != gammelAvdeling.getSjefId() ) {//(ansatt != null) && 
				tx.begin();
				gammelAvdeling.fjernAnsatte(ansatt);
				ansatt.setAvdelingId(avdelingId);
				nyAvdeling.leggtilAnsatte(ansatt);
				em.merge(ansatt);
				em.merge(gammelAvdeling);
				em.merge(nyAvdeling);
				tx.commit();
			} else {
				System.out.println("Kan ikke vere sjef i to ulike avdelinger");
			}
		}catch(Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		}finally {
			em.close();
		}
		
	}
	
	
}
