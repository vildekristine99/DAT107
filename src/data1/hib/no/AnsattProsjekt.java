package data1.hib.no;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "Oblig3", name = "AnsattProsjekt")
public class AnsattProsjekt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansattProsjektId;
	private int timer;
	private String rolle;
	
	@ManyToOne
	@JoinColumn(name = "AnsattID", referencedColumnName = "AnsattID")
	private Ansatt ansatt;
	

	@ManyToOne
	@JoinColumn(name = "ProsjektID", referencedColumnName = "ProsjektID")
	private Prosjekt prosjekt;

	
	public int getAnsattProsjektId() {
		return ansattProsjektId;
	}


	public void setAnsattProsjektId(int ansattProsjektId) {
		this.ansattProsjektId = ansattProsjektId;
	}


	public int getTimer() {
		return timer;
	}


	public void setTimer(int timer) {
		this.timer = timer;
	}


	public String getRolle() {
		return rolle;
	}


	public void setRolle(String rolle) {
		this.rolle = rolle;
	}


	public Ansatt getAnsatt() {
		return ansatt;
	}


	public void setAnsatt(Ansatt ansatt) {
		this.ansatt = ansatt;
	}


	public Prosjekt getProsjekt() {
		return prosjekt;
	}


	public void setProsjekt(Prosjekt prosjekt) {
		this.prosjekt = prosjekt;
	}


	public void leggTilTimer(int timer2) {
		this.timer += timer2;
		
	}


	@Override
	public String toString() {
		return "AnsattProsjekt \nAnsattProsjektId: " + ansattProsjektId + "\nTimer: " + timer + "\nRolle: " + rolle
				+ "\nAnsatt: " + ansatt + "\nProsjekt: " + prosjekt + "\n";
	}
}

