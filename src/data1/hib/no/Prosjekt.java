package data1.hib.no;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "Oblig3", name = "Prosjekt")
public class Prosjekt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektId;
	private String navn;
	private String beskrivelse;
	
	@OneToMany(mappedBy = "Prosjekt", fetch = FetchType.EAGER)
	private List<AnsattProsjekt> deltagere;
	
	public Prosjekt() {
		
	}
	
	@Override
	public String toString() {
		return "Prosjekt \nProsjektId=" + prosjektId + ", navn=" + navn + ", beskrivelse=" + beskrivelse + ", deltagere="
				+ deltagere + "\n";
	}

	public int getProsjektId() {
		return prosjektId;
	}

	public void setProsjektId(int prosjektId) {
		this.prosjektId = prosjektId;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public List<AnsattProsjekt> getDeltagere() {
		return deltagere;
	}

	public void setDeltagere(List<AnsattProsjekt> deltagere) {
		this.deltagere = deltagere;
	}

}
