package data1.hib.no;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
public class AvdelingEAO {
	
	private EntityManagerFactory emf;
	
	public AvdelingEAO() {
    	emf = Persistence.createEntityManagerFactory("oblig3PU");
    }
	
	public Avdeling finnAvdelingMedId(int id) {
		EntityManager em = emf.createEntityManager();
		
		try {
			return em.find(Avdeling.class, id);
		
		} finally {
			em.close();
		}
	}
	
	public void oppdaterAvdelingAnsatt(Ansatt ansatt, int avdelingId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			if(ansatt.getAnsattId() != finnAvdelingMedId(ansatt.getAvdelingId()).getSjefId()) {
				ansatt.setAvdelingId(avdelingId);
				em.merge(ansatt);
			} else {
				System.out.println();
			}
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
	
	public void leggTilAvdeling(Avdeling avdeling) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		AnsattEAO ansattEAO = new AnsattEAO();
		
		try {
			tx.begin();
			em.persist(avdeling);
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
