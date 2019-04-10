package data1.hib.no;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(schema = "Oblig3", name = "Avdeling")
public class Avdeling {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdelingId;
	private String navn;
	private int sjefId;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name = "SjefID", referencedColumnName = "AnsattID")
	private Ansatt ansatt;
	
	@OneToMany(mappedBy = "Avdeling", fetch = FetchType.EAGER)//for det heletiden automatisk
	private List<Ansatt> ansatte;

	public Avdeling(){
		
	}
	
	public List<Ansatt> getList(){
		return ansatte;
	}
	
	public void fjernAnsatte(Ansatt ansatt) {
		ansatte.remove(ansatt);
	}
	
	public void leggtilAnsatte(Ansatt ansatt) {
		ansatte.add(ansatt);
	}
	
	public int getSjefId() {
		return sjefId;
	}
	
	public void setSjefId(int sjefId) {
		this.sjefId = sjefId;
	}

	public int getAvdelingId() {
		return avdelingId;
	}

	public void setAvdelingId(int avdelingId) {
		this.avdelingId = avdelingId;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	@Override
	public String toString() {
		return "Avdeling \nAvdelingId: " + avdelingId + "\nNavn: " + navn + "\nSjefId: " + sjefId + "\n"
				+ "Sjef:\n" + ansatt.getFornavn().toString() + " " + ansatt.getEtternavn().toString()
				+ "\nAnsatte:\n" + ansatte + "\n\n";
	}
	
//	public String toStringSjef() {
//		//Ansatt sjef = ansatte.get(0);
//		//ansatte.remove(sjef);
//		return "Sjef:\n" + ansatt
//				+ "\nAnsatte:\n" + ansatte + "\n\n";
//	}
//	
	
	

	
}
