package data1.hib.no;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(schema = "Oblig3", name = "Ansatt")
public class Ansatt {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansattId;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansettelsedato;
	private String stilling;
	private long mandeslonn;
	private int avdelingId;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "AvdelingID", referencedColumnName = "AvdelingID")
	private Avdeling avdeling;
		
	@OneToMany(mappedBy = "Ansatt", fetch = FetchType.EAGER)
	private List<AnsattProsjekt> prosjekter;
	
	public Ansatt() {
		
	}
	
	
	public Avdeling getAvdeling() {
		return avdeling;
	}


	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}


	public List<AnsattProsjekt> getProsjekter() {
		return prosjekter;
	}


	public void setProsjekter(List<AnsattProsjekt> prosjekter) {
		this.prosjekter = prosjekter;
	}


	public int getAvdelingId() {
		return avdelingId;
	}

	public void setAvdelingId(int avdelingId) {
		this.avdelingId = avdelingId;
	}
	
	public int getAnsattId() {
		return ansattId;
	}
	
	public void setAnsattId(int ansattId) {
		this.ansattId = ansattId;
	}
	
	public String getBrukernavn() {
		return brukernavn;
	}
	
	public void setBrukernavn(String brukeranavn) {
		this.brukernavn = brukeranavn;
	}
	
	public String getFornavn() {
		return fornavn;
	}
	
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	
	public String getEtternavn() {
		return etternavn;
	}
	
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	
	public LocalDate getAnsettelsedato() {
		return ansettelsedato;
	}
	
	public void setAnsettelsedato(LocalDate ansettelsedato) {
		this.ansettelsedato = ansettelsedato;
	}
	
	public String getStilling() {
		return stilling;
	}
	
	public void setStilling(String stilling) {
		this.stilling = stilling;
	}
	
	public long getMandeslonn() {
		return mandeslonn;
	}
	
	public void setMandeslonn(long mondeslonn) {
		this.mandeslonn = mondeslonn;
	}

	/*public Avdeling getAvdeling() {
		return avdeling;
	}

	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}*/

	@Override
	public String toString() {
		return "Ansatt \nAnsattId: " + ansattId + "\nBrukernavn: " + brukernavn + "\nFornavn: " + fornavn + "\nEtternavn: "
				+ etternavn + "\nAnsettelsedato: " + ansettelsedato + "\nStilling: " + stilling + "\nMandeslonn: "
				+ mandeslonn + "\nAvdeling: " + avdelingId + "\nProsjketer: "+prosjekter+"\n";
	}


	
	
	
}
