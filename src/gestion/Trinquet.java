package gestion;
import agenda.Calendrier;
import agenda.Jour;
import agenda.Date;
import java.time.LocalDate;

public class Trinquet {
	
	private int idTrinquet;
	private String nom;
	private Ville ville;
	private Calendrier planning;
	private boolean plein;
	private String adresse;
	
	//Constructeur
	public Trinquet(int id,String nom,Ville ville) {
		this.idTrinquet = id;
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
	
	//Getter
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
	
	//Affichage
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
	public void afficherAdresse() {
		System.out.println(this.adresse);
	}
	
	public void afficherNbDispo() {
		System.out.println("Il reste " + planning.nbHoraireDispo()+" créneau disponible du " 
	+ planning.getJour(0).getDate().afficherDate() + " au " +planning.getJour(planning.getTaille()-1).getDate().afficherDate());
	}
	
	//Actualiser adresse
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
}
