package agenda;
import gestion.Client;

public class Horaire {
	private int heuredebut;
	private int heurefin;
	private boolean dispo;
	private Client client;
	
	//Constructeur
	/**
	 * constructeur de la classe horaire
	 * @param int hdebut,int hfin
	 * @return renvoie l horaire creer avec pour dispo, true par defaut, l atribut client n est pas initialisé .
	 */
	public Horaire(int hdebut, int hfin) {
		this.heuredebut = hdebut;
		this.heurefin = hfin;
		this.dispo = true;
		
	}
	
	//Fonctions de manipulation de la classe horaire
	/**
	 * reserve l horaire pour un client donnée
	 * @param client , client qui reserve .
	 * @return .
	 */
	public void reserverHoraire(Client client) {
		this.dispo = false;
		this.client = client;
	}
	/**
	 * renvoie true si l horaire est disponible false sinon
	 * @param 
	 * @return l attribut dispo.
	 */
	public boolean estDisponible() {
		return dispo;
	}
	
	//Fonctions d affichage de la class
	/**
	 * affiche l horaire sous la forme hdebut à hfin
	 * @param 
	 * @return 
	 */
	public void afficherHoraire() {
		if(this.dispo) {
			System.out.println(afficherDebut() + " à " +afficherFin());
		}
	}
	/**
	 * affiche l heure du debut
	 * @param .
	 * @return .
	 */
	private String afficherDebut() {
		return heuredebut +"h00" ;
	}
	/**
	 * affiche l heure de fin
	 * @param  .
	 * @return .
	 */
	private String afficherFin() {
		return heurefin +"h00";
	}
	
	//Fonctions getter
	/**
	 * getter qui renvoie l attribut heuredebut
	 * @param .
	 * @return attribut heuredebut .
	 */
	public int getDebut() {
		return this.heuredebut;
	}
	/**
	 * getter qui renvoie l attribut heurefin
	 * @param .
	 * @return attribut heurefin .
	 */
	public int getFin() {
		return this.heurefin;
	}

}

