package gestion;
import agenda.Calendrier;
import agenda.Jour;
import agenda.Date;
import java.time.LocalDate;

public class Trinquet {
	private static int compteurIdtrinquet = 1; 
	
	private int idTrinquet;
	private String nom;
	private Ville ville;
	private Calendrier planning;
	private boolean plein;
	private String adresse;
	private Gerant gerant;
	
	public Trinquet(String nom,Ville ville) {
		this.idTrinquet = compteurIdtrinquet;
		compteurIdtrinquet++;
		this.nom = nom;
		this.ville = ville;
		this.plein = false;
		this.planning = creerPlanning();
	}
	
	private Calendrier creerPlanning() {
		LocalDate aujourdHui = LocalDate.now();
		int j = aujourdHui.getDayOfMonth();
		int m = aujourdHui.getMonthValue();
		int a = aujourdHui.getYear();
		Date date = new Date(a,m,j);
		Jour jour = new Jour(date);
		Calendrier planning = new Calendrier(jour,14);
		return planning;
	}
	
	private void actualiserPlanning() {
		LocalDate aujourdHui = LocalDate.now();
		int j = aujourdHui.getDayOfMonth();
		int m = aujourdHui.getMonthValue();
		int a = aujourdHui.getYear();
		Date date = new Date(a,m,j);
		Jour jour = new Jour(date);
		Calendrier planning = new Calendrier(jour,14);
		this.planning = planning;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getId() {
		return this.idTrinquet;
	}
	
	public Ville getVille() {
		return this.ville;
	}
	
	public Calendrier getPlanning() {
		return this.planning;
	}
	public void afficherNom() {
		System.out.println(getNom());
	}
	
	public void afficherPlaning() {
		afficherNom();
		this.planning.afficherPlaning();
	}
	
	public void afficherJour(int i) {
		afficherNom();
		this.planning.getJour(i).afficherDisponibilité();
	}
	public void afficherTrinquet() {
		
	}
	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}
	public void afficherGerant() {
		if(this.gerant != null) {
			System.out.println(this.gerant.getNom() +" "+ this.gerant.getPrenom());
		}
		else System.out.println("On ne connait pas le gerant de ce trinquet, mystere ...");
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public void afficherAdresse() {
		System.out.println(this.adresse);
	}
	
	public void afficherNbDispo() {
		System.out.println("Il reste " + planning.nbHoraireDispo()+" créneau disponible du " 
	+ planning.getJour(0).getDate().afficherDate() + " au " +planning.getJour(planning.getTaille()-1).getDate().afficherDate());
	}
}
