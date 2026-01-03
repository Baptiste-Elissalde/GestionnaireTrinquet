package agenda;
import gestion.Client;

public class Horaire {
	private int heuredebut;
	private int heurefin;
	private boolean dispo;
	private Client client;
	
	public Horaire(int hdebut, int hfin) {
		this.heuredebut = hdebut;
		this.heurefin = hfin;
		this.dispo = true;
		
	}
	
	public void reserverHoraire(Client client) {
		this.dispo = false;
		this.client = client;
	}
	
	private String afficherDebut() {
		return heuredebut +"h00" ;
	}
	
	private String afficherFin() {
		return heurefin +"h00";
	}
	
	public boolean estDisponible() {
		return dispo;
	}
	
	public void afficherHoraire() {
		if(this.dispo) {
			System.out.println(afficherDebut() + " à " +afficherFin());
		}
	}
	
	public int getDebut() {
		return this.heuredebut;
	}
	public int getFin() {
		return this.heurefin;
	}
}

