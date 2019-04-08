package data1.hib.no;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Meny {
	private Scanner sc = new Scanner(System.in);
	private AnsattEAO ansattEAO;
	private AvdelingEAO avdelingEAO;
	private List<Ansatt> ansatte;
	private Ansatt a;
	private Avdeling av;
	private boolean run;
	private ProsjektEAO prosjektEAO;
	
	public Meny() {
		ansattEAO = new AnsattEAO();
		avdelingEAO = new AvdelingEAO();
		prosjektEAO = new ProsjektEAO();
		run = true;
	}
	
	public void start() {
		while(run) {
			menyValg();
		}
		sc.close();
	}
	
	public void menyValg() {
		String meny = "1. Søke etter ansatt med ansatt-id\n"
				+ "2. Søke etter ansatt på brukernavn (initialer)\n"
				+ "3. Utlisting av alle ansatte\n"
				+ "4. Oppdatere en ansatt sin stilling\n"
				+ "5. Legge inn en ny ansatt\n"
				+ "6. Søk etter avdeling med avdelingsid\n"
				+ "7. Oppdater en ansatt sin avdeling\n"
				+ "8. Legg til avdeling\n"
				+ "9. Legg til prosjekt\n"
				+ "10. Registrer prosjektdeltagelse\n"
				+ "11. Føre timer til ansatt i prosjekt\n"
				+ "(12. Utskrift av infor om Prosjekt\n"
				+ "13. Avslutt";
		
		System.out.println(meny);
		int menyvalg = sc.nextInt();
		sc.nextLine();
		
		switch(menyvalg) {
		case(1):
			System.out.println("AnsattID: ");
			int ID = sc.nextInt();	
			sc.nextLine();
			a = ansattEAO.finnAnsattMedId(ID);
			System.out.println(a);
			break;
			
		case(2):
			System.out.println("Brukernavn: ");
			String brukernavn = sc.nextLine();	
			ansatte = ansattEAO.finnAnsattMedBrukernavn(brukernavn);
			System.out.println(ansatte);
			break;
			
		case(3):
			ansattEAO.utlistingAvAlleAnsatte();
			break;
		
		case(4):
			System.out.println("Ansatt sin ID: ");
			int id2 = sc.nextInt();
			sc.nextLine();
			a = ansattEAO.finnAnsattMedId(id2);
			System.out.println("Ny stilling: ");
			String nyStilling = sc.nextLine();
			ansattEAO.oppdaterStilling(a, nyStilling);
			break;
		
		case(5):
			System.out.println("Brukernavn: ");
			String brukernavn2 = sc.nextLine();
			System.out.println("Fornavn: ");
			String fornavn = sc.nextLine();
			System.out.println("Etternavn: ");
			String etternavn = sc.nextLine();
			System.out.println("Ansettelsedato: ");			
			String dato = sc.nextLine();
			LocalDate ansettelsedato = LocalDate.parse(dato);
			System.out.println("Stilling: ");
			String stilling = sc.nextLine();
			System.out.println("Mandeslonn: ");
			long mandeslonn = sc.nextLong();
			sc.nextLine();
			System.out.println("AvdelingsID: ");
			int avdelingsId = sc.nextInt();
			sc.nextLine();
			ansattEAO.leggTilAnsatt(brukernavn2, fornavn, 
					etternavn, ansettelsedato, stilling, mandeslonn, avdelingsId);
			break;
		
		case 6:
			System.out.println("AvdelingsID: ");
			int avdelingId = sc.nextInt();	
			sc.nextLine();
			av = avdelingEAO.finnAvdelingMedId(avdelingId);
			System.out.println(av);
			break;
			
		case 7:
			System.out.println("Ansatt sin ID: ");
			int id4 = sc.nextInt();
			sc.nextLine();
			a = ansattEAO.finnAnsattMedId(id4);
			System.out.println("Ny avdeling: ");
			int nyAvdeling = sc.nextInt();
			sc.nextLine();
			avdelingEAO.oppdaterAvdelingAnsatt(a, nyAvdeling);
			break;
		
		case 8:
			System.out.println("Avdelingsnavn: ");
			String aNavn = sc.nextLine();
			System.out.println("Sjefid: ");
			int sjefId = sc.nextInt();
			sc.nextLine();
			a = ansattEAO.finnAnsattMedId(sjefId);
			av = avdelingEAO.finnAvdelingMedId(a.getAvdelingId());
			if(a != null && av.getSjefId() != sjefId) {
				Avdeling avd = new Avdeling();
				avd.setNavn(aNavn);
				avd.setSjefId(sjefId);
				avdelingEAO.leggTilAvdeling(avd);
				ansattEAO.oppdaterAvdeling(a, avd.getAvdelingId());
			} else {
				System.out.println("Ansatt eksisterer ikke eller er sjef fra før");
				break;
			}
			break;
			
		case 9:
			System.out.println("Navn: ");
			String navn = sc.nextLine();
			System.out.println("Beskrivelse: ");
			String beskrivelse = sc.nextLine();
			prosjektEAO.leggTilProsjekt(navn, beskrivelse);
			break;
			
		case 10:
			System.out.println("AnsattID: ");
			int ansattId = sc.nextInt();
			sc.nextLine();
			System.out.println("ProsjektID: ");
			int prosjektId = sc.nextInt();
			sc.nextLine();
			System.out.println("Timer: ");
			int timer = sc.nextInt();
			sc.nextLine();
			System.out.println("Rolle: ");
			String rolle = sc.nextLine();
			prosjektEAO.registrerProsjektdeltagelse(ansattId, prosjektId, timer, rolle);
			break;
		
		case 11:
			System.out.println("Timer: ");
			int timer2 = sc.nextInt();
			sc.nextLine();
			System.out.println("AnsattProsjektID: ");
			int ansattProsjektId = sc.nextInt();
			sc.nextLine();
			prosjektEAO.foerTimer(timer2, ansattProsjektId);
			break;
			
		case 12:
			break;
			
		case 13: 
			String melding = "Avslutter programmet";
			System.out.println(melding);
			run = false;
			break;
		
		default:
			System.out.println("Ikke gyldig");
			
		}
		
		
	}
}
