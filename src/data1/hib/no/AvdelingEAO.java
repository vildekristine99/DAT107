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
	
	
}
