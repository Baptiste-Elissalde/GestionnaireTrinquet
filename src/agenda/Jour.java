package agenda;
import gestion.Client;

public class Jour {
	private Date date;
	private Horaire [] creneau;
	private boolean plein;
	
	//Constructeur
	/**
	 * creer une class jour a partir d une date avec pour atrtubuts une date, un tableau d horaire et un booleen plein
	 * @param Date date.
	 * @return .
	 */
	public Jour(Date date) {
		if (date.estValide()) { //si la date est valide on creer
			this.date = date;
			this.creneau = creerCreneau();
			this.plein = false;
		}
		else throw new IllegalArgumentException("Date invalide veuillez chnagez la date"); // sinon non
	}
	
	// Fonctions manipulation de l attribut creneau
	/**
	 * initialise creneau en remplissant d horaire de 8h a 23h
	 * @param .
	 * @return le tableau d horaire initialiser .
	 */
	private Horaire[] creerCreneau() {
		Horaire [] reservation = new Horaire[15];
		for(int i = 8; i<23;i++) {
			reservation[i-8] = new Horaire(i,i+1) ;
		}
		return reservation;
	}
	
	//Fonctions d affichages
	/**
	 * affiche le nombre de creneau disponible puis affiche les reservations disponible
	 * si pas de creneau disponible affiche "pas de creneau disponible"
	 * @param .
	 * @return .
	 */
	public void afficherDisponibilité() {
		if(!this.plein) {
			afficherDateReservation();
			System.out.println(nbHoraireDispo() +" créneau disponible");
			afficherReservation();
		}
		else {
			afficherDateReservation();
			System.out.println("Pas de créneau disponible");
		}
	}
	/**
	 * affiche la date de la reservation
	 * @param .
	 * @return .
	 */
	private void afficherDateReservation() {
		System.out.println("Créneau du " +this.date.afficherDate());
	}
	/**
	 * affiche la date du jour puis affiche les horaires de la journée
	 * @param .
	 * @return .
	 */
	public void afficherReservation() {
		afficherDateReservation();
		for(int i =0;i<15;i++) {
			this.creneau[i].afficherHoraire();
		}
	}
	/**
	 * affiche le nombre de disponibilités dans la journée
	 * @param .
	 * @return .
	 */
	public void afficherNbDispo() {
		System.out.println(nbHoraireDispo() +" créneau disponible");
	}
	
	//Fonctions getter
	/**
	 * acces a l attribut date de la classe jour
	 * @param .
	 * @return attribut date.
	 */
	public Date getDate() {
		return this.date;
	}
	/**
	 * accede a l heure demandé 
	 * @param heure demandé.
	 * @return horaire de l heure.
	 */
	public Horaire getHoraire(int heure) {
		int indice = heure-8;
		if(indice>=0 && indice<creneau.length) {
			return this.creneau[indice]; //si le creneau est valide c est bon
		}
		else {
			 throw new IllegalArgumentException(
				        "Heure invalide : " + heure + " (doit être entre 8 et 22)" // sinon erreur
				    );
		}
	}
	/**
	 * dit si le jour est plein cad si toute les horaire sont reservés 
	 * @param 
	 * @return l attribut plein .
	 */
	public boolean getPlein() {
		return this.plein;
	}
	/**
	 * accede a l attribut creneau, un tableau d horaire
	 * @param heure demandé.
	 * @return horaire de l heure.
	 */
	public Horaire[] getCreneau() {
		return creneau;
	}
	
	// Fonctions manipulations de la class Jour
	/**
	 * renvoie le jour suivant creer a partir de datesuivant
	 * @param .
	 * @return le jour suivant ce jour ci .
	 */
	public Jour jSuivant() {
		 return new Jour(this.date.dateSuivant());
		
	}
	/**
	 * reserve une horaire pour un client
	 * @param client qui reserve , heure du debut de la reservation.
	 * @return .
	 */
	public void reserverHoraire(Client client,int heure) {
		assert heure >= 8 && heure <23;
		getHoraire(heure-8).reserverHoraire(client); 
	}
	/**
	 * donne le nombre d hoaraire disponible restante
	 * @param .
	 * @return le nombre d horaire disponible restante.
	 */
	public int nbHoraireDispo() {
	    int cpt = 0;
	    for(int i = 8; i <= 22; i++) { 
	        if (getHoraire(i).estDisponible()) { 
	            cpt++;
	        }
	    }
	    return cpt;
	}
	/**
	 * met a plein le jour
	 * @param .
	 * @return .
	 */
	public void setPlein() {
		this.plein = nbHoraireDispo() == 0;
	}
	/**
	 * donne l indice de l heure recherché
	 * @param heuredebut cad le debut de l horaire qu on recherche.
	 * @return ieme position de l horaire si elle y est, - 1 sinon.
	 */
	public int chercherHoraireParHeure(int heureDebut) {
	    for (int i = 0; i < 15; i++) {
	        if (creneau[i].getDebut() == heureDebut) {
	            return i;
	        }
	    }
	    return -1;
	}

}
